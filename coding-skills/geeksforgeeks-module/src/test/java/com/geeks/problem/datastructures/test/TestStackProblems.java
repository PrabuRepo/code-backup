package com.geeks.problem.datastructures.test;

import java.util.Arrays;
import java.util.Stack;

import com.geeks.problem.datastructures.StackProblems;

public class TestStackProblems extends StackProblems {
	public static void main(String[] x) {
		TestStackProblems ob = new TestStackProblems();

		// ob.testDesignAndImplProblems();

		// ob.testStandardProblems();

		// ob.testStackOperations();

		ob.testMiscProblems();
	}

	// Design and Implementation
	public void testDesignAndImplProblems() {
		System.out.println("Implement Queue using Stacks:");
		enqueue(1);
		enqueue(2);
		enqueue(3);

		System.out.println("Display: ");
		displayQueue();
		System.out.println("\nPeek: " + peek());
		dequeue();
		System.out.println("Display after dequeue operation:");
		displayQueue();
		System.out.println("\nPeek: " + peek());

	}

	public void testStandardProblems() {
		System.out.println("Expression conversion problems:");
		testExprConversionProblems();

		// String postFixExpr = "231*+9-";
		String postFixExpr = "5332^7-121*+^*+2-";
		System.out.println("Evaluate the post fix expression:" + postFixExpr);
		evaluatePosfixExpression(postFixExpr);

		System.out.println(
				"Infix Expression Evaluation(Basic Calculator-III): " + calculator3("(2+6* 3+5- (3*14/7+2)*5)+3"));
		System.out.println("Check the expr has balances parentheses:");
		balancedParentheses();

		int[] a = { 7, 8, 1, 4 };
		System.out.println("Find the next greater element(Without stack):");
		nextGreaterElement1(a);
		System.out.println("Find the next greater element(Using stack):");
		nextGreaterElement2(a);
		System.out.println("Find the next greater element(Using stack):" + Arrays.toString(nextGreaterElement3(a)));

		System.out.println("Towers of Hanoi puzzle:");
		// towersOfHanoi(4);

		int[] prices = { 10, 4, 5, 90, 120, 80 };
		System.out.println("Stock Span Problems(Brute Force Approach): ");
		stockSpan1(prices);
		System.out.println("Stock Span Problems(Using Stack): ");
		stockSpan2(prices);

		System.out.println("The Celebrity Problem: " + findCelebrity1(4));
		System.out.println("The Celebrity Problem: " + findCelebrity2(4));

		int[][] trust = { { 1, 3 }, { 1, 4 }, { 2, 3 }, { 2, 4 }, { 4, 3 } };
		System.out.println("Judge: " + findJudge(4, trust));
	}

	private void testExprConversionProblems() {
		String infixExpr = null, prefixExpr = null, postFixExpr = null;
		infixExpr = "(a+b*c)/(d-e*f)";
		// infixExpr = "a+b*(c^d-e)^(f+g*h)-i";
		// String expr = "5+3*(3^2-7)^(1+2*1)-2";
		// infixExpr = "(a+b)*(c+d)";
		System.out.println("Infix expression -> " + infixExpr);
		infixToPostfix(infixExpr);
		infixToPrefix(infixExpr);

		// prefixExpr = "*-A/BC-/AKL";
		// prefixExpr = "+a-*b^-^cde+f*ghi";
		prefixExpr = "/+a*bc-d*ef";
		System.out.println("Prefix expression -> " + prefixExpr);
		prefixToInfix(prefixExpr);
		prefixToPostfix(prefixExpr);

		// postFixExpr = "ABC/-AK/L-*";
		postFixExpr = "abc*+def*-/";
		System.out.println("Postfix expression -> " + postFixExpr);
		postfixToPrefix(postFixExpr);
		postfixToInfix(postFixExpr);
	}

	public void testStackOperations() {
		System.out.println("Reverse a stack using recursion: ");
		Stack<Integer> stack = mockData1();
		reverseStack(stack);

		System.out.println("\nSort a stack using recursion");
		stack = mockData2();
		sortStack(stack);

		System.out.println("\nSort a stack using a temporary stack");
		stack = mockData1();
		sortStackUsingTempStack(stack);

		System.out.println("\nDelete middle element of a stack");
		stack = mockData1();
		deleteMiddleElementInStack(stack);
	}

	public void testMiscProblems() {
		String[] tokens1 = { "4", "13", "5", "/", "+" };
		String[] tokens = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" };
		System.out.println("Reverse Polish Notation: " + evalRPN(tokens1));

		int[] heights = { 2, 1, 5, 6, 2, 3 };
		System.out.println("Large rectangle in histogram: " + largestRectangleArea1(heights));
		System.out.println("Large rectangle in histogram: " + largestRectangleArea2(heights));

		String s = "3[a]2[b4[F]c]";
		System.out.println("Decode String: " + decodeString(s));

		String path = "/a//b////c/d//././/..";
		System.out.println("Simplify Path: " + simplifyPath(path));

		System.out.println("Longest Path: " + lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
	}

	private Stack<Integer> mockData1() {
		Stack<Integer> stack = new Stack<>();
		stack.push(5);
		stack.push(6);
		stack.push(2);
		stack.push(9);
		stack.push(1);
		stack.push(3);
		return stack;
	}

	private Stack<Integer> mockData2() {
		Stack<Integer> stack = new Stack<>();
		stack.push(30);
		stack.push(-5);
		stack.push(18);
		stack.push(14);
		stack.push(-3);
		return stack;
	}

}
