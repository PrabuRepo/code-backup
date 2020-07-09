package com.consolidated.problems.cheatsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.common.utilities.Utils;

public class StringProblems {

	/************************* Type1: Using 2 Pointers & Simple Problems *************/
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

	// Reverse Vowels of a String
	public String reverseVowels(String s) {
		char[] arr = s.toCharArray();
		int l = 0, h = arr.length - 1;
		while (l < h) {
			while (l < h && !isVowel(arr[l]))
				l++;
			while (h > l && !isVowel(arr[h]))
				h--;
			if (l < h && arr[l] != arr[h])
				Utils.swap(arr, l, h);
			l++;
			h--;
		}
		return new String(arr);
	}

	private boolean isVowel(char ch) {
		return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I'
				|| ch == 'O' || ch == 'U');
	}

	// Reverse the words in a String:
	/* Approach1: Using String API
	 * I'm splitting on the regex for one-or-more whitespace, this takes care of multiple spaces/tabs/newlines/etc in
	 * the input. Since the input could have leading/trailing whitespace, which would result in empty matches, I first
	 * trim the input string.
	 */
	public String reverseWords1(String s) {
		String[] words = s.trim().split("\\s+");
		if (words.length == 0)
			return "";
		int n = words.length;
		StringBuilder sb = new StringBuilder();
		sb.append(words[n - 1].trim());
		for (int i = n - 2; i >= 0; i--)
			sb.append(" ").append(words[i].trim());
		return sb.toString();
	}

	// Approach2: Without using String API
	public String reverseWords2(String s) {
		if (s == null)
			return null;
		char[] arr = s.toCharArray();
		reverse(arr, 0, arr.length - 1);
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != ' ') {
				if (index != 0)
					arr[index++] = ' ';
				int j = i;
				while (j < arr.length && arr[j] != ' ')
					arr[index++] = arr[j++];
				reverse(arr, index - (j - i), index - 1);
				i = j;
			}
		}
		return new String(arr).substring(0, index);
	}

	public void reverse(char[] str, int i, int j) {
		while (i < j) {
			char tem = str[i];
			str[i++] = str[j];
			str[j--] = tem;
		}
	}

	// Reverse Words in a String III:
	// Approach1: Using String API
	public String reverseWords31(String s) {
		String[] str = s.split(" ");
		for (int i = 0; i < str.length; i++)
			str[i] = new StringBuilder(str[i]).reverse().toString();
		StringBuilder result = new StringBuilder();
		for (String st : str)
			result.append(st + " ");
		return result.toString().trim();
	}

	// Approach2: Without using String API
	public String reverseWords32(String s) {
		char[] ca = s.toCharArray();
		for (int i = 0; i < ca.length; i++) {
			if (ca[i] != ' ') {
				int j = i;
				while (j + 1 < ca.length && ca[j + 1] != ' ') {
					j++;
				}
				reverse(ca, i, j);
				i = j;
			}
		}
		return new String(ca);
	}

	/*
	 * Valid Palindrome:Given a string, determine if it is a palindrome, considering only alphanumeric characters and
	 * ignoring cases.
	 */
	// Valid Palindrome I:
	public boolean isPalindrome1(String s) {
		if (s == null || s == "")
			return true;
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (int i = 0; i < s.length(); i++) {
			index = (int) s.charAt(i);
			if ((index >= 97 && index <= 122) || (index >= 65 && index <= 90)) {
				if (index <= 90)
					index += 32;
				sb.append((char) index);
			} else if (Character.isDigit(s.charAt(i))) {
				sb.append(s.charAt(i));
			}
		}
		char[] ch = sb.toString().toCharArray();
		return isPalindrome(ch);
	}

	public boolean isPalindrome(char[] ch) {
		int i = 0, j = ch.length - 1;
		while (i < j) {
			if (ch[i++] != ch[j--])
				return false;
		}
		return true;
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
				if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail))
					return false;
				l++;
				h--;
			}
		}
		return true;
	}

	/*
	 * Valid Palindrome II:Given a non-empty string s, you may delete at most one character. Judge whether you 
	 * can make it a palindrome.
	 */
	// Valid Palindrome II
	public boolean validPalindrome(String s) {
		int l = 0, r = s.length() - 1;
		while (l < r && s.charAt(l) == s.charAt(r)) {
			l++;
			r--;
		}
		if (l >= r)
			return true;
		if (isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1))
			return true;
		return false;
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

	/************************* Type2: String Comparison *************/
	// Is Subsequence
	public boolean isSubsequence1(String s, String t) {
		int nextIndex = 0, j = 0, count = 0;
		for (int i = 0; i < s.length(); i++) {
			for (j = nextIndex; j < t.length(); j++) {
				if (s.charAt(i) == t.charAt(j)) {
					count++;
					break;
				}
			}
			nextIndex = j + 1;
		}
		return s.length() == count ? true : false;
	}

	public boolean isSubsequence2(String s, String t) {
		int index = -1;
		for (char c : s.toCharArray()) {
			index = t.indexOf(c, index + 1);
			if (index == -1)
				return false;
		}
		return true;
	}

	// Longest Common Prefix: Write a function to find the longest common prefix string amongst an array of strings.
	// Approach1:Brute force approach
	public String longestCommonPrefix(String[] strs) {
		int arrSize = strs.length, i, j;
		char ch;
		if (strs.length == 0)
			return "";
		int len = minStringSize(strs);
		for (i = 0; i < len; i++) {
			ch = strs[0].charAt(i);
			for (j = 1; j < arrSize; j++) {
				if (ch != strs[j].charAt(i))
					break;
			}
			if (j != arrSize)
				break;
		}
		return strs[0].substring(0, i);
	}

	private int minStringSize(String[] strs) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < strs.length; i++)
			min = Integer.min(min, strs[i].length());
		return min;
	}

	// Approach3: Sort the array first, and then you can simply compare the first and last elements in the sorted array.
	public String longestCommonPrefix2(String[] strs) {
		if (strs == null)
			return null;
		if (strs.length == 0)
			return "";
		Arrays.sort(strs);
		char[] first = strs[0].toCharArray();
		char[] last = strs[strs.length - 1].toCharArray();
		int i = 0, len = Math.min(first.length, last.length);
		while (i < len && first[i] == last[i])
			i++;
		return strs[0].substring(0, i);
	}

	// Approach3: Simple & Brute force approach
	public String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0 && prefix.length() > 0)
				prefix = prefix.substring(0, prefix.length() - 1);
		return prefix;
	}

	// Rotate String:
	public boolean rotateString1(String A, String B) {
		if (A.length() != B.length())
			return false;
		return (A + A).indexOf(B) == -1 ? false : true;
	}

	// One line solution using Java util
	public boolean rotateString2(String A, String B) {
		return (A.length() == B.length() && (A + A).contains(B));
	}

	public boolean rotateString(String A, String B) {
		char start = A.charAt(0);
		for (int i = 0; i < B.length(); i++) {
			int j = i;
			while (j < B.length() && B.charAt(j) != start)
				j++;
			if (j >= B.length())
				return false;
			for (int n = 0; n < B.length(); n++) {
				if (A.charAt(n) != B.charAt((n + j) % B.length()))
					break;
				if (n == B.length() - 1)
					return true;
			}
			i = j;
		}
		return false;
	}

	// Check if string is rotated by two places
	public static int stringRotatedByTwoPlaces(String s1, String s2) {
		if (s1.length() == s2.length() && ((s1 + s1).indexOf(s2) == 2 || (s1 + s1).indexOf(s2) == s1.length() - 2))
			return 1;
		else
			return 0;
	}

	/************************* Type3: String Modification/Rearrangements *************/
	// Recursively remove all adjacent duplicates
	public String removeRecursiveDuplicates(String s) {
		int n = s.length();
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

	// One Edit Distance/One Away:
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
				if (foundDifference)
					return false;
				foundDifference = true;
			}
		}
		return true;
	}

	private boolean oneEditInsert(String s1, String s2) {
		int i1 = 0, i2 = 0;
		while (i1 < s1.length() && i2 < s2.length()) {
			if (s1.charAt(i1) != s2.charAt(i2)) {
				if (i1 != i2)
					return false;
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
		if (Math.abs(m - n) > 1)
			return false;
		String shorter = m < n ? str1 : str2;
		String longer = m > n ? str1 : str2;
		int i1 = 0, i2 = 0;
		boolean foundDiff = false;
		while (i1 < shorter.length() && i2 < longer.length()) {
			if (shorter.charAt(i1) != longer.charAt(i2)) {
				if (foundDiff)
					return false;
				foundDiff = true;
				if (m == n)
					i1++;
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
		if (Math.abs(m - n) > 1)
			return false;
		for (int i = 0; i < Math.min(m, n); i++) {
			if (str1.charAt(i) == str2.charAt(i))
				continue;
			if (m == n)
				return str1.substring(i + 1).equals(str2.substring(i + 1));
			else if (m > n)
				return str1.substring(i + 1).equals(str2.substring(i));
			else
				return str1.substring(i).equals(str2.substring(i + 1));
		}
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
		if (str.length() == 0)
			return str;
		int spacesCount = 0;
		for (int i = 0; i < trueLength; i++)
			if (str.charAt(i) == ' ')
				spacesCount++;
		int len = (trueLength + 2 * spacesCount) - 1;
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

	// License Key Formatting:
	public static String licenseKeyFormatting(String s, int K) {
		if (s.length() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--)
			if (s.charAt(i) != '-')
				sb.append(sb.length() % (K + 1) == K ? '-' : "").append(s.charAt(i));
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

	/* ZigZag Conversion:
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may 
	 * want to display this pattern in a fixed font for better legibility)
	 * Example 1: Input: s = "PAYPALISHIRING", numRows = 3; Output: "PAHNAPLSIIGYIR"
	 * Example 2: Input: s = "PAYPALISHIRING", numRows = 4; Output: "PINALSIGYAHRPI"
	 */
	public String convert(String s, int numRows) {
		if (numRows <= 1)
			return s;
		StringBuilder sb = new StringBuilder();
		int n = s.length(), step1, step2, pos;
		for (int i = 0; i < numRows; i++) {
			step1 = 2 * (numRows - i - 1);
			step2 = 2 * i;
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

	/************************* Type4: String Math *************/

	/* Compare Version Numbers:
	 * Compare two version numbers version1 and version2. 
	 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
	 */
	public int compareVersion(String version1, String version2) {
		if (version1.length() == 0 && version2.length() == 0)
			return 0;
		String[] ver1 = version1.split("\\.");
		String[] ver2 = version2.split("\\.");
		for (int i = 0; i < Math.max(ver1.length, ver2.length); i++) {
			int v1 = i < ver1.length ? Integer.valueOf(ver1[i]) : 0;
			int v2 = i < ver2.length ? Integer.valueOf(ver2[i]) : 0;
			if (v1 > v2)
				return 1;
			if (v1 < v2)
				return -1;
		}
		return 0;
	}

	/*
	 * Non-negative Integers without Consecutive Ones or Binary Strings with no Consecutive Ones:
	 */
	public int findIntegers(int num) {
		StringBuilder sb = new StringBuilder(Integer.toBinaryString(num));
		int len = sb.length();
		int[] a = new int[len], b = new int[len];
		a[0] = 1;
		b[0] = 1;
		for (int i = 1; i < len; i++) {
			a[i] = a[i - 1] + b[i - 1];
			b[i] = a[i - 1];
		}
		int count = a[len - 1] + b[len - 1];
		sb.reverse();
		for (int i = len - 2; i >= 0; i--) {
			if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1')
				break;
			if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0')
				count -= b[i];
		}
		return count;
	}

	/*
	 * Count number of binary strings without consecutive 1’s:
	 *  It is really a straight up fibonacci series with values 1,2,3,5,8,13....
	 * 	Look how we assign a[i] value of a[i-1] + b[i-1] and then b[i] becomes a[i]
	 */
	public int count1(int n) {
		int a = 1, b = 1;
		for (int i = 1; i < n; i++) {
			int tmp = a;
			a = a + b;
			b = tmp;
		}
		return a + b;
	}

	public int count2(int n) {
		int a[] = new int[n];
		int b[] = new int[n];
		a[0] = 1;
		b[0] = 1;
		for (int i = 1; i < n; i++) {
			a[i] = a[i - 1] + b[i - 1];
			b[i] = a[i - 1];
		}
		return a[n - 1] + b[n - 1];
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
			output.append(String.valueOf(str.length()) + "#");
			output.append(str);
		}
		return output.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode1(String s) {
		List<String> res = new ArrayList<>();
		int i = 0;
		while (i < s.length()) {
			int strIdx = s.indexOf('#', i);
			int len = Integer.parseInt(s.substring(i, strIdx));
			res.add(s.substring(strIdx + 1, strIdx + len + 1));
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
			while (i < s.length() && s.charAt(i) != '#') {
				len = len * 10 + Character.getNumericValue(s.charAt(i));
				i++;
			}
			String str = s.substring(i + 1, i + len + 1);
			result.add(str);
			i = i + len + 1;
		}
		return result;
	}

	/*
	 * Count and Say:
	 * The count-and-say sequence is the sequence of integers with the first five terms as following:
	 * 	1.     1
	 * 	2.     11
	 * 	3.     21
	 * 	4.     1211
	 * 	5.     111221
	 * 	1 is read off as "one 1" or 11.
	 * 	11 is read off as "two 1s" or 21.
	 * 	21 is read off as "one 2, then one 1" or 1211.
	 */
	public String countAndSay(int n) {
		if (n == 0)
			return "";
		String result = "1";
		int i = 1, count = 1;
		while (i < n) {
			count = 1;
			StringBuilder sb = new StringBuilder();
			for (int j = 1; j < result.length(); j++) {
				if (result.charAt(j) == result.charAt(j - 1)) {
					count++;
				} else {
					sb.append(count);
					sb.append(result.charAt(j - 1));
					count = 1;
				}
			}
			sb.append(count);
			sb.append(result.charAt(result.length() - 1));
			result = sb.toString();
			i++;
		}
		return result;
	}

	/*
	 * String Compression:
	 * Input: ["a","a","b","b","c","c","c"]
	 * 	Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
	 */
	public int compress(char[] chars) {
		if (chars.length <= 0)
			return 0;
		int count = 1, n = chars.length, l = 0, r = 1;
		while (r <= n) {
			if (r < n && chars[r - 1] == chars[r]) {
				count++;
			} else {
				chars[l++] = chars[r - 1];
				if (count > 1) {
					for (char c : Integer.toString(count).toCharArray())
						chars[l++] = c;
				}
				count = 1;
			}
			r++;
		}
		return l;
	}

	public String strCompression(String str) {
		if (str == null || str.length() <= 1)
			return str;
		int count = 1, n = str.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (i < n && str.charAt(i - 1) == str.charAt(i)) {
				count++;
			} else {
				sb.append(str.charAt(i - 1)).append(count);
				count = 1;
			}
		}
		return sb.length() < str.length() ? sb.toString() : str;
	}

	/************************* Type5: Hashing(Using a Length-256 Integer Array) *************/
	/*
	 * 1.Is Unique:
	 * Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional 
	 * data structures? ASCII(128 chars) Vs Unicode Vs extended ASCII(256 chars)?
	 */
	// Approach1: Brute force-> O(n^2)
	// Approach2: Sort and check in linear time -> O(nlogn)
	// Approach3: Using Hashing concepts -> Time Complexity: O(n), but takes additional space
	public boolean isUniqueChars1(String str) {
		if (str.length() > 128)
			return false;
		boolean[] chars = new boolean[128];
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i);
			if (chars[index])
				return false;
			chars[index] = true;
		}
		return true;
	}

	// Approach4: Bit manipulation-> O(n) (Assumption: string only uses the lowercase letters a through z)
	public boolean isUniqueChars2(String str) {
		int testBit = 0;
		for (int i = 0; i < str.length(); i++) {
			int charValue = str.charAt(i) - 'a';
			if ((testBit & (1 << charValue)) > 0)
				return false;
			testBit |= (1 << charValue);
		}
		return true;
	}

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

	// App2: Using Sorting; Time Complexity-O(nlogn)
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

	/* Group Anagrams
	 * Given an array of strings, group anagrams together.
	 * 	Example: Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
	Output:
	[
	["ate","eat","tea"],
	["nat","tan"],
	["bat"]
	]
	 */
	// Using Hash Map: Time Complexity: O(n*m); n - no of string, m - each String length
	// If sort the String, then time complexity will be, O(n*mlogm)
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			String asc = ascOrder(str);
			List<String> list = null;
			if (map.containsKey(asc)) {
				list = map.get(asc);
			} else {
				list = new ArrayList<>();
			}
			list.add(str);
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
	}

	/*
	 * Print a single integer denoting the number of characters you must delete to make the two strings anagrams of each other.
	 * 	Sample Input: cde, 	abc; Output: 4
	 */
	public int makeAnagram(String a, String b) {
		int count = 0;
		int[] hash = new int[26];
		for (int i = 0; i < a.length(); i++)
			hash[a.charAt(i) - 'a']++;
		for (int i = 0; i < b.length(); i++)
			hash[b.charAt(i) - 'a']--;
		for (int i = 0; i < 26; i++) {
			if (hash[i] != 0)
				count += Math.abs(hash[i]);
		}
		return count;
	}

	/*
	 * 2.Check Permutation: 
	 *     Given two strings, write a method to decide if one is a permutation of the other.
	 */
	// Approach1: Sort the strings and compare
	public boolean checkPermutation1(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;
		return sort(str1).equals(sort(str2));
	}

	private String sort(String str) {
		char[] ch = str.toCharArray();
		Arrays.sort(ch);
		return new String(ch);
	}

	// Approach2: using hashing
	public boolean checkPermutation2(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;
		int[] charCount = new int[128];
		for (int i = 0; i < str1.length(); i++)
			charCount[str1.charAt(i)]++;
		for (int i = 0; i < str2.length(); i++)
			if (--charCount[str2.charAt(i)] < 0)
				return false;
		return true;
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
	public boolean isPalindrome(String str) {
		int[] charCount = new int[26];
		for (int i = 0; i < str.length(); i++) {
			char ch = upperToLowerCase(str.charAt(i));
			if (ch >= 'a' && ch <= 'z')
				charCount[ch - 'a']++;
		}
		int oddCount = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = upperToLowerCase(str.charAt(i));
			if (ch >= 'a' && ch <= 'z') {
				if (charCount[ch - 'a'] % 2 == 1)
					oddCount++;
			}
		}
		return (oddCount > 1) ? false : true;
	}

	private char upperToLowerCase(char ch) {
		if (ch >= 'A' && ch <= 'Z')
			return (char) (ch + 25 + 7);
		return ch;
	}

	// Sherlock and the Valid String:

	/* Two Strings:
	 * Complete the function twoStrings in the editor below. It should return a string, either YES or NO based on whether 
	 * the strings share a common substring.
	 * Input: hello, world; Output: 1;  The substrings s1 and s2 are common to both strings.
	 * Input: hi, world; Output: 0; s1 and  s2 share no common substrings.
	 */
	static String twoStrings(String s1, String s2) {
		int[] hash = new int[26];
		for (int i = 0; i < s1.length(); i++)
			hash[s1.charAt(i) - 'a']++;
		for (int i = 0; i < s2.length(); i++)
			if (hash[s2.charAt(i) - 'a'] != 0)
				return "YES";
		return "NO";
	}

	// Ransome Note: Array of strings
	public void ransomNote1(String[] magazine, String[] note) {
		Hashtable<String, Integer> map = new Hashtable<String, Integer>();
		for (String s : magazine)
			map.put(s, map.getOrDefault(s, 0) + 1);
		for (String s : note) {
			if (!map.containsKey(s)) {
				System.out.println("No");
				return;
			}
			int counter = map.get(s) - 1;
			if (counter == 0)
				map.remove(s);
			else
				map.put(s, counter);
		}
		System.out.println("Yes");
	}

	// Ransome Note: Check the chars in the string
	public boolean ransomNote2(String ransomNote, String magazine) {
		int[] hash = new int[26];
		for (int i = 0; i < magazine.length(); i++)
			hash[magazine.charAt(i) - 'a']++;
		for (int i = 0; i < ransomNote.length(); i++) {
			if (hash[ransomNote.charAt(i) - 'a'] == 0)
				return false;
			hash[ransomNote.charAt(i) - 'a']--;
		}
		return true;
	}

	public boolean isIsomorphic(String s, String t) {
		int[] hash1 = new int[128];
		int[] hash2 = new int[128];
		for (int i = 0; i < s.length(); i++) {
			if (hash1[s.charAt(i)] != hash2[t.charAt(i)])
				return false;
			hash1[s.charAt(i)] = i + 1;
			hash2[t.charAt(i)] = i + 1;
		}
		return true;
	}

	/************************************ Type6: Substring Problems **************************/
	/*
	 * Special Palindrome Again:
	 * A string is said to be a special palindromic string if either of two conditions is met:
	 *  All of the characters are the same, e.g. aaa.
	 * 	All characters except the middle one are the same, e.g. aadaa.
	 * A special palindromic substring is any substring of a string which meets one of those criteria. Given a string, 
	 * determine how many special palindromic substrings can be formed from it.
	 * Input: 5, asasd; Outpur: 7
	 * Input: 7, abcbaba; Outpur: 10
	 */
	static long substrCount(int n, String str) {
		int[] sameChar = new int[n];
		int i = 0, result = 0;
		while (i < n) {
			int sameCharCount = 1, j = i + 1;
			while (j < n && str.charAt(i) == str.charAt(j)) {
				sameCharCount++;
				j++;
			}
			result += (sameCharCount * (sameCharCount + 1) / 2);
			sameChar[i] = sameCharCount;
			i = j;
		}
		for (int j = 1; j < n; j++) {
			if (str.charAt(j) == str.charAt(j - 1))
				sameChar[j] = sameChar[j - 1];
			if (j > 0 && j < (n - 1) && (str.charAt(j - 1) == str.charAt(j + 1) && str.charAt(j) != str.charAt(j - 1)))
				result += Math.min(sameChar[j - 1], sameChar[j + 1]);
		}
		return result;
	}

	/*** Adhoc: Clean up below ****/

	/*
	 * Word Pattern:Given a pattern and a string str, find if str follows the same pattern. Here follow means a full
	 * match, such that there is a bijection between a letter in pattern and a non-empty word in str. 
	 * Example 1: Input: pattern = "abba", str = "dog cat cat dog" 
	 * 			  Output: true
	 */
	public boolean wordPattern(String pattern, String str) {
		if (str == null || str.length() == 0 || pattern.length() == 0)
			return false;

		Map<Character, String> map = new HashMap<>();
		String[] arr = str.split(" ");
		if (pattern.length() != arr.length)
			return false;

		for (int i = 0; i < pattern.length(); i++) {
			char pat = pattern.charAt(i);
			if (map.containsKey(pat) && !map.get(pat).equals(arr[i]))
				return false;
			if (!map.containsKey(pat) && map.containsValue(arr[i]))
				return false;
			map.put(pat, arr[i]);
		}

		return true;
	}

	public String addStrings1(String num1, String num2) {
		String result = "";
		int i1 = num1.length() - 1, i2 = num2.length() - 1;
		int a = 0, b = 0, sum = 0, carry = 0;
		while (i1 >= 0 || i2 >= 0) {
			a = i1 >= 0 ? Integer.valueOf(num1.charAt(i1)) - '0' : 0;
			b = i2 >= 0 ? Integer.valueOf(num2.charAt(i2)) - '0' : 0;

			sum = a + b + carry;
			carry = sum / 10;
			sum = sum > 9 ? sum % 10 : sum;
			result = sum + result;

			i1--;
			i2--;
		}
		if (carry > 0)
			result = carry + result;

		return result;
	}

	public String addStrings(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--) {
			int x = i < 0 ? 0 : num1.charAt(i) - '0';
			int y = j < 0 ? 0 : num2.charAt(j) - '0';
			sb.append((x + y + carry) % 10);
			carry = (x + y + carry) / 10;
		}
		return sb.reverse().toString();
	}

	/*
	 * Shortest Word Distance I: Given a list of words and two words word1 and word2, return the shortest distance
	 * between these two words in the list. 
	 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. 
	 * Given word1 = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding", return 1.
	 */
	public int shortestDistanceI(String[] words, String word1, String word2) {
		int min = Integer.MAX_VALUE, index1 = -1, index2 = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1))
				index1 = i;

			if (words[i].equals(word2))
				index2 = i;

			if (index1 != -1 && index2 != -1)
				min = Math.min(min, Math.abs(index1 - index2));
		}
		return min;
	}

	Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

	/* Shortest Word Distance II: 
	 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and
	 * your method will be called repeatedly many times with different parameters. How would you optimize it? Design a
	 * class which receives a list of words in the constructor, and implements a method that takes two words word1 and
	 * word2 and return the shortest distance between these two words in the list. 
	 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. 
	 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = “makes”, word2 = “coding”, return 1. 
	 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
	 */

	/*Solution: Initialize the words:
	 * We can pre scan the list to record the indexes for each word, and store the sorted index list into a HashTable.
	 * The key is the word, and the value is the sorted index list. In the query process, the problem becomes to find
	 * the minimum difference between the values in the two sorted index lists.
	 */
	public void initialize(String[] words) {
		for (int i = 0; i < words.length; i++) {
			String curWord = words[i];
			if (!map.containsKey(curWord)) {
				map.put(curWord, new ArrayList<>());
			}
			map.get(curWord).add(i);
		}
	}

	// Here apply merging sorted lists to get the minimum.
	public int shortestDistanceII(String word1, String word2) {
		List<Integer> indexList1 = map.get(word1);
		List<Integer> indexList2 = map.get(word2);
		int minDistance = Integer.MAX_VALUE;
		int i1 = 0, i2 = 0;

		while (i1 < indexList1.size() && i2 < indexList2.size()) {
			int wordIndex1 = indexList1.get(i1);
			int wordIndex2 = indexList2.get(i2);

			minDistance = Math.min(minDistance, Math.abs(wordIndex1 - wordIndex2));
			if (wordIndex1 < wordIndex2)
				i1++;
			else
				i2++;
		}

		return minDistance;
	}

	/*
	 * Shortest Word Distance III: This is a follow up of Shortest Word Distance. The only difference is now word1 could
	 * be the same as word2. Given a list of words and two words word1 and word2, return the shortest distance between
	 * these two words in the list. word1 and word2 may be the same and they represent two individual words in the list.
	 *
	 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. 
	 * Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = “makes”, word2 = “makes”, return 3. 
	 * Note: You may assume word1 and word2 are both in the list.
	 */
	int shortestWordDistanceIII(String[] words, String word1, String word2) {
		int i1 = -1, i2 = -1, minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; ++i) {
			String curWord = words[i];
			if (word1.equals(word2)) {
				if (curWord.equals(word1)) {
					// let i1, i2 point to the two largest indexes
					if (i2 < i1)
						i2 = i;
					else
						i1 = i;
				}
			} else {
				if (curWord.equals(word1))
					i1 = i;
				if (curWord.equals(word2))
					i2 = i;
			}
			if (i1 >= 0 && i2 >= 0)
				minDistance = Math.min(minDistance, Math.abs(i1 - i2));
		}
		return minDistance;
	}

}