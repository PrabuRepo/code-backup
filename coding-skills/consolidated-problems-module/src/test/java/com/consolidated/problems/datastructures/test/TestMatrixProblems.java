package com.consolidated.problems.datastructures.test;

import com.consolidated.problems.datastructures.MatrixProblems;

public class TestMatrixProblems extends MatrixProblems {
	public static void main(String[] args)

	{
		char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', 'p', '.', '.', '.', '.' },
				{ '.', '.', '.', 'R', '.', '.', '.', 'p' }, { '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', 'p', '.', '.', '.', '.' },
				{ '.', '.', '.', 'p', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.' } };
		System.out.println("Rook Problems: " + numRookCaptures(board));
	}
}
