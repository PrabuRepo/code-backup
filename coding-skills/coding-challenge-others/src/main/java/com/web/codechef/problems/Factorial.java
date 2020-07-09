package com.web.codechef.problems;

import java.util.Scanner;

class Factorial {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-- > 0) {
			double fact = 1;
			int n = scanner.nextInt();
			for (int j = n; j > 0; j--) {
				fact = fact * j;
			}
			System.out.println(fact);
		}
		scanner.close();
	}

	static void factNormalApproach(Scanner scanner) {
		double fact = 1;
		int n = scanner.nextInt();
		for (int j = n; j > 0; j--) {
			fact = fact * j;
		}
		String temp = String.format("%f", fact);
		System.out.println(temp);
		System.out.println("Length of factorial:" + temp.length());
	}
	
	static void factEfficientApproach(Scanner scanner) {

		int temp = 0, x = 0, count = 0;
		int[] result = new int[200];
		int[] a = new int[100];
		
		int n = scanner.nextInt();
		temp = n;
		for (int i = 0; temp > 0; i++) {
			result[i] = temp % 10;
			temp = temp / 10;
			count++;
		}
		for (int i = n-1; i>0; i--) {
			for(int j=0;j<count;j++) {
				x = result[j] * i + temp;
				result[j] = x % 10;
				temp = x/10;
			}
			while(temp>0) {
				result[count++] = temp%10;
				temp/=10;
			}
		}
		System.out.println();
		for(int i=count-1;i>=0;i--)
			System.out.print(result[i]);
	
	}
}
