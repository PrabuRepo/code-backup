package com.consolidated.problems.cheatsheet;

public class BitAlgorithmsBasic {

	/*Practice tricks in below links:
	 ********************************
	    https://github.com/keon/awesome-bits
		https://www.hackerearth.com/practice/notes/bit-manipulation/
		https://www.quora.com/What-are-some-cool-bit-manipulation-tricks-hacks
		https://stackoverflow.com/questions/1533131/what-useful-bitwise-operator-code-tricks-should-a-developer-know-about
		https://gist.github.com/stephenLee/4024869
		https://www.topcoder.com/community/data-science/data-science-tutorials/a-bit-of-fun-fun-with-bits/
		
	Applications of bit operations:
	1) They are widely used in areas of graphics ,specially XOR(Exclusive OR) operations.
	2) They are widely used in the embedded systems, in situations, where we need to set/clear/toggle just one single bit of a specific 
	   register without modifying the other contents. We can do OR/AND/XOR operations with the appropriate mask for the bit position.
	3) Data structure like n-bit map can be used to allocate n-size resource pool to represent the current status.
	4) Bits are used in networking, framing the packets of numerous bits which is sent to another system generally through any type 
	   of serial interface.
	*/

	/*********** Bit Manipulations - Basics Ideas ******************/
	public void dataTypesInfo() {
		/*
		  Java Data Types:
		  ****************
			1.byte:  8-bit signed two's complement integer. Range(Min: -128(2^7); Max: 127(2^7-1) (inclusive)).
			2.short: 16-bit signed two's complement integer. Range(Min: -32,768(2^15); Max: 32,767(2^15-1) (inclusive)).
			3.int: By default, the int data type is a 32-bit signed two's complement integer. Range(Min:-2^31; Max:2^31-1). 
			  In Java SE 8 and later, you can use the int data type to represent an unsigned 32-bit. Range(Min:0; Max:2^32-1). Use the Integer class to use int data type as an unsigned integer.
			4.long: 64-bit two's complement integer. Range(Min:-2^63; Max:2^63-1). 
			 In Java SE 8 and later, you can use the long data type to represent an unsigned 64-bit long. Range(Min:0; Max:2^64-1). 
			5.float: a single-precision 32-bit IEEE 754 floating point.  Range: ???
			6.double: a double-precision 64-bit IEEE 754 floating point. Range: ??? 
			7.boolean: The boolean data type has only two possible values: true and false. This data type represents one bit of information, but its "size" isn't something that's precisely defined.
			8.char: a single 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).
			String: In addition to the eight primitive data types listed above, the Java programming language also provides special support for character strings 
			via the java.lang.String class. 
		*/
	}

	public void bitwiseOperator(int a, int b) {
		System.out.println(
				"Bitwise AND Operation:" + Integer
						.toBinaryString(a & b));
		System.out.println("Bitwise OR Operation:"
				+ Integer.toBinaryString(a | b));
		System.out.println(
				"Bitwise XOR Operation:" + Integer
						.toBinaryString(a ^ b));
		System.out.println(
				"Bitwise Negation or Complement Operation:"
						+ Integer.toBinaryString(
								~b));
		System.out.println("Left shift operator:"
				+ (b << 1));
		/* >> Vs >>>:  ">>>" shifts a zero into the
		 * leftmost position, while the leftmost 
		 * position after ">>" depends on sign*/
		System.out.println(
				"Signed Right shift operator(>>)   :"
						+ Integer.toBinaryString(
								-1 >> 2));
		System.out.println(
				"Unsigned Right shift operator(>>>):"
						+ Integer.toBinaryString(
								-1 >>> 2));
		System.out.println(
				"Signed Right shift operator(>>)   :"
						+ Integer.toBinaryString(
								Integer.MAX_VALUE >> 2));
		System.out.println(
				"Unsigned Right shift operator(>>>):"
						+ Integer.toBinaryString(
								Integer.MAX_VALUE >>> 2));
	}

	public void testBitwiseAnd() {
		int i = 0;
		// To check the odd/even no's
		while (i < 16) {
			System.out.println(
					i + " -> " + (i & 1));
			i++;
		}
	}

	public void findMemoryInfo(int n) {
		// From some value to 2th power
		int twoPowN = (int) Math
				.ceil(Math.log(n) / Math.log(2));
		double bytes = Math.pow(2, twoPowN);
		double bits = 8 * bytes; // 1byte = 8bits
		System.out.println("For the input n: " + n
				+ "-> 2th Power: " + twoPowN
				+ "; Bytes: " + bytes + "; Bits: "
				+ bits);
	}

	/************** Bit Manipulations - Simple tricks ******************/

	public int intMaxValue() {
		int max = 0;
		max = ~max;
		max = max >>> 1;
		return max;
	}

	public long longMaxValue() {
		long max = 0;
		max = ~max;
		max = max >>> 1;
		return max;
	}

	public int intMinValue() {
		int max = 0;
		max = ~max;
		max = max >>> 1;
		int min = ~max;
		return min;
	}

	public long longMinValue() {
		long max = 0;
		max = ~max;
		max = max >>> 1;
		long min = ~max;
		return min;
	}

	// Test nth bit is set:
	public boolean isSetBit(int data, int n) {
		return (data & (1 << n)) > 0;
	}

	// Set nth bit:
	public int setNthBit(int data, int n) {
		return (data | (1 << n));
	}

	// Turn off nth bit:
	public int unSetNthBit(int data, int n) {
		return (data & ~(1 << n));
	}

	// Toggle the nth bit:
	public int toggleNthBit(int data, int n) {
		return (data ^ (1 << n));
	}

	public int toggleBitsRange(int data, int l,
			int r) {
		int testBit = 1 << (l - 1);
		while (l <= r) {
			data = data ^ testBit;
			testBit <<= 1;
			l++;
		}
		return data;
	}

	// Each Left Rotation will be multiplied by 2
	public int leftRotation(int data,
			int noOfRotations) {
		return data << noOfRotations;
	}

	// Each Right Rotation will be divided by 2
	public int rightRotation(int data,
			int noOfRotations) {
		return data >> noOfRotations;
	}

	/*
	 * Rotate bits of a number Bit Rotation: A rotation (or circular shift) is an operation similar to shift except that
	 * the bits that fall off at one end are put back to the other end. 
	 *    In left rotation, the bits that fall off at left end are put back at right end. 
	 *    In right rotation, the bits that fall off at right end are put back at left end.
	 * 		
	 * Problem: Given an integer N and an integer D, you are required to write a program to rotate the binary representation
	 * of the integer N by D digits to the left as well as right and print the results in decimal values after each of the rotation.
	 * Note: Integer N is stored using 16 bits. i.e. 12 will be stored as 0000.....001100.
	 */

	// Rotate bits of a number Bit Rotation
	static int	BITS_16	= 16;
	static int	BITS_32	= 32;

	// For 32 bits
	public static int rotateLeft1(int n, int d) {
		return ((n << d) | (n >> BITS_32 - d));
	}

	// For 16 Bits
	public static int rotateLeft2(int n, int d) {
		if (d > 16) d = d % BITS_16;
		return ((n << d) | (n >> BITS_16 - d))
				& 0xFFFF;
	}

	// For 32 bits
	public static int rotateRight1(int n, int d) {
		return ((n >> d) | (n << BITS_32 - d));
	}

	// For 16 bits
	public static int rotateRight2(int n, int d) {
		if (d > 16) d = d % BITS_16;
		return ((n >> d) | (n << BITS_16 - d))
				& 0xFFFF;
	}

	public int twoPowerOfN(int n) {
		return (1 << n);
	}

	public boolean checkPowerOfTwo(int x) {
		return (x & (x - 1)) == 0;
	}

	public boolean checkEquality(int a, int b) {
		return (a ^ b) == 0;
	}

	public boolean isEven(int n) {
		return (n & 1) == 0;
	}

	public boolean isOdd1(int n) {
		return (n & 1) == 1;
	}

	public boolean isOdd2(int n) {
		return ((n - 1) & 1) == 0;
	}

	public int offRightMost(int n) {
		return n & (n - 1);
	}

	// Approach 1 & 2 are exactly same; Because -n and complement of (n-1) are same; or -n => 2's complement of n
	public int rightMostSetBit1(int n) {
		return n & -n;
	}

	public int rightMostSetBit2(int n) {
		return n & ~(n - 1);
	}

	// Returns the rightmost 1 in binary representation of x;
	public int findFirstSetBit(int data) {
		if (data == 0) return 0;
		int bit = 1;
		int count = 1;
		while ((data & bit) == 0) {
			bit <<= 1;
			count++;
		}
		return count;
	}

	// Number of 1 Bits or Hamming Weight This method counts no of ones in the given number
	// Approach1:
	public int hammingWeight1(int n) {
		int count = 0;
		while (n > 0) {
			count += n & 1;
			n = n >> 1;
		}
		return count;
	}

	// Approach2:
	public int hammingWeight2(int n) {
		int count = 0;
		while (n > 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	// Approach3: Handled for negative value or signed integer
	public int hammingWeight3(int n) {
		int count = 0;
		if (n < 0) {
			n = n & Integer.MAX_VALUE;
			count = 1;
		}
		while (n > 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	// Recursive Approach
	public int countSetBits3(int n) {
		if (n == 0) return 0;
		return (n % 2 == 1 ? 1 : 0)
				+ countSetBits3(n / 2);
	}

	// Find the sum of all bits from numbers 1 to N.
	public int countAllSetBits(int n) {
		int bitCount = 0;
		for (int i = 1; i <= n; i++) {
			// bitCount += Integer.bitCount(i); // Bit count using Java API
			bitCount += hammingWeight1(i);
		}
		return bitCount;
	}

	public int findMin(int a, int b) {
		// return a & ((a - b) >> 31)
		// | b & (~(a - b) >> 31);
		return (b ^ ((a ^ b) & ((a - b) >> 31)));
	}

	public int findMax(int a, int b) {
		return (a ^ ((a ^ b) & ((a - b) >> 31)));
	}

	// Using Bitwise operator
	public void swap1(int a, int b) {
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
		/*XOR property y^(x^y) = x.
		 *and x^(x^y) = y*/
	}

	// Using difference
	public void swap2(int a, int b) {
		a = a - b;
		b = a + b;
		a = a - b;
	}

	// Simple Approach
	public int swapOddEvenBits1(int n) {
		int evenBits = n & 0xAAAAAAAA;
		int oddBits = n & 0x55555555;
		evenBits >>>= 1;
		oddBits <<= 1;
		return (oddBits | evenBits);
	}

	// One line code of previous one
	public int swapOddEvenBits2(int n) {
		return ((n & 0xAAAAAAAA) >>> 1
				| (n & 0x55555555) << 1);
	}

}