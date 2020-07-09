package com.consolidated.problems.datastructures.adv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.common.model.Query;

public class SegmentTreeProblems {
	public void sumRange(int[] arr, Query[] queries) {
		int n = arr.length;
		int[] segmentTree = new int[findSegmentTreeSize(n)];

		buildSumSegmentTree(arr, segmentTree, 0, n - 1, 0);
		// Find the min in given range
		for (int i = 0; i < queries.length; i++) {
			int l = queries[i].left;
			int r = queries[i].right;
			System.out.println("Sum Range: " + l + " to " + r + " : " + querySumRange(segmentTree, l, r, 0, n - 1, 0));
		}
	}

	public void buildSumSegmentTree(int[] arr, int[] segmentTree, int l, int h, int pos) {
		if (l == h) {
			segmentTree[pos] = arr[l];
			return;
		}

		int m = l + (h - l) / 2;

		buildSumSegmentTree(arr, segmentTree, l, m, 2 * pos + 1);
		buildSumSegmentTree(arr, segmentTree, m + 1, h, 2 * pos + 2);
		segmentTree[pos] = segmentTree[2 * pos + 1] + segmentTree[2 * pos + 2];
	}

	public int querySumRange(int[] segmentTree, int qLow, int qHigh, int arrLow, int arrHigh, int pos) {
		if (arrLow >= qLow && arrHigh <= qHigh) // Total overlap
			return segmentTree[pos];

		if (arrLow > qHigh || arrHigh < qLow) // No overlap
			return 0;

		int m = (arrLow + arrHigh) / 2;

		return querySumRange(segmentTree, qLow, qHigh, arrLow, m, 2 * pos + 1)
				+ querySumRange(segmentTree, qLow, qHigh, m + 1, arrHigh, 2 * pos + 2);
	}

	/* A recursive function to update the nodes which have the given  
	index in their range. The following are parameters 
	 st, si, ss and se are same as getSumUtil() 
	 i    --> index of the element to be updated. This index is in 
	          input array. 
	diff --> Value to be added to all nodes which have i in range */
	void updateValueUtil(int[] seqmentTree, int l, int h, int index, int diff, int pos) {
		// Base Case: If the input index lies outside the range of this segment
		if (l > index || h < index) return;

		// If the input index is in range of this node, then update the
		// value of the node and its children
		seqmentTree[pos] = seqmentTree[pos] + diff;
		if (l != h) {
			int mid = l + (h - l) / 2;
			updateValueUtil(seqmentTree, l, mid, index, diff, 2 * pos + 1);
			updateValueUtil(seqmentTree, mid + 1, h, index, diff, 2 * pos + 2);
		}
	}

	// The function to update a value in input array and segment tree.
	// It uses updateValueUtil() to update the value in segment tree
	void updateValue(int arr[], int[] segmentTree, int n, int i, int new_val) {
		// Check for erroneous input index
		if (i < 0 || i > n - 1) {
			System.out.println("Invalid Input");
			return;
		}

		// Get the difference between new value and old value
		int diff = new_val - arr[i];

		// Update the value in array
		arr[i] = new_val;

		// Update the values of nodes in segment tree
		updateValueUtil(segmentTree, 0, n - 1, i, diff, 0);
	}

	/*
	 * Count of Smaller Numbers After Self:
	 * You are given an integer array nums and you have to return a new counts array. The counts array has the property 
	 * where counts[i] is the number of smaller elements to the right of nums[i].
	 * Example:
	 * 	Input: [5,2,6,1]; Output: [2,1,1,0] 
	 */
	// Approach1: Using BST
	public List<Integer> countSmaller1(int[] nums) {
		int n = nums.length;
		Integer[] result = new Integer[n];
		TreeNode root = null;
		for (int i = n - 1; i >= 0; i--) {
			root = insert(root, nums[i], result, i, 0);
		}

		return Arrays.asList(result);
	}

	public TreeNode insert(TreeNode root, int data, Integer[] result, int i, int currCount) {
		if (root == null) {
			root = new TreeNode(data, 0);
			result[i] = currCount;
		} else if (data < root.val) {
			root.count++;
			root.left = insert(root.left, data, result, i, currCount);
		} else {
			root.right = insert(root.right, data, result, i, currCount + root.count + (data > root.val ? 1 : 0));
		}
		return root;
	}

	// Approach2: Using Merge Sort: Time: O(nlogn)
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> res = new ArrayList<>();
		int[] count = new int[nums.length];
		int[] index = new int[nums.length];
		for (int i = 0; i < index.length; i++)
			index[i] = i;

		mergeSort(nums, index, count, 0, nums.length - 1);

		for (int i : count)
			res.add(i);
		return res;
	}

	private void mergeSort(int[] nums, int[] index, int[] count, int low, int high) {
		if (low >= high) return;
		int mid = low + (high - low) / 2;
		mergeSort(nums, index, count, low, mid);
		mergeSort(nums, index, count, mid + 1, high);
		int rightCount = 0, i = low;
		for (int j = mid + 1; i <= mid && j <= high;) {
			if (nums[index[j]] < nums[index[i]]) {
				rightCount++;
				j++;
			} else count[index[i++]] += rightCount;
		}

		while (i <= mid)
			count[index[i++]] += rightCount;

		merge(nums, index, low, high);
	}

	private void merge(int[] nums, int[] index, int low, int high) {
		int mid = low + (high - low) / 2;
		int[] arr = new int[high - low + 1];
		int i = low, j = mid + 1, k = 0;

		while (k < arr.length) {
			int num1 = i <= mid ? nums[index[i]] : Integer.MAX_VALUE;
			int num2 = j <= high ? nums[index[j]] : Integer.MAX_VALUE;

			arr[k++] = num1 <= num2 ? index[i++] : index[j++];
		}

		for (int p = 0; p < arr.length; p++)
			index[p + low] = arr[p];
	}

	/*
	 * Count of Range Sum:
	 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
	 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i <= j), inclusive.
	 * Input: nums = [-2,5,-1], lower = -2, upper = 2;  Output: 3 
	 */
	// Approach1: Brute Force Approach: Time:O(n^2)
	public int countRangeSum1(int[] nums, int lower, int upper) {
		int n = nums.length;
		long[] sums = new long[n + 1];
		for (int i = 0; i < n; ++i)
			sums[i + 1] = sums[i] + nums[i];
		int ans = 0;
		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j <= n; ++j)
				if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper) ans++;
		return ans;
	}

	int	count	= 0;
	int	lower;
	int	upper;

	// Approach2: Using Merge Sort; Time: O(nlogn)
	/*
	 * Despite the nested loops, the time complexity of the "merge & count" stage is still linear. Because the indices
	 * i, j, k will only increase but not decrease, each of them will only traversal the right half once at most. The
	 * total time complexity of this divide and conquer solution is then O(n log n).
	 */
	public int countRangeSum2(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0) return 0;

		long[] sums = new long[nums.length + 1];
		for (int i = 1; i < sums.length; i++) {
			sums[i] = nums[i - 1] + sums[i - 1];
		}

		return mergeSort(sums, lower, upper, 0, sums.length - 1);
	}

	private int mergeSort(long[] sums, int lower, int upper, int low, int high) {
		if (low >= high) return 0;

		int mid = low + (high - low) / 2;
		int count = mergeSort(sums, lower, upper, low, mid) + mergeSort(sums, lower, upper, mid + 1, high);
		int i = mid + 1, j = mid + 1;

		// Time complexity: for i or j, it could only be moved from mid+1 to high,
		// so this is a two pointer problem, not 2 loops
		for (int k = low; k <= mid; k++) {
			while (i <= high && sums[i] - sums[k] < lower)
				i++;
			while (j <= high && sums[j] - sums[k] <= upper)
				j++;
			count += j - i;
		}

		merge(sums, low, high);
		return count;
	}

	private void merge(long[] sums, int low, int high) {
		int mid = low + (high - low) / 2;
		int i = low, j = mid + 1, k = 0;
		long[] arr = new long[high - low + 1];

		while (k < arr.length) {
			long num1 = i <= mid ? sums[i] : Long.MAX_VALUE;
			long num2 = j <= high ? sums[j] : Long.MAX_VALUE;

			arr[k++] = num1 <= num2 ? sums[i++] : sums[j++];
		}

		for (int p = 0; p < arr.length; p++)
			sums[p + low] = arr[p];
	}

	/**************** Common Method *******************/
	/* Find the segment size or no of nodes in the tree:
	 * Find the nth next 2 power value(Which is equal to no of leafs in the tree); 
	 *     Eg: for 9, it will be 16. No of nodes in the tree = 2*16 -1 (2*no of leafs - 1);
	 *  Steps:
	 *    - Find 2th power from input n(which is equal to max no of leafs in the tree), because all the inputs will be leaf node in segment tree
	 *    - max tree size = 2* no of leafs - 1;  
	 */
	private int findSegmentTreeSize(int n) {
		int h = (int) (Math.ceil(Math.log(n) / Math.log(2))); // Find 2th power from input n, which is equal to max no
																// of leafs in the tree
		// Maximum size of segment tree
		int noOfLeafs = (int) Math.pow(2, h);
		int treeSize = 2 * noOfLeafs - 1; // No of nodes in tree: 2*(No of leafs =2^h) - 1
		return treeSize;
	}
}

class TreeNode {
	TreeNode	left, right;
	int			val;
	int			count;		// No of nodes which is greater than left elements

	public TreeNode(int data, int c) {
		this.val = data;
		this.count = c;
	}
}