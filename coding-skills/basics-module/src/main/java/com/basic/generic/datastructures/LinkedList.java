package com.basic.generic.datastructures;

import java.util.Scanner;

class ListNode<T> {
	T			data;
	ListNode<T>	next;

	public ListNode(T data) {
		this.data = data;
		this.next = null;
	}
}

public class LinkedList<T extends Comparable<T>> {

	ListNode<T> head;

	public ListNode<T> insert(T n) {
		ListNode<T> newNode = new ListNode<>(n);
		if (head == null) {
			head = newNode;
		} else if (head.next == null) {
			head.next = newNode;
		} else {
			ListNode<T> temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
		}
		return head;
	}

	public boolean find(T n) {
		ListNode<T> temp = head;
		boolean flag = false;
		while (temp != null) {
			if (temp.data.compareTo(n) == 0) {
				flag = true;
				break;
			} else {
				temp = temp.next;
			}
		}
		return flag;
	}

	public boolean delete(T n) {
		boolean flag = false;
		if (head != null && head.data.compareTo(n) == 0) {
			head = head.next;
			flag = true;
		} else {
			ListNode<T> temp = head;
			while (temp != null && temp.next != null) {
				if (temp.next.data.compareTo(n) == 0) {
					temp.next = temp.next.next;
					flag = true;
					break;
				} else {
					temp = temp.next;
				}
			}
		}
		return flag;
	}

	public ListNode<T> middleNode() {
		ListNode<T> slowPtr = head, fastPtr = head;
		while (fastPtr.next.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}

	public boolean isPalindrome() {
		boolean flag = true;
		ListNode<T> middle;
		ListNode<T> slowPtr = head, fastPtr = head;
		while (fastPtr.next != null && fastPtr.next.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		// To handle odd and even nodes
		middle = fastPtr.next != null ? slowPtr.next : slowPtr;

		ListNode<T> temp = head;
		ListNode<T> reverse = reverse(middle);
		while (reverse != null) {
			if (reverse.data == temp.data) {
				reverse = reverse.next;
				temp = temp.next;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public ListNode<T> reverse(ListNode<T> headNode) {
		ListNode<T> prev = null, next = null, current = headNode;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		headNode = prev;
		return headNode;
	}

	public void display() {
		ListNode<T> temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		LinkedList<Integer> list = new LinkedList<>();
		do {
			System.out.println("Linked List Operations:");
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
				System.out.println("Element has deleted? " + list.delete(in.nextInt()));
				break;
			case 3:
				System.out.println("Enter elements needs to be find:");
				System.out.println("Element is present in the list? " + list.find(in.nextInt()));
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
