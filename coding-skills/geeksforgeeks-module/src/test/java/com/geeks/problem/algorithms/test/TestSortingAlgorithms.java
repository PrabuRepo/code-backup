package com.geeks.problem.algorithms.test;

import java.util.Arrays;

import com.common.model.ListNode;
import com.geeks.problem.algorithms.SortingAlgorithms;

public class TestSortingAlgorithms extends SortingAlgorithms {
	public static void main(String[] args) {
		TestSortingAlgorithms ob = new TestSortingAlgorithms();

		// ob.testSortingAlgorithms();

		ob.testSortingProblems();
	}

	public void testSortingAlgorithms() {
		System.out.println("Before Sorting: ");
		int[] a = mockData1();
		display(a);

		System.out.println("\nSorting Algorithms:");
		testBubbleSort(a);

		testSelectionSort(a);

		testInsertionSort(a);

		testMergeSort(a);

		testQuickSort(a);

		testHeapSort(a);

		testCountSort(a);

		System.out.println("\nMerge Sort for Linked List:");
		System.out.println("Enter no of elements to be inserted:");
		ListNode head = null;
		for (int i = 0; i < a.length; i++)
			head = insertLL(head, a[i]);
		head = mergeSortForLL(head);
		displayLL(head);
	}

	public void testSortingProblems() {
		int[] arr = { 10, 90, 49, 2, 1, 5, 23 };
		System.out.println("Sort array in wave form or Peaks & Valleys(approach-1): ");
		waveForm1(arr);
		System.out.println(Arrays.toString(arr));

		int[] arr2 = { 10, 90, 49, 2, 1, 5, 23 };
		System.out.println("Sort array in wave form or Peaks & Valleys(approach-2): ");
		waveForm2(arr2);
		System.out.println(Arrays.toString(arr2));

		System.out.println("Wiggele Sort-II: ");
		int[] nums = { 6, 13, 5, 4, 5, 2 };
		wiggleSort2(nums);
		System.out.println(Arrays.toString(nums));

		int[] arr3 = { 10, 90, 49, 2, 1, 5, 23 };
		System.out.println("Alternative Sorting: ");
		alternativeSorting(arr3);

		int[] a1 = { 2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8 };
		int[] a2 = { 2, 1, 8, 3 };
		System.out.println("Relative Sorting: ");
		relativeSorting(a1, a2);

	}

	public void testBubbleSort(int[] a) {
		System.out.println("\nBubble Sort(Iterative):");
		bubbleSort(a);
		display(a);

		System.out.println("\nBubble Sort(Recursive):");
		a = mockData1();
		bubbleSortRecursive(a);
		display(a);
	}

	public void testSelectionSort(int[] a) {
		System.out.println("\nSelection Sort:");
		selectionSort(a);
		display(a);
	}

	public void testInsertionSort(int[] a) {
		System.out.println("\nInsertion Sort(Iterative):");
		insertionSort(a);
		display(a);

		System.out.println("\nInsertion Sort(Recursive):");
		a = mockData1();
		insertionSortRecursive(a);
		display(a);
	}

	public void testMergeSort(int[] a) {
		System.out.println("\nMerge Sort:");
		mergeSort(a);
		display(a);
	}

	public void testQuickSort(int[] a) {
		System.out.println("\nQuick Sort:");
		quickSort(a);
		display(a);
		// quickSort1(a, 0, a.length - 1); //Check this??
	}

	public void testHeapSort(int[] a) {
		System.out.println("\nHeap Sort:");
		heapSort(a);
		display(a);
	}

	public void testCountSort(int[] a) {
		System.out.println("\nCounting Sort(Integer):");
		countingSort(a);
		display(a);

		System.out.println("\nCounting Sort(Character):");
		char arr[] = { 'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's' };
		countingSort(arr);
		System.out.println("Sorted Array:");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i]);
		System.out.println();
	}

	public int[] mockData1() {
		int[] a = { 43, 12, 53, 17, 56, 8, 68, 32, 27, 49, 7 };
		return a;
	}
}
