package com.web.hackersrank.datastructures;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
	}

	static void balancedParenthesis() {

		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			String s = in.next();
			String result = isBalanced(s);
			System.out.println(result);
		}
		in.close();

	}

	static String isBalanced(String s) {
		Stack<Character> st = new Stack<>();
		char ch;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (ch == '(' || ch == '{' || ch == '[') {
				st.push(ch);
			} else if (!st.isEmpty()) {
				if ((st.peek() == '{' && ch == '}') || (st.peek() == '[' && ch == ']') || (st.peek() == '(' && ch == ')'))
					st.pop();
			} else {
				return "NO";
			}
		}
		return st.isEmpty() ? "YES" : "NO";
	}

	public void maxElement() {

		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		Stack<Integer> st = new Stack<>();
		Integer max = Integer.MIN_VALUE;
		while (t-- > 0) {
			switch (in.nextInt()) {
			case 1:
				int data = in.nextInt();
				st.push(data);
				if (data > max) {
					max = data;
				}
				break;
			case 2:
				if (!st.isEmpty()) {
					Integer top = st.pop();
					if (top.equals(max)) {
						if (!st.isEmpty())
							max = st.stream().max(Comparator.comparing(Integer::valueOf)).get();
						else
							max = Integer.MIN_VALUE;
					}
				}
				break;
			case 3:
				if (!st.isEmpty())
					System.out.println(max);
				break;
			}
		}
		in.close();

	}
}
