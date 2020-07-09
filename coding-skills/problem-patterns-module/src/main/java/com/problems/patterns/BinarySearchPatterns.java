package com.problems.patterns;

import java.util.Arrays;

/*
 * Binary Search Patterns:
 * 
 * Note: 
 * 	Mostly l=m+1, h=m -> This condition allows to move the index
 *  But l=m, h=m-1 -> This condition doesnt move and loop runs without exit. 
 *  
 * Overflow condition: 
 *   m = l + (h-l)/2
 */
public class BinarySearchPatterns {

	// Search Insert Position
	public int searchInsert(int[] nums, int target) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target)
				return m;
			else if (nums[m] < target)
				l = m + 1;
			else
				h = m - 1;
		}
		return l;
	}

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

	/*
	 * Search for a Range or 
	 * Find First and Last Position of Element in Sorted Array 
	 * or Total Occurrences Of K In A Sorted Array
	 */
	// Approach1:
	public int[] searchRange1(int[] nums, int target) {
		int left = findIndex(nums, target, true);
		if (left == -1)
			return new int[] { -1, -1 };
		int right = findIndex(nums, target, false);
		return new int[] { left, right };
	}

	private int findIndex(int[] a, int x, boolean left) {
		int l = 0, h = a.length - 1, m = 0, index = -1;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (a[m] == x) {
				index = m;
				if (left) {
					h = m - 1;
				} else {
					l = m + 1;
				}
			} else if (x < a[m]) {
				h = m - 1;
			} else {
				l = m + 1;
			}
		}

		return index;
	}

	public int[] searchRange2(int[] nums, int target) {
		int[] range = { -1, -1 };
		int leftIndex = binarySearch(nums, target, true);

		if (leftIndex == nums.length
				|| nums[leftIndex] != target)
			return range;

		// Modification: use rightIndex-1
		int rightIndex = binarySearch(nums, target, false)
				- 1;
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	public int binarySearch(int[] nums, int x,
			boolean left) {
		// Modification: use h = nums.length
		int l = 0, h = nums.length, m = 0;
		while (l < h) {
			m = l + (h - l) / 2;
			// Modification
			if (nums[m] == x) {
				if (left)
					h = m;
				else
					l = m + 1;
			} else if (x < nums[m]) {
				h = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}

	// Find Minimum in Rotated Sorted Array
	public int findMinRotatedSortedArray1(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;

			if (nums[m] > nums[h])
				l = m + 1;
			else
				h = m;
		}
		return nums[h];
	}

	// Find Minimum in Rotated Sorted Array II
	public int findMinRotatedSortedArray2(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;

			if (nums[m] > nums[h])
				l = m + 1;
			else if (nums[m] < nums[h])
				h = m;
			else
				h--;
		}
		return nums[h]; // nums[l]
	}

	// Search in Rotated Sorted Array
	public int searchRotatedSortedArray1(int[] nums,
			int target) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target)
				return m;

			if (nums[m] < nums[h]) { // right part is sorted
				if (target > nums[m] && target <= nums[h]) { // Check target between m to h, if so l = m+1
					l = m + 1;
				} else { // Reverse of if condition
					h = m - 1;
				}
			} else { // left part is sorted
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
			if (nums[m] == target)
				return true;

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

	/********BS Problems - Good to have***************/

	/*
	 * Single Element in a Sorted Array:
	 * Given a sorted array consisting of only integers where every element appears twice except
	 * for one element which appears once. Find this single element that appears only once.
	 * Expected Time Complexity: O(logn)
	 */
	public int singleNonDuplicate1(int[] nums) {
		int l = 0, h = nums.length - 1;
		while (l < h) {
			int m = (l + h) / 2;
			// Non duplicate present in second half
			if ((m % 2 == 0 && nums[m] == nums[m + 1])
					|| (m % 2 == 1
							&& nums[m - 1] == nums[m]))
				l = m + 1;
			else
				h = m; // Non duplicate present in first half
		}

		return nums[l];
	}

	// Another Binary Search Approach
	/*My solution using binary search. lo and hi are not regular index, but the pair index here. 
	 * Basically you want to find the first even-index number not followed by the same number.*/
	public int singleNonDuplicate2(int[] nums) {
		// binary search
		int n = nums.length, lo = 0, hi = n / 2;
		while (lo < hi) {
			int m = (lo + hi) / 2;
			if (nums[2 * m] != nums[2 * m + 1])
				hi = m;
			else
				lo = m + 1;
		}
		return nums[2 * lo];
	}

	// Binary Search Approach: O(nlogn)
	// Note: If data is already sorted, binary search will be efficient approach
	public int missingNumber(int[] nums) {
		Arrays.sort(nums);
		int l = 0, h = nums.length, m = (l + h) / 2;
		while (l < h) {
			m = (l + h) / 2;
			// Modification: Compare mth index with m element.
			if (nums[m] > m)
				h = m;
			else
				l = m + 1;
		}
		return l;
	}

	// Find the Duplicate Number
	// Approach4: Binary Search - Time:O(nlogn)
	public int findDuplicate2(int[] nums) {
		int low = 1, high = nums.length - 1;
		while (low <= high) {
			int mid = (int) (low + (high - low) * 0.5);
			int cnt = 0;
			for (int a : nums) {
				if (a <= mid)
					++cnt;
			}
			if (cnt <= mid)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return low;
	}

	// Find Peak Element
	public int findPeakElement(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (m > 0 && nums[m - 1] > nums[m])
				h = m - 1;
			else if (m < nums.length - 1
					&& nums[m] < nums[m + 1])
				l = m + 1;
			else
				return m;
		}
		return 0;
	}

}

class BinarySearch2 {
	/*
	 * Search for a Range or 
	 * Find First and Last Position of Element in Sorted Array 
	 * or Total Occurrences Of K In A Sorted Array
	 */
	// Approach1:
	public int[] searchRange1(int[] nums, int target) {
		int[] range = { -1, -1 };

		int leftIndex = binarySearch1(nums, target, 0,
				nums.length - 1, true);

		if (leftIndex == -1)
			return range;

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
			if (nums[m] == x) { // Modification
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
		if (nums.length == 0)
			return range;
		int l = 0, h = nums.length - 1, m = 0;
		// Search Left:
		while (l < h) {
			m = l + (h - l) / 2;
			if (target > nums[m])
				l = m + 1;
			else
				h = m;
		}

		if (nums[l] != target)
			return range;

		range[0] = l; // or h

		// Search Right:And retains the left index
		h = nums.length - 1;
		while (l < h) {
			// Modification: Make mid biased to the right
			m = l + (h - l) / 2 + 1;
			if (target < nums[m])
				h = m - 1;
			else
				l = m;
		}

		range[1] = l; // or h

		return range;
	}

	public int[] searchRange21(int[] nums, int target) {
		int[] range = { -1, -1 };

		int leftIndex = binarySearch1(nums, target, 0,
				nums.length - 1, true);

		if (leftIndex == -1)
			return range;

		int rightIndex = binarySearch1(nums, target,
				leftIndex, nums.length - 1, false);
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	// Approach4:
	public int[] searchRange4(int[] A, int target) {
		int start = firstGreaterEqual(A, target);

		if (start == A.length || A[start] != target)
			return new int[] { -1, -1 };

		// Modification Here: Check target+1
		return new int[] { start,
				firstGreaterEqual(A, target + 1) - 1 };
	}

	private int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			// low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

}
