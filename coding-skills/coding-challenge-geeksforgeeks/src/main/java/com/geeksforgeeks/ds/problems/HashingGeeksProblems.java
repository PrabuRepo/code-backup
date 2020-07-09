package com.geeksforgeeks.ds.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HashingGeeksProblems {

	boolean isSubSet1(int[] a1, int[] a2) {
		// brute force approach
		return true;
	}

	// Quick Sort & Binary Search; Time Complexity: O(mLogm + m2);
	// Quick Sort is sued and worst case time complexity of Quick Sort is O(m^2)
	boolean isSubSet2(int[] a1, int[] a2) {
		boolean flag = true;
		quickSort(a1, 0, a1.length - 1);
		for (int i = 0; i < a2.length; i++) {
			if (!binarySearch(a1, 0, a1.length - 1, a2[i])) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// HeapSort & Merge Sort; Time Complexity: O(mLogm + mLogm);
	boolean isSubSet3(int[] a1, int[] a2) {
		boolean flag = true;
		heapSort(a1);
		heapSort(a2);
		int m = a1.length;
		int n = a2.length;
		int i = 0, j = 0;
		while (i < m && j < n) {
			if (a1[i] == a2[j]) {
				i++;
				j++;
			} else {
				i++;
			}
		}

		if (j < n)
			flag = false;

		return flag;
	}

	boolean isSubSet4(int[] a1, int[] a2) {
		boolean flag = true;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < a1.length; i++)
			set.add(a1[i]);

		for (int i = 0; i < a2.length; i++) {
			if (!set.contains(a2[i])) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// Using HashMap: Time Complexity : O(n); Auxiliary Space : O(n)
	int firstNonRepeatingElement(int[] a) {
		int result = -1;
		Integer count = null;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			count = map.get(a[i]);
			if (count != null) {
				map.put(a[i], ++count);
			} else {
				map.put(a[i], 1);
			}
		}

		for (int i = 0; i < a.length; i++) {
			if (map.get(a[i]) == 1) {
				result = a[i];
				break;
			}
		}
		return result;
	}

	boolean binarySearch(int[] a, int low, int high, int x) {
		boolean flag = false;
		if (low <= high) {
			int mid = (low + high) / 2;
			if (x == a[mid]) {
				flag = true;
			} else if (x < a[mid]) {
				flag = binarySearch(a, low, mid - 1, x);
			} else {
				flag = binarySearch(a, mid + 1, high, x);
			}
		}
		return flag;
	}

	void quickSort(int[] a, int left, int right) {
		if (left < right) {
			int mid = partition(a, left, right);
			quickSort(a, left, mid - 1);
			quickSort(a, mid + 1, right);
		}
	}

	int partition(int[] a, int left, int right) {
		int pivot = a[right];
		int partionIndex = left;
		for (int i = left; i < right; i++) {
			if (a[i] < pivot) {
				swap(a, i, partionIndex);
				partionIndex++;
			}
		}
		swap(a, partionIndex, right);
		return partionIndex;
	}

	private void swap(int[] a, int m, int n) {
		int temp = a[m];
		a[m] = a[n];
		a[n] = temp;
	}

	public void heapSort(int[] a) {
		int n = a.length - 1;
		for (int i = (n / 2) - 1; i >= 0; i--) {
			heapify(a, i, a.length);
		}

		for (int i = a.length - 1; i > 0; i--) {
			swap(a, 0, i);
			heapify(a, 0, i);
		}
	}

	private void heapify(int[] a, int i, int n) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int largest = i;

		if (left < n && a[largest] < a[left])
			largest = left;

		if (right < n && a[largest] < a[right])
			largest = right;

		if (i != largest) {
			swap(a, i, largest);
			heapify(a, largest, n);
		}
	}

	public static void main(String[] args) {
		HashingGeeksProblems ob = new HashingGeeksProblems();
		int arr1[] = { 11, 1, 13, 21, 3, 7 };
		int arr2[] = { 11, 3, 7, 9 };
		// System.out.println("Is that subset: " + ob.isSubSet3(arr1, arr2));

		int arr[] = { 9, 4, 9, 6, 7, 4 };
		System.out.println("First non repeating element:" + ob.firstNonRepeatingElement(arr));
	}

}
