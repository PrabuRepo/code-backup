package com.consolidated.problems.design;

import java.util.Stack;

public class MinStack {

	public static void main(String[] args) {
		MinStack ob = new MinStack();
		ob.testStackMin1();
		ob.testStackMin2();
		ob.testStackMin3();
	}

	public void testStackMin1() {
		MinStack1 stack = new MinStack1();
		System.out.println("Stack (using Auxiliary Stack): ");
		stack.push(4);
		stack.push(3);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		System.out.println("Min: " + stack.getMin());
		System.out.println("Pop: " + stack.top());
		stack.pop();
		System.out.println("Min: " + stack.getMin());
		System.out.println("Pop: " + stack.top());
		stack.pop();
		System.out.println("Min: " + stack.getMin());
		System.out.println("Pop: " + stack.top());
		stack.pop();
		System.out.println("Min: " + stack.getMin());
		System.out.println("Pop: " + stack.top());
		stack.pop();
	}

	public void testStackMin2() {
		MinStack2 stack = new MinStack2();
		System.out.println("Stack (using min variable): ");
		stack.push(4);
		stack.push(3);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
	}

	public void testStackMin3() {
		MinStack3 stack = new MinStack3();
		System.out.println("Stack (using min variable & O(1) space): ");
		stack.push(4);
		stack.push(3);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
	}
}

// Using additional Min Variable
class MinStack1 {
	public StackNode top;

	public MinStack1() {

	}

	public void push(int x) {
		if (top == null) {
			top = new StackNode(x, x);
		} else {
			StackNode node = new StackNode(x, Math.min(x, top.min));
			node.next = top;
			top = node;
		}

	}

	public void pop() {
		if (top == null) return;
		StackNode temp = top.next;
		top.next = null;
		top = temp;

	}

	public int top() {
		if (top == null) return -1;
		return top.value;
	}

	public int getMin() {
		if (top == null) return -1;
		return top.min;
	}
}

class StackNode {
	public int			value;
	public int			min;
	public StackNode	next;

	public StackNode(int value, int min) {
		this.value = value;
		this.min = min;
	}
}

// Design and Implement Special Stack Data Structure, using one auxiliary min stack
class MinStack2 extends Stack<Integer> {
	private static final long serialVersionUID = 53463461L;

	// To hold the min values
	Stack<Integer> min;

	public MinStack2() {
		min = new Stack<>();
	}

	public void push(int data) {
		if (this.empty()) {
			min.push(data);
		} else {
			if (data <= min()) min.push(data);
		}
		super.push(data);
	}

	public Integer pop() {
		if (!super.isEmpty()) {
			if (super.peek() == min()) min.pop();
			return super.pop();
		}
		return -1;
	}

	public int min() {
		return min.isEmpty() ? Integer.MIN_VALUE : min.peek();
	}
}

/* Design and Implement Special Stack Data Structure, using O(1) space?
 *  
 * How does this approach work? When element to be inserted is less than minEle, we insert “2x – minEle”. The important
 * thing to notes is, 2x – minEle will always be less than x (proved below), i.e., new minEle and while popping out this
 * element we will see that something unusual has happened as the popped element is less than the minEle. So we will be
 * updating minEle.
 */
class MinStack3 {
	public int				minElement;
	public Stack<Integer>	stack	= new Stack<>();

	public void push(int x) {
		if (stack.isEmpty()) {
			stack.push(x);
			minElement = x;
		} else {
			if (x < minElement) {
				stack.push(2 * x - minElement);
				minElement = x;
			} else {
				stack.push(x);
			}
		}
	}

	public int pop() {
		int top = -1;
		if (!stack.isEmpty()) {
			if (stack.peek() < minElement) {
				top = minElement;
				minElement = 2 * minElement - stack.peek();
				stack.pop();
			} else {
				top = stack.pop();
			}
		}
		return top;

	}

	public int top() {
		if (stack.isEmpty()) return -1;
		return (stack.peek() < minElement) ? minElement : stack.peek();
	}

	public int min() {
		return stack.isEmpty() ? -1 : minElement;
	}
}