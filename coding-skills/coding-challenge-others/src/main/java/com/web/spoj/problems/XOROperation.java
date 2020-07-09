package com.web.spoj.problems;

import java.util.Scanner;

public class XOROperation {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Input:");
		int p = reader.nextInt();
		int q = reader.nextInt();
		int output = 0;
		if(p==0 && q==0) {
			output = 1;
		}else {
			output = 0;
		}
		System.out.println(output);
		reader.close();
	}

}
