package com.geeks.problem.algorithms;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.common.model.ListNode;
import com.common.utilities.Utils;

public class SortingAlgorithms {

	/*************************** Java API Sorting Algorithms *********************/

	/*************************** Sorting Algorithms *********************/
	// Bubble sort - start
	public static void bubbleSort(int[] a) {
		boolean swapFlag;
		int count = 0;
		if (a.length == 1) return;

		for (int i = 1; i < a.length; i++) {
			swapFlag = false;
			count++;
			for (int j = 0; j < a.length - i; j++) {
				if (a[j] > a[j + 1]) {
					Utils.swap(a, j, j + 1);
					swapFlag = true;
				}
			}
			// System.out.println("No of iterations:" + count);
			if (!swapFlag) {
				System.out.println("No swapping, i.e array sorted");
				break;
			}
		}
	}

	public static void bubbleSortRecursive(int[] a) {
		bubbleSortAlg(a, a.length);
	}

	private static void bubbleSortAlg(int[] a, int n) {
		if (n == 1) return;

		/*for (int j = 0; j < n - 1; j++) {
			if (a[j] > a[j + 1]) {
				a = swap(a, j, j + 1);
			}
		}*/

		compare(a, 0, n - 1); // Bubble sort using only recursive function; without iteration
		bubbleSortAlg(a, n - 1);
	}

	private static void compare(int[] a, int l, int r) {
		if (l >= r) return;
		if (a[l] > a[l + 1]) Utils.swap(a, l, l + 1);
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
			Utils.swap(a, minIndex, i);
		}
	}
	// Selection - end

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
			for (j = i; j > 0 && a[j - 1] > lastElement; j--)
				a[j] = a[j - 1];

			a[j] = lastElement;
			// display(a);
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

		while (i < leftSize)
			a[k++] = left[i++];

		while (j < rightSize)
			a[k++] = right[j++];

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

		return mergeLL(left, right);
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
				if (i != partitionIndex) // Optional cond: If both are same index, it doesnt make any sense to swap
					Utils.swap(a, partitionIndex, i);
				partitionIndex++;
			}
		}

		Utils.swap(a, partitionIndex, right); // swap pivot element at the end
		return partitionIndex;
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
	// Quick sort - end

	// Heap sort - start
	// O(n) time for buildHeap and O(n log n) to remove each node in order, so the complexity is O(n log n).
	public void heapSort(int[] a) {
		int size = a.length;
		// Build heapify the given array - O(n) time for buildHeap
		for (int i = (size / 2) - 1; i >= 0; i--)
			maxHeapify(a, i, size);
		// One by one extract an element from heap
		for (int i = size - 1; i >= 0; i--) { // Time complexity: maxheapify for n times, its o(nlog(n))
			// Move max element (current root) to end
			Utils.swap(a, 0, i);
			// call max heapify on the reduced heap
			maxHeapify(a, 0, i);
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
			Utils.swap(a, largest, startIndex);
			maxHeapify(a, largest, size);
		}
	}

	/*private static int[] swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}*/
	// Heap sort - end

	public int[] insert(Scanner in) {
		System.out.println("Enter no of elements to be inserted:");
		int t = in.nextInt();
		int[] a = new int[t];
		for (int i = 0; i < t; i++)
			a[i] = in.nextInt();
		return a;
	}

	public static void display(int[] a) {
		Arrays.stream(a).forEach(ele -> System.out.print(ele + " "));
	}

	/*
	 * The counting sort is used if you just need to sort a list of integers. Rather than using a comparison, you create an integer array
	 *  whose index range covers the entire range of values in your array to sort. Each time a value occurs in the original array, you 
	 *  increment the counter at that index. At the end, run through your counting array, printing the value of each non-zero valued index
	 *  that number of times.
	 */
	public int[] countingSort(int[] arr) {
		int[] countArray = new int[100];
		int count;
		// Count the elements int the array and set into new array
		for (int i = 0; i < arr.length; i++)
			countArray[arr[i]]++;

		// Arrange elements based on count array
		int k = 0;
		for (int i = 0; i < countArray.length; i++) {
			count = countArray[i];
			if (count != 0) {
				if (count > 1) {
					for (int j = 0; j < count; j++)
						arr[k++] = i;
				} else {
					arr[k++] = i;
				}
			}
		}

		return arr;
	}

	public char[] countingSort(char[] arr) {
		int[] countArray = new int[256];
		int count;
		for (int i = 0; i < arr.length; i++) {
			countArray[arr[i]]++;
			// System.out.print((int) arr[i] + " ");
		}

		int k = 0;
		for (int i = 0; i < countArray.length; i++) {
			count = countArray[i];
			if (count != 0) {
				if (count > 1) {
					for (int j = 0; j < count; j++) {
						arr[k] = (char) i;
						k++;
					}
				} else {
					arr[k] = (char) i;
					k++;
				}
			}
		}
		return arr;
	}

	/*************************** Coding Problems *********************/

	/*Sort an array in wave form or Peaks and Valleys: 
	 * Given an unsorted array of integers, sort the array into a wave like array. An array ‘arr[0..n-1]’ is sorted in wave form 
	 * if arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] >=
	 */
	// Approach1: Using Sorting - Time Complexity: O(nlogn)
	public void waveForm1(int[] arr) {
		Arrays.sort(arr);
		for (int i = 0; i < arr.length - 1; i += 2)
			Utils.swap(arr, i, i + 1);
	}

	// Approach2: Time Complexity: O(n)
	public void waveForm2(int[] arr) {
		for (int i = 1; i < arr.length; i += 2) {
			if ((i % 2 == 1 && arr[i - 1] < arr[i]) || (i % 2 == 0 && arr[i - 1] > arr[i])) Utils.swap(arr, i - 1, i);
		}
	}

	/*
	 * Wiggle Sort or Convert array into Zig-Zag fashion: 
	 * Given an array of distinct elements, rearrange the elements of array in zig-zag fashion in O(n) time. 
	 * The converted array should be in form a < b > c < d > e < f.
	 */
	public void wiggleSort1(int[] arr) {
		int n = arr.length;
		if (n < 1) return;

		boolean flag = true;
		for (int i = 0; i < n - 1; i++) {
			if (flag) { // odd element should be less than next element
				if (arr[i] > arr[i + 1]) // swap only if greater than next element
					Utils.swap(arr, i, i + 1);
			} else {// even element should be less than next element
				if (arr[i] < arr[i + 1])// swap only if lesser than next element
					Utils.swap(arr, i, i + 1);
			}
			flag = !flag;
		}

		// Print the result
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}

	/*
	 * Wiggle Sort II:
	 *   Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
	 */
	public void wiggleSort2(int[] nums) {
		int median = findKthLargest(nums, (nums.length + 1) / 2);
		int n = nums.length;

		System.out.println("After Finding Median: " + Arrays.toString(nums));
		int left = 0, i = 0, right = n - 1;

		while (i <= right) {
			System.out.println(
					i + "- i: " + newIndex(i, n) + " Left: " + newIndex(left, n) + " Right: " + newIndex(right, n));
			if (nums[newIndex(i, n)] > median) {
				Utils.swap(nums, newIndex(left++, n), newIndex(i++, n));
			} else if (nums[newIndex(i, n)] < median) {
				Utils.swap(nums, newIndex(right--, n), newIndex(i, n));
			} else {
				i++;
			}
		}

	}

	private int newIndex(int index, int n) {
		return (1 + 2 * index) % (n | 1);
	}

	private int findKthLargest(int[] nums, int k) {

		k = nums.length - k;
		int lo = 0;
		int hi = nums.length - 1;
		while (lo < hi) {
			final int j = partition2(nums, lo, hi);
			if (j < k) {
				lo = j + 1;
			} else if (j > k) {
				hi = j - 1;
			} else {
				break;
			}
		}
		return nums[k];
	}

	private int partition2(int[] nums, int left, int right) {

		int i = left;
		int j = right + 1;
		while (true) {
			while (i < right && nums[++i] < nums[left])
				;
			while (j > left && nums[left] < nums[--j])
				;
			if (i >= j) {
				break;
			}
			Utils.swap(nums, i, j);
		}
		Utils.swap(nums, left, j);
		return j;
	}

	/*Alternative Sorting:
	 * Given an array of integers, print the array in such a way that the first element is first maximum and second element 
	 * is first minimum and so on.
	 */
	public void alternativeSorting(int[] arr) {
		Arrays.sort(arr);
		int l = 0, h = arr.length - 1;
		while (l < h) {
			System.out.print(arr[h--] + " ");
			System.out.print(arr[l++] + " ");
		}
	}

	public void relativeSorting(int[] a1, int[] a2) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < a1.length; i++)
			map.put(a1[i], map.getOrDefault(a1[i], 0) + 1);

		int[] result = new int[a1.length];
		int index = 0;
		for (int i = 0; i < a2.length; i++) {
			if (map.get(a2[i]) != null) {
				Integer count = map.get(a2[i]);
				while (count-- > 0)
					result[index++] = a2[i];
				map.remove(a2[i]);
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Integer count = map.get(entry.getKey());
			while (count-- > 0)
				result[index++] = entry.getKey();
		}

		System.out.println(Arrays.toString(result));
	}

}