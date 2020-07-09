package com.geeksforgeeks.mustdocoding.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class A_ArrayProblems {

	// 1.Kadane's Algorithm
	void kadaneAlgorithm(int[] a) {
		int sum = 0, startIndex = 0, endIndex = 0, startIndexUpdate = 0, max = a[0];
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum > max) {
				max = sum;
				startIndex = startIndexUpdate;
				endIndex = i;
			}
			if (sum < 0) {
				sum = 0;
				startIndexUpdate = i + 1;
			}
		}
		System.out.println(max);
		System.out.println("Start: " + startIndex + " End:" + endIndex);
	}

	// 2.Missing number in array
	public static int missingNumber(int[] a) {
		int sum = 0, missingSum = 0;
		for (int i = 1; i <= a.length + 1; i++) {
			sum += i;
		}

		for (int i = 0; i < a.length; i++) {
			missingSum += a[i];
		}

		return sum - missingSum;
	}

	// 3.Subarray with given sum
	// Naive solution or Brute force approach: Time Complexity: O(n^2)
	public static void subArrayWithGivenSum1(int[] arr, int sum) {
		int start = 0, end = 0, tempSum = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			start = i;
			tempSum = 0;
			for (int j = i; j < n; j++) {
				tempSum += arr[j];
				if (sum == tempSum) {
					end = j;
					break;
				}
			}
			if (sum == tempSum)
				break;
		}
		if (sum == tempSum)
			System.out.println((start + 1) + " " + (end + 1));
		else
			System.out.println("-1");
	}

	// 3.Subarray with given sum
	// Efficient approach: Time Complexity: O(n)
	public static void subArrayWithGivenSum2(int[] arr, int sum) {
		int currSum = arr[0], start = 0, end = 0, n = arr.length, i;
		for (i = 1; i < n; i++) {
			currSum += arr[i];

			while (currSum > sum && start < i - 1) {
				currSum = currSum - arr[start];
				start++;
			}

			if (currSum == sum) {
				end = i;
				break;
			}
		}

		if (sum == currSum) {
			// System.out.println((start) + " " + (end)); // Print the Index
			System.out.println((start + 1) + " " + (end + 1)); // Print the Position
		} else {
			System.out.println("-1");
		}
	}

	// 4.Sort an array of 0s, 1s and 2s
	public static int[] sortArray012(int[] a) {
		int low = 0, mid = 0, high = a.length - 1;

		while (mid <= high) {
			switch (a[mid]) {
			case 0:
				swap(a, low, mid);
				low++;
				mid++;
				break;
			case 1:
				mid++;
				break;
			case 2:
				swap(a, mid, high);
				high--;
				break;
			default:
				System.out.println("Wrong Data");
				break;
			}
		}
		return a;
	}

	public static void swap(int[] a, int i1, int i2) {
		int temp = a[i1];
		a[i1] = a[i2];
		a[i2] = temp;
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	/*
	 * Equilibrium index of an array is an index such that the sum of elements at lower indexes is equal to the sum of elements
	 * at higher indexes.
	 */
	// 5.Equilibrium point: Brute Force Approach- Time complexity: O(n^2)
	public static int equilibriumPoint1(int[] a) {
		int leftSum, rightSum;
		if (a.length == 1)
			return 1;
		for (int i = 1; i < a.length; i++) {
			leftSum = 0;
			// Left Sum:
			for (int j = 0; j < i; j++)
				leftSum += a[j];

			// Right sum
			rightSum = 0;
			for (int k = i + 1; k < a.length; k++)
				rightSum += a[k];

			if (leftSum == rightSum)
				return (i + 1); // Problem expects to return position(not index)
		}
		return -1;
	}

	// 5.Equilibrium point: Brute Force Approach- Time complexity: O(n)
	public static int equilibriumPoint2(int[] a) {
		int leftSum = 0, sum = 0;
		if (a.length == 1)
			return 1;
		for (int i = 0; i < a.length; i++)
			sum += a[i];

		for (int j = 0; j < a.length; j++) {
			sum -= a[j];
			if (leftSum == sum)
				return j + 1;
			leftSum += a[j];
		}

		return -1;
	}

	// 7.Leaders in array: BruteForce Approach- TimeComplexity:O(n^2)
	public static void leadersInArray1(int[] arr) {

	}

	// 7.Leaders in array: Using PriorityQueue- TimeComplexity:O(nlogn)
	public static void leadersInArray2(int[] arr) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++)
			queue.add(arr[i]);

		for (int i = 0; i < arr.length; i++) {
			queue.remove(arr[i]);
			if (queue.isEmpty() || arr[i] > queue.peek())
				System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 7.Leaders in array: Efficient Approach TimeComplexity:O(nlogn)
	public static void leadersInArray3(int[] arr) {
		int currMax = Integer.MIN_VALUE;
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] > currMax) {
				currMax = arr[i];
				result.add(arr[i]);
			}
		}

		for (int i = result.size(); i >= 0; i--) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();
	}

	// 18.Find the element that appears once in a sorted array:Linear algorithm
	public static int findElementAppearsOnce(int[] a) {
		int i, result = -1;
		for (i = 0; i < a.length - 1; i += 2) {
			if (a[i] != a[i + 1]) {
				result = a[i];
				break;
			}
		}

		if (result == -1)
			result = a[a.length - 1];

		return result;
	}

	public static void main(String[] args) {
		System.out.println("Kadane Alg:");

		int[] a = { 2, 3, 4, 1, 6, 7 };
		System.out.println("Missing No: ");
		missingNumber(a);

		System.out.println("Sub array with given sum: ");
		subArrayWithGivenSum1(a, 11);
		subArrayWithGivenSum2(a, 11);

		int[] a2 = { 1, 2, 3, 4, 4, 7 };
		System.out.println("Find the element which appears more than once: " + findElementAppearsOnce(a2));
	}
}
