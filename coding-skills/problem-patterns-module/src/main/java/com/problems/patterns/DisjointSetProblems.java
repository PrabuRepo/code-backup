package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.common.utilities.DisjointSet;

public class DisjointSetProblems {
	private static final int[] rowSet4 = { -1, 1, 0,
			0 };
	private static final int[] colSet4 = { 0, 0, -1,
			1 };

	// Using DisJoint Set/Union-Find DS
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0
				|| grid[0].length == 0)
			return 0;

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
						if (x < 0 || x >= m || y < 0
								|| y >= n
								|| grid[x][y] == '0')
							continue;

						if (union(parent, i * n + j,
								x * n + y))
							count--;
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
	public List<Integer> numIslands2(int m, int n,
			int[][] positions) {
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
				if (i >= 0 && j >= 0 && i < m && j < n
						&& ds.parent[set2] != -1) {
					if (!ds.union(set1, set2)) count--;
				}

			}
			result.add(count);
		}
		return result;
	}
}
