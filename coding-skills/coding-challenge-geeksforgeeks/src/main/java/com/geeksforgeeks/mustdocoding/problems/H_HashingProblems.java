package com.geeksforgeeks.mustdocoding.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class H_HashingProblems {

	/*
	 * sumA-a+b = sumB-b+a
	 * sumA- sumB = 2a-2b => sumA-sumB = 2(a-b) => a-b = (sumA - sumB)/2
	 */
	public static int isSwappingPairMakesSumEqual1(int[] a1, int m, int[] a2, int n) {
		int sum1 = sum(a1, m);
		int sum2 = sum(a2, n);
		int diff = Math.abs(sum1 - sum2);
		if (diff % 2 == 1)
			return -1;

		diff /= 2;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (diff == Math.abs(a1[i] - a2[j])) {
					System.out.println("Swapping Values are: " + a1[i] + ", " + a2[j]);
					return 1;
				}
			}
		}
		return -1;
	}

	private static int sum(int[] a, int n) {
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += a[i];
		return sum;
	}

	public static int isSwappingPairMakesSumEqual2(int[] a1, int m, int[] a2, int n) {
		int sum1 = sum(a1, m);
		int sum2 = sum(a2, n);

		Arrays.sort(a1);
		Arrays.sort(a2);

		int diff = Math.abs(sum1 - sum2);
		if (diff % 2 == 1)
			return -1;

		diff = diff / 2;
		int i = 0, j = 0;
		while (i < m && j < n) {
			int temp = Math.abs(a1[i] - a2[j]);
			if (diff == temp) {
				System.out.println("Swapping Values are: " + a1[i] + ", " + a2[j]);
				return 1;
			} else if (diff < temp) {
				i++;
			} else {
				j++;
			}
		}

		return -1;
	}

	// Time complexity: O((n-k)k)
	void countDistinct1(int A[], int k, int n) {
		for (int i = 0; i <= n - k; i++) {
			System.out.print(count(A, i, i + k) + " ");
		}
	}

	int count(int A[], int start, int end) {
		int count = 0;
		Set<Integer> set = new HashSet<>();
		for (int i = start; i < end; i++) {
			if (!set.contains(A[i])) {
				count++;
				set.add(A[i]);
			}
		}
		return count;
	}

	// Time complexity: O(n)
	static void countDistinct2(int A[], int k, int n) {
		Map<Integer, Integer> map = new HashMap<>(); // Number, count
		Integer value;
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				value = map.get(A[i - k]);
				if (value > 1)
					map.put(A[i - k], --value);
				else
					map.remove(A[i - k]);
			}

			value = map.get(A[i]);
			if (value == null)
				value = 0;
			map.put(A[i], ++value);

			if (i >= k - 1)
				System.out.print(map.size() + " ");
		}
	}

	public static void main(String[] args) {
		int[] A = { 1, 2, 1, 3, 4, 2, 3 };

		countDistinct2(A, 4, 7);

	}

}
