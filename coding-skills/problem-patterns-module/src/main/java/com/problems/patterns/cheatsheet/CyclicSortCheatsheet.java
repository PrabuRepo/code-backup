package com.problems.patterns.cheatsheet;

import java.util.ArrayList;
import java.util.List;

public class CyclicSortCheatsheet {
	// Cyclic Sort (easy) : https://www.geeksforgeeks.org/cycle-sort/
	// Find the Missing Number (easy)
	// Find all Missing Numbers (easy)

	// Find the Duplicate Number (easy)
	// Approach3: Floyd's Tortoise and Hare; Time Complexity:O(n); Space Complexity: O(1)
	public int findDuplicate(int[] nums) {
		int tortoise = 0;
		int hare = 0;
		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);
		int find = 0;
		while (find != tortoise) {
			tortoise = nums[tortoise];
			find = nums[find];
		}
		return find;
	}

	// Find all Duplicate Numbers (easy)
	/* Find All Duplicates/Find All Duplicates in an Array
	 * Given an array of integers, 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.
	 * Find all the elements that appear twice in this array.
	 * Approach1: Brute Force Approach - TC:O(n^2)
	 * Approach2: Using HashSet - TC:O(n), SC:O(n)     
	 * Approach3: Using Sorting - TC:O(nlogn)
	 * Approach4: - TC:O(n), SC:O(1)
	 */
	public List<Integer> findDuplicates(
			int[] nums) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int index = Math.abs(nums[i]) - 1;
			if (nums[index] < 0)
				result.add(Math.abs(nums[i]));
			else nums[index] = -nums[index];
		}
		for (int i = 0; i < nums.length; i++)
			nums[i] = -nums[i];
		return result;
	}

	// Find the Corrupt Pair (easy)
	// Find the Smallest Missing Positive Number (medium)
	// Find the First K Missing Positive Numbers (hard)
	// Insert into a Cyclic Sorted List - Additional Prob - Check this
}
