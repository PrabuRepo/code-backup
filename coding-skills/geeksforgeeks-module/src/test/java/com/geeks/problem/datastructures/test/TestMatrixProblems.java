package com.geeks.problem.datastructures.test;

import com.common.utilities.Utils;
import com.geeks.problem.datastructures.MatrixProblems;

public class TestMatrixProblems extends MatrixProblems {
	private Integer INF = Integer.MAX_VALUE;

	public static void main(String[] args) {

		TestMatrixProblems ob = new TestMatrixProblems();

		ob.testMatrixProblems();

		// ob.testGraphProblems();

		// ob.testBSProblems();

	}

	public void testMatrixProblems() {
		System.out.println("Find the transpose of a matrix: ");
		Utils.printMatrix(transpose(mockMatrix1()));

		System.out.println("Print Matrix diagonoally: ");
		printMatrixDiagonally(mockMatrix2());

		System.out.println("Rotate matrix(Anti clock-wise) - Approach1: ");
		rotateRightMatrix1(mockMatrix2());
		System.out.println("Rotate matrix(Anti clock-wise) - Approach2: ");
		rotateRightMatrix2(mockMatrix2());

		System.out.println("Rotate matrix(clock-wise) - Approach1: ");
		rotateLeftMatrix1(mockMatrix2());
		System.out.println("Rotate matrix(clock-wise) - Approach2: ");
		rotateLeftMatrix2(mockMatrix2());

		int[][] a = { { 1, 0, 0 }, { -1, 0, 3 } };
		int[][] b = { { 7, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
		System.out.println("Matrix Multiplication: ");
		Utils.printMatrix(matrixMul(a, b));
		System.out.println("Sparse Matrix Multiplication: ");
		Utils.printMatrix(sparseMatrixMul(a, b));
		System.out.println("Bomb Enemy: " + maxKilledEnemies1(mockCharMatrix1()));

		int[][] grid = { { 1, 0, 1, 0, 1 }, { 0, 1, 0, 0, 0 }, { 0, 1, 1, 0, 0 } };
		System.out.println("Best Meeting Point: " + minTotalDistance1(grid));
		System.out.println("Best Meeting Point: " + minTotalDistance2(grid));

		int[][] matrix = { { -1, 2, 3 }, { 4, 5, -6 }, { 7, 8, 9 } };
		System.out.println("Max Path Matrix Product: " + maxPathProduct2(matrix));
	}

	public void testGraphProblems() {
		System.out.println("No of islands: " + numberOfIslandsDFS(mockAdjMatrixData5()));
		// int[][] positions = { { 0, 0 }, { 0, 1 }, { 1, 2 }, { 2, 1 } };
		int[][] positions = { { 0, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 } };
		System.out.println("No of islands: " + numIslands2(3, 3, positions));

		System.out.println(
				"Shortest Source to Destination Path: " + shortestSourceToDestPath(mockAdjMatrixData6(), 3, 4));
		System.out.println("Find whether path exist: " + isPathExist(mockAdjMatrixData7()));

		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		System.out.println("Search the word in the matrix: " + wordSearchI(board, "ADEC"));

		// String[] words = { "oath", "pea", "eat", "rain" };
		// char[][] board2 = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' }, { 'i', 'f', 'l',
		// 'v' } };
		String[] words = { "ab", "cb", "ad", "bd", "ac", "ca", "da", "bc", "db", "adcb", "dabc", "abb", "acb" };
		char[][] board2 = { { 'a', 'b' }, { 'c', 'd' } };
		System.out.println("Search the words in the matrix: ");
		// wordSearchII1(board2, words);
		// wordSearchII2(board2, words);
		wordSearchII3(board2, words);

		System.out.println("\nSurrounded Regions: ");
		surroundedRegions(mockMatrix3());
		System.out.println("\nSurrounded Regions - UF: ");
		surroundedRegionsUF(mockMatrix3());

		System.out.println("Maximal Rectangle: " + maximalRectangle1(mockMatrix4()));

		System.out.println("Maximal Rectangle: " + maximalRectangle2(mockMatrix4()));

		System.out.println("Maximal Square: " + maximalSquare(mockMatrix4()));

		System.out.println("Min Cost Path Sum: " + minCostPath1(mockMatrix5()));

		System.out.println("Min Cost Path Sum: " + minCostPath2(mockMatrix5()));

		System.out.println("Longest Increasing Path: " + longestIncreasingPath2(mockMatrix6()));

		System.out.println("Game of Life: ");
		gameOfLife(mockMatrix11());

		System.out.println("\nShortest path in binary maze: " + shortestPathInBinaryMaze(mockMatrix7(), 0, 0, 3, 4));

		System.out.println("\nShortest Distance from All Buildings: ");
		System.out.println("\nOutput: " + shortestDistance(mockMatrix8()));

		System.out.println("\nShortest distance from guard in bank: ");
		shortestDistFromGuard(mockMatrix10());

		System.out.println("Walls & Gates: ");
		System.out.println("BFS Algorithm: ");
		wallsAndGatesBFS(mockMatrix12());
		System.out.println("DFS Algorithm: ");
		wallsAndGatesDFS(mockMatrix12());

		System.out.println("Rat in Maze: ");
		printRatMazePath(mockMatrix13());

		System.out.println("\nThe Maze I(DFS): " + hasPath1(mockMatrix14(), new int[] { 0, 4 }, new int[] { 3, 2 }));
		System.out.println("\nThe Maze I(BFS): " + hasPath2(mockMatrix14(), new int[] { 0, 4 }, new int[] { 3, 2 }));

		System.out.println(
				"\nThe Maze II(DFS): " + shortestDistance1(mockMatrix14(), new int[] { 0, 4 }, new int[] { 4, 4 }));
		System.out.println(
				"\nThe Maze II(BFS): " + shortestDistance2(mockMatrix14(), new int[] { 0, 4 }, new int[] { 4, 4 }));

		int[][] matrix = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };
		System.out.println("minimum time required to rotten all the oranges: " + timeRequiredToRottenImages(matrix));
	}

	public void testBSProblems() {
		System.out.println("Smallest Rectangle Enclosing Black Pixels:  " + minArea1(mockCharMatrix2(), 0, 2));
		System.out.println("Smallest Rectangle Enclosing Black Pixels:  " + minArea2(mockCharMatrix2(), 0, 2));
	}

	private int[][] mockMatrix1() {
		int mat[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		return mat;
	}

	private int[][] mockMatrix2() {
		int mat[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		return mat;
	}

	private char[][] mockMatrix3() {
		char mat[][] = { { 'X', 'O', 'X', 'X', 'X', 'X' }, { 'X', 'O', 'X', 'X', 'O', 'X' },
				{ 'X', 'X', 'X', 'O', 'O', 'X' }, { 'O', 'X', 'X', 'X', 'X', 'X' }, { 'X', 'X', 'X', 'O', 'X', 'O' },
				{ 'O', 'O', 'X', 'O', 'O', 'O' }, };
		return mat;
	}

	private char[][] mockMatrix4() {
		char mat[][] = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '0', '1', '1', '1' } };
		return mat;
	}

	private int[][] mockMatrix5() {
		int mat[][] = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		return mat;
	}

	private int[][] mockMatrix6() {
		int mat[][] = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
		return mat;
	}

	private int[][] mockMatrix7() {
		int mat[][] = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 } };
		return mat;
	}

	private int[][] mockMatrix8() {
		int mat[][] = { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
		return mat;
	}

	private char[][] mockMatrix10() {
		char mat[][] = { { 'O', 'O', 'O', 'O', 'G' }, { 'O', 'W', 'W', 'O', 'O' }, { 'O', 'O', 'O', 'W', 'O' },
				{ 'G', 'W', 'W', 'W', 'O' }, { 'O', 'O', 'O', 'O', 'G' } };
		return mat;
	}

	private int[][] mockMatrix11() {
		int mat[][] = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
		return mat;
	}

	private int[][] mockMatrix12() {
		int[][] rooms = { { INF, -1, 0, INF }, { INF, INF, INF, -1 }, { INF, -1, INF, -1 }, { 0, -1, INF, INF } };
		return rooms;
	}

	private int[][] mockMatrix13() {
		int[][] mat = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 1, 1, 1 } };
		return mat;
	}

	private int[][] mockMatrix14() {
		int[][] mat = { { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 }, { 0, 0, 0, 0, 0 } };
		return mat;
	}

	private char[][] mockCharMatrix1() {
		char mat[][] = { { '0', 'E', '0', '0' }, { 'E', '0', 'W', 'E' }, { '0', 'E', '0', '0' } };
		return mat;
	}

	private char[][] mockCharMatrix2() {
		char mat[][] = { { '0', '0', '1', '0' }, { '0', '1', '1', '0' }, { '0', '1', '0', '0' } };
		return mat;
	}

	public int[][] mockAdjMatrixData5() {
		int[][] graph = { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 },
				{ 1, 0, 1, 0, 1 } };
		return graph;
	}

	public int[][] mockAdjMatrixData6() {
		// int[][] graph = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 1, 1 } };
		int[][] graph = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 } };
		return graph;
	}

	public int[][] mockAdjMatrixData7() {
		int[][] graph = { { 0, 3, 1, 0 }, { 3, 0, 3, 3 }, { 2, 3, 0, 3 }, { 0, 3, 3, 3 } };
		return graph;
	}
}