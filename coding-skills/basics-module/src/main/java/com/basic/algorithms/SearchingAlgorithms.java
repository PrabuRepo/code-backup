package com.basic.algorithms;

public class SearchingAlgorithms {

	// Time complexity: O(n)
	public static int linearSearch(int[] a,
			int x) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) { return i; }
		}
		return -1;
	}

	// Iterative Approach: Time complexity: O(logn)
	public static int binarySearch1(int[] a,
			int x) {
		int mid, l = 0, h = a.length - 1;
		while (l <= h) {
			mid = (l + h) / 2;
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
	public static int binarySearch2(int[] a,
			int x) {
		return binarySearch(a, 0, a.length - 1,
				610);
	}

	private static int binarySearch(int[] a,
			int l, int h, int x) {
		int index = -1;
		if (l > h) return index;

		int m = (l + h) / 2;
		if (a[m] == x) index = m;
		else if (x < a[m])
			index = binarySearch(a, l, m - 1, x);
		else index = binarySearch(a, m + 1, h, x);

		return index;
	}

	/* Binary Search Template I:
	 * Template #1 is the most basic and elementary form of Binary Search.Template #1 is used to search for an element or 
	 * condition which can be determined by accessing a single index in the array.
	 * Initial Condition: left = 0, right = length-1
	 * Termination: left > right
	 * Searching Left: right = mid-1
	 * Searching Right: left = mid+1
	 */
	public int binarySearchTemplate1(int[] nums,
			int target) {
		if (nums == null || nums.length == 0)
			return -1;

		int left = 0, right = nums.length - 1;
		while (left <= right) {
			// Prevent (left + right) overflow
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		// End Condition: left > right
		return -1;
	}

	/* Binary Search Template II:
	 * Template #2 is an advanced form of Binary Search. It is used to search for an element or condition which requires
	 * accessing the current index and its immediate right neighbor's index in the array.
	 * Initial Condition: left = 0, right = length
	 * Termination: left == right
	 * Searching Left: right = mid
	 * Searching Right: left = mid+1
	 */
	public int binarySearchTemplate2(int[] nums,
			int target) {
		if (nums == null || nums.length == 0)
			return -1;

		int left = 0, right = nums.length;
		while (left < right) {
			// Prevent (left + right) overflow
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		// Post-processing:
		// End Condition: left == right
		if (left != nums.length
				&& nums[left] == target)
			return left;
		return -1;
	}

	/* Binary Search Template III:
	 * Template #3 is another unique form of Binary Search. It is used to search for an element or condition which
	 * requires accessing the current index and its immediate left and right neighbor's index in the array.
	 * Initial Condition: left = 0, right = length
	 * Termination: left == right
	 * Searching Left: right = mid
	 * Searching Right: left = mid+1
	 */
	public int binarySearchTemplate3(int[] nums,
			int target) {
		if (nums == null || nums.length == 0)
			return -1;

		int left = 0, right = nums.length - 1;
		while (left + 1 < right) {
			// Prevent (left + right) overflow
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}

		// Post-processing:
		// End Condition: left + 1 == right
		if (nums[left] == target) return left;
		if (nums[right] == target) return right;
		return -1;
	}

	// Time Complexity: O(log3n)
	public static int ternarySearch1(int[] a,
			int x) {
		int n = a.length;
		int left = 0, right = n - 1, mid1, mid2;

		while (left <= right) {
			mid1 = left + ((right - left) / 3);
			mid2 = left
					+ (2 * (right - left) / 3); // or mid2 = mid1 + ((right - left) / 3);

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

	public static int ternarySearch2(int[] a,
			int x) {
		return ternarySearch(a, 0, a.length - 1,
				x);
	}

	public static int ternarySearch(int[] a,
			int l, int h, int x) {
		if (l > h) return -1;

		int mid1 = l + (h - l) / 3;
		int mid2 = l + (2 * (h - l)) / 3;
		if (a[mid1] == x) return mid1;
		else if (a[mid2] == x) return mid2;
		else if (x < a[mid1])
			return ternarySearch(a, l, mid1 - 1,
					x);
		else if (x > a[mid2])
			return ternarySearch(a, mid2 + 1, h,
					x);
		else return ternarySearch(a, mid1 + 1,
				mid2 - 1, x);
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
	public static int interpolationSearch(int[] a,
			int x) {
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
	public static int exponentialSearch(int[] a,
			int x) {
		int i, n = a.length - 1;

		for (i = 1; i <= n
				&& a[i] <= x; i = i * 2)
			;
		return binarySearch(a, i / 2,
				Math.min(i, n), x);
	}

	/* Returns index of x if present, else returns -1 */
	public static int fibonacciSearch(int arr[],
			int x, int n) {
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
			int i = Math.min(offset + fibMMm2,
					n - 1);

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
		if (fibMMm1 == 1 && arr[offset + 1] == x)
			return offset + 1;

		/*element not found. return -1 */
		return -1;
	}

	public static int ubiquitousBinarySearch(
			int[] a, int x) {
		int index = -1;

		/*Write a logic here*/

		return index;
	}

}
