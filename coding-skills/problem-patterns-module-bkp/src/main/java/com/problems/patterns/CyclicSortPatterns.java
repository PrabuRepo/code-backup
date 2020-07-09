package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.common.utilities.Utils;

/*
 * Cycle sort is a comparison sorting algorithm which forces array to be factored into the number of cycles where each of them can be rotated 
 * to produce a sorted array. It is theoretically optimal in the sense that it reduces the number of writes to the original array.
 * 
 * Steps:
 *    if the element is found to be at its correct position, simply leave it as it is.
 *    Otherwise, find the correct position by swapping curr element and curr index. This process continues until we got an element at the original position.
 *    
 * This class covers both cyclic sort & marker approach
 */
public class CyclicSortPatterns {

	public static void main(String[] args) {
		CyclicSortPatterns ob = new CyclicSortPatterns();
		int[] arr = { 2, 3, 1, 8, 2, 3, 5, 1 };
		System.out.println("Missing Numbers: ");
		ob.missingNumbers2(arr).stream().forEach(k -> System.out.print(k + " "));
		System.out.println("\nDuplicate Numbers: ");
		ob.findDuplicates4(arr).stream().forEach(k -> System.out.print(k + " "));
		System.out.println("\nDup Number: " + ob.findDuplicate61(new int[] { 1, 4, 4, 3, 2 }));
		int[] result = ob.findCorruptPair(new int[] { 1, 4, 4, 3, 2 });
		System.out.println("\nCorrupt Pair: " + Arrays.toString(result));

	}

	// 1.Cyclic Sort:
	// Array range from 1 to n
	public void cycleSort1(int[] nums) {
		int n = nums.length;
		int i = 0;
		while (i < n) {
			int val = nums[i] - 1;
			if (nums[val] != nums[i]) {
				// if the current number is not at the correct index, swap it
				Utils.swap(nums, val, i);
			} else {
				i++;
			}
		}
	}

	// Similar to above with small modification
	public void cycleSort2(int[] nums) {
		int n = nums.length, val = 0;
		for (int i = 0; i < n; i++) {
			val = nums[i];
			while (val > 0 && val <= n && nums[val - 1] != val) {
				Utils.swap(nums, val - 1, i);
				val = nums[i];
			}
		}
		System.out.println("Sorted Array: " + Arrays.toString(nums));
	}

	// 2. Find the Missing Number
	/* Find Missing Element/Missing Number:
	 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
	 *  1.Math Operation
	 *  2.Bit Manipulations(XOR)
	 *  3.Binary Search -O(nlogn); if it is already sorted-O(logn)
	 *  4.Using Cyclic Sort
	 */
	public int missingNumber4(int[] nums) {
		int i = 0, n = nums.length;
		// rearrange the array using cyclic sort.
		while (i < n) {
			int val = nums[i];
			if (val < n && nums[val] != nums[i])
				Utils.swap(nums, val, i);
			else
				i++;
		}

		for (i = 0; i < nums.length; i++)
			if (nums[i] != i)
				return i;

		return n;
	}

	// 3.Find all Missing Numbers
	/* Given an array containing n numbers taken from the range 1 to n. It can have duplicates. Find all those missing numbers.
	 * 1. Counting Sort Approach
	 * 2. Cyclic Sort Approach
	 * 3. Marker Approach
	 */

	// Cyclic Sort Approach
	public List<Integer> missingNumbers2(int[] arr) {
		List<Integer> missed = new ArrayList<>();
		// rearrange the array using cyclic sort.
		int i = 0, n = arr.length;
		while (i < n) {
			int val = arr[i] - 1;
			if (arr[i] != arr[val]) {
				Utils.swap(arr, i, val);
			} else {
				i++;
			}
		}

		for (i = 0; i < n; i++) {
			if (i + 1 != arr[i])
				missed.add(i + 1);
		}

		return missed;
	}

	// Marker Approach
	public void missingNumbers3(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int value = Math.abs(array[i]);
			if (array[value - 1] > 0) {
				array[value - 1] = -array[value - 1];
			}
		}
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > 0) {
				System.out.println(i + 1);
			}
		}
	}

	// Find the Duplicate Number
	// 4.Find the Duplicate Number:
	/* Given an array containing n+1 numbers taken from the range 1 to n. It has only one duplicate number but can be repeated over time. Find that one.
	 * Solution:
	 * 1.BruteForce Approach: T:O(n^2)
	 * 2.Sort & Linear Search: T:O(nlogn)
	 * 3.HashSet: T:O(n), S:O(n)
	 * 4.Binary Search Alg: T:O(nlogn)
	 * Below are applicable only elements from 1 to n+1
	 * 5.Sum of elements: T:O(n); 
	 * 6.Cyclic Sort Approach: T:O(n)
	 * 7.Marker Approach:O(n)
	 * 8.Slow/Fast Floyd's Tortoise and Hare or cycle finding Alg: T:O(n)
	 */

	// Cyclic Sort Approach:
	public int findDuplicate6(int[] nums) {
		int i = 0, n = nums.length;
		// rearrange the array using cyclic sort.
		while (i < n) {
			int val = nums[i] - 1;
			if (nums[val] != nums[i])
				Utils.swap(nums, val, i);
			else
				i++;
		}

		for (i = 0; i < nums.length; i++)
			if (nums[i] != i + 1)
				return i;

		return 0;
	}

	// Cyclic Sort Approach: With single loop
	public int findDuplicate61(int[] nums) {
		int i = 0, n = nums.length;
		// rearrange the array using cyclic sort.
		while (i < n) {
			if (i + 1 != nums[i]) {
				int val = nums[i] - 1;
				if (nums[val] != nums[i])
					Utils.swap(nums, val, i);
				else
					return nums[i];
			} else {
				i++;
			}
		}
		return 0;
	}

	// Marker Approach
	public int findDuplicate7(int[] nums) {
		int val = 0, dupNum = 0;
		for (int i = 0; i < nums.length; i++) {
			val = Math.abs(nums[i]) - 1;
			if (nums[val] < 0) {
				dupNum = Math.abs(nums[i]);
				break;
			} else {
				nums[val] = -nums[val];
			}
		}
		// Reset the original elements
		for (int i = 0; i < nums.length; i++)
			if (nums[i] < 0)
				nums[i] = -nums[i];
		return dupNum;
	}

	// 5.Find all Duplicate Numbers (easy)
	/* Solution:
	 * Given an array of integers, 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.
	 * Find all the elements that appear twice in this array.
	 * Approach1: Brute Force Approach - TC:O(n^2)
	 * Approach2: Using HashSet - TC:O(n), SC:O(n)     
	 * Approach3: Using Sorting - TC:O(nlogn)
	 * Approach4: Cyclic Sort Approach- TC:O(n), SC:O(1)
	 * Approach5: Marker Approach- TC:O(n), SC:O(1)
	 */
	// Approach4: Cyclic Sort Approach
	public List<Integer> findDuplicates4(int[] nums) {
		List<Integer> duplicates = new ArrayList<Integer>();

		int i = 0, n = nums.length;
		// rearrange the array using cyclic sort.
		while (i < n) {
			int val = nums[i] - 1;
			if (nums[val] != nums[i])
				Utils.swap(nums, val, i);
			else
				i++;
		}

		for (i = 0; i < n; i++) {
			if (i + 1 != nums[i])
				duplicates.add(nums[i]);
		}

		return duplicates;
	}

	// Approach5: Marker Approach
	public List<Integer> findDuplicates5(int[] nums) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if (nums[val] < 0)
				result.add(Math.abs(nums[i]));
			else
				nums[val] = -nums[val];
		}
		// Reset the original elements
		for (int i = 0; i < nums.length; i++)
			nums[i] = -nums[i];
		return result;
	}

	// 6.Find the Corrupt Pair
	/*
	 * Given an array containing n+1 numbers taken from the range 1 to n. One of the numbers got duplicated which also resulted in one number going
	 * missing. Find these numbers.
	 * Approach1: Cyclic Sort:
	 * Approach2: Marker Approach
	 */
	public int[] findCorruptPair(int[] nums) {
		// rearrange the array using cyclic sort.
		int i = 0, n = nums.length;
		while (i < n) {
			int val = nums[i] - 1;
			if (nums[val] != nums[i])
				Utils.swap(nums, val, i);
			else
				i++;
		}

		for (i = 0; i < nums.length; i++)
			if (nums[i] != i + 1)
				return new int[] { nums[i], i + 1 };

		return new int[] { 0, 0 };
	}

	// 7.Find the Smallest Missing Positive Number or First Missing Positive
	/*
	 * 1. Cyclic Sort Approach
	 * 2. Marker Approach
	 */
	// Using Cyclic Sort: T:O(n);
	public int firstMissingPositive11(int[] nums) {
		int i = 0, val = 0;
		// Convert the array to "1 to n" (example below) form using Cyclic Sort
		// and map +ve numbers to respective indices around negative numbers
		while (i < nums.length) {
			val = nums[i];
			if (val > 0 // Skip negative numbers
					&& val <= nums.length // Critical, so we do not want to go out of bound
					&& nums[val - 1] != val) { // Cyclic Sort criteria
				Utils.swap(nums, val - 1, i);
			} else {
				i++;
			}
		}

		// Example: For an input array [-3, 1, 5, 4, 2 ], the cyclic sorted
		// array after the above jumps will look like [1, 2, -3, 4, 5].
		// Looking at this we know the element after 2, i.e 3 is missing.
		for (i = 0; i < nums.length; i++) {
			if (i + 1 != nums[i]) {
				return i + 1;
			}
		}

		return i + 1; // This will tc of all negative testcases like empty array etc
	}

	// Both are same approach
	public int firstMissingPositive12(int[] nums) {
		int n = nums.length, val = 0;
		for (int i = 0; i < n; i++) {
			val = nums[i];
			while (val > 0 && val <= n && nums[val - 1] != val) {
				Utils.swap(nums, val - 1, i);
				val = nums[i];
			}
		}
		for (int i = 0; i < n; i++)
			if (nums[i] != i + 1)
				return i + 1;
		return n + 1;
	}

	// Marker Approach
	public int firstMissingPositive2(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; i++)
			if (nums[i] <= 0 || nums[i] > n)
				nums[i] = n + 1;
		for (int i = 0; i < n; i++) {
			int val = Math.abs(nums[i]);
			if (val > n)
				continue;
			val -= 1;
			if (nums[val] > 0)
				nums[val] = -nums[val];
		}

		for (int i = 0; i < n; i++)
			if (nums[i] >= 0)
				return i + 1;

		return n + 1;
	}

	// 8.Find the First K Missing Positive Numbers (hard)

	// 9.Insert into a Cyclic Sorted List - Additional Prob - Check this
}
