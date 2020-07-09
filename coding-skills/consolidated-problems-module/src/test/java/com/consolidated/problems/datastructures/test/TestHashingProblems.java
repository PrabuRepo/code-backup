package com.consolidated.problems.datastructures.test;

import java.util.Arrays;

import com.consolidated.problems.datastructures.HashingProblems;

public class TestHashingProblems extends HashingProblems {
	public static void main(String[] args) {
		TestHashingProblems ob = new TestHashingProblems();

		// ob.testSlidingWindowStringProbs();

		ob.testSlidingWindowArrayProbs();
	}

	public void testSlidingWindowStringProbs() {
		System.out.println("Longest Substring Without Repeating Characters: " + lengthOfLongestSubstring1("abcabcbb"));
		System.out.println("Longest Repeating Character Replacement: " + characterReplacement1("AABABBA", 2));
	}

	public void testSlidingWindowArrayProbs() {
		int[] arr1 = { 1, 3, 4, 2, 6, 3 };
		System.out.println("sum of subarrays of size k: " + Arrays.toString(printSubarraySumRangeK(arr1, 3)));

		int[] arr2 = { 4, 1, 2, -5, 1, 2, 8 };
		System.out.println("Zero Sum Array: " + Arrays.toString(zeroSumSubArray(arr2)));

		int[] arr3 = { 1, 3, -1, -3, 5, 3, 6, 7 };
		System.out.println("Max Sliding Window: " + Arrays.toString(maxSlidingWindow(arr3, 3)));
	}
}
