package com.web.hackersrank.datastructures;

class ListNode {
	int		data;
	ListNode	prev, next;

	public ListNode(int data) {
		this.data = data;
		prev = next = null;
	}
}

public class DoubleLinkedListTest {

	public static void main(String[] args) {
		DoubleLinkedListTest test = new DoubleLinkedListTest();

		ListNode head = null;
		head = test.insert(head, 5);
		head = test.insert(head, 4);
		head = test.insert(head, 3);
		head = test.insert(head, 2);
		head = test.insert(head, 1);
		// test.display(head);
		// head = test.reverse(head);
		// 1 4 2 3 7 6 9 10
		ListNode head2 = null;
		head2 = test.SortedInsert(head2, 1);
		head2 = test.SortedInsert(head2, 4);
		head2 = test.SortedInsert(head2, 2);
		head2 = test.SortedInsert(head2, 3);
		head2 = test.SortedInsert(head2, 7);
		head2 = test.SortedInsert(head2, 6);
		head2 = test.SortedInsert(head2, 9);
		head2 = test.SortedInsert(head2, 10);
		test.display(head2);

	}

	public ListNode insert(ListNode head, int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) {
			head = newNode;
		} else {
			ListNode temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
			newNode.prev = temp;
		}
		return head;
	}

	ListNode SortedInsert(ListNode head, int data) {
		ListNode newNode = new ListNode(data);

		if (head == null) { // First node
			head = newNode;
		} else if (head.data > data) { // Insert before head
			newNode.next = head;
			head = newNode;
		} else {
			ListNode temp = head;
			while (temp.next != null) {    // Insert b/w two elements
				if (temp.next.data >= data) {
					newNode.next = temp.next;
					newNode.prev = temp;
					temp.next.prev = newNode;
					temp.next = newNode;
					break;
				} /*else if (temp.next.next == null) {
					newNode.prev = temp.next;
					temp.next.next = newNode;
					break;
					} */else {
					temp = temp.next;
				}
			}

			if (temp.next == null) { // Insert at the end
				temp.next = newNode;
				newNode.prev = temp;
			}
		}
		return head;
	}

	ListNode reverse(ListNode head) {
		if (head != null) {
			ListNode temp = null;
			head.prev = head.next;
			head.next = null;
			while (head.prev != null) {
				head = head.prev;
				temp = head.next;
				head.next = head.prev;
				head.prev = temp;
			}
		}
		return head;
	}

	ListNode reverseRecursive(ListNode head) {
		if (head != null) {
			ListNode temp = null;
			head.prev = head.next;
			head.next = null;
			while (head.prev != null) {
				head = head.prev;
				temp = head.next;
				head.next = head.prev;
				head.prev = temp;
			}
		}
		return head;
	}

	public void display(ListNode temp) {
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}
}
