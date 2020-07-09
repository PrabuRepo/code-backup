package com.basic.algorithms;

/*
Divide and Conquer algorithm solves a problem using following three steps:
	1. Divide: Break the given problem into subproblems of same type.
	2. Conquer: Recursively solve these subproblems
	3. Combine: Appropriately combine the answers
	Eg: Binary Search, Quick Sort, Merge Sort, Strassen’s Algorithm
 */
public class DivideAndConquer {

	public static int binarySearch(int[] a, int x) {
		return binarySearch(a, 0, a.length - 1, 610);
	}

	private static int binarySearch(int[] a, int l, int h, int x) {
		int index = -1;
		if (l > h) return index;

		int m = (l + h) / 2;
		if (a[m] == x) index = m;
		else if (x < a[m]) index = binarySearch(a, l, m - 1, x);
		else index = binarySearch(a, m + 1, h, x);

		return index;
	}

	public void mergeSort() {
		SortingAlgorithms ob = new SortingAlgorithms();
		// MergeSort for array
		// ob.mergeSort(a);

		// MergeSort for linked list
		// ob.mergeSortForLL(head);
	}

	public void quickSort() {
		SortingAlgorithms ob = new SortingAlgorithms();
		// QuickSort for array
		// ob.quickSort(a);
	}

	// Calculate pow(x, n)
	public int pow(int m, int n) {
		if (n == 0) return 1;
		return m * pow(m, n - 1);
	}

	// Better Approach: Time Complexity: o(n)
	public int pow2(int m, int n) {
		if (n == 0) return 1;
		else if (n % 2 == 0) return pow2(m, n / 2) * pow2(m, n / 2);
		else return m * pow2(m, n / 2) * pow2(m, n / 2);
	}

	// Time Complexity of optimized solution: O(logn)
	static int pow3(int m, int n) {
		int temp;
		if (n == 0) return 1;
		temp = pow3(m, n / 2);
		if (n % 2 == 0) return temp * temp;
		else return m * temp * temp;
	}

}
