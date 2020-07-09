package com.basic.generic.datastructures;

import java.util.Scanner;

/**
 * 
 * Queue Linked List Implementation
 *
 */

public class Queue<T> {
	QueueNode<T> front, rear;

	public void enQueue(T n) {
		QueueNode<T> newNode = new QueueNode<>(n);
		if (isEmpty()) {
			front = rear = newNode;
		} else {
			rear.next = newNode;
			rear = rear.next;
		}
	}

	public T deQueue() {
		T data = null;
		if (!isEmpty()) {
			data = front.data;
			if (front.equals(rear)) {
				front = front.next;
				rear = front;
			} else {
				front = front.next;
			}
		}
		return data;
	}

	public boolean isEmpty() {
		return (front == null && rear == null) ? true : false;
	}

	public T getFront() {
		return !isEmpty() ? front.data : null;
	}

	public T getRear() {
		return !isEmpty() ? rear.data : null;
	}

	public void display() {
		if (isEmpty()) {
			System.out.print("Queue is Empty!!!");
		} else {
			QueueNode<T> temp = front;
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
		Queue<Integer> queue = new Queue<>();
		do {
			System.out.println("Queue Operations:");
			System.out.println("1.EnQueue");
			System.out.println("2.Dequeue");
			System.out.println("3.Front");
			System.out.println("4.Rear");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {
			case 1:
				System.out.println("Enter no of elements to be inserted:");
				int t = in.nextInt();
				while (t-- > 0) {
					queue.enQueue(in.nextInt());
				}
				System.out.println("Elements are inserted!");
				break;
			case 2:
				System.out.println("Dequeue operation: " + queue.deQueue());
				break;
			case 3:
				System.out.println("Front element in the Queue: " + queue.getFront());
				break;
			case 4:
				System.out.println("Rear element in the Queue: " + queue.getRear());
				break;
			default:
				System.out.println("Please enter the valid option!!!");
				break;
			}

			System.out.println("\nDisplay:");
			queue.display();

			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}

}

class QueueNode<T> {
	T				data;
	QueueNode<T>	next;

	public QueueNode(T data) {
		this.data = data;
		this.next = null;
	}
}
