package com.gaylemcdowell.problems;

import java.nio.charset.Charset;
import java.util.Arrays;

public class Ch01ArraysAndStrings {

	public static final int	ASCII_CHAR_SIZE				= 128;
	public static final int	EXTENDED_ASCII_CHAR_SIZE	= 256;
	public static final int	ALPHABET_SIZE				= 26;

	public static void main(String[] args) {
		Ch01ArraysAndStrings ob = new Ch01ArraysAndStrings();

		System.out.println("Is unique chars: " + ob.isUniqueChars1("abvcd"));
		System.out.println("Is unique chars: " + ob.isUniqueChars2("abcde"));

		System.out.println("Check Permutations: " + ob.checkPermutation1("abcde", "edbca"));
		System.out.println("Check Permutations: " + ob.checkPermutation2("abcdr", "edbca"));

		System.out.println("Urlify: " + ob.urlify("Mr John Smith    ", 13));

		System.out.println("Palindrome Permutation: " + ob.isPalindrome1("Tact Coa"));

		System.out.println("One Away1:" + ob.oneAway1("pale", "bae"));
		System.out.println("One Away:" + ob.oneAway2("pale", "bae"));
		System.out.println("One Away:" + ob.oneAway3("pale", "pale"));

		System.out.println("String Compression: " + ob.stringCompression1("aabbbcccccaaad"));

		System.out.println("String Rotation: " + ob.isRotation("waterbottle", "erbottlewat"));

		// ob.checkISOChars();

	}

	/*
	 * 1.Is Unique:
	 * Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional 
	 * data structures? ASCII(128 chars) Vs Unicode Vs extended ASCII(256 chars)?
	 */
	// Approach1: Brute force-> O(n^2)
	// Approach2: Sort and check in linear time -> O(nlogn)
	// Approach3: Using Hashing concepts -> Time Complexity: O(n), but takes additional space
	public boolean isUniqueChars1(String str) {
		if (str.length() > 128) return false;

		boolean[] chars = new boolean[128];
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i);
			if (chars[index]) return false;
			chars[index] = true;
		}
		return true;
	}

	// Approach4: Bit manipulation-> O(n) (Assumption: string only uses the lowercase letters a through z)
	public boolean isUniqueChars2(String str) {
		int testBit = 0;
		for (int i = 0; i < str.length(); i++) {
			int charValue = str.charAt(i) - 'a';
			// If testBit is one in position of this charValue, then there is duplicate char
			if ((testBit & (1 << charValue)) > 0) return false;
			// Set charValue position in testBit
			testBit |= (1 << charValue);
		}
		return true;
	}

	/*
	 * 2.Check Permutation: 
	 *     Given two strings, write a method to decide if one is a permutation of the other.
	 */
	// Approach1: Sort the strings and compare
	public boolean checkPermutation1(String str1, String str2) {
		if (str1.length() != str2.length()) return false;

		return sort(str1).equals(sort(str2));
	}

	// Approach2: using hashing
	public boolean checkPermutation2(String str1, String str2) {
		if (str1.length() != str2.length()) return false;

		int[] charCount = new int[ASCII_CHAR_SIZE];
		for (int i = 0; i < str1.length(); i++)
			charCount[str1.charAt(i)]++;

		for (int i = 0; i < str2.length(); i++)
			if (--charCount[str2.charAt(i)] < 0) return false;

		return true;
	}

	/*
	 * 3.URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string has sufficient space
	 * at the end to hold the additional characters, and that you are given the "true" length of the string. 
	 * (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
	 * Eg: 
	 * Input: "Mr John Smith "
	 * Output: "Mr%20John%20Smith"
	 */
	public String urlify(String str, int trueLength) {
		if (str.length() == 0) return str;

		int spacesCount = 0;
		for (int i = 0; i < trueLength; i++)
			if (str.charAt(i) == ' ') spacesCount++;

		int len = (trueLength + 2 * spacesCount) - 1; // str.length() - 1;
		char[] arr = str.toCharArray();
		for (int i = trueLength - 1; i >= 0 && len >= 0; i--) {
			if (arr[i] == ' ') {
				arr[len--] = '0';
				arr[len--] = '2';
				arr[len--] = '%';
			} else {
				arr[len--] = arr[i];
			}
		}
		return new String(arr);
	}

	/*
	 * 4.Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is
	 * a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does 
	 * not need to be limited to just dictionary words.
	 * EXAMPLE - 
	 *   Input: Tact Coa 
	 *   Output: True (permutations:"taco cat'; "atco cta'; etc.)
	 */
	// Using Hashing - It handles lower case, upper case & special char's-> Time Complexity: O(n)
	public boolean isPalindrome1(String str) {
		int[] charCount = new int[ALPHABET_SIZE];
		for (int i = 0; i < str.length(); i++) {
			char ch = upperToLowerCase(str.charAt(i));
			if (ch >= 'a' && ch <= 'z') charCount[ch - 'a']++;
		}

		int oddCount = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = upperToLowerCase(str.charAt(i));
			if (ch >= 'a' && ch <= 'z') {
				if (charCount[ch - 'a'] % 2 == 1) oddCount++;
			}
		}
		return (oddCount > 1) ? false : true;
	}

	// Using bit manipulations
	public boolean isPalindrome2(String str) {
		return false;
	}

	/*
	 * 5.One Away: There are three types of edits that can be performed on strings: insert a character, remove a character, or 
	 * replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.
	 *  EXAMPLE
	 *   pale, ple -) true
	 *   pales, pale -) true
	 *   pale, bale -) true
	 *   pale, bae -) false
	 */
	/*
	 * Approach1: (Incomplete)
	 * 1.Replacement: Consider two strings, such as bale and pale, that are one replacement away. Yes, that does mean that you 
	 * could replace a character in bale to make pale. But more precisely, it means that they are different only in one place.
	 * 2.Insertion: The strings apple and aple are one insertion away. This means that if you compared the strings, they would 
	 * be identical-except for a shift at some point in the strings.
	 * 3.Removal: The strings apple and aple are also one removal away, since removal is just the inverse of insertion.
	 */
	public boolean oneAway1(String str1, String str2) {
		if (Math.abs(str1.length() - str2.length()) > 1) {
			return false;
		} else if (str1.length() == str2.length()) {
			return oneEditReplace(str1, str2);
		} else {
			return oneEditInsert(str1, str2);
		}
	}

	private boolean oneEditReplace(String s1, String s2) {
		boolean foundDifference = false;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (foundDifference) return false;
				foundDifference = true;
			}
		}
		return true;
	}

	private boolean oneEditInsert(String s1, String s2) {
		int i1 = 0, i2 = 0;
		while (i1 < s1.length() && i2 < s2.length()) {
			if (s1.charAt(i1) != s2.charAt(i2)) {
				if (i1 != i2) return false;
				i2++;
			} else {
				i1++;
				i2++;
			}
		}
		return true;
	}

	// Approach2: Handle both in single method
	public boolean oneAway2(String str1, String str2) {
		int m = str1.length(), n = str2.length();
		if (Math.abs(m - n) > 1) return false;
		String shorter = m < n ? str1 : str2;
		String longer = m > n ? str1 : str2;
		int i1 = 0, i2 = 0;
		boolean foundDiff = false;
		while (i1 < shorter.length() && i2 < longer.length()) {
			if (shorter.charAt(i1) != longer.charAt(i2)) {
				// To handle the replace logic
				if (foundDiff) return false;
				foundDiff = true;
				// To handle the insert/remove logic
				if (m == n) i1++;
			} else {
				i1++;
			}
			i2++;
		}
		return true;
	}

	// Approach3: Simple Approach - Similar to above
	public boolean oneAway3(String str1, String str2) {
		int m = str1.length(), n = str2.length();
		if (Math.abs(m - n) > 1) return false;

		for (int i = 0; i < Math.min(m, n); i++) {
			if (str1.charAt(i) == str2.charAt(i)) continue;

			if (m == n) return str1.substring(i + 1).equals(str2.substring(i + 1)); //Replacement
			else if (m > n) return str1.substring(i + 1).equals(str2.substring(i)); //Insert/Removal
			else return str1.substring(i).equals(str2.substring(i + 1));//Insert/Removal
		}

		return true;
	}

	/*
	 *6.String Compression: Implement a method to perform basic string compression using the counts of repeated characters. For 
	 *example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the 
	 *original string, your method should return the original string. You can assume the string has only uppercase and lowercase
	 *letters (a - z). 
	 */
	// Approach1:
	public String stringCompression1(String str) {
		StringBuilder compressed = new StringBuilder();
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			count++;
			if ((i + 1) >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressed.append(count);
				compressed.append(str.charAt(i));
				count = 0;
			}
		}
		// Other way of iterations
		/*int count = 1;
		for (int i = 0; i < str.length(); i++) {
			if ((i + 1) >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressed.append(count);
				compressed.append(str.charAt(i));
				count = 1;
			} else {
				count++;
			}
		}*/
		return compressed.length() > str.length() ? str : compressed.toString();
	}

	/*Approach2:
	 * Both of these solutions create the compressed string first and then return the shorter of the input string and the 
	 * compressed string. Instead, we can check in advance. This will be more optimal in cases where we don't have a large 
	 * number of repeating characters. It will avoid us having to create a string that we never use. The downside of this is
	 * that it causes a second loop through the characters and also adds nearly duplicated code.  
	 */
	public String stringCompression2(String str) {
		return "";
	}

	/*
	 * 7.Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method
	 * to rotate the image by 90 degrees. Can you do this in place?
	 */
	// Aproach1: Refer ArrayProblems.java class
	// Approach2: without using any space - Time Complexity:O(n^2)
	public void rotateLeftMatrix2(int[][] matrix) {

		System.out.println("Before: ");
		printMatrix(matrix);

		int n = matrix.length, temp = 0;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				// Save the top
				temp = matrix[i][j];
				// Move left to top
				matrix[i][j] = matrix[n - j - 1][i];
				// Move bottom to left
				matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
				// Move right to bottom
				matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
				// Assign the top(from temp) to right
				matrix[j][n - i - 1] = temp;
			}
		}

		System.out.println("After: ");
		printMatrix(matrix);

	}

	/*
	 * 8.Zero Matrix: Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column 
	 * are set to 0. 
	 * 
	 */
	public void zeroMatrix(int[][] matrix) {
		boolean firstRowZero = false;
		boolean firstColZero = false;
		int rSize = matrix.length, cSize = matrix[0].length;
		for (int i = 0; i < rSize; i++)
			if (matrix[i][0] == 0) {
				firstColZero = true;
				break;
			}

		for (int j = 0; j < cSize; j++)
			if (matrix[0][j] == 0) {
				firstRowZero = true;
				break;
			}

		for (int i = 1; i < rSize; i++)
			for (int j = 1; j < cSize; j++)
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}

		for (int i = 1; i < rSize; i++)
			for (int j = 1; j < cSize; j++)
				if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;

		if (firstRowZero) {
			for (int j = 0; j < cSize; j++)
				matrix[0][j] = 0;
		}

		if (firstColZero) {
			for (int i = 0; i < rSize; i++)
				matrix[i][0] = 0;
		}
	}

	/*
	 * 9.String Rotation: Assume you have a method i5Substring which checks ifone word is a substring of another. Given two strings, 
	 * 51 and 52, write code to check if 52 is a rotation of 51 using only one call to isSubstring 
	 * (e.g., "waterbottle" is a rotation of "erbottlewat").
	 */
	// One line solution using Java util
	public boolean isRotation1(String A, String B) {
		return (A.length() == B.length() && (A + A).contains(B));
	}

	public boolean isRotation(String str1, String str2) {
		if ((str1.length() != str2.length()) || str1.length() == 0) return false;
		// Concatenate str1 twice, if str2 will be a sub string of newstring, then str2 is rotation of str1.
		String newString = str1 + str1;
		return isSubString(newString, str2);
	}

	public static int stringRotatedByTwoPlaces(String s1, String s2) {
		// System.out.println("Index: "+(s1+s1).indexOf(s2));
		if (s1.length() == s2.length() && ((s1 + s1).indexOf(s2) == 2 || (s1 + s1).indexOf(s2) == s1.length() - 2))
			return 1;
		else return 0;
	}

	public boolean isSubString(String str, String pattern) {
		int j, strLength = str.length(), patternLength = pattern.length();
		for (int i = 0; i <= (strLength - patternLength); i++) {
			if (str.charAt(i) == pattern.charAt(0)) {// If first character is same, then proceed comparison
				for (j = 0; j < patternLength; j++) {
					if (str.charAt(i + j) != pattern.charAt(j)) break;
				}
				if (j == patternLength) { return true; }
			}
		}
		return false;
	}

	/******************************* Util Methods ****************************/
	private String sort(String str) {
		char[] ch = str.toCharArray();
		Arrays.sort(ch);
		return new String(ch);
	}

	private char upperToLowerCase(char ch) {
		if (ch >= 'A' && ch <= 'Z') return (char) (ch + 25 + 7);
		return ch;
	}

	public void checkISOChars() {
		String str = "JEDINY /LUK�S";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			System.out
					.println(ch + ": " + (int) ch + " -> " + Charset.forName("ISO_8859_1").newEncoder().canEncode(ch));
		}
	}

	public void printASCIIchars(int n) {
		System.out.println("Char " + " Value");
		for (int i = 0; i < n; i++) {
			System.out.println("  " + i + "    " + (char) i);
		}
	}

	public void printMatrix(int[][] data) {
		int row = data.length;
		int col = data[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}
}
