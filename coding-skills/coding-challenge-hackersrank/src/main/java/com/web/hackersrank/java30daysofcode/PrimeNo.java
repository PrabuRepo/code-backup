package com.web.hackersrank.java30daysofcode;

import java.util.Scanner;

public class PrimeNo {

	public static void main(String[] args) {
		System.out.println("Find Prime no with(o(n) time comlexity:");
		primeNoApproach1();
		System.out.println("Find Prime no with(o(sqrt(n) time comlexity:");
		primeNoApproach2();
	}

	public static void primeNoApproach1() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while (n-- > 0) {
			int data = in.nextInt();
			if (data == 1)
				System.out.println("Not prime");
			else if (data == 2)
				System.out.println("Prime");
			else {
				boolean flag = true;
				for (int i = 2; i < data; i++) {
					if (data % i == 0) {
						flag = false;
						break;
					}
				}
				if (flag)
					System.out.println("Prime");
				else
					System.out.println("Not prime");
			}
		}
		in.close();

	}

	public static void primeNoApproach2() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while (n-- > 0) {
			int data = in.nextInt();
			int rootValue = (int) Math.sqrt((double) data);

			if (data == 1)
				System.out.println("Not prime");
			else if (data == 2)
				System.out.println("Prime");
			else {
				boolean flag = true;
				for (int i = 2; i <= rootValue; i++) {
					if (data % i == 0) {
						flag = false;
						break;
					}
				}
				if (flag)
					System.out.println("Prime");
				else
					System.out.println("Not prime");
			}
		}
		in.close();
	}

}
