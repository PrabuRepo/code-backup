package com.geeks.problem.algorithms.test;

import com.geeks.problem.algorithms.BitAlgorithms;

public class TestBitAlgorithms extends BitAlgorithms {
	public static void main(String[] args) {
		TestBitAlgorithms ob = new TestBitAlgorithms();

		// ob.testArithmeticOpertations();

		// ob.testBasicProblems();

		ob.testIntermediateProblems();
	}

	/************* Test Methods ***************/
	public void testArithmeticOpertations() {
		System.out.println("Sum: " + sum1(15, 15));
		System.out.println("Sum: " + sum2(15, 15));

		System.out.println("Sub: " + sub1(15, 19));
		System.out.println("Sub: " + sub2(15, 19));

		System.out.println("Math.Div: " + Math.floorDiv(-93298, 4275));
		System.out.println("Div: " + divide1(-93298, 4275));
		System.out.println("Div: " + divide2(2147483647, 2));
		System.out.println("Div: " + divide3(2147483647, 2));
	}

	public void testBasicProblems() {
		System.out.println("Check given numbers are opposite?:" + oppositeSigns3(50, -70));
		System.out.println("Add one with given number:" + addOne(15));
		int[] nums = { 7, 3, 5, 4, 5, 3, 7 };
		System.out.println("Find the element appears once in the array: " + singleElement(nums));

		int[] arr1 = { 2, 3, 4, 3, 2, 4, 3 };
		System.out.println("Find the number which occurs odd number of times: " + oddCountInArray(arr1));

		int[] nums2 = { 2, 3, 2, 2 }; // { 0, 1, 0, 1, 0, 1, 99 };
		System.out.println("Find the element appears once in the array: " + singleNumber2(nums2));

		int[] arr = { 1, 2, 4, 5, 6, 7, 9, 3 };
		System.out.println("Missing Element: " + findMissingNo(arr));

		System.out.println("Toggle Bits: " + toggleBits(10));
	}

	public void testIntermediateProblems() {
		System.out.println("Reverse the bits: " + reverseBits1(11));
		System.out.println("Reverse the bits2: " + reverseBits2(16));
		System.out.println("Reverse all the bits: " + reverseAllBits(16));
		System.out.println("Flip Bit to Win: " + flipBitToWin(1775));
		int[] arr = { 8, 1, 2, 12, 7, 6 };
		System.out.println("Max Subset XOR: " + maxSubsetXOR(arr));
	}
}
