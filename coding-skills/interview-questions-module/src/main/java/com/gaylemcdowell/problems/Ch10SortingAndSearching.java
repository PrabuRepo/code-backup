package com.gaylemcdowell.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ch10SortingAndSearching {

	public static void main(String[] args) {
		Ch10SortingAndSearching ob = new Ch10SortingAndSearching();

		System.out.println("Sorted Merge: ");
		int[] a = { 4, 7, 9, 0, 0, 0 };
		int[] b = { 1, 5, 8 };
		ob.mergeSortedArrays(a, b);

		int[] arr = { 1, 4, 3, 7, 32000, 4, 8, 3, 8, 32000 };
		System.out.println("Print Duplicates: ");
		ob.findDuplicates(arr);

		System.out.println("Sorrted Search without size: ");
		int[] array = { 1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18 };
		Listy list1 = new Listy(array);
		for (int num : array)
			System.out.print(search(list1, num) + " ");
		System.out.println(search(list1, 15));

		System.out.println("Rank from Stream: ");
		int[] list = { 10, 4, 5, 17, 8, 3, 11, 27, 16, 5 };
		for (int i = 0; i < list.length; i++)
			track(list[i]);
		for (int i = 0; i < list.length; i++)
			System.out.print(root.getRank(list[i]) + " ");

	}

	/*
	 * 1.Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. 
	 * Write a method to merge B into A in sorted order.
	 */
	public void mergeSortedArrays(int[] a, int[] b) {
		int i = a.length - b.length - 1, j = b.length - 1, curr = a.length - 1;

		while (i >= 0 && j >= 0)
			a[curr--] = (a[i] > b[j]) ? a[i--] : b[j--];

		while (j >= 0)
			a[curr--] = b[j--];

		System.out.println("Result: " + Arrays.toString(a));
	}

	/*
	 * 2.Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next to each other.
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			String asc = ascOrder(str);
			// System.out.println(asc);
			List<String> list = null;
			if (map.containsKey(asc)) {
				list = map.get(asc);
				list.add(str);
			} else {
				list = new ArrayList<>();
				list.add(str);
			}
			map.put(asc, list);
		}

		List<List<String>> result = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	}

	public String ascOrder(String str) {
		char[] arr = new char[26];
		for (int i = 0; i < str.length(); i++) {
			arr[str.charAt(i) - 'a']++;
		}
		return new String(arr);
		/*
		char[] arr = str.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
		*/
	}

	/*
	 * 3.Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown number of times, write code to
	 * find an element in the array. You may assume that the array was originally sorted in increasing order. 
	 * EXAMPLE 
	 *  Input:findSin{lS, 16, 19, 2a, 25, 1, 3,4,5,7, la, 14}
	 *  Output: 8 (the index of 5 in the array)
	 */

	/*
	 * 4.Sorted Search, No Size: You are given an array-like data structure Listy which lacks a size method. It does, however, have
	 * an elementAt (i) method that returns the element at index i in 0(1) time. If i is beyond the bounds of the data structure,
	 * it returns -1. (For this reason, the data structure only supports positive integers.) Given a Listy which contains sorted, 
	 * positive integers, find the index at which an element x occurs. If x occurs multiple times, you may return any index.
	 */
	public static int binarySearch(Listy list, int value, int low, int high) {
		int mid;

		while (low <= high) {
			mid = (low + high) / 2;
			int middle = list.elementAt(mid);
			if (middle > value || middle == -1) {
				high = mid - 1;
			} else if (middle < value) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static int search(Listy list, int value) {
		int index = 1;
		while (list.elementAt(index) != -1 && list.elementAt(index) < value) {
			index *= 2;
		}
		return binarySearch(list, value, index / 2, index);
	}

	/*
	 * 5.Sparse Search: Given a sorted array of strings that is interspersed with empty strings, write a method to find the 
	 * location of a given string. 
	 * EXAMPLE
	 *  Input: ball,  { "at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "" };
	 *  Output: 4
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
	 * 6.Sort Big File: Imagine you have a 20 GB file with one string per line. Explain how you would sort the file.
	 */

	/*
	 * 7.Missing Int: Given an input file with four billion non-negative integers, provide an algorithm to generate an integer
	 * that is not contained in the file. Assume you have 1 GB of memory available for this task.
	 * FOLLOW UP 
	 * What if you have only 10MB of memory? Assume that all the values are distinct and we now have no more than one 
	 * billion non-negative integers.
	 */

	/*
	 * 8.Find Duplicates: You have an array with all the numbers from 1 to N, where N is at most 32,000. The array may have 
	 * duplicate entries and you do not know what N is. With only 4 kilobytes of memory available, how would you print all 
	 * duplicate elements in the array?
	 */

	public void findDuplicates(int[] arr) {
		BitSet bitSet = new BitSet(32000); // We can use Java's built in BitSet class also

		for (int i = 0; i < arr.length; i++) {
			if (bitSet.get(arr[i] - 1)) System.out.println(arr[i] + " ");
			else bitSet.set(arr[i] - 1);
		}
	}

	/*
	 * 9.Sorted Matrix Search: Given an M x N matrix in which each row and each column is sorted in ascending order, write 
	 * a method to find an element.
	 */

	/*
	 * 10.Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish to be able to look up the 
	 * rank of a number x (the number of values less than or equal to x). Implement the data structures and algorithms to support 
	 * these operations. That is, implement the method track (int x), which is called when each number is generated, and the 
	 * method getRankOfNumber(int x), which returns the number of values less than or equal to X (not including x itself). 
	 * EXAMPLE 
	 * Stream(inorderofappearance):5, 1,4,4, 5, 9, 7, 13, 3
	 * getRankOfNumber(l) = 0
	 * getRankOfNumber(3) = 1
	 * getRankOfNumber(4) = 3
	 */
	private static RankNode root = null;

	public static void track(int number) {
		if (root == null) root = new RankNode(number);
		else root.insert(number);
	}

	public static int getRankOfNumber(int number) {
		return root.getRank(number);
	}

	/*
	 * 11.Peaks and Valleys: In an array of integers, a "peak" is an element which is greater than or equal to the adjacent 
	 * integers and a "valley" is an element which is less than or equal to the adjacent integers. For example, in the 
	 * array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2} are valleys. Given an array of integers, sort the array into 
	 * an alternating sequence of peaks and valleys.
	 * EXAMPLE
	 *   Input: {S, 3, 1,2, 3} 
	 *   Output: {S, 1,3,2, 3}
	 */
}

class BitSet {
	int[] bitSet;

	public BitSet(int size) {
		bitSet = new int[(size >> 5) + 1]; // Divide by 32
	}

	public boolean get(int pos) {
		int word = pos >> 5; // Divide by 32
		int bit = pos & 0x1F; // Modulo 32
		return (bitSet[word] & (1 << bit)) != 0;
	}

	public void set(int pos) {
		int word = pos >> 5; // Divide by 32
		int bit = pos & 0x1F; // Modulo 32
		bitSet[word] |= (1 << bit);
		System.out.println(word + " " + bit + " " + bitSet[word]);
		// System.out.println(Arrays.toString(bitSet));
	}
}

class Listy {
	int[] array;

	public Listy(int[] arr) {
		array = arr.clone();
	}

	public int elementAt(int index) {
		if (index >= array.length) { return -1; }
		return array[index];
	}
}

class RankNode {
	public int		left_size	= 0;
	public RankNode	left;
	public RankNode	right;
	public int		data		= 0;

	public RankNode(int d) {
		data = d;
	}

	public void insert(int d) {
		if (d <= data) {
			if (left != null) left.insert(d);
			else left = new RankNode(d);
			left_size++;
		} else {
			if (right != null) right.insert(d);
			else right = new RankNode(d);
		}
	}

	public int getRank(int d) {
		if (d == data) return left_size;
		else if (d < data) {
			if (left == null) return -1;
			else return left.getRank(d);
		} else {
			int right_rank = right == null ? -1 : right.getRank(d);
			if (right_rank == -1) return -1;
			else return left_size + 1 + right_rank;
		}
	}
}