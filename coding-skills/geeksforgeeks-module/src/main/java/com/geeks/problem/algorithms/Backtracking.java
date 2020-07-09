package com.geeks.problem.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.common.utilities.Utils;

/*
 * What is Backtracking Programming?? 
 *  Recursion is the key in backtracking programming. As the name suggests we backtrack to find the solution. We start with one
 * possible move out of many available moves and try to solve the problem if we are able to solve the problem with the selected move then
 * we will print the solution else we will backtrack and select some other move and try to solve it. If none if the moves work out we will 
 * claim that there is no solution for the problem.
 */
public class Backtracking {

	/*************** Subsets, Subsequence, Permutations, Combination Sum, Palindrome Partitioning ****************/
	/*
	* 1.SubArray/SubString: A subarray is derived from another array by zero or more elements, it is contiguous and inherently
	* maintains the order. In general, for an array/string of size n, there are n*(n+1)/2 non-empty subarray/substring. 
	* Eg: Substrings: abc   -> {a, ab, abc, b, bc, c}; (3*4)/2 = 6
	*     SubArrays:[1,2,3] -> {1},{1,2},{1,2,3},{2},{2,3},{3}; (3*4)/2 = 6
	* 
	* 2.SubSequence: A subsequence is a sequence that can be derived from another sequence by zero or more elements, without
	* changing the order of the remaining elements. sequence of size n, we can have (2^n) sub-sequences or (2^n-1)
	* non-empty sub-sequences in total. SubSequence - need not be contiguous but needs to maintain the order
	* Eg: abc: 8 sequences -> a, ab, ac, abc, b, bc, c & empty string
	* Array SubSequences: {},{1},{1,2},{1,3},{2},{2,3},{3},{1,2,3} -> 2^3 = 8
	* 
	* 3.SubSet: A subset is any possible combination of original set. The term subset is often used for subsequence but this
	* is wrong. A subsequence always maintain the relative order of elements of the array(i.e increasing index) but there
	* is no such restriction on a subset. Subset - It is no order and no contiguous For example {3,1} is valid subset of {1,2,3,4,5}
	* - Subset: 2^n (Order doesn't matter in sets)
	* - Subsequence: 2^n (Since we keep the original ordering, this is the same.)
	* 
	* 4.Multiplication Principle of Counting:  n!
	*  
	* 5.Permutations(Ordering or Rearranging): act of arranging all the members of a set into some sequence or order, or if the set is
	*  already ordered, rearranging (reordering) its elements, a process called permuting. Number of permutations(npr)= n!/(n-r)! 
	*  where r - combinations in n elements
	* Eg: Str=abc, n=3, r=3: 3!/(3-3)!=6 permutations -> abc, acb, bac, bca, cab, cba
	* 
	* 6.Combinations(Selection): combination is a selection of items from a collection, such that (unlike permutations) the order of 
	* selection does not matter.  No of combinations(ncr) = n!/((n-r)! * r!);  where r - combinations in n elements
	* Eg: n=5, r=2 -> 5!/(5-2)!*2! -> 10
	* Eg: Str=abcde: n=5,r=2; 10 combinations: ab, ac, ad, ae, bc, bd, be, cd, ce, de (Here ab==ba, ac==ca; just combination matters, not order)
	*  
	* Note:All subarrays are subsequences All subsequences are subset. But sometimes subset and subarrays and sub sequences
	* are used interchangeably and the word contiguous is prefixed to make it more clear
	*/

	/* Backtracking Template -- General Approach:
		  All backtracking problems are composed by these three steps: choose, explore, unchoose. so for each problem, you need to know:
		    - choose what? For this problem, we choose each substring.
		    - how to explore? For this problem, we do the same thing to the remained string.
		    - unchoose Do the opposite operation of choose.
	 */

	// Subsets/Subsequence of Array: Time Complexity: O(2^n)
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		// Arrays.sort(nums); -> Here sorting in not required
		backtrackSubsets(result, new ArrayList<>(), nums, 0);
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	private void backtrackSubsets(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
		result.add(new ArrayList<>(tempList)); // Add the value

		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]); // Choose
			backtrackSubsets(result, tempList, nums, i + 1); // Explore
			tempList.remove(tempList.size() - 1); // Unchoose
		}
	}

	// Subsets II (Subset/Subsequence of array contains duplicates) :
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		backtrackSubsetsWithDup(result, new ArrayList<>(), nums, 0);
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	private void backtrackSubsetsWithDup(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
		result.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			/*skip duplicates; (i>start) -> To skip from 2nd element in duplicates; Eg:{1,1,2}; -> 2nd '1' will be skipped */
			if (i > start && nums[i] == nums[i - 1]) continue;
			tempList.add(nums[i]);
			backtrackSubsetsWithDup(result, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	// Subsets/Subsequence of a string: Time Complexity: O(2^n)
	public List<List<Character>> subSequence(String str) {
		List<List<Character>> result = new ArrayList<>();
		subSequence(result, new ArrayList<>(), str, 0);
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	public void subSequence(List<List<Character>> result, List<Character> tempList, String str, int start) {
		result.add(new ArrayList<>(tempList));
		for (int i = start; i < str.length(); i++) {
			tempList.add(str.charAt(i));
			subSequence(result, tempList, str, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	// Combinations:
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();
		backtrackCombine(n, k, 1, new ArrayList<>(), result);
		result.stream().forEach(val -> System.out.print(val + ", "));
		return result;
	}

	public void backtrackCombine(int n, int k, int start, List<Integer> eachList, List<List<Integer>> result) {
		if (eachList.size() == k) {
			result.add(new ArrayList<>(eachList));
		} else {
			for (int i = start; i <= n; i++) {
				eachList.add(i);
				backtrackCombine(n, k, i + 1, eachList, result);
				eachList.remove(eachList.size() - 1);
			}
		}
	}

	// Combination Sum : The same number can be repeated in the combination.
	public List<List<Integer>> combinationSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		backtrackCombinationSum(result, new ArrayList<>(), nums, target, 0);
		result.stream().forEach(val -> System.out.print(val + ", "));
		return result;
	}

	private void backtrackCombinationSum(List<List<Integer>> result, List<Integer> tempList, int[] nums, int target,
			int start) {
		if (target < 0) return;
		else if (target == 0) result.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				backtrackCombinationSum(result, tempList, nums, target - nums[i], i); // not i + 1 because we can reuse
																						// same elements
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	// Combination Sum-II: Number may be used once in the combination
	public List<List<Integer>> combinationSum2(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		backtrackCombinationSum2(result, new ArrayList<>(), nums, target, 0);
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;

	}

	private void backtrackCombinationSum2(List<List<Integer>> result, List<Integer> tempList, int[] nums, int target,
			int start) {
		if (target < 0) return;
		else if (target == 0) result.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < nums.length; i++) {
				if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
				tempList.add(nums[i]);
				backtrackCombinationSum2(result, tempList, nums, target - nums[i], i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	// Combination Sum-III
	public List<List<Integer>> combinationSum3(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();
		backtrackCombination3(n, k, 1, new ArrayList<>(), result);
		result.stream().forEach(data -> System.out.print(data + ", "));
		return result;
	}

	public void backtrackCombination3(int sum, int k, int start, List<Integer> eachList, List<List<Integer>> result) {
		if (eachList.size() == k && sum == 0) result.add(new ArrayList<>(eachList));
		else if (eachList.size() >= k || sum < 0) return;
		else {
			for (int i = start; i <= 9; i++) {
				eachList.add(i);
				backtrackCombination3(sum - i, k, i + 1, eachList, result);
				eachList.remove(eachList.size() - 1);
			}
		}
	}

	/*Factor Combinations:
	 * 
	 */
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> result = new ArrayList<>();
		backtrackFactorCombinations(n, 2, result, new ArrayList<>());
		result.stream().forEach(data -> System.out.print(data + ", "));
		return result;
	}

	public void backtrackFactorCombinations(int n, int start, List<List<Integer>> result, List<Integer> tempList) {
		if (n == 1) {
			if (tempList.size() > 1) result.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i <= n; i++) {
				if (n % i == 0) {
					tempList.add(i);
					backtrackFactorCombinations(n / i, i, result, tempList);
					tempList.remove(tempList.size() - 1);
				}
			}
		}
	}

	// Palindrome Partitioning :
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
		backtrackPartition(result, new ArrayList<>(), s, 0);
		result.stream().forEach(val -> System.out.print(val + ", "));
		return result;
	}

	public void backtrackPartition(List<List<String>> result, List<String> tempList, String s, int start) {
		if (start == s.length()) result.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < s.length(); i++) {
				if (isPalindrome(s, start, i)) {
					tempList.add(s.substring(start, i + 1));
					backtrackPartition(result, tempList, s, i + 1);
					tempList.remove(tempList.size() - 1);
				}
			}
		}
	}

	public boolean isPalindrome(String s, int low, int high) {
		while (low < high)
			if (s.charAt(low++) != s.charAt(high--)) return false;
		return true;
	}

	// Permutations :
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		// Arrays.sort(nums); // not necessary
		backtrackPermute(result, new ArrayList<>(), nums);
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	private void backtrackPermute(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
		if (tempList.size() == nums.length) {
			result.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (tempList.contains(nums[i])) continue; // element already exists, skip
				tempList.add(nums[i]);
				backtrackPermute(result, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	// Permutations II (contains duplicates) :
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrackPermuteUnique(list, new ArrayList<>(), nums, new boolean[nums.length]);
		list.stream().forEach(val -> System.out.print(val + ", "));
		return list;
	}

	private void backtrackPermuteUnique(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
				used[i] = true;
				tempList.add(nums[i]);
				backtrackPermuteUnique(list, tempList, nums, used);
				used[i] = false;
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	/*Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
	 represent.
	 Combination: Eg: 236 -> 3C1*3C1*3C1 -> 3*3*3 -> 27 combinations
	 */
	public List<String> letterCombinations(String num) {
		if (num.length() == 0) return new ArrayList<>();

		Map<Character, String> map = new HashMap<>();
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
		map.put('0', "");

		List<String> result = new ArrayList<>();
		letterCombinations(num, 0, map, new StringBuilder(), result);
		// result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	private void letterCombinations(String num, int index, Map<Character, String> phoneNoMap,
			StringBuilder combinations, List<String> result) {
		if (index >= num.length()) {
			result.add(combinations.toString());
		} else {
			// Get the numbers one by one from the input(num) & Get the char from map based on number
			String letters = phoneNoMap.get(num.charAt(index));
			for (int i = 0; i < letters.length(); i++) {
				combinations.append(letters.charAt(i)); // Add the char from 0th index one by one
				letterCombinations(num, index + 1, phoneNoMap, combinations, result); // Increase the index & call
				combinations.deleteCharAt(index); // Backtracking: remove the added char after print
			}
		}
	}

	/************************** Standard Problems *******************/
	/*Algorithm:
	  ----------
	   Pick a starting point.
		   while(Problem is not solved)
		      For each path from the starting point.
		         check if selected path is safe, if yes select it
		         and make recursive call to rest of the problem
		         If recursive calls returns true, 
		           then return true.
		         else 
		           undo the current move and return false.
		      End For
		 If none of the move works out, return false, NO SOLUTON.
	  */

	// Sudoku Problem
	public boolean solveSudoku(int[][] grid) {
		int n = grid.length; // Row & Col size is same

		// Find the unassigned Location
		int row = -1, col = -1;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 0) {
					row = i;
					col = j;
				}

		// All the boxes are filled; success
		if (row == -1 && col == -1) return true;

		for (int i = 1; i <= n; i++) {
			if (isSafe(grid, row, col, i)) {
				grid[row][col] = i;

				if (solveSudoku(grid)) // Success
					return true;

				// Backtracking: Failure, reset the value and try again
				grid[row][col] = 0;
			}
		}

		return false;
	}

	public boolean isAnyUnassignedLocation(int[][] grid) {
		int n = grid.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 0) return true;
		return false;
	}

	public boolean isSafe(int[][] grid, int row, int col, int num) {
		int boxSize = 3;
		if (!usedInRow(grid, row, num) && !usedInCol(grid, col, num)
				&& !usedInBox(grid, row - row % boxSize, col - col % boxSize, num))
			return true;
		return false;
	}

	public boolean usedInRow(int[][] grid, int row, int num) {
		for (int j = 0; j < grid.length; j++)
			if (grid[row][j] == num) return true;
		return false;
	}

	public boolean usedInCol(int[][] grid, int col, int num) {
		for (int i = 0; i < grid.length; i++)
			if (grid[i][col] == num) return true;
		return false;
	}

	public boolean usedInBox(int[][] grid, int boxStartRow, int boxStartCol, int num) {
		int boxSize = 3;
		for (int i = 0; i < boxSize; i++)
			for (int j = 0; j < boxSize; j++)
				if (grid[i + boxStartRow][j + boxStartCol] == num) return true;
		return false;
	}

	public void printGrid(int[][] grid) {
		int N = grid.length;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++)
				System.out.print(grid[row][col] + "  ");
			System.out.println();
		}
	}

	// The Knight’s tour problem
	public void knightTour(int N) {
		int moveCount = 1;
		int[][] table = new int[N][N];

		int[] xMove = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int[] yMove = { 1, 2, 2, 1, -1, -2, -2, -1 };

		/*Why this possibilities are not working?
		 * int[] xMove = { 2, 2, -2, -2, 1, 1, -1, -1 };
		int[] yMove = { 1, -1, 1, -1, 2, -2, 2, -2 };*/

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				table[i][j] = -1;

		table[0][0] = 0;

		knightTourUtil(0, 0, N, moveCount, xMove, yMove, table);

		printSolution(table, N);
	}

	private boolean knightTourUtil(int x, int y, int N, int moveCount, int[] xMove, int[] yMove, int[][] table) {
		int nextX, nextY;
		count++;
		if (moveCount == (N * N)) return true;

		for (int i = 0; i < N; i++) {
			nextX = x + xMove[i];
			nextY = y + yMove[i];
			if (isSafe(nextX, nextY, N, table)) {
				// System.out.println("if-> X:" + nextX + " Y:" + nextY);
				table[nextX][nextY] = moveCount;
				if (knightTourUtil(nextX, nextY, N, moveCount + 1, xMove, yMove, table)) {
					return true;
				} else {
					// System.out.println("else-> X:" + nextX + " Y:" + nextY);
					table[nextX][nextY] = -1;
				}
			}
		}

		return false;
	}

	private boolean isSafe(int nextX, int nextY, int N, int[][] table) {
		return (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && table[nextX][nextY] == -1);
	}

	private void printSolution(int[][] table, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(table[i][j] + "  ");
			}
			System.out.println();
		}
	}

	// Rat in a Maze
	public boolean solveMaze(int maze[][]) {
		int sol[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		if (solveMazeUtil(maze, 0, 0, sol) == false) {
			System.out.print("Solution doesn't exist");
			return false;
		}

		printSolution(sol);
		return true;
	}

	// A recursive utility function to solve Maze problem
	boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) {
		int N = maze.length;
		if (x == N - 1 && y == N - 1) { // Base case: When it reaches the end of row & col, return true
			sol[x][y] = 1;
			return true;
		}

		// Check if maze[x][y] is valid
		if (isSafe(maze, x, y) == true) {
			sol[x][y] = 1; // mark x,y as part of solution path

			if (solveMazeUtil(maze, x + 1, y, sol)) // Move forward in x direction
				return true;

			if (solveMazeUtil(maze, x, y + 1, sol)) // Move down in y direction
				return true;

			sol[x][y] = 0; // If none of the above movements works then BACKTRACK: un mark x,y as part of solution path
			return false;
		}

		return false;
	}

	boolean isSafe(int maze[][], int x, int y) {
		int N = maze.length;
		// if (x,y outside maze) return false
		return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
	}

	public void printSolution(int sol[][]) {
		int N = sol.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + sol[i][j] + " ");
			System.out.println();
		}
	}

	// N Queen Problem
	public boolean solveNQ() {
		int board[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		if (solveNQUtil(board, 0) == false) {
			System.out.print("Solution does not exist");
			return false;
		}

		printSolution(board);
		return true;
	}

	// A recursive utility function to solve N Queen problem
	boolean solveNQUtil(int board[][], int col) {
		int N = board.length;
		// base case: If all queens are placed then return true
		if (col >= N) return true;

		// Consider this column and try placing this queen in all rows one by one
		for (int i = 0; i < N; i++) {
			// Check if the queen can be placed on board[i][col]
			if (isSafeBoard(board, i, col)) {
				board[i][col] = 1; // Place this queen in board[i][col]

				if (solveNQUtil(board, col + 1) == true) // recur to place rest of the queens
					return true;

				// If placing queen in board[i][col] doesn't lead to a solution then remove queen from board[i][col]
				board[i][col] = 0; // BACKTRACK
			}
		}
		return false;
	}

	/* A utility function to check if a queen can be placed on board[row][col]. Note that this function is called when "col" queens
	 * are already placed in columns from 0 to col -1. So we need to check only left side for attacking queens */
	boolean isSafeBoard(int board[][], int row, int col) {
		int N = board.length;
		int i, j;

		/* Check this row on left side */
		for (i = 0; i < col; i++)
			if (board[row][i] == 1) return false;

		/* Check upper diagonal on left side */
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1) return false;

		/* Check lower diagonal on left side */
		for (i = row, j = col; j >= 0 && i < N; i++, j--)
			if (board[i][j] == 1) return false;

		return true;
	}

	void printNQueenSolution(int board[][]) {
		int N = board.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + board[i][j] + " ");
			System.out.println();
		}
	}

	static final int	SIZE		= 10;
	static final int[]	R_OFFSETS	= { 0, 1 };
	static final int[]	C_OFFSETS	= { 1, 0 };

	// Crossword Puzzle: DFS & Backtracking
	public char[][] solvePuzzle(char[][] grid, String words) {
		return search(grid, Arrays.stream(words.split(";")).collect(Collectors.toSet()), 0, 0, 0);
	}

	public char[][] search(char[][] grid, Set<String> words, int r, int c, int direction) {
		if (r == SIZE) return grid;
		if (c == SIZE) return search(grid, words, r + 1, 0, 0);
		if (direction == R_OFFSETS.length) return search(grid, words, r, c + 1, 0);

		// Count the length of the path in the grid
		int insertLength = countInsertLength(grid, r, c, direction);

		if (insertLength > 1) {
			for (String word : new ArrayList<>(words)) {
				// Validate the word can be inserted in grid
				if (canInsertWord1(grid, r, c, direction, insertLength, word)) {
					List<Integer> insertOffsets = new ArrayList<Integer>();

					for (int i = 0; i < insertLength; i++) {
						int row = r + R_OFFSETS[direction] * i;
						int col = c + C_OFFSETS[direction] * i;

						if (grid[row][col] == '-') {
							grid[row][col] = word.charAt(i);

							insertOffsets.add(i);
						}
					}
					words.remove(word);

					char[][] subResult = search(grid, words, r, c, direction + 1);

					if (subResult != null) return subResult;

					// Backtracking: Reassign the values
					words.add(word);

					for (int insertOffset : insertOffsets) {
						// Calculate row & col using prev offset
						int row = r + R_OFFSETS[direction] * insertOffset;
						int col = c + C_OFFSETS[direction] * insertOffset;

						grid[row][col] = '-';
					}
				}
			}

			return null;
		} else {
			return search(grid, words, r, c, direction + 1);
		}
	}

	public int countInsertLength(char[][] grid, int r, int c, int direction) {
		int prevRow = r - R_OFFSETS[direction];
		int prevCol = c - C_OFFSETS[direction];

		if (prevRow >= 0 && prevRow < SIZE && prevCol >= 0 && prevCol < SIZE && grid[prevRow][prevCol] != '+') return 0;

		int insertLength = 0;
		while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] != '+') {
			insertLength++;
			r += R_OFFSETS[direction];
			c += C_OFFSETS[direction];
		}
		return insertLength;
	}

	public boolean canInsertWord1(char[][] grid, int r, int c, int direction, int insertLength, String word) {
		if (word.length() != insertLength) return false;

		for (int k = 0; k < word.length(); k++) {
			int row = r + R_OFFSETS[direction] * k;
			int col = c + C_OFFSETS[direction] * k;
			if (grid[row][col] != '-' && grid[row][col] != word.charAt(k)) return false;
		}

		return true;
	}

	public boolean canInsertWord2(char[][] grid, int r, int c, int direction, int insertLength, String word) {

		return word.length() == insertLength && IntStream.range(0, word.length()).allMatch(k -> {
			int row = r + R_OFFSETS[direction] * k;
			int col = c + C_OFFSETS[direction] * k;

			return grid[row][col] == '-' || grid[row][col] == word.charAt(k);
		});
	}

	/* Generate Parentheses: write a function to generate all combinations of well-formed parentheses.
	 */
	public List<String> generateParentheses(int n) {
		if (n <= 0) return null;

		List<String> result = new ArrayList<>();
		generateParentheses(0, 0, n, result, "");
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	public void generateParentheses(int openParen, int closedParen, int n, List<String> result, String str) {
		if (openParen == n && closedParen == n) result.add(str);

		if (openParen < closedParen) // if no of open parentheses is less than closed parentheses
			return;

		if (openParen < n) // Add open parentheses one by one & increase openParen count
			generateParentheses(openParen + 1, closedParen, n, result, str + "(");

		if (closedParen < n)// Add closed parentheses one by one & increase closedParen count
			generateParentheses(openParen, closedParen + 1, n, result, str + ")");
	}

	// Using DFS
	public List<String> removeInvalidParentheses1(String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
		int count = 0;
		for (int i = last_i; i < s.length(); i++) {
			if (s.charAt(i) == par[0]) count++;
			if (s.charAt(i) == par[1]) count--;
			if (count >= 0) continue;
			for (int j = last_j; j <= i; j++)
				if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
					remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
			return;
		}

		String reversed = new StringBuilder(s).reverse().toString();
		if (par[0] == '(') // finished left to right
			remove(reversed, ans, 0, 0, new char[] { ')', '(' }); // Eg: ")("
		else // finished right to left
			ans.add(reversed);
	}

	// Using BFS
	public List<String> removeInvalidParentheses2(String s) {
		List<String> result = new ArrayList<>();
		if (s == null) return result;

		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		boolean level = false;

		queue.add(s);
		visited.add(s);
		while (!queue.isEmpty()) {
			String curr = queue.poll();
			if (isValid(curr)) {
				result.add(curr);
				level = true;
			}
			// If answer is found, make level true so that valid string of only that level are processed.
			if (level) continue;

			// generate all possible states
			for (int i = 0; i < curr.length(); i++) {
				// we only try to remove left or right paren
				if (curr.charAt(i) != '(' && curr.charAt(i) != ')') continue;

				String subStr = curr.substring(0, i) + curr.substring(i + 1);
				if (visited.add(subStr)) queue.add(subStr);
			}
		}
		return result;
	}

	private boolean isValid(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') count++;
			if (s.charAt(i) == ')') count--;
			if (count < 0) return false;
		}
		return count == 0;
	}

	// Approach1: Iterative Method; Approach 1 & 2 are same logic
	public List<String> restoreIpAddresses1(String s) {
		List<String> result = new ArrayList<>();
		for (int i = 1; i <= 3; i++)
			for (int j = 1; j <= 3; j++)
				for (int k = 1; k <= 3; k++)
					for (int l = 1; l <= 3; l++)
						if (i + j + k + l == s.length()) {
							String a = s.substring(0, i);
							if (Integer.parseInt(a) > 255 || (a.charAt(0) == '0' && a.length() > 1)) continue;

							String b = s.substring(i, i + j);
							if (Integer.parseInt(b) > 255 || (b.charAt(0) == '0' && b.length() > 1)) continue;

							String c = s.substring(i + j, i + j + k);
							if (Integer.parseInt(c) > 255 || (c.charAt(0) == '0' && c.length() > 1)) continue;

							String d = s.substring(i + j + k, i + j + k + l);
							if (Integer.parseInt(d) > 255 || (d.charAt(0) == '0' && d.length() > 1)) continue;

							result.add(a + "." + b + "." + c + "." + d);
						}

		result.stream().forEach(val -> System.out.print(val + ", "));
		return result;
	}

	// Approach2: Recursive Method; DFS Search
	public List<String> restoreIpAddresses2(String s) {
		List<String> solutions = new ArrayList<String>();
		restoreIp(s, solutions, 0, "", 0);
		solutions.stream().forEach(k -> System.out.print(k + ", "));
		return solutions;
	}

	private void restoreIp(String ip, List<String> solutions, int idx, String restored, int digits) {
		if (digits == 4 && idx == ip.length()) solutions.add(restored);
		if (digits == 4) return;

		for (int i = 1; i <= 3; i++) { // a.b.c.d -> Each block has max of 3 digits(0-255)
			if (idx + i > ip.length()) break;
			String s = ip.substring(idx, idx + i);
			if ((s.startsWith("0") && s.length() > 1) || (i == 3 && Integer.parseInt(s) > 255)) continue;
			restoreIp(ip, solutions, idx + i, restored + s + (digits == 3 ? "" : "."), digits + 1);
		}
	}

	/****************** Misc Problems **********************/
	/*
	 * Permutation Sequence: The set [1,2,3,...,n] contains a total of n! unique permutations. By listing and labeling
	 * all of the permutations in order, we get the following sequence for n = 3: "123" "132" "213" "231" "312" "321"
	 * Given n and k, return the kth permutation sequence.
	 * Example 1: Input: n = 3, k = 3; Output: "213"
	 * Time Complexity: O(n)
	 */
	public String getPermutation(int n, int k) {
		if (n == 0 || k == 0) return "";

		int[] fact = new int[n + 1];
		fact[0] = 1;
		List<Integer> list = new ArrayList<>();
		// Find the factorial & add number into the list
		for (int i = 1; i <= n; i++) {
			fact[i] = fact[i - 1] * i;
			list.add(i);
		}

		k--;
		int index = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			index = k / fact[n - i];
			sb.append(String.valueOf(list.get(index)));
			list.remove(index);
			k -= fact[n - i] * index;
		}

		return sb.toString();
	}

	/*
	 * Flip Game:  You are playing the following Flip Game with your friend: Given a string that contains only these two
	 * characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a
	 * person can no longer make a move and therefore the other person will be the winner. Write a function to compute
	 * all possible states of the string after one valid move. 
	 * For example, given s = "++++", after one move, it may become one of the following states: 
	 * [ "--++", "+--+", "++--" ] 
	 * If there is no valid move, return an empty list [].
	 */
	public List<String> generatePossibleNextMoves1(String s) {
		List<String> result = new ArrayList<>();
		int n = s.length();
		for (int i = 1; i < n; i++) {
			if (s.charAt(i - 1) == '+' && s.charAt(i) == '+') {
				result.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, n));
			}
		}

		return result;
	}

	public List<String> generatePossibleNextMoves2(String s) {
		List<String> result = new ArrayList<String>();

		if (s == null) return result;

		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == arr[i + 1] && arr[i] == '+') {
				arr[i] = '-';
				arr[i + 1] = '-';
				result.add(new String(arr));
				arr[i] = '+';
				arr[i + 1] = '+';
			}
		}

		return result;
	}

	/* Flip Game II: 
	 */
	// Approach1: Backtracking Solution
	public boolean canWin1(String s) {
		if (s == null || s.length() < 2) return false;

		for (int i = 0; i < s.length() - 1; i++) {
			if (s.startsWith("++", i)) {
				String subStr = s.substring(0, i) + "--" + s.substring(i + 2);

				if (!canWin1(subStr)) return true;
			}
		}

		return false;
	}

	// Approach2: Optimization: DP+ memory search
	public boolean canWin2(String s) {
		if (s == null || s.length() < 2) return false;
		Map<String, Boolean> map = new HashMap<>();
		return helper(s, map);
	}

	public boolean helper(String s, Map<String, Boolean> map) {
		if (map.containsKey(s)) return map.get(s);

		for (int i = 0; i < s.length() - 1; i++) {
			if (s.startsWith("++", i)) {
				String subStr = s.substring(0, i) + "--" + s.substring(i + 2);

				if (!helper(subStr, map)) {
					map.put(s, true);
					return true;
				}
			}
		}
		map.put(s, false);
		return false;
	}

	/*
	 * Word Pattern II: Given a pattern and a string str, find if str follows the same pattern.
	 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
	 * 	Examples:	pattern = "abab", str = "redblueredblue" should return true.
	 * 				pattern = "aaaa", str = "asdasdasdasd" should return true.
	 * 				pattern = "aabb", str = "xyzabcxzyabc" should return false.
	 */
	public boolean wordPatternMatch1(String pattern, String str) {
		if (pattern.length() == 0 && str.length() == 0) return true;
		if (pattern.length() == 0) return false;

		HashMap<Character, String> map = new HashMap<Character, String>();
		return helper(pattern, str, 0, 0, map);
	}

	public boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map) {
		if (i == pattern.length() && j == str.length()) return true;

		if (i >= pattern.length() || j >= str.length()) return false;

		char pat = pattern.charAt(i); // pattern char
		for (int k = j + 1; k <= str.length(); k++) {
			String sub = str.substring(j, k);
			if (!map.containsKey(pat) && !map.containsValue(sub)) {
				map.put(pat, sub);

				if (helper(pattern, str, i + 1, k, map)) return true;

				// Backtracking, remove and check from next index
				map.remove(pat);
			} else if (map.containsKey(pat) && map.get(pat).equals(sub)) {
				if (helper(pattern, str, i + 1, k, map)) return true;
			}
		}

		return false;
	}

	/* Since containsValue() method is used here, the time complexity is O(n). We can use another set to track the value 
	 * set which leads to time complexity of O(1):
	 */
	public boolean wordPatternMatch2(String pattern, String str) {
		if (pattern.length() == 0 && str.length() == 0) return true;
		if (pattern.length() == 0) return false;

		HashMap<Character, String> map = new HashMap<Character, String>();
		HashSet<String> set = new HashSet<String>();
		return helper(pattern, str, 0, 0, map, set);
	}

	public boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map,
			HashSet<String> set) {
		if (i == pattern.length() && j == str.length()) return true;

		if (i >= pattern.length() || j >= str.length()) return false;

		char c = pattern.charAt(i);
		for (int k = j + 1; k <= str.length(); k++) {
			String sub = str.substring(j, k);
			if (!map.containsKey(c) && !set.contains(sub)) {
				map.put(c, sub);
				set.add(sub);
				if (helper(pattern, str, i + 1, k, map, set)) return true;
				map.remove(c);
				set.remove(sub);
			} else if (map.containsKey(c) && map.get(c).equals(sub)) {
				if (helper(pattern, str, i + 1, k, map, set)) return true;
			}
		}

		return false;
	}

	/**************************************** Different Approach Backup - Start ******************************/
	/************************** Permutation Problems *******************/
	static int count = 0;

	// Print all permutations of a given string:Time Complexity: O(n!)
	// Approach1: Backtracking(Using String swapping)
	public void permuationOfString1(String str) {
		permutate(str, 0, str.length() - 1);
	}

	private void permutate(String str, int left, int right) {
		if (left == right) {
			System.out.print(str + " ");
		} else {
			for (int i = left; i <= right; i++) {
				str = Utils.swap(str, left, i);
				permutate(str, left + 1, right);
				str = Utils.swap(str, left, i);
			}
		}
	}

	// Approach2: Backtracking(Using Char array swapping)
	public void permuationOfString2(String str) {
		permutate(str.toCharArray(), 0, str.length() - 1);
	}

	private void permutate(char[] arr, int left, int right) {
		if (left == right) {
			System.out.print(new String(arr) + " ");
		} else {
			for (int i = left; i <= right; i++) {
				Utils.swap(arr, left, i);
				permutate(arr, left + 1, right);
				Utils.swap(arr, left, i);
			}
		}
	}

	// Print all unique permutations of a given string:Time Complexity: O(n!)
	public void uniquePermutationOfString(String str) {
		uniquePermutationOfString(str.toCharArray(), 0, str.length() - 1);
	}

	private void uniquePermutationOfString(char[] arr, int left, int right) {
		if (left == right) {
			System.out.print(new String(arr) + " ");
		} else {
			HashSet<Character> appeared = new HashSet<>();
			for (int i = left; i <= right; i++) {
				if (appeared.add(arr[i])) {
					Utils.swap(arr, left, i);
					uniquePermutationOfString(arr, left + 1, right);
					Utils.swap(arr, left, i);
				}
			}
		}
	}

	public boolean checkInclusion(String s1, String s2) {
		return checkPermutation(s1.toCharArray(), s2, 0);
	}

	public boolean checkPermutation(char[] s1, String s2, int start) {
		if (start == s1.length) {
			String str = new String(s1);
			return s2.indexOf(str) == -1 ? false : true;
		} else {
			for (int i = start; i < s1.length; i++) {
				swap(s1, i, start);
				if (checkPermutation(s1, s2, start + 1)) return true;
				swap(s1, i, start);
			}
		}
		return false;
	}

	public void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// Print all permutations of a given array:Time Complexity: O(n!)
	// Approach1: Using Recursion
	public List<List<Integer>> permutationOfArray1(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		permute(nums, 0, result);
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	public void permute(int[] nums, int s, List<List<Integer>> result) {
		if (s >= nums.length) {
			// Convert the array to list using streams
			List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.<Integer>toList());
			// Convert the array to list (Simple Iteration)
			// List<Integer> list = Utils.convertArrayToList(nums);
			result.add(list);
		} else {
			for (int i = s; i < nums.length; i++) {
				Utils.swap(nums, s, i); // Arrange
				permute(nums, s + 1, result);
				Utils.swap(nums, s, i); // Rearrange to original
			}
		}
	}

	// Approach2: Using Iteration
	public ArrayList<ArrayList<Integer>> permutationOfArray2(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		// start from an empty list
		result.add(new ArrayList<Integer>());
		for (int i = 0; i < num.length; i++) {
			// list of list in current iteration of the array num
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size() + 1; j++) {
					// + add num[i] to different locations
					l.add(j, num[i]);
					ArrayList<Integer> temp = new ArrayList<Integer>(l);
					current.add(temp);
					// System.out.println(temp);
					// - remove num[i] add
					l.remove(j);
				}
			}
			result = new ArrayList<ArrayList<Integer>>(current);
		}

		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	// Print all unique permutations(without duplicates) of a given array:Time Complexity: O(n!)
	public List<List<Integer>> uniquePermutationOfArray(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		uniquePermutationOfArray(nums, 0, result);
		// Print the result:
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	public void uniquePermutationOfArray(int[] nums, int s, List<List<Integer>> result) {
		if (s >= nums.length) {
			// Convert the array to list using streams
			List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.<Integer>toList());
			// Convert the array to list (Simple Iteration)
			// List<Integer> list = Utils.convertArrayToList(nums);
			result.add(list);
		} else {
			Set<Integer> appeared = new HashSet<>();
			for (int i = s; i < nums.length; i++) {
				if (appeared.add(nums[i])) {
					Utils.swap(nums, s, i); // swap
					uniquePermutationOfArray(nums, s + 1, result);
					Utils.swap(nums, s, i); // Rearrange to original
				}
			}
		}
	}

	/************************** Combination Problems *******************/
	public List<List<Integer>> combineRecursion(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();
		combineRecursion(n, k, 0, 1, new ArrayList<>(), result);
		return result;
	}

	public void combineRecursion(int n, int k, int value, int count, List<Integer> eachList,
			List<List<Integer>> result) {
		if (eachList.size() == k) {
			List<Integer> temp = new ArrayList<>(eachList);
			result.add(temp);
			return;
		}

		if (eachList.size() > k || value > n) return;

		eachList.add(value);
		combineRecursion(n, k, value + 1, count + 1, eachList, result);
		eachList.remove(eachList.size() - 1);
		combineRecursion(n, k, value + 1, count + 1, eachList, result);
	}

	// Combination Sum-I - Using only recursion:
	public List<List<Integer>> combinationSumRecursion(int[] a, int target) {
		List<Integer> eachList = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		// Sort the array
		Arrays.sort(a);

		csHelper(a, target, 0, eachList, result);
		result.stream().forEach(k -> System.out.print(k + ", "));
		return result;
	}

	public void csHelper(int[] a, int target, int i, List<Integer> eachList, List<List<Integer>> result) {
		if (i >= a.length) return;
		if (target < 0) return;
		if (target == 0) {
			ArrayList<Integer> temp = new ArrayList<Integer>(eachList);
			result.add(temp);
			return;
		}
		eachList.add(a[i]);
		csHelper(a, target - a[i], i, eachList, result);
		eachList.remove(eachList.size() - 1);
		csHelper(a, target, i + 1, eachList, result);
	}

	/************************ Subsequence Problems ********************/
	// Print the binary numbers
	public void binaryNumbers(int n) {
		int[] arr = new int[n];
		printBinary(arr, n - 1);
	}

	public void printBinary(int[] arr, int n) {
		if (n < 0) {
			System.out.print(Arrays.toString(arr) + ", ");
			return;
		}

		arr[n] = 0;
		printBinary(arr, n - 1);

		arr[n] = 1;
		printBinary(arr, n - 1);
	}

	// Subsequence of a string
	public void subSequence2(String str) {
		int[] arr = new int[str.length()];

		subSequence2(str, arr, str.length() - 1);
	}

	public void subSequence2(String str, int[] arr, int n) {
		if (n < 0) {
			printSubSequence(arr, str);
			return;
		}

		arr[n] = 0;
		subSequence2(str, arr, n - 1);

		arr[n] = 1;
		subSequence2(str, arr, n - 1);
	}

	public void printSubSequence(int[] arr, String str) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) System.out.print(str.charAt(i) + "");
		}
		System.out.print(", ");
	}
	/**************************************** Different Approach Backup - End ******************************/
}