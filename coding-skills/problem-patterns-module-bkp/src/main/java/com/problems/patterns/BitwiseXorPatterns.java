package com.problems.patterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BitwiseXorPatterns {

	// Single Number
	/*
	 *  Given a non-empty array of integers, every element appears twice except for one. Find that single one.
	 */
	public int singleNumber(int[] nums) {
		int result = 0;
		for (int i = 0; i < nums.length; i++)
			result ^= nums[i];
		return result;
	}

	// Two Single Numbers
	// TODO:

	// Complement of Base 10 Number
	// TODO

	/*
	 * Find Two Missing Numbers: 
	 * Given an array of n unique integers where each element in the array is in range [1, n]. The array has all distinct 
	 * elements and size of array is (n-2).Find the two missing numbers.
	 * 
	 * Approach1: Using Counting Sort(Boolean Array): – O(n) time complexity and O(n) Extra Space
	 * Approach2: Using Math Operation - O(n) time and O(1) space
	 * Approach3: Using Bit Manipulation - O(n) time and O(1) space
	 * Approach4: Combination of 2 & 3 - O(n) time and O(1) space
	 */

	// 2. Using Math Operation
	public static int[] findTwoMissingNumbers2(int arr[]) {
		int[] result = new int[2];
		int n = arr.length + 2;
		// 1.Sum of all elements in the array
		int sumOfN = sum(n);

		// 2.Sum of 2 missing numbers
		int sum = 0;
		for (int a : arr) {
			sum += a;
		}

		// 3. Sum of two missing numbers
		int sumOfMissingNum = sumOfN - sum;

		// 4. Average of 2 missing numbers
		int avgOfMissingElem = sumOfMissingNum / 2;

		// 5.Sum of natural numbers from 1 to avg
		int sumOfAvg = sum(avgOfMissingElem);

		// 6.Sum of array elements less than or equal to average
		int sumBeforeAvg = 0;
		for (int a : arr)
			if (a <= avgOfMissingElem)
				sumBeforeAvg += a;

		// First missing number
		result[0] = sumOfAvg - sumBeforeAvg;
		// Second missing number
		result[1] = sumOfMissingNum - result[0];
		return result;
	}

	private static int sum(int n) {
		return (n * (n + 1)) / 2;
	}

	// 2. Using Bit Manipulations
	public static int[] findTwoMissingNumbers3(int arr[]) {
		int[] result = new int[2];
		int n = arr.length + 2;

		int xor = 0;
		/* 1.Find XOR of all array elements and natural numbers from 1 to n; 
		  Note: Result is xor of two missing numbers; one element should be less than other. */
		for (int i = 1; i <= n; i++)
			xor ^= i;
		for (int a : arr)
			xor ^= a;

		/* Note: A bit is set in xor only if corresponding bits in X and Y are different. 
		 * Let us consider the rightmost set bit in XOR; which is used to split the elements into 2 sets.   
		 */

		// 2.Find the Right Most Set Bit
		int rightMostSetXor = xor & -xor; // xor & ~(xor - 1);

		/*3. Now if we XOR all the elements of arr[] and (1 to n) that have rightmost bit set(rightMostSetXor & elem > 0) 
		 * we will get one of the missing numbers, say result[0].
		 * Similarly, if we XOR all the elements of arr[] and (1 to n) that have rightmost bit not set(rightMostSetXor & elem == 0),
		 * we will get the other element, say result[1].
		 * 
		 */
		for (int i = 1; i <= n; i++) {
			if ((i & rightMostSetXor) > 0)
				result[0] ^= i;
			else
				result[1] ^= i;
		}
		for (int a : arr) {
			if ((a & rightMostSetXor) > 0)
				result[0] ^= a;
			else
				result[1] ^= a;
		}

		return result;
	}

	// Approach4: Combination of 2 & 3; Time: O(n), Space: O(1)
	public static int[] findTwoMissingNumbers4(int arr[]) {
		int[] result = new int[2];
		int n = arr.length + 2;

		// 1.Sum of all elements in the array
		int sumOfN = sum(n);

		// 2.Sum of 2 missing numbers
		int sum = 0;
		for (int a : arr) {
			sum += a;
		}

		// 3. Sum of two missing numbers
		int sumOfMissingNum = sumOfN - sum;

		// 4. Average of 2 missing numbers
		int avgOfMissingElem = sumOfMissingNum / 2;

		int totalLeftXor = 0, arrLeftXor = 0, totalRightXor = 0, arrRightXor = 0;

		/* 5.XOR of all the elements(1 to n and arr) which is less than qual to avgOfMissingElem. It gives one missing element
		 *   XOR of all the elements(1 to n and arr) which is greater than avgOfMissingElem. It gives another missing element
		 */
		for (int i = 1; i <= n; i++) {
			if (i <= avgOfMissingElem)
				totalLeftXor ^= i;
			else
				totalRightXor ^= i;
		}
		for (int a : arr) {
			if (a <= avgOfMissingElem)
				arrLeftXor ^= a;
			else
				arrRightXor ^= a;
		}

		result[0] = totalLeftXor ^ arrLeftXor;
		result[1] = totalRightXor ^ arrRightXor;
		return result;
	}

	/* Find Even Occurring Element:
	 * Given an integer array, one element occurs even number of times and all others have odd occurrences. Find the 
	 * element with even occurrences.
	 * 	1. BruteForce - Check one by one -> O(n^2), Space: O(1)
	 *  2. Using HashMap - Time: O(n), Space: O(n)
	 *  3. Bit Manipulations - Using Set and XOR - Time: O(n), Space: O(n)
	 */

	/* Approach: Set & XOR
	 * First we get all the unique numbers in the array using a set in O(N) time. Then we XOR the original array and the
	 * unique numbers all together. Result of XOR is the even occurring element.
	 * 
	 * For example, let’s say we’re given the following array: [2, 1, 3, 1]. First we get all the unique elements [1, 2, 3].
	 * Then we construct a new array from the original array and the unique elements by appending them together 
	 * [2, 1, 3, 1, 1, 2, 3]. We XOR all the elements in this new array. The result is 2^1^3^1^1^2^3 = 1.
	 */
	public int getEvenOccurrence3(int ar[], int n) {
		int result = 0;
		HashSet<Integer> set = new HashSet<>();

		for (int a : ar) {
			set.add(a);
			result ^= a;
		}

		for (Integer num : set)
			result ^= num;

		return result;
	}

	/*
	 * Find even occurring elements in an array of limited range:(0 to 63)
	 * Given an array that contains odd number of occurrences for all numbers except for a few elements which are present
	 * even number of times. Find the elements which have even occurrences in the array in O(n) time complexity and 
	 * O(1) extra space.
	 * Assume array contain elements in the range 0 to 63.
	 */
	public static void getEvenOccurrenceInRange1(int arr[], int n) {
		int xor = 0;
		List<Integer> result = new ArrayList<>();

		// Right shift 1 by value of each element in the array and take XOR of all the items
		for (int a : arr) {
			xor ^= (1 << a);
		}
		System.out.println(Integer.toBinaryString(xor));

		/* Each 1 in the i’th index from the right represents odd occurrence of element i.
		 * And each 0 in the i’th index from the right represents even or non-occurrence of element i in the array.
		 */
		for (int a : arr) {
			if ((xor & (1 << a)) == 0) {
				result.add(a);
				xor |= (1 << a); // Set '1' here to avoid duplicate
			}
		}

		result.stream().forEach(k -> System.out.print(k + " "));
	}

}