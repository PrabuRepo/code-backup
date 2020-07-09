package com.web.codechef.problems;

import java.io.*;
import java.util.*;

class A {
	public static void main(String args[]) throws IOException {
		StringBuffer str = new StringBuffer();
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- != 0) {
			int n = in.nextInt();
			long minx = in.nextLong();
			long maxx = in.nextLong();
			long w[] = new long[n];
			long b[] = new long[n];
			for (int i = 0; i < n; i++) {
				w[i] = in.nextLong();
				b[i] = in.nextLong();
			}
			long x = 1;
			for (int i = 0; i < n; i++) {
				x = w[i] * x + b[i];
				if (x % 2 == 0)
					x = 2;
				else
					x = 1;
			}
			long y = 2;
			for (int i = 0; i < n; i++) {
				y = w[i] * y + b[i];
				if (y % 2 == 0)
					y = 2;
				else
					y = 1;
			}
			if ((x % 2 == 0) && (y % 2 == 0))
				str.append(maxx - minx + 1).append(" ").append(0).append("\n");
			else if ((x % 2 != 0) && (y % 2 != 0))
				str.append(0).append(" ").append(maxx - minx + 1).append("\n");
			else {
				if (x == 2) {
					if ((minx % 2 == 0) && (maxx % 2 == 0)) {
						long p = maxx - minx;
						str.append(p / 2).append(" ").append(p / 2 + 1).append("\n");
					} else if ((minx % 2 != 0) && (maxx % 2 != 0)) {
						long p = maxx - minx;
						str.append(p / 2 + 1).append(" ").append(p / 2).append("\n");
					} else {
						long p = maxx - minx + 1;
						str.append(p / 2).append(" ").append(p / 2).append("\n");
					}
				} else {
					if ((minx % 2 == 0) && (maxx % 2 == 0)) {
						long p = maxx - minx;
						str.append(p / 2 + 1).append(" ").append(p / 2).append("\n");
					} else if ((minx % 2 != 0) && (maxx % 2 != 0)) {
						long p = maxx - minx;
						str.append(p / 2).append(" ").append(p / 2 + 1).append("\n");
					} else {
						long p = maxx - minx + 1;
						str.append(p / 2).append(" ").append(p / 2).append("\n");
					}
				}
			}
		}
		System.out.println(str);
	}
}