package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

import com.common.utilities.Utils;
import com.geeks.problem.algorithms.PatternSearching;

public class StringProblems {
	public static final int	ASCII_CHAR_SIZE				= 128;
	public static final int	EXTENDED_ASCII_CHAR_SIZE	= 256;
	public static final int	ALPHABET_SIZE				= 26;

	/**
	 * ---------Character Counting Based Problems :-----------------
	 */
	// Using sorting and comparison Approach
	public char returnMaxOccurringChar1(String s) {
		char[] a = s.toCharArray();
		Arrays.sort(a);
		s = new String(a);
		System.out.println("Sorted String:" + s);
		int count = 1, max = -1;
		char current, maxChar = 0;
		maxChar = current = s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			if (current == s.charAt(i)) {
				count++;
			} else {
				if (count > max) {
					max = count;
					maxChar = current;
				}
				current = s.charAt(i);
				count = 1;
			}
		}
		return maxChar;
	}

	// Using HashMap Approach
	public static char returnMaxOccurringChar2(String s) {
		Map<Character, Integer> map = new HashMap<>();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			count = map.get(s.charAt(i)) != null ? map.get(s.charAt(i)) : 0;
			map.put(s.charAt(i), ++count);
		}

		int max = -1;
		char maxChar = 0;
		for (int i = 0; i < s.length(); i++) {
			count = map.get(s.charAt(i));
			if (count > max && maxChar != s.charAt(i)) {
				maxChar = s.charAt(i);
				max = count;
			}
		}
		return maxChar;
	}

	// Using Hashing Approach (with no of ASCII char size count array)
	public char returnMaxOccurringChar3(String str) {
		// Create array to keep the count of individual characters and initialize the array as 0
		int count[] = new int[EXTENDED_ASCII_CHAR_SIZE];

		// Construct character count array from the input string.
		for (int i = 0; i < str.length(); i++)
			count[str.charAt(i)]++;

		int max = -1; // Initialize max count
		char result = ' '; // Initialize result
		/*
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0)
				System.out.println(i + "-" + count[i]);
		}
		*/
		// Traversing through the string and maintaining the count of each character
		for (int i = 0; i < str.length(); i++) {
			if (max < count[str.charAt(i)]) {
				max = count[str.charAt(i)];
				result = str.charAt(i);
			}
		}

		return result;
	}

	public static char secondMostFrequentChar1(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			count = map.get(s.charAt(i)) != null ? map.get(s.charAt(i)) : 0;
			map.put(s.charAt(i), ++count);
		}

		int first = -1, second = -1, data;
		char firstMaxChar = 0, secMaxChar = 0;
		for (Entry<Character, Integer> entry : map.entrySet()) {
			data = entry.getValue();
			if (data > first) {
				second = first;
				secMaxChar = firstMaxChar;
				first = data;
				firstMaxChar = entry.getKey();
			}

			if (data > second && data != first) {
				second = data;
				secMaxChar = entry.getKey();
			}

		}
		return secMaxChar;
	}

	// Using Hashing
	public static char secondMostFrequentChar2(String s) {
		return ' ';
	}

	// Using Hashing
	public static char kthNonRepeatingChar1() {
		return ' ';
	}

	// Using Linked HashMap
	public static char kthNonRepeatingChar2() {
		return ' ';
	}

	public static int countWordsInString1(String s) {
		int wc = 0;
		boolean wordEndFlag = true;
		char ch;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (ch == ' ' || ch == '\n' || ch == '\t') {
				wordEndFlag = true;
			} else if (wordEndFlag) {
				wc++;
				wordEndFlag = false;
			}
		}
		return wc;
	}

	// Using Java API's: Split & String Tokenizer
	// (It wont work for all type of string)
	public static int countWordsInString2(String str) {
		int wc = 0; // word count
		System.out.println("Split API:" + str.split(" ").length);
		StringTokenizer token = new StringTokenizer(str, " ");
		wc = token.countTokens();
		return wc;
	}

	/**
	 * ---------Anagram Problems :-----------------
	 */

	// Using sorting: Time complexity: O(nlogn)
	public static boolean checkAnagram1(String s1, String s2) {
		boolean flag = false;
		if (s1.length() == s2.length()) {
			char[] ch1 = s1.toLowerCase().toCharArray();
			char[] ch2 = s2.toLowerCase().toCharArray();
			Arrays.sort(ch1);
			Arrays.sort(ch2);
			flag = true;
			for (int i = 0; i < ch1.length; i++)
				if (ch1[i] != ch2[i]) {
					flag = false;
					break;
				}
		}
		return flag;
	}

	// Approach2: Using hashing;(Using count arrays with size of 256(No of Ascii chars))- Time complexity: O(n)
	public static boolean checkAnagram2(String s1, String s2) {
		boolean flag = false;
		if (s1.length() == s2.length()) {
			int[] count1 = new int[EXTENDED_ASCII_CHAR_SIZE];
			int[] count2 = new int[EXTENDED_ASCII_CHAR_SIZE];
			for (int i = 0; i < s1.length(); i++) {
				count1[s1.charAt(i)]++;
				count2[s2.charAt(i)]++;
			}

			flag = true;
			for (int i = 0; i < s1.length(); i++) {
				char ch = s1.charAt(i);
				if (count1[ch] != count2[ch]) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	/*
	 * Group Anagrams: Given an array of strings, return all groups of strings that are anagrams. 
	 * If the average length of verbs is m and array length is n, then the time is O(n*m).
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			String asc = ascOrder(str);
			List<String> list = null;
			if (map.containsKey(asc)) {
				list = map.get(asc);
				list.add(str);
			} else {
				list = new ArrayList<>();
				list.add(str);
			}
			map.put(asc, list);
		}

		List<List<String>> result = new ArrayList<>();
		result.addAll(map.values());
		return result;
	}

	public String ascOrder(String str) {
		char[] arr = new char[26];
		for (int i = 0; i < str.length(); i++) {
			arr[str.charAt(i) - 'a']++;
		}
		return new String(arr);

		/*char[] arr = str.toCharArray();
		Arrays.sort(arr);
		return new String(arr);*/
	}

	/**
	 * ---------Palindrome Problems :-----------------
	 */
	// Check whether given string is palindrome or not
	public boolean isPalindrome(String str) {
		int l = 0, h = str.length() - 1;
		while (l < h) {
			if (str.charAt(l++) != str.charAt(h--)) return false;
		}
		return true;
	}

	// Check whether given number is palindrome or not
	public boolean isPalindrome(int n) {
		int reverse = 0;
		for (int i = n; i > 0; i /= 10) {
			reverse = reverse * 10 + i % 10;
		}
		return reverse == n;
	}

	// Check if a given string is a rotation of a palindrome
	// Approach1:Rotate the string one by one and check the string
	public boolean isRotatedStringPalindrome1(String str) {
		if (str.length() <= 1) return true;

		// Try all the rotations one by one
		for (int i = 1; i < str.length(); i++) {
			String str1 = str.substring(i);
			String str2 = str.substring(0, i);
			// System.out.println(str1 + str2);
			if (isPalindrome(str1 + str2)) return true;
		}
		return false;
	}

	/* Approach2:
	 * An Optimized Solution can work in O(n) time. The idea is similar to this post. Following are steps.
	 *    1) Let the given string be ‘str’ and length of string be ‘n’. Create a temporary string ‘temp’ which is of size 2n 
	 *       and contains str followed by str again. For example, let str be “aab”, temp would be “aabaab”.
	 *    2) Now the problem reduces to find a palindromic substring of length n in str. If there is palindromic substring 
	 *       of length n, then return true, else return false.
	 *  We can find whether there is a "palindromic substring of size n" or not in O(n) time
	 */
	public boolean isRotatedStringPalindrome2(String str) {
		int n = str.length();
		if (n <= 1) return true;
		// 1. Concatenate the same string twice
		String temp = str + str;
		// 2. Find the length of palindromic substring with size n;
		int len = palindromicSubstring(temp, n);
		return len == n ? true : false;
	}

	// Its longest Palindromic Substring algorithm
	private int palindromicSubstring(String str, int subStringLen) {
		int n = str.length();
		boolean[][] lookup = new boolean[n][n];
		for (int i = 0; i < n; i++)
			lookup[i][i] = true;
		int max = 1;

		for (int range = 2; range <= subStringLen; range++) {
			for (int i = 0; i < n - range + 1; i++) {
				int j = i + range - 1;
				if (lookup[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
					lookup[i][j] = true;
					if (max < range) max = range;
				}
			}
		}
		return max;
	}

	// Approach3: Check the odd no of chars count, if it is less than equal to one, then rotated string can be
	// palindrome
	public boolean isRotatedStringPalindrome3(String str) {
		return canFormPalindrome(str);
	}

	// Program to print all palindromes in a given range
	public void printPalindromesGivenRange(int min, int max) {
		for (int i = min; i <= max; i++) {
			if (isPalindrome(i)) System.out.print(i + " ");
		}
	}

	// Check if characters of a given string can be rearranged to form a palindrome:
	public boolean canFormPalindrome(String str) {
		int[] charCount = new int[EXTENDED_ASCII_CHAR_SIZE];
		for (int i = 0; i < str.length(); i++)
			charCount[str.charAt(i)]++;

		int oddCount = 0;
		for (int i = 0; i < charCount.length; i++) {
			if ((charCount[i] & 1) == 1) // if (charCount[i] % 2 == 1)
				oddCount++;

			if (oddCount > 1) return false;
		}
		return true;
	}

	// Shortest Palindrome
	public String shortestPalindrome(String str) {
		int n = str.length();
		if (n <= 1) return str;

		// Find how many char needed to add infront
		int i = 0, j = str.length() - 1, count = 0;
		while (i < j) {
			if (str.charAt(i) == str.charAt(j)) {
				i++;
				j--;
			} else {
				count++;
				j--;
			}
		}

		if (count == 0) return str;

		String subString = str.substring(n - count, n);
		return Utils.reverse(subString, 0, subString.length() - 1) + str;
	}

	/*Minimum number of Appends needed to make a string palindrome
	 * Given a string s we need to tell minimum characters to be appended (insertion at end) to make a string palindrome.
	 */

	public int noOfCharAppendsToFormPalindrome(String str) {
		return noOfCharAppendsToFormPalindrome(str, 0, str.length() - 1);
	}

	public int noOfCharAppendsToFormPalindrome(String str, int l, int h) {
		if (l > h) return 0;
		if (l == h) return 0;

		return (str.charAt(l) == str.charAt(h)) ? noOfCharAppendsToFormPalindrome(str, l + 1, h - 1)
				: noOfCharAppendsToFormPalindrome(str, l + 1, h) + 1;
	}

	/*Minimum insertions to form a palindrome: Naive Approach
	 * This is extension of previous one(noOfCharAppendsToFormPalindrome)
	 */
	public int minCharNeedToFormPalindrome1(String str) {
		return minCharNeedToFormPalindrome1(str, 0, str.length() - 1);
	}

	public int minCharNeedToFormPalindrome1(String str, int low, int high) {
		// Base Cases
		if (low > high) return Integer.MAX_VALUE; // This condition to ignore this value in below Min operation
		if (low == high) return 0;
		if (low == high - 1) return (str.charAt(low) == str.charAt(high)) ? 0 : 1;

		return (str.charAt(low) == str.charAt(high)) ? minCharNeedToFormPalindrome1(str, low + 1, high - 1)
				: Integer.min(minCharNeedToFormPalindrome1(str, low, high - 1),
						minCharNeedToFormPalindrome1(str, low + 1, high)) + 1;
	}

	// Using DP
	public int minCharNeedToFormPalindrome2(String str) {
		int n = str.length();
		int[][] lookup = new int[n][n];

		for (int range = 1; range < n; range++) {
			for (int i = 0, j = range; j < n; i++, j++) {
				// int j = i + range;
				lookup[i][j] = (str.charAt(i) == str.charAt(j)) ? lookup[i + 1][j - 1]
						: (Math.min(lookup[i + 1][j], lookup[i][j - 1]) + 1);
			}
		}

		return lookup[0][n - 1];
	}

	// Using DP, but use LCS solution
	public void minCharNeedToFormPalindrome3(String str) {
	}

	// Find the length and print the longest palindromic substring
	// 1.Naive Approach: Time Complexity:O(n^3)
	public String longestPalindromicSubString1(String str) {
		int max = -1, n = str.length();
		String maxString = null, subString;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				subString = str.substring(i, j + 1);
				if (isPalindrome(subString) && max < (j - i + 1)) {
					max = j - i + 1;
					maxString = subString;
				}
			}
		}
		System.out.println("Length of longest Palindromic SubString: " + max);
		return maxString;
	}

	// 2. Using DP
	public String longestPalindromicSubString2(String str) {
		int n = str.length(), max = 1, start = 0;
		boolean[][] table = new boolean[n][n];
		for (int i = 0; i < n; i++)
			table[i][i] = true;

		for (int len = 2; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1;
				if (str.charAt(i) == str.charAt(j) && (len == 2 || table[i + 1][j - 1])) {
					table[i][j] = true;
					if (len > max) {
						start = i;
						max = len;
					}
				}
			}
		}

		int maxLen = max + start;
		// Print sub string
		System.out.println("Length of longest Palindromic SubString: " + max);
		return str.substring(start, maxLen);
	}

	// Print all palindromic partitions of a string
	public void printPalindromicPartitions(String str) {

	}

	// Find all palindromic sub-strings of a given string
	// Count All Palindrome Sub-Strings in a String
	public void printPalindromicSubString(String str) {

	}

	// Count All Palindromic Subsequence in a given String
	// Approach1: DP - Revisit this solution???
	public int printPalindromicSubsequence(String str) {
		int N = str.length();

		// create a 2D array to store the count
		// of palindromic subsequence
		int[][] cps = new int[N + 1][N + 1];

		// palindromic subsequence of length 1
		for (int i = 0; i < N; i++)
			cps[i][i] = 1;

		// check subsequence of length L is palindrome or not
		for (int L = 2; L <= N; L++) {
			for (int i = 0; i < N; i++) {
				int k = L + i - 1;
				if (k < N) {
					if (str.charAt(i) == str.charAt(k)) cps[i][k] = cps[i][k - 1] + cps[i + 1][k] + 1;
					else cps[i][k] = cps[i][k - 1] + cps[i + 1][k] - cps[i + 1][k - 1];
				}
			}
		}

		// return total palindromic subsequence
		return cps[0][N - 1];
	}

	// Count maximum-length palindromes in a String
	public int countMaxLengthPalindromes(String str) {
		return 0;
	}

	// Number of palindromic permutations
	public void printPalindromicPermutations() {

	}

	/**
	 * ---------Binary String Problems :-----------------
	 */

	// Change if all bits can be made same by single flip
	// Approach1: Counting 0’s and 1’s
	public static boolean isSingleFlipMakesAllBitSame1(String str) {
		int zeros = 0, ones = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') zeros++;
			else ones++;
		}
		return (zeros == 1 || ones == 1);
	}

	// Approach2: Counting only 0's
	public static boolean isSingleFlipMakesAllBitSame2(String str) {
		int zerosCount = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') zerosCount++;
		}
		return (zerosCount == 1 || zerosCount == str.length() - 1);
	}

	// Program to add two binary strings
	public String addBinary(String a, String b) {
		// System.out.println("Zero: " + (int) '0' + " One: " + (int) '1');
		int i = a.length() - 1, j = b.length() - 1, sum = 0;
		String result = "";
		while (i >= 0 || j >= 0 || sum == 1) {
			// Add digits from last and carry
			sum += (i >= 0) ? a.charAt(i) - '0' : 0;
			sum += (j >= 0) ? b.charAt(j) - '0' : 0;

			// If current digit sum is 1 or 3, add 1 to result; else it will be zero
			result = (char) (sum % 2 + '0') + result;
			sum /= 2;
			i--;
			j--;
		}
		return result;
	}

	// Add two bit strings
	/*
	 * Following is step by step algorithm. 
	 *   1. Make them equal sized by adding 0s at the begining of smaller string.
	 *   2. Perform bit addition
	 *       …..Boolean expression for adding 3 bits a, b, c
	 *       …..Sum = a XOR b XOR c
	 *       …..Carry = (a AND b) OR ( b AND c ) OR ( c AND a )
	 */
	public String addBitStrings(String a, String b) {
		int diff, max = a.length();
		String result = "";
		// make the lengths same for a & b before adding
		if (a.length() < b.length()) {
			max = b.length();
			diff = b.length() - a.length();
			while (diff-- > 0) {
				a = '0' + a;
			}
		} else if (a.length() > b.length()) {
			max = a.length();
			diff = a.length() - b.length();
			while (diff-- > 0) {
				b = '0' + b;
			}
		}

		// Add all bits one by one
		int firstBit, secondBit, carry = 0, sum = 0;
		for (int i = 0; i < max; i++) {
			firstBit = a.charAt(i) - '0';
			secondBit = b.charAt(i) - '0';

			// Sum = a XOR b XOR c
			sum = (firstBit ^ secondBit ^ carry);
			result = (char) (sum + '0') + result;

			// Carry = (a AND b) OR ( b AND c ) OR ( c AND a )
			carry = (firstBit & secondBit) | (secondBit & carry) | (firstBit & carry);
		}
		// if overflow, then add a leading 1
		if (carry == 1) result = '1' + result;
		return result;
	}

	// Generate all binary strings from given pattern
	// Using Recursive Function
	public void generateBinaryStrings1(String str) {
		generateBinaryStrings1(str.toCharArray(), 0);
	}

	public void generateBinaryStrings1(char[] str, int index) {
		if (index == str.length) {
			for (int i = 0; i < str.length; i++)
				System.out.print(str[i] + "");
			System.out.println();
			// System.out.println(Arrays.toString(str));
			return;
		}

		if (str[index] == '?') {
			str[index] = '0';
			generateBinaryStrings1(str, index + 1);
			str[index] = '1';
			generateBinaryStrings1(str, index + 1);
			// NOTE: Need to backtrack as string is passed by reference to the function
			str[index] = '?';
		} else {
			generateBinaryStrings1(str, index + 1);
		}
	}

	// using Queue
	public void generateBinaryStrings2(String str) {
		char[] array = str.toCharArray();
		char[] temp;
		Queue<char[]> queue = new LinkedList<>();
		queue.add(array);
		while (!queue.isEmpty()) {
			temp = queue.poll();
			int index = linearSearch(temp, '?');
			if (index != -1) {
				temp[index] = '0';
				queue.add(temp);
				temp[index] = '1';
				queue.add(temp);
			} else {
				for (int i = 0; i < temp.length; i++)
					System.out.print(temp[i] + "");
				System.out.println();
			}
		}
	}

	private int linearSearch(char[] a, char ch) {
		int result = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == ch) return i;
		}
		return result;
	}

	/*Count number of binary strings without consecutive 1’s: (Its similar to fibonacci series) 
	 * The Fibonacci Numbers are 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 141, ….
	 * EG: For 1bit (n = 1), count = 2  
	 *     For 2bits(n = 2), count = 3  
	 *     For 3bits(n = 3), count = 5
	 *     For 4bits(n = 4), count = 8 
	 */
	// Approach1: Using DP(its bottom up to find the fib of any index n)
	public int binaryStringWithoutConsecutiveOnes1(int n) {
		int[] a = new int[n];
		int[] b = new int[n];
		a[0] = 1;
		b[0] = 1;
		for (int i = 1; i < n; i++) {
			a[i] = a[i - 1] + b[i - 1];
			b[i] = a[i - 1];
		}
		return a[n - 1] + b[n - 1];
	}

	// Approach2:Simple iterative approach to find the fib of any index n
	public int binaryStringWithoutConsecutiveOnes2(int n) {
		int a = 1, b = 1, i = 0;

		while (i < n) {
			int temp = a;
			a += b;
			b = temp;
		}
		return a + b;
	}

	/**
	 * 600. Non-negative Integers without Consecutive Ones: Given a positive integer n, find the number of non-negative
	 * integers less than or equal to n, whose binary representations do NOT contain consecutive ones.
	 */
	public int findIntegers(int num) {
		// Find the Binary value of num
		StringBuilder sb = new StringBuilder(Integer.toBinaryString(num));
		// System.out.println(Integer.toBinaryString(num)); //Length of binary digits
		// System.out.println(sb.toString());

		// Count number of binary strings without consecutive 1’s:
		int len = sb.length(); // Length of binary digits; For 1 bit - 1, 2 - 2, 4 - 4, 8 -5, 16 -8...
		int[] a = new int[len], b = new int[len];
		a[0] = 1;
		b[0] = 1;
		for (int i = 1; i < len; i++) {
			a[i] = a[i - 1] + b[i - 1];
			b[i] = a[i - 1];
		}
		int count = a[len - 1] + b[len - 1];

		// Eliminate element from given num to count(Count number of binary strings without consecutive 1’s)
		// Revisit this, how this is working
		sb.reverse();
		for (int i = len - 2; i >= 0; i--) {
			if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') break;
			if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') count -= b[i];
		}

		return count;
	}

	// Binary representation of next number
	public String nextNumber(String str) {
		return addBinary(str, "1");
	}

	/**
	 * ---------------------------Subsequence Problems-----------------------------
	 */

	// Print all subsequences of a string - Iterative & Recursive Method
	public void printSubSeq(String str) {
		System.out.println("Recursive Approach: ");
		subSequenceOfString1(str);

		System.out.println("Iterative Approach: ");
		subSequenceOfString2(str);

		System.out.println("Backtracking Approach: ");
		subSequenceOfString3(str);
	}

	// Print all Subsequences of String which Start with Vowel and End with Consonant.
	public void subSequenceStartWithVowel(String str) {
		subSequenceStartWithVowel(str, new StringBuilder(), 0);
	}

	// Using Backtracking
	private void subSequenceStartWithVowel(String str, StringBuilder currStr, int index) {
		if (index > str.length()) return;

		if (currStr.length() > 0 && Utils.isVowel(currStr.charAt(0))
				&& !Utils.isVowel(currStr.charAt(currStr.length() - 1)))
			System.out.println(currStr);

		for (int i = index; i < str.length(); i++) {
			currStr.append(str.charAt(i));
			subSequenceStartWithVowel(str, currStr, i + 1);
			currStr.deleteCharAt(currStr.length() - 1);
		}
	}

	// Given two strings, find if first string is a subsequence of second
	// 1.Recursive Implementation - Time Complexity: O(n)
	public boolean isSubsequence1(String str1, String str2) {
		return isSubsequence1(str1, str2, str1.length() - 1, str2.length() - 1);
	}

	public boolean isSubsequence1(String str1, String str2, int index1, int index2) {
		if (index1 == 0) return true;
		if (index2 == 0) return false;
		// If last characters of two strings are matching, decrement both indexes
		if (str1.charAt(index1) == str2.charAt(index2)) return isSubsequence1(str1, str2, index1 - 1, index2 - 1);

		// If last characters are not matching, decrement only str2
		return isSubsequence1(str1, str2, index1, index2 - 1);
	}

	// 2. Iterative Implementation - Time Complexity: O(n)
	public boolean isSubsequence2(String str1, String str2) {
		int m = str1.length(), n = str2.length();
		if (m < 1 || n < 1) return false;
		int i1 = 0;
		for (int i2 = 0; i2 < n; i2++) {
			if (str1.charAt(i1) == str2.charAt(i2)) i1++;
		}
		return i1 == m;
	}

	/*Count Distinct Subsequences:
	 * A Simple Solution to count distinct subsequences in a string with duplicates is to generate all subsequences. For every 
	 * subsequence, store it in a hash table if it doesn’t exist already. Time complexity of this solution is exponential and 
	 * it requires exponential extra space.
	 * 
	 * But below solution solution is linear time & space complexity- O(n).
	 *  2^0+2^1+2^2+2^3+....+2^n
	 */
	public int countDistinctSeq(String str) {
		int n = str.length();
		if (n == 0) return n;

		int[] charArray = new int[256];
		Arrays.fill(charArray, -1);
		int[] lookup = new int[n + 1];

		// Empty substring has only one subsequence
		lookup[0] = 1;

		for (int i = 1; i <= n; i++) {
			// Number of subsequences with substring str[0..i-1]
			lookup[i] = 2 * lookup[i - 1];

			// If current character has appeared before, then remove all subsequences ending with previous occurrence.
			if (charArray[(int) str.charAt(i - 1)] != -1) lookup[i] -= lookup[charArray[(int) str.charAt(i - 1)]];

			// Mark occurrence of current character
			charArray[(int) str.charAt(i - 1)] = i - 1;
		}

		return lookup[n];
	}

	public int noOfSeqOfabc(String s) {
		// Returns count of subsequences of the form a^i b^j c^k
		int aCount = 0, bCount = 0, cCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'a') aCount = 1 + 2 * aCount;
			else if (s.charAt(i) == 'b') bCount = aCount + 2 * bCount;
			else if (s.charAt(i) == 'c') cCount = bCount + 2 * cCount;
		}
		return cCount;
	}

	// Number of subsequences in a string divisible by n
	// Approach1: Brute force approach
	public int noOfSeqDivisibleByN(String s, int n, int idx, int rem) {

		if (idx == s.length()) return 0;

		int result = 0;

		/*Exclude it, left side(or first recursive call doesn't give any result); it always gives zero */
		result += noOfSeqDivisibleByN(s, n, idx + 1, rem);

		// Take element one by one from the string and divide by n
		int newRem = ((rem * 10) + s.charAt(idx) - '0') % n;
		if (newRem == 0) // If any one of the sequence is divisible by n, then increment it
			result++;

		// Include it and thus new remainder
		result += noOfSeqDivisibleByN(s, n, idx + 1, newRem);
		return result;
	}

	// Approach2: Dynamic Programming
	public int noOfSeqDivisibleByNusingDP(String s, int n) {
		int len = s.length();
		char ch;
		int temp;
		int[][] dp = new int[len][n];
		dp[0][(s.charAt(0) - '0') % n]++;
		for (int i = 1; i < len; i++) {
			ch = s.charAt(i);
			dp[i][(ch - '0') % n]++;
			for (int j = 0; j < n; j++) {
				dp[i][j] += dp[i - 1][j];
				temp = ((j * 10) + ch - '0') % n;
				dp[i][temp] += dp[i - 1][j];
			}
		}
		return dp[len - 1][0];
	}

	// Find number of times a string occurs as a subsequence in given string
	// Using Recursive Approach
	public int countStringInSubSeq(String s1, String s2) {
		return countStringInSubSeq(s1, s2, s1.length(), s2.length());
	}

	public int countStringInSubSeq(String s1, String s2, int m, int n) {
		if ((m == 0 && n == 0) || n == 0) return 1;
		if (m == 0) return 0;

		if (s1.charAt(m - 1) == s2.charAt(n - 1))
			return countStringInSubSeq(s1, s2, m - 1, n - 1) + countStringInSubSeq(s1, s2, m - 1, n);
		else return countStringInSubSeq(s1, s2, m - 1, n);
	}

	// Using DP
	public int countStringInSubSeq2(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		int[][] result = new int[m + 1][n + 1];

		for (int i = 0; i <= n; i++)
			result[0][i] = 0;

		for (int i = 0; i <= m; i++)
			result[i][0] = 1;

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
				} else {
					result[i][j] = result[i - 1][j];
				}
			}
		}

		return result[m][n];
	}

	/*Longest common subsequence with permutations allowed.  The output longest string must be sorted.*/
	public String lcsWithAllPermutations(String str1, String str2) {
		int n1 = str1.length(), n2 = str2.length();
		final int ALPHABET = 26;
		int[] count1 = new int[ALPHABET];
		int[] count2 = new int[ALPHABET];

		for (int i = 0; i < n1; i++)
			count1[str1.charAt(i) - 'a']++;

		for (int i = 0; i < n2; i++)
			count2[str2.charAt(i) - 'a']++;

		StringBuilder sb = new StringBuilder();
		// Output will be sorted automatically as part of this iteration
		for (int i = 0; i < 26; i++) {
			if (count1[i] != 0 && count2[i] != 0) {
				int n = Integer.min(count1[i], count2[i]);
				char ch = (char) (i + 'a');
				while (n-- > 0)
					sb.append(ch);
			}
		}

		return sb.toString();
	}

	// Longest Common subsequence: Using Recursive approach
	public int longestCommonSubSequence1(String str1, String str2) {
		return longestCommonSubSequence1(str1, str2, str1.length(), str2.length());
	}

	private int longestCommonSubSequence1(String str1, String str2, int m, int n) {
		if (m == 0 || n == 0) return 0;

		if (str1.charAt(m - 1) == str2.charAt(n - 1)) return 1 + longestCommonSubSequence1(str1, str2, m - 1, n - 1);

		return Integer.max(longestCommonSubSequence1(str1, str2, m - 1, n),
				longestCommonSubSequence1(str1, str2, m, n - 1));
	}

	// Using DP Approach
	public int longestCommonSubSequence2(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		int[][] lookup = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					lookup[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					lookup[i][j] = 1 + lookup[i - 1][j - 1];
				} else {
					lookup[i][j] = Math.max(lookup[i - 1][j], lookup[i][j - 1]);
				}
			}
		}
		printLongestSubSequence(lookup, s1, s2);
		return lookup[m][n];
	}

	// Print the longest common sub sequence
	private void printLongestSubSequence(int[][] lcsArr, String s1, String s2) {
		int i = s1.length(), j = s2.length();
		int longSeqCount = lcsArr[i][j];
		char[] result = new char[longSeqCount];
		int index = longSeqCount;
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				result[--index] = s1.charAt(i - 1);
				i--;
				j--;
			} else if (lcsArr[i - 1][j] > lcsArr[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		System.out.print("SubSequence:");
		for (int k = 0; k < longSeqCount; k++) {
			System.out.print(result[k] + "-");
		}
	}

	// Shortest common super sequence:
	public int shortestCommonSuperSequence1(String str1, String str2) {
		return shortestCommonSuperSequence1(str1, str2, str1.length(), str2.length());
	}

	private int shortestCommonSuperSequence1(String str1, String str2, int m, int n) {
		if (m == 0) return n;
		if (n == 0) return m;

		if (str1.charAt(m - 1) == str2.charAt(n - 1)) return 1 + shortestCommonSuperSequence1(str1, str2, m - 1, n - 1);

		return 1 + Math.min(shortestCommonSuperSequence1(str1, str2, m - 1, n),
				shortestCommonSuperSequence1(str1, str2, m, n - 1));
	}

	/**
	 * ---------------------------Substring: 1.General Problems-----------------------------
	 */

	// Find substrings that contain all vowels: Time Complexity: O(n) and extra space for hashset
	public void subStringsAllVowels(String str) {
		Set<Character> set = new HashSet<>();
		int startIndex = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Utils.isVowel(str.charAt(i))) {
				if (set.isEmpty()) startIndex = i;
				set.add(str.charAt(i));
				if (set.size() == 5) System.out.println(str.substring(startIndex, i + 1));
			} else {
				set.clear();
			}
		}
	}

	// Length of the longest substring with equal 1s and 0s
	public int longestSubstringWithEqual0And1(String str) {
		int zeroCount = 0, oneCount = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') zeroCount++;
			else oneCount++;
		}

		if (zeroCount > 0 && oneCount > 0) return zeroCount < oneCount ? 2 * zeroCount : 2 * oneCount;

		return 0;
	}

	// Length of longest common substring using DP
	public int longestCommonSubstring(String str1, String str2) {
		int m = str1.length(), n = str2.length();
		int[][] lookup = new int[m][n];

		int max = 0, row = 0, col = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					if (i == 0 || j == 0) {
						lookup[i][j] = 1;
					} else {
						lookup[i][j] = lookup[i - 1][j - 1] + 1;
					}
					// Update the max value
					if (max < lookup[i][j]) {
						max = lookup[i][j];
						row = i;
						col = j;
					}
				}
			}
		}

		System.out.println("Longest Common Substring: " + printLongestCommonSubstring(lookup, row, col, str1));
		return max;
	}

	public String printLongestCommonSubstring(int[][] lookup, int row, int col, String str) {
		String substring = "";
		while (row >= 0 && col >= 0 && lookup[row][col] != 0) {
			substring = str.charAt(row) + substring;
			row--;
			col--;
		}
		return substring;
	}

	// Longest Substring with At Least K Repeating Characters
	/* Understand the logic, it just copied
	 * For each h, apply two pointer technique to find the longest substring with at least K repeating characters and the number 
	 * of unique characters in substring is h.
	 */
	public int longestSubstringAtleastKRepeatingChar(String s, int k) {
		char[] str = s.toCharArray();
		int[] counts = new int[26];
		int h, i, j, idx, max = 0, unique, noLessThanK;
		for (h = 1; h <= 26; h++) {
			Arrays.fill(counts, 0);
			i = 0;
			j = 0;
			unique = 0;
			noLessThanK = 0;
			while (j < str.length) {
				if (unique <= h) {
					idx = str[j] - 'a';
					if (counts[idx] == 0) unique++;
					counts[idx]++;
					if (counts[idx] == k) noLessThanK++;
					j++;
				} else {
					idx = str[i] - 'a';
					if (counts[idx] == k) noLessThanK--;
					counts[idx]--;
					if (counts[idx] == 0) unique--;
					i++;
				}
				if (unique == h && unique == noLessThanK) max = Math.max(j - i, max);
			}
		}
		return max;
	}

	// Longest Common Prefix : https://www.geeksforgeeks.org/longest-common-prefix-set-6-sorting/
	/* Different approaches:
	 * 1.Word by Word Matching
	 * 2.Character by Character Matching
	 * 3.Divide and Conquer
	 * 4.Binary Search
	 * 5.Using Trie
	 */
	// Approach1: Brute Force Approach
	public void longestCommonPrefix1(String str1, String str2) {
		int max = 0, current = 0, j = 0, index = -1;
		for (int i = 0; i <= str2.length(); i++) {
			j = 0;
			current = 0;
			while (j < str1.length() && (i + j) < str2.length()) {
				if (str1.charAt(j) != str2.charAt(i + j)) break;
				j++;
				current++;
			}
			if (current > max) {
				max = current;
				index = i;
			}
		}
		System.out.println(index + " " + str1.substring(0, max));
	}

	/**
	 * ---------------------------Substring: 2.Palindromic Problems-----------------------------
	 */
	// Length of longest Palindromic substring using DP
	public void longestPalindromicSubstring(String str) {
		System.out.println("Longest Palindromic Substring(Naive Approach): " + longestPalindromicSubString1(str));
		System.out.println("Longest Palindromic Substring(DP): " + longestPalindromicSubString2(str));
	}

	/**
	 * ---------Substring: 3.Pattern Searching Problems -----------------
	 */
	// Naive Pattern search & find the Frequency of a substring in a string:
	public int naivePatternSearching(String str, String pattern) {
		int j, count = 0, strLength = str.length(), patternLength = pattern.length();
		for (int i = 0; i <= (strLength - patternLength); i++) {
			if (str.charAt(i) == pattern.charAt(0)) {// If first character is same, then proceed comparison
				for (j = 0; j < patternLength; j++) {
					if (str.charAt(i + j) != pattern.charAt(j)) break;
				}
				if (j == patternLength) {
					count++;
					System.out.println("Pattern found at: " + i);
				}
			}
		}
		return count;
	}

	/**
	 * ---------Substring: 3.Sliding Window Problems -----------------
	 */
	// Minimum Window Substring:
	// Approach1: Sliding Window solution using Array to store the data
	public String minWindowSubstring1(String str, String pat) {
		int len1 = str.length(), len2 = pat.length();
		if (len1 == 0 || len2 == 0 || (len1 < len2)) return "";

		int[] hashStr = new int[EXTENDED_ASCII_CHAR_SIZE];
		int[] hashPat = new int[EXTENDED_ASCII_CHAR_SIZE];

		for (int i = 0; i < len2; i++)
			hashPat[pat.charAt(i)]++;

		int l = 0, r = 0, minWindow = Integer.MAX_VALUE, minLeft = -1, count = 0, index = 0;
		// Move 'r' one by one from zero
		while (r < len1) {
			index = str.charAt(r); // Get the String index
			hashStr[index]++;// Increase the char count in hashStr

			if (hashPat[index] != 0 && hashStr[index] <= hashPat[index]) count++;

			// Move 'l' from zero and update min window & the string map
			while (l <= r && count == len2) {
				if ((r - l + 1) < minWindow) {
					minWindow = r - l + 1;
					minLeft = l;
				}

				index = str.charAt(l); // Get the String index
				hashStr[index]--; // Decrease the char count in strMap
				if (hashStr[index] < hashPat[index]) count--;
				l++;
			}
			r++;
		}
		return minLeft != -1 ? str.substring(minLeft, minLeft + minWindow) : "";
	}

	// Approach2: Sliding Window solution using Map to store the data
	public String minWindowSubstring2(String str, String pat) {
		int len1 = str.length(), len2 = pat.length();
		if (len1 == 0 || len2 == 0 || (len1 < len2)) return "";

		Map<Character, Integer> strMap = new HashMap<>();
		Map<Character, Integer> patMap = new HashMap<>();

		for (int i = 0; i < len2; i++) {
			int val = patMap.getOrDefault(pat.charAt(i), 0);
			patMap.put(pat.charAt(i), val + 1);
		}

		int l = 0, r = 0, minWindow = Integer.MAX_VALUE, minLeft = -1, count = 0;
		char ch;
		// Move 'r' one by one from zero
		while (r < len1) {
			ch = str.charAt(r);
			// Increase the char count in strMap
			int val = strMap.getOrDefault(ch, 0);
			strMap.put(ch, val + 1);

			// Increase the count, if char matches with pattern
			if (patMap.get(ch) != null && strMap.get(ch) <= patMap.get(ch)) count++;

			// Move 'l' from zero and update minwindow & the string map
			while (l <= r && count == len2) {
				// update the window
				if ((r - l + 1) < minWindow) {
					minWindow = r - l + 1;
					minLeft = l;
				}
				// Decrease the char count in strMap
				ch = str.charAt(l);
				strMap.put(ch, strMap.get(ch) - 1);
				if (patMap.get(ch) != null && strMap.get(ch) < patMap.get(ch)) count--;
				l++;
			}
			r++;
		}
		return minLeft != -1 ? str.substring(minLeft, minLeft + minWindow) : "";
	}

	// Longest Substring Without Repeating Characters
	// Approach1: Sliding Window solution using Array to store the data
	public int longestSubstringWithoutRepeatingChar1(String s) {
		if (s.length() == 0) return 0;

		int l = 0, r = 0, maxLen = 0, n = s.length();
		int[] countArr = new int[128];
		while (r < n) {
			char ch = s.charAt(r);
			if (countArr[ch] == 0) {
				countArr[ch]++;
				r++;
				maxLen = Math.max(maxLen, r - l);
			} else {
				while (countArr[ch] > 0) {
					countArr[s.charAt(l)]--;
					l++;
				}
			}
		}
		return maxLen;
	}

	// Approach2: Sliding Window solution using map to store the data
	public int longestSubstringWithoutRepeatingChar2(String s) {
		if (s.length() == 0) return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int l = 0, r = 0, maxLen = 0, n = s.length();
		while (r < n) {
			char ch = s.charAt(r);
			if (!map.containsKey(ch)) {
				map.put(ch, r);
				r++;
				maxLen = Math.max(maxLen, r - l);
			} else {
				l = Math.max(l, map.get(ch) + 1); // If char already present, move left index to repeating char
				map.remove(ch);
			}
		}
		return maxLen;
	}

	// Approach3: Similar to same pattern like Longest Substring with At Most 2 Distinct Characters, Longest Substring
	// with
	// At Most K Distinct Characters
	public int longestSubstringWithoutRepeatingChar3(String s) {
		int l = 0, r = 0, maxLen = 0, n = s.length(), counter = 0;
		int[] countArr = new int[128];
		while (r < n) {
			char ch = s.charAt(r++);
			if (countArr[ch]++ > 0) counter++;
			while (counter > 0 && l < n)
				if (countArr[s.charAt(l++)]-- > 1) counter--;

			maxLen = Math.max(maxLen, r - l);
		}
		return maxLen;
	}

	/* Longest Substring with At Most 2 Distinct Characters:
	 * Given a string, find the longest substring that contains only two unique characters. For example, given "abcbbbbcccbdddadacb",
	 * the longest substring that contains 2 unique character is "bcbbbbcccb".
	 */
	// Approach1: Using Array storage
	public int longestSubstring2Distinct1(String s) {
		int l = 0, r = 0, maxLen = 0, n = s.length(), counter = 0;
		int[] countArr = new int[128];
		while (r < n) {
			char ch = s.charAt(r++);
			if (countArr[ch]++ == 0) counter++;
			while (counter > 2 && l < n)
				if (countArr[s.charAt(l++)]-- == 1) counter--;

			maxLen = Math.max(maxLen, r - l);
		}
		return maxLen;
	}

	// Approach2: Using Map storage
	public int longestSubstring2Distinct2(String s) {
		int n = s.length();
		if (n == 0) return 0;

		Map<Character, Integer> map = new HashMap<>();
		int maxLen = 0, l = 0, r = 0;
		char ch;
		while (r < n) {
			ch = s.charAt(r++);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			while (map.size() > 2 && l < n) {
				ch = s.charAt(l++);
				if (map.getOrDefault(ch, 0) == 1) map.remove(ch);
				else map.put(ch, map.getOrDefault(ch, 0) - 1);
			}

			maxLen = Math.max(maxLen, r - l);
		}

		return maxLen;
	}

	/*Longest Substring with At Most K Distinct Characters:
	 * Find the longest substring with k unique characters in a given string. The following solution is corrected. Given "abcadcacacaca" and 3, 
	 * it returns "cadcacacaca".
	 */
	// Approach1: Using Array storage
	public int longestSubstringKDistinct1(String s, int k) {
		int l = 0, r = 0, maxLen = 0, n = s.length(), counter = 0;
		int[] countArr = new int[128];
		while (r < n) {
			char ch = s.charAt(r++);
			if (countArr[ch]++ == 0) counter++;

			while (counter > k && l < n)
				if (countArr[s.charAt(l++)]-- == 1) counter--;

			maxLen = Math.max(maxLen, r - l);
		}
		return maxLen;
	}

	// Approach2: Using Map storage
	public int longestSubstringKDistinct2(String s, int k) {
		int n = s.length();
		if (n == 0 || n < k) return 0;

		Map<Character, Integer> map = new HashMap<>();
		int maxLen = 0, l = 0, r = 0;
		char ch;
		while (r < n) {
			ch = s.charAt(r++);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			while (map.size() > k && l < n) {
				ch = s.charAt(l++);
				if (map.getOrDefault(ch, 0) == 1) map.remove(ch);
				else map.put(ch, map.getOrDefault(ch, 0) - 1);
			}

			maxLen = Math.max(maxLen, r - l);
		}

		return maxLen;

	}

	/**
	 * ---------Misc Problems :-----------------
	 */

	// A Program to check if strings are rotations of each other or not
	// Using Java API
	public boolean areRotatedStringSame1(String str1, String str2) {
		return (str1.length() == str2.length()) && (str1 + str1).indexOf(str2) != -1;
	}

	// Using KMP Pattern Searching Algorithm
	public boolean areRotatedStringSame2(String str1, String str2) {
		boolean flag = false;
		if (str1.length() == str2.length()) {
			PatternSearching patternSearching = new PatternSearching();
			flag = patternSearching.KMPAlgorithmUtil(str1 + str1, str2);
		}
		return flag;
	}

	// Panagram: sentence which it contains every letter of the alphabet.
	public String pangrams(String s) {
		String result = "not pangram";
		if (s.length() > 0) {
			String str = s.toLowerCase();
			int[] alphabet = new int[26];

			int val;
			for (int i = 0; i < str.length(); i++) {
				val = str.charAt(i) - 'a';
				if (val >= 0 && val <= 25) alphabet[val]++;
			}
			boolean flag = true;
			for (int i = 0; i < alphabet.length; i++) {
				if (alphabet[i] == 0) {
					flag = false;
					break;
				}
			}

			if (flag) result = "pangram";
		}
		return result;
	}

	public int atoi(String str) {
		str = str.trim();
		int limit = Integer.MAX_VALUE / 10;
		char ch;
		int result = 0;
		boolean positive = true;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (i == 0 && ch == '-') {
				positive = false;
				limit = -(Integer.MIN_VALUE / 10) + 1;
			} else if (i == 0 && ch == '+') {
				continue;
			} else if (ch < '0' || ch > '9') {
				if (i > 0) return positive ? result : -result;
				return 0;
			} else {
				if (positive && result >= limit && ((ch - '0') > 7 || i != str.length() - 1)) { // To handle the string
																								// over the range
					return Integer.MAX_VALUE;
				} else if (result >= limit && ((ch - '0') > 8 || i != str.length() - 1)) { return Integer.MIN_VALUE; }
				result = result * 10 + (ch - '0');
			}
		}
		return positive ? result : -result;
	}

	/*
	 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is
	 * decoded back to the original list of strings.
	 * Note:
	 *   - The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized
	 *     enough to work on any possible characters.
	 *   - Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
	 *   - Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
	 */

	public String encodeString(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str.length()).append("/").append(str); // concatenate length of string + any char + string
		}
		return sb.toString();
	}

	public List<String> decodeString(String str) {
		List<String> result = new ArrayList<>();
		int i = 0;
		while (i < str.length()) {
			int slashIndex = str.indexOf("/", i); // Here 'i' is fromIndex
			int len = Integer.valueOf(str.substring(i, slashIndex));
			i = slashIndex + len + 1;
			String s = str.substring(slashIndex + 1, i);
			result.add(s);
		}
		return result;
	}

	public static String licenseKeyFormatting(String s, int K) {
		if (s.length() == 0) return "";

		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--)
			if (s.charAt(i) != '-') sb.append(sb.length() % (K + 1) == K ? '-' : "").append(s.charAt(i));

		return sb.reverse().toString().toUpperCase();
	}

	// Sentence Screen Fitting:
	public static int wordsTyping(String[] sentence, int rows, int cols) {
		String s = String.join(" ", sentence) + " ";
		int len = s.length(), count = 0;
		int[] map = new int[len];
		for (int i = 1; i < len; ++i)
			map[i] = s.charAt(i) == ' ' ? 1 : map[i - 1] - 1;

		for (int i = 0; i < rows; ++i) {
			count += cols;
			count += map[count % len];
		}
		return count / len;
	}

	public int lengthOfLastWord(String s) {
		String[] arr = s.split(" ");

		return arr.length == 0 ? 0 : arr[arr.length - 1].length();
	}

	/**
	 * ---------Coding Interview Problems :-----------------
	 */
	// Convert char to art
	public void displayCharChart(ArrayList<String> ascii, int width, int height, String text) {
		int charIndex = -1;

		width = width + 1;// width+1 -> to consider space b/w char's
		// print result
		for (int j = 0; j < height; j++) {
			for (int k = 0; k < text.length(); k++) {
				charIndex = text.charAt(k) - 'A';
				System.out.print(ascii.get(j).substring(charIndex * width, charIndex * width + width));
			}
			System.out.println();
		}
	}

	// Convert art to char
	public char getAlphabetChar(ArrayList<String> asciiChart, ArrayList<String> singleChar, int width, int height) {
		int count, index = -1, ALPHABHET_SIZE = 26;
		char ch = ' ';
		int w = width + 1;// width+1 -> to consider space b/w char's
		for (int i = 0; i < (ALPHABHET_SIZE * w); i = i + 4) {
			count = 0;
			for (int j = 0; j < 5; j++) {
				if (asciiChart.get(j).substring(i, i + width).equalsIgnoreCase(singleChar.get(j).substring(0, width)))
					count++;
			}
			if (count == height) {
				index = i;
				break;
			}
		}

		if (index != -1) ch = (char) ((index / w) + 65);
		return ch;
	}

	public String removeRecursiveDuplicates(String s) {
		int n = s.length();
		if (n <= 1) return s;
		StringBuilder sb = new StringBuilder();
		boolean duplicateFlag = false;
		for (int i = 0; i < n; i++) {
			if ((i < n - 1 && s.charAt(i) != s.charAt(i + 1)) || (i == n - 1 && s.charAt(i - 1) != s.charAt(i))) {
				sb.append(s.charAt(i));
			} else {
				duplicateFlag = true;
				while (i < n - 1 && s.charAt(i) == s.charAt(i + 1))
					i++;
			}
		}
		return duplicateFlag == true ? removeRecursiveDuplicates(sb.toString()) : sb.toString();
	}

	public String reverseWords(String s) {
		/* I'm splitting on the regex for one-or-more whitespace, this takes care of multiple spaces/tabs/newlines/etc in the
		   input. Since the input could have leading/trailing whitespace, which would result in empty matches, I first trim the
		   input string. */
		String[] words = s.trim().split("\\s+");
		if (words.length == 0) return "";
		int n = words.length;
		System.out.println(words.toString());
		StringBuilder sb = new StringBuilder();
		sb.append(words[n - 1].trim());
		for (int i = n - 2; i >= 0; i--)
			sb.append(" ").append(words[i].trim());

		return sb.toString();
	}

	// Approach2:
	public String longestCommonPrefix1(String[] strs) {
		if (strs == null || strs.length == 0) return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0 && prefix.length() > 0)
				prefix = prefix.substring(0, prefix.length() - 1);

		return prefix;
	}

	// Approach3: Sort the array first, and then you can simply compare the first and last elements in the sorted array.
	public String longestCommonPrefix2(String[] strs) {
		if (strs == null) return null;
		if (strs.length == 0) return "";

		Arrays.sort(strs);
		char[] first = strs[0].toCharArray();
		char[] last = strs[strs.length - 1].toCharArray();

		int i = 0, len = Math.min(first.length, last.length);
		while (i < len && first[i] == last[i])
			i++;
		return strs[0].substring(0, i);
	}

	/**
	 * -----------------SubArray/SubString Vs Subsequence Vs SubSet Vs Permutations Vs Combinations Comparisons---------
	 */

	/* 
	 * Subsequence Vs SubSet Vs Permutations Vs Combinations  -> Refer BacktrackingAlgorithms.java 
	 * SubArray - Refer StringProblems.java 
	 * SubString - Refer ArrayProblems.java
	 * 
	* 1.SubArray/SubString: A sub array is a contiguous part of array. An array that is inside another array. It is contiguous and inherently
	* maintains the order. In general, for an array/string of size n, there are n*(n+1)/2 non-empty subarray/substring. 
	* Eg: abc: 6 substrings -> a, ab, abc, b, bc, c
	* SubArrays:[1,2,3] -> {1},{1,2},{1,2,3},{2},{2,3},{3}; (3*4)/2 = 6
	* 
	* 2.SubSequence: A subsequence is a sequence that can be derived from another sequence by zero or more elements, without
	* changing the order of the remaining elements. sequence of size n, we can have (2^n) sub-sequences or (2^n-1)
	* non-empty sub-sequences in total. SubSequence - need not be contiguous but needs to maintain the order
	* Eg: abc: 8 sequences -> a, ab, ac, abc, b, bc, c & empty string
	* Array SubSequences: {},{1},{1,2},{1,3},{2},{2,3},{3},{1,2,3} -> 2^3 = 8
	* 
	* 3.SubSet: A subset is any possible combination of original set. The term subset is often used for subsequence but this
	* is wrong. A subsequence always maintain the relative order of elements of the array(i.e increasing index) but there
	* is no such restriction on a subset. Subset - It is no order and no contiguous For example {3,1} is valid subset of {1,2,3,4,5}
	* - Subset: 2^n (Order doesn't matter in sets)
	* - Subsequence: 2^n (Since we keep the original ordering, this is the same.)
	* 
	* 4.Permutations: act of arranging all the members of a set into some sequence or order, or if the set is already ordered,
	* rearranging (reordering) its elements, a process called permuting. Number of permutations(npr)= n!
	* Eg: abc: 3!=6 permutations -> abc, acb, bac, bca, cab, cba
	* 
	* 5.Combinations: combination is a selection of items from a collection, such that (unlike permutations) the order of selection 
	* does not matter.  No of combinations(ncr) = n!/((n-r)! * r!);  where r - combinations in n elements
	* Eg: n=5, r=2 -> 5!/(5-2)!*2! -> 10
	* Eg: abcde: n=5,r=2; 10 combinations: ab, ac, ad, ae, bc, bd, be, cd, ce, de (Here ab==ba, ac==ca; just combination matters, not order)
	*  
	* Note:All subarrays are subsequences All subsequences are subset. But sometimes subset and subarrays and sub sequences
	* are used interchangeably and the word contiguous is prefixed to make it more clear
	*/

	// 1.1 SubArrays: n(n+1) * 1/2 (Elements must be consecutive); Time Complexity: O(n^3)
	public void printSubArrays(int[] arrA) {
		int arrSize = arrA.length;
		for (int i = 0; i < arrSize; i++) {
			for (int j = i; j < arrSize; j++) {
				for (int k = i; k <= j; k++) {
					System.out.print(arrA[k] + " ");
				}
				System.out.println();
			}
		}
	}

	// 1.2Substring: n(n+1) * 1/2 (Elements must be consecutive); Time Complexity: O(n^3)
	public void printSubStrings1(String str) {
		int len = str.length();
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				for (int k = i; k <= j; k++) {
					System.out.print(str.charAt(k) + "");
				}
				System.out.print(" ");
			}
		}
	}

	public void printSubStrings2(String str) {
		int len = str.length();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {
				System.out.print(str.substring(i, j) + " ");
			}
		}
	}

	/* Subset: 2^n (Order doesn't matter in sets)
	   Subsequence: 2^n (Since we keep the original ordering, this is the same.)*/

	// 2.1 Subsequence(Binary String): Recursive Approach - Time Complexity: O(2^n)
	public void printBinarySequence1(int n) {
		int[] arr = new int[n];
		printBinarySequence1(arr, n);
	}

	public void printBinarySequence1(int[] arr, int n) {
		if (n <= 0) {
			System.out.println(Arrays.toString(arr));
		} else {
			arr[n - 1] = 0;
			printBinarySequence1(arr, n - 1);
			arr[n - 1] = 1;
			printBinarySequence1(arr, n - 1);
		}
	}

	// 2.2 SubSequence(Binary String): Iterative Approach - Time Complexity: O(2^n * strLen)
	public void printBinarySequence2(int n) {
		int noOfSeq = (int) Math.pow(2, n);

		for (int i = 0; i < noOfSeq; i++) {
			for (int j = n - 1; j >= 0; j--) {
				if ((i & (1 << j)) != 0) System.out.print(1 + "");
				else System.out.print(0 + "");
			}
			System.out.println();
		}
	}

	// 2.3 Subsequence(Array): Recursive Approach
	public void subSequenceOfArray1(int[] a) {
		int[] enableBit = new int[a.length];
		subSequenceOfArray1(a, 0, enableBit);
	}

	public void subSequenceOfArray1(int[] a, int index, int[] enableBit) {
		if (index == a.length) {
			printSubSequence(a, enableBit);
		} else {
			enableBit[index] = 1;
			subSequenceOfArray1(a, index + 1, enableBit);
			enableBit[index] = 0;
			subSequenceOfArray1(a, index + 1, enableBit);
		}
	}

	public void printSubSequence(int[] a, int[] enableBit) {
		String result = "";
		for (int i = 0; i < a.length; i++) {
			if (enableBit[i] == 1) result += a[i];
		}
		if (result == "") System.out.println("Empty Set");
		else System.out.println(result);
	}

	// 2.4 Subsequence(Array): Iterative approach
	public void subSequenceOfArray2(int[] a) {
		int noofSeq = (int) Math.pow(2, a.length);
		for (int i = 0; i < noofSeq; i++) {
			for (int j = 0; j < a.length; j++) {
				/*Returns true if and only if the designated bit is set. (Computes ((this & (1<<n)) != 0).)*/
				// if (BigInteger.valueOf(i).testBit(j)) {
				if ((i & (1 << j)) != 0) {
					System.out.print(a[j] + "");
				}
			}
			System.out.println();
		}
	}

	/// 2.5 Subsequence(String): Recursive Approach Time Complexity: O(2^n)
	public void subSequenceOfString1(String str) {
		int[] enableBit = new int[str.length()];
		subSequenceOfString1(str, 0, enableBit);
	}

	public void subSequenceOfString1(String str, int index, int[] enableBit) {
		if (index == str.length()) {
			printSubSequence(str, enableBit);
		} else {
			enableBit[index] = 0;
			subSequenceOfString1(str, index + 1, enableBit);
			enableBit[index] = 1;
			subSequenceOfString1(str, index + 1, enableBit);
		}
	}

	public void printSubSequence(String str, int[] enableBit) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (enableBit[i] == 1) result += str.charAt(i);
		}

		if (result == "") System.out.println("Empty Set");
		else System.out.println(result);
	}

	// 2.6 Subsequence(String): Iterative approach - Time Complexity: O(2^n * strLen)
	public void subSequenceOfString2(String str) {
		// Calculate no of sequences
		int noofSeq = (int) Math.pow(2, str.length());

		for (int i = 0; i < noofSeq; i++) { // Iterate for no of sequences
			for (int j = 0; j < str.length(); j++) { // Iterate for string length
				if ((i & (1 << j)) != 0) System.out.print(str.charAt(j) + "");
			}
			System.out.println();
		}
	}

	// 2.7 Subsequence(String): BackTracking approach - Time Complexity:O(2^n)
	public void subSequenceOfString3(String str) {
		subSequenceOfString3(str, new StringBuilder(), 0);
	}

	/* One by one fix characters and recursively generates all subsets starting from them. After every recursive call, 
	 * we remove last character so that next permutation can be generated.
	 */
	public void subSequenceOfString3(String str, StringBuilder currString, int index) {
		if (index > str.length()) return;

		System.out.println(currString.toString());

		for (int i = index; i < str.length(); i++) {
			currString.append(str.charAt(i)); // Append the char one by one
			subSequenceOfString3(str, currString, i + 1);
			currString.deleteCharAt(currString.length() - 1); // Remove the char after the recursive call
		}
	}

	// 4. Permutation of the String: Time Complexity: O(n!)
	public void permutationOfString(String str) {
		permutationOfString(str.toCharArray(), 0, str.length() - 1);
	}

	public void permutationOfString(char[] charArray, int l, int h) {
		if (l > h) {
			System.out.println(new String(charArray));
			return;
		}
		for (int i = l; i <= h; i++) {
			Utils.swap(charArray, l, i);
			permutationOfString(charArray, l + 1, h);
			Utils.swap(charArray, l, i);
		}
	}

	// 4. Permutation of Array
	public ArrayList<ArrayList<Integer>> permutationOfArray(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		// start from an empty list
		result.add(new ArrayList<Integer>());

		for (int i = 0; i < num.length; i++) {
			// list of list in current iteration of the array num
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

			for (ArrayList<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size() + 1; j++) {
					// + add num[i] to different locations
					l.add(j, num[i]);

					ArrayList<Integer> temp = new ArrayList<Integer>(l);
					current.add(temp);

					// System.out.println(temp);

					// - remove num[i] add
					l.remove(j);
				}
			}

			result = new ArrayList<ArrayList<Integer>>(current);
		}

		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	// 5. Combination: Make all combinations of size k
	public ArrayList<ArrayList<Integer>> combination(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		if (n <= 0 || n < k) return result;

		ArrayList<Integer> item = new ArrayList<Integer>();
		dfs(n, k, 1, item, result); // because it need to begin from 1

		return result;
	}

	// Use DFS approach to print all the combinations
	private void dfs(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
		if (item.size() == k) {
			res.add(new ArrayList<Integer>(item));
			return;
		}

		for (int i = start; i <= n; i++) {
			item.add(i);
			dfs(n, k, i + 1, item, res);
			item.remove(item.size() - 1);
		}
	}
}