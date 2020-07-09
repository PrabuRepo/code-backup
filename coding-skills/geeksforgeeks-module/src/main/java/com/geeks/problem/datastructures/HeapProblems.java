package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

import com.common.model.Cell;
import com.common.model.ListNode;
import com.common.model.TreeNode;
import com.common.utilities.Utils;

public class HeapProblems {
	/************************ Basics ***********************/
	/**** Heap Operation ****/
	/*Complexity: O(n). 
	 * max_heapify function has complexity O(logn) and the build_maxheap functions runs only n/2 times, but the amortized 
	 * complexity for this function is actually linear.*/
	private void buildMaxBinaryHeap(int[] a) {
		int n = a.length;
		for (int i = (n / 2) - 1; i >= 0; i--) {
			maxHeapify(a, n - 1, i);
		}
	}

	/*Complexity: O(n). 
	 * max_heapify function has complexity O(logn) and the build_maxheap functions runs only n/2 times, but the amortized 
	 * complexity for this function is actually linear.*/
	private void buildMinBinaryHeap(int[] a) {
		int n = a.length;
		for (int i = (n / 2) - 1; i >= 0; i--) {
			minHeapify(a, n - 1, i);
		}
	}

	// Time Complexity: O(logn)
	private void maxHeapify(int[] a, int n, int startIndex) {
		int left = 2 * startIndex + 1;
		int right = 2 * startIndex + 2;
		int maxIndex = startIndex;

		if (left <= n && a[maxIndex] < a[left]) maxIndex = left;

		if (right <= n && a[maxIndex] < a[right]) maxIndex = right;

		if (maxIndex != startIndex) {
			Utils.swap(a, startIndex, maxIndex);
			maxHeapify(a, n, maxIndex);
		}
	}

	// Time Complexity: O(logn)
	private void minHeapify(int[] a, int n, int startIndex) {
		int left = 2 * startIndex + 1;
		int right = 2 * startIndex + 2;
		int minIndex = startIndex;

		if (left <= n && a[minIndex] > a[left]) minIndex = left;

		if (right <= n && a[minIndex] > a[right]) minIndex = right;

		if (minIndex != startIndex) {
			Utils.swap(a, startIndex, minIndex);
			minHeapify(a, n, minIndex);
		}
	}

	private int extractMax(int[] a, int n) {
		int max = a[0];
		Utils.swap(a, n, 0);
		maxHeapify(a, n - 1, 0);
		return max;
	}

	private int extractMin(int[] a, int n) {
		int min = a[0];
		Utils.swap(a, n, 0);
		minHeapify(a, n - 1, 0);
		return min;
	}

	// HeapSort - Starts
	/*
	 * Time Complexity: O(nlogn)
	 * As we know max/min heapify has complexityO(logn), build max/min heap has complexity O(n)  and we run max/min heapify for n-1
	 * times in heapSort() function, therefore complexity of heapSort function is O(nlogn) 
	 */
	public void heapSort(int[] a, boolean flag) {
		if (flag) heapSortAsc(a);
		else heapSortDesc(a);
		// System.out.println("Result: " + Arrays.toString(a));
	}

	public void heapSortAsc(int[] a) {
		int n = a.length;
		// Build max binary heap
		buildMaxBinaryHeap(a);

		/*Keep moving the max element to the last and apply heap property for remaining elements*/
		for (int i = n - 1; i > 0; i--) {
			Utils.swap(a, 0, i);
			maxHeapify(a, i - 1, 0);
		}
	}

	public void heapSortDesc(int[] a) {
		int n = a.length;
		// Build min binary heap
		buildMinBinaryHeap(a);

		/*Keep moving the max element to the last and apply heap property for remaining elements*/
		for (int i = n - 1; i > 0; i--) {
			Utils.swap(a, 0, i);
			minHeapify(a, i - 1, 0);
		}
	}

	// HeapSort - ends
	/************************ Heap Problems ***********************/
	// K largest elements in the array: Write an efficient program for printing k largest elements in an array.
	// Approach1: Using bubble sort; Time complexity: O(nk)
	public void kLargestElementsInArray1(int[] a, int k) {
		for (int i = 0; i < k; i++) {
			for (int j = a.length - 1; j > 0; j--) {
				if (a[j - 1] < a[j]) Utils.swap(a, j, j - 1);
			}
		}
		for (int i = 0; i < k; i++)
			System.out.print(a[i] + " ");
	}

	// Approach2: Use Sorting: Time complexity: O(nlogn).
	public void kLargestElementsInArray2(int[] a, int k) {
		Arrays.sort(a);

		int n = a.length;
		for (int i = n - 1; i >= n - k; i--)
			System.out.print(a[i] + " ");
	}

	// Approach31: Using Max Binary Heap; Time complexity: O(n + klogn)
	public void kLargestElementsInArray31(int[] a, int k) {
		// Build Max Binary Heap
		buildMaxBinaryHeap(a); // O(n)

		// Extract Max
		int n = a.length - 1;
		for (int i = 0; i < k; i++) // O(klogn)
			System.out.print(extractMax(a, n--) + " ");
	}

	// Approach32: Using Min Binary Heap and temp array; Time complexity: O(k + (n-k)Logk); Ignore this approach
	public void kLargestElementsInArray32(int[] a, int k) {
		int[] largestElements = new int[k];
		for (int i = 0; i < k; i++)
			largestElements[i] = a[i];

		buildMinBinaryHeap(largestElements);

		for (int i = k; i < a.length; i++) {
			if (a[i] > largestElements[0]) replaceMin(largestElements, a[i]);
		}

		int size = k - 1;
		for (int i = 0; i < k; i++)
			System.out.print(extractMin(largestElements, size--) + " ");
	}

	/*
	 * Using Quick sort Partitioning: Worst Case Linear Time complexity: O(n) 
	 */
	public int kthLargestElementsInArray31(int[] nums, int k) {
		if (nums.length == 0 || k == 0) return 0;

		int l = 0, r = nums.length - 1;

		while (l <= r) {
			int index = reversePartition(nums, l, r);

			if (index == k - 1) return nums[index];
			else if (index < k - 1) l = index + 1;
			else r = index - 1;
		}

		return -1;
	}

	// K’th Smallest Element in Unsorted Array
	// Approach1:Sort the given array using a sorting algorithm and return the element at index k-1 in the sorted array.
	// Time Complexity: O(nLogn)
	public int kthSmallestElementInArray1(int[] a, int k) {
		Arrays.sort(a);
		return a[k - 1];
	}

	// Approach2: Using Max Binary Heap: Time Complexity-O(nlogk)
	public int kthSmallestElementInArray21(int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++) {// O(nlogk) times
			if (queue.isEmpty() || queue.size() < k) {
				queue.add(arr[i]);
			} else if (arr[i] < queue.peek()) {
				queue.remove();
				queue.add(arr[i]);
			}
		}
		return queue.peek();
	}

	// Approach2: Using Min Binary Heap: Time Complexity-O(n+klogn)
	public int kthSmallestElementInArray22(int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) // O(n) Time to build the heap
			queue.add(arr[i]);

		for (int i = 0; i < k - 1; i++) // O(k.logn) time
			queue.remove();

		return queue.peek();
	}

	/*
	 * Using Quick sort Partitioning or Quick Select: Expect Linear Time complexity: O(n) 
	 * K’th Smallest Element in Unsorted Array -  
	 *   Partition or Quick Select: The partition subroutine of quicksort can also be used to solve this problem. 
	 *  In partition, we divide the array into elements>=pivot pivot elements<=pivot. Then, according to the index of pivot,
	 *  we will know whther the kth largest element is to the left or right of pivot or just itself. In average, this
	 *  algorithm reduces the size of the problem by approximately one half after each partition, giving the 
	 *  recurrence T(n) = T(n/2) + O(n) with O(n) being the time for partition. The solution is T(n) = O(n), which means 
	 *  we have found an average linear-time solution. However, in the worst case, the recurrence will become 
	 *  T(n) = T(n - 1) + O(n) and T(n) = O(n^2).
	 */
	// This is simpler than kthSmallestElementInArray32
	public int kthSmallestElementInArray31(int[] nums, int k) {
		if (nums.length == 0 || k == 0) return 0;

		int l = 0, r = nums.length - 1;

		while (l <= r) {
			int index = partition(nums, l, r); // Here partition being invoked all the condition

			if (index == k - 1) return nums[index];
			else if (index < k - 1) l = index + 1;
			else r = index - 1;
		}

		return -1;
	}

	/* Partition:
	 * Left side elements are less than pivotIndex(i) and right side elements are greater than pivotIndex(i)
	 * Use Partition to find the kth Smallest Element; 
	 */
	public int partition(int[] a, int left, int right) {
		int i = left, j = left, pivot = a[right];
		while (j < right) {
			if (a[j] < pivot) {
				Utils.swap(a, i, j);
				i++;
			}
			j++;
		}
		Utils.swap(a, i, right);
		return i;
	}

	/* Reverse Partition:
	 * Left side elements are greater than pivotIndex(i) and right side elements are less than pivotIndex(i)
	 * Use reverse Partition to find the kth Largest Element;
	 */
	private int reversePartition(int[] a, int left, int right) {
		int pivot = a[right];
		int i = left, j = left;
		while (j < right) {
			if (a[j] > pivot) { // Here swap only element greater than pivot
				Utils.swap(a, i, j);
				i++;
			}
			j++;
		}
		Utils.swap(a, i, right); // swap pivot element at the end
		return i;
	}

	public int randomPartition(int arr[], int l, int r) {
		int pivot = (int) (Math.random()) * (r - l);
		Utils.swap(arr, l + pivot, r);
		return partition(arr, l, r);
	}

	/*
	 * Using Quick sort Partitioning: Worst Case Linear Time complexity: O(n) 
	 * Steps to be followed: 
	 *   1) Divide arr[] into n/5 groups where size of each group is 5 except possibly the last group which may have less than 5 elements.  
	 *   2) Sort the above created n/5 groups and find median of all groups. Create an auxiliary array 'median[]' and store medians
	 *      of all n/5 groups in this median array.
	 *   3) medOfMed = kthSmallest(median[0..n/5-1], n/10). Recursively call this method to find median of median[0..n/5-1] 
	 *   4) Partition arr[] around medOfMed and obtain its position. pos = partition(arr, n, medOfMed)
	 *   5) If pos == k return medOfMed
	 *   6) If pos > k return kthSmallest(arr[l..pos-1], k)
	 *   7) If pos < k return kthSmallest(arr[pos+1..r], k-pos+l-1)
	 **/
	public int kthSmallestElementInArray32(int[] a, int k) {
		if (a.length == 0 || a.length < k) return 0;
		return quickSelect(a, 0, a.length - 1, k);
	}

	public int quickSelect(int[] arr, int left, int right, int k) {
		int pivotIndex = partition(arr, left, right);
		// int pivotIndex = randomPartition(arr, left, right);
		int len = pivotIndex - left + 1;

		if (len == k) {
			return arr[pivotIndex];
		} else if (len > k) { // Here quickSelect method is invoked with modifying the k
			return quickSelect(arr, left, pivotIndex - 1, k);
		} else {
			return quickSelect(arr, pivotIndex + 1, right, k - len);
		}
	}

	// Kth largest elements in the stream
	/*
	  Approach1: Simple Solution-> using array size of k. 
	             Time complexity-> Element found in O(1) time, processing a new element is O(k) time
	  Approach2: Better Solution -> Self Balancing Binary Search Tree of size k.
	  			 Time complexity-> Element found in O(logk) time, processing a new element is O(logk) time
	  Approach3: Efficient Solution using Binary MinHeap size of k. 
	             Time complexity-> Element found in O(1) time, processing a new element is O(logk) time
	*/
	public void kthLargestElementInStream1(int[] a, int k) {
		PriorityQueue<Integer> pQueue = new PriorityQueue<>();

		for (int i = 0; i < a.length; i++) {
			pQueue.add(a[i]);
			if (pQueue.size() > k) pQueue.poll();
			System.out.print((pQueue.size() < k) ? -1 : pQueue.peek() + " ");
		}
	}

	// Both are same logic, but below logic saves unnecessary insert operation
	public void kthLargestElementInStream2(int[] a, int k) {
		PriorityQueue<Integer> pQueue = new PriorityQueue<>();
		for (int i = 0; i < a.length; i++) {
			if (pQueue.size() < k) pQueue.add(a[i]);
			else {
				if (a[i] > pQueue.peek()) {
					pQueue.poll();
					pQueue.add(a[i]);
				}
			}
			System.out.print((pQueue.size() < k) ? -1 : pQueue.peek() + " ");
		}
	}

	// Merge k sorted arrays:
	/* 
	 * 1.BruteForce Approach: A simple solution is to create an output array of size n*k and one by one copy all
	 * arrays to it. Finally, sort the output array using any O(nLogn) sorting algorithm.
	 * This approach takes O(NlogN) time, where N=nk, k - no of arrays; n - no of elements in each array
	 */
	public int[] mergeKSortedArrays1(int[][] arr) {
		int k = arr.length, n = arr[0].length;
		// Assuming equal size array
		int[] output = new int[n * k];
		int index = 0;
		// Copy all the elements in ouput array
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n; j++) {
				output[index++] = arr[i][j];
			}
		}
		// Sort
		Arrays.sort(output);
		System.out.println("After Merge: " + Arrays.toString(output));
		return output;
	}

	// Merge Sorted Arrays using PriorityQueue;
	// Time Complexity: O(Nlogk) where N=nk, k - no of arrays; n - no of elements in each array
	public int[] mergeKSortedArrays2(int[][] arr) {
		int size = 0;
		PriorityQueue<Cell> queue = new PriorityQueue<>((a, b) -> a.data - b.data);
		for (int i = 0; i < arr.length; i++) {
			queue.add(new Cell(i, 0, arr[i][0]));
			// To count the no elements, if it diff size array; otherwise directly calculate from input
			size += arr[i].length;
		}

		int[] result = new int[size];
		int index = 0;
		while (!queue.isEmpty()) {
			Cell curr = queue.poll();
			result[index++] = curr.data;
			if (curr.j < arr[curr.i].length - 1) queue.add(new Cell(curr.i, curr.j + 1, arr[curr.i][curr.j + 1]));
		}

		System.out.println("After merge:" + Arrays.toString(result));
		return result;
	}

	// merge arrays in O(nk*Logk) time using Min Heap Own Implementation
	public int[] mergeKSortedArrays3(int[][] arr) {
		int k = arr.length, n = arr[0].length;
		// Create an output array of size n*k.
		int[] output = new int[n * k];
		int[] temp = new int[k];
		int count = 0;

		/*Create a min heap of size k and insert 1st element in all the arrays into the heap*/
		for (int i = 0; i < k; i++)
			temp[i] = arr[i][0];

		// Build Minimum Binary heap
		buildMinBinaryHeap(temp);

		// Repeat following steps n*k times.
		for (int j = 1; j < n; j++) {
			for (int i = 0; i < k; i++) {
				/*a) Get minimum element from heap (minimum is always at root) and store it in output array.
				b) Replace heap root with next element from the array from which the element is extracted.*/
				output[count++] = replaceMin(temp, arr[i][j]);
			}
		}

		/*If the array doesn’t have any more elements, then replace root with infinite. After replacing the root, heapify the tree.*/
		while (count < n * k)
			output[count++] = replaceMin(temp, Integer.MAX_VALUE);

		System.out.println("After merge:" + Arrays.toString(output));
		return output;
	}

	private int replaceMin(int[] a, int x) {
		int minElement = a[0];
		a[0] = x;
		minHeapify(a, a.length - 1, 0);
		return minElement;
	}

	// Merge K sorted linked lists;
	/* Linear Merge Algorithm: Merge the List one by one 
	 * Time Complexity: O(Nk) where N = nk; k = no of linked list; n = no of elements in the list
	 */
	public ListNode mergeKSortedLinkedList1(ListNode[] lists) {
		int k = lists.length;
		if (k == 0) return null;

		ListNode result = null;
		for (int i = 0; i < k; i++)
			result = merge(result, lists[i]);
		return result;
	}

	public ListNode merge(ListNode head1, ListNode head2) {
		if (head1 == null) return head2;
		if (head2 == null) return head1;

		ListNode result = null;
		if (head1.data < head2.data) {
			result = head1;
			result.next = merge(head1.next, head2);
		} else {
			result = head2;
			result.next = merge(head1, head2.next);
		}
		return result;
	}

	// Using Min Heap: O(NLogk); where N = nk; k = no of linked list; n = no of elements in the list
	public ListNode mergeKSortedLinkedList2(ListNode[] nodes, int k) {
		if (k == 0) return null;
		if (k == 1) return nodes[0];
		PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.data - o2.data);
		for (int i = 0; i < k; i++)
			if (nodes[i] != null) queue.add(nodes[i]);

		ListNode dummy = new ListNode(0);
		ListNode temp = dummy;
		while (!queue.isEmpty()) {
			ListNode curr = queue.poll();
			// Add next val in the queue
			if (curr.next != null) queue.add(curr.next);
			temp.next = curr;
			temp = temp.next;
		}
		return dummy.next;
	}

	// Apply Merge K List Algorithm
	public int[] smallestRange(List<List<Integer>> nums) {
		int[] result = new int[2];
		if (nums.size() == 0) return result;

		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]); // i,j,val
		int max = Integer.MIN_VALUE, minRange = Integer.MAX_VALUE;

		// Set the first value in entire list
		for (int i = 0; i < nums.size(); i++) {
			max = Math.max(max, nums.get(i).get(0));
			queue.add(new int[] { i, 0, nums.get(i).get(0) });
		}

		while (queue.size() == nums.size()) {
			int[] curr = queue.poll();
			int i = curr[0], j = curr[1], min = curr[2];

			// update the result
			if (max - min < minRange) {
				result[0] = min;
				result[1] = max;
				minRange = max - min;
			}

			// Check next value from the top element
			j++;

			if (nums.get(i) != null && j >= nums.get(i).size()) continue;

			int nextVal = nums.get(i).get(j);
			max = Math.max(max, nextVal);

			queue.add(new int[] { i, j, nextVal });
		}

		return result;
	}

	/*
	 * Find top k (or most frequent) numbers in a stream:
	 *  Given an array of n numbers. Your task is to read numbers from the array and keep at-most K numbers at the top (According 
	 *  to their decreasing frequency) every time a new number is read
	 */
	// Approach1: Using Hashmap & Heap; Time Complexity: O(nlogk)
	public List<Integer> topKFrequent1(int[] nums, int k) {
		int n = nums.length;
		if (n == 0 || k == 0) return null;

		// Count the frequency of elements
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

		// Create a min Heap based on count(freq of elements)
		PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

		// Insert the elements one by one in queue and maintain k elements in PQ
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			queue.add(entry);
			if (queue.size() > k) queue.poll();
		}

		// Insert the queue values in result
		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty())
			result.add(queue.poll().getKey());

		Collections.reverse(result);

		// result.stream().forEach(i -> System.out.print(i + " "));
		return result;
	}

	// Approach2: Using Hashmap & Bucket Sort; Time Complexity: O(n)
	public List<Integer> topKFrequent2(int[] nums, int k) {
		int n = nums.length;
		if (n == 0 || k == 0) return null;

		// Count the frequency of elements
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

		// get the max frequency
		int max = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet())
			max = Math.max(max, entry.getValue());

		// Bucket Sorting
		ArrayList<Integer>[] buckets = new ArrayList[max + 1];
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (buckets[entry.getValue()] == null) buckets[entry.getValue()] = new ArrayList<>();
			buckets[entry.getValue()].add(entry.getKey());
		}

		List<Integer> result = new ArrayList<Integer>();

		// add most frequent numbers to result
		for (int i = max; i >= 1 && result.size() < k; i--) { // Start from max value
			if (buckets[i] != null && buckets[i].size() > 0) {
				// If there is more than one element in the same count
				for (int a : buckets[i]) {
					if (result.size() == k) // if size==k, stop
						break;
					result.add(a);
				}
			}
		}

		// result.stream().forEach(i -> System.out.print(i + " "));
		return result;
	}

	/* Check Solution using Functional Programming:
	// initialize
	Map<Character, Integer> histogram = new HashMap<>();
	histogram.put( 'c', 10 );
	histogram.put( 'a', 12 );
	histogram.put( 'b', 6 );
	// output according to priority queue
	Queue<Map.Entry<Character, Integer>> maxQueue = new PriorityQueue<>( ( o1, o2 ) ->  o2.getValue() - o1.getValue() );
	maxQueue.addAll( histogram.entrySet() );
	return maxQueue.stream()
	               .sorted( ( o1, o2 ) -> ( o2.getValue() - o1.getValue() ) )
	               .limit( k )
	               .map( o -> o.getKey() )
	               .collect( Collectors.toList() );*/

	/*
	 * Sort Characters By Frequency: 
	 *  	Given a string, sort it in decreasing order based on the frequency of characters.
	 */
	// 1.Using Heap: Time Complexity: O(nlogm); where m - distinct chars; n- no of chars
	public String stringFrequencySort1(String s) {
		int n = s.length();
		if (n <= 1) return s;

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int count = map.getOrDefault(s.charAt(i), 0);
			map.put(s.charAt(i), count + 1);
		}

		PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		queue.addAll(map.entrySet());

		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			Map.Entry<Character, Integer> entry = queue.poll();
			for (int i = 0; i < (int) entry.getValue(); i++)
				sb.append(entry.getKey());
		}

		return sb.toString();
	}

	// 2.Using Bucket Sort: Time Complexity: O(n);
	public String stringFrequencySort2(String s) {
		int n = s.length();
		if (n <= 1) return s;

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int count = map.getOrDefault(s.charAt(i), 0);
			map.put(s.charAt(i), count + 1);
		}

		List<Character>[] bucket = new ArrayList[s.length() + 1];
		for (char ch : map.keySet()) {
			int freq = map.get(ch);
			if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
			bucket[freq].add(ch);
		}

		StringBuilder sb = new StringBuilder();
		for (int freq = s.length(); freq > 0; freq--) {
			if (bucket[freq] != null) {
				for (char ch : bucket[freq])
					for (int i = 0; i < freq; i++)
						sb.append(ch);
			}
		}
		return sb.toString();
	}

	/*
	 * Sorting Elements of an Array by Frequency: 
	 *  	Given an array of integers, sort the array according to frequency of elements.
	 */
	// 1.Using Heap: Time Complexity: O(nlogm); where m - distinct elements; n- no of elements
	public int[] arrayFrequencySort1(int[] arr) {
		int n = arr.length;
		if (n <= 1) return arr;

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

		PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		queue.addAll(map.entrySet());

		ArrayList<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			Map.Entry<Integer, Integer> entry = queue.poll();
			for (int i = 0; i < (int) entry.getValue(); i++)
				result.add(entry.getKey());
		}

		// result.stream().forEach(d -> System.out.print(d + " "));
		return result.stream().mapToInt(k -> k).toArray();
	}

	// 2.Using Bucket Sort: Time Complexity: O(n);
	public int[] arrayFrequencySort2(int[] arr) {
		int n = arr.length;
		if (n <= 1) return arr;

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

		List<Integer>[] bucket = new ArrayList[arr.length + 1];
		for (int key : map.keySet()) {
			int freq = map.get(key);
			if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
			bucket[freq].add(key);
		}

		ArrayList<Integer> result = new ArrayList<>();
		for (int freq = arr.length; freq > 0; freq--) {
			if (bucket[freq] != null) {
				for (int elem : bucket[freq])
					for (int i = 0; i < freq; i++)
						result.add(elem);
			}
		}
		return result.stream().mapToInt(k -> k).toArray();
	}

	/*
	 * Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least
	 * distance k from each other. All input strings are given in lower case letters. If it is not possible to rearrange
	 * the string, return an empty string "". 
	 * Example 1: str = "aabbcc", k = 3 Result: "abcabc". The same letters are at least distance 3 from each other.
	 */
	public String rearrangeString(String str, int k) {
		Map<Character, Integer> map = new HashMap<>();
		StringBuilder rearranged = new StringBuilder();

		for (int i = 0; i < str.length(); i++)
			map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);

		PriorityQueue<Map.Entry<Character, Integer>> lower = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		lower.addAll(map.entrySet());

		Queue<Map.Entry<Character, Integer>> waitingQueue = new LinkedList<>();

		while (!lower.isEmpty()) {
			Entry<Character, Integer> entry = lower.poll();
			rearranged.append(entry.getKey());

			entry.setValue(entry.getValue() - 1);
			waitingQueue.add(entry);

			if (waitingQueue.size() < k) continue;

			Entry<Character, Integer> topEntry = waitingQueue.poll();
			if (topEntry.getValue() > 0) lower.add(topEntry);
		}
		return rearranged.length() == str.length() ? rearranged.toString() : "";
	}

	/*Find Median from Data Stream:
	 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
	 * So the median is the mean of the two middle value.
	 * Given that integers are read from a data stream. Find median of elements read so for in efficient way.
	 * Approaches:
	 *    1.Simple Sorting: Store the numbers in a resize-able container. Every time you need to output the median, sort the 
	 *      container and output the median. Time: O(nlogn), Space:O(n)
	 *    2.Insertion Sort & Binary Search: Keeping our input container always sorted. BS is used to find the correct place to 
	 *      insert the incoming number. Time O(logn)+O(n) - O(n); Space:O(n)
	 *    3.Using Two Heaps: Time O(logn); Space:O(n)
	 *    4.Using Balanced BST: Time O(logn); Space:O(n); But little complex to build the Balanced-BST  
	 *         - Try to use using multiset-TreeSet/TreeMap
	 */
	// 1.Simple Sorting:
	public void findMedianInStream1(int[] a) {
	}

	// 2.Insertion Sort & Binary Search:
	public void findMedianInStream2(int[] a) {

	}

	/* Using Two Heap:
	 *  Here we only need a consistent way to access the median elements. Keeping the entire input sorted is not a requirement.
	 *  Heap has direct access to the maximal/minimal elements in a group.If we could maintain two heaps in the following way:
	 *   - A max-heap to store the smaller half of the input numbers
	 *   - A min-heap to store the larger half of the input numbers
	 *  This gives access to median values in the input: they comprise the top of the heaps!
	 *  Time O(logn); Space:O(n)
	 */
	PriorityQueue<Integer>	lower;	// lower/first half of elements & it uses Max Heap
	PriorityQueue<Integer>	upper;	// upper/second half of elements & it uses Min Heap

	public void findMedianInStream3(int[] a) {
		int n = a.length;
		if (n == 0) return;
		lower = new PriorityQueue<>(Collections.reverseOrder());
		upper = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			addNum(a[i]);
			System.out.print(findMedian() + " ");
		}
	}

	/* Sliding Window Median:
	 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the
	 * very right. You can only see the k numbers in the window. Each time the sliding window moves right by one
	 * position. Your job is to output the median array for each window in the original array.
	 */
	/*
	 * Solution:
	 * Use two Heaps to store numbers. lower for numbers smaller than current median, upper for numbers bigger than and 
	 * equal to current median. A small trick I used is always make size of upper equal (when there are even numbers) 
	 * or 1 element more (when there are odd numbers) than the size of lower. Then it will become very easy to calculate 
	 * current median.
	 * Keep adding number from the right side of the sliding window and remove number from left side of the sliding window.
	 * And keep adding current median to the result.
	 * Time Complexity: O(nk); Heap takes k times to remove the element
	 */
	public double[] medianSlidingWindow1(int[] nums, int k) {
		int n = nums.length;
		if (n == 0) return new double[0];

		lower = new PriorityQueue<>(Collections.reverseOrder());
		upper = new PriorityQueue<>();
		double[] result = new double[n - k + 1];
		int j = 0;

		for (int i = 0; i <= n; i++) {
			if (i >= k) {
				result[j++] = findMedian();
				removeNum(nums[i - k]);
			}

			if (i < n) addNum(nums[i]);
		}

		return result;
	}

	public void addNum(int num) {
		// Here first data should be in upper part, to maintain upper > lower
		if (!lower.isEmpty() && num < lower.peek()) lower.add(num);
		else upper.add(num);

		balanceHeap();
	}

	public void removeNum(int num) {
		if (!lower.isEmpty() && num <= lower.peek()) lower.remove(num);
		else upper.remove(num);
		balanceHeap();
	}

	// If size is odd, Upper should have one elements more than Lower
	private void balanceHeap() {
		if (lower.size() > upper.size()) upper.add(lower.poll());

		if (upper.size() - lower.size() > 1) lower.add(upper.poll());
	}

	// Returns the median of current data stream
	public double findMedian() {
		return lower.size() == upper.size() ? ((double) lower.peek() + (double) upper.peek()) * 0.5
				: (double) upper.peek();
	}

	// Below are another approach:
	// Here lower has 1st half of the elements & upper has 2nd half of the elements
	public void addNum2(int num) {
		lower.add(num);
		balance2();
	}

	// Balance the elements b/w two heaps
	private void balance2() {
		upper.add(lower.poll()); // lower always poll the min element in the list of elements;
		if (lower.size() < upper.size()) {
			lower.add(upper.poll()); // upper always poll the max element in the list of elements;
		}
	}

	/* Approach2: Using Treeset
	 *  However instead of using two priority queue's we use two Tree Sets as we want O(logk) for remove(element).
	 * Priority Queue would have been O(k) for remove(element) giving us an overall time complexity of O(nk) instead of
	 * O(nlogk).
	 */
	public double[] medianSlidingWindow2(int[] nums, int k) {
		// Should add index in the set to handle duplicate elements
		Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
		TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
		TreeSet<Integer> right = new TreeSet<>(comparator);

		// Function to find the median
		Supplier<Double> median = (k % 2 == 0) ? () -> ((double) nums[left.first()] + nums[right.first()]) / 2
				: () -> (double) nums[right.first()];

		// Function to balance lefts size and rights size (if not equal then right will be larger by one)
		Runnable balance = () -> {
			if (left.size() > right.size()) right.add(left.pollFirst());
			if (right.size() - left.size() > 1) left.add(right.pollFirst());
		};

		double[] result = new double[nums.length - k + 1];

		for (int i = 0, j = 0; i <= nums.length; i++) {
			if (i >= k) {
				result[j++] = median.get();
				// Remove the elements
				if (!left.isEmpty() && nums[i - k] <= nums[left.first()]) left.remove(i - k);
				else right.remove(i - k);
				balance.run();
			}

			if (i >= nums.length) continue;

			// Add the elements
			if (!left.isEmpty() && nums[i] < nums[left.first()]) left.add(i);
			else right.add(i);
			balance.run();
		}

		return result;
	}

	/* Fraudulent Activity Notifications:
	 * Given the number of trailing days and a client's total daily expenditures for a period of days, find and print
	 * the number of times the client will receive a notification over all days.
	 * Eg: I.p: 2 3 4 2 3 6 8 4 5; o/p:2 
	 */
	public int activityNotifications1(int[] expenditure, int k) {
		int n = expenditure.length;
		if (n == 0) return 0;

		lower = new PriorityQueue<>(Collections.reverseOrder());
		// maxHeap = new PriorityQueue<>((a,b)->(b.compareTo(a)));
		upper = new PriorityQueue<>();

		int count = 0;
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				double median = findMedian();
				if (expenditure[i] >= 2 * median) count++;
				removeNum(expenditure[i - k]);
			}

			addNum(expenditure[i]);
		}

		return count;
	}

	// Approach2: Using Counting Sort
	public int activityNotifications2(int[] expenditure, int k) {
		int n = expenditure.length, count = 0;
		int[] freq = new int[201];
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				double median = findMedian2(freq, k);
				if (expenditure[i] >= 2 * median) count++;
				freq[expenditure[i - k]]--;
			}

			freq[expenditure[i]]++;
		}

		return count;
	}

	public static double findMedian2(int[] freq, int k) {
		int count = 0;
		double median = 0;
		if (k % 2 == 0) {
			int m1 = -1, m2 = -1;
			for (int i = 0; i < freq.length; i++) {
				if (freq[i] == 0) continue;
				count += freq[i];
				if (m1 == -1 && count >= k / 2) m1 = i;
				if (m2 == -1 && count >= (k / 2) + 1) {
					m2 = i;
					break;
				}
			}
			median = (m1 + m2) / 2.0;
		} else {
			for (int i = 0; i < freq.length; i++) {
				if (freq[i] == 0) continue;
				count += freq[i];
				if (count > k / 2) {
					median = i;
					break;
				}
			}
		}

		return median;
	}

	/* Check Binary Heap Tree(Tree data structure):
	 It should be a complete tree (i.e. all levels except last should be full).
	Every node’s value should be greater than or equal to its child node (considering max-heap).*/
	public boolean isBinaryHeap(TreeNode root) {
		if (root == null) return true;
		// Count no of nodes
		int count = BTProblems.sizeOfBinaryTree(root);

		// Check both Complete binary tree property, Max Binary Heap Property
		return isCompleteProperty(root, 0, count) && isMaxBinaryHeap(root);
	}

	// Check Complete property
	private boolean isCompleteProperty(TreeNode root, int index, int count) {
		if (root == null) return true;

		if (index >= count) return false;

		return isCompleteProperty(root.left, (2 * index) + 1, count)
				&& isCompleteProperty(root.right, (2 * index) + 2, count);
	}

	// Check Max Binary Heap Property
	private boolean isMaxBinaryHeap(TreeNode root) {
		if (root.left == null && root.right == null) return true;

		if (root.right == null) return (root.data > root.left.data);

		return (root.data >= root.left.data && root.data >= root.right.data) && isMaxBinaryHeap(root.left)
				&& isMaxBinaryHeap(root.right);
	}

	// To check if a given array represents a Binary Heap(Array)
	// Approach1: Iterative
	public boolean isBinaryHeap1(int[] a) {
		int n = a.length;
		int left = 0, right = 0;
		for (int i = (n / 2) - 1; i >= 0; i--) {
			left = 2 * i + 1;
			right = 2 * i + 2;
			if (left < n && a[i] < a[left] && ((right < n && a[i] < a[right]) || right > n)) return false;
		}
		return true;
	}

	// Approach1: Recursive Approach
	public boolean isBinaryHeap2(int[] a) {
		int i = a.length / 2 - 1;
		return isBinaryHeap2(a, i);
	}

	public boolean isBinaryHeap2(int[] a, int index) {
		if (index < 0) return true;

		int left = 2 * index + 1;
		int right = 2 * index + 2;

		return a[left] < a[index] && (right < a.length && a[right] < a[index] || right >= a.length)
				&& isBinaryHeap2(a, index - 1);
	}

	// Rearrange characters
	public int rerrangeChars(String str) {
		int[] count = new int[26];

		for (int i = 0; i < str.length(); i++) {
			count[str.charAt(i) - 'a']++;
			if (count[str.charAt(i) - 'a'] > (str.length() / 2)) return 0;
		}
		return 1;
	}

	/************************** Misc Problems **********************/

	public String minAbbreviation(String target, String[] dictionary) {
		Set<String> visited = new HashSet<>();
		PriorityQueue<Abbr> q = new PriorityQueue<>((a, b) -> a.len - b.len);
		int len = target.length();
		String first = "";

		for (int i = 0; i < len; i++)
			first += "*";

		q.offer(new Abbr(first, 1));
		while (!q.isEmpty()) {
			Abbr ab = q.poll();
			String abbr = ab.abbr;
			boolean conflict = false;
			for (String word : dictionary) {
				if (word.length() == len && isConflict(abbr, word)) {
					conflict = true;
					break;
				}
			}
			if (conflict) generateAbbr(target, abbr, visited, q);
			else return NumAbbr(abbr);
		}

		return null;
	}

	boolean isConflict(String abbr, String str) {
		for (int i = 0; i < abbr.length(); i++)
			if (abbr.charAt(i) != '*' && str.charAt(i) != abbr.charAt(i)) return false;
		return true;
	}

	void generateAbbr(String str, String abbr, Set<String> visited, PriorityQueue<Abbr> q) {
		char[] temp = abbr.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == '*') {
				temp[i] = str.charAt(i);
				String next = new String(temp);
				if (!visited.contains(next)) {
					q.offer(new Abbr(next, abbrLength(next)));
					visited.add(next);
				}
				temp[i] = '*';
			}
		}
	}

	int abbrLength(String abbr) {
		int ret = 0, star = 0;
		for (char c : abbr.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				ret += 1 + star;
				star = 0;
			} else if (c == '*') {
				star = 1;
			}
		}
		return ret + star;
	}

	/*class CompAbbr implements Comparator<Abbr> {
		public int compare(Abbr s1, Abbr s2) {
			return Integer.compare(s1.len, s2.len);
		}
	}*/

	String NumAbbr(String abbr) {
		String ret = "";
		int count = 0;
		for (char c : abbr.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				if (count > 0) {
					ret += count;
					count = 0;
				}
				ret += c;
			} else {
				count++;
			}
		}
		if (count > 0) ret += count;
		return ret;
	}
}

class Abbr {
	String	abbr;
	int		len;

	Abbr(String abbr, int len) {
		this.abbr = abbr;
		this.len = len;
	}
}