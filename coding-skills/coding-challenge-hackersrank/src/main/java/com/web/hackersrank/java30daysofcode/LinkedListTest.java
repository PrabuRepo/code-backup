package com.web.hackersrank.java30daysofcode;

import java.util.Scanner;

class ListNode {
	int data;
	ListNode next;

	public ListNode(int data) {
		this.data = data;
		this.next = null;
	}
}

public class LinkedListTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ListNode head = null;
		int T = sc.nextInt();
		while (T-- > 0) {
			int ele = sc.nextInt();
			head = insert(head, ele);
		}
		System.out.println("Before removing duplicates:");
		display(head);
		head = removeDuplicates(head);
		System.out.println("After removing duplicates:");
		display(head);
		sc.close();
	}

	public static ListNode insert(ListNode head, int n) {
		ListNode newNode = new ListNode(n);
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
		return head;
	}

	public static void display(ListNode head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

	public static ListNode removeDuplicates(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			if (temp.next != null && temp.data == temp.next.data)
				temp.next = temp.next.next;
			else
				temp = temp.next;
		}
		return head;
	}
}
