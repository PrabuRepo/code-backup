package com.consolidated.problems.cheatsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
	}

	public void reverseKGroup2(int[] arr, int k) {
		StringBuffer sb = new StringBuffer();
		int n = arr.length;
		for (int i = 0; i < n; i += k) {
			int left = i;
			int right = Math.min(i + k - 1,
					n - 1);
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
		if (n > 0 && k >= n) k = k % n;
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
	public int removeElement(int[] nums,
			int val) {
		if (nums == null || nums.length == 0)
			return 0;
		int i = 0;
		for (int n : nums) {
			if (n != val) nums[i++] = n;
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

	// Approach-2: Single Traversal - 2 ptr
	public void moveZeroes2(int[] nums) {
		if (nums.length <= 1) return;
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
	public boolean containsDuplicateI1(
			int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i]))
				return true;
			set.add(nums[i]);
		}
		return false;
	}

	// Using Sort
	public boolean containsDuplicateI2(
			int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] == nums[i])
				return true;
		}
		return false;
	}

	// Contains Duplicate II
	public boolean containsNearbyDuplicate(
			int[] nums, int k) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (i > k)
				set.remove(nums[i - k - 1]);
			if (!set.add(nums[i])) return true;
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
	// Approach1: Use two hash sets - Time : O(n)
	public int[] intersection1(int[] nums1,
			int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Set<Integer> intersect = new HashSet<>();
		for (int i = 0; i < nums1.length; i++)
			set.add(nums1[i]);
		for (int i = 0; i < nums2.length; i++)
			if (set.contains(nums2[i])) {
				intersect.add(nums2[i]);
			}
		int[] result = new int[intersect.size()];
		int i = 0;
		for (Integer num : intersect)
			result[i++] = num;
		return result;
	}

	// Approach2: Using Merging - Time: O(nlogn)
	public int[] intersection2(int[] nums1,
			int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i1 = 0, i2 = 0;
		Set<Integer> set = new HashSet<>();
		while (i1 < nums1.length
				&& i2 < nums2.length) {
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
		int[] result = new int[set.size()];
		int i = 0;
		for (Integer val : set)
			result[i++] = val;
		return result;
	}

	// Approach3: Binary search -Time:O(nlogn)
	public int[] intersection(int[] nums1,
			int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Arrays.sort(nums2);
		for (Integer num : nums1)
			if (binarySearch(nums2, num)) {
				set.add(num);
			}
		int i = 0;
		int[] result = new int[set.size()];
		for (Integer num : set)
			result[i++] = num;
		return result;
	}

	public boolean binarySearch(int[] nums,
			int target) {
		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] == target) return true;
			if (nums[mid] > target)
				high = mid - 1;
			else low = mid + 1;
		}
		return false;
	}

	/* Intersection of Two Arrays II:
	1. Brute force :O(n^2)
	2. Using sorting: O(nlogn+mlogm)
	3. Hashing : O(n), but takes additional space
	*/
	// Intersection of Two Arrays II:
	// Using sorting: O(nlogn+mlogm)
	public int[] intersectII1(int[] nums1,
			int[] nums2) {
		ArrayList<Integer> list = new ArrayList<>();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0, j = 0, index = 0;
		while (i < nums1.length
				&& j < nums2.length) {
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
	// Hashing : O(n)
	public int[] intersectII2(int[] nums1,
			int[] nums2) {
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
				int l = a[r] - 2 > 0 ? a[r] - 2
						: 0;
				while (l < r)
					if (a[l++] > a[r]) count++;
			}
		}
		if (flag) System.out.println(count);
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

	// Triple sum No of distinct triplets(p < q > r)
	public long triplets(int[] a, int[] b,
			int[] c) {
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
	int[]	original;
	int[]	shuffled;
	Random	random;

	public void init(int[] nums) {
		original = nums;
		shuffled = Arrays.copyOf(nums,
				nums.length);
		random = new Random();
	}

	public int[] reset() {
		shuffled = Arrays.copyOf(original,
				original.length);
		return shuffled;
	}

	public int[] shuffle() {
		int n = shuffled.length;
		for (int i = 0; i < n; i++) {
			int k = random.nextInt(n);
			Utils.swap(shuffled, i, k);
		}
		return shuffled;
	}

	/**************************** Type2: Number Problems ***********************************/
	/* Element with left side smaller and right side greater
	 * Given an unsorted array of size N. Find the first element in array such that all of its left elements are smaller and 
	 * all right elements to it are greater than it.
	 * Note: Left and right side elements can be equal to required element. And extreme elements cannot be required element.
	 * Approach1: Brute Force Approach
	 *    	A simple solution is to consider every element one by one. For every element, compare it with all elements on the left
	 *  and all elements on right. Time complexity of this solution is O(n^2).
	 * Approach2: Efficient Approach
	 * 	   Create two arrays leftMax[] and rightMin[].Traverse input array from left to right and fill leftMax[] such that leftMax[i] 
	 * contains maximum element from 0 to i-1 in input array. Traverse input array from right to left and fill rightMin[] such that
	 * rightMin[i] contains minimum element from to n-1 to i+1 in input array. Traverse input array. For every element arr[i], check
	 * if arr[i] is greater than leftMax[i] and smaller than rightMin[i]. If yes, return i.
	 * Approach3:
	 * 		Further Optimization to the above approach is to use only one extra array and traverse input array only twice. The first 
	 * traversal is the same as above and fills leftMax[]. Next traversal traverses from the right and keeps track of the minimum. 
	 * The second traversal also finds the required element.
	 */
	// Approach3: TC: O(n) & SC: O(2n)
	public static int findElement1(int[] arr) {
		int n = arr.length;
		int[] leftMax = new int[n];
		int[] rightMin = new int[n];
		leftMax[0] = arr[0];
		for (int i = 1; i < n; i++)
			leftMax[i] = Math.max(leftMax[i - 1],
					arr[i]);
		rightMin[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--)
			rightMin[i] = Math.min(arr[i],
					rightMin[i + 1]);
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
	public static void leadersInArray3(
			int[] arr) {
		int currMax = Integer.MIN_VALUE;
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = arr.length
				- 1; i >= 0; i--) {
			if (arr[i] > currMax) {
				currMax = arr[i];
				result.add(arr[i]);
			}
		}
		for (int i = result.size()
				- 1; i >= 0; i--) {
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
			if (queue.isEmpty()
					|| arr[i] > queue.peek())
				System.out.print(arr[i] + " ");
		}
	}

	/* Equilibrium point/Find Pivot Index - Use Prefix Sum
	 * Given an array A of N positive numbers. The task is to find the position where equilibrium first occurs in the array.
	 * Equilibrium position in an array is a position such that the sum of elements before it is equal to the sum of elements
	 * after it.
	 */
	public static int equilibriumPoint1(int[] a) {
		int leftSum, rightSum;
		if (a.length == 1) return 1;
		for (int i = 1; i < a.length; i++) {
			leftSum = 0;
			for (int j = 0; j < i; j++)
				leftSum += a[j];
			rightSum = 0;
			for (int k = i + 1; k < a.length; k++)
				rightSum += a[k];
			if (leftSum == rightSum)
				return (i + 1);
		}
		return -1;
	}

	public static int equilibriumPoint2(int[] a) {
		int leftSum = 0, sum = 0;
		if (a.length == 1) return 1;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		for (int j = 0; j < a.length; j++) {
			sum -= a[j];
			if (leftSum == sum) return j + 1;
			leftSum += a[j];
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
		int count = 1, maxCount = 1,
				majElement = nums[0];
		for (int i = 0; i < n; i++) {
			if (i < n - 1
					&& nums[i] == nums[i + 1]) {
				count++;
			} else {
				if (count > maxCount) {
					majElement = nums[i - 1];
					maxCount = count;
				}
				count = 1;
			}
		}
		return (maxCount >= n / 2) ? majElement
				: 0;
	}

	// Using Moore’s Voting Algorithm: Time:O(n)
	public int majorityElement2(int[] nums) {
		int count = 1, majElement = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == majElement) count++;
			else count--;
			if (count == 0) {
				majElement = nums[i];
				count = 1;
			}
		}
		count = 0;
		for (int i = 0; i < nums.length; i++)
			if (nums[i] == majElement) count++;
		return (count >= nums.length / 2)
				? majElement
				: 0;
	}

	/* Third Maximum Number
	 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the 
	 * maximum number. The time complexity must be in O(n).
	 */
	public int max3Max(int[] nums) {
		Integer max1 = null, max2 = null,
				max3 = null;
		for (Integer num : nums) {
			if (num.equals(max1)
					|| num.equals(max2)
					|| num.equals(max3))
				continue;
			if (max1 == null || max1 <= num) {
				max3 = max2;
				max2 = max1;
				max1 = num;
			} else if (max2 == null
					|| max2 <= num) {
				max3 = max2;
				max2 = num;
			} else if (max3 == null
					|| max3 <= num) {
				max3 = num;
			}
		}
		return max3 == null ? max1 : max3;
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
			if (first >= nums[i]) first = nums[i];
			else if (second >= nums[i])
				second = nums[i];
			else return true;
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
	public List<String> summaryRanges(
			int[] nums) {
		List<String> result = new ArrayList<>();
		int n = nums.length;
		if (n == 0) return result;
		String start;
		for (int i = 0; i < n; i++) {
			start = String.valueOf(nums[i]);
			while (i + 1 < n
					&& nums[i] + 1 == nums[i + 1])
				i++;
			if (!start.equals(
					String.valueOf(nums[i])))
				start += "->"
						+ String.valueOf(nums[i]);
			result.add(start);
		}
		return result;
	}

	// Brute Force Approach:
	public List<String> summaryRanges2(
			int[] nums) {
		List<String> result = new ArrayList<>();
		int n = nums.length;
		if (n == 0) return result;
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
				if (start == end) result.add(
						String.valueOf(start));
				else result
						.add(start + "->" + end);
				start = end = nums[i];
			}
		}
		if (start == end)
			result.add(String.valueOf(start));
		else result.add(start + "->" + end);
		return result;
	}

	/*
	 * Missing Ranges: Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its
	 * missing ranges. 
	 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
	 */
	public List<String> findMissingRanges(
			int[] nums, int lower, int upper) {
		List<String> result = new ArrayList<>();
		int n = nums.length;
		if (n == 0) return result;
		int prev = lower - 1;
		for (int i = 0; i <= n; i++) {
			int curr = i == n ? upper : nums[i];
			if (prev + 2 == curr) result.add(
					String.valueOf(prev + 1));
			else if (prev + 2 < curr) result
					.add(String.valueOf(prev + 1)
							+ "->"
							+ String.valueOf(
									curr - 1));
			prev = curr;
		}
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
		for (int[] update : updates) {
			si = update[0];
			ei = update[1];
			val = update[2];
			result[si] += val;
			if (ei + 1 < length)
				result[ei + 1] -= val;
		}
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += result[i];
			result[i] = sum;
		}
		return result;
	}

	/*
	 * Range Addition II:
	 * Given an m * n matrix M initialized with all 0's and several update operations. Operations are represented by a 2D array,
	 * and each operation is represented by an array with two positive integers a and b, which means M[i][j] should be added by 
	 * one for all 0 <= i < a and 0 <= j < b.
	 * You need to count and return the number of maximum integers in the matrix after performing all the operations.
	 */
	public int maxCount(int m, int n,
			int[][] ops) {
		if (ops.length == 0) return m * n;
		int rowMin = Integer.MAX_VALUE,
				colMin = Integer.MAX_VALUE;
		for (int[] op : ops) {
			rowMin = Math.min(rowMin, op[0]);
			colMin = Math.min(colMin, op[1]);
		}
		return rowMin * colMin;
	}

	/* Range Sum Query - Immutable -> Use Prefix Sum
	 * Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
	 * Example:	Given nums = [-2, 0, 3, -5, 2, -1]
	 * 	sumRange(0, 2) -> 1
	 * 	sumRange(2, 5) -> -1
	 * 	sumRange(0, 5) -> -3
	 */
	private int[] sum;

	public void init2(int[] nums) {
		for (int i = 1; i < nums.length; i++)
			nums[i] += nums[i - 1];
		this.sum = nums;
	}

	public int sumRange(int i, int j) {
		if (i == 0) return sum[j];
		return sum[j] - sum[i - 1];
	}

	/* Range Sum Query 2D - Immutable
	 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner 
	 * (row1, col1) and lower right corner (row2, col2).
	 */
	private int[][] lookup, matrix;

	public void init3(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		populateLookup(matrix);
	}

	// Approach1: Brute force approach
	public int sumRegion1(int row1, int col1,
			int row2, int col2) {
		int sum = 0;
		for (int i = row1; i <= row2; i++) {
			for (int j = col1; j <= col2; j++) {
				sum += matrix[i][j];
			}
		}
		return sum;
	}

	// Efficient Approach
	public void populateLookup(int[][] matrix) {
		int rowLen = matrix.length,
				colLen = matrix[0].length;
		lookup = new int[rowLen + 1][colLen + 1];
		for (int i = 1; i <= rowLen; i++)
			for (int j = 1; j <= colLen; j++)
				lookup[i][j] = lookup[i][j - 1]
						+ lookup[i - 1][j]
						+ matrix[i - 1][j - 1]
						- lookup[i - 1][j - 1];
	}

	public int sumRegion2(int row1, int col1,
			int row2, int col2) {
		if (lookup.length == 0) return 0;
		return lookup[row2 + 1][col2 + 1]
				- lookup[row2 + 1][col1]
				- lookup[row1][col2 + 1]
				+ lookup[row1][col1];
	}

	/**************************** Type4: Two Ptr *****************************************/
	// Trapping Rain Water I, II
	// Container With Most Water
	// Product of Array Except Self - Prefix/Suffix Mul
	// Two Sum I, II, III /Ice Cream Parlor - 2 Ptr/Bin Search
	// Three Sum/ Triplet/All 3 Sum in Array/3Sum Closest/3Sum Smaller
	// Pythagorean Triplet
	// Count Triplets - GP Triplets
	// 4Sum I, II
	// Pairs - Hashset
	long countTriplets(List<Long> arr, long r) {
		long count = 0, target = 0,
				gp = (r + r * r);
		HashSet<Long> set;
		for (int i = 0; i < arr.size(); i++) {
			target = arr.get(i) * gp; // i.e arr.get(i)*gp = arr.get(i)*(1+r+r*r) - arr.get(i)
			System.out.println(target);
			set = new HashSet<>();
			for (int j = i + 1; j < arr
					.size(); j++) {
				System.out.println(
						target - arr.get(j));
				if (set.contains(arr.get(j)))
					count++;
				else set.add(target - arr.get(j));
			}
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
		for (int i = 0; i < n; i++)
			arr[i] -= 1;
		for (int i = 0; i < n; i++)
			arr[arr[i] % n] += n;
		for (int i = 0; i < n; i++)
			System.out.println(
					i + 1 + " - " + (arr[i] / n));
	}

	//Repeat and Missing Number Array: Time: O(n)
	public int[] repeatedNumber(final int[] A) {
		int[] result = new int[2];
		int n = A.length;
		if (n == 0) return result;

		//1.Find Repeating no:
		for (int i = 0; i < n; i++) {
			int index = Math.abs(A[i]) - 1;
			if (A[index] < 0) {
				result[0] = Math.abs(A[i]);
				continue;
			}
			A[index] = -A[index];
		}

		//2.Find Missing No:
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
			if (num > n) continue;
			num--;
			if (nums[num] > 0)
				nums[num] = -nums[num];
		}
		for (int i = 0; i < n; i++)
			if (nums[i] >= 0) return i + 1;
		return n + 1;
	}

	// Find the Duplicate/Repeating Number
	// Approach1: Using Sort: Time:O(nlogn)
	public int findDuplicate1(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++)
			if (nums[i] == nums[i - 1])
				return nums[i];
		return -1;
	}

	// Approach2: Using Hashset:T:O(n); S:O(n)
	public int findDuplicate2(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i]))
				return nums[i];
			set.add(nums[i]);
		}
		return -1;
	}

	// Approach3: Floyd's Tortoise & Hare; T:O(n); S:O(1)
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

	// Approach4: Binary Search -Time:O(nlogn)
	public int findDuplicate4(int[] nums) {
		int low = 1, high = nums.length - 1;
		while (low <= high) {
			int mid = (int) (low
					+ (high - low) * 0.5);
			int cnt = 0;
			for (int a : nums) {
				if (a <= mid) ++cnt;
			}
			if (cnt <= mid) low = mid + 1;
			else high = mid - 1;
		}
		return low;
	}

	public List<Integer> findDisappearedNumbers(
			int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums.length <= 1) return result;
		for (int i = 0; i < nums.length; i++) {
			int valIndex = Math.abs(nums[i]) - 1;
			if (nums[valIndex] < 0) continue;
			nums[valIndex] = -nums[valIndex];
		}
		for (int i = 0; i < nums.length; i++)
			if (nums[i] > 0) result.add(i + 1);
		return result;
	}

	/*************************** Type6: Greedy/Optimization *********************************/

	/*
	 * Buy and Sell Stock Problems:
	 * Ref: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
	 */
	/*
	 * Max Profit Stock Problem Solution:
	 * 	- Find Minimum  - Buying Price 
	 *  - Find Max difference - Profit(SP - BP) 
	 *  1. Only 1 trx
	 *  2. Only 2 trx
	 *  3: Only K trx
	 *  4: Any number of Trx
	 *  5: Any no of Trx with cooling time
	 *  6. Any no of Trx with Fee
	 *  Note:
	 *  min: BuyingPrice,
	 *  diff: Profit = (Sell Price - Buy Price)
	 *  
	 */
	// Best Time to Buy and Sell Stock - One Transaction
	public int maxProfit1(int[] prices) {
		int diff = 0, min = Integer.MAX_VALUE;
		for (int price : prices) {
			//Find Min:(without considering prev values)
			min = Math.min(min, price);
			//Find Max Diff (as per element order):
			diff = Math.max(diff, price - min);
		}
		return diff;
	}

	// Best Time to Buy and Sell Stock III - Atmost Two Transactions
	public int maxProfit2(int[] prices) {
		int diff1 = 0, min1 = Integer.MAX_VALUE;
		int diff2 = 0, min2 = Integer.MAX_VALUE;
		for (int price : prices) {
			//Find max profit for one trans
			min1 = Math.min(min1, price);
			diff1 = Math.max(diff1, price - min1);
			//Find max profit for 2 trx:
			//Here we consider only 1 max trx profit/diff:
			min2 = Math.min(min2, price - diff1);
			diff2 = Math.max(diff2, price - min2);
		}
		return diff2;
	}

	// Best Time to Buy and Sell Stock IV
	public int maxProfit3(int k, int[] prices) {
		// or prices.length/2
		if (k >= prices.length >>> 1) {
			//Use Multiple Trx solution
			return maxProfit4(prices);
		}
		int[] diff = new int[k + 1];
		int[] min = new int[k + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		for (int price : prices) {
			for (int i = 1; i <= k; i--) {
				min[i] = Math.min(min[i],
						price - diff[i - 1]);
				diff[i] = Math.max(diff[i],
						price - min[i]);
			}
		}
		return diff[k];
	}

	// Best Time to Buy and Sell Stock II - Multiple Transactions
	public int maxProfit4(int[] prices) {
		int diff = 0, min = Integer.MAX_VALUE;
		for (int price : prices) {
			//Find min and also keep adding 
			//prev profit/diff:
			min = Math.min(min, price - diff);
			//Find Max diff:
			diff = Math.max(diff, price - min);
		}
		return diff;
	}

	// Best Time to Buy and Sell Stock with Cooldown - Multiple Transactions
	//Similar to Prev soln, but store prev diff
	public int maxProfit5(int[] prices) {
		int diff2 = 0, diff = 0,
				min = Integer.MAX_VALUE;
		for (int price : prices) {
			int diff1 = diff;
			min = Math.min(min, price - diff2);
			diff = Math.max(diff, price - min);
			diff2 = diff1;
		}
		return diff;
	}

	// Best Time to Buy and Sell Stock with Trx Fee - Multiple Transactions
	// Approach1: pay the fee when selling the stock:
	public int maxProfit6(int[] prices, int fee) {
		int diff = 0, min = Integer.MAX_VALUE;
		for (int price : prices) {
			min = Math.min(min, price - diff);
			diff = Math.max(diff,
					price - min - fee);
		}
		return diff;
	}

	// Approach2: pay the fee when buying the stock:
	public int maxProfit62(int[] prices,
			int fee) {
		int diff = 0, min = Integer.MAX_VALUE;
		for (int price : prices) {
			min = Math.min(min,
					price + fee - diff);
			diff = Math.max(diff, price - min);
		}
		return diff;
	}

	/************* Clean up ********/

	int minimumSwaps(int[] arr) {
		int count = 0, n = arr.length;
		for (int i = 0; i < n; i++) {
			if (arr[i] == i + 1) continue;
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
	public int findMaxConsecutiveOnes1(
			int[] nums) {
		int count = 0, zeros = 0, k = 1; // flip at most k zero
		for (int l = 0, h = 0; h < nums.length; h++) {
			if (nums[h] == 0) zeros++;
			while (zeros > k) {
				if (nums[l++] == 0) zeros--;
			}

			count = Math.max(count, h - l + 1);
		}

		return count;
	}

	/*
	 * Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store
	 * all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently? 
	 */

	public int findMaxConsecutiveOnes2(
			int[] nums) {
		int count = 0, k = 1; // flip at most k zero
		Queue<Integer> zeroIndex = new LinkedList<>();
		for (int l = 0, h = 0; h < nums.length; h++) {
			if (nums[h] == 0) zeroIndex.add(h);

			if (zeroIndex.size() > k)
				l = zeroIndex.poll() + 1;

			count = Math.max(count, h - l + 1);
		}

		return count;
	}

}