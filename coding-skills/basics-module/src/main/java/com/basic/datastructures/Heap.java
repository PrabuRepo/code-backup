package com.basic.datastructures;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Heap Implementation - Max Binary Heap, Min Binary Heap & Priority Queue Impl using Heap
 */
public class Heap {

}

class MaxBinaryHeap {
	int			heapSize;
	int			capacity;
	Integer[]	data;

	public MaxBinaryHeap(int capacity) {
		this.heapSize = 0;
		this.capacity = capacity;
		this.data = new Integer[capacity];
	}

	// Time Complexity: O(logn)
	public void insert(int x) {
		if (isHeapFull()) {
			System.out.println("Heap Full!");
		} else if (heapSize == 0) {
			data[heapSize++] = x;
		} else {
			data[heapSize] = x;
			for (int i = heapSize; i > 0; i = parent(
					i)) {
				if (data[parent(i)] != null
						&& data[parent(i)] < x) {
					swap(i, parent(i));
				} else {
					break;
				}
			}
			heapSize++;
		}
	}

	// Time Complexity:O(1)
	public Integer getMax() {
		return isHeapEmpty() ? null : data[0];
	}

	// Time Complexity: O(logn)
	public Integer extractMax() {
		Integer value = null;
		if (!isHeapEmpty()) {
			value = data[0];
			if (heapSize == 1) {
				heapSize--;
			} else {
				data[0] = data[heapSize - 1]; // Last element assigned to root
				data[heapSize--] = null; // Last element set it as null & reduce the heap size
				maxHeapify(0);
			}
		}
		return value;
	}

	// Time Complexity: O(logn)
	public void increaseKey(int index,
			Integer increasedValue) {
		if (index < heapSize) {
			data[index] = increasedValue;
			while (index > 0 && data[parent(
					index)] < data[index]) {
				swap(index, parent(index));
				index = parent(index);
			}
		} else {
			System.out.println(
					"Not a valid position");
		}
	}

	// Search/Access element: Time Complexity: O(n)
	public boolean search(int n) {
		boolean flag = false;
		for (int i = 0; i < heapSize; i++) {
			if (data[i] == n) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public void display() {
		for (int i = 0; i < heapSize; i++)
			System.out.print(data[i] + " ");
	}

	// Time Complexity: O(logn)
	private void maxHeapify(int i) {
		int left, right, maxIndex;
		while (i < heapSize) {
			left = left(i);
			right = right(i);
			maxIndex = i;

			if (left < heapSize
					&& data[left] > data[maxIndex])
				maxIndex = left;

			if (right < heapSize
					&& data[right] > data[maxIndex])
				maxIndex = right;

			if (maxIndex == i) break;
			swap(i, maxIndex);
			i = maxIndex;
		}
	}

	// Time Complexity: O(logn)
	// Recursive:
	public void maxHeapify2(int i) {
		int left = left(i);
		int right = right(i);
		int maxIndex = i;

		if (left < heapSize
				&& data[left] > data[maxIndex])
			maxIndex = left;

		if (right < heapSize
				&& data[right] > data[maxIndex])
			maxIndex = right;

		if (i != maxIndex) {
			swap(i, maxIndex);
			maxHeapify2(maxIndex);
		}
	}

	private int parent(int i) {
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
		int temp = data[pos1];
		data[pos1] = data[pos2];
		data[pos2] = temp;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		MaxBinaryHeap binaryHeap = new MaxBinaryHeap(
				20);
		do {
			System.out.println(
					"Maximum Binary Heap Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.getMaximum");
			System.out.println("4.Increase Key");
			System.out.println("5.Find/Search");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {
				case 1:
					System.out.println(
							"Enter no of data to be inserted:");
					int t = in.nextInt();
					while (t-- > 0)
						binaryHeap.insert(
								in.nextInt());

					System.out.println(
							"Elements are inserted!");
					break;
				case 2:
					System.out.println(
							"Max Element: "
									+ binaryHeap
											.extractMax());
					break;
				case 3:
					System.out.println(
							"Max element present in Heap: "
									+ binaryHeap
											.getMax());
					break;
				case 4:
					System.out.println(
							"Enter the index & increased value:");
					binaryHeap.increaseKey(
							in.nextInt(),
							in.nextInt());
					break;
				case 6:
					System.out.println(
							"Enter element needs to find: ");
					System.out.println(
							"Element present in the heap? "
									+ binaryHeap
											.search(in
													.nextInt()));
					break;
				default:
					System.out.println(
							"Please enter the valid option!!!");
					break;

			}
			System.out.println("\nDisplay:");
			binaryHeap.display();

			System.out.println(
					"\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}
}

class MinBinaryHeap {
	Integer[]	elements;
	int			capacity;
	int			currSize;

	public MinBinaryHeap(int max) {
		this.elements = new Integer[max];
		this.capacity = max;
		this.currSize = 0;
	}

	// Time Complexity: O(logn)
	public void insert(int data) {
		if (!isFull()) {
			elements[currSize] = data;
			for (int i = currSize; (i > 0
					&& elements[parent(
							i)] > elements[i]); i = parent(
									i))
				swap(parent(i), i);

			currSize++;
		} else {
			System.out.println(
					"Overflow: heap full!");
		}
	}

	// TODO: Delete Operation - Time Complexity - ???
	public void delete() {

	}

	// Time Complexity:O(1)
	public Integer getMin() {
		return currSize > 0 ? elements[0] : null;
	}

	// Time Complexity: O(logn)
	public Integer extractMin() {
		Integer element = null;
		if (!isEmpty()) {
			// Get the min element from 0th position
			element = elements[0];
			if (currSize == 1) {
				elements[0] = null;
				currSize--;
			} else {
				elements[0] = elements[currSize
						- 1]; // assign last element in the heap
				elements[currSize - 1] = null;
				currSize--;
				// minHeapify(0);
				minHeapifyIterative(0);
			}
		}
		return element;
	}

	private void minHeapifyIterative(int i) {
		int left, right, minIndex;
		while (i < currSize) {
			left = leftChild(i);
			right = rightChild(i);
			minIndex = i;

			if (left < currSize
					&& elements[left] < elements[minIndex]) {
				minIndex = left;
			}
			if (right < currSize
					&& elements[right] < elements[minIndex]) {
				minIndex = right;
			}
			if (i != minIndex) {
				swap(minIndex, i);
				i = minIndex;
			} else {
				break;
			}
		}

	}

	// Time Complexity: O(logn)
	private void minHeapify(int index) {
		int left = leftChild(index);
		int right = rightChild(index);
		int smallest = index;

		if (left < currSize
				&& elements[left] < elements[smallest])
			smallest = left;

		if (right < currSize
				&& elements[right] < elements[smallest])
			smallest = right;

		if (smallest != index) {
			swap(index, smallest);
			minHeapify(smallest);
		}
	}

	// Time Complexity: O(logn)
	public void decreaseKey(int index,
			int newValue) {
		if (index < currSize) {
			elements[index] = newValue;
			for (int i = index; (i > 0
					&& elements[parent(
							i)] > elements[i]); i = parent(
									i))
				swap(parent(i), i);
		}

	}

	// Time Complexity: O(logn)
	public void removeKey(int index) {
		if (index < currSize) {
			elements[index] = elements[currSize
					- 1];
			elements[currSize - 1] = null;
			currSize--;
			minHeapify(index);
		}
	}

	public void display() {
		if (currSize > 0) {
			for (int i = 0; i < currSize; i++)
				System.out
						.print(elements[i] + " ");
		} else {
			System.out.println("Heap is Empty!");
		}
	}

	private boolean isFull() {
		return (currSize == capacity) ? true
				: false;
	}

	private boolean isEmpty() {
		return currSize <= 0;
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int leftChild(int i) {
		return (2 * i) + 1;
	}

	private int rightChild(int i) {
		return (2 * i) + 2;
	}

	private void swap(int pos1, int pos2) {
		int temp = elements[pos1];
		elements[pos1] = elements[pos2];
		elements[pos2] = temp;
	}

	// Search/Access element: Time Complexity: O(n)
	public boolean search(int n) {
		boolean flag = false;
		for (int i = 0; i < currSize; i++) {
			if (elements[i] == n) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		MinBinaryHeap binaryHeap = new MinBinaryHeap(
				20);
		do {
			System.out.println(
					"Minimum Binary Heap Operations:");
			System.out.println("1.Insert");
			System.out.println(
					"2.Delete/extractMin");
			System.out.println("3.getMinimum");
			System.out.println("4.Decrease Key");
			System.out.println("5.Find/Search");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {

				case 1:
					System.out.println(
							"Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0)
						binaryHeap.insert(
								in.nextInt());

					System.out.println(
							"Elements are inserted!");
					break;
				case 2:
					System.out.println(
							"Deleted min Element in the heap: "
									+ binaryHeap
											.extractMin());
					break;
				case 3:
					System.out.println(
							"Min element present in Heap: "
									+ binaryHeap
											.getMin());
					break;
				case 4:
					System.out.println(
							"Enter the index & decreased value:");
					binaryHeap.decreaseKey(
							in.nextInt(),
							in.nextInt());
					break;
				case 5:
					System.out.println(
							"Enter element needs to find: ");
					System.out.println(
							"Element present in the heap? "
									+ binaryHeap
											.search(in
													.nextInt()));
					break;
				default:
					System.out.println(
							"Please enter the valid option!!!");
					break;

			}
			System.out.println("\nDisplay:");
			binaryHeap.display();

			System.out.println(
					"\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}
}

class PriorityQueue {

	int[]	elements;
	int		currSize;
	int		maxSize;

	public PriorityQueue(int max) {
		this.maxSize = max;
		this.currSize = 0;
		elements = new int[max];
		Arrays.fill(elements, -1);
	}

	// Add the elements
	public void enQueue(int data) {
		if (currSize == maxSize) {
			System.out.println(
					"Overflow: Queue is full.");
		} else {
			elements[currSize] = data;
			for (int i = currSize; i >= 0
					&& elements[i] < elements[parent(
							i)]; i = parent(i))
				swap(elements, i, parent(i));
		}
		currSize++;
	}

	// Remove the smallest element(Highest priority) in the queue
	public int deQueue() {
		int minElement = -1;
		if (!isEmpty()) {
			minElement = elements[0];
			if (currSize == 1) {
				elements[0] = -1;
				currSize--;
			} else {
				elements[0] = elements[currSize
						- 1];
				elements[currSize - 1] = -1;
				currSize--; // Current size should be reduced before invoking the heapify
				// Rebuild the heap data structure
				heapify(elements, 0);
			}
		}
		return minElement;
	}

	private boolean isEmpty() {
		return currSize == 0 ? true : false;
	}

	public int peek() {
		return isEmpty() ? -1 : elements[0];
	}

	public void display() {
		if (!isEmpty()) {
			for (int i = 0; i < currSize; i++)
				System.out
						.print(elements[i] + " ");
		}
	}

	/* Heapify - It is used to build the heap property array. It does from any element to leaf*/
	private void heapify(int[] a,
			int startIndex) {
		if (startIndex < currSize) {
			int leftChild = leftChild(startIndex);
			int rightChild = rightChild(
					startIndex);
			int minIndex = startIndex;

			if (leftChild < currSize
					&& a[leftChild] < a[minIndex])
				minIndex = leftChild;

			if (rightChild < currSize
					&& a[rightChild] < a[minIndex])
				minIndex = rightChild;

			if (startIndex != minIndex) {
				swap(elements, startIndex,
						minIndex);
				heapify(a, minIndex);
			}
		}
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int leftChild(int i) {
		return (2 * i) + 1;
	}

	private int rightChild(int i) {
		return (2 * i) + 2;
	}

	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		PriorityQueue queue = new PriorityQueue(
				10);
		do {
			System.out
					.println("Queue Operations:");
			System.out.println("1.EnQueue");
			System.out.println(
					"2.Dequeue(highest Priority)");
			System.out.println("3.Peek");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {
				case 1:
					System.out.println(
							"Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0) {
						queue.enQueue(
								in.nextInt());
					}
					System.out.println(
							"Elements are inserted!");
					break;
				case 2:
					System.out.println(
							"Dequeue operation: "
									+ queue.deQueue());
					break;
				case 3:
					System.out.println(
							"Top element in the Queue: "
									+ queue.peek());
					break;
				default:
					System.out.println(
							"Please enter the valid option!!!");
					break;
			}

			System.out.println("\nDisplay:");
			queue.display();

			System.out.println(
					"\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}
}
