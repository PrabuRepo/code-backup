package com.problems.patterns;

import com.common.model.ListNode;

/* Pattern: In-place Reversal of a LinkedList
 * 		Introduction Reverse a LinkedList (easy) 
 * 		Reverse a Sub-list (medium) 
 * 		Reverse every K-element Sub-list (medium) 
 * 		Reverse alternating K-element Sub-list (medium) 
 * 		Rotate a LinkedList (medium)
 */
public class InPlaceReversalLLPatterns {
	// Reverse a LinkedList (easy)
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

	// Recursive Approach
	public ListNode reverseList2(ListNode head) {
		return helper(head, null);
	}

	public ListNode helper(ListNode curr, ListNode prev) {
		if (curr == null) return prev;
		ListNode next = curr.next;
		curr.next = prev;
		return helper(next, curr);
	}

	// Reverse a Sub-list (medium)
	// Reverse a linked list II: Reverse a linked list from position m to n. Do it in one-pass.
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode fakeHead = new ListNode(-1);
		fakeHead.next = head;
		ListNode curr = fakeHead.next;
		ListNode prev = fakeHead;
		int i = 1;
		// Move to node 'm'
		while (i < m) {
			prev = curr;
			curr = curr.next;
			i++;
		}
		// End of first m node
		ListNode mthNode = prev;
		// Reverse the node from 'm' to node 'n'
		while (i <= n) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			i++;
		}
		// mthNode 'next of next' will point to curr node
		mthNode.next.next = curr;
		// mthNode next will point to reversed node
		mthNode.next = prev;
		return fakeHead.next;
	}

	// Reverse every K-element Sub-list (medium)
	/* Reverse Nodes in k-Group:
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null) return head;
		int count = 0;
		ListNode curr = head, prev = null, next = null;
		// Find the k+1 node
		while (count != k && curr != null) {
			curr = curr.next;
			count++;
		}

		if (count != k) return head;
		// reverse list with k+1 node as head
		prev = reverseKGroup(curr, k);
		// LL Reversal Alg: reverse current k-group from head ptr
		while (count-- > 0) {
			next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	// Reverse alternating K-element Sub-list (medium)
	/* Reverses alternate k nodes and returns the pointer to the new head node */
	ListNode kAltReverse(ListNode head, int k) {
		ListNode curr = head, next = null, prev = null;
		int count = 0;
		/*1) LL Revese Alg: reverse first k nodes of the linked list */
		while (curr != null && count < k) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			count++;
		}
		/* 2) Now head points to the kth node. So change next of head to (k+1)th node*/
		if (head != null) {
			head.next = curr;
		}
		/* 3) We do not want to reverse next k nodes. So move the curr pointer to skip next k nodes */
		count = 0;
		while (count < k - 1 && curr != null) {
			curr = curr.next;
			count++;
		}
		/* 4) Recursively call for the list starting from curr->next. 
		And make rest of the list as next of first node */
		if (curr != null) {
			curr.next = kAltReverse(curr.next, k);
		}
		/* 5) prev is new head of the input list */
		return prev;
	}

	// Rotate a LinkedList (medium)
	/* Eg:Input: 1->2->3->4->5->NULL, k = 2;
	 * Output: 3->4->5->1->2->NULL
	 */
	public ListNode rotateLeft(ListNode head, int k) {
		if (k <= 0) return head;
		ListNode curr = head;
		int count = 1;
		while (count++ < k && curr != null)
			curr = curr.next;
		if (curr == null) return head;
		ListNode nextHead = curr;
		while (curr.next != null)
			curr = curr.next;
		curr.next = head;
		head = nextHead.next;
		nextHead.next = null;
		return head;
	}

	/* Rotate Right:
	 * Eg:Input: 1->2->3->4->5->NULL, k = 2;
	 * Output: 4->5->1->2->3->NULL
	 */
	public ListNode rotateRight(ListNode head, int k) {
		int size = listSize(head);
		if (head == null || k <= 0 || k == size) return head;
		if (k > size) k %= size;
		int count = 1;
		ListNode curr = head;
		k = size - k;
		while (count < k && curr != null) {
			curr = curr.next;
			count++;
		}
		ListNode nextHead = curr;
		while (curr.next != null)
			curr = curr.next;
		curr.next = head;
		head = nextHead.next;
		nextHead.next = null;
		return head;
	}

	public int listSize(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}
}