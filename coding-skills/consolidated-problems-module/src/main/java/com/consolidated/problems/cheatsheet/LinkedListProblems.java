package com.consolidated.problems.cheatsheet;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import com.common.model.ListNode;
import com.common.model.ListNode;
import com.common.model.ListNode;

public class LinkedListProblems {
	/************************* Type1: DS Manipulations **********************************/
	// Insert an element at specific position in the list
	public ListNode insert(ListNode head, int data,
			int pos) {
		ListNode newLLNode = new ListNode(data);
		if (head == null) {
			head = newLLNode;
		} else {
			ListNode temp = head;
			int count = 1;
			if (pos == 1) {
				newLLNode.next = head;
				head = newLLNode;
			} else {
				while (temp != null) {
					if (count == pos - 1) {
						newLLNode.next = temp.next;
						temp.next = newLLNode;
						break;
					}
					temp = temp.next;
					count++;
				}
			}
		}
		return head;
	}

	// Inserting a Node into a Sorted Doubly Linked List
	ListNode SortedInsert(ListNode head,
			int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) {
			head = newNode;
		} else if (head.data > data) {
			newNode.next = head;
			head = newNode;
		} else {
			ListNode temp = head;
			while (temp.next != null) {
				if (temp.next.data >= data) {
					newNode.next = temp.next;
					newNode.prev = temp;
					temp.next.prev = newNode;
					temp.next = newNode;
					break;
				} else {
					temp = temp.next;
				}
			}
			if (temp.next == null) {
				temp.next = newNode;
				newNode.prev = temp;
			}
		}
		return head;
	}

	// Delete Node in a Linked List
	public void deleteNode(ListNode node) {
		if (node != null) {
			node.data = node.next.data;
			node.next = node.next.next;
		}
	}

	// Remove Linked List Elements
	public ListNode removeElements(ListNode head,
			int val) {
		if (head == null) return null;
		ListNode temp = new ListNode(0);
		temp.next = head;
		ListNode curr = temp;
		while (curr.next != null) {
			if (curr.next.data == val) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}
		return temp.next;
	}

	// Remove Duplicates from Sorted List
	public ListNode deleteDuplicates(
			ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode curr = head;
		while (curr != null) {
			if (curr.next != null
					&& curr.data == curr.next.data) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}
		return head;
	}

	// Finding middle element in a linked list
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

	// Nth node from end of linked list
	int getNthFromLast(ListNode head, int n) {
		int count = 0, size = 0;
		ListNode first = head, second = head;
		while (first != null) {
			if (count++ >= n)
				second = second.next;
			first = first.next;
			size++;
		}
		return n > size ? -1 : second.data;
	}

	// Remove Nth Node From End of List
	public ListNode removeNthFromEnd(
			ListNode head, int n) {
		ListNode ptr1 = head, ptr2 = head;
		while (n-- > 0 && ptr1 != null)
			ptr1 = ptr1.next;
		if (ptr1 == null) {
			head = head.next;
			return head;
		}
		while (ptr1.next != null) {
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
		}
		ptr2.next = ptr2.next.next;
		return head;
	}

	// Print Linked List in Reversed Order
	// Recursive approach
	public void displayRecursive(ListNode head) {
		if (head != null) {
			System.out.print(head.data + " ");
			displayRecursive(head.next);
		}
	}

	// Print using recursive approach
	public void displayReverseRecursive(
			ListNode head) {
		if (head == null) return;
		displayReverseRecursive(head.next);
		System.out.print(head.data + " ");

	}

	/************************* Type2: DS Rearrangement **********************************/
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

	// Recursive Approach
	public ListNode reverseList2(ListNode head) {
		return helper(head, null);
	}

	public ListNode helper(ListNode head,
			ListNode prev) {
		if (head == null) return prev;
		ListNode next = head.next;
		head.next = prev;
		return helper(next, head);
	}

	// Reverse a linked list II: Reverse a linked list from position m to n. Do it in one-pass.
	public ListNode reverseBetween(ListNode head,
			int m, int n) {
		ListNode fakeHead = new ListNode(-1);
		fakeHead.next = head;
		ListNode prev = fakeHead;
		ListNode curr = fakeHead.next;
		int i = 1;
		while (i < m) {
			prev = curr;
			curr = curr.next;
			i++;
		}
		ListNode node = prev;
		while (i <= n) {
			ListNode tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
			i++;
		}
		node.next.next = curr;
		node.next = prev;
		return fakeHead.next;
	}

	// Reverse a doubly linked list
	public ListNode reverseDLL(ListNode head) {
		ListNode prev = null, curr = head;
		if (head != null) {
			while (curr != null) {
				prev = curr.prev;
				curr.prev = curr.next;
				curr.next = prev;
				curr = curr.prev;
			}
			head = prev.prev;
		}
		return head;
	}

	/*Largest Node on the Right for Each Node in a Linked List
	 *   1. Brute Force Approach: O(n^2)
	 *   2. Reverse the List & find the max for each Node
	 *   3. Use Stack
	 */
	// Approach1:
	public void largestNode1(ListNode head) {
		if (head == null) return;
		ListNode revHead = reverseList1(head);
		ListNode curr = revHead;
		int currMax = -1;
		while (curr != null) {
			System.out.print(currMax + " ");
			currMax = Math.max(currMax,
					curr.data);
			curr = curr.next;
		}
		head = reverseList1(revHead);
	}

	// Approach2:
	public void largestNode2(ListNode head) {
		if (head == null) return;
		Stack<Integer> stack = new Stack<>();
		ListNode curr = head;
		while (curr != null) {
			stack.push(curr.data);
			curr = curr.next;
		}
		int currMax = -1;
		while (!stack.isEmpty()) {
			System.out.print(currMax + " ");
			currMax = Math.max(currMax,
					stack.pop());
		}
	}

	/* Reverse Nodes in k-Group:
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
	 */
	public ListNode reverseKGroup(ListNode head,
			int k) {
		if (head == null) return null;
		ListNode curr = head;
		int count = 0;
		while (curr != null && count != k) {
			curr = curr.next;
			count++;
		}
		if (count == k) {
			ListNode prev = reverseKGroup(curr,
					k);
			while (count-- > 0) {
				ListNode next = head.next;
				head.next = prev;
				prev = head;
				head = next;
			}
			head = prev;
		}
		return head;
	}

	// Reverse alternating K-element Sub-list (medium)
	/* Reverses alternate k nodes and returns the pointer to the new head node */
	ListNode kAltReverse(ListNode node, int k) {
		ListNode current = node;
		ListNode next = null, prev = null;
		int count = 0;
		while (current != null && count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}
		if (node != null) node.next = current;
		count = 0;
		while (count < k - 1 && current != null) {
			current = current.next;
			count++;
		}
		if (current != null) {
			current.next = kAltReverse(
					current.next, k);
		}
		return prev;
	}

	// Rotate a Linked List
	public ListNode rotateLinkedList(
			ListNode head, int k) {
		if (k > 0) {
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
		}
		return head;
	}

	/* Odd Even Linked List:
	 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking
	 * about the node number and not the value in the nodes.
	 * Example 1:
	 * 	Input: 1->2->3->4->5->NULL
	 *  Output: 1->3->5->2->4->NULL
	 */
	public ListNode oddEvenList(ListNode head) {
		if (head == null) return head;
		ListNode odd = head, even = head.next;
		ListNode oddHead = odd, evenHead = even;
		while (even != null
				&& even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return oddHead;
	}

	public ListNode oddEvenList2(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode curr = null, odd, even;
		int count = 3;
		ListNode oddNode = head,
				evenNode = head.next;
		odd = oddNode;
		even = evenNode;
		curr = evenNode.next;
		while (curr != null) {
			if (count % 2 == 1) {
				odd.next = curr;
				odd = odd.next;
			} else {
				even.next = curr;
				even = even.next;
			}
			count++;
			curr = curr.next;
		}
		even.next = null;
		odd.next = evenNode;
		return oddNode;
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

	/*
	 * Partition List:
	 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than
	 * or equal to x. You should preserve the original relative order of the nodes in each of the two partitions.
	 * Example:
	 *  Input: head = 1->4->3->2->5->2, x = 3
	 *  Output: 1->2->2->4->3->5
	 */
	public ListNode partition(ListNode head,
			int x) {
		ListNode beforeXHead = new ListNode(0);
		ListNode before = beforeXHead;
		ListNode afterXHead = new ListNode(0);
		ListNode after = afterXHead;
		while (head != null) {
			if (head.data < x) {
				before.next = head;
				before = before.next;
			} else {
				after.next = head;
				after = after.next;
			}
			head = head.next;
		}
		after.next = null;
		before.next = afterXHead.next;
		return beforeXHead.next;
	}

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

	// Sort List:Sort a linked list in O(n log n) time using constant space complexity.
	// Use mergesort
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode middle = middleNode2(head);
		ListNode next = middle.next;
		middle.next = null;
		ListNode left = sortList(head);
		ListNode right = sortList(next);
		return merge(left, right);
	}

	public ListNode merge(ListNode head1,
			ListNode head2) {
		if (head1 == null) return head2;
		if (head2 == null) return head1;
		ListNode result = null;
		if (head1.data < head2.data) {
			result = head1;
			result.next = merge(head1.next,
					head2);
		} else {
			result = head2;
			result.next = merge(head1,
					head2.next);
		}
		return result;
	}

	// Sort List – Insertion List
	public ListNode insertionSortList(
			ListNode head) {
		if (head == null) return head;
		ListNode sortedList = null, next = null,
				curr = head;
		while (curr != null) {
			next = curr.next;
			sortedList = sort(curr, sortedList);
			curr = next;
		}
		return sortedList;
	}

	public ListNode sort(ListNode curr,
			ListNode sortedList) {
		if (sortedList == null
				|| curr.data < sortedList.data) {
			curr.next = sortedList;
			sortedList = curr;
		} else {
			ListNode temp = sortedList;
			while (temp.next != null
					&& temp.next.data < curr.data)
				temp = temp.next;
			curr.next = temp.next;
			temp.next = curr;
		}
		return sortedList;
	}

	/* Copy List with Random Pointer:
	 * A linked list is given such that each node contains an additional random pointer which could point to any node
	 * in the list or null.	Return a deep copy of the list.
	 */
	/* Approach1: Using HashMap: Time-O(n), Space:O(n)
	 * Approach2: Efficient Linear Approach: Time-O(n), Space-O(1)
	 */
	// Approach1:
	public Node copyRandomList1(Node head) {
		if (head == null) return head;
		Map<Integer, Node> map = new HashMap<>();
		Node curr = head;
		while (curr != null) {
			map.put(curr.val, new Node(curr.val,
					null, null));
			curr = curr.next;
		}
		curr = head;
		Node cloneHead = map.get(head.val);
		Node clone = cloneHead;
		while (curr != null) {
			clone.next = curr.next == null ? null
					: map.get(curr.next.val);
			clone.random = curr.random == null
					? null
					: map.get(curr.random.val);

			curr = curr.next;
			clone = clone.next;
		}
		return cloneHead;
	}

	// Approach2:
	public Node copyRandomList2(Node head) {
		if (head == null) return head;
		Node curr = head, clone;
		while (curr != null) {
			clone = new Node(curr.val, curr.next,
					null);
			curr.next = clone;
			curr = clone.next;
		}
		curr = head;
		clone = null;
		while (curr != null) {
			clone = curr.next;
			clone.random = curr.random == null
					? null
					: curr.random.next;
			curr = clone.next;
		}
		curr = head;
		Node cloneHead = curr.next;
		clone = cloneHead;
		while (curr != null) {
			curr.next = clone.next;
			clone.next = clone.next == null ? null
					: clone.next.next;

			clone = clone.next;
			curr = curr.next;
		}
		return cloneHead;
	}

	/************************* Type3: DS Actions/Validations **********************************/
	// Linked List Cycle
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

	/* Linked List Cycle II: Given a linked list, return the node where the cycle begins. If there is no cycle, return
	 null. */
	// Linked List Cycle II
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

	// Remove loop in Linked List:
	public int removeTheLoop(ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		boolean flag = false;
		int result = 0;
		while (fastPtr != null
				&& fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr == fastPtr) {
				flag = true;
				break;
			}
		}
		if (flag) {
			int count = 0;
			do {
				slowPtr = slowPtr.next;
				count++;
			} while (slowPtr != fastPtr);
			slowPtr = head;
			while (count-- > 0) {
				slowPtr = slowPtr.next;
			}
			fastPtr = head;
			while (slowPtr.next != fastPtr.next) {
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next;
			}
			slowPtr.next = null;
			result = 1;
		}
		return result;
	}

	// Palindrome Linked List:
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

	/*
	 * Linked List Random Node:
	 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same
	 * probability of being chosen.
	 */
	public int getRandom(ListNode node) {
		ListNode curr = node;
		Random random = new Random();
		int count = 1, randomVal = 0;
		while (curr != null) {
			if (random.nextInt(count++) == 0)
				randomVal = curr.data;
			curr = curr.next;
		}
		return randomVal;
	}

	/************************* Type4: DS manipulation on mulitple objects **********************************/

	// Intersection of Two Linked Lists
	public ListNode getIntersectionNode(
			ListNode head1, ListNode head2) {
		if (head1 != null && head2 != null) {
			int len1 = length(head1);
			int len2 = length(head2);
			int diff;
			if (len1 > len2) {
				diff = len1 - len2;
				while (diff-- > 0)
					head1 = head1.next;
			} else {
				diff = len2 - len1;
				while (diff-- > 0)
					head2 = head2.next;
			}
			while (head1 != null
					&& head2 != null) {
				if (head1.data == head2.data)
					return head1;
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		return null;
	}

	private int length(ListNode head) {
		if (head == null) return 0;
		return 1 + length(head.next);
	}

	// Merge Two Sorted Lists:
	// Recursive Approach:
	public ListNode mergeTwoLists1(ListNode l1,
			ListNode l2) {
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		ListNode result = null;
		if (l1.data <= l2.data) {
			result = l1;
			result.next = mergeTwoLists(l1.next,
					l2);
		} else {
			result = l2;
			result.next = mergeTwoLists(l1,
					l2.next);
		}
		return result;
	}

	// Iterative Approach
	public ListNode mergeTwoLists(ListNode l1,
			ListNode l2) {
		ListNode result = new ListNode(0);
		ListNode curr = result;
		while (l1 != null && l2 != null) {
			if (l1.data < l2.data) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}
			curr = curr.next;
		}
		if (l1 == null) curr.next = l2;
		if (l2 == null) curr.next = l1;
		return result.next;
	}

	// Flattening a Linked List:
	FlattenNode flatten(FlattenNode root) {
		if (root == null || root.next == null)
			return root;
		root.next = flatten(root.next);
		root = merge(root, root.next);
		return root;
	}

	public FlattenNode merge(FlattenNode node1,
			FlattenNode node2) {
		if (node1 == null) return node2;
		if (node2 == null) return node1;
		FlattenNode result = null;
		if (node1.data < node2.data) {
			result = node1;
			result.bottom = merge(node1.bottom,
					node2);
		} else {
			result = node2;
			result.bottom = merge(node1,
					node2.bottom);
		}
		return result;
	}
}

class Node {
	public int	val;
	public Node	next;
	public Node	random;

	public Node(int _val, Node _next,
			Node _random) {
		val = _val;
		next = _next;
		random = _random;
	}
}

class FlattenNode {
	int			data;
	FlattenNode	next;
	FlattenNode	bottom;

	public FlattenNode(int d) {
		data = d;
		next = null;
		bottom = null;
	}
}