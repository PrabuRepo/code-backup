package com.problems.patterns.cheatsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TwoPointerProblems {
	/*Trapping rain water:
	 * Height of water = Minimum(tallest left hand bar & tallest right hand bar) - height of current bar 
	 */
	// Approach1 : Time Complexity: O(n) and takes Auxiliary Space: O(n)
	public int trappingRainWater1(int[] a) {
		int n = a.length;
		int[] tallestLeftArr = new int[n];
		int[] tallestRightArr = new int[n];

		// Find the tallest bar from the left side
		tallestLeftArr[0] = a[0];
		for (int i = 1; i < n; i++)
			tallestLeftArr[i] = Math.max(tallestLeftArr[i - 1], a[i]);

		// Find the tallest bar from the right side
		tallestRightArr[n - 1] = a[n - 1];
		for (int i = n - 2; i >= 0; i--)
			tallestRightArr[i] = Math.max(tallestRightArr[i + 1], a[i]);

		// Find minimum from tallestLeftArr & tallestRightAr and then subtract with current bar
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += (Math.min(tallestRightArr[i], tallestRightArr[i]) - a[i]);

		return sum;
	}

	// Its similar to approach1 without any additional space
	// Time Complexity: O(n) without Auxiliary Space
	public int trappingRainWater2(int[] a) {
		int sum = 0;
		int low = 0, high = a.length - 1;
		int tallestLeft = 0, tallestRight = 0;
		while (low < high) {
			// Find the tallest bar from the left side & right side and execute corresponding block
			if (a[low] < a[high]) {
				// If current index is less than tallest left bar then add in the sum, otherwise keep updating the
				// tallestLeft
				if (a[low] > tallestLeft) tallestLeft = a[low];
				else sum += tallestLeft - a[low];

				low++;
			} else {
				// If current index is less than tallest right bar then add in the sum, otherwise keep updating the
				// tallestRight
				if (a[high] > tallestRight) tallestRight = a[high]; // Update the max right
				else sum += tallestRight - a[high];

				high--;
			}
		}
		return sum;
	}

	/* Container With Most Water: 
	 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical
	 * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
	 * with x-axis forms a container, such that the container contains the most water.
	 */
	public int maxArea(int[] height) {
		if (height.length <= 1) return 0;

		int l = 0, h = height.length - 1, max = 0, minHeight = 0;
		while (l < h) {
			minHeight = Math.min(height[l], height[h]);
			max = Math.max(max, minHeight * (h - l));
			if (height[l] < height[h]) l++;
			else h--;
		}
		return max;
	}

	/*Product of Array Except Self:
	 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the
	 *  product of all the elements of nums except nums[i].
	 *  	Example: Input:  [1,2,3,4] Output: [24,12,8,6]
	 */
	/*  Solution without using Divison
	Time Complexity: O(n)
	Space Complexity: O(n)
	Auxiliary Space: O(n)*/
	public int[] productExceptSelf1(int[] nums) {
		int n = nums.length;
		int[] left = new int[n];
		int[] right = new int[n];
		int[] prod = new int[n];

		// Multiply from left side
		left[0] = 1;
		for (int i = 1; i < n; i++)
			left[i] = left[i - 1] * nums[i - 1];

		// Multiply from right side
		right[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--)
			right[i] = right[i + 1] * nums[i + 1];

		// Combine both
		for (int i = 0; i < n; i++)
			prod[i] = left[i] * right[i];

		return prod;
	}

	// Improved version
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length, temp = 1;
		int[] prod = new int[n];

		// Multiply from left side
		for (int i = 0; i < n; i++) {
			prod[i] = temp;
			temp *= nums[i];
		}

		// Multiply from right side
		temp = 1;
		for (int i = n - 1; i >= 0; i--) {
			prod[i] *= temp;
			temp *= nums[i];
		}

		return prod;
	}

	// Two Sum: Given an array of integers, return indices of the two numbers such that they add up to a specific
	// target.
	// 1. Brute force approach: Time Complexity:(n^2)
	public int[] twoSum1(int[] nums, int target) {
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

	// 2.Two Ptr Approach: Time Complexity: O(n) for sorted array; o(nlogn) for unsorted array;
	// This works only if array is sorted
	public int[] twoSum2(int[] nums, int target) {
		int[] result = new int[2];
		int l = 0, h = nums.length - 1, currSum = 0;
		while (l < h) {
			currSum = nums[l] + nums[h];
			if (currSum == target) {
				result[0] = l;
				result[1] = h;
				break;
			} else if (currSum > target) {
				h--;
			} else {
				l++;
			}
		}
		return result;
	}

	// 3.Two Ptr Approach: Time Complexity: O(n) for sorted array; o(nlogn) for unsorted array;
	public int[] twoSum3(int[] arr, int k) {
		Arrays.sort(arr);
		int[] result = new int[2];
		for (int i = 0; i < arr.length; i++) {
			int siblingIndex = Arrays.binarySearch(arr, k - arr[i]);
			if (siblingIndex >= 0) { // Found it!
				/* If this points at us, then the pair exists only if there is another copy of the element. Look ahead of us and behind us. */
				if (siblingIndex != i || (i > 0 && arr[i - 1] == arr[i])
						|| (i < arr.length - 1 && arr[i + 1] == arr[i])) {
					result[0] = i;
					result[1] = siblingIndex;
					return result;
				}
			}
		}
		return result;
	}

	// Approach4: Using Hashing: Time complexity : O(n)for sorted & unsorted
	public int[] twoSum4(int[] nums, int target) {
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

	/*Pairs: You will be given an array of integers and a target value. Determine the number of pairs of array elements 
	 * that have a difference equal to a target value.
	 * For example, given an array of [1, 2, 3, 4] and a target value of 1, we have three values meeting the condition.
	 */
	// Approach1: BruteForce Approach: Time Complexity: O(n^2)
	public int pairs1(int k, int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (Math.abs(arr[i] - arr[j]) == k) count++;
			}
		}

		return count;
	}

	// Approach2: Two Ptr Approach: Time Complexity: O(nlogn)
	public int pairs2(int k, int[] arr) {
		int count = 0;
		Arrays.sort(arr);

		int l = 0, r = 1;
		while (r < arr.length) {
			int diff = arr[r] - arr[l];
			if (diff == k) {
				count++;
				r++;
			} else if (diff < k) {
				r++;
			} else {
				l++;
			}
		}

		return count;
	}

	// Approach3: Two Ptr Approach: Time Complexity: O(n)
	public int pairs3(int k, int[] arr) {
		int count = 0;
		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i < arr.length; i++)
			set.add(arr[i]);

		for (int i = 0; i < arr.length; i++)
			if (set.contains(arr[i] + k)) count++;

		return count;
	}

	// Find a triplet that sum to a given value
	// 1.Brute Force Approach: Time Complexity-O(n^3)
	public boolean tripletSum1(int[] a, int sum) {
		int n = a.length;
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					if (a[i] + a[j] + a[k] == sum) {
						System.out.println("Triplet is: " + a[i] + ", " + a[j] + ", " + a[k]);
						return true;
					}
				}
			}
		}
		return false;
	}

	// 2. Using sorting and two ptr approach - Time Complexity: O(nlogn) + O(n^2) => O(n^2)
	public boolean tripletSum2(int[] a, int sum) {
		int n = a.length;

		// 1.Sort the given array
		Arrays.sort(a);

		// 2. Take element one by one from 0th the index
		for (int i = 0; i < n - 2; i++) {
			// 3. Find remaining two elements using two ptr alg
			if (sumPresent1(a, i + 1, n - 1, a[i], sum - a[i])) return true;
		}

		return false;
	}

	// 3. Using Hash Based approach - Time Complexity: O(n^2)
	public boolean tripletSum3(int[] a, int sum) {
		int n = a.length;

		// 1. Take element one by one from 0th the index
		for (int i = 0; i < n - 2; i++) {
			// 3. Find remaining two elements using hash DS
			if (sumPresent2(a, i + 1, n - 1, a[i], sum - a[i])) return true;
		}

		return false;
	}

	// Using 2 ptr approach
	public boolean sumPresent1(int[] nums, int l, int h, int firstValue, int target) {
		// target -= firstValue; // Remove first value from the target, two find the remaining two values

		while (l < h) {
			if (nums[l] + nums[h] == target) {
				System.out.println("The Triplet is: " + firstValue + ", " + nums[l] + ", " + nums[h]);
				return true;
			} else if (nums[l] + nums[h] > target) {
				h--;
			} else {
				l++;
			}
		}

		return false;
	}

	public boolean sumPresent2(int[] nums, int l, int h, int firstValue, int target) {
		// target -= firstValue; // Remove first value from the target, two find the remaining two values
		HashSet<Integer> set = new HashSet<>();

		for (int i = l; i <= h; i++) {
			if (set.contains(target - nums[i])) {
				System.out.println("The Triplet is: " + firstValue + ", " + (target - nums[i]) + ", " + nums[i]);
				return true;
			} else {
				set.add(nums[i]);
			}
		}
		return false;
	}

	/* 3Sum Closest:
	 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest
	 * to target. Return the sum of the three integers. You may assume that each input would have exactly one 
	 */
	public int threeSumClosest(int[] nums, int target) {
		int min = Integer.MAX_VALUE;
		int result = 0;

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				int diff = Math.abs(sum - target);

				if (diff == 0) return sum;

				if (diff < min) {
					min = diff;
					result = sum;
				}
				if (sum <= target) {
					j++;
				} else {
					k--;
				}
			}
		}

		return result;
	}

	/*
	 * Pythagorean Triplet in an array: Given an array of integers, write a function that returns true if there is a 
	 * triplet (a, b, c) that satisfies a2 + b2 = c2.
	 */
	// 1.Brute Force Approach: Time Complexity-O(n^3)
	public boolean isPythagoreanTriplet1(int[] arr) {
		int n = arr.length;
		int a, b, c;
		for (int i = 0; i < n; i++) {
			a = arr[i] * arr[i];
			for (int j = i + 1; j < n; j++) {
				b = arr[j] * arr[j];
				for (int k = j + 1; k < n; k++) {
					c = arr[k] * arr[k];
					if (a + b == c || a + c == b || a == b + c) {
						System.out.println(arr[i] + " " + arr[j] + " " + arr[k]);
						return true;
					}
				}
			}
		}

		return false;
	}

	// Using Two Ptr:
	public boolean isPythagoreanTriplet2(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n; i++)
			arr[i] = arr[i] * arr[i];

		Arrays.sort(arr);

		for (int i = n - 1; i > 1; i--) {
			if (sumPresent1(arr, 0, i - 1, arr[i], arr[i])) return true;
		}
		return false;
	}

	// Using sort & hash DS
	public boolean isPythagoreanTriplet3(int[] arr) {
		int n = arr.length;
		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i < n; i++)
			set.add(arr[i] * arr[i]);

		int a = 0, b = 0;
		for (int i = 0; i < n - 1; i++) {
			a = arr[i] * arr[i];

			for (int j = i + 1; j < n; j++) {
				b = arr[j] * arr[j];
				if (set.contains(a + b)) {
					System.out.println(arr[i] + " " + arr[j] + " " + (int) Math.sqrt(a + b));
					return true;
				}
			}
		}
		return false;
	}

	// 4Sum I:
	// Brute Force Approach
	public List<List<Integer>> fourSum(int[] nums, int target) {
		int n = nums.length;
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> eachList = new ArrayList<>();
		for (int i = 0; i < n - 3; i++) {
			target -= nums[i];
			for (int j = i + 1; j < n - 2; j++) {
				target -= nums[j];
				for (int k = j + 1; k < n; k++) {
					target -= nums[k];
					for (int l = 0; l < n; l++) {
						target -= nums[k];
						if (target == 0 && !result.contains(eachList)) result.add(new ArrayList<>(eachList));
					}
				}
			}
		}
		return result;
	}

	// Approach2: Two Ptr Approach: Time Complexity-O(n^3)
	public List<List<Integer>> fourSum2(int[] nums, int target) {
		int n = nums.length;
		Arrays.sort(nums); // Sort the array to remove the duplicate order
		List<List<Integer>> result = new ArrayList<>();

		for (int i = 0; i < n - 3; i++) {
			for (int j = i + 1; j < n - 2; j++) {
				int l = j + 1;
				int h = n - 1;
				while (l < h) {
					int sum = nums[i] + nums[j] + nums[l] + nums[h];
					if (sum == target) {
						List<Integer> eachList = Arrays.asList(nums[i], nums[j], nums[l], nums[h]);
						if (!result.contains(eachList)) result.add(eachList);
						l++;
						h--;
					} else if (sum < target) {
						l++;
					} else {
						h--;
					}
				}
			}
		}
		return result;
	}

	/*
	 * 4SumII: 
	 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that
	 *  A[i] + B[j] + C[k] + D[l] is zero.
	 */
	// Approach1: BruteForce Approach: Time Complexity-O(n^4)
	public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
		int n = A.length, count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					for (int l = 0; l < n; l++)
						if (0 == A[i] + B[j] + C[k] + D[l]) count++;
		return count;
	}

	// Approach2: Efficient Approach: Time Complexity-O(n^2), Space Complexity-O(n^2)
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int n = A.length, count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				int sum = A[i] + B[j];
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				int sum = C[i] + D[j];
				count += map.getOrDefault(-sum, 0);
			}

		return count;
	}

}
