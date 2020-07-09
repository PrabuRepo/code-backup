package com.web.codechef.problems;

import java.util.Scanner;

public class SpamPattern2 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Input:");
		int nonSpam, spam, n, minX, maxX, data;
		int[] result = new int[2];
		int t = scanner.nextInt();
		for (int i = 1; i <= t; i++) {// For each test case T
			nonSpam = 0;
			spam = 0;
			n = scanner.nextInt();
			minX = scanner.nextInt();
			maxX = scanner.nextInt();
			int[] w = new int[n + 1];
			int[] b = new int[n + 1];
			for (int j = 1; j <= n; j++) { // Get the data
				w[j] = scanner.nextInt();
				b[j] = scanner.nextInt();
			}
			for (int x = 0; x <= 1; x++) {
				for (int j = 1; j <= n; j++) { // Calculate Wx+b for 2 sets to find the pattern
					data = (j == 1) ? x : result[x];
					result[x] = w[j] * data + b[j];
				}
			}
			int range = (maxX-minX)+1;
			if (result[0] % 2 == 0 && result[1] % 2 == 0) {
				nonSpam = range;
			}else if(result[0] % 2 == 1 && result[1] % 2 == 1) {
				spam = range;
			}else if(result[0] % 2 == 0 && range%2 == 0) {
				nonSpam = range/2;
				spam = nonSpam;
			}else {
				if(result[0] % 2 == 0){
					spam = range/2;
					nonSpam = range - spam;
				}else {
					nonSpam = range/2;
					spam = range - nonSpam;
				}
			}
				
			System.out.println(nonSpam+" "+spam);
		}
		scanner.close();

	}

}
