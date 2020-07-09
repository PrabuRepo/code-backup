package com.consolidated.problems.datastructures.test;

import com.consolidated.problems.datastructures.ArrayProblems;

public class TestArrayProblems extends ArrayProblems {
	public static void main(String[] args) {
		TestArrayProblems ob = new TestArrayProblems();
		System.out.println("Missing Ranges: ");
		int[] nums = { 4, 5, 7, 50, 75 };
		ob.findMissingRanges(nums, 0, 99);

		int[] nums3 = { 1, 0, 1, 1, 0, 1, 1 };
		System.out.println("Max Consecutive Ones II: " + ob.findMaxConsecutiveOnes1(nums3));
		System.out.println("Max Consecutive Ones II: " + ob.findMaxConsecutiveOnes2(nums3));

		int[] num4 = { 7, 1, 5, 3, 6, 4 };
		System.out.println("Max Profit with cooldown: " + ob.maxProfit5(num4));

		System.out.println("Max Profit with K transactions: " + ob.maxProfit4(2, num4));
	}
}
