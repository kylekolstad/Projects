package com.campfire.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// This function was created by Arpit Shah from https://www.youtube.com/watch?v=hNKfEwTO3AQ
public class HashPassword {
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public static String hashPassword(String username, String password)
	{
		String mySalt = "IRIDIUM";
		byte[] salt = mySalt.getBytes();
		String data = username + password + "IRIDIUM";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.reset();
		//md.update(salt);
		byte[] hash = md.digest(data.getBytes());
		return bytesToStringHex(hash);
	}
	
	public static String bytesToStringHex(byte[] bytes) 
	{
		char[] hexChars = new char[bytes.length * 2];
		for(int j = 0; j < bytes.length; j++) 
		{
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		
		return new String(hexChars);
	}
}
