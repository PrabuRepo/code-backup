package com.consolidated.problems.datastructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.common.model.Point;
import com.common.utilities.DisjointSet;

public class MatrixProblems {
	// Below set follows the order: {up, down,left, right}
	private static final int[]		rowSet4		= { -1, 1, 0, 0 };
	private static final int[]		colSet4		= { 0, 0, -1, 1 };
	private static final int[][]	directions	= { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	// Left,Right,Up,Down & diagonal
	private static final int[]	rowSet8	= { -1, 1, 0, 0, -1, -1, 1, 1 };
	private static final int[]	colSet8	= { 0, 0, -1, 1, -1, 1, -1, 1 };

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
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		int rSize = image.length, cSize = image[0].length;
		if (sc < 0 || sc >= rSize || sr < 0 || sr >= cSize || image[sr][sc] == newColor) return image;

		floodFill(image, newColor, image[sr][sc], sr, sc);

		return image;
	}

	// Solution using DFS
	public void floodFill(int[][] matrix, int newColor, int oldColor, int i, int j) {
		int rSize = matrix.length, cSize = matrix[0].length;
		// Validation
		if (i < 0 || i >= rSize || j < 0 || j >= cSize || matrix[i][j] != oldColor) return;

		// Functionality based on the problem
		matrix[i][j] = newColor;

		// Traverse 4 directions
		for (int dir = 0; dir < 4; dir++)
			floodFill(matrix, newColor, oldColor, i + rowSet4[dir], j + colSet4[dir]);
	}

	// Find the number of islands
	// Approach using DFS & BFS
	public int numIslands1(char[][] grid) {
		if (grid.length == 0) return 0;

		int row = grid.length, col = grid[0].length, count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					// numIslandsDFS(grid, i, j);
					numIslandsBFS(grid, i, j);
					count++;
				}
			}
		}

		return count;
	}

	// Using DFS(left/right/up/down) movement; Time Complexity - O(m*n)
	public void numIslandsDFS(char[][] matrix, int i, int j) {
		int rSize = matrix.length, cSize = matrix[0].length;
		// Validation
		if (i < 0 || i >= rSize || j < 0 || j >= cSize || matrix[i][j] == '0') return;

		// Functionality based on the problem
		matrix[i][j] = '0'; // Apply the new color

		// Traverse 4 directions
		for (int dir = 0; dir < 4; dir++)
			numIslandsDFS(matrix, i + rowSet4[dir], j + colSet4[dir]);
	}

	// Using BFS
	public void numIslandsBFS(char[][] grid, int i, int j) {
		Queue<Integer> queue = new LinkedList<>();
		int rowSize = grid.length, colSize = grid[0].length;

		// Add the first value in the queue
		int index = i * colSize + j;
		queue.add(index); // Other approach is create class with row and column, add that value in queue
		grid[i][j] = '0';

		// Traverse the data in level by level
		while (!queue.isEmpty()) {
			int top = queue.poll();
			int r = top / colSize;
			int c = top % colSize;
			// Check 4 directions of r & c
			for (int dir = 0; dir < 4; dir++) {
				i = r + rowSet4[dir];
				j = c + colSet4[dir];
				if (i < 0 || i >= rowSize || j < 0 || j >= colSize || grid[i][j] == '0') continue;

				grid[i][j] = '0';
				queue.add((i) * colSize + j);
			}
		}
	}

	// Using DisJoint Set/Union-Find DS
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

		int m = grid.length;
		int n = grid[0].length;
		int count = 0;

		// Create Parent array to build the disjoint set
		int[] parent = new int[m * n];
		// Count all the land or 1 in the array
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == '1') {
					parent[i * n + j] = i * n + j; // rowIndex * colSize + colIndex
					count++;
				}

		// Group the connected components(meaning 1's in the 4 directions)
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					// Check in four directions
					for (int dir = 0; dir < 4; dir++) {
						int x = i + rowSet4[dir];
						int y = j + colSet4[dir];
						if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') continue;

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
	 * Connected Cell in a Grid: Given m X n matrix, find and print the number of cells in the largest region in the matrix
	 */
	public int maxRegion(int[][] grid) {
		int rowSize = grid.length, colSize = grid[0].length, max = 0;
		for (int i = 0; i < rowSize; i++)
			for (int j = 0; j < colSize; j++)
				if (grid[i][j] == 1) max = Math.max(max, dfs(grid, i, j));

		return max;
	}

	public int dfs(int[][] grid, int i, int j) {
		int rowSize = grid.length, colSize = grid[0].length;
		if (i < 0 || i >= rowSize || j < 0 || j >= colSize || grid[i][j] == 0) return 0;
		grid[i][j] = 0;
		int count = 1;

		for (int dir = 0; dir < 8; dir++) {
			count += dfs(grid, i + rowSet8[dir], j + colSet8[dir]);
		}

		return count;
	}

	/* Surrounded Regions:
	 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'. A region is
	 * captured by flipping all 'O's into 'X's in that surrounded region.
	 */
	public void solve1(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return;

		int row = matrix.length, col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			if (matrix[i][0] == 'O') {
				surroundedRegionsDFS(matrix, i, 0);
				// surroundedRegionsBFS(matrix, i, 0);
			}
			if (matrix[i][col - 1] == 'O') {
				surroundedRegionsDFS(matrix, i, col - 1);
				// surroundedRegionsBFS(matrix, i, col - 1);
			}
		}

		for (int i = 0; i < col; i++) {
			if (matrix[0][i] == 'O') {
				surroundedRegionsDFS(matrix, 0, i);
				// surroundedRegionsBFS(matrix, 0, i);
			}
			if (matrix[row - 1][i] == 'O') {
				surroundedRegionsDFS(matrix, row - 1, i);
				// surroundedRegionsBFS(matrix, row-1, i);
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 'O') matrix[i][j] = 'X';
				else if (matrix[i][j] == '#') matrix[i][j] = 'O';
			}
		}

	}

	// Using DFS
	public void surroundedRegionsDFS(char[][] matrix, int i, int j) {
		int rSize = matrix.length, cSize = matrix[0].length;
		// Validation
		if (i < 0 || i >= rSize || j < 0 || j >= cSize || matrix[i][j] != 'O') return;

		// Functionality based on the problem
		matrix[i][j] = '#';

		// Traverse 4 directions
		for (int dir = 0; dir < 4; dir++)
			surroundedRegionsDFS(matrix, i + rowSet4[dir], j + colSet4[dir]);
	}

	// Using BFS
	public void surroundedRegionsBFS(char[][] matrix, int i, int j) {
		Queue<Integer> queue = new LinkedList<>();
		int rowSize = matrix.length, colSize = matrix[0].length;

		// Add the first value in the queue
		int index = i * colSize + j;
		queue.add(index); // Other approach is create class with row and column, add that value in queue
		matrix[i][j] = '#';

		// Traverse the data in level by level
		while (!queue.isEmpty()) {
			int top = queue.poll();
			int r = top / colSize;
			int c = top % colSize;
			// Check 4 directions of r & c
			for (int dir = 0; dir < 4; dir++) {
				i = r + rowSet4[dir];
				j = c + colSet4[dir];
				if (i < 0 || i >= rowSize || j < 0 || j >= colSize || matrix[i][j] != 'O') continue;

				matrix[i][j] = '#';
				queue.add((i) * colSize + j);
			}
		}
	}

	// Solution using Union-Find(Disjoint set)
	public void solve2(char[][] matrix) {

		if (matrix == null || matrix.length == 0) return;

		int rowSize = matrix.length;
		int colSize = matrix[0].length;

		// last one is dummy, all outer O are connected to this dummy
		int arrSize = rowSize * colSize;
		DisjointSet uf = new DisjointSet(arrSize + 1);
		for (int i = 0; i <= arrSize; i++)
			uf.parent[i] = i;

		int dummyNode = arrSize;

		for (int i = 0; i < rowSize; i++)
			for (int j = 0; j < colSize; j++)
				if (matrix[i][j] == 'O') {
					int set1 = i * colSize + j;
					if (i == 0 || i == rowSize - 1 || j == 0 || j == colSize - 1) { // For the 4 edges
						uf.union(set1, dummyNode);
					} else {
						for (int dir = 0; dir < 4; dir++) {
							int r = i + rowSet4[dir];
							int c = j + colSet4[dir];
							if (r < 0 || r >= rowSize || c < 0 || c > colSize || matrix[r][c] != 'O') continue;

							int set2 = r * colSize + c;
							uf.union(set1, set2);
						}
					}
				}

		for (int i = 0; i < rowSize; i++)
			for (int j = 0; j < colSize; j++) {
				int set = i * colSize + j;
				if (!(uf.find(set) == uf.find(dummyNode)) && matrix[i][j] == 'O') {
					matrix[i][j] = 'X';
				}
			}
	}

	/* Pacific Atlantic Water Flow:
	 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the
	 * "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and
	 * bottom edges. Water can only flow in four directions (up, down, left, or right) from a cell to another one with
	 * height equal or lower. Find the list of grid coordinates where water can flow to both the Pacific and Atlantic
	 * ocean.
	 */
	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> result = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;

		int rSize = matrix.length, cSize = matrix[0].length;
		boolean[][] pacific = new boolean[rSize][cSize];
		boolean[][] atlantic = new boolean[rSize][cSize];

		// Pacific - Left & Atlantic - Right
		for (int i = 0; i < rSize; i++) {
			dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
			dfs(matrix, atlantic, Integer.MIN_VALUE, i, cSize - 1);
		}

		// Pacific - Top & Atlantic - Bottom
		for (int j = 0; j < cSize; j++) {
			dfs(matrix, pacific, Integer.MIN_VALUE, 0, j);
			dfs(matrix, atlantic, Integer.MIN_VALUE, rSize - 1, j);
		}

		for (int i = 0; i < rSize; i++)
			for (int j = 0; j < cSize; j++)
				if (pacific[i][j] && atlantic[i][j]) result.add(new int[] { i, j });

		return result;
	}

	int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public void dfs(int[][] matrix, boolean[][] visited, int height, int i, int j) {
		int rSize = matrix.length, cSize = matrix[0].length;

		if (i < 0 || i >= rSize || j < 0 || j >= cSize || visited[i][j] || height > matrix[i][j]) return;

		visited[i][j] = true;

		for (int[] d : dir)
			dfs(matrix, visited, matrix[i][j], i + d[0], j + d[1]);

	}

	/* Find whether path exist:
	 * Given a N X N matrix (M) filled with 1 , 0 , 2 , 3 . Your task is to find whether there is a path possible from
	 * source to destination, while traversing through blank cells only. You can traverse up, down, right and left. 
	 * A value of cell 1 means Source. A value of cell 2 means Destination. A value of cell 3 means Blank cell. 
	 * A value of cell 0 means Blank Wall.
	 */
	public int isPathExist(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return -1;

		int rowSize = matrix.length, colSize = matrix[0].length;
		boolean[][] visited = new boolean[rowSize][colSize];
		Point src = null, dest = null;

		// Get the src & dest node
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				if (matrix[i][j] == 0) { // Mark 0 as visited node
					visited[i][j] = true;
				} else if (matrix[i][j] == 1) { // 1 - Source Node
					src = new Point(i, j, 0);
				} else if (matrix[i][j] == 2) { // 2 - Destination Node
					dest = new Point(i, j, 0);
				}
			}
		}

		// Find the distance between src & dest
		return shortestSourceToDestPathBFS(matrix, visited, src, dest) == -1 ? 0 : 1;
	}

	// Solution using BFS
	public int shortestSourceToDestPathBFS(int[][] matrix, boolean[][] visited, Point src, Point dest) {
		if (matrix.length == 0 || matrix[0].length == 0) return -1;
		Queue<Point> queue = new LinkedList<>();
		Point currNode, adjNode;
		int row, col, rowSize = matrix.length, colSize = matrix[0].length;

		// Start from the Top left corner(0,0)
		queue.add(new Point(src.x, src.y, src.dist));
		visited[src.x][src.y] = true;

		while (!queue.isEmpty()) {
			currNode = queue.poll();
			row = currNode.x;
			// if current queue node matches with destination returns the distance
			if (currNode.x == dest.x && currNode.y == dest.y) return currNode.dist;

			// Traverse the node in four directions
			for (int i = 0; i < 4; i++) {
				row = currNode.x + rowSet4[i];
				col = currNode.y + colSet4[i];
				if (row >= 0 && row < rowSize && col >= 0 && col < colSize && !visited[row][col]) {
					adjNode = new Point(row, col, currNode.dist + 1);
					queue.add(adjNode);
					visited[row][col] = true;
				}
			}
		}
		return -1;
	}

	/* Shortest Source to Destination Path:
	 * Given a boolean 2D matrix (0-based index), find whether there is path from (0,0) to (x,y) and if there is one
	 * path, print the minimum no of steps needed to reach it, else print -1 if the destination is not reachable. You
	 * may move in only four direction ie up, down, left and right. The path can only be created out of a cell if its
	 * value is 1.
	 */
	public int shortestSourceToDestPath(int[][] matrix, int destRow, int destCol) {
		Point src = new Point(0, 0, 0);
		Point dest = new Point(destRow, destCol, 0);
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		return shortestSourceToDestPathBFS(matrix, visited, src, dest);
	}

	/* Rat in a Maze Problem:
	 * Consider a rat placed at (0, 0) in a square matrix m[][] of order n and has to reach the destination at (n-1,
	 * n-1). Your task is to complete the function which returns a sorted array of strings denoting all the possible
	 * directions which the rat can take to reach the destination at (n-1, n-1). The directions in which the rat can
	 * move are 'U'(up), 'D'(down), 'L' (left), 'R' (right).
	 */
	public ArrayList<String> ratInMaze(int[][] grid, int n) {
		ArrayList<String> result = new ArrayList<>();
		ratInMazeDFS(grid, "", 0, 0, result);
		// result.stream().forEach(k -> System.out.print(k + " "));
		return result;
	}

	// Solution using DFS
	public void ratInMazeDFS(int[][] grid, String s, int i, int j, ArrayList<String> result) {
		int rSize = grid.length, cSize = grid[0].length;

		if (i < 0 || i >= rSize || j < 0 || j >= cSize || grid[i][j] == 0 || grid[i][j] == Integer.MAX_VALUE) return;

		if (i == rSize - 1 && j == cSize - 1 && grid[i][j] == 1) result.add(s);

		grid[i][j] = Integer.MAX_VALUE;

		ratInMazeDFS(grid, s + "D", i + 1, j, result);
		ratInMazeDFS(grid, s + "L", i, j - 1, result);
		ratInMazeDFS(grid, s + "R", i, j + 1, result);
		ratInMazeDFS(grid, s + "U", i - 1, j, result);

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
	public boolean hasPath1(int[][] maze, int[] start, int[] destination) {
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		return hasPath1(maze, visited, start[0], start[1], destination[0], destination[1]);
	}

	// Using DFS Algorithm
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

	/* Castle on the Grid:
	 * You are given a square grid with some cells open (.) and some blocked (X). Your playing piece can move along any
	 * row or column until it reaches the edge of the grid or a blocked cell. Given a grid, a start and an end position,
	 * determine the number of moves it will take to get to the end position.
	 */
	public static int minimumMoves(char[][] board, int a, int b, int c, int d) {
		// a,b --> c,d
		Queue<Point> que = new LinkedList<Point>();
		Queue<Point> nextlevel;
		que.offer(new Point(c, d));
		int s = 0, n = board.length;
		while (!que.isEmpty()) {
			nextlevel = new LinkedList<Point>();
			while (!que.isEmpty()) {
				Point cur = que.poll();
				int x = cur.x, y = cur.y;
				if (a == x && b == y) return s;
				// steps[x][y] = s;
				for (int j = y - 1; j >= 0; j--) { // left
					if (board[x][j] == 'X') break;
					nextlevel.offer(new Point(x, j));
					board[x][j] = 'X';
				}
				for (int j = y + 1; j < n; j++) { // right
					if (board[x][j] == 'X') break;
					nextlevel.offer(new Point(x, j));
					board[x][j] = 'X';
				}
				for (int i = x - 1; i >= 0; i--) { // up
					if (board[i][y] == 'X') break;
					nextlevel.offer(new Point(i, y));
					board[i][y] = 'X';
				}
				for (int i = x + 1; i < n; i++) {
					if (board[i][y] == 'X') break;
					nextlevel.offer(new Point(i, y));
					board[i][y] = 'X';
				}
			}
			s += 1;
			que = nextlevel;
		}
		return s;
	}

	/*
	 * Given a 2D board and a list of words from the dictionary, find all words in the board.
	 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally 
	 * or vertically neighboring. The same letter cell may not be used more than once in a word.
	 * 	Example:
	 * 	Input: words = ["oath","pea","eat","rain"] and board =
	 * 	[ ['o','a','a','n'],
	 *    ['e','t','a','e'],
	 *    ['i','h','k','r'],
	 *    ['i','f','l','v']
	 *  ]
	 *  Output: ["eat","oath"]
	 */
	// Approach1: Using DFS -> Time Complexity: O(len*m*n) where len- no of words, m- row size, n-colSize
	public List<String> findWords1(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();
		if (words.length == 0 || board.length == 0 || board[0].length == 0) return result;

		HashSet<String> set = new HashSet<>(); // Set is used to remove the duplicate word
		for (String word : words)
			if (set.add(word) && isExist(board, word)) result.add(word);

		result.stream().forEach(k -> System.out.print(k + " "));
		return result;
	}

	public boolean isExist(char[][] board, String word) {
		int row = board.length, col = board[0].length;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (word.charAt(0) == board[i][j]) if (dfsSearch(board, word, i, j, 0)) return true;
		return false;
	}

	public boolean dfsSearch(char[][] board, String word, int i, int j, int index) {
		int row = board.length, col = board[0].length;
		if (i < 0 || i >= row || j < 0 || j >= col || index >= word.length()) return false;

		if (word.charAt(index) == board[i][j]) {
			if (index == word.length() - 1) return true;
			char temp = board[i][j];
			board[i][j] = '#'; // Avoid to revisit the same value

			if (dfsSearch(board, word, i - 1, j, index + 1) || dfsSearch(board, word, i + 1, j, index + 1)
					|| dfsSearch(board, word, i, j - 1, index + 1) || dfsSearch(board, word, i, j + 1, index + 1)) {
				board[i][j] = temp;
				return true;
			}
			board[i][j] = temp;
		}

		return false;
	}

	public static int numRookCaptures(char[][] board) {
		if (board.length == 0 || board[0].length == 0) return 0;

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if (board[i][j] == 'R') return dfs(board, i, j);

		return 0;
	}

	public static int dfs(char[][] board, int row, int col) {
		int m = board.length, n = board[0].length;
		int count = 0;
		for (int[] dir : directions) {
			int i = row + dir[0], j = col + dir[1];
			while (i >= 0 && i < m && j >= 0 && j < n && board[i][j] == '.') {
				i += dir[0];
				j += dir[1];
			}

			if (i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'p') count++;
		}

		return count;
	}

	/*********************** Others *************/

	public int islandPerimeter1(int[][] grid) {
		int perimeter = 0;
		if (grid.length == 0 || grid[0].length == 0) return perimeter;

		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				if (grid[i][j] == 1) perimeter += findCellPerimeter(grid, i, j);

		return perimeter;
	}

	private int findCellPerimeter(int[][] grid, int i, int j) {
		int cellPerimeter = 0;
		if (i == 0 || grid[i - 1][j] == 0) cellPerimeter++;
		if (i == grid.length - 1 || grid[i + 1][j] == 0) cellPerimeter++;
		if (j == 0 || grid[i][j - 1] == 0) cellPerimeter++;
		if (j == grid[0].length - 1 || grid[i][j + 1] == 0) cellPerimeter++;
		return cellPerimeter;
	}

}
