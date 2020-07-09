package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.common.utilities.Utils;

/* 
 * Number Problems: Its collection of Cyclic Sort, Marker Approach, Bitwise XOR, Floyd's Algorithm(Fast & Slow Ptr), some Binary Search alg problems
 *  
 */

public class NumberProblems {
	// 1.Find the Duplicate Number (easy)
	/* Solution:
	 * 1.BruteForce Approach: T:O(n^2)
	 * 2.Sort & Linear Search: T:O(nlogn)
	 * 3.HashSet: T:O(n), S:O(n)
	 * 4.Binary Search Alg: T:O(nlogn)
	 * Below are applicable only elements from 1 to n+1
	 * 5.Sum of elements: T:O(n); 
	 * 6.Cyclic Sort Approach: T:O(n)
	 * 7.Marker Approach:O(n)
	 * 8.Slow/Fast Floyd's Tortoise and Hare or cycle finding Alg: T:O(n)
	 */
	// Approach1:BruteForce
	public int findDuplicate1(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] == nums[j])
					return nums[i];
			}
		}
		return -1;
	}

	// Approach2: Using Sort: Time Complexity:O(nlogn)
	public int findDuplicate2(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++)
			if (nums[i] == nums[i - 1])
				return nums[i];
		return -1;
	}

	// Approach3: Using Hashset: Time Complexity:O(n); Space Complexity: O(n)
	public int findDuplicate3(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i]))
				return nums[i];
			set.add(nums[i]);
		}
		return -1;
	}

	// Approach4: Binary Search - Time:O(nlogn)
	public int findDuplicate4(int[] nums) {
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

	// Approach5:Sum of the elements: Time Complexity:O(nlogn)
	// It is working solely in the case where we have one duplicate.
	public int findDuplicate5(int[] nums) {
		int sum = 0, numSum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += i;
			numSum += nums[i];
		}
		return numSum - sum;
	}

	// Cyclic Sort Approach:
	public int findDuplicate6(int[] nums) {
		// TODO:
		return 0;
	}

	// Marker Approach
	public int findDuplicate7(int[] nums) {
		int val = 0, dupNum = 0;
		for (int i = 0; i < nums.length; i++) {
			val = Math.abs(nums[i]) - 1;
			if (nums[val] < 0) {
				dupNum = Math.abs(nums[i]);
				break;
			} else {
				nums[val] = -nums[val];
			}
		}
		// Reset the original elements
		for (int i = 0; i < nums.length; i++)
			if (nums[i] < 0)
				nums[i] = -nums[i];
		return dupNum;
	}

	// Floyd's Tortoise and Hare; Time Complexity:O(n); Space Complexity: O(1)
	public int findDuplicate8(int[] nums) {
		int tortoise = 0;
		int hare = 0;
		// Find the intersection point of the two runners.
		do {
			tortoise = nums[tortoise]; // slowPtr
			hare = nums[nums[hare]]; // fastPtr
		} while (tortoise != hare);

		// Find the "entrance" to the cycle.
		tortoise = 0;
		while (tortoise != hare) {
			hare = nums[hare];
			tortoise = nums[tortoise];
		}
		return tortoise;
	}

	// 5.Find all Duplicate Numbers (easy)
	/* Solution:
	 * Given an array of integers, 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.
	 * Find all the elements that appear twice in this array.
	 * Approach1: Brute Force Approach - TC:O(n^2)
	 * Approach2: Using HashSet - TC:O(n), SC:O(n)     
	 * Approach3: Using Sorting - TC:O(nlogn)
	 * Approach4: Cyclic Sort Approach- TC:O(n), SC:O(1)
	 * Approach5: Marker Approach- TC:O(n), SC:O(1)
	 */
	// Approach4: Cyclic Sort Approach
	public List<Integer> findDuplicates4(int[] nums) {
		// TODO:
		return null;
	}

	// Approach5: Marker Approach
	public List<Integer> findDuplicates5(int[] nums) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if (nums[val] < 0)
				result.add(Math.abs(nums[i]));
			else
				nums[val] = -nums[val];
		}
		// Reset the original elements
		for (int i = 0; i < nums.length; i++)
			nums[i] = -nums[i];
		return result;
	}
	/*
	 * 8.Find Duplicates: You have an array with all the numbers from 1 to N, where N is at most 32,000. The array may have 
	 * duplicate entries and you do not know what N is. With only 4 kilobytes of memory available, how would you print all 
	 * duplicate elements in the array?
	 */

	public void findDuplicatesInRange(int[] arr) {
		// Java's built in BitSet class
		BitSet bitSet = new BitSet(32000);

		for (int i = 0; i < arr.length; i++) {
			if (bitSet.get(arr[i] - 1))
				System.out.println(arr[i] + " ");
			else
				bitSet.set(arr[i] - 1);
		}
	}

	// 2. Find the Missing Number
	/* Find Missing Element/Missing Number:
	 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
	 *  1.Math Operation
	 *  2.Bit Manipulations(XOR)
	 *  3.Binary Search -O(nlogn); if it is already sorted-O(logn)
	 *  4.Using Cyclic Sort
	 */
	// Math Operation
	public int missingNumber1(int[] nums) {
		int n = nums.length;
		int sum = (n * (n + 1)) / 2;
		for (int i = 0; i < n; i++)
			sum -= nums[i];

		return sum;
	}

	// Using Bit manipulations - XOR Operation
	public int missingNumber2(int[] nums) {
		int xor = nums.length; // Assign Max size into result and then XOR for all elements

		for (int i = 0; i < nums.length; i++) {
			xor ^= i;
			xor ^= nums[i];
		}
		return xor;
	}

	// Binary Search Approach: O(nlogn)
	// Note: If data is already sorted, binary search will be efficient approach
	public int missingNumber3(int[] nums) { // binary search
		Arrays.sort(nums);
		int l = 0, h = nums.length, m = (l + h) / 2;
		while (l < h) {
			m = (l + h) / 2;
			// Modification: Compare m with m element.
			if (nums[m] > m)
				h = m;
			else
				l = m + 1;
		}
		return l;
	}

	public int missingNumber4(int[] nums) {
		int n = nums.length;
		int i = 0;
		while (i < n) {
			int valIndex = nums[i];
			if (valIndex < n && nums[valIndex] != nums[i])
				Utils.swap(nums, valIndex, i);
			else
				i++;
		}

		for (i = 0; i < nums.length; i++)
			if (nums[i] != i)
				return i;

		return n;
	}

	// 3.Find all Missing Numbers
	/*
	 * 1. Counting Sort Approach
	 * 2. Cyclic Sort Approach
	 * 3. Marker Approach
	 */
	// Counting Sort Approach
	public void missingNumbers1(int[] arr) {
		int[] count = new int[arr.length];
		for (int i : arr)
			count[i] = 1;
		System.out.println("missing numbers in given array");
		for (int i = 1; i < count.length; i++) {
			if (count[i] == 0) {
				System.out.println(i);
			}
		}
	}

	// Cyclic Sort Approach
	public void missingNumbers2(int[] array) {
		// TODO:
	}

	// Marker Approach
	public void missingNumbers3(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int value = Math.abs(array[i]);
			if (array[value - 1] > 0) {
				array[value - 1] = -array[value - 1];
			}
		}
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > 0) {
				System.out.println(i + 1);
			}
		}
	}

	/*
	 * Find Two Missing Numbers: 
	 * Given an array of n unique integers where each element in the array is in range [1, n]. The array has all distinct 
	 * elements and size of array is (n-2).Find the two missing numbers.
	 * 
	 * Approach1: Using Counting Sort(Boolean Array): – O(n) time complexity and O(n) Extra Space
	 * Approach2: Using Math Operation - O(n) time and O(1) space
	 * Approach3: Using Bit Manipulation - O(n) time and O(1) space
	 * Approach4: Combination of 2 & 3 - O(n) time and O(1) space
	 */

	public int[] findTwoMissingNumbers1(int arr[]) {
		int[] result = new int[2];
		int n = arr.length + 2;
		boolean[] flag = new boolean[n + 1];
		for (int a : arr) {
			flag[a] = true;
		}

		int index = 0;
		for (int i = 1; i < flag.length; i++)
			if (!flag[i]) {
				result[index++] = i;
			}

		return result;
	}

	// 2. Using Math Operation
	public static int[] findTwoMissingNumbers2(int arr[]) {
		int[] result = new int[2];
		int n = arr.length + 2;
		// 1.Sum of all elements in the array
		int sumOfN = sum(n);

		// 2.Sum of 2 missing numbers
		int sum = 0;
		for (int a : arr) {
			sum += a;
		}

		// 3. Sum of two missing numbers
		int sumOfMissingNum = sumOfN - sum;

		// 4. Average of 2 missing numbers
		int avgOfMissingElem = sumOfMissingNum / 2;

		// 5.Sum of natural numbers from 1 to avg
		int sumOfAvg = sum(avgOfMissingElem);

		// 6.Sum of array elements less than or equal to average
		int sumBeforeAvg = 0;
		for (int a : arr)
			if (a <= avgOfMissingElem)
				sumBeforeAvg += a;

		// First missing number
		result[0] = sumOfAvg - sumBeforeAvg;
		// Second missing number
		result[1] = sumOfMissingNum - result[0];
		return result;
	}

	private static int sum(int n) {
		return (n * (n + 1)) / 2;
	}

	// 2. Using Bit Manipulations
	public static int[] findTwoMissingNumbers3(int arr[]) {
		int[] result = new int[2];
		int n = arr.length + 2;

		int xor = 0;
		/* 1.Find XOR of all array elements and natural numbers from 1 to n; 
		  Note: Result is xor of two missing numbers; one element should be less than other. */
		for (int i = 1; i <= n; i++)
			xor ^= i;
		for (int a : arr)
			xor ^= a;

		/* Note: A bit is set in xor only if corresponding bits in X and Y are different. 
		 * Let us consider the rightmost set bit in XOR; which is used to split the elements into 2 sets.   
		 */

		// 2.Find the Right Most Set Bit
		int rightMostSetXor = xor & -xor; // xor & ~(xor - 1);

		/*3. Now if we XOR all the elements of arr[] and (1 to n) that have rightmost bit set(rightMostSetXor & elem > 0) 
		 * we will get one of the missing numbers, say result[0].
		 * Similarly, if we XOR all the elements of arr[] and (1 to n) that have rightmost bit not set(rightMostSetXor & elem == 0),
		 * we will get the other element, say result[1].
		 * 
		 */
		for (int i = 1; i <= n; i++) {
			if ((i & rightMostSetXor) > 0)
				result[0] ^= i;
			else
				result[1] ^= i;
		}
		for (int a : arr) {
			if ((a & rightMostSetXor) > 0)
				result[0] ^= a;
			else
				result[1] ^= a;
		}

		return result;
	}

	// Approach4: Combination of 2 & 3; Time: O(n), Space: O(1)
	public static int[] findTwoMissingNumbers4(int arr[]) {
		int[] result = new int[2];
		int n = arr.length + 2;

		// 1.Sum of all elements in the array
		int sumOfN = sum(n);

		// 2.Sum of 2 missing numbers
		int sum = 0;
		for (int a : arr) {
			sum += a;
		}

		// 3. Sum of two missing numbers
		int sumOfMissingNum = sumOfN - sum;

		// 4. Average of 2 missing numbers
		int avgOfMissingElem = sumOfMissingNum / 2;

		int totalLeftXor = 0, arrLeftXor = 0, totalRightXor = 0, arrRightXor = 0;

		/* 5.XOR of all the elements(1 to n and arr) which is less than qual to avgOfMissingElem. It gives one missing element
		 *   XOR of all the elements(1 to n and arr) which is greater than avgOfMissingElem. It gives another missing element
		 */
		for (int i = 1; i <= n; i++) {
			if (i <= avgOfMissingElem)
				totalLeftXor ^= i;
			else
				totalRightXor ^= i;
		}
		for (int a : arr) {
			if (a <= avgOfMissingElem)
				arrLeftXor ^= a;
			else
				arrRightXor ^= a;
		}

		result[0] = totalLeftXor ^ arrLeftXor;
		result[1] = totalRightXor ^ arrRightXor;
		return result;
	}

	/*
	 *  Single Number:
	 *  Given a non-empty array of integers, every element appears twice except for one. Find that single one.
	 */
	public int singleNumber(int[] nums) {
		int result = 0;
		for (int i = 0; i < nums.length; i++)
			result ^= nums[i];
		return result;
	}
	/*
	 * Find Odd Occurring Element:
	 *  Given an array of positive integers. All numbers occur even number of times except one number which occurs odd 
	 *  number of times. Find the number in O(n) time & constant space.
	 *  1. BruteForce - Check one by one -> O(n^2), Space: O(1)
	 *  2. Using HashMap - Time: O(n), Space: O(n)
	 *  3. Bit Manipulations(XOR) - Time: O(n), Space: O(1)
	 */

	public int getOddOccurrence2(int arr[], int n) {
		HashMap<Integer, Integer> hmap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			hmap.put(arr[i], hmap.getOrDefault(arr[i], 0) + 1);
		}

		for (Integer a : hmap.keySet()) {
			if (hmap.get(a) % 2 != 0)
				return a;
		}
		return -1;
	}

	public int getOddOccurrence3(int ar[], int n) {
		int i;
		int res = 0;
		for (i = 0; i < n; i++) {
			res = res ^ ar[i];
		}
		return res;
	}

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
			if ((m % 2 == 0 && nums[m] == nums[m + 1]) || (m % 2 == 1 && nums[m - 1] == nums[m]))
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

	/* Find Even Occurring Element:
	 * Given an integer array, one element occurs even number of times and all others have odd occurrences. Find the 
	 * element with even occurrences.
	 * 	1. BruteForce - Check one by one -> O(n^2), Space: O(1)
	 *  2. Using HashMap - Time: O(n), Space: O(n)
	 *  3. Bit Manipulations - Using Set and XOR - Time: O(n), Space: O(n)
	 */
	public int getEvenOccurrence2(int arr[], int n) {
		HashMap<Integer, Integer> hmap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			hmap.put(arr[i], hmap.getOrDefault(arr[i], 0) + 1);
		}

		for (Integer a : hmap.keySet())
			if (hmap.get(a) % 2 == 0)
				return a;

		return -1;
	}

	/* Approach: Set & XOR
	 * First we get all the unique numbers in the array using a set in O(N) time. Then we XOR the original array and the
	 * unique numbers all together. Result of XOR is the even occurring element.
	 * 
	 * For example, let’s say we’re given the following array: [2, 1, 3, 1]. First we get all the unique elements [1, 2, 3].
	 * Then we construct a new array from the original array and the unique elements by appending them together 
	 * [2, 1, 3, 1, 1, 2, 3]. We XOR all the elements in this new array. The result is 2^1^3^1^1^2^3 = 1.
	 */
	public int getEvenOccurrence3(int ar[], int n) {
		int result = 0;
		HashSet<Integer> set = new HashSet<>();

		for (int a : ar) {
			set.add(a);
			result ^= a;
		}

		for (Integer num : set)
			result ^= num;

		return result;
	}

	/*
	 * Find even occurring elements in an array of limited range:(0 to 63)
	 * Given an array that contains odd number of occurrences for all numbers except for a few elements which are present
	 * even number of times. Find the elements which have even occurrences in the array in O(n) time complexity and 
	 * O(1) extra space.
	 * Assume array contain elements in the range 0 to 63.
	 */
	public static void getEvenOccurrenceInRange1(int arr[], int n) {
		int xor = 0;
		List<Integer> result = new ArrayList<>();

		// Right shift 1 by value of each element in the array and take XOR of all the items
		for (int a : arr) {
			xor ^= (1 << a);
		}
		System.out.println(Integer.toBinaryString(xor));

		/* Each 1 in the i’th index from the right represents odd occurrence of element i.
		 * And each 0 in the i’th index from the right represents even or non-occurrence of element i in the array.
		 */
		for (int a : arr) {
			if ((xor & (1 << a)) == 0) {
				result.add(a);
				xor |= (1 << a); // Set '1' here to avoid duplicate
			}
		}

		result.stream().forEach(k -> System.out.print(k + " "));
	}
}