package com.web.spoj.problems;

import java.util.*;

class BSEARCH1 {

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
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter N & Q:");
		int n = reader.nextInt(); 
		int q = reader.nextInt();
		
		System.out.println("Enter sorted array:");
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = reader.nextInt();
		System.out.println("Entered array:\n" + Arrays.toString(array));
		
		System.out.println("Enter Queries one by one:");
		BSEARCH1 bsearch1 = new BSEARCH1();
		int[] result = new int[q];
		for (int i = 0; i < q; i++) {
			result[i] = bsearch1.firstOccurence(array, reader.nextInt());
			/*System.out.println("Position of " + element + " is:" + bsearch1.firstOccurence(array, element));
			if (i < q)
				System.out.println("Next element:");
			else
				System.out.println("You are done!!!");*/
		}
		
		System.out.println("Output:");
		for (int i = 0; i < q; i++) {
			System.out.println(result[i]);
		}
		reader.close();
	}

}
