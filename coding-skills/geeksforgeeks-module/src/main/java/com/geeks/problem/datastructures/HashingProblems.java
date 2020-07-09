package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashingProblems {

	/************** Introduction ****************/
	// HashSet, HashMap, Linked HashSet, Linked HAshMap - Refer JavaCollectionAPI.java

	/************** Easy Problems ****************/
	// Find whether an array is subset of another array
	// Approach1: Brute force Approach; Time Complexity: O(m*n)
	public boolean isSubsetArray1(int[] a1, int[] a2) {
		for (int i = 0; i < a2.length; i++) {
			for (int j = 0; j < a1.length; j++) {
				if (a2[i] == a1[j]) break;

				if (j == a1.length - 1) return false;
			}
		}
		return true;
	}

	// Approach2: Sort first array & Binary Search; Time Complexity: O(mLogm)
	public boolean isSubsetArray2(int[] a1, int[] a2) {
		Arrays.sort(a1);
		for (int i = 0; i < a2.length; i++) {
			if (Arrays.binarySearch(a1, a2[i]) <= -1) { return false; }
		}
		return true;
	}

	// Sort both arrays & merge; Time Complexity: O(mLogm + nLogn)
	/* Time Complexity: O(mLogm + nLogn) which is better than method 2. Please note that this will be the complexity if an nLogn 
	 * algorithm is used for sorting both arrays which is not the case in above code. In above code Quick Sort is sued and worst
	 * case time complexity of Quick Sort is O(n^2)*/
	public boolean isSubsetArray3(int[] a1, int[] a2) {
		int i = 0, j = 0;
		boolean flag = true;
		Arrays.sort(a1);
		Arrays.sort(a2);
		while (i < a1.length && j < a2.length) {
			if (a1[i] == a2[j]) {
				i++;
				j++;
			} else if (a1[i] < a2[j]) {
				i++;
			} else {
				flag = false;
				break;
			}
		}

		if (j < a2.length) flag = false;

		return flag;
	}

	// Using Hashset; Time Complexity:O(n)
	/* Note that method 1, method 2 and method 4 don’t handle the cases when we have duplicates in arr2[]. 
	 * For example, {1, 4, 4, 2} is not a subset of {1, 4, 2}, but these methods will print it as a subset.*/
	public boolean isSubsetArray4(int[] a1, int[] a2) {
		boolean flag = true;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < a1.length; i++)
			set.add(a1[i]);
		for (int i = 0; i < a2.length; i++) {
			if (!set.contains(a2[i])) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/************** Intermediate Problems ****************/

	public int zeroSumArrays1(int[] a) {
		int n = a.length;
		int[][] sum = new int[n][n];

		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j) sum[i][j] = a[i];
				else sum[i][j] = a[j] + sum[i][j - 1];

				if (sum[i][j] == 0) count++;

			}
		}
		return count;
	}

	public int zeroSumArrays2(int[] a) {
		int n = a.length;
		HashMap<Integer, Integer> map = new HashMap<>();

		map.put(0, 1);
		int count = 0, sum = 0;
		Integer value = null;
		for (int i = 0; i < n; i++) {
			sum += a[i];
			value = map.get(sum);
			if (value != null) {
				count += value;
				map.put(sum, value + 1);
			} else {
				map.put(sum, 1);
			}
		}
		return count;
	}

	// Returns true if arr[] has a subarray with sero sum
	public Boolean subArrayExists(int arr[]) {
		// Creates an empty hashMap hM
		HashMap<Integer, Integer> hM = new HashMap<Integer, Integer>();

		// Initialize sum of elements
		int sum = 0;

		// Traverse through the given array
		for (int i = 0; i < arr.length; i++) {
			// Add current element to sum
			sum += arr[i];

			// Return true in following cases
			// a) Current element is 0
			// b) sum of elements from 0 to i is 0
			// c) sum is already present in hash map
			if (arr[i] == 0 || sum == 0 || hM.get(sum) != null) return true;

			// Add sum to hash map
			hM.put(sum, i);
		}

		// We reach here only when there is no subarray with 0 sum
		return false;
	}

	public void maxLenZeroSubArray1(int[] a) {
		int n = a.length, sum = 0, maxLen = 0;
		for (int i = 0; i < n; i++) {
			sum = 0;
			for (int j = i; j < n; j++) {
				sum += a[j];
				if (sum == 0) {
					maxLen = Math.max(maxLen, j - i + 1);
				}
			}
		}
		System.out.println("Max Length:" + maxLen);
	}

	public void maxLenZeroSubArray2(int[] a) {
		int sum = 0, maxLen = 0;
		Integer prevSumIndex;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			sum += a[i];

			// If sum is zero Add max length by one directly
			if (sum == 0) maxLen = i + 1;

			prevSumIndex = map.get(sum);

			if (prevSumIndex == null) map.put(sum, i);
			else // If any element present in the map, then one of the sub array became zero
				maxLen = Math.max(maxLen, i - prevSumIndex);

		}
		System.out.println("Max Length:" + maxLen);
	}

	public void longestConsecutiveSubSeq1(int[] a) {
		Arrays.sort(a);
		// printArray(a);
		int max = 0, currSum = 1;
		for (int i = 1; i < a.length; i++) {

			if (a[i] == 175) {
				System.out.print(1);
			}
			if (a[i - 1] + 1 == a[i]) {
				currSum++;
			} else {
				if (max < currSum) max = currSum;
				currSum = 1;
			}
		}
		// Max value has taken to fix last consecutive numbers
		System.out.println("\nConsecutive seq length:" + Math.max(max, currSum));
	}

	public void longestConsecutiveSubSeq2(int[] a) {
		/*Create an empty hash.*/
		HashSet<Integer> set = new HashSet<>();

		/*Insert all array elements to hash.*/
		for (int i = 0; i < a.length; i++)
			set.add(a[i]);

		int j = 0, result = 0;
		for (int i = 0; i < a.length; i++) {
			/*Check if this element is the starting point of a subsequence.  To check this, we simply look for arr[i] - 1 
			in hash, if not found, then this is the first element a subsequence.*/
			if (!set.contains(a[i] - 1)) {
				j = a[i];

				/*If this element is a first element, then count number of elements in the consecutive starting with this element.*/
				while (set.contains(j))
					j++;

				/*If count is more than current result, then update result.*/
				if (result < j - a[i]) result = j - a[i];
			}
		}

		System.out.println(result);
	}

	/*
	 * Largest Range or Longest Consecutive Subsequence
	 */
	public static int[] longestConsecutiveSubSeq3(int[] array) {
		int[] result = new int[2];
		if (array.length == 0) return result;

		HashSet<Integer> set = new HashSet<>();
		for (int a : array)
			set.add(a);

		int range = Integer.MIN_VALUE, left, right;
		for (int a : array) {
			if (!set.contains(a)) continue;
			set.remove(a);
			left = a;
			while (set.contains(left - 1)) {
				set.remove(left - 1);
				left -= 1;
			}

			right = a;
			while (set.contains(right + 1)) {
				set.remove(right + 1);
				right += 1;
			}

			if (range < right - left) {
				result[0] = left;
				result[1] = right;
				range = right - left;
			}
		}

		return result;
	}

	/*
	 * sumA-a+b = sumB-b+a
	 * sumA- sumB = 2a-2b => sumA-sumB = 2(a-b) => a-b = (sumA - sumB)/2
	 */
	public static int isSwappingPairMakesSumEqual1(int[] a1, int m, int[] a2, int n) {
		int sum1 = sum(a1, m);
		int sum2 = sum(a2, n);
		int diff = Math.abs(sum1 - sum2);
		if (diff % 2 == 1) return -1;

		diff /= 2;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (diff == Math.abs(a1[i] - a2[j])) {
					System.out.println("Swapping Values are: " + a1[i] + ", " + a2[j]);
					return 1;
				}
			}
		}
		return -1;
	}

	private static int sum(int[] a, int n) {
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += a[i];
		return sum;
	}

	public static int isSwappingPairMakesSumEqual2(int[] a1, int m, int[] a2, int n) {
		int sum1 = sum(a1, m);
		int sum2 = sum(a2, n);

		Arrays.sort(a1);
		Arrays.sort(a2);

		int diff = Math.abs(sum1 - sum2);
		if (diff % 2 == 1) return -1;

		diff = diff / 2;
		int i = 0, j = 0;
		while (i < m && j < n) {
			int temp = Math.abs(a1[i] - a2[j]);
			if (diff == temp) {
				System.out.println("Swapping Values are: " + a1[i] + ", " + a2[j]);
				return 1;
			} else if (diff < temp) {
				i++;
			} else {
				j++;
			}
		}

		return -1;
	}

	void countDistinct(int A[], int k, int n) {
		for (int i = 0; i <= n - k; i++) {
			System.out.print(count(A, i, i + k) + " ");
		}
	}

	int count(int A[], int start, int end) {
		int count = 0;
		Set<Integer> set = new HashSet<>();
		for (int i = start; i < end; i++) {
			if (!set.contains(A[i])) {
				count++;
				set.add(A[i]);
			}
		}
		return count;
	}

	// Array Pair Sum Divisibility Problem
	public boolean isArrayPairDivisibleByK(int[] a, int k) {
		int n = a.length;
		if (n == 0 || n % 2 == 1) return false;

		Map<Integer, Integer> map = new HashMap<>();
		int rem = 0;
		for (int i = 0; i < n; i++) {
			rem = a[i] % k;
			if (map.get(rem) == null) map.put(rem, 1);
			else map.put(rem, map.get(rem) + 1);

		}

		for (Entry<Integer, Integer> entry : map.entrySet())
			System.out.println(entry.getKey() + " - " + entry.getValue());

		Integer value;
		for (int i = 0; i < n; i++) {
			rem = a[i] % k;
			value = map.get(rem);

			if (rem == 0) {
				if (value != null && value % 2 == 1) return false;
			} else if (2 * rem == k) {
				if (value != null && value % 2 == 1) return false;
			} else if (value != null && map.get(k - rem) != null && value != map.get(k - rem)) { return false; }
		}
		return true;
	}

	// Find all pairs with a given sum
	public void allPairsWithSum(int[] arr1, int[] arr2, int sum) {
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < arr2.length; i++)
			set.add(arr2[i]);

		for (int i = 0; i < arr1.length; i++) {
			if (set.contains(sum - arr1[i])) System.out.print(arr1[i] + " " + (sum - arr1[i]) + ", ");
		}
	}

	// Find first repeated character
	public String firstRepeatedChar(String str) {
		Set<Character> set = new HashSet<>();
		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (set.contains(ch)) return String.valueOf(ch);
			set.add(ch);
		}
		return "-1";
	}
	// Minimum indexed character

	// Check if two arrays are equal or not
	public int isTwoArraysSame(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) return 0;

		int n = arr1.length;

		Map<Integer, Integer> map = new HashMap<>();
		Integer count;
		for (int i = 0; i < n; i++) {
			count = map.get(arr2[i]);
			if (count == null) map.put(arr2[i], 1);
			else map.put(arr2[i], count + 1);
		}

		for (int i = 0; i < n; i++) {
			count = map.get(arr1[i]);
			if (count == null) return 0;
			if (count == 0) return 0;
			count--;
			map.put(arr1[i], count);
		}
		return 1;
	}

	// Uncommon characters
	public static void uncommonChars(String s1, String s2) {
		int[] count = new int[26];

		for (int i = 0; i < s1.length(); i++)
			count[s1.charAt(i) - 'a'] = 1;
		int index = 0;
		for (int i = 0; i < s2.length(); i++) {
			index = s2.charAt(i) - 'a';
			if (count[index] == 1 || count[index] == -1) count[index] = -1;
			else count[index] = 2;
		}

		for (int i = 0; i < 26; i++) {
			if (count[i] == 1 || count[i] == 2) System.out.print((char) (i + 'a'));
		}
		System.out.println();
	}

	// Smallest window in a string containing all the char of another string - Tricky Question

	// First element to occur k times
	public static int firstElementOccurKtimes(int[] arr, int k) {
		if (arr.length == 0) return -1;

		int n = arr.length;

		Map<Integer, Integer> map = new HashMap<>();
		Integer count;
		for (int i = 0; i < n; i++) {
			count = map.get(arr[i]);
			if (count == null) map.put(arr[i], 1);
			else map.put(arr[i], count + 1);
		}

		for (int i = 0; i < n; i++)
			if (map.get(arr[i]) == k) return arr[i];

		return -1;
	}

	public static List<List<String>> printItems(Map<String, Double> map, double total) {
		List<List<String>> result = new ArrayList<>();
		List<String> eachList = new ArrayList<>();
		for (Entry<String, Double> entry : map.entrySet()) {
			Double value = entry.getValue();
			String key = entry.getKey();
			map.remove(key);
			for (Entry<String, Double> entry2 : map.entrySet()) {
				Double sum = value + entry2.getValue();
				if (sum == total) {
					eachList.add(key);
					eachList.add(entry2.getKey());
					map.remove(entry2.getKey());
					break;
				} else if (sum < total) {
					eachList.add(entry2.getKey());
					map.remove(entry2.getKey());
				}
			}
			if (!eachList.isEmpty()) {
				result.add(eachList);
				result.stream().forEach(k -> System.out.print(k + ", "));
			}
		}
		return result;
	}

	// Check if frequencies can be equal - Read

	// Unique Word Abbreviation
	public boolean isUnique(Map<String, String> map, String word) {
		String abb = getAbbrevation(word);
		return (!map.containsKey(abb) || map.get(abb).equals(word));
	}

	public String getAbbrevation(String word) {
		int n = word.length();
		if (n <= 2) return word;

		return String.valueOf(word.charAt(0) + Integer.toString(n - 2) + word.charAt(n - 1));
	}

	public Map<String, String> buildDictionary(String[] strings) {
		Map<String, String> map = new HashMap<>();

		for (String str : strings) {
			String abb = getAbbrevation(str);
			// If there is any duplicate str, put empty or put str
			if (map.containsKey(abb)) map.put(abb, "");
			else map.put(abb, str);
		}

		return map;
	}

	/************** Hard Problems ****************/
	/*
	 * Longest Consecutive Sequence:
	 * 1.Brute Force: Check elements one by one for the consective elements -O(n^3)
	 * 2.Sorting and linear search - O(nlogn)
	 * 3.Use Hash set & up/down sequence search - O(n)
	 * 4.Union Find: 
	 */

	// Approach1:
	public int longestConsecutive1(int[] nums) {
		int longestSeq = 0, count, curr;
		for (int i = 0; i < nums.length; i++) {
			count = 1;
			curr = nums[i];
			while (contains(nums, curr + 1)) {
				count++;
				curr++;
			}

			longestSeq = Math.max(longestSeq, count);
		}

		return longestSeq;
	}

	private boolean contains(int[] arr, int num) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == num) return true;

		return false;
	}

	// Approach2:
	public int longestConsecutive2(int[] nums) {
		// Sort
		Arrays.sort(nums);

		// Linear Search:
		int longestSeq = 0, count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] + 1 == nums[i]) {
				count++;
			} else {
				longestSeq = Math.max(longestSeq, count);
				count = 1;
			}
		}

		return Math.max(longestSeq, count);
	}

	// Approach3:
	public int longestConsecutive3(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		// Add all the elements in set and also remove duplicates
		for (int num : nums)
			set.add(num);

		// Up/Down search
		int longestSeq = 0, up, down, curr;
		for (int i = 0; i < nums.length; i++) {
			curr = nums[i];
			if (!set.contains(curr)) continue;

			set.remove(curr);

			// Down Search: Decrement the element by one and search
			down = curr;
			while (set.contains(curr - 1)) {
				curr--;
				down--;
				set.remove(curr);
			}

			// Up Search: Decrement the element by one and search
			curr = nums[i];
			up = curr;
			while (set.contains(curr + 1)) {
				curr++;
				up++;
				set.remove(curr);
			}

			longestSeq = Math.max(longestSeq, up - down + 1);
		}

		return longestSeq;
	}

	/* Palindrome Pairs:
	 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so
	 * that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
	 */
	// Approach1: Using HashMap
	public List<List<Integer>> palindromePairs(String[] words) {
		Map<String, Integer> map = new HashMap<>(); // word, Index
		int n = words.length;
		List<List<Integer>> result = new ArrayList<>();

		for (int i = 0; i < n; i++)
			map.put(words[i], i);

		if (map.containsKey("")) {
			int index = map.get("");
			for (int i = 0; i < n; i++) {
				if (isPalindrome(words[i])) {
					if (i == index) continue;
					result.add(Arrays.asList(index, i));
					result.add(Arrays.asList(i, index));
				}
			}
		}

		for (int i = 0; i < n; i++) {
			String revStr = new StringBuilder(words[i]).reverse().toString();
			if (map.containsKey(revStr)) {
				int revIndex = map.get(revStr);
				if (i == revIndex) continue;
				result.add(Arrays.asList(i, revIndex));
			}
		}

		for (int i = 0; i < n; i++) {
			String curr = words[i];
			for (int j = 1; j < curr.length(); j++) {
				if (isPalindrome(curr.substring(0, j))) {
					String revStr = new StringBuilder(curr.substring(j)).reverse().toString();
					if (map.containsKey(revStr)) {
						int index = map.get(revStr);
						if (index == i) continue;
						result.add(Arrays.asList(index, i));
					}
				}
				if (isPalindrome(curr.substring(j))) {
					String revStr = new StringBuilder(curr.substring(0, j)).reverse().toString();
					if (map.containsKey(revStr)) {
						int index = map.get(revStr);
						if (index == i) continue;
						result.add(Arrays.asList(i, index));
					}
				}
			}
		}
		return result;
	}

	private boolean isPalindrome(String str) {
		int l = 0, r = str.length() - 1;
		while (l < r) {
			if (str.charAt(l) != str.charAt(r)) return false;
			l++;
			r--;
		}
		return true;
	}

	/* Group Shifted Strings: Given a string, we can "shift" each of its letter to its successive letter,
	 * for example:"abc" -> "bcd". We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz" 
	 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same 
	 * shifting sequence. For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
	 * A solution is: [ ["abc","bcd","xyz"], ["az","ba"], ["acef"], ["a","z"] ]
	 */
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String str : strings) {
			String bitMap = bitMap(str);
			if (!map.containsKey(bitMap)) map.put(bitMap, new ArrayList<>());
			map.get(bitMap).add(str);
		}

		for (String key : map.keySet()) {
			List<String> list = map.get(key);
			result.add(list);
		}
		result.stream().forEach(k -> System.out.println(k));
		return result;
	}

	public String bitMap(String str) {
		int[] arr = new int[str.length()];
		arr[0] = 0;
		for (int i = 1; i < str.length(); i++) {
			int diff = str.charAt(i) - str.charAt(0);
			arr[i] = diff > 0 ? diff : (diff % 26) + 26;
		}
		return Arrays.toString(arr);
	}

	/* Line Reflection: Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the
	 * given points. 
	 *  Example 1: Given points = [[1,1],[-1,1]], return true.
	 *  Example 2: Given points = [[1,1],[-1,-1]], return false.
	 */
	/*
	 * Solution: If there exists a line reflecting the points, then each pair of symmetric points will have their x coordinates
	 * adding up to the same value, including the pair with the maximum and minimum x coordinates.
	 * Note: For line parallel to x-axis do below approach for y(point[1] below) values
	 */
	public boolean isReflected(int[][] points) {
		Set<String> set = new HashSet<>();
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

		for (int[] point : points) {
			min = Math.min(min, point[0]);
			max = Math.max(max, point[0]);
			set.add(point[0] + "-" + point[1]);
		}
		int sum = min + max;

		for (int[] point : points)
			if (!set.contains(sum - point[0] + "-" + point[1])) return false;

		return true;
	}

	/************** Util Methods ****************/
	void printArray(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

}
