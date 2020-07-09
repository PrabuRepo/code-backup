package com.geeksforgeeks.mustdocoding.problems;

/*
Divide and Conquer algorithm solves a problem using following three steps:
	1. Divide: Break the given problem into subproblems of same type.
	2. Conquer: Recursively solve these subproblems
	3. Combine: Appropriately combine the answers
	Eg: Binary Search, Quick Sort, Merge Sort, Strassen’s Algorithm
 */
public class L_DivideAndConquerAlgorithms {
	int bin_search(int A[], int left, int right, int k) {
		if (left <= right) {
			int mid = (left + right) / 2;
			if (A[mid] == k) {
				return mid;
			} else if (k < A[mid]) {
				return bin_search(A, left, mid - 1, k);
			} else {
				return bin_search(A, mid + 1, right, k);
			}
		}
		return -1;
	}

	public void quickSort(int[] a) {
		qSort(a, 0, a.length - 1);
	}

	public void qSort(int[] a, int low, int high) {
		if (low < high) {
			int mid = partition(a, low, high);
			qSort(a, low, mid - 1);
			qSort(a, mid + 1, high);
		}
	}

	int partition(int arr[], int low, int high) {
		int j = low;
		int pivot = arr[high];

		for (int i = low; i < high; i++) {
			if (arr[i] < pivot) {
				swap(arr, i, j);
				j++;
			}
		}
		swap(arr, j, high);
		return j;
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	public void mergeSort(int[] arr, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}

	void merge(int arr[], int l, int m, int r) {
		int leftSize = m - l + 1;
		int rightSize = r - m;
		int[] left = new int[leftSize];
		int[] right = new int[rightSize];
		int index = 0;

		for (int i = l; i <= m; i++)
			left[index++] = arr[i];

		index = 0;
		for (int i = m + 1; i <= r; i++)
			right[index++] = arr[i];

		int i = l, j = 0, k = 0;
		while (j < leftSize && k < rightSize) {
			if (left[j] < right[k]) {
				arr[i++] = left[j++];
			} else {
				arr[i++] = right[k++];
			}
		}

		while (j < leftSize)
			arr[i++] = left[j++];

		while (k < rightSize)
			arr[i++] = right[k++];

	}

	// Find the element that appears once in a sorted array: Divide & Alg
	void search(int[] arr, int low, int high) {
		// Base cases
		if (low > high)
			return;

		if (low == high) {
			System.out.println("The required element is: " + arr[low]);
			return;
		}

		// Find the middle point
		int mid = (low + high) / 2;

		/*If mid is even and element next to mid is same as mid, then output element lies on right side, else on left side*/
		if (mid % 2 == 0) {
			if (arr[mid] == arr[mid + 1])
				search(arr, mid + 2, high);
			else
				search(arr, low, mid);
		} else { // If mid is odd
			if (arr[mid] == arr[mid - 1])
				search(arr, mid + 1, high);
			else
				search(arr, low, mid - 1);
		}
	}

	public void printArray(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	public static void main(String[] args) {
		int[] a = { 7, 9, 12, 1, 6, 5, 2 };
		L_DivideAndConquerAlgorithms ob = new L_DivideAndConquerAlgorithms();
		ob.quickSort(a);

		ob.mergeSort(a);
		// ob.printArray(a);

		int arr[] = { 1, 1, 3, 3, 4, 5, 5, 7, 7, 8, 8 };
		ob.search(arr, 0, arr.length - 1);

	}
}
