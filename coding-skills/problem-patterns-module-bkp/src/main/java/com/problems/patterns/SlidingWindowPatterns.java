package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import com.common.utilities.Utils;

public class SlidingWindowPatterns {

	/***************************** Type2: Sliding Window: String ******************************/
	/* Window Sliding Technique: 
	 * 	This technique shows how a nested for loop in few problems can be converted to single for loop and hence reducing the time 
	 *  complexity.
	 */

	/* Minimum Window Substring/Smallest window in a string containing all the char of another string:
	 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
	 * complexity O(n). 
	 * Example: Input: S = "ADOBECODEBANC", T = "ABC" Output: "BANC"
	 */
	public String minWindow1(String str, String pat) {
		int len1 = str.length(), len2 = pat.length();
		if (len1 == 0 || len2 == 0 || (len1 < len2))
			return "";

		int[] hashStr = new int[256];
		int[] hashPat = new int[256];

		for (int i = 0; i < len2; i++)
			hashPat[pat.charAt(i)]++;

		int l = 0, r = 0, minWindow = Integer.MAX_VALUE,
				minLeft = -1, count = 0, index = 0;
		// Move 'r' one by one from zero
		while (r < len1) {
			index = str.charAt(r); // Get the String index
			// Increase the char count in hashStr
			hashStr[index]++;
			if (hashPat[index] != 0
					&& hashStr[index] <= hashPat[index])
				count++;

			// Move 'l' from zero and update minwindow & the string map
			while (l <= r && count == len2) {
				if ((r - l + 1) < minWindow) {
					minWindow = r - l + 1;
					minLeft = l;
				}
				index = str.charAt(l); // Get the String index
				// Decrease the char count in strMap
				hashStr[index]--;
				if (hashStr[index] < hashPat[index])
					count--;
				l++;
			}
			r++;
		}
		return minLeft != -1
				? str.substring(minLeft,
						minLeft + minWindow)
				: "";
	}

	/* Longest Substring Without Repeating Characters:
	 * Given a string, find the length of the longest substring without repeating characters. 
	 * Example 1: Input:"abcabcbb" Output: 3 Explanation: The answer is "abc", with the length of 3. 
	 * Example 2: Input: "pwwkew" Output: 3 Explanation: The answer is "wke", with the length of 3. 
	 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
	 */
	// Approach1: Sliding Window solution using Array to store the data
	public int lengthOfLongestSubstring1(String s) {
		if (s.length() == 0) return 0;

		int l = 0, r = 0, maxLen = 0, n = s.length();
		int[] countArr = new int[128];
		while (r < n) {
			char ch = s.charAt(r);
			if (countArr[ch] == 0) {
				countArr[ch]++;
				r++;
				maxLen = Math.max(maxLen, r - l);
			} else {
				while (countArr[ch] > 0) {
					countArr[s.charAt(l)]--;
					l++;
				}
			}
		}
		return maxLen;
	}

	// Similar to previous
	public int lengthOfLongestSubstring2(String s) {
		int l = 0, r = 0, maxLen = 0, n = s.length(),
				counter = 0;
		int[] countArr = new int[128];
		while (r < n) {
			char ch = s.charAt(r++);
			if (countArr[ch]++ > 0) counter++;
			while (counter > 0 && l < n)
				if (countArr[s.charAt(l++)]-- > 1)
					counter--;

			maxLen = Math.max(maxLen, r - l);
		}
		return maxLen;
	}

	// Approach2: Sliding Window solution using map to store the data
	public int lengthOfLongestSubstring3(String s) {
		if (s.length() == 0) return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int l = 0, r = 0, maxLen = 0, n = s.length();
		while (r < n) {
			char ch = s.charAt(r);
			if (!map.containsKey(ch)) {
				map.put(ch, r);
				r++;
				maxLen = Math.max(maxLen, r - l);
			} else {
				l = Math.max(l, map.get(ch) + 1); // If char already present, move left index to repeating char
				map.remove(ch);
			}
		}
		return maxLen;
	}

	/*
	 * Longest Substring with At Most Two Distinct Characters:
	 * Given a string, find the longest substring that contains only two unique characters. For example, given "abcbbbbcccbdddadacb",
	 * the longest substring that contains 2 unique character is "bcbbbbcccb".
	 */
	public int lengthOfLongestSubstringTwoDistinct(
			String s) {
		int max = 0, start = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			map.put(ch, map.getOrDefault(ch, 0) + 1);
			if (map.size() > 2) {
				max = Math.max(max, i - start);

				while (map.size() > 2) {
					char t = s.charAt(start);
					int count = map.get(t);
					if (count > 1) {
						map.put(t, count - 1);
					} else {
						map.remove(t);
					}
					start++;
				}
			}
		}

		return Math.max(max, s.length() - start);
	}

	/*
	 * Longest Substring with At Most K Distinct Characters:
	 */
	public int lengthOfLongestSubstringKDistinct(String s,
			int k) {
		int result = 0;
		int i = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int j = 0; j < s.length(); j++) {
			char ch = s.charAt(i);

			map.put(ch, map.getOrDefault(ch, 0) + 1);

			if (map.size() <= k) {
				result = Math.max(result, j - i + 1);
			} else {
				while (map.size() > k) {
					char l = s.charAt(i);
					int count = map.get(l);
					if (count == 1) {
						map.remove(l);
					} else {
						map.put(l, count - 1);
					}
					i++;
				}
			}

		}

		return result;
	}

	/*Find All Anagrams in a String/Anagram Pattern Search:
	 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s. Strings consists of
	 * lowercase English letters only and the length of both strings s and p will not be larger than 20,100. The order
	 * of output does not matter. 
	 * Example 1: Input: s: "cbaebabacd" p: "abc" Output: [0, 6] Explanation: The substring with start index = 0 is "cba", 
	 * which is an anagram of "abc". The substring with start index = 6 is "bac", which is an anagram of "abc".
	 */
	public List<Integer> findAnagrams(String s, String p) {
		int[] hash = new int[26];
		for (char c : p.toCharArray())
			hash[c - 'a']++;

		int l = 0, r = 0;
		List<Integer> result = new ArrayList<>();

		while (r < s.length()) {
			int index = s.charAt(r) - 'a';
			hash[index]--;

			// if we have seen the character too many times or it is a character that is not in the pattern, rewind the
			// starting index
			while (hash[index] < 0) {
				hash[s.charAt(l) - 'a']++;
				l++;
			}

			if (r - l + 1 == p.length()) {
				result.add(l);
				hash[s.charAt(l) - 'a']++;
				l++;
			}
			r++;
		}
		return result;
	}

	/*Permutation in String:
	 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other
	 * words, one of the first string's permutations is the substring of the second string. 
	 * Example 1: Input:s1 = "ab" s2 = "eidbaooo" Output:True Explanation: s2 contains one permutation of s1 ("ba").
	 */
	// Using Sliding Window
	public boolean checkInclusion(String s1, String s2) { // Here s1 is Pattern, s2 is whole string
		int l = 0, r = 0;
		int[] hash = new int[26];

		for (int i = 0; i < s1.length(); i++)
			hash[s1.charAt(i) - 'a']++;

		while (r < s2.length()) {
			int index = s2.charAt(r) - 'a';
			hash[index]--;

			while (hash[index] < 0) {
				hash[s2.charAt(l) - 'a']++;
				l++;
			}

			if (r - l + 1 == s1.length()) return true;
			r++;
		}

		return false;
	}

	/* Longest Repeating Character Replacement: 
	 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with
	 * another letter at most k times. Find the length of a longest substring containing all repeating letters you can
	 * get after performing the above operations. 
	 * Example: Input: s = "AABABBA", k = 1 Output: 4 Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA". 
	 * The substring "BBBB" has the longest repeating letters, which is 4.
	 */
	// Sliding Window approach
	// Time Complexity: O(n)
	public int characterReplacement1(String s, int k) {
		int[] charCount = new int[26];
		int l = 0, r = 0, maxCharCount = 0, maxLength = 0;
		while (r < s.length()) {
			maxCharCount = Math.max(maxCharCount,
					++charCount[s.charAt(r) - 'A']);
			while ((r - l + 1) - maxCharCount > k) {
				charCount[s.charAt(l) - 'A']--;
				l++;
			}
			maxLength = Math.max(maxLength, r - l + 1);
			r++;
		}
		return maxLength;
	}

	// Time Complexity: O(26n)
	public int characterReplacement2(String s, int k) {
		int n = s.length();
		if (n < k) return 0;

		// Added this to improve the performance; Solution will work without this
		int[] countArray = new int[26];
		for (int i = 0; i < n; i++)
			countArray[s.charAt(i) - 'A']++;

		int maxLen = 0;
		for (int i = 0; i < 26; i++) {
			char ch = (char) (i + 'A'); // Get the char one by one from A to Z;(input has only upper case)
			int l = 0, r = 0, count = 0;

			if (countArray[ch - 'A'] == 0) continue;

			while (r < n) {
				if (s.charAt(r) != ch) // if char mismatches, increase the count
					count++;

				while (k < count) { // Slide the window
					if (s.charAt(l) != ch) // Here, id char mismatches decrease the count
						count--;
					l++;
				}

				maxLen = Math.max(maxLen, r - l + 1); // Find the max length
				r++;
			}
		}

		return maxLen;
	}

	/* Substring with Concatenation of All Words:
	 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices
	 * of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening
	 * characters. 
	 * Example: Input: s = "barfoothefoobarman", words = ["foo","bar"] Output: [0,9] 
	 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively. The output order does 
	 * not matter, returning [9,0] is fine too.
	 */
	public List<Integer> findSubstring(String s,
			String[] words) {
		List<Integer> result = new ArrayList<>();
		if (s == null || s.length() == 0
				|| words.length == 0)
			return result;

		HashMap<String, Integer> map = new HashMap<>();

		for (String word : words)
			map.put(word, map.getOrDefault(word, 0) + 1);

		int n = s.length(), len = words[0].length(),
				size = words.length;

		for (int i = 0; i <= n - len * size; i++) {
			HashMap<String, Integer> copy = new HashMap<>(
					map);
			for (int j = 0; j < size; j++) {
				String sub = s.substring(i + j * len,
						i + j * len + len);
				if (copy.containsKey(sub)) {
					if (copy.get(sub) == 1)
						copy.remove(sub);
					else copy.put(sub, copy.get(sub) - 1);
					if (copy.isEmpty()) {
						result.add(i);
						break;
					}
				} else {
					break;
				}
			}
		}

		return result;
	}

	/***************************** Type3: Sliding Window: Array ******************************/
	/* Maximum Subarray - Kadane’s Algorithm:
	 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
	 * sum and return its sum. 
	 * Example: Input: [-2,1,-3,4,-1,2,1,-5,4], Output: 6 Explanation: [4,-1,2,1] has the largest sum = 6.
	 */
	public int maxSubArray(int[] nums) {
		if (nums.length == 0) return 0;

		int sum = 0, maxSum = Integer.MIN_VALUE;
		for (int num : nums) {
			sum += num;
			maxSum = Math.max(sum, maxSum);
			if (sum < 0) sum = 0;

		}
		return maxSum;
	}

	/*
	 * Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate the sum of that subset.
	 */
	// Recursive slow solution.
	public int maxSubsetSum1(int arr[], int index) {
		if (index == 0) {
			return arr[0];
		} else if (index == 1) {
			return Math.max(arr[0], arr[1]);
		}
		return Math.max(
				maxSubsetSum1(arr, index - 2) + arr[index],
				maxSubsetSum1(arr, index - 1));
	}

	public int maxSubsetSum(int[] arr) {
		if (arr.length == 0) return 0;
		int incl = 0, excl = 0, temp = 0;
		for (int a : arr) {
			temp = incl;
			incl = Math.max(incl, excl + a);
			excl = temp;
		}
		return incl;
	}

	/* Maximum Product Subarray - Kadane’s Algorithm:
	 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which
	 * has the largest product. Example 1: Input: [2,3,-2,4] Output: 6 Explanation: [2,3] has the largest product 6.
	 */
	public int maxProduct(int[] nums) {
		if (nums.length == 0) return 0;
		int max = nums[0], min = nums[0], result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int tempMax = max;
			max = Utils.max(max * nums[i], min * nums[i],
					nums[i]);
			min = Utils.min(tempMax * nums[i],
					min * nums[i], nums[i]);
			result = Math.max(max, result);
		}
		return result;
	}

	/* Maximum Average Subarray I, II/Maximum average subarray of size k:
	 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum
	 * average value. And you need to output the maximum average value. 
	 * Example 1: Input: [1,12,-5,-6,50,3], k = 4 Output: 12.75 Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
	 */
	public double findMaxAverage(int[] arr, int k) {
		if (arr.length == 0 || arr.length < k) return 0;

		int sum = 0;
		double maxAvg = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (i < k) sum += arr[i];
			else {
				maxAvg = Math.max(maxAvg, (double) sum / k);
				sum -= arr[i - k];
				sum += arr[i];
			}
		}

		return Math.max(maxAvg, (double) sum / k);
	}

	/* Max Consecutive one I:
	 * Given a binary array, find the maximum number of consecutive 1s in this array. Example 1: Input: [1,1,0,1,1,1]
	 * Output: 3
	 */
	public int findMaxConsecutiveOnes(int[] nums) {
		int count = 0, maxCount = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				count++;
			} else {
				maxCount = Math.max(maxCount, count);
				count = 0;
			}
		}

		return Math.max(maxCount, count);
	}

	/* Max Consecutive one II & III:
	 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
	 * Example 1: Input: [1,0,1,1,0] Output: 4 Explanation: Flip the first zero will get the the maximum number of
	 * consecutive 1s. After flipping, the maximum number of consecutive 1s is 4.
	 */
	public int findMaxConsecutiveOnesII1(int[] nums) {
		int max = 0, zero = 0, k = 1; // flip at most k zero
		int l = 0, r = 0;
		while (r < nums.length) {
			if (nums[r] == 0) zero++;

			while (zero > k)
				if (nums[l++] == 0) zero--;

			max = Math.max(max, r - l + 1);
			r++;
		}
		return max;
	}

	/* Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store
	 * all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
	 */
	public int findMaxConsecutiveOnesII2(int[] nums) {
		int max = 0, k = 1; // flip at most k zero
		Queue<Integer> zeroIndex = new LinkedList<>();
		for (int l = 0, h = 0; h < nums.length; h++) {
			if (nums[h] == 0) zeroIndex.offer(h);
			if (zeroIndex.size() > k)
				l = zeroIndex.poll() + 1;
			max = Math.max(max, h - l + 1);
		}
		return max;
	}

	/* Minimum Size Subarray Sum/Find Minimum Length Sub Array With Sum K:
	 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray
	 * of which the sum >= s. If there isn't one, return 0 instead. Example: Input: s = 7, nums = [2,3,1,2,4,3] Output:
	 * 2 Explanation: the subarray [4,3] has the minimal length under the problem constraint.
	 */
	public int minSubArrayLen(int s, int[] nums) {
		if (nums.length == 0) return 0;

		int l = 0, r = 0, sum = 0,
				minLen = Integer.MAX_VALUE;
		while (r < nums.length) {
			sum += nums[r];
			while (sum >= s && l <= r) {
				// if (sum == s)
				minLen = Math.min(minLen, r - l + 1);
				sum -= nums[l];
				l++;
			}
			r++;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	/*
	 * Subarray with given sum:Given an unsorted array A of size N of non-negative integers, find a continuous sub-array 
	 * which adds to a given number.
	 */
	public void subArrayWithGivenSum(int[] arr, int sum) {

		int currSum = arr[0], start = 0, end = 0,
				n = arr.length, i;
		for (i = 1; i < n; i++) {
			currSum += arr[i];

			while (currSum > sum && start < i - 1) {
				currSum = currSum - arr[start];
				start++;
			}

			if (currSum == sum) {
				end = i;
				break;
			}
		}

		if (sum == currSum) System.out
				.println((start + 1) + " " + (end + 1));
		else System.out.println("-1");
	}

	/*
	 * Maximum Size Subarray Sum Equals k/Largest subarray with 0 sum:
	 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
	 */
	public int maxSubArrayLen(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int max = 0, sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sum == k) max = Math.max(max, i + 1);
			int diff = sum - k;
			if (map.containsKey(diff))
				max = Math.max(max, i - map.get(diff));
			if (!map.containsKey(sum)) map.put(sum, i);

		}

		return max;
	}

	/**
	 * Sliding Window - Prefix Sum Patterns
	 * If we consider all prefix sums, we can notice that there is a subarray with 0 sum when :
	 * 	1) Either a prefix sum repeats or
	 * 	2) Or prefix sum becomes 0.
	 */
	/* Subarray Sum Equals K/Zero Sum Subarrays
	 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
	 * equals to k. Example 1: Input:nums = [1,1,1], k = 2 Output: 2
	 */
	public int subarraySum(int[] nums, int k) {
		int n = nums.length, count = 0, sum = 0;
		Map<Integer, Integer> map = new HashMap<>(); // key-sum & val -count(number of continuous subarrays with sum k)
		map.put(0, 1); // Initialize with count 1;
		for (int i = 0; i < n; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k))
				count += map.get(sum - k);

			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;

	}

	/*
	 * Zero Sum Subarrays: - Count no zero sum sub arrays
	 * You are given an array A of size N. You need to print the total count of sub-arrays having their sum equal to 0
	 */
	public static int zeroSumArrays(int[] a) {
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

	/*
	 * Zero Sum Subarray:
	 */
	public int[] zeroSumSubArray(int[] arr) {
		Map<Integer, Integer> sumMap = new HashMap<>();// Sum, Index

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (sumMap.containsKey(sum)) {
				int oldIndex = sumMap.get(sum);
				return Arrays.copyOfRange(arr, oldIndex + 1,
						i + 1); // Sum range from next index of sum to curr index
			} else {
				sumMap.put(sum, i);
			}
		}

		return null;
	}

	/*
	 * Maximum Sum of Subarray Close to K (or) Longest Subarray having sum of elements atmost ‘k’:
	 * Given an array, find the maximum sum of subarray close to k but not larger than k
	 */
	// Approach1: Using Sliding Window
	public int getLargestSumCloseToK1(int[] arr, int k) {
		int sum = 0, n = arr.length, len = 0, maxLen = 0;

		for (int i = 0; i < n; i++) {
			if ((sum + arr[i]) <= k) { // If adding current element doesn't cross limit add it to current window
				sum += arr[i];
				len++;
			} else if (sum != 0) { // Else, remove first element of current window.
				sum = sum - arr[i - len] + arr[i];
			}

			maxLen = Math.max(len, maxLen);
		}
		return maxLen;
	}

	// Approach1: Using HashSet Ceiling Method
	public int getLargestSumCloseToK2(int[] arr, int k) {
		int sum = 0;
		TreeSet<Integer> set = new TreeSet<Integer>();
		int maxLen = Integer.MIN_VALUE;
		set.add(0);

		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];

			Integer ceiling = set.ceiling(sum - k);
			if (ceiling != null) {
				maxLen = Math.max(maxLen, sum - ceiling);
			}

			set.add(sum);
		}

		return maxLen;
	}

	/*Count distinct elements in every window:
	 * Given an array A[] of size N and an integer K. Your task is to complete the function countDistinct() which prints
	 * the count of distinct numbers in all windows of size k in the array A[].
	 */
	public void countDistinct(int A[], int k, int n) {
		Map<Integer, Integer> map = new HashMap<>(); // Number, count
		Integer value;
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				value = map.get(A[i - k]);
				if (value > 1) map.put(A[i - k], --value);
				else map.remove(A[i - k]);
			}

			value = map.get(A[i]);
			if (value == null) value = 0;
			map.put(A[i], ++value);

			if (i >= k - 1)
				System.out.print(map.size() + " ");
		}

	}

	/* Sliding Window Maximum/Maximum of all subarrays of size k:
	 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the
	 * very right. You can only see the k numbers in the window. Each time the sliding window moves right by one
	 * position. Return the max sliding window.
	 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3; Output: [3,3,5,5,6,7] 
	 * 
	 * Solution:
	 * 	1. BruteForce: O(nk)
	 *  2. Balanced BST(TreeMap): O(nlogk)
	 *  3.Sliding Window: O(n)
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums.length == 0 || k > nums.length)
			return new int[0];

		int n = nums.length;
		int[] result = new int[n - k + 1];
		// Queue to store the index of the elements; Max element index should be retained in the deque front.
		Deque<Integer> deque = new LinkedList<>();

		for (int i = 0; i <= n; i++) {
			if (i >= k) result[i - k] = nums[deque.peek()]; // or initialize index=0 and increment->result[index++]

			if (i == n) break;

			// If 'i' reaches the size k, then Remove the top element
			if (!deque.isEmpty() && i - deque.peek() == k)
				deque.poll();

			// Keep removing the smaller element from the last in deque
			while (!deque.isEmpty()
					&& nums[i] > nums[deque.peekLast()])
				deque.removeLast();

			deque.addLast(i);
		}

		return result;
	}

	/* Sliding Window Motivation Problem: To understand the Sliding Window Concepts
	 *   Find the sum of subarrays of size k in a given array
	 * 
	 * Solution:
	 *   BruteForce Algorithm: O(nk)
	 *   Sliding Window Algorithm: O(n)
	 */
	public int[] printSubarraySumRangeK(int[] arr, int k) {
		int sum = 0, n = arr.length;
		int[] result = new int[n - k + 1];
		for (int i = 0; i <= n; i++) {
			if (i >= k) {
				result[i - k] = sum; // or initialize index=0 and increment->result[index++]
				sum -= arr[i - k];
			}

			if (i == n) break;

			sum += arr[i];
		}

		return result;
	}

	/*
	Min Max Riddle - Deque
	Max Sum of Rectangle No Larger Than K
	Shortest Unsorted Continuous Subarray
	Sliding Window Median - Heap
	 */

	/*
	 * Max Min:
	 * You will be given a list of integers arr and a single integer k. You must create an array of length k
	 *  from elements of arr such that its unfairness is minimized. Call that array subArr. 
	 *  Unfairness of an array is calculated as
	 *  	max(subArr) - min(subArr);
	 *  	Where:
	 *  		- max denotes the largest integer in subArr
	 *   		- min denotes the smallest integer in subArr*/
	int maxMin(int k, int[] nums) {
		if (nums.length == 0 || k > nums.length) return 0;
		Arrays.sort(nums);

		int n = nums.length, maxDiff = Integer.MAX_VALUE;
		// Queue to store the index of the elements; Max element index should be
		// retained in the deque front.
		Deque<Integer> minDeque = new LinkedList<>();
		Deque<Integer> maxDeque = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			// If 'i' reaches the size k, then Remove the top element
			if (!minDeque.isEmpty()
					&& i - minDeque.peek() == k)
				minDeque.poll();
			if (!maxDeque.isEmpty()
					&& i - maxDeque.peek() == k)
				maxDeque.poll();

			// Keep removing the smaller element from the last in deque
			while (!minDeque.isEmpty()
					&& nums[i] < nums[minDeque.peekLast()])
				minDeque.removeLast();
			while (!maxDeque.isEmpty()
					&& nums[i] > nums[maxDeque.peekLast()])
				maxDeque.removeLast();
			minDeque.addLast(i);
			maxDeque.addLast(i);

			if (i + 1 >= k) maxDiff = Math.min(maxDiff,
					nums[maxDeque.peek()]
							- nums[minDeque.peek()]);
		}
		return maxDiff;
	}
}
