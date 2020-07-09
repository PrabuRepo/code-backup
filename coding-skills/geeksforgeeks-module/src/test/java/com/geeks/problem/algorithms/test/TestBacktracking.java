package com.geeks.problem.algorithms.test;

import java.util.ArrayList;
import java.util.List;

import com.common.utilities.Utils;
import com.geeks.problem.algorithms.Backtracking;

public class TestBacktracking extends Backtracking {

	public static void main(String[] args) {
		TestBacktracking ob = new TestBacktracking();

		// ob.testGeneralApproachProblems();

		ob.testStandardProblems();

		// ob.testPermutationProblems();

		// ob.testCombinationProblems();

		// ob.testSubsequenceProblems();

		// ob.testMiscProblems();

	}

	public void testGeneralApproachProblems() {
		int[] nums = { 1, 2, 3 };
		int[] nums2 = { 1, 1, 2 };

		System.out.println("Subset: ");
		subsets(nums);

		System.out.println("\nsubsets with Duplicates: ");
		subsetsWithDup(nums2);

		System.out.println("\n Subsequence of a string: ");
		subSequence("abc");

		System.out.println("\nCombinations: ");
		combine(6, 5);

		int[] a = { 10, 1, 2, 7, 6, 1, 5, 3 };
		System.out.println("\nCombination Sum I: ");
		combinationSum(a, 4);
		System.out.println("\nCombination Sum II: ");
		combinationSum2(a, 8);
		System.out.println("\nCombination Sum III: ");
		combinationSum3(9, 3);
		System.out.println("\nFactor Combinations: ");
		getFactors(256);

		System.out.println("\nPalindrome Partitioning: ");
		partition("aabb");

		System.out.println("\nPermutations: ");
		permute(nums);
		System.out.println();
		permuteUnique(nums2);

		System.out.println("\nLetter Combinations: ");
		letterCombinations("234");
	}

	public void testStandardProblems() {
		System.out.println("Solve Sudoku:");
		int[][] grid = mockMatrix1();
		System.out.println("Input:");
		printGrid(grid);
		System.out.println("" + solveSudoku(grid));
		printGrid(grid);

		System.out.println("\nKnight Tour Problem:");
		knightTour(8);

		System.out.println("\nRat in maze: ");
		solveMaze(mockMatrix2());

		System.out.println("\nN-Queen Problem: ");
		solveNQ();

		System.out.println("\nCross word Puzzle: ");
		String words = "POLAND;LHASA;SPAIN;INDIA";
		char[][] matrix = mockCharMatrix1();
		Utils.printMatrix(matrix);
		char[][] result = solvePuzzle(matrix, words);
		System.out.println("Result: ");
		Utils.printMatrix(result);

		System.out.println("\nGenerate Parentheses: ");
		generateParentheses(3);

		System.out.println("\nRemove Invalid Parentheses - DFS: " + removeInvalidParentheses1("()())()"));
		System.out.println("Remove Invalid Parentheses - BFS: " + removeInvalidParentheses2(")("));

		System.out.println("\nGenerate IP Address: ");
		restoreIpAddresses2("25525511135");

	}

	public void testPermutationProblems() {
		System.out.println("Print all permutations of a given string:");
		String str = "abcd";
		System.out.println("Approach1: ");
		permuationOfString1(str);
		System.out.println("\nApproach2: ");
		permuationOfString2(str);
		System.out.println("\nPrint all unique permutations of a given string:");
		uniquePermutationOfString("abbc");

		int[] arr = { 1, 2, 3, 4 };
		int[] arr2 = { 1, 2, 2, 4 };
		System.out.println("\nPrint all permutations of a given array:");
		System.out.println("Approach1: ");
		permutationOfArray1(arr);
		System.out.println("\nApproach2: ");
		permutationOfArray2(arr);
		System.out.println("\nPrint all unique permutations of a given array:");
		uniquePermutationOfArray(arr2);
	}

	public void testCombinationProblems() {
		System.out.println("Binary numbers:");
		binaryNumbers(4);

		System.out.println("Subsequence of a given string: ");
		subSequence("abc");
	}

	public void testSubsequenceProblems() {
		System.out.println("Binary numbers:");
		binaryNumbers(4);

		System.out.println("\nSubsequence of a given string: ");
		subSequence("abc");
	}

	public void testMiscProblems() {

		System.out.println("Flip Game: " + generatePossibleNextMoves1("++++++"));

		System.out.println("Flip Game: " + canWin1("++++"));
		System.out.println("Flip Game: " + canWin2("++++"));

		System.out.println("Word Pattern II: " + wordPatternMatch1("aaaa", "asdasdasdasd"));
	}

	public int[][] mockMatrix1() {
		int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		return grid;
	}

	public int[][] mockMatrix2() {
		int maze[][] = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
		return maze;
	}

	public char[][] mockCharMatrix1() {
		char[][] matrix = new char[10][10];
		List<String> list = new ArrayList<>();

		list.add("++++++++++");
		list.add("+------+++");
		list.add("+++-++++++");
		list.add("+++-++++++");
		list.add("+++-----++");
		list.add("+++-++-+++");
		list.add("++++++-+++");
		list.add("++++++-+++");
		list.add("++++++-+++");
		list.add("++++++++++");

		for (int i = 0; i < list.size(); i++)
			for (int j = 0; j < list.get(i).length(); j++)
				matrix[i][j] = list.get(i).charAt(j);

		return matrix;
	}

}