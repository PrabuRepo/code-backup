package com.problems.patterns;

import com.common.utilities.Utils;

/*
 * Quick Select or Partition Problems:
 * 	2-way partitioning  - Forward & Reverse
 *  3-way partitioning
 *  
 * Note: Dont add too many problems here, These problems are already covered in DataRearrangement Patterns
 */
public class QuickSelectPatterns {

	/* K’th Smallest Element in Unsorted Array: 
	 * Using Quick sort Partitioning or Quick Select: Expect Linear Time complexity: O(n) 
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
		if (nums.length == 0 || k == 0)
			return 0;
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int index = partition(nums, l, r);
			if (index == k - 1)
				return nums[index];
			else if (index < k - 1)
				l = index + 1;
			else
				r = index - 1;
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

	// K largest elements in the array:
	// Quick sort Partitioning: Worst Case Time: O(n)
	public int kthLargestElementsInArray31(int[] nums, int k) {
		if (nums.length == 0 || k == 0)
			return 0;
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int index = reversePartition(nums, l, r);
			if (index == k - 1)
				return nums[index];
			else if (index < k - 1)
				l = index + 1;
			else
				r = index - 1;
		}
		return -1;
	}

	/* Reverse Partition:
	 * Left side elements are greater than pivotIndex(i) and right side elements are less than pivotIndex(i)
	 * Use reverse Partition to find the kth Largest Element;
	 */
	private int reversePartition(int[] a, int left, int right) {
		int pivot = a[right];
		int i = left, j = left;
		while (j < right) {
			if (a[j] > pivot) {
				Utils.swap(a, i, j);
				i++;
			}
			j++;
		}
		Utils.swap(a, i, right);
		return i;
	}

	/* Sample for Quick Select Algorithm:
	 * Move all negative numbers to beginning and positive to end - Not maintaining order
	 *  Solution: Use Quick Sort Partition
	 */
	public void moveNegNumFront1(int arr[]) {
		int i = 0, j = 0, n = arr.length;
		while (j < n) {
			if (arr[j] < 0) {
				Utils.swap(arr, i, j);
				i++;
			}
			j++;
		}
	}

	/*Sort Colors/Sort an array of 0s, 1s and 2s*/
	// 1.Using count array - With additional space
	// 2.Using 3-way Partition: Time: O(n)
	public int[] sort012Approach2(int a[]) {
		int arr_size = a.length;
		int l = 0, h = arr_size - 1, curr = 0;
		while (curr <= h) {
			if (a[curr] == 0) {
				Utils.swap(a, l, curr);
				l++;
				curr++;
			} else if (a[curr] == 1) {
				curr++;
			} else if (a[curr] == 2) {
				Utils.swap(a, curr, h);
				h--;
			}
		}

		return a;
	}
}
