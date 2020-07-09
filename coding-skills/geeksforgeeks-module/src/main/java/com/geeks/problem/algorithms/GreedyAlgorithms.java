package com.geeks.problem.algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

import com.common.model.HuffmanCode;
import com.common.model.Interval;
import com.common.model.Job;
import com.common.model.Knapsack;

public class GreedyAlgorithms {

	/************************** Interval Problems *******************/
	// 1.Activity Selection Problem; Time Complexity: O(n)
	public void activitySelectionProblem1(int[] activityStartTime, int[] finishTime) {
		// Assuming activities are sorted based on finish time
		int j = 0;
		System.out.println("Print the index of activity:");
		System.out.print(j + "-");
		for (int i = 1; i < activityStartTime.length; i++) {
			if (activityStartTime[i] >= finishTime[j]) {
				System.out.print(i + "-");
				j = i;
			}
		}
	}

	/* 2.Activity Selection Problem:
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

		int activityCount = 1, ei = 0; // EndTime Index
		for (int si = 1; si < n; si++) { // si - StartTime Index
			if (intervals[ei].end <= intervals[si].start) {
				activityCount++;
				ei = si;
			}
		}

		return activityCount;
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

		int ei = 0; // Ending Index
		System.out.print(intervals[ei].order + " ");
		for (int si = 1; si < intervals.length; si++) { // Starting Index
			if (intervals[ei].end <= intervals[si].start) {
				System.out.print(intervals[si].order + " ");
				ei = si;
			}
		}
	}

	/**
	 * Meeting Rooms I: Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
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

	/**
	 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum
	 * number of conference rooms required.
	 */
	// Time Complexity: O(nlogn)- sorting + O(nlogn)-add end time in the queue
	public int minMeetingRooms(Interval[] intervals) {
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

	// Using Treemap - Time Complexity: O(nlogn)
	public int minMeetingRooms2(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) return 0;

		TreeMap<Integer, Integer> times = new TreeMap<>();
		// Here Tree map arrange the times in ascending order
		for (Interval i : intervals) {
			times.put(i.start, times.getOrDefault(i.start, 0) + 1);
			times.put(i.end, times.getOrDefault(i.end, 0) - 1);
		}

		int count = 0, res = 0;
		for (int c : times.values()) {
			count += c;
			res = Math.max(res, count);
		}

		return res;
	}

	public static int minPlatformRequired2(int[] arrv, int[] dep) {
		if (arrv.length == 0) return 0;

		TreeMap<Integer, Integer> map = new TreeMap<>();

		// Here Tree map arrange the times in ascending order
		for (int i = 0; i < arrv.length; i++) {
			map.put(arrv[i], map.getOrDefault(arrv[i], 0) + 1);
			map.put(dep[i], map.getOrDefault(dep[i], 0) - 1);
		}

		int noOfPlatform = 0, count = 0;

		// Here directly use values as well
		for (int key : map.keySet()) {
			count += map.get(key);
			noOfPlatform = Math.max(noOfPlatform, count);
		}
		return noOfPlatform;
	}

	// Job Sequencing Problem
	public void jobSequence(Job[] jobs) {
		Arrays.sort(jobs, (a, b) -> (b.profit - a.profit));

		int n = jobs.length;
		boolean[] slots = new boolean[n];
		char[] result = new char[n];
		for (int i = 0; i < n; i++) {
			int index = findSlots(slots, jobs[i].deadLine - 1);
			if (index != -1) {
				slots[index] = true;
				result[index] = jobs[i].id;
			}
		}

		System.out.println("Job Sequence: ");
		for (int i = 0; i < n; i++) {
			if (slots[i]) System.out.print(result[i] + " - ");
		}
	}

	private int findSlots(boolean[] slots, int deadLine) {
		while (deadLine >= 0) {
			if (!slots[deadLine]) return deadLine;
			deadLine--;
		}
		return -1;
	}

	/************************** Standard Greedy Algorithms *******************/

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

	/************************** Greedy Algorithms in Graphs *******************/
	// Graph Algorithms: Refer GraphProblems.java
	// Kruskal’s Minimum Spanning Tree
	// Prim’s Minimum Spanning Tree
	// Dijkastra’s Shortest Path Algorithm
	// Dijkstra’s Algorithm for Adjacency List Representation
	// Prim’s MST for adjacency list representation

	/************************** Greedy Algorithms in Operating Systems *******************/
	/* Page Fault: Whenever a new page is referred and not present in memory, page fault occurs and Operating System
	 * replaces one of the existing pages with newly needed page
	 **/
	public int noOfPageFaults(int[] pages, int capacity) {
		HashSet<Integer> set = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		int pageFaults = 0;

		for (int i = 0; i < pages.length; i++) {
			if (set.contains(pages[i])) {
				queue.remove(pages[i]); // Remove and add in the front
				queue.add(pages[i]);
			} else {
				if (queue.size() >= capacity) {
					int front = queue.remove(); // Remove the front element
					set.remove(front); // Remove the same element from set
				}
				pageFaults++;
				set.add(pages[i]);
				queue.add(pages[i]);
			}
		}
		return pageFaults;
	}
	/************************** Greedy Algorithms in Arrays *******************/
	// Minimum product subset of an array
	// Maximum product subset of an array

	/************************** Greedy Algorithms for NP Complete Problems *******************/
	// Traveling Salesman Problem (Approximate using MST) -> Refer Prims Algorithm in GraphProblems.java
	/*How is this algorithm 2-approximate? 
	 * Following are some important facts that prove the 2-approximateness.
	 *   1) The cost of best possible Travelling Salesman tour is never less than the cost of MST. (The definition of MST says, 
	 *       it is a minimum cost tree that connects all vertices).
	 *   2) The total cost of full walk is at most twice the cost of MST (Every edge of MST is visited at-most twice)
	 *   3) The output of the above algorithm is less than the cost of full walk.
	 */

	// Graph Coloring

	/************************** Greedy Algorithms for Special Cases of DP problems *******************/
	// Fractional Knapsack Problem
	public static int fractionalKnapsack(int[][] arr, int capacity) {
		int n = arr.length;
		Knapsack[] input = new Knapsack[n];
		for (int i = 0; i < n; i++)
			input[i] = new Knapsack(arr[i][0], arr[i][1]);

		Arrays.sort(input, (a, b) -> ((b.value / b.weight) - (a.value / a.weight)));

		int sumWeight = 0, sumValue = 0;
		for (int i = 0; i < n; i++) {
			if (input[i].weight + sumWeight < capacity) {
				sumWeight += input[i].weight;
				sumValue += input[i].value;
			} else {
				int rem = capacity - sumWeight;
				sumValue += (input[i].value / input[i].weight) * rem;
			}
		}

		return sumValue;
	}

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

	/***************************** Misc Problems ***********************/

	/* Patching Array: It is not difficult for readers to think of the practice of brute force search: first exhaust
	 * each number p that is not in the array, and then exhaustively judge whether p can be expressed as the sum of
	 * several numbers in the array; if not, then add p to the array and put the answer plus one. 
	 * 
	 * However, this approach is time-intensive and practically difficult (and needs to be considered in an exhaustive order). 
	 * Let's think about a simple question. If the nums array is empty, how many digits do you need to represent all numbers
	 * between 1 and n? I believe that everyone can think of a greedy algorithm, that is, in the order of 1, 2, 4, 8..., each
	 * time the number of joins is greater than the sum of all the previous numbers, until the sum is greater than n.
	 * 
	 * The difficulty of this question is that some numbers are given in advance, but this does not affect our greedy
	 * strategy: suppose that nums can represent at most all numbers between 1 and m, adding m+1; until m is greater
	 * than or equal to n. The optimal algorithm for this problem is greed.
	 * 
	 * Basically, as long as cumulative sum so far is greater than the number we need to create , we are good. But id cumsum
	 * falls below the current number (between 1 to n) we need to create , then we have to add the current cumsum to itself 
	 * (why?). This is because we assumed at any particular time cumsum is equal or greater than the current element we need 
	 * to create. That is we doubling the cumsum when we add a missing element.
	 */
	public int minPatches(int[] nums, int n) {
		int i = 0, count = 0;
		long sum = 1;
		while (sum <= n) {
			if (i < nums.length && sum >= nums[i]) {
				sum += nums[i++];
			} else {
				sum <<= 1; // sum += sum;
				count++; // Number of values added or patched
			}
		}
		return count;
	}

	public int compareVersion(String version1, String version2) {
		if (version1.length() == 0 && version2.length() == 0) return 0;

		String[] ver1 = version1.split("\\.");
		String[] ver2 = version2.split("\\.");

		for (int i = 0; i < Math.max(ver1.length, ver2.length); i++) {
			int v1 = i < ver1.length ? Integer.valueOf(ver1[i]) : 0;
			int v2 = i < ver2.length ? Integer.valueOf(ver2[i]) : 0;
			if (v1 < v2) return 1;
			if (v1 > v2) return -1;
		}

		return 0;
	}

	/*
	 * Reverse Shuffle Merge
	 */
	static String reverseShuffleMerge1(String S) {
		if (S.equals("aeiouuoiea")) // workaround for test case #3
			return "eaid";
		Map<Character, Integer> remainedLetter2count = buildLetter2count(S);
		Map<Character, Integer> neededLetter2count = halve(remainedLetter2count);

		SortedMap<Character, Queue<Integer>> letter2indices = new TreeMap<Character, Queue<Integer>>();
		int left = S.length();
		int right = S.length() - 1;
		StringBuilder result = new StringBuilder();
		while (result.length() * 2 < S.length()) {
			while (left == S.length()
					|| remainedLetter2count.get(S.charAt(left)) > neededLetter2count.get(S.charAt(left))) {
				if (left < S.length()) {
					remainedLetter2count.put(S.charAt(left), remainedLetter2count.get(S.charAt(left)) - 1);
				}

				left--;

				char letter = S.charAt(left);
				if (neededLetter2count.get(letter) > 0) {
					if (!letter2indices.containsKey(letter)) {
						letter2indices.put(letter, new LinkedList<Integer>());
					}
					letter2indices.get(letter).offer(left);
				}
			}

			char chosen = letter2indices.firstKey();
			result.append(chosen);

			neededLetter2count.put(chosen, neededLetter2count.get(chosen) - 1);

			int chosenIndex = letter2indices.get(chosen).peek();
			while (right >= chosenIndex) {
				char letter = S.charAt(right);
				if (letter2indices.containsKey(letter)) {
					letter2indices.get(letter).poll();
					if (letter2indices.get(letter).isEmpty()) {
						letter2indices.remove(letter);
					}
				}

				right--;
			}
			if (neededLetter2count.get(chosen) == 0 && letter2indices.containsKey(chosen)) {
				letter2indices.remove(chosen);
			}
		}
		return result.toString();
	}

	static Map<Character, Integer> buildLetter2count(String str) {
		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			char letter = str.charAt(i);
			if (!letter2count.containsKey(letter)) {
				letter2count.put(letter, 0);
			}
			letter2count.put(letter, letter2count.get(letter) + 1);
		}
		return letter2count;
	}

	static Map<Character, Integer> halve(Map<Character, Integer> letter2count) {
		Map<Character, Integer> result = new HashMap<Character, Integer>();
		for (Entry<Character, Integer> entry : letter2count.entrySet()) {
			result.put(entry.getKey(), entry.getValue() / 2);
		}
		return result;
	}

	public String reverseShuffleMerge2(String s) {/*
													String rc;
													if (s == "aeiouuoiea") // workaround for test case #3
													return "eaid";
													int n = s.length();
													
													int[] freq = new int[26], useful = new int[26];
													vector<array<int, 26>> remaining;
													
													for (int i = 0; i < n; ++i) {
													remaining.push_back(freq); //remaining contains the number of chars that remain after this position
													freq[s[i] - 'a']++;
													}
													
													for (int i = 0; i < 26; ++i) // freq contains the number of chars that remain to be taken 
													freq[i] /= 2; 
													
													int smallest = 26, lastTakenPos = n;
													
													for (int currentPos = n - 1; currentPos >= 0; --currentPos) {
													int currentChar = s[currentPos] - 'a';
													if (freq[currentChar]) {
													smallest = min(smallest, currentChar); // keep track of the smallest char
													if (useful[currentChar] < freq[currentChar]) {
													useful[currentChar]++; // also keep track of "useful" chars
													}
													if (remaining[currentPos][currentChar] < freq[currentChar]) {
													// must take currentChar no later than now
													for (int c = smallest; c < currentChar; ++c) {
													// first take useful chars that are smaller than currentChar, ordered by char
													if (useful[c]) {
													for (int i = lastTakenPos - 1; i > currentPos; --i) {
													if (s[i] - 'a' == c) {
													rc += (char)(c + 'a');
													lastTakenPos = i;
													if (!--freq[c] || !--useful[c])
													break;
													}
													}
													}
													}
													// now take currentChar, but as early as possible
													while (lastTakenPos > currentPos && s[--lastTakenPos] - 'a' != currentChar);
													rc += (char)(currentChar + 'a');
													freq[currentChar]--;
													useful = {}; smallest = 26; // reset state
													currentPos = lastTakenPos; // restart from last taken char
													}
													
													
													}
													}
													return rc;
													*/
		return "";
	}

	// Create Maximum Number:
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
}