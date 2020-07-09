package com.web.codechef.problems;

import java.io.IOException;
import java.util.Scanner;

public class SpamPattern3 {

	public static void main(String[] args) throws Exception {
		int t;
		FastIO scanner = new FastIO();
		System.out.println("Input:");
		t = scanner.nextInt();
		while (t-- > 0) {
			int n;
			// long long int minx,maxx;
			long minx, maxx;
			n = scanner.nextInt();
			minx = scanner.nextInt();
			maxx = scanner.nextInt();
			int w, b;
			int even = 0, odd = 1;
			for (int i = 0; i < n; i++) {
				w = scanner.nextInt();
				b = scanner.nextInt();
				even = (w * even + b) % 2;
				odd = (w * odd + b) % 2;
			}

			int ans = 0;
			long k = (maxx - minx + 1) / 2, l;
			if (minx % 2 == 0) {
				k++;
			}

			l = (maxx - minx + 1) - k;

			if (even == 0) {
				ans += k;
			}
			if (odd == 0) {
				ans += l;
			}
			System.out.println(ans + " " + ((maxx + 1 - minx) - ans));
		}
		scanner.close();
	}
}