package com.geeksforgeeks.mustdocoding.problems;

import com.common.model.ListNode;

public class C_LinkedListProblems {
	ListNode reverse(ListNode head) {
		ListNode prev = null, curr = head, next;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	public void rotate(ListNode head, int k) {
		if (k > 0) {
			ListNode temp1 = head;
			int count = 1;
			while (count++ < k && temp1 != null)
				temp1 = temp1.next;

			if (temp1 == null) return;

			ListNode temp2 = temp1;

			while (temp1.next != null)
				temp1 = temp1.next;

			temp1.next = head;
			head = temp2.next;
			temp2.next = null;

			display(head);
		}
	}

	public void display(ListNode head) {
		if (head != null) {
			while (head != null) {
				System.out.print(head.data + " ");
				head = head.next;
			}
		}
	}

	public ListNode reverseListForKSize(ListNode head, int k) {

		ListNode prev = null, next = null, curr = head;
		int count = 1;
		while (count++ < k && curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		// head point to lst element; After reverse, head will be last element;
		// So it makes sense when we point next to recursive call
		if (next != null) head.next = reverseListForKSize(next, k);

		return prev;
	}

	int detectLoop(ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		int result = 0;
		while (fastPtr != null && fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr == fastPtr) {
				result = 1;
				break;
			}
		}
		return result;
	}

	int removeTheLoop(ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		boolean flag = false;
		int result = 0;
		// Detect the loop
		while (fastPtr != null && fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr == fastPtr) {
				flag = true;
				break;
			}
		}

		if (flag) {
			// Find the loop count
			int count = 0;
			do {
				slowPtr = slowPtr.next;
				count++;
			} while (slowPtr != fastPtr);

			// Move slowPtr from head to till loop count size
			slowPtr = head;
			while (count-- > 0) {
				slowPtr = slowPtr.next;
			}

			// Now move fastPtr from head and slowPtr from the place its present
			// Meeting point will be loop starting point
			fastPtr = head;
			while (slowPtr.next != fastPtr.next) {
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next;
			}

			// System.out.println("Loop starting point:" + slowPtr.next.data);

			// Remove the loop
			slowPtr.next = null;
			result = 1;
		}
		return result;
	}

}
