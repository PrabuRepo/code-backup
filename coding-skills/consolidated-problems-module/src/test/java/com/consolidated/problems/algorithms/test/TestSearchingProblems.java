package com.consolidated.problems.algorithms.test;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.consolidated.problems.algorithms.SearchingAlgorithms;

public class TestSearchingProblems extends SearchingAlgorithms {
	public static void main(String[] args) {
		long[] arr = { 1, 2, 3 };
		System.out.println("Maximum Subarray Sum: " + maximumSum(arr, 2));

		System.out.println("Shortest Palindrome: " + CountSpecialPalindrome(6, "aaabbc"));
	}

	// Function to count special Palindromic susbstring
	public static int CountSpecialPalindrome(int n, String str) {
		int[] sameChar = new int[n]; // it will store the count of continues same char
		int i = 0, result = 0;

		// traverse string character from left to right
		while (i < n) {
			int sameCharCount = 1, j = i + 1;
			// count similar character
			while (j < n && str.charAt(i) == str.charAt(j)) {
				sameCharCount++;
				j++;
			}
			// Case : 1- so total number of substring that we can generate are : K*(K + 1)/2, here K is sameCharCount
			result += (sameCharCount * (sameCharCount + 1) / 2);

			// store current same char count in sameChar[] array
			sameChar[i] = sameCharCount;
			i = j;
		}

		// Case 2: Count all odd length Special Palindromic substring
		for (int j = 1; j < n; j++) {
			// if current character is equal to previous one then we assign Previous same character count to current one
			if (str.charAt(j) == str.charAt(j - 1)) sameChar[j] = sameChar[j - 1];

			// case 2: odd length
			if (j > 0 && j < (n - 1) && (str.charAt(j - 1) == str.charAt(j + 1) && str.charAt(j) != str.charAt(j - 1)))
				result += Math.min(sameChar[j - 1], sameChar[j + 1]);
		}

		return result;
	}

	static long maximumSum(long[] arr, long m) {
		long maxSum = 0;
		int n = arr.length;

		TreeSet<Long> prefix = new TreeSet<Long>();
		long currentSum = 0;
		for (int i = 0; i < n; i++) {
			currentSum = (currentSum + arr[i] % m) % m;
			SortedSet<Long> set = prefix.tailSet(currentSum + 1);
			Iterator<Long> itr = set.iterator();
			if (itr.hasNext()) maxSum = Math.max(maxSum, (currentSum - itr.next() + m) % m);

			maxSum = Math.max(maxSum, currentSum);
			prefix.add(currentSum);
		}

		return maxSum;
	}
}
