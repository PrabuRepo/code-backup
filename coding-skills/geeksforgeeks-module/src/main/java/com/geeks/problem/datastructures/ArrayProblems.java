package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;

import com.common.model.Query;
import com.common.utilities.Utils;

public class ArrayProblems {

	/**
	 * ---------Array Rotations Problems :-----------------
	 */
	// Approach1: Brute force approach - Simple one by one movement
	public void arrayLeftRotation1(int[] a, int d) {
		int n = a.length;
		if (d != n && n > 1) {
			if (d > n) // To handle No of rotations greater than input size
				d = d % n;

			for (int i = 0; i < d && i < n; i++) {
				int temp = a[0];

				for (int j = 1; j < n; j++)
					a[j - 1] = a[j];

				a[n - 1] = temp;
			}
		}
	}

	// Approach2: Using temp space
	public void arrayLeftRotation2(int[] a, int d) {

	}

	// Approach3 : Using Juggling Alg - Revisit this
	public void arrayLeftRotation3(int[] a, int d) {
		int gcdValue = Utils.gcd(a.length, d);
		if (gcdValue > 1) {
			for (int i = 0; i < d; i++) {
				for (int j = i; (j + gcdValue) < a.length; j += gcdValue) {
					Utils.swap(a, j, j + gcdValue);
				}
			}
		} else {
			arrayLeftRotation1(a, d);
		}
	}

	// Approach4: Using Reversal Algorithm
	public void arrayLeftRotation4(int[] nums, int k) {
		int n = nums.length;

		// This condition to handle scenario, where k>=n
		if (n > 0 && k >= n) k = k % n;

		if (n > 1 && k > 0) {
			// 1.First reverse 0 to k-1 & k to n-1
			Utils.reverse(nums, 0, k - 1);
			Utils.reverse(nums, k, n - 1);
			// 2.Second reverse all the elements
			Utils.reverse(nums, 0, n - 1);
		}

	}

	// Approach1: Brute force Approach - Simple one by one movement
	public void arrayRightRotation1(int[] a, int d) {
		int n = a.length;
		if (n != d && n > 1) {
			d = (d > n) ? d % n : d;

			int temp = 0;
			for (int i = 0; i < d; i++) {
				temp = a[n - 1];
				for (int j = n - 1; j > 0; j--) {
					a[j] = a[j - 1];
				}
				a[0] = temp;
			}
		}
	}

	// Approach2: Using temp space
	public void arrayRightRotation2(int[] a, int d) {
	}

	// Approach3 : Using Juggling Alg
	public void arrayRightRotation3(int[] a, int d) {
	}

	// Approach4: Using Reversal Algorithm
	public void arrayRightRotation4(int[] nums, int k) {
		int n = nums.length;
		// This condition to handle scenario, where k>=n
		if (n > 0 && k >= n) k = k % nums.length;

		if (n > 1 && k > 0) {
			// 1.Second reverse all the elements
			Utils.reverse(nums, 0, n - 1);
			// 2.First reverse 0 to k-1 & k to n-1
			Utils.reverse(nums, 0, k - 1);
			Utils.reverse(nums, k, n - 1);
		}
	}

	/**
	 * ---------Arrangement Rearrangement Problems :-----------------
	 */
	/*Rearrange the Positive & Negative mix of array:
	 *  Solution is similar to partition process of QuickSort. Time Complexity-O(n) 
	 */
	public int[] rearrangePosAndNegNumbers(int[] a) {
		// 1. Move all the negative numbers to front side
		int j = 0;
		for (int i = 0; i < a.length; i++)
			if (a[i] < 0) {
				Utils.swap(a, i, j);
				j++;
			}

		/* 2.Now all positive numbers are at end and negative numbers at the beginning of array. Increment the negative 
		 *   index by 2 and positive index by 1, i.e. swap every alternate negative number with next positive number.
		 */
		int posIndex = j, negIndex = 0;
		while (posIndex < a.length && negIndex < posIndex && a[negIndex] < 0) {
			Utils.swap(a, posIndex, negIndex);
			posIndex++;
			negIndex += 2;
		}
		return a;
	}

	// Revere the array using Iterative approach
	public int[] reverseArrayIterative(int[] a) {
		int start = 0, end = a.length - 1;
		while (start < end) {
			Utils.swap(a, start, end);
			start++;
			end--;
		}
		return a;
	}

	// Revere the array using Recursive approach
	public int[] reverseArray(int[] a) {
		reverseArray(a, 0, a.length - 1);
		return a;
	}

	private void reverseArray(int[] a, int l, int r) {
		if (l < r) {
			// Utils.swap(a, l, r); // Tail recursive call: First do the logic and make the call;
			reverseArray(a, l + 1, r - 1);
			Utils.swap(a, l, r); // Head recursive call: First make the call and then do the logic
		}
	}

	// Approach1: First sort the input array, then swap all adjacent elements.
	// Time Complexity: O(nlogn)
	public int[] sortArrayInWaveform(int[] a) {
		Arrays.sort(a);
		for (int i = 0; i + 1 < a.length; i += 2)
			Utils.swap(a, i, i + 1);

		return a;
	}

	// Approach2:
	public int[] sortArrayInWaveform2(int[] a) {
		for (int i = 0; i + 1 < a.length; i += 2) {

			if (i > 0 && a[i - 1] > a[i]) Utils.swap(a, i, i - 1);

			if (a[i] < a[i + 1]) Utils.swap(a, i, i + 1);

		}
		return a;
	}

	// Using count array - With additional space
	public int[] segrgateZeroAndOne1(int[] a) {
		int[] count = new int[2];

		for (int i = 0; i < a.length; i++)
			count[a[i]]++;

		int index = 0;
		int tempSize = 0;
		while (tempSize++ < count[0])
			a[index++] = 0;

		tempSize = 0;
		while (tempSize++ < count[1])
			a[index++] = 1;

		return a;
	}

	public int[] segrgateZeroAndOne2(int[] a) {
		int low = 0, high = a.length - 1;
		while (low < high) {
			if (a[low] < a[high]) {
				low++;
				high--;
			} else if (a[low] > a[high]) {
				Utils.swap(a, low, high);
			} else {
				if (a[low] == 0) low++;
				else high--;
			}
		}
		return a;
	}

	// Using count array - With additional space
	public int[] sortArray012(int[] a) {
		int[] count = new int[3];

		for (int i = 0; i < a.length; i++)
			count[a[i]]++;

		int index = 0;
		while (count[0]-- > 0)
			a[index++] = 0;

		while (count[1]-- > 0)
			a[index++] = 1;

		while (count[2]-- > 0)
			a[index++] = 2;

		return a;
	}

	public int[] sort012(int a[]) {
		int arr_size = a.length;
		int lo = 0, hi = arr_size - 1, mid = 0;
		while (mid <= hi) {
			if (a[mid] == 0) {
				Utils.swap(a, lo, mid);
				lo++;
				mid++;
			} else if (a[mid] == 1) {
				mid++;
			} else if (a[mid] == 2) {
				Utils.swap(a, mid, hi);
				hi--;
			}
		}

		return a;
	}

	// Approach-1
	public void moveZeroes1(int[] nums) {
		int j = 0;
		int k = nums.length;
		// Traverse the array. If element encountered is non-zero, then replace the element at index 'j' with this
		// element
		for (int i = 0; i < k; i++) {
			if (nums[i] != 0) {
				nums[j++] = nums[i];
			}
		}

		// Now all non-zero elements have been shifted to front and 'j' is set as index of first 0.
		// Make all elements 0 from count to end.
		while (j < k) {
			nums[j++] = 0;
		}
	}

	// Approach-2: Using single traversal
	public void moveZeroes2(int[] nums) {
		if (nums.length <= 1) return;

		int i = 0, j = 0;
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				Utils.swap(nums, i, j);
				j++;
			}
		}
	}

	/* Shuffle a given array using Fisher–Yates shuffle Algorithm: Time Complexity:O(n)
	 * Given an array, write a program to generate a random permutation of array elements. This question is also asked 
	 * as “shuffle a deck of cards” or “randomize a given array”. Here shuffle means that every permutation of array element 
	 * should equally likely.
	 */
	public int[] shuffleArray(int[] arr) {
		Random random = new Random(); // How does this work?
		for (int i = 0; i < arr.length; i++) {
			int randomIndex = random.nextInt(arr.length);
			int temp = arr[i];
			arr[i] = arr[randomIndex];
			arr[randomIndex] = temp;
		}
		return arr;
	}

	public int[] shuffleDeckOfCards(int[] arr) {
		return shuffleArray(arr);
	}

	/**
	 * ---------Order Statistics Problems :-----------------
	 */
	/*
	 * Refer Heap Problem section for K’th Smallest/Largest Element in Unsorted Array with different solutions
	 *  K’th Smallest/Largest Element in Unsorted Array | Set 1
	 *  K’th Smallest/Largest Element in Unsorted Array | Set 2 (Expected Linear Time)
	 *  K’th Smallest/Largest Element in Unsorted Array | Set 3 (Worst Case Linear Time)
	 * */

	// Find Mean of an unsorted array
	public int findMeanOfUnsortedArray(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		return sum / a.length;
	}

	// Find median of an unsorted array
	public int findMedianOfUnsortedArray(int[] a) {
		int n = a.length;
		Arrays.sort(a);
		return n % 2 == 0 ? (a[n / 2] + a[(n / 2) - 1]) / 2 : a[n / 2];
	}

	// K-th Largest Sum Contiguous Subarray; Time complexity: O(n^2 log (k)) and O(k) space for min-heap
	public int kthLargestSumInSubarray(int[] a, int k) {
		int n = a.length, temp = 0;
		int[] sum = new int[n + 1];
		PriorityQueue<Integer> queue = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			temp += a[i];
			sum[i] = temp;
			insert(queue, temp, k);
		}

		temp = 0;
		for (int i = 1; i < n; i++) {
			for (int j = i; j < n; j++) {
				temp = sum[j] - sum[i - 1];
				insert(queue, temp, k);
			}
		}
		return queue.poll();
	}

	private void insert(PriorityQueue<Integer> queue, int element, int k) {
		if (queue.size() < k) { // if queue has less than k elements, then simply push it
			queue.add(element);
		} else {
			// if queue has more than k elements and peek value is less than element extract
			// the minimum and insert the element; otherwise ignore it
			if (queue.peek() < element) {
				queue.poll();
				queue.add(element);
			}
		}
	}

	// Largest 3 elements in the array
	public void largest3ElementsInArray(int[] a) {
		// largest three elements in an array
		int first, second, third;
		first = second = third = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > first) {
				third = second;
				second = first;
				first = a[i];
			} else if (a[i] > second) {
				third = second;
				second = a[i];
			} else if (a[i] > third) {
				third = a[i];
			}
		}
		System.out.println("\nPrint largest 3 elements:" + first + "," + second + "," + third);
	}

	/**
	 * ---------Range Queries & Interval Problems :-----------------
	 */
	/* Range Queries can be solved by,
	 *   - Naive or Brute Force Approach:  O(n) time to query
	 *   - Square Root Decomposition: Time complexity of query is O(sqrt(n)); Space required is O(sqrt(n))
	 *   - MO's Algorithm: ??  
	 *   - Simple Table or lookup: query time O(1) with extra space O(n^2) 
	 *   - Sparse Table: query time O(1) with extra space O(n Log n)
	 *   - Segment Tree: Preprocessing time: O(n); Range minimum query: O(Logn); Extra space required is O(n) to store the segment tree. Segment tree allows updates also in O(Log n) time.
	 *  		-> Lazy Propagation in Segment tree
	 */

	// 1.Naive Approach
	public void rangeSumQuery1(int[] a, Query[] query) {
		int l, r, sum;
		for (int i = 0; i < query.length; i++) {
			l = query[i].left;
			r = query[i].right;
			sum = 0;
			for (int j = l; j <= r; j++) {
				sum += a[j];
			}
			System.out.println("Sum range from " + l + " to " + r + " : " + sum);
		}
	}

	// 2.Sqrt (or Square Root) Decomposition Technique
	public void rangeSumQuery2(int[] a, Query[] query) {
		int l, r, n = a.length;
		int sqrt = (int) Math.sqrt(n);
		int blockSize = n / sqrt;
		int[] processedData = new int[blockSize];
		preProcess(a, n, processedData, sqrt);

		for (int i = 0; i < query.length; i++) {
			l = query[i].left;
			r = query[i].right;
			System.out.println("Sum range from " + l + " to " + r + " : " + query(a, processedData, l, r));
		}
	}

	private void preProcess(int[] arr, int n, int[] processedData, int sqrt) {
		int count = 0, sum = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			if ((i + 1) % sqrt == 0) {
				processedData[count++] = sum;
				sum = 0;
			}
		}
	}

	private int query(int[] arr, int[] preProcessedData, int left, int right) {
		int n = arr.length;
		int sqrt = (int) Math.sqrt(n);
		// int blockSize = n / sqrt;
		int sum = 0;

		if (left <= right) {
			while (left < right && left % sqrt != 0)
				sum += arr[left++];

			while (left % sqrt == 0 && (left + sqrt) <= right) {
				sum += preProcessedData[left / sqrt];
				left = left + sqrt;
			}

			while (left <= right && right < n)
				sum += arr[left++];
		}

		return sum;

	}

	// 3.Using MO's Algorithm
	public void rangeSumQuery3(int[] a, Query[] query) {

	}

	// 4.Using Lookup table or Dynamic Programming
	public void rangeSumQuery4(int[] a, Query[] query) {
		int n = a.length;
		int[][] lookup = new int[n][n];

		preProcess(a, lookup, n);

		for (int i = 0; i < query.length; i++) {
			int l = query[i].left;
			int r = query[i].right;
			System.out.println("Sum range from " + l + " to " + r + " : " + lookup[l][r]);
		}
	}

	public void preProcess(int[] input, int[][] lookup, int n) {
		int sum;
		for (int i = 0; i < n; i++) {
			sum = 0;
			for (int j = i; j < n; j++) {
				sum += input[j];
				lookup[i][j] = sum;
			}
		}
	}

	// 5.Using Sparse Table
	public void rangeSumQuery5(int[] a, Query[] query) {

	}

	// 6.Using Segment Tree
	public void rangeSumQuery6(int[] a, Query[] query) {

	}

	// Naive Approach
	public static void rangeMinQuery1(int[] a, Query[] query) {
		// We should be able to efficiently find the minimum value from index L (query
		// start) to R (query end) where 0 <= L <= R <= n-1

	}

	// Square Root Decomposition Approach
	public static void rangeMinQuery2(int[] a, Query[] query) {

	}

	// Using MO's Algorithm
	public static void rangeMinQuery3(int[] a, Query[] query) {

	}

	// Using Lookup table
	public void rangeMinQuery4(int[] arr, Query[] query) {

	}

	// Using Sparse Table
	public void rangeMinQuery5(int[] arr, Query[] query) {
		int n = arr.length, l, r;
		int col = (int) (Math.ceil(Math.log(n)) + 1);
		// int col = log2(n) + 1;
		int[][] sparseTable = new int[n][col];

		// Build sparse table
		buildSparseTable(sparseTable, arr);

		for (int i = 0; i < query.length; i++) {
			l = query[i].left;
			r = query[i].right;
			System.out.println("Min element in range from " + l + " to " + r + " : " + query(sparseTable, arr, l, r));
		}
	}

	private void buildSparseTable(int[][] sparseTable, int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n; i++)
			sparseTable[i][0] = i; // col=2^j; when j=0, it always be one

		for (int j = 1; (1 << j) <= n; j++) {
			// Compute minimum value for all intervals with size 2^j; (2^j == 1<<j)
			for (int i = 0; (i + (1 << j) - 1) < n; i++) {

				int range = 1 << (j - 1);
				if (sparseTable[i][j - 1] < sparseTable[i + range][j - 1]) sparseTable[i][j] = sparseTable[i][j - 1];
				else sparseTable[i][j] = sparseTable[i + range][j - 1];
			}
		}
	}

	// Returns minimum of arr[L..R]
	public int query(int[][] sparseTable, int[] arr, int left, int right) {

		/*Find highest power of 2 that is smaller than or equal to count of elements in given range. For [2, 10], j = 3*/
		int j = (int) Math.log(right - left + 1);

		/*Compute minimum of last 2^j elements with first 2^j elements in range.For [2, 10], we compare arr[lookup[0][3]] and arr[lookup[3][3]]*/
		int index1 = sparseTable[left][j];
		int index2 = sparseTable[right - (1 << j) + 1][j];
		return Integer.min(arr[index1], arr[index2]);
	}

	// Segment Tree
	public void rangeMinQuery6(int[] a, Query[] query) {
		int n = a.length, l, r;
		// Similar to find the nth next 2 power value; Eg: for 9, it will be 16. height = 4;
		// Formula: No of nodes in tree N=2^H-1;; H = floor( lg(N) ) = ceil( (lg(N+1) - 1) )
		// Height of segment tree;
		int h = (int) (Math.ceil(Math.log(n) / Math.log(2)));
		// Maximum size of segment tree
		int treeSize = 2 * (int) Math.pow(2, h) - 1;
		int[] segmentTree = new int[treeSize];

		// Build the segment Tree
		buildSegmentTree(a, segmentTree, 0, n - 1, 0);

		for (int i = 0; i < query.length; i++) {
			l = query[i].left;
			r = query[i].right;
			System.out.println(
					"Min element in range from " + l + " to " + r + " : " + query(segmentTree, l, r, 0, n - 1, 0));
		}
	}

	private void buildSegmentTree(int[] input, int[] segmentTree, int low, int high, int pos) {
		/*If there is one element in array, store it in current node of segment tree and return*/
		if (low == high) {
			segmentTree[pos] = input[low];
			return;
		}

		int mid = (low + high) / 2;
		buildSegmentTree(input, segmentTree, low, mid, 2 * pos + 1);
		buildSegmentTree(input, segmentTree, mid + 1, high, 2 * pos + 2);
		segmentTree[pos] = Integer.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
	}

	private int query(int[] segmentTree, int qLow, int qHigh, int treeLeft, int treeRight, int pos) {
		/*If segment of this node is a part of given range, then return the min of the segment*/
		if (qLow <= treeLeft && qHigh >= treeRight) // Total Overlap
			return segmentTree[pos];

		// If segment of this node is outside the given range
		if (qLow > treeRight || qHigh < treeLeft) // No Overlap
			return Integer.MAX_VALUE;

		int mid = (treeLeft + treeRight) / 2;
		// If a part of this segment overlaps with the given range
		return Integer.min(query(segmentTree, qLow, qHigh, treeLeft, mid, 2 * pos + 1),
				query(segmentTree, qLow, qHigh, mid + 1, treeRight, 2 * pos + 2));
	}

	public static void rangeLCMQuery(int[] a, Query[] query) {
		// Given an array of integers, evaluate queries of the form LCM(l, r).

	}

	// Naive Approach
	public static void rangeGCDQuery(int[] a, Query[] query) {
		// Given an array a[0 . . . n-1]. We should be able to efficiently find the GCD
		// from index qs (query start) to qe (query end) where 0 <= qs <= qe <= n-1.

	}

	/**
	 * ---------Optimization Problems-----------------
	 */

	// Largest Sum Contiguous Subarray
	// 1.Brute Force Approach: Time complexity: 0(n^2)
	public static int largestSumContiguousSubArray1(int[] a) {
		int max = Integer.MIN_VALUE, sum = 0, start = 0, end = 0, updateIndex = 0;
		for (int i = 0; i < a.length; i++) {
			sum = 0;
			for (int j = i; j < a.length; j++) {
				sum += a[j];
				if (sum > max) {
					max = sum;
					start = updateIndex;
					end = i;
				}
				if (sum < 0) {
					sum = 0;
					updateIndex = i + 1;
				}
			}
		}
		System.out.println("Starting Index: " + start + "; Ending Index: " + end);
		return max;
	}

	// 2. The Kadane’s Algorithm for this problem takes O(n) time
	public int largestSumContiguousSubArray2(int[] a) {
		int max, sum = 0, startIndexUpdate = 0, start = 0, end = 0;
		max = a[0];
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum > max) {
				max = sum;
				start = startIndexUpdate;
				end = i;
			}
			if (sum < 0) {
				sum = 0;
				startIndexUpdate = i + 1;
			}
		}
		System.out.println("Starting Index: " + start + "; Ending Index: " + end);
		System.out.println("Size of The Subarray With Maximum Sum: " + (end - start + 1));
		return max;
	}

	// 3.Another simple approach to solve this problem
	public int largestSumContiguousSubArray3(int[] a) {
		int currMax = a[0], max = a[0];
		for (int i = 1; i < a.length; i++) {
			currMax = Math.max(a[i], currMax + a[i]);
			max = Math.max(currMax, max);
		}
		return max;
	}

	/*Subarray Sum Equals K:
	 *    Given an array of integers and an integer k, you need to find the "total number of continuous subarrays" whose sum equals to k.
	 */
	public int subarraySum(int[] nums, int k) {
		int n = nums.length, count = 0, sum = 0;
		Map<Integer, Integer> map = new HashMap<>(); // key-sum & val -count(number of continuous subarrays with sum k)
		map.put(0, 1); // Initialize with count 1;
		for (int i = 0; i < n; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k)) count += map.get(sum - k);

			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;

	}

	/*Maximum Size Subarray Sum Equals k:
	 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
	 */
	// Brute Force Approach:
	public int longestSubArrayWithSumK1(int[] a, int k) {
		int n = a.length, maxSize = 0;
		for (int i = 0; i < n; i++) {
			int size = 0, sum = 0;
			for (int j = i; j < n; j++) {
				size++;
				sum += a[j];
				if (sum == k) maxSize = Math.max(size, maxSize);
			}
		}
		return maxSize;
	}

	// Efficient Approach(Its Similar to 2sum solution):Time Complexity-O(n)
	public int longestSubArrayWithSumK2(int[] a, int k) {
		int n = a.length, max = 0, sum = 0;
		Map<Integer, Integer> map = new HashMap<>(); // key-sum & val -index
		map.put(0, -1); // Intialize the index as -1
		for (int i = 0; i < n; i++) {
			sum += a[i];
			if (!map.containsKey(sum)) map.put(sum, i);

			if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
		}
		return max;
	}

	/*Maximum Sum of Sub array Close to K:
	 *  Given an array, find the maximum sum of subarray close to k but not larger than k. 
	 */
	public int getLargestSumCloseToK(int[] arr, int k) {
		TreeSet<Integer> set = new TreeSet<>();
		int sum = 0, result = Integer.MIN_VALUE;
		set.add(0);
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			Integer ceiling = set.ceiling(sum - k);
			/*System.out.println("Set: " + set.toString() + "-> Sum-K: " + (sum - k) + ", Ceiling: " + ceiling);*/
			if (ceiling != null) result = Math.max(result, sum - ceiling);
			System.out.println("Result: " + result);
			set.add(sum);
		}
		System.out.println("Set: " + set.toString());
		return result;
	}

	/*
	 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
	 * (i.e., buy one and sell one share of the stock multiple times).
	 */
	public int maxProfit(int[] prices) {
		int n = prices.length;
		if (n <= 1) return 0;
		int profit = 0, buyPrice = prices[0];
		for (int i = 1; i < n; i++) {
			if (prices[i] > buyPrice) profit += prices[i] - buyPrice;
			buyPrice = prices[i];
		}

		return profit;
	}

	// Maximum difference between two elements such that larger element appears after the smaller number
	public int maxDifference(int[] arr) {
		int currDiff, maxDiff = Integer.MIN_VALUE, maxRight = arr[arr.length - 1];

		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] > maxRight) {
				maxRight = arr[i];
			} else {
				currDiff = maxRight - arr[i];
				if (currDiff > maxDiff) maxDiff = currDiff;
			}
		}
		return maxDiff == Integer.MIN_VALUE ? 0 : maxDiff;
	}

	int maxDifference(int[] arr, int left, int right) {
		int currDiff, maxDiff = Integer.MIN_VALUE, maxRight = arr[right];

		for (int i = right - 1; i >= left; i--) {
			if (arr[i] > maxRight) {
				maxRight = arr[i];
			} else {
				currDiff = maxRight - arr[i];
				if (currDiff > maxDiff) maxDiff = currDiff;
			}
		}
		return maxDiff == Integer.MIN_VALUE ? 0 : maxDiff;
	}

	/*Problem:Maximum profit by buying and selling a share at most twice. 
	 * Approach1: Max profit with at most two transactions = MAX {max profit with one transaction and subarray price[0..i] +
	                  + max profit with one transaction and subarray price[i+1..n-1]  }
	   Timecomplexity:o(n^2)
	 */
	public int maximumProfit1(int[] prices) {
		int profit, maxProfit = Integer.MIN_VALUE;
		int n = prices.length;
		for (int i = 1; i < n - 1; i++) {
			profit = maxDifference(prices, 0, i) + maxDifference(prices, i + 1, n - 1);
			maxProfit = Math.max(profit, maxProfit);
		}
		return maxProfit;
	}

	public int maximumProfit2(int[] prices) {
		int n = prices.length;
		int[] profitArray = new int[n];
		int maxRight = prices[n - 1];

		for (int i = n - 2; i >= 0; i--) {
			// maxRight has maximum of price[i..n-1]
			if (prices[i] > maxRight) maxRight = prices[i];
			// we can get profit[i] by taking maximum of:
			// a) previous maximum, i.e., profit[i+1]
			// b) profit by buying at price[i] and selling at max_price
			profitArray[i] = Math.max(profitArray[i + 1], maxRight - prices[i]);
		}
		int minLeft = prices[0];
		for (int i = 1; i < n; i++) {
			// minLeft is minimum price in price[0..i]
			if (prices[i] < minLeft) minLeft = prices[i];

			// Maximum profit is maximum of:
			// a) previous maximum, i.e., profit[i-1]
			// b) (Buy, Sell) at (min_price, price[i]) and add profit of other trans. stored
			// in profit[i]
			profitArray[i] = Math.max(profitArray[i - 1], profitArray[i] + (prices[i] - minLeft));
		}

		return profitArray[n - 1];
	}

	public int maxProfit3(int[] prices) {
		int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; i++) {
			buy1 = Math.max(buy1, -prices[i]);
			sell1 = Math.max(sell1, buy1 + prices[i]);
			buy2 = Math.max(buy2, sell1 - prices[i]);
			sell2 = Math.max(sell2, buy2 + prices[i]);
			System.out.println(buy1 + " : " + sell1 + " " + buy2 + " : " + sell2);
		}
		return sell2;
	}

	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if (k >= n / 2) return quickSolve(prices);

		int[][] dp = new int[k + 1][n];
		int currMax = 0;
		for (int i = 1; i <= k; i++) {
			currMax = -prices[0];
			for (int j = 1; j < n; j++) {
				dp[k][j] = Math.max(dp[i][j - 1], prices[i] - currMax);
				currMax = Math.max(currMax, dp[i - 1][j - 1] - currMax);
			}
		}

		return dp[k][n - 1];
	}

	private int quickSolve(int[] prices) {
		int len = prices.length, profit = 0;
		for (int i = 1; i < len; i++)
			// as long as there is a price gap, we gain a profit.
			if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
		return profit;
	}

	// Find the subarray with least average:
	// Approach1: Brute force approach-O(nk)
	// Approach2: Time Complexity: O(n)
	public void subArrayWithLeastAvg(int[] a, int k) {
		int minAvg = Integer.MAX_VALUE;
		int kSum = 0, minIndex = 0, maxIndex = 0;
		for (int i = 0; i < k; i++)
			kSum += a[i];

		for (int i = 0; i < a.length - k; i++) {
			if (minAvg > (kSum / k)) {
				minAvg = kSum / k;
				minIndex = i;
				maxIndex = i + k - 1;
			}
			kSum = (kSum - a[i]) + a[i + k];
		}

		System.out.println("MinIndex: " + minIndex + " MaxIndex: " + maxIndex);
	}

	// Smallest subarray with sum greater than a given value
	public void smallestSubarray(int[] arr) {

	}

	// Brute Force Approach: O(n^3)
	public int sumOfSubArray1(int[] a) {
		int n = a.length, sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				for (int k = i; k <= j; k++) {
					sum += a[k];
				}
			}
		}
		return sum;
	}

	/*Every element arr[i] appears in two types of subsets:
		i)  In subarrays beginning with arr[i]. There are (n-i) such subsets. For example [2] appears in [2] and [2, 3].
		ii) In (n-i)*i subarrays where this element is not first element. For example [2] appears in [1, 2] and [1, 2, 3].
		Total of above (i) and (ii) = (n-i) + (n-i)*i = (n-i)(i+1)
		Formula: a[index] *(n-index)*(index+1).
	*/
	// Efficient Approach: O(n)
	public int sumOfSubArray2(int[] a) {
		int n = a.length, sum = 0;
		for (int i = 0; i < n; i++) {
			sum += (a[i] * (n - i) * (i + 1));
		}
		return sum;
	}

	/**
	 * ---------Sorting Problems-----------------
	 */

	// Union and Intersection of two sorted arrays/Unsorted arrays
	/*
	1. Brute force :O(n^2)
	2. Using sorting: O(nlogn+mlogm)
	3. Hashing : O(n), but takes additional space
	*/
	// Using Sorting and intersect
	public int[] intersect1(int[] nums1, int[] nums2) {
		ArrayList<Integer> list = new ArrayList<>();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0, j = 0;
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

	// Using Hashing: This solution works for data: [1] [1,1]
	public int[] intersect2(int[] nums1, int[] nums2) {
		ArrayList<Integer> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums1.length; i++)
			set.add(nums1[i]);

		for (int i = 0; i < nums2.length; i++) {
			if (set.contains(nums2[i])) list.add(nums2[i]);
		}

		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			result[i] = list.get(i);
		return result;
	}

	// Using Sorting and intersect
	public int[] union1(int[] nums1, int[] nums2) {
		ArrayList<Integer> list = new ArrayList<>();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0, j = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				list.add(nums1[i]);
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				list.add(nums1[i]);
				i++;
			} else {
				list.add(nums2[j]);
				j++;
			}
		}

		while (i < nums1.length)
			list.add(nums1[i++]);

		while (j < nums2.length)
			list.add(nums2[j++]);

		int[] result = new int[list.size()];
		for (i = 0; i < list.size(); i++)
			result[i] = list.get(i);
		return result;
	}

	// Using Hashing:
	public int[] union2(int[] nums1, int[] nums2) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums1.length; i++)
			set.add(nums1[i]);

		for (int i = 0; i < nums2.length; i++)
			set.add(nums2[i]);

		int[] result = new int[set.size()];
		Iterator<Integer> iter = set.iterator();
		int index = 0;
		while (iter.hasNext())
			result[index++] = iter.next();
		return result;
	}

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
			System.out.println(i + 1 + " - " + (arr[i] / n));
	}

	/**
	 * ---------Searching Problems-----------------
	 */

	public void insertUnSortedArray(int[] a, int x, int pos) {
		a[pos] = x;
	}

	public int searchUnSortedArray(int[] a, int x) {
		int index = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) {
				index = i;
			}
		}
		return index;
	}

	public int[] deleteUnSortedArray(int[] a, int x) {
		int index = searchUnSortedArray(a, x);
		if (index != -1) {
			for (int i = index; i < a.length - 1; i++)
				a[i] = a[i + 1];
			a[a.length - 1] = 0;
		}
		return a;
	}

	public void insertSortedArray(int[] a, int x) {
		if (a[0] == -1) {
			a[0] = x;
		} else {
			for (int i = 0; i < a.length; i++) {
				if (i == a.length - 1 || a[i] == -1) {
					a[i] = x;
				} else if (x < a[i]) {
					int curr = a[i], next;
					a[i] = x;
					for (int j = i; j < a.length - 1 && curr != -1; j++) {
						next = a[j + 1];
						a[j + 1] = curr;
						curr = next;
					}
					break;
				}
			}
		}
	}

	// Binary Search
	public int searchSortedArray(int[] a, int x) {
		int index = -1, mid, l = 0, h = a.length - 1;
		while (l <= h) {
			mid = (l + h) / 2;
			if (x == a[mid]) {
				index = mid;
				break;
			} else if (x < a[mid]) {
				h = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return index;
	}

	public void deleteSortedArray(int[] a, int x) {

		int index = searchUnSortedArray(a, x);
		if (index != -1) {
			for (int i = index; i < a.length - 1; i++)
				a[i] = a[i + 1];
			a[a.length - 1] = 0;
		}
	}

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

	// BRute Force Approach
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

	/*
	 * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax^2 +bx+c
	 * to each element x in the array. The returned array must be in sorted order. 
	 * Expected time complexity: O(n)
	 * Example: 
	 *  nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5, Result: [3, 9, 15, 33] 
	 *  nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5 Result: [-23, -5, 1, 7]
	 */
	/* Solution using Two Ptr Approach:
	 * We know that the transformation function forms a parabola, which has a minimum/maximum in the middle, if a != 0,
	 * or a line, if a == 0. So we can start from two ends, for a > 0, fill the result array from end to start, for a <
	 * 0, fill the result array from start to end.
	 */
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		int n = nums.length;
		int[] result = new int[n];
		int l = 0, h = n - 1, fl = 0, fh = 0;
		int i = a >= 0 ? n - 1 : 0; // If a<0 - forward traversal, a>=0 - reverse traversal

		while (l < h) {
			fl = a * (nums[l] * nums[l]) + b * nums[l] + c;
			fh = a * (nums[h] * nums[h]) + b * nums[h] + c;
			if (a >= 0) {
				if (fl > fh) {// reverse traversal
					result[i] = fl;
					l++;
				} else {
					result[i] = fh;
					h--;
				}
				i--;
			} else { // forward traversal
				if (fl < fh) {
					result[i] = fl;
					l++;
				} else {
					result[i] = fh;
					h--;
				}
				i++;
			}
		}
		return result;
	}

	/**
	 * ---------Prefix Sum Array Problems :-----------------
	 */
	/* Given an array arr[] of size n, its prefix sum array is another array prefixSum[] of same size such that the
	 * value of prefixSum[i] is arr[0] + arr[1] + arr[2] … arr[i].
	 */
	public void fillPrefixSum(int arr[], int n, int prefixSum[]) {
		prefixSum[0] = arr[0];

		// Adding present element with previous element
		for (int i = 1; i < n; ++i)
			prefixSum[i] = prefixSum[i - 1] + arr[i];
	}

	/*
	 * Problems:
	 * 	 - Equilibrium index of an array
	 *   - Find if there is a subarray with 0 sum
	 *   - Maximum subarray size, such that all subarrays of that size have sum less than k
	 *   - Maximum subarray sum modulo m 
	 */

	/**
	 * ---------Misc Problems-----------------
	 */

	/*
	 * Given an array of n elements which contains elements from 0 to n-1, with any of these numbers appearing any number of times.
	 * Approach 1: Duplicates in an array in O(n) and by using O(1) extra space
	 */
	public void printDuplicates1(int[] a) {
		int n = a.length, index;
		for (int i = 0; i < n; i++) {
			index = a[i] % n;
			a[index] = a[index] + n;
		}
		for (int i = 0; i < n; i++) {
			if ((a[i] / n) > 1) {
				System.out.print(i + " ");
			}
		}
	}

	// Approach 2: Duplicates in an array in O(n) and by using O(1) extra space
	public void printDuplicates2(int[] arr) {
		int i, size = arr.length;
		System.out.println("The repeating elements are : ");

		for (i = 0; i < size; i++) {
			if (arr[Math.abs(arr[i])] >= 0) arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
			else System.out.print(Math.abs(arr[i]) + " ");
		}
	}

	// Given an array of integers, 1 <=a[i] <= n (n = size of array), some elements appear twice and others appear once.
	public void printDuplicates3(int[] arr) {
		int i, size = arr.length;
		System.out.println("The repeating elements are : ");

		for (i = 0; i < size; i++) {
			if (arr[Math.abs(arr[i]) - 1] >= 0) arr[Math.abs(arr[i]) - 1] = -arr[Math.abs(arr[i]) - 1];
			else System.out.print(Math.abs(arr[i]) + " ");
		}
	}

	// 1.Using formula: n(n+1)/2
	public int missingNumber1(int[] a) {
		int sum = 0, missingSum = 0, n = a.length + 1;
		// formula: n(n+1)/2
		sum = (n * (n + 1)) / 2;

		for (int i = 0; i < a.length; i++)
			missingSum += a[i];

		return sum - missingSum;
	}

	// 2.Using Sum of n elements
	public int missingNumber2(int[] a) {
		int sum = 0, missingSum = 0;
		for (int i = 1; i <= a.length + 1; i++)
			sum += i;

		for (int i = 0; i < a.length; i++)
			missingSum += a[i];

		return sum - missingSum;
	}

	// 3.Using Bitwise operator
	public int missingNumber3(int[] arr) {
		int n = arr.length, x1, x2;
		x1 = arr[0];

		// Find the XOR result for all the elements in the array
		for (int i = 1; i < n; i++)
			x1 ^= arr[i];

		x2 = 1;
		// Find the XOR result for all the elements from 1 to n+1
		for (int i = 2; i <= n + 1; i++)
			x1 ^= i;

		// When you XOR for x1 and x2, duplicate elements will be eliminated and gets the duplicate no
		return x1 ^ x2;
	}

	// Element with left side smaller and right side greater
	public int firstMidElement1(int[] a) {
		int n = a.length;
		if (n <= 2) return -1;

		int min = 0, max = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] < a[min]) min = i;
			if (a[i] > a[max]) max = i;
		}

		int i = min + 1;

		while (i < n) {
			if (a[i] > a[min] && a[i] < a[max]) return a[i];
			i++;
		}
		return -1;
	}

	// Efficient Approach: TC: O(n) & SC: O(n)
	public int findElement2(int[] arr) {
		int n = arr.length;
		int[] leftMax = new int[n];

		leftMax[0] = Integer.MIN_VALUE;
		for (int i = 1; i < n; i++)
			leftMax[i] = Math.max(leftMax[i - 1], arr[i - 1]);
		// System.out.println(Arrays.toString(leftMax));

		int rightMin = Integer.MAX_VALUE;
		for (int i = n - 1; i >= 0; i--) {
			System.out.println(arr[i] + " " + leftMax[i] + " " + rightMin);
			if (arr[i] > leftMax[i] && arr[i] < rightMin) return arr[i];

			rightMin = Math.min(arr[i], rightMin);
		}

		return -1;
	}

	public int findElement3(int[] arr) {
		int m = arr[0], n = arr.length;
		int ans = -1;
		for (int i = 1; i < n; i++) {
			if (arr[i] >= m) {
				m = arr[i];
				if (ans == -1 && i < n - 1) ans = m;
			} else if (arr[i] < ans) {
				ans = -1;
			}
		}
		return ans;
	}

	public int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}

		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;
		return newNumber;
	}

	/**
	 * ---------sub Array Problems :-----------------
	 */

	// Brute force Approach: Time Complexity: O((n-k+1)*k) == O(nk)
	public void maxSlidingWindow1(int arr[], int k) {
		int j, max, n = arr.length;
		for (int i = 0; i <= n - k; i++) {
			max = arr[i];
			for (j = 1; j < k; j++) {
				if (arr[i + j] > max) max = arr[i + j];
			}
			System.out.print(max + " ");
		}
	}

	public int[] maxSlidingWindow2(int[] nums, int k) {
		if (nums.length == 0 || k > nums.length) return new int[0];

		int n = nums.length;
		int[] result = new int[n - k + 1];
		// Queue to store the index of the elements; Max element index should be retained in the deque front.
		Deque<Integer> deque = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			// If 'i' reaches the size k, then Remove the top element
			if (!deque.isEmpty() && i - deque.peek() == k) deque.poll();

			// Keep removing the smaller element from the last in deque
			while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()])
				deque.removeLast();

			deque.addLast(i);

			if (i + 1 >= k) result[i - k + 1] = nums[deque.peek()];
		}

		return result;
	}

	// Length & Print the Longest increasing subarray
	public int longestIncreasingSubarray(int[] arr) {
		int n = arr.length;
		if (n <= 1) return n;

		int len = 1, startIndex = 0, maxLen = 1, i;
		for (i = 1; i < n; i++) {
			if (arr[i - 1] < arr[i]) {
				len++;
			} else {
				if (maxLen < len) {
					maxLen = len;
					startIndex = i - len;
				}
				len = 1;
			}
		}

		if (maxLen < len) {
			maxLen = len;
			startIndex = i - len;
		}

		System.out.print("Print the increasing subarray: ");
		printIncreasingSubArray(arr, maxLen, startIndex);

		return maxLen;
	}

	private void printIncreasingSubArray(int[] arr, int maxLen, int startIndex) {
		while (maxLen-- > 0 && startIndex < arr.length)
			System.out.print(arr[startIndex++] + ", ");
	}

	// Sum of minimum and maximum elements of all subarrays of size k.
	public int sumOfMinMaxOfSubarray(int[] nums, int k) {
		if (nums.length == 0 || k > nums.length) return 0;

		int n = nums.length, sum = 0;
		// Queue to store the index of the elements; Max element index should be retained in the deque front.
		Deque<Integer> maxDeque = new LinkedList<>();
		Deque<Integer> minDeque = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			// If 'i' reaches the size k, then Remove the top element
			if (!maxDeque.isEmpty() && i - maxDeque.peek() == k) maxDeque.poll();
			if (!minDeque.isEmpty() && i - minDeque.peek() == k) minDeque.poll();

			// Keep removing the smaller element from the last in deque
			while (!maxDeque.isEmpty() && nums[i] >= nums[maxDeque.peekLast()])
				maxDeque.removeLast();
			while (!minDeque.isEmpty() && nums[i] <= nums[minDeque.peekLast()])
				minDeque.removeLast();

			maxDeque.addLast(i);
			minDeque.addLast(i);

			if (i + 1 >= k) sum += nums[maxDeque.peek()] + nums[minDeque.peek()];
		}

		return sum;
	}

	// Maximum Average Subarray I: Find maximum average subarray of k length
	public double maxAvgSubArray(int[] arr, int k) {
		if (arr.length == 0 || arr.length < k) return 0;

		int sum = 0;
		double maxAvg = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (i < k) sum += arr[i];
			else {
				maxAvg = Math.max(maxAvg, (double) sum / k);
				sum -= arr[i - k];
				sum += arr[i];
			}
		}

		return Math.max(maxAvg, (double) sum / k);
	}

	/*Maximum Product Subarray:
	 * It is similar to Largest Sum Contiguous Subarray problem. The only thing to note here is, maximum product can also be 
	 * obtained by minimum (negative) product ending with the previous element multiplied by this element.
	 */
	public int maxProduct(int[] nums) {
		if (nums.length == 0) return 0;
		int max = nums[0], min = nums[0], result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int tempMax = max;
			max = Utils.max(max * nums[i], min * nums[i], nums[i]);
			min = Utils.min(tempMax * nums[i], min * nums[i], nums[i]);
			result = Math.max(max, result);
		}
		return result;
	}

	// Find Minimum Length Sub Array With Sum K: Time Complexity: O(n)
	public int minSubArrayLen(int s, int[] nums) {
		if (nums.length == 0) return 0;

		int startIndex = 0, sum = 0, minLen = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			while (sum >= s && startIndex <= i) { // sum is greater than 's' and should be less than 'i'
				if (sum == s) minLen = Math.min(minLen, i - startIndex + 1);
				sum -= nums[startIndex];
				startIndex++;
			}
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	/*Shortest Unsorted Continuous Subarray: Find the Minimum length Unsorted Subarray, sorting which makes the complete
	 array sorted*/
	// Efficient Approach: Time Complexity-O(n)
	public int findUnsortedSubarray(int[] a) {
		if (a.length == 0) return 0;
		int n = a.length, l, r;

		for (l = 0; l < n - 1; l++)
			if (a[l] > a[l + 1]) break;

		if (l == n - 1) // If already sorted
			return 0;

		for (r = n - 1; r > 0; r--)
			if (a[r] < a[r - 1]) break;

		// Find the min & max in between l & r
		int min = a[l], max = a[l];
		for (int i = l + 1; i <= r; i++) {
			min = Math.min(a[i], min);
			max = Math.max(a[i], max);
		}

		// Search element greater than min from 0 to l-1
		for (int i = 0; i < l; i++) {
			if (a[i] > min) {
				l = i;
				break;
			}
		}
		// Search element lesser than ax from r+1 to n
		for (int i = n - 1; i > r; i--) {
			if (a[i] < max) {
				r = i;
				break;
			}
		}
		System.out.println(l + " " + r);
		return r - l + 1;
	}

	public int findUnsortedSubarray2(int[] a) {
		if (a.length == 0) return 0;
		int n = a.length, l = a.length - 1, r = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			// Remove if stack top element is greater than curr element
			while (!stack.isEmpty() && a[stack.peek()] > a[i])
				l = Math.min(l, stack.pop());

			stack.push(i);
		}

		stack.clear();
		for (int i = n - 1; i >= 0; i--) {
			// Remove if stack top element is lesser than curr element
			while (!stack.isEmpty() && a[stack.peek()] < a[i])
				r = Math.max(r, stack.pop());

			stack.push(i);
		}
		return l - r > 0 ? l - r + 1 : 0;
	}

	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int[] tr : transactions) {
			// From: Add from current value -> +ve means others owe you
			map.put(tr[0], map.getOrDefault(tr[0], 0) + tr[2]);
			// To: Subtract from curr value -> -ve means you owe others
			map.put(tr[1], map.getOrDefault(tr[1], 0) - tr[2]);
		}

		int[] account = new int[map.size()];
		int i = 0;
		for (int val : map.values())
			account[i++] = val;

		return dfs(0, 0, account);
	}

	private int dfs(int prevIndex, int count, int[] account) {
		int minCount = Integer.MAX_VALUE, n = account.length;

		while (prevIndex < n && account[prevIndex] == 0)
			prevIndex++;

		for (int currIndex = prevIndex + 1; currIndex < n; currIndex++) {
			if (account[prevIndex] * account[currIndex] < 0) {
				account[currIndex] += account[prevIndex];
				minCount = Math.min(minCount, dfs(prevIndex + 1, count + 1, account));
				account[currIndex] -= account[prevIndex];
			}
		}
		return minCount != Integer.MAX_VALUE ? minCount : count;
	}

	public void nextPermutation(int[] nums) {
		// find two adjacent elements, n[i-1] < n[i]
		int i = nums.length - 1;
		for (; i > 0; i--)
			if (nums[i] > nums[i - 1]) break;

		if (i != 0) {
			// swap (i-1, min), where min is index of the smallest number in [i, n)
			int minIndex = nums.length - 1;
			for (; minIndex >= i; minIndex--)
				if (nums[minIndex] > nums[i - 1]) break;

			swap(nums, i - 1, minIndex);
		}
		reverse(nums, i, nums.length - 1);
	}

	// reverse a[i, j]
	void reverse(int[] a, int i, int j) {
		for (; i < j; i++, j--)
			swap(a, i, j);
	}

	void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}