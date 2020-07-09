package com.problems.patterns;

public class BinaryIndexedTreeProblems {

}

/*
 * Implement fenwick or binary indexed tree:
 * A Fenwick tree or binary indexed tree is a data structure providing efficient methods
 * for calculation and manipulation of the prefix sums of a table of values.
 * 
 * Space complexity for fenwick tree is O(n)
 * Time complexity to create fenwick tree is O(nlogn)
 * Time complexity to update value is O(logn)
 * Time complexity to get sum of range is O(logn)
 */
class BinaryIndexedTree {
	int[]	input;
	int[]	biTree;

	public BinaryIndexedTree(int[] nums) {
		int n = nums.length;
		input = nums;
		biTree = new int[n + 1];
		for (int i = 0; i < n; i++)
			insert(i, nums[i]);
	}

	public void update(int i, int val) {
		int oldVal = input[i];
		input[i] = val;
		insert(i, val - oldVal);
	}

	public void insert(int i, int val) {
		i += 1;
		while (i < biTree.length) {
			biTree[i] += val;
			i += (i & -i);
		}
	}

	public int sumRange(int i, int j) {
		int left = sum(i);
		int right = sum(j + 1);
		return right - left;
	}

	public int sum(int i) {
		int sum = 0;
		while (i > 0) {
			sum += biTree[i];
			i -= (i & -i);
		}
		return sum;
	}
}

/*
 *Range Sum Query 2D - Mutable:
 *Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1)
 *and lower right corner (row2, col2).
 *Range Sum Query 2D
 *The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 *Example:
 *  Given matrix = [ [3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]
 *  sumRegion(2, 1, 4, 3) -> 8
 *  update(3, 2, 2)
 *  sumRegion(2, 1, 4, 3) -> 10
 *  Note:
 *  The matrix is only modifiable by the update function.
 *  You may assume the number of calls to update and sumRegion function is distributed evenly.
 *  You may assume that row1 <= row2 and col1 <= col2.
 */
// Time Complexity for Binary Indexed Tree is, O(logm) + O(logn)
class BinaryIndexedTree2DArray {

	int[][]	biTree;
	int[][]	nums;
	int		m;
	int		n;

	public BinaryIndexedTree2DArray(
			int[][] matrix) {
		if (matrix.length == 0
				|| matrix[0].length == 0)
			return;
		m = matrix.length;
		n = matrix[0].length;
		biTree = new int[m + 1][n + 1];
		nums = matrix;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				insert(i, j, nums[i][j]);
	}

	public void insert(int row, int col,
			int val) {
		if (m == 0 || n == 0) return;
		for (int i = row + 1; i <= m; i += i
				& (-i)) {
			for (int j = col + 1; j <= n; j += j
					& (-j)) {
				biTree[i][j] += val;
			}
		}
	}

	public void update(int row, int col,
			int val) {
		if (m == 0 || n == 0) return;
		int delta = val - nums[row][col];
		nums[row][col] = val;
		insert(row, col, delta);
	}

	public int sumRegion(int row1, int col1,
			int row2, int col2) {
		if (m == 0 || n == 0) return 0;
		return sum(row2 + 1, col2 + 1)
				+ sum(row1, col1)
				- sum(row1, col2 + 1)
				- sum(row2 + 1, col1);
	}

	public int sum(int row, int col) {
		int sum = 0;
		for (int i = row; i > 0; i -= i & (-i)) {
			System.out.print(i + "- ");
			for (int j = col; j > 0; j -= j
					& (-j)) {
				sum += biTree[i][j];
			}
		}
		return sum;
	}
}
