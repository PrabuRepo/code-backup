package com.web.leetcode.datastructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayProblems {

	/**************** Easy Problems ************************/
	/*
	 * 1. Two Sum: Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	 * Time Complexity:(n^2)
	 */
	public static int[] twoSum1(int[] nums, int target) {
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			result[0] = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					result[1] = j;
					return result;
				}
			}
		}
		return result;
	}

	// Assuming that given nums array is sorted; Time Complexity: O(n)
	public static int[] twoSum2(int[] nums, int target) {
		int[] result = new int[2];
		int i = 0, j = nums.length - 1;
		while (i < j) {
			if (nums[i] + nums[j] == target) {
				result[0] = i;
				result[1] = j;
				break;
			} else if (nums[i] + nums[j] > target) {
				j--;
			} else {
				i++;
			}
		}
		return result;
	}

	// Elements are not sorted; Time complexity : O(n)
	public static int[] twoSum3(int[] nums, int target) {
		int[] result = new int[2];
		Arrays.fill(result, -1);
		Map<Integer, Integer> map = new HashMap<>(); // Value, Index
		map.put(target - nums[0], 0);
		for (int i = 1; i < nums.length; i++) {
			if (map.get(nums[i]) != null) {
				result[0] = map.get(nums[i]);
				result[1] = i;
				break;
			} else {
				map.put(target - nums[i], i);
			}
		}
		return result;
	}

	/********* Top Interview Questions ***************/
	public static int removeDuplicates(int[] nums) {
		int count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] != nums[i]) {
				nums[count] = nums[i];
				count++;
			}
		}

		for (int i = 0; i < count; i++)
			System.out.print(nums[i] + " ");

		return count;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 7, 11, 15 };
		int target = 13;
		System.out.println("Two Sum1: " + Arrays.toString(twoSum1(arr, target)));
		System.out.println("Two Sum2: " + Arrays.toString(twoSum2(arr, target)));
		System.out.println("Two Sum3: " + Arrays.toString(twoSum3(arr, target)));

		int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		System.out.println("\nRemove Duplicates in sorted array: " + removeDuplicates(nums));
	}
}
