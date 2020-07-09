package com.gaylemcdowell.problems;

import java.util.ArrayList;
import java.util.List;

public class Ch08RecursionAndDP {

	public static void main(String[] args) {
		Ch08RecursionAndDP ob = new Ch08RecursionAndDP();
		System.out.println("calculate the number of ways of representing n cents: " + ob.coinChanges(25));

		System.out.println("Pemutations without dups: ");
		List<String> result = ob.permWoDups("abcd");
		result.stream().forEach(k -> System.out.print(k + ", "));
	}

	/*
	 * 1.Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time.
	 * Implement a method to count how many possible ways the child can run up the stairs.
	 */

	/*
	 * 2.Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot can only
	 * move in two directions, right and down, but certain cells are "off limits" such that the robot cannot step on them. Design 
	 * an algorithm to find a path for the robot from the top left to the bottom right.
	 */

	/*
	 * 3.Magic Index: A magic index in an array A [e ... n -1] is defined to be an index such that A[i] = i. Given a sorted array 
	 * of distinct integers, write a method to find a magic index, if one exists, in array A. 
	 * FOLLOW UP What if the values are not distinct?
	 */

	/*
	 * 4.Power Set: Write a method to return all subsets of a set.
	 */

	/*
	 * 5.Recursive Multiply: Write a recursive function to multiply two positive integers without using the * operator. You can 
	 * use addition, subtraction, and bit shifting, but you should minimize the number of those operations.
	 */

	/*
	 * 6.Towers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of different sizes which 
	 * can slide onto any tower. The puzzle starts with disks sorted in ascending order of size from top to bottom 
	 * (Le., each disk sits on top of an even larger one). You have the following constraints:
	 *    (1) Only one disk can be moved at a time.
	 *    (2) A disk is slid off the top of one tower onto another tower.
	 *    (3) A disk cannot be placed on top of a smaller disk.
	 *    Write a program to move the disks from the first tower to the last using stacks.
	 */

	/*
	 * 7.Permutations without Dups: Write a method to compute all permutations of a string of unique characters.
	 */
	ArrayList<String> permWoDups(String str) {
		if (str == null)
			return null;

		ArrayList<String> permutations = new ArrayList<String>();
		if (str.length() == 0) { // base case
			permutations.add("");
			return permutations;
		}

		char first = str.charAt(0); // get the first char
		String remainder = str.substring(1); // remove the first char
		ArrayList<String> words = permWoDups(remainder);
		for (String word : words) {
			for (int j = 0; j <= word.length(); j++) {
				String s = insertCharAt(word, first, j);
				permutations.add(s);
			}
		}
		return permutations;
	}

	// Insert char c at index i in word.
	String insertCharAt(String word, char c, int i) {
		String start = word.substring(0, i);
		String end = word.substring(i);
		return start + c + end;
	}

	/*
	 * 8.Permutations with Dups: Write a method to compute all permutations of a string whose characters are not necessarily 
	 * unique. The list of permutations should not have duplicates.
	 */

	/*
	 * 9.Parens: Implement an algorithm to print all valid (e.g., properly opened and closed) combinations of n pairs of parentheses.
	 * EXAMPLE 
	 * Input: 3
	 * Output: « () ) ) J «) (», «» () J () ( (», () () ()
	 */

	/*
	 * 10.Paint Fill: Implement the "paint nil" function that one might see on many image editing programs. That is, given 
	 * a screen (represented by a two-dimensional array of colors), a point, and a new color, fill in the surrounding area 
	 * until the color changes from the original color.
	 */

	/*
	 * 11.Coins: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent), 
	 * write code to calculate the number of ways of representing n cents.
	 */

	public int coinChanges(int n) {
		int[] cents = { 1, 5, 10, 25 };
		return coinChanges(cents, n, cents.length - 1);
	}

	public int coinChanges(int[] a, int n, int i) {
		if (i < 0 || n < 0)
			return 0;
		if (n == 0)
			return 1;

		return coinChanges(a, n - a[i], i) + coinChanges(a, n, i - 1);
	}

	/*
	 * 12.Eight Queens: Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board so that none of them 
	 * share the same row, column, or diagonal. In this case, "diagonal" means all diagonals, not just the two that bisect the board.
	 */

	/*
	 * 13.Stack of Boxes: You have a stack of n boxes, with widths Wi ' heights hi ' and depths di . The boxes cannot be rotated 
	 * and can only be stacked on top of one another if each box in the stack is strictly larger than the box above it in width, 
	 * height, and depth. Implement a method to compute the height of the tallest possible stack. The height of a stack is the 
	 * sum of the heights of each box.
	 */

	/*
	 * 14.Boolean Evaluation: Given a boolean expression consisting of the symbols e (false), 1 (true), & (AND), I (OR), and" (XOR),
	 * and a desired boolean result value result, implement a function to count the number of ways of parenthesizing the expression
	 * such that it evaluates to result. 
	 * EXAMPLE 
	 * countEval("1"eleI1"J false) -) 2
	 * countEval("e&e&e&l"lle"J true) -) 1e
	 */
}
