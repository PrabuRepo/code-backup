package com.web.hackersrank.datastructures;

class Node {
	int		data;
	Node	next;
}

public class LinkedListTest {

	public static void main(String[] args) {
		LinkedListTest test = new LinkedListTest();
		Node head = null, head1 = null, head2 = null;
		head = test.InsertNth(head, 3, 0);
		head = test.InsertNth(head, 5, 1);
		head = test.InsertNth(head, 4, 2);
		head = test.InsertNth(head, 2, 3);
		head = test.InsertNth(head, 10, 1);
		// head = test.reverse(head);
		// System.out.println("head: Display List:");
		// test.printList(head);

		// Merge two linked list
		head1 = test.tailInsert(head1, 11);
		head1 = test.tailInsert(head1, 12);
		head1 = test.tailInsert(head1, 13);

		head2 = test.tailInsert(head2, 14);
		head2 = test.tailInsert(head2, 15);
		head2 = test.tailInsert(head2, 16);
		head1.next.next.next = head2;
		head.next.next = head2;

		System.out.println("head: Display List:");
		test.printList(head);
		System.out.println("\nhead1: Display List:");
		test.printList(head1);
		// System.out.println("\nDisplay Reverse List:");
		// test.ReversePrint(head);
		System.out.println("\nMerge Node:" + test.findMergeNode(head, head1));

	}

	void printList(Node head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

	Node tailInsert(Node head, int data) {
		Node newNode = new Node();
		newNode.data = data;

		if (head == null) {
			return newNode;
		} else {
			Node temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
		}
		return head;
	}

	Node headInsert(Node head, int x) {
		Node newNode = new Node();
		newNode.data = x;

		if (head == null) {
			return newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		return head;
	}

	Node InsertNth(Node head, int data, int position) {
		Node newNode = new Node();
		newNode.data = data;
		int pos = 0;
		if (head == null || position == 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node temp = head;
			Node prev = null;
			while (pos <= position) {
				if (position == pos++) {
					newNode.next = temp;
					prev.next = newNode;
					break;
				}
				prev = temp;
				temp = temp.next;
			}
		}
		return head;
	}

	Node Delete(Node head, int position) {
		int count = 0;
		if (head != null) {
			if (position == 0) {
				head = head.next;
			} else {
				Node temp = head;
				Node prev = head;
				while (count <= position) {
					if (position == count++) {
						prev.next = temp.next;
						break;
					}
					prev = temp;
					temp = temp.next;
				}
			}
		}
		return head;
	}

	Node reverse(Node head) {
		if (head == null || head.next == null)
			return head;
		Node rem = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return rem;
	}

	void ReversePrint(Node head) {
		if (head != null) {
			ReversePrint(head.next);
			System.out.println(head.data);
		}
	}

	int CompareLists(Node headA, Node headB) {
		int result = 1;
		if (headA == null && headB == null) {
			result = 1;
		} else if (headA == null || headB == null) {
			result = 0;
		} else {
			while (headA != null || headB != null) {
				if (headA != null && headB != null) {
					if ((headA.data != headB.data)) {
						result = 0;
						break;
					}
				} else {
					result = 0;
					break;
				}
				headA = headA.next;
				headB = headB.next;
			}
		}
		return result;
	}

	Node mergeLists(Node headA, Node headB) {
		if (headA == null)
			return headB;
		else if (headB == null)
			return headA;
		else if (headA.data <= headB.data) {
			headA.next = mergeLists(headA.next, headB);
			return headA;
		} else {
			headB.next = mergeLists(headA, headB.next);
			return headB;
		}
	}

	int GetNode(Node head, int n) {
		Node temp = head;
		int count = 0;
		while (head != null) {
			if (count++ > n) {
				temp = temp.next;
			}
			head = head.next;
		}
		return temp.data;
	}

	Node RemoveDuplicates(Node head) {
		Node temp = head;
		while (temp != null) {
			if (temp.next != null && temp.data == temp.next.data) {
				temp.next = temp.next.next;
			} else {
				temp = temp.next;
			}
		}
		return head;
	}

	// Tortoise and hare approach
	boolean hasCycle(Node head) {
		if (head == null || head.next == null)
			return false;
		Node slow = head;
		Node fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	int findMergeNode(Node headA, Node headB) {
		Node currA = headA;
		Node currB = headB;
		while (currA != currB) {
			if (currA == null) {
				currA = headB;
			} else {
				currA = currA.next;
			}

			if (currB == null) {
				currB = headA;
			} else {
				currB = currB.next;
			}
		}
		return currB.data; // can use currA or currB; because of both are equal
	}
}
