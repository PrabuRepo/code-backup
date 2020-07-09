package com.practice.others.crypto.old;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class MessageAuthenticationCodeExample {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		
		String text = "Sample text";
		byte[] plainText = text.getBytes("UTF8");
	    //
	    // get a key for the HmacMD5 algorithm
	    System.out.println( "\nStart generating key" );
	    KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
	    SecretKey MD5key = keyGen.generateKey();
	    System.out.println( "Finish generating key" );
	    //
	    // get a MAC object and update it with the plaintext
	    Mac mac = Mac.getInstance("HmacMD5");
	    mac.init(MD5key);
	    mac.update(plainText);
	    //
	    // print out the provider used and the MAC
	    System.out.println( "\n" + mac.getProvider().getInfo() );
	    System.out.println( "\nMAC: " );
	    System.out.println( new String( mac.doFinal(), "UTF8") );
	}

}
