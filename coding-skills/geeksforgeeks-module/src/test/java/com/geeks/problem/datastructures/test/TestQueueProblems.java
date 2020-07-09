package com.geeks.problem.datastructures.test;

import java.util.LinkedList;
import java.util.Queue;

import com.geeks.problem.datastructures.QueueProblems;

public class TestQueueProblems extends QueueProblems {
	public static void main(String[] args) {
		int pages[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };
		// int[] pages = { 0, 2, 1, 6, 4, 0, 1, 0, 3, 1, 2, 1 };
		// int[] pages = { 3, 1, 0, 2, 5, 4, 1, 2 };

		QueueProblems ob = new QueueProblems();
		System.out.println("Page Fault: " + ob.noOfPageFaults(pages, 4));

		System.out.println("Insert:");
		ob.push(1);
		ob.push(2);
		ob.push(3);
		ob.push(4);

		System.out.println("Delete:");
		System.out.println("Peek: " + ob.peek());
		System.out.println("Pop: " + ob.pop());
		System.out.println("Peek: " + ob.peek());
		System.out.println("Pop: " + ob.pop());
		System.out.println("Peek: " + ob.peek());
		System.out.println("Pop: " + ob.pop());
		System.out.println("Peek: " + ob.peek());
		System.out.println("Pop: " + ob.pop());

		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		ob.reverseQueue(queue);

		System.out.println("First non-repeating character in a stream: ");
		ob.firstNonRepeatingChar("aabc".toCharArray());

		int[][] people = { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
		System.out.println("\nReconstruct Queue: ");
		ob.reconstructQueue(people);

	}
}
