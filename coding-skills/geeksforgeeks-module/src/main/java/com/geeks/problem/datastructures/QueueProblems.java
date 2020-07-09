package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.common.utilities.Utils;

public class QueueProblems {

	/**************** Introduction ******************/

	/*	Queue - Enqueue/Dequeue/peek
		Priority Queue
		Deque
		Circular Queue
		Queue Interface In Java
	*/

	/**************** Implementation ******************/

	/**************** Standard Problems ******************/
	/* Page Fault: Whenever a new page is referred and not present in memory, page fault occurs and Operating System
	 * replaces one of the existing pages with newly needed page
	 **/
	// FIFO page replacement in Operating Systems.
	public int noOfPageFaults(int[] pages,
			int capacity) {
		HashSet<Integer> set = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		int pageFaults = 0;

		for (int i = 0; i < pages.length; i++) {

			if (!set.contains(pages[i])) {
				if (queue.size() >= capacity) {
					int front = queue.remove(); // Remove the front element
					set.remove(front); // Remove the same element from set
				}
				pageFaults++;
				set.add(pages[i]);
				queue.add(pages[i]);
			} else {
				queue.remove(pages[i]); // Remove and add in the front
				queue.add(pages[i]);
			}
		}
		return pageFaults;
	}

	/**************** Operations on Queue ******************/
	// Stack Implementation using 2 queues
	Queue<Integer>	q1	= new LinkedList<Integer>();
	Queue<Integer>	q2	= new LinkedList<Integer>();
	Queue<Integer>	q	= new LinkedList<Integer>();

	// 1.Stack Implementation using 2 queues - Approach1: pop-O(n) time
	// Push - Time:O(1)
	public void push1(int a) {
		q1.add(a);
	}

	// Pop - Time:O(n)
	public int pop1() {
		if (q1.isEmpty()) return -1;

		while (q1.size() > 1)
			q2.add(q1.poll());
		int top = q1.poll();

		// Assign q2 to q1
		Queue<Integer> temp = q1;
		q1 = q2;
		q2 = temp;

		return top;
	}

	public int peek1() {
		if (q1.isEmpty()) return -1;

		int top = pop1();
		q1.add(top);
		return top;
	}

	// 2.Stack Implementation using 2 queues - Approach2: push-O(n) time - Its better than Approach1
	// Push - Time:O(1)
	public void push2(int a) {
		q2.add(a);
		while (!q1.isEmpty()) {
			q2.add(q1.poll());
		}

		// Assign q2 to q1
		Queue<Integer> temp = q1;
		q1 = q2;
		q2 = temp;
	}

	// Pop - Time:O(n)
	public int pop2() {
		if (q1.isEmpty()) return -1;
		return q1.poll();
	}

	public int peek2() {
		if (q1.isEmpty()) return -1;
		return q1.peek();
	}

	// Implement a stack using single queue
	// Stack using queue: insert
	public void push(int a) {
		q.add(a);
		int n = q.size();
		while (n-- > 1)
			q.add(q.poll());
	}

	// Stack using queue: delete
	public int pop() {
		return q.poll();
	}

	public int peek() {
		return q.peek();
	}

	/**************** Misc Problems ******************/

	public void reverseQueue(
			Queue<Integer> queue) {
		System.out.println("Before reverse: ");
		queue.stream().forEach(
				k -> System.out.print(k + " "));
		reverseQueueUtil(queue);
		System.out.println("\nAfter reverse: ");
		queue.stream().forEach(
				k -> System.out.print(k + " "));
	}

	private void reverseQueueUtil(
			Queue<Integer> queue) {
		if (queue.isEmpty()) return;

		int element = queue.poll();
		reverseQueueUtil(queue);

		queue.add(element);
	}

	public void sortQueue(Queue<Integer> queue) {
		System.out.println("Before reverse: ");
		queue.stream().forEach(
				k -> System.out.print(k + " "));
		sortQueueUtil(queue);
		System.out.println("\nAfter reverse: ");
		queue.stream().forEach(
				k -> System.out.print(k + " "));
	}

	private void sortQueueUtil(
			Queue<Integer> queue) {
		if (queue.isEmpty()) return;

		int element = queue.poll();
		sortQueueUtil(queue);
		// sort(); Not completed
	}

	// First non-repeating character in a stream
	public void firstNonRepeatingChar(char[] ch) {
		if (ch.length == 0) return;

		Queue<Character> queue = new LinkedList<>();
		int[] count = new int[26];
		for (int i = 0; i < ch.length; i++) {
			// Add first non repeating char in queue
			queue.add(ch[i]);
			// Increase the count array corresponding to the char
			count[ch[i] - 'a']++;

			while (!queue.isEmpty()) {
				if (count[queue.peek()
						- 'a'] > 1) {
					queue.poll();
				} else {
					System.out.print(
							queue.peek() + " ");
					break;
				}
			}
			if (queue.isEmpty())
				System.out.print("-1" + " ");
		}
	}

	/* Queue Reconstruction by Height - Sort & Insertion 
	 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 
	 * where h is the height of the person and k is the number of people in front of this person who have a height greater 
	 * than or equal to h. Write an algorithm to reconstruct the queue.
	 */
	public int[][] reconstructQueue(
			int[][] people) {
		int n = people.length;
		int[][] result = new int[n][2];

		// Sort people 2D array;
		Arrays.sort(people,
				new Comparator<int[]>() {
					public int compare(int[] a1,
							int[] a2) {
						if (a1[0] != a2[0]) // Desc order(height h), if 0th index is not equal
							return a2[0] - a1[0];
						else// Asc order(number of people k), if 0th index is not equal
							return a1[1] - a2[1];
					}
				});

		System.out.println("After Sorting: ");
		Utils.print2DArray(people);

		ArrayList<int[]> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			int[] arr = people[i];
			list.add(arr[1], arr);
		}

		for (int i = 0; i < n; i++)
			result[i] = list.get(i);

		System.out.println("\nResult: ");
		Utils.print2DArray(result);

		return result;
	}

	/* Greedy Algorithm: 
	*The algorithm is pretty easy to understand. Imagine we take a tour around this circle, the only condition that we can complete this trip is to have more fuel provided than costed in total. That's what the first loop does.
	
	If we do have more fuel provided than costed, that means we can always find a start point around this circle that we could complete the journey with an empty tank. Hence, we check from the beginning of the array, if we can gain more fuel at the current station, we will maintain the start point, else, which means we will burn out of oil before reaching to the next station, we will start over at the next station.
	*/
	// Greedy Algorithm:
	public int canCompleteCircuit1(int[] gas,
			int[] cost) {
		int n = gas.length, tank = 0;
		for (int i = 0; i < n; i++)
			tank += gas[i] - cost[i];
		if (tank < 0) return -1;
		int rem = 0, start = 0;
		for (int i = 0; i < n; i++) {
			rem += gas[i] - cost[i];
			if (rem < 0) {
				start = i + 1;
				rem = 0;
			}
		}
		return start;
	}

	// Using Circular Array/Queue
	public int canCompleteCircuit(int[] gas,
			int[] cost) {
		int n = gas.length, front = 0, rear = 1;
		int rem = gas[front] - cost[front];
		if (n == 1) return rem < 0 ? -1 : 0;
		while (front != rear || rem < 0) {
			// Move front ptr
			while (front != rear && rem < 0) {
				rem -= gas[front] - cost[front];
				front = (front + 1) % n;
				if (front == 0) return -1;
			}
			rem += gas[rear] - cost[rear];
			rear = (rear + 1) % n;
		}
		return front;
	}

}
