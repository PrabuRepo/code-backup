package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.common.utilities.Utils;

public class StringProblems {

	/************************* 1.Using 2 Pointers & Simple Problems *************/
	// Reverse String
	public String reverseString(String s) {
		if (s == null)
			return s;

		char[] charArray = s.toCharArray();
		int l = 0, r = charArray.length - 1;
		while (l < r) {
			Utils.swap(charArray, l, r);
			l++;
			r--;
		}
		return String.valueOf(charArray);
	}

	// Simple Palindrome Problem
	public boolean isPalindrome(char[] ch) {
		int i = 0, j = ch.length - 1;
		while (i < j) {
			if (ch[i++] != ch[j--])
				return false;
		}
		return true;
	}

	public boolean isPalindrome(String s, int l, int r) {
		while (l < r) {
			if (s.charAt(l) != s.charAt(r))
				return false;
			l++;
			r--;
		}
		return true;
	}

	/*
	 * Valid Palindrome:Given a string, determine if it is a palindrome, 
	 * considering only alphanumeric characters and ignoring cases.
	 */
	public int isPalindrome10(String str) {
		if (str == null || str.length() <= 1)
			return 1;
		int l = 0, h = str.length() - 1;
		while (l < h) {
			while (l < h && (str.charAt(l) == ' '
					|| !Character.isLetterOrDigit(
							str.charAt(l))))
				l++;
			while (l < h && (str.charAt(h) == ' '
					|| !Character.isLetterOrDigit(
							str.charAt(h))))
				h--;
			if (l == h)
				break;
			if (Character
					.toLowerCase(str.charAt(l)) == Character
							.toLowerCase(str.charAt(h))) {
				l++;
				h--;
			} else {
				return 0;
			}
		}
		return 1;
	}

	public boolean isPalindrome11(String s) {
		if (s == null || s == "")
			return true;
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (int i = 0; i < s.length(); i++) {
			index = (int) s.charAt(i);
			if ((index >= 97 && index <= 122)
					|| (index >= 65 && index <= 90)) {
				if (index <= 90)
					index += 32;
				sb.append((char) index);
			} else if (Character.isDigit(s.charAt(i))) {
				sb.append(s.charAt(i));
			}
		}
		// System.out.println(sb.toString());
		char[] ch = sb.toString().toCharArray();
		return isPalindrome(ch);
	}

	public boolean isPalindrome2(String s) {
		if (s.isEmpty())
			return true;

		int l = 0, h = s.length() - 1;
		char cHead, cTail;
		while (l <= h) {
			cHead = s.charAt(l);
			cTail = s.charAt(h);
			if (!Character.isLetterOrDigit(cHead)) {
				l++;
			} else if (!Character.isLetterOrDigit(cTail)) {
				h--;
			} else {
				if (Character
						.toLowerCase(cHead) != Character
								.toLowerCase(cTail))
					return false;
				l++;
				h--;
			}
		}

		return true;
	}

	/*
	 * Valid Palindrome II:Given a non-empty string s, 
	 * you may delete at most one character. Judge 
	 * whether you can make it a palindrome.
	 */
	public boolean validPalindrome(String s) {
		int l = 0, r = s.length() - 1;
		while (l < r && s.charAt(l) == s.charAt(r)) {
			l++;
			r--;
		}

		if (l >= r)
			return true;

		if (isPalindrome(s, l + 1, r)
				|| isPalindrome(s, l, r - 1))
			return true;

		return false;
	}

	/***************3.Sting Pretty Print**************/
	/* ZigZag Conversion:
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of 
	 * rows like this: (you may want to display this pattern in a fixed font for better legibility)
	 * Example 1: Input: s = "PAYPALISHIRING", numRows = 3; Output: "PAHNAPLSIIGYIR"
	 * Example 2: Input: s = "PAYPALISHIRING", numRows = 4; Output: "PINALSIGYAHRPI"
	 */
	public String convert(String s, int numRows) {
		if (numRows <= 1)
			return s;

		StringBuilder sb = new StringBuilder();
		int n = s.length(), step1, step2, pos;

		for (int i = 0; i < numRows; i++) {
			step1 = 2 * (numRows - i - 1); // 2(n-1), 2(n-2), 2(n-3)...0
			step2 = 2 * i; // 0, 2, 4,...
			pos = i;
			if (pos < n)
				sb.append(s.charAt(pos));
			while (true) {
				pos += step1;
				if (pos >= n)
					break;
				if (step1 != 0)
					sb.append(s.charAt(pos));

				pos += step2;
				if (pos >= n)
					break;
				if (step2 != 0)
					sb.append(s.charAt(pos));
			}
		}

		return sb.toString();
	}

	/************************* 4.String Math ************************/

	/* Compare Version Numbers:
	 * Compare two version numbers version1 and version2. 
	 * If version1 > version2 return 1; if version1 < version2 
	 * return -1;otherwise return 0.
	 */
	public int compareVersion(String version1,
			String version2) {
		if (version1.length() == 0
				&& version2.length() == 0)
			return 0;
		String[] ver1 = version1.split("\\.");
		String[] ver2 = version2.split("\\.");
		for (int i = 0; i < Math.max(ver1.length,
				ver2.length); i++) {
			int v1 = i < ver1.length
					? Integer.valueOf(ver1[i])
					: 0;
			int v2 = i < ver2.length
					? Integer.valueOf(ver2[i])
					: 0;
			if (v1 > v2)
				return 1;
			if (v1 < v2)
				return -1;
		}
		return 0;
	}

	/* Encode and Decode Strings:
	 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and 
	 * is decoded back to the original list of strings.
	 * Implement the encode and decode methods.
	 * Note: The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be 
	 * generalized enough to work on any possible characters. Do not use class member/global/static variables to store 
	 * states. Your encode and decode algorithms should be stateless. Do not rely on any library method such as eval or
	 * serialize methods. You should implement your own encode/decode algorithm.
	 */

	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		StringBuilder output = new StringBuilder();
		for (String str : strs) {
			output.append(
					String.valueOf(str.length()) + "#");
			output.append(str);
		}
		return output.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode1(String s) {
		List<String> res = new ArrayList<>();
		int i = 0;
		while (i < s.length()) {
			// Get length
			int strIdx = s.indexOf('#', i); // Find the '#' index from 'i'
			int len = Integer
					.parseInt(s.substring(i, strIdx));
			// Get string
			res.add(s.substring(strIdx + 1,
					strIdx + len + 1));
			i = strIdx + len + 1;
		}
		return res;
	}

	public List<String> decode2(String s) {
		List<String> result = new ArrayList<>();
		if (s == null || s.length() == 0)
			return result;

		int i = 0;
		while (i < s.length()) {
			int len = 0;
			// Get length
			while (i < s.length() && s.charAt(i) != '#') {
				len = len * 10 + Character
						.getNumericValue(s.charAt(i));
				i++;
			}
			// Get string
			String str = s.substring(i + 1, i + len + 1);
			result.add(str);
			i = i + len + 1;
		}
		return result;
	}

	/************************* 5.Hashing ************************/
	// Valid Anagram
	// Approach1: Using Hashing; Time Complexity-O(n)
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length())
			return false;

		int[] count = new int[26];
		for (int i = 0; i < s.length(); i++)
			count[s.charAt(i) - 'a']++;

		for (int i = 0; i < s.length(); i++)
			if (count[t.charAt(i) - 'a']-- == 0)
				return false;

		return true;
	}

	// Approach2: Using Sorting; Time Complexity-O(nlogn)
	public boolean isAnagram2(String s, String t) {
		if (s.length() != t.length())
			return false;

		char[] arr1 = s.toCharArray();
		char[] arr2 = t.toCharArray();
		Arrays.sort(arr1);
		Arrays.sort(arr2);

		for (int i = 0; i < arr1.length; i++)
			if (arr1[i] != arr2[i])
				return false;

		return true;
	}

}