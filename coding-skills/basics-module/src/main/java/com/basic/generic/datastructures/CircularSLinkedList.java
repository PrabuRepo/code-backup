package com.basic.generic.datastructures;

import java.util.Scanner;

class CLNode<T> {
	T			data;
	CLNode<T>	next;

	public CLNode(T data) {
		this.data = data;
		this.next = null;
	}
}

public class CircularSLinkedList<T> {
	CLNode<T> head;

	public void insert(T data) {
		CLNode<T> newNode = new CLNode<>(data);
		if (head == null) {
			head = newNode;
			head.next = head;
		} else {
			CLNode<T> temp = head;
			do {
				if (temp.next == head) {
					temp.next = newNode;
					newNode.next = head;
					break;
				} else {
					temp = temp.next;
				}
			} while (temp != head);
		}
	}

	public boolean search(T data) {
		CLNode<T> temp = head;
		boolean flag = false;
		if (temp != null) {
			do {
				if (temp.data == data) {
					flag = true;
					break;
				} else {
					temp = temp.next;
				}
			} while (temp != head);
		}
		return flag;
	}

	public int countNodes() {
		CLNode<T> temp = head;
		int count = 0;
		if (temp != null) {
			do {
				count++;
				temp = temp.next;
			} while (temp != head);
		}
		return count;
	}

	public void delete(T data) {
		CLNode<T> temp = head;
		if (temp != null) {
			if (temp.data == data && temp.next == head) {
				head = null;
			} else if (temp.data == data) {
				temp.data = temp.next.data;
				temp.next = temp.next.next;
			} else {
				do {
					if (temp.next.data == data) {
						temp.next = temp.next.next;
						break;
					} else {
						temp = temp.next;
					}
				} while (temp != head);
			}
		} else {
			System.out.println("List is empty");
		}
	}

	public void display() {
		CLNode<T> temp = head;
		if (temp != null) {
			do {
				System.out.print(temp.data + " ");
				temp = temp.next;
			} while (temp != head);
		} else {
			System.out.println("List is empty");
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		CircularSLinkedList<Integer> list = new CircularSLinkedList<>();
		do {
			System.out.println("Doubly Linked List Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.Find");
			System.out.println("4.Find");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {
			case 1:
				System.out.println("Enter no of elements to be inserted:");
				int t = in.nextInt();
				while (t-- > 0) {
					list.insert(in.nextInt());
				}
				System.out.println("Elements are inserted!");
				break;
			case 2:
				System.out.println("Enter element needs to be deleted:");
				list.delete(in.nextInt());
				break;
			case 3:
				System.out.println("Enter elements needs to be find:");
				System.out.println("Element is present in the list? " + list.search(in.nextInt()));
				break;
			case 4:
				System.out.println("No of nodes in Circular Linked List: " + list.countNodes());
			default:
				System.out.println("Please enter the valid option!!!");
				break;

			}
			System.out.println("\nDisplay:");
			list.display();

			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}

}
