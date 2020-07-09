package com.problems.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SweepLinePatterns {

	//	The Skyline Problem
	/* Skyline problem without any additional object 
	 * SkyLine Problem using Priority Queue: TimeComplexity:  O(n^2) (Here n - no of building points)
	 * SkyLine Problem using TreeMap: TimeComplexity: O(nlogn);
	 */
	public List<int[]> getSkyline1(int[][] buildings) {
		List<int[]> points = new ArrayList<>();
		List<int[]> result = new ArrayList<>();
		for (int i = 0; i < buildings.length; i++) {
			points.add(new int[] { buildings[i][0],
					buildings[i][2] });
			points.add(new int[] { buildings[i][1],
					-buildings[i][2] });
		}
		// Using lambda expression
		Collections.sort(points, (a, b) -> {
			if (a[0] != b[0]) return a[0] - b[0];
			return b[1] - a[1];
		});

		// Solution using Priority Queue - O(n^2) operation
		PriorityQueue<Integer> queue = new PriorityQueue<>(
				Collections.reverseOrder());
		queue.add(0);
		int prev = 0, curr = 0;
		for (int[] point : points) {
			if (point[1] < 0) {
				queue.remove(-point[1]);
			} else {
				queue.add(point[1]);
			}
			curr = queue.peek();
			if (prev != curr) {
				result.add(new int[] { point[0], curr });
				prev = curr;
			}
		}
		return result;
	}

	public List<int[]> getSkyline2(int[][] buildings) {
		List<int[]> points = new ArrayList<>();
		List<int[]> result = new ArrayList<>();
		for (int i = 0; i < buildings.length; i++) {
			points.add(new int[] { buildings[i][0],
					buildings[i][2] });
			points.add(new int[] { buildings[i][1],
					-buildings[i][2] });
		}
		// Using lambda expression
		Collections.sort(points, (a, b) -> {
			if (a[0] != b[0]) return a[0] - b[0];
			return b[1] - a[1];
		});
		// Solution using Tree Map - O(nlogn) operation
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(0, 1);
		int prev = 0, curr = 0;
		for (int[] point : points) {
			if (point[1] < 0) {
				int value = map.get(-point[1]);
				if (value > 1)
					map.put(-point[1], value - 1);
				else map.remove(-point[1]);
			} else {
				map.put(point[1],
						map.getOrDefault(point[1], 0) + 1);
			}
			curr = map.lastKey();
			if (prev != curr) {
				result.add(new int[] { point[0], curr });
				prev = curr;
			}
		}
		return result;
	}

	//	Rectangle Area II
	//	Time Intersection
	//	Number of Airplanes in the Sky
	//	Perfect Rectangle

}
