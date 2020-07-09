package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BacktrackingPatterns {

	/********************* 1.Backtracking template-1 ***********************/
	// Combination Sum-II
	public List<List<Integer>> combinationSum2(int[] nums,
			int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack5(res, new ArrayList<>(), nums, target, 0);
		res.stream()
				.forEach(k -> System.out.print(k + ", "));
		return res;

	}

	private void backtrack5(List<List<Integer>> res,
			List<Integer> tmp, int[] nums, int target,
			int start) {
		if (target < 0) return;
		else if (target == 0) res.add(new ArrayList<>(tmp));
		else {
			for (int i = start; i < nums.length; i++) {
				if (i > start && nums[i] == nums[i - 1])
					continue;
				tmp.add(nums[i]);
				backtrack5(res, tmp, nums, target - nums[i],
						i + 1);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	// Combination Sum-III
	public List<List<Integer>> combinationSum3(int n,
			int k) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack6(n, k, 1, new ArrayList<>(), res);
		res.stream().forEach(
				data -> System.out.print(data + ", "));
		return res;
	}

	public void backtrack6(int sum, int k, int start,
			List<Integer> list, List<List<Integer>> res) {
		if (list.size() == k && sum == 0)
			res.add(new ArrayList<>(list));
		else if (list.size() >= k || sum < 0) return;
		else {
			for (int i = start; i <= 9; i++) {
				list.add(i);
				backtrack6(sum - i, k, i + 1, list, res);
				list.remove(list.size() - 1);
			}
		}
	}

	// Factor Combinations
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack7(n, 2, res, new ArrayList<>());
		res.stream().forEach(
				data -> System.out.print(data + ", "));
		return res;
	}

	public void backtrack7(int n, int start,
			List<List<Integer>> res, List<Integer> tmp) {
		if (n == 1) {
			if (tmp.size() > 1)
				res.add(new ArrayList<>(tmp));
		} else {
			for (int i = start; i <= n; i++) {
				if (n < i) break;
				if (n % i == 0) {
					tmp.add(i);
					backtrack7(n / i, i, res, tmp);
					tmp.remove(tmp.size() - 1);
				}
			}
		}
	}

	/*
	 *  Letter Case Permutation:
	 *  Examples: Input: S = "a1b2" Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
	 */
	// Approach1: DFS
	public List<String> letterCasePermutation1(String S) {
		List<String> result = new ArrayList<>();
		backtrack(S.toCharArray(), 0, result);
		return result;
	}

	public void backtrack(char[] arr, int i,
			List<String> result) {
		if (i == arr.length) {
			result.add(new String(arr));
		} else if (Character.isDigit(arr[i])) {
			backtrack(arr, i + 1, result);
		} else {
			arr[i] = Character.toLowerCase(arr[i]);
			backtrack(arr, i + 1, result);
			arr[i] = Character.toUpperCase(arr[i]);
			backtrack(arr, i + 1, result);
		}
	}

	// Approach2: BFS
	public List<String> letterCasePermutation2(String S) {
		Queue<String> queue = new LinkedList<>();
		queue.add(S);

		for (int i = 0; i < S.length(); i++) {
			if (Character.isDigit(S.charAt(i))) continue;
			int size = queue.size();
			while (size-- > 0) {
				char[] arr = queue.poll().toCharArray();
				arr[i] = Character.toLowerCase(arr[i]);
				queue.add(new String(arr));
				arr[i] = Character.toUpperCase(arr[i]);
				queue.add(new String(arr));
			}
		}
		return new ArrayList<String>(queue);
	}

	// Approach3: Using Bit Manipulation
	public List<String> letterCasePermutation(String S) {
		int n = S.length();
		int size = 1 << n; // or Math.pow(2, n);
		HashSet<String> set = new HashSet<>();
		S = S.toLowerCase();
		for (int i = 0; i < size; i++) {
			char[] arr = S.toCharArray();
			for (int j = 0; j < n; j++) {
				if (Character.isLetter(arr[j])
						&& (i >> j & 1) == 1)
					arr[j] = (char) (arr[j] - 32); // or Character.toUpperCase(arr[j]);
			}
			set.add(String.valueOf(arr));
		}
		return new ArrayList<String>(set);
	}

	/********************* 2.Backtracking template -1 problems ***********************/
	// Palindrome Partitioning :
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		backtrack12(res, new ArrayList<>(), s, 0);
		res.stream().forEach(
				val -> System.out.print(val + ", "));
		return res;
	}

	public void backtrack12(List<List<String>> res,
			List<String> tmp, String s, int start) {
		if (start == s.length())
			res.add(new ArrayList<>(tmp));
		else {
			for (int i = start; i < s.length(); i++) {
				if (isPalindrome(s, start, i)) {
					tmp.add(s.substring(start, i + 1));
					backtrack12(res, tmp, s, i + 1);
					tmp.remove(tmp.size() - 1);
				}
			}
		}
	}

	public boolean isPalindrome(String s, int low,
			int high) {
		while (low < high)
			if (s.charAt(low++) != s.charAt(high--))
				return false;
		return true;
	}

	/*Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
	 represent.
	 Combination: Eg: 236 -> 3C1*3C1*3C1 -> 3*3*3 -> 27 combinations
	 */
	public List<String> letterCombinations(String num) {
		if (num.length() == 0) return new ArrayList<>();
		Map<Character, String> map = new HashMap<>();
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
		map.put('0', "");
		List<String> res = new ArrayList<>();
		backtrack13(num, 0, map, new StringBuilder(), res);
		return res;
	}

	private void backtrack13(String num, int index,
			Map<Character, String> phoneNoMap,
			StringBuilder combinations,
			List<String> res) {
		if (index >= num.length()) {
			res.add(combinations.toString());
		} else {
			String letters = phoneNoMap
					.get(num.charAt(index));
			for (int i = 0; i < letters.length(); i++) {
				combinations.append(letters.charAt(i));
				backtrack13(num, index + 1, phoneNoMap,
						combinations, res);
				combinations.deleteCharAt(index);
			}
		}
	}

	// Generate IP Addresses/Restore IP Addresses
	public List<String> restoreIpAddresses1(String s) {
		List<String> res = new ArrayList<>();
		for (int i = 1; i <= 3; i++)
			for (int j = 1; j <= 3; j++)
				for (int k = 1; k <= 3; k++)
					for (int l = 1; l <= 3; l++)
						if (i + j + k + l == s.length()) {
							String a = s.substring(0, i);
							if (Integer.parseInt(a) > 255
									|| (a.charAt(0) == '0'
											&& a.length() > 1))
								continue;
							String b = s.substring(i,
									i + j);
							if (Integer.parseInt(b) > 255
									|| (b.charAt(0) == '0'
											&& b.length() > 1))
								continue;
							String c = s.substring(i + j,
									i + j + k);
							if (Integer.parseInt(c) > 255
									|| (c.charAt(0) == '0'
											&& c.length() > 1))
								continue;
							String d = s.substring(
									i + j + k,
									i + j + k + l);
							if (Integer.parseInt(d) > 255
									|| (d.charAt(0) == '0'
											&& d.length() > 1))
								continue;
							res.add(a + "." + b + "." + c
									+ "." + d);
						}
		return res;
	}

	// Approach2: Recursive Method; DFS Search
	public List<String> restoreIpAddresses2(String s) {
		List<String> solutions = new ArrayList<String>();
		restoreIp(s, solutions, 0, "", 0);
		solutions.stream()
				.forEach(k -> System.out.print(k + ", "));
		return solutions;
	}

	private void restoreIp(String ip,
			List<String> solutions, int idx,
			String restored, int digits) {
		if (digits == 4 && idx == ip.length())
			solutions.add(restored);
		if (digits == 4) return;
		for (int i = 1; i <= 3; i++) {
			if (idx + i > ip.length()) break;
			String s = ip.substring(idx, idx + i);
			if ((s.startsWith("0") && s.length() > 1)
					|| (i == 3
							&& Integer.parseInt(s) > 255))
				continue;
			restoreIp(ip, solutions, idx + i,
					restored + s + (digits == 3 ? "" : "."),
					digits + 1);
		}
	}
}