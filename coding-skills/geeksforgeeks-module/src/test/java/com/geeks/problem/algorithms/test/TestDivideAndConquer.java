package com.geeks.problem.algorithms.test;

import com.geeks.problem.algorithms.DivideAndConquer;

public class TestDivideAndConquer extends DivideAndConquer {

	public static void main(String[] args) {

		TestDivideAndConquer ob = new TestDivideAndConquer();

		// ob.testStandardAlgorithms();

		ob.testBinaryBasedProblems();

	}

	public void testStandardAlgorithms() {
		System.out.println("Pow method:" + pow2(2, 16));
		int[] a = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
		System.out.println("Max sub array sum(Brute Force Approach):" + maxSubArraySumBruteForce(a));
		System.out.println("Max sub array sum:" + maxSubArraySum(a, 0, a.length - 1));
		int[] a2 = { 1, -5, -2, 3, -1, -4 };
		System.out.println("Max sub array sum(using Kadane's Algorithm):" + maxSubArraySumKadaneAlg(a2));

		System.out.println("SkyLine Problem(Using Priority Queue): ");
		skyLineProblem2(skyLineMockData2());
		System.out.println("SkyLine Problem(Using TreeMap): ");
		skyLineProblem3(skyLineMockData2());

		System.out.println("Multiply Polynomials: ");
		// The following array represents polynomial 5 + 10x^2 + 6x^3
		int A[] = { 5, 0, 10, 6 };
		// The following array represents polynomial 1 + 2x + 4x^2
		int B[] = { 1, 2, 4 };
		multiplyPolynomials1(A, B);
	}

	public void testBinaryBasedProblems() {
		int[] arr1 = { 5, 7, 18, 28, 30 };
		int[] arr2 = { 3, 9, 15, 20, 23 };
		/*int[] arr1 = { 1, 3, 5, 7, 9, 11 };
		int[] arr2 = { 2, 4, 6, 8, 10, 12 };*/
		System.out.println("Median2: " + findMedian2(arr1, arr2));
		System.out.println("Median3: " + findMedian3(arr1, arr2));

		int[] a1 = { 22, 23 };
		int[] a2 = { 2, 4, 6, 8, 10, 12, 14, 16, 17 };
		System.out.println("Median for diff size arr: " + findMedianDiffSizeArray2(a1, a2));

		int[] ar1 = { 2, 3, 12, 14, 20 };
		int[] ar2 = { 6, 7, 9, 18 };

		System.out.println("Kth Element:" + findKthElement2(ar1, ar2, 4));
		System.out.println("Kth Element-1:" + kthElement(ar1, ar2, 4));
		System.out.println("Kth Element:" + findKthSmallestElement(ar1, ar2, 7));
	}

	public int[][] skyLineMockData2() {
		// Index Values: 0- x from left side, 1 - height value & 2- x from right side
		int[][] arr = { { 1, 11, 5 }, { 2, 6, 7 }, { 3, 13, 9 }, { 12, 7, 16 }, { 14, 3, 25 }, { 19, 18, 22 },
				{ 23, 13, 29 }, { 24, 4, 28 } };
		return arr;
	}
}
