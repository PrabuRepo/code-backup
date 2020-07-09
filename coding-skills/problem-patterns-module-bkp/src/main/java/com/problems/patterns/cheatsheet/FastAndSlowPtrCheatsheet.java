package com.problems.patterns.cheatsheet;

import java.util.Stack;

import com.common.model.ListNode;

public class FastAndSlowPtrCheatsheet {

	// LinkedList Cycle (easy)
	public boolean hasCycle(ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr != null
				&& fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr == fastPtr) return true;
		}
		return false;
	}

	// Start of LinkedList Cycle (medium)
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null)
			return null;
		ListNode slow = head, fast = head,
				entry = head;
		while (fast.next != null
				&& fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				while (slow != entry) {
					slow = slow.next;
					entry = entry.next;
				}
				return entry;
			}
		}
		return null;
	}

	// Happy Number (medium)
	/* Write an algorithm to determine if a number is "happy". A happy number is a number defined by the following process:
	 * Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the
	 * process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
	 * Those numbers for which this process ends in 1 are happy numbers.
	 */
	public boolean isHappy(int n) {
		if (n == 0) return false;
		int slow = n, fast = n;
		do {
			slow = squareSum(slow);
			fast = squareSum(squareSum(fast));
		} while (slow != fast);
		return (slow == 1);
	}

	private int squareSum(int n) {
		int sum = 0, digit = 0;
		while (n > 0) {
			digit = n % 10;
			sum += (digit * digit);
			n = n / 10;
		}
		return sum;
	}

	// Middle of the LinkedList (easy)
	public ListNode middleNode(ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr != null
				&& fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}

	public ListNode middleNode2(ListNode head) {
		if (head == null) return null;
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr.next != null
				&& fastPtr.next.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}

	// Palindrome LinkedList (medium)
	public boolean isPalindrome1(ListNode head) {
		Stack<ListNode> stack = new Stack<>();
		ListNode curr = head;
		while (curr != null) {
			stack.push(curr);
			curr = curr.next;
		}
		curr = head;
		while (curr != null) {
			if (curr.data != stack.pop().data)
				return false;
			curr = curr.next;
		}
		return true;
	}

	public boolean isPalindrome2(ListNode head) {
		if (head == null || head.next == null)
			return true;
		ListNode slowPtr = head, fastPtr = head,
				prevNode = null;
		while (fastPtr != null
				&& fastPtr.next != null) {
			prevNode = slowPtr;
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		ListNode midNode = null;
		if (fastPtr != null) {
			midNode = slowPtr;
			slowPtr = slowPtr.next;
		}
		prevNode.next = null;
		ListNode secondHalf = slowPtr;
		secondHalf = reverseList1(secondHalf);
		boolean flag = compare(head, secondHalf);
		secondHalf = reverseList1(secondHalf);
		if (midNode != null) {
			prevNode.next = midNode;
			midNode.next = slowPtr;
		} else {
			prevNode.next = slowPtr;
		}
		return flag;
	}

	public boolean compare(ListNode node1,
			ListNode node2) {
		if (node1 == null && node2 == null)
			return true;
		if (node1 == null || node2 == null)
			return false;
		return ((node1.data == node2.data)
				&& compare(node1.next,
						node2.next));
	}

	// Reverse a linked list I
	// Iterative Apporach
	public ListNode reverseList1(ListNode head) {
		ListNode curr = head, next, prev = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	// Rearrange a LinkedList (medium)
	/*
	 * Reorder List:
	 * Example 1: Given 1->2->3->4, reorder it to 1->4->2->3.
	 * Example 2: Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
	 */
	/* 3 Steps: 
	 *    1. Find the middle node
	 *    2. Reverse the 2nd half  
	 *    3. Merge 2 list: 1st half & reversed 2nd half
	 */
	public void reorderList(ListNode head) {
		if (head == null) return;
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr.next != null
				&& fastPtr.next.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		ListNode rightHalf = reverseList1(
				slowPtr.next);
		slowPtr.next = null;
		ListNode leftHalf = head;
		mergeList(leftHalf, rightHalf);
	}

	public void mergeList(ListNode leftHalf,
			ListNode rightHalf) {
		while (rightHalf != null) {
			ListNode rightHalfNext = rightHalf.next;
			rightHalf.next = leftHalf.next;
			leftHalf.next = rightHalf;
			leftHalf = leftHalf.next.next;
			rightHalf = rightHalfNext;
		}
	}

	/*
	 * Swap Nodes in Pairs:
	 * Given a linked list, swap every two adjacent nodes and return its head.You may not modify the values in the list's 
	 * nodes, only nodes itself may be changed.
	 */
	// Approach1: Iterative
	public ListNode swapPairs1(ListNode head) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		dummy.next = head;
		while (curr.next != null
				&& curr.next.next != null) {
			ListNode first = curr.next;
			ListNode second = curr.next.next;
			first.next = second.next;
			second.next = first;
			curr.next = second;
			curr = curr.next.next;
		}
		return dummy.next;
	}

	// Recursive solution
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode second = head.next;
		head.next = swapPairs(head.next.next);
		second.next = head;
		return second;
	}
	// Cycle in a Circular Array (hard) -> Refer "Circular Array Loop" problems in leetcode
}
