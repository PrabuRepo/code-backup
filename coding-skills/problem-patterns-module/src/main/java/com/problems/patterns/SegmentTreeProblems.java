package com.problems.patterns;

import java.util.Arrays;
import java.util.List;

import com.common.model.Query;
import com.common.model.TreeNode;

public class SegmentTreeProblems {
	public void sumRange(int[] arr, Query[] queries) {
		int n = arr.length;
		int[] segTree = new int[findSegTreeSize(n)];
		buildSegTree(arr, segTree, 0, n - 1, 0);
		for (int i = 0; i < queries.length; i++) {
			int l = queries[i].left;
			int r = queries[i].right;
			System.out.println("Sum Range: " + l + " to " + r + " : " + querySumRange(segTree, l, r, 0, n - 1, 0));
		}
	}

	public void buildSegTree(int[] arr, int[] segTree, int l, int h, int pos) {
		if (l == h) {
			segTree[pos] = arr[l];
			return;
		}
		int m = l + (h - l) / 2;
		buildSegTree(arr, segTree, l, m, 2 * pos + 1);
		buildSegTree(arr, segTree, m + 1, h, 2 * pos + 2);
		segTree[pos] = segTree[2 * pos + 1] + segTree[2 * pos + 2];
	}

	public int querySumRange(int[] segTree, int qLow, int qHigh, int arrLow, int arrHigh, int pos) {
		if (arrLow >= qLow && arrHigh <= qHigh) return segTree[pos];
		if (arrLow > qHigh || arrHigh < qLow) return 0;
		int m = (arrLow + arrHigh) / 2;
		return querySumRange(segTree, qLow, qHigh, arrLow, m, 2 * pos + 1)
				+ querySumRange(segTree, qLow, qHigh, m + 1, arrHigh, 2 * pos + 2);
	}

	/* A recursive function to update the nodes which have the given  
	index in their range. The following are parameters 
	 st, si, ss and se are same as getSumUtil() 
	 i    --> index of the element to be updated. This index is in 
	          input array. 
	diff --> Value to be added to all nodes which have i in range */
	void updateValueUtil(int[] segTree, int l, int h, int index, int diff, int pos) {
		if (l > index || h < index) return;
		segTree[pos] = segTree[pos] + diff;
		if (l != h) {
			int mid = l + (h - l) / 2;
			updateValueUtil(segTree, l, mid, index, diff, 2 * pos + 1);
			updateValueUtil(segTree, mid + 1, h, index, diff, 2 * pos + 2);
		}
	}

	// The function to update a value in input array and segment tree.
	// It uses updateValueUtil() to update the value in segment tree
	void updateValue(int arr[], int[] segTree, int n, int i, int new_val) {
		if (i < 0 || i > n - 1) {
			System.out.println("Invalid Input");
			return;
		}
		int diff = new_val - arr[i];
		arr[i] = new_val;
		updateValueUtil(segTree, 0, n - 1, i, diff, 0);
	}

	/*
	 * Count of Smaller Numbers After Self:
	 * You are given an integer array nums and you have to return a new counts array. The counts array has the property 
	 * where counts[i] is the number of smaller elements to the right of nums[i].
	 * Example:
	 * 	Input: [5,2,6,1]; Output: [2,1,1,0] 
	 */
	public List<Integer> countSmaller(int[] nums) {
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
		} else if (data < root.data) {
			root.count++;
			root.left = insert(root.left, data, result, i, currCount);
		} else {
			root.right = insert(root.right, data, result, i, currCount + root.count + (data > root.data ? 1 : 0));
		}
		return root;
	}

	/**************** Common Method *******************/
	/* Find the segment size or no of nodes in the tree:
	 * Find the nth next 2 power value(Which is equal to no of leafs in the tree); 
	 *     Eg: for 9, it will be 16. No of nodes in the tree = 2*16 -1 (2*no of leafs - 1);
	 *  Steps:
	 *    - Find 2th power from input n(which is equal to max no of leafs in the tree), because all the inputs will be leaf node in segment tree
	 *    - max tree size = 2* no of leafs - 1;  
	 */
	private int findSegTreeSize(int n) {
		int h = (int) (Math.ceil(Math.log(n) / Math.log(2))); // Find 2th power from input n, which is equal to max no
																// of leafs in the tree
		// Maximum size of segment tree
		int noOfLeafs = (int) Math.pow(2, h);
		int treeSize = 2 * noOfLeafs - 1; // No of nodes in tree: 2*(No of leafs =2^h) - 1
		return treeSize;
	}
}
