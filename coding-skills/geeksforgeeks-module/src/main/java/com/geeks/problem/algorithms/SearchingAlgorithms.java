package com.geeks.problem.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class SearchingAlgorithms {

	/*************************** Java API Searching Algorithms *********************/

	/*************************** Searching Algorithms ***************/
	// Time complexity: O(n)
	public static int linearSearch(int[] a, int x) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) { return i; }
		}
		return -1;
	}

	// Iterative Approach: Time complexity: O(logn)
	public static int binarySearch1(int[] a, int x) {
		int mid, l = 0, h = a.length - 1;
		while (l <= h) {
			// mid = (l + h) / 2;
			mid = l + (h - l) / 2; // Prevent (left + right) overflow
			if (x == a[mid]) {
				return mid;
			} else if (x < a[mid]) {
				h = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return -1;
	}

	// Recursive Approach: Time complexity: O(logn)
	public static int binarySearch2(int[] a, int x) {
		return binarySearch(a, 0, a.length - 1, 610);
	}

	private static int binarySearch(int[] a, int l, int h, int x) {
		int index = -1;
		if (l > h) return index;

		int m = (l + h) / 2;
		if (a[m] == x) index = m;
		else if (x < a[m]) index = binarySearch(a, l, m - 1, x);
		else index = binarySearch(a, m + 1, h, x);

		return index;
	}

	// Time Complexity: O(log3n)
	public static int ternarySearch1(int[] a, int x) {
		int n = a.length;
		int left = 0, right = n - 1, mid1, mid2;

		while (left <= right) {
			mid1 = left + ((right - left) / 3);
			mid2 = left + (2 * (right - left) / 3); // or mid2 = mid1 + ((right - left) / 3);

			if (a[mid1] == x) return mid1;

			if (a[mid2] == x) return mid2;

			if (x < a[mid1]) { // Data present in first quarter
				right = mid1 - 1;
			} else if (x > a[mid2]) { // Data present in last quarter
				left = mid2 + 1;
			} else { // Data present in Middle Part (or second quarter).
				left = mid1 + 1;
				right = mid2 - 1;
			}
		}
		return -1;
	}

	public static int ternarySearch2(int[] a, int x) {
		return ternarySearch(a, 0, a.length - 1, x);
	}

	public static int ternarySearch(int[] a, int l, int h, int x) {
		if (l > h) return -1;

		int mid1 = l + (h - l) / 3;
		int mid2 = l + (2 * (h - l)) / 3;
		if (a[mid1] == x) return mid1;
		else if (a[mid2] == x) return mid2;
		else if (x < a[mid1]) return ternarySearch(a, l, mid1 - 1, x);
		else if (x > a[mid2]) return ternarySearch(a, mid2 + 1, h, x);
		else return ternarySearch(a, mid1 + 1, mid2 - 1, x);
	}

	// Time complexity: O(sqrt(n))
	public static int jumpSearch(int[] a, int x) {
		int block, step;
		double n = a.length;

		// Find the block size to jump the index
		block = (int) Math.floor(Math.sqrt(n));
		step = block;
		int prev = 0;

		// (n/m) jumps, where m is block
		while (a[step - 1] < x) {
			prev = step;
			step += block;
		}

		// Linear Search (m-1) comparisons
		while (a[prev] <= x) {
			if (a[prev] == x) return prev;
			prev++;
		}

		return -1;
	}

	/*
	 * The Interpolation Search is an improvement over Binary Search for instances, where the values in a sorted array are uniformly 
	 * distributed. Binary Search always goes to the middle element to check. On the other hand, interpolation search may go to 
	 * different locations according to the value of the key being searched. For example, if the value of the key is closer to the 
	 * last element, interpolation search is likely to start search toward the end side.
	 * 
	 * Formula to find the position:
	 *  pos = low + [ (x-arr[low])*{(high-low) / (arr[high]-arr[low])} ]
	 * where, arr[] ==> Array where elements need to be searched
	 * 		  x     ==> Element to be searched
	 *        low   ==> Starting index in arr[]
	 *        high  ==> Ending index in arr[]
	 *  The idea of formula is to return higher value of pos  when element to be searched is closer to arr[high]. And smaller value 
	 *  when closer to arr[low].
	 *  
	 *  Time complexity: a(log(log(n)))
	 */
	public static int interpolationSearch(int[] a, int x) {
		int index = -1;
		int l = 0, h = a.length - 1, pos;

		while (l <= h && x >= a[l] && x < a[h]) {

			// Formula to calculate position
			int temp = ((h - l) / (a[h] - a[l]));
			pos = l + ((x - a[l]) * temp);

			if (a[pos] == x) {
				index = pos;
				break;
			}

			if (x < a[pos]) h = pos - 1;
			else l = pos + 1;
		}
		return index;
	}

	/* Exponential Search: The idea is to start with subarray size 1 compare its last element with x, then try size 2, 
	 * then 4 and so on until last element of a subarray is not greater. Time Complexity: O(Log n)
	 */
	public static int exponentialSearch(int[] a, int x) {
		int i, n = a.length - 1;

		for (i = 1; i <= n && a[i] <= x; i = i * 2)
			;
		return binarySearch(a, i / 2, Math.min(i, n), x);
	}

	/* Returns index of x if present, else returns -1 */
	public static int fibonacciSearch(int arr[], int x, int n) {
		/* Initialize fibonacci numbers */
		int fibMMm2 = 0; // (m-2)'th Fibonacci No.
		int fibMMm1 = 1; // (m-1)'th Fibonacci No.
		int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

		/* fibM is going to store the smallest 
		Fibonacci Number greater than or equal to n */
		while (fibM < n) {
			fibMMm2 = fibMMm1;
			fibMMm1 = fibM;
			fibM = fibMMm2 + fibMMm1;
		}

		// Marks the eliminated range from front
		int offset = -1;

		/* while there are elements to be inspected. 
		Note that we compare arr[fibMm2] with x. 
		When fibM becomes 1, fibMm2 becomes 0 */
		while (fibM > 1) {
			// Check if fibMm2 is a valid location
			int i = Math.min(offset + fibMMm2, n - 1);

			/* If x is greater than the value at 
			index fibMm2, cut the subarray array 
			from offset to i */
			if (arr[i] < x) {
				fibM = fibMMm1;
				fibMMm1 = fibMMm2;
				fibMMm2 = fibM - fibMMm1;
				offset = i;
			}

			/* If x is greater than the value at index 
			fibMm2, cut the subarray after i+1 */
			else if (arr[i] > x) {
				fibM = fibMMm2;
				fibMMm1 = fibMMm1 - fibMMm2;
				fibMMm2 = fibM - fibMMm1;
			}

			/* element found. return index */
			else return i;
		}

		/* comparing the last element with x */
		if (fibMMm1 == 1 && arr[offset + 1] == x) return offset + 1;

		/*element not found. return -1 */
		return -1;
	}

	public static int ubiquitousBinarySearch(int[] a, int x) {
		int index = -1;

		/*Write a logic here*/

		return index;
	}

	/*************************** Coding Problems ***************/

	// Iterative Approach
	public int searchInRotatedArray1(int[] nums, int target) {
		if (nums.length == 0) return -1;

		int low = 0, high = nums.length - 1, mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (nums[mid] == target) return mid;
			if (nums[low] <= nums[mid]) {
				if (target < nums[mid] && target >= nums[low]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				if (target > nums[mid] && target <= nums[high]) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
		return -1;
	}

	// Recursive Approach
	public int searchInRotatedArray2(int[] nums, int target) {
		if (nums.length == 0) return -1;
		return searchInRotatedArray2(nums, 0, nums.length - 1, target);
	}

	public int searchInRotatedArray2(int[] nums, int l, int r, int target) {
		return -1;
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
		int row = matrix.length, col = matrix[0].length;
		int low = 0, high = (row * col) - 1, mid = 0, i, j;
		while (low <= high) {
			mid = (low + high) / 2;
			System.out.println(low + "," + high + "," + mid);
			i = mid / col;
			j = mid % col;
			// System.out.println(mid+","+i+","+j);
			if (target == matrix[i][j]) return true;
			else if (target < matrix[i][j]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return false;
	}

	/**
	 * Given a sorted array of integers, find the starting and ending position of a given target value. Your algorithm's
	 * runtime complexity must be in the order of O(log n). If the target is not found in the array, return [-1, -1].
	 * For example, given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
	 */
	public int[] searchRange(int[] nums, int target) {
		return searchRange(nums, 0, nums.length - 1, target);
	}

	public int[] searchRange(int[] nums, int l, int h, int target) {
		if (l > h) {
			int[] arr = { -1, -1 };
			return arr;
		}

		int m = (l + h) / 2;

		if (target < nums[m]) return searchRange(nums, l, m - 1, target);
		else if (target > nums[m]) return searchRange(nums, m + 1, h, target);
		else {
			int[] arr = new int[2];
			arr[0] = m;
			arr[1] = m;

			// Check whether same element present in the left side
			int i = m;
			while (i > l && nums[i] == nums[i - 1]) {
				i--;
				arr[0] = i;
			}
			// Check whether same element present in the right side
			i = m;
			while (i < h && nums[i] == nums[i + 1]) {
				i++;
				arr[1] = i;
			}
			return arr;
		}
	}

	/* Last index of One:
	 * Find the index of first 1 in a sorted array of 0’s and 1’s Given a sorted array consisting 0’s and 1’s. The
	 * problem is to find the index of first ‘1’ in the sorted array. It could be possible that the array consists of
	 * only 0’s or only 1’s. If 1’s are not present in the array then print “-1”.
	 */
	public int indexOfFirstOne(int arr[], int low, int high) {
		while (low <= high) {
			int mid = (low + high) / 2;

			// if true, then 'mid' is the index of first '1'
			if (arr[mid] == 1 && (mid == 0 || arr[mid - 1] == 0)) return mid;

			// first '1' lies to the left of 'mid'
			else if (arr[mid] == 1) high = mid - 1;

			// first '1' lies to the right of 'mid'
			else low = mid + 1;
		}

		// 1's are not present in the array
		return -1;
	}

	// A Binary Search based method to find the element
	// that appears only once
	public static void search(int[] arr, int low, int high) {
		if (low > high) return;
		if (low == high) {
			System.out.println("The required element is " + arr[low]);
			return;
		}

		// Find the middle point
		int mid = (low + high) / 2;

		// If mid is even and element next to mid is
		// same as mid, then output element lies on
		// right side, else on left side
		if (mid % 2 == 0) {
			if (arr[mid] == arr[mid + 1]) search(arr, mid + 2, high);
			else search(arr, low, mid);
		}
		// If mid is odd
		else if (mid % 2 == 1) {
			if (arr[mid] == arr[mid - 1]) search(arr, mid + 1, high);
			else search(arr, low, mid - 1);
		}
	}

	// Approach1: Using Sorting DP; Time Complexity: O(n^2)
	public int maxEnvelopes1(int[][] envelopes) {
		int n = envelopes.length;
		if (n <= 1) return n;
		// Sort the array based on width
		Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
		// Find the increasing value of height on width based sorted data; Longest Increasing Seq logic
		int[] lis = new int[n];
		int max = 0;
		Arrays.fill(lis, 1);
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1] && lis[i] <= lis[j])
					lis[i] = lis[j] + 1;
			}
			max = Math.max(max, lis[i]);
		}

		return max;
	}

	/*
	 * Floor in a Sorted Array: - Not working
	 * 	Given a sorted array and a value x, the floor of x is the largest element in array smaller than or equal to x. 
	 */
	public int floorSearch(int[] arr, int target) {
		int l = 0, h = arr.length - 1;
		while (l < h) {
			int m = l + (h - l) / 2;
			if (arr[m] == target) return m;
			else if (m > 0 && arr[m - 1] <= target && target < arr[m]) return m - 1;
			else if (target < arr[m]) h = m;
			else l = m + 1;
		}
		return -1;
	}

	/*
	 * Ceiling in a sorted array: - Not working
	 * 	Given a sorted array and a value x, the ceiling of x is the smallest element in array greater than or equal to x, 
	 *  and the floor is the greatest element smaller than or equal to x.
	 */
	public int ceilSearch(int[] arr, int target) {
		int l = 0, h = arr.length - 1;
		while (l < h) {
			int m = l + (h - l) / 2;
			if (arr[m] == target) return m;
			else if (m < h && arr[m + 1] >= target && arr[m] > target) return m + 1;
			else if (target < arr[m]) h = m;
			else l = m + 1;
		}
		return -1;
	}

	// Approach2: Using Sorting & Binary Search; Time Complexity: O(nlogn)
	public int maxEnvelopes(int[][] envelopes) {
		int n = envelopes.length;
		if (n <= 1) return n;

		// Sort the array based on width
		Arrays.sort(envelopes, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) return b[1] - a[1];
				return a[0] - b[0];
			}
		});

		// Find the increasing value of height on width based sorted data; Longest Increasing Seq using Binary Search
		int[] arr = new int[n];
		int len = 0;
		for (int[] envelope : envelopes) {
			int index = Arrays.binarySearch(arr, 0, len, envelope[1]);
			// System.out.println("Binary Index :"+index);
			if (index < 0) index = -(index + 1);
			arr[index] = envelope[1];
			if (index == len) len++;
		}

		return len;
	}

	public int minArea(char[][] image, int x, int y) {
		int m = image.length, n = image[0].length;
		int left = searchColumns(image, 0, y, 0, m, true);
		int right = searchColumns(image, y + 1, n, 0, m, false);
		int top = searchRows(image, 0, x, left, right, true);
		int bottom = searchRows(image, x + 1, m, left, right, false);
		System.out.println("L: " + left + " R: " + right + " T: " + top + " B: " + bottom);
		return (right - left) * (bottom - top);
	}

	private int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean opt) {
		while (i != j) {
			int k = top, mid = (i + j) / 2;
			while (k < bottom && image[k][mid] == '0')
				++k;
			if (k < bottom == opt) j = mid;
			else i = mid + 1;
		}
		return i;
	}

	private int searchRows(char[][] image, int i, int j, int left, int right, boolean opt) {
		while (i != j) {
			int k = left, mid = (i + j) / 2;
			while (k < right && image[mid][k] == '0')
				++k;
			if (k < right == opt) j = mid;
			else i = mid + 1;
		}
		return i;
	}

	/*
	 * Searching an Infinitely Sized List 
	 *   Find position of an element in a sorted array of infinite numbers - Suppose you have a sorted array of infinite
	 *   numbers, how would you search an element in the array? 
	 *   Solution: Since array is sorted, the first thing clicks into mind is binary search, but the problem
	 *   here is that we don’t know size of array. If the array is infinite, that means we don’t have proper bounds to
	 *   apply binary search. So in order to find position of key, first we find bounds and then apply binary search
	 *    algorithm.
	 *    
	 *  Time Complexity: O(logn)
	 */
	public int findElementInfiniteSizeArray(int[] arr, int key) {
		// Find the l & h
		int l = 0, h = 1, val = arr[0];
		// We don't know size of arr[] and we can assume size to be infinite in this function.
		// NOTE THAT THIS FUNCTION ASSUMES arr[] TO BE OF INFINITE SIZE. THEREFORE, THERE IS NO INDEX OUT OF BOUND
		// CHECKING
		while (val < key) {
			l = h;
			h = 2 * h;
			val = arr[h];
		}
		return binarySearch(arr, l, h, key);
	}

	/* Search Unknown Length Array:(Similar to previous problem, except exception handling)
	 * Standard binary search wouldn’t work because we don’t know the size of the array to provide an upper limit index.
	 * So, we perform one-sided binary search for both the size of the array and the element itself simultaneously.
	 * Let’s say we’re searching for the value k. We check array indexes 0, 1, 2, 4, 8, 16, …, 2^N in a loop until
	 * either we get an exception or we see an element larger than k.
	 *  1.If the value is less than k we continue, or if we luckily find the actual value k then we return the index. 
	 *  2.If at index 2^m we see an element larger than k, it means the value k (if it exists) must be between indexes
	 *    2^(m-1)+1 and 2^m-1 (inclusive), since the array is sorted. 
	 *  3.The same is true if we get an exception, because we know that the number at index 2^(m-1) is less than k, and we 
	 *    didn’t get an exception accessing that index. Getting an exception at index 2^m means the size of the array is
	 *    somewhere between 2^(m-1) and 2^m-1.
	 *   
	 *  Time Complexity: O(logn)
	 */

	public int findElementInUnknownLenArray(int[] arr, int key) {
		// Find the range/boundary in unknown array: l & h
		int l = 0, h = 1, val = arr[0];
		while (val < key) {
			try {
				l = h;
				h = 2 * h;
				val = arr[h];
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		// Find the element in the unknown length array
		while (l <= h) {
			int m = l + (h - l) / 2;
			try {
				if (arr[m] == key) return m;
				else if (key < arr[m]) h = m - 1;
				else l = m + 1;
			} catch (IndexOutOfBoundsException e) {
				h = m - 1;
			}
		}

		return -1;
	}

	/*
	 * Sparse Search: Given a sorted array of strings that is interspersed with empty strings, write a method to find the
	 * location of a given string.
	 * 
	 * Time Complexity- Worst Case: O(n), Avg Case: O(logn)
	 */
	public int sparseSearch(String[] strings, String key) {
		if (strings == null || strings.length == 0 || key == "") return -1;

		return sparseSearch(strings, 0, strings.length - 1, key);
	}

	public int sparseSearch(String[] strings, int l, int h, String key) {
		if (l > h) return -1;

		// Find the mid
		int m = (l + h) / 2;
		if (strings[m].isEmpty()) {
			int left = m - 1, right = m + 1;
			while (true) {
				if (left < l && right > h) {
					return -1;
				} else if (right <= h && !strings[right].isEmpty()) {
					m = right;
					break;
				} else if (left >= l && !strings[left].isEmpty()) {
					m = left;
					break;
				} else {
					right++;
					left--;
				}
			}
		}

		// Binary Search
		if (key.compareTo(strings[m]) == 0) return m;
		else if (key.compareTo(strings[m]) < 0) // Search Left
			return sparseSearch(strings, l, m - 1, key);
		else // Search Right
			return sparseSearch(strings, m + 1, h, key);
	}

	/*
	 * The idea is to leverage decent Arrays.binarySearch() function provided by Java. 
	 *  1.For each house, find its position between those heaters (thus we need the heaters array to be sorted). 
	 *  2.Calculate the distances between this house and left heater and right heater, get a MIN value of those two values.
	 *    Corner cases are there is no left or right heater. 
	 * 3.Get MAX value among distances in step 2. It's the answer. 
	 * 
	 * Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.
	 */
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int result = Integer.MIN_VALUE;

		for (int house : houses) {
			int index = Arrays.binarySearch(heaters, house);
			if (index < 0) {
				index = -(index + 1);
			}
			int RHS = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
			int LHS = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

			result = Math.max(result, Math.min(LHS, RHS));
		}

		return result;
	}

	/*************************** Util Methods ***************/
	public int medianOfTwoArray(int[] a) {
		int mid = -1;
		return mid;
	}

	public int longestIncreasingSubSequence1(int[] a) {
		int result = -1, temp, min;
		for (int i = 0; i < a.length; i++) {
			temp = 0;
			min = a[i];
			for (int j = i + 1; j < a.length; j++) {
				if (min < a[j]) {
					// min
					temp++;
				}
			}
			if (temp > result) result = temp;
		}
		return result;
	}

	public static int[] heapSort(int[] a) {
		int n = a.length;
		// apply heap property for the given elements
		for (int i = (n / 2) - 1; i >= 0; i--)
			maxHeapify(a, i, n);

		for (int i = n - 1; i >= 0; i--) {

			// Move 0th element to last index
			swap(a, 0, i);

			// Heapify
			maxHeapify(a, 0, i);
		}
		return a;
	}

	private static void maxHeapify(int[] a, int startIndex, int size) {
		int left = 2 * startIndex + 1;
		int right = 2 * startIndex + 2;
		int max = startIndex;
		if (left < size && a[max] < a[left]) max = left;
		if (right < size && a[max] < a[right]) max = right;
		if (max != startIndex) {
			swap(a, startIndex, max);

			maxHeapify(a, max, size);
		}
	}

	private static int[] swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}

	public static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

}
