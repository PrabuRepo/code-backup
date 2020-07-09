package com.gaylemcdowell.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Ch03StacksAndQueues {
	public static void main(String[] args) {
		Ch03StacksAndQueues ob = new Ch03StacksAndQueues();

		System.out.println("Stack Min: ");
		ob.stackMin1();
		ob.stackMin2();
		ob.stackMin3();

		ob.animalShelter();

		ob.stackOfPlates();
	}

	/*
	 * 1.Three in One: Describe how you could use a single array to implement three stacks.
	 */
	public void threeInOne() {

	}

	/*
	 * 2.Stack Min: How would you design a stack which, in addition to push and pop, has a function min which returns the minimum
	 * element? Push, pop and min should all operate in 0(1) time.
	 */
	public void stackMin1() {
		MinStack stack = new MinStack();
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

	public void stackMin2() {
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

	public void stackMin3() {
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

	/*
	 * 3.Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, 
	 * we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure SetOfStacks 
	 * that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the previous one 
	 * exceeds capacity. SetOfStacks. push () and SetOfStacks. pop () should behave identically to a single stack (that is, pop() 
	 * should return the same values as it would if there were just a single stack).
	 * FOLLOW UP: Implement a function popAt (int index) which performs a pop operation on a specific sub-stack.
	 */
	public void stackOfPlates() {
		StackOfPlates sp = new StackOfPlates(3);
		System.out.println("Push: ");
		for (int i = 1; i <= 10; i++)
			sp.push(i);

		System.out.println("Size of stack of plates: " + sp.stacks.size());
		System.out.println("Pop: " + sp.pop());
		System.out.println("Size of stack of plates: " + sp.stacks.size());
		System.out.println("Pop: " + sp.pop());
		System.out.println("Pop: " + sp.pop());
		System.out.println("Pop: " + sp.pop());
		System.out.println("Size of stack of plates: " + sp.stacks.size());
		System.out.println("Pop: " + sp.pop());
		System.out.println("Pop: " + sp.pop());
		System.out.println("Pop: " + sp.pop());
		System.out.println("Size of stack of plates: " + sp.stacks.size());
	}

	/*
	 * 4.Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
	 */
	public void queueViaStacks() {

	}

	/*
	 * 5.Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use an additional 
	 * temporary stack, but you may not copy the elements into any other data structure (such as an array). The stack supports 
	 * the following operations: push, pop, peek, and isEmpty.
	 */
	public void sortStack() {

	}

	/*
	 * 6.Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first out" basis. People
	 * must adopt either the "oldest" (based on arrival time) of all animals at the shelter, or they can select whether they would 
	 * prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would
	 * like. Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog, 
	 * and dequeueCat. You may use the built-in Linked List data structure.
	 */
	public void animalShelter() {
		AnimalQueue queue = new AnimalQueue();
		System.out.println("Enqueue Animals: ");
		queue.enqueue(new Dog("dog1"));
		queue.enqueue(new Cat("cat1"));
		queue.enqueue(new Dog("dog2"));
		queue.enqueue(new Dog("dog3"));
		queue.enqueue(new Cat("cat2"));

		System.out.println("Dequeue Any: " + queue.dequeueAny().name);
		System.out.println("Dequeue Cat: " + queue.dequeueCat().name);
		System.out.println("Dequeue Dog: " + queue.dequeueDog().name);
		System.out.println("Dequeue Any: " + queue.dequeueAny().name);
	}
}

// Using additional Min Variable
class MinStack {
	public Node top;

	public MinStack() {

	}

	public void push(int x) {
		if (top == null) {
			top = new Node(x, x);
		} else {
			Node node = new Node(x, Math.min(x, top.min));
			node.next = top;
			top = node;
		}

	}

	public void pop() {
		if (top == null)
			return;
		Node temp = top.next;
		top.next = null;
		top = temp;

	}

	public int top() {
		if (top == null)
			return -1;
		return top.value;
	}

	public int getMin() {
		if (top == null)
			return -1;
		return top.min;
	}
}

class Node {
	public int	value;
	public int	min;
	public Node	next;

	public Node(int value, int min) {
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
			if (data <= min())
				min.push(data);
		}
		super.push(data);
	}

	public Integer pop() {
		if (!super.isEmpty()) {
			if (super.peek() == min())
				min.pop();
			return super.pop();
		}
		return -1;
	}

	public int min() {
		return min.isEmpty() ? Integer.MIN_VALUE : min.peek();
	}
}

// Design and Implement Special Stack Data Structure, using O(1) space
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
		if (stack.isEmpty())
			return -1;
		return (stack.peek() < minElement) ? minElement : stack.peek();
	}

	public int min() {
		return stack.isEmpty() ? -1 : minElement;
	}
}

abstract class Animal {
	private int		order;
	public String	name;

	public Animal(String name) {
		this.name = name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}

	public boolean isOlderThan(Animal a) {
		return this.order < a.getOrder();
	}
}

class Dog extends Animal {
	public Dog(String name) {
		super(name);
	}
}

class Cat extends Animal {
	public Cat(String name) {
		super(name);
	}
}

class AnimalQueue {
	public LinkedList<Dog>	dogs;
	public LinkedList<Cat>	cats;
	int						order;

	public AnimalQueue() {
		order = 0;
		dogs = new LinkedList<>();
		cats = new LinkedList<>();
	}

	public void enqueue(Animal a) {
		a.setOrder(order);
		order++;

		if (a instanceof Dog) {
			dogs.add((Dog) a);
		} else if (a instanceof Cat) {
			cats.add((Cat) a);
		}
	}

	public Animal dequeueAny() {
		if (dogs.size() == 0)
			return dequeueCat();
		else if (cats.size() == 0)
			return dequeueDog();
		else {
			Dog dog = dogs.peek();
			Cat cat = cats.peek();
			if (dog.isOlderThan(cat))
				return dequeueDog();
			else
				return dequeueCat();
		}
	}

	public Dog dequeueDog() {
		return dogs.poll();
	}

	public Cat dequeueCat() {
		return cats.poll();
	}
}

class StackOfPlates {
	List<Stack<Integer>>	stacks	= new ArrayList<>();
	public int				capacity;

	public StackOfPlates(int cap) {
		this.capacity = cap;
	}

	public void push(int data) {
		Stack<Integer> stack = getTopStack();
		if (stack != null && stack.size() < capacity) {
			stack.push(data);
		} else {
			stack = new Stack<>();
			stack.push(data);
			stacks.add(stack);
		}
	}

	public int pop() {
		Stack<Integer> stack = getTopStack();
		int top = -1;
		if (stack != null) {
			top = stack.pop();
			if (stack.isEmpty())
				stacks.remove(stacks.size() - 1);
		}
		return top;
	}

	public Stack<Integer> getTopStack() {
		if (stacks.size() == 0)
			return null;

		return stacks.get(stacks.size() - 1);
	}
}