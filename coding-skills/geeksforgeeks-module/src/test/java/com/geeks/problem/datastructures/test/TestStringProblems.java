package com.geeks.problem.datastructures.test;

import java.util.ArrayList;
import java.util.List;

import com.common.utilities.Utils;
import com.geeks.problem.datastructures.StringProblems;

public class TestStringProblems extends StringProblems {

	public static void main(String[] args) {
		TestStringProblems ob = new TestStringProblems();

		// ob.testBinaryStringProblems();

		// ob.testAnagramProblems();

		// ob.testSubsequenceProblems();

		// ob.testSubStringProblems();

		ob.testPalindromeProblems();

		// ob.testSubSeqAndStringComparisons();

		// ob.testMiscProblems();

		// ob.testCodingInterviewProblems();
	}

	public void testBasicProblems() {
		System.out.println("ASCII chars: ");
		Utils.printASCIIchars(ASCII_CHAR_SIZE);

		System.out.println("Extended ASCII chars: ");
		Utils.printASCIIchars(EXTENDED_ASCII_CHAR_SIZE);

	}

	public void testCharCountingBasedProblems() {
		String str;
		System.out.println("Max no of char in String: " + returnMaxOccurringChar3("bbbdDDdDDB"));
		System.out.println("Second max no of char in String: " + secondMostFrequentChar1("bbbddDDDDB"));
		// str = "One two three\n four\tfive ";
		str = "This is a sample string";
		// str = "THIS IS A TEST TEXT TEST";
		System.out.println("No of words in a string:" + countWordsInString2(str));
	}

	public void testAnagramProblems() {
		System.out.println("Anagram(Approach 1): " + checkAnagram1("Silent", "Listen"));
		System.out.println("Anagram(Approach 2): " + checkAnagram2("silent", "listen"));
		System.out.println("Group Anagrams: ");
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		groupAnagrams(strs);
	}

	public void testPalindromeProblems() {
		System.out.println("Check Palindrome: " + isPalindrome("abbcbba"));

		System.out.println(
				"Check if a given string is a rotation of a palindrome: " + isRotatedStringPalindrome1("aaaad"));
		System.out.println(
				"Check if a given string is a rotation of a palindrome: " + isRotatedStringPalindrome2("aaaad"));
		System.out.println(
				"Check if a given string is a rotation of a palindrome: " + isRotatedStringPalindrome3("aaaad"));

		System.out.println("Program to print all palindromes in a given range: ");
		printPalindromesGivenRange(10, 115);

		System.out.println("\nCheck if characters of a given string can be rearranged to form a palindrome: "
				+ canFormPalindrome("geeksogeeks"));

		System.out.println("Shortest Palindrome: " + shortestPalindrome("ananab"));

		System.out.println("Minimum number of Appends needed to make a string palindrome: "
				+ noOfCharAppendsToFormPalindrome("aabb"));

		System.out.println("Minimum insertions to form a palindrome: " + minCharNeedToFormPalindrome1("aaaabbbb"));
		System.out.println("Minimum insertions to form a palindrome(DP): " + minCharNeedToFormPalindrome2("abcde"));

		System.out.println(
				"Longest Palindromic Substring(Naive Approach): " + longestPalindromicSubString1("forgeeksskeegfor"));

		System.out.println("Longest Palindromic Substring(DP): " + longestPalindromicSubString2("forgeeksskeegfor"));
	}

	public void testBinaryStringProblems() {
		System.out.println("Is single flip makes all same bits:" + isSingleFlipMakesAllBitSame2("10001"));
		String a = "1101", b = "100";
		System.out.println("Add Binary String: " + addBinary(a, b));
		System.out.println("Generate all binary strings from given pattern(Recursive Approach): ");
		generateBinaryStrings1("1??0?101");
		System.out.println("Generate all binary strings from given pattern(Using Queue): ");
		generateBinaryStrings2("???");
		System.out.println(
				"Count number of binary strings without consecutive 1’s: " + binaryStringWithoutConsecutiveOnes1(15));
		System.out.println(
				"Count number of binary strings without consecutive 1’s: " + binaryStringWithoutConsecutiveOnes1(15));
		System.out.println("Non-negative Integers without Consecutive Ones: " + findIntegers(8));
		System.out.println("Binary representation of next number: " + nextNumber("10011"));
	}

	public void testSubsequenceProblems() {
		System.out.println("Print all subsequences of a string :");
		printSubSeq("abc");

		System.out.println("Print all Subsequences of String which Start with Vowel and End with Consonant: ");
		subSequenceStartWithVowel("xabcef");

		System.out.println("Given two strings, find if first string is a subsequence of second(Recursive): "
				+ isSubsequence1("gksrek", "geeksforgeeks"));
		System.out.println("Given two strings, find if first string is a subsequence of second(Iterative): "
				+ isSubsequence1("gksrek", "geeksforgeeks"));

		System.out.println("Count Distinct Subsequences: " + countDistinctSeq("aabc"));

		System.out.println("No of times a string occurs as a subsequence in given string: "
				+ countStringInSubSeq("GeeksforGeeks", "Gks"));
		System.out.println("No of times a string occurs as a subsequence in given string(DP): "
				+ countStringInSubSeq2("GeeksforGeeks", "Gks"));

		System.out.println("Longest Common Subsequence(Recursive): " + longestCommonSubSequence1("GXTXAYB", "AGGTAB"));
		System.out.println("\nLongest Common Subsequence(DP): " + longestCommonSubSequence2("AGGTAB", "GXTXAYB"));

		System.out.println(
				"Longest common subsequence with permutations allowed:" + lcsWithAllPermutations("geeks", "cake"));

		System.out.println("Shortest Common Super sequence: " + shortestCommonSuperSequence1("AGGTAB", "GXTXAYB")); // Understand
																													// this

		System.out.println("Returns count of subsequences of the form of a^i b^j c^k:" + noOfSeqOfabc("abbc"));

		System.out.println("No of subsequences divisible by n: " + noOfSeqDivisibleByN("676", 6, 0, 0));
		System.out.println("No of subsequences divisible by n: " + noOfSeqDivisibleByNusingDP("676", 6));
	}

	public void testSubStringProblems() {
		System.out.println("Frequency of a substring in a string: " + naivePatternSearching("aaaa", "aa"));
		System.out.println("Find substrings that contain all vowels: ");
		subStringsAllVowels("aeoibsddaeiouudb");
		System.out.println(
				"Length of the longest substring with equal 1s and 0s: " + longestSubstringWithEqual0And1("101001000"));
		System.out.println("Length of longest palindromic substring: ");
		longestPalindromicSubstring("forgeeksskeegfor");
		System.out.println("Length of longest common substring: "
				+ longestCommonSubstring("OldSite:GeeksforGeeks.org", "NewSite:GeeksQuiz.com"));
		System.out.println("Find the longest substring with 2 unique characters in a given string: "
				+ longestSubstring2Distinct1("abcbbbbcccbdddadacb"));
		System.out.println("Find the longest substring with 2 unique characters in a given string: "
				+ longestSubstring2Distinct2("abcbbbbcccbdddadacb"));
		System.out.println("Find the longest substring with k unique characters in a given string: "
				+ longestSubstringKDistinct1("abcadcacacaca", 3));
		System.out.println("Find the longest substring with k unique characters in a given string: "
				+ longestSubstringKDistinct2("abcadcacacaca", 3));
		System.out.println(
				"Longest Substring Without Repeating Characters: " + longestSubstringWithoutRepeatingChar1("au"));
		longestCommonPrefix1("geeks", "dgeek");
		String str = "this is a test string";
		String pat = "tist";
		System.out.println("Minimum Window Substring:" + minWindowSubstring1(str, pat));
		System.out.println("Minimum Window Substring:" + minWindowSubstring2(str, pat));
	}

	public void testPatternSearchingProblems() {
		String str = "AABAACAADAABAAABAA";
		String pattern = "AABA";
		System.out.println("Naive Pattern Searching:");
		naivePatternSearching(str, pattern);
	}

	public void testSubSeqAndStringComparisons() {
		System.out.println("Sub Arrays: ");
		int[] arrA = { 1, 2, 3, 4 };
		printSubArrays(arrA);

		System.out.println("Sub Strings: ");
		String str1 = "abcd";
		printSubStrings1(str1);
		System.out.println();
		printSubStrings2(str1);

		System.out.println("\nBinary Sequence(Recursive): ");
		printBinarySequence1(4);
		System.out.println("Binary Sequence(Iterative): ");
		printBinarySequence2(4);

		int[] arr = { 1, 2, 3, 4 };
		System.out.println("SubSequence of a given array(Recursive): ");
		subSequenceOfArray1(arr);
		System.out.println("SubSequence of a given array(Iterative): ");
		subSequenceOfArray2(arr);

		String str2 = "abcd";
		System.out.println("SubSequence of a given string(Recursive): ");
		subSequenceOfString1(str2);
		System.out.println("SubSequence of a given string(Iterative): ");
		subSequenceOfString2(str2);

		String str3 = "abc";
		System.out.println("Permutation of the string: ");
		permutationOfString(str3);

		System.out.println("Permutation of the array: ");
		int[] num = { 1, 2, 3 };
		permutationOfArray(num);

		System.out.println("Combinations: ");
		combination(4, 2);
	}

	public void testMiscProblems() {
		System.out.println("A Program to check if strings are rotations of each other or not: "
				+ areRotatedStringSame1("ABACD", "CDFBA"));
		System.out.println("A Program to check if strings are rotations of each other or not: "
				+ areRotatedStringSame2("ABACD", "CDFBA"));

		String s = "We promptly judged antique ivory buckles for the next prize";
		System.out.println("Check the given string is Panagram? " + pangrams(s));

		System.out.println("String to Integer: " + atoi("-2147483649"));

		List<String> strs = new ArrayList<>();
		strs.add("abc");
		strs.add("ghij");
		strs.add("tuvwx");
		String encodedString = encodeString(strs);
		System.out.println("Encode the String: " + encodedString);
		System.out.println("Decode the String: " + decodeString(encodedString));

		System.out.println("Recursively remove all adjacent duplicates: " + removeRecursiveDuplicates("mississippi"));

		System.out.println("Reverse the words: " + reverseWords("a b"));

		String[] strArr = { "flower", "flow", "flight" };
		System.out.println("Longest Common Prefix: " + longestCommonPrefix1(strArr));

	}

	public void testCodingInterviewProblems() {
		int width = 3, height = 5;
		System.out.println("Display char chart from given String: ");
		displayCharChart(mockData1(), width, height, "PRABU");

		System.out.println(
				"Display String from  given char chart: " + getAlphabetChar(mockData1(), mockDataC(), width, height));
	}

	public ArrayList<String> mockData1() {
		ArrayList<String> ascii = new ArrayList<String>();
		/*for (int i = 0; i < height; i++) {
			ascii.add(in.nextLine());
		}*/

		// ABCDEFGHIJKLMNOPQRSTUVWXYZ?
		ascii.add(
				" #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ###  ");
		ascii.add(
				"# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   #  ");
		ascii.add(
				"### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ##  ");
		ascii.add(
				"# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #        ");
		ascii.add(
				"# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #  ");
		return ascii;

	}

	public ArrayList<String> mockDataA() {
		ArrayList<String> str = new ArrayList<String>();
		str.add(" # ");
		str.add("# #");
		str.add("###");
		str.add("# #");
		str.add("# #");
		return str;
	}

	public ArrayList<String> mockDataC() {
		ArrayList<String> str = new ArrayList<String>();
		str.add(" ##");
		str.add("#  ");
		str.add("#  ");
		str.add("#  ");
		str.add(" ##");
		return str;
	}

	public ArrayList<String> mockDataZ() {
		ArrayList<String> str = new ArrayList<String>();
		str.add("### ");
		str.add("  #");
		str.add(" # ");
		str.add("#  ");
		str.add("###");
		return str;
	}
}
