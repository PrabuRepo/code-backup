package com.basic.datastructures;

import java.util.Arrays;
import java.util.Scanner;

import com.common.model.ListNode;

/*  
 * Deque(Double Ended Queue):
 * 	Deque can act as both stack(push/pop) & queue(enqueue/deque)
 * 	A linear collection that supports element insertion and removal at both ends. The name deque is short for "double ended queue" 
 * 	and is usually pronounced "deck". Most Deque implementations place no fixed limits on the number of elements they may contain, 
 * 	but this interface supports capacity-restricted deques as well as those with no fixed size limit.
 * 
 * Deque Impl using Circular Array & Doubly Linked List
 */
public class Deque {

}

class DequeArrayImpl {
	int[] queue;
	int   front, rear;
	int   maxSize;
	int   currSize;

	public DequeArrayImpl(int maxSize) {
		queue = new int[maxSize];
		Arrays.fill(queue, -1);
		this.maxSize = maxSize;
		this.front = -1;
		this.rear = -1;
		currSize = 0;
	}

	public void insertFront(int data) {
		if (isFull2()) return;
		if (isEmpty()) { // When queue is empty
			front++;
			rear++;
		} else if (front == 0) {// Only if one element inserted already
			front = maxSize - 1;
		} else { // Normal case: decrement the front size and insert the element
			front--;
		}
		queue[front] = data;
		// currSize++;
	}

	public void insertRear(int data) {
		if (isFull2()) return;
		if (isEmpty()) {// When queue is empty
			front++;
			rear++;
		} else if (rear == maxSize - 1) {// rear reached the max size index
			rear = 0;
		} else { // Normal case: Increment the rear size and insert the data
			rear++;
		}
		queue[rear] = data;
		// currSize++;

	}

	public int deleteFront() {
		if (isEmpty()) return -1;
		int element = queue[front];
		queue[front] = -1;
		if (front == rear) { // If both are same, only one data present in the queue
			front = -1;
			rear = -1;
		} else if (front == maxSize - 1) {// front is in last index, move front to first index to point to the front
											// or rear element
			front = 0;
		} else { // Normal Case: Increment the front to delete the element
			front++;
		}
		// currSize--;
		return element;
	}

	public int deleteRear() {
		if (isEmpty()) return -1;
		int element = queue[rear];
		queue[rear] = -1;
		if (front == rear) {// If both are same, only one data present in the queue
			front = -1;
			rear = -1;
		} else if (rear == 0) {// If rear is zero, move rear to last index to point to the front element
			rear = maxSize - 1;
		} else {// Normal Case: decrement the front to delete the element
			rear--;
		}
		// currSize--;
		return element;
	}

	public int getFront() {
		return isEmpty() ? -1 : queue[front];
	}

	public int getRear() {
		return isEmpty() ? -1 : queue[rear];
	}

	public boolean isFull1() {
		return (currSize == maxSize);
	}

	// without using currSize
	public boolean isFull2() {
		return ((front == rear + 1)
				|| (front == 0 && rear == maxSize - 1));
	}

	public boolean isEmpty() {
		return front == -1 ? true : false;
	}

	public void display() {
		if (!isEmpty()) {
			for (int i = 0; i < maxSize; i++)
				System.out.print(queue[i] + " ");
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		char ch;
		DequeArrayImpl queue = new DequeArrayImpl(6);
		do {
			System.out.println(
					"Double Ended Queue Operations:");
			System.out.println("1.Insert Front");
			System.out.println("2.Insert Rear");
			System.out.println("3.Delete Front");
			System.out.println("4.Delete Rear");
			System.out.println("5.Front element");
			System.out.println("6.Rear element");
			System.out.print("Enter option:");
			switch (in.nextInt()) {
				case 1:
					System.out.println(
							"Enter no of elements to be inserted:");
					int t = in.nextInt();

					while (t-- > 0) {
						queue.insertFront(in.nextInt());
					}
					System.out.println(
							"Elements are inserted in front!");
					break;
				case 2:
					System.out.println(
							"Enter no of elements to be inserted:");
					t = in.nextInt();
					while (t-- > 0) {
						queue.insertRear(in.nextInt());
					}
					System.out.println(
							"Elements are inserted in rear!");
					break;
				case 3:
					int data = queue.deleteFront();
					if (data != -1) System.out.println(
							"Dequeued front element is: "
									+ data);
					else System.out
							.println("Queue is empty");
					break;
				case 4:
					data = queue.deleteRear();
					if (data != -1) System.out.println(
							"Dequeued rear element is: "
									+ data);
					else System.out
							.println("Queue is empty");
					break;
				case 5:
					data = queue.getFront();
					if (data != -1) System.out.println(
							"Front element is: " + data);
					else System.out
							.println("Queue is empty");
					break;
				case 6:
					data = queue.getRear();
					if (data != -1) System.out.println(
							"Rear element is: " + data);
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

class DequeDLLImpl {
	ListNode front, rear;

	public void insertFront(int data) {
		ListNode newNode = new ListNode(data);
		if (front == null) {
			front = rear = newNode;
		} else {
			newNode.next = front;
			front.prev = newNode;
			front = newNode;
		}
	}

	public void insertRear(int data) {
		ListNode newNode = new ListNode(data);
		if (rear == null) {
			front = rear = newNode;
		} else {
			rear.next = newNode;
			newNode.prev = rear;
			rear = newNode;
		}
	}

	public int deleteFront() {
		int element = -1;
		if (front != null && rear != null) {
			element = front.data;
			if (front == rear) {
				front = rear = null;
			} else {
				front = front.next;
				front.prev = null;
			}
		}
		return element;
	}

	public int deleteRear() {
		int element = -1;
		if (front != null && rear != null) {
			element = rear.data;
			if (front == rear) {
				front = rear = null;
			} else {
				rear = rear.prev;
				rear.next = null;
			}
		}
		return element;
	}

	public int getFront() {
		return isEmpty() ? -1 : front.data;
	}

	public int getRear() {
		return isEmpty() ? -1 : rear.data;
	}

	public boolean isEmpty() {
		return (front == null && rear == null);
	}

	public void display() {
		if (!isEmpty()) {
			ListNode temp = front;
			while (temp != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		DequeDLLImpl queue = new DequeDLLImpl();
		do {
			System.out.println(
					"Double Ended Queue Operations:");
			System.out.println("1.Insert Front");
			System.out.println("2.Insert Rear");
			System.out.println("3.Delete Front");
			System.out.println("4.Delete Rear");
			System.out.println("5.Front element");
			System.out.println("6.Rear element");
			System.out.print("Enter option:");
			switch (in.nextInt()) {
				case 1:
					System.out.println(
							"Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0) {
						queue.insertFront(in.nextInt());
					}
					System.out.println(
							"Elements are inserted in front!");
					break;
				case 2:
					System.out.println(
							"Enter no of elements to be inserted:");
					t = in.nextInt();
					while (t-- > 0) {
						queue.insertRear(in.nextInt());
					}
					System.out.println(
							"Elements are inserted in rear!");
					break;
				case 3:
					int data = queue.deleteFront();
					if (data != -1) System.out.println(
							"Dequeued front element is: "
									+ data);
					else System.out
							.println("Queue is empty");
					break;
				case 4:
					data = queue.deleteRear();
					if (data != -1) System.out.println(
							"Dequeued rear element is: "
									+ data);
					else System.out
							.println("Queue is empty");
					break;
				case 5:
					data = queue.getFront();
					if (data != -1) System.out.println(
							"Front element is: " + data);
					else System.out
							.println("Queue is empty");
					break;
				case 6:
					data = queue.getRear();
					if (data != -1) System.out.println(
							"Rear element is: " + data);
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
