package com.basic.datastructures;

import java.util.Arrays;

import com.common.model.ListNode;

/**
 * Queue Implementation using Array & Linked List
 */

public class Queue {
	public static void main(String[] args) {
		System.out.println("Queue Operations: ");
		Queue ob = new Queue();

		System.out
				.println("Queue - Array Impl: ");
		ob.testQueueArrayImpl();

		System.out.println(
				"Queue - LinkedList Impl: ");
		ob.testQueueLinkedListImpl();
	}

	public void testQueueArrayImpl() {
		QueueArrayImpl queue = new QueueArrayImpl(
				5);
		System.out.println("Stack Operations:");
		System.out.println("EnQueue");
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		System.out.println("Display:");
		queue.display();
		System.out.println(
				"\nDequeue: " + queue.deQueue());
		System.out.println(
				"Front: " + queue.getFront());
		System.out.println(
				"Rear: " + queue.getRear());
	}

	public void testQueueLinkedListImpl() {
		QueueLinkedListImpl queue = new QueueLinkedListImpl();
		System.out.println("Stack Operations:");
		System.out.println("EnQueue");
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		System.out.println("Display:");
		queue.display();
		System.out.println(
				"\nDequeue: " + queue.deQueue());
		System.out.println(
				"Front: " + queue.getFront());
		System.out.println(
				"Rear: " + queue.getRear());
	}

}

class QueueArrayImpl {
	int[] queue;
	int   front, rear;
	int   maxSize;

	public QueueArrayImpl(int size) {
		this.maxSize = size;
		this.front = this.rear = -1;
		this.queue = new int[maxSize];
		Arrays.fill(queue, -1);
	}

	public void enQueue(int data) {
		if (!isFull()) {
			if (isEmpty()) // When Queue is empty; both front & rear will be increased zero.
				front++;
			queue[++rear] = data; // Normal case: Simply insert the data in the rear end
		} else {
			System.out.println("Queue is Full!");
		}
	}

	public int deQueue() {
		if (isEmpty()) return -1;
		int element = queue[front];
		if (front == rear) front = rear = -1;
		else front++;
		return element;
	}

	public boolean isEmpty() {
		return front == -1;
	}

	public boolean isFull() {
		return rear == maxSize - 1;
	}

	public int getFront() {
		return isEmpty() ? -1 : queue[front];
	}

	public int getRear() {
		return isEmpty() ? -1 : queue[rear];
	}

	public void display() {
		if (!isEmpty()) {
			for (int i = front; i <= rear; i++)
				System.out.print(queue[i] + " ");
		}
	}
}

class QueueLinkedListImpl {
	ListNode front, rear;

	public void enQueue(int n) {
		ListNode newNode = new ListNode(n);
		if (isEmpty()) {
			front = rear = newNode;
		} else {
			rear.next = newNode;
			rear = rear.next;
		}
	}

	public int deQueue() {
		if (isEmpty()) return -1;
		int data = front.data;
		if (front == rear) {
			front = rear = null;
		} else {
			front = front.next;
		}
		return data;
	}

	public boolean isEmpty() {
		return (front == null && rear == null);
	}

	public int getFront() {
		return !isEmpty() ? front.data : null;
	}

	public int getRear() {
		return !isEmpty() ? rear.data : null;
	}

	public void display() {
		if (isEmpty()) {
			System.out.print("Queue is Empty!!!");
		} else {
			ListNode curr = front;
			while (curr != null) {
				System.out.print(curr.data + " ");
				curr = curr.next;
			}
		}
	}

}