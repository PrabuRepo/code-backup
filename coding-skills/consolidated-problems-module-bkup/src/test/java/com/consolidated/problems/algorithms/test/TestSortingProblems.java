package com.consolidated.problems.algorithms.test;

import java.util.Arrays;

import com.consolidated.problems.algorithms.SortingAlgorithms;

public class TestSortingProblems extends SortingAlgorithms {
	public static void main(String[] args) {
		TestSortingProblems ob = new TestSortingProblems();

		ob.testRearragmentProblems();
	}

	public void testRearragmentProblems() {
		int[] arr1 = { -12, 11, 13, -4, 6, -7, 5, -3, -6 };
		System.out.println("Move all negative numbers to beginning and positive to end: ");
		System.out.println("Before: " + Arrays.toString(arr1));
		moveNegNumFront1(arr1);
		System.out.println("After: " + Arrays.toString(arr1));

		int[] arr2 = { -12, 11, 13, -4, 6, -7, 5, -3, -6 };
		System.out.println("Rearrange positive and negative values in an array - Not Maintaining Order: ");
		System.out.println("Before: " + Arrays.toString(arr2));
		rearrangePosAndNegNumbers1(arr2);
		System.out.println("After: " + Arrays.toString(arr2));

		int[] arr3 = { -12, 11, 13, -4, 6, -7, 5, -3, -6 };
		System.out.println("Rearrange positive and negative values in an array - Maintaining Order: ");
		System.out.println("Before: " + Arrays.toString(arr3));
		rearrangePosAndNegNumbers2(arr3);
		System.out.println("After: " + Arrays.toString(arr3));
	}
}
