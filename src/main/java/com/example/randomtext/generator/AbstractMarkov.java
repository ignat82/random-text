package com.example.randomtext.generator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Getter
public class AbstractMarkov implements Markov {
    private final Random myRandom;

    private String myText;

    public void setRandom(int seed) {
        myRandom.setSeed(seed);
    }

    public void setTraining(String trainingString) {
        myText = trainingString.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        return sb.toString();
    }

    public List<String> getFollows(String key) {
        int i = 0;
        int keyLength = key.length();
        List<String> follows = new ArrayList<>();
        while (i < myText.length() - keyLength) {
            String substring = myText.substring(i, i + keyLength);
            if (substring.equals(key)) {
                follows.add(myText.substring(i + keyLength, i + 2 * keyLength));
                i += key.length();
            } else {
                i++;
            }
        }
        return follows;
    }

}
