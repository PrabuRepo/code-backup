package com.problems.patterns.cheatsheet;

import java.util.Arrays;

import com.common.utilities.Utils;

public class DataRearrangementPatterns {

	/************************* Rearrangement Problems Category ****************/
	/*Sort Colors/Sort an array of 0s, 1s and 2s*/
	// 1.Using count array - With additional space
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

	// 2.Using 3-way Partition: Time: O(n)
	public int[] sort012Approach2(int a[]) {
		int arr_size = a.length;
		int lo = 0, hi = arr_size - 1, mid = 0;
		while (mid <= hi) {
			if (a[mid] == 0) {
				Utils.swap(a, lo, mid);
				lo++;
				mid++;
			} else if (a[mid] == 1) {
				mid++;
			} else if (a[mid] == 2) {
				Utils.swap(a, mid, hi);
				hi--;
			}
		}

		return a;
	}

	/*Sort Transformed Array - Parabola Prob - 2 ptr  approach
	 * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 +bx + c 
	 * to each element x in the array. The returned array must be in sorted order.
	 */
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		int len = nums.length;
		int[] result = new int[len];

		int i = 0, j = len - 1;
		int index = a >= 0 ? len - 1 : 0;
		while (i <= j) {
			int l = function(nums[i], a, b, c);
			int h = function(nums[j], a, b, c);
			if (a >= 0) {
				if (l >= h) {
					result[index--] = l;
					i++;
				} else {
					result[index--] = h;
					j--;
				}
			} else {
				if (l <= h) {
					result[index++] = l;
					i++;
				} else {
					result[index++] = h;
					j--;
				}
			}
		}
		return result;
	}

	public int function(int num, int a, int b, int c) {
		return a * num * num + b * num + c;
	}

	/*Wiggle Sort I/Convert array into Zig-Zag fashion: 
	 * Given an array of distinct elements, rearrange the elements of array in zig-zag fashion in O(n) time. 
	 * The converted array should be in form a < b > c < d > e < f.
	 */
	// Approach1:
	public void wiggleSort11(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			if (i % 2 == 0) { // odd element should be less than next element;
				if (arr[i] > arr[i + 1]) // swap only if greater than next element
					Utils.swap(arr, i, i + 1);
			} else {// even element should be less than next element
				if (arr[i] < arr[i + 1])// swap only if lesser than next element
					Utils.swap(arr, i, i + 1);
			}
		}

		// Print the result
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}

	/* Sort an array in wave form/Peaks and Valleys: (Vice versa of previous one)
	 * This function sorts arr[0..n-1] in wave form, i.e. arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4]....
	 */
	void sortInWave(int arr[], int n) {
		for (int i = 0; i < n; i += 2) {
			// If current even element is smaller than previous
			if (i > 0 && arr[i - 1] > arr[i]) Utils.swap(arr, i - 1, i);

			// If current even element is smaller than next
			if (i < n - 1 && arr[i] < arr[i + 1]) Utils.swap(arr, i, i + 1);
		}
	}

	/*Wiggle Sort II: (Vice versa of previous one & allowed duplicates)*/
	// Approach: Combination of Using 3-Way Partitioning & Kth Smallest element Algorithm
	public void wiggleSort(int[] nums) {
		int n = nums.length;
		int left = 0, i = 0, right = n - 1;
		// Find Median
		int median = findMedian(nums, (n + 1) / 2);

		// Using 3-Way Partitioning or Sort 012 algorithm to order in waveform
		while (i <= right) {
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

	// Find the Median using kth smallest element Quick Select Algorithm
	public int findMedian(int[] nums, int k) {
		if (nums.length == 0 || k == 0) return 0;

		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int index = partition(nums, l, r);
			if (index == k - 1) return nums[index];
			else if (index < k - 1) l = index + 1;
			else r = index - 1;
		}
		return -1;
	}

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
	public void mergeGroup(int[] arr, int low, int mid, int high) {
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
		while (posIndex < a.length && negIndex < posIndex && a[negIndex] < 0) {
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
	void rightrotate(int arr[], int n, int outofplace, int cur) {
		int tmp = arr[cur];
		for (int i = cur; i > outofplace; i--)
			arr[i] = arr[i - 1];
		arr[outofplace] = tmp;
	}

	public void rearrangePosAndNegNumbers2(int arr[]) {
		int outofplace = -1, n = arr.length;

		for (int index = 0; index < n; index++) {
			if (outofplace >= 0) {
				if (((arr[index] >= 0) && (arr[outofplace] < 0)) || ((arr[index] < 0) && (arr[outofplace] >= 0))) {
					rightrotate(arr, n, outofplace, index);

					// the new out-of-place entry is now 2 steps ahead
					if (index - outofplace > 2) outofplace = outofplace + 2;
					else outofplace = -1;
				}
			}

			// if no entry has been flagged out-of-place
			if (outofplace == -1) {
				// check if current entry is out-of-place: odd/even
				// if (((arr[index] >= 0) && ((index & 0x01) == 0)) || ((arr[index] < 0) && (index & 0x01) == 1))
				if ((arr[index] >= 0 && index % 2 == 0) || (arr[index] < 0 && index % 2 == 1)) {
					outofplace = index;
				}
			}
		}
	}

	/* Alternative Sorting: Max Min arrangement
	 * Given an array of integers, print the array in such a way that the first element is first maximum and second
	 * element is first minimum and so on.
	 * Eg: Input: arr[] = {1, 6, 9, 4, 3, 7, 8, 2}; Output : 9 1 8 2 7 3 6 4
	 */
	public void alternateSort(int arr[], int n) {
		Arrays.sort(arr);

		// Printing the last element of array first and then first element and then second last element and then second
		// element and so on.
		int i = 0, j = n - 1;
		while (i < j) {
			System.out.print(arr[j--] + " ");
			System.out.print(arr[i++] + " ");
		}

		// If the total element in array is odd then print the last middle element.
		if (n % 2 != 0) System.out.print(arr[i]);
	}

	/*Relative Sorting - Sorting based on another array*/

}