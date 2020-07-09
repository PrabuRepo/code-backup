package com.geeks.problem.datastructures.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.common.model.ListNode;
import com.common.model.NestedInteger;
import com.common.model.ListNode;
import com.common.model.ListNode;
import com.geeks.problem.datastructures.LinkedListProblems;

public class TestLinkedListProblems extends LinkedListProblems {

	public static void main(String[] args) {
		TestLinkedListProblems ob = new TestLinkedListProblems();

		// Basic linked list operations
		// ob.testBasicOperations();

		ob.testSinglyLinkedList();

		// ob.testCircularLinkedList();

		// ob.testDoublyLinkedList();

		// ob.testMiscProblems();
	}

	public void testSinglyLinkedList() {
		ListNode head = mockData1();
		// Find middle node the list
		ListNode middleLLNode = findMiddleLLNode2(head);
		if (middleLLNode != null) System.out.print("\nMiddle element in the list(Approach1):" + middleLLNode.data);
		middleLLNode = findMiddleLLNode3(head);
		if (middleLLNode != null) System.out.print("\nMiddle element in the list(Approach3):" + middleLLNode.data);

		// Detect the loop in the linkedlist
		head = mockData1();
		head = mockLoopList(head);
		System.out.println("\nDoes list has loop (using Hash method): " + detectLoopHashingApproach(head));
		System.out.println("Does list has loop (using Floyd Cycle Algorithm): " + detectLoopFloydAlg(head));
		head = removeLoop(head);
		System.out.println("After Removing the loop in the list.....");
		System.out.println("Does list has loop (using Hash method): " + detectLoopHashingApproach(head));
		System.out.println("Does list has loop (using Floyd Cycle Algorithm): " + detectLoopFloydAlg(head));

		// Merge two linked list
		ListNode head1 = mockData2(), head2 = mockData3();
		System.out.println("Merged List(Recursive Approach):");
		display(mergeSortedList(head1, head2));
		head1 = mockData2();
		head2 = mockData3();
		System.out.println("\nMerged List(Iterative Approach):");
		display(mergeSortedListIterative(head1, head2));

		// Check given string is Palindrome or not
		System.out.println("\nPalindrome using only Java API");
		LinkedList<Character> list = mockCharList();
		System.out.println("Is it a Palindrome:" + isPalindrome(list));
		System.out.println("\nIs it a Palindrome(Without using Stack)" + isPalindrome2(mockData4()));
		System.out.println("Is it a Palindrome(Without using Stack)" + isPalindrome3(mockData4()));

		// Nth node from the last
		System.out.println(
				"Find the nth node from the linked list(Approach1):" + nthLLNodeFromLast1(mockData1(), 2).data);
		System.out.println(
				"Find the nth node from the linked list(Approach2):" + nthLLNodeFromLast2(mockData1(), 2).data);
		System.out.println("Remove the nth node from the linked list(Approach2):");
		display(removeNthFromEnd(mockData1(), 6));

		// IntersectionPoint of two List
		head = mockData1();
		System.out.println(
				"\nFind the intersection of List:" + intersectionPointOfLL(head, mockIntersectionList(head)).data);

		// Set Theory:Intersection of two list
		System.out.println("Intersection(Set theory) of List:");
		head1 = mockData1();
		head2 = mockData2();
		display(intersectionOfTwoList(head1, head2));
		System.out.println("\nUnion(Set theory) of List:");
		head1 = mockData1();
		head2 = mockData2();
		display(unionOfTwoList(head2, head1));

		// Insertion sort
		System.out.println("\nInsertion Sort:");
		head = mockData5();
		System.out.println("Before Sorting:");
		display(head);
		System.out.println("\nAfter Sorting:");
		display(insertionSort(head));

		// Reverse List for group of k size
		System.out.println("\nReverse elements for group of k size:");
		System.out.println("Before Reverse:");
		head = mockData5();
		display(head);
		System.out.println("\nAfter Reverse:");
		display(reverseListForKSize(head, 3));

		head = mockSortedDuplicateList();
		System.out.println("\nRemove dupicate from sorted element:");
		display(removeDuplicatesFromSortedList(head));

		System.out.println("\nDuplicate List: ");
		head = mockUnSortedDuplicateList();
		display(head);
		System.out.println("\nRemove dupicate from sorted element-1:");
		display(removeDuplicatesFromUnsortedList1(head));

		head = mockUnSortedDuplicateList();
		System.out.println("\nRemove dupicate from sorted element-2:");
		display(removeDuplicatesFromUnsortedList2(head));

		head = mockUnSortedDuplicateList();
		System.out.println("\nRemove dupicate from sorted element-3:");
		display(removeDuplicatesFromUnsortedList3(head));

		System.out.println("\nFlattening a Linked List: ");
		printFlattenedList(flatteningLinkedList(mockFlattenList()));

		System.out.println("\nGiven a linked list of 0s, 1s and 2s, sort it: ");
		System.out.println("Using Counting sort: ");
		ListNode head6 = mockData6();
		linksort1(head6);
		display(head6);
		System.out.println("\nUsing 3 Flag Approach: ");
		head6 = mockData6();
		display(linksort2(head6));
		// linksort1(head3);

		System.out.println("\nAdd two numbers represented by linked lists-Iterative: ");
		display(addTwoLists1(mockData8(), mockData9()));
		System.out.println("\nAdd two numbers represented by linked lists-Recursive: ");
		display(addTwoLists2(mockData8(), mockData9()));
		System.out.println("\nPlus One(Approach1): ");
		display(plusOne1(mockData4()));
		System.out.println("\nPlus One(Approach2): ");
		display(plusOne2(mockData4()));

		System.out.println("\nSegregate even & odd list(based node number): ");
		System.out.print("Input: ");
		display(mockData8());
		System.out.println();
		display(oddEvenList1(mockData8()));
		System.out.println("\nSegregate even & odd list(based node value): ");
		display(oddEvenList2(mockData8()));

		System.out.println("\nPairwise swap elements of a linked list by swapping data: ");
		ListNode head7 = mockData10();
		swapPairs1(head7);
		display(head7);

		System.out.println("\nPartition the linked list( preserve the original relative order): ");
		display(partition1(mockData1(), 3));
		System.out.println("\nPartition the linked list: ");
		display(partition2(mockData1(), 3));

		System.out.println("\nLargest Node on the Right for Each Node in a Linked List: ");
		head7 = mockData8();
		System.out.println("Approach1: ");
		largestNode1(head7);
		System.out.println("\nApproach2: ");
		largestNode2(head7);

	}

	public void testCircularLinkedList() {

	}

	public void testDoublyLinkedList() {
		ListNode head4 = mockDLNodeWithArbitraryPtr();
		System.out.println("Print Random Pointer before clone");
		printRandomLL(head4);
		System.out.println("Print Random Pointer after clone");
		ListNode head5 = copyLLWithArbitraryPointer1(head4);
		printRandomLL(head5);

		ListNode head1 = mockDLNode();
		System.out.println("Reverse the Doubly Linked List:");
		printDLL(reverseDLL(head1));

		ListNode head2 = mockDLNode();
		System.out.println("\nIn-place conversion of Sorted DLL to Balanced BST: ");
		printDLL(sortedListToBST(head2));
	}

	public void testBasicOperations() {
		ListNode head = null;
		System.out.println("**Insert Operations**");
		head = insertAtEnd(head, 3);
		head = insertAtEnd(head, 4);
		head = insertAtEnd(head, 5);
		System.out.println("Display after insertAtEnd():");
		displayRecursive(head);
		head = insertAtFront(head, 2);
		head = insertAtFront(head, 1);
		System.out.println("\nDisplay after insertAtFirst():");
		displayRecursive(head);
		head = insert(head, 8, 5);
		head = insert(head, 11, 1);
		head = insert(head, 10, 8);
		System.out.println("\nDisplay after insert on particular position:");
		displayRecursive(head);

		System.out.println("\n**Print the list in reverse**");
		System.out.println("Print the list in reverse(Iterative Approach):");
		displayReverseIterative(head);
		System.out.println("\nPrint the list in reverse(Recursive Approach):");
		displayReverseRecursive(head);

		System.out.println("\n**Reverse the Linked List**");
		System.out.println("Reversed List(Iterative):");
		head = mockData1();
		display(reverseLinkedList(head));
		System.out.println("Reversed List(Recursive):");
		head = mockData1();
		display(reverseLinkedListRecursive(head));

		System.out.println("\n**Delete Operations**");
		head = delete(head, 11);
		head = delete(head, 10);
		System.out.println("Display after delete():");
		displayRecursive(head);
		head = deleteAtPos(head, 3);
		head = deleteAtPos(head, 4);
		head = deleteAtPos(head, 1);
		System.out.println("\nDisplay after deleteAtPos():");
		displayRecursive(head);

		System.out.println("\n**Search Operations**");
		System.out.println("Search at particular Postion:");
		ListNode findLLNode = searchAtPos(head, 1);
		if (findLLNode != null) System.out.println("Is that node present?: " + findLLNode.data);
		System.out.println("Search with data(Iterative):");
		findLLNode = search(head, 2);
		if (findLLNode != null) System.out.println("Is that node present?: " + findLLNode.data);
		System.out.println("Search with data(Recursive):");
		findLLNode = searchRecursive(head, 5);
		if (findLLNode != null) System.out.println("Is that node present?: " + findLLNode.data);

		System.out.println("\n**Length or Size of list **");
		head = mockData1();
		display(head);
		System.out.println("\nLength of a linked List(Iterative):" + lengthOfList1(head));
		System.out.println("Length of a linked List(Recursive):" + lengthOfList2(head));
	}

	public void testMiscProblems() {
		System.out.println("Print NestedInteger: ");
		printNestedInteger(mockNestedInteger());

		System.out.println("Nested List Weight Sum - DFS: " + depthSum1(mockNestedInteger()));
		System.out.println("Nested List Weight Sum - BFS: " + depthSum2(mockNestedInteger()));

		System.out.println("Nested List Weight Sum II- BFS: " + depthSumInverse(mockNestedInteger()));

	}

	private ListNode mockData1() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 1);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 3);
		head3 = insertAtEnd(head3, 4);
		head3 = insertAtEnd(head3, 5);
		head3 = insertAtEnd(head3, 6);
		return head3;
	}

	private ListNode mockData2() {
		ListNode head1 = null;
		head1 = insertAtEnd(head1, 1);
		head1 = insertAtEnd(head1, 3);
		head1 = insertAtEnd(head1, 7);
		return head1;
	}

	private ListNode mockData3() {
		ListNode head2 = null;
		head2 = insertAtEnd(head2, 4);
		head2 = insertAtEnd(head2, 6);
		head2 = insertAtEnd(head2, 9);
		return head2;
	}

	private ListNode mockData4() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 1);
		/*head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 3);
		head3 = insertAtEnd(head3, 3);*/
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 9);
		return head3;
	}

	private ListNode mockData5() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 9);
		head3 = insertAtEnd(head3, 6);
		head3 = insertAtEnd(head3, 11);
		head3 = insertAtEnd(head3, 12);
		head3 = insertAtEnd(head3, 5);
		head3 = insertAtEnd(head3, 7);
		head3 = insertAtEnd(head3, 3);
		return head3;
	}

	private ListNode mockData6() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 0);
		head3 = insertAtEnd(head3, 1);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 1);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 1);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 0);
		return head3;
	}

	private ListNode mockData7() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 0);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 0);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 1);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 2);
		return head3;
	}

	private ListNode mockData8() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 8);
		head3 = insertAtEnd(head3, 11);
		head3 = insertAtEnd(head3, 9);
		head3 = insertAtEnd(head3, 3);
		head3 = insertAtEnd(head3, 1);
		head3 = insertAtEnd(head3, 7);
		head3 = insertAtEnd(head3, 5);
		head3 = insertAtEnd(head3, 6);
		return head3;
	}

	private ListNode mockData9() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 9);
		head3 = insertAtEnd(head3, 5);
		head3 = insertAtEnd(head3, 9);
		head3 = insertAtEnd(head3, 7);
		head3 = insertAtEnd(head3, 5);
		head3 = insertAtEnd(head3, 3);
		head3 = insertAtEnd(head3, 8);
		head3 = insertAtEnd(head3, 8);
		head3 = insertAtEnd(head3, 9);
		return head3;
	}

	private ListNode mockData10() {
		ListNode head = null;
		head = insertAtEnd(head, 1);
		head = insertAtEnd(head, 2);
		head = insertAtEnd(head, 3);
		head = insertAtEnd(head, 4);
		head = insertAtEnd(head, 5);
		head = insertAtEnd(head, 6);
		head = insertAtEnd(head, 7);
		head = insertAtEnd(head, 8);
		head = insertAtEnd(head, 9);
		return head;
	}

	private ListNode mockSortedDuplicateList() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 1);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 4);
		head3 = insertAtEnd(head3, 4);
		head3 = insertAtEnd(head3, 6);
		return head3;
	}

	private ListNode mockUnSortedDuplicateList() {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 7);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 4);
		head3 = insertAtEnd(head3, 5);
		head3 = insertAtEnd(head3, 2);
		head3 = insertAtEnd(head3, 4);
		return head3;
	}

	private LinkedList<Character> mockCharList() {
		LinkedList<Character> list = new LinkedList<>();
		list.add('R');
		list.add('A');
		list.add('D');
		list.add('A');
		list.add('R');
		return list;
	}

	private ListNode mockFlattenList() {
		ListNode node1 = new ListNode(5);
		node1.down = new ListNode(7);
		node1.down.down = new ListNode(8);
		node1.down.down.down = new ListNode(30);

		ListNode node2 = new ListNode(10);
		node2.down = new ListNode(20);

		ListNode node3 = new ListNode(19);
		node3.down = new ListNode(22);
		node3.down.down = new ListNode(50);

		ListNode node4 = new ListNode(28);
		node4.down = new ListNode(35);
		node4.down.down = new ListNode(40);
		node4.down.down.down = new ListNode(45);
		node3.right = node4;
		node2.right = node3;
		node1.right = node2;
		return node1;
	}

	// Build LL loop for testing
	public static ListNode mockLoopList(ListNode head) {
		if (head != null) {
			ListNode temp1 = head;
			ListNode temp2 = head.next.next;
			while (temp1.next != null)
				temp1 = temp1.next;
			temp1.next = temp2;
		}
		return head;
	}

	// Build LL Intersection for testing
	public static ListNode mockIntersectionList(ListNode head) {
		ListNode head3 = null;
		head3 = insertAtEnd(head3, 9);
		head3 = insertAtEnd(head3, 10);
		head3 = insertAtEnd(head3, 12);
		ListNode temp = head3;
		while (temp.next != null)
			temp = temp.next;
		temp.next = head.next.next;
		return head3;
	}

	private ListNode mockDLNodeWithArbitraryPtr() {
		ListNode head4 = null;
		head4 = insertDLL(head4, 1);
		head4 = insertDLL(head4, 2);
		head4 = insertDLL(head4, 3);
		head4 = insertDLL(head4, 4);
		head4 = insertDLL(head4, 5);
		// head = insert(head4, 6);

		// Setting up random references.
		head4.prev = head4.next.next;
		head4.next.prev = head4.next.next.next;
		head4.next.next.prev = head4.next.next.next.next;
		head4.next.next.next.prev = head4.next.next.next.next.next;
		head4.next.next.next.next.prev = head4.next;
		return head4;
	}

	private ListNode mockDLNode() {
		ListNode head = null;
		head = insertDLL2(head, 1);
		head = insertDLL2(head, 2);
		head = insertDLL2(head, 3);
		head = insertDLL2(head, 4);
		head = insertDLL2(head, 5);
		head = insertDLL2(head, 6);
		return head;
	}

	public List<NestedInteger> mockNestedInteger() {
		List<NestedInteger> mockList = new ArrayList<>();
		List<NestedInteger> list = new ArrayList<>();
		list.add(new NestedInteger(1));
		list.add(new NestedInteger(1));
		mockList.add(new NestedInteger(list));

		NestedInteger nestedInteger = new NestedInteger();
		nestedInteger.setInteger(2);
		mockList.add(nestedInteger);

		list = new ArrayList<>();
		list.add(new NestedInteger(3));
		list.add(new NestedInteger(3));
		mockList.add(new NestedInteger(list));
		return mockList;
	}
}