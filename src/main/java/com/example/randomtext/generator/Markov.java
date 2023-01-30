package com.example.randomtext.generator;

public interface Markov {
    void setRandom(int seed);
    void setTraining(String trainingString);
    String getRandomText(int numChars);
}
