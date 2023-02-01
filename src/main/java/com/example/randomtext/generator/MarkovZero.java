package com.example.randomtext.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class MarkovZero extends AbstractMarkov implements Markov {

	public MarkovZero(Random myRandom) {
		super(myRandom);
	}

	@Override
	public String getRandomText(int numChars) {
		//log.info(super.getFollows("ab").toString());
		return super.getRandomText(numChars);

	}

}
