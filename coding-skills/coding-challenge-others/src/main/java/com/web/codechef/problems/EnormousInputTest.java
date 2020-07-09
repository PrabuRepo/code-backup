package com.web.codechef.problems;

import java.util.Scanner;

class EnormousInputTest {

	public static void main(String[] args) {
		System.out.println("Input:");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int count = 0, data;
		for(int i=1;i<=n;i++) {
			data = scanner.nextInt();
			if(data % k == 0)
				count++;
		}
		System.out.println("Output:");
		System.out.println(count);
		scanner.close();
	}
}
