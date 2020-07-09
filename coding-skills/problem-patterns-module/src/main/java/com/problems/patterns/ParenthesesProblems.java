package com.problems.patterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ParenthesesProblems {
	//Valid Parentheses/Balanced Brackets
	public static void balancedParentheses() {
		char expr[] = { '{', '(', ')', '}', '[', ']' };
		// char expr[] = { '{', '(', ')', '}', '[', ']', '{', ')' };
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < expr.length; i++) {
			char ch = expr[i];
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else if ((ch == ')' && stack.peek() == '(')
					|| (ch == '}' && stack.peek() == '{')
					|| (ch == ']' && stack.peek() == '[')) {
						stack.pop();
					}
		}

		if (stack.isEmpty())
			System.out.println("Balanced Parentheses");
		else System.out.println("Invalid Expression");
	}

	/* Generate Parentheses - Backtracking 
	 * Generate Parentheses: write a function to generate all combinations of well-formed parentheses.
	 */
	public List<String> generateParentheses(int n) {
		if (n <= 0) return null;
		List<String> res = new ArrayList<>();
		backtrack14(0, 0, n, res, "");
		res.stream()
				.forEach(k -> System.out.print(k + ", "));
		return res;
	}

	public void backtrack14(int op, int cp, int n,
			List<String> res, String str) {
		if (op == n && cp == n) res.add(str);
		if (op < cp) return;
		if (op < n)
			backtrack14(op + 1, cp, n, res, str + "(");
		if (cp < n)
			backtrack14(op, cp + 1, n, res, str + ")");
	}

	//Longest Valid Parentheses - Stack/DP
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (!stack.isEmpty())
					max = Math.max(max, i - stack.peek());
				else stack.push(i);
			}
		}
		return max;
	}

	//Remove Invalid Parentheses - DFS/BFS
	public List<String> removeInvalidParentheses1(
			String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	public void remove(String s, List<String> ans,
			int last_i, int last_j, char[] par) {
		int count = 0;
		for (int i = last_i; i < s.length(); i++) {
			if (s.charAt(i) == par[0]) count++;
			if (s.charAt(i) == par[1]) count--;
			if (count >= 0) continue;
			for (int j = last_j; j <= i; j++)
				if (s.charAt(j) == par[1] && (j == last_j
						|| s.charAt(j - 1) != par[1]))
					remove(s.substring(0, j) + s
							.substring(j + 1, s.length()),
							ans, i, j, par);
			return;
		}
		String reversed = new StringBuilder(s).reverse()
				.toString();
		if (par[0] == '(') remove(reversed, ans, 0, 0,
				new char[] { ')', '(' });
		else ans.add(reversed);
	}

	// Using BFS
	public List<String> removeInvalidParentheses2(
			String s) {
		List<String> res = new ArrayList<>();
		if (s == null) return res;
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		boolean level = false;
		queue.add(s);
		visited.add(s);
		while (!queue.isEmpty()) {
			String curr = queue.poll();
			if (isValid(curr)) {
				res.add(curr);
				level = true;
			}
			if (level) continue;
			for (int i = 0; i < curr.length(); i++) {
				if (curr.charAt(i) != '('
						&& curr.charAt(i) != ')')
					continue;
				String subStr = curr.substring(0, i)
						+ curr.substring(i + 1);
				if (visited.add(subStr)) queue.add(subStr);
			}
		}
		return res;
	}

	private boolean isValid(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') count++;
			if (s.charAt(i) == ')') count--;
			if (count < 0) return false;
		}
		return count == 0;
	}

}
