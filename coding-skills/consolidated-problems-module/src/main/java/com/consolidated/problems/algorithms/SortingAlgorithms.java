package com.consolidated.problems.algorithms;

import java.util.Arrays;

import com.common.utilities.Utils;

public class SortingAlgorithms {

	/************************************** Type1: Basic Problems *******************************************/
	/*
	 * Merge Sorted Array:Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
	 */
	// Simple approach
	public void merge(int[] nums1, int m,
			int[] nums2, int n) {
		int i = m - 1, j = n - 1,
				index = nums1.length - 1;

		while (i >= 0 && j >= 0)
			nums1[index--] = (nums1[i] > nums2[j])
					? nums1[i--]
					: nums2[j--];

		while (j >= 0)
			nums1[index--] = nums2[j--];

		// System.out.println("Result: " + Arrays.toString(nums1));
	}

	public void merge2(int[] nums1, int m,
			int[] nums2, int n) {
		if (nums1.length == 0
				|| nums2.length == 0)
			return;
		int size = nums1.length - 1, i1 = m - 1,
				i2 = n - 1;
		// Merge from max value or last index
		while (size >= 0 && i1 >= 0 && i2 >= 0) {
			if (nums1[i1] > nums2[i2]) {
				nums1[size--] = nums1[i1--];
			} else {
				nums1[size--] = nums2[i2--];
			}
		}

		while (i2 >= 0)
			nums1[size--] = nums2[i2--];

		// System.out.println(Arrays.toString(nums1));
	}

	/************************************** Type2: Rearrangement Problems *******************************************/
	// Sort Colors/Sort an array of 0s, 1s and 2s
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

	// 2.Using 3-way Partition
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
	// Sort Transformed Array - Parabola Prob - 2 ptr approach

	// Wiggle Sort I/Convert array into Zig-Zag fashion:A[0]<=A[1]>=A[2]<=A[3]>=A[4]<=A[5]
	public void wiggleSort(int[] nums) {
		if (nums == null
				|| nums.length <= 1) { return; }

		for (int i = 0; i < nums.length
				- 1; i++) {
			if (i % 2 == 0) {
				if (nums[i] > nums[i + 1]) {
					Utils.swap(nums, i, i + 1);
				}
			} else {
				if (nums[i] < nums[i + 1]) {
					Utils.swap(nums, i, i + 1);
				}
			}
		}
	}

	// Wiggle Sort II/Sort an array in wave form/Peaks and Valleys -> arr[0]>=arr[1]<=arr[2]>=arr[3]<=arr[4]....
	void sortInWave(int arr[], int n) {
		// Traverse all even elements
		for (int i = 0; i < n; i += 2) {
			// If current even element is smaller
			// than previous
			if (i > 0 && arr[i - 1] > arr[i]) {
				Utils.swap(arr, i - 1, i);
			}

			// If current even element is smaller
			// than next
			if (i < n - 1
					&& arr[i] < arr[i + 1]) {
				Utils.swap(arr, i, i + 1);
			}
		}
	}

	/*Move all negative numbers to beginning and positive to end:
	 *  1. Not maintaing order
	 *  2. Maintaing order 
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

	// Solution3:
	public void moveNegNumFront22(int[] arr) {
		divideGroups(arr, 0, arr.length - 1);
	}

	public void divideGroups(int[] arr, int low,
			int high) {
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
	public void mergeGroup(int[] arr, int low,
			int mid, int high) {
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

	// Rearrange positive and negative values in an array - Quick Sort Partition
	/* 1.Rearrange positive and negative values in an array - Quick Sort Partition:
	 *  1. Not maintaing order
	 *  2. Maintaing order 
	 */

	/* 1. Not maintaing order
	 * An array contains both positive and negative numbers in random order. Rearrange the array elements so that positive and
	 * negative numbers are placed alternatively. The order of the appearance of elements is not maintained with this approach
	 * Eg: Input: [-1, 2, -3, 4, 5, 6, -7, 8, 9], then the output should be [9, -7, 8, -3, 5, -1, 2, 4, 6]
	 */
	public int[] rearrangePosAndNegNumbers1(
			int[] a) {
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
		while (posIndex < a.length
				&& negIndex < posIndex
				&& a[negIndex] < 0) {
			Utils.swap(a, posIndex, negIndex);
			posIndex++;
			negIndex += 2;
		}
		return a;
	}

	// 2. To maintain the order:
	/* Solution:
	 * The idea is to process array from left to right. While processing, find the first out of place element in the
	 * remaining unprocessed array. An element is out of place if it is negative and at odd index, or it is positive and
	 * at even index. Once we find an out of place element, we find the first element after it with opposite sign. We
	 * right rotate the subarray between these two elements (including these two).
	 */
	void rightrotate(int arr[], int n,
			int outofplace, int cur) {
		int tmp = arr[cur];
		for (int i = cur; i > outofplace; i--)
			arr[i] = arr[i - 1];
		arr[outofplace] = tmp;
	}

	public void rearrangePosAndNegNumbers2(
			int arr[]) {
		int outofplace = -1, n = arr.length;

		for (int index = 0; index < n; index++) {
			if (outofplace >= 0) {
				if (((arr[index] >= 0)
						&& (arr[outofplace] < 0))
						|| ((arr[index] < 0)
								&& (arr[outofplace] >= 0))) {
					rightrotate(arr, n,
							outofplace, index);

					// the new out-of-place entry is now 2 steps ahead
					if (index - outofplace > 2)
						outofplace = outofplace
								+ 2;
					else outofplace = -1;
				}
			}

			// if no entry has been flagged out-of-place
			if (outofplace == -1) {
				// check if current entry is out-of-place: odd/even
				// if (((arr[index] >= 0) && ((index & 0x01) == 0)) || ((arr[index] < 0) && (index & 0x01) == 1))
				if ((arr[index] >= 0
						&& index % 2 == 0)
						|| (arr[index] < 0
								&& index % 2 == 1)) {
					outofplace = index;
				}
			}
		}
	}

	/* Alternative Sorting:
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

	// Relative Sorting - Sorting based on another array

	/********************************* Type3: Min no of swap required to sort array ************************/
	/* Count Inversions in an array | Set 1 (Using Merge Sort):
	 * Inversion Count for an array indicates – how far (or close) the array is from being sorted. If array is already sorted 
	 * then inversion count is 0. If array is sorted in reverse order that inversion count is the maximum.
	 * Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j
	 * Example:	The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3)
	 */
	// Brute Force Approach: Time: O(n^2)
	public int countInversions1(int[] arr,
			int n) {
		int inv_count = 0;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (arr[i] > arr[j]) inv_count++;

		return inv_count;
	}

	// Using Merge Sort Alg: Time: O(nlogn)
	public int countInversions2(int arr[],
			int array_size) {
		int temp[] = new int[array_size];
		return mergeSort(arr, temp, 0,
				array_size - 1);
	}

	/* An auxiliary recursive method that sorts the input array and 
	  returns the number of inversions in the array. */
	public int mergeSort(int arr[], int temp[],
			int left, int right) {
		int mid, inv_count = 0;
		if (right > left) {
			/* Divide the array into two parts and call _mergeSortAndCountInv() 
			for each of the parts */
			mid = (right + left) / 2;

			/* Inversion count will be the sum of inversions in left-part, right-part 
			and number of inversions in merging */
			inv_count = mergeSort(arr, temp, left,
					mid);
			inv_count += mergeSort(arr, temp,
					mid + 1, right);

			/*Merge the two parts*/
			inv_count += merge(arr, temp, left,
					mid + 1, right);
		}
		return inv_count;
	}

	/* This method merges two sorted arrays and returns inversion count in 
	the arrays.*/
	public int merge(int arr[], int temp[],
			int left, int mid, int right) {
		int i, j, k;
		int inv_count = 0;

		i = left; /* i is index for left subarray*/
		j = mid; /* j is index for right subarray*/
		k = left; /* k is index for resultant merged subarray*/
		while ((i <= mid - 1) && (j <= right)) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];

				/*this is tricky -- see above explanation/diagram for merge()*/
				inv_count = inv_count + (mid - i);
			}
		}

		/* Copy the remaining elements of left subarray 
		(if there are any) to temp*/
		while (i <= mid - 1)
			temp[k++] = arr[i++];

		/* Copy the remaining elements of right subarray 
		(if there are any) to temp*/
		while (j <= right)
			temp[k++] = arr[j++];

		/*Copy back the merged elements to original array*/
		for (i = left; i <= right; i++)
			arr[i] = temp[i];

		return inv_count;
	}

	// Using BIT DS: Time: O(nlog(maximumelement)).
	// Space: O(maximumelement)
	// Returns sum of arr[0..index].
	// This function assumes that the
	// array is preprocessed and partial
	// sums of array elements are stored
	// in BITree[].
	public int getSum(int[] BITree, int index) {
		int sum = 0; // Initialize result

		// Traverse ancestors of BITree[index]
		while (index > 0) {
			// Add current element of BITree to sum
			sum += BITree[index];

			// Move index to parent node in getSum View
			index -= index & (-index);
		}
		return sum;
	}

	// Updates a node in Binary Index
	// Tree (BITree) at given index
	// in BITree. The given value 'val'
	// is added to BITree[i] and all
	// of its ancestors in tree.
	public void updateBIT(int[] BITree, int n,
			int index, int val) {
		// Traverse all ancestors and add 'val'
		while (index <= n) {
			// Add 'val' to current node of BI Tree
			BITree[index] += val;

			// Update index to that of parent in update View
			index += index & (-index);
		}
	}

	// Returns inversion count arr[0..n-1]
	public int getInvCount(int[] arr, int n) {
		int invcount = 0; // Initialize result

		// Find maximum element in arr[]
		int maxElement = 0;
		for (int i = 0; i < n; i++)
			if (maxElement < arr[i])
				maxElement = arr[i];

		// Create a BIT with size equal to
		// maxElement+1 (Extra one is used so
		// that elements can be directly be
		// used as index)
		int[] BIT = new int[maxElement + 1];
		for (int i = 1; i <= maxElement; i++)
			BIT[i] = 0;

		// Traverse all elements from right.
		for (int i = n - 1; i >= 0; i--) {
			// Get count of elements smaller than arr[i]
			invcount += getSum(BIT, arr[i] - 1);

			// Add current element to BIT
			updateBIT(BIT, maxElement, arr[i], 1);
		}

		return invcount;
	}

	/********************************* Type4: Sorting Alg applications ************************/

	/************************************** Others *******************************************/
	/*
	 * Triplet Sum: Given 3 arrays a,b,c of different sizes, find the number of distinct triplets(p,q,r) 
	 * where p is an element of a, written as p->a,q->b and r->c, satisfying the criteria: p<=q && q>=r.
	 * For example, a={3,5,7} b={3,6} and c={4,6,9}, we find four distinct triplets: (3,6,4), (3,6,6), (5,6,4), (5,6,6)
	 */
	// Approach1: Brute Force Approach
	static long triplets1(int[] a, int[] b,
			int[] c) {
		int count = 0, p, q, r;

		for (int i = 0; i < a.length; i++) {
			p = a[i];
			if (i > 0 && a[i - 1] == a[i])
				continue;
			for (int j = 0; j < b.length; j++) {
				q = b[j];
				if (p > q || (j > 0
						&& b[j - 1] == b[j]))
					continue;
				for (int k = 0; k < c.length; k++) {
					r = c[k];
					if (k > 0 && a[k - 1] == a[k])
						continue;

					if (q >= r) count++;
				}
			}
		}
		return count;
	}

	// Approach2: Sorting & compare with b[] array- Time Complexity-O(n^3)
	static long triplets(int[] a, int[] b,
			int[] c) {
		Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);

		int p = 0, r = 0;
		long pCount = 0, rCount = 0, total = 0;
		for (int q = 0; q < b.length; q++) {
			while (p < a.length && a[p] <= b[q]) {
				if (p == 0 || a[p - 1] != a[p])
					pCount++;
				p++;
			}
			while (r < c.length && c[r] <= b[q]) {
				if (r == 0 || c[r - 1] != c[r])
					rCount++;
				r++;
			}
			if (q == 0 || b[q - 1] != b[q])
				total += pCount * rCount;
		}

		return total;
	}

}