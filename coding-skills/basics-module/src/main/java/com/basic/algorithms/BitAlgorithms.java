package com.basic.algorithms;

public class BitAlgorithms {

	public static void main(String[] args) {
		BitAlgorithms ob = new BitAlgorithms();

		ob.testBasicIdeas();

		ob.testSimpleTricks();

	}

	/************* Test Methods ***************/
	public void testBasicIdeas() {
		System.out.println("Basic numbers and binary reperesentation:");
		printBits();

		System.out.println("Bitwise Operations :");
		bitwiseOperator(4, 8);

		System.out.println("Test Bitwise And Operations(To check the odd/even no's): ");
		testBitwiseAnd();

		System.out.println("To undestand the some random value to 2th power and no of bytes");
		findMemoryInfo(1000000000); // 1000000000 - 1billion bytes
	}

	public void testSimpleTricks() {
		System.out.println("Integer.MAX_VALUE: " + intMaxValue());
		System.out.println("Integer.MIN_VALUE: " + intMinValue());
		System.out.println("Long.MAX_VALUE: " + longMaxValue());
		System.out.println("Long.MIN_VALUE: " + longMinValue());
		System.out.println("Set nth bit:" + setNthBit(3, 2));
		System.out.println("Check whether nth bit is set or not? " + isSetBit(16, 5));
		System.out.println("UnSet nth bit:" + unSetNthBit(15, 3));
		System.out.println("Toggle Nth bit:" + toggleNthBit(10, 3));
		System.out.println("Left Rotations:" + leftRotation(128, 3));
		System.out.println("Right Rotations:" + rightRotation(128, 3));
		System.out.println("2^n(2 power n) Value:" + twoPowerOfN(5));
		System.out.println("Check the Power of 2:" + checkPowerOfTwo(64));
		System.out.println("Given numbers are equal? " + checkEquality(24, 24));
		System.out.println("Is even number? " + isEven(7));
		System.out.println("Is Odd number? " + isOdd1(21));
		System.out.println("Is Odd number? " + isOdd2(21));
		System.out.println("Off the right most bit: " + offRightMost(14));
		System.out.println("Rightmost 1 in binary representation: " + rightMostSetBit1(12));
		System.out.println("Find first set bit: " + findFirstSetBit(12));
		System.out.println("Number of ones(Hamming Weight):" + hammingWeight1(424732));
		System.out.println("Number of ones(Hamming Weight): " + hammingWeight2(424732));
		System.out.println("Number of ones(Hamming Weight): " + hammingWeight3(424732));
		System.out.println("Count no of set bits 1 to given number:" + countAllSetBits(4));
		System.out.println("Find Min:" + findMin(200, 90));
		System.out.println("Find Max:" + findMax(200, 90));
		System.out.println("Swap without using temp variable1:");
		swap1(2905, 6079);
		System.out.println("Swap without using temp variable2:");
		swap2(2905, 6079);
	}

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

	public void printBits() {

		System.out.println("Positive numbers binary representation:");
		System.out.println("1: " + Integer.toBinaryString(1));
		System.out.println("2: " + Integer.toBinaryString(2));
		System.out.println("4: " + Integer.toBinaryString(4));
		System.out.println("8: " + Integer.toBinaryString(8));
		System.out.println("16: " + Integer.toBinaryString(16));
		System.out.println("32: " + Integer.toBinaryString(32));

		System.out.println("1's Complement of given number and binary representation:");
		System.out.println("1: " + Integer.toBinaryString(~1));
		System.out.println("2: " + Integer.toBinaryString(~2));
		System.out.println("3: " + Integer.toBinaryString(~3));
		System.out.println("4: " + Integer.toBinaryString(~4));
		System.out.println("8: " + Integer.toBinaryString(~8));

		System.out.println("Same Positive & Negative number binary representation:");
		System.out.println("Positive No: " + Integer.toBinaryString(7));
		System.out.println("Negative No: " + Integer.toBinaryString(-7));

		/* Two way of Negative number/2's Complement representation(Negation of N = 2's complement of N; ie -N = 2's complement of N):
		 * 1. Directly change the sign of N - binary representation of -N (negative N)
		 * 2. 1's Complement + 1  
		 */
		System.out.println("Negative number/2's Complement of N:(binary representation):");
		System.out.println("-1/2's Complement of 1: " + Integer.toBinaryString(-1) + " " + Integer.toBinaryString(~1 + 1));
		System.out.println("-2/2's Complement of 2: " + Integer.toBinaryString(-2) + " " + Integer.toBinaryString(~2 + 1));
		System.out.println("-3/2's Complement of 3: " + Integer.toBinaryString(-3) + " " + Integer.toBinaryString(~3 + 1));
		System.out.println("-4/2's Complement of 4: " + Integer.toBinaryString(-4) + " " + Integer.toBinaryString(~4 + 1));
		System.out.println("-5/2's Complement of 5: " + Integer.toBinaryString(-5) + " " + Integer.toBinaryString(~5 + 1));
		System.out.println("-8/2's Complement of 6: " + Integer.toBinaryString(-8) + " " + Integer.toBinaryString(~8 + 1));

		System.out.println("Negative number/2's Complement of N:(Decimal representation):");
		System.out.println("-1/2's Complement of 1: " + (-1) + " " + (~1 + 1));
		System.out.println("-2/2's Complement of 2: " + (-2) + " " + (~2 + 1));
		System.out.println("-3/2's Complement of 3: " + (-3) + " " + (~3 + 1));
		System.out.println("-4/2's Complement of 4: " + (-4) + " " + (~4 + 1));
		System.out.println("-5/2's Complement of 5: " + (-5) + " " + (~5 + 1));
		System.out.println("-8/2's Complement of 6: " + (-8) + " " + (~8 + 1));

		System.out.println("To understand Byte, Short & Integer Range: ");
		/*
		 * byte	8(1signed bit + 7 bits): from -128(2^7) to 127(2^7-1)
		 * short 16(1signed bit + 15 bits): from -32,768(2^15) to 32,767(2^15-1) 
		 * int 32(1signed bit + 31 bits) : from -(2^31) to (2^31-1) 
		*/
		System.out.println("Byte MAX: +127: " + Integer.toBinaryString(127)); // 0111 1111
		System.out.println("Byte MIN: -128: " + Integer.toBinaryString(-128)); // 1000 0000
		System.out.println("Short MAX: +32767: " + Integer.toBinaryString(32767)); // 0111 1111 1111 1111
		System.out.println("Short MIN: -32768: " + Integer.toBinaryString(-32768)); // 1000 0000 0000 0000
		System.out.println("Integer MAX: +2147483647: " + Integer.toBinaryString(2147483647)); // 0111 1111 1111 1111
		// 1111 1111 1111 1111
		System.out.println("Integer MIN: -2147483648: " + Integer.toBinaryString(-2147483648)); // 1000 0000 0000 0000
		// 0000 0000 0000 0000
		// The number 0xAAAAAAAA is a 32 bit number with all even bits set as 1 and all odd bits as 0.
		System.out.println("All Odd set bits: " + Integer.toBinaryString(0xAAAAAAAA));
		// The number 0x55555555 is a 32 bit number with all odd bits set as 1 and all even bits as 0.
		System.out.println("All Even set bits: " + Integer.toBinaryString(0x55555555));
		// To make all 1 bits; Eg: To get 1111; n=4; > 10000 -1
		int n = 4;
		System.out.println("To make n '1' bits: " + Integer.toBinaryString((1 << n) - 1));
	}

	public void bitwiseOperator(int a, int b) {
		/*Bitwise and Bit Shift Operators
		 ********************************
		~       Unary bitwise complement
		<<      Signed left shift
		>>      Signed right shift
		>>>     Unsigned right shift
		&       Bitwise AND
		^       Bitwise exclusive OR
		|       Bitwise inclusive OR*/
		System.out.println("Bitwise AND Operation:" + Integer.toBinaryString(a & b));
		System.out.println("Bitwise OR Operation:" + Integer.toBinaryString(a | b));
		System.out.println("Bitwise XOR Operation:" + Integer.toBinaryString(a ^ b));
		System.out.println("Bitwise Negation or Complement Operation:" + Integer.toBinaryString(~b));
		System.out.println("Left shift operator:" + (b << 1));

		/* >> (Signed right shift) In Java, the operator ‘>>’ is signed right shift operator. All integers are signed in Java, and
		 * it is fine to use >> for negative numbers. The operator ‘>>’ uses the sign bit (left most bit) to fill the trailing
		 * positions after shift. If the number is negative, then 1 is used as a filler and if the number is positive, then 0 
		 * is used as a filler.*/
		/* >>> (Unsigned right shift) In Java, the operator ‘>>>’ is unsigned right shift operator. It always fills 0 
		 * irrespective of the sign of the number. 
		 * >> Vs >>>:  ">>>" shifts a zero into the leftmost position, while the leftmost position after ">>" depends on sign*/
		System.out.println("Signed Right shift operator(>>)   :" + Integer.toBinaryString(-1 >> 2));
		System.out.println("Unsigned Right shift operator(>>>):" + Integer.toBinaryString(-1 >>> 2));
		System.out.println("Signed Right shift operator(>>)   :" + Integer.toBinaryString(Integer.MAX_VALUE >> 2));
		System.out.println("Unsigned Right shift operator(>>>):" + Integer.toBinaryString(Integer.MAX_VALUE >>> 2));
	}

	public void testBitwiseAnd() {
		int i = 0;
		// To check the odd/even no's
		while (i < 16) {
			System.out.println(i + " -> " + (i & 1));
			i++;
		}
	}

	public void findMemoryInfo(int n) { // n- Input in Bytes
		int twoPowN = (int) Math.ceil(Math.log(n) / Math.log(2)); // From some value to 2th power
		double bytes = Math.pow(2, twoPowN);
		double bits = 8 * bytes; // 1byte = 8bits
		System.out.println("For the input n: " + n + "-> 2th Power: " + twoPowN + "; Bytes: " + bytes + "; Bits: " + bits);
	}

	/************** Bit Manipulations - Simple tricks ******************/

	public int intMaxValue() {
		int max = 0;
		max = ~max; // 1111 1111 1111 1111 1111 1111 1111 1111 - 32 bits
		max = max >>> 1; // 0111 1111 1111 1111 1111 1111 1111 1111 -> use (>>>)unsigned right shift operator
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
		max = ~max; // 1111 1111 1111 1111 1111 1111 1111 1111 - 32 bits
		max = max >>> 1; // 0111 1111 1111 1111 1111 1111 1111 1111
		int min = ~max; // 1000 0000 0000 0000 0000 0000 0000 0000
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

	public int toggleBitsRange(int data, int l, int r) {
		int testBit = 1 << (l - 1);
		while (l <= r) {
			data = data ^ testBit;
			testBit <<= 1;
			l++;
		}
		return data;
	}

	// Each Left Rotation will be multiplied by 2
	public int leftRotation(int data, int noOfRotations) {
		return data << noOfRotations;
	}

	// Each Right Rotation will be divided by 2
	public int rightRotation(int data, int noOfRotations) {
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

	static int BITS_16 = 16;
	static int BITS_32 = 32;

	// For 32 bits
	public static int rotateLeft1(int n, int d) {
		/* In n<<d, last d bits are 0. To put first 3 bits of n at last, do bitwise or of n<<d with n >>(INT_BITS - d) */
		return ((n << d) | (n >> BITS_32 - d));
	}

	// For 16 Bits
	public static int rotateLeft2(int n, int d) {
		if (d > 16) // To handle d is more than 16
			d = d % BITS_16;
		/* In n<<d, last d bits are 0. To put first 3 bits of n at last, do bitwise or of n<<d with n >>(INT_BITS - d) */
		// & 0xFFFF -> To clear elements more than 16 bits
		return ((n << d) | (n >> BITS_16 - d)) & 0xFFFF;
	}

	// For 32 bits
	public static int rotateRight1(int n, int d) {
		/* In n>>d, first d bits are 0. To put last 3 bits of at first, do bitwise or of n>>d with n <<(INT_BITS - d) */
		return ((n >> d) | (n << BITS_32 - d));
	}

	// For 16 bits
	public static int rotateRight2(int n, int d) {
		if (d > 16) // To handle d is more than 16
			d = d % BITS_16;
		/* In n>>d, first d bits are 0. To put last 3 bits of at first, do bitwise or of n>>d with n <<(INT_BITS - d) */
		return ((n >> d) | (n << BITS_16 - d)) & 0xFFFF;
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
		if (data == 0)
			return 0;

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
		// Handle for negative value or signed integer
		if (n < 0) {
			n = n & Integer.MAX_VALUE; // Max Value: 0111 1111 1111 1111 1111 1111 1111 1111
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
		if (n == 0)
			return 0;
		return (n % 2 == 1 ? 1 : 0) + countSetBits3(n / 2);
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
		// return a & ((a - b) >> 31) | b & (~(a - b) >> 31);
		return (b ^ ((a ^ b) & ((a - b) >> 31)));
	}

	public int findMax(int a, int b) {
		return (a ^ ((a ^ b) & ((a - b) >> 31)));
	}

	// Using Bitwise operator
	public void swap1(int a, int b) {
		System.out.println("Before swap a, b:" + a + ", " + b);
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
		System.out.println("After swap a, b:" + a + ", " + b);
		/*How it works? - Because of XOR property y^(x^y) = x. and x^(x^y) = y.
		 (be aware that it is not actually faster than the general extra variable swap, as it can’t achieve instruction parallelism.)*/
	}

	// Using difference
	public void swap2(int a, int b) {
		System.out.println("Before swap a, b:" + a + ", " + b);
		a = a - b;
		b = a + b;
		a = a - b;
		System.out.println("After swap a, b:" + a + ", " + b);
		/*How it works? - Because of XOR property y^(x^y) = x. and x^(x^y) = y.
		 (be aware that it is not actually faster than the general extra variable swap, as it can’t achieve instruction parallelism.)*/
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
		return ((n & 0xAAAAAAAA) >>> 1 | (n & 0x55555555) << 1);
	}

}