package com.consolidated.problems.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BitAlgorithms {
	/*************************** Type1: Check/Scan the bits *******************/
	// Check whether K-th bit is set or not
	public static String checkKthBit(int data, int k) {
		if ((data & (1 << k)) >= 1) return "Yes";
		return "No";
	}

	// Find first set bit
	public static int findFirstSetBit(int data) {
		if (data == 0) return 0;

		int bit = 1;
		int count = 1;
		while ((data & bit) == 0) {
			bit <<= 1;
			count++;
		}

		return count;
	}

	public int findFirstSetBit2(int data) {
		int count = 0, testBit = 1, temp;
		if (data > 0) {
			while (count < 32) {
				count++;
				temp = data & testBit;
				if (temp == testBit) return count;

				testBit <<= 1;
			}
		}
		return 0;
	}

	// Rightmost different bit
	public static int rightMostDifferentBit(int m, int n) {
		int xorValue = m ^ n;
		return findFirstSetBit(xorValue);
	}

	/* Number of 1 Bits/Hamming Weight:
	 * Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as 
	 * the Hamming weight).
	 */
	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	public static int hammingWeight2(int n) {
		int ones = 0;
		while (n != 0) {
			ones = ones + (n & 1);
			n = n >>> 1;
		}
		return ones;
	}

	// Count total set bits
	public static int countAllSetBits(int n) {
		int count = 0;
		if (n > 0) {
			for (int i = 1; i <= n; i++) {
				count += countSetBits1(i);
			}
		}
		return count;
	}

	public static int countSetBits1(int n) {
		int count = 0;
		if (n > 0) {
			while (n > 0) {
				/*count += n&1;
				n = n >> 1;*/
				n = n & (n - 1);
				count++;
			}
		}
		return count;
	}

	public int countSetBits2(int n) {
		int count = 0;
		while (n > 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	/* Hamming Distance/Conversion/Bit Difference
	 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
	 * Given two integers x and y, calculate the Hamming distance.
	 */
	public int hammingDistance(int x, int y) {
		return countSetBits2(x ^ y);
	}

	/* Total Hamming Distance:
	 *  find the total Hamming distance between all pairs of the given numbers.
	 */
	public int totalHammingDistance(int[] nums) {
		int totalDistance = 0, setBitCount = 0, n = nums.length;
		for (int bit = 0; bit < 32; bit++) {
			setBitCount = 0;
			for (int i = 0; i < n; i++) {
				setBitCount += ((nums[i] >> bit) & 1);
			}
			totalDistance += setBitCount * (n - setBitCount);
		}
		return totalDistance;
	}

	/* Counting Bits
	 * Given a non negative integer number num. For every numbers i in the range 0 <= i <= num calculate the number of 1's
	 * in their binary representation and return them as an array.
	 * Example 1: Input: 2; Output: [0,1,1]
	 * Example 2: Input: 5; Output: [0,1,1,2,1,2]
	 */
	// Brute force approach: Time Complexity : O(n*sizeof(integer))
	public int[] countBits1(int num) {
		int[] result = new int[num + 1];

		for (int i = 0; i <= num; i++)
			result[i] = noOfSetBits(i);

		return result;
	}

	public int noOfSetBits(int n) {
		int count = 0;
		while (n > 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	// Efficient Approach: Time Complexity: O(n)
	/*For number 1or2^0(01), 2or2^1(10), 4(100), 8(1000), 16(10000), ..., the number of 1's is 1. Any other number can be converted to be 2^m + x. For example, 9=8+1, 10=8+2. The number of 1's for any other number is 1 + # of 1's in x. */

	public int[] countBits(int num) {
		int[] result = new int[num + 1];
		int pow = 1, x = 1;

		for (int i = 1; i <= num; i++) {
			if (i == pow) { // if i value is two power value
				result[i] = 1;
				pow <<= 1;
				x = 1;
			} else {
				result[i] = result[x] + 1;
				x++;
			}
		}

		return result;
	}

	/*  Maximum Binary Gap/Binary Gap - Sliding Window
	 * Given a positive integer N, find and return the longest distance between two consecutive 1's in the binary 
	 * representation of N.	If there aren't two consecutive 1's, return 0.
	 * Example 1: Input: 22, Output: 2
	 */
	public int binaryGap(int n) {
		System.out.println(Integer.toBinaryString(n));
		int count = 0, max = 0;
		while (n > 0) {
			if ((n & 1) == 1) {
				max = Math.max(max, count);
				count = 1;
			} else if (count > 0) {
				count++;
			}
			n >>= 1;
		}
		return max;
	}

	/* Sparse Number
	 * Given a number N, check whether it is sparse or not. A number is said to be a sparse number if in the binary
	 * representation of the number no two or more consecutive bits are set.
	 */
	public int checkParse(int n) {
		return ((n & (n >> 1)) == 0) ? 1 : 0;
	}

	// Longest Consecutive 1’s
	public int longestConsecutiveOne(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n << 1);
			count++;
		}
		return count;
	}

	// Next Number
	public static void getNextAndPrevNum(int n) {
		System.out.println("Next Number: " + getNextArith(n));
		System.out.println("Prev Number: " + getPrevArith(n));
	}

	public static int getNextArith(int n) {
		int c = n, c0 = 0, c1 = 0;

		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}

		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}

		/* If c is 0, then n is a sequence of 1s followed by a sequence of 0s. This is already the biggest
		 * number with c1 ones. Return error.
		 */
		if (c0 + c1 == 31 || c0 + c1 == 0) return -1;

		/* Arithmetically:
		 * 2^c0 = 1 << c0
		 * 2^(c1-1) = 1 << (c0 - 1)
		 * next = n + 2^c0 + 2^(c1-1) - 1;
		 */
		return n + (1 << c0) + (1 << (c1 - 1)) - 1;
	}

	public static int getPrevArith(int n) {
		int temp = n, c0 = 0, c1 = 0;
		while (((temp & 1) == 1) && (temp != 0)) {
			c1++;
			temp >>= 1;
		}

		/* If temp is 0, then the number is a sequence of 0s followed by a sequence of 1s. This is already
		 * the smallest number with c1 ones. Return -1 for an error.
		 */
		if (temp == 0) return -1;

		while ((temp & 1) == 0 && (temp != 0)) {
			c0++;
			temp >>= 1;
		}

		/* Arithmetic:
		 * 2^c1 = 1 << c1
		 * 2^(c0 - 1) = 1 << (c0 - 1)
		 */
		return n - (1 << c1) - (1 << (c0 - 1)) + 1;
	}

	/*************************** Type2: Modify the bits *******************/
	// Set kth bit
	public static int setKthBit(int data, int k) {
		int testBit = 1 << k;
		return (data | testBit);
	}
	/*
	 * 3.Flip Bit to Win: You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length 
	 * of the longest sequence of ls you could create. 
	 * EXAMPLE 
	 * Input: 1775 (or: 11011101111)
	 * Output: 8
	 */

	public int flipBitToWin(int n) {
		if (n == -1) return Integer.BYTES * 8; // 4 * 8 = 32
		int maxLen = 0, prevLen = 0, currLen = 0;

		while (n != 0) {
			if ((n & 1) == 1) {
				currLen++;
			} else {
				/* Update to a (if next bit is a) or currentLength (if next bit is 1). */
				// previous Length = (a & 2) == a ? a : currentLength; -> Check how this is working
				// or
				prevLen = currLen;
				currLen = 0;
			}
			maxLen = Math.max(maxLen, (prevLen + currLen + 1));
			n >>>= 1; // Right
		}
		return maxLen;
	}

	// Toggle all Bits
	public int toggleBits(int data) {
		int result = 0, setBit = 1;
		while (data > 0) {
			if ((data & 1) == 0) result |= setBit;
			setBit <<= 1;
			data >>= 1;
		}
		return result;
	}

	// Toggle bits given range
	public int toggleBitsRange1(int data, int l, int r) {
		int testBit = 1 << (l - 1);
		while (l <= r) {
			data = data ^ testBit;
			testBit <<= 1;
			l++;
		}
		return data;
	}

	// Swap all odd and even bits/Pairwise Swap
	// Simple Approach
	public static int swapBits1(int n) {
		int evenBits = n & 0xAAAAAAAA;
		int oddBits = n & 0x55555555;
		evenBits >>>= 1;
		oddBits <<= 1;
		return (oddBits | evenBits);
	}

	// One line code of previous one
	public static int swapBits2(int n) {
		return ((n & 0xAAAAAAAA) >>> 1 | (n & 0x55555555) << 1);
	}

	// Insertion
	/*
	 * 1.Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to insert M into N 
	 * such that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space to fit all of M.
	 * That is, if M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for example,
	 * have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
	 * EXAMPLE:
	 *   Input:  N=10000000000, M=10011, i=2, j=6
	 *   Output: N=10001001100
	 */

	public void insertion(int N, int M, int i, int j) {
		System.out.println("N: " + Integer.toBinaryString(N));
		System.out.println("M: " + Integer.toBinaryString(M));
		// 1.Clear the bits j through i in N
		int allOnes = ~0;
		int left = allOnes << (j + 1);
		int right = (1 << i) - 1;
		int mask = left | right;
		// 2.Shift M so that it lines up with bits j through i
		M = M << i;
		// 3.Merge M and N
		N = N & mask;
		N = N | M;
		System.out.println("Result: " + Integer.toBinaryString(N));
	}

	// Rotate Bits
	static int	BITS		= 16;
	static int	CLEAR_BITS	= (-1 >>> BITS);

	public static int rotateLeft(int n, int d) {
		if (d > 16) d = d % BITS;

		return ((n << d) | (n >> BITS - d)) & 0xFFFF;
	}

	public static int rotateRight(int n, int d) {
		if (d > 16) d = d % BITS;
		return ((n >> d) | (n << BITS - d)) & CLEAR_BITS;
	}

	/* Reverse Bits/Reverse all bits
	 * Reverse bits of a given 32 bits unsigned integer.
	 */
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		int count = 31, result = 0;
		while (count >= 0) {
			int bit = n & 1;
			bit <<= count;
			if (bit != 0) {
				result |= bit;
			}
			n >>>= 1;
			count--;
		}
		return result;
	}

	// you need treat n as an unsigned value
	public int reverseBits2(int n) {
		// System.out.println("Input: " + Integer.toBinaryString(n));
		int count = 31;
		int rev = 0;
		while (n > 0) {
			rev <<= 1;
			rev |= n & 1;
			n >>= 1;
			count--;
		}
		rev <<= count;
		// System.out.println("Ouput: " + Integer.toBinaryString(rev));
		return rev;
	}

	/*************************** Type3: Apply the Bit Magic to Problems *******************/
	// Alone in a couple/Single Number I, II, III
	public static int findAloneInCouple(int[] arr) {
		int result = arr[0];
		for (int i = 1; i < arr.length; i++)
			result ^= arr[i];
		return result;
	}

	/* Single Number II: Given a non-empty array of integers, every element appears three times except for one, which
	   appears exactly once. Find that single one.
	 */
	public int singleNumberII1(int[] nums) {
		int ones = 0, twos = -1;
		for (int i = 0; i < nums.length; i++) {
			ones = (ones ^ nums[i]) & twos;
			twos = (twos ^ nums[i]) | ones;
		}
		return ones;
	}

	public int singleNumberII2(int[] nums) {
		// we need to implement a tree-time counter(base 3) that if a bit appears three time ,it will be zero.
		// #curent income ouput
		// # ab c/c ab/ab
		// # 00 1/0 01/00
		// # 01 1/0 10/01
		// # 10 1/0 00/10
		// a=~abc+a~b~c;
		// b=~a~bc+~ab~c;
		int a = 0;
		int b = 0;
		for (int c : nums) {
			int ta = (~a & b & c) | (a & ~b & ~c);
			b = (~a & ~b & c) | (~a & b & ~c);
			a = ta;
		}
		// we need find the number that is 01,10 => 1, 00 => 0.
		return a | b;
	}

	// Find the number which occurs odd number of times
	public int oddCountInArray(int[] arr) {
		int oddCountNumber = 0;
		for (int i = 0; i < arr.length; i++)
			oddCountNumber ^= arr[i];

		return oddCountNumber;
	}

	/* Missing Number/Missing number in array - Math/XOR/BS
	 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
	 */
	// Math Operation
	public int missingNumber1(int[] nums) {
		int n = nums.length;
		int sum = (n * (n + 1)) / 2;
		for (int i = 0; i < n; i++)
			sum -= nums[i];

		return sum;
	}

	// Using Bit manipulations - XOR Operation
	public int missingNumber2(int[] nums) {
		int xor = nums.length; // Assign Max size into result and then XOR for all elements

		for (int i = 0; i < nums.length; i++) {
			xor ^= i;
			xor ^= nums[i];
		}
		return xor;
	}

	// Binary Search Approach: O(nlogn)
	// Note: If data is already sorted, binary search will be efficient approach
	public int missingNumber(int[] nums) { // binary search
		Arrays.sort(nums);
		int left = 0, right = nums.length, mid = (left + right) / 2;
		while (left < right) {
			mid = (left + right) / 2;
			// Modification: Compare mid with mid index element.
			if (nums[mid] > mid) right = mid;
			else left = mid + 1;
		}
		return left;
	}

	// Power of Two
	// Approach1: Bit Manipulation; Time Complexity:O(1)
	public boolean isPowerOfTwo1(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}

	// Approach2: Bit Manipulation; Time Complexity:O(1)
	public boolean isPowerOfTwo2(int n) {
		return n > 0 && Integer.bitCount(n) == 1;
	}

	/* Binary Watch
	 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible
	 * times the watch could represent.
	 * Example: Input: n = 1, Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
	 */
	public List<String> readBinaryWatch(int num) {
		List<String> result = new ArrayList<>();
		for (int h = 0; h < 12; h++)
			for (int m = 0; m < 60; m++)
				if (Integer.bitCount(h) + Integer.bitCount(m) == num) result.add(h + (m < 10 ? ":0" : ":") + m);

		return result;
	}

	/* Bitwise AND of Numbers Range
	 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
	 * Example 1: Input: [5,7]; Output: 4
	 */
	// Approach1:
	public int rangeBitwiseAnd1(int m, int n) {
		while (m < n)
			n = n & (n - 1);

		return n;
	}

	// Approach2:
	public int rangeBitwiseAnd(int m, int n) {
		int moveFactor = 1;
		while (m != n) {
			m >>= 1;
			n >>= 1;
			moveFactor <<= 1;
		}
		return m * moveFactor;
	}

	/* Maximum Product of Word Lengths/Find the Difference
	 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do 
	 * not share common letters. You may assume that each word will contain only lower case letters. If no such two words
	 * exist, return 0.
	 * Example 1: Input: ["abcw","baz","foo","bar","xtfn","abcdef"], Output: 16
	 * Explanation: The two words can be "abcw", "xtfn".
	 */
	public int maxProduct(String[] words) {
		int n = words.length;
		int[] checker = new int[n];
		int masks = 0; // Variable to enable the bit based on the char
		for (int i = 0; i < n; i++) {
			masks = 0;
			for (int j = 0; j < words[i].length(); j++) {
				masks |= 1 << (words[i].charAt(j) - 'a');
			}
			checker[i] = masks;
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((checker[i] & checker[j]) == 0) // 0 means -> There is no same char b/w the strings
					max = Math.max(max, (words[i].length() * words[j].length()));
			}
		}

		return max;
	}

	/* Maximum subset XOR: 
	 * Given a set of positive integers. The task is to complete the function maxSubarrayXOR which returns an 
	 * integer denoting the maximum XOR subset value in the given set.
	 */
	public static int maxSubarrayXOR1(int[] nums, int n) {
		int mask = 0, currMax = 0, max = 0;
		HashSet<Integer> set;
		for (int i = 31; i >= 0; i--) {
			mask |= (1 << i);
			// Process bit by bit from LSB
			set = new HashSet<>();
			for (int num : nums)
				set.add(num & mask);

			// Find the currMax & max
			currMax = max | (1 << i);
			for (Integer val : set) {
				if (set.contains(val ^ currMax)) {
					max = currMax;
					break;
				}
			}
		}

		return max;
	}

	static int maxSubarrayXOR(int set[], int n) {
		int index = 0;

		for (int i = 31; i >= 0; i--) {
			int maxInd = index;
			int maxEle = Integer.MIN_VALUE;
			for (int j = index; j < n; j++) {
				if ((set[j] & (1 << i)) != 0 && set[j] > maxEle) {
					maxEle = set[j];
					maxInd = j;
				}
			}

			if (maxEle == -2147483648) continue;

			int temp = set[index];
			set[index] = set[maxInd];
			set[maxInd] = temp;

			maxInd = index;

			for (int j = 0; j < n; j++) {
				if (j != maxInd && (set[j] & (1 << i)) != 0) set[j] = set[j] ^ set[maxInd];
			}

			index++;
		}

		// Final result is XOR of all elements
		int res = 0;
		for (int i = 0; i < n; i++)
			res ^= set[i];
		return res;
	}

	/* Maximum subarray XOR/Maximum XOR of Two Numbers in an Array
	 * Find the maximum result of ai XOR aj, where 0 <= i, j < n. Could you do this in O(n) runtime?
	 * Example: Input: [3, 10, 5, 25, 2, 8]	Output: 28
	 * Explanation: The maximum result is 5 ^ 25 = 28.
	 */
	public int findMaximumXOR(int[] nums) {
		int mask = 0, currMax = 0, max = 0;
		HashSet<Integer> set;
		for (int i = 31; i >= 0; i--) {
			mask |= (1 << i);
			// Process bit by bit from LSB
			set = new HashSet<>();
			for (int num : nums)
				set.add(num & mask);

			// Find the currMax & max
			currMax = max | (1 << i);
			for (Integer val : set) {
				if (set.contains(val ^ currMax)) {
					max = currMax;
					break;
				}
			}
		}

		return max;
	}

	/* UTF-8 Validation
	 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
	 * For 1-byte character, the first bit is a 0, followed by its unicode code. For n-bytes character, the first n-bits 
	 * are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
	 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
	 */
	public boolean validUtf81(int[] data) {
		int count = 0;

		for (int d : data) {
			if (count == 0) {
				if (d >> 5 == 0b110) count = 1; // 2 Bytes data, Remaining :1 - '10' starting should be there
				else if (d >> 4 == 0b1110) count = 2; // 3 Bytes, Remaining :2 - '10' starting should be there
				else if (d >> 3 == 0b11110) count = 3; // 4 Bytes, Remaining :3 - '10' starting should be there
				else if (d >> 7 != 0) return false; // More than 4 Bytes -> return false
			} else {
				if (d >> 6 != 0b10) return false;
				count--;
			}
		}

		return count == 0; // If count is zero, Given data followed UTF-8 rules
	}

	public boolean validUtf8(int[] data) {

		// Number of bytes in the current UTF-8 character
		int numberOfBytesToProcess = 0;

		// Masks to check two most significant bits in a byte.
		int mask1 = 1 << 7;
		int mask2 = 1 << 6;

		// For each integer in the data array.
		for (int i = 0; i < data.length; i++) {
			// If this is the case then we are to start processing a new UTF-8 character.
			if (numberOfBytesToProcess == 0) {
				int mask = 1 << 7;
				while ((mask & data[i]) != 0) {
					numberOfBytesToProcess += 1;
					mask = mask >> 1;
				}

				// 1 byte characters
				if (numberOfBytesToProcess == 0) {
					continue;
				}

				// Invalid scenarios according to the rules of the problem.
				if (numberOfBytesToProcess > 4 || numberOfBytesToProcess == 1) { return false; }

			} else {

				// data[i] should have most significant bit set and
				// second most significant bit unset. So, we use the two masks
				// to make sure this is the case.
				if (!((data[i] & mask1) != 0 && (mask2 & data[i]) == 0)) { return false; }
			}

			// We reduce the number of bytes to process by 1 after each integer.
			numberOfBytesToProcess -= 1;
		}

		// This is for the case where we might not have the complete data for
		// a particular UTF-8 character.
		return numberOfBytesToProcess == 0;
	}

	// Draw Line

}
