package com.andrei.session;

public class ExpressionEvaluation {
	char[] data = new char[20];
	int pos = -1;

	boolean evalExpr(String expr) {
		boolean flag = false;
		for (int i = 0; i < expr.length(); i++) {
			if (expr.charAt(i) == '{' || expr.charAt(i) == '(' || expr.charAt(i) == '[') {
				push(expr.charAt(i));
			} else if (expr.charAt(i) == '}' || expr.charAt(i) == ')' || expr.charAt(i) == ']') {
				pop();
				if (isEmpty())
					flag = true;
			}
		}
		return flag;
	}

	void push(char a) {
		data[++pos] = a;
	}

	void pop() {
		data[pos] = ' ';
		pos--;
	}

	boolean isEmpty() {
		return pos == -1 ? true : false;
	}

	public static void main(String[] args) {
		String expr = "{[([)]}";
		ExpressionEvaluation obj = new ExpressionEvaluation();
		System.out.println("Is it a valid expression:" + obj.evalExpr(expr));
	}

}
