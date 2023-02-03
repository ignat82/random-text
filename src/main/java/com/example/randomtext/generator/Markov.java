package com.example.randomtext.generator;

public interface Markov {
    void setRandom(int seed);
    void setN(int n);
    void setTraining(String trainingString);
    String getRandomText(int numChars);
}
