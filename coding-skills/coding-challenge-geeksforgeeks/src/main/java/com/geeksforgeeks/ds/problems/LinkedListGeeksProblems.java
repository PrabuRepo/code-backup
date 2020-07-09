package com.geeksforgeeks.ds.problems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
import java.util.Stack;

import com.common.model.ListNode;

public class LinkedListGeeksProblems {

	public static ListNode buildLoop(ListNode head) {
		if (head != null) {
			ListNode temp1 = head;
			ListNode temp2 = head.next.next;
			while (temp1.next != null)
				temp1 = temp1.next;
			temp1.next = temp2;
		}
		return head;
	}

	public static boolean detectLoopHashingApproach(ListNode head) {
		boolean flag = false;
		Set<ListNode> hashSet = new HashSet<>();
		while (head.next != null) {
			if (hashSet.contains(head)) {
				flag = true;
				break;
			}
			hashSet.add(head);
			head = head.next;
		}
		return flag;
	}

	public static boolean detectLoopFloydAlg(ListNode head) {
		ListNode slowPtr = head;
		ListNode fastPtr = head;
		boolean flag = false;
		while (fastPtr != null && fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr.equals(fastPtr)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static ListNode removeLoop(ListNode head) {
		ListNode slowPtr = head;
		ListNode fastPtr = head;
		boolean flag = false;
		while (fastPtr != null && fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr.equals(fastPtr)) {
				flag = true;
				break;
			}
		}

		// If loop presents then proceed below steps to remove the loop
		if (flag) {
			// 1.Find the loop Size (Move slowPtr till fastPtr reaches)
			slowPtr = slowPtr.next;
			int loopCount = 1;
			while (!slowPtr.equals(fastPtr)) {
				slowPtr = slowPtr.next;
				loopCount++;
			}

			// 2.Point both slowPtr & fastPtr points to head
			slowPtr = fastPtr = head;

			// 3.Move fastPtr till loopCount
			int i = 0;
			while (i < loopCount) {
				fastPtr = fastPtr.next;
				i++;
			}

			// 4.Move slowPtr & fastPtr move towards the loop starting point. Merging point
			// will be loop starting point.
			while (slowPtr.next != fastPtr.next) {
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next;
			}

			System.out.println("Loop starting point:" + slowPtr.next.data);

			// 5.Remove the loop
			fastPtr.next = null;
		}

		return head;
	}

	public static ListNode mergeListIterative(ListNode head1, ListNode head2) {
		ListNode temp = new ListNode(0);
		ListNode result = temp;

		while (head1 != null && head2 != null) {
			if (head1.data < head2.data) {
				temp.next = head1;
				head1 = head1.next;
			} else {
				temp.next = head2;
				head2 = head2.next;
			}
			temp = temp.next;
		}

		if (head1 == null) temp.next = head2;
		if (head2 == null) temp.next = head1;
		return result.next;
	}

	private static ListNode moveNode(ListNode node) {
		return node;
	}

	public static ListNode mergeList(ListNode head1, ListNode head2) {
		ListNode result;
		if (head1 == null) return head2;

		if (head2 == null) return head1;

		if (head1.data < head2.data) {
			result = head1;
			result.next = mergeList(head1.next, head2);
		} else {
			result = head2;
			result.next = mergeList(head1, head2.next);
		}
		return result;
	}

	public static boolean isPalindrome(LinkedList<Character> list) {
		boolean flag = true;
		Stack<Character> s = new Stack<>();
		ListIterator<Character> listIterator = list.listIterator();

		while (listIterator.hasNext()) {
			s.push(listIterator.next());
		}

		listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			if (!(s.pop().equals(listIterator.next()))) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static ListNode middleNode(ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}

	public static ListNode insert(ListNode head, int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) {
			return newNode;
		} else {
			ListNode temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
		}
		return head;
	}

	static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

	public static void main(String[] args) {

		ListNode head = null;
		head = insert(head, 1);
		head = insert(head, 2);
		head = insert(head, 3);
		head = insert(head, 4);
		head = insert(head, 5);
		// head = insert(head, 6);
		System.out.println("Middle Node: " + middleNode(head).data);

		head = buildLoop(head);
		System.out.println("Does list has loop (using Hash method): " + detectLoopHashingApproach(head));
		System.out.println("Does list has loop (using Floyd Cycle Algorithm): " + detectLoopFloydAlg(head));
		head = removeLoop(head);
		System.out.println("Does list has loop (using Hash method): " + detectLoopHashingApproach(head));
		System.out.println("Does list has loop (using Floyd Cycle Algorithm): " + detectLoopFloydAlg(head));

		// Merge two linked list
		ListNode head1 = null, head2 = null;
		head1 = insert(head1, 1);
		head1 = insert(head1, 3);
		head1 = insert(head1, 7);

		head2 = insert(head2, 4);
		head2 = insert(head2, 6);
		head2 = insert(head2, 9);

		// LLNode mergeNode1 = mergeList(head1, head2);
		// LLNode mergeNode2 = mergeListIterative(head1, head2);
		// printList(mergeNode2);

		System.out.println("Palindrome using only Java API");
		LinkedList<Character> list = new LinkedList<>();
		list.add('R');
		list.add('A');
		list.add('D');
		list.add('A');
		list.add('R');
		System.out.println("Is it a Palindrome:" + isPalindrome(list));

		System.out.println("Palindrome using only Linked List API");
		com.basic.generic.datastructures.LinkedList<Character> list2 = new com.basic.generic.datastructures.LinkedList<>();
		list2.insert('R');
		list2.insert('A');
		list2.insert('D');
		list2.insert('D');
		list2.insert('A');
		list2.insert('R');
		System.out.println("Is it a Palindrome:" + list2.isPalindrome());
	}

}
