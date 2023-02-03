package com.example.randomtext.generator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class MarkovN implements Markov {
    private final Random myRandom;
    public int n = 0;
    private String myText;
    private Map<String, List<String>> myFollows;
    public void setRandom(int seed) {
        myRandom.setSeed(seed);
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setTraining(String trainingString) {
        myText = trainingString.trim();
        initialiseFollows();
    }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        int index = myRandom.nextInt(myText.length()-n);
        String firstKey = (n == 0)
                ? myText.substring(index, index + n)
                : String.valueOf(myText.charAt(index));
        StringBuilder sb = new StringBuilder();
        sb.append(firstKey);
        String key = (n == 0)
                ? ""
                : firstKey;
        for(int k = 0; k < numChars - n; k++) {
            log.info("key is {}", key);
            List<String> followsList = myFollows.get(key);
            // log.info("got followsList {}", followsList);
            if (followsList == null || followsList.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(followsList.size());
            sb.append(followsList.get(index));
            key = (n == 0)
                    ? ""
                    : key.substring(1, n) + followsList.get(index);
        }
        return sb.toString();
    }

    private void initialiseFollows() {
        Map<String, List<String>> followsMap = new HashMap<>();
        //myText = "its a training";
        for(int k = 0; k < myText.length() - n; k++) {
            // refactor
            String key = (n == 0)
                    ? ""
                    : myText.substring(k, k + n);
            if (!followsMap.containsKey(key)) {
                List<String> followsList = getFollows(key);
                if (followsList.isEmpty()) {
                    break;
                }
                followsMap.put(key, followsList);
                //log.info("got key {}, follows {}", key, followsList);
            } else {
                //log.info("skipping key {}", key);
            }
        }
        this.myFollows = followsMap;
    }

    private List<String> getFollows(String key) {
        int i = 0;
        int keyLength = key.length();
        List<String> follows = new ArrayList<>();
        while (i < myText.length() - keyLength) {
            String substring = myText.substring(i, i + keyLength);
            if (key.equals("") || substring.equals(key)) {
                follows.add(myText.substring(i + keyLength, i + keyLength + 1));
            }
            i++; // refactor this
        }

        return follows;
    }
}
