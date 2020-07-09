package com.web.codechef.problems;

import java.util.Scanner;

public class SpamPattern {

	public static void main(String[] args) throws Exception {
		//Scanner scanner = new Scanner(System.in);
		FastIO scanner = new FastIO();
		System.out.println("Input:");
		int n, minX, maxX;
		int t = scanner.nextInt();
		for (int i = 1; i <= t; i++) {
			n = scanner.nextInt();
			minX = scanner.nextInt();
			maxX = scanner.nextInt();

			int x0 = 0, x1 = 1, result = 0, k, l;
			int spam = 0, nonSpam = 0;
			for (int j = 1; j <= n; j++) {
				int w = scanner.nextInt();
				int b = scanner.nextInt();
				x0 = ((x0 * w) + b) % 2;
				x1 = ((x1 * w) + b) % 2;
				// System.out.println(x0 + " " + x1 );
			}
			int range = (maxX - minX) + 1;
			if (x0 == 0 && x1 == 0)
				nonSpam = range;
			else if (x0 == 1 && x1 == 1)
				spam = range;
			else if (x0 == 0 && range % 2 == 0) {
				nonSpam = range / 2;
				spam = nonSpam;
			} else {
				if ((x1 == 0 && minX % 2 == 0) || (x1 == 1 && minX % 2 == 1)) {
					// odd > even
					nonSpam = range / 2;
					spam = range - nonSpam;
				} else if ((x1 == 0 && minX % 2 == 1) || (x1 == 1 && minX % 2 == 0)) {
					// even > odd
					spam = range / 2;
					nonSpam = range - spam;
				}
			}
			System.out.println(nonSpam + " " + spam);
		}
		scanner.close();
	}

}

/*
 * 
 * k = range / 2; //4 3 if (minX % 2 == 0) k++; //4
 * 
 * l = range - k; //4 3
 * 
 * if (sample1 == 0) { result += k; } if (sample2 == 0) { result += l; //4 3 }
 * System.out.println(result + " " + (range - result)); //4,4 / 3, 4
 */

/*
 * java.io.BufferedReader reader = new java.io.BufferedReader(new
 * java.io.InputStreamReader(System.in)); System.out.println("Input:"); int n,
 * minX, maxX; int t = Integer.parseInt(reader.readLine()); for (int i = 1; i <=
 * t; i++) {// For each test case T n = Integer.parseInt(reader.readLine());
 * minX = Integer.parseInt(reader.readLine()); maxX =
 * Integer.parseInt(reader.readLine()); int sample1 = 0, sample2 = 1, result =
 * 0, k, l; for (int j = 1; j <= n; j++) { sample1 = (sample1 *
 * Integer.parseInt(reader.readLine())) % 2; sample2 = (sample2 *
 * Integer.parseInt(reader.readLine())) % 2; } int range = (maxX - minX) + 1; k
 * = range / 2; if (minX % 2 == 0) k++;
 * 
 * l = range - k;
 * 
 * if (sample1 == 0) { result += k; } if (sample2 == 0) { result += l; }
 * System.out.println(result + " " + (range - result)); } reader.close();
 * 
 */
