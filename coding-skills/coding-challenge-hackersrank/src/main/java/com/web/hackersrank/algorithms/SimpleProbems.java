package com.web.hackersrank.algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SimpleProbems {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int appleCount = 0, orangeCount = 0;
		int s = in.nextInt();
		int t = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int m = in.nextInt();
		int n = in.nextInt();
		int[] apple = new int[m];
		for (int apple_i = 0; apple_i < m; apple_i++) {
			apple[apple_i] = in.nextInt();
			if ((a + apple[apple_i]) >= s && (a + apple[apple_i]) <= t)
				appleCount++;

		}
		int[] orange = new int[n];
		for (int orange_i = 0; orange_i < n; orange_i++) {
			orange[orange_i] = in.nextInt();
			if ((b + orange[orange_i]) >= s && (b + orange[orange_i]) <= t)
				orangeCount++;
		}
		System.out.println(appleCount);
		System.out.println(orangeCount);
		in.close();
	}

	static void solve() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] grades = new int[n];
		for (int grades_i = 0; grades_i < n; grades_i++) {
			grades[grades_i] = in.nextInt();
		}

		for (int i = 0; i < grades.length; i++)
			grades[i] = (grades[i] >= 38 && grades[i] % 5 >= 3) ? grades[i] + (5 - grades[i] % 5) : grades[i];
		// Solution using streams
		// Arrays.stream(grades).map(g -> g >= 38 && g % 5 >= 3 ? g + 5 - g % 5 :
		// g).forEach(System.out::println);
		// int[] result = Arrays.stream(grades).map(g -> g >= 38 && g % 5 >= 3 ? g + 5 -
		// g % 5 : g).toArray();

		for (int i = 0; i < grades.length; i++) {
			System.out.print(grades[i] + (i != grades.length - 1 ? "\n" : ""));
		}
		System.out.println("");
	}

	static String timeConversion(String s) {
		String militaryTime;
		String temp = s.substring(0, 8);
		StringTokenizer token = new StringTokenizer(temp, ":");
		int hh = Integer.valueOf(token.nextToken());
		String mm = token.nextToken();
		String ss = token.nextToken();
		if (s.endsWith("AM")) {
			militaryTime = (hh >= 1 && hh <= 11) ? temp : ("00" + ":" + mm + ":" + ss);
		} else {
			militaryTime = (hh >= 1 && hh <= 11) ? (hh + 12 + ":" + mm + ":" + ss) : temp;
		}
		return militaryTime;
	}

	static int birthdayCakeCandles(int n, int[] ar) {
		int count = 0, max = 0;
		for (int i = 0; i < n; i++) {
			if (ar[i] >= max) {
				count = (max == ar[i]) ? ++count : 1;
				max = ar[i];
			}
		}
		return count;
	}

	static void miniMaxSum(int[] arr) {
		long total = 0, min = 0, max = 0, diff;
		for (int i = 0; i < arr.length; i++)
			total += arr[i];

		min = total - arr[0];
		max = min;
		for (int i = 1; i < arr.length; i++) {
			diff = total - arr[i];
			if (min > diff)
				min = diff;
			if (max < diff)
				max = diff;
		}
		System.out.println(min + " " + max);
	}

	static void staircase(int input) {
		System.out.println("My solution:");
		for (int i = 0; i < input; i++) {
			for (int j = input - 1; j >= 0; j--) {
				if (j <= i)
					System.out.print("#");
				else
					System.out.print(" ");
			}
			System.out.println();
		}

		System.out.println("Solution with o(n) complexity:");
		StringBuilder str = new StringBuilder("#");
		for (int i = 0; i < input; i++) {
			System.out.printf("%" + input + "s\n", str);
			// Printf -> its a formatted string
			// % input s %n -> It should start with %, input -> user input(constant), s ->
			// Add "input" space before 'str', %n -> new line
			str = str.append("#");
		}
	}

	static void plusMinus(int[] arr) {
		int n = arr.length;
		int zeros = 0, pos = 0, neg = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] == 0) {
				zeros++;
			} else if (arr[i] > 0) {
				pos++;
			} else {
				neg++;
			}
		}

		System.out.println((float) pos / n);
		System.out.println((float) neg / n);
		System.out.println((float) zeros / n);
	}

	static int diagonalDifference(int[][] a) {
		int diff = 0;
		int n = a[0].length - 1;
		for (int i = 0; i < (n + 1) / 2; i++)
			diff += (a[i][i] + a[n - i][n - i]) - (a[i][n - i] + a[n - i][i]);
		return Math.abs(diff);
	}

	static void aVeryBigSum() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += scanner.nextInt();
		}
		System.out.println(sum);
		scanner.close();
	}
}