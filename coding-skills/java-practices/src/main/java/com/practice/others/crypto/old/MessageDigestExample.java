package com.practice.others.crypto.old;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestExample {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	 String text  = "Sample text";
		byte[] plainText = text.getBytes("UTF8");
	    
	    // get a message digest object using the MD5 algorithm
	    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    
	    // print out the provider used
	    System.out.println( "\n" + messageDigest.getProvider().getInfo() );
	    
	    // calculate the digest and print it out
	    messageDigest.update( plainText);
	    System.out.println( "\nDigest: " );
	    System.out.println( new String( messageDigest.digest(), "UTF8") );
	}

}
