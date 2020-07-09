package com.geeksforgeeks.ds.problems;

public class ArrayGeeksProblems {

	// Array Rotations
	public static void arrayRotationsOnebyOne(int[] a, int d) {
		int n = a.length;
		if (d < n) {
			for (int i = 0; i < d && i < n; i++) {
				int temp = a[0];

				for (int j = 1; j < n; j++)
					a[j - 1] = a[j];

				a[n - 1] = temp;
			}
		} else {
			System.out.println("Rotation is not required");
		}
	}

	public static void arrayRotationJugglingAlg(int[] a, int d) {
		int gcd = gcd(a.length, d);
		if (gcd > 1) {
			int j = 0;
			while (j < gcd) {
				for (int i = j; i <= (a.length / gcd + j); i = i + gcd) {
					swap(a, i, i + gcd);
				}
				j++;
			}
		} else {
			arrayRotationsOnebyOne(a, d);
		}
	}

	private static void swap(int[] a, int pos1, int pos2) {
		int temp = a[pos1];
		a[pos1] = a[pos2];
		a[pos2] = temp;
	}

	private static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.println("Array before rotation:");

		printArray(a);

		// Array Rotations one by one approach
		// arrayRotationsOnebyOne(a, 7);
		// Array Rotations Juggling approach
		arrayRotationJugglingAlg(a, 6);
		System.out.println("\nArray after rotation: ");
		printArray(a);
	}

}
