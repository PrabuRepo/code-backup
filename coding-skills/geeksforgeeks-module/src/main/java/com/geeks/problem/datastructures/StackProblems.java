package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;

import com.common.utilities.Utils;

public class StackProblems {

	Stack<Integer>	s1	= new Stack<Integer>();
	Stack<Integer>	s2	= new Stack<Integer>();

	/***************** Design and Implementation *****************/

	// Queue using 2 Stack:Insert
	public Stack<Integer> enqueue(int data) {
		s1.push(data); // Insert the data into s1
		return s1;
	}

	// Queue using 2 Stack:Remove
	public int dequeue() {
		shiftStacks();
		return s2.isEmpty() ? -1 : s2.pop();
	}

	public int peek() {
		shiftStacks();
		return s2.isEmpty() ? -1 : s2.peek();
	}

	// Move values from s1 to s2
	public void shiftStacks() {
		if (s2.isEmpty()) {
			while (!s1.isEmpty())
				s2.push(s1.pop());
		}
	}

	public void displayQueue() {
		display(s2);
		display(s1);
	}

	public void display(Stack<Integer> stack) {
		ListIterator<Integer> listIterator = stack.listIterator();
		while (listIterator.hasNext())
			System.out.print(listIterator.next() + " ");
	}

	/***************** Standard Problems based on Stack *****************/
	/*
	 Conversion has below combinations:
	 	- Infix to Postfix, Infix to Prefix 
	 	- Prefix to Infix, Prefix to Postfix
	 	- Postfix to Prefix, Postfix to Infix 
	 */
	public static String infixToPostfix(String expr) {
		StringBuilder result = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < expr.length(); i++) {
			/*1.Scan the infix expression from left to right.*/
			char ch = expr.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				/*2.Operand: If the scanned character is an operand(letter or digit), output it.*/
				result.append(ch);

			} else if (ch == '(') {
				/*3.Open Parentheses: If the scanned character is an ‘(‘, push it to the stack.*/
				stack.push(ch);

			} else if (stack.isEmpty() || charPrecedence(ch) > charPrecedence(stack.peek())) {
				/*4.Operator(Higher Precedence): If the precedence of the scanned operator is greater than the precedence of the 
				 *  operator in the stack(or the stack is empty), push it.*/
				stack.push(ch);

			} else if (ch == ')') {
				/*5. Closed Parentheses: 
				 *   i.If the scanned character is an ‘)’, pop and output from the stack until an ‘(‘ is encountered.*/
				while (!stack.isEmpty() && stack.peek() != '(')
					result.append(stack.pop());
				// ii.Pop the closed parentheses
				if (!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
				} else {
					System.out.println("Invalid Expression");
					break;
				}
			} else {
				/*6.Operator(Lower Precedence): 
				 *  i. Pop the operator from the stack until the precedence of the scanned operator is less than equal to the precedence of
				 *     the operator residing on the top of the stack. Push the scanned operator to the stack.*/
				while (!stack.isEmpty() && charPrecedence(ch) <= charPrecedence(stack.peek()))
					result.append(stack.pop());

				/*  ii.Push the given the arithmetic symbol into the stack*/
				stack.push(ch);
			}
		}

		while (!stack.isEmpty())
			result.append(stack.pop());

		System.out.println("infixToPostfix: " + result);
		return result.toString();
	}

	public void infixToPrefix(String str) {
		StringBuilder builder1 = new StringBuilder(str);
		/*1.Reverse the infix expression i.e A+B*C will become C*B+A. Note while reversing 
		    each ‘(‘ will become ‘)’ and each ‘)’ becomes ‘(‘.*/
		builder1.reverse();
		// System.out.println("before change:" + builder1);
		for (int i = 0; i < builder1.length(); i++) {
			if (builder1.charAt(i) == '(') builder1.setCharAt(i, ')');
			else if (builder1.charAt(i) == ')') builder1.setCharAt(i, '(');
		}
		System.out.println("After change:" + builder1);
		// System.out.println("Infix-Prefix uses Infix-Postfix algorithm.....");

		/*2.Obtain the postfix expression of the modified expression i.e CB*A+.*/
		String postFixExpr = infixToPostfix(builder1.toString());

		/*3.Reverse the postfix expression. Hence in our example prefix is +A*BC.*/
		StringBuilder builder2 = new StringBuilder(postFixExpr);
		System.out.println("infixToPrefix:" + builder2.reverse());
	}

	public static void prefixToInfix(String expr) {
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

	public static void prefixToPostfix(String expr) {
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

	public static void postfixToInfix(String expr) {
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

	public static void postfixToPrefix(String expr) {
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

	public static void balancedParentheses() {
		char expr[] = { '{', '(', ')', '}', '[', ']' };
		// char expr[] = { '{', '(', ')', '}', '[', ']', '{', ')' };
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < expr.length; i++) {
			char ch = expr[i];
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else if ((ch == ')' && stack.peek() == '(') || (ch == '}' && stack.peek() == '{')
					|| (ch == ']' && stack.peek() == '[')) {
				stack.pop();
			}
		}

		if (stack.isEmpty()) System.out.println("Balanced Parentheses");
		else System.out.println("Invalid Expression");
	}

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

	/*Evaluate Infix Expression:
	 *  - Basic Calculator I
	 *  - Basic Calculator II
	 *  - Basic Calculator III
	 */

	/*
	 * Basic Calculator I: Implement a basic calculator to evaluate a simple expression string. The expression string may
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
				while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) { // Collect all the digits from the input
					digit = digit * 10 + s.charAt(i + 1) - '0';
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
				while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) { // Collect all the digits from the input
					num = num * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				if (oper == '+') stack.push(num);
				else if (oper == '-') stack.push(-num);
				else if (oper == '*') stack.push(stack.pop() * num);
				else if (oper == '/') stack.push(stack.pop() / num);
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
		int n = s.length(), result = 0, num = 0, prevVal = 0;
		char oper = '+';
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (ch == ' ') {
				continue;
			} else if (Character.isDigit(ch)) {
				num = (int) ch - '0';
				while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) { // Collect all the digits from the input
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
				while (i + 1 < n && Character.isDigit(s.charAt(i + 1)))
					i++;
				valStack.push(Integer.valueOf(s.substring(start, i + 1)));
			} else if (ch == '(') {
				operStack.push('(');
			} else if (ch == ')') {
				while (operStack.peek() != '(')
					valStack.push(arithmeticOperation(operStack.pop(), valStack.pop(), valStack.pop()));
				operStack.pop();
			} else {
				while (!operStack.isEmpty() && (operStack.peek() == '*' || operStack.peek() == '/'))
					valStack.push(arithmeticOperation(operStack.pop(), valStack.pop(), valStack.pop()));
				operStack.push(ch);
			}
		}

		while (!operStack.isEmpty())
			valStack.push(arithmeticOperation(operStack.pop(), valStack.pop(), valStack.pop()));

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

	// Approach1: BruteForce Approach -> Time Complexity: O(n^2)
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

	// Approach2: Using Stack; Time Complexity: O(n), with additional stack space
	public static void nextGreaterElement2(int[] a) {
		Stack<Integer> stack = new Stack<>();
		stack.push(a[0]);
		for (int i = 1; i < a.length; i++) {
			while (!stack.isEmpty() && stack.peek() < a[i])
				System.out.println(stack.pop() + " -- " + a[i]);

			stack.push(a[i]);
		}

		while (!stack.isEmpty())
			System.out.println(stack.pop() + " -- " + "-1");
	}

	// Next greater Element: Return output as array
	public static int[] nextGreaterElement3(int[] a) {
		int n = a.length;
		if (n == 0) return new int[0];

		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();
		Map<Integer, Integer> map = new HashMap<>();
		stack.push(a[0]);

		for (int i = 1; i < a.length; i++) {
			while (!stack.isEmpty() && stack.peek() < a[i])
				map.put(stack.pop(), a[i]);

			stack.push(a[i]);
		}

		while (!stack.isEmpty())
			map.put(stack.pop(), -1);

		for (int i = 0; i < n; i++)
			result[i] = map.get(a[i]);

		return result;
	}

	/*The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we need to calculate span of 
	 * stock’s price for all n days. 
	 * The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the given day, for 
	 * which the price of the stock on the current day is less than or equal to its price on the given day.
	 */
	// Brute force method
	public void stockSpan1(int[] prices) {
		int[] span = new int[prices.length];
		Arrays.fill(span, 1);
		for (int i = 1; i < prices.length; i++) {
			for (int j = i - 1; j >= 0 && prices[j] < prices[i]; j--) {
				span[i]++;
			}
		}
		System.out.println("Prices: " + Arrays.toString(prices));
		System.out.println("Span: " + Arrays.toString(span));
	}

	// Approach2: Using stack; Linear time approach
	public void stockSpan2(int[] prices) {
		int[] span = new int[prices.length];
		Stack<Integer> stack = new Stack<>();

		stack.push(0); // Add the price index in stack
		span[0] = 1; // First index should be one

		// Calculate span values for rest of the elements
		for (int i = 1; i < prices.length; i++) {

			/*Pop elements from stack while stack is not empty and top of stack is smaller than price[i]*/
			while (!stack.isEmpty() && prices[stack.peek()] < prices[i])
				stack.pop();

			/*If stack becomes empty, then price[i] is greater than all elements on left of it, i.e., price[0], price[1],..price[i-1]. 
			  Else price[i] is greater than elements after top of stack*/
			span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();

			// Push the index into the stack
			stack.push(i);

		}
		System.out.println("Prices: " + Arrays.toString(prices));
		System.out.println("Span: " + Arrays.toString(span));
	}

	// Complete the freqQuery function below.
	static List<Integer> freqQuery(List<List<Integer>> queries) {
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, List<Integer>> countMap = new HashMap<>();
		List<Integer> result = new ArrayList<>();
		for (List<Integer> query : queries) {
			int opt = query.get(0), val = query.get(1);
			if (opt == 1) {
				map.put(val, map.getOrDefault(val, 0) + 1);
				int count = map.get(val);
				// Update the countMap
				if (countMap.get(count) != null) {
					if (countMap.get(count).size() == 1) countMap.remove(count);
					else countMap.get(count).remove((Integer) val);
				}
				// Add the value
				if (!countMap.containsKey(count)) countMap.put(count, new ArrayList<>());
				countMap.get(count).add(val);
			} else if (opt == 2) {
				if (!map.containsKey(val)) continue;
				int count = map.get(val);
				// Update the map
				if (map.get(val) == 1) map.remove(val);
				else map.put(val, map.get(val) - 1);
				// Update the countMap
				if (countMap.get(count).size() == 1) countMap.remove(count);
				else countMap.get(count).remove((Integer) val);
			} else {
				if (countMap.containsKey(val)) result.add(1);
				else result.add(0);
			}
		}
		return result;
	}

	public void towersOfHanoi(int n) {
		// Create 3 empty stacks
		Stack<Integer> sourceStack = new Stack<>(), destStack = new Stack<>(), auxStack = new Stack<>();
		// MAx no of moves
		int totalMoves = (int) Math.pow(2, n) - 1;
		char s = 'S', d = 'D', a = 'A';

		// If number of disks is even, then interchange destination pole and auxiliary pole
		if (n % 2 == 0) {
			char temp = d;
			d = a;
			a = temp;
		}

		// Insert all the disk(Number) from last to first
		for (int i = n; i > 0; i--)
			sourceStack.push(i);

		// Iterate disks(elements) one by one and move b/w poles S, D & A.
		for (int i = 1; i <= totalMoves; i++) {
			if (i % 3 == 1) {
				moveDisks1(sourceStack, destStack, s, d); // S <-> D
			} else if (i % 3 == 2) {
				moveDisks1(sourceStack, auxStack, s, a); // S <-> A
			} else if (i % 3 == 0) {
				moveDisks1(destStack, auxStack, d, a); // D <-> A
			}
		}
	}

	private void moveDisks1(Stack<Integer> sourceStack, Stack<Integer> destStack, char source, char dest) {
		if (sourceStack.isEmpty() || (!destStack.empty() && sourceStack.peek() > destStack.peek())) {
			sourceStack.push(destStack.pop());
			displayMove(dest, source, sourceStack.peek());
		} else {
			destStack.push(sourceStack.pop());
			displayMove(source, dest, destStack.peek());
		}
	}

	private void displayMove(char s, char d, int disk) {
		System.out.println("Disk: " + disk + " from " + s + " destination " + d);
	}

	private void moveDisks2(Stack<Integer> sourceStack, Stack<Integer> destStack, char source, char dest) {
		int topElementInSource = sourceStack.isEmpty() ? 0 : sourceStack.pop();
		int topElementInDest = destStack.isEmpty() ? 0 : destStack.pop();

		if (topElementInSource == 0) {
			sourceStack.push(topElementInDest);
			displayMove(dest, source, topElementInDest);
		} else if (topElementInDest == 0) {
			destStack.push(topElementInSource);
			displayMove(source, dest, topElementInSource);
		} else if (topElementInSource < topElementInDest) {
			destStack.push(topElementInDest);
			destStack.push(topElementInSource);
			displayMove(source, dest, topElementInSource);
		} else {
			sourceStack.push(topElementInSource);
			sourceStack.push(topElementInDest);
			displayMove(dest, source, topElementInDest);
		}
	}

	/* The Celebrity Problem:
	 * In a party of N people, only one person is known to everyone. Such a person may be present in the party, if yes,
	 * (s)he doesn’t know anyone in the party. We can only ask questions like “does A know B? “. Find the stranger
	 * (celebrity) in minimum number of questions.
	 */
	/*
	 * Solution1: The problem can be transformed as a graph problem. We count the in-degree and out-degree for each person. 
	 * Then find out the person with in-degree n - 1 and out-degree 0. 
	 */
	public int findCelebrity1(int n) {
		if (n <= 1) return -1;

		int[] indegree = new int[n];
		int[] outdegree = new int[n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i != j && knows(i, j)) {
					outdegree[i]++;
					indegree[j]++;
				}

		// Person with in-degree n - 1 and out-degree 0, is Celebrity.
		for (int i = 0; i < n; i++) {
			if (indegree[i] == n - 1 && outdegree[i] == 0) return i;
		}

		return -1;
	}

	// Solution: Using Stack
	public int findCelebrity2(int n) {
		if (n <= 1) return -1;
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++)
			stack.push(i);

		while (stack.size() > 1) {
			int i = stack.pop(), j = stack.pop();
			if (knows(i, j)) stack.push(j);
			else stack.push(i);
		}

		int celebrity = stack.pop();

		// Validate the result
		for (int i = 0; i < n; i++) {
			if (i != celebrity && (knows(celebrity, i) || !knows(i, celebrity))) return -1;
		}

		return celebrity;
	}

	/* A O(n) time O(1) Space Solution:
	 * Use two pointers, left starts from 0, and right starts from n - 1. Check if knows(left, right). If yes, left++.
	 * Else, right--. After the first step, we could find out the potential candidate. In the second step, we validate
	 * the candidate by iterating through all the person again. Each one must know the candidate while the candidate
	 * must not know anyone else.
	 */
	public int findCelebrity3(int n) {
		if (n <= 1) { return -1; }

		int left = 0;
		int right = n - 1;

		// Step 1: Find the potential candidate
		while (left < right) {
			if (knows(left, right)) {
				left++;
			} else {
				right--;
			}
		}

		// Step 2: Validate the candidate
		int candidate = right;
		for (int i = 0; i < n; i++) {
			if (i != candidate && (!knows(i, candidate) || knows(candidate, i))) { return -1; }
		}

		return candidate;
	}

	public int findJudge(int N, int[][] trust) {
		if (N == 0) return -1;
		int[][] matrix = new int[N][N];

		// Build the Matrix using edges
		for (int[] edge : trust)
			matrix[edge[0] - 1][edge[1] - 1] = 1;

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < N; i++)
			stack.push(i);

		while (stack.size() > 1) {
			int i = stack.pop();
			int j = stack.pop();
			if (matrix[i][j] == 1) stack.push(j);
			else stack.push(i);
		}

		int judge = stack.pop();

		for (int i = 0; i < N; i++) {
			if (i != judge && (matrix[judge][i] == 1 || matrix[i][judge] != 1)) return -1;
		}

		return judge + 1;
	}

	// Here person with 2 is celebrity
	static int MATRIX[][] = { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 } };

	// Returns true if a knows
	// b, false otherwise
	static boolean knows(int a, int b) {
		boolean res = (MATRIX[a][b] == 1) ? true : false;
		return res;
	}

	/***************** Operations on Stack *****************/
	// Reverse a stack using recursion - start
	public void reverseStack(Stack<Integer> stack) {
		System.out.println("Before reverse:");
		display(stack);
		reverseStackUtil(stack);
		System.out.println("\nAfter Reverse:");
		display(stack);
	}

	/*  Time Complexity to reverse the stack: 
	 *     insertAtBottom - O(n)
	 *     reverseStackUtil - O(n) * O(n) -> O(n^2)
	 *     OverAll Time Complexity: O(n^2) 
	 *     Space Complexity to reverse the stack: 
	 *     insertAtBottom - O(n)
	 *     reverseStackUtil - O(n) + O(n) -> O(2n)
	 *     OverAll Time Complexity: O(n) 
	 */
	private void reverseStackUtil(Stack<Integer> stack) {
		if (stack.isEmpty()) return;
		int element = stack.pop();
		// Recursive call to make empty stack
		reverseStackUtil(stack);
		// Send element one by one and set into stack
		insertAtBottom(stack, element);
	}

	private void insertAtBottom(Stack<Integer> stack, int element) {
		if (stack.isEmpty()) {
			stack.push(element);
		} else {
			/*Keep popping the element till stack empty and then insert the top element in the bottom*/
			int data = stack.pop();
			insertAtBottom(stack, element);
			stack.push(data);
		}
	}
	// Reverse a stack using recursion - end

	// Its similar to reverse the stack using recursion
	public void sortStack(Stack<Integer> stack) {
		System.out.println("Before Sort:");
		stack.stream().forEach(k -> System.out.print(k + " "));
		sort(stack);
		System.out.println("\nAfter Sort:");
		stack.stream().forEach(k -> System.out.print(k + " "));
	}

	private void sort(Stack<Integer> stack) {
		if (stack.isEmpty()) return;
		int element = stack.pop();
		// Recursive call to remove all the element from the stack
		sort(stack);
		// Arrange element increasing order one by one
		sort(stack, element);
	}

	private void sort(Stack<Integer> stack, int element) {
		if (stack.isEmpty() || element < stack.peek()) {
			stack.push(element);
		} else {
			/*Keep popping the element till element greater than element in the stack and then insert in ascending order in stack*/
			int data = stack.pop();
			sort(stack, element);

			stack.push(data);
		}
	}

	// Sort a stack using a temporary stack
	public void sortStackUsingTempStack(Stack<Integer> stack) {
		System.out.println("Before Sort:");
		stack.stream().forEach(k -> System.out.print(k + " "));

		Stack<Integer> tempStack = new Stack<>();
		while (!stack.isEmpty()) {
			int next = stack.pop();
			// while temporary stack is not empty and top of stack is greater than next
			while (!tempStack.isEmpty() && tempStack.peek() > next) {
				// Pop from temp stack and push into input stack
				stack.push(tempStack.pop());
			}
			tempStack.push(next);
		}

		// Copy the elements back to original stack
		while (!tempStack.isEmpty())
			stack.push(tempStack.pop());

		System.out.println("\nAfter Sort:");
		stack.stream().forEach(k -> System.out.print(k + " "));
	}

	// Delete middle element of a stack
	public void deleteMiddleElementInStack(Stack<Integer> stack) {
		System.out.println("Before removing the middle element:");
		stack.stream().forEach(k -> System.out.print(k + " "));

		deleteMiddleElement(stack, stack.size() / 2, 0);

		System.out.println("\nAfter removing the middle element:");
		stack.stream().forEach(k -> System.out.print(k + " "));
	}

	private void deleteMiddleElement(Stack<Integer> stack, int mid, int curr) {
		if (stack.isEmpty()) {
			return;
		} else if (curr == mid) {
			stack.pop();
			return;
		}
		int data = stack.pop();
		deleteMiddleElement(stack, mid, curr + 1);
		stack.push(data);
	}

	/***************** Misc Problems *****************/
	// Compare this solution with Post Fix Evaluation
	// Evaluate Reverse Polish Notation
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		String str = null;
		int val1 = 0, val2 = 0;
		for (int i = 0; i < tokens.length; i++) {
			str = tokens[i];
			if (str.equals("/") || str.equals("-") || str.equals("*") || str.equals("+")) {
				val2 = stack.pop();
				val1 = stack.pop();
				stack.push(arithmeticOperation(str.charAt(0), val1, val2));
			} else {
				stack.push(Integer.valueOf(str));
			}
		}
		return (!stack.isEmpty() && stack.size() == 1) ? stack.pop() : 0;
	}

	// Without using stack
	int i;

	public int evalRPN2(String[] tokens) {
		i = tokens.length - 1;
		return eval(tokens);
	}

	int eval(String[] ss) {
		switch (ss[i--]) {
			case "+":
				return eval(ss) + eval(ss);
			case "-":
				return -eval(ss) + eval(ss);
			case "*":
				return eval(ss) * eval(ss);
			case "/":
				return (int) (1.0 / eval(ss) * eval(ss));
			default:
				return Integer.valueOf(ss[i + 1]);
		}
	}

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
		return largestRectangleArea2(heights, 0, heights.length - 1);
	}

	public int largestRectangleArea2(int[] heights, int l, int h) {
		if (l > h) return 0;
		if (l == h) return heights[l];

		// Rewrite this using Range Minimum query
		int m = findMin(heights, l, h);

		return Utils.max(largestRectangleArea2(heights, l, m - 1), largestRectangleArea2(heights, m + 1, h),
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
			if (stack.isEmpty() || i < n && (heights[stack.peek()] <= heights[i])) {
				stack.push(i++); // Store the index
			} else {
				topIndex = stack.pop(); // Get the top value, this will be used below to get height
				width = stack.isEmpty() ? i : (i - stack.peek() - 1); // i - peek/prev in the stack
				maxArea = Math.max(maxArea, heights[topIndex] * width);
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
			if (indegree[i] == n - 1 && outdegree[i] == 0) return i;

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
			if (i != celeb && (input[i][celeb] != 1 || input[celeb][i] != 0)) // check celebrity in all the rows, if
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
			if (i != celeb && (input[i][celeb] != 1 || input[celeb][i] != 0)) // check celebrity in all the rows, if
																				// cond satisfies return -1;
				return -1;

		return celeb;
	}

	public boolean knows(int[][] M, int i, int j) {
		return M[i][j] == 1 ? true : false;
	}

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		char ch;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else {
				if (!stack.isEmpty() && ((ch == ')' && stack.peek() == '(') || (ch == ']' && stack.peek() == '[')
						|| (ch == '}' && stack.peek() == '{')))
					stack.pop();
				else return false;
			}
		}

		return stack.isEmpty();
	}

	public String decodeString(String s) {
		String result = "";
		Stack<Integer> countStack = new Stack<>();
		Stack<String> valStack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				int start = i;
				while (Character.isDigit(s.charAt(i + 1)))
					i++;
				countStack.push(Integer.parseInt(s.substring(start, i + 1)));
			} else if (s.charAt(i) == '[') {
				valStack.push(result);
				result = "";
			} else if (s.charAt(i) == ']') {
				StringBuilder sb = new StringBuilder(valStack.pop());
				int repeatTimes = countStack.pop();
				while (repeatTimes-- > 0)
					sb.append(result);
				result = sb.toString();
			} else {
				result += s.charAt(i);
			}
		}
		return result;
	}

	public String simplifyPath(String path) {
		Stack<String> stack = new Stack<>();

		for (String str : path.split("/")) {
			if (!stack.isEmpty() && str.equals("..")) stack.pop();
			else if (!str.equals("") && !str.equals(".") && !str.equals("..")) stack.push(str);
		}

		StringBuilder result = new StringBuilder();
		for (String str : stack)
			result.append("/").append(str);

		return stack.isEmpty() ? "/" : result.toString();
	}

	/*
	 * Longest Absolute File Path:
	 * Note: For example, if s = "\t\t\tdirname", then s.lastIndexOf("\t") will be 2, the number of "\t" will be 3. 
	 * If a diretory name does not contain"\t", then s.lastIndexOf("\t") will be -1, the number of "\t" will be 0.
	 */
	// Approach1: Using Array
	public int lengthLongestPath(String input) {
		String[] files = input.split("\n");
		int[] stack = new int[files.length + 1];
		stack[0] = 0;
		int maxLength = 0;
		for (String str : files) {
			int level = str.lastIndexOf("\t") + 1;
			// (prev len) + (curr str length) - (\t len) + (1 for forward slash)
			int currLength = stack[level] + str.length() - level + 1;
			stack[level + 1] = currLength;
			// check if it is file and currLength-1 for removing slash
			if (str.contains(".")) maxLength = Math.max(maxLength, currLength - 1);
		}

		return maxLength;
	}

	// Approach2: Using Stack
	public int lengthLongestPath2(String input) {
		Stack<Integer> stack = new Stack<>();
		stack.push(0); // "dummy" length
		int maxLen = 0;

		for (String s : input.split("\n")) {
			int lev = s.lastIndexOf("\t") + 1; // number of "\t"
			while (lev + 1 < stack.size())
				stack.pop(); // find parent
			int len = stack.peek() + s.length() - lev + 1; // remove "/t", add"/"
			stack.push(len);
			// check if it is file
			if (s.contains(".")) maxLen = Math.max(maxLen, len - 1);
		}

		return maxLen;
	}

	/********************** Util Methods ********************/
	private static int pow(int a, int b) {
		if (b == 1) return a;
		return a * pow(a, b - 1);
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

	// Longest Valid Parentheses
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (!stack.isEmpty()) max = Math.max(max, i - stack.peek());
				else stack.push(i);
			}
		}
		return max;
	}

	// Approach1: Using Hashing, Time Complexity: O(n)
	public String removeDuplicateLetters1(String s) {
		int[] hash = new int[26];
		for (int i = 0; i < s.length(); i++)
			hash[s.charAt(i) - 'a']++;
		HashSet<Character> visited = new HashSet<>();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			hash[ch - 'a']--;
			if (visited.contains(ch)) continue;
			while (result.length() > 0 && ch < result.charAt(result.length() - 1)
					&& hash[result.charAt(result.length() - 1) - 'a'] > 0) {
				visited.remove(result.charAt(result.length() - 1));
				result.deleteCharAt(result.length() - 1);
			}
			result.append(ch);
			visited.add(ch);
		}
		return result.toString();
	}

	// Approach1: Using Stack, Time Complexity: O(n)
	public String removeDuplicateLetters(String s) {
		int[] hash = new int[26];
		for (int i = 0; i < s.length(); i++)
			hash[s.charAt(i) - 'a']++;
		boolean[] visited = new boolean[26];
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			hash[ch - 'a']--;
			if (visited[ch - 'a']) continue;
			while (!stack.isEmpty() && ch < stack.peek() && hash[stack.peek() - 'a'] > 0) {
				visited[stack.peek() - 'a'] = false;
				stack.pop();
			}
			stack.push(ch);
			visited[ch - 'a'] = true;
		}
		StringBuilder result = new StringBuilder();
		for (char ch : stack)
			result.append(ch);
		return result.toString();
	}
}