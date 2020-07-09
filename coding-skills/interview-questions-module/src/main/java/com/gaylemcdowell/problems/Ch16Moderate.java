package com.gaylemcdowell.problems;

public class Ch16Moderate {

	public static void main(String[] args) {
		Ch16Moderate ob = new Ch16Moderate();
		int a = 6, b = 11;
		System.out.println("Number swapper(Approach1): ");
		ob.numberSwapper1(a, b);
		System.out.println("Number swapper(Approach2): ");
		ob.numberSwapper2(a, b);
	}

	/*
	 * 1.Number Swapper: Write a function to swap a number in place (that is, without temporary variables).
	 */
	// Approach1: Using differences
	public void numberSwapper1(int a, int b) {
		System.out.println("Before swapping: " + a + ", " + b);
		a = a + b;
		b = a - b;
		a = a - b;

		// Both works
		/*a = a - b;
		b = a + b;
		a = b - a;*/
		System.out.println("After swapping: " + a + ", " + b);
	}

	// Approach2: using Bit manipulations
	public void numberSwapper2(int a, int b) {
		System.out.println("Before swapping: " + a + ", " + b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("After swapping: " + a + ", " + b);
	}
}
