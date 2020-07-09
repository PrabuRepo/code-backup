package com.web.hackersrank.java30daysofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sample {

	public static void main(String[] args) {
		int t = 5;
		System.out.println(t);
		int a1[] = { -1, 9, 1 };
		print(3, 2, a1);

		int a2[] = { -5, 0, 4, 5, 3 };
		print(5, 2, a2);

		int a3[] = { -1, -3, 4, 5, 3, 9, 12 };
		print(7, 5, a3);

		int a4[] = { -1, 0, -4, 5, -3, 9, -4, -7, -3 };
		print(9, 6, a4);

		int a5[] = { 3, 2, -3, 9, -5, -7, -1, 0, 4, 5 };
		print(10, 8, a5);

		System.out.println();

		testCaseChallenge(3, 2, a1);
		testCaseChallenge(5, 2, a2);
		testCaseChallenge(7, 5, a3);
		testCaseChallenge(9, 6, a4);
		testCaseChallenge(10, 8, a5);

	}

	public static void print(int n, int k, int[] a) {
		System.out.println(n + " " + k);
		Arrays.stream(a).forEach(num -> System.out.print(num + " "));
		System.out.println();
	}

	public static void testCaseChallenge(int n, int k, int[] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] <= 0)
				count++;
		}

		if (count >= k)
			System.out.println("NO");
		else
			System.out.println("YES"); // Cancelled

	}

	public static void bitManipulation() {

		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int max = 0;
			int n = in.nextInt();
			int k = in.nextInt();
			for (int a = 1; a < n - 1; a++) {
				for (int b = a + 1; b <= n; b++) {
					int val = a & b;
					if (val > max && val < k)
						max = val;
				}
			}
			System.out.println(max);
		}
		in.close();

		/*	
		 * Better solution :
		Scanner	in	= new Scanner(System.in);
		int t = in.nextInt();
		
		for(
		int i = 0;i<t;i++)
		{
			int n = in.nextInt();
			int k = in.nextInt();
			if ((k - 1 | k) <= n) {
				System.out.println(k - 1);
			} else {
				System.out.println(k - 2);
			}
		}
		*/
	}

	public static void regExHackersRankProb() {
		String namePattern = "[a-z]{1,20}";
		String emailPattern = "[a-z.]{1,40}+@gmail+.[a-z]{3}";
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		List<String> data = new ArrayList<>();
		for (int a0 = 0; a0 < N; a0++) {
			String firstName = in.next();
			String emailID = in.next();
			if (isValidInput(emailPattern, emailID) && isValidInput(namePattern, firstName)) {
				data.add(firstName);
			}
		}
		Collections.sort(data);
		data.forEach(System.out::println);
		in.close();
	}

	public static boolean isValidInput(String regEx, String input) {
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public void nestedLogic() {

		Scanner in = new Scanner(System.in);
		int actualDay = in.nextInt();
		int actualMonth = in.nextInt();
		int actualYear = in.nextInt();

		int expectedDay = in.nextInt();
		int expectedMonth = in.nextInt();
		int expectedYear = in.nextInt();
		int fine = 0;
		boolean sameYear = false, sameMonth = false;
		if (expectedYear == actualYear)
			sameYear = true;
		if (expectedMonth == actualMonth)
			sameMonth = true;

		if (expectedYear < actualYear) {
			fine = 10000;
		} else if (expectedMonth < actualMonth && sameYear) {
			fine = 500 * (actualMonth - expectedMonth);
		} else if (expectedDay < actualDay && sameMonth) {
			fine = 15 * (actualDay - expectedDay);
		} else {
			fine = 0;
		}
		System.out.println(fine);

	}

}
