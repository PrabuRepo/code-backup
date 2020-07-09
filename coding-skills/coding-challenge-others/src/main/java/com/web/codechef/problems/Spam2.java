package com.web.codechef.problems;

import java.util.Scanner;

class Spam2 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input:");
		long nonSpam, spam, n, minX, maxX, data;
		//System.out.println("Total Memory:"+Runtime.getRuntime().totalMemory());
		//System.out.println("Free Memory:"+Runtime.getRuntime().freeMemory());
		long mem1 = Runtime.getRuntime().freeMemory();
		long t = scanner.nextLong();
		for (int i = 1; i <= t; i++) {//For each test case T
			nonSpam = 0;
			spam = 0;
			n = scanner.nextLong();
			minX = scanner.nextLong();
			maxX = scanner.nextLong();
			long[] output = new long[(int) (maxX+1)];
			for (int j = 1; j <=n; j++) { //for N no of data
				int w = scanner.nextInt();
				int b = scanner.nextInt();
				for (int x = (int) minX; x <= maxX; x++) {
					data = 0;
					if(output[x] != 0)
						data = output[x];
					else
						data = x;
					output[x] = w * data+ b;
				}
			}
			for(int k =(int) minX; k<=maxX;k++) {
					if (output[k] % 2 == 0)
						nonSpam++;
					else
						spam++;
			}
			//System.out.println("Free Memory:"+Runtime.getRuntime().freeMemory());
			//long mem2 = Runtime.getRuntime().freeMemory();
			//System.out.println("Amout allocated for array:"+(mem1-mem2));
			System.out.println(nonSpam);
			System.out.println(spam);
			
		}
		scanner.close();
	}

}
