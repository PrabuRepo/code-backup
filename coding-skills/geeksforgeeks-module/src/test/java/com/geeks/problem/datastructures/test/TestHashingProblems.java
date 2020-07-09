package com.geeks.problem.datastructures.test;

import java.util.HashMap;
import java.util.Map;

import com.geeks.problem.datastructures.HashingProblems;

public class TestHashingProblems extends HashingProblems {

	public static void main(String[] args) {
		TestHashingProblems ob = new TestHashingProblems();

		// ob.testEasyProblems();

		// ob.testIntermediateProblems();

		// ob.testHardProblems();

		ob.testMiscProblems();
	}

	public void testEasyProblems() {
		int arr1[] = { 1, 2, 3, 4, 5, 6 };
		int arr2[] = { 1, 2, 4 };
		System.out.println("arr2 is subset of arr1(Brute Force Apporach) ? " + isSubsetArray1(arr1, arr2));
		System.out.println("arr2 is subset of arr1(Sort & Binary Search) ? " + isSubsetArray2(arr1, arr2));
		System.out.println("arr2 is subset of arr1(Sort & Merge) ? " + isSubsetArray3(arr1, arr2));
		System.out.println("arr2 is subset of arr1(USing Hashing) ? " + isSubsetArray4(arr1, arr2));
	}

	public void testIntermediateProblems() {
		int[] a1 = { 4, -1, -3, 4, -2, 2, -4 };
		System.out.println("No of zero sum arrays: " + zeroSumArrays1(a1));
		System.out.println("No of zero sum arrays: " + zeroSumArrays2(a1));
		System.out.println("Is zero sub array exists: " + subArrayExists(a1));

		int[] a = { 15, -2, 2, -8, 1, 7, 10, 23 };
		// int[] a = { 16, 3, 2, -1, -2, -1, -1, 5 };
		System.out.println("Largest subarray with 0 sum:");
		// maxLenZeroSubArray1(a);
		maxLenZeroSubArray2(a);

		int arr[] = { 36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42 };
		System.out.println("Longest Consecutive Subsequence");
		// longestConsecutiveSubSeq1(arr);
		longestConsecutiveSubSeq2(arr);

		String[] strings = { "deer", "door", "cake", "card" };
		Map<String, String> map = buildDictionary(strings);
		System.out.println("Unique Word Abbreviation: " + isUnique(map, "dear"));
		System.out.println("Unique Word Abbreviation: " + isUnique(map, "cart"));
		System.out.println("Unique Word Abbreviation: " + isUnique(map, "cane"));
		System.out.println("Unique Word Abbreviation: " + isUnique(map, "make"));

	}

	public void testHardProblems() {
		int[] nums = { 0 };
		System.out.println("Longest Consecutive Seq: " + longestConsecutive3(nums));
	}

	public void testMiscProblems() {
		int[] a = { 1, 1 };
		System.out.println("Array Pair Sum Divisibility Problem: " + isArrayPairDivisibleByK(a, 1));

		Map<String, Double> map = new HashMap<>();
		map.put("Latte", 3.15);
		map.put("Cappaccino", 4.25);
		map.put("Mocha", 5.0);
		map.put("Green Tea", 2.85);
		map.put("Cookie", 3.85);
		map.put("Brownie", 6.00);
		map.put("Banana", 2.00);
		map.put("Water", 3.00);
		map.put("Black Coffee", 1.00);
		// printItems(map, (double) 6.0);

		String[] words = { "abcd", "dcba", "sll", "s", "llsss" };
		System.out.println("Palindrome Pairs: " + palindromePairs(words));

		String[] strings = { "abc", "bcd", "efgh", "xyz", "az", "ba", "a", "z" };
		System.out.println("\nGroup Shifted Strings: " + groupStrings(strings));

		int[][] points = { { 1, 1 }, { -1, -1 } };
		System.out.println("Line Reflection: " + isReflected(points));
	}

}
