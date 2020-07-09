package com.practice.others.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Advanced Encryption algorithm
 */
public class AES {

	private static SecretKeySpec	secretKey;
	private static byte[]			key;

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt, String secret) {
		try {
			// Step1: set the SecretKeySpec using messagedigest SHA(secret hashing
			// algorithm) based on given "secret" string
			setKey(secret);
			// Step2: Get the cipher instance for AES
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			// Step3: Initialize the cipher object by using the encrypt mode &
			// secretkeyspec
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			// Step4: Encrypt the given string "strToEncrypt" by invoking doFinal()
			// method
			byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
			// Step5: Encode the encryptedBytes using the BAse64 Encoder
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt, String secret) {
		try {
			// Step1: set the SecretKeySpec using messagedigest SHA(secret hashing
			// algorithm) based on given "secret" string
			setKey(secret);
			// Step2: Get the cipher instance for AES
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			// Step3: Initialize the cipher object by using the decrypt mode &
			// secretkeyspec
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			// Step4: Decode the given string "strToDecrypt" using the Base64 Decoder
			byte[] decodedBytes = Base64.getDecoder().decode(strToDecrypt);
			// Step5: Decrypt the decodedBytes by invoking doFinal() method
			return new String(cipher.doFinal(decodedBytes));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String secretKey = "secret";

		String originalString = "Prabu";
		String encryptedString = AES.encrypt(originalString, secretKey);
		String decryptedString = AES.decrypt(encryptedString, secretKey);

		System.out.println(originalString);
		System.out.println(encryptedString);
		System.out.println(decryptedString);
	}
}