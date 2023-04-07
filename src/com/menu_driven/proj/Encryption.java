package com.menu_driven.proj;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
	// --------------- ENCRYTING THE PASSWORD USING SECURE HASH ALGORITH -------------------- //
	
	public static String encrypt(String password) {  
		
		String strEncryptedValue = null;
		
		// --------------- [ SHA_512 -> 512bits -> 64char_plain_text ] -------------------- //
		try {
			
			MessageDigest instMd=MessageDigest.getInstance("SHA-512");              // ------------ message_digest instance for hashing using SHA512  ------------ //
			
			byte byteMessageDigest[] = instMd.digest(password.getBytes());              // ------------  convert the password  into hash value and store it in an array of bytes ----------//

			BigInteger bigInt = new BigInteger(1,byteMessageDigest);             // ------------ store the array of bytes in bigInteger variable  ----------- //

			strEncryptedValue = bigInt.toString(16);                        // -----------  convert the bigInteger into hexadecimal value ------------ //

		}catch(NoSuchAlgorithmException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		return strEncryptedValue;
	}

}
