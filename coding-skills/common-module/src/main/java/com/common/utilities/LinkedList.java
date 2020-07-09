package com.common.utilities;

import java.util.Scanner;

import com.common.model.List;
import com.common.model.ListNode;

public class LinkedList implements List {

	ListNode head;

	@Override
	public boolean add(int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) {
			head = newNode;
		} else if (head.next == null) {
			head.next = newNode;
		} else {
			ListNode temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
		}

		return true;
	}

	@Override
	public boolean insert(int pos, int data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(int data) {
		ListNode temp = head;
		while (temp != null) {
			if (temp.data == data) return true;
			else temp = temp.next;
		}
		return false;
	}

	@Override
	public boolean remove(int data) {
		boolean flag = false;

		if (head != null && head.data == data) {
			head = head.next;
			flag = true;
		} else {
			ListNode temp = head;
			while (temp != null && temp.next != null) {
				if (temp.data == data) {
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

	public ListNode middleNode() {
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr.next.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}

	public boolean isPalindrome() {
		boolean flag = true;
		ListNode middle;
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr.next != null && fastPtr.next.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		// To handle odd and even nodes
		middle = fastPtr.next != null ? slowPtr.next : slowPtr;

		ListNode temp = head;
		ListNode reverse = reverse(middle);
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

	public ListNode reverse(ListNode headNode) {
		ListNode prev = null, next = null, current = headNode;
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
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

	@Override
	public int get(int pos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		LinkedList list = new LinkedList();
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
						list.add(in.nextInt());
					}
					System.out.println("Elements are inserted!");
					break;
				case 2:
					System.out.println("Enter element needs to be deleted:");
					System.out.println("Element has deleted? " + list.remove(in.nextInt()));
					break;
				case 3:
					System.out.println("Enter elements needs to be find:");
					System.out.println("Element is present in the list? " + list.contains(in.nextInt()));
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