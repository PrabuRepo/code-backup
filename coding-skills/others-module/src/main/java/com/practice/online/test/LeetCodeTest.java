package com.practice.online.test;

import java.util.Arrays;

public class LeetCodeTest {

	public static void main(String[] args) {
		System.out.println("Prime Palindrome: " + primePalindrome(12));

		for (int i = 1; i <= 1001; i++) {
			if (isPalindrome(i) && isPrime(i))
				System.out.println(i);
		}

		Integer value = 26;
		/* System.out.println("Ouptut:" + validateRegisterNumber("26,27", String.valueOf(value)));*/
		System.out.println(hexToASCII("2F62BD9F"));
	}

	public static int primePalindrome(int N) {
		while (true) {
			N++;
			if (isPalindrome(N) && isPrime(N))
				return N;
		}
	}

	private static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n == 2 || n == 3)
			return true;
		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	private static boolean isPalindrome(int n) {
		if (n < 10)
			return true;

		if (n == reverseNumber(n))
			return true;

		return false;
	}

	private static int reverseNumber(int n) {
		int rev = 0;
		while (n > 0) {
			rev = rev * 10 + (n % 10);
			n /= 10;
		}
		return rev;
	}

	public static String hexToASCII(String hexValue) {
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < hexValue.length(); i += 2) {
			String str = hexValue.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}
		return output.toString();
	}

	public static boolean validateRegisterNumber(String xeroxRegister, String kioskRegisterNmb) {
		String[] xssRegisters = xeroxRegister.split(",");
		return Arrays.asList(xssRegisters).contains(kioskRegisterNmb.trim());
	}

}
