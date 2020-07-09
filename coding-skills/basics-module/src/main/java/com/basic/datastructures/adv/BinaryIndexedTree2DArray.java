package com.basic.datastructures.adv;

import com.common.utilities.Utils;

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
public class BinaryIndexedTree2DArray {

	public static void main(String[] args) {
		int[][] matrix = { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 },
				{ 1, 0, 3, 0, 5 } };
		BinaryIndexedTree2DArray ob = new BinaryIndexedTree2DArray(matrix);

		System.out.println("Sum: ");
		System.out.println("Sum Region: " + ob.sumRegion(2, 1, 4, 3));
		System.out.println("Sum Region: " + ob.sumRegion(1, 1, 2, 2));
		System.out.println("Sum Region: " + ob.sumRegion(1, 2, 2, 4));
		System.out.println("Update: ");
		ob.update(3, 2, 2);
		System.out.println("Sum: ");
		System.out.println("Sum Region: " + ob.sumRegion(2, 1, 4, 3));
	}

	int[][]	biTree;
	int[][]	nums;
	int		m;
	int		n;

	public BinaryIndexedTree2DArray(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return;
		m = matrix.length;
		n = matrix[0].length;
		biTree = new int[m + 1][n + 1];
		nums = matrix;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				insert(i, j, nums[i][j]);
	}

	public void insert(int row, int col, int val) {
		if (m == 0 || n == 0) return;

		for (int i = row + 1; i <= m; i += i & (-i)) {
			// System.out.print(i + "- ");
			for (int j = col + 1; j <= n; j += j & (-j)) {
				// System.out.print(j + " ");
				biTree[i][j] += val;
			}
			// System.out.println();
		}
		Utils.print2DArray(biTree);
		System.out.println();
	}

	public void update(int row, int col, int val) {
		if (m == 0 || n == 0) return;
		int delta = val - nums[row][col];
		nums[row][col] = val;
		System.out.println(val + "-" + nums[row][col] + " " + delta);
		insert(row, col, delta);
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (m == 0 || n == 0) return 0;
		System.out.println("Calculate Sum of Regions: " + row1 + " " + col1 + " " + row2 + " " + col2);
		return sum(row2 + 1, col2 + 1) + sum(row1, col1) - sum(row1, col2 + 1) - sum(row2 + 1, col1);
	}

	public int sum(int row, int col) {
		int sum = 0;
		for (int i = row; i > 0; i -= i & (-i)) {
			System.out.print(i + "- ");
			for (int j = col; j > 0; j -= j & (-j)) {
				System.out.print(j + " ");
				sum += biTree[i][j];
			}
			System.out.println();
		}

		return sum;
	}
}

/* Solution using DP: Keep row sum (dp), O(m) + O(n)
public class NumMatrix {
    private int[][] rowSums;
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        if(   matrix           == null
           || matrix.length    == 0
           || matrix[0].length == 0   ){
            return;   
         }

         this.matrix = matrix;

         int m   = matrix.length;
         int n   = matrix[0].length;
         rowSums = new int[m][n];
         for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(j == 0)
                    rowSums[i][j] = matrix[i][j];
                else rowSums[i][j] = rowSums[i][j - 1] + matrix[i][j];
            } 
         }
    }
    //time complexity for the worst case scenario: O(m)
    public void update(int row, int col, int val) {
        for(int i = col; i < matrix[0].length; i++){
            rowSums[row][i] = rowSums[row][i] - matrix[row][col] + val;
        }

        matrix[row][col] = val;
    }
    //time complexity for the worst case scenario: O(n)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ret = 0;

        for(int j = row1; j <= row2; j++){
            if(col1 == 0) ret += rowSums[j][col2];
            else ret += rowSums[j][col2] - rowSums[j][col1 - 1];
        }

        return ret;
    }
}*/