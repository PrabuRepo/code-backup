package com.web.leetcode.datastructures;

import java.util.Arrays;

public class SortingAndSearching {

	public static void main(String[] args) {
		System.out.println("Merge sorted array:");

		int[] nums1 = { -1, 0, 0, 3, 3, 3, 0, 0, 0 };
		int[] nums2 = { 1, 2, 2 };
		merge(nums1, 6, nums2, 3);
	}

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int index = 0;
		int num1Size = 0, num2Size = 0, k;
		while (index < (m + n) && num1Size < m && num2Size < n) {
			if (nums1[index] <= nums2[num2Size]) {
				num1Size++;
				index++;
			} else {
				k = m + n - 1;
				while (k > index) {
					nums1[k] = nums1[k - 1];
					k--;
				}
				nums1[index] = nums2[num2Size];
				num2Size++;
				index++;
			}
		}
		while (num2Size < n)
			nums1[index++] = nums2[num2Size++];

		System.out.println(Arrays.toString(nums1));

	}
}
