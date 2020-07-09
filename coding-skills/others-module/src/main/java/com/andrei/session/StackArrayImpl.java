package com.andrei.session;

import java.util.Arrays;

public class StackArrayImpl {

	int[] data = new int[20];
	int pos = -1;

	void push(int a) {
		data[++pos] = a;
	}

	void pop() {
		data[pos] = 0;
		pos--;
	}

	boolean isEmpty() {
		return pos == -1 ? true : false;
	}

	int top() {
		return data[pos];
	}

	public static void main(String[] args) {

		System.out.println("Stack Operation:");
		StackArrayImpl obj = new StackArrayImpl();
		obj.push(10);
		obj.push(4);
		obj.pop();
		obj.push(7);
		obj.push(11);
		
		if (!obj.isEmpty())
			System.out.println("Top Element:" + obj.top());
		System.out.println("Stack Elements:"+Arrays.toString(obj.data));
		obj.pop();
		obj.pop();
		obj.pop();
		System.out.println("Is stack empty:"+obj.isEmpty());
	}

}
