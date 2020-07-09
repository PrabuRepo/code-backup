package com.geeksforgeeks.ds.problems;

import java.util.ListIterator;
import java.util.Stack;

public class StackGeeksProblems {

	public static void evaluatePosfixExpression() {
		// String expr = "231*+9-";
		String expr = "5332^7-121*+^*+2-";
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (Character.isDigit(ch)) {
				stack.push(Character.getNumericValue(ch));
				// stack.push(Integer.valueOf(ch - '0'));
				// System.out.println(Integer.valueOf('0')); //48
			} else {
				int val1 = stack.pop();
				int val2 = stack.pop();
				int result = 0;
				switch (ch) {
				case '+':
					result = val2 + val1;
					break;
				case '-':
					result = val2 - val1;
					break;
				case '*':
					result = val2 * val1;
					break;
				case '/':
					result = val2 / val1;
					break;
				case '^':
					result = pow(val2, val1);
					break;
				}
				stack.push(result);
			}
		}
		System.out.println("Value:" + stack.pop());
	}

	private static int pow(int a, int b) {
		if (b == 1)
			return a;
		return a * pow(a, b - 1);
	}

	public static void prefixToInfix() {
		// String expr = "*-A/BC-/AKL";
		String expr = "+a-*b^-^cde+f*ghi";
		Stack<String> stack = new Stack<>();

		for (int i = expr.length() - 1; i >= 0; i--) {
			char ch = expr.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				stack.push(String.valueOf(ch));
			} else {
				stack.push("(" + stack.pop() + ch + stack.pop() + ")");
			}
		}
		System.out.println("prefixToInfix:" + stack.pop());
	}

	public static void prefixToPostfix() {
		// String expr = "*-A/BC-/AKL";
		String expr = "+a-*b^-^cde+f*ghi";
		Stack<String> stack = new Stack<>();

		for (int i = expr.length() - 1; i >= 0; i--) {
			char ch = expr.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				stack.push(String.valueOf(ch));
			} else {
				stack.push(stack.pop() + stack.pop() + ch);
			}
		}
		System.out.println("prefixToPostfix:" + stack.pop());
	}

	public static void postfixToInfix() {
		String expr = "ABC/-AK/L-*";
		Stack<String> stack = new Stack<>();

		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				stack.push(String.valueOf(ch));
			} else {
				String val1 = stack.pop();
				String val2 = stack.pop();
				stack.push("(" + val2 + ch + val1 + ")");
			}
		}
		System.out.println("postfixToInfix:" + stack.pop());
	}

	public static void postfixToPrefix() {
		String expr = "ABC/-AK/L-*";
		Stack<String> stack = new Stack<>();

		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				stack.push(String.valueOf(ch));
			} else {
				String val1 = stack.pop();
				String val2 = stack.pop();
				stack.push(ch + val2 + val1);
			}
		}
		System.out.println("postfixToPrefix:" + stack.pop());
	}

	public static String infixToPostfix(String expr) {
		// String expr = "5+3*(3^2-7)^(1+2*1)-2";
		// String expr = "a+b*(c^d-e)^(f+g*h)-i";
		StringBuilder result = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				result.append(ch);
			} else if (stack.isEmpty() || ch == '(' || charPrecedence(ch) > charPrecedence(stack.peek())) {
				stack.push(ch);
			} else if (ch == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					result.append(stack.pop());

				if (!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
				} else {
					System.out.println("Invalid Expression");
					break;
				}
			} else {
				while (!stack.isEmpty() && charPrecedence(ch) <= charPrecedence(stack.peek()))
					result.append(stack.pop());
				stack.push(ch);
			}
		}

		while (!stack.isEmpty())
			result.append(stack.pop());

		System.out.println("infixToPostfix: " + result);
		return result.toString();
	}

	private static void infixToPrefix() {
		String expr = "a+b*(c^d-e)^(f+g*h)-i";
		StringBuilder builder1 = new StringBuilder(expr);
		// String reverseString = builder1.reverse();
		builder1.reverse();
		// System.out.println("before change:" + builder1);
		for (int i = 0; i < builder1.length(); i++) {
			if (builder1.charAt(i) == '(')
				builder1.setCharAt(i, ')');
			else if (builder1.charAt(i) == ')')
				builder1.setCharAt(i, '(');
		}
		// System.out.println("After change:" + builder1);
		String postFixExpr = infixToPostfix(builder1.toString());
		StringBuilder builder2 = new StringBuilder(postFixExpr);
		System.out.println("infixToPrefix:" + builder2.reverse());
	}

	private static int charPrecedence(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		default:
			return -1;
		}
	}

	public static void balancedParentheses() {
		char expr[] = { '{', '(', ')', '}', '[', ']' };
		// char expr[] = { '{', '(', ')', '}', '[', ']', '{', ')' };
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < expr.length; i++) {
			char ch = expr[i];
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else if ((ch == ')' && stack.peek() == '(') || (ch == '}' && stack.peek() == '{') || (ch == ']' && stack.peek() == '[')) {
				stack.pop();
			}
		}

		if (stack.isEmpty())
			System.out.println("Balanced Parentheses");
		else
			System.out.println("Invalid Expression");
	}

	public static Stack<Integer> reverseStack(Stack<Integer> stack) {
		// Its not working
		if (stack.isEmpty())
			return stack;
		int temp = stack.pop();
		stack = reverseStack(stack);
		stack.push(temp);
		return stack;
	}

	public static void display(Stack<Integer> stack) {
		ListIterator<Integer> listIterator = stack.listIterator();
		while (listIterator.hasNext()) {
			System.out.print(listIterator.next() + " ");
		}
	}

	public static void nextGreaterElement1(int[] a) {
		int next;
		for (int i = 0; i < a.length; i++) {
			next = -1;
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] < a[j]) {
					next = a[j];
					break;
				}
			}
			System.out.println(a[i] + " -- " + next);
		}
	}

	public static void nextGreaterElement2(int[] a) {
		Stack<Integer> stack = new Stack<>();
		stack.push(a[0]);
		int next;
		for (int i = 1; i < a.length; i++) {
			next = a[i];
			if (!stack.isEmpty()) {
				while (stack.peek() < next) {
					System.out.println(stack.pop() + " -- " + next);
					if (stack.isEmpty())
						break;
				}
			}
			stack.push(next);
		}

		while (!stack.isEmpty())
			System.out.println(stack.pop() + " -- " + "-1");
	}

	public static void main(String[] x) {
		// infixToPostfix("a+b*(c^d-e)^(f+g*h)-i");
		// infixToPrefix();
		// prefixToInfix();
		// prefixToPostfix();
		// postfixToInfix();
		// postfixToPrefix();
		// evaluatePosfixExpression();
		// balancedParentheses();

		/*Stack<Integer> stack = new Stack<>();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		System.out.println("Before Reverse:");
		display(stack);
		stack = reverseStack(stack);
		System.out.println("After Reverse:");
		display(stack);*/

		int[] a = { 4, 5, 2, 25 };
		nextGreaterElement2(a);
	}
}
