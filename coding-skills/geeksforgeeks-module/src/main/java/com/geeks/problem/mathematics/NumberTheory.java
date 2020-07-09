package com.geeks.problem.mathematics;

public class NumberTheory {

	public static void main(String[] args) {
		// System.out.println(9 % 11);
		System.out.println("GCD:" + gcd(2, 7));
		System.out.println("GCD(Iterative):" + gcdIterative(6, 10));
		System.out.println("LCM:" + lcm(126, 4));
		int[] a = { 2, 4, 6, 8, 16 };
		System.out.println("LCM for arrays:" + lcmForArray(a));
		System.out.println("GCD for arrays:" + gcdForArray(a));
		int y = -3;
		System.out.println("Modulo division: " + y % 2);
	}

	// Calculated using the formula -> [a*b = gcd(a,b) * lcm(a,b)]
	// Least Common Multiplier (LCM)
	static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}

	// GCD is calculated using Euclid's algorithm;
	// Greatest Common Divisor or Greatest common Factor or Highest Common Factor
	// (GCD or GCF or HCF)
	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static int gcdIterative(int a, int b) {
		if (b != 0) {
			int temp;
			while (b > 0) {
				temp = a % b;
				a = b;
				b = temp;
			}
		}
		return a;
	}

	// Other way to calculate the GCD;
	static int gcd2(int a, int b) {
		// Everything divides 0
		if (a == 0 || b == 0)
			return 0;

		// base case
		if (a == b)
			return a;

		// a is greater
		if (a > b)
			return gcd2(a - b, b);
		return gcd2(a, b - a);
	}

	static int lcmForArray(int[] a) {
		int result = 0;
		result = lcm(a[0], a[1]);
		for (int i = 2; i < a.length; i++) {
			result = lcm(result, a[i]);
		}
		return result;

	}

	static int gcdForArray(int[] a) {
		int result = 0;
		result = a[0];
		for (int i = 1; i < a.length; i++) {
			result = gcd(result, a[i]);
		}
		return result;

	}

	public static int reverse(int x) {
		long result = 0, rem;
		while (x != 0) {
			result = (result * 10) + (x % 10);
			x /= 10;
			System.out.println(result);
		}
		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
			return 0;
		return (int) result;
	}

}
