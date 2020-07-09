package com.web.codechef.problems;

import java.util.Scanner;

class Spam {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input:");
		int nonSpam, spam, n, minX, maxX, data;
		int t = scanner.nextInt();
		for (int i = 1; i <= t; i++) {// For each test case T
			nonSpam = 0;
			spam = 0;
			n = scanner.nextInt();
			minX = scanner.nextInt();
			maxX = scanner.nextInt();
			int[] w = new int[n+1];
			int[] b = new int[n+1];
			for (int j = 1; j <= n; j++) { // for N no of data
				w[j] = scanner.nextInt();
				b[j] = scanner.nextInt();
			}
			// int[] output = new int[maxX+1];
			for (int x = minX; x <= maxX; x++) {
				int total = 0;
				for (int j = 1; j <= n; j++) { // for N no of data
					data = (j==1)?x:total;
					total = w[j] * data + b[j];
				}
				if(total%2 == 0)
					nonSpam++;
				else
					spam++;
			}
			System.out.println(nonSpam);
			System.out.println(spam);

		}
		scanner.close();
	}
}

/*
 * Scanner scanner = new Scanner(System.in); System.out.println("Input:"); int
 * nonSpam, spam, n, minX, maxX, data;
 * System.out.println("Total Memory:"+Runtime.getRuntime().totalMemory());
 * System.out.println("Free Memory:"+Runtime.getRuntime().freeMemory()); long
 * mem1 = Runtime.getRuntime().freeMemory(); int t = scanner.nextInt(); for (int
 * i = 1; i <= t; i++) {//For each test case T nonSpam = 0; spam = 0; n =
 * scanner.nextInt(); minX = scanner.nextInt(); maxX = scanner.nextInt(); int[]
 * output = new int[maxX+1]; for (int j = 1; j <=n; j++) { //for N no of data
 * int w = scanner.nextInt(); int b = scanner.nextInt(); for (int x = minX; x <=
 * maxX; x++) { data = 0; if(output[x] != 0) data = output[x]; else data = x;
 * output[x] = w * data+ b; } } for(int k =minX; k<=maxX;k++) { if (output[k] %
 * 2 == 0) nonSpam++; else spam++; }
 * System.out.println("Free Memory:"+Runtime.getRuntime().freeMemory()); long
 * mem2 = Runtime.getRuntime().freeMemory();
 * System.out.println("Amout allocated for array:"+(mem1-mem2));
 * System.out.println(nonSpam); System.out.println(spam);
 * 
 * } scanner.close();
 */