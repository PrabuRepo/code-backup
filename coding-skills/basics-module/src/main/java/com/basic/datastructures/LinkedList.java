package com.basic.datastructures;

import com.common.model.ListNode;

/*
 * Linked List Implementation: 
 *   1. Singly Linked List
 *   2. Doubly Linked List
 *   3. Circular Singly Linked List
 *   
 *  Note: Consider below for all the LL operations:
 *  	1.Front End
 *  	2.Tail End
 *  	3.Middle
 */
public class LinkedList {
	public static void main(String[] args) {
		System.out.println(
				"Linked List Operations:");
		LinkedList ob = new LinkedList();

		System.out
				.println("1. Singly Linked List");
		ob.testSinglyLinkedList();

		System.out.println(
				"\n2. Doubly Linked List");
		ob.testDoublyLinkedList();

		System.out.println(
				"\n3. Circular Singly Linked List");
		ob.testCircularSLinkedList();
	}

	public void testSinglyLinkedList() {
		SinglyLinkedList list = new SinglyLinkedList();
		System.out.println("Insert:");
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(7);
		System.out.println("\nDisplay:");
		list.display();
		list.insert(4, 4);
		list.insert(7, 8);
		System.out.println("\nDisplay:");
		list.display();
		System.out.println(
				"\nDelete: " + list.remove(1));
		System.out.println(
				"Find: " + list.contains(3));
	}

	public void testDoublyLinkedList() {
		CircularSLinkedList list = new CircularSLinkedList();
		System.out.println("Insert");
		list.insert(1);
		list.insert(2);
		list.insert(3);
		System.out.println("Display:");
		list.display();
		System.out.println("\nDelete: ");
		list.delete(1);
		System.out.println(
				"Find: " + list.search(3));

	}

	public void testCircularSLinkedList() {
		CircularSLinkedList list = new CircularSLinkedList();
		System.out.println("Insert");
		list.insert(1);
		list.insert(2);
		list.insert(3);
		System.out.println("Display:");
		list.display();
		System.out.println("\nDelete: ");
		list.delete(1);
		System.out.println(
				"Find: " + list.search(3));
	}
}

class SinglyLinkedList {

	ListNode head;

	public void add(int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) {
			head = newNode;
		} else {
			ListNode curr = head;
			while (curr.next != null)
				curr = curr.next;
			curr.next = newNode;
		}
	}

	public void insert(int pos, int data) {
		ListNode curr = head;
		while (--pos > 1)// Move to prev node
			curr = curr.next;

		ListNode newNode = new ListNode(data);
		if (curr != null) {
			newNode.next = curr.next;
			curr.next = newNode;
		}
	}

	public boolean contains(int data) {
		ListNode curr = head;
		while (curr != null) {
			if (curr.data == data) return true;
			else curr = curr.next;
		}
		return false;
	}

	public boolean remove(int data) {
		boolean flag = false;
		if (head != null && head.data == data) {
			head = head.next;
			flag = true;
		} else {
			ListNode curr = head;
			while (curr != null
					&& curr.next != null) {
				if (curr.next.data == data) {
					curr.next = curr.next.next;
					flag = true;
					break;
				} else {
					curr = curr.next;
				}
			}
		}
		return flag;
	}

	public void display() {
		ListNode curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
	}

	//TODO: Move this
	public ListNode middleNode() {
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr.next.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}

	public boolean isPalindrome() {
		boolean flag = true;
		ListNode middle;
		ListNode slowPtr = head, fastPtr = head;
		while (fastPtr.next != null
				&& fastPtr.next.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		// To handle odd and even nodes
		middle = fastPtr.next != null
				? slowPtr.next
				: slowPtr;

		ListNode temp = head;
		ListNode reverse = reverse(middle);
		while (reverse != null) {
			if (reverse.data == temp.data) {
				reverse = reverse.next;
				temp = temp.next;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public ListNode reverse(ListNode headNode) {
		ListNode prev = null, next = null,
				current = headNode;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		headNode = prev;
		return headNode;
	}

}

class DoublyLinkedList {

	ListNode head;

	public void add(int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) {
			head = newNode;
		} else {
			ListNode curr = head;
			while (curr.next != null)
				curr = curr.next;
			curr.next = newNode;
			newNode.prev = curr;
		}
	}

	public void delete1(int data) {
		if (head == null) {
			System.out.println("List is empty");
		} else if (head.data == data) {
			head = head.next;
			if (head != null) head.prev = null;
		} else {
			ListNode curr = head;
			while (curr.next != null) {
				if (curr.next.data == data) {
					ListNode next = curr.next.next;
					curr.next = next;
					if (next != null)
						next.prev = curr.next;
					break;
				} else {
					curr = curr.next;
				}
			}

		}
	}

	public void delete(int data) {
		if (head == null) {
			System.out.println("List is empty");
		} else {
			ListNode curr = head;
			// Find the Node
			while (curr != null) {
				if (curr.data == data) break;
				curr = curr.next;
			}
			// Delete the node
			if (curr != null) {
				ListNode prev = curr.prev;
				if (prev != null) {
					prev.next = curr.next;
				} else {
					curr = curr.next;
					if (curr != null) // Need this check for only head node in the list
						curr.prev = null;
					head = curr;
				}
			} else {
				System.out.println(
						"Data is not found!");
			}
		}
	}

	public boolean search(int data) {
		if (head == null) return false;
		ListNode curr = head;
		while (curr != null) {
			if (curr.data == data)
				return true;
			curr = curr.next;
		}
		return false;
	}

	public void display() {
		ListNode curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
	}

}

// This implementation using single pointer, if we use 2 pointers, all the operations complexity will be O(1) or
// Constant time.
class CircularSLinkedList {
	ListNode head;

	public void insert(int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) {
			head = newNode;
			head.next = head;
		} else {
			ListNode curr = head;
			do {
				if (curr.next == head) {
					curr.next = newNode;
					newNode.next = head;
					break;
				} else {
					curr = curr.next;
				}
			} while (curr != head);
		}
	}

	public boolean search(int data) {
		ListNode curr = head;
		if (curr != null) {
			do {
				if (curr.data == data)
					return true;
				curr = curr.next;
			} while (curr != head);
		}

		return false;
	}

	public int countNodes() {
		ListNode curr = head;
		int count = 0;
		if (curr != null) {
			do {
				count++;
				curr = curr.next;
			} while (curr != head);
		}
		return count;
	}

	public void delete(int data) {
		ListNode curr = head;
		if (curr != null) {
			if (curr.data == data
					&& curr.next == head) {
				head = null;
			} else if (curr.data == data) {
				curr.data = curr.next.data;
				curr.next = curr.next.next;
			} else {
				do {
					if (curr.next.data == data) {
						curr.next = curr.next.next;
						break;
					} else {
						curr = curr.next;
					}
				} while (curr != head);
			}
		} else {
			System.out.println("List is empty");
		}
	}

	public void display() {
		ListNode temp = head;
		if (temp != null) {
			do {
				System.out.print(temp.data + " ");
				temp = temp.next;
			} while (temp != head);
		} else {
			System.out.println("List is empty");
		}

	}
}