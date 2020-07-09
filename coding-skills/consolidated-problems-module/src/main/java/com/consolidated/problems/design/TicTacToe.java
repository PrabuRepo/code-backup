package com.consolidated.problems.design;

/* Design Tic-Tac-Toe:
 * Design a Tic-tac-toe game that is played between two players on a n x n grid. You may assume the following rules: A
 * move is guaranteed to be valid and is placed on an empty block. Once a winning condition is reached, no more moves is
 * allowed. A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 */
public class TicTacToe {
	public static void main(String[] args) {
		System.out.println("Tic Tac Toe Game: ");
		TicTacToe1 ob1 = new TicTacToe1(3);
		System.out.print(ob1.move(0, 0, 1) + ", ");
		System.out.print(ob1.move(0, 2, 2) + ", ");
		System.out.print(ob1.move(2, 2, 1) + ", ");
		System.out.print(ob1.move(1, 1, 2) + ", ");
		System.out.print(ob1.move(2, 0, 1) + ", ");
		System.out.print(ob1.move(1, 0, 2) + ", ");
		System.out.print(ob1.move(2, 1, 1) + ", ");
	}
}

/* Efficient Approach: Move Time Complexity-O(1)
 * O(1) Time, O(n) extra space. Set the player to 1 and -1; create rows[n] and cols[n] arrays and variables diagonal, 
 * anti_diagonal to record this row/column/diagonal .
 */
class TicTacToe1 {
	private int[]	rows;
	private int[]	cols;
	private int		diagonal;
	private int		antiDiagonal;

	public TicTacToe1(int n) {
		this.rows = new int[n];
		this.cols = new int[n];
	}

	public int move(int i, int j, int player) {
		int size = rows.length;
		int toAdd = (player) == 1 ? 1 : -1;

		rows[i] += toAdd;
		cols[i] += toAdd;

		if (i == j) diagonal += toAdd;
		if (i + j == size + 1) antiDiagonal += toAdd;

		if (Math.abs(rows[i]) == size || Math.abs(cols[i]) == size || Math.abs(diagonal) == size
				|| Math.abs(antiDiagonal) == size)
			return player;

		return 0;
	}
}
