package com.geeks.problem.algorithms.test;

import com.common.model.Point;
import com.geeks.problem.algorithms.MathAlgorithms;

public class TestMathAlgorithms extends MathAlgorithms {
	public static void main(String[] args) {
		TestMathAlgorithms ob = new TestMathAlgorithms();

		ob.testBasicMathProblems();

		// ob.testConversionProblems();

		// ob.testArithmeticOperations();

		// ob.testMatrix();

		// ob.testMiscProblems();

	}

	public void testBasicMathProblems() {
		System.out.println("Prime Numbers: ");
		generatePrimeNumbers(50);

		System.out.println("\nFactorial Number: " + factorial1(6));

		System.out.println("Fibonacci Series: ");
		fibonacci2(7);

		int[] a = { 2, 7, 3, 9, 4 };
		System.out.println("\nGreatest Common Divisior(GCD): ");
		System.out.println("GCD(Recursive):" + gcd1(36, 24));
		System.out.println("GCD(Iterative):" + gcd2(6, 10));
		System.out.println("GCD(Other Approach):" + gcd2(20, 15));
		System.out.println("GCD for arrays:" + gcdForArray(a));

		System.out.println("Least Common Multiplier(LCM): " + lcm(126, 4));
		System.out.println("LCM for arrays:" + lcmForArray(a));

		System.out.println("Pascal Triangle: ");
		displayPascalTriangle(5);

		System.out.println("Check if a number can be expressed as x^y: " + isPower1(1024));
		System.out.println("Check if a number can be expressed as x^y: " + isPower2(1024));
	}

	public void testConversionProblems() {
		System.out.println("Decimal to Binary: " + decimalToBin(927));
		System.out.println("Binary to Decimal: " + BinToDecimal("1110011111"));

		System.out.println("Roman to Integer: " + romanToInt("MCMXCIV")); // MCMXCIV - 1994
	}

	public void testArithmeticOperations() {
		System.out.println("Sum: " + sum1(15, 15));
		System.out.println("Sum: " + sum2(15, 15));

		System.out.println("Sub: " + sub1(15, 19));
		System.out.println("Sub: " + sub2(15, 19));

		System.out.println("Math.Div: " + Math.floorDiv(-93298, 4275));
		System.out.println("Div: " + div1(-93298, 4275));
		System.out.println("Div: " + div2(4, 2));

		System.out.println("Power of num(m^n): " + pow4(3, -4));
		System.out.println("Power of num(m^n): " + modularExponentiation(2, 10, 9));

		System.out.println("Is power of 2: " + isPowerOfTwo1(32));
		System.out.println("Is power of 3: " + isPowerOfThree2(25));

		System.out.println("Next power of N: " + nextPowerOfN(57));

		System.out.println("Square root: ");
		for (int i = 2147483647; i == 2147483647; i++) {
			// Highest value: 2147395600
			System.out.println("Sqrt of " + i + " - " + sqrt2(i));
			System.out.println("Math.sqrt: " + Math.sqrt(i));
		}
	}

	void testMatrix() {
		int[][] a1 = { { 12, 5, 8 }, { 11, 9, 16 }, { 17, 18, 19 } };
		int[][] b1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] a2 = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
		int[][] b2 = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
		int[][] a3 = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 } };
		int[][] test = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 }, { 13, 14, 15, 16, 17, 18 } };

		int[][] result;

		result = addMatrix(a2, b2);
		System.out.println("Matrix addition:");
		printMatrix(result);

		result = subMatrix(a1, b1);
		System.out.println("Matrix subraction:");
		printMatrix(result);

		result = mulMatrix(a2, b2);
		System.out.println("Matrix Multiplication:");
		printMatrix(result);

		System.out.println("Original Matrix:");
		printMatrix(a3);
		System.out.println("Transpose of matrix:");
		printMatrix(transposeMatrix(a3));

	}

	public void testMiscProblems() {

		System.out.println(isHappynumber(20));

		Point[] points = { new Point(0, 0), new Point(94911151, 94911150), new Point(94911152, 94911151) };
		System.out.println("Max points on a line: " + maxPoints(points));

		System.out.println("Lexical Order: " + lexicalOrder(1000));

	}

}
