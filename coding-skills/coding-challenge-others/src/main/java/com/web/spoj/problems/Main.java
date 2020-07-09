package com.web.spoj.problems;

import java.util.*;

class Main {

	public static void main(String[] args) throws java.lang.Exception {
		java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		System.out.println("Input:");
		int n = Integer.parseInt(reader.readLine());
		int q = Integer.parseInt(reader.readLine());

		int[] a = new int[100005];
		for (int i = 0; i < n; i++)
			a[i] = Integer.parseInt(reader.readLine());

		int left, right, mid, result, element;
		for (int i = 0; i < q; i++) {
			element = Integer.parseInt(reader.readLine());

			result = -1;
			left = 0;
			right = n - 1;
			mid = 0;

			while (left <= right) {
				mid = (left + right) / 2;
				if (element == a[mid]) {
					result = mid;
					right = mid - 1;
				} else if (element < a[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			System.out.println("Output:" + result);
		}
		reader.close();

	}
}

/*
 * public static void main(String[] args) throws java.lang.Exception { Scanner
 * reader = new Scanner(System.in); System.out.println("Input:"); int n =
 * reader.nextInt(); int q = reader.nextInt();
 * 
 * int[] a = new int[100005]; for (int i = 0; i < n; i++) a[i] =
 * reader.nextInt();
 * 
 * int left, right, mid, result, element; for (int i = 0; i < q; i++) { element
 * = reader.nextInt();
 * 
 * result = -1; left = 0; right =n - 1; mid = 0;
 * 
 * while (left <= right) { mid = (left + right) / 2; if (element == a[mid]) {
 * result = mid; right = mid - 1; } else if (element < a[mid]) { right = mid -
 * 1; } else { left = mid + 1; } } System.out.println("Output:" + result); }
 * reader.close();
 * 
 * }
 */