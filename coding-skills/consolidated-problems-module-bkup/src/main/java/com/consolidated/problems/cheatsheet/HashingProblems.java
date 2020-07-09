package com.consolidated.problems.cheatsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.common.utilities.Utils;

public class HashingProblems {

	private static final int EXTENDED_ASCII_CHAR_SIZE = 256;

	/************************************* Type1: Hashing(Array/Set/Map) Problems ************************/
	/*
	 * Longest Consecutive Sequence:
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
	 * Your algorithm should run in O(n) complexity.
	 * Example: Input: [100, 4, 200, 1, 3, 2]; Output: 4
	 */
	/*
	 * Longest Consecutive Sequence:
	 * 1.Brute Force: Check elements one by one for the consecutive elements -O(n^3)
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
			 longestSeq = Math.max(longestSeq,
					count);
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
		if (nums.length == 0) return 0;
		Arrays.sort(nums);
		int longestSeq = 0, count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] == nums[i]) {
				continue;
			} else if (nums[i - 1]
					+ 1 == nums[i]) {
				count++;
			} else {
				longestSeq = Math.max(longestSeq,
						count);
				count = 1;
			}
		}
		return Math.max(longestSeq, count);
	}

	// Approach3:
	public int longestConsecutive(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : nums)
			set.add(num);
		int longestSeq = 0, up, down;
		for (int i = 0; i < nums.length; i++) {
			if (!set.contains(nums[i])) continue;
			set.remove(nums[i]);
			down = nums[i];
			while (set.contains(down - 1)) {
				down--;
				set.remove(down);
			}
			up = nums[i];
			while (set.contains(up + 1)) {
				up++;
				set.remove(up);
			}
			longestSeq = Math.max(longestSeq,
					up - down + 1);
		}
		return longestSeq;
	}

	/* Unique Word Abbreviation
	 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word
	 * abbreviations:Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
	Example: 
	Given dictionary = [ "deer", "door", "cake", "card" ]
	
	isUnique("dear") -> false
	isUnique("cart") -> true
	isUnique("cane") -> false
	isUnique("make") -> true
	 */
	public boolean isUnique(
			Map<String, String> map,
			String word) {
		String abb = getAbbrevation(word);
		return (!map.containsKey(abb)
				|| map.get(abb).equals(word));
	}

	public String getAbbrevation(String word) {
		int n = word.length();
		if (n <= 2) return word;
		return String.valueOf(word.charAt(0)
				+ Integer.toString(n - 2)
				+ word.charAt(n - 1));
	}

	public Map<String, String> buildDictionary(
			String[] strings) {
		Map<String, String> map = new HashMap<>();
		for (String str : strings) {
			String abb = getAbbrevation(str);
			if (map.containsKey(abb))
				map.put(abb, "");
			else map.put(abb, str);
		}
		return map;
	}

	/*
	 * Repeated DNA Sequences:
	 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When 
	 * studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
	 * 	Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
	 * 	Example: Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
	 * 	Output: ["AAAAACCCCC", "CCCCCAAAAA"]
	 */
	public List<String> findRepeatedDnaSequences0(
			String s) {
		Set<String> uniqueSeq = new HashSet<>();
		Set<String> repeatedSeq = new HashSet<>();
		for (int i = 0; i <= s.length()
				- 10; i++) {
			String subStr = s.substring(i,
					i + 10);
			if (!uniqueSeq.add(subStr))
				repeatedSeq.add(subStr);
		}
		return new ArrayList<>(repeatedSeq);
	}

	// Approach2: Bit Manipulations - 1
	public List<String> findRepeatedDnaSequences1(
			String s) {
		Set<Integer> words = new HashSet<>();
		Set<Integer> doubleWords = new HashSet<>();
		List<String> rv = new ArrayList<>();
		char[] map = new char[26];
		// map['A' - 'A'] = 0;
		map['C' - 'A'] = 1;
		map['G' - 'A'] = 2;
		map['T' - 'A'] = 3;
		for (int i = 0; i < s.length() - 9; i++) {
			int v = 0;
			for (int j = i; j < i + 10; j++) {
				v <<= 2;
				v |= map[s.charAt(j) - 'A'];
			}
			if (!words.add(v)
					&& doubleWords.add(v)) {
				rv.add(s.substring(i, i + 10));
			}
		}
		return rv;
	}

	// Approach2: Bit Manipulations Improved version -
	// why Bitwise AND operation with 0x3FFFF? - To consider only 18 bits from LSB, remaining will be zero. Because
	// "A,C,G,T" -> Char set needs only 2 bits.
	public List<String> findRepeatedDnaSequences2(
			String s) {
		int len = s.length(), cur = 0;
		if (len < 10) return new ArrayList<>();
		Set<Integer> seen = new HashSet<>();
		Set<String> repeated = new HashSet<>();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
		for (int i = 0; i < 9; i++) {
			cur = (cur << 2)
					| map.get(s.charAt(i));
		}
		for (int i = 9; i < len; i++) {
			cur = ((cur & 0x3ffff) << 2)
					| map.get(s.charAt(i));
			if (!seen.add(cur)) {
				repeated.add(s.substring(i - 9,
						i + 1));
			}
		}
		return new ArrayList<String>(repeated);
	}

	/*
	 *  Bulls and Cows:
	 *  Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls
	 *  and B to indicate the cows. Please note that both secret number and friend's guess may contain duplicate digits.
	 *  Example 1:	Input: secret = "1807", guess = "7810"	Output: "1A3B"
	 *  Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
	 *  Example 2: Input: secret = "1123", guess = "0111" Output: "1A1B"
	 *  Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
	 */
	// Approach1: Brute Force Approach
	public String getHint(String secret,
			String guess) {
		Map<Character, Integer> map = new HashMap<>();
		int n = secret.length(), bulls = 0,
				cows = 0;
		for (int i = 0; i < n; i++) {
			char s = secret.charAt(i);
			if (s == guess.charAt(i)) bulls++;
			else map.put(s,
					map.getOrDefault(s, 0) + 1);
		}
		for (int i = 0; i < n; i++) {
			char g = guess.charAt(i);
			if (g == secret.charAt(i)) continue;
			if (map.containsKey(g)) {
				cows++;
				if (map.get(g) == 1)
					map.remove(g);
				else map.put(g, map.get(g) - 1);
			}
		}
		return bulls + "A" + cows + "B";
	}

	/*
	 * Shortest Word Distance I: Given a list of words and two words word1 and word2, return the shortest distance
	 * between these two words in the list. 
	 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. 
	 * Given word1 = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding", return 1.
	 */
	public int shortestDistanceI(String[] words,
			String word1, String word2) {
		int min = Integer.MAX_VALUE, index1 = -1,
				index2 = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1))
				index1 = i;
			if (words[i].equals(word2))
				index2 = i;
			if (index1 != -1 && index2 != -1)
				min = Math.min(min, Math
						.abs(index1 - index2));
		}
		return min;
	}

	Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

	/* Shortest Word Distance II: 
	 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and
	 * your method will be called repeatedly many times with different parameters. How would you optimize it? Design a
	 * class which receives a list of words in the constructor, and implements a method that takes two words word1 and
	 * word2 and return the shortest distance between these two words in the list. 
	 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. 
	 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = “makes”, word2 = “coding”, return 1. 
	 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
	 */

	/*Solution: Initialize the words:
	 * We can pre scan the list to record the indexes for each word, and store the sorted index list into a HashTable.
	 * The key is the word, and the value is the sorted index list. In the query process, the problem becomes to find
	 * the minimum difference between the values in the two sorted index lists.
	 */
	public void initialize(String[] words) {
		for (int i = 0; i < words.length; i++) {
			String curWord = words[i];
			if (!map.containsKey(curWord)) {
				map.put(curWord,
						new ArrayList<>());
			}
			map.get(curWord).add(i);
		}
	}

	// Here apply merging sorted lists to get the minimum.
	public int shortestDistanceII(String word1,
			String word2) {
		List<Integer> indexList1 = map.get(word1);
		List<Integer> indexList2 = map.get(word2);
		int minDistance = Integer.MAX_VALUE;
		int i1 = 0, i2 = 0;
		while (i1 < indexList1.size()
				&& i2 < indexList2.size()) {
			int wordIndex1 = indexList1.get(i1);
			int wordIndex2 = indexList2.get(i2);
			minDistance = Math.min(minDistance,
					Math.abs(wordIndex1
							- wordIndex2));
			if (wordIndex1 < wordIndex2) i1++;
			else i2++;
		}
		return minDistance;
	}

	/*
	 * Shortest Word Distance III: This is a follow up of Shortest Word Distance. The only difference is now word1 could
	 * be the same as word2. Given a list of words and two words word1 and word2, return the shortest distance between
	 * these two words in the list. word1 and word2 may be the same and they represent two individual words in the list.
	 *
	 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. 
	 * Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = “makes”, word2 = “makes”, return 3. 
	 * Note: You may assume word1 and word2 are both in the list.
	 */
	int shortestWordDistanceIII(String[] words,
			String word1, String word2) {
		int i1 = -1, i2 = -1,
				minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; ++i) {
			String curWord = words[i];
			if (word1.equals(word2)) {
				if (curWord.equals(word1)) {
					if (i2 < i1) i2 = i;
					else i1 = i;
				}
			} else {
				if (curWord.equals(word1)) i1 = i;
				if (curWord.equals(word2)) i2 = i;
			}
			if (i1 >= 0 && i2 >= 0)
				minDistance = Math.min(
						minDistance,
						Math.abs(i1 - i2));
		}
		return minDistance;
	}

	/* Group Shifted Strings: Given a string, we can "shift" each of its letter to its successive letter,
	 * for example:"abc" -> "bcd". We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz" 
	 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same 
	 * shifting sequence. For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
	 * A solution is: [ ["abc","bcd","xyz"], ["az","ba"], ["acef"], ["a","z"] ]
	 */
	public List<List<String>> groupStrings(
			String[] strings) {
		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String str : strings) {
			String bitMap = bitMap(str);
			if (!map.containsKey(bitMap))
				map.put(bitMap,
						new ArrayList<>());
			map.get(bitMap).add(str);
		}
		for (String key : map.keySet()) {
			List<String> list = map.get(key);
			result.add(list);
		}
		result.stream().forEach(
				k -> System.out.println(k));
		return result;
	}

	public String bitMap(String str) {
		int[] arr = new int[str.length()];
		arr[0] = 0;
		for (int i = 1; i < str.length(); i++) {
			int diff = str.charAt(i)
					- str.charAt(0);
			arr[i] = diff > 0 ? diff
					: (diff % 26) + 26;
		}
		return Arrays.toString(arr);
	}

	/*Number of Boomerangs:
	 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that
	 * the distance between i and j equals the distance between i and k (the order of the tuple matters).
	 * Input: [[0,0],[1,0],[2,0]],  Output:2
	 * Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
	 * 
	 * Solution:
	 * for each point, create a hashmap and count all points with same distance. If for a point p, there are k points
	 * with distance d, number of boomerangs corresponding to that are n*(n-1). Keep adding these to get the final
	 * result.
	 * Why n*(n-1)? Permutation: Take 2 from n elements (order matters), there are total 
	 * P(2, n) = n! / (n-2)! = n * (n-1) possible solutions
	 */
	public int numberOfBoomerangs(
			int[][] points) {
		if (points.length == 0) return 0;
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i1 = 0; i1 < points.length; i1++) {
			for (int i2 = 0; i2 < points.length; i2++) {
				if (i1 == i2) continue;
				int d = getDistance(points[i1],
						points[i2]);
				map.put(d, map.getOrDefault(d, 0)
						+ 1);
			}
			for (int n : map.values())
				count += n * (n - 1);
			map.clear();
		}
		return count;
	}

	private int getDistance(int[] a, int[] b) {
		int dx = a[0] - b[0];
		int dy = a[1] - b[1];
		return dx * dx + dy * dy;
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
		int min = Integer.MAX_VALUE,
				max = Integer.MIN_VALUE;
		for (int[] point : points) {
			min = Math.min(min, point[0]);
			max = Math.max(max, point[0]);
			set.add(point[0] + "-" + point[1]);
		}
		int sum = min + max;
		for (int[] point : points)
			if (!set.contains(sum - point[0] + "-"
					+ point[1]))
				return false;
		return true;
	}

	// Longest Substring with At Least K Repeating Characters
	/* Understand the logic, it just copied
	 * For each h, apply two pointer technique to find the longest substring with at least K repeating characters and the number 
	 * of unique characters in substring is h.
	 */
	public int longestSubstringAtleastKRepeatingChar(
			String s, int k) {
		char[] str = s.toCharArray();
		int[] counts = new int[26];
		int h, idx, max = 0;
		for (h = 1; h <= 26; h++) {
			Arrays.fill(counts, 0);
			int i = 0, j = 0, unique = 0,
					noLessThanK = 0;
			while (j < str.length) {
				if (unique <= h) {
					idx = str[j] - 'a';
					if (counts[idx] == 0)
						unique++;
					counts[idx]++;
					if (counts[idx] == k)
						noLessThanK++;
					j++;
				} else {
					idx = str[i] - 'a';
					if (counts[idx] == k)
						noLessThanK--;
					counts[idx]--;
					if (counts[idx] == 0)
						unique--;
					i++;
				}
				if (unique == h
						&& unique == noLessThanK)
					max = Math.max(j - i, max);
			}
		}
		return max;
	}

	// Array Pair Sum Divisibility Problem
	public boolean isArrayPairDivisibleByK(
			int[] a, int k) {
		int n = a.length;
		if (n == 0 || n % 2 == 1) return false;
		Map<Integer, Integer> map = new HashMap<>();
		int rem = 0;
		for (int i = 0; i < n; i++) {
			rem = a[i] % k;
			if (map.get(rem) == null)
				map.put(rem, 1);
			else map.put(rem, map.get(rem) + 1);
		}
		for (Entry<Integer, Integer> entry : map
				.entrySet())
			System.out.println(entry.getKey()
					+ " - " + entry.getValue());
		Integer value;
		for (int i = 0; i < n; i++) {
			rem = a[i] % k;
			value = map.get(rem);
			if (rem == 0) {
				if (value != null
						&& value % 2 == 1)
					return false;
			} else if (2 * rem == k) {
				if (value != null
						&& value % 2 == 1)
					return false;
			} else if (value != null
					&& map.get(k - rem) != null
					&& value != map.get(k - rem))
				return false;
		}
		return true;
	}

	// Array Subset of another array

	// Find all pairs with a given sum
	public void allPairsWithSum(int[] arr1,
			int[] arr2, int sum) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < arr2.length; i++)
			set.add(arr2[i]);
		for (int i = 0; i < arr1.length; i++) {
			if (set.contains(sum - arr1[i]))
				System.out.print(arr1[i] + " "
						+ (sum - arr1[i]) + ", ");
		}
	}

	// Find first repeated character
	public String firstRepeatedChar(String str) {
		Set<Character> set = new HashSet<>();
		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (set.contains(ch))
				return String.valueOf(ch);
			set.add(ch);
		}
		return "-1";
	}
	// Minimum indexed character

	// Check if two arrays are equal or not
	public int isTwoArraysSame(int[] arr1,
			int[] arr2) {
		if (arr1.length != arr2.length) return 0;
		int n = arr1.length;
		Map<Integer, Integer> map = new HashMap<>();
		Integer count;
		for (int i = 0; i < n; i++) {
			count = map.get(arr2[i]);
			if (count == null)
				map.put(arr2[i], 1);
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
	public static void uncommonChars(String s1,
			String s2) {
		int[] count = new int[26];
		for (int i = 0; i < s1.length(); i++)
			count[s1.charAt(i) - 'a'] = 1;
		int index = 0;
		for (int i = 0; i < s2.length(); i++) {
			index = s2.charAt(i) - 'a';
			if (count[index] == 1
					|| count[index] == -1)
				count[index] = -1;
			else count[index] = 2;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] == 1 || count[i] == 2)
				System.out
						.print((char) (i + 'a'));
		}
	}

	// Smallest window in a string containing all the char of another string - Tricky Question

	// First element to occur k times
	public static int firstElementOccurKtimes(
			int[] arr, int k) {
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
			if (map.get(arr[i]) == k)
				return arr[i];
		return -1;
	}

	/*Swapping pairs make sum equal:
	 * sumA-a+b = sumB-b+a
	 * sumA- sumB = 2a-2b => sumA-sumB = 2(a-b) => a-b = (sumA - sumB)/2
	 */
	public int isSwappingPairMakesSumEqual1(
			int[] a1, int m, int[] a2, int n) {
		int sum1 = sum(a1, m);
		int sum2 = sum(a2, n);
		int diff = Math.abs(sum1 - sum2);
		if (diff % 2 == 1) return -1;
		diff /= 2;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (diff == Math
						.abs(a1[i] - a2[j])) {
					System.out.println(
							"Swapping Values are: "
									+ a1[i] + ", "
									+ a2[j]);
					return 1;
				}
			}
		}
		return -1;
	}

	private int sum(int[] a, int n) {
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += a[i];
		return sum;
	}

	public int isSwappingPairMakesSumEqual2(
			int[] a1, int m, int[] a2, int n) {
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
				System.out.println(
						"Swapping Values are: "
								+ a1[i] + ", "
								+ a2[j]);
				return 1;
			} else if (diff < temp) {
				i++;
			} else {
				j++;
			}
		}
		return -1;
	}
	
	/* Sherlock and Anagrams:
	 * Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string. 
	 * Given a string, find the number of pairs of substrings of the string that are anagrams of each other.
	 * Eg: abba => The list of all anagrammatic pairs is {a,a}, {ab, ba}, {b,b}, {abb, bba}; Count: 4
	 */
	//Approach1: Brute Force Approach:
	static int sherlockAndAnagrams1(String s) {
		int n = s.length(), count = 0;
		for (int len = 1; len < n; len++) {
			for (int i = 0; i < n - len
					+ 1; i++) {
				String s1 = s.substring(i,
						i + len);
				for (int j = i + 1; j < n - len
						+ 1; j++) {
					String s2 = s.substring(j,
							j + len);
					if (isAnagram(s1, s2))
						count++;
				}
			}
		}

		return count;
	}

	//Approach2: Using Hashing
	static int sherlockAndAnagrams(String s) {
		int n = s.length(), count = 0;
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				char[] charArray = s
						.substring(i, j)
						.toCharArray();
				Arrays.sort(charArray);
				String str = new String(
						charArray);
				map.put(str,
						map.getOrDefault(str, 0)
								+ 1);
			}
		}
		Iterator<Integer> iter = map.values()
				.iterator();
		while (iter.hasNext()) {
			int val = iter.next();
			count += (val * (val - 1)) / 2;
		}
		return count;
	}

	private static boolean isAnagram(String s1,
			String s2) {
		int m = s1.length(), n = s2.length();
		if (m != n) return false;
		if (m == 1)
			return s1.equals(s2) ? true : false;
		int[] hash = new int[26];
		for (int i = 0; i < m; i++) {
			hash[s1.charAt(i) - 'a']++;
			hash[s2.charAt(i) - 'a']--;
		}

		for (int i = 0; i < 26; i++)
			if (Math.abs(hash[i]) != 0)
				return false;

		return true;
	}


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
	public String minWindow1(String str,
			String pat) {
		int len1 = str.length(),
				len2 = pat.length();
		if (len1 == 0 || len2 == 0
				|| (len1 < len2))
			return "";
		int[] hashStr = new int[EXTENDED_ASCII_CHAR_SIZE];
		int[] hashPat = new int[EXTENDED_ASCII_CHAR_SIZE];
		for (int i = 0; i < len2; i++)
			hashPat[pat.charAt(i)]++;
		int l = 0, r = 0,
				minWindow = Integer.MAX_VALUE,
				minLeft = -1, count = 0,
				index = 0;
		while (r < len1) {
			index = str.charAt(r);
			hashStr[index]++;
			if (hashPat[index] != 0
					&& hashStr[index] <= hashPat[index])
				count++;
			while (l <= r && count == len2) {
				if ((r - l + 1) < minWindow) {
					minWindow = r - l + 1;
					minLeft = l;
				}
				index = str.charAt(l);
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
	public int lengthOfLongestSubstring1(
			String s) {
		int l = 0, r = 0, maxLen = 0,
				n = s.length();
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
	public int lengthOfLongestSubstring2(
			String s) {
		int l = 0, r = 0, maxLen = 0,
				n = s.length(), counter = 0;
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
	public int lengthOfLongestSubstring3(
			String s) {
		if (s.length() == 0) return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int l = 0, r = 0, maxLen = 0,
				n = s.length();
		while (r < n) {
			char ch = s.charAt(r);
			if (!map.containsKey(ch)) {
				map.put(ch, r);
				r++;
				maxLen = Math.max(maxLen, r - l);
			} else {
				l = Math.max(l, map.get(ch) + 1);
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
			map.put(ch,
					map.getOrDefault(ch, 0) + 1);
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
	public int lengthOfLongestSubstringKDistinct(
			String s, int k) {
		int result = 0;
		int i = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int j = 0; j < s.length(); j++) {
			char ch = s.charAt(i);
			map.put(ch,
					map.getOrDefault(ch, 0) + 1);
			if (map.size() <= k) {
				result = Math.max(result,
						j - i + 1);
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
	public List<Integer> findAnagrams(String s,
			String p) {
		int[] hash = new int[26];
		for (char c : p.toCharArray())
			hash[c - 'a']++;
		int l = 0, r = 0;
		List<Integer> result = new ArrayList<>();
		while (r < s.length()) {
			int index = s.charAt(r) - 'a';
			hash[index]--;
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
	public boolean checkInclusion(String s1,
			String s2) { // Here s1 is Pattern, s2 is whole string
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
			if (r - l + 1 == s1.length())
				return true;
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
	public int characterReplacement1(String s,
			int k) {
		int[] charCount = new int[26];
		int l = 0, r = 0, maxCharCount = 0,
				maxLength = 0;
		while (r < s.length()) {
			maxCharCount = Math.max(maxCharCount,
					++charCount[s.charAt(r)
							- 'A']);
			while ((r - l + 1)
					- maxCharCount > k) {
				charCount[s.charAt(l) - 'A']--;
				l++;
			}
			maxLength = Math.max(maxLength,
					r - l + 1);
			r++;
		}
		return maxLength;
	}

	// Time Complexity: O(26n)
	public int characterReplacement2(String s,
			int k) {
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

			if (countArray[ch - 'A'] == 0)
				continue;

			while (r < n) {
				if (s.charAt(r) != ch) // if char mismatches, increase the count
					count++;

				while (k < count) { // Slide the window
					if (s.charAt(l) != ch) // Here, id char mismatches decrease the count
						count--;
					l++;
				}

				maxLen = Math.max(maxLen,
						r - l + 1); // Find the max length
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
			map.put(word,
					map.getOrDefault(word, 0)
							+ 1);

		int n = s.length(),
				len = words[0].length(),
				size = words.length;

		for (int i = 0; i <= n
				- len * size; i++) {
			HashMap<String, Integer> copy = new HashMap<>(
					map);
			for (int j = 0; j < size; j++) {
				String sub = s.substring(
						i + j * len,
						i + j * len + len);
				if (copy.containsKey(sub)) {
					if (copy.get(sub) == 1)
						copy.remove(sub);
					else copy.put(sub,
							copy.get(sub) - 1);
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
	public int maxSubsetSum1(int arr[],
			int index) {
		if (index == 0) {
			return arr[0];
		} else if (index == 1) { return Math
				.max(arr[0], arr[1]); }
		return Math.max(
				maxSubsetSum1(arr, index - 2)
						+ arr[index],
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
		int max = nums[0], min = nums[0],
				result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int tempMax = max;
			max = Utils.max(max * nums[i],
					min * nums[i], nums[i]);
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
	public double findMaxAverage(int[] arr,
			int k) {
		if (arr.length == 0 || arr.length < k)
			return 0;

		int sum = 0;
		double maxAvg = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (i < k) sum += arr[i];
			else {
				maxAvg = Math.max(maxAvg,
						(double) sum / k);
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
	public int findMaxConsecutiveOnes(
			int[] nums) {
		int count = 0, maxCount = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				count++;
			} else {
				maxCount = Math.max(maxCount,
						count);
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
	public int findMaxConsecutiveOnesII1(
			int[] nums) {
		StringBuilder sb = new StringBuilder();
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
	public int findMaxConsecutiveOnesII2(
			int[] nums) {
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
				minLen = Math.min(minLen,
						r - l + 1);
				sum -= nums[l];
				l++;
			}
			r++;
		}
		return minLen == Integer.MAX_VALUE ? 0
				: minLen;
	}

	/*
	 * Subarray with given sum:Given an unsorted array A of size N of non-negative integers, find a continuous sub-array 
	 * which adds to a given number.
	 */
	public void subArrayWithGivenSum(int[] arr,
			int sum) {

		int currSum = arr[0], start = 0, end = 0,
				n = arr.length, i;
		for (i = 1; i < n; i++) {
			currSum += arr[i];

			while (currSum > sum
					&& start < i - 1) {
				currSum = currSum - arr[start];
				start++;
			}

			if (currSum == sum) {
				end = i;
				break;
			}
		}

		if (sum == currSum) System.out.println(
				(start + 1) + " " + (end + 1));
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
			if (sum == k)
				max = Math.max(max, i + 1);
			int diff = sum - k;
			if (map.containsKey(diff)) max = Math
					.max(max, i - map.get(diff));
			if (!map.containsKey(sum))
				map.put(sum, i);

		}

		return max;
	}

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

			map.put(sum,
					map.getOrDefault(sum, 0) + 1);
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
				return Arrays.copyOfRange(arr,
						oldIndex + 1, i + 1); // Sum range from next index of sum to curr index
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
	public int getLargestSumCloseToK1(int[] arr,
			int k) {
		int sum = 0, n = arr.length, len = 0,
				maxLen = 0;

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
	public int getLargestSumCloseToK2(int[] arr,
			int k) {
		int sum = 0;
		TreeSet<Integer> set = new TreeSet<Integer>();
		int maxLen = Integer.MIN_VALUE;
		set.add(0);

		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];

			Integer ceiling = set
					.ceiling(sum - k);
			if (ceiling != null) {
				maxLen = Math.max(maxLen,
						sum - ceiling);
			}

			set.add(sum);
		}

		return maxLen;
	}

	/*Count distinct elements in every window:
	 * Given an array A[] of size N and an integer K. Your task is to complete the function countDistinct() which prints
	 * the count of distinct numbers in all windows of size k in the array A[].
	 */
	public void countDistinct(int A[], int k,
			int n) {
		Map<Integer, Integer> map = new HashMap<>(); // Number, count
		Integer value;
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				value = map.get(A[i - k]);
				if (value > 1)
					map.put(A[i - k], --value);
				else map.remove(A[i - k]);
			}

			value = map.get(A[i]);
			if (value == null) value = 0;
			map.put(A[i], ++value);

			if (i >= k - 1) System.out
					.print(map.size() + " ");
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
	public int[] maxSlidingWindow(int[] nums,
			int k) {
		if (nums.length == 0 || k > nums.length)
			return new int[0];

		int n = nums.length;
		int[] result = new int[n - k + 1];
		// Queue to store the index of the elements; Max element index should be retained in the deque front.
		Deque<Integer> deque = new LinkedList<>();

		for (int i = 0; i <= n; i++) {
			if (i >= k) result[i - k] = nums[deque
					.peek()]; // or initialize index=0 and increment->result[index++]

			if (i == n) break;

			// If 'i' reaches the size k, then Remove the top element
			if (!deque.isEmpty()
					&& i - deque.peek() == k)
				deque.poll();

			// Keep removing the smaller element from the last in deque
			while (!deque.isEmpty()
					&& nums[i] > nums[deque
							.peekLast()])
				deque.removeLast();

			deque.addLast(i);
		}

		return result;
	}

	/*
	Min Max Riddle - Deque
	Max Sum of Rectangle No Larger Than K
	Shortest Unsorted Continuous Subarray
	Sliding Window Median - Heap
	 */

	/* Sliding Window Motivation Problem: To understand the Sliding Window Concepts
	 *   Find the sum of subarrays of size k in a given array
	 * 
	 * Solution:
	 *   BruteForce Algorithm: O(nk)
	 *   Sliding Window Algorithm: O(n)
	 */
	public int[] printSubarraySumRangeK(int[] arr,
			int k) {
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
}