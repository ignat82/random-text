package com.example.randomtext.generator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MarkovZero extends AbstractMarkov implements Markov {

	public MarkovZero(Random myRandom) {
		super(myRandom);
	}
}
