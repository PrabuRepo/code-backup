package com.basic.datastructures.adv;

import com.common.model.Query;
import com.common.utilities.Utils;

public class SegmentTree {

	public static void main(String[] args) {
		SegmentTree ob = new SegmentTree();
		System.out.println("Range Min Query: ");
		int arr[] = { 4, 3, 2, 1, 6, 8, 11, 9 };
		ob.rangeMinQuery(arr, ob.mockQuery1());

		System.out
				.println("Sum of given range: ");
		ob.sumRange(arr, ob.mockQuery1());

		System.out.println("Range LCM Query: ");
		int[] arr2 = { 5, 7, 5, 2, 10, 12, 11, 17,
				14, 1, 44 };
		// Queries: LCM(2, 5), LCM(5, 10), LCM(0, 10)
		ob.rangeLCMQuery(arr2, ob.mockQuery2());

		System.out.println("Range GCD Query: ");
		int arr3[] = { 2, 3, 60, 90, 50 };
		ob.rangeGCDQuery(arr3, ob.mockQuery3());
	}

	/*
	 * Steps to calculate any logic in the range
	 *  1. Find the segment tree size from input size
	 *  2. Build the segment tree for any given logic(like min, max, lcm, sum etc); Time Complexity: O(n), Space: O(n)
	 *  3. Query the range and find the value Time Complexity: O(logn)
	 */
	// Range Minimum Query
	public void rangeMinQuery(int[] arr,
			Query[] query) {
		int n = arr.length, l, r;
		// Initialize segment tree with expected size
		int[] segmentTree = new int[findSegmentTreeSize(
				n)];

		// Build the segment Tree
		buildSegmentTree(arr, segmentTree, 0,
				n - 1, 0);

		// Find the min in given range
		for (int i = 0; i < query.length; i++) {
			l = query[i].left;
			r = query[i].right;
			System.out.println(
					"Min element in range from "
							+ l + " to " + r
							+ " : "
							+ queryMin(
									segmentTree,
									l, r, 0,
									n - 1, 0));
		}
	}

	private void buildSegmentTree(int[] arr,
			int[] segmentTree, int low, int high,
			int pos) {
		/*If there is one element in array, store it in current node of segment tree and return*/
		if (low == high) {
			segmentTree[pos] = arr[low];
			return;
		}

		int mid = (low + high) / 2;
		buildSegmentTree(arr, segmentTree, low,
				mid, 2 * pos + 1);
		buildSegmentTree(arr, segmentTree,
				mid + 1, high, 2 * pos + 2);
		segmentTree[pos] = Integer.min(
				segmentTree[2 * pos + 1],
				segmentTree[2 * pos + 2]);
	}

	private int queryMin(int[] segmentTree,
			int qLow, int qHigh, int arrLeft,
			int arrRight, int pos) {
		/*If segment of this node is a part of given range, then return the min of the segment*/
		if (qLow <= arrLeft && arrRight <= qHigh) // Total Overlap(Tree indexes are between query indexes)
			return segmentTree[pos];

		// If segment of this node is outside the given range
		if (qLow > arrRight || qHigh < arrLeft) // No Overlap (Tree indexes are outside the query indexes)
			return Integer.MAX_VALUE;

		int mid = (arrLeft + arrRight) / 2;
		// If a part of this segment overlaps with the given range
		return Integer.min(
				queryMin(segmentTree, qLow, qHigh,
						arrLeft, mid,
						2 * pos + 1),
				queryMin(segmentTree, qLow, qHigh,
						mid + 1, arrRight,
						2 * pos + 2));
	}

	// Given an array of integers, evaluate queries of the form LCM(l, r).
	public void rangeLCMQuery(int[] a,
			Query[] query) {
		int n = a.length, l, r;
		// Initialize segment tree with expected size
		int[] segmentTree = new int[findSegmentTreeSize(
				n)];

		// Build the segment Tree
		buildSegmentTreeForLCM(a, segmentTree, 0,
				n - 1, 0);

		// Find the min in given range
		for (int i = 0; i < query.length; i++) {
			l = query[i].left;
			r = query[i].right;
			System.out.println(
					"LCM element in range from "
							+ l + " to " + r
							+ " : "
							+ queryLCM(
									segmentTree,
									l, r, 0,
									n - 1, 0));
		}

	}

	private void buildSegmentTreeForLCM(int[] arr,
			int[] segmentTree, int low, int high,
			int pos) {
		/*If there is one element in array, store it in current node of segment tree and return*/
		if (low == high) {
			segmentTree[pos] = arr[low];
			return;
		}

		int mid = (low + high) / 2;
		buildSegmentTreeForLCM(arr, segmentTree,
				low, mid, 2 * pos + 1);
		buildSegmentTreeForLCM(arr, segmentTree,
				mid + 1, high, 2 * pos + 2);
		segmentTree[pos] = Utils.lcm(
				segmentTree[2 * pos + 1],
				segmentTree[2 * pos + 2]);
	}

	private int queryLCM(int[] segmentTree,
			int qLow, int qHigh, int arrLeft,
			int arrRight, int pos) {
		/*If segment of this node is a part of given range, then return the min of the segment*/
		if (qLow <= arrLeft && qHigh >= arrRight) // Total Overlap
			return segmentTree[pos];

		// If segment of this node is outside the given range
		if (qLow > arrRight || qHigh < arrLeft) // No Overlap
			return 1;

		int mid = (arrLeft + arrRight) / 2;
		// If a part of this segment overlaps with the given range
		return Utils.lcm(
				queryLCM(segmentTree, qLow, qHigh,
						arrLeft, mid,
						2 * pos + 1),
				queryLCM(segmentTree, qLow, qHigh,
						mid + 1, arrRight,
						2 * pos + 2));
	}

	// Naive Approach
	public void rangeGCDQuery(int[] a,
			Query[] query) {
		int n = a.length, l, r;
		// Initialize segment tree with expected size
		int[] segmentTree = new int[findSegmentTreeSize(
				n)];

		// Build the segment Tree
		buildSegmentTreeForGCD(a, segmentTree, 0,
				n - 1, 0);

		// Find the min in given range
		for (int i = 0; i < query.length; i++) {
			l = query[i].left;
			r = query[i].right;
			System.out.println(
					"GCD element in range from "
							+ l + " to " + r
							+ " : "
							+ queryGCD(
									segmentTree,
									l, r, 0,
									n - 1, 0));
		}

	}

	private void buildSegmentTreeForGCD(int[] arr,
			int[] segmentTree, int low, int high,
			int pos) {
		/*If there is one element in array, store it in current node of segment tree and return*/
		if (low == high) {
			segmentTree[pos] = arr[low];
			return;
		}

		int mid = (low + high) / 2;
		buildSegmentTreeForGCD(arr, segmentTree,
				low, mid, 2 * pos + 1);
		buildSegmentTreeForGCD(arr, segmentTree,
				mid + 1, high, 2 * pos + 2);
		segmentTree[pos] = Utils.gcd(
				segmentTree[2 * pos + 1],
				segmentTree[2 * pos + 2]);
	}

	private int queryGCD(int[] segmentTree,
			int qLow, int qHigh, int arrLeft,
			int arrRight, int pos) {
		/*If segment of this node is a part of given range, then return the min of the segment*/
		if (qLow <= arrLeft && qHigh >= arrRight) // Total Overlap
			return segmentTree[pos];

		// If segment of this node is outside the given range
		if (qLow > arrRight || qHigh < arrLeft) // No Overlap
			return 0;

		int mid = (arrLeft + arrRight) / 2;
		// If a part of this segment overlaps with the given range
		return Utils.gcd(
				queryGCD(segmentTree, qLow, qHigh,
						arrLeft, mid,
						2 * pos + 1),
				queryGCD(segmentTree, qLow, qHigh,
						mid + 1, arrRight,
						2 * pos + 2));
	}

	public void sumRange(int[] arr,
			Query[] queries) {
		int n = arr.length;
		int[] segmentTree = new int[findSegmentTreeSize(
				n)];

		buildSumSegmentTree(arr, segmentTree, 0,
				n - 1, 0);
		// Find the min in given range
		for (int i = 0; i < queries.length; i++) {
			int l = queries[i].left;
			int r = queries[i].right;
			System.out.println("Sum Range: " + l
					+ " to " + r + " : "
					+ querySumRange(segmentTree,
							l, r, 0, n - 1, 0));
		}
	}

	public void buildSumSegmentTree(int[] arr,
			int[] segmentTree, int l, int h,
			int pos) {
		if (l == h) {
			segmentTree[pos] = arr[l];
			return;
		}

		int m = l + (h - l) / 2;

		buildSumSegmentTree(arr, segmentTree, l,
				m, 2 * pos + 1);
		buildSumSegmentTree(arr, segmentTree,
				m + 1, h, 2 * pos + 2);
		segmentTree[pos] = segmentTree[2 * pos
				+ 1] + segmentTree[2 * pos + 2];
	}

	public int querySumRange(int[] segmentTree,
			int qLow, int qHigh, int arrLow,
			int arrHigh, int pos) {
		if (arrLow >= qLow && arrHigh <= qHigh) // Total overlap
			return segmentTree[pos];

		if (arrLow > qHigh || arrHigh < qLow) // No overlap
			return 0;

		int m = (arrLow + arrHigh) / 2;

		return querySumRange(segmentTree, qLow,
				qHigh, arrLow, m, 2 * pos + 1)
				+ querySumRange(segmentTree, qLow,
						qHigh, m + 1, arrHigh,
						2 * pos + 2);
	}

	/* A recursive function to update the nodes which have the given  
	index in their range. The following are parameters 
	 st, si, ss and se are same as getSumUtil() 
	 i    --> index of the element to be updated. This index is in 
	          input array. 
	diff --> Value to be added to all nodes which have i in range */
	void updateValueUtil(int[] seqmentTree, int l,
			int h, int index, int diff, int pos) {
		// Base Case: If the input index lies outside the range of this segment
		if (l > index || h < index) return;

		// If the input index is in range of this node, then update the
		// value of the node and its children
		seqmentTree[pos] = seqmentTree[pos]
				+ diff;
		if (l != h) {
			int mid = l + (h - l) / 2;
			updateValueUtil(seqmentTree, l, mid,
					index, diff, 2 * pos + 1);
			updateValueUtil(seqmentTree, mid + 1,
					h, index, diff, 2 * pos + 2);
		}
	}

	// The function to update a value in input array and segment tree.
	// It uses updateValueUtil() to update the value in segment tree
	void updateValue(int arr[], int[] segmentTree,
			int n, int i, int new_val) {
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
		updateValueUtil(segmentTree, 0, n - 1, i,
				diff, 0);
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
		int h = (int) (Math
				.ceil(Math.log(n) / Math.log(2))); // Find 2th power from input n, which is equal to max no
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
