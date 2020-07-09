package com.basic.datastructures.adv;

import com.common.model.Query;

public class SegmentTree2 {

	public static void main(String[] args) {
		SegmentTree2 ob = new SegmentTree2();
		int arr[] = { 4, 3, 2, 1, 6, 8, 11, 9 };
		System.out.println("Sum of given range: ");
		ob.sumRange(arr, ob.mockQuery1());
	}

	/*
	 * Steps to calculate any logic in the range
	 *  1. Find the segment tree size from input size
	 *  2. Build the segment tree for any given logic(like min, max, lcm, sum etc); Time Complexity: O(n), Space: O(n)
	 *  3. Query the range and find the value Time Complexity: O(logn)
	 */
	// Range Minimum Query:
	public void sumRange(int[] arr, Query[] queries) {
		int n = arr.length;
		int size = findSegTreeSize(n);
		int[] segTree = new int[size];

		buildSegTree(arr, segTree, 0, n - 1, 0);
		// Find the min in given range
		for (int i = 0; i < queries.length; i++) {
			int l = queries[i].left;
			int r = queries[i].right;
			System.out.println("Sum Range: " + sumRange(segTree, l, r, 0, n - 1, 0));
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

	public int sumRange(int[] segTree, int qLow, int qHigh, int arrLow, int arrHigh, int pos) {
		if (arrLow >= qLow && arrHigh <= qHigh) // Total overlap
			return segTree[pos];

		if (arrLow > qHigh || arrHigh < qLow) // No overlap
			return 0;

		int m = (arrLow + arrHigh) / 2;

		return sumRange(segTree, qLow, qHigh, arrLow, m, 2 * pos + 1)
				+ sumRange(segTree, qLow, qHigh, m + 1, arrHigh, 2 * pos + 2);
	}

	/* A recursive function to update the nodes which have the given  
	index in their range. The following are parameters 
	 st, si, ss and se are same as getSumUtil() 
	 i    --> index of the element to be updated. This index is in 
	          input array. 
	diff --> Value to be added to all nodes which have i in range */
	void updateUtil(int[] segTree, int l, int h, int index, int diff, int pos) {
		// Base Case: If the input index lies outside the range of this segment
		if (l > index || h < index) return;

		// If the input index is in range of this node, then update the
		// value of the node and its children
		segTree[pos] = segTree[pos] + diff;
		if (l != h) {
			int mid = l + (h - l) / 2;
			updateUtil(segTree, l, mid, index, diff, 2 * pos + 1);
			updateUtil(segTree, mid + 1, h, index, diff, 2 * pos + 2);
		}
	}

	// The function to update a value in input array and segment tree.
	// It uses updateUtil() to update the value in segment tree
	void update(int arr[], int[] segTree, int n, int i, int new_val) {
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
		updateUtil(segTree, 0, n - 1, i, diff, 0);
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

	/********************** Test Data *****************/
	public Query[] mockQuery1() {
		Query q1 = new Query();
		q1.left = 0;
		q1.right = 2;
		Query q2 = new Query();
		q2.left = 0;
		q2.right = 7;
		Query q3 = new Query();
		q3.left = 1;
		q3.right = 3;
		Query[] queries = { q1, q2, q3 };
		return queries;
	}

	public Query[] mockQuery2() {
		Query q1 = new Query();
		q1.left = 1;
		q1.right = 5;
		Query q2 = new Query();
		q2.left = 5;
		q2.right = 10;
		Query q3 = new Query();
		q3.left = 0;
		q3.right = 10;
		Query[] queries = { q1, q2, q3 };
		return queries;
	}

	public Query[] mockQuery3() {
		Query q1 = new Query();
		q1.left = 1;
		q1.right = 3;
		Query q2 = new Query();
		q2.left = 2;
		q2.right = 4;
		Query q3 = new Query();
		q3.left = 0;
		q3.right = 2;
		Query[] queries = { q1, q2, q3 };
		return queries;
	}
}
