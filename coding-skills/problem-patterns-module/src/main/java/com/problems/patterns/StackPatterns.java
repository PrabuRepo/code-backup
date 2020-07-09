package com.problems.patterns;

import java.util.Stack;

import com.common.utilities.Utils;

public class StackPatterns {

	/********************** Type1: Parse the expression/String *******************/
	// Evaluate Post Fix Expression or Polish Notation
	public void evaluatePosfixExpression(String expr) {
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < expr.length(); i++) { // Scan the given expression and do following for every scanned
													// element.
			char ch = expr.charAt(i);
			if (Character.isDigit(ch)) { // If the element is a number, push it into the stack
				stack.push(Character.getNumericValue(ch));
				// stack.push(Integer.valueOf(ch - '0'));
				// System.out.println(Integer.valueOf('0')); //48
			} else { /*If the element is a operator, pop operands for the operator from stack. Evaluate the operator and push the result back to the stack*/
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
		if (b == 1) return a;
		return a * pow(a, b - 1);
	}

	//TOSO:  Compare this solution with Post Fix Evaluation
	// Evaluate Reverse Polish/Postfix Notation
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		String str = null;
		int val1 = 0, val2 = 0;
		for (int i = 0; i < tokens.length; i++) {
			str = tokens[i];
			if (str.equals("/") || str.equals("-")
					|| str.equals("*") || str.equals("+")) {
				val2 = stack.pop();
				val1 = stack.pop();
				stack.push(arithmeticOperation(
						str.charAt(0), val1, val2));
			} else {
				stack.push(Integer.valueOf(str));
			}
		}
		return (!stack.isEmpty() && stack.size() == 1)
				? stack.pop()
				: 0;
	}
	//Evaluate infix expression/Basic Calculator I, II, III
	/*Evaluate Infix Expression:
	 *  - Basic Calculator I
	 *  - Basic Calculator II
	 *  - Basic Calculator III
	 */

	/* Basic Calculator I: Implement a basic calculator to evaluate a simple expression string. The expression string may
	 * contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
	 * Input: "1 + 1" Output: 2 
	 * Input: " 2-1 + 2 " Output: 3
	 */
	public int calculator1(String s) {
		int n = s.length(), sign = 1, result = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				int digit = (int) ch - '0';
				while (i + 1 < n && Character
						.isDigit(s.charAt(i + 1))) { // Collect all the digits from the input
					digit = digit * 10 + s.charAt(i + 1)
							- '0';
					i++;
				}
				result += digit * sign;
			} else if (ch == '+') {
				sign = 1;
			} else if (ch == '-') {
				sign = -1;
			} else if (ch == '(') {
				stack.push(result);
				stack.push(sign);
				result = 0;
				sign = 1;
			} else if (ch == ')') {
				result = result * stack.pop() + stack.pop(); // 1st pops the sign, 2nd pops the prev calculation
			}
		}
		return result;
	}

	/*
	 * Basic Calculator II:
	 * Implement a basic calculator to evaluate a simple expression string. The expression string contains only non-negative
	 * integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
	 * Input: "3+2*2" Output: 7 
	 * Input: " 3/2 " Output: 1 
	 * Input: " 3+5 / 2 " Output: 5
	 */

	// Approach1: Using Stack
	public int calculator21(String s) {
		int n = s.length(), result = 0, num = 0;
		Stack<Integer> stack = new Stack<>();
		char oper = '+';
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (ch == ' ') {
				continue;
			} else if (Character.isDigit(ch)) {
				num = (int) ch - '0';
				while (i + 1 < n && Character
						.isDigit(s.charAt(i + 1))) { // Collect all the digits from the input
					num = num * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				if (oper == '+') stack.push(num);
				else if (oper == '-') stack.push(-num);
				else if (oper == '*')
					stack.push(stack.pop() * num);
				else if (oper == '/')
					stack.push(stack.pop() / num);
			} else {
				oper = ch;
				result = 0;
			}
		}

		while (!stack.isEmpty())
			result += stack.pop();
		return result;
	}

	// Approach2: Without using Stack
	public int calculator22(String s) {
		int n = s.length(), result = 0, num = 0,
				prevVal = 0;
		char oper = '+';
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (ch == ' ') {
				continue;
			} else if (Character.isDigit(ch)) {
				num = (int) ch - '0';
				while (i + 1 < n && Character
						.isDigit(s.charAt(i + 1))) { // Collect all the digits from the input
					num = num * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				if (oper == '+') {
					result += prevVal;
					prevVal = num;
				} else if (oper == '-') {
					result += prevVal;
					prevVal = -num;
				} else if (oper == '*') {
					prevVal = prevVal * num;
				} else if (oper == '/') {
					prevVal = prevVal / num;
				}
			} else {
				oper = ch;
			}
		}

		return result + prevVal;
	}

	/*Basic Calculator III:
	 * Implement a basic calculator to evaluate a simple expression string. The expression string may contain open ( and
	 * closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces . The expression string
	 * contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The
	 * integer division should truncate toward zero.
	 * "1 + 1" = 2
	 * " 6-4 / 2 " = 4
	 * "2*(5+5*2)/3+(6/2+8)" = 21
	 */
	public int calculator3(String s) {
		Stack<Character> operStack = new Stack<>();
		Stack<Integer> valStack = new Stack<>();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (ch == ' ') continue;
			else if (Character.isDigit(ch)) {
				int start = i;
				while (i + 1 < n && Character
						.isDigit(s.charAt(i + 1)))
					i++;
				valStack.push(Integer.valueOf(
						s.substring(start, i + 1)));
			} else if (ch == '(') {
				operStack.push('(');
			} else if (ch == ')') {
				while (operStack.peek() != '(')
					valStack.push(arithmeticOperation(
							operStack.pop(), valStack.pop(),
							valStack.pop()));
				operStack.pop();
			} else {
				while (!operStack.isEmpty()
						&& (operStack.peek() == '*'
								|| operStack.peek() == '/'))
					valStack.push(arithmeticOperation(
							operStack.pop(), valStack.pop(),
							valStack.pop()));
				operStack.push(ch);
			}
		}

		while (!operStack.isEmpty())
			valStack.push(arithmeticOperation(
					operStack.pop(), valStack.pop(),
					valStack.pop()));

		return valStack.pop();
	}

	private int arithmeticOperation(char ch, int b, int a) {
		int result = 0;
		switch (ch) {
			case '+':
				result = a + b;
				break;
			case '-':
				result = a - b;
				break;
			case '*':
				result = a * b;
				break;
			case '/':
				result = a / b;
				break;
		}
		return result;
	}

	//Simplify Path
	public String simplifyPath(String path) {
		Stack<String> stack = new Stack<>();

		for (String str : path.split("/")) {
			if (!stack.isEmpty() && str.equals(".."))
				stack.pop();
			else if (!str.equals("") && !str.equals(".")
					&& !str.equals(".."))
				stack.push(str);
		}

		StringBuilder result = new StringBuilder();
		for (String str : stack)
			result.append("/").append(str);

		return stack.isEmpty() ? "/" : result.toString();
	}

	/********************** Type2: Stack usage problems *******************/
	// Largest Rectangle in Histogram
	// Approach1: BruteForce Approach: Time Complexity-O(n^2)
	public int largestRectangleArea1(int[] h) {
		int max = 0, width;
		int minHeight;

		for (int i = 0; i < h.length; i++) {
			width = 1;
			minHeight = Integer.MAX_VALUE;
			for (int j = i; j >= 0; j--, width++) {
				minHeight = Math.min(minHeight, h[j]);
				max = Math.max(max, width * minHeight);
			}
		}
		return max;
	}

	// Using Divide & Conquer Algorithm:
	/*
	 * worst case time complexity of this algorithm becomes O(n^2). In worst case, we always have (n-1) elements in one side and 0 
	 * elements in other side and if the finding minimum takes O(n) time, we get the recurrence similar to worst case of Quick Sort. 
	 * How to find the minimum efficiently? Range Minimum Query using Segment Tree can be used for this. We build segment tree of the
	 * given histogram heights. Once the segment tree is built, all range minimum queries take O(Logn) time.
	 * 
	 * Overall Time = Time to build Segment Tree + Time to recursively find maximum area; 
	 * Time to build segment tree is O(n). Let the time to recursively find max area be T(n). It can be written as following.
	 * T(n) = O(Logn) + T(n-1)
	 */
	public int largestRectangleArea2(int[] heights) {
		if (heights.length == 0) return 0;
		return largestRectangleArea2(heights, 0,
				heights.length - 1);
	}

	public int largestRectangleArea2(int[] heights, int l,
			int h) {
		if (l > h) return 0;
		if (l == h) return heights[l];

		// Rewrite this using Range Minimum query
		int m = findMin(heights, l, h);

		return Utils.max(
				largestRectangleArea2(heights, l, m - 1),
				largestRectangleArea2(heights, m + 1, h),
				(h - l + 1) * heights[m]);
	}

	private int findMin(int[] a, int l, int h) {
		int minIndex = l;
		for (int i = l + 1; i <= h; i++)
			if (a[i] < a[minIndex]) minIndex = i;

		return minIndex;
	}

	// Using Stack
	public int largestRectangleArea3(int[] heights) {
		int n = heights.length;
		if (n == 0) return 0;
		Stack<Integer> stack = new Stack<>();
		int i = 0, width = 0, maxArea = 0, topIndex = 0;

		while (i < n || !stack.isEmpty()) {
			if (stack.isEmpty() || i < n && (heights[stack
					.peek()] <= heights[i])) {
				stack.push(i++); // Store the index
			} else {
				topIndex = stack.pop(); // Get the top value, this will be used below to get height
				width = stack.isEmpty() ? i
						: (i - stack.peek() - 1); // i - peek/prev in the stack
				maxArea = Math.max(maxArea,
						heights[topIndex] * width);
			}
		}

		return maxArea;
	}

	/*The Celebrity Problem
	 *  1. Using Graph
	 *  2. Using Stack
	 *  3. Efficient Approach(Without any data structure)
	 */
	// Using Graph: Time Complexity: O(n^2)
	public int celebrityProblem1(int[][] input, int n) {
		if (n == 0) return -1;

		int[] indegree = new int[n], outdegree = new int[n];

		// Find indegree, outdegree
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (input[i][j] == 1) {
					outdegree[i]++;
					indegree[j]++;
				}
			}
		}

		for (int i = 0; i < n; i++)
			if (indegree[i] == n - 1 && outdegree[i] == 0)
				return i;

		return -1;
	}

	// Using Stack
	public int celebrityProblem2(int[][] input, int n) {
		if (n == 0) return -1;

		Stack<Integer> stack = new Stack<>();

		// Push all the celebrities into a stack.
		for (int i = 0; i < n; i++)
			stack.push(i);

		// Pop off top two persons from the stack, discard one person based on status
		while (stack.size() > 1) {
			int a = stack.pop();
			int b = stack.pop();

			if (input[a][b] == 1) stack.push(b);
			else stack.push(a);
		}

		// Check the remained person in stack doesn’t have acquaintance with anyone else.
		int celeb = stack.pop();
		for (int i = 0; i < n; i++)
			if (i != celeb && (input[i][celeb] != 1
					|| input[celeb][i] != 0)) // check celebrity in all the rows, if
				// cond satisfies return -1;
				return -1;

		return celeb;
	}

	// Efficient Approach(Without any data structure): Time Complexity: O(n)
	public int celebrityProblem3(int[][] input, int n) {
		if (n == 0) return -1;

		int celeb = 0;

		for (int j = 0; j < n; j++)
			if (input[celeb][j] == 1) // Find the celebrity from first row
				celeb = j;

		for (int i = 0; i < n; i++)
			if (i != celeb && (input[i][celeb] != 1
					|| input[celeb][i] != 0)) // check celebrity in all the rows, if
				// cond satisfies return -1;
				return -1;

		return celeb;
	}

	public boolean knows(int[][] M, int i, int j) {
		return M[i][j] == 1 ? true : false;
	}

	/********************** Type3: Stack design problems *******************/

	/********************** Type4: Monotonic Stack problems *******************/
}