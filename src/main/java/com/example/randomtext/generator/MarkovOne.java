package com.example.randomtext.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class MarkovOne extends AbstractMarkov implements Markov {

    public MarkovOne(Random myRandom) {
        super(myRandom);
    }

    @Override
    public String getRandomText(int numChars) {
        String myText = super.getMyText();
        // log.info("myText is \n{}", myText);
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Random random = super.getMyRandom();
        char firstChar = myText.charAt(random.nextInt(myText.length()-1));
        sb.append(firstChar);
        log.info(String.valueOf(firstChar));
        for(int k = 1; k < numChars; k++) {
            List<String> follows = getFollows(sb.substring(k-1, k));
            int index = random.nextInt(follows.size());
            sb.append(follows.get(index));
            log.info("index is {}, char is {}", index, follows.get(index));
        }
        return sb.toString();
    }

}
