package com.andrei.session;

public class BinarySearch {

	/**
	 * Complexity of Binary Search is O(logn)
	 * 
	 * @param a
	 * @param element
	 * @return
	 */
	int binSearch(int[] a, int element) {
		int left = 0;
		int right = a.length - 1;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (element == a[mid]) {
				return mid;
			} else if (element < a[mid]) {
				right = mid - 1;
			} else if (element > a[mid]) {
				left = mid + 1;
			} else {
				return -1;
			}
		}
		return -1;
	}

	/**
	 * Complexity of Linear Search is O(n)
	 * 
	 * @param a
	 * @param element
	 * @return
	 */
	int linearSearch(int[] a, int element) {
		for (int i = 1; i < a.length; i++) {
			if (a[i] == element) {
				return i;
			}
		}
		return -1;
	}

	int firstOccurence(int[] a, int element) {
		int left = 0;
		int right = a.length - 1;
		int mid = 0;
		int result = -1;
		while (left <= right) {
			mid = (left + right) / 2;
			if (element == a[mid]) {
				result = mid;
				right = mid - 1;
			} else if (element < a[mid]) {
				right = mid - 1;
			} else if (element > a[mid]) {
				left = mid + 1;
			} else {
				return result;
			}
		}
		return result;
	}

	int lastOccurence(int[] a, int element) {
		int left = 0;
		int right = a.length - 1;
		int mid = 0;
		int result = -1;
		while (left <= right) {
			mid = (left + right) / 2;
			if (element == a[mid]) {
				result = mid;
				left = mid + 1;
			} else if (element < a[mid]) {
				right = mid - 1;
			} else if (element > a[mid]) {
				left = mid + 1;
			} else {
				return result;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		BinarySearch obj = new BinarySearch();
		int[] a = { 2, 5, 6, 6, 6, 6, 26, 45, 69 };
		System.out.println("Element position:" + obj.binSearch(a, 6));
		System.out.println("First occurence of the element:" + obj.firstOccurence(a, 6));
		System.out.println("Last occurence of the element:" + obj.lastOccurence(a, 6));
	}

}
