package com.example.randomtext;

import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class MarkovZero {
    private String myText;
	private Random myRandom = new Random();

	public void setRandom(int seed){
		myRandom = new Random(seed);
	}

	public void setTraining(String s){
		myText = s.trim();
	}

	public String getRandomText(int numChars){
		if (myText == null){
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
