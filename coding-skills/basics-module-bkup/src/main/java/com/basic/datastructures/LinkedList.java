package com.basic.datastructures;

import com.common.model.ListNode;

/*
 * Linked List Implementation: 
 *   1. Singly Linked List
 *   2. Doubly Linked List
 *   3. Circular Singly Linked List
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
		} else if (head.next == null) {
			head.next = newNode;
		} else {
			ListNode temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
		}
	}

	public void insert(int pos, int data) {
		ListNode temp = head;
		while (--pos > 1)// Move to prev node
			temp = temp.next;

		ListNode newNode = new ListNode(data);
		if (temp != null) {
			newNode.next = temp.next;
			temp.next = newNode;
		}
	}

	public boolean contains(int data) {
		ListNode temp = head;
		while (temp != null) {
			if (temp.data == data) return true;
			else temp = temp.next;
		}
		return false;
	}

	public boolean remove(int data) {
		boolean flag = false;
		if (head != null && head.data == data) {
			head = head.next;
			flag = true;
		} else {
			ListNode temp = head;
			while (temp != null
					&& temp.next != null) {
				if (temp.data == data) {
					temp.next = temp.next.next;
					flag = true;
					break;
				} else {
					temp = temp.next;
				}
			}
		}
		return flag;
	}

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

	public void display() {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

}

class DoublyLinkedList {

	ListNode head;

	public void add(int data) {
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
		if (head != null) {
			ListNode temp = head;
			while (temp != null) {
				if (temp.data == data)
					return true;
				temp = temp.next;
			}
		}
		return false;
	}

	public void display() {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

	public void reverse() {

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
			ListNode temp = head;
			do {
				if (temp.next == head) {
					temp.next = newNode;
					newNode.next = head;
					break;
				} else {
					temp = temp.next;
				}
			} while (temp != head);
		}
	}

	public boolean search(int data) {
		ListNode temp = head;
		if (temp != null) {
			do {
				if (temp.data == data)
					return true;
				else temp = temp.next;
			} while (temp != head);
		}

		return false;
	}

	public int countNodes() {
		ListNode temp = head;
		int count = 0;
		if (temp != null) {
			do {
				count++;
				temp = temp.next;
			} while (temp != head);
		}
		return count;
	}

	public void delete(int data) {
		ListNode temp = head;
		if (temp != null) {
			if (temp.data == data
					&& temp.next == head) {
				head = null;
			} else if (temp.data == data) {
				temp.data = temp.next.data;
				temp.next = temp.next.next;
			} else {
				do {
					if (temp.next.data == data) {
						temp.next = temp.next.next;
						break;
					} else {
						temp = temp.next;
					}
				} while (temp != head);
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