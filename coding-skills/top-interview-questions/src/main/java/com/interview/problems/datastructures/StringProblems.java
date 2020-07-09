package com.interview.problems.datastructures;

import java.util.HashSet;

public class StringProblems {
	public static void main(String[] args) {
		StringProblems ob = new StringProblems();
		System.out.println("Remove Duplicates: "
				+ ob.removeDuplicate1(
						"tree traversal"));
	}

	/*
	 * Remove Duplicate Characters in String:
	 * Remove duplicate characters in a given string keeping only the first occurrences. For example, if the input is 
	 * ‘tree traversal’ the output will be ‘tre avsl’.
	 */
	// Using Hashing
	public String removeDuplicate1(String str) {
		int[] hash = new int[128];
		StringBuilder sb = new StringBuilder();
		for (char ch : str.toCharArray()) {
			if (hash[ch] == 0) {
				sb.append(ch);
				hash[ch]++;
			}
		}
		return sb.toString();
	}

	// Using Set
	public String removeDuplicate2(String str) {
		HashSet<Character> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for (char ch : str.toCharArray())
			if (set.add(ch)) sb.append(ch);
		return sb.toString();
	}

}
