package com.problems.patterns;

import com.common.utilities.Utils;

public class DataRearrangementPatterns {

	/************************* Rearrangement Problems Category ****************/

	/*Sort Transformed Array - Parabola Prob - 2 ptr  approach
	 * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 +bx + c 
	 * to each element x in the array. The returned array must be in sorted order.
	 */
	public int[] sortTransformedArray(int[] nums, int a,
			int b, int c) {
		int len = nums.length;
		int[] result = new int[len];

		int l = 0, h = len - 1;
		int index = a >= 0 ? len - 1 : 0;
		while (l <= h) {
			int fl = function(nums[l], a, b, c);
			int fh = function(nums[h], a, b, c);
			if (a >= 0) {
				if (fl >= fh) {
					result[index--] = fl;
					l++;
				} else {
					result[index--] = fh;
					h--;
				}
			} else {
				if (fl <= fh) {
					result[index++] = fl;
					l++;
				} else {
					result[index++] = fh;
					h--;
				}
			}
		}
		return result;
	}

	public int function(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
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
			if (i > 0 && arr[i - 1] > arr[i])
				Utils.swap(arr, i - 1, i);

			// If current even element is smaller than next
			if (i < n - 1 && arr[i] < arr[i + 1])
				Utils.swap(arr, i, i + 1);
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
				Utils.swap(nums, newIndex(left++, n),
						newIndex(i++, n));
			} else if (nums[newIndex(i, n)] < median) {
				Utils.swap(nums, newIndex(right--, n),
						newIndex(i, n));
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

}