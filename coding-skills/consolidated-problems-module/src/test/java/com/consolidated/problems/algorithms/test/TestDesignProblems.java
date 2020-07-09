package com.consolidated.problems.algorithms.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.consolidated.problems.algorithms.DesignProblems;

public class TestDesignProblems extends DesignProblems {
	public static void main(String[] args) {

	}
}

class ShortestDistance {
	public HashMap<String, List<Integer>> map;

	public ShortestDistance(String[] words) {
		map = new HashMap<>();

		for (int i = 0; i < words.length; i++) {
			if (map.containsKey(words[i])) {
				map.get(words[i]).add(i);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(words[i], list);
			}
		}
	}

	//The time complexity for shortest method is O(N^2)
	public int shortest1(String word1, String word2) {
		List<Integer> l1 = map.get(word1);
		List<Integer> l2 = map.get(word2);
		int min = Integer.MAX_VALUE;

		for (int i1 : l1)
			for (int i2 : l2)
				min = Math.min(min, Math.abs(i1 - i2));

		return min;
	}

	//Improved Method
	public int shortest2(String word1, String word2) {
		List<Integer> l1 = map.get(word1);
		List<Integer> l2 = map.get(word2);
		int min = Integer.MAX_VALUE, i1 = 0, i2 = 0;

		while (i1 < l1.size() && i2 < l2.size()) {
			min = Math.min(min, Math.abs(l1.get(i1) - l2.get(i2)));
			if (l1.get(i1) < l2.get(i2))
				i1++;
			else
				i2++;
		}

		return min;
	}
}