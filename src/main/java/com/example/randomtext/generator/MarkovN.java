package com.example.randomtext.generator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class MarkovN implements Markov {
    private final Random myRandom;
    public int n = 0;
    private String myText;

    public void setRandom(int seed) {
        myRandom.setSeed(seed);
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setTraining(String trainingString) {
        myText = trainingString.trim();
    }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        int index = myRandom.nextInt(myText.length()-n);
        String key = (n == 0)
                ? String.valueOf(myText.charAt(index))
                : myText.substring(index, index + n);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int k = 0; k < numChars - n; k++) {
            List<String> follows = getFollows(key);
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String truncatedKey = (n == 0)
                    ? ""
                    : key.substring(1, n);
            key = truncatedKey + follows.get(index);
            sb.append(follows.get(index));
        }
        return sb.toString();
    }

    private List<String> getFollows(String key) {
        int i = 0;
        int keyLength = key.length();
        List<String> follows = new ArrayList<>();
        while (i < myText.length() - keyLength) {
            String substring = myText.substring(i, i + keyLength);
            if (substring.equals(key)) {
                follows.add(myText.substring(i + keyLength, i + keyLength + 1));
            }
            i++;
        }
        return follows;
    }
}
