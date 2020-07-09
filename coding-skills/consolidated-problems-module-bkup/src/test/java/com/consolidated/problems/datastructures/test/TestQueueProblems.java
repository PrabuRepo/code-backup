package com.consolidated.problems.datastructures.test;

import java.util.Arrays;

import com.consolidated.problems.datastructures.StackAndQueueProblems;

public class TestQueueProblems extends StackAndQueueProblems {
	public static void main(String[] args) {
		TestQueueProblems ob = new TestQueueProblems();

		//long[] arr = { 6, 3, 5, 1, 12 };
		long[] arr = { 1, 2, 3, 5, 1, 13, 3 };
		System.out.println("Min Max Riddle: " + Arrays.toString(ob.riddle(arr)));

		int[] arr2 = { 10, 100, 300, 200, 1000, 20, 30 };
		System.out.println("Min Max Riddle: " + ob.maxMin(3, arr2));
	}
}
