package com.web.hackersrank.problems;

import java.util.Arrays;
import java.util.Scanner;

public class StringManipulation {

	public static void main(String[] args) {
		int tc;
		String s;
		StringBuilder sOdd,sEven;
		Scanner scanner = new Scanner(System.in);
		tc = scanner.nextInt();
		while(tc-- > 0) {
			s = scanner.next();
			sOdd = new StringBuilder();
			sEven = new StringBuilder();
			for(int i= 0;i<s.length();i++) {
				if(i%2==0 || i==0)
					sOdd.append(s.charAt(i));
				else 
					sEven.append(s.charAt(i));
			}
			System.out.println(sOdd.toString() + " " + sEven.toString());
		}
		scanner.close();
	}

}
