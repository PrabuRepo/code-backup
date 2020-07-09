package com.gaylemcdowell.problems;

public class Ch05BitManipulations {

	public static void main(String[] args) {
		Ch05BitManipulations ob = new Ch05BitManipulations();
		System.out.println("Merge two binary values: ");
		ob.insertion(1024, 19, 2, 6);

		System.out.println("Decimal(Fractional Part) to Binary Conversion: " + ob.decimalToBinary1(0.625));
		System.out.println("Decimal(Fractional Part) to Binary Conversion: " + ob.decimalToBinary2(0.625));

		System.out.println("Get Next & Prev Number: ");
		getNextAndPrevNum(12);

		System.out.println("Power of 2: " + ob.isPowerOf2(64));

		System.out.println("number of bits you would need to flip to convert integer A to integer B: "
				+ ob.hammingDistance(29, 15));

		System.out.println("Draw Line: ");
		// testDrawLine();
	}

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

	/*
	 * 2.Binary to String: Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print the binary 
	 * representation. If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR:'
	 */
	// Approach1:
	public String decimalToBinary1(double n) {
		if (n >= 1) return "ERROR";

		StringBuilder sb = new StringBuilder();
		double r;
		while (n > 0) {
			r = n * 2;
			if (r >= 1) {
				sb.append("1");
				n = r - 1;
			} else {
				sb.append("0");
				n = r;
			}
			
			if (sb.length() >= 32) {
				// return "ERROR";
				return sb.toString();
			}
		}
		return sb.toString();
	}

	// Approach2:
	public String decimalToBinary2(double n) {
		if (n >= 1) return "ERROR";

		StringBuilder sb = new StringBuilder();
		double frac = 0.5;
		while (n > 0) {
			if (n >= frac) {
				sb.append("1");
				n -= frac;
			} else {
				sb.append("0");
			}
			
			if (sb.length() >= 32) {
				// return "ERROR";
				return sb.toString();
			}
			frac /= 2;
		}
		return sb.toString();
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

	/*
	 * 4.Next Number: Given a positive integer, print the next smallest and the next largest number that have the same number 
	 * of 1 bits in their binary representation.
	 */

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

	/*
	 * 5.Debugger: Explain what the following code does: ( (n & (n -1) ) ==0);
	 * 		- Power of 2 problem 
	 */

	public boolean isPowerOf2(int n) {
		return (n & (n - 1)) == 0 ? true : false;
	}

	/*
	 * 6.Conversion: Write a function to determine the number of bits you would need to flip to convert integer A to integer B. 
	 *  EXAMPLE 
	 *  Input: 29 (or: 111131), 15 (or: 131111)
	 *  Output: 2
	 *  
	 *  Similar to Hamming Distance:
	 *     The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
	 */

	public int hammingDistance(int x, int y) {
		return countSetBits(x ^ y);
	}

	private int countSetBits(int n) {
		int count = 0;
		while (n > 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	/*
	 * 7.Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as possible 
	 * (e.g., bit 13 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
	 */
	// Simple Approach
	public static int swapOddEvenBits1(int n) {
		int evenBits = n & 0xAAAAAAAA;
		int oddBits = n & 0x55555555;
		evenBits >>>= 1;
		oddBits <<= 1;
		return (oddBits | evenBits);
	}

	// One line code of previous one
	public static int swapOddEvenBits2(int n) {
		return ((n & 0xAAAAAAAA) >>> 1 | (n & 0x55555555) << 1);
	}

	/*
	 * 8.Draw Line: A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in 
	 * one byte. The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows). The height of 
	 * the screen, of course, can be derived from the length of the array and the width. Implement a function that draws a 
	 * horizontal line from (xl, y) to (x2 J y). The method signature should look something like: 
	 * drawLine(byte[] screen, int width, int xl, int x2, int y)
	 */

	public static void testDrawLine() {
		int width = 8 * 1;
		int height = 1;
		for (int r = 0; r < height; r++) {
			for (int c1 = 0; c1 < width; c1++) {
				for (int c2 = c1; c2 < width; c2++) {
					byte[] screen = new byte[width * height / 8];

					System.out.println("row: " + r + ": " + c1 + " -> " + c2);
					drawLine(screen, width, c1, c2, r);
					printScreen(screen, width);
					System.out.println("\n\n");
				}
			}
		}
	}

	public static int computeByteNum(int width, int x, int y) {
		return (width * y + x) / 8;
	}

	public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int start_offset = x1 % 8;
		int first_full_byte = x1 / 8;
		if (start_offset != 0) first_full_byte++;

		int end_offset = x2 % 8;
		int last_full_byte = x2 / 8;
		if (end_offset != 7) last_full_byte--;

		// Set full bytes
		for (int b = first_full_byte; b <= last_full_byte; b++)
			screen[(width / 8) * y + b] = (byte) 0xFF;

		byte start_mask = (byte) (0xFF >> start_offset);
		byte end_mask = (byte) ~(0xFF >> (end_offset + 1));

		// Set start and end of line
		if ((x1 / 8) == (x2 / 8)) { // If x1 and x2 are in the same byte
			byte mask = (byte) (start_mask & end_mask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if (start_offset != 0) {
				int byte_number = (width / 8) * y + first_full_byte - 1;
				screen[byte_number] |= start_mask;
			}
			if (end_offset != 7) {
				int byte_number = (width / 8) * y + last_full_byte + 1;
				screen[byte_number] |= end_mask;
			}
		}
	}

	public static void printByte(byte b) {
		for (int i = 7; i >= 0; i--) {
			char c = ((b >> i) & 1) == 1 ? '1' : '_';
			System.out.print(c);
		}
	}

	public static void printScreen(byte[] screen, int width) {
		int height = screen.length * 8 / width;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c += 8) {
				byte b = screen[computeByteNum(width, c, r)];
				printByte(b);
			}
			System.out.println("");
		}
	}

	/*
	 * 9.
	 */
}
