package com.problems.patterns.cheatsheet;

import java.util.ArrayList;
import java.util.List;

public class ModifiedBinarySearchPatterns {

	/*
	 * Search for a Range or 
	 * Find First and Last Position of Element in Sorted Array 
	 * or Total Occurrences Of K In A Sorted Array
	 */
	// Approach1:
	public int[] searchRange(int[] nums, int target) {
		int[] range = { -1, -1 };

		int leftIndex = binarySearch1(nums, target, 0, nums.length - 1, true);

		if (leftIndex == -1) return range;

		int rightIndex = binarySearch1(nums, target, leftIndex, nums.length - 1, false);
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	// Binary Search to find the extreme left & right based on the flag
	public int binarySearch1(int[] nums, int x, int l, int h, boolean leftFlag) {
		int m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == x) { // Modification Here
				if (leftFlag && m > 0 && nums[m - 1] == x) {
					h = m - 1;
				} else if (!leftFlag && m < nums.length - 1 && nums[m + 1] == x) {
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
		int left = searchLeft(nums, 0, nums.length - 1, target);

		if (nums[left] != target) return range;

		// Search Right:
		int right = searchRight(nums, left, nums.length - 1, target);

		range[0] = left;
		range[1] = right;

		return range;
	}

	public int searchLeft(int[] nums, int l, int h, int target) {
		int m;
		while (l < h) {
			m = l + (h - l) / 2;
			if (target > nums[m]) l = m + 1;
			else h = m;
		}
		return l;
	}

	public int searchRight(int[] nums, int l, int h, int target) {
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

		if (leftIndex == nums.length || nums[leftIndex] != target) return range;

		int rightIndex = binarySearch3(nums, target, false);
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	// Binary Search to find the extreme left &
	// right based on the flag
	public int binarySearch3(int[] nums, int x, boolean left) {
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

		int left = searchLeft(nums, 0, nums.length - 1, target);

		if (nums[left] != target) return new int[] { -1, -1 };

		// Modification: Check target+1
		int right = searchRight(nums, left, nums.length - 1, target);

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
			else if (m < nums.length - 1 && nums[m] < nums[m + 1]) l = m + 1;
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
			if ((m % 2 == 0 && nums[m] == nums[m + 1]) || (m % 2 == 1 && nums[m - 1] == nums[m])) l = m + 1;
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
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
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
	public int searchRotatedSortedArray1(int[] nums, int target) {
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
	public boolean searchRotatedSortedArray2(int[] nums, int target) {

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

		int leftIndex = binarySearch1(nums, target, 0, nums.length - 1, true);

		if (leftIndex == -1) return range;

		int rightIndex = binarySearch1(nums, target, leftIndex, nums.length - 1, false);
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	// Binary Search to find the extreme left & right based on the flag
	public int binarySearch1(int[] nums, int x, int l, int h, boolean leftFlag) {
		int m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == x) { // Modification
				if (leftFlag && m > 0 && nums[m - 1] == x) {
					h = m - 1;
				} else if (!leftFlag && m < nums.length - 1 && nums[m + 1] == x) {
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
		int l = 0, h = nums.length - 1, m = 0;
		// Search Left:
		while (l < h) {
			m = l + (h - l) / 2;
			if (target > nums[m]) l = m + 1;
			else h = m;
		}

		if (nums[l] != target) return range;

		range[0] = l; // or h

		// Search Right:And retains the left index
		h = nums.length - 1;
		while (l < h) {
			// Modification: Make mid biased to the right
			m = l + (h - l) / 2 + 1;
			if (target < nums[m]) h = m - 1;
			else l = m;
		}

		range[1] = l; // or h

		return range;
	}

	// Approach3:
	public int[] searchRange3(int[] nums, int target) {
		int[] range = { -1, -1 };
		int leftIndex = binarySearch3(nums, target, true);

		if (leftIndex == nums.length || nums[leftIndex] != target) return range;

		// Modification: use rightIndex-1
		int rightIndex = binarySearch3(nums, target, false) - 1;
		range[0] = leftIndex;
		range[1] = rightIndex;

		return range;
	}

	public int binarySearch3(int[] nums, int x, boolean left) {
		// Modification: use h = nums.length
		int l = 0, h = nums.length, m = 0;
		while (l < h) {
			m = l + (h - l) / 2;
			// Modification
			if (nums[m] == x) {
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

	// Approach4:
	public int[] searchRange4(int[] A, int target) {
		int start = firstGreaterEqual(A, target);

		if (start == A.length || A[start] != target) return new int[] { -1, -1 };

		// Modification Here: Check target+1
		return new int[] { start, firstGreaterEqual(A, target + 1) - 1 };
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

	// Find Peak Element
	public int findPeakElement(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (m > 0 && nums[m - 1] > nums[m]) h = m - 1;
			else if (m < nums.length - 1 && nums[m] < nums[m + 1]) l = m + 1;
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
			if ((m % 2 == 0 && nums[m] == nums[m + 1]) || (m % 2 == 1 && nums[m - 1] == nums[m])) l = m + 1;
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
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		// Modification here: h = arr.length-k
		int l = 0, h = arr.length - k, m = 0;

		while (l < h) {
			m = l + (h - l) / 2;
			if (x - arr[m] <= arr[m + k] - x) h = m;
			else l = m + 1;
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < k; i++)
			result.add(arr[i + l]);

		return result;
	}

	// Search in Rotated Sorted Array
	public int searchRotatedSortedArray1(int[] nums, int target) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target) return m;

			if (nums[m] < nums[h]) {
				if (target > nums[m] && target <= nums[h]) {
					l = m + 1;
				} else {
					h = m - 1;
				}
			} else {
				if (target >= nums[l] && target < nums[m]) {
					h = m - 1;
				} else {
					l = m + 1;
				}
			}
		}
		return -1;
	}

	// Search in Rotated Sorted Array II
	public boolean searchRotatedSortedArray2(int[] nums, int target) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target) return true;

			if (nums[m] < nums[h]) {
				if (target > nums[m] && target <= nums[h]) {
					l = m + 1;
				} else {
					h = m - 1;
				}
			} else if (nums[m] > nums[h]) {
				if (target >= nums[l] && target < nums[m]) {
					h = m - 1;
				} else {
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

}
