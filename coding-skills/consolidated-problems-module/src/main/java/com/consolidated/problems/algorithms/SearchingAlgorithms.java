package com.consolidated.problems.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class SearchingAlgorithms {
	/************************* Type1: Modified Binary Search Alg Problems ***********************/
	/*
	 * Search for a Range or 
	 * Find First and Last Position of Element in Sorted Array 
	 * or Total Occurrences Of K In A Sorted Array
	 */
	// Approach1:
	public int[] searchRange(int[] nums, int target) {
		int[] range = { -1, -1 };

		int leftIndex = binarySearch1(nums, target, 0,
				nums.length - 1, true);

		if (leftIndex == -1) return range;

		int rightIndex = binarySearch1(nums, target,
				leftIndex, nums.length - 1, false);
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	// Binary Search to find the extreme left & right based on the flag
	public int binarySearch1(int[] nums, int x, int l,
			int h, boolean leftFlag) {
		int m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == x) { // Modification Here
				if (leftFlag && m > 0 && nums[m - 1] == x) {
					h = m - 1;
				} else if (!leftFlag && m < nums.length - 1
						&& nums[m + 1] == x) {
							l = m + 1;
						} else {
							return m;
						}
			} else if (x < nums[m]) {
				h = m - 1;
			} else {
				l = m + 1;
			}
		}
		return -1;
	}

	// Approach2:
	/* Why does this trick work? When we use mid = (i+j)/2, the mid is rounded to the lowest integer. In other words, 
	 * mid is always biased towards the left. This means we could have i == mid when j - i == mid, but we NEVER have 
	 * j == mid. So in order to keep the search range moving, you must make sure the new i is set to something different 
	 * than mid, otherwise we are at the risk that i gets stuck. But for the new j, it is okay if we set it to mid, since 
	 * it was not equal to mid anyways. Our two rules in search of the left boundary happen to satisfy these requirements, 
	 * so it works perfectly in that situation. Similarly, when we search for the right boundary, we must make sure i won't
	 * get stuck when we set the new i to i = mid. The easiest way to achieve this is by making mid biased to the right, 
	 * i.e. mid = (i+j)/2+1.
	 */
	public int[] searchRange2(int[] nums, int target) {
		int[] range = { -1, -1 };
		if (nums.length == 0) return range;

		// Search Left:
		int left = searchLeft(nums, 0, nums.length - 1,
				target);

		if (nums[left] != target) return range;

		// Search Right:
		int right = searchRight(nums, left, nums.length - 1,
				target);

		range[0] = left;
		range[1] = right;

		return range;
	}

	public int searchLeft(int[] nums, int l, int h,
			int target) {
		int m;
		while (l < h) {
			m = l + (h - l) / 2;
			if (target > nums[m]) l = m + 1;
			else h = m;
		}
		return l;
	}

	public int searchRight(int[] nums, int l, int h,
			int target) {
		int m;
		while (l < h) {
			// Modification:Make mid biased to the right
			m = l + (h - l) / 2 + 1;
			if (target < nums[m]) h = m - 1;
			else l = m;
		}
		return l;
	}

	// Approach3:
	public int[] searchRange3(int[] nums, int target) {
		int[] range = { -1, -1 };
		int leftIndex = binarySearch3(nums, target, true);

		if (leftIndex == nums.length
				|| nums[leftIndex] != target)
			return range;

		int rightIndex = binarySearch3(nums, target, false);
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	// Binary Search to find the extreme left &
	// right based on the flag
	public int binarySearch3(int[] nums, int x,
			boolean left) {
		// Modification: use h = nums.length
		int l = 0, h = nums.length, m = 0;
		while (l < h) {
			m = l + (h - l) / 2;
			// Modification here
			if (nums[m] == x) {
				if (left) h = m;
				else l = m + 1;
			} else if (x < nums[m]) {
				h = m;
			} else {
				l = m + 1;
			}
		}
		return l - 1;
	}

	// Approach4:
	public int[] searchRange4(int[] nums, int target) {
		if (nums.length == 0) return new int[] { -1, -1 };

		int left = searchLeft(nums, 0, nums.length - 1,
				target);

		if (nums[left] != target)
			return new int[] { -1, -1 };

		// Modification: Check target+1
		int right = searchRight(nums, left, nums.length - 1,
				target);

		return new int[] { left, right - 1 };
	}

	// find the first number that is greater than or equal to target.
	// could return A.length if target is greater than A[A.length-1].
	// actually this is the same as lower_bound in C++ STL.
	private int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			// low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				// should not be mid-1 when A[mid]==target.
				// could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
		return low;
	}

	// Find Peak Element
	public int findPeakElement(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (m > 0 && nums[m - 1] > nums[m]) h = m - 1;
			else if (m < nums.length - 1
					&& nums[m] < nums[m + 1])
				l = m + 1;
			else return m;
		}
		return 0;
	}

	// Search Insert Position
	public int searchInsert(int[] a, int target) {
		int l = 0, h = a.length, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;
			if (a[m] < target) {
				l = m + 1;
			} else {
				h = m;
			}
		}
		return l;
	}

	// Single Element in a Sorted Array
	public int singleNonDuplicate(int[] nums) {
		int l = 0, h = nums.length - 1;
		while (l < h) {
			int m = (l + h) / 2;
			if ((m % 2 == 0 && nums[m] == nums[m + 1])
					|| (m % 2 == 1
							&& nums[m - 1] == nums[m]))
				l = m + 1;
			else h = m;
		}

		return nums[l];
	}

	// Another Binary Search Approach
	public int singleNonDuplicate3(int[] nums) {
		// binary search
		int n = nums.length, lo = 0, hi = n / 2;
		while (lo < hi) {
			int m = (lo + hi) / 2;
			if (nums[2 * m] != nums[2 * m + 1]) hi = m;
			else lo = m + 1;
		}
		return nums[2 * lo];
	}

	// Find the Duplicate Number
	// Approach4: Binary Search - Time:O(nlogn)
	public int findDuplicate2(int[] nums) {
		int low = 1, high = nums.length - 1;
		while (low <= high) {
			int mid = (int) (low + (high - low) * 0.5);
			int cnt = 0;
			for (int a : nums) {
				if (a <= mid) ++cnt;
			}
			if (cnt <= mid) low = mid + 1;
			else high = mid - 1;
		}
		return low;
	}

	// Find K Closest Elements
	public List<Integer> findClosestElements(int[] arr,
			int k, int x) {
		// Modification here: h = arr.length-k
		int l = 0, h = arr.length - k, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;
			// Modification: condition is difference b/w target-mid
			if (x - arr[m] <= arr[m + k] - x) {
				h = m;
			} else {
				l = m + 1;
			}
		}

		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < k; i++)
			result.add(arr[i + l]);

		return result;
	}

	// Search in Rotated Sorted Array
	public int searchRotatedSortedArray1(int[] nums,
			int target) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target) return m;

			if (nums[m] < nums[h]) {
				if (target > nums[m] && target <= nums[h]) { // Check target between m to h, if so l = m+1
					l = m + 1;
				} else { // Reverse of if condition
					h = m - 1;
				}
			} else {
				if (target >= nums[l] && target < nums[m]) { // Check target between l to m, if so h = m-1
					h = m - 1;
				} else { // Reverse of if condition
					l = m + 1;
				}
			}
		}
		return -1;
	}

	// Search in Rotated Sorted Array II
	public boolean searchRotatedSortedArray2(int[] nums,
			int target) {

		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target) return true;

			if (nums[m] < nums[h]) {
				if (target > nums[m] && target <= nums[h]) { // Check target between m to h, if so l = m+1
					l = m + 1;
				} else { // Reverse of if condition
					h = m - 1;
				}
			} else if (nums[m] > nums[h]) {
				if (target >= nums[l] && target < nums[m]) { // Check target between l to m, if so h = m-1
					h = m - 1;
				} else { // Reverse of if condition
					l = m + 1;
				}
			} else {
				h--;
			}
		}
		return false;
	}

	// Find Minimum in Rotated Sorted Array
	public int findMinRotatedSortedArray1(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;

			if (nums[m] > nums[h]) l = m + 1;
			else h = m;
		}
		return nums[h];
	}

	// Find Minimum in Rotated Sorted Array II
	public int findMinRotatedSortedArray2(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;

			if (nums[m] > nums[h]) l = m + 1;
			else if (nums[m] < nums[h]) h = m;
			else h--;
		}
		return nums[h]; // nums[l]
	}

	/************** Clean up below *******************/
	/****************************** BS-Template-I *************************/
	// Simple Binary Search Algorithm:
	public int binarySearch(int[] a, int x) {
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

	/* Search Insert Position:
	 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where
	 * it would be if it were inserted in order.
	 */
	public int searchInsert2(int[] a, int target) {
		int l = 0, h = a.length, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;
			if (a[m] < target) {
				l = m + 1;
			} else {
				h = m;
			}
		}

		return l;
	}

	/* Search in Rotated Sorted Array:
	 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand. (i.e.,
	 * [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). You are given a target value to search. If found in the array
	 * return its index, otherwise return -1. You may assume no duplicate exists in the array.
	 */
	public int search(int[] nums, int target) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target) return m;
			else if (nums[m] <= nums[h]) {
				// Check target between m to h, if so l = m+1
				if (target > nums[m] && target <= nums[h])
					l = m + 1;
				else h = m - 1;
			} else {
				// Check target between l to m, if so h = m-1
				if (target >= nums[l] && target < nums[m])
					h = m - 1;
				else l = m + 1;
			}
		}
		return -1;

	}

	public boolean searchDuplicate(int[] nums, int target) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target) return true;

			if (nums[m] < nums[h]) {
				if (target > nums[m] && target <= nums[h]) { // Check target between m to h, if so l = m+1
					l = m + 1;
				} else { // Reverse of if condition
					h = m - 1;
				}
			} else if (nums[m] > nums[h]) {
				if (target >= nums[l] && target < nums[m]) { // Check target between l to m, if so h = m-1
					h = m - 1;
				} else { // Reverse of if condition
					l = m + 1;
				}
			} else {
				h--;
			}
		}
		return false;
	}

	/* Single Element in a Sorted Array:
	 * Given a sorted array consisting of only integers where every element appears twice except for one element which
	 * appears once. Find this single element that appears only once. 
	 * Example 1: Input: [1,1,2,3,3,4,4,8,8] Output: 2
	 */
	/* Solution using Binary Search: Here 2 conditions,       
	 *  1. mid index m is even, then check m & m+1 index data - if both are same non duplicate present in 2nd half;
	 *     Reason: mid index is even, then there are even no of elements in both sides, so m & m+1 is same, 
	 *     then odd no elemes in 2nd half. Non duplicate will be in 2nd half
	 *  2. mid index m is odd, then check m-1 & m index data - if both are same non duplicate present in 2nd half; 
	 *     Reason: mid index is odd, then there are odd no of elements in both sides, so m & m-1 is same, 
	 *     then it will be odd no elems.  
	 */
	public int singleNonDuplicate2(int[] nums) {
		int l = 0, h = nums.length - 1;
		while (l < h) {
			int m = (l + h) / 2;
			// Non duplicate present in second half
			if ((m % 2 == 0 && nums[m] == nums[m + 1])
					|| (m % 2 == 1
							&& nums[m - 1] == nums[m]))
				l = m + 1;
			else h = m; // Non duplicate present in first half
		}

		return nums[l];
	}

	/****************************** BS-Template-II *************************/
	// Binary Search Algorithm: Find the left most similar value
	public int firstBadVersion(int n) {
		int l = 1, h = n, m = 0;
		while (l < h) {
			m = l + (h - l) / 2;
			if (isBadVersion(m)) h = m;
			else l = m + 1;
		}
		return l;
	}

	private boolean isBadVersion(int n) {
		return (n == 3); // Here you can mock any data
	}

	/* Find Peak Element:
	 * A peak element is an element that is greater than its neighbors. Given an input array nums, where nums[i] !=
	 * nums[i+1], find a peak element and return its index. The array may contain multiple peaks, in that case return
	 * the index to any one of the peaks is fine. You may imagine that nums[-1] = nums[n] = -Infinity. 
	 * Example 1: Input: nums = [4,3,1,6,4] Output: 1 or 5
	 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where
	 * the peak element is 6.
	 */

	// Linear Algorithm: Time Complexity:O(n)
	public int findPeakElement1(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < nums[i - 1]) return i - 1;
		}
		return nums.length - 1;
	}

	// Binary Search Algorithm: Time Complexity:O(logn)
	public int findPeakElement2(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (m > 0 && nums[m - 1] > nums[m]) h = m - 1;
			else if (m < nums.length - 1
					&& nums[m] < nums[m + 1])
				l = m + 1;
			else return m;
		}
		return 0;
	}

	/* Find Minimum in Rotated Sorted Array:
	 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand. (i.e.,
	 * [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). Find the minimum element.
	 */
	// Linear Solution: Time Complexity:O(n)
	public int findMin1(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1]) return nums[i + 1];
		}

		return nums[0];
	}

	// Using Binary Search: Time Complexity: O(logn)
	public int findMin2(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;

			if (nums[m] > nums[h]) l = m + 1;
			else h = m;
		}
		return nums[h]; // nums[l]
	}

	/*
	 * Find Minimum in Rotated Sorted Array II:  The array may contain duplicates.
	 */
	public int findMinWithDuplicate(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;

			if (nums[m] > nums[h]) l = m + 1;
			else if (nums[m] < nums[h]) h = m;
			else h--;
		}
		return nums[h]; // nums[l]
	}

	/****************************** BS-Template-III *************************/
	/*Search for a Range/Find First and Last Position of Element in Sorted Array:
	 * Given a sorted array of integers, find the starting and ending position of a given target value. Your algorithm's
	 * runtime complexity must be in the order of O(log n). If the target is not found in the array, return [-1, -1]. For
	 * example, given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
	 * 
	 * Similar Problems: Total Occurrences Of K In A Sorted Array/Search Sorted Array For First Occurrence Of K
	 */
	// Time Complexity: O(2logn) = O(logn)
	public int[] searchRange5(int[] nums, int target) {
		int[] range = { -1, -1 };
		int leftIndex = binarySearch(nums, target, true);

		if (leftIndex == nums.length
				|| nums[leftIndex] != target)
			return range;

		int rightIndex = binarySearch(nums, target, false)
				- 1;
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	// Binary Search to find the extreme left & right based on the flag
	public int binarySearch(int[] nums, int x,
			boolean left) {
		int l = 0, h = nums.length, m = 0;
		while (l < h) {
			m = l + (h - l) / 2;
			if (x == nums[m]) {
				if (left) h = m;
				else l = m + 1;
			} else if (x < nums[m]) {
				h = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}

	/* Count number of occurrences (or frequency) in a sorted array:
	 *    1.Bruteforce Approach: Time: O(n)
	 *    2.Binary Search Approach: O(logn+count)
	 *    3.Improved BS Approach: O(logn) 
	 */

	public int count2(int[] arr, int x) {
		int n = arr.length;
		int ind = binarySearch(arr, x);

		// If element is not present
		if (ind == -1) return 0;

		// Count elements on left side.
		int count = 1, left = ind - 1;
		while (left >= 0 && arr[left] == x) {
			count++;
			left--;
		}

		// Count elements on right side.
		int right = ind + 1;
		while (right < n && arr[right] == x) {
			count++;
			right++;
		}

		return count;
	}

	public int count3(int arr[], int x) {

		/* get the index of first occurrence of x */
		int leftIndex = binarySearch(arr, x, true);

		/* If x doesn't exist in arr[] then return -1 */
		if (leftIndex == arr.length || arr[leftIndex] != x)
			return -1;

		int rightIndex = binarySearch(arr, x, false);

		/* return count */
		return rightIndex - leftIndex + 1;
	}

	public static int firstIndexOne(String str) {
		if (str.length() == 0) return -1;

		int l = 0, h = str.length() - 1, m = 0;
		while (l < h) {
			m = l + (h - l) / 2;
			if (str.charAt(m) == '0') l = m + 1;
			else h = m;
		}

		return str.charAt(l) == '1' ? l : -1;
	}

	public static int lastIndexOne(String str) {
		if (str.length() == 0) return -1;

		int l = 0, h = str.length() - 1, m = 0;
		while (l < h) {
			m = l + (h - l) / 2;
			if (str.charAt(m) == '1') l = m + 1;
			else l = m + 1;
		}

		return str.charAt(l) == '1' ? l : -1;
	}

	/* Find K Closest Elements:
	 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted
	 * in ascending order. If there is a tie, the smaller elements are always preferred.
	 *   Example 1:  Input: [1,2,3,4,5], k=4, x=3
	 *               Output: [1,2,3,4]
	 */
	//2-ptr approach:
	public List<Integer> findClosestElements1(int[] arr,
			int k, int x) {
		int lo = 0;
		int hi = arr.length - 1;
		while (hi - lo >= k) {
			if (Math.abs(arr[lo] - x) > Math
					.abs(arr[hi] - x)) {
				lo++;
			} else {
				hi--;
			}
		}
		List<Integer> result = new ArrayList<>(k);
		for (int i = lo; i <= hi; i++) {
			result.add(arr[i]);
		}
		return result;
	}

	// Binary searh approach - O(logn)
	public List<Integer> findClosestElements2(int[] arr,
			int k, int x) {
		int l = 0, h = arr.length - k, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;
			if (x - arr[m] <= arr[m + k] - x) {
				h = m;
			} else {
				l = m + 1;
			}
		}

		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < k; i++)
			result.add(arr[i + l]);

		return result;
	}
	//Using minheap priority queue
	//Time: O(n*logk)
	//Space: O(k)

	public List<Integer> findClosestElements32(int[] arr,
			int k,
			int x) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (pq.size() > k - 1) {
				if (Math.abs(x - arr[i]) < Math
						.abs(x - pq.peek())) {
					pq.poll();
					pq.add(arr[i]);
				}
			} else
				pq.add(arr[i]);
		}
		int s = pq.size();
		for (int i = 0; i < s; i++) {
			result.add(pq.remove());
		}
		return result;
	}

	// Interesting Approach: 
	public List<Integer> findClosestElements3(int[] arr,
			int k, int x) {
		// convert int[] to List<Integer> for better implementation simplicity
		List<Integer> list = Arrays.stream(arr).boxed()
				.collect(Collectors.toList());
		Collections.sort(list, (a, b) -> a == b ? a - b
				: Math.abs(a - x) - Math.abs(b - x));
		list = list.subList(0, k);
		Collections.sort(list);
		return list;
	}
}
