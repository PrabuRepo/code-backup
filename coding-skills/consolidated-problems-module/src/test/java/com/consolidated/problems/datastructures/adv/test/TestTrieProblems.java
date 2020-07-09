package com.consolidated.problems.datastructures.adv.test;

import com.consolidated.problems.datastructures.adv.TrieProblems;

public class TestTrieProblems extends TrieProblems {

	public static void main(String[] args) {
		TestTrieProblems ob = new TestTrieProblems();
		ob.testProblems();
	}

	public void testProblems() {
		String arr[] = { "geeksforgeeks", "geeks", "geek", "geezer" };
		System.out.println("Longest Common Substring: " + longestCommonPrefix(arr));
	}
}
