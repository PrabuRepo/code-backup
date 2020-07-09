package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.common.model.Point;
import com.common.model.TrieNode;
import com.common.utilities.DisjointSet;
import com.common.utilities.Utils;

public class MatrixProblems {
	// Below set follows the order: {up, down,left, right}
	private static final int[]		rowSet4		= { -1, 1, 0, 0 };
	private static final int[]		colSet4		= { 0, 0, -1, 1 };
	private static final int[][]	directions	= { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	// Left,Right,Up,Down & diagonal
	private static final int[]	rowSet8	= { -1, 1, 0, 0, -1, -1, 1, 1 };
	private static final int[]	colSet8	= { 0, 0, -1, 1, -1, 1, -1, 1 };

	/*************** Matrix Basic Problems ************************/
	public int[][] transpose(int[][] A) {
		int row = A.length, col = A[0].length;
		int[][] transpose = new int[col][row];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				transpose[j][i] = A[i][j];
			}
		}
		return transpose;
	}

	public void printMatrixDiagonally(int[][] matrix) {
		int r = matrix.length, c = matrix[0].length;

		for (int k = 0; k < r; k++) {
			int i = k, j = 0;
			while (i >= 0 && j <= k) {
				System.out.print(matrix[i][j] + " ");
				i--;
				j++;
			}
			System.out.println();
		}

		for (int k = 1; k < c; k++) {
			int i = r - 1, j = k;
			while (i >= 0 && j < c) {
				System.out.print(matrix[i][j] + " ");
				i--;
				j++;
			}
			System.out.println();
		}
	}

	public void reverseMatrixColumn(int[][] A) {
		int n = A.length, rowStart = 0, rowEnd = 0, temp = 0;
		for (int col = 0; col < n; col++) {
			rowStart = 0;
			rowEnd = n - 1;
			// Swap the element one by one
			while (rowStart < rowEnd) {
				temp = A[rowStart][col];
				A[rowStart][col] = A[rowEnd][col];
				A[rowEnd][col] = temp;
				rowStart++;
				rowEnd--;
			}
		}
	}

	public void reverseMatrixRow(int[][] A) {
		int n = A.length, colStart = 0, colEnd = 0, temp = 0;
		for (int row = 0; row < n; row++) {
			colStart = 0;
			colEnd = n - 1;
			// Swap the element one by one
			while (colStart < colEnd) {
				temp = A[row][colStart];
				A[row][colStart] = A[row][colEnd];
				A[row][colEnd] = temp;
				colStart++;
				colEnd--;
			}
		}
	}

	// Anti clock wise direction:
	// Apprach1: Take transpose, reverse the column & use an additional space
	public void rotateRightMatrix1(int[][] matrix) {
		System.out.println("Before: ");
		Utils.printMatrix(matrix);

		matrix = transpose(matrix);
		reverseMatrixColumn(matrix);

		System.out.println("After: ");
		Utils.printMatrix(matrix);
	}

	// Approach2: without using any space
	public void rotateRightMatrix2(int[][] matrix) {
		System.out.println("Before: ");
		Utils.printMatrix(matrix);

		int n = matrix.length, temp = 0;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				// Save the top
				temp = matrix[i][j];
				// Move right to top
				matrix[i][j] = matrix[j][n - i - 1];
				// Move bottom to right
				matrix[j][n - i - 1] = matrix[n - i - 1][n - j - 1];
				// Move left to bottom
				matrix[n - i - 1][n - j - 1] = matrix[n - j - 1][i];
				// Assign the top(from temp) to left
				matrix[n - j - 1][i] = temp;
			}
		}

		System.out.println("After: ");
		Utils.printMatrix(matrix);
	}

	// Clock wise direction:
	// Apprach1: Take transpose, reverse the row & use an additional space
	public void rotateLeftMatrix1(int[][] matrix) {
		System.out.println("Before: ");
		Utils.printMatrix(matrix);

		matrix = transpose(matrix);
		reverseMatrixRow(matrix);

		System.out.println("After: ");
		Utils.printMatrix(matrix);
	}

	// Approach2: without using any space
	public void rotateLeftMatrix2(int[][] matrix) {

		System.out.println("Before: ");
		Utils.printMatrix(matrix);

		int n = matrix.length, temp = 0;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				// Save the top
				temp = matrix[i][j];
				// Move left to top
				matrix[i][j] = matrix[n - j - 1][i];
				// Move bottom to left
				matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
				// Move right to bottom
				matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
				// Assign the top(from temp) to right
				matrix[j][n - i - 1] = temp;
			}
		}

		System.out.println("After: ");
		Utils.printMatrix(matrix);
	}

	/* Spiral Matrix:
	 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
	 * 	Example 1:
		Input:
			[
			[ 1, 2, 3 ],
			[ 4, 5, 6 ],
			[ 7, 8, 9 ]
			]
		Output: [1,2,3,6,9,8,7,4,5]
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;

		int r = matrix.length, c = matrix[0].length;
		int left = 0, right = c - 1, top = 0, bottom = r - 1;

		while (top <= bottom && left <= right) { // or result.size() < r*c
			// From left to right
			for (int j = left; j <= right; j++)
				result.add(matrix[top][j]);
			top++;

			// From top to bottom
			for (int i = top; i <= bottom; i++)
				result.add(matrix[i][right]);
			right--;

			if (top > bottom || left > right) break;

			// From right to left
			for (int j = right; j >= left; j--)
				result.add(matrix[bottom][j]);
			bottom--;

			// From bottom to top
			for (int i = bottom; i >= top; i--)
				result.add(matrix[i][left]);
			left++;
		}

		return result;
	}

	static int[] spiralCopy(int[][] matrix) {
		int r = matrix.length, c = matrix[0].length;
		int[] result = new int[r * c];

		if (matrix.length == 0 || matrix[0].length == 0) return result;
		int left = 0, right = c - 1, top = 0, bottom = r - 1, index = 0;

		while (top <= bottom && left <= right) { // or result.size() < r*c
			for (int j = left; j <= right; j++)
				result[index++] = matrix[top][j];
			top++;

			for (int i = top; i <= bottom; i++)
				result[index++] = matrix[i][right];
			right--;

			if (top > bottom || left > right) break;

			for (int j = right; j >= left; j--)
				result[index++] = matrix[bottom][j];
			bottom--;

			for (int i = bottom; i >= top; i--)
				result[index++] = matrix[i][left];
			left++;
		}

		return result;
	}

	// Matrix Multiplication
	public int[][] matrixMul(int[][] a, int[][] b) {
		int m = a.length, n = b[0].length;
		int[][] result = new int[m][n];

		if (a[0].length != b.length) return result;

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < b.length; k++) // k<b.length or k<a[0].length
					result[i][j] += a[i][k] * b[k][j];

		return result;
	}

	/*Sparse Matrix Multiplication:
	 * Optimized Method: We can see that when a_ik is 0, there is no need to compute b_kj. So we switch the inner two 
	 * loops and add a 0-checking condition. 
	 * Since the matrix is sparse, time complexity is ~O(n^2) which is much faster than O(n^3).
	 */
	public int[][] sparseMatrixMul(int[][] a, int[][] b) {
		int m = a.length, n = b[0].length;
		int[][] result = new int[m][n];

		if (a[0].length != b.length) return result;

		for (int i = 0; i < m; i++) {
			for (int k = 0; k < b.length; k++) {
				if (a[i][k] == 0) continue;
				for (int j = 0; j < n; j++)
					result[i][j] += a[i][k] * b[k][j];
			}
		}

		return result;
	}

	/* Bomb Enemy:
	 *      Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return
	 * the maximum enemies you can kill using one bomb. The bomb kills all the enemies in the same row and column from
	 * the planted point until it hits the wall since the wall is too strong to be destroyed. Note that you can only put
	 * the bomb at an empty cell.
	 */
	// Time Complexity: O(mn) == O(mn*(m+n)); Space Complexity: O(n)
	// Solution using DP:
	public int maxKilledEnemies1(char[][] grid) {
		int maxEnemies = 0;
		int m = grid.length, n = grid[0].length;
		int[] colEnemiesCount = new int[n];
		int rowEnemiesCount = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'W') continue;

				// Row Search: Either starts from col 0th index or after the 'W' index
				if (j == 0 || grid[i][j - 1] == 'W') {
					rowEnemiesCount = 0;
					for (int col = j; col < n && grid[i][col] != 'W'; col++)
						if (grid[i][col] == 'E') rowEnemiesCount++;
				}

				// Col Search: Either starts from row 0th index or after the 'W' index
				if (i == 0 || grid[i - 1][j] == 'W') {
					colEnemiesCount[j] = 0;
					for (int row = i; row < m && grid[row][j] != 'W'; row++)
						if (grid[row][j] == 'E') colEnemiesCount[j]++;
				}

				if (grid[i][j] == '0')// Count only in open space
					maxEnemies = Math.max(maxEnemies, rowEnemiesCount + colEnemiesCount[j]);
			}
		}

		return maxEnemies;
	}

	// Solution using DFS(Simple 2 direction Search)
	public int maxKilledEnemies2(char[][] grid) {
		int maxEnemies = 0;
		int m = grid.length, n = grid[0].length;
		int[] colEnemiesCount = new int[n];
		int rowEnemiesCount = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {}
		}

		return maxEnemies;
	}

	/*
	 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of
	 * values 0 or 1, where each 1 marks the home of someone in the group. 
	 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
	 * 
	 * Solution: 1.Find the median of x & y axis
	 *           2.Find the distance from mid points using Manhattan Distance formula
	 */
	public int minTotalDistance1(int[][] grid) {
		int minDistance = 0, m = grid.length, n = grid[0].length;
		List<Integer> pointX = new ArrayList<>();
		List<Integer> pointY = new ArrayList<>();

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 1) {
					pointX.add(i);
					pointY.add(j);
				}
		// Sort the pointY. pointX is already in order, no need to sort
		Collections.sort(pointY);

		int midX = pointX.get(pointX.size() / 2);
		int midY = pointY.get(pointY.size() / 2);
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 1) minDistance += Math.abs(midX - i) + Math.abs(midY - j);

		return minDistance;
	}

	/* Solution: 
	 * If there is only one point, the best meeting point is at the point; if there are two points, the best meeting
	 * point is any point between the two points; If it is three points, the best meeting point is at the middle point;
	 * if it is N points, if N is even, the best meeting point is any point between the two innermost points; if it is N
	 * Odd, the best meeting point is the middlemost point. So you can conclude that finding the midpoint can make the
	 * total distance the shortest.
	 */
	public int minTotalDistance2(int[][] grid) {
		int minDistance = 0, m = grid.length, n = grid[0].length;
		List<Integer> pointX = new ArrayList<>();
		List<Integer> pointY = new ArrayList<>();

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 1) {
					pointX.add(i);
					pointY.add(j);
				}

		// Sort the pointY. pointX is already in order, no need to sort
		Collections.sort(pointY);

		for (Integer i : pointX)
			minDistance += Math.abs(i - pointX.get(pointX.size() / 2));

		for (Integer i : pointY)
			minDistance += Math.abs(i - pointY.get(pointY.size() / 2));

		return minDistance;
	}

	/************** Matrix 4/8 directions flow problems (Top/Bottom/Left/Right) ***************/
	// Matrix 4/8 directions flow problems: These problems can be solved using DFS, BFS, Union Find, Back or simple
	// search

	/*Flood Fill/Paint Fill Algorithm:
	 * Flood fill Algorithm – how to implement fill() in paint?
	 * 	In MS-Paint, when we take the brush to a pixel and click, the color of the region of that pixel is replaced with 
	 * a new selected color. 
	 * Following is the problem statement to do this task:
	 * Given a 2D screen, location of a pixel in the screen ie(x,y) and a color(K), your task is to replace color of the 
	 * given pixel and all adjacent(excluding diagonally adjacent) same colored pixels with the given color K.
	 */
	public void floodFillAlg(int[][] m, int x, int y, int newColor) {
		int rSize = m.length, cSize = m[0].length;
		if (x < 0 || x >= rSize || y < 0 || y >= cSize) return;

		floodFill(m, newColor, m[x][y], x, y);

		// Print the result:
		for (int i = 0; i < rSize; i++)
			for (int j = 0; j < cSize; j++)
				System.out.print(m[i][j] + " ");
	}

	public void floodFill(int[][] m, int newColor, int oldColor, int i, int j) {
		int rSize = m.length, cSize = m[0].length;
		if (i < 0 || i >= rSize || j < 0 || j >= cSize || m[i][j] != oldColor) return;

		m[i][j] = newColor; // Apply the new color
		floodFill(m, newColor, oldColor, i + 1, j);
		floodFill(m, newColor, oldColor, i - 1, j);
		floodFill(m, newColor, oldColor, i, j + 1);
		floodFill(m, newColor, oldColor, i, j - 1);
	}

	// Find the number of islands
	// Approach1: Using DFS(left/right/up/down) movement; Time Complexity - O(m*n)
	public int numberOfIslandsDFS(int[][] matrix) {
		int row = matrix.length, col = matrix[0].length, count = 0;
		boolean[][] visited = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 1 && !visited[i][j]) {
					// searchIn4Dir1(matrix, visited, i, j); // 1. Search in 4 directions using recursion
					// searchIn4Dir2(matrix, visited, i, j); // 2. Search in 4 directions using iteration
					// searchIn8Dir1(matrix, visited, i, j); // 3. Search in 8 directions using recursion
					searchIn8Dir2(matrix, visited, i, j); // 4. Search in 8 directions using iteration
					count += 1;
				}
			}
		}
		return count;
	}

	// Approach2: Using BFS
	public int numberOfIslandsBFS(char[][] matrix) {
		int row = matrix.length, col = matrix[0].length, count = 0;
		boolean[][] visited = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 1 && !visited[i][j]) {
					numberOfIslandBFS(matrix, i, j);
					count += 1;
				}
			}
		}
		return count;
	}

	// Search in 4 directions: Using Recursive calls
	public void searchIn4Dir1(int[][] matrix, boolean[][] visited, int i, int j) {
		int row = matrix.length, col = matrix[0].length;
		// Base case
		if (!isValid(i, j, row, col) || matrix[i][j] != 1 || visited[i][j]) return;

		visited[i][j] = true;

		searchIn4Dir1(matrix, visited, i + 1, j); // down
		searchIn4Dir1(matrix, visited, i - 1, j); // up
		searchIn4Dir1(matrix, visited, i, j + 1); // right
		searchIn4Dir1(matrix, visited, i, j - 1); // left
	}

	// Search in 4 directions: Using Recursive & Iterative calls
	public void searchIn4Dir2(int[][] matrix, boolean[][] visited, int i, int j) {
		int row = matrix.length, col = matrix[0].length;

		visited[i][j] = true;

		for (int k = 0; k < rowSet4.length; k++) {
			int r = i + rowSet4[k];
			int c = j + colSet4[k];
			if (isValid(r, c, row, col) && matrix[r][c] == 1 && !visited[r][c]) searchIn4Dir2(matrix, visited, r, c);
		}
	}

	// Search in 8 directions : Using Recursive calls
	public void searchIn8Dir1(int[][] matrix, boolean[][] visited, int i, int j) {
		int row = matrix.length, col = matrix[0].length;
		// Base case
		if (!isValid(i, j, row, col) || matrix[i][j] != 1 || visited[i][j]) return;

		visited[i][j] = true;

		searchIn8Dir1(matrix, visited, i + 1, j); // down
		searchIn8Dir1(matrix, visited, i - 1, j); // up
		searchIn8Dir1(matrix, visited, i, j + 1); // right
		searchIn8Dir1(matrix, visited, i, j - 1); // left

		searchIn8Dir1(matrix, visited, i - 1, j - 1); // leftUpCorner
		searchIn8Dir1(matrix, visited, i - 1, j + 1); // RightUpCorner
		searchIn8Dir1(matrix, visited, i + 1, j - 1); // LeftDownCorner
		searchIn8Dir1(matrix, visited, i + 1, j + 1); // Right Down Corner
	}

	// Search in 8 directions: Using Recursive & Iterative calls
	public void searchIn8Dir2(int[][] matrix, boolean[][] visited, int i, int j) {
		int row = matrix.length, col = matrix[0].length;

		visited[i][j] = true;

		for (int k = 0; k < rowSet8.length; k++) {
			int r = i + rowSet8[k];
			int c = j + colSet8[k];
			if (isValid(r, c, row, col) && matrix[r][c] == 1 && !visited[r][c]) searchIn8Dir2(matrix, visited, r, c);
		}
	}

	public void numberOfIslandBFS(char[][] grid, int i, int j) {
		Queue<Integer> queue = new LinkedList<>();

		int row = grid.length, col = grid[0].length;
		int index = i * col + j;
		queue.add(index); // Other approach is create class with row and column, add that value in queue
		grid[i][j] = '0';

		while (!queue.isEmpty()) {
			int top = queue.poll();
			int r = top / col;
			int c = top % col;

			if (r - 1 >= 0 && grid[r - 1][c] == '1') {
				grid[r - 1][c] = '0';
				queue.add((r - 1) * col + c);
			}

			if (r + 1 < row && grid[r + 1][c] == '1') {
				grid[r + 1][c] = '0';
				queue.add((r + 1) * col + c);
			}

			if (c - 1 >= 0 && grid[r][c - 1] == '1') {
				grid[r][c - 1] = '0';
				queue.add(r * col + (c - 1));
			}

			if (c + 1 < col && grid[r][c + 1] == '1') {
				grid[r][c + 1] = '0';
				queue.add(r * col + (c + 1));
			}
		}
	}

	// Approach3: Using Disjoint Set(Union-Find)
	public int numberOfIslandsUF(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

		int m = grid.length;
		int n = grid[0].length;
		int count = 0;
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		int[] parent = new int[m * n];
		// Create the disjoint set, for all the '1's in the matrix
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					parent[i * n + j] = i * n + j; // rowIndex * colSize + colIndex
					count++;
				}
			}
		}

		// Group the connected components(meaning 1's in the 4 directions)
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					// Check in four directions
					for (int k = 0; k < 4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1')
							if (union(parent, i * n + j, x * n + y)) count--;
					}
				}
			}
		}

		return count;
	}

	public boolean union(int[] parent, int i1, int i2) {
		int root1 = find(parent, i1);
		int root2 = find(parent, i2);
		if (root1 != root2) { // If it doesn't have same parent
			parent[root2] = root1;
			return true; // Means pointed to same parent or union the two sets
		}
		return false; // Means already pointed to same parent, no need to combine or union the sets
	}

	public int find(int[] parent, int i) {
		while (parent[i] != i) {
			parent[i] = parent[parent[i]];
			i = parent[i];
		}

		return i;
	}

	/*
	 * Number of Islands II: 
	 *     A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns 
	 * the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each
	 *  addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
	 *  You may assume all four edges of the grid are all surrounded by water.
	 *  
	 * Solution using Union-Find:
	 * UNION operation is only changing the root parent so the running time is O(1).
	 * FIND operation is proportional to the depth of the tree. If N is the number of points added, the average running time is O(logN), and a sequence of 4N operations take O(NlogN). 
	 * If there is no balancing, the worse case could be O(N^2).
	 */
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int count = 0;

		// Create the disjoint set
		DisjointSet ds = new DisjointSet(m * n);
		Arrays.fill(ds.parent, -1);// Initially set -1s in all the set

		// Traverse edges one by one
		for (int k = 0; k < positions.length; k++) {
			count++;
			int[] p = positions[k];
			int set1 = p[0] * n + p[1]; // row * colSize + col
			ds.parent[set1] = set1;// set root to be itself for each node

			for (int dir = 0; dir < 4; dir++) {
				int i = p[0] + rowSet4[dir];
				int j = p[1] + colSet4[dir];
				int set2 = i * n + j;
				if (i >= 0 && j >= 0 && i < m && j < n && ds.parent[set2] != -1) {
					if (!ds.union(set1, set2)) count--;
				}

			}
			result.add(count);
		}

		return result;
	}

	// Union operation
	public int getRoot(int[] arr, int i) {
		while (i != arr[i])
			i = arr[i];

		return i;
	}

	/* Word Search I:
	 * Given a 2D board and a "word", find if the word exists in the grid.
	 */
	public boolean wordSearchI(char[][] board, String str) {
		if (str.length() == 0 || board.length == 0 || board[0].length == 0) return false;

		int row = board.length, col = board[0].length;

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (str.charAt(0) == board[i][j]) if (dfsSearch1(board, str, i, j, 0)) return true;

		return false;
	}

	/* Word SearchII: 
	 * Given a 2D board and a "list of words" from the dictionary, find all words in the board.
	 */
	public List<String> wordSearchII1(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();
		if (words.length == 0 || board.length == 0 || board[0].length == 0) return result;

		HashSet<String> set = new HashSet<>(); // Set is used to remove the duplicate word
		for (String word : words)
			if (!set.add(word) && isExist(board, word)) result.add(word);

		result.stream().forEach(k -> System.out.print(k + " "));
		return result;
	}

	public List<String> wordSearchII2(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();

		// Build Trie datastructure
		TrieNode root = buildTrie(words);

		// dfs search
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				dfsSearch(board, root, i, j, result);

		result.stream().forEach(k -> System.out.print(k + " "));

		return result;
	}

	public List<String> wordSearchII3(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();

		// Build Trie datastructure
		TrieNode root = buildTrie2(words);

		// dfs search
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				dfsSearch2(board, root, i, j, new StringBuilder(), result);

		result.stream().forEach(k -> System.out.print(k + " "));

		return result;
	}

	public boolean dfsSearch1(char[][] board, String word, int i, int j, int index) {
		int row = board.length, col = board[0].length;

		if (i < 0 || i >= row || j < 0 || j >= col || index >= word.length()) return false;

		if (word.charAt(index) == board[i][j]) {
			if (index == word.length() - 1) return true;
			char temp = board[i][j];
			board[i][j] = '#'; // Avoid to revisit the same value

			if (dfsSearch1(board, word, i - 1, j, index + 1) || dfsSearch1(board, word, i + 1, j, index + 1)
					|| dfsSearch1(board, word, i, j - 1, index + 1) || dfsSearch1(board, word, i, j + 1, index + 1)) {
				board[i][j] = temp;
				return true;
			}
			board[i][j] = temp;
		}

		return false;
	}

	// Try this using iterative approach
	public boolean dfsSearch2(char[][] board, String str, int i, int j, int index) {
		return true;
	}

	public boolean isExist(char[][] board, String word) {
		int row = board.length, col = board[0].length;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (word.charAt(0) == board[i][j]) if (dfsSearch1(board, word, i, j, 0)) return true;
		return false;
	}

	// Insert all the words in the Trie DS
	public TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode curr = root;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if (curr.children[index] == null) curr.children[index] = new TrieNode();
				curr = curr.children[index];
			}
			curr.word = word;
		}
		return root;
	}

	public TrieNode buildTrie2(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode curr = root;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if (curr.children[index] == null) curr.children[index] = new TrieNode();
				curr = curr.children[index];
			}
			curr.isEndOfWord = true;
		}
		return root;
	}

	public void dfsSearch(char[][] board, TrieNode root, int i, int j, List<String> result) {
		int rSize = board.length, cSize = board[0].length;
		// Row & col Validation
		if (i < 0 || i >= rSize || j < 0 || j >= cSize) return;
		// Trie Validation
		char ch = board[i][j];
		if (ch == '#' || root.children[ch - 'a'] == null) return;

		root = root.children[ch - 'a'];
		if (root.word != null) {
			result.add(root.word);
			root.word = null;
		}

		board[i][j] = '#';

		dfsSearch(board, root, i, j - 1, result);
		dfsSearch(board, root, i, j + 1, result);
		dfsSearch(board, root, i - 1, j, result);
		dfsSearch(board, root, i + 1, j, result);

		board[i][j] = ch;
	}

	public void dfsSearch2(char[][] board, TrieNode root, int i, int j, StringBuilder sb, List<String> result) {
		int rSize = board.length, cSize = board[0].length;
		// Row & col Validation
		if (i < 0 || i >= rSize || j < 0 || j >= cSize) return;
		// Trie Validation
		char ch = board[i][j];
		if (ch == '#' || root.children[ch - 'a'] == null) return;

		root = root.children[ch - 'a'];

		if (root.isEndOfWord) {
			result.add(sb.toString() + ch);
			root.isEndOfWord = false;
			return;
		}

		sb.append(ch);
		board[i][j] = '#';

		dfsSearch2(board, root, i, j - 1, sb, result);
		dfsSearch2(board, root, i, j + 1, sb, result);
		dfsSearch2(board, root, i - 1, j, sb, result);
		dfsSearch2(board, root, i + 1, j, sb, result);

		board[i][j] = ch;
	}

	// Shortest Source to Destination Path
	public int shortestSourceToDestPath(int[][] matrix, int destRow, int destCol) {
		if (matrix.length == 0 || matrix[0].length == 0) return -1;

		Point src = new Point(0, 0, 0);
		Point dest = new Point(destRow, destCol, 0);
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		return shortestSourceToDestPath(matrix, visited, src, dest);
	}

	/*
	 * Find whether path exist:(Shortest distance between two cells in a matrix or grid)
	 * Given a N X N matrix (M) filled with 1 , 0 , 2 , 3 . Your task is to find whether there is a path possible from source to destination,
	 * while traversing through blank cells only. You can traverse up, down, right and left.
	 * A value of cell 1 means Source.
	 * A value of cell 2 means Destination.
	 * A value of cell 3 means Blank cell.
	 * A value of cell 0 means Blank Wall.
	 * Note : there is only single source and single destination.
	 */
	public int isPathExist(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return -1;

		int rowSize = matrix.length, colSize = matrix[0].length;
		boolean[][] visited = new boolean[rowSize][colSize];
		Point src = null, dest = null;

		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				if (matrix[i][j] == 0) {
					visited[i][j] = true;
				} else if (matrix[i][j] == 1) {
					src = new Point(i, j, 0); // Only one src & dest in entire matrix
				} else if (matrix[i][j] == 2) {
					dest = new Point(i, j, 0);
				}
			}
		}
		return shortestSourceToDestPath(matrix, visited, src, dest) == -1 ? 0 : 1;
	}

	/* Surrounded Regions: Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'. A region 
	 * is captured by flipping all 'O's into 'X's in that surrounded region.
	 *  
	 *  - Solved using, DFS, BFS & UnionFind  
	 */
	public void surroundedRegions(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return;

		int row = matrix.length, col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			if (matrix[i][0] == 'O') {
				// surroundedRegionsDFS(matrix, i, 0);
				surroundedRegionsBFS(matrix, i, 0);
			}
			if (matrix[i][col - 1] == 'O') {
				// surroundedRegionsDFS(matrix, i, col - 1);
				surroundedRegionsBFS(matrix, i, col - 1);
			}
		}

		for (int i = 0; i < col; i++) {
			if (matrix[0][i] == 'O') {
				// surroundedRegionsDFS(matrix, 0, i);
				surroundedRegionsBFS(matrix, 0, i);
			}
			if (matrix[row - 1][i] == 'O') {
				// surroundedRegionsDFS(matrix, row - 1, i);
				surroundedRegionsBFS(matrix, row - 1, i);
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 'O') matrix[i][j] = 'X';
				else if (matrix[i][j] == '#') matrix[i][j] = 'O';
			}
		}

		Utils.printMatrix(matrix);

	}

	public void surroundedRegionsDFS(char[][] matrix, int i, int j) {
		int row = matrix.length, col = matrix[0].length;

		if (!isValid(i, j, row, col) || matrix[i][j] != 'O') // or matrix[i][j] == 'X'
			return;

		matrix[i][j] = '#';

		surroundedRegionsDFS(matrix, i - 1, j); // up
		surroundedRegionsDFS(matrix, i + 1, j);// down
		surroundedRegionsDFS(matrix, i, j - 1);// left
		surroundedRegionsDFS(matrix, i, j + 1);// right
	}

	public void surroundedRegionsUF(char[][] matrix) {
		if (matrix == null || matrix.length == 0) return;

		int rows = matrix.length;
		int cols = matrix[0].length;

		// last one is dummy, all outer O are connected to this dummy
		int arrSize = rows * cols;
		DisjointSet uf = new DisjointSet(arrSize + 1);
		for (int i = 0; i <= arrSize; i++)
			uf.parent[i] = i;

		int dummyNode = arrSize;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 'O') {
					if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
						uf.union(node(i, j, cols), dummyNode);
					} else {
						if (i > 0 && matrix[i - 1][j] == 'O') uf.union(node(i, j, cols), node(i - 1, j, cols));
						if (i < rows - 1 && matrix[i + 1][j] == 'O') uf.union(node(i, j, cols), node(i + 1, j, cols));
						if (j > 0 && matrix[i][j - 1] == 'O') uf.union(node(i, j, cols), node(i, j - 1, cols));
						if (j < cols - 1 && matrix[i][j + 1] == 'O') uf.union(node(i, j, cols), node(i, j + 1, cols));
					}
				}
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (!(uf.find(node(i, j, cols)) == uf.find(dummyNode)) && matrix[i][j] == 'O') matrix[i][j] = 'X';
			}
		}

		Utils.printMatrix(matrix);
	}

	int node(int i, int j, int cols) {
		return i * cols + j;
	}

	/* Maximal Rectangle: 
	 * 		Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
	 */
	public int maximalRectangle1(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;

		int row = matrix.length, col = matrix[0].length, maxRect = 0;
		int[][] heights = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == '1') heights[i][j] = (i == 0) ? 1 : heights[i - 1][j] + 1;
			}
			maxRect = Math.max(maxRect, largestRectangleArea(heights[i]));
		}

		for (int i = 0; i < row; i++) {

		}
		return maxRect;
	}

	/* Approach2: DP Solution - Time:O(mn)
	 * The DP solution proceeds row by row, starting from the first row. Let the maximal rectangle area at row i and
	 * column j be computed by [right(i,j) - left(i,j)]*height(i,j). 
	 * All the 3 variables left, right, and height can be determined by the information from previous row, and also information
	 * from the current row. So it can be regarded as a DP solution. 
	 * The transition equations are: left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row 
	 * right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row height(i,j) = height(i-1,j) + 1,
	 * if matrix[i][j]=='1'; height(i,j) = 0, if matrix[i][j]=='0' The code is as below. 
	 * The loops can be combined for speed but I separate them for more clarity of the algorithm.
	 */
	public int maximalRectangle2(char[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[] left = new int[n];
		int[] right = new int[n];
		int[] height = new int[n];

		Arrays.fill(right, n);

		int maxA = 0;
		for (int i = 0; i < m; i++) {
			int currLeft = 0, currRight = n;

			for (int j = 0; j < n; j++) { // compute height (can do this from either side)
				if (matrix[i][j] == '1') height[j]++;
				else height[j] = 0;
			}
			for (int j = 0; j < n; j++) { // compute left (from left to right)
				if (matrix[i][j] == '1') left[j] = Math.max(left[j], currLeft);
				else {
					left[j] = 0;
					currLeft = j + 1;
				}
			}
			// compute right (from right to left)
			for (int j = n - 1; j >= 0; j--) {
				if (matrix[i][j] == '1') right[j] = Math.min(right[j], currRight);
				else {
					right[j] = n;
					currRight = j;
				}
			}

			// compute the area of rectangle (can do this from either side)
			for (int j = 0; j < n; j++)
				maxA = Math.max(maxA, (right[j] - left[j]) * height[j]);

			/*System.out.println("Left: " + Arrays.toString(left));
			System.out.println("Right: " + Arrays.toString(right));
			System.out.println("Height: " + Arrays.toString(height));*/
		}

		return maxA;
	}

	// Largest Rectangle in Histogram
	public int largestRectangleArea(int[] heights) {
		int n = heights.length;
		if (n == 0) return 0;
		Stack<Integer> stack = new Stack<>();
		int i = 0, width = 0, maxArea = 0, top = 0;

		while (i < n) {
			if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
				stack.push(i++);
			} else {
				top = stack.pop();
				width = stack.isEmpty() ? i : (i - stack.peek() - 1);
				maxArea = Math.max(maxArea, heights[top] * width);
			}
		}

		while (!stack.isEmpty()) {
			top = stack.pop();
			width = stack.isEmpty() ? i : (i - stack.peek() - 1);
			maxArea = Math.max(maxArea, heights[top] * width);
		}
		return maxArea;
	}

	// Maximal Square: Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and
	// return its area.
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;

		int row = matrix.length, col = matrix[0].length, max = 0, imax = 0, jmax = 0;
		int[][] lookup = new int[row][col];

		// update first column
		for (int i = 0; i < row; i++)
			if (matrix[i][0] == '1') lookup[i][0] = 1;

		// update first row
		for (int j = 0; j < col; j++)
			if (matrix[0][j] == '1') lookup[0][j] = 1;

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (matrix[i][j] == '1')
					lookup[i][j] = Utils.min(lookup[i - 1][j - 1], lookup[i][j - 1], lookup[i - 1][j]) + 1;
			}
		}

		// Get the max square
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				// maxSquare = Math.max(maxSquare, lookup[i][j]);
				if (max < lookup[i][j]) {
					max = lookup[i][j];
					imax = i;
					jmax = j;
				}
			}

		// Print the result
		for (int i = imax; i > imax - max; i--) {
			for (int j = jmax; j > jmax - max; j--) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		return max * max;
	}

	/* Min Cost Path(Recursive Approach):
	 *   For 2 directions -> right, down
	 * 	 For 3 directions -> right, down & diagonal
	 */
	public int minCostPath1(int[][] grid) {
		if (grid.length == 0 && grid[0].length == 0) return 0;
		return minCostPath1(grid, 0, 0);
	}

	public int minCostPath1(int[][] grid, int i, int j) {
		int r = grid.length, c = grid[0].length;
		if (!isValid(i, j, r, c)) return Integer.MAX_VALUE;
		if (i == r - 1 && j == c - 1) return grid[i][j];
		// For 2 directions
		// return grid[i][j] + Math.min(minCostPath1(grid, i + 1, j), minCostPath1(grid, i, j + 1));
		// for 3 directions
		return grid[i][j] + Utils.min(minCostPath1(grid, i + 1, j), minCostPath1(grid, i, j + 1),
				minCostPath1(grid, i + 1, j + 1));
	}

	/* Min Cost Path(DP Approach):
	 *   For 2 directions -> right, down
	 * 	 For 3 directions -> right, down & diagonal
	 */
	public int minCostPath2(int[][] grid) {
		if (grid.length == 0 && grid[0].length == 0) return 0;

		int r = grid.length, c = grid[0].length;
		int[][] lookup = new int[r][c];
		lookup[0][0] = grid[0][0];
		for (int i = 1; i < r; i++)
			lookup[i][0] = lookup[i - 1][0] + grid[i][0];

		for (int j = 1; j < c; j++)
			lookup[0][j] = lookup[0][j - 1] + grid[0][j];

		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				// For 2 directions
				// lookup[i][j] = grid[i][j] + Math.min(lookup[i][j - 1], lookup[i - 1][j]);
				// For 3 directions
				lookup[i][j] = grid[i][j] + Utils.min(lookup[i][j - 1], lookup[i - 1][j], lookup[i - 1][j - 1]);
			}
		}

		return lookup[r - 1][c - 1];
	}

	// Unique Paths: Recursive Approach
	public int uniquePaths(int m, int n) {
		if (m == 1 || n == 1) return 1;

		return uniquePaths(m - 1, n) + uniquePaths(n - 1, m);
		// Including diagonal
		// return uniquePaths(m-1,n)+uniquePaths(n-1,m)+uniquePaths(m-1. n-1);
	}

	public int uniquePathsDP(int m, int n) {
		if (m == 0 && n == 0) return 0;

		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 1;
				} else {
					// For 2 directions
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
					// For 3 directions
					// dp[i][j] = dp[i][j - 1] + dp[i - 1][j]+ dp[i - 1][j - 1];
				}
			}
		}

		return dp[m - 1][n - 1];
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int r = obstacleGrid.length, c = obstacleGrid[0].length;
		if (r == 0 && c == 0) return 0;
		return uniquePathsWithObstacles(obstacleGrid, 0, 0);
	}

	public int uniquePathsWithObstacles(int[][] a, int i, int j) {
		int r = a.length, c = a[0].length;
		if (i == r - 1 && j == c - 1 && a[i][j] == 0) return 1;
		if (!isValid(i, j, r, c)) return 0;
		if (a[i][j] == 1) // Here 1 means obstacle, 0 means empty path
			return 0;
		return uniquePathsWithObstacles(a, i + 1, j) + uniquePathsWithObstacles(a, i, j + 1);
	}

	public int uniquePathsWithObstacles2(int[][] a) {
		if (a.length == 0 && a[0].length == 0) return 0;

		int m = a.length, n = a[0].length;
		if (a[0][0] == 1 || a[m - 1][n - 1] == 1) return 0;
		int[][] dp = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// Here 1 means obstacle, 0 means empty path
				if (a[i][j] == 1) continue;

				if (i == 0 && j == 0) {
					dp[i][j] = 1;
				} else if (i == 0) {
					dp[i][j] = dp[i][j - 1];
				} else if (j == 0) {
					dp[i][j] = dp[i - 1][j];
				} else {
					// For 2 directions
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
					// For 3 directions
					// dp[i][j] = dp[i][j - 1] + dp[i - 1][j]+ dp[i - 1][j - 1];
				}
			}
		}
		return dp[m - 1][n - 1];
	}

	// Recursive approach
	public int minPathSum1(int[][] grid) {
		if (grid.length == 0 && grid[0].length == 0) return 0;
		return minPathSum(grid, 0, 0);
	}

	public int minPathSum(int[][] grid, int i, int j) {
		int r = grid.length, c = grid[0].length;
		if (i < 0 || i >= r || j < 0 || j >= c) return Integer.MAX_VALUE;

		if (i == r - 1 && j == c - 1) return grid[i][j];
		return grid[i][j] + Math.min(minPathSum(grid, i + 1, j), minPathSum(grid, i, j + 1));
	}

	// DP approach
	public int minPathSum(int[][] grid) {
		if (grid.length == 0 && grid[0].length == 0) return 0;
		int r = grid.length, c = grid[0].length;
		int[][] dp = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = grid[i][j];
				} else if (i == 0) {
					dp[i][j] = grid[i][j] + dp[i][j - 1];
				} else if (j == 0) {
					dp[i][j] = grid[i][j] + dp[i - 1][j];
				} else {
					dp[i][j] = grid[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
					// dp[i][j] = grid[i][j] + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]);
				}
			}
		}
		return dp[r - 1][c - 1];
	}

	// Matrix Product: Maximum Product Path in the Matrix

	// Recursion Approach: It works only for +ve numbers; Need modification
	public int maxPathProduct1(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) return 0;

		return maxPathProduct(m, 0, 0);
	}

	public int maxPathProduct(int[][] m, int i, int j) {
		if (i < 0 || i >= m.length || j < 0 || j >= m[0].length) return 1;

		return m[i][j] * Math.max(maxPathProduct(m, i + 1, j), maxPathProduct(m, i, j + 1));
	}

	// DP-Bottom Up Approach:
	public int maxPathProduct2(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) return 0;
		int r = m.length, c = m[0].length;
		int[][] minDp = new int[r][c], maxDp = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i == 0 && j == 0) {
					minDp[i][j] = m[i][j];
					maxDp[i][j] = m[i][j];
					continue;
				}

				int maxProduct = Integer.MIN_VALUE, minProduct = Integer.MAX_VALUE;
				if (i > 0) {
					int tempMax = Math.max(m[i][j] * minDp[i - 1][j], m[i][j] * maxDp[i - 1][j]);
					maxProduct = Math.max(maxProduct, tempMax);
					int tempMin = Math.min(m[i][j] * minDp[i - 1][j], m[i][j] * maxDp[i - 1][j]);
					minProduct = Math.min(minProduct, tempMin);
				}

				if (j > 0) {
					int tempMax = Math.max(m[i][j] * minDp[i][j - 1], m[i][j] * maxDp[i][j - 1]);
					maxProduct = Math.max(maxProduct, tempMax);
					int tempMin = Math.min(m[i][j] * minDp[i][j - 1], m[i][j] * maxDp[i][j - 1]);
					minProduct = Math.min(minProduct, tempMin);
				}
				minDp[i][j] = minProduct;
				maxDp[i][j] = maxProduct;
			}
		}
		return maxDp[r - 1][c - 1];
	}

	public int longestIncreasingPath1(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		int max = 0, r = matrix.length, c = matrix[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				max = Math.max(max, longestIncreasingPath1(matrix, i, j, Integer.MIN_VALUE));
			}
		}
		return max;
	}

	public int longestIncreasingPath1(int[][] matrix, int i, int j, int prev) {
		int r = matrix.length, c = matrix[0].length;
		if (!isValid(i, j, r, c)) return 0;
		if (prev >= matrix[i][j]) return 0;
		int curr = matrix[i][j];
		int result = 1 + Utils.max(longestIncreasingPath1(matrix, i + 1, j, curr),
				longestIncreasingPath1(matrix, i, j + 1, curr), longestIncreasingPath1(matrix, i - 1, j, curr),
				longestIncreasingPath1(matrix, i, j - 1, curr));
		// System.out.println("Result: " + result);
		return result;
	}

	// DP Approach - Memoization
	public int longestIncreasingPath2(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		int max = 0, r = matrix.length, c = matrix[0].length;
		int[][] lookup = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				max = Math.max(max, longestIncreasingPath2(matrix, i, j, Integer.MIN_VALUE, lookup));
			}
		}
		return max;
	}

	public int longestIncreasingPath2(int[][] matrix, int i, int j, int prev, int[][] lookup) {
		int r = matrix.length, c = matrix[0].length;
		// if (i < 0 || i >= r || j < 0 || j >= c)
		if (!isValid(i, j, r, c)) return 0;

		if (prev >= matrix[i][j]) return 0;
		// Lookup from the table
		if (lookup[i][j] != 0) return lookup[i][j];

		int curr = matrix[i][j];
		// Store the result in lookup table
		lookup[i][j] = 1 + Utils.max(longestIncreasingPath2(matrix, i + 1, j, curr, lookup),
				longestIncreasingPath2(matrix, i, j + 1, curr, lookup),
				longestIncreasingPath2(matrix, i - 1, j, curr, lookup),
				longestIncreasingPath2(matrix, i, j - 1, curr, lookup));
		// System.out.println("Result: " + result);
		return lookup[i][j];
	}

	// Shortest path in a Binary Maze - Solve this - > use Lee algorithm and BFS.
	public int shortestPathInBinaryMaze(int[][] grid, int si, int sj, int di, int dj) {
		int r = grid.length, c = grid[0].length;
		if (r == 0 && c == 0) return 0;
		return shortestPathInBinaryMazeUtil(grid, 0, 0, di, dj);
	}

	public int shortestPathInBinaryMazeUtil(int[][] a, int i, int j, int di, int dj) {
		int r = a.length, c = a[0].length;
		if (i == di && j == dj && a[i][j] == 1) return 1;
		if (!isValid(i, j, r, c)) return 0;
		if (a[i][j] == 0) // Here 0 means obstacle, 1 means path
			return 0;
		int result = shortestPathInBinaryMazeUtil(a, i + 1, j, di, dj)
				+ shortestPathInBinaryMazeUtil(a, i, j + 1, di, dj);
		// System.out.print(result + " ");
		return result;
	}

	public void gameOfLife(int[][] board) {
		if (board.length == 0 || board[0].length == 0) return;

		int row = board.length, col = board[0].length, count = 0;
		int[] rowSet = { 0, 0, 1, -1, -1, -1, 1, 1 };
		int[] colSet = { 1, -1, 0, 0, -1, 1, -1, 1 };
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				count = 0;
				// Count no of ones for each element
				for (int dir = 0; dir < 8; dir++) {
					int x = i + rowSet[dir];
					int y = j + colSet[dir];
					if (x >= 0 && x < row && y >= 0 && y < col && (board[x][y] & 1) == 1) count++;
				}

				// <2 die
				if (count < 2) board[i][j] &= 1;

				// same state
				if (count == 2 || count == 3) board[i][j] |= board[i][j] << 1;

				// go live
				if (count == 3) board[i][j] |= 2;

				// >3 die
				if (count > 3) board[i][j] &= 1;
			}
		}

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				board[i][j] >>= 1;

		// Print the result:
		Utils.printMatrix(board);
	}

	/************** BFS Search: Using Queue ***************/
	// This is solved using BFS
	public int shortestSourceToDestPath(int[][] matrix, boolean[][] visited, Point src, Point dest) {
		if (matrix.length == 0 || matrix[0].length == 0) return -1;
		if (matrix[src.x][src.y] != 1 || matrix[dest.x][dest.y] != 1) return -1;

		Queue<Point> queue = new LinkedList<>();
		Point currNode, adjNode;
		int row, col, rowSize = matrix.length, colSize = matrix[0].length;

		// Start from the Top left corner(0,0)
		queue.add(new Point(src.x, src.y, src.dist));
		visited[src.x][src.y] = true;

		while (!queue.isEmpty()) {
			currNode = queue.poll();
			// if current queue node matches with destination returns the distance
			if (currNode.x == dest.x && currNode.y == dest.y) return currNode.dist;

			// Traverse the node in four directions
			for (int i = 0; i < rowSet4.length; i++) {
				row = currNode.x + rowSet4[i];
				col = currNode.y + colSet4[i];

				if (row >= 0 && row < rowSize && col >= 0 && col < colSize && matrix[row][col] == 1
						&& !visited[row][col]) {
					adjNode = new Point(row, col, currNode.dist + 1);
					queue.add(adjNode);
				}
			}
		}
		return -1;
	}

	/*
	 * Find Shortest distance from a guard in a Bank: 
	 * Given a matrix that is filled with ‘O’, ‘G’, and ‘W’ where 
	 *  ‘O’ represents open space, 
	 *  ‘G’ represents guards 
	 *  ‘W’ represents walls 
	 *  in a Bank. Replace all of the O’s in the matrix with their shortest distance from a guard, without being able to
	 *  go through any walls. Also, replace the guards with 0 and walls with -1 in output matrix.
	 */

	public void shortestDistFromGuard(char[][] matrix) {
		int r = matrix.length, c = matrix[0].length;
		Queue<Point> queue = new LinkedList<>();
		int[][] result = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (matrix[i][j] == 'G') {
					queue.add(new Point(i, j, 0));
					result[i][j] = 0;
				} else if (matrix[i][j] == 'W') {
					result[i][j] = -1;
				} else {
					result[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		bfsSearch(queue, result);

		Utils.printMatrix(result);
	}

	public void bfsSearch(Queue<Point> queue, int[][] result) {
		int r = result.length, c = result[0].length;
		// BFS Search
		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			for (int dir = 0; dir < rowSet4.length; dir++) {
				int i = curr.x + rowSet4[dir];
				int j = curr.y + colSet4[dir];
				if (i >= 0 && i < r && j >= 0 && j < c && result[i][j] == Integer.MAX_VALUE) {
					result[i][j] = curr.dist + 1;
					queue.add(new Point(i, j, curr.dist + 1));
				}
			}
		}
	}

	// Try this, Queue with integer value
	public void bfsSearch2(Queue<Integer> queue, int[][] result) {
		int rowSize = result.length, colSize = result[0].length;
		int currDist = 0;
		// BFS Search
		while (!queue.isEmpty()) {
			int cord = queue.poll();
			int x = cord / colSize;
			int y = cord % colSize;
			currDist++;
			for (int dir = 0; dir < rowSet4.length; dir++) {
				int i = x + rowSet4[dir];
				int j = y + colSet4[dir];
				if (i >= 0 && i < rowSize && j >= 0 && j < colSize && result[i][j] == Integer.MAX_VALUE) {
					result[i][j] = currDist;
					queue.add(i * colSize + j);
				}
			}
		}
	}

	/*
	 * Walls and Gates: 
	 * You are given a m x n 2D grid initialized with these three possible values. 
	 *    -1 - A wall or an obstacle. 
	 *     0 - A gate. 
	 *    INF - Infinity means an empty room. 
	 * We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
	 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
	 * 
	 * Solution: Exactly same as previous one
	 * 
	 * BFS is better than DFS for this problem
	 */
	public void wallsAndGatesBFS(int[][] rooms) {
		if (rooms == null || rooms.length == 0) return;

		int r = rooms.length, c = rooms[0].length;
		Queue<Point> queue = new LinkedList<>();

		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (rooms[i][j] == 0) queue.add(new Point(i, j, 0));
		/*for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (rooms[i][j] == 0)
					queue.add(i * c + j);*/

		bfsSearch(queue, rooms);

		Utils.printMatrix(rooms);
	}

	// Solution using DFS algorithm
	public void wallsAndGatesDFS(int[][] rooms) {
		int r = rooms.length, c = rooms[0].length;

		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (rooms[i][j] == 0) {
					dfsSearch(rooms, i, j, 0);
					Utils.printMatrix(rooms);
					System.out.println();
				}

		Utils.printMatrix(rooms);
	}

	public void dfsSearch(int[][] rooms, int i, int j, int dist) {
		int rSize = rooms.length, cSize = rooms[0].length;

		if (i < 0 || i >= rSize || j < 0 || j >= cSize || rooms[i][j] < dist) return;

		rooms[i][j] = dist;

		dfsSearch(rooms, i - 1, j, dist + 1);
		dfsSearch(rooms, i + 1, j, dist + 1);
		dfsSearch(rooms, i, j - 1, dist + 1);
		dfsSearch(rooms, i, j + 1, dist + 1);
	}

	/*
	 * Problem: Shortest Distance from All Buildings:
	 * You want to build a house on an empty land which reaches all buildings in the
	 * shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2,
	 * where:
	 *   Each 0 marks an empty land which you can pass by freely. 
	 *   Each 1 marks a building which you cannot pass through. 
	 *   Each 2 marks an obstacle which you cannot pass through.
	 *   
	 *   Time  Complexity: O(m^2 * n^2);
	 *   Space Complexity: O(mn)
	 *   
	 *   Solution:
	 *      Find 
	 */
	public int shortestDistance(int[][] grid) {
		int row = grid.length;
		if (row == 0) return -1;

		int col = grid[0].length;
		int[][] hit = new int[row][col]; // How many times empty land has reached
		int[][] distanceSum = new int[row][col]; // Sum of all the buildings to that empty land
		int numOfBuilding = 0;

		// Count no of buildings
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (grid[i][j] == 1) numOfBuilding++;

		// BFS from all the buildings
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (grid[i][j] == 1)// '1' means buildings
					if (numOfBuilding != bfsSearch(grid, distanceSum, hit, i, j)) return -1;

		// Find the minimum sum of distance
		int result = Integer.MAX_VALUE;
		for (int r = 0; r < row; r++)
			for (int c = 0; c < col; c++)
				if (grid[r][c] == 0 && hit[r][c] == numOfBuilding && distanceSum[r][c] < result)
					result = distanceSum[r][c];

		System.out.println("No of hits: ");
		Utils.print2DArray(hit);

		System.out.println("\nSum of distances: ");
		Utils.print2DArray(distanceSum);

		return result == Integer.MAX_VALUE ? -1 : result;
	}

	/*
	 * Navigate from Building to all the directions and calculate the distance and also find check the buildings are connected;
	 * In below countOne should be equal to no of buildings in the grid, otherwise buildings are not connected
	 */
	public int bfsSearch(int[][] grid, int[][] distanceSum, int[][] hit, int i, int j) {
		int row = grid.length, col = grid[0].length;
		int countOne = 1; // Building count
		int currDistance = 0;

		boolean[][] visited = new boolean[row][col];
		Queue<int[]> queue = new LinkedList<int[]>();

		queue.offer(new int[] { i, j }); // Add building('1') one by one and check entire matrix
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			currDistance++;
			int levelCount = queue.size();
			// Loop over one level in the queue
			while (levelCount-- > 0) {
				int[] node = queue.poll();
				// Check in 4 directions
				for (int dir = 0; dir < rowSet4.length; dir++) {
					int x = node[0] + rowSet4[dir];
					int y = node[1] + colSet4[dir];
					if (x >= 0 && x < row && y >= 0 && y < col && !visited[x][y]) {
						visited[x][y] = true;
						if (grid[x][y] == 0) {
							distanceSum[x][y] += currDistance;
							hit[x][y]++;
							queue.add(new int[] { x, y });
						} else if (grid[x][y] == 1) {
							countOne++;
						}
					}
				}
			}
		}

		return countOne;
	}

	public void surroundedRegionsBFS(char[][] matrix, int i, int j) {
		Queue<Integer> queue = new LinkedList<>();

		int row = matrix.length, col = matrix[0].length;
		queue.add(i * col + j); // Other approach is create class with row and column, add that value in queue
		matrix[i][j] = '#';

		while (!queue.isEmpty()) {
			int top = queue.poll();
			int r = top / col;
			int c = top % col;

			if (r - 1 >= 0 && matrix[r - 1][c] == 'O') {
				matrix[r - 1][c] = '#';
				queue.add((r - 1) * col + c);
			}

			if (r + 1 < row && matrix[r + 1][c] == 'O') {
				matrix[r + 1][c] = '#';
				queue.add((r + 1) * col + c);
			}

			if (c - 1 >= 0 && matrix[r][c - 1] == 'O') {
				matrix[r][c - 1] = '#';
				queue.add(r * col + (c - 1));
			}

			if (c + 1 < col && matrix[r][c + 1] == 'O') {
				matrix[r][c + 1] = '#';
				queue.add(r * col + (c + 1));
			}
		}
	}

	/*Rat in a Maze Problem:
	 * Consider a rat placed at (0, 0) in a square matrix m[][] of order n and has to reach the destination at (n-1, n-1). 
	 * Your task is to complete the function which returns a sorted array of strings denoting all the possible directions 
	 * which the rat can take to reach the destination at (n-1, n-1). 
	 * The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right).
	 */
	public List<String> printRatMazePath(int[][] grid) {
		List<String> result = new ArrayList<>();
		dfs(grid, "", 0, 0, result);
		result.stream().forEach(k -> System.out.print(k + " "));
		return result;
	}

	public void dfs(int[][] grid, String s, int i, int j, List<String> result) {
		int rSize = grid.length, cSize = grid[0].length;

		if (i < 0 || i >= rSize || j < 0 || j >= cSize || grid[i][j] == 0 || grid[i][j] == Integer.MAX_VALUE) return;

		if (i == rSize - 1 && j == cSize - 1 && grid[i][j] == 1) result.add(s);

		grid[i][j] = Integer.MAX_VALUE;

		dfs(grid, s + "D", i + 1, j, result);
		dfs(grid, s + "L", i, j - 1, result);
		dfs(grid, s + "R", i, j + 1, result);
		dfs(grid, s + "U", i - 1, j, result);

		grid[i][j] = 1;
	}

	/*
	 * The Maze I:There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling
	 * up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the
	 * next direction. 
	 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination. 
	 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the
	 * borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
	 */
	// DFS Approach
	public boolean hasPath1(int[][] maze, int[] start, int[] destination) {
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		return hasPath1(maze, visited, start[0], start[1], destination[0], destination[1]);
	}

	private boolean hasPath1(int[][] maze, boolean[][] visited, int si, int sj, int di, int dj) {
		int rSize = maze.length, cSize = maze[0].length;
		if (visited[si][sj]) return false;
		if (si == di && sj == dj) return true;

		visited[si][sj] = true;
		for (int[] d : directions) {
			int row = si, col = sj;
			// This looping to identify the end. "it won't stop rolling until hitting a wall"
			while (row >= 0 && row < rSize && col >= 0 && col < cSize && maze[row][col] == 0) {
				row += d[0];
				col += d[1];
			}

			// New Start: row-d[0], col-d[1]; It has reduced to adjust the increased val in while loop
			row -= d[0];
			col -= d[1];

			if (hasPath1(maze, visited, row, col, di, dj)) return true;
		}

		return false;
	}

	// BFS Approach:
	public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
		int rSize = maze.length, cSize = maze[0].length;
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[rSize][cSize];

		queue.add(new Point(start[0], start[1]));
		visited[start[0]][start[1]] = true;
		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int[] d : directions) {
				int row = curr.x, col = curr.y;
				// This looping to identify the end. "it won't stop rolling until hitting a wall"
				while (row >= 0 && row < rSize && col >= 0 && col < cSize && maze[row][col] == 0) {
					row += d[0];
					col += d[1];
				}
				// New Start: row-d[0], col-d[1]; It has reduced to adjust the increased val in while loop
				row -= d[0];
				col -= d[1];

				if (row == destination[0] && col == destination[1]) return true;
				if (visited[row][col]) continue;

				visited[row][col] = true;
				queue.add(new Point(row, col));
			}

		}

		return false;
	}

	/*
	 * The Maze II: There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by
	 * rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could
	 * choose the next direction.
	 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at
	 * the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position
	 * (excluded) to the destination (included). If the ball cannot stop at the destination, return -1. 
	 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the 
	 * borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
	 */

	public int shortestDistance1(int[][] maze, int[] start, int[] destination) {
		int rSize = maze.length, cSize = maze[0].length;
		int[][] distance = new int[rSize][cSize];

		for (int i = 0; i < rSize * cSize; i++)
			distance[i / cSize][i % cSize] = Integer.MAX_VALUE;

		distance[start[0]][start[1]] = 0;
		shortestDistance1(maze, distance, start[0], start[1], destination[0], destination[1]);

		return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1
				: distance[destination[0]][destination[1]];
	}

	private void shortestDistance1(int[][] maze, int[][] distance, int si, int sj, int di, int dj) {
		if (si == di && sj == dj) return;
		int rSize = maze.length, cSize = maze[0].length;
		for (int[] d : directions) {
			int row = si, col = sj;
			// This looping to identify the end. "it won't stop rolling until hitting a wall"
			while (row >= 0 && row < rSize && col >= 0 && col < cSize && maze[row][col] == 0) {
				row += d[0];
				col += d[1];
			}

			// New Start: row-d[0], col-d[1]; It has reduced to adjust the increased val in while loop
			row -= d[0];
			col -= d[1];

			int dist = distance[si][sj] + Math.abs(row - si) + Math.abs(col - si); // currdistance +no of path increased
			if (dist < distance[row][col]) {
				distance[row][col] = dist;
				shortestDistance1(maze, distance, row, col, di, dj);
			}
		}
	}

	public int shortestDistance2(int[][] maze, int[] start, int[] destination) {
		int rSize = maze.length, cSize = maze[0].length;
		PriorityQueue<Point> queue = new PriorityQueue<>((a, b) -> (a.dist - b.dist));

		int[][] distance = new int[rSize][cSize];
		for (int i = 0; i < rSize * cSize; i++)
			distance[i / cSize][i % cSize] = Integer.MAX_VALUE;

		queue.add(new Point(start[0], start[1], 0));
		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			if (distance[curr.x][curr.y] <= curr.dist) // if found the path already skip it
				continue;

			distance[curr.x][curr.y] = curr.dist;
			for (int[] d : directions) {
				int row = curr.x, col = curr.y, dist = curr.dist;
				// This looping to identify the end. "it won't stop rolling until hitting a wall"
				while (row >= 0 && row < rSize && col >= 0 && col < cSize && maze[row][col] == 0) {
					row += d[0];
					col += d[1];
					dist++;
				}
				// New Start: row-d[0], col-d[1]; It has reduced to adjust the increased val in while loop
				row -= d[0];
				col -= d[1];
				dist--;

				queue.add(new Point(row, col, dist));
			}

		}

		return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1
				: distance[destination[0]][destination[1]];
	}

	public int timeRequiredToRottenImages(int[][] matrix) {
		int count = 0, rowSize = matrix.length, colSize = matrix[0].length;
		for (int i = 0; i < rowSize; i++)
			for (int j = 0; j < colSize; j++)
				if (matrix[i][j] == 2) {
					rottenImages(matrix, i, j);
					count++;
				}
		for (int i = 0; i < rowSize; i++)
			for (int j = 0; j < colSize; j++)
				if (matrix[i][j] == 1) return -1;

		return count;
	}

	public void rottenImages(int[][] matrix, int i, int j) {
		int rowSize = matrix.length, colSize = matrix[0].length;
		if (i < 0 || i >= rowSize || j < 0 || j >= colSize || matrix[i][j] == 0 || matrix[i][j] == -1) return;

		matrix[i][j] = -1;

		if (matrix[i][j] == 1) return;

		rottenImages(matrix, i + 1, j);
		rottenImages(matrix, i - 1, j);
		rottenImages(matrix, i, j + 1);
		rottenImages(matrix, i, j - 1);
	}

	public static final int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	/* Castle on the Grid:
	 * You are given a square grid with some cells open (.) and some blocked (X). Your playing piece can move along any
	 * row or column until it reaches the edge of the grid or a blocked cell. Given a grid, a start and an end position,
	 * determine the number of moves it will take to get to the end position.
	 */
	static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
		if (grid.length == 0) return 0;
		int rSize = grid.length, cSize = grid[0].length();
		int[][] distance = new int[rSize][cSize];
		for (int i = 0; i < rSize * cSize; i++)
			distance[i / cSize][i % cSize] = Integer.MAX_VALUE;
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		queue.add(new int[] { startX, startY, 0 }); // i,j,dist

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if (distance[curr[0]][curr[1]] <= curr[2]) continue;
			distance[curr[0]][curr[1]] = curr[2];

			for (int[] d : dir) {
				int i = curr[0], j = curr[1], dist = curr[2];
				while (i >= 0 && i < rSize && j >= 0 && j < cSize && grid[i].charAt(j) == '.') {
					if (i == goalX && j == goalY) break;
					i += d[0];
					j += d[1];
				}
				if (!(i == goalX && j == goalY)) {
					i -= d[0];
					j -= d[1];
				}
				queue.add(new int[] { i, j, dist + 1 });
			}

		}
		return distance[goalX][goalY] == Integer.MAX_VALUE ? -1 : distance[goalX][goalY];
	}

	/*
	 * Problem: Shortest Distance from All Buildings:
	 * You want to build a house on an empty land which reaches all buildings in the
	 * shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2,
	 * where:
	 *   Each 0 marks an empty land which you can pass by freely. 
	 *   Each 1 marks a building which you cannot pass through. 
	 *   Each 2 marks an obstacle which you cannot pass through.
	 *   
	 *   Time  Complexity: O(m^2 * n^2);
	 *   Space Complexity: O(mn)
	 *   
	 *   Solution:
	 *      Find 
	 */
	public int shortestDistance(String[] grid, int startX, int startY, int goalX, int goalY) {
		if (grid.length == 0) return 0;
		int rSize = grid.length, cSize = grid[0].length();
		int[][] distance = new int[rSize][cSize];
		for (int i = 0; i < rSize * cSize; i++)
			distance[i / cSize][i % cSize] = Integer.MAX_VALUE;
		PriorityQueue<Point> list = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist); // using priority queue

		list.offer(new Point(startX, startY, 0));
		while (!list.isEmpty()) {
			Point p = list.poll();
			if (distance[p.x][p.y] <= p.dist) continue; // if we have already found a route shorter
			distance[p.x][p.y] = p.dist;
			for (int i = 0; i < 4; i++) {
				int xx = p.x, yy = p.y, l = p.dist;
				while (xx >= 0 && xx < rSize && yy >= 0 && yy < cSize && grid[xx].charAt(yy) == '.') {
					xx += dir[i][0];
					yy += dir[i][1];
				}
				if (!(xx == goalX && yy == goalY)) {
					xx -= dir[i][0];
					yy -= dir[i][1];
				}
				list.offer(new Point(xx, yy, l + 1));
			}
		}
		return distance[goalX][goalY] == Integer.MAX_VALUE ? -1 : distance[goalX][goalY];
	}

	/*************** Matrix Binary Search Problems ************************/
	/* Search element in a sorted matrix
	 *   - Integers in each row are sorted from left to right.
	 *   - The first integer of each row is greater than the last integer of the previous row.
	 *   Time Complexity: O(log(m*n)) => O(logm) +O(logn)
	 */
	public boolean searchMatrixI(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

		int r = matrix.length, c = matrix[0].length;
		int l = 0, h = r * c - 1, m, i, j;

		while (l <= h) {
			m = (l + h) / 2;
			i = m / c;
			j = m % c;
			if (matrix[i][j] == target) return true;
			else if (target > matrix[i][j]) l = m + 1;
			else h = m - 1;
		}
		return false;
	}

	/* Search in a row wise and column wise sorted matrix
	 *  - Integers in each row are sorted in ascending from left to right.
	 *  - Integers in each column are sorted in ascending from top to bottom.
	 *  Time Complexity: O(m+n)
	 */
	public boolean searchMatrixII(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

		int r = matrix.length, c = matrix[0].length;
		int i = r - 1, j = 0;
		while (i >= 0 && j < c) {
			if (target == matrix[i][j]) return true;
			else if (target < matrix[i][j]) i--;
			else j++;
		}
		return false;
	}

	/*Smallest Rectangle Enclosing Black Pixels:
	 * 	An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are 
	 * connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location
	 * (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black 
	 * pixels.
	 * For example, given the following image:[ "0010",  "0110",  "0100" ]
	 * and x = 0, y = 2, Return 6.
	 */
	// Apprach1: Using Biniary Search - Time Complexity: O(nlogm+mlogn)
	public int minArea1(char[][] iImage, int x, int y) {
		int m = iImage.length, n = iImage[0].length;

		int left = searchColumns(iImage, 0, y, 0, m, true);
		int right = searchColumns(iImage, y + 1, n, 0, m, false);
		int top = searchRows(iImage, 0, x, left, right, true);
		int bottom = searchRows(iImage, x + 1, m, left, right, false);

		return (right - left) * (bottom - top);

	}

	// Find Left & Right in columns
	public int searchColumns(char[][] image, int l, int r, int t, int b, boolean opt) {
		while (l != r) {
			int row = t, mid = l + (r - l) / 2;
			while (row < b && image[row][mid] == '0')
				row++;
			if (row < b == opt) r = mid;
			else l = mid + 1;
		}

		return l;
	}

	// Find Top & Bottom in rows
	public int searchRows(char[][] image, int t, int b, int l, int r, boolean opt) {
		while (t != b) {
			int col = l, mid = t + (b - t) / 2;
			while (col < r && image[mid][col] == '0')
				col++;
			if (col < r == opt) b = mid;
			else t = mid + 1;
		}

		return t;
	}

	// Using DFS - Time ComplexityO(mn)

	private int	top;
	private int	bottom;
	private int	left;
	private int	right;
	private int	area	= 0;

	public int minArea2(char[][] image, int x, int y) {
		if (image == null || image.length == 0) { return 0; }

		/*this.top = y;
		this.bottom = y;
		this.left = x;
		this.right = x;*/
		this.top = x;
		this.bottom = x;
		this.left = y;
		this.right = y;

		int m = image.length;
		int n = image[0].length;

		boolean[][] visited = new boolean[m][n];

		minAreaHelper(image, x, y, visited);
		System.out.println(top + ", " + bottom + ", " + left + ", " + right);

		return area;
	}

	private void minAreaHelper(char[][] image, int x, int y, boolean[][] visited) {
		int m = image.length;
		int n = image[0].length;

		if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) { return; }

		if (image[x][y] == '0') { return; }

		visited[x][y] = true;

		// update the border
		top = Math.min(top, x);
		bottom = Math.max(bottom, x);

		left = Math.min(left, y);
		right = Math.max(right, y);

		int curArea = (bottom - top + 1) * (right - left + 1);
		area = Math.max(area, curArea);

		minAreaHelper(image, x, y - 1, visited);
		minAreaHelper(image, x, y + 1, visited);
		minAreaHelper(image, x - 1, y, visited);
		minAreaHelper(image, x + 1, y, visited);
	}

	// Common validations
	public boolean isValid(int r, int c, int rowSize, int colSize) {
		return (r >= 0 && r < rowSize && c >= 0 && c < colSize);
	}

}