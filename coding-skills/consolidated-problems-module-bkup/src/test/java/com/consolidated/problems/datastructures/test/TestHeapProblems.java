package com.consolidated.problems.datastructures.test;

import java.util.Arrays;

import com.consolidated.problems.datastructures.HeapProblems;

public class TestHeapProblems extends HeapProblems {
	public static void main(String[] args) {
		TestHeapProblems ob = new TestHeapProblems();
		ob.testTwoHeapProblems();
	}

	public void testTwoHeapProblems() {
		int[] nums = { 5, 2, 2, 7, 3, 7, 9, 0, 2, 3 };
		System.out.println("Sliding Window Median: " + Arrays.toString(medianSlidingWindow2(nums, 9)));
	}
}
