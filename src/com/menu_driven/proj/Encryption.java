package com.menu_driven.proj;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
	public static String encrypt(String password) {  
		
		String strEncryptedValue = null;
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte messagedigest[]=md.digest(password.getBytes());
			System.out.println("digested...");
			BigInteger bigInt = new BigInteger(1,messagedigest);
			System.out.println("bigInt...");
			strEncryptedValue = bigInt.toString(16);
			System.out.println("encrypted...");
		}catch(NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return strEncryptedValue;
	}

}
