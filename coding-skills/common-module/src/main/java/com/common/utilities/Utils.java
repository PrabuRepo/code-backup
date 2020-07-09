package com.common.utilities;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

	// Find the min among three numbers
	public static int min(int a, int b, int c) {
		return (a < b && a < c) ? a : (b < c) ? b : c;
	}

	// Find the max among three numbers
	public static int max(int a, int b, int c) {
		return (a > b && a > c) ? a : (b > c) ? b : c;
	}

	// Find the max among four numbers
	public static int max(int a, int b, int c, int d) {
		return (a > b && a > c && a > d) ? a : (b > c && b > d) ? b : (c > d) ? c : d;
	}

	// Find the maximum in the array
	public static int max(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (max < arr[i])
				max = arr[i];
		return max;
	}

	// Swap the (int)elements in index i & j
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// Swap the (char)elements in index i & j
	public static void swap(char[] charArray, int i, int j) {
		char temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
	}

	// Swap the (String)elements in index i & j
	public static String swap(String str, int pos1, int pos2) {
		char[] arr = str.toCharArray();
		char temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
		return new String(arr);
	}

	// Convert the array to list
	public static List<Integer> convertArrayToList(int[] a) {
		List<Integer> list = new ArrayList<>();
		for (int i : a)
			list.add(i);
		return list;
		// Using stream & lambda expression
		// return Arrays.stream(a).boxed().collect(Collectors.<Integer>toList());
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	// Print the 2-Dim Array
	public static void print2DArray(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(Arrays.toString(data[i]) + ", ");
		}
	}

	// A utility function to print a polynomial
	public static void printPoly(int poly[]) {
		int n = poly.length;
		for (int i = 0; i < n; i++) {
			System.out.print(poly[i]);
			if (i != 0)
				System.out.print("x^" + i);
			if (i != n - 1)
				System.out.print(" + ");
		}
	}

	// Method to print int matrix
	public static void printMatrix(int[][] data) {
		int row = data.length;
		int col = data[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Method to print char matrix
	public static void printMatrix(char[][] data) {
		int row = data.length;
		int col = data[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Check given char is vowel or not?
	public static boolean isVowel(char ch) {
		return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
	}

	// Find the gcd of given two numbers
	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	// utility function to find lcm
	public static int lcm(int a, int b) {
		return a * b / Utils.gcd(a, b);
	}

	// Reverse all
	public static void reverse(int[] arr) {
		int last = arr.length - 1;
		int middle = arr.length / 2;
		for (int i = 0; i <= middle; i++) {
			int temp = arr[i];
			arr[i] = arr[last - i];
			arr[last - i] = temp;
		}
	}

	// Reverse the array of integers
	public static void reverse(int[] nums, int start, int end) {
		while (start < end)
			swap(nums, start++, end--);
	}

	// Reverse the String
	public static String reverse(String str, int start, int end) {
		char[] arr = str.toCharArray();
		while (start < end)
			swap(arr, start++, end--);
		return new String(arr);
	}

	// Find the log base 2 value of given value
	public static int log2(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		return 31 - Integer.numberOfLeadingZeros(n);
	}

	public static void checkISOChars() {
		String str = "JEDINY /LUK�S";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			System.out.println(ch + ": " + (int) ch + " -> " + Charset.forName("ISO_8859_1").newEncoder().canEncode(ch));
		}
	}

	public static void printASCIIchars(int n) {
		System.out.println("Char " + " Value");
		for (int i = 0; i < n; i++) {
			System.out.println("  " + i + "    " + (char) i);
		}
	}
}