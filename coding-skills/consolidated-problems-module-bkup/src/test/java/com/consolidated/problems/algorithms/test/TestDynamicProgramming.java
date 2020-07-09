package com.consolidated.problems.algorithms.test;

import com.consolidated.problems.algorithms.DynamicProgramming;

public class TestDynamicProgramming extends DynamicProgramming {

	public static void main(String[] args) {

		TestDynamicProgramming ob = new TestDynamicProgramming();

		// ob.testPattern1Problems();

		ob.testPattern2Problems();

		// ob.testPattern3Problems();

		// ob.testPattern4Problems();

		// ob.testPattern5Problems();

		// ob.testPattern6Problems();
	}

	public void testPattern1Problems() {
		System.out.println("Knapsack Problem: ");
		int[] val = { 1, 2, 3 }, weight = { 4, 5, 1 };
		int w = 4;
		System.out.println("Approach1: " + knapsack1(val, weight, w));
		System.out.println("Approach31: " + knapsack31(val, weight, w));
		System.out.println("Approach32: " + knapsack32(val, weight, w));

		System.out.println("Subset Sum: ");
		int[] arr = { 2, 3, 5, 6, 8, 10 };
		System.out.println("Approach31: " + isSubsetSum31(arr, 15));
		System.out.println("Approach32: " + isSubsetSum32(arr, 15));

		System.out.println("Count no of subset sum: ");
		System.out.println("Approach1: " + countSubsetSum1(arr, 15));
		System.out.println("Approach3: " + countSubsetSum3(arr, 15));

		int[] A = { 3, 4, 8, 5 };
		System.out.println("Backpack Problem: " + backPack1(1, A));
	}

	public void testPattern2Problems() {
		System.out.println("Unbounded Knapsack Problem: ");
		int[] val = { 3, 2, 1, 5 }, weight = { 4, 5, 2, 2 };
		int w = 4;
		System.out.println("Approach1: " + unboundedKnapsack1(val, weight, w));
		System.out.println("Approach2: " + unboundedKnapsack3(val, weight, w));

		int[] prices = { 4, 5, 8, 9, 10 };
		System.out.println("Cutting Rod: " + cutRod1(prices));
	}

	public void testPattern3Problems() {
		System.out.println("Count Palindromic Substrings: " + CountPalindromicSubstrings("abaab"));
	}

	public void testPattern4Problems() {

	}

	public void testPattern5Problems() {
		System.out.println("Longest Common Substring: ");
		String s1 = "abcdxyz", s2 = "xyzabcd";
		System.out.println("Approach1: " + longestCommonSubstring1(s1, s2));
		System.out.println("Approach3: " + longestCommonSubstring3(s1, s2));

		System.out.println("Longest Common Subsequence: ");
		String s3 = "AGGTAB", s4 = "GXTXAYB";
		System.out.println("Approach1: " + longestCommonSubSequence1(s3, s4));
		System.out.println("Approach2: " + longestCommonSubSequence2(s3, s4));
		System.out.println("\nApproach3: " + longestCommonSubSequence3(s3, s4));
	}

	public void testPattern6Problems() {
		int[][] dimensions = { { 4, 6, 7 }, { 1, 2, 3 }, { 4, 5, 6 }, { 10, 12, 32 } };
		System.out.println("Box Stacking - Max Height: " + boxStacking(dimensions));
	}
}
