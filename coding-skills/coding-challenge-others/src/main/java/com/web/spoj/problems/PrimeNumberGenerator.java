package com.web.spoj.problems;

import java.util.Scanner;

public class PrimeNumberGenerator {

	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in);
			int t = sc.nextInt();
			int[] a = new int[100];
			int[] c = new int[100];
			int m, n, count, ln = 0, k = 0;
			if (t > 10) {
				System.out.println("Invalid Input");
				System.exit(0);
			}
			for (int i = 0; i < t; i++) {
				m = sc.nextInt();
				n = sc.nextInt();
				for (int j = m; j <= n; j++) {
					count = 0;
					if (j != 1) {
						if (j == 2) {
							a[ln] = j;
							ln++;
						} else {
							for (int l = 2; l <= j / 2; l++) {
								if (j % l == 0) {
									count++;
								}

							}
							if (count == 0) {
								a[ln] = j;
								ln++;
							}
						}
					}
					if (j == n) {
						c[k] = ln;
						k++;
					}
				}
			}
			int p = 0;
			for (int i = 0; i < ln; i++) {
				System.out.println(a[i]);
				if (c[p] == i + 1) {
					System.out.println("");
					p++;
				}
			}
		} catch (Exception e) {

		}

	}
}
