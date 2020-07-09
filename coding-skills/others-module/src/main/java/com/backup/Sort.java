package com.backup;

import java.util.Arrays;
import java.util.Scanner;

public class Sort {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		int[] a = null;
		Sort sort = new Sort();
		do {
			System.out.println("Sorting Algorithms:");
			System.out.println("1.Bubble Sort");
			System.out.println("2.Selection Sort");
			System.out.println("3.Insertion Sort");
			System.out.println("4.Merge Sort");
			System.out.println("5.Merge Sort for Linked List");
			System.out.println("6.Quick Sort");
			System.out.println("7.Heap Sort");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {
			case 1:
				a = sort.insert(in);
				// sort.bubbleSort(a);
				sort.bubbleSortRecursive(a);
				break;
			case 2:
				a = sort.insert(in);
				sort.selectionSort(a);
				break;
			case 3:
				a = sort.insert(in);
				sort.insertionSort(a);
				// sort.insertionSortRecursive(a);
				break;
			case 4:
				a = sort.insert(in);
				sort.mergeSort(a);
				break;
			case 5:
				System.out.println("Enter no of elements to be inserted:");
				int t = in.nextInt();
				LLNode head = null;
				for (int i = 0; i < t; i++)
					head = sort.insertLL(head, in.nextInt());
				head = sort.mergeSortForLL(head);
				sort.displayLL(head);
				break;
			case 6:
				a = sort.insert(in);
				sort.quickSort(a);
				break;
			case 7:
				a = sort.insert(in);
				sort.heapSort(a);
				break;
			default:
				System.out.println("Please enter the valid option!!!");
				break;

			}
			System.out.println("\nDisplay:");
			if (a != null)
				sort.display(a);
			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}

	// Bubble sort - start
	public void bubbleSort(int[] a) {
		boolean swapFlag;
		int count = 0;
		if (a.length == 1)
			return;
		for (int i = 1; i < a.length; i++) {
			swapFlag = false;
			count++;
			for (int j = 0; j < a.length - i; j++) {
				if (a[j] > a[j + 1]) {
					a = swap(a, j, j + 1);
					swapFlag = true;
				}
			}
			System.out.println("No of iterations:" + count);
			if (!swapFlag) {
				System.out.println("No swapping, i.e array sorted");
				break;
			}
		}
	}

	public void bubbleSortRecursive(int[] a) {
		bubbleSortAlg(a, a.length);
	}

	private void bubbleSortAlg(int[] a, int n) {
		if (n == 1)
			return;

		for (int j = 0; j < n - 1; j++) {
			if (a[j] > a[j + 1]) {
				a = swap(a, j, j + 1);
			}
		}

		bubbleSortAlg(a, n - 1);
	}
	// Bubble sort - end

	// Selection - start
	public void selectionSort(int[] a) {
		int minIndex;
		for (int i = 0; i < a.length; i++) {
			minIndex = i;
			for (int j = i; j < a.length; j++) {
				if (a[minIndex] > a[j])
					minIndex = j;
			}
			swap(a, minIndex, i);
		}
	}
	// Selection - end

	// Insertion - start
	public void insertionSort(int[] a) {
		int index;
		for (int i = 1; i < a.length; i++) {
			index = i;
			for (int j = 0; j <= i; j++) {
				if (a[index] < a[j])
					swap(a, index, j);
			}
			display(a);
			System.out.println();
		}
	}

	public void insertionSortRecursive(int[] a) {
		insertionSortAlg(a, a.length);
	}

	private void insertionSortAlg(int[] a, int n) {
		if (n <= 1)
			return;

		insertionSortAlg(a, n - 1);

		int index = n - 1;
		for (int j = 0; j <= n - 1; j++) {
			if (a[index] < a[j])
				swap(a, index, j);
		}
	}
	// Insertion - end

	// Merge sort - start
	public void mergeSort(int[] a) {
		divide(a, 0, a.length - 1);
	}

	private void divide(int[] a, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			// System.out.println("1.left:" + l + " Right: " + r);
			divide(a, l, m);
			// System.out.println("2.middle:" + (m + 1) + " Right: " + r);
			// System.out.println("2.left:" + l + " Right: " + r);
			divide(a, m + 1, r);
			// System.out.println("3.left:" + l + " Right: " + r);
			// Merge sub arrays
			merge(a, l, m, r);
		}
	}

	private void merge(int[] a, int l, int m, int r) {
		int leftSize = m - l + 1;
		int rightSize = r - m;

		int[] left = new int[leftSize];
		int[] right = new int[rightSize];

		for (int i = 0; i < leftSize; i++)
			left[i] = a[l + i];

		for (int j = 0; j < rightSize; j++)
			right[j] = a[m + 1 + j];

		int i = 0, j = 0, k = l;
		while (i < leftSize && j < rightSize) {
			if (left[i] <= right[j]) {
				a[k++] = left[i++];
			} else {
				a[k++] = right[j++];
			}
		}

		while (i < leftSize) {
			a[k++] = left[i++];
		}

		while (j < rightSize) {
			a[k++] = right[j++];
		}
	}

	public LLNode mergeSortForLL(LLNode head) {
		return divideLL(head);
	}

	private LLNode divideLL(LLNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		LLNode middle = middleNode(head);
		LLNode nextMiddle = middle.next;
		middle.next = null;

		LLNode left = divideLL(head);
		LLNode right = divideLL(nextMiddle);

		LLNode sortedListHead = mergeLL(left, right);
		return sortedListHead;
	}

	private LLNode middleNode(LLNode node) {
		if (node == null)
			return node;

		LLNode slowPtr = node;
		LLNode fastPtr = node;
		while (fastPtr != null) {
			if (fastPtr.next != null && fastPtr.next.next != null) {
				fastPtr = fastPtr.next.next;
				slowPtr = slowPtr.next;
			} else {
				break;
			}
		}
		return slowPtr;
	}

	private LLNode mergeLL(LLNode left, LLNode right) {
		LLNode result;
		if (left == null)
			return right;
		if (right == null)
			return left;

		if (left.data < right.data) {
			result = left;
			result.next = mergeLL(left.next, right);
		} else {
			result = right;
			result.next = mergeLL(left, right.next);
		}
		return result;
	}

	public LLNode insertLL(LLNode head, int data) {
		LLNode newNode = new LLNode(data);
		if (head == null) {
			head = newNode;
		} else {
			LLNode temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
		}
		return head;
	}

	public void displayLL(LLNode head) {
		if (head != null) {
			while (head != null) {
				System.out.print(head.data + " ");
				head = head.next;
			}
		}
	}
	// Merge sort - end

	// Quick sort - start
	public void quickSort(int[] a) {
		qSort(a, 0, a.length - 1);
	}

	private void qSort(int[] a, int left, int right) {
		if (left < right) {
			int mid = partition(a, left, right);
			qSort(a, left, mid - 1);
			qSort(a, mid + 1, right);
		}
	}

	private int partition(int[] a, int left, int right) {
		int pivot = a[right];
		int partitionIndex = left; // set first element from left

		for (int i = left; i < right; i++) {
			if (a[i] < pivot) { // swap if element is lesser than pivot
				swap(a, partitionIndex, i);
				partitionIndex++;
			}
		}

		swap(a, partitionIndex, right); // swap pivot element at the end
		return partitionIndex;
	}
	// Quick sort - end

	// Heap sort - start
	public void heapSort(int[] a) {
		int size = a.length;
		// Build heapify the given array
		for (int i = (size / 2) - 1; i >= 0; i--)
			maxHeapify(a, i, size);
		// One by one extract an element from heap
		for (int i = size - 1; i >= 0; i--) { // Time complexity: maxheapify for n times, its o(nlog(n))
			// Move max element (current root) to end
			a = swap(a, 0, i);
			// call max heapify on the reduced heap
			maxHeapify(a, 0, i);
		}
	}

	// Time complexity for maxheapify is o(log(n))
	private void maxHeapify(int[] a, int index, int size) {
		int left = 2 * index + 1; // left child
		int right = 2 * index + 2; // right child
		int largest = index; // Initialize largest as root

		if (left < size && a[left] > a[largest])  // Check left child with parent
			largest = left;

		if (right < size && a[right] > a[largest]) // Check right child with parent
			largest = right;

		if (largest != index) { // Swap if largest element index changes
			a = swap(a, largest, index);
			maxHeapify(a, largest, size);
		}
	}

	private int[] swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}

	// Heap sort - end
	public int[] insert(Scanner in) {
		System.out.println("Enter no of elements to be inserted:");
		int t = in.nextInt();
		int[] a = new int[t];
		for (int i = 0; i < t; i++)
			a[i] = in.nextInt();
		return a;
	}

	public void display(int[] a) {
		Arrays.stream(a).forEach(ele -> System.out.print(ele + " "));
	}
}

class LLNode {

	int		data;
	LLNode	next;

	public LLNode(int data) {
		this.data = data;
		this.next = null;
	}

}