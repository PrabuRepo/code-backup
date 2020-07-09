package com.problems.patterns;

public class PrefixSumPattern {

	/* Prefix Sum Array: Given an array arr[] of size n, its prefix sum 
	 * array is another array prefixSum[] of same size such that the 
	 * value of prefixSum[i] is arr[0] + arr[1] + arr[2] … arr[i].
	 */

	public void fillPrefixSum(int arr[], int n,
			int prefixSum[]) {
		prefixSum[0] = arr[0];
		// Adding present element with previous element
		for (int i = 1; i < n; ++i)
			prefixSum[i] = prefixSum[i - 1] + arr[i];
	}

	/* Range Sum Query - Immutable -> Use Prefix Sum
	 * Given an integer array nums, find the sum of the
	 * elements between indices i and j (i <= j), inclusive.
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
		if (i == 0)
			return sum[j];

		return sum[j] - sum[i - 1];
	}

	/* Range Sum Query 2D - Immutable
	 * Given a 2D matrix matrix, find the sum of the elements 
	 * inside the rectangle defined by its upper left corner 
	 * (row1, col1) and lower right corner (row2, col2).
	 */
	private int[][] lookup, matrix;

	public void init3(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		populateLookup(matrix);
	}

	// Approach1: Brute force approach
	public int sumRegion1(int row1, int col1, int row2,
			int col2) {
		int sum = 0;
		for (int i = row1; i <= row2; i++) {
			for (int j = col1; j <= col2; j++) {
				sum += matrix[i][j];
			}
		}
		return sum;
	}

	public int sumRegion(int row1, int col1, int row2,
			int col2) {
		if (lookup.length == 0)
			return 0;
		return lookup[row2 + 1][col2 + 1]
				- lookup[row2 + 1][col1]
				- lookup[row1][col2 + 1]
				+ lookup[row1][col1];
	}

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

	/*Product of Array Except Self:
	 * Given an array nums of n integers where n > 1,  return 
	 * an array output such that output[i] is equal to the
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

}
