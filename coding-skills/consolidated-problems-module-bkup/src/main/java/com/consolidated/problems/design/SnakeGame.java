package com.consolidated.problems.design;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class SnakeGame {

}

class SnakeGame1 {

	/**
	 * Initialize your data structure here.
	 * 
	 * @param width
	 *            - screen width
	 * @param height
	 *            - screen height
	 * @param food
	 *            - A list of food positions E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the
	 *            second is at [1,0].
	 */
	HashSet<Integer>	set;
	Deque<Integer>		body;
	int					score;
	int[][]				food;
	int					foodIndex;
	int					width;
	int					height;

	public SnakeGame1(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;
		set = new HashSet<Integer>();
		set.add(0);
		body = new LinkedList<Integer>();
		body.addLast(0);
	}

	/**
	 * Moves the snake.
	 * 
	 * @param direction
	 *            - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
	 * @return The game's score after the move. Return -1 if game over. Game over when snake crosses the screen boundary
	 *         or bites its body.
	 */
	public int move(String direction) {
		if (score == -1) { return -1; }
		// calculate head's position
		int rowHead = body.peekFirst() / width;
		int colHead = body.peekFirst() % width;
		// move
		switch (direction) {
			case "U":
				rowHead--;
				break;
			case "D":
				rowHead++;
				break;
			case "L":
				colHead--;
				break;
			case "R":
				colHead++;
				break;
		}
		set.remove(body.peekLast()); // since the snake moved, it is no longer in the old tail's position.
		int headPos = rowHead * width + colHead; // recaluate new head position
		// Game Over
		if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(headPos))
			return score = -1;
		// add head
		set.add(headPos);
		body.addFirst(headPos);
		// eating food, keep tail, add head
		if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
			set.add(body.peekLast()); // old tail does not change, so add it back to set
			foodIndex++;
			return ++score;
		}
		// if didnt eat food, remove old tail,just normal move
		body.removeLast();
		return score;
	}
}