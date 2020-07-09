package com.consolidated.problems.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import com.common.utilities.Utils;

public class ArrayProblems {
	/**************************** Type1: Array Manipulations ***********************************/
	// Reverse array in groups
	public void reverseKGroup1(int[] arr, int k) {
		int l = 0, r = 0, n = arr.length;
		while (l < n) {
			r = Math.min(l + k - 1, n - 1);
			Utils.reverse(arr, l, r);
			l += k;
		}
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public void reverseKGroup2(int[] arr, int k) {
		StringBuffer sb = new StringBuffer();
		int n = arr.length;
		for (int i = 0; i < n; i += k) {
			int left = i;
			int right = Math.min(i + k - 1, n - 1);
			// reverse the sub-array [left, right]
			while (left <= right) {
				sb.append(arr[right] + " ");
				right -= 1;
			}
		}
		System.out.println(sb);
	}

	// Rotate Array/Arrays: Left Rotation
	/*Right Rotation:
	1. Rotate one by one - Time Complexity: O(nk)
	2. Using Juggling Algorithm - Time Complexity: O(n)
	3. Using Reversal Algorithm - Time Complexity: O(n)
	*/
	public void rotate(int[] nums, int k) {
		int n = nums.length;
		if (n > 0 && k >= n)
			k = k % n;
		if (n > 1 && k > 0) {
			Utils.reverse(nums, 0, n - 1);
			Utils.reverse(nums, 0, k - 1);
			Utils.reverse(nums, k, n - 1);
		}
	}

	// Remove Duplicates from Sorted Array I, II
	public int removeDuplicatesI(int[] nums) {
		int i = 0;
		for (int n : nums)
			if (i == 0 || n > nums[i - 1])
				nums[i++] = n;
		return i;
	}

	// Remove Duplicates from Sorted Array II
	public int removeDuplicatesII(int[] nums) {
		int i = 0;
		for (int n : nums)
			if (i < 2 || n > nums[i - 2])
				nums[i++] = n;
		return i;
	}

	// Remove Element
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length == 0)
			return 0;
		int i = 0;
		for (int n : nums) {
			if (n != val)
				nums[i++] = n;
		}
		return i;
	}

	// Move Zeroes
	// Approach-1
	public void moveZeroes(int[] nums) {
		int i = 0;
		for (int num : nums)
			if (num != 0) {
				nums[i++] = num;
			}
		while (i < nums.length)
			nums[i++] = 0;
	}

	// Approach-2: Single Traversal - 2 pointers
	public void moveZeroes2(int[] nums) {
		if (nums.length <= 1)
			return;
		int l = 0, r = 0;
		while (r < nums.length) {
			if (nums[r] != 0) {
				Utils.swap(nums, l, r);
				l++;
			}
			r++;
		}
	}

	// Contains Duplicate I, II, III
	// Contains Duplicate I
	/*
	1.Brute Force Approach - O(n^2)
	2.Sorting Approach - O(nlogn)
	3.Hashing Approach - O(n); But it takes O(n) extra space
	4.COunting Sort - O(n); But it takes extra space based on element present in the array
	*/
	// Using Set
	public boolean containsDuplicateI1(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i]))
				return true;
			set.add(nums[i]);
		}
		return false;
	}

	// Using Sort
	public boolean containsDuplicateI2(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] == nums[i])
				return true;
		}
		return false;
	}

	// Contains Duplicate II
	public boolean containsNearbyDuplicate(int[] nums,
			int k) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (i > k)
				set.remove(nums[i - k - 1]);
			if (!set.add(nums[i]))
				return true;
		}
		return false;
	}

	// Intersection of Two Arrays I, II - Bin Search
	/*
	 * Given two arrays, write a function to compute their intersection.
	 * Example 1: Input: nums1 = [1,2,2,1], nums2 = [2,2]
	 * 			  Output: [2]
	 * Example 2: Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
	 * 			  Output: [9,4]
	 */
	// Approach1: Use two hash sets - Time complexity: O(n)
	public int[] intersection1(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Set<Integer> intersect = new HashSet<>();
		for (int i = 0; i < nums1.length; i++) {
			set.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			if (set.contains(nums2[i])) {
				intersect.add(nums2[i]);
			}
		}
		int[] result = new int[intersect.size()];
		int i = 0;
		for (Integer num : intersect) {
			result[i++] = num;
		}
		return result;
	}

	// Approach2: Using Merging - Time complexity:O(nlogn)
	public int[] intersection2(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i1 = 0, i2 = 0;
		Set<Integer> set = new HashSet<>();
		while (i1 < nums1.length && i2 < nums2.length) {
			if (nums1[i1] == nums2[i2]) {
				set.add(nums1[i1]);
				i1++;
				i2++;
			} else if (nums1[i1] < nums2[i2]) {
				i1++;
			} else {
				i2++;
			}
		}
		// Convert to array
		int[] result = new int[set.size()];
		int i = 0;
		for (Integer val : set)
			result[i++] = val;

		return result;
	}

	// Approach3: Binary search - Time complexity: O(nlogn)
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Arrays.sort(nums2);
		for (Integer num : nums1) {
			if (binarySearch(nums2, num)) {
				set.add(num);
			}
		}
		int i = 0;
		int[] result = new int[set.size()];
		for (Integer num : set) {
			result[i++] = num;
		}
		return result;
	}

	public boolean binarySearch(int[] nums, int target) {
		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] == target) {
				return true;
			}
			if (nums[mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return false;
	}

	/* Intersection of Two Arrays II:
	1. Brute force :O(n^2)
	2. Using sorting: O(nlogn+mlogm)
	3. Hashing : O(n), but takes additional space
	*/
	// Approach2:
	public int[] intersectII1(int[] nums1, int[] nums2) {
		ArrayList<Integer> list = new ArrayList<>();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0, j = 0, index = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				// result[index++] = nums1[i];
				list.add(nums1[i]);
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		int[] result = new int[list.size()];
		for (i = 0; i < list.size(); i++)
			result[i] = list.get(i);
		return result;
	}

	// Approach3:
	// This solution works for data: [1] [1,1]
	public int[] intersectII2(int[] nums1, int[] nums2) {
		ArrayList<Integer> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums1.length; i++)
			set.add(nums1[i]);
		for (int i = 0; i < nums2.length; i++) {
			if (set.contains(nums2[i]))
				list.add(nums2[i]);
		}
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			result[i] = list.get(i);
		return result;
	}

	// New Year Chaos
	public void minimumBribes(int[] a) {
		int count = 0;
		boolean flag = true;
		for (int r = a.length - 1; r >= 0; r--) {
			if ((a[r] - (r + 1)) > 2) {
				System.out.println("Too chaotic");
				flag = false;
				break;
			} else {
				int l = a[r] - 2 > 0 ? a[r] - 2 : 0;
				while (l < r)
					if (a[l++] > a[r])
						count++;
			}
		}
		if (flag)
			System.out.println(count);
	}

	// Chocolate Distribution Problem
	public int minDiff(int[] arr, int m) {
		Arrays.sort(arr);
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i <= arr.length - m; i++)
			minDiff = Math.min(minDiff,
					arr[i + m - 1] - arr[i]);
		return minDiff;
	}

	// Triple sum - No of distinct triplets(p < q > r)
	public long triplets(int[] a, int[] b, int[] c) {
		Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);
		int p = 0, r = 0;
		long pCount = 0, rCount = 0, total = 0;
		for (int q = 0; q < b.length; q++) {
			while (p < a.length && a[p] <= b[q]) {
				if (p == 0 || a[p - 1] != a[p])
					pCount++;
				p++;
			}
			while (r < c.length && c[r] <= b[q]) {
				if (r == 0 || c[r - 1] != c[r])
					rCount++;
				r++;
			}
			if (q == 0 || b[q - 1] != b[q])
				total += pCount * rCount;
		}
		return total;
	}

	// Shuffle an Array
	int[]  original;
	int[]  shuffled;
	Random random;

	public void init(int[] nums) {
		original = nums;
		shuffled = Arrays.copyOf(nums, nums.length);
		random = new Random();
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		shuffled = Arrays.copyOf(original, original.length);
		return shuffled;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int n = shuffled.length;
		for (int i = 0; i < n; i++) {
			int k = random.nextInt(n);
			// Swap the number based on random index 'k'; Util Random generates number from 0 to n-1;
			Utils.swap(shuffled, i, k);
		}
		return shuffled;
	}

	/**************************** Type2: Number Problems ***********************************/
	/* Element with left side smaller and right side greater
	 * Given an unsorted array of size N. Find the first element in array such that all of its left elements are smaller and 
	 * all right elements to it are greater than it.
	 * Note: Left and right side elements can be equal to required element. And extreme elements cannot be required element.
	 */
	// TC: O(n) & SC: O(2n)
	public static int findElement1(int[] arr) {
		int n = arr.length;
		int[] leftMax = new int[n];
		int[] rightMin = new int[n];
		leftMax[0] = arr[0];
		for (int i = 1; i < n; i++)
			leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
		// System.out.println(Arrays.toString(leftMax));
		rightMin[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--)
			rightMin[i] = Math.min(arr[i], rightMin[i + 1]);
		// System.out.println(Arrays.toString(rightMin));
		for (int i = 1; i < n - 1; i++) {
			if (arr[i] == leftMax[i]
					&& arr[i] == rightMin[i])
				return arr[i];
		}
		return -1;
	}

	// Efficient Approach: TC: O(n) & SC: O(n)
	public static int findElement(int[] arr) {
		int m = arr[0], n = arr.length;
		int ans = -1;
		for (int i = 1; i < n; i++) {
			if (arr[i] >= m) {
				m = arr[i];
				if (ans == -1 && i < n - 1)
					ans = m;
			} else if (arr[i] < ans) {
				ans = -1;
			}
		}
		return ans;
	}

	/* Leaders in an array:
	 * Given an array of positive integers. Your task is to find the leaders in the array.
	 * Note: An element of array is leader if it is greater than or equal to all the elements to its right side. Also, the
	 * rightmost element is always a leader. 
	 */
	public static void leadersInArray3(int[] arr) {
		int currMax = Integer.MIN_VALUE;
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] > currMax) {
				currMax = arr[i];
				result.add(arr[i]);
			}
		}
		for (int i = result.size() - 1; i >= 0; i--) {
			System.out.print(result.get(i) + " ");
		}
	}

	public static void leadersInArray(int[] arr) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(
				Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++)
			queue.add(arr[i]);
		for (int i = 0; i < arr.length; i++) {
			queue.remove(arr[i]);
			if (queue.isEmpty() || arr[i] > queue.peek())
				System.out.print(arr[i] + " ");
		}
	}

	/* Equilibrium point/Find Pivot Index - Use Prefix Sum
	 * Equilibrium position in an array is a position such that the sum 
	 * of elements before it is equal to the sum of elements after it.
	 */
	public static int equilibriumPoint2(int[] a) {
		int leftSum = 0, sum = 0;
		if (a.length == 1)
			return 1;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		for (int j = 0; j < a.length; j++) {
			sum -= a[j];
			if (leftSum == sum)
				return j + 1;
			leftSum += a[j];
		}
		return -1;
	}

	public static int equilibriumPoint1(int[] a) {
		int leftSum, rightSum;
		if (a.length == 1)
			return 1;
		for (int i = 1; i < a.length; i++) {
			leftSum = 0;
			// Left Sum:
			for (int j = 0; j < i; j++)
				leftSum += a[j];
			// Right sum
			rightSum = 0;
			for (int k = i + 1; k < a.length; k++)
				rightSum += a[k];
			if (leftSum == rightSum)
				return (i + 1); // Problem expects to return position(not index)
		}
		return -1;
	}

	/* Find Majority Element in an Array/Majority Element I, II
	 * Given an array of size n, find the majority element. The majority element is the element that appears more than n/2 times.
	 */
	// Using Sorting:
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length;
		int count = 1, maxCount = 1, majElement = nums[0];
		for (int i = 0; i < n; i++) {
			if (i < n - 1 && nums[i] == nums[i + 1]) {
				count++;
			} else {
				if (count > maxCount) {
					majElement = nums[i - 1];
					maxCount = count;
				}
				count = 1;
			}
		}
		return (maxCount >= n / 2) ? majElement : 0;
	}

	// Using Moore’s Voting Algorithm: Time Complexity:O(n)
	public int majorityElement2(int[] nums) {
		// Find the Majority Element
		int count = 1, majElement = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == majElement)
				count++;
			else
				count--;
			if (count == 0) {
				majElement = nums[i];
				count = 1;
			}
		}
		// Validate the Majority element which you found in prev step
		System.out
				.println("Majority Element: " + majElement);
		count = 0;
		for (int i = 0; i < nums.length; i++)
			if (nums[i] == majElement)
				count++;

		return (count >= nums.length / 2) ? majElement : 0;
	}

	/* Third Maximum Number
	 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the 
	 * maximum number. The time complexity must be in O(n).
	 */
	public int thirdMax(int[] nums) {
		Integer first = null, second = null, third = null;
		for (Integer num : nums) {
			if (num.equals(first) || num.equals(second)
					|| num.equals(third))
				continue;
			if (first == null || first <= num) {
				third = second;
				second = first;
				first = num;
			} else if (second == null || second <= num) {
				third = second;
				second = num;
			} else if (third == null || third <= num) {
				third = num;
			}
		}
		return third == null ? first : third;
	}

	/* Increasing Triplet Subsequence -> Similar to 3rd max No
	 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
	 * Formally the function should:
	 * 	 Return true if there exists i, j, k
	 * 	 such that arr[i] < arr[j] < arr[k] given 0 <= i < j < k <= n-1 else return false.
	 */
	public boolean increasingTriplet(int[] nums) {
		int first = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (first >= nums[i])
				first = nums[i];// update first to be a smaller value
			else if (second >= nums[i])
				second = nums[i]; // update second to be a smaller value
			else
				return true;
		}
		return false;
	}

	/**************************** Type3: Range Problems ***********************************/
	/* Summary Ranges:
	 * Given a sorted integer array without duplicates, return the summary of its ranges.
	 * Example 1:
	 * 	Input:  [0,1,2,4,5,7]; Output: ["0->2","4->5","7"]
	 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
	 */
	// Simplified Program
	public List<String> summaryRanges(int[] nums) {
		List<String> result = new ArrayList<>();
		int n = nums.length;
		if (n == 0)
			return result;
		String start;
		for (int i = 0; i < n; i++) {
			start = String.valueOf(nums[i]);
			while (i + 1 < n && nums[i] + 1 == nums[i + 1])
				i++;
			if (!start.equals(String.valueOf(nums[i])))
				start += "->" + String.valueOf(nums[i]);
			result.add(start);
		}
		return result;
	}

	// Brute Force Approach:
	public List<String> summaryRanges2(int[] nums) {
		List<String> result = new ArrayList<>();
		int n = nums.length;
		if (n == 0)
			return result;
		if (n == 1) {
			result.add(String.valueOf(nums[0]));
			return result;
		}
		int start, end;
		start = end = nums[0];
		for (int i = 1; i < n; i++) {
			if (nums[i - 1] + 1 == nums[i]) {
				end = nums[i];
			} else {
				if (start == end)
					result.add(String.valueOf(start));
				else
					result.add(start + "->" + end);
				start = end = nums[i];
			}
		}
		if (start == end)
			result.add(String.valueOf(start));
		else
			result.add(start + "->" + end);
		return result;
	}

	/*
	 * Missing Ranges: Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its
	 * missing ranges. 
	 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
	 */
	public List<String> findMissingRanges(int[] nums,
			int lower, int upper) {

		List<String> result = new ArrayList<>();
		int n = nums.length;
		if (n == 0)
			return result;

		int prev = lower - 1;
		for (int i = 0; i <= n; i++) {
			int curr = i == n ? upper : nums[i];

			if (prev + 2 == curr)
				result.add(String.valueOf(prev + 1));
			else if (prev + 2 < curr)
				result.add(String.valueOf(prev + 1) + "->"
						+ String.valueOf(curr - 1));

			prev = curr;
		}

		result.stream()
				.forEach(k -> System.out.print(k + " "));
		return result;
	}

	/*
	 * Range Addition(1D) I/Array Manipulation: 
	 *    Assume you have an array of length n initialized with all 0's and are given k update operations.
	 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray
	 * A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc. Return the modified array after all k
	 * operations were executed. 
	 * Example: Given: length = 5,
		updates = [
		    [1,  3,  2],
		    [2,  4,  3],
		    [0,  2, -2]
		]
		Output: [-2, 0, 3, 5, 3]
	 */
	/* Solution: For each update, increment the start index by inc, decrement the end index + 1 by inc. Then do a moving sum at 
	 * last. Time Complexity:O(k+n)
	 */
	public int[] getModifiedArray(int length,
			int[][] updates) {
		int[] result = new int[length];

		int si, ei, val;
		for (int[] update : updates) { // O(k) time
			si = update[0];
			ei = update[1];
			val = update[2];
			result[si] += val;
			if (ei + 1 < length)
				result[ei + 1] -= val;
		}

		// Sum the result indices
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += result[i];
			result[i] = sum;
		}

		return result;
	}

	/**************************** Type4: Two Ptr *****************************************/
	// Triplet/All 3 Sum in Array/3Sum Smaller
	// Pythagorean Triplet
	// Count Triplets - GP Triplets
	// 4Sum I, II
	// Pairs - Hashset

	/*Pairs: You will be given an array of integers and a target value. Determine the number of pairs of array elements 
	 * that have a difference equal to a target value.
	 * For example, given an array of [1, 2, 3, 4] and a target value of 1, we have three values meeting the condition.
	 */
	// Approach1: BruteForce Approach: Time Complexity: O(n^2)
	public int pairs1(int k, int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (Math.abs(arr[i] - arr[j]) == k)
					count++;
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
			if (set.contains(arr[i] + k))
				count++;

		return count;
	}

	// Count Triplets - GP Triplets
	long countTriplets(List<Long> arr, long r) {
		long count = 0, target = 0, gp = (r + r * r);
		HashSet<Long> set;
		for (int i = 0; i < arr.size(); i++) {
			target = arr.get(i) * gp; // i.e arr.get(i)*gp = arr.get(i)*(1+r+r*r) - arr.get(i)
			System.out.println(target);
			set = new HashSet<>();
			for (int j = i + 1; j < arr.size(); j++) {
				System.out.println(target - arr.get(j));
				if (set.contains(arr.get(j)))
					count++;
				else
					set.add(target - arr.get(j));
			}
		}

		return count;
	}

	public int[][] threeSumZero(int[] nums) {
		Arrays.sort(nums);
		List<int[]> res = new ArrayList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i - 1] == nums[i])
				continue;
			if (nums[i] > 0)
				break;
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				long sum = (long) nums[i]
						+ (long) nums[left]
						+ (long) nums[right];
				if (sum == 0) {
					res.add(new int[] { nums[i], nums[left],
							nums[right] });
					while (left < right
							&& nums[left] == nums[left + 1])
						left++;
					while (left < right
							&& nums[right] == nums[right
									- 1])
						right--;
				}
				if (sum > 0)
					right--;
				else
					left++;
			}
		}
		return res.toArray(new int[res.size()][]);
	}

	// Using List:
	public ArrayList<ArrayList<Integer>> threeSumZero(
			ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (A == null || A.size() <= 2)
			return result;
		Collections.sort(A);
		// System.out.println(A.toString());
		ArrayList<Integer> temp = null;
		for (int i = 0; i < A.size(); i++) {
			if (i > 0 && A.get(i - 1).equals(A.get(i)))
				continue;
			int l = i + 1, h = A.size() - 1;
			while (l < h) {
				// System.out.println(A.get(i)+ ", " + A.get(l) +", "+A.get(h));
				long sum = (long) A.get(i) + (long) A.get(l)
						+ (long) A.get(h);
				if (sum == 0) {
					// System.out.println("Sum: "+sum);
					temp = new ArrayList<>();
					temp.add(A.get(i));
					temp.add(A.get(l));
					temp.add(A.get(h));
					result.add(temp);

					// Skip the duplicate
					while (l < h && A.get(l)
							.equals(A.get(l + 1)))
						l++;
					while (l < h && A.get(h)
							.equals(A.get(h - 1)))
						h--;
				}
				if (sum <= 0)
					l++;
				else
					h--;
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
					if (a + b == c || a + c == b
							|| a == b + c) {
						System.out.println(arr[i] + " "
								+ arr[j] + " " + arr[k]);
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
			if (sumPresent1(arr, 0, i - 1, arr[i], arr[i]))
				return true;
		}
		return false;
	}

	// Using 2 ptr approach
	public boolean sumPresent1(int[] nums, int l, int h,
			int firstValue, int target) {
		// target -= firstValue; // Remove first value from the target, two find the remaining two values

		while (l < h) {
			if (nums[l] + nums[h] == target) {
				System.out.println("The Triplet is: "
						+ firstValue + ", " + nums[l] + ", "
						+ nums[h]);
				return true;
			} else if (nums[l] + nums[h] > target) {
				h--;
			} else {
				l++;
			}
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
					System.out.println(arr[i] + " " + arr[j]
							+ " " + (int) Math.sqrt(a + b));
					return true;
				}
			}
		}
		return false;
	}

	// 4Sum I:
	// Brute Force Approach
	public List<List<Integer>> fourSum(int[] nums,
			int target) {
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
						if (target == 0 && !result
								.contains(eachList))
							result.add(new ArrayList<>(
									eachList));
					}
				}
			}
		}
		return result;
	}

	// Approach2: Two Ptr Approach: Time Complexity-O(n^3)
	public List<List<Integer>> fourSum2(int[] nums,
			int target) {
		int n = nums.length;
		Arrays.sort(nums); // Sort the array to remove the duplicate order
		List<List<Integer>> result = new ArrayList<>();

		for (int i = 0; i < n - 3; i++) {
			for (int j = i + 1; j < n - 2; j++) {
				int l = j + 1;
				int h = n - 1;
				while (l < h) {
					int sum = nums[i] + nums[j] + nums[l]
							+ nums[h];
					if (sum == target) {
						List<Integer> eachList = Arrays
								.asList(nums[i], nums[j],
										nums[l], nums[h]);
						if (!result.contains(eachList))
							result.add(eachList);
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
	public int fourSumCount1(int[] A, int[] B, int[] C,
			int[] D) {
		int n = A.length, count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					for (int l = 0; l < n; l++)
						if (0 == A[i] + B[j] + C[k] + D[l])
							count++;
		return count;
	}

	// Approach2: Efficient Approach: Time Complexity-O(n^2), Space Complexity-O(n^2)
	public int fourSumCount(int[] A, int[] B, int[] C,
			int[] D) {
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

	/**************************** Type5: Fixed Space Array Problems **************************/
	/*Count frequencies of array elements in range 1 to n: 
	 * Given an unsorted array of n integers which can contain integers from 1 to n. Some elements can be repeated multiple 
	 * times and some other elements can be absent from the array. Count frequency of all elements that are present and 
	 * print the missing elements.
	 * 1. Brute force Approach: Check elements one by one - O(n^2)
	 * 2. Use Counting Sort: Time Complexity -O(n) & Space -O(n)
	 * 3. Efficient Approach: Time Complexity -O(n) & Space -O(1) 
	 */
	public void freqOfElements3(int[] arr) {
		int n = arr.length;
		// 1.Subtract all the elements by one
		for (int i = 0; i < n; i++)
			arr[i] -= 1;

		// 2.Add by n for mod of each index
		for (int i = 0; i < n; i++)
			arr[arr[i] % n] += n;

		// 3.Divide each element by n
		for (int i = 0; i < n; i++)
			System.out
					.println(i + 1 + " - " + (arr[i] / n));
	}

	// Repeat and Missing Number Array: Time: O(n)
	public int[] repeatedNumber(final int[] A) {
		int[] result = new int[2];
		int n = A.length;
		if (n == 0)
			return result;

		// 1.Find Repeating no:
		for (int i = 0; i < n; i++) {
			int index = Math.abs(A[i]) - 1;
			if (A[index] < 0) {
				result[0] = Math.abs(A[i]);
				continue;
			}
			A[index] = -A[index];
		}

		// 2.Find Missing No:
		for (int i = 0; i < n; i++) {
			if (A[i] > 0) {
				result[1] = i + 1;
				break;
			}
		}
		return result;
	}

	/* First Missing Positive:
	 * Given an unsorted integer array, find the smallest missing positive integer.
	 * 	Example 1: Input: [1,2,0] Output: 3
	 * 	Example 2: Input: [3,4,-1,1] Output: 2
	 */
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; i++)
			if (nums[i] <= 0 || nums[i] > n)
				nums[i] = n + 1;
		for (int i = 0; i < n; i++) {
			int num = Math.abs(nums[i]);
			if (num > n)
				continue;
			num--;
			if (nums[num] > 0)
				nums[num] = -nums[num]; // num-1 for array index
		}
		// System.out.println(Arrays.toString(nums));
		for (int i = 0; i < n; i++)
			if (nums[i] >= 0)
				return i + 1;
		return n + 1;
	}

	// Find the Duplicate Number
	// Approach1: Using Sort: Time Complexity:O(nlogn)
	public int findDuplicate1(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++)
			if (nums[i] == nums[i - 1])
				return nums[i];
		return -1;
	}

	// Approach2: Using Hashset: Time Complexity:O(n); Space Complexity: O(n)
	public int findDuplicate2(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i]))
				return nums[i];
			set.add(nums[i]);
		}
		return -1;
	}

	// Approach3: Floyd's Tortoise and Hare; Time Complexity:O(n); Space Complexity: O(1)
	public int findDuplicate(int[] nums) {
		int tortoise = 0;
		int hare = 0;
		// Find the intersection point of the two runners.
		do {
			tortoise = nums[tortoise]; // slowPtr
			hare = nums[nums[hare]]; // fastPtr
		} while (tortoise != hare);

		// Find the "entrance" to the cycle.
		int find = 0;
		while (find != tortoise) {
			tortoise = nums[tortoise];
			find = nums[find];
		}
		return find;
	}

	// Approach4: Binary Search - Time:O(nlogn)
	public int findDuplicate4(int[] nums) {
		int low = 1, high = nums.length - 1;
		while (low <= high) {
			int mid = (int) (low + (high - low) * 0.5);
			int cnt = 0;
			for (int a : nums) {
				if (a <= mid)
					++cnt;
			}
			if (cnt <= mid)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return low;
	}

	public List<Integer> findDisappearedNumbers(
			int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums.length <= 1)
			return result;

		for (int i = 0; i < nums.length; i++) {
			int valIndex = Math.abs(nums[i]) - 1;
			if (nums[valIndex] < 0)
				continue;

			nums[valIndex] = -nums[valIndex];
		}

		for (int i = 0; i < nums.length; i++)
			if (nums[i] > 0)
				result.add(i + 1);

		return result;
	}

	/*************************** Type6: Greedy/Optimization *********************************/

	/************* Clean up ********/

	int minimumSwaps(int[] arr) {
		int count = 0, n = arr.length;
		for (int i = 0; i < n; i++) {
			if (arr[i] == i + 1)
				continue;
			Utils.swap(arr, i, arr[i] - 1);
			count++;
			i--;
		}
		return count;
	}

	/* Max Consecutive Ones II:
	 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
	 * Example 1: Input: [1,0,1,1,0] 
	 * Output: 4 
	 * Explanation: Flip the first zero will get the the maximum number of consecutive 1s. After flipping, the maximum
	 * number of consecutive 1s is 4.
	 */
	// Approach: Two Ptr Approach/Sliding Window
	public int findMaxConsecutiveOnes1(int[] nums) {
		int count = 0, zeros = 0, k = 1; // flip at most k zero
		for (int l = 0, h = 0; h < nums.length; h++) {
			if (nums[h] == 0)
				zeros++;
			while (zeros > k) {
				if (nums[l++] == 0)
					zeros--;
			}

			count = Math.max(count, h - l + 1);
		}

		return count;
	}

	/*
	 * Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store
	 * all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently? 
	 */

	public int findMaxConsecutiveOnes2(int[] nums) {
		int count = 0, k = 1; // flip at most k zero
		Queue<Integer> zeroIndex = new LinkedList<>();
		for (int l = 0, h = 0; h < nums.length; h++) {
			if (nums[h] == 0)
				zeroIndex.add(h);

			if (zeroIndex.size() > k)
				l = zeroIndex.poll() + 1;

			count = Math.max(count, h - l + 1);
		}

		return count;
	}

}