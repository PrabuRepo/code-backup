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

	/************************* Using 2-way Partition**************************/
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
	public int kthSmallestElementInArray31(int[] nums,
			int k) {
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

	/* Reverse Partition:
	 * Left side elements are greater than pivotIndex(i) and right side elements are less than pivotIndex(i)
	 * Use reverse Partition to find the kth Largest Element;
	 */
	private int reversePartition(int[] a, int left,
			int right) {
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

	// K largest elements in the array:
	// Quick sort Partitioning: Worst Case Time: O(n)
	public int kthLargestElementsInArray31(int[] nums,
			int k) {
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

	/* Move all negative numbers to beginning and positive to end -Quick Sort Partition/Insertion/Merge Sort
	 *  1. Not maintaining order
	 *  2. Maintaining order 
	 */
	/*
	 * 1.Don't need to maintain the order
	 *  	An array contains both positive and negative numbers in random order. Rearrange the array elements so that all negative 
	 *  numbers appear before all positive numbers.
	 *  Eg: Input : -12, 11, -13, -5, 6, -7, 5, -3, -6; Output :-12 -7 -3 -13 -5  -6 11 6 5
	 *  
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

	/*
	 * 2.Maintain the order of elements
	 *  	An array contains both positive and negative numbers in random order. Rearrange the array elements so that all negative 
	 *  numbers appear before all positive numbers.
	 *  Eg: Input : -12, 11, -13, -5, 6, -7, 5, -3, -6; Output :-12 -13 -5 -7 -3 -6 11 6 5
	 */
	/*
	 * Solution1: A simple solution is to use another array. We copy all elements of original array to new array. We then traverse 
	 * the new array and copy all negative and positive elements back in original array one by one. Time: O(n^2); Space O(n)
	 * Solution2: Using Insertion Sort; Time: O(n^2); Space O(1)
	 * Solution3: Using Merge Sort; Time: O(nlogn); Space O(1); Merge Sort takes O(n) space, but here use reverse alg to save space;
	 */
	// Solution2: Insertion Sort Alg:
	public void moveNegNumFront21(int[] arr) {
		int key, j, n = arr.length;

		for (int i = 1; i < n; i++) {
			key = arr[i];
			// if current element is positive do nothing
			if (key > 0) continue;

			/* if current element is negative, shift positive elements of arr[0..i-1], to one position to their right */
			j = i - 1;
			while (j >= 0 && arr[j] > 0) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

	// Solution3:Merge Sort Algorithm
	public void moveNegNumFront22(int[] arr) {
		divideGroups(arr, 0, arr.length - 1);
	}

	public void divideGroups(int[] arr, int low, int high) {
		if (low >= high) return;
		int mid = (low + high) / 2;
		divideGroups(arr, low, mid);
		divideGroups(arr, mid + 1, high);
		mergeGroup(arr, low, mid, high);
	}

	/* Reverse Merge: steps to convert [Ln Lp Rn Rp] to [Ln Rn Lp Rp] without using extra space:
	 * 1. Reverse Lp and Rn. We get [Lp] -> [Lp'] and [Rn] -> [Rn'];    -> [Ln Lp Rn Rp] -> [Ln Lp’ Rn’ Rp]
	 * 2. Reverse [Lp’ Rn’]. We get [Rn Lp];  =>  [Ln Lp’ Rn’ Rp] -> [Ln Rn Lp Rp]
	 */
	public void mergeGroup(int[] arr, int low, int mid,
			int high) {
		int l = low;
		int r = mid + 1;
		while (l <= mid && arr[l] <= 0)
			l++;
		while (r <= high && arr[r] <= 0)
			r++;

		reverse(arr, l, mid);
		reverse(arr, mid + 1, r - 1);
		reverse(arr, l, r - 1);
	}

	public void reverse(int[] arr, int l, int h) {
		while (l < h) {
			Utils.swap(arr, l, h);
			l++;
			h--;
		}
	}

	/* Rearrange positive and negative values in an array - Quick Sort Partition/Right Rotation
	 *  1. Not maintaining order
	 *  2. Maintaining order 
	 */

	/* 1. Not maintaining order: Using Partition Algorithm
	 * An array contains both positive and negative numbers in random order. Rearrange the array elements so that positive and
	 * negative numbers are placed alternatively. The order of the appearance of elements is not maintained with this approach
	 * Eg: Input: [-1, 2, -3, 4, 5, 6, -7, 8, 9], then the output should be [9, -7, 8, -3, 5, -1, 2, 4, 6]
	 */
	public int[] rearrangePosAndNegNumbers1(int[] a) {
		// 1. Move all the negative numbers to front side
		int i = 0, j = 0;
		while (j < a.length) {
			if (a[j] < 0) {
				Utils.swap(a, i, j);
				i++;
			}
			j++;
		}

		/* 2.Now all positive numbers are at end and negative numbers at the beginning of array. Increment the negative 
		 *   index by 2 and positive index by 1, i.e. swap every alternate negative number with next positive number.
		 */
		int posIndex = i, negIndex = 0;
		while (posIndex < a.length && negIndex < posIndex
				&& a[negIndex] < 0) {
			Utils.swap(a, posIndex, negIndex);
			posIndex++;
			negIndex += 2;
		}
		return a;
	}

	// 2. To maintain the order: Right Rotation
	/* Solution:
	 * The idea is to process array from left to right. While processing, find the first out of place element in the
	 * remaining unprocessed array. An element is out of place if it is negative and at odd index, or it is positive and
	 * at even index. Once we find an out of place element, we find the first element after it with opposite sign. We
	 * right rotate the subarray between these two elements (including these two).
	 */
	void rightrotate(int arr[], int n, int outofplace,
			int cur) {
		int tmp = arr[cur];
		for (int i = cur; i > outofplace; i--)
			arr[i] = arr[i - 1];
		arr[outofplace] = tmp;
	}

	public void rearrangePosAndNegNumbers2(int arr[]) {
		int outofplace = -1, n = arr.length;

		for (int index = 0; index < n; index++) {
			if (outofplace >= 0) {
				if (((arr[index] >= 0)
						&& (arr[outofplace] < 0))
						|| ((arr[index] < 0)
								&& (arr[outofplace] >= 0))) {
					rightrotate(arr, n, outofplace, index);

					// the new out-of-place entry is now 2 steps ahead
					if (index - outofplace > 2)
						outofplace = outofplace + 2;
					else outofplace = -1;
				}
			}

			// if no entry has been flagged out-of-place
			if (outofplace == -1) {
				// check if current entry is out-of-place: odd/even
				// if (((arr[index] >= 0) && ((index & 0x01) == 0)) || ((arr[index] < 0) && (index & 0x01) == 1))
				if ((arr[index] >= 0 && index % 2 == 0)
						|| (arr[index] < 0
								&& index % 2 == 1)) {
					outofplace = index;
				}
			}
		}
	}

	/************************* Using 3-way Partition**************************/

	/*Sort Colors/Sort an array of 0s, 1s and 2s*/
	// 1.Using count array - With additional space
	// 2.Using 3-way Partition: Time: O(n)
	public int[] sort012Approach1(int[] a) {
		int[] count = new int[3];

		for (int i = 0; i < a.length; i++)
			count[a[i]]++;

		int index = 0;
		while (count[0]-- > 0)
			a[index++] = 0;

		while (count[1]-- > 0)
			a[index++] = 1;

		while (count[2]-- > 0)
			a[index++] = 2;

		return a;
	}

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
