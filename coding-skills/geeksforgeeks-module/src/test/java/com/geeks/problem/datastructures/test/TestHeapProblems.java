package com.geeks.problem.datastructures.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.common.model.TreeNode;
import com.geeks.problem.datastructures.HeapProblems;

public class TestHeapProblems extends HeapProblems {
	public static void main(String[] args) {
		TestHeapProblems ob = new TestHeapProblems();

		// ob.testBasicProblems();

		ob.testHeapProblems();

		// ob.testKthElementProb();

		// ob.testMiscProblems();
	}

	public void testBasicProblems() {
		System.out.println("Heap Sort:");
		heapSort(mockSingleDimArray1(), true);
		heapSort(mockSingleDimArray1(), false);
	}

	public void testHeapProblems() {
		System.out.println("K largest/smallest elements in an array/stream ---> ");
		// testKthElementProb();

		System.out.println("\n\nFind median in the stream->  ");
		int[] a = { 5, 14, 2, 3, 7, 20, 6 };
		// findMedianInStream3(a);

		int[] nums1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
		System.out.println("\nSliding Window Median(Heap): " + Arrays.toString(medianSlidingWindow1(nums1, 2)));

		System.out.println("\nSliding Window Median(TreeSet): " + Arrays.toString(medianSlidingWindow2(nums1, 2)));

		System.out.println("\n\nMerge k sorted arrays & linked list->  ");
		System.out.println("Merge k sorted arrays:  ");
		System.out.println("Using merge elements and sort: ");
		mergeKSortedArrays1(mockMultiDimArray1());
		System.out.println("Using Binary heap to merge elements: ");
		mergeKSortedArrays2(mockMultiDimArray1());

		System.out.println("\nMerge k sorted linked list:  ");
		// Mock the data and test this

		System.out.println("Smallest Range: " + Arrays.toString(smallestRange(mockList())));

		System.out.println("\nTop K elements-> ");
		int[] nums = { 5, 2, 5, 3, 5, 3, 1, 1, 3 };
		System.out.println("Using Heap: " + topKFrequent1(nums, 2).toString());
		System.out.println("Using Bucket Sort: " + topKFrequent2(nums, 2).toString());

		System.out.println("\nSort Characters By Frequency-> ");
		System.out.println("Using Heap: " + stringFrequencySort1("tree"));
		System.out.println("Using Bucket Sort: " + stringFrequencySort2("tree"));

		int[] arr = { 5, 5, 4, 6, 4 };
		System.out.println("\nSorting Elements of an Array by Frequency -> ");
		System.out.println("Using Heap: " + Arrays.toString(arrayFrequencySort1(arr)));
		System.out.println("Using Bucket Sort: " + Arrays.toString(arrayFrequencySort2(arr)));

		System.out.println("Rearrange String k Distance Apart: " + rearrangeString("aabbcc", 3)); // "aaabc", 3 ;
																									// "aaadbbcc", 2

		System.out.println("\nCheck if a given Binary Tree is Heap -> ");
		System.out.println("Linked List impl: " + isBinaryHeap(mockBTData1()));
		System.out.println("Array Impl - Iterative approach: " + isBinaryHeap1(mockSingleDimArray1()));
		System.out.println("Array Impl - Recursive approach: " + isBinaryHeap2(mockSingleDimArray1()));

	}

	public void testKthElementProb() {
		System.out.println("K largest elements in an array: ");
		System.out.println("Using bubble sort for k times: ");
		kLargestElementsInArray1(mockSingleDimArray1(), 3);
		System.out.println("\nUsing simple sort: ");
		kLargestElementsInArray2(mockSingleDimArray1(), 3);
		System.out.println("\nUsing Max Binary Heap: ");
		kLargestElementsInArray31(mockSingleDimArray1(), 3);
		System.out.println("\nUsing Min Binary Heap & k size temp array: ");
		kLargestElementsInArray32(mockSingleDimArray1(), 3);

		System.out.println("\n\nKth smallest elements in an array: ");
		System.out.println("Using Sorting Approach: " + kthSmallestElementInArray1(mockSingleDimArray1(), 3));
		System.out.println("Using Max Binary Heap: " + kthSmallestElementInArray21(mockSingleDimArray1(), 3));
		System.out.println("Using Min Binary Heap: " + kthSmallestElementInArray22(mockSingleDimArray1(), 3));
		System.out.println("Using Quick Select: " + kthSmallestElementInArray31(mockSingleDimArray1(), 5));
		System.out.println("Using Quick Select: " + kthSmallestElementInArray32(mockSingleDimArray1(), 5));

		System.out.println(
				"Using Quick Select(Largest Element): " + kthLargestElementsInArray31(mockSingleDimArray1(), 2));

		System.out.println("\nKth largest element in a stream: ");
		int stream[] = { 10, 20, 11, 70, 50, 40, 100, 5 };
		kthLargestElementInStream1(stream, 3);
		System.out.println();
		kthLargestElementInStream2(stream, 3);
	}

	public void testMiscProblems() {
		String target = "apple";
		String[] dictionary = { "plain", "amber", "blade", "afrge" };
		System.out.println("Minimum Unique Word Abbreviation: " + minAbbreviation(target, dictionary));
	}

	private TreeNode mockBTData1() {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(9);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(6);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(4);
		// root.right.right.right = new BLNode(14);
		root.left.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(2);
		root.left.right.left = new TreeNode(1);
		return root;
	}

	private int[] mockSingleDimArray1() {
		int[] a = { 7, 12, 1, 3, 19, 5, 15 };
		return a;
	}

	private int[][] mockMultiDimArray1() {
		int arr[][] = { { 1, 3, 5, 7 }, { 2, 4, 6, 8 }, { 0, 9, 10, 11 } };
		return arr;
	}

	public List<List<Integer>> mockList() {
		// [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
		List<List<Integer>> mockList = new ArrayList<>();
		List<Integer> l1 = new ArrayList<>();
		l1.add(4);
		l1.add(10);
		l1.add(15);
		l1.add(24);
		l1.add(26);
		List<Integer> l2 = new ArrayList<>();
		l2.add(0);
		l2.add(9);
		l2.add(12);
		l2.add(20);
		List<Integer> l3 = new ArrayList<>();
		l3.add(5);
		l3.add(18);
		l3.add(22);
		l3.add(30);

		mockList.add(l1);
		mockList.add(l2);
		mockList.add(l3);

		return mockList;
	}
}
