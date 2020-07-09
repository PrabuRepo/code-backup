package com.problems.patterns.cheatsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;
import java.util.function.Supplier;

import com.common.model.Cell;
import com.common.model.ListNode;
import com.common.model.TreeNode;
import com.common.utilities.Utils;

public class HeapPatternsCheatsheet {

	/********************* Type1: Search Kth element *************************/
	// K’th Smallest Element in Unsorted Array
	/* Find the kth Smallest element in an unsorted array. Note that it is the kth largest element in the sorted order,
	 * not the kth distinct element.
	 * Example 1: Input: [3,2,1,5,6,4] and k = 2; Output: 2
	 */
	// Approach1:Sort the given array using a sorting algorithm and return the element at index k-1 in the sorted array.
	// Time Complexity: O(nLogn)
	public int kthSmallestElementInArray1(int[] a,
			int k) {
		Arrays.sort(a);
		return a[k - 1];
	}

	// Same solution used in the Kth Largest Element in the Stream
	// Approach2: Using Max Binary Heap: Time Complexity-O(nlogk)
	public int kthSmallestElementInArray21(
			int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(
				Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++) {
			if (queue.isEmpty()
					|| queue.size() < k) {
				queue.add(arr[i]);
			} else if (arr[i] < queue.peek()) {
				queue.remove();
				queue.add(arr[i]);
			}
		}
		return queue.peek();
	}

	// Approach3: Using Min Binary Heap: Time Complexity-O(n+klogn)
	public int kthSmallestElementInArray22(
			int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++)
			queue.add(arr[i]);
		for (int i = 0; i < k - 1; i++)
			queue.remove();
		return queue.peek();
	}

	/*
	 * Using Quick sort Partitioning or Quick Select: Expect Linear Time complexity: O(n) 
	 * K’th Smallest/Largest Element in Unsorted Array -  
	 *   Partition or Quick Select: The partition subroutine of quicksort can also be used to solve this problem. 
	 *  In partition, we divide the array into elements>=pivot pivot elements<=pivot. Then, according to the index of pivot,
	 *  we will know whther the kth largest element is to the left or right of pivot or just itself. In average, this
	 *  algorithm reduces the size of the problem by approximately one half after each partition, giving the 
	 *  recurrence T(n) = T(n/2) + O(n) with O(n) being the time for partition. The solution is T(n) = O(n), which means 
	 *  we have found an average linear-time solution. However, in the worst case, the recurrence will become 
	 *  T(n) = T(n - 1) + O(n) and T(n) = O(n^2).
	 */
	// This is simpler than kthSmallestElementInArray32
	public int kthSmallestElementInArray31(
			int[] nums, int k) {
		if (nums.length == 0 || k == 0) return 0;
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int index = partition(nums, l, r);
			if (index == k - 1)
				return nums[index];
			else if (index < k - 1) l = index + 1;
			else r = index - 1;
		}
		return -1;
	}

	/* Partition:
	 * Left side elements are less than pivotIndex(i) and right side elements are greater than pivotIndex(i)
	 * Use Partition to find the kth Smallest Element; 
	 */
	public int partition(int[] a, int left,
			int right) {
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

	/*
	 * Kth Largest Element in a Stream:
	 * 	Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, 
	 * not the kth distinct element. Your KthLargest class will have a constructor which accepts an integer k and an integer 
	 * array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the 
	 * element representing the kth largest element in the stream.
	 * int k = 3; int[] arr = [4,5,8,2]; 
	 * KthLargest kthLargest = new KthLargest(3, arr);
	 * 	kthLargest.add(3);   // returns 4
	 * 	kthLargest.add(5);   // returns 5
	 */
	PriorityQueue<Integer>	queue;
	int						k;

	public void init(int k, int[] nums) {
		this.queue = new PriorityQueue<>();
		this.k = k;
		for (int i = 0; i < nums.length; i++) {
			add(nums[i]);
		}
	}

	public int add(int val) {
		queue.add(val);
		if (queue.size() > k) queue.poll();
		return queue.size() < k ? -1
				: queue.peek();
	}

	/*
	 * Kth Smallest Element in a BST:
	 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
	 * Note: You may assume k is always valid, 1 <= k <= BST's total elements.
	 * Example 1: Input: root = [3,1,4,null,2], k = 1
	 * 
	 * Solution: 
	 * 	Approach1: 
	 * 		i. Inorder traversal - Recursive
	 * 		ii.Inorder traversal Modification - Recursive
	 * 		iii.Inorder traversal - Iterative
	 * 	Approach2: 
	 * 		Using Priority Queue
	 */
	// Approach1: i.Using Inorder Traversal
	public int kthSmallest11(TreeNode root,
			int k) {
		if (root == null || k == 0) return 0;
		ArrayList<Integer> list = new ArrayList<>();
		kthSmallest(root, list);
		return list.get(k - 1);
	}

	public void kthSmallest(TreeNode root,
			ArrayList<Integer> list) {
		if (root == null) return;
		kthSmallest(root.left, list);
		list.add(root.data);
		kthSmallest(root.right, list);
	}

	// ii.Inorder traversal Modification
	int	count	= 0;
	int	result	= Integer.MIN_VALUE;

	public int kthSmallest12(TreeNode root,
			int k) {
		traverse(root, k);
		return result;
	}

	public void traverse(TreeNode root, int k) {
		if (root == null) return;
		traverse(root.left, k);
		count++;
		if (count == k) {
			result = root.data;
			return;
		}
		traverse(root.right, k);
	}

	// iii.Inorder traversal - Iterative
	public int kthSmallest13(TreeNode root,
			int k) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		int count = 0;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				TreeNode node = stack.pop();
				if (++count == k)
					return node.data;
				p = node.right;
			}
		}
		return Integer.MIN_VALUE;
	}

	// Approach2: Using Heap
	public int kthSmallest2(TreeNode root,
			int k) {
		if (root == null) return 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>(
				Collections.reverseOrder());
		kthSmallest(root, queue, k);
		return queue.peek();
	}

	public void kthSmallest(TreeNode root,
			PriorityQueue<Integer> queue, int k) {
		if (root == null) return;
		if (queue.isEmpty() || queue.size() < k
				|| root.data < queue.peek()) {
			if (queue.size() == k) queue.remove();
			queue.add(root.data);
		}
		kthSmallest(root.left, queue, k);
		kthSmallest(root.right, queue, k);
	}

	/*
	 * 'K' Closest Points to the Origin: ???
	 *  We have a list of points on the plane.  Find the K closest points to the origin (0, 0). (Here, the distance between two 
	 *  points on a plane is the Euclidean distance.)
	 *  You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
	 *  
	 *  Example 1: Input: points = [[1,3],[-2,2]], K = 1; Output: [[-2,2]]
	 *  Explanation: The distance between (1, 3) and the origin is sqrt(10).
	 *  			 The distance between (-2, 2) and the origin is sqrt(8).
	 *  Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin. We only want the closest K = 1 points from the origin, so the
	 *  answer is just [[-2,2]].
	 */
	/*
	Approach1: Using Simple Sorting: time complexity is O(NlogK)
	*/
	/*
	Approach2: Using Max Heap: time complexity is O(NlogK)
	*/
	/*
	Approach3: Using QuickSelect: Avg Time complexity: O(N) & Worst Case: O(N^2)
	 Explanation: Ideally, in first iteration it will run n times, in the second iteration I will n/2 times, in the third iteration I will run n/4.... , therefore
	   sum(n + n/2 + n/4 + ...) = O(n), here I have to do logN iterations.
	But in worst case, 
	sum(n + n - 1 + n - 2 +.... ) = O(n^2), here I have to do N iterations        
	*/
	public int[][] kClosest(int[][] points,
			int K) {
		return null;
	}

	/********************* Type2: Merge K data sets/K-way merge *************************/
	// Merge K sorted linked lists;
	/* Linear Merge Algorithm: Merge the List one by one 
	 * Time Complexity: O(Nk) where N = nk; k = no of linked list; n = no of elements in the list
	 */
	public ListNode mergeKSortedLinkedList1(
			ListNode[] lists) {
		int k = lists.length;
		if (k == 0) return null;
		ListNode result = null;
		for (int i = 0; i < k; i++)
			result = merge(result, lists[i]);
		return result;
	}

	public ListNode merge(ListNode head1,
			ListNode head2) {
		if (head1 == null) return head2;
		if (head2 == null) return head1;
		ListNode result = null;
		if (head1.data < head2.data) {
			result = head1;
			result.next = merge(head1.next,
					head2);
		} else {
			result = head2;
			result.next = merge(head1,
					head2.next);
		}
		return result;
	}

	// Using Min Heap: O(NLogk); where N = nk; k = no of linked list; n = no of elements in the list
	public ListNode mergeKSortedLinkedList2(
			ListNode[] nodes, int k) {
		if (k == 0) return null;
		if (k == 1) return nodes[0];
		PriorityQueue<ListNode> queue = new PriorityQueue<>(
				(o1, o2) -> o1.data - o2.data);
		for (int i = 0; i < k; i++)
			if (nodes[i] != null)
				queue.add(nodes[i]);
		ListNode dummy = new ListNode(0);
		ListNode temp = dummy;
		while (!queue.isEmpty()) {
			ListNode curr = queue.poll();
			if (curr.next != null)
				queue.add(curr.next);
			temp.next = curr;
			temp = temp.next;
		}
		return dummy.next;
	}

	// Merge k sorted arrays:
	/* 
	 * 1.BruteForce Approach: A simple solution is to create an output array of size n*k and one by one copy all
	 * arrays to it. Finally, sort the output array using any O(nLogn) sorting algorithm.
	 * This approach takes O(NlogN) time, where N=nk, k - no of arrays; n - no of elements in each array
	 */
	public int[] mergeKSortedArrays1(
			int[][] arr) {
		int k = arr.length, n = arr[0].length;
		int[] output = new int[n * k];
		int index = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n; j++) {
				output[index++] = arr[i][j];
			}
		}
		Arrays.sort(output);
		return output;
	}

	// Merge Sorted Arrays using PriorityQueue;
	// Time Complexity: O(Nlogk) where N=nk, k - no of arrays; n - no of elements in each array
	public int[] mergeKSortedArrays2(
			int[][] arr) {
		int size = 0;
		PriorityQueue<Cell> queue = new PriorityQueue<>(
				(a, b) -> a.data - b.data);
		for (int i = 0; i < arr.length; i++) {
			queue.add(new Cell(i, 0, arr[i][0]));
			size += arr[i].length;
		}
		int[] result = new int[size];
		int index = 0;
		while (!queue.isEmpty()) {
			Cell curr = queue.poll();
			result[index++] = curr.data;
			if (curr.j < arr[curr.i].length - 1)
				queue.add(new Cell(curr.i,
						curr.j + 1,
						arr[curr.i][curr.j + 1]));
		}
		return result;
	}

	/*
	 * Shortest Range in K sorted lists/Smallest range/Minimize the absolute difference
	 */
	/* Smallest Range:
	 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number
	 * from each of the k lists. We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a ==
	 * d-c. 
	 * Example 1: Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]] 
	 * Output: [20,24] 
	 * Explanation: List 1: [4, 10, 15, 24,26], 24 is in range [20,24]. List 2: [0, 9, 12, 20], 20 is in range [20,24]. 
	 * List 3: [5, 18, 22, 30], 22 is in range [20,24].	 
	 */
	// TODO: Modify this to use Container to to hold the indices
	// Apply Merge K List Algorithm
	public int[] smallestRange(
			List<List<Integer>> nums) {
		int[] result = new int[2];
		if (nums.size() == 0) return result;
		PriorityQueue<int[]> queue = new PriorityQueue<>(
				(a, b) -> a[2] - b[2]); // i,j,val
		int max = Integer.MIN_VALUE,
				minRange = Integer.MAX_VALUE;
		for (int i = 0; i < nums.size(); i++) {
			max = Math.max(max,
					nums.get(i).get(0));
			queue.add(new int[] { i, 0,
					nums.get(i).get(0) });
		}
		while (queue.size() == nums.size()) {
			int[] curr = queue.poll();
			int i = curr[0], j = curr[1],
					min = curr[2];
			if (max - min < minRange) {
				result[0] = min;
				result[1] = max;
				minRange = max - min;
			}
			j++;
			if (nums.get(i) != null
					&& j >= nums.get(i).size())
				continue;
			int nextVal = nums.get(i).get(j);
			max = Math.max(max, nextVal);
			queue.add(
					new int[] { i, j, nextVal });
		}
		return result;
	}

	/*
	 * Minimize the absolute difference: 
	 * Given three sorted arrays A, B and Cof not necessarily same sizes.
	 * Calculate the minimum absolute difference between the maximum and minimum number from the triplet a, b, c such that a, b, c
	 * belongs arrays A, B, C respectively.i.e. minimize | max(a,b,c) - min(a,b,c) |.
	 * Example :Input: A : [ 1, 4, 5, 8, 10 ], B : [ 6, 9, 15 ], C : [ 2, 3, 6, 6 ]
	 * 			Output: 1
	 */
	public static int minAbsoluteDiff(
			ArrayList<Integer> A,
			ArrayList<Integer> B,
			ArrayList<Integer> C) {
		int diff = Integer.MAX_VALUE;
		int i = 0, j = 0, k = 0;
		int p = A.size(), q = B.size(),
				r = C.size();
		while (i < p && j < q && k < r) {
			int maximum = Math.max(A.get(i),
					Math.max(B.get(j), C.get(k)));
			int minimum = Math.min(A.get(i),
					Math.min(B.get(j), C.get(k)));
			if (maximum - minimum < diff)
				diff = maximum - minimum;
			if (diff == 0) break;
			if (A.get(i) == minimum) i++;
			else if (B.get(j) == minimum) j++;
			else k++;
		}
		return diff;
	}

	/* Kth Smallest Element in a Sorted Matrix: 
	 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
	 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
	 * 	Example:
	 * 	matrix = [[ 1,  5,  9],	[10, 11, 13],[12, 13, 15]], k = 8, return 13.
	 */
	public int kthSmallest2(int[][] matrix,
			int k) {
		int r = matrix.length,
				c = matrix[0].length;
		if (k > r * c) return 0;
		PriorityQueue<Cell> queue = new PriorityQueue<>(
				(ob1, ob2) -> ob1.data
						- ob2.data);
		for (int j = 0; j < c; j++)
			queue.add(
					new Cell(0, j, matrix[0][j]));
		for (int i = 1; i < k; i++) {
			Cell cell = queue.poll();
			if (cell.i < r - 1)
				queue.add(new Cell(cell.i + 1,
						cell.j, matrix[cell.i
								+ 1][cell.j]));
		}
		return queue.peek().data;
	}

	/* Find K Pairs with Smallest Sums: 
	 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
	 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
	 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
	 * 	Example 1: Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3	Output: [[1,2],[1,4],[1,6]]
	 * 	Explanation: The first 3 pairs are returned from the sequence:
	 *          [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
	 */
	// Approach1: Brute Force using Binary Min Heap
	public List<int[]> kSmallestPairs1(
			int[] nums1, int[] nums2, int k) {
		List<int[]> result = new ArrayList<>();
		if (nums1.length == 0 || nums2.length == 0
				|| k == 0)
			return result;
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(
				(a, b) -> (a[2] - b[2]));
		for (int i = 0; i < nums1.length; i++)
			for (int j = 0; j < nums2.length; j++)
				minHeap.add(new int[] { nums1[i],
						nums2[j],
						nums1[i] + nums2[j] });
		while (k-- > 0 && minHeap.size() > 0) {
			int[] data = minHeap.poll();
			result.add(new int[] { data[0],
					data[1] });
		}
		return result;
	}

	// Approach2: Better Approach:
	public List<int[]> kSmallestPairs(int[] nums1,
			int[] nums2, int k) {
		PriorityQueue<Cell> pq = new PriorityQueue<Cell>(
				(a, b) -> a.data - b.data);
		int m = nums1.length, n = nums2.length;
		List<int[]> res = new ArrayList<int[]>();
		if (nums1 == null || nums1.length == 0
				|| nums2 == null
				|| nums2.length == 0 || k <= 0)
			return res;
		for (int j = 0; j <= n - 1; j++)
			pq.offer(new Cell(0, j,
					nums1[0] + nums2[j]));
		for (int i = 0; i < Math.min(k,
				m * n); i++) {
			Cell t = pq.poll();
			res.add(new int[] { nums1[t.i],
					nums2[t.j] });
			if (t.i == m - 1) continue;
			pq.offer(new Cell(t.i + 1, t.j,
					nums1[t.i + 1] + nums2[t.j]));
		}
		return res;
	}

	// Approach3: Efficient Approach:
	public List<int[]> kSmallestPairs3(
			int[] nums1, int[] nums2, int k) {
		List<int[]> res = new ArrayList<>();
		// Heap -- n[0] x, n[1] y
		PriorityQueue<int[]> minIndexHeap = new PriorityQueue<>(
				(a, b) -> nums1[a[0]]
						+ nums2[a[1]]
						- nums1[b[0]]
						- nums2[b[1]]);
		minIndexHeap.offer(new int[] { 0, 0 });
		int len1 = nums1.length,
				len2 = nums2.length;
		for (int i = 0; i < k
				&& !minIndexHeap.isEmpty(); i++) {
			int[] min = minIndexHeap.poll();
			res.add(new int[] { nums1[min[0]],
					nums2[min[1]] });
			if (min[1] != len2 - 1)
				minIndexHeap.offer(new int[] {
						min[0], min[1] + 1 });
			if (min[1] == 0 && min[0] != len1 - 1)
				minIndexHeap.offer(new int[] {
						min[0] + 1, 0 });
		}
		return res;
	}

	/*
	 * Kth Smallest Number in M Sorted Lists:???
	 */
	/********************** Type3: Find top K frequent elements *************************/
	/* Top K Frequent Elements:
	 * Find top k (or most frequent) numbers in a stream:
	 *  Given an array of n numbers. Your task is to read numbers from the array and keep at-most K numbers at the top (According 
	 *  to their decreasing frequency) every time a new number is read
	 */
	// Approach1: Using Hashmap & Heap; Time : O(nlogk)
	public List<Integer> topKFrequent1(int[] nums,
			int k) {
		int n = nums.length;
		if (n == 0 || k == 0) return null;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			map.put(nums[i],
					map.getOrDefault(nums[i], 0)
							+ 1);
		PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(
				(a, b) -> a.getValue()
						- b.getValue());
		for (Map.Entry<Integer, Integer> entry : map
				.entrySet()) {
			queue.add(entry);
			if (queue.size() > k) queue.poll();
		}
		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty())
			result.add(queue.poll().getKey());
		Collections.reverse(result);
		return result;
	}

	// Approach2: Using Hashmap & Bucket Sort; Time: O(n)
	public List<Integer> topKFrequent2(int[] nums,
			int k) {
		int n = nums.length, max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			map.put(nums[i],
					map.getOrDefault(nums[i], 0)
							+ 1);
		for (Map.Entry<Integer, Integer> entry : map
				.entrySet())
			max = Math.max(max, entry.getValue());
		// Bucket Sorting
		ArrayList<Integer>[] buckets = new ArrayList[max
				+ 1];
		for (Map.Entry<Integer, Integer> entry : map
				.entrySet()) {
			if (buckets[entry.getValue()] == null)
				buckets[entry
						.getValue()] = new ArrayList<>();
			buckets[entry.getValue()]
					.add(entry.getKey());
		}
		List<Integer> result = new ArrayList<Integer>();
		for (int i = max; i >= 1
				&& result.size() < k; i--) {
			if (buckets[i] != null
					&& buckets[i].size() > 0) {
				for (int a : buckets[i]) {
					if (result.size() == k) break;
					result.add(a);
				}
			}
		}
		return result;
	}

	/* Top K Frequent Words:
	 * Given a non-empty list of words, return the k most frequent elements.
	 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word 
	 * with the lower alphabetical order comes first.
	 * Example 1:
	 * 	Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	 * 	Output: ["i", "love"]
	 * 	Explanation: "i" and "love" are the two most frequent words.
	 * 	Note that "i" comes before "love" due to a lower alphabetical order.
	 */
	// Approach1: using Map & Sorting -> Time: O(nlogn)
	public List<String> topKFrequent(
			String[] words, int k) {
		Map<String, Integer> count = new HashMap<>();
		for (String word : words) {
			count.put(word,
					count.getOrDefault(word, 0)
							+ 1);
		}
		List<String> candidates = new ArrayList<>(
				count.keySet());
		Collections.sort(candidates,
				(w1, w2) -> count.get(w1)
						.equals(count.get(w2))
								? w1.compareTo(w2)
								: count.get(w2)
										- count.get(
												w1));
		return candidates.subList(0, k);
	}

	// Approach2: using Map & Heap -> Time - O(nlogk)
	public List<String> topKFrequent2(
			String[] words, int k) {
		if (words.length == 0 || k == 0)
			return null;
		HashMap<String, Integer> map = new HashMap<>();
		for (String word : words)
			map.put(word,
					map.getOrDefault(word, 0)
							+ 1);
		PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(
				(a, b) -> {
					if (a.getValue() == b
							.getValue())
						return a.getKey()
								.compareTo(b
										.getKey());
					return b.getValue()
							- a.getValue();
				});
		for (Map.Entry<String, Integer> entry : map
				.entrySet())
			queue.add(entry);
		List<String> result = new ArrayList<>();
		while (!queue.isEmpty()
				&& result.size() < k) {
			result.add(queue.poll().getKey());
		}
		return result;
	}

	/*
	 *  Sort Characters By Frequency:
	 *  Given a string, sort it in decreasing order based on the frequency of characters.
	 *  Example 1:	Input: "tree";  Output: "eert"
	 *  Explanation: 'e' appears twice while 'r' and 't' both appear once.
	 *  So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
	 */
	// Using Priority Queue
	public String frequencySort1(String s) {
		int n = s.length();
		if (n <= 1) return s;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int count = map
					.getOrDefault(s.charAt(i), 0);
			map.put(s.charAt(i), count + 1);
		}
		PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
				(a, b) -> b.getValue()
						- a.getValue());
		queue.addAll(map.entrySet());
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			Map.Entry<Character, Integer> entry = queue
					.poll();
			for (int i = 0; i < (int) entry
					.getValue(); i++)
				sb.append(entry.getKey());
		}
		return sb.toString();
	}

	// Using TreeSet(Balanced BST)
	public String frequencySort(String s) {
		int n = s.length();
		if (n <= 1) return s;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int count = map
					.getOrDefault(s.charAt(i), 0);
			map.put(s.charAt(i), count + 1);
		}
		List<Character>[] bucket = new ArrayList[s
				.length() + 1];
		for (char ch : map.keySet()) {
			int freq = map.get(ch);
			if (bucket[freq] == null)
				bucket[freq] = new ArrayList<>();
			bucket[freq].add(ch);
		}
		StringBuilder sb = new StringBuilder();
		for (int freq = s
				.length(); freq > 0; freq--) {
			if (bucket[freq] != null) {
				for (char ch : bucket[freq])
					for (int i = 0; i < freq; i++)
						sb.append(ch);
			}
		}
		return sb.toString();
	}

	/* Task Scheduler: Given a char array representing tasks CPU need to do. It contains capital letters A to Z where
	 * different letters represent different tasks. Tasks could be done without original order. Each task could be done
	 * in one interval. For each interval, CPU could finish one task or just be idle. However, there is a non-negative
	 * cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing
	 * different tasks or just be idle. You need to return the least number of intervals the CPU will take to finish all
	 * the given tasks. 
	 * Example: Input: tasks = ["A","A","A","B","B","B"], n = 2 Output: 8 
	 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
	 */
	// Appoach1:
	public int leastInterval1(char[] tasks,
			int n) {
		int[] cnt = new int[26];
		for (char c : tasks)
			cnt[c - 'A']++;
		int maxChar = 0, maxCharCnt = 0;
		for (int i = 0; i < 26; i++) {
			if (cnt[i] == maxChar) {
				maxCharCnt++;
			} else if (cnt[i] > maxChar) {
				maxChar = cnt[i];
				maxCharCnt = 1;
			}
		}
		int minimum = (maxChar - 1) * (n + 1)
				+ maxCharCnt;
		if (tasks.length > minimum)
			return tasks.length;
		return minimum;
	}

	// Approach-2
	// Java PriorityQueue solution
	public int leastInterval2(char[] tasks,
			int n) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (char ch : tasks)
			map.put(ch,
					map.getOrDefault(ch, 0) + 1);
		PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
				(a, b) -> (b.getValue()
						- a.getValue()));
		queue.addAll(map.entrySet());
		int cnt = 0;
		while (!queue.isEmpty()) {
			int interval = n + 1;
			List<Map.Entry<Character, Integer>> list = new ArrayList<>();
			while (interval > 0
					&& !queue.isEmpty()) {
				Map.Entry<Character, Integer> entry = queue
						.poll();
				entry.setValue(
						entry.getValue() - 1);
				list.add(entry);
				interval--;
				cnt++;
			}
			for (Map.Entry<Character, Integer> entry : list) {
				if (entry.getValue() > 0)
					queue.offer(entry);
			}
			if (queue.isEmpty()) break;
			cnt += interval;
		}
		return cnt;
	}

	/* Rearrange String K Distance Apart:
	 * Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least
	 * distance k from each other. All input strings are given in lowercase letters. If it is not possible to rearrange
	 * the string, return an empty string "".
	 * str = "aabbcc", k = 3
	 * Result: "abcabc"
	 * The same letters are at least distance 3 from each other.
	 */
	public String rearrangeString(String str,
			int k) {
		final HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (map.containsKey(c))
				map.put(c, map.get(c) + 1);
			else map.put(c, 1);
		}
		PriorityQueue<Character> queue = new PriorityQueue<Character>(
				new Comparator<Character>() {
					public int compare(
							Character c1,
							Character c2) {
						if (map.get(c2)
								.intValue() != map
										.get(c1)
										.intValue()) {
							return map.get(c2)
									- map.get(c1);
						} else return c1
								.compareTo(c2);
					}
				});
		for (char c : map.keySet())
			queue.offer(c);
		StringBuilder sb = new StringBuilder();
		int len = str.length();
		while (!queue.isEmpty()) {
			int cnt = Math.min(k, len);
			ArrayList<Character> temp = new ArrayList<Character>();
			for (int i = 0; i < cnt; i++) {
				if (queue.isEmpty()) return "";
				char c = queue.poll();
				sb.append(String.valueOf(c));
				map.put(c, map.get(c) - 1);
				if (map.get(c) > 0) temp.add(c);
				len--;
			}
			for (char c : temp)
				queue.offer(c);
		}
		return sb.toString();
	}

	/********************* Type4: Two Heap *************************/

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
		lower = new PriorityQueue<>(
				Collections.reverseOrder());
		upper = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			addNum(a[i]);
			System.out.print(findMedian() + " ");
		}
	}

	public void addNum(int num) {
		if (!lower.isEmpty()
				&& num < lower.peek())
			lower.add(num);
		else upper.add(num);
		balanceHeap();
	}

	// If size is odd, Upper should have one elements more than Lower
	private void balanceHeap() {
		if (lower.size() > upper.size())
			upper.add(lower.poll());
		if (upper.size() - lower.size() > 1)
			lower.add(upper.poll());
	}

	// Returns the median of current data stream
	public double findMedian() {
		return lower.size() == upper.size()
				? ((double) lower.peek()
						+ (double) upper.peek())
						* 0.5
				: (double) upper.peek();
	}

	public void removeNum(int num) {
		if (!lower.isEmpty()
				&& num <= lower.peek())
			lower.remove(num);
		else upper.remove(num);
		balanceHeap();
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
	public double[] medianSlidingWindow1(
			int[] nums, int k) {
		int n = nums.length;
		if (n == 0) return new double[0];
		lower = new PriorityQueue<>(
				Collections.reverseOrder());
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

	/* Approach2: Using Treeset
	 *  However instead of using two priority queue's we use two Tree Sets as we want O(logk) for remove(element).
	 * Priority Queue would have been O(k) for remove(element) giving us an overall time complexity of O(nk) instead of
	 * O(nlogk).
	 */
	// TODO: ITs not working; Change it to index based; Because TreeSet doesnt allow duplicate
	public double[] medianSlidingWindow2(
			int[] nums, int k) {
		TreeSet<Integer> lower = new TreeSet<>(
				Collections.reverseOrder());
		TreeSet<Integer> upper = new TreeSet<>();
		int n = nums.length, index = 0;
		double[] result = new double[n - k + 1];
		for (int i = 0; i <= n; i++) {
			if (i >= k) {
				result[index++] = findMedian(
						lower, upper);
				removeNum(lower, upper,
						nums[n - k]);
			}
			if (i < n)
				addNum(lower, upper, nums[i]);
		}
		return result;
	}

	public void addNum(TreeSet<Integer> lower,
			TreeSet<Integer> upper, int num) {
		if (!lower.isEmpty()
				&& num < lower.first())
			lower.add(num);
		else upper.add(num);
		balanceTreeSet(lower, upper);
	}

	public void removeNum(TreeSet<Integer> lower,
			TreeSet<Integer> upper, int num) {
		if (!lower.isEmpty()
				&& num < lower.first())
			lower.remove(num);
		else upper.remove(num);
		balanceTreeSet(lower, upper);
	}

	public void balanceTreeSet(
			TreeSet<Integer> lower,
			TreeSet<Integer> upper) {
		if (lower.size() > upper.size())
			upper.add(lower.pollFirst());
		if (upper.size() - lower.size() > 1)
			lower.add(upper.pollFirst());
	}

	public double findMedian(
			TreeSet<Integer> lower,
			TreeSet<Integer> upper) {
		if (lower.size() == upper.size())
			return ((double) (lower.first()
					+ upper.first()) * 0.5);
		return upper.first();
	}

	// Using Lambda Expression
	public double[] medianSlidingWindow3(
			int[] nums, int k) {
		Comparator<Integer> comparator = (a,
				b) -> nums[a] != nums[b]
						? Integer.compare(nums[a],
								nums[b])
						: a - b;
		TreeSet<Integer> left = new TreeSet<>(
				comparator.reversed());
		TreeSet<Integer> right = new TreeSet<>(
				comparator);
		Supplier<Double> median = (k % 2 == 0)
				? () -> ((double) nums[left
						.first()]
						+ nums[right.first()]) / 2
				: () -> (double) nums[right
						.first()];
		Runnable balance = () -> {
			if (left.size() > right.size())
				right.add(left.pollFirst());
			if (right.size() - left.size() > 1)
				left.add(right.pollFirst());
		};
		double[] result = new double[nums.length
				- k + 1];
		for (int i = 0, j = 0; i <= nums.length; i++) {
			if (i >= k) {
				result[j++] = median.get();
				if (!left.isEmpty() && nums[i
						- k] <= nums[left
								.first()])
					left.remove(i - k);
				else right.remove(i - k);
				balance.run();
			}
			if (i >= nums.length) continue;
			if (!left.isEmpty()
					&& nums[i] < nums[left
							.first()])
				left.add(i);
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
	public int activityNotifications1(
			int[] expenditure, int k) {
		int n = expenditure.length;
		lower = new PriorityQueue<>(
				Collections.reverseOrder());
		upper = new PriorityQueue<>();
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				double median = findMedian();
				if (expenditure[i] >= 2 * median)
					count++;
				removeNum(expenditure[i - k]);
			}
			addNum(expenditure[i]);
		}
		return count;
	}

	// Approach2: Using Counting Sort
	public int activityNotifications2(
			int[] expenditure, int k) {
		int n = expenditure.length, count = 0;
		int[] freq = new int[201];
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				double median = findMedian2(freq,
						k);
				if (expenditure[i] >= 2 * median)
					count++;
				freq[expenditure[i - k]]--;
			}
			freq[expenditure[i]]++;
		}
		return count;
	}

	public static double findMedian2(int[] freq,
			int k) {
		int count = 0;
		double median = 0;
		if (k % 2 == 0) {
			int m1 = -1, m2 = -1;
			for (int i = 0; i < freq.length; i++) {
				if (freq[i] == 0) continue;
				count += freq[i];
				if (m1 == -1 && count >= k / 2)
					m1 = i;
				if (m2 == -1
						&& count >= (k / 2) + 1) {
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

	/*
	 *  Ugly Number II:
	 *  Write a program to find the n-th ugly number. Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
	 *  Example: Input: n = 10; Output: 12
	 *  Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
	 */
	// Approach1: Brute Force Approach
	public int nthUglyNumber1(int n) {
		int count = 0;
		for (int i = 1; count < n; i++) {
			if (isUgly(i)) count++;
			if (count == n) return i;
		}
		return count;
	}

	// Approach2-1: Using DP-1
	public int nthUglyNumber21(int n) {
		if (n <= 1) return n;
		int[] dp = new int[n];
		dp[0] = 1;
		int t2 = 0, t3 = 0, t5 = 0;
		for (int i = 1; i < n; i++) {
			dp[i] = Math.min(
					Math.min(dp[t2] * 2,
							dp[t3] * 3),
					dp[t5] * 5);
			if (dp[i] == 2 * dp[t2]) t2++;
			if (dp[i] == 3 * dp[t3]) t3++;
			if (dp[i] == 5 * dp[t5]) t5++;
		}
		return dp[n - 1];
	}

	// Approach2-2: Using DP-2
	public int nthUglyNumber22(int n) {
		if (n <= 1) return n;
		int[] dp = new int[n];
		dp[0] = 1;
		int min = 0;
		int[] primes = { 2, 3, 5 };
		int[] primeIndex = new int[primes.length];
		for (int i = 1; i < n; i++) {
			min = Integer.MAX_VALUE;
			for (int j = 0; j < primes.length; j++)
				min = Math.min(min, primes[j]
						* dp[primeIndex[j]]);
			dp[i] = min;
			for (int j = 0; j < primeIndex.length; j++)
				if (dp[i] == dp[primeIndex[j]]
						* primes[j])
					primeIndex[j]++;
		}
		return dp[n - 1];
	}

	// Approach3: Using Heap
	public int nthUglyNumber(int n) {
		if (n <= 1) return n;
		int[] result = new int[n];
		result[0] = 1;
		int[] primes = { 2, 3, 5 };
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(
				(a, b) -> a[2] - b[2]);
		for (int i = 0; i < primes.length; i++)
			minHeap.add(new int[] { primes[i], 0,
					primes[i] });
		for (int i = 1; i < n; i++) {
			result[i] = minHeap.peek()[2];
			while (result[i] == minHeap
					.peek()[2]) {
				int[] top = minHeap.poll();
				int prime = top[0],
						index = top[1],
						val = top[2];
				minHeap.add(new int[] { prime,
						index + 1,
						prime * result[index
								+ 1] });
			}
		}
		return result[n - 1];
	}

	public boolean isUgly(int num) {
		while (num > 1) {
			if (num % 2 == 0) num /= 2;
			else if (num % 3 == 0) num /= 3;
			else if (num % 5 == 0) num /= 5;
			else break;
		}
		return num == 1 ? true : false;
	}

	/* Super Ugly Number:
	 * Write a program to find the nth super ugly number. Super ugly numbers are positive numbers whose all prime
	 * factors are in the given prime list primes of size k. 
	 * Example: Input: n = 12, primes = [2,7,13,19] Output: 32
	 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes =
	 * [2,7,13,19] of size 4.
	 */
	// Approach1: Using DP
	public int nthSuperUglyNumber1(int n,
			int[] primes) {
		if (n <= 1) return n;
		int[] dp = new int[n];
		int min = 0;
		int[] primeIndex = new int[primes.length];
		dp[0] = 1;
		for (int i = 1; i < n; i++) {
			min = Integer.MAX_VALUE;
			for (int j = 0; j < primes.length; j++)
				min = Math.min(min, primes[j]
						* dp[primeIndex[j]]);
			dp[i] = min;
			for (int j = 0; j < primeIndex.length; j++)
				if (dp[i] == dp[primeIndex[j]]
						* primes[j])
					primeIndex[j]++;
		}
		return dp[n - 1];
	}

	// Approach2: Using Heap
	public int nthSuperUglyNumber(int n,
			int[] primes) {
		if (n <= 1) return n;
		int[] result = new int[n];
		result[0] = 1;
		// 0-Prime, 1=Index, 2=Val
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(
				(a, b) -> a[2] - b[2]);
		for (int i = 0; i < primes.length; i++)
			minHeap.add(new int[] { primes[i], 0,
					primes[i] });
		for (int i = 1; i < n; i++) {
			result[i] = minHeap.peek()[2];
			while (result[i] == minHeap
					.peek()[2]) {
				int[] top = minHeap.poll();
				int prime = top[0],
						index = top[1],
						val = top[2];
				minHeap.add(new int[] { prime,
						index + 1,
						prime * result[index
								+ 1] });
			}
		}
		return result[n - 1];
	}

	/*************************** Misc ************************************/
	/* Check Binary Heap Tree(Tree data structure):
	 It should be a complete tree (i.e. all levels except last should be full).
	Every node’s value should be greater than or equal to its child node (considering max-heap).*/
	public boolean isBinaryHeap(TreeNode root) {
		if (root == null) return true;
		int count = sizeOfBinaryTree(root);
		return isCompleteProperty(root, 0, count)
				&& isMaxBinaryHeap(root);
	}

	// Size of a BT - Recursive Approach
	public int sizeOfBinaryTree(TreeNode root) {
		if (root == null) return 0;
		return 1 + sizeOfBinaryTree(root.left)
				+ sizeOfBinaryTree(root.right);
	}

	// Check Complete property
	private boolean isCompleteProperty(
			TreeNode root, int index, int count) {
		if (root == null) return true;
		if (index >= count) return false;
		return isCompleteProperty(root.left,
				(2 * index) + 1, count)
				&& isCompleteProperty(root.right,
						(2 * index) + 2, count);
	}

	// Check Max Binary Heap Property
	private boolean isMaxBinaryHeap(
			TreeNode root) {
		if (root.left == null
				&& root.right == null)
			return true;
		if (root.right == null)
			return (root.data > root.left.data);
		return (root.data >= root.left.data
				&& root.data >= root.right.data)
				&& isMaxBinaryHeap(root.left)
				&& isMaxBinaryHeap(root.right);
	}

}