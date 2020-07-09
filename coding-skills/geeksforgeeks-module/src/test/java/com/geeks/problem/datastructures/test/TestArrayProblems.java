package com.geeks.problem.datastructures.test;

import java.util.Arrays;

import com.common.model.Query;
import com.common.utilities.Utils;
import com.geeks.problem.datastructures.ArrayProblems;

public class TestArrayProblems extends ArrayProblems {

	public static void main(String[] args) {
		TestArrayProblems ob = new TestArrayProblems();

		// ob.testArrayRotationProblems();

		// ob.testArrangeAndRearrangeProblems();

		// ob.testOrderStatisticsProblems();

		// ob.testRangeQueriesProblems();

		// ob.testOptimizationProblems();

		// ob.testSortingProblems();

		// ob.testSearchingProblems();

		ob.testMiscProblems();

		// ob.testSubArrayProblems();

	}

	// ************Test Methods***************
	public void testArrayRotationProblems() {
		System.out.println("Left Rotations=> ");
		int[] a = mockArray1();
		System.out.println("Before rotation :");
		Utils.printArray(a);

		System.out.println("\nArray Rotations Brute force approach :");
		arrayLeftRotation1(a, 14);
		Utils.printArray(a);

		a = mockArray1();
		System.out.println("\nArray Rotations Juggling approach :");
		arrayLeftRotation3(a, 4);
		Utils.printArray(a);

		a = mockArray1();
		System.out.println("\nArray Rotations Reverse Algorithm :");
		arrayLeftRotation4(a, 4);
		Utils.printArray(a);

		System.out.println("\n\nRight Rotations=> ");

		a = mockArray1();
		System.out.println("Array Rotations Brute force approach :");
		arrayRightRotation1(a, 3);
		Utils.printArray(a);

		a = mockArray1();
		System.out.println("\nArray Rotations Reverse Algorithm :");
		arrayRightRotation4(a, 3);
		Utils.printArray(a);
	}

	public void testArrangeAndRearrangeProblems() {
		int[] arr = { -1, 2, -3, 4, 5, 6, -7, 8, 9, 100 };
		// Problem 1:
		System.out.println("Rearrange Positive & Negative numbers:");
		Utils.printArray(rearrangePosAndNegNumbers(arr));

		// Problem 2:
		System.out.println("\nReverse the array:");
		Utils.printArray(reverseArray(arr));

		int[] a = { 10, 90, 49, 2, 1, 5, 23 };
		// Problem 3:
		System.out.println("\nSort arrays in wave form:");
		Utils.printArray(sortArrayInWaveform2(a));
		System.out.println();
		Utils.printArray(sortArrayInWaveform(a));

		System.out.println("\nSegregate Zero's and one's - using count array:");
		int[] a2 = { 1, 0, 1, 1, 0, 0, 1, 1 };
		// Utils.printArray(segrgateZeroAndOne1(a2));
		System.out.println("\nSegregate Zero's and one's:");
		Utils.printArray(segrgateZeroAndOne2(a2));

		System.out.println("\nSort an array of 0s, 1s and 2s");
		int[] a3 = { 2, 2, 0, 1, 2, 1, 1, 0, 1, 0 };
		// Utils.printArray(sortArray012(a3));
		Utils.printArray(sort012(a3));

		System.out.println("\nShuffle an array: ");
		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		Utils.printArray(shuffleArray(a4));

		System.out.println("\nShuffle a deck of cards: ");
		int[] a5 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
				27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
		Utils.printArray(shuffleDeckOfCards(a5));
	}

	public void testOrderStatisticsProblems() {
		int[] arr = { -1, 2, -3, 4, 5, 6, -7, 8, 9, 100 };
		largest3ElementsInArray(arr);

		int[] a = { 10, -10, 20, -40 };
		System.out.println("K-th Largest Sum Contiguous Subarray:" + kthLargestSumInSubarray(a, 6));
	}

	public void testRangeQueriesProblems() {
		// int arr[] = { 1, 1, 2, 1, 3, 4, 5, 2, 8 }; // Perfect Square
		// int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int arr[] = { 7, 2, 3, 0, 5, 10, 3, 12, 18 };
		Query q1 = new Query();
		q1.left = 0;
		q1.right = 4;
		Query q2 = new Query();
		q2.left = 4;
		q2.right = 7;
		Query q3 = new Query();
		q3.left = 7;
		q3.right = 8;
		Query[] queries = { q1, q2, q3 };

		System.out.println("Range sum query-> ");
		System.out.println("Naive Approach: ");
		rangeSumQuery1(arr, queries);
		System.out.println("Decomposition Approach: ");
		rangeSumQuery2(arr, queries);
		System.out.println("Lookup table or Dynamic Programming Approach:");
		rangeSumQuery4(arr, queries);
		System.out.println("Sparse Table Approach:");
		rangeSumQuery5(arr, queries);
		System.out.println("Segment Tree Approach: ");
		rangeSumQuery6(arr, queries);

		System.out.println("Range min query-> ");
		System.out.println("Naive Approach: ");
		rangeMinQuery1(arr, queries);
		System.out.println("Decomposition Approach: ");
		rangeMinQuery2(arr, queries);
		System.out.println("Lookup table or Dynamic Programming Approach:");
		rangeMinQuery4(arr, queries);
		System.out.println("Sparse Table Approach: "); // Rewrite one more time
		rangeMinQuery5(arr, queries);
		System.out.println("Segment Tree Approach: ");// Rewrite one more time
		rangeMinQuery6(arr, queries);

	}

	public void testOptimizationProblems() {
		// int[] a = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, 7};
		int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
		System.out.println("Max sub array sum(Brute Force Approach):" + largestSumContiguousSubArray1(a));
		System.out.println("Max sub array sum(using Kadane's Algorithm):" + largestSumContiguousSubArray2(a));
		System.out.println("Max sub array sum (Approach 2):" + largestSumContiguousSubArray3(a));

		int[] arr = { 2, 30, 15, 10, 8, 25, 80 };
		System.out.println("\nMax Difference:" + maxDifference(arr));

		int[] prices = { 2, 30, 15, 10, 8, 25, 80 };
		System.out.println("Maximum profit by buying and selling a share at most twice:" + maximumProfit1(prices));
		System.out.println("Maximum profit by buying and selling a share at most twice:" + maximumProfit2(prices));
		int[] prices1 = { 2, 30, 15, 10, 8, 25, 80 };
		System.out.println("Maximum profit by buying and selling a share at most twice:" + maxProfit3(prices1));

		int[] arr2 = { 3, 7, 5, 20, -10, 0, 12 };
		System.out.println("\nFind the subarray with least average: ");
		subArrayWithLeastAvg(arr2, 2);

		int arr3[] = { 2, 2, -1, 5, -3, -2 };
		System.out.println("Maximum Sum of Sub array Close to K: " + getLargestSumCloseToK(arr3, 7));

	}

	public void testSearchingProblems() {
		int[] array1 = new int[6];
		insertUnSortedArray(array1, 40, 0);
		insertUnSortedArray(array1, 20, 1);
		insertUnSortedArray(array1, 60, 2);
		insertUnSortedArray(array1, 10, 3);
		insertUnSortedArray(array1, 50, 4);
		insertUnSortedArray(array1, 30, 5);
		// System.out.println("Find" + searchUnSortedArray(array1, 20));
		// Utils.printArray(array1);
		// System.out.println("\nAfter deletion:");
		deleteUnSortedArray(array1, 60);
		// Utils.printArray(array1);

		int[] array2 = new int[6];
		Arrays.fill(array2, -1);
		insertSortedArray(array2, 40);
		insertSortedArray(array2, 20);
		insertSortedArray(array2, 60);
		insertSortedArray(array2, 10);
		insertSortedArray(array2, 50);
		insertSortedArray(array2, 30);
		System.out.println("Find:" + searchSortedArray(array2, 10));
		Utils.printArray(array2);
		System.out.println("\nAfter deletion:");
		deleteSortedArray(array2, 40);
		Utils.printArray(array2);

		int[] a = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println("\nCalculate amount of trapping rain water->" + trappingRainWater1(a));
		System.out.println("\nCalculate amount of trapping rain water(Efficient Approach)->" + trappingRainWater2(a));

		int a2[] = { 12, 3, 4, 1, 6, 9 };
		System.out.println("Triplet sum(Brute force Approach): ");
		tripletSum1(a2, 11);

		System.out.println("Triplet sum(Hashing based approach): ");
		tripletSum3(a2, 11);

		System.out.println("Triplet sum(Sorting & 2 ptr approach): ");
		tripletSum2(a2, 11);

		int arr[] = { 3, 1, 4, 6, 5 };
		System.out.println("Pythagorean Triplet in an array(Brute force Approach): ");
		isPythagoreanTriplet1(arr);

		System.out.println("Pythagorean Triplet in an array(Hashing based approach): ");
		isPythagoreanTriplet3(arr);

		System.out.println("Pythagorean Triplet in an array(Sort & 2 Ptr): ");
		isPythagoreanTriplet2(arr);

	}

	public void testSortingProblems() {
		int arr1[] = { 2, 5, 6 };
		int arr2[] = { 4, 6, 8, 10 };
		System.out.println("Intersection(Approach1): ");
		Utils.printArray(intersect1(arr1, arr2));
		System.out.println("\nIntersection(Approach2): ");
		Utils.printArray(intersect1(arr1, arr2));

		System.out.println("\nUnion(Approach1): ");
		Utils.printArray(union1(arr1, arr2));
		System.out.println("\nUnion(Approach2): ");
		Utils.printArray(union2(arr1, arr2));

		int[] arr3 = { 1, 4, 2, 6, 2, 6 };
		System.out.println("\nCount frequencies of array elements in range 1 to n: ");
		freqOfElements3(arr3);
	}

	public void testSubArrayProblems() {
		int[] a = { 1, 3, -1, -3, 5, 3, 6, 7 };
		System.out.println("Maximum Sliding Window(Brute Force Approach): ");
		maxSlidingWindow1(a, 3);
		System.out.println("\nMaximum Sliding Window(Using Deque): ");
		Utils.printArray(maxSlidingWindow2(a, 3));

		int arr[] = { 2, 5, -1, 7, -3, -1, -2 };
		System.out.println(
				"\nSum of minimum and maximum elements of all subarrays of size k.: " + sumOfMinMaxOfSubarray(arr, 3));

		int[] arr2 = { -1 };
		System.out.println("Maximum Average Subarray I: " + maxAvgSubArray(arr2, 1));

		System.out.println("Maximum Product Subarray: " + maxProduct(arr));

		int[] arr3 = { 2, 3, 1, 2, 4, 3 };
		System.out.println("Find Minimum Length Sub Array With Sum K: " + minSubArrayLen(0, arr3));

		int[] arr4 = { 4, 3, 2, 1 };
		System.out.println("Shortest Unsorted Continuous Subarray: " + findUnsortedSubarray2(arr4));

		int[] a3 = { 5, 6, 3, 5, 7, 8, 9, 1, 2 };
		System.out.println("\nLongest Increasing subarray: " + longestIncreasingSubarray(a3));
	}

	public void testMiscProblems() {
		int[] a = { 1, 2, 3, 4 };
		System.out.println("Sum of SubArray(Brute Force): " + sumOfSubArray1(a));
		System.out.println("Sum of SubArray(Efficient Approach): " + sumOfSubArray2(a));

		int arr1[] = { 1, 6, 3, 2, 3, 6, 6 };
		Utils.printArray(arr1);
		System.out.println("\nPrint duplicates(Approach1):");
		printDuplicates1(arr1);
		System.out.println("\nPrint duplicates(Approach2):");
		int arr2[] = { 1, 6, 3, 2, 3, 6, 6 };
		printDuplicates2(arr2);

		int[] arr3 = { 1, 2, 4, 5, 6, 7, 9, 3 };
		System.out.println("\nMissing Element(Using Formula): " + missingNumber1(arr3));
		System.out.println("\nMissing Element(Using Sum): " + missingNumber2(arr3));
		System.out.println("Missing Element(Using XOR): " + missingNumber3(arr3));

		int[] arr4 = { 11, 9, 12 };
		System.out.println("Element with left side smaller and right side greater: " + firstMidElement1(arr4));

		int[] arr5 = { 4, 3, 2, 7, 8, 9 };
		System.out.println("Element with left side smaller and right side greater: " + findElement3(arr5));

		int[][] transactions = { { 0, 1, 10 }, { 1, 0, 1 }, { 1, 2, 15 }, { 2, 0, 5 } };
		System.out.println("Optimal Account Balancing: " + minTransfers(transactions));

		int[] nums = { 1, 3, 2 };
		nextPermutation(nums);
		System.out.println("Find the next Permutation: " + Arrays.toString(nums));
	}

	private int[] mockArray1() {
		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		return a;
	}
}
