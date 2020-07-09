package com.problems.patterns.cheatsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.common.utilities.Utils;

public class SubsetPatternCheatsheet {
	// Subsets/Subsequence of Array: Time : O(2^n)
	public List<List<Integer>> subsets1(
			int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack1(res, new ArrayList<>(), nums,
				0);
		return res;
	}

	private void backtrack1(
			List<List<Integer>> res,
			List<Integer> tmp, int[] nums,
			int start) {
		res.add(new ArrayList<>(tmp));
		for (int i = start; i < nums.length; i++) {
			tmp.add(nums[i]);
			backtrack1(res, tmp, nums, i + 1);
			tmp.remove(tmp.size() - 1);
		}
	}

	// Using Bit Manipulations:
	public List<List<Integer>> subsets2(
			int[] nums) {
		int noofSeq = (int) Math.pow(2,
				nums.length);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < noofSeq; i++) {
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < nums.length; j++) {
				if ((i & (1 << j)) != 0)
					temp.add(nums[j]);
			}
			res.add(temp);
		}
		return res;
	}

	// SubsetsII(Subset/Subseq of arr contains duplicates):
	public List<List<Integer>> subsetsWithDup(
			int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack2(res, new ArrayList<>(), nums,
				0);
		res.stream().forEach(
				k -> System.out.print(k + ", "));
		return res;
	}

	private void backtrack2(
			List<List<Integer>> res,
			List<Integer> tmp, int[] nums,
			int start) {
		res.add(new ArrayList<>(tmp));
		for (int i = start; i < nums.length; i++) {
			if (i > start
					&& nums[i] == nums[i - 1])
				continue;
			tmp.add(nums[i]);
			backtrack2(res, tmp, nums, i + 1);
			tmp.remove(tmp.size() - 1);
		}
	}

	// Permutations I- Time Complexity: O(n!)
	// Approach1: Time Complexity: O(n!)
	public List<List<Integer>> permute1(
			int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack8(res, new ArrayList<>(), nums);
		res.stream().forEach(
				k -> System.out.print(k + ", "));
		return res;
	}

	private void backtrack8(
			List<List<Integer>> res,
			List<Integer> tmp, int[] nums) {
		if (tmp.size() == nums.length) {
			res.add(new ArrayList<>(tmp));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (tmp.contains(nums[i]))
					continue;
				tmp.add(nums[i]);
				backtrack8(res, tmp, nums);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	// Approach2:
	public List<List<Integer>> permute2(
			int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack9(nums, 0, res);
		res.stream().forEach(
				k -> System.out.print(k + ", "));
		return res;
	}

	public void backtrack9(int[] nums, int s,
			List<List<Integer>> res) {
		if (s >= nums.length) {
			List<Integer> list = Arrays
					.stream(nums).boxed()
					.collect(Collectors
							.<Integer>toList());
			res.add(list);
		} else {
			for (int i = s; i < nums.length; i++) {
				Utils.swap(nums, s, i); // Arrange
				backtrack9(nums, s + 1, res);
				Utils.swap(nums, s, i); // Rearrange to original
			}
		}
	}

	// String Permutations by changing case
	/*
	 * Print all permutations of a string keeping the sequence but changing cases.
	 * Examples: Input : ab; Output : AB Ab ab aB
	 * 		     Input : ABC; Output : abc Abc aBc ABc abC AbC aBC ABC
	 */
	// Using Bit Manipulation
	public List<String> letterCasePermutation(
			String S) {
		int n = S.length();
		int size = 1 << n; // or Math.pow(2, n);
		List<String> result = new ArrayList<>();
		S = S.toLowerCase();
		for (int i = 0; i < size; i++) {
			char[] arr = S.toCharArray();
			for (int j = 0; j < n; j++) {
				if ((i >> j & 1) == 1)
					arr[j] = (char) (arr[j] - 32);
			}
			result.add(String.valueOf(arr));
		}
		return result;
	}

	// Balanced Parentheses

	// Unique Generalized Abbreviations
	/* Unique Word Abbreviation:
	 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation 
	 * is unique if no other word from the dictionary has the same abbreviation.
	 * Example:Given dictionary = [ "deer", "door", "cake", "card" ]
	 * isUnique("dear") -> false; isUnique("cart") -> true
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
	// Evaluate Expression

	// Structurally Unique Binary Search Trees

	// Count of Structurally Unique Binary Search Trees

}