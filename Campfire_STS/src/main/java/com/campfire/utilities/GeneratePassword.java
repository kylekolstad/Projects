package com.campfire.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneratePassword {
	
	public static String generateRandomPassword() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString;
	}
	
}
