package com.consolidated.problems.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

import com.common.model.HuffmanCode;
import com.common.model.Interval;
import com.common.utilities.Utils;

public class GreedyAlgorithms {

	/***************************************** Type1: Interval Problems **************************/
	/* Activity Selection Problem:
	 *  Select the maximum number of activities that can be performed by a single person, assuming that a person can only work
	 *  on a single activity at a time.
	 *  
	 *  Time Complexity: O(nlogn)
	 */
	public int maxActivities(int[] start, int[] end) {
		if (start.length == 0 || end.length == 0) return 0;
		int n = start.length;

		// Construct Interval Object;
		Interval[] intervals = new Interval[n];
		for (int i = 0; i < n; i++)
			intervals[i] = new Interval(start[i], end[i]);

		// Sort based on end time
		Arrays.sort(intervals, (a, b) -> (a.end - b.end));

		int activityCount = 1, l = 0, r = 1;
		while (r < n) {
			if (intervals[l].end <= intervals[r].start) {
				activityCount++;
				l = r;
			}
			r++;
		}

		return activityCount;
	}

	/*
	 * Max length chain/Maximum Length of Pair Chain: 
	 * Time Complexity: O(nlogn)
	 */
	public int findLongestChain(int[][] pairs) {
		int count = 1, i = 0, j = 1;
		Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
		while (i < pairs.length && j < pairs.length) {
			if (pairs[i][1] < pairs[j][0]) {
				count++;
				i = j;
				j++;
			} else {
				if (pairs[i][1] > pairs[j][1]) i = j;
				j++;
			}
		}

		return count;
	}

	/*
	 * N meetings in one room:
	 *   There are n meetings in the form of (S [ i ], F [ i ]) where S [ i ] is start time of meeting i and F [ i ] is finish time 
	 *  of meeting i.What is the maximum number of meetings that can be accommodated in the meeting room?
	 *  
	 */
	// Similar to Activity Selection Problem -2
	public void findMeetingsInOneRoom(int[] start, int[] end) {
		if (start.length == 0 || end.length == 0) return;
		int n = start.length;
		// Construct Interval Object;
		Interval[] intervals = new Interval[n];
		for (int i = 0; i < n; i++)
			intervals[i] = new Interval(start[i], end[i], i + 1);

		// Sort based on end time
		Arrays.sort(intervals, (a, b) -> (a.end - b.end));

		int l = 0, r = 1;
		System.out.print(intervals[l].order + " ");
		while (r < n) { // Starting Index
			if (intervals[l].end <= intervals[r].start) {
				System.out.print(intervals[r].order + " ");
				l = r;
			}
			r++;
		}

	}

	/* Meeting Rooms I: 
	 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
	 * (si<ei), determine if a person could attend all meetings. For example, Given [[0, 30],[5, 10],[15, 20]], return
	 * false.
	 */
	public boolean canAttendAllMeetings(Interval[] intervals) {
		if (intervals.length <= 1) return true;

		Arrays.sort(intervals, (ob1, ob2) -> ob1.start - ob2.start);

		for (int i = 0; i < intervals.length - 1; i++) {
			if (intervals[i].end > intervals[i + 1].start) return false;
		}
		return true;
	}

	/* Meeting Rooms II:
	 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum
	 * number of conference rooms required.
	 */
	// Approach1: Greedy Algorithm:
	public int minMeetingRooms1(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) return 0;

		int len = intervals.length;
		int[] startTime = new int[len];
		int[] endTime = new int[len];

		for (int i = 0; i < len; i++) {
			Interval curr = intervals[i];
			startTime[i] = curr.start;
			endTime[i] = curr.end;
		}

		// Sort the start and end time
		Arrays.sort(startTime);
		Arrays.sort(endTime);

		int activeMeetings = 0, numMeetingRooms = 0, i = 0, j = 0;
		while (i < len && j < len) {
			if (startTime[i] < endTime[j]) {
				activeMeetings++;
				numMeetingRooms = Math.max(numMeetingRooms, activeMeetings);
				i++;
			} else {
				activeMeetings--;
				j++;
			}
		}
		return numMeetingRooms;
	}

	// Approach2: Using Heap
	public int minMeetingRooms2(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) return 0;

		Arrays.sort(intervals, (ob1, ob2) -> ob1.start - ob2.start);

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.add(intervals[0].end);
		int count = 1;
		for (int i = 1; i < intervals.length - 1; i++) {
			if (intervals[i].start < queue.peek()) {
				count++;
			} else {
				queue.poll();
			}
			queue.add(intervals[i].end);
		}
		return count;
	}

	// Approach2: Using TreeMap:
	public int minMeetingRooms3(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) { return 0; }

		TreeMap<Integer, Integer> timesMap = new TreeMap<>(); // Here treeMap sorts the time in asc order
		for (Interval i : intervals) {
			timesMap.put(i.start, timesMap.getOrDefault(i.start, 0) + 1);
			timesMap.put(i.end, timesMap.getOrDefault(i.end, 0) - 1);
		}

		int count = 0, res = 0;
		for (int c : timesMap.values()) {
			count += c;
			res = Math.max(res, count);
		}

		return res;
	}

	/* Minimum Platforms:
	 * Given arrival and departure times of all trains that reach a railway station. Your task is to find the minimum
	 * number of platforms required for the railway station so that no train waits. Note: Consider that all the trains
	 * arrive on the same day and leave on the same day. Also, arrival and departure times must not be same for a train.
	 */
	// Using Simple Greedy Alg:
	public int minPlatformRequired1(int[][] arr, int[][] dep) {
		if (arr.length == 0) return 0;

		// arr, dep has 2 dim array, 0th index has time, 1st index has order/seq of the train
		Arrays.sort(arr, (a, b) -> a[0] - b[0]);
		Arrays.sort(dep, (a, b) -> a[0] - b[0]);

		int platform = 1, i = 1, j = 0, result = 1;
		// Merge
		while (i < arr.length && j < dep.length) {
			// Additional 'or' condition to handle the train arrives & departure at the same time
			if (arr[i][0] < dep[j][0] || (arr[i][0] == dep[j][0] && arr[i][1] == dep[j][1])) {
				platform++;
				i++;
				result = Math.max(result, platform);
			} else {
				platform--;
				j++;
			}
		}

		return result;
	}

	// Approach2: Using TreeMap
	public static int minPlatformRequired2(int[] arrv, int[] dep) {
		if (arrv.length == 0) return 0;

		TreeMap<Integer, Integer> map = new TreeMap<>();

		for (int i = 0; i < arrv.length; i++) {
			map.put(arrv[i], map.getOrDefault(arrv[i], 0) + 1);
			map.put(dep[i], map.getOrDefault(dep[i], 0) - 1);
		}

		int noOfPlatform = 0, count = 0;
		for (int key : map.keySet()) {
			count += map.get(key);
			noOfPlatform = Math.max(noOfPlatform, count);
		}
		return noOfPlatform;
	}

	/* Insert Interval:
	 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary). You may
	 * assume that the intervals were initially sorted according to their start times.
	 * Example: Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
	 *          Output: [[1,2],[3,10],[12,16]] Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
	 */
	// Approach1:
	public List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<>();
		// All the comments based on new Interval perspective
		for (Interval interval : intervals) {
			if (interval.end < newInterval.start) { // Right New Interval(No Overlap)->Eg:
													// interval:(1,2),newInterval:(4,8)
				result.add(interval);
			} else if (interval.start > newInterval.end) { // Left New Interval(No Overlap) -> Eg:
															// interval:(3,10),newInterval:(12,16)
				result.add(newInterval);
				newInterval = interval;
			} else if (interval.start <= newInterval.end || interval.end >= newInterval.start) {
				// Eg: RightOverLap->intl:(3,5),newIntl:(4,8) & LeftOverLap:intl:(6,8),newIntl:(4,7)
				// Eg: TotalOverLap->intl:(6,7),newIntl:(3,8) & intl:(2,10),newIntl:(5,7)
				newInterval = new Interval(Math.min(interval.start, newInterval.start),
						Math.max(interval.end, newInterval.end));
			}
		}
		result.add(newInterval);
		return result;
	}

	// Approach2: Simplified Code: No need to use additional memory. In place solution with little bit change.
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		int i = 0;
		while (i < intervals.size() && intervals.get(i).end < newInterval.start)
			i++;
		while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
			newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start),
					Math.max(intervals.get(i).end, newInterval.end));
			intervals.remove(i);
		}
		intervals.add(i, newInterval);
		return intervals;
	}

	// Approach3: Similar to Appraoch1; Binary Search:The best time is O(log(n)) and worst case time is O(n).
	public List<Interval> insert3(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<>();

		if (intervals.size() == 0) {
			result.add(newInterval);
			return result;
		}
		// Binary Search is used to find the starting position to insert the interval
		int startingIndex = binarySearch(intervals, newInterval);
		// Add all the intervals before the startinIndex in the result list
		result.addAll(intervals.subList(0, startingIndex));

		for (int i = startingIndex; i < intervals.size(); i++) {
			Interval interval = intervals.get(i);
			if (interval.end < newInterval.start) {
				result.add(interval);
			} else if (interval.start > newInterval.end) {
				result.add(newInterval);
				newInterval = interval;
			} else if (interval.end >= newInterval.start || interval.start <= newInterval.end) {
				newInterval = new Interval(Math.min(interval.start, newInterval.start),
						Math.max(newInterval.end, interval.end));
			}
		}

		result.add(newInterval);

		return result;
	}

	public int binarySearch(List<Interval> intervals, Interval newInterval) {
		int low = 0;
		int high = intervals.size() - 1;

		while (low < high) {
			int mid = low + (high - low) / 2;

			if (newInterval.start <= intervals.get(mid).start) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}

		return high == 0 ? 0 : high - 1;
	}

	/*
	 * Merge Intervals:
	 * Given a collection of intervals, merge all overlapping intervals.
	 * Example 1: Input: [[1,3],[2,6],[8,10],[15,18]]; Output: [[1,6],[8,10],[15,18]]
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1) return intervals;

		Collections.sort(intervals, Comparator.comparing(i -> i.start));

		// Merge the overlapping intervals
		List<Interval> result = new ArrayList<>();
		Interval prevInterval = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval currInterval = intervals.get(i);
			if (prevInterval.end >= currInterval.start) {
				prevInterval.end = Math.max(currInterval.end, prevInterval.end);
			} else {
				result.add(prevInterval);
				prevInterval = currInterval;
			}
		}
		result.add(prevInterval);

		return result;
	}

	/*
	 * Non-overlapping Intervals
	 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
	 * Input: [ [1,2], [2,3], [3,4], [1,3] ]	Output: 1
	 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
	 */
	public int eraseOverlapIntervals(Interval[] intervals) {
		Arrays.sort(intervals, Comparator.comparingInt(i -> i.end));
		int max = 0, lastend = Integer.MIN_VALUE;
		for (Interval in : intervals) {
			if (lastend <= in.start) {
				lastend = in.end;
				max++;
			}
		}
		return intervals.length - max;
	}

	/*************************** Type2: Optimal Solution Problem ******************/
	/* Minimum number of Coins:
	 * Given a value N, total sum you have. You have to make change for Rs. N, and there is infinite supply of each of the
	 * denominations in Indian currency
	 */
	private final int[] coins = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000 };

	public void minNoOfCoins(int sum) {
		if (sum < 1) return;

		int i = coins.length - 1;
		while (sum > 0) {
			if (sum - coins[i] >= 0) {
				System.out.print(coins[i] + " ");
				sum -= coins[i];
			} else {
				i--;
			}
		}
	}

	/*
	 * Maximize Toys:
	 * Given an array consisting cost of toys. Given an integer K depicting the amount with you. Maximize the number of toys you 
	 * can have with K amount.
	 */
	// Similar to Minimum number of Coins
	public static int maximizeToys(int[] prices, int k) {
		if (k < 1) return 0;

		Arrays.sort(prices);
		int count = 0;

		for (int i = 0; (k > 0 && i < prices.length); i++) {
			if (k - prices[i] >= 0) {
				k -= prices[i];
				count++;
			}
		}

		return count;
	}

	/*
	 * Minimum Absolute Difference in an Array
	 * Complete the minimumAbsoluteDifference function in the editor below. It should return an integer that represents
	 * the minimum absolute difference between any pair of elements.
	 */
	static int minimumAbsoluteDifference(int[] arr) {
		Arrays.sort(arr);

		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length - 1; i++)
			minDiff = Math.min(minDiff, Math.abs(arr[i] - arr[i + 1]));

		return minDiff;
	}

	/*************************** Type3: Possibilities ******************/
	/*Largest Number: 
	 * Given a list of non negative integers, arrange them such that they form the largest number.
	 * Example 1: Input: [10,2]; Output: "210"; 
	 * Example 2: Input: [3,30,34,5,9]; Output: "9534330"
	 */
	public String largestNumber(int[] nums) {
		if (nums.length == 0) return "0";

		String[] arr = new String[nums.length];
		for (int i = 0; i < nums.length; i++)
			arr[i] = String.valueOf(nums[i]);

		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String a, String b) {
				return (b + a).compareTo(a + b);
			}
		});

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < arr.length; i++)
			sb.append(arr[i]);

		while (sb.charAt(0) == '0' && sb.length() > 1)
			sb.deleteCharAt(0);

		return sb.toString();
	}

	/*
	 * Largest number possible:
	 * Given two numbers 'N' and 'S' , find the largest number that can be formed with 'N' digits and whose sum
	 * of digits should be equals to 'S'.
	 * Input:2 9; Output: 90
	 * Input:3 20; Output: 992 
	 */
	public String largestNumber(int n, int sum) {
		if (sum == 0 || sum > n * 9) return "-1";
		StringBuilder sb = new StringBuilder();
		while (n-- > 0) {
			if (sum > 9) {
				sum -= 9;
				sb.append(9);
			} else {
				sb.append(sum % 10);
				sum -= sum % 10;
			}
		}
		return sb.toString();
	}

	/*
	 *  Maximum Swap: Given a non-negative integer, you could swap two digits at most once to get the maximum valued number.
	 *  Return the maximum valued number you could get.
	 */
	// Approach 1: Traverse from right to left index and find the max & min index to swap;
	public int maximumSwap(int num) {
		if (num < 10) return num;

		char[] digits = String.valueOf(num).toCharArray();

		int l = 0, h = 0, maxIndex = digits.length - 1;
		for (int i = digits.length - 2; i >= 0; i--) {
			if (digits[i] == digits[maxIndex]) {
				continue;
			} else if (digits[i] > digits[maxIndex]) {
				maxIndex = i;
			} else {
				h = maxIndex;
				l = i;
			}
		}

		if (l != h) {
			char temp = digits[l];
			digits[l] = digits[h];
			digits[h] = temp;
		}

		return Integer.valueOf(new String(digits));
	}

	// Approach2:
	public int maximumSwap2(int num) {
		char[] chars = Integer.toString(num).toCharArray();
		int maxIndex = chars.length - 1;
		int x = 0, y = 0;
		for (int i = chars.length - 2; i >= 0; i--) {
			if (chars[maxIndex] == chars[i]) continue;

			if (chars[maxIndex] < chars[i]) {
				maxIndex = i;
			} else {
				x = maxIndex;
				y = i;
			}
		}

		char temp = chars[x];
		chars[x] = chars[y];
		chars[y] = temp;

		return Integer.valueOf(new String(chars));
	}

	/* Create Maximum Number:
	 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length
	 * k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return 
	 * an array of the k digits.
	 * Note: You should try to optimize your time and space complexity.
	 * Input: nums1 = [3, 4, 6, 5]; nums2 = [9, 1, 2, 5, 8, 3]; k = 5
	 * Output:[9, 8, 6, 5, 3]
	 */
	/*Solution: In short we can first solve 2 simpler problem
	 *   - Create the maximum number of one array
	 *   - Create the maximum number of two array using all of their digits.
	 */
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int get_from_nums1 = Math.min(nums1.length, k);
		int[] maxNumber = new int[k];
		for (int i = Math.max(k - nums2.length, 0); i <= get_from_nums1; i++) {
			int[] res1 = new int[i];
			int[] res2 = new int[k - i];
			int[] res = new int[k];
			// Create maximum number array
			res1 = maxArray(nums1, i);
			res2 = maxArray(nums2, k - i);
			int pos1 = 0, pos2 = 0, tpos = 0;
			// Merge the max arrays
			while (res1.length > 0 && res2.length > 0 && pos1 < res1.length && pos2 < res2.length) {
				if (compare(res1, pos1, res2, pos2)) res[tpos++] = res1[pos1++];
				else res[tpos++] = res2[pos2++];
			}
			while (pos1 < res1.length)
				res[tpos++] = res1[pos1++];
			while (pos2 < res2.length)
				res[tpos++] = res2[pos2++];

			// Finally choose the maximum number combinations
			if (!compare(maxNumber, 0, res, 0)) maxNumber = res;
		}

		return maxNumber;
	}

	public int[] maxArray(int[] nums, int k) {
		int[] res = new int[k];
		int len = 0;
		for (int i = 0; i < nums.length; i++) {
			while (len > 0 && len + nums.length - i > k && res[len - 1] < nums[i]) {
				len--;
			}
			if (len < k) res[len++] = nums[i];
		}
		return res;
	}

	public boolean compare(int[] nums1, int start1, int[] nums2, int start2) {
		for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++) {
			if (nums1[start1] > nums2[start2]) return true;
			if (nums1[start1] < nums2[start2]) return false;
		}
		return start1 != nums1.length;
	}

	/* Remove K Digits: 
	 * Given a non-negative integer num represented as a string, remove k digits from the number so that the 
	 * new number is the smallest possible. 
	 * Note: The length of num is less than 10002 and will be >= k. The given num does not contain any leading zero.
	 * Input: num = "1432219", k = 3; Output: "1219"
	 * Input: num = "10200", k = 1; Output: "200"
	 */public String removeKdigits(String num, int k) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < num.length(); i++) {
			while (!stack.isEmpty() && k > 0 && stack.peek() > num.charAt(i)) {
				stack.pop();
				k--;
			}
			stack.push(num.charAt(i));
		}

		// For corner cases same number like "44444"
		while (k > 0) {
			stack.pop();
			k--;
		}

		StringBuilder sb = new StringBuilder();
		for (char ch : stack)
			sb.append(ch);

		// Remove leading zeros
		while (sb.length() > 1 && sb.charAt(0) == '0')
			sb.deleteCharAt(0);

		return sb.length() == 0 ? "0" : sb.toString();
	}

	/* Patching Array:
	 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number
	 * in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of 
	 * patches required.
	 * Input: nums = [1,3], n = 6; Output: 1
	 * Input: nums = [1,5,10], n = 20; Output: 2  
	 */
	public int minPatches(int[] nums, int n) {
		int i = 0, count = 0;
		long sum = 1;
		while (sum <= n) {
			if (i < nums.length && sum >= nums[i]) {
				sum += nums[i++];
			} else {
				sum += sum; // or miss <<= 1;
				count++; // Number of values added or patched
			}
		}
		return count;
	}

	/*Find Permutation:
	 * By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing
	 * relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret
	 * signature was constructed by a special integer array, which contains uniquely all the different number from 1 to
	 * n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by
	 * array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal
	 * constructing special string that can't represent the "DI" secret signature.
	 * On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer
	 * to the given secret signature in the input. 
	 * Example 1: Input: "I" Output: [1,2] Explanation: [1,2] is the only legal initial special string can construct secret 
	 * signature "I", where the number 1 and 2 construct an increasing relationship. 
	 * Example 2: Input: "DI" Output: [2,1,3] Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI",
	 * but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3] Note: The 
	 * input string will only contain the character 'D' and 'I'.
	 */
	// Approach1: Assign the elements in forward & reverse direction
	public int[] findPermutation1(String s) {
		int num = 1, l = 0, r = 0;
		int[] result = new int[s.length() + 1];

		while (l < result.length) {
			if (l == s.length() || s.charAt(l) == 'I') {
				result[l++] = num++; // Store number in forward direction
			} else {
				r = l;
				while (r < s.length() && s.charAt(r) == 'D')
					r++;
				for (int i = r; i >= l; i--)
					result[i] = num++; // Store number in reverse direction
				l = r + 1;
			}
		}
		return result;
	}

	// Approach2: Assign the elements first, after that reverse the elements when char is 'D'.
	public int[] findPermutation2(String s) {
		int n = s.length(), arr[] = new int[n + 1];
		// Assign the elements
		for (int i = 0; i <= n; i++)
			arr[i] = i + 1; // sorted

		// Reverse the elements, if there in any 'D'
		for (int h = 0; h < n; h++) {
			if (s.charAt(h) == 'D') {
				int l = h;
				while (h < n && s.charAt(h) == 'D')
					h++;
				reverse(arr, l, h);
			}
		}
		return arr;
	}

	void reverse(int[] arr, int l, int h) {
		while (l < h) {
			arr[l] ^= arr[h];
			arr[h] ^= arr[l];
			arr[l] ^= arr[h];
			l++;
			h--;
		}
	}

	/*Next Permutation
	 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
	 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
	 * The replacement must be in-place and use only constant extra memory.
	 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
	 * 1,2,3 -> 1,3,2
	 * 3,2,1 -> 1,2,3
	 * 1,1,5 -> 1,5,1
	 */

	public void nextPermutation(int[] nums) {
		// find two adjacent elements, n[i-1] < n[i]
		int i = nums.length - 1;
		for (; i > 0; i--)
			if (nums[i] > nums[i - 1]) break;

		if (i != 0) {
			// swap (i-1, min), where min is index of the smallest number in [i, n)
			int minIndex = nums.length - 1;
			for (; minIndex >= i; minIndex--)
				if (nums[minIndex] > nums[i - 1]) break;

			Utils.swap(nums, i - 1, minIndex);
		}
		reverse(nums, i, nums.length - 1);
	}

	/*************************** Type4: Other Greedy Problems -Revisit this ******************/
	/*
	 * Huffman Coding & Decoding:
	 *    Huffman coding is a lossless data compression algorithm. The idea is to assign variable-length codes to input characters, 
	 * lengths of the assigned codes are based on the frequencies of corresponding characters. The most frequent character gets the 
	 * smallest code and the least frequent character gets the largest code.
	 * Time complexity: O(nlogn) where n is the number of unique characters
	 */
	public void huffmanCoding(String str) {
		// 1.Build map from input string, like key-> char & value->freq of chars
		Map<Character, Integer> charFreqMap = findCharFreq(str);

		// 2.Create a Priority Queue, which is used to build min heap based on char frequency
		PriorityQueue<HuffmanCode> queue = new PriorityQueue<>(charFreqMap.size(), new Comparator<HuffmanCode>() {
			public int compare(HuffmanCode o1, HuffmanCode o2) {
				return o1.count - o2.count;
			}
		});

		// 3.Create a leaf node for each unique character and build a min heap of all leaf nodes
		for (Entry<Character, Integer> entry : charFreqMap.entrySet()) {
			HuffmanCode hc = new HuffmanCode(entry.getKey(), entry.getValue());
			queue.add(hc);
		}

		// Repeat steps #4 and #5 until the heap contains only one node. The remaining node is the root node and the
		// tree is
		// complete.
		while (queue.size() > 1) {
			// 4.Extract two nodes with the minimum frequency from the min heap.
			HuffmanCode first = queue.poll(); // Extract first min
			HuffmanCode second = queue.poll(); // Extract second min

			// 5.Create a new internal node with frequency equal to the sum of the two nodes frequencies. Make the first
			// extracted
			// node as its left child and the other extracted node as its right child. Add this node to the min heap.
			HuffmanCode huffmanCode = new HuffmanCode('-', first.count + second.count);
			huffmanCode.left = first;
			huffmanCode.right = second;
			queue.add(huffmanCode);
		}

		Map<Character, String> encodedMap = new TreeMap<>();

		// 6.Encode the given string: String to Binary Conversion
		HuffmanCode root = queue.peek();
		encodeString(root, "", encodedMap);

		// 7.Concatenate all the binary in char order of input string.
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			builder.append(encodedMap.get(ch));
		}
		System.out.println("Huffman encoded data:" + builder.toString());

		// Huffman decoding
		System.out.println("Huffman decoded data:" + huffmanDecoding(root, builder.toString()));
	}

	// Build map from input string, like key-> char & value->freq of chars
	public Map<Character, Integer> findCharFreq(String str) {
		Map<Character, Integer> charFreqMap = new TreeMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int freq = charFreqMap.get(ch) == null ? 0 : charFreqMap.get(ch);
			charFreqMap.put(ch, ++freq);
		}
		return charFreqMap;
	}

	/*
	 * Traverse the tree formed starting from the root. Maintain an auxiliary array. 
	 * While moving to the left child, write 0 to the array. While moving to the right child, write 1 to the array. 
	 * Print the array when a leaf node is encountered and also .
	 */
	public void encodeString(HuffmanCode root, String binaryStr, Map<Character, String> encodedData) {
		if (root.left == null && root.right == null && Character.isLetter(root.c)) {
			System.out.println(root.c + " : " + binaryStr + " : " + root.count);
			encodedData.put(root.c, binaryStr);
			return;
		}

		encodeString(root.left, binaryStr + "0", encodedData);
		encodeString(root.right, binaryStr + "1", encodedData);
	}

	/*
	 * To decode the encoded data we require the Huffman tree and binary encoded data.
	 */
	public String huffmanDecoding(HuffmanCode root, String encodedString) {
		HuffmanCode curr = root;
		StringBuilder decodedString = new StringBuilder();
		// We start from root and do following until a leaf is found.
		for (int i = 0; i < encodedString.length(); i++) {
			char ch = encodedString.charAt(i);
			// 1.If current bit is 0, we move to left node of the tree.
			if (ch == '0') curr = curr.left;
			else // 2.If the bit is 1, we move to right node of the tree.
				curr = curr.right;

			// 3.If during traversal, we encounter a leaf node, append character of that particular leaf node and then
			// again
			// continue the iteration of the encoded data starting from step 1.
			if (curr.left == null && curr.right == null) {
				decodedString.append(curr.c);
				curr = root;
			}
		}
		return decodedString.toString();
	}
}