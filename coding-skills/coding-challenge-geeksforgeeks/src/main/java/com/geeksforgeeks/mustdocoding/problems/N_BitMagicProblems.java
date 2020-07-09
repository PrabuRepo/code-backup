package com.geeksforgeeks.mustdocoding.problems;

import java.math.BigInteger;

public class N_BitMagicProblems {
	// 1.Find first set bit
	public static int findFirstSetBit(int data) {
		int count = 0, temp, bit = 1;
		if (data > 0) {
			while (count < 32) {
				count++;
				temp = data & bit;
				if (temp == bit) {
					break;
				}
				bit <<= 1;
			}
		}
		return count;
	}

	// 2.Rightmost different bit
	public static int rightMostDifferentBit(int m, int n) {
		int xorValue = m ^ n;
		return findFirstSetBit(xorValue);
	}

	// 3.Check whether K-th bit is set or not
	// Generally it starts from 1st bit, but for this example, for N = 4(100), 0th bit = 0, 1st bit = 0, 2nd bit = 1.
	public static String checkKthBit(int data, int k) {
		int testBit = 1 << k;
		if (testBit == (data & testBit))
			return "Yes";

		return "No";
	}

	// 4.Toggle bits given range
	public static int toggleBitsRange(int data, int l, int r) {
		int testBit = 1 << (l - 1);
		int diff = r - l;
		while (diff-- >= 0) {
			if (testBit == (data & testBit))
				data -= testBit;
			else
				data += testBit;
			testBit <<= 1;
		}
		return data;
	}

	// 5.Set kth bit
	public static int setKthBit(int data, int k) {
		int testBit = 1 << k;
		return (data | testBit);
	}

	// 6.Power of 2
	public static String checkNumberPowerOfTwo(BigInteger n) {
		BigInteger one = new BigInteger("1");
		BigInteger zero = new BigInteger("0");
		// Condition: (n == 1 || ( n > 0 && (n & n-1) == 0)
		if (n.compareTo(one) == 0 || (n.compareTo(zero) == 1 && (n.and(n.subtract(one))).equals(zero))) {
			return "YES";
		}
		return "NO";
	}

	// 7.Bit Difference
	public static int bitDifference(int a, int b) {
		return countSetBits(a ^ b);
	}

	public static int countSetBits(int n) {
		int count = 0;
		while (n > 0) {
			n = n & (n - 1);
			count++;
		}
		return count;

	}

	// 8.Rotate Bits
	public static void rotateBits(int n, int d) {
		System.out.println(n << d);
		System.out.println(n >> d);
	}

	// 9.Swap all odd and even bits
	public static int swapBits(int x) {
		int evenBits = x & 0xAAAAAAAA; // Get all even bits of x by doing bitwise and of x with 0xAAAAAAAA
		int oddBits = x & 0x55555555; // Get all odd bits of x by doing bitwise and of x with 0x55555555
		evenBits >>= 1; // Right shift all even bits.
		oddBits <<= 1; // Left shift all odd bits
		return (oddBits | evenBits);
	}

	public static void main(String[] args) {
		System.out.println("First set bit position: " + findFirstSetBit(8));

		System.out.println("Right most different bit: " + rightMostDifferentBit(52, 4));

		System.out.println("Check whether K-th bit is set or not: " + checkKthBit(4, 2));

		System.out.println(toggleBitsRange(17, 2, 3));

		System.out.println("Set Kth bit: " + setKthBit(8, 2));

		System.out.println("Check the Number is Power Of 2: " + checkNumberPowerOfTwo(new BigInteger("128")));

		System.out.println("Bit Difference: " + bitDifference(10, 20));

		System.out.println("Rotate Bits: ");
		rotateBits(12, 2);

	}
}
