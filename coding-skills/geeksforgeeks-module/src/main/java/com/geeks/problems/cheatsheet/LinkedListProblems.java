package com.geeks.problems.cheatsheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.common.model.ListNode;
import com.common.model.NestedInteger;

public class LinkedListProblems {

	/************************* Single Linked List Problems *****************/
	/********************** i.SLL Basic Operations *****************/
	public static ListNode insertAtEnd(ListNode head,
			int data) {
		ListNode newLLNode = new ListNode(data);
		if (head == null) {
			return newLLNode;
		} else {
			ListNode temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newLLNode;
		}
		return head;
	}

	public ListNode insertAtFront(ListNode head, int data) {
		ListNode newLLNode = new ListNode(data);
		if (head == null) {
			head = newLLNode;
		} else {
			newLLNode.next = head;
			head = newLLNode;
		}
		return head;
	}

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

	public ListNode delete(ListNode head, int data) {
		if (head != null) {
			if (head.data == data) {
				head = head.next;
			} else {
				ListNode temp = head;
				while (temp.next != null) {
					if (temp.next.data == data) {
						temp.next = temp.next.next;
						break;
					}
					temp = temp.next;
				}
			}
		}
		return head;
	}

	// delete an element at specific position in the list
	public ListNode deleteAtPos(ListNode head, int pos) {
		if (head != null) {
			if (pos == 1) {
				head = head.next;
			} else {
				ListNode temp = head;
				int count = 1;
				while (temp.next != null) {
					if (count == pos - 1) {
						temp.next = temp.next.next;
						break;
					}
					temp = temp.next;
					count++;
				}
			}
		}
		return head;
	}

	public ListNode search(ListNode head, int data) {
		if (head != null) {
			while (head != null) {
				if (head.data == data) break;
				head = head.next;
			}
		}
		return head;
	}

	public ListNode searchRecursive(ListNode head,
			int data) {
		if (head == null) return head;
		if (head.data == data) return head;
		return searchRecursive(head.next, data);
	}

	// search an element at specific position in the list
	public ListNode searchAtPos(ListNode head, int pos) {
		if (head != null) {
			int count = 1;
			while (head != null) {
				if (count++ == pos) break;
				head = head.next;
			}
		}
		return head;
	}

	public void display(ListNode head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

	// Print using recursive approach
	public void displayRecursive(ListNode head) {
		if (head != null) {
			System.out.print(head.data + " ");
			displayRecursive(head.next);
		}
	}

	// Print using recursive approach
	public void displayReverseRecursive(ListNode head) {
		if (head == null) return;
		displayReverseRecursive(head.next);
		System.out.print(head.data + " ");

	}

	public void displayReverseIterative(ListNode head) {

	}

	// Iterative Approach
	public int lengthOfList1(ListNode head) {
		int count = 0;
		while (head != null) {
			head = head.next;
			count++;
		}
		return count;
	}

	// Recursive Approach
	public int lengthOfList2(ListNode head) {
		if (head == null) return 0;
		return lengthOfList2(head.next) + 1;
	}

	/********************** ii.SLL Problems *****************/
	// 3 approaches
	// 1.find size and then iterate size/2
	// 2.Using 2 pointers slow and fast pointers
	// 3.Using mid pointer as counter as zero and move mid pointer when counter is odd(Similar to approach 2)

	// 1.Method to get the first middle number in the even list of data
	public ListNode findMiddleLLNode1(ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr != null) {
			// condition to get the first middle number in the even list of data
			if (fastPtr.next != null
					&& fastPtr.next.next != null) {
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next.next;
			} else {
				break;
			}
		}
		return slowPtr;
	}

	// 1.Method to get the second middle number in the even list of data
	public static ListNode findMiddleLLNode2(
			ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		// Condition to get the second middle number in the even list of data
		while (fastPtr != null && fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}

	// 3.Using mid pointer as counter as zero and move mid pointer when counter is odd
	public static ListNode findMiddleLLNode3(
			ListNode head) {
		if (head == null || head.next == null) return head;
		int count = 1;
		ListNode midPtr = head;
		while (head.next != null) {
			if ((count & 1) == 1) // move if it is odd
				midPtr = midPtr.next;

			head = head.next;
			count++;
		}
		return midPtr;
	}

	// Approach1: Iterative Approach
	public ListNode reverseLinkedList(ListNode head) {
		ListNode curr = head, prev = null, next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head = prev;
		return head;
	}

	// Approach2: Recursive Approach
	public ListNode reverseLinkedListRecursive(
			ListNode head) {
		return reverseLinkedListUtil(head, null);
	}

	private ListNode reverseLinkedListUtil(ListNode curr,
			ListNode prev) {
		if (curr.next == null) {
			curr.next = prev;
			return curr;
		}
		ListNode next = curr.next;
		curr.next = prev;
		return reverseLinkedListUtil(next, curr);
	}

	// Approach 1:Use length of linked list
	public ListNode nthLLNodeFromLast1(ListNode head,
			int n) {
		ListNode nthNodeFromLast = null;
		if (head != null) {
			int length = lengthOfList1(head);
			if (n <= length)
				nthNodeFromLast = searchAtPos(head,
						(length - n + 1));
		}
		return nthNodeFromLast;
	}

	// Approach 2: Using 2 pointers
	public ListNode nthLLNodeFromLast2(ListNode head,
			int n) {
		ListNode firstPtr = head, secondPtr = head;
		int count = 1;
		while (count <= n && firstPtr != null) {
			count++;
			firstPtr = firstPtr.next;
		}
		if (firstPtr == null) return null;

		while (firstPtr != null) {
			secondPtr = secondPtr.next;
			firstPtr = firstPtr.next;
		}

		return secondPtr;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode ptr1 = head, ptr2 = head;
		while (n-- > 1 && ptr1 != null)
			ptr1 = ptr1.next;
		if (ptr1 == null) {
			return head;
		} else if (ptr1.next == null) {
			head = head.next;
			return head;
		}

		// After n, move both ptr's till first ptr reaches null value
		while (ptr1.next != null) {
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
		}
		ptr2.next = ptr2.next.next;
		return head;
	}

	// Approach1: Detect loop in the list using hashset
	public static boolean detectLoopHashingApproach(
			ListNode head) {
		Set<ListNode> hashSet = new HashSet<>();
		while (head.next != null) {
			if (hashSet.contains(head)) return true;
			hashSet.add(head);
			head = head.next;
		}
		return false;
	}

	// Approach2: Detect loop in the list using Floyd Alg
	/*
	 * Imagine two runners running on a track at different speed. What happens when the track is actually a circle?
	 * Algorithm:
	 * 	The space complexity can be reduced to O(1) by considering two pointers at different speed - a slow pointer and 
	 * a fast pointer. The slow pointer moves one step at a time while the fast pointer moves two steps at a time. If there
	 * is no cycle in the list, the fast pointer will eventually reach the end and we can return false in this case.
	 * 
	 * Now consider a cyclic list and imagine the slow and fast pointers are two runners racing around a circle track. The 
	 * fast runner will eventually meet the slow runner. 
	 * Complexity analysis: Time complexity : O(n). 
	 * Let us denote n as the total number of nodes in the linked list. To analyze its time complexity, we consider the 
	 * following two cases separately.
	 * List has no cycle: The fast pointer reaches the end first and the run time depends on the list's length, which is O(n).
	 * List has a cycle: We break down the movement of the slow pointer into two steps, the non-cyclic part and the 
	 * cyclic part:
	 *     1. The slow pointer takes "non-cyclic length" steps to enter the cycle. At this point, the fast pointer has already 
	 *        reached the cycle.  Number of iterations = non-cyclic length = N
	 *     2. Both pointers are now in the cycle. Consider two runners running in a cycle - the fast runner moves 2 steps
	 *        while the slow runner moves 1 steps at a time. Since the speed difference is 1, it takes
	 *        "distance between the 2 runners/difference of speed" loops for the fast runner to catch up with the 
	 *        slow runner. As the distance is at most "cyclic length K" and the speed difference is 1, we conclude that 
	 *        Number of iterations = almost cyclic length K.
	 *        
	 *        Therefore, the worst case time complexity is O(N+K), which is O(n).
	 *        Space complexity : O(1). We only use two nodes (slow and fast) so the space complexity is O(1).
	 */
	public static boolean detectLoopFloydAlg(
			ListNode head) {
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr != null && fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr.equals(fastPtr)) return true;
		}
		return false;
	}

	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) return null;
		ListNode slow = head, fast = head, entry = head;
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

	public ListNode removeLoop(ListNode head) {
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
		if (flag) {
			int loopCount = 0;
			do {
				slowPtr = slowPtr.next;
				loopCount++;
			} while (slowPtr != fastPtr);
			slowPtr = fastPtr = head;
			int i = 0;
			while (i < loopCount) {
				fastPtr = fastPtr.next;
				i++;
			}
			while (slowPtr.next != fastPtr.next) {
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next;
			}
			fastPtr.next = null;
		}
		return head;
	}

	// Approach1: Using stack
	public boolean isPalindrome(
			LinkedList<Character> list) {
		Stack<Character> s = new Stack<>();
		Iterator<Character> iter = list.iterator();
		while (iter.hasNext())
			s.push(iter.next());
		iter = list.iterator();
		while (iter.hasNext()) {
			if (!(s.pop().equals(iter.next())))
				return false;
		}
		return true;
	}

	// Approach2: Without using stack
	public boolean isPalindrome2(ListNode head) {
		if (head == null || head.next == null) return true;
		ListNode slowPtr = head, fastPtr = head,
				slowPtrPrevNode = null, secondHalf,
				middleNode = null, reverseListHead;
		while (fastPtr != null && fastPtr.next != null) {
			slowPtrPrevNode = slowPtr;
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		if (fastPtr != null) {
			middleNode = slowPtr;
			secondHalf = slowPtr.next;
		} else {
			secondHalf = slowPtr;
		}
		slowPtrPrevNode.next = null;
		reverseListHead = reverseLinkedList(secondHalf);
		boolean flag = compare(head, reverseListHead);
		secondHalf = reverseLinkedList(reverseListHead);
		if (middleNode != null) {
			slowPtrPrevNode.next = middleNode;
			middleNode.next = secondHalf;
		} else {
			slowPtrPrevNode.next = secondHalf;
		}
		return flag;
	}

	private boolean compare(ListNode node1,
			ListNode node2) {
		if (node1 != null && node2 != null) {
			while (node1 != null && node2 != null) {
				if (node1.data != node2.data) {
					return false;
				} else {
					node1 = node1.next;
					node2 = node2.next;
				}
			}
		}
		return true;
	}

	// temp
	public boolean isPalindrome3(ListNode head) {
		if (head == null || head.next == null) return true;
		ListNode slowPtr = head, fastPtr = head,
				prevNode = null;
		while (fastPtr != null && fastPtr.next != null) {
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
		secondHalf = reverseList(secondHalf);
		boolean flag = compare1(head, secondHalf);
		secondHalf = reverseList(secondHalf);
		if (midNode != null) {
			prevNode.next = midNode;
			midNode.next = slowPtr;
		} else {
			prevNode.next = slowPtr;
		}
		return flag;
	}

	public ListNode reverseList(ListNode head) {
		ListNode curr = head, next, prev = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	public boolean compare1(ListNode node1,
			ListNode node2) {
		if (node1 == null && node2 == null) return true;
		if (node1 == null || node2 == null) return false;
		return ((node1.data == node2.data)
				&& compare1(node1.next, node2.next));
	}

	// temp

	public ListNode removeDuplicatesFromSortedList(
			ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			if (temp.next != null
					&& temp.data == temp.next.data)
				temp.next = temp.next.next;
			else temp = temp.next;
		}
		return head;
	}

	// Approach1:Using two loops
	public ListNode removeDuplicatesFromUnsortedList1(
			ListNode head) {
		ListNode node = head;
		while (node != null) {
			ListNode curr = node;
			while (curr.next != null) {
				if (curr.next.data == node.data) {
					curr.next = curr.next.next;
				} else {
					curr = curr.next;
				}
			}
			node = node.next;
		}
		return head;
	}

	// Approach2:Use sorting
	public ListNode removeDuplicatesFromUnsortedList2(
			ListNode head) {
		ListNode sortedNode = insertionSort(head);
		return removeDuplicatesFromSortedList(sortedNode);
	}

	// Approach3:Using hashing
	public ListNode removeDuplicatesFromUnsortedList3(
			ListNode head) {
		HashSet<Integer> set = new HashSet<>();
		ListNode curr = head, prev = null;
		while (curr != null) {
			if (set.contains(curr.data)) {
				prev.next = curr.next;
			} else {
				set.add(curr.data);
				prev = curr;
			}
			curr = curr.next;
		}
		return head;
	}

	// Merge two sorted list: Iterative approach
	public static ListNode mergeSortedListIterative(
			ListNode head1, ListNode head2) {
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

	// Merge two sorted list: Recursive approach
	public static ListNode mergeSortedList(ListNode head1,
			ListNode head2) {
		ListNode result;
		if (head1 == null) return head2;
		if (head2 == null) return head1;
		if (head1.data < head2.data) {
			result = head1;
			result.next = mergeSortedList(head1.next,
					head2);
		} else {
			result = head2;
			result.next = mergeSortedList(head1,
					head2.next);
		}
		return result;
	}

	// Set theory: Intersection of two sorted list
	public ListNode intersectionOfTwoList(ListNode head1,
			ListNode head2) {
		ListNode result = new ListNode(0);
		ListNode intersectionList = result;
		while (head1 != null && head2 != null) {
			if (head1.data == head2.data) {
				result.next = head1; // or head2
				result = result.next;
				head1 = head1.next;
				head2 = head2.next;
			} else if (head1.data < head2.data) {
				head1 = head1.next;
			} else {
				head2 = head2.next;
			}
		}
		result.next = null;
		return intersectionList.next;
	}

	// Set theory: Union of two sorted list
	public ListNode unionOfTwoList(ListNode head1,
			ListNode head2) {
		ListNode curr = new ListNode(0);
		ListNode unionList = curr;
		while (head1 != null && head2 != null) {
			if (head1.data == head2.data) {
				curr.next = head1;
				head1 = head1.next;
				head2 = head2.next;
			} else if (head1.data < head2.data) {
				curr.next = head1;
				head1 = head1.next;
			} else {
				curr.next = head2;
				head2 = head2.next;
			}
			curr = curr.next;
		}
		if (head1 != null) curr.next = head1;
		if (head2 != null) curr.next = head2;
		return unionList.next;
	}

	// Find the intersection point of two list
	public ListNode intersectionPointOfLL(ListNode head1,
			ListNode head2) {
		if (head1 != null && head2 != null) {
			int len1 = lengthOfList2(head1);
			int len2 = lengthOfList2(head2);
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
			while (head1 != null && head2 != null) {
				if (head1.data == head2.data) return head1;
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		return null;
	}

	public int CheckLLLengthIsEvenOrOdd(ListNode head) {
		return 0;
	}

	public ListNode cloneLinkedList(ListNode head) {
		return null;
	}

	public ListNode rotateLinkedList(ListNode head, int k) {
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

	public static ListNode deleteAlterateLLNodes(
			ListNode list) {
		return null;
	}

	public static ListNode rearrangeZigZagFashion(
			ListNode list) {
		return null;
	}

	public int compareTwoStringLL(ListNode list1,
			ListNode list2) {
		return 0; // return 0, 1, -1 based on comparison
	}

	public static ListNode nthLLNodeWithHead(
			ListNode list) {
		return null;
	}

	public static ListNode reverseEvenLLNodes(
			ListNode list) {
		return null;
	}

	// Reverse a Linked List in groups of given size
	public ListNode reverseListForKSize(ListNode head,
			int k) {
		ListNode prev = null, next = null, curr = head;
		int count = 1;
		while (count++ <= k && curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		if (next != null)
			head.next = reverseListForKSize(next, k);
		return prev;
	}

	public static ListNode iteratorPatternOfLL(
			ListNode list) {
		return null;
	}

	public ListNode insertionSort(ListNode head) {
		ListNode sorted = null;
		ListNode curr = head, next;
		while (curr != null) {
			next = curr.next;
			sorted = sort(curr, sorted);
			curr = next;
		}
		return sorted;
	}

	private ListNode sort(ListNode curr, ListNode sorted) {
		if (sorted == null || curr.data < sorted.data) {
			curr.next = sorted;
			sorted = curr;
		} else {
			ListNode temp = sorted;
			while (temp.next != null
					&& temp.next.data < curr.data)
				temp = temp.next;
			curr.next = temp.next;
			temp.next = curr;
		}
		return sorted;
	}

	public static ListNode mergeSort(ListNode List) {
		return null;
	}

	/*
	 1.Using Counting Sort: Time Complexity: O(n); O(3) extraspace to count 0, 1 & 2
	 2.Three flag approach: Time Complexity: O(n);
	*/
	// 1.Using Counting Sort:
	public void linksort1(ListNode head) {
		ListNode curr = head;
		int[] count = new int[3];
		while (curr != null) {
			count[curr.data]++;
			curr = curr.next;
		}
		curr = head;
		int i = 0;
		while (curr != null) {
			if (count[i] == 0) {
				i++;
			} else {
				curr.data = i;
				curr = curr.next;
				count[i]--;
			}
		}
	}

	// 2. Three Flag Approach
	public ListNode linksort2(ListNode head) {
		ListNode curr = head, zeroD, oneD, twoD;
		zeroD = new ListNode(Integer.MAX_VALUE);
		oneD = new ListNode(Integer.MAX_VALUE);
		twoD = new ListNode(Integer.MAX_VALUE);
		ListNode zeroCursor = zeroD, oneCursor = oneD,
				twoCursor = twoD;
		while (curr != null) {
			switch (curr.data) {
				case 0:
					zeroCursor.next = curr;
					zeroCursor = zeroCursor.next;
					break;
				case 1:
					oneCursor.next = curr;
					oneCursor = oneCursor.next;
					break;
				case 2:
					twoCursor.next = curr;
					twoCursor = twoCursor.next;
					break;
				default:
					break;
			}
			curr = curr.next;
		}
		twoCursor.next = null;
		oneCursor.next = twoD.next;
		zeroCursor.next = oneD.next;
		return zeroD.next;
	}

	// Add Two Numbers: Iterative approach
	public ListNode addTwoLists1(ListNode list1,
			ListNode list2) {
		if (list1 == null && list2 == null) return null;
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		ListNode result = new ListNode(0);
		ListNode current = result;
		int carry = 0, temp = 0, sum = 0, data1 = 0,
				data2 = 0;
		while (list1 != null || list2 != null) {
			data1 = (list1 != null) ? list1.data : 0;
			data2 = (list2 != null) ? list2.data : 0;
			temp = data1 + data2 + carry;
			if (temp > 9) {
				sum = temp % 10;
				carry = temp / 10;
			} else {
				sum = temp;
				carry = 0;
			}
			current.next = new ListNode(sum);
			if (list1 != null) list1 = list1.next;
			if (list2 != null) list2 = list2.next;
			current = current.next;
		}
		if (carry != 0) current.next = new ListNode(carry);
		return result.next;
	}

	// Add Two Numbers: Recursive approach
	public ListNode addTwoLists2(ListNode list1,
			ListNode list2) {
		return addTwoLists2(list1, list2, 0);
	}

	public ListNode addTwoLists2(ListNode list1,
			ListNode list2, int carry) {
		if (list1 == null && list2 == null && carry == 0)
			return null;
		int sum = carry;
		if (list1 != null) sum += list1.data;
		if (list2 != null) sum += list2.data;
		ListNode result = new ListNode(sum % 10);
		if (list1 != null || list2 != null)
			result.next = addTwoLists2(
					list1 != null ? list1.next : null,
					list2 != null ? list2.next : null,
					sum >= 10 ? 1 : 0);
		return result;
	}

	public ListNode addTwoPolynomials(ListNode list1,
			ListNode list2) {
		return null;
	}

	/*
	 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.The digits are stored 
	 * such that the most significant digit is at the head of the list.
	 * Example: Input:1->2->3, Output:1->2->4
	 */
	/*
	 * Approach1: The easiest way is to reverse the linked list, do the math, and reverse it back. But that requires us 
	 * to go through the list three times (reverse, calculate, and reverse again).
	 */
	public ListNode plusOne1(ListNode head) {
		if (head == null) return head;
		ListNode revHead = reverseList(head);
		ListNode curr = revHead;
		while (curr != null) {
			if (curr.data < 9) {
				curr.data++;
				break;
			} else {
				curr.data = 0;
				if (curr.next == null) {
					curr.next = new ListNode(1);
					break;
				}
			}
			curr = curr.next;
		}
		head = reverseList(revHead);
		return head;
	}

	/* Approach2: A better way is to use recursion. We first recursively get the carry from its next node, if we reach the end, 
	 * we return 1, which is the number to be added. Now for every node, we return the carry it may have until the head. If we 
	 * find the carry is not 0, we add a new node in front of the head and return head.
	 */
	public ListNode plusOne2(ListNode head) {
		int carry = addList(head);
		if (carry == 1) {
			ListNode newNode = new ListNode(1);
			newNode.next = head;
			head = newNode;
		}
		return head;
	}

	public int addList(ListNode head) {
		if (head == null) return 1;
		int carry = addList(head.next);
		int sum = head.data + carry;
		head.data = sum % 10;
		return sum / 10;
	}

	/*
	 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about 
	 * the node number and not the value in the nodes.
	 * Eg: Input: 2->1->3->5->6->4->7->NULL
	 *     Output: 2->3->6->7->1->5->4->NULL
	 */
	public ListNode oddEvenList1(ListNode head) {
		if (head == null) return head;
		ListNode odd = head, even = head.next;
		ListNode oddHead = odd, evenHead = even;
		while (even != null && even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return oddHead;
	}

	public ListNode oddEvenList2(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode curr = null, odd, even;
		int count = 3;
		ListNode oddHead = head, evenHead = head.next;
		odd = oddHead;
		even = evenHead;
		curr = evenHead.next;
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
		odd.next = evenHead;
		return oddHead;
	}

	/**
	 * Segregate Even And Odd Nodes in a Linked List : Please note here we are talking about the node number and not the
	 * value in the nodes. Eg: Input: 2->1->3->5->6->4->7->NULL Output: 2->6->4->1->3->5->7->NULL
	 */
	public ListNode evenOddList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode oddHead = null, evenHead = null,
				odd = null, even = null, curr = head;
		while (curr != null) {
			if (curr.data % 2 == 0) {
				if (evenHead == null) {
					evenHead = even = curr;
				} else {
					even.next = curr;
					even = even.next;
				}
			} else {
				if (oddHead == null) {
					oddHead = odd = curr;
				} else {
					odd.next = curr;
					odd = odd.next;
				}
			}
			curr = curr.next;
		}
		if (odd != null) odd.next = null;
		if (even != null) even.next = oddHead;
		else return oddHead;
		return evenHead;
	}

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
		if (head == null || head.next == null) return head;
		ListNode second = head.next;
		head.next = swapPairs(head.next.next);
		second.next = head;
		return second;
	}

	/*
	 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
	 *  1.You should preserve the original relative order of the nodes in each of the two partitions.
	 *  2.You should preserve the original relative order of the nodes in each of the three partitions.
	 *  3.Partitioning a linked list around a given value and don’t care about making the elements of the list “stable”
	 */
	// 1.You should preserve the original relative order of the nodes in each of the two partitions.
	public ListNode partition1(ListNode head, int x) {
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

	// 2.Partitioning a linked list around a given value and don’t care about making the elements of the list “stable”
	public ListNode partition2(ListNode head, int x) {
		if (head == null || head.next == null) return head;
		ListNode curr = head, next, tail = head;
		while (curr != null) {
			next = curr.next;
			if (curr.data < x) {
				curr.next = head;
				head = curr;
			} else {
				tail.next = curr;
				tail = curr;
			}
			curr = next;
		}
		tail.next = null;
		return head;
	}

	/* Reorder List: You may not modify the values in the list's nodes, only nodes itself may be changed. 
	 *  Example 1: Given 1->2->3->4, reorder it to 1->4->2->3.
	 *  Example 2: Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
	 *  
	 * 3 Steps: 
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
		ListNode rightHalf = reverseList(slowPtr.next);
		slowPtr.next = null;
		ListNode leftHalf = head;
		while (rightHalf != null) {
			ListNode temp = rightHalf.next;
			rightHalf.next = leftHalf.next;
			leftHalf.next = rightHalf;
			leftHalf = leftHalf.next.next;
			rightHalf = temp;
		}
	}

	/*Largest Node on the Right for Each Node in a Linked List
	 *   1. Brute Force Approach: O(n^2)
	 *   2. Reverse the List & find the max for each Node
	 *   3. Use Stack
	 */
	// Approach2:
	public void largestNode1(ListNode head) {
		if (head == null) return;
		ListNode revHead = reverseLinkedList(head);
		ListNode curr = revHead;
		int currMax = -1;
		while (curr != null) {
			System.out.print(currMax + " ");
			currMax = Math.max(currMax, curr.data);
			curr = curr.next;
		}
		head = reverseLinkedList(revHead);
	}

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
			currMax = Math.max(currMax, stack.pop());
		}
	}

	/********************** Circular Linked List Problems *********************/

	public ListNode convertBinaryTreeFromCLL(
			ListNode head) {
		return null;
	}

	public boolean checkCircularLL(ListNode head) {
		return true;
	}

	public void josephusCircleUsingCLL(ListNode head) {

	}

	/********************** Double Linked List Problems *********************/
	// Its similiar to SLL(Not using prev pointer)
	public ListNode insertDLL(ListNode head, int data) {
		if (head == null) {
			head = new ListNode(data);
		} else {
			ListNode temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = new ListNode(data);
		}
		return head;
	}

	public ListNode insertDLL2(ListNode head, int data) {
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

	public void printDLL(ListNode head) {
		if (head != null) {
			while (head != null) {
				System.out.print(head.data + " ");
				head = head.next;
			}
		}
	}

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

	public int lengthOfDLL(ListNode head) {
		int count = 0;
		while (head != null) {
			head = head.next;
			count++;
		}
		return count;
	}

	public ListNode copyLLWithArbitraryPointer1(
			ListNode head) {
		ListNode curr = head;
		HashMap<ListNode, ListNode> map = new HashMap<>();
		while (curr != null) {
			map.put(curr, new ListNode(curr.data));
			curr = curr.next;
		}
		curr = head;
		ListNode cloneNodeHead = map.get(curr);
		ListNode tempClone = cloneNodeHead;
		while (curr != null) {
			tempClone.next = curr.next == null ? null
					: map.get(curr.next);
			tempClone.random = curr.random == null ? null
					: map.get(curr.random);

			tempClone = tempClone.next;
			curr = curr.next;
		}
		return cloneNodeHead;
	}

	// Without additional space
	public ListNode copyLLWithArbitraryPointer2(
			ListNode head) {
		ListNode clone, curr = head;
		while (curr != null) {
			clone = new ListNode(curr.data);
			clone.next = curr.next;
			curr.next = clone;
			curr = clone.next;
		}
		curr = head;
		while (curr != null) {
			clone = curr.next;
			clone.random = curr.random == null ? null
					: curr.random.next;
			curr = clone.next;
		}
		curr = head;
		ListNode cloneNodeHead = null;
		cloneNodeHead = new ListNode(0);
		clone = cloneNodeHead;
		while (curr != null) {
			clone.next = curr.next;
			curr.next = curr.next.next;
			curr = curr.next;
			clone = clone.next;
		}
		return cloneNodeHead.next;
	}

	public void printRandomLL(ListNode head) {
		while (head != null) {
			System.out.print("Current:" + head.data);
			if (head.prev != null) System.out
					.print("; Random: " + head.prev.data);
			else System.out.print("; Random: " + "-1");
			System.out.println();
			head = head.next;
		}
	}

	public ListNode flatteningLinkedList(ListNode root) {
		if (root == null || root.right == null) return root;
		root.right = flatteningLinkedList(root.right);
		root = merge(root, root.right);
		return root;
	}

	// Looks like duplicate method
	public ListNode merge(ListNode node1, ListNode node2) {
		if (node1 == null) return node2;
		if (node2 == null) return node1;
		ListNode result = null;
		if (node1.data < node2.data) {
			result = node1;
			result.down = merge(node1.down, node2);
		} else {
			result = node2;
			result.down = merge(node1, node2.down);
		}
		return result;
	}

	public void printFlattenedList(ListNode root) {
		if (root != null) {
			while (root != null) {
				System.out.print(root.data + " ");
				root = root.down;
			}
		}
	}

	public ListNode sortedListToBST(ListNode head) {
		int n = lengthOfDLL(head);
		return buildBST(head, n);
	}

	public ListNode buildBST(ListNode head, int n) {
		if (n <= 0) return null;
		ListNode leftSubTree = buildBST(head, n / 2);
		ListNode root = head;
		root.prev = leftSubTree;
		head = head.next;
		root.next = buildBST(head, (n - n / 2 - 1));
		return root;
	}

	public static void greatTreeList(ListNode head) {

	}

	public static ListNode createDLLFromTernaryTree(
			ListNode head) {
		return head;
	}

	public static ListNode sortBiotonicDLL(ListNode head) {
		return null;
	}

	/********************** Misc Problems *********************/
	public void printNestedInteger(
			List<NestedInteger> list) {
		printNestedInteger(list, 1);
	}

	// Using DFS
	public void printNestedInteger(List<NestedInteger> list,
			int depth) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isInteger()) {
				System.out.print(depth + "-> "
						+ list.get(i).getInteger() + "; ");
			} else {
				printNestedInteger(list.get(i).getList(),
						depth + 1);
			}
		}
		System.out.println();
	}

	/*Nested List Weight Sum: 
	 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth. Each element is 
	 * either an integer, or a list -- whose elements may also be integers or other lists.
	 * Example 1: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
	 * Example 2: Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
	 */
	// Approach1: DFS Algorithm
	public int depthSum1(List<NestedInteger> nestedList) {
		return depthSum1(nestedList, 1);
	}

	public int depthSum1(List<NestedInteger> list,
			int depth) {
		if (list == null || list.size() == 0) return 0;
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isInteger()) {
				sum += list.get(i).getInteger() * depth;
			} else {
				sum += depthSum1(list.get(i).getList(),
						depth + 1);
			}
		}
		return sum;
	}

	// Approach2: BFS Algorithm
	public int depthSum2(List<NestedInteger> nestedList) {
		int level = 1, sum = 0;
		while (!nestedList.isEmpty()) {
			LinkedList<NestedInteger> nextLevelList = new LinkedList<>();
			for (NestedInteger curr : nestedList) {
				if (curr.isInteger())
					sum += curr.getInteger() * level;
				else nextLevelList.addAll(curr.getList());
			}
			level++;
			nestedList = nextLevelList;
		}

		return sum;
	}

	/* Nested List Weight Sum II: Given a nested list of integers, return the sum of all integers in the list weighted
	 * by their depth. Each element is either an integer, or a list -- whose elements may also be integers or other
	 * lists. 
	 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from
	 * bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight. 
	 * Example 1: Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2) 
	 * Example 2: Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
	 */
	/*
	 * Approach: Instead of multiplying by depth, add integers multiple times (by going level by level and adding the 
	 * unweighted sum to the weighted sum after each level).
	 */
	public int depthSumInverse(
			List<NestedInteger> nestedList) {
		int unweighted = 0, weighted = 0;
		while (!nestedList.isEmpty()) {
			List<NestedInteger> nextLevelList = new ArrayList<>();
			for (NestedInteger ni : nestedList) {
				if (ni.isInteger())
					unweighted += ni.getInteger();
				else nextLevelList.addAll(ni.getList());
			}
			weighted += unweighted;
			nestedList = nextLevelList;
		}
		return weighted;
	}
}
