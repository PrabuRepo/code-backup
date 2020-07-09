package com.practice.others.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SymmetricAlgorithms {
	public static void main(String[] args) {
		SymmetricAlgorithms ob = new SymmetricAlgorithms();
		String algorithm = "AES";
		String transformation = "AES/CBC/PKCS5Padding";
		int keySize = 128;
		String encryptedMessage = ob.encrypt("Hello World", algorithm, transformation, keySize);
		System.out.println("Encrypted Message: " + encryptedMessage);

		String decryptedMessage = ob.decrypt(encryptedMessage, algorithm, transformation, keySize);
		System.out.println("Decrypted Message: " + decryptedMessage);
	}

	public String encrypt(String message, String algorithm, String transformation, int keySize) {
		String result = null;
		try {
			// Generate the Key - using KeyGenerator
			SecretKey key = generateKey(algorithm, keySize);

			// Get the Initialization Vector(iv) - using SecureRandom
			IvParameterSpec iv = getInitializationVector();

			// Encrypt using Cipher Instance
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encryptedBytes = cipher.doFinal(message.getBytes());

			// Covert encryptedBytes to String - using the Base64 Encoder
			result = Base64.getEncoder().encodeToString(encryptedBytes);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String decrypt(String encryptedMessage, String algorithm, String transformation, int keySize) {
		String result = null;
		try {
			// Generate the Key - using KeyGenerator
			SecretKey key = generateKey(algorithm, keySize);

			// Get the Initialization Vector(iv) - using SecureRandom
			IvParameterSpec iv = getInitializationVector();

			// Get the encrypted messages
			byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);

			// Encrypt using Cipher Instance
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

			// Covert encryptedBytes to String - using the Base64 Encoder
			result = new String(decryptedBytes);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return result;
	}

	public SecretKey generateKey(String algorithm, int keySize) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		keyGenerator.init(keySize);
		return keyGenerator.generateKey();
	}

	public IvParameterSpec getInitializationVector() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[16];
		random.nextBytes(bytes);
		return new IvParameterSpec(bytes);
	}

}