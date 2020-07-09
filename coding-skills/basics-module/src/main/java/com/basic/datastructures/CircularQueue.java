package com.basic.datastructures;

import java.util.Arrays;
import java.util.Scanner;

import com.common.model.ListNode;

public class CircularQueue {

	public void testCircularDLinkedList() {
		CircularDLinkedList list = new CircularDLinkedList();
		System.out.println("1.Insert");
		list.enQueue(1);
		list.enQueue(2);
		list.enQueue(3);
		System.out.println("\nDisplay:");
		list.display();
		System.out.println("2.Delete: ");
		list.deQueue();
		System.out.println("3.Find: " + list.front);
	}
}

class CircularQueueArrayImpl {
	int[] queue;
	int   front;
	int   rear;
	int   maxSize;

	public CircularQueueArrayImpl(int size) {
		this.maxSize = size;
		this.queue = new int[maxSize];
		Arrays.fill(queue, -1);
		this.front = -1;
		this.rear = -1;
	}

	public void enqueue(int data) {
		if (isFull()) {
			System.out.println("Queue is Full!");
		} else if (isEmpty()) { // When Queue is empty; both front & rear will be increased zero.
			front++;
			rear++;
		} else if (front > 0 && rear == maxSize - 1) {
			// When rear is maxSize and front>0 -> Meaning there is some space in front
			rear = 0;
		} else { // Normal case: Simply insert the data in the rear end
			rear++;
		}
		queue[rear] = data;
	}
	/*public void enqueue(int data) {
		if ((front == 0 && rear == maxSize - 1) || (front == rear + 1)) {
			System.out.println("Queue is full!");
		} else if (front == -1) {
			++front;
			queue[++rear] = data;
		} else if (rear == maxSize - 1 && front > 0) {
			rear = 0;
			queue[rear] = data;
		} else {
			queue[++rear] = data;
		}
	}*/

	public int dequeue() {
		int element = -1;
		if (!isEmpty()) {
			element = queue[front];
			queue[front] = -1;
			if (front == rear) { // If both are same, only one data present in the queue
				front = rear = -1;
			} else if (front == maxSize - 1 && rear >= 0) { // front is in last index and rear >0; Meaning: There are
															// elements in the Front
				front = 0;
			} else {
				front++; // Normal case: Simply increase the front count
			}
		} else {
			System.out.println("Queue is Empty!");
		}
		return element;
	}

	/*public int dequeue() {
		int element = -1;
		if (front == -1) {
			System.out.println("Queue is empty");
			return element;
		}
		element = queue[front];
		queue[front] = -1;
		if (front == maxSize - 1 && front != rear) {
			front = 0;
		} else if (front == rear) {
			rear = front = -1;
		} else {
			front++;
		}
		return element;
	}*/

	public void display() {
		if (front == -1) {
			System.out.println("Queue is empty!");
		} else if (front <= rear) { // Normal Case: Print from front to rear
			for (int i = front; i <= rear; i++)
				System.out.print(queue[i] + " ");
		} else { // Rear moved to place front side and front is placed in rear side
			for (int i = front; i < maxSize; i++)
				System.out.print(queue[i] + " ");
			for (int i = 0; i <= rear; i++)
				System.out.print(queue[i] + " ");
		}
	}

	public int getFront() {
		return front == -1 ? -1 : queue[front];
	}

	public boolean isEmpty() {
		return front == -1;
	}

	public boolean isFull() {
		return (front == 0 && rear == maxSize - 1)
				|| (front == rear + 1);
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		char ch;
		CircularQueueArrayImpl queue = new CircularQueueArrayImpl(
				6);
		do {
			System.out
					.println("Circular Queue Operations:");
			System.out.println("1.Enqueue");
			System.out.println("2.Dequeue");
			System.out.println("3.Front");
			System.out.print("Enter option:");
			switch (in.nextInt()) {
				case 1:
					System.out.println(
							"Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0) {
						queue.enqueue(in.nextInt());
					}
					System.out.println(
							"Elements are inserted!");
					break;
				case 2:
					int data = queue.dequeue();
					if (data != -1) System.out.println(
							"Dequeued element is: " + data);
					else System.out
							.println("Queue is empty");
					break;
				case 3:
					data = queue.getFront();
					if (data != -1) System.out.println(
							"Front element is: " + data);
					else System.out
							.println("Queue is empty");
					break;
				default:
					System.out.println(
							"Please enter the valid option!!!");
					break;

			}

			System.out.println("\nDisplay:");
			queue.display();
			System.out.println(
					"\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}
}

class MyCircularQueue {
	private int   front, rear, currSize;
	private int[] queue;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public MyCircularQueue(int k) {
		rear = -1;
		currSize = 0;
		queue = new int[k];
		Arrays.fill(queue, -1);
	}

	/** Insert an element into the circular queue. Return true if the operation is successful. */
	public boolean enQueue(int value) {
		if (isFull()) return false;

		rear = (rear + 1) % queue.length;
		queue[rear] = value;
		currSize++;
		return true;
	}

	/** Delete an element from the circular queue. Return true if the operation is successful. */
	public boolean deQueue() {
		if (isEmpty()) return false;

		front = (front + 1) % queue.length;
		currSize--;
		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		return isEmpty() ? -1 : queue[front];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		return isEmpty() ? -1 : queue[rear];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return currSize == 0;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		return currSize == queue.length;
	}
}

class CircularQueueSLL {
	ListNode front, rear;

	public void enQueue(int data) {
		ListNode node = new ListNode(data);
		if (isEmpty()) {
			front = rear = node;
			rear.next = front;
		} else {
			node.next = rear.next;
			rear.next = node;
			rear = node;
		}
	}

	public int deQueue() {
		int element = -1;
		if (!isEmpty()) {
			element = front.data;
			if (front == rear) {
				front = rear = null;
			} else {
				front = front.next;
				rear.next = front;
			}
		}
		return element;
	}

	public boolean isEmpty() {
		return (front == null && rear == null);
	}

	public int getFront() {
		return isEmpty() ? -1 : front.data;
	}

	public void display() {
		if (!isEmpty()) {
			ListNode temp = front;
			do {
				System.out.print(temp.data + " ");
				temp = temp.next;
			} while (temp != front);
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		char ch;
		CircularQueueSLL queue = new CircularQueueSLL();
		do {
			System.out
					.println("Circular Queue Operations:");
			System.out.println("1.Enqueue");
			System.out.println("2.Dequeue");
			System.out.println("3.Front");
			System.out.print("Enter option:");
			switch (in.nextInt()) {
				case 1:
					System.out.println(
							"Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0) {
						queue.enQueue(in.nextInt());
					}
					System.out.println(
							"Elements are inserted!");
					break;
				case 2:
					int data = queue.deQueue();
					if (data != -1) System.out.println(
							"Dequeued element is: " + data);
					else System.out
							.println("Queue is empty");
					break;
				case 3:
					data = queue.getFront();
					if (data != -1) System.out.println(
							"Front element is: " + data);
					else System.out
							.println("Queue is empty");
					break;
				default:
					System.out.println(
							"Please enter the valid option!!!");
					break;
			}

			System.out.println("\nDisplay:");
			queue.display();
			System.out.println(
					"\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}
}

class CircularDLinkedList {
	ListNode front, rear;

	public void enQueue(int data) {
		ListNode newNode = new ListNode(data);
		if (isEmpty()) {
			rear = front = newNode;
		} else {
			newNode.prev = rear;
			rear.next = newNode;
			rear = newNode;
		}
		front.prev = rear;
		rear.next = front;
	}

	public int deQueue() {
		int element = -1;
		if (!isEmpty()) {
			element = front.data;
			if (front == rear) {
				front = rear = null;
			} else {
				front = front.next;
				front.prev = rear;
				rear.next = front;
			}
		}
		return element;
	}

	public boolean isEmpty() {
		return (front == null && rear == null);
	}

	public int getFront() {
		return isEmpty() ? -1 : front.data;
	}

	public void display() {
		if (!isEmpty()) {
			ListNode temp = front;
			do {
				System.out.print(temp.data + " ");
				temp = temp.next;
			} while (temp != front);
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		char ch;
		CircularDLinkedList queue = new CircularDLinkedList();
		do {
			System.out
					.println("Circular Queue Operations:");
			System.out.println("1.Enqueue");
			System.out.println("2.Dequeue");
			System.out.println("3.Front");
			System.out.print("Enter option:");
			switch (in.nextInt()) {
				case 1:
					System.out.println(
							"Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0) {
						queue.enQueue(in.nextInt());
					}
					System.out.println(
							"Elements are inserted!");
					break;
				case 2:
					int data = queue.deQueue();
					if (data != -1) System.out.println(
							"Dequeued element is: " + data);
					else System.out
							.println("Queue is empty");
					break;
				case 3:
					data = queue.getFront();
					if (data != -1) System.out.println(
							"Front element is: " + data);
					else System.out
							.println("Queue is empty");
					break;
				default:
					System.out.println(
							"Please enter the valid option!!!");
					break;
			}

			System.out.println("\nDisplay:");
			queue.display();
			System.out.println(
					"\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();

	}
}