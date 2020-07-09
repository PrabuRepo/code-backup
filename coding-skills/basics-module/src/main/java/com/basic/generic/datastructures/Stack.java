package com.basic.generic.datastructures;

import java.util.Scanner;

/**
 * 
 * Stack Linked List Implementation
 *
 */

public class Stack<T> {
	StackNode<T> stack;

	public void push(T data) {
		StackNode<T> newNode = new StackNode<>(data);
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

	public T pop() {
		T data = null;
		if (!isEmpty()) {
			data = stack.data;
			stack = stack.next;
		}
		return data;
	}

	public T peek() {
		return stack != null ? stack.data : null;
	}

	public void display() {
		StackNode<T> temp = stack;
		if (isEmpty()) {
			System.out.println("Stack is empty!!!");
		} else {
			while (temp != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		Stack<Integer> stack = new Stack<>();
		do {
			System.out.println("Stack Operations:");
			System.out.println("1.Push");
			System.out.println("2.pop");
			System.out.println("3.peek");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {
			case 1:
				System.out.println("Enter no of elements to be inserted:");
				int t = in.nextInt();
				while (t-- > 0) {
					stack.push(in.nextInt());
				}
				System.out.println("Elements are inserted!");
				break;
			case 2:
				System.out.println("Pop operation: " + stack.pop());
				break;
			case 3:
				System.out.println("Top element in the stack: " + stack.peek());
				break;
			default:
				System.out.println("Please enter the valid option!!!");
				break;
			}

			System.out.println("\nDisplay:");
			stack.display();

			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();

	}
}

class StackNode<T> {
	T				data;
	StackNode<T>	next;

	public StackNode(T data) {
		this.data = data;
		this.next = null;
	}
}
