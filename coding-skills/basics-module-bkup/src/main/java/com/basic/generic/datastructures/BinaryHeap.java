package com.basic.generic.datastructures;

import java.util.Scanner;

// TODO: Try to max & min operation in a single class
public class BinaryHeap<T extends Comparable<T>> {

	int	heapSize;
	int	capacity;
	T[]	elements;

	public BinaryHeap(int capacity) {
		this.heapSize = 0;
		this.capacity = capacity;
		this.elements = (T[]) new Comparable[capacity];
	}

	public void insert(T x) {
		if (isHeapFull()) {
			System.out.println("Heap Full!");
		} else if (heapSize == 0) {
			elements[heapSize++] = x;
		} else {
			elements[heapSize] = x;
			for (int i = heapSize; i > 0; i = (i - 1) / 2) {
				if (elements[root(i)] != null && elements[root(i)].compareTo(x) > 0) {
					swap(i, root(i));
				} else {
					break;
				}
			}
			heapSize++;
		}
	}

	public T getMin() {
		return isHeapEmpty() ? null : elements[0];
	}

	public T extractMin() {
		T value = null;
		if (!isHeapEmpty()) {
			value = elements[0];
			if (heapSize == 1) {
				elements[0] = null;
				heapSize--;
			} else {
				elements[0] = elements[heapSize - 1]; // Last element assigned to root
				elements[heapSize--] = null; // Last element set it as null & reduce the heap size
				minHeapify(0);
			}
		}
		return value;
	}

	public void decreaseKey(int index, T decreasedValue) {
		if (index < heapSize) {
			elements[index] = decreasedValue;
			while (index > 0 && elements[root(index)].compareTo(elements[index]) > 0) {
				swap(index, root(index));
				index = root(index);
			}
		} else {
			System.out.println("Not a valid position");
		}
	}

	public void display() {
		for (int i = 0; i < heapSize; i++)
			System.out.print(elements[i] + " ");
	}

	private void minHeapify(int node) {
		while (node < heapSize) {
			if (elements[left(node)] == null && elements[right(node)] == null) {
				break;
			} else if (elements[right(node)] == null) {
				swap(node, left(node));
				node = left(node);
			} else if (elements[left(node)].compareTo(elements[right(node)]) < 0) {
				swap(node, left(node));
				node = left(node);
			} else {
				swap(node, right(node));
				node = right(node);
			}
		}
	}

	public boolean search(T n) {
		boolean flag = false;
		for (int i = 0; i < heapSize; i++) {
			if (elements[i].compareTo(n) == 0) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private int root(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	private boolean isHeapFull() {
		return (heapSize == capacity);
	}

	private boolean isHeapEmpty() {
		return (heapSize == 0);
	}

	private void swap(int pos1, int pos2) {
		T temp = elements[pos1];
		elements[pos1] = elements[pos2];
		elements[pos2] = temp;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(20);
		do {
			System.out.println("Minimum Binary Heap Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.getMinimum");
			System.out.println("4.Decrease Key");
			System.out.println("5.Find/Search");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {

				case 1:
					System.out.println("Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0)
						binaryHeap.insert(in.nextInt());

					System.out.println("Elements are inserted!");
					break;
				case 2:
					System.out.println("Min Element: " + binaryHeap.extractMin());
					break;
				case 3:
					System.out.println("Min element present in Heap: " + binaryHeap.getMin());
					break;
				case 4:
					System.out.println("Enter the index & decreased value:");
					binaryHeap.decreaseKey(in.nextInt(), in.nextInt());
					break;
				case 5:
					System.out.println("Enter element needs to find: ");
					System.out.println("Element present in the heap? " + binaryHeap.search(in.nextInt()));
					break;
				default:
					System.out.println("Please enter the valid option!!!");
					break;

			}
			System.out.println("\nDisplay:");
			binaryHeap.display();

			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}
}
