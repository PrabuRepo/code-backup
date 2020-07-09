package com.web.codechef.problems;

import java.io.IOException;

public class TestFastIO {

	public static void main(String[] args) throws IOException {
		FastIO input = new FastIO();
		int n = input.nextInt(), k = input.nextInt(), count = 0, number;
		while (n-- > 0) {
			number = input.nextInt();
			if (0 == (number % k))
				++count;
		}
		input.println(count);
		input.flush();
		input.close();
	}

}
