package com.basic.algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import com.common.model.ListNode;
import com.common.utilities.Utils;

public class SortingAlgorithms {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		int[] a = null;
		SortingAlgorithms sort = new SortingAlgorithms();
		do {
			System.out.println("Sorting Algorithms:");
			System.out.println("1.Bubble Sort");
			System.out.println("2.Selection Sort");
			System.out.println("3.Insertion Sort");
			System.out.println("4.Merge Sort");
			System.out.println("5.Merge Sort for Linked List");
			System.out.println("6.Quick Sort");
			System.out.println("7.Heap Sort");
			System.out.println("8.Counting Sort");
			System.out.println("9.Radix Sort");
			System.out.println("10.Bucket Sort");
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
					// sort.insertionSort(a.length, a);
					sort.insertionSortRecursive(a);
					break;
				case 4:
					a = sort.insert(in);
					sort.mergeSort(a);
					break;
				case 5:
					System.out.println("Enter no of elements to be inserted:");
					int t = in.nextInt();
					ListNode head = null;
					for (int i = 0; i < t; i++)
						head = sort.insertLL(head, in.nextInt());
					head = sort.mergeSortForLL(head);
					sort.displayLL(head);
					break;
				case 6:
					a = sort.insert(in);
					// sort.quickSort(a);
					quickSort1(a, 0, a.length - 1);
					break;
				case 7:
					a = sort.insert(in);
					sort.heapSort(a);
					break;
				case 8:
					char arr[] = { 'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's', 'a', 'z' };
					countingSort(arr);
					System.out.println("Sorted Array:");
					for (int i = 0; i < arr.length; i++)
						System.out.print(arr[i]);
					System.out.println();
				case 9:
					a = sort.insert(in);
					sort.radixSort(a);
					break;
				case 10:
					float[] arr2 = sort.insertFloat(in);
					sort.bucketSort(arr2);
					break;
				default:
					System.out.println("Please enter the valid option!!!");
					break;
			}
			if (a != null) {
				System.out.println("\nDisplay:");
				sort.display(a);
			}
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
		if (a.length == 1) return;

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
		if (n == 1) return;

		/*for (int j = 0; j < n - 1; j++) {
			if (a[j] > a[j + 1]) {
				a = swap(a, j, j + 1);
			}
		}*/

		compare(a, 0, n - 1); // Bubble sort using only recursive function; without iteration
		bubbleSortAlg(a, n - 1);
	}

	void compare(int[] a, int l, int r) {
		if (l >= r) return;
		if (a[l] > a[l + 1]) swap(a, l, l + 1);
		compare(a, l + 1, r);
	}
	// Bubble sort - end

	// Selection - start
	public void selectionSort(int[] a) {
		int minIndex;
		for (int i = 0; i < a.length; i++) {
			minIndex = i;
			for (int j = i; j < a.length; j++) {
				if (a[j] < a[minIndex]) minIndex = j;
			}
			swap(a, minIndex, i);
		}
	}
	// Selection - end

	/* Pancake Sort Algorithm: 
	 *    The idea is to do something similar to Selection Sort. We one by one place maximum element at the end and reduce the size of
	 *  current array by one.
	 *  Following are the detailed steps. Let given array be arr[] and size of array be n.
	 *      1) Start from current size equal to n-1 and reduce current size by one while it’s greater than 0. Let the current size be
	 *         curr_size. Do following for every curr_size
	 *           a) Find index of the maximum element in arr[0..curr_szie-1]. Let the index be ‘mi’
	 *           b) Call flip(arr, mi) 
	 *           c) Call flip(arr, curr_size-1)
	 */
	public int[] pancakeSort(int[] arr) {
		int n = arr.length;
		for (int i = n - 1; i > 0; i--) {
			int maxIndex = findMax(arr, i);
			if (maxIndex == i) continue;
			flip(arr, maxIndex);
			flip(arr, i);
		}
		return arr;
	}

	public int findMax(int[] arr, int end) {
		int i = 1, maxIndex = 0;
		while (i <= end) {
			if (arr[i] > arr[maxIndex]) maxIndex = i;
			i++;
		}
		return maxIndex;
	}

	public void flip(int[] arr, int k) {
		int i = 0;
		while (i < k) {
			Utils.swap(arr, i, k);
			i++;
			k--;
		}
	}

	// Insertion - start
	/*public void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] < a[j])
					swap(a, i, j);
			}
			display(a);
			System.out.println();
		}
	}*/

	// There is no swap in the Insertion sort.
	public void insertionSort(int[] a) {
		int j, lastElement;
		for (int i = 1; i < a.length; i++) {
			lastElement = a[i];
			for (j = i; j > 0 && a[j - 1] > lastElement; j--) {
				a[j] = a[j - 1];
			}
			a[j] = lastElement;
			display(a);
		}
	}

	public void insertionSortRecursive(int[] a) {
		insertionSortAlg(a, a.length - 1);
	}

	private void insertionSortAlg(int[] a, int n) {
		if (n == 0) return;

		insertionSortAlg(a, n - 1);

		int j;
		int lastElement = a[n];
		for (j = n; j > 0 && a[j - 1] > lastElement; j--) {
			a[j] = a[j - 1];
		}
		a[j] = lastElement;
	}
	// Insertion - end

	// Merge sort - start
	/*
	 * Merge sort is a divide-and-conquer algorithm based on the idea of breaking down a list into several sub-lists until each sublist
	 * consists of a single element and merging those sublists in a manner that results into a sorted list.
	 */
	public void mergeSort(int[] a) {
		divide(a, 0, a.length - 1);
	}

	private void divide(int[] a, int l, int r) {
		if (l >= r) return;

		int m = (l + r) / 2;
		divide(a, l, m);
		divide(a, m + 1, r);
		// Merge sub arrays, used 2 arrays
		// merge(a, l, m, r);
		// Merge sub arrays, used 1 array
		merge2(a, l, m, r);

	}

	private void merge(int[] a, int l, int m, int r) {
		int leftSize = m - l + 1; // left to middle
		int rightSize = r - m; // middle+1 to rightIndex
		int[] left = new int[leftSize];
		int[] right = new int[rightSize];

		// Copy the elements index from l to m into left[] array
		for (int i = 0; i < leftSize; i++)
			left[i] = a[l + i];

		// Copy the elements index from m+1 to r into right[] array
		for (int i = 0; i < rightSize; i++)
			right[i] = a[m + 1 + i];

		int leftIndex = 0, rightIndex = 0, curr = l;
		// Merge the elements in asc order
		while (leftIndex < leftSize && rightIndex < rightSize)
			a[curr++] = (left[leftIndex] <= right[rightIndex]) ? left[leftIndex++] : right[rightIndex++];

		// Copy the remaining left side elements into array
		while (leftIndex < leftSize)
			a[curr++] = left[leftIndex++];

		// The right half doesn't need to be copied because it's already there.
		// Copy the remaining right side elements into array
		/*while (rightIndex < rightSize)
			a[curr++] = right[rightIndex++];*/

	}

	private void merge2(int[] a, int l, int m, int r) {
		int[] helper = new int[a.length]; // This helper should move to mergeSort(a); That would be efficient

		for (int i = l; i <= r; i++)
			helper[i] = a[i];

		int helperLeft = l, helperRight = m + 1, curr = l;

		while (helperLeft <= m && helperRight <= r)
			a[curr++] = (helper[helperLeft] <= helper[helperRight]) ? helper[helperLeft++] : helper[helperRight++];

		// Copy the remaining left side elements into array
		while (helperLeft <= m)
			a[curr++] = helper[helperLeft++];

		// The right half doesn't need to be copied because it's already there.
		// Copy the remaining right side elements into array
		/*while (helperRight <= r)
			a[curr++] = helper[helperRight++];*/
	}

	public ListNode mergeSortForLL(ListNode head) {
		return divideLL(head);
	}

	private ListNode divideLL(ListNode head) {
		if (head == null || head.next == null) { return head; }

		ListNode middle = middleNode(head);
		ListNode nextMiddle = middle.next;
		middle.next = null;

		ListNode left = divideLL(head);
		ListNode right = divideLL(nextMiddle);

		ListNode sortedListHead = mergeLL(left, right);
		return sortedListHead;
	}

	private ListNode middleNode(ListNode node) {
		if (node == null) return node;

		ListNode slowPtr = node;
		ListNode fastPtr = node;
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

	private ListNode mergeLL(ListNode left, ListNode right) {
		ListNode result;
		if (left == null) return right;
		if (right == null) return left;

		if (left.data < right.data) {
			result = left;
			result.next = mergeLL(left.next, right);
		} else {
			result = right;
			result.next = mergeLL(left, right.next);
		}
		return result;
	}

	public ListNode insertLL(ListNode head, int data) {
		ListNode newNode = new ListNode(data);
		if (head == null) {
			head = newNode;
		} else {
			ListNode temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = newNode;
		}
		return head;
	}

	public void displayLL(ListNode head) {
		if (head != null) {
			while (head != null) {
				System.out.print(head.data + " ");
				head = head.next;
			}
		}
	}
	// Merge sort - end

	// Quick sort - start
	/*
	 * Quick sort is based on the divide-and-conquer approach based on the idea of choosing one element as a pivot element and 
	 * partitioning the array around it such that: Left side of pivot contains all the elements that are less than the pivot element
	 * Right side contains all elements greater than the pivot
	 */
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
		int i = left, j = left;
		while (j < right) {
			if (a[j] < pivot) {
				swap(a, i, j);
				i++;
			}
			j++;
		}
		swap(a, i, right); // swap pivot element at the end
		return i;
	}
	// Quick sort - end

	// Heap sort - start
	// O(n) time for buildHeap and O(n log n) to remove each node in order, so the
	// complexity is O(n log n).
	public void heapSort(int[] a) {
		int size = a.length;
		// Build heapify the given array - O(n) time for buildHeap
		for (int i = (size / 2) - 1; i >= 0; i--)
			maxHeapify(a, i, size);

		// One by one extract an element from heap
		for (int i = size - 1; i >= 0; i--) { // Time complexity: maxheapify for n times, its o(nlog(n))
			a = swap(a, 0, i); // Move max element (current root) to end
			maxHeapify(a, 0, i); // call max heapify on the reduced heap
		}
	}

	// Time complexity for maxheapify is o(log(n))
	private void maxHeapify(int[] a, int startIndex, int size) {
		int left = 2 * startIndex + 1; // left child
		int right = 2 * startIndex + 2; // right child
		int largest = startIndex; // Initialize largest as root

		if (left < size && a[left] > a[largest]) // Check left child with parent
			largest = left;

		if (right < size && a[right] > a[largest]) // Check right child with parent
			largest = right;

		if (largest != startIndex) { // Swap if largest element index changes
			a = swap(a, largest, startIndex);
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

	public float[] insertFloat(Scanner in) {
		System.out.println("Enter no of elements to be inserted(Float):");
		int t = in.nextInt();
		float[] a = new float[t];
		for (int i = 0; i < t; i++)
			a[i] = in.nextFloat();
		return a;
	}

	public void display(int[] a) {
		Arrays.stream(a).forEach(ele -> System.out.print(ele + " "));
	}

	/*
	 * The counting sort is used if you just need to sort a list of integers. Rather than using a comparison, you create an integer array
	 *  whose index range covers the entire range of values in your array to sort. Each time a value occurs in the original array, you 
	 *  increment the counter at that index. At the end, run through your counting array, printing the value of each non-zero valued index
	 *  that number of times.
	 *  
	 *  The array arr[] is traversed in n time and the resulting sorted array is also computed in O(n) time.  count array is traversed 
	 *  in O(k) time. Therefore, the overall time complexity of counting sort algorithm is O(n+k).	
	 */

	public int[] countingSort(int[] arr, int capacity) {
		int[] countArray = new int[capacity]; // Here you can find the max vaue initialize the array using that value
		int count;
		// Count the elements int the array and set into new array; O(n) time
		for (int i = 0; i < arr.length; i++)
			countArray[arr[i]]++;

		// Arrange elements based on count array; O(k) time
		int index = 0;
		for (int i = 0; i < countArray.length; i++) {
			count = countArray[i];
			while (count-- > 0)
				arr[index++] = countArray[i];
		}

		return arr;
	}

	static char[] countingSort(char[] arr) {
		int[] countArray = new int[256];
		int count;
		for (int i = 0; i < arr.length; i++)
			countArray[arr[i]]++;

		int index = 0;
		for (int i = 0; i < countArray.length; i++) {
			count = countArray[i];
			while (count-- > 0)
				arr[index++] = (char) i;

		}
		return arr;
	}

	/*
	 * The lower bound for Comparison based sorting algorithm (Merge Sort, Heap Sort, Quick-Sort .. etc) is O(nLogn), 
	 * i.e., they cannot do better than nLogn.
	 * Counting sort is a linear time sorting algorithm that sort in O(n+k) time when elements are in range from 1 to k.
	 * What if the elements are in range from 1 to n2? We can’t use counting sort because counting sort will take O(n2) which
	 * is worse than comparison based sorting algorithms. 
	 * Can we sort such an array in linear time? Radix Sort is the answer. 
	 * The idea of Radix Sort is to do digit by digit sort starting from least significant digit to most significant digit. 
	 * Radix sort uses counting sort as a subroutine to sort.
	 * 
	 * Time Complexity((n+b) * logb(n)) = O(nlogb(n)); where b- base; Value of b here is 10
	 */
	public void radixSort(int[] arr) {
		// Find the max value from the array
		int max = findMax(arr);

		for (int digit = 1; max / digit > 0; digit *= 10) { // O(logb(n) times
			countSort(arr, digit); // Counting sort takes O(n+b) time
			System.out.println("Counting sort (digits from LSB) : " + Arrays.toString(arr));
		}
	}

	// Count sort for radix sort
	void countSort(int arr[], int digit) {
		int[] freq = new int[10]; // Frequency Array capacity always be 10 for the values 0 to 9;
		int n = arr.length;
		int[] temp = new int[n];

		// Count the array values
		for (int i = 0; i < n; i++)
			freq[(arr[i] / digit) % 10]++;

		// Change freq[i] so that freq[i] now contains actual position of this digit in temp[] array
		for (int i = 1; i < 10; i++)
			freq[i] += freq[i - 1];

		// Arrange the element based on the index in the freq[] array
		for (int i = n - 1; i >= 0; i--) {
			int index = (arr[i] / digit) % 10;
			temp[freq[index] - 1] = arr[i];
			freq[index]--;
		}

		// Finally copy the value from temp[] to arr[]
		for (int i = 0; i < n; i++)
			arr[i] = temp[i];
	}

	public int findMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++)
			max = Math.max(max, arr[i]);
		return max;
	}

	/*
	 * Bucket sort is a comparison sort algorithm that operates on elements by dividing them into different buckets and then
	 * sorting these buckets individually. Each bucket is sorted individually using a separate sorting algorithm or by
	 * applying the bucket sort algorithm recursively. Bucket sort is mainly useful when the input is uniformly distributed
	 * over a range. 
	 * For example, consider the following problem. Sort a large set of floating point numbers which are in
	 * range from 0.0 to 1.0 and are uniformly distributed across the range. How do we sort the numbers efficiently?
	 * The average time complexity for Bucket Sort is O(n + k). The worst time complexity is O(n^2). 
	 * The space complexity for Bucket Sort is O(n+k).
	 */
	public void bucketSort(float[] arr) {
		int n = arr.length;
		LinkedList<Float>[] buckets = new LinkedList[n];

		// Insert the elements into the bucket; where n is size of the array
		for (int i = 0; i < n; i++) {
			int key = (int) (arr[i] * n);
			if (buckets[key] == null) buckets[key] = new LinkedList<>();
			buckets[key].add(arr[i]);
		}

		// Sort individual buckets
		for (int i = 0; i < n; i++)
			if (buckets[i] != null) Collections.sort(buckets[i]);

		// Set all the elements back into the arr
		int curr = 0;
		for (int i = 0; i < n; i++) {
			if (buckets[i] != null) {
				ListIterator<Float> iter = buckets[i].listIterator();
				while (iter.hasNext())
					arr[curr++] = iter.next();
			}
		}

		System.out.println("After Sorting: ");
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}

	static void quickSort1(int[] a, int left, int right) {
		if (left < right) {
			int mid = partition1(a, left, right);
			quickSort1(a, left, mid - 1);
			quickSort1(a, mid + 1, right);
		}
	}

	static int partition1(int[] a, int left, int right) {
		int j = left, pivot = a[right];
		for (int i = left; i < right; i++) {
			if (a[i] < pivot) {
				swap1(a, i, j);
				j++;
			}
		}
		swap1(a, right, j);
		return j;
	}

	private static int[] swap1(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}

	static int findMedian(int[] arr) {
		int n = arr.length - 1;
		quickSort1(arr, 0, n);
		for (int i = 0; i <= n; i++)
			System.out.print(arr[i] + " ");
		return arr[n / 2];
	}
}
