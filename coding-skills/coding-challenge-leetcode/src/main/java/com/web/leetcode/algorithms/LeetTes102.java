package com.web.leetcode.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.common.utilities.Utils;

public class LeetTes102 {

	public static void main(String[] args) {

		LeetTes102 ob = new LeetTes102();

		System.out.println("Prob 1: ");
		int[] A = { 3, 1, 2, 4 };
		// Utils.printArray(ob.sortArrayByParity(A));
		// System.out.println(isCheckSquare(676));
		// System.out.println(ob.superpalindromesInRange("4", "1000"));
		System.out.println(ob.superpalindromesInRange("4", "1000"));

	}

	public int superpalindInRange(String L, String R) {
		long l = Long.valueOf(L), r = Long.valueOf(R);
		int count = 0;
		int max = (int) Math.sqrt(r);
		for (long i = l; i <= max; i++) {
			if (i == 1) {
				count++;
				continue;
			}
			double d = Math.sqrt(i);
			int sqrt = (int) d;
			boolean isSquare = (d - sqrt == 0);
			if (isPalindrome(i)) {
				if (isSquare && isPalindrome(sqrt)) count++;
				if (isPalindrome(i * i)) count++;
			}
		}

		return count;
	}

	public boolean isSquare(int n) {
		double d = Math.sqrt(n);
		int val = (int) d;
		return (d - val == 0);
	}

	public boolean isPalindrome(long n) {
		if (n < 10) return true;
		long rev = 0, temp = n;
		while (temp > 0) {
			rev = (10 * rev) + (temp % 10);
			temp /= 10;
		}

		return n == rev;
	}

	public int[] sortArrayByParity(int[] A) {
		if (A == null && A.length == 0) return null;

		int i = 0, j = 0;
		while (i < A.length && j < A.length) {
			if (A[i] % 2 == 0) {
				Utils.swap(A, i, j);
				i++;
				j++;
			} else {
				i++;
			}
		}
		return A;
	}

	/*
	159 - Longest Substring with At most Two Distinct Characters
	https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
	https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/*/

	public int totalFruit2(int[] tree) {
		int res = 0, cur = 0, count_b = 0, a = 0, b = 0;
		for (int c : tree) {
			cur = c == a || c == b ? cur + 1 : count_b + 1;
			count_b = c == b ? count_b + 1 : 1;
			if (b != c) {
				a = b;
				b = c;
			}
			res = Math.max(res, cur);
		}
		return res;
	}

	public int totalFruit(int[] tree) {
		int[] fruitCount = new int[400001];
		int maxFruits = 0, left = 0, fruitTypes = 0;
		for (int right = 0; right < tree.length; ++right) {
			int rightMost = tree[right];
			if (++fruitCount[rightMost] == 1) {
				++fruitTypes;
			}
			while (fruitTypes > 2) {
				int leftMost = tree[left++];
				if (--fruitCount[leftMost] == 0) --fruitTypes;
			}
			maxFruits = Math.max(maxFruits, right - left + 1);
		}
		return maxFruits;
	}

	public int superpalindromesInRange(String L, String R) {
		int l = (int) Math.sqrt(Long.parseLong(L));
		int r = (int) Math.sqrt(Long.parseLong(R));

		List<Integer> allP = new ArrayList<>();
		help(allP, l, r);
		int cnt = 0;
		for (int p : allP) {
			long k = (long) p * p;
			if (isP(k)) cnt++;
		}
		return cnt;
	}

	private boolean isP(long n) {
		long rev = 0;
		for (long i = n; i > 0; i /= 10)
			rev = rev * 10 + i % 10;
		return (n == rev);
	}

	int createP(int input, int b, int isOdd) {
		long n = input;
		long palin = input;
		if (isOdd == 1) n /= b;
		while (n > 0) {
			palin = palin * b + (n % b);
			n /= b;
		}
		return (int) palin;
	}

	void help(List<Integer> allP, int k, int n) {
		int number;
		for (int j = 0; j < 2; j++) {
			int i = 1;
			while ((number = createP(i, 10, j % 2)) <= n) {
				if (number >= k) allP.add(number);
				i++;
			}
		}
	}

	public int sumSubarrayMins(int[] A) {
		int l = A.length;
		if (l == 0) return 0;
		long ans = A[0];
		int n = 1000000007;
		Stack<int[]> st = new Stack<>();
		st.push(new int[] { A[0], 0, A[0] });

		for (int i = 1; i < A.length; i++) {
			if (A[i] >= st.peek()[0]) {
				st.push(new int[] { A[i], i, (A[i] + st.peek()[2]) % n });
			} else {
				while (!st.isEmpty() && A[i] < st.peek()[0]) {
					st.pop();
				}
				int preIdx = st.isEmpty() ? -1 : st.peek()[1];
				int preVal = st.isEmpty() ? 0 : st.peek()[2];
				st.push(new int[] { A[i], i, (A[i] * (i - preIdx) + preVal) });
			}
			ans = (ans + st.peek()[2]) % n;
		}
		return (int) ans;
	}

}
