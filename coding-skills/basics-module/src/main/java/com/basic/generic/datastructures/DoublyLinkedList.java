package com.basic.generic.datastructures;

import java.util.Scanner;

class DLNode<T> {
	T			data;
	DLNode<T>	prev, next;

	public DLNode(T data) {
		this.data = data;
		prev = next = null;
	}
}

public class DoublyLinkedList<T extends Comparable<T>> {

	DLNode<T> head;

	public void insert(T data) {
		DLNode<T> newNode = new DLNode<>(data);
		if (head == null) {
			head = newNode;
		} else {
			DLNode<T> temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
			newNode.prev = temp;
		}
	}

	public void delete1(T data) {
		if (head == null) {
			System.out.println("List is empty");
		} else if (head.data == data) {
			head = head.next;
			if (head != null) head.prev = null;
		} else {
			DLNode<T> curr = head;
			while (curr.next != null) {
				if (curr.next.data == data) {
					DLNode<T> next = curr.next.next;
					curr.next = next;
					if (next != null) next.prev = curr.next;
					break;
				} else {
					curr = curr.next;
				}
			}

		}
	}

	public void delete(T data) {

		if (head == null) {
			System.out.println("List is empty");
		} else {
			DLNode<T> curr = head;

			// Find the Node
			while (curr != null) {
				if (curr.data == data) break;
				curr = curr.next;
			}

			// Delete the node
			if (curr != null) {
				DLNode<T> prev = curr.prev;
				if (prev != null) {
					prev.next = curr.next;
				} else {
					curr = curr.next;
					if (curr != null) // Need this check for only head node in the list
						curr.prev = null;
					head = curr;
				}
			} else {
				System.out.println("Data is not found!");
			}
		}
	}

	public boolean search(T data) {
		boolean flag = false;
		if (head != null) {
			DLNode<T> temp = head;
			while (temp != null) {
				if (temp.data == data) {
					flag = true;
					break;
				} else {
					temp = temp.next;
				}
			}
		} else {
			System.out.println("List is empty!");
		}
		return flag;
	}

	public void display() {
		DLNode<T> temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

	public void reverse() {

	}

	/*Node temp = root;
	while(temp!=null){
	    System.out.print(temp.data+" ");
	    temp = temp.left;
	}
	temp = root.right;
	while(temp!=null){
	    System.out.print(temp.data+" ");
	    temp = temp.right;
	}
	*/
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		do {
			System.out.println("Doubly Linked List Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.Find");
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
