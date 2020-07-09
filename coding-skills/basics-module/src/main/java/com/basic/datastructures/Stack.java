package com.basic.datastructures;

import java.util.Arrays;

import com.common.model.ListNode;

/**
 * Stack Implementation using Linked List & Array
 */

public class Stack {
	public static void main(String[] args) {
		System.out.println("Stack Operations: ");
		Stack ob = new Stack();

		System.out
				.println("Stack - Array Impl: ");
		ob.testStackArrayImpl();

		System.out.println(
				"Stack - LinkedList Impl: ");
		ob.testStackLinkedListImpl();
	}

	public void testStackArrayImpl() {
		StackArrayImpl stack = new StackArrayImpl(
				5);
		System.out.println("Stack Operations:");
		System.out.println("Push");
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println("Display:");
		stack.display();
		System.out
				.println("\npop: " + stack.pop());
		System.out
				.println("peek: " + stack.peek());
	}

	public void testStackLinkedListImpl() {
		StackLinkedListImpl stack = new StackLinkedListImpl();
		System.out.println("Stack Operations:");
		System.out.println("Push");
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println("Display:");
		stack.display();
		System.out
				.println("\npop: " + stack.pop());
		System.out
				.println("peek: " + stack.peek());
	}
}

class StackArrayImpl {

	int[]	stack;
	int		top;
	int		maxSize;

	public StackArrayImpl(int size) {
		this.maxSize = size;
		this.top = -1;
		this.stack = new int[maxSize];
		Arrays.fill(stack, -1);
	}

	public void push(int data) {
		if (!isFull()) stack[++top] = data;
		else System.out.println("Stack is Full!");

	}

	public int pop() {
		return isEmpty() ? -1 : stack[top--];
	}

	public int peek() {
		return isEmpty() ? -1 : stack[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == maxSize - 1;
	}

	public void display() {
		if (!isEmpty()) {
			for (int i = 0; i <= top; i++)
				System.out.print(stack[i] + " ");
		}
	}
}

class StackLinkedListImpl {
	ListNode stack;

	public void push(int data) {
		ListNode newNode = new ListNode(data);
		if (stack == null) {
			stack = newNode;
		} else {
			newNode.next = stack;
			stack = newNode;
		}
	}

	public boolean isEmpty() {
		return stack == null ? true : false;
	}

	public int pop() {
		int data = -1;
		if (!isEmpty()) {
			data = stack.data;
			stack = stack.next;
		}
		return data;
	}

	public int peek() {
		return stack != null ? stack.data : null;
	}

	public void display() {
		ListNode temp = stack;
		if (isEmpty()) {
			System.out
					.println("Stack is empty!!!");
		} else {
			while (temp != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
		}
	}
}