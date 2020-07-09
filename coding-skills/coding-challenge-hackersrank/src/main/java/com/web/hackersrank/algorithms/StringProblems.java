package com.web.hackersrank.algorithms;

import java.io.IOException;
import java.util.Scanner;

public class StringProblems {

	static String super_reduced_string(String s) {
		int[] alphabet = new int[26];
		int index;
		for (int i = 0; i < s.length(); i++) {
			index = s.charAt(i) - 'a';
			alphabet[index]++;
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			index = s.charAt(i) - 'a';
			if (alphabet[index] > 0 && alphabet[index] % 2 == 1)
				result.append(s.charAt(i));

			alphabet[index] = 0;
		}
		/*for(int i=0;i<alphabet.length;i++){
		    if(alphabet[i]>0 && alphabet[i]%2==1)
		        result.append((char)(i+'a'));         
		}*/

		if (result.length() == 0)
			result.append("Empty String");

		return result.toString();
	}

	// Panagram: sentence which it contains every letter of the alphabet.
	static String pangrams(String s) {
		String result = "not pangram";
		if (s.length() > 0) {
			String str = s.toLowerCase();
			int[] alphabet = new int[26];

			int val;
			for (int i = 0; i < str.length(); i++) {
				val = str.charAt(i) - 'a';
				if (val >= 0 && val <= 25)
					alphabet[val]++;
			}
			boolean flag = true;
			for (int i = 0; i < alphabet.length; i++) {
				if (alphabet[i] == 0) {
					flag = false;
					break;
				}
			}

			if (flag)
				result = "pangram";
		}
		return result;
	}

	static void testPanagrams(Scanner scanner) throws IOException {
		String s = scanner.nextLine();
		String result = pangrams(s);
		System.out.println("Output:" + result);
	}

	static void testSuperReducedString(Scanner in) {
		String s = in.next();
		String result = super_reduced_string(s);
		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		// testSuperReducedString(in);

		// Sample: We promptly judged antique ivory buckles for the next prize
		testPanagrams(in);
		in.close();

	}

}
