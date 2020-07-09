package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

import com.common.model.TreeNode;
import com.common.utilities.Utils;

/*Kth element Pattern - Heap/BS/QuickSelect*/
public class KthElementPattern {
	//	Kth Smallest Element in a Sorted Matrix
	//	Kth Smallest Number in Multiplication Table
	//	Kth Smallest Number in M Sorted Lists

	/********************* Search Kth element *************************/
	//	Kth Smallest/Largest Element in an Array
	// K’th Smallest Element in Unsorted Array
	/* Find the kth Smallest element in an unsorted array. Note that it is the kth largest element in the sorted order,
	 * not the kth distinct element.
	 * Example 1: Input: [3,2,1,5,6,4] and k = 2; Output: 2
	 */
	// Approach1:Sort the given array using a sorting algorithm and return the element at index k-1 in the sorted array.
	// Time Complexity: O(nLogn)
	public int kthSmallestElementInArray1(int[] a, int k) {
		Arrays.sort(a);
		return a[k - 1];
	}

	// Approach2: Using Max Binary Heap: Time Complexity-O(nlogk)
	// Same solution used in the Kth Largest Element in the Stream
	public int kthSmallestElementInArray21(int[] arr,
			int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(
				Collections.reverseOrder());
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

	// Approach3: Using Min Binary Heap: Time Complexity-O(n+klogn)
	public int kthSmallestElementInArray22(int[] arr,
			int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) // O(n) Time to build the heap
			queue.add(arr[i]);

		for (int i = 0; i < k - 1; i++) // O(k.logn) time
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
	public int kthSmallestElementInArray31(int[] nums,
			int k) {
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
	PriorityQueue<Integer> queue;
	int                    k;

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

		return queue.size() < k ? -1 : queue.peek();
	}

	//	Kth Smallest/Largest Element in a BST 
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
	public int kthSmallest11(TreeNode root, int k) {
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
	int count  = 0;
	int result = Integer.MIN_VALUE;

	public int kthSmallest12(TreeNode root, int k) {
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
	public int kthSmallest13(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		int count = 0;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p); // Just like recursion
				p = p.left;
			} else {
				TreeNode node = stack.pop();
				if (++count == k) return node.data;
				p = node.right;
			}
		}
		return Integer.MIN_VALUE;
	}

	// Approach2: Using Heap
	public int kthSmallest2(TreeNode root, int k) {
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
	public int[][] kClosest(int[][] points, int K) {
		return null;
	}
}
