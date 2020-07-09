package com.geeks.problem.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BitAlgorithms {
	/******************* Arithmetic Operations using bitwise operators ***************************/

	/*Sum without using arithmetic operators.
	 * Half Adder Logic:
	 * Sum of two bits can be obtained by performing XOR (^) of the two bits(S= a^b).
	 * Carry bit can be obtained by performing AND (&) of two bits(C= a&b).
	 */
	public static int sum1(int a, int b) {
		int sum = 0, carry = 0;
		while (b != 0) {
			sum = a ^ b;
			carry = a & b;

			a = sum;
			b = (carry << 1);
		}
		return sum;
	}

	// Recursive approach
	public static int sum2(int a, int b) {
		if (b == 0) return a;
		int sum = a ^ b;
		int carry = a & b;
		return sum2(sum, carry << 1);
	}

	/*Sub without using arithmetic operators.
	 * Half Subtractor Logic:
	 * Difference of two bits can be obtained by performing XOR (^) of the two bits(D= a^b).
	 * Borrow bit can be obtained by performing AND (&) of two bits(B= a' & b).
	 */
	public static int sub1(int a, int b) {
		int sub = 0, borrow = 0;
		while (b != 0) {
			sub = a ^ b;
			borrow = (~a) & b;

			a = sub;
			b = borrow << 1;
		}
		return sub;
	}

	public static int sub2(int a, int b) {
		if (b == 0) return a;
		int diff = a ^ b;
		int borrow = (~a) & b;
		return sub2(diff, borrow << 1);
	}

	/*Divide two integers without using multiplication, division and mod operator.
	 *  1. Using subraction & increment operator - Time Complexity: O(n) 
	 *  2. Using Bit Manipulations - Time Complexity: O(logn)
	 */
	/*
	 * Keep subtracting the divisor from dividend until dividend becomes less than divisor. The dividend becomes the
	 * remainder, and the number of times subtraction is done becomes the quotient. 
	 * - Time Complexity: O(a) This solution fails for large inputs.
	 */
	public static int divide1(int dividend, int divisor) {
		int quotient = 0;

		// Calculate the sign
		int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		/* Keep subtracting the divisor from dividend until dividend becomes less than divisor. The dividend becomes the remainder, 
		 * and the number of times subtraction is done becomes the quotient. 
		 */
		while (dividend >= divisor) {
			dividend -= divisor;
			quotient++;
		}
		return sign * quotient;
	}

	// Time Complexity: O(logn)
	public int divide2(int dividend, int divisor) {
		// Find the sign of the result
		boolean negSign = (dividend < 0 ^ divisor < 0) ? true : false;

		// handle special cases
		if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;

		// get positive & long values
		long lDividend = Math.abs((long) dividend);
		long lDivisor = Math.abs((long) divisor);
		int quotient = 0;

		while (lDividend >= lDivisor) {
			int count = 1;
			long temp = lDivisor;
			while (lDividend - temp >= temp) {
				temp <<= 1;
				count <<= 1;
			}
			quotient += count;
			lDividend -= temp;
		}

		return negSign ? -quotient : quotient;
	}

	// Try this without using long
	public int divide3(int dividend, int divisor) {
		int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1, quotient = 0;
		if (dividend == Integer.MIN_VALUE) {
			if (divisor == -1) return Integer.MAX_VALUE;
			if (divisor == Integer.MIN_VALUE) return 1;
			dividend += Math.abs(divisor);
			quotient++;
		}
		if (divisor == Integer.MIN_VALUE) return 0;
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor); // Math.abs(-2147483648)=-2147483648
		while (dividend >= divisor) {
			int tmp = divisor, count = 1;
			while (dividend - tmp >= tmp) { // (dividend >= tmp<<1 or >= 2*tmp) may cause overflows
				tmp <<= 1;
				count <<= 1;
			}
			dividend -= tmp;
			quotient += count;
		}
		return sign * quotient;
	}

	/************* Bit Manipulations - Basic Problems ***************/
	// Using branching(w/o using bit manipulation)
	public boolean oppositeSigns1(int a, int b) {
		return (a < 0 ? (b >= 0) : (b < 0));
	}

	public boolean oppositeSigns2(int a, int b) {
		return ((a ^ b) >> 31) < 0;
	}

	public boolean oppositeSigns3(int a, int b) {
		return ((a ^ b) < 0);
	}

	// Without using operators such as ‘+’, ‘-‘, ‘*’, ‘/’, ‘++’, ‘–‘
	public int addOne(int n) {
		int m = 1;
		// Flip all the set bits until we find a 0
		while ((n & m) == 1) {
			n = n ^ m;
			m = m << 1;
		}
		// flip the rightmost 0 bit
		n = n ^ m;
		return n;
	}

	public void printMinMax(int x, int y) {
		// int max = y ^ ((x ^ y) & (-(x < y)));
		// int min = x ^ ((x ^ y) & (-(x < y)));
	}

	// Find the element that appears once in an array where every other element appears twice
	/*
	1. Brute force :O(n^2)
	2. Using sorting: O(nlogn)
	3. Hashing/Counting sort : O(n), but takes additional space
	4. Using Bit Manipulations: O(n)
	*/
	public int singleElement(int[] nums) {
		int result = 0;
		for (int i = 0; i < nums.length; i++)
			result ^= nums[i];
		return result;
	}

	// Find the number which occurs odd number of times
	public int oddCountInArray(int[] arr) {
		int oddCountNumber = 0;
		for (int i = 0; i < arr.length; i++)
			oddCountNumber ^= arr[i];

		return oddCountNumber;
	}

	// What is happening here?? How this is working ?
	public int singleNumber2(int[] nums) {
		int ones = 0, twos = -1;
		System.out.println("-1         : " + Integer.toBinaryString(-3));
		System.out.println("-2147483648: " + Integer.toBinaryString(-2147483648));
		for (int i = 0; i < nums.length; i++) {
			System.out.println("Start - Ones: " + ones + " Twos: " + twos);
			ones = (ones ^ nums[i]) & twos;
			twos = (twos ^ nums[i]) | ones;
			System.out.println("End   - Ones: " + ones + " Twos: " + twos);
		}
		return ones;
	}

	// Using Bitwise operator
	public int findMissingNo(int[] arr) {
		int n = arr.length, x1, x2;
		x1 = arr[0];

		// Find the XOR result for all the elements in the array
		for (int i = 1; i < n; i++)
			x1 ^= arr[i];

		x2 = 1;
		// Find the XOR result for all the elements from 1 to n+1
		for (int i = 2; i <= n + 1; i++)
			x1 ^= i;

		// When you XOR for x1 and x2, duplicate elements will be eliminated and gets the duplicate no
		return x1 ^ x2;
	}

	/*
	 * Using Bit Magic: The idea is based on the concept that if we AND a bit sequence with a shifted version of itself, we’re effectively 
	 * removing the trailing 1 from every sequence of consecutive 1s. So the operation x = (x & (x << 1)) reduces length of every sequence 
	 * of 1s by one in binary representation of x. If we keep doing this operation in a loop, we end up with x = 0. The number of iterations 
	 * required to reach 0 is actually length of the longest consecutive sequence of 1s.
	 */
	public int longestConsecutiveOne(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n << 1);
			count++;
		}
		return count;
	}

	/*use bitwise AND of binary representation of the “given number its “right shifted number”(i.e., half the given number)
	  to figure out whether the number is sparse or not. */
	public boolean checkParse(int n) {
		return (n & (n >> 1)) == 0;
	}

	public int toggleBits(int data) {
		int result = 0, setBit = 1;
		while (data > 0) {
			if ((data & 1) == 0) result |= setBit;
			setBit <<= 1;
			data >>= 1;
		}
		return result;
	}

	/************* Bit Manipulations - Intermediate Problems ***************/

	public int reverseBits1(int n) {
		System.out.println("Input: " + Integer.toBinaryString(n));
		int rev = 0;
		while (n > 0) {
			rev <<= 1;
			if ((n & 1) == 1) rev = rev ^ 1;
			n >>= 1;
		}
		System.out.println("Ouput: " + Integer.toBinaryString(rev));
		return rev;
	}

	// Efficient Approach
	public int reverseBits2(int n) {
		System.out.println("Input: " + Integer.toBinaryString(n));
		int rev = 0;
		while (n > 0) {
			rev <<= 1;
			rev |= n & 1;
			n >>= 1;
		}
		System.out.println("Ouput: " + Integer.toBinaryString(rev));
		return rev;
	}

	public int reverseAllBits(int n) {
		System.out.println("Input: " + Integer.toBinaryString(n));
		int count = 32;
		int rev = 0;
		while (n > 0) {
			rev <<= 1;
			rev |= n & 1;
			n >>= 1;
			count--;
		}
		rev <<= count;
		System.out.println("Ouput: " + Integer.toBinaryString(rev));
		return rev;
	}

	/*Maximum Product of Word Lengths:
	 * The soultion is calcuated by doing a product of the length of each string to every other string. Anyhow the
	 * constraint given is that the two strings should not have any common character. This is taken care by creating a
	 * unique number for every string. Image a an 32 bit integer where 0 bit corresponds to 'a', 1st bit corresponds to
	 * 'b' and so on. Thus if two strings contain the same character when we do and "AND" the result will not be zero
	 * and we can ignore that case.
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

	/* Find the maximum subarray XOR in a given array:
	 * Given an array of integers. find the maximum XOR subarray value in given array. 
	 */
	// Approach1: Bruteforce Approach - Time Complexity-O(n^2)
	int maxSubarrayXOR(int[] arr, int n) {
		int ans = Integer.MIN_VALUE; // Initialize result

		// Pick starting points of subarrays
		for (int i = 0; i < n; i++) {
			// to store xor of current subarray
			int curr_xor = 0;

			// Pick ending points of subarrays starting with i
			for (int j = i; j < n; j++) {
				curr_xor = curr_xor ^ arr[j];
				ans = Math.max(ans, curr_xor);
			}
		}
		return ans;
	}

	// Approach2: Using Trie - Time Complexity-O(n)
	int maxSubarrayXOR2(int[] arr, int n) {
		return 0;
	}

	/* Find the maximum subset XOR of a given set:
	 * Given an set of positive integers. find the maximum XOR subset value in the given set. 
	 */
	public int maxSubsetXOR(int[] arr) {
		int n = arr.length, index = 0, INT_BITS = 32;
		// 1.Traverse through all bits of integer starting from the most significant bit (MSB)
		for (int i = INT_BITS - 1; i >= 0; i--) {
			// 2. Find the maximum element with i'th bit set.
			int maxInd = index, maxEle = Integer.MIN_VALUE;
			for (int j = index; j < n; j++) {
				// If i'th bit of arr[j] is 'set' & arr[j] is greater than max so far.
				if ((arr[j] & (1 << i)) != 0 && arr[j] > maxEle) {
					maxEle = arr[j];
					maxInd = j;
				}
			}

			// If there was no element with i'th bit arr, move to smaller i
			if (maxEle == -2147483648) continue;

			// swapping set[index] and set[maxInd] - Put maximum element with i'th bit arr at index 'index'
			int temp = arr[index];
			arr[index] = arr[maxInd];
			arr[maxInd] = temp;

			maxInd = index; // Update maxInd and increment index

			// Do XOR of arr[maxIndex] with all numbers having i'th bit as arr.
			for (int j = 0; j < n; j++) {
				// XOR arr[maxInd] those numbers which have the i'th bit arr
				if (j != maxInd && (arr[j] & (1 << i)) != 0) arr[j] = arr[j] ^ arr[maxInd];
			}

			index++; // Increment index of chosen elements
		}

		// Final result is XOR of all elements
		int res = 0;
		for (int i = 0; i < n; i++)
			res ^= arr[i];

		return res;
	}

	/*
	 * Maximum XOR of Two Numbers in an Array: Max XOR of "Only for two Numbers"
	 */
	public int findMaximumXOR(int[] nums) {
		int mask = 0, currMax = 0, max = 0, INT_BITS = 32;
		HashSet<Integer> set;
		for (int i = INT_BITS - 1; i >= 0; i--) {
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

	public List<String> findRepeatedDnaSequences(String s) {
		Set<String> uniqueSeq = new HashSet<>();
		Set<String> repeatedSeq = new HashSet<>();
		for (int i = 0; i <= s.length() - 10; i++) {
			String subStr = s.substring(i, i + 10);
			if (!uniqueSeq.add(subStr)) repeatedSeq.add(subStr);
		}

		return new ArrayList<>(repeatedSeq);
	}
	/************* Bit Manipulations - Hard Problems ***************/

	// Solve Hard Problems here

}
