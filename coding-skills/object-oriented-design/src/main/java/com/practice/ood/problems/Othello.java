package com.practice.ood.problems;

import java.util.ArrayList;

import com.practice.ood.lib.AssortedMethods;

public class Othello {
	public static void main(String[] args) {
		Game game = Game.getInstance();
		game.getBoard().initialize();
		game.getBoard().printBoard();
		Automator automator = Automator.getInstance();
		while (!automator.isOver() && automator.playRandom()) {
		}
		automator.printScores();
	}
}

/* A helper class to automate this game. This is just used for testing purposes.*/
class Automator {
	private Player[] players;
	private Player lastPlayer = null;
	public ArrayList<Location> remainingMoves = new ArrayList<Location>();
	private static Automator instance;

	private Automator() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Location loc = new Location(i, j);
				remainingMoves.add(loc);
			}
		}
	}

	public static Automator getInstance() {
		if (instance == null) {
			instance = new Automator();
		}
		return instance;
	}

	public void initialize(Player[] ps) {
		players = ps;
		lastPlayer = players[1];
	}

	public void shuffle() {
		for (int i = 0; i < remainingMoves.size(); i++) {
			int t = AssortedMethods.randomIntInRange(i, remainingMoves.size() - 1);
			Location other = remainingMoves.get(t);
			Location current = remainingMoves.get(i);
			remainingMoves.set(t, current);
			remainingMoves.set(i, other);
		}
	}

	public void removeLocation(int r, int c) {
		for (int i = 0; i < remainingMoves.size(); i++) {
			Location loc = remainingMoves.get(i);
			if (loc.isSameAs(r, c)) {
				remainingMoves.remove(i);
			}
		}
	}

	public Location getLocation(int index) {
		return remainingMoves.get(index);
	}

	public boolean playRandom() {
		Board board = Game.getInstance().getBoard();
		shuffle();
		lastPlayer = lastPlayer == players[0] ? players[1] : players[0];
		String color = lastPlayer.getColor().toString();
		for (int i = 0; i < remainingMoves.size(); i++) {
			Location loc = remainingMoves.get(i);
			boolean success = lastPlayer.playPiece(loc.getRow(), loc.getColumn());

			if (success) {
				System.out.println("Success: " + color + " move at (" + loc.getRow() + ", " + loc.getColumn() + ")");
				board.printBoard();
				printScores();
				return true;
			}
		}
		System.out.println("Game over. No moves found for " + color);
		return false;
	}

	public boolean isOver() {
		if (players[0].getScore() == 0 || players[1].getScore() == 0) {
			return true;
		}
		return false;
	}

	public void printScores() {
		System.out.println("Score: " + players[0].getColor().toString() + ": " + players[0].getScore() + ", "
				+ players[1].getColor().toString() + ": " + players[1].getScore());
	}
}

class Board {
	private int blackCount = 0;
	private int whiteCount = 0;
	private Piece2[][] board;

	public Board(int rows, int columns) {
		board = new Piece2[rows][columns];
	}

	public void initialize() {
		/* initial board has a grid like the following in the center:
		 *     WB
		 *     BW
		 */
		int middleRow = board.length / 2;
		int middleColumn = board[middleRow].length / 2;
		board[middleRow][middleColumn] = new Piece2(Color.White);
		board[middleRow + 1][middleColumn] = new Piece2(Color.Black);
		board[middleRow + 1][middleColumn + 1] = new Piece2(Color.White);
		board[middleRow][middleColumn + 1] = new Piece2(Color.Black);
		blackCount = 2;
		whiteCount = 2;
	}

	public boolean placeColor(int row, int column, Color color) {
		if (board[row][column] != null) {
			return false;
		}

		/* attempt to flip each of the four directions */
		int[] results = new int[4];
		results[0] = flipSection(row - 1, column, color, Direction.up);
		results[1] = flipSection(row + 1, column, color, Direction.down);
		results[2] = flipSection(row, column + 1, color, Direction.right);
		results[3] = flipSection(row, column - 1, color, Direction.left);

		/* compute how many pieces were flipped */
		int flipped = 0;
		for (int result : results) {
			if (result > 0) {
				flipped += result;
			}
		}

		/* if nothing was flipped, then it's an invalid move */
		if (flipped < 0) {
			return false;
		}

		/* flip the piece, and update the score */
		board[row][column] = new Piece2(color);
		updateScore(color, flipped + 1);
		return true;
	}

	private int flipSection(int row, int column, Color color, Direction d) {
		/* Compute the delta for the row and the column. At all times, only the row or the column
		 * will have a delta, since we're only moving in one direction at a time.
		 */
		int r = 0;
		int c = 0;
		switch (d) {
		case up:
			r = -1;
			break;
		case down:
			r = 1;
			break;
		case left:
			c = -1;
			break;
		case right:
			c = 1;
			break;
		}

		/* If out of bounds, or nothing to flip, return an error (-1) */
		if (row < 0 || row >= board.length || column < 0 || column >= board[row].length || board[row][column] == null) {
			return -1;
		}

		/* Found same color - return nothing flipped */
		if (board[row][column].getColor() == color) {
			return 0;
		}

		/* Recursively flip the remainder of the row. If -1 is returned, then we know we hit the boundary
		 * of the row (or a null piece) before we found our own color, so there's nothing to flip. Return
		 * the error code.
		 */
		int flipped = flipSection(row + r, column + c, color, d);
		if (flipped < 0) {
			return -1;
		}

		/* flip our own color */
		board[row][column].flip();
		return flipped + 1;
	}

	public int getScoreForColor(Color c) {
		if (c == Color.Black) {
			return blackCount;
		} else {
			return whiteCount;
		}
	}

	public void updateScore(Color newColor, int newPieces) {
		/* If we added x pieces of a color, then we actually removed x - 1 pieces of the other
		 * color. The -1 is because one of the new pieces was the just-placed one.
		 */
		if (newColor == Color.Black) {
			whiteCount -= newPieces - 1;
			blackCount += newPieces;
		} else {
			blackCount -= newPieces - 1;
			whiteCount += newPieces;
		}
	}

	public void printBoard() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == null) {
					System.out.print("_");
				} else if (board[r][c].getColor() == Color.White) {
					System.out.print("W");
				} else {
					System.out.print("B");
				}
			}
			System.out.println();
		}
	}
}

enum Color {
	White, Black
}

enum Direction {
	left, right, up, down
}

class Game {
	private Player[] players;
	private static Game instance;
	private Board board;
	private final int ROWS = 10;
	private final int COLUMNS = 10;

	private Game() {
		board = new Board(ROWS, COLUMNS);
		players = new Player[2];
		players[0] = new Player(Color.Black);
		players[1] = new Player(Color.White);
		Automator.getInstance().initialize(players); // used for testing
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public Board getBoard() {
		return board;
	}
}

class Location {
	private int row;
	private int column;

	public Location(int r, int c) {
		row = r;
		column = c;
	}

	public boolean isSameAs(int r, int c) {
		return row == r && column == c;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}

class Piece2 {
	private Color color;

	public Piece2(Color c) {
		color = c;
	}

	public void flip() {
		if (color == Color.Black) {
			color = Color.White;
		} else {
			color = Color.Black;
		}
	}

	public Color getColor() {
		return color;
	}
}

class Player {
	private Color color;

	public Player(Color c) {
		color = c;
	}

	public int getScore() {
		return Game.getInstance().getBoard().getScoreForColor(color);
	}

	public boolean playPiece(int row, int column) {
		return Game.getInstance().getBoard().placeColor(row, column, color);
	}

	public Color getColor() {
		return color;
	}
}
