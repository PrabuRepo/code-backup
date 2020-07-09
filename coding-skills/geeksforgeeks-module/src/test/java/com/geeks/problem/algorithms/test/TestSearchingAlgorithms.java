package com.geeks.problem.algorithms.test;

import java.util.Arrays;

import com.geeks.problem.algorithms.SearchingAlgorithms;

public class TestSearchingAlgorithms extends SearchingAlgorithms {
	public static void main(String[] args) {

		/*
		 int[] a = { 8, 4, 56, 75, 88, 33, 23, 12, 2 };
		System.out.println("Before sorting:");
		print(a);
		heapSort(a);
		System.out.println("After sorting:");
		print(a);*/
		TestSearchingAlgorithms ob = new TestSearchingAlgorithms();
		ob.testCodingProblems();

	}

	public void testAlgorithms() {
		int[] arr = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610 };
		System.out.println("Input: ");
		print(arr);
		System.out.println("Linear Search: Element present at index:" + linearSearch(arr, 55));
		System.out.println("Binary Search: Element present at index:" + binarySearch1(arr, 55));
		System.out.println("Binary Search(Recursive): Element present at index:" + binarySearch2(arr, 610));
		System.out.println("Ternary Search: Element present at index:" + ternarySearch1(arr, 377));
		System.out.println("Ternary Search(Recursive): Element present at index:" + ternarySearch2(arr, 377));
		System.out.println("Jump Search: Element present at index:" + jumpSearch(arr, 55));
		System.out.println("Interpolation Search: Element present at index:" + interpolationSearch(arr, 55));
		System.out.println("Exponential Search: Element present at index:" + exponentialSearch(arr, 1));
		System.out.println("Fibanocci Search: Element present at index:" + fibonacciSearch(arr, 377, arr.length));
	}

	public void testCodingProblems() {
		int[] a = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println("Search in Rotated Sorted Array:" + searchInRotatedArray1(a, 4));

		int[] arr = { 5, 7, 7, 7, 7, 10 };
		System.out.println("Search Range: " + Arrays.toString(searchRange(arr, 7)));

		int[][] envelopes = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
		System.out.println(maxEnvelopes(envelopes));

		char[][] image = { { '1', '0', '0', '0' }, { '0', '1', '1', '0' }, { '0', '0', '0', '0' } };
		System.out.println("Smallest Rectangle Enclosing Black Pixels:" + minArea(image, 0, 2));

		int[] arr2 = { 3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };
		System.out.println("Searching an Infinitely Sized List: " + findElementInfiniteSizeArray(arr2, 90));

		// int[] arr2 = { 3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };
		System.out.println("Search Unknown Length Array: " + findElementInUnknownLenArray(arr2, 160));

		String[] strings = { "at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "" };
		System.out.println("Sparse Search: " + sparseSearch(strings, "car"));

		int[] houses = { 1, 2, 3, 4 };
		int[] heaters = { 1, 4 };
		System.out.println("Find the radius: " + findRadius(houses, heaters));

		int[] arr3 = { 1, 2, 8, 10, 10, 12, 19 };
		System.out.println("Floor in a Sorted Array: " + floorSearch(arr3, 6));
		System.out.println("Ceil in a Sorted Array: " + ceilSearch(arr3, 6));
	}
}
