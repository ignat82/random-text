package com.example.randomtext.generator;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
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
}
