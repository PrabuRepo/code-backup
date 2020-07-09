package com.consolidated.problems.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.common.utilities.Utils;

public class Backtracking {
	/********************* Backtracking Template - I ***********************/
	// Subsets/Subsequence of Array: Time : O(2^n)
	public List<List<Integer>> subsets1(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack1(res, new ArrayList<>(), nums, 0);
		res.stream().forEach(k -> System.out.print(k + ", "));
		return res;
	}

	private void backtrack1(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start) {
		res.add(new ArrayList<>(tmp));
		for (int i = start; i < nums.length; i++) {
			tmp.add(nums[i]);
			backtrack1(res, tmp, nums, i + 1);
			tmp.remove(tmp.size() - 1);
		}
	}

	// Using Bit Manipulations:
	public List<List<Integer>> subsets2(int[] nums) {
		int noofSeq = (int) Math.pow(2, nums.length);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < noofSeq; i++) {
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < nums.length; j++) {
				/*Returns true if and only if the designated bit is set. (Computes ((this & (1<<n)) != 0).)*/
				if ((i & (1 << j)) != 0) {
					temp.add(nums[j]);
				}
			}
			res.add(temp);
		}
		return res;
	}

	// SubsetsII(Subset/Subseq of arr contains duplicates):
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack2(res, new ArrayList<>(), nums, 0);
		res.stream().forEach(k -> System.out.print(k + ", "));
		return res;
	}

	private void backtrack2(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start) {
		res.add(new ArrayList<>(tmp));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1]) continue;
			tmp.add(nums[i]);
			backtrack2(res, tmp, nums, i + 1);
			tmp.remove(tmp.size() - 1);
		}
	}

	// Subsets/Subseq of a string: Time: O(2^n)
	public List<List<Character>> subSequence(String str) {
		List<List<Character>> res = new ArrayList<>();
		subSeq(res, new ArrayList<>(), str, 0);
		res.stream().forEach(k -> System.out.print(k + ", "));
		return res;
	}

	public void subSeq(List<List<Character>> res, List<Character> tmp, String str, int start) {
		res.add(new ArrayList<>(tmp));
		for (int i = start; i < str.length(); i++) {
			tmp.add(str.charAt(i));
			subSeq(res, tmp, str, i + 1);
			tmp.remove(tmp.size() - 1);
		}
	}

	// Combinations:
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack3(n, k, 1, new ArrayList<>(), res);
		res.stream().forEach(val -> System.out.print(val + ", "));
		return res;
	}

	public void backtrack3(int n, int k, int start, List<Integer> list, List<List<Integer>> res) {
		if (list.size() == k) {
			res.add(new ArrayList<>(list));
		} else {
			for (int i = start; i <= n; i++) {
				list.add(i);
				backtrack3(n, k, i + 1, list, res);
				list.remove(list.size() - 1);
			}
		}
	}

	// Combination Sum :
	public List<List<Integer>> combinationSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack4(res, new ArrayList<>(), nums, target, 0);
		res.stream().forEach(val -> System.out.print(val + ", "));
		return res;
	}

	private void backtrack4(List<List<Integer>> res, List<Integer> tmp, int[] nums, int target, int start) {
		if (target < 0) return;
		else if (target == 0) res.add(new ArrayList<>(tmp));
		else {
			for (int i = start; i < nums.length; i++) {
				tmp.add(nums[i]);
				backtrack4(res, tmp, nums, target - nums[i], i);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	// Combination Sum-II
	public List<List<Integer>> combinationSum2(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack5(res, new ArrayList<>(), nums, target, 0);
		res.stream().forEach(k -> System.out.print(k + ", "));
		return res;

	}

	private void backtrack5(List<List<Integer>> res, List<Integer> tmp, int[] nums, int target, int start) {
		if (target < 0) return;
		else if (target == 0) res.add(new ArrayList<>(tmp));
		else {
			for (int i = start; i < nums.length; i++) {
				if (i > start && nums[i] == nums[i - 1]) continue;
				tmp.add(nums[i]);
				backtrack5(res, tmp, nums, target - nums[i], i + 1);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	// Combination Sum-III
	public List<List<Integer>> combinationSum3(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack6(n, k, 1, new ArrayList<>(), res);
		res.stream().forEach(data -> System.out.print(data + ", "));
		return res;
	}

	public void backtrack6(int sum, int k, int start, List<Integer> list, List<List<Integer>> res) {
		if (list.size() == k && sum == 0) res.add(new ArrayList<>(list));
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
		res.stream().forEach(data -> System.out.print(data + ", "));
		return res;
	}

	public void backtrack7(int n, int start, List<List<Integer>> res, List<Integer> tmp) {
		if (n == 1) {
			if (tmp.size() > 1) res.add(new ArrayList<>(tmp));
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

	// Permutations I- Time Complexity: O(n!)
	// Approach1: Time Complexity: O(n!)
	public List<List<Integer>> permute1(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack8(res, new ArrayList<>(), nums);
		res.stream().forEach(k -> System.out.print(k + ", "));
		return res;
	}

	private void backtrack8(List<List<Integer>> res, List<Integer> tmp, int[] nums) {
		if (tmp.size() == nums.length) {
			res.add(new ArrayList<>(tmp));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (tmp.contains(nums[i])) continue;
				tmp.add(nums[i]);
				backtrack8(res, tmp, nums);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	// Approach2:
	public List<List<Integer>> permute2(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack9(nums, 0, res);
		res.stream().forEach(k -> System.out.print(k + ", "));
		return res;
	}

	public void backtrack9(int[] nums, int s, List<List<Integer>> res) {
		if (s >= nums.length) {
			List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.<Integer>toList());
			res.add(list);
		} else {
			for (int i = s; i < nums.length; i++) {
				Utils.swap(nums, s, i); // Arrange
				backtrack9(nums, s + 1, res);
				Utils.swap(nums, s, i); // Rearrange to original
			}
		}
	}

	// Permutations II (contains duplicates) :
	// Approach-1
	public List<List<Integer>> permuteUnique1(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack10(list, new ArrayList<>(), nums, new boolean[nums.length]);
		list.stream().forEach(val -> System.out.print(val + ", "));
		return list;
	}

	private void backtrack10(List<List<Integer>> list, List<Integer> tmp, int[] nums, boolean[] used) {
		if (tmp.size() == nums.length) {
			list.add(new ArrayList<>(tmp));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
				used[i] = true;
				tmp.add(nums[i]);
				backtrack10(list, tmp, nums, used);
				used[i] = false;
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	// Approach-2
	public List<List<Integer>> permuteUnique2(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack11(nums, 0, res);
		res.stream().forEach(k -> System.out.print(k + ", "));
		return res;
	}

	public void backtrack11(int[] nums, int s, List<List<Integer>> res) {
		if (s >= nums.length) {
			List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.<Integer>toList());
			res.add(list);
		} else {
			Set<Integer> appeared = new HashSet<>();
			for (int i = s; i < nums.length; i++) {
				if (appeared.add(nums[i])) {
					Utils.swap(nums, s, i);
					backtrack11(nums, s + 1, res);
					Utils.swap(nums, s, i);
				}
			}
		}
	}

	/********************* Type2: Backtracking Template-1 usage problems ***********************/
	// Palindrome Partitioning :
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		backtrack12(res, new ArrayList<>(), s, 0);
		res.stream().forEach(val -> System.out.print(val + ", "));
		return res;
	}

	public void backtrack12(List<List<String>> res, List<String> tmp, String s, int start) {
		if (start == s.length()) res.add(new ArrayList<>(tmp));
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

	public boolean isPalindrome(String s, int low, int high) {
		while (low < high)
			if (s.charAt(low++) != s.charAt(high--)) return false;
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

	private void backtrack13(String num, int index, Map<Character, String> phoneNoMap, StringBuilder combinations,
			List<String> res) {
		if (index >= num.length()) {
			res.add(combinations.toString());
		} else {
			String letters = phoneNoMap.get(num.charAt(index));
			for (int i = 0; i < letters.length(); i++) {
				combinations.append(letters.charAt(i));
				backtrack13(num, index + 1, phoneNoMap, combinations, res);
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
							if (Integer.parseInt(a) > 255 || (a.charAt(0) == '0' && a.length() > 1)) continue;
							String b = s.substring(i, i + j);
							if (Integer.parseInt(b) > 255 || (b.charAt(0) == '0' && b.length() > 1)) continue;
							String c = s.substring(i + j, i + j + k);
							if (Integer.parseInt(c) > 255 || (c.charAt(0) == '0' && c.length() > 1)) continue;
							String d = s.substring(i + j + k, i + j + k + l);
							if (Integer.parseInt(d) > 255 || (d.charAt(0) == '0' && d.length() > 1)) continue;
							res.add(a + "." + b + "." + c + "." + d);
						}
		return res;
	}

	// Approach2: Recursive Method; DFS Search
	public List<String> restoreIpAddresses2(String s) {
		List<String> solutions = new ArrayList<String>();
		restoreIp(s, solutions, 0, "", 0);
		solutions.stream().forEach(k -> System.out.print(k + ", "));
		return solutions;
	}

	private void restoreIp(String ip, List<String> solutions, int idx, String restored, int digits) {
		if (digits == 4 && idx == ip.length()) solutions.add(restored);
		if (digits == 4) return;
		for (int i = 1; i <= 3; i++) {
			if (idx + i > ip.length()) break;
			String s = ip.substring(idx, idx + i);
			if ((s.startsWith("0") && s.length() > 1) || (i == 3 && Integer.parseInt(s) > 255)) continue;
			restoreIp(ip, solutions, idx + i, restored + s + (digits == 3 ? "" : "."), digits + 1);
		}
	}

	/* Generate Parentheses: write a function to generate all combinations of well-formed parentheses.
	 */
	public List<String> generateParentheses(int n) {
		if (n <= 0) return null;
		List<String> res = new ArrayList<>();
		backtrack14(0, 0, n, res, "");
		res.stream().forEach(k -> System.out.print(k + ", "));
		return res;
	}

	public void backtrack14(int op, int cp, int n, List<String> res, String str) {
		if (op == n && cp == n) res.add(str);
		if (op < cp) return;
		if (op < n) backtrack14(op + 1, cp, n, res, str + "(");
		if (cp < n) backtrack14(op, cp + 1, n, res, str + ")");
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

	public void backtrack(char[] arr, int i, List<String> result) {
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
				if (Character.isLetter(arr[j]) && (i >> j & 1) == 1) arr[j] = (char) (arr[j] - 32); // or
																									// Character.toUpperCase(arr[j]);
			}
			set.add(String.valueOf(arr));
		}
		return new ArrayList<String>(set);
	}

	// Remove Invalid Parentheses - DFS/BFS
	// Using DFS
	public List<String> removeInvalidParentheses1(String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
		int count = 0;
		for (int i = last_i; i < s.length(); i++) {
			if (s.charAt(i) == par[0]) count++;
			if (s.charAt(i) == par[1]) count--;
			if (count >= 0) continue;
			for (int j = last_j; j <= i; j++)
				if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
					remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
			return;
		}
		String reversed = new StringBuilder(s).reverse().toString();
		if (par[0] == '(') remove(reversed, ans, 0, 0, new char[] { ')', '(' });
		else ans.add(reversed);
	}

	// Using BFS
	public List<String> removeInvalidParentheses2(String s) {
		List<String> res = new ArrayList<>();
		if (s == null) return res;
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		boolean level = false;
		queue.add(s);
		visited.add(s);
		while (!queue.isEmpty()) {
			String curr = queue.poll();
			if (isValid(curr)) {
				res.add(curr);
				level = true;
			}
			if (level) continue;
			for (int i = 0; i < curr.length(); i++) {
				if (curr.charAt(i) != '(' && curr.charAt(i) != ')') continue;
				String subStr = curr.substring(0, i) + curr.substring(i + 1);
				if (visited.add(subStr)) queue.add(subStr);
			}
		}
		return res;
	}

	private boolean isValid(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') count++;
			if (s.charAt(i) == ')') count--;
			if (count < 0) return false;
		}
		return count == 0;
	}

	/* Permutation Sequence: The set [1,2,3,...,n] contains a total of n! unique permutations. By listing and labeling
	 * all of the permutations in order, we get the following sequence for n = 3: "123" "132" "213" "231" "312" "321"
	 * Given n and k, return the kth permutation sequence. 
	 * Example 1: Input: n = 3, k = 3; Output: "213";
	 * Time Complexity: O(n)
	 */
	public String getPermutation(int n, int k) {
		if (n == 0 || k == 0) return "";
		int[] fact = new int[n + 1];
		fact[0] = 1;
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			fact[i] = fact[i - 1] * i;
			list.add(i);
		}
		k--;
		int index = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			index = k / fact[n - i];
			sb.append(String.valueOf(list.get(index)));
			list.remove(index);
			k -= fact[n - i] * index;
		}
		return sb.toString();
	}

	// Word Pattern I, II - Hashing & Backtrack
	/*
	 * Word Pattern II: Given a pattern and a string str, find if str follows the same pattern.
	 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
	 * 	Examples:	pattern = "abab", str = "redblueredblue" should return true.
	 * 				pattern = "aaaa", str = "asdasdasdasd" should return true.
	 * 				pattern = "aabb", str = "xyzabcxzyabc" should return false.
	 */
	public boolean wordPatternMatch1(String pat, String str) {
		if (pat.length() == 0 && str.length() == 0) return true;
		if (pat.length() == 0) return false;
		HashMap<Character, String> map = new HashMap<Character, String>();
		return helper(pat, str, 0, 0, map);
	}

	public boolean helper(String pat, String str, int i, int j, HashMap<Character, String> map) {
		if (i == pat.length() && j == str.length()) return true;
		if (i >= pat.length() || j >= str.length()) return false;
		char ch = pat.charAt(i);
		for (int k = j + 1; k <= str.length(); k++) {
			String sub = str.substring(j, k);
			if (!map.containsKey(ch) && !map.containsValue(sub)) {
				map.put(ch, sub);
				if (helper(pat, str, i + 1, k, map)) return true;
				map.remove(ch);
			} else if (map.containsKey(ch) && map.get(ch).equals(sub)) {
				if (helper(pat, str, i + 1, k, map)) return true;
			}
		}
		return false;
	}

	/* Since containsValue() method is used here, the time complexity is O(n). We can use another set to track the value 
	 * set which leads to time complexity of O(1):
	 */
	public boolean wordPatternMatch2(String pat, String str) {
		if (pat.length() == 0 && str.length() == 0) return true;
		if (pat.length() == 0) return false;
		HashMap<Character, String> map = new HashMap<Character, String>();
		HashSet<String> set = new HashSet<String>();
		return helper(pat, str, 0, 0, map, set);
	}

	public boolean helper(String pat, String str, int i, int j, HashMap<Character, String> map, HashSet<String> set) {
		if (i == pat.length() && j == str.length()) return true;
		if (i >= pat.length() || j >= str.length()) return false;
		char c = pat.charAt(i);
		for (int k = j + 1; k <= str.length(); k++) {
			String sub = str.substring(j, k);
			if (!map.containsKey(c) && !set.contains(sub)) {
				map.put(c, sub);
				set.add(sub);
				if (helper(pat, str, i + 1, k, map, set)) return true;
				map.remove(c);
				set.remove(sub);
			} else if (map.containsKey(c) && map.get(c).equals(sub)) {
				if (helper(pat, str, i + 1, k, map, set)) return true;
			}
		}
		return false;
	}

	// Flip Game I, II:
	/*
	 * Flip Game:  You are playing the following Flip Game with your friend: Given a string that contains only these two
	 * characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a
	 * person can no longer make a move and therefore the other person will be the winner. Write a function to compute
	 * all possible states of the string after one valid move. 
	 * For example, given s = "++++", after one move, it may become one of the following states: 
	 * [ "--++", "+--+", "++--" ] 
	 * If there is no valid move, return an empty list [].
	 */
	// Flip Game I
	public List<String> generatePossibleNextMoves1(String s) {
		List<String> res = new ArrayList<>();
		int n = s.length();
		for (int i = 1; i < n; i++) {
			if (s.charAt(i - 1) == '+' && s.charAt(i) == '+') {
				res.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, n));
			}
		}
		return res;
	}

	public List<String> generatePossibleNextMoves2(String s) {
		List<String> res = new ArrayList<String>();
		if (s == null) return res;
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == arr[i + 1] && arr[i] == '+') {
				arr[i] = '-';
				arr[i + 1] = '-';
				res.add(new String(arr));
				arr[i] = '+';
				arr[i + 1] = '+';
			}
		}
		return res;
	}

	// Flip Game II:
	// Approach1: Backtracking Solution
	public boolean canWin1(String s) {
		if (s == null || s.length() < 2) return false;
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.startsWith("++", i)) {
				String subStr = s.substring(0, i) + "--" + s.substring(i + 2);
				if (!canWin1(subStr)) return true;
			}
		}
		return false;
	}

	// Approach2: Optimization: DP+ memory search
	public boolean canWin2(String s) {
		if (s == null || s.length() < 2) return false;
		Map<String, Boolean> map = new HashMap<>();
		return helper(s, map);
	}

	public boolean helper(String s, Map<String, Boolean> map) {
		if (map.containsKey(s)) return map.get(s);
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.startsWith("++", i)) {
				String subStr = s.substring(0, i) + "--" + s.substring(i + 2);
				if (!helper(subStr, map)) {
					map.put(s, true);
					return true;
				}
			}
		}
		map.put(s, false);
		return false;
	}

	// Minimum Unique Word Abbreviation - Heap/Trie/Bactracking

	/********************* Backtracking Template - II ***********************/
	// N-Queens, N-Queens II/Eight Queens
	// N Queen Problem
	public boolean solveNQ() {
		int board[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		if (solveNQUtil(board, 0) == false) {
			System.out.print("Solution does not exist");
			return false;
		}

		printSolution(board);
		return true;
	}

	// A recursive utility function to solve N Queen problem
	boolean solveNQUtil(int board[][], int col) {
		int N = board.length;
		// base case: If all queens are placed then return true
		if (col >= N) return true;

		// Consider this column and try placing this queen in all rows one by one
		for (int i = 0; i < N; i++) {
			// Check if the queen can be placed on board[i][col]
			if (isSafeBoard(board, i, col)) {
				board[i][col] = 1; // Place this queen in board[i][col]

				if (solveNQUtil(board, col + 1) == true) // recur to place rest of the queens
					return true;

				// If placing queen in board[i][col] doesn't lead to a solution then remove queen from board[i][col]
				board[i][col] = 0; // BACKTRACK
			}
		}
		return false;
	}

	/* A utility function to check if a queen can be placed on board[row][col]. Note that this function is called when "col" queens
	 * are already placed in columns from 0 to col -1. So we need to check only left side for attacking queens */
	boolean isSafeBoard(int board[][], int row, int col) {
		int N = board.length;
		int i, j;

		/* Check this row on left side */
		for (i = 0; i < col; i++)
			if (board[row][i] == 1) return false;

		/* Check upper diagonal on left side */
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1) return false;

		/* Check lower diagonal on left side */
		for (i = row, j = col; j >= 0 && i < N; i++, j--)
			if (board[i][j] == 1) return false;

		return true;
	}

	void printNQueenSolution(int board[][]) {
		int N = board.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + board[i][j] + " ");
			System.out.println();
		}
	}

	// Solve the Sudoku/Sudoku Solver
	public boolean solveSudoku(int[][] grid) {
		int n = grid.length; // Row & Col size is same

		// Find the unassigned Location
		int row = -1, col = -1;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 0) {
					row = i;
					col = j;
				}

		// All the boxes are filled; success
		if (row == -1 && col == -1) return true;

		for (int i = 1; i <= n; i++) {
			if (isSafe(grid, row, col, i)) {
				grid[row][col] = i;

				if (solveSudoku(grid)) // Success
					return true;

				// Backtracking: Failure, reset the value and try again
				grid[row][col] = 0;
			}
		}

		return false;
	}

	public boolean isAnyUnassignedLocation(int[][] grid) {
		int n = grid.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 0) return true;
		return false;
	}

	public boolean isSafe(int[][] grid, int row, int col, int num) {
		int boxSize = 3;
		if (!usedInRow(grid, row, num) && !usedInCol(grid, col, num)
				&& !usedInBox(grid, row - row % boxSize, col - col % boxSize, num))
			return true;
		return false;
	}

	public boolean usedInRow(int[][] grid, int row, int num) {
		for (int j = 0; j < grid.length; j++)
			if (grid[row][j] == num) return true;
		return false;
	}

	public boolean usedInCol(int[][] grid, int col, int num) {
		for (int i = 0; i < grid.length; i++)
			if (grid[i][col] == num) return true;
		return false;
	}

	public boolean usedInBox(int[][] grid, int boxStartRow, int boxStartCol, int num) {
		int boxSize = 3;
		for (int i = 0; i < boxSize; i++)
			for (int j = 0; j < boxSize; j++)
				if (grid[i + boxStartRow][j + boxStartCol] == num) return true;
		return false;
	}

	public void printGrid(int[][] grid) {
		int N = grid.length;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++)
				System.out.print(grid[row][col] + "  ");
			System.out.println();
		}
	}

	// Crossword Puzzle
	static final int	SIZE		= 10;
	static final int[]	R_OFFSETS	= { 0, 1 };
	static final int[]	C_OFFSETS	= { 1, 0 };

	// Crossword Puzzle: DFS & Backtracking
	public char[][] solvePuzzle(char[][] grid, String words) {
		return search(grid, Arrays.stream(words.split(";")).collect(Collectors.toSet()), 0, 0, 0);
	}

	public char[][] search(char[][] grid, Set<String> words, int r, int c, int direction) {
		if (r == SIZE) return grid;
		if (c == SIZE) return search(grid, words, r + 1, 0, 0);
		if (direction == R_OFFSETS.length) return search(grid, words, r, c + 1, 0);

		// Count the length of the path in the grid
		int insertLength = countInsertLength(grid, r, c, direction);

		if (insertLength > 1) {
			for (String word : new ArrayList<>(words)) {
				// Validate the word can be inserted in grid
				if (canInsertWord1(grid, r, c, direction, insertLength, word)) {
					List<Integer> insertOffsets = new ArrayList<Integer>();

					for (int i = 0; i < insertLength; i++) {
						int row = r + R_OFFSETS[direction] * i;
						int col = c + C_OFFSETS[direction] * i;

						if (grid[row][col] == '-') {
							grid[row][col] = word.charAt(i);

							insertOffsets.add(i);
						}
					}
					words.remove(word);

					char[][] subResult = search(grid, words, r, c, direction + 1);

					if (subResult != null) return subResult;

					// Backtracking: Reassign the values
					words.add(word);

					for (int insertOffset : insertOffsets) {
						// Calculate row & col using prev offset
						int row = r + R_OFFSETS[direction] * insertOffset;
						int col = c + C_OFFSETS[direction] * insertOffset;

						grid[row][col] = '-';
					}
				}
			}

			return null;
		} else {
			return search(grid, words, r, c, direction + 1);
		}
	}

	public int countInsertLength(char[][] grid, int r, int c, int direction) {
		int prevRow = r - R_OFFSETS[direction];
		int prevCol = c - C_OFFSETS[direction];

		if (prevRow >= 0 && prevRow < SIZE && prevCol >= 0 && prevCol < SIZE && grid[prevRow][prevCol] != '+') return 0;

		int insertLength = 0;
		while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] != '+') {
			insertLength++;
			r += R_OFFSETS[direction];
			c += C_OFFSETS[direction];
		}
		return insertLength;
	}

	public boolean canInsertWord1(char[][] grid, int r, int c, int direction, int insertLength, String word) {
		if (word.length() != insertLength) return false;

		for (int k = 0; k < word.length(); k++) {
			int row = r + R_OFFSETS[direction] * k;
			int col = c + C_OFFSETS[direction] * k;
			if (grid[row][col] != '-' && grid[row][col] != word.charAt(k)) return false;
		}

		return true;
	}

	public boolean canInsertWord2(char[][] grid, int r, int c, int direction, int insertLength, String word) {

		return word.length() == insertLength && IntStream.range(0, word.length()).allMatch(k -> {
			int row = r + R_OFFSETS[direction] * k;
			int col = c + C_OFFSETS[direction] * k;

			return grid[row][col] == '-' || grid[row][col] == word.charAt(k);
		});
	}

	// The Knight’s tour problem
	public void knightTour(int N) {
		int moveCount = 1;
		int[][] table = new int[N][N];

		int[] xMove = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int[] yMove = { 1, 2, 2, 1, -1, -2, -2, -1 };

		/*Why this possibilities are not working?
		 * int[] xMove = { 2, 2, -2, -2, 1, 1, -1, -1 };
		int[] yMove = { 1, -1, 1, -1, 2, -2, 2, -2 };*/

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				table[i][j] = -1;

		table[0][0] = 0;

		knightTourUtil(0, 0, N, moveCount, xMove, yMove, table);

		printSolution(table, N);
	}

	static int count = 0;

	private boolean knightTourUtil(int x, int y, int N, int moveCount, int[] xMove, int[] yMove, int[][] table) {
		int nextX, nextY;
		count++;
		if (moveCount == (N * N)) return true;

		for (int i = 0; i < N; i++) {
			nextX = x + xMove[i];
			nextY = y + yMove[i];
			if (isSafe(nextX, nextY, N, table)) {
				// System.out.println("if-> X:" + nextX + " Y:" + nextY);
				table[nextX][nextY] = moveCount;
				if (knightTourUtil(nextX, nextY, N, moveCount + 1, xMove, yMove, table)) {
					return true;
				} else {
					// System.out.println("else-> X:" + nextX + " Y:" + nextY);
					table[nextX][nextY] = -1;
				}
			}
		}

		return false;
	}

	private boolean isSafe(int nextX, int nextY, int N, int[][] table) {
		return (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && table[nextX][nextY] == -1);
	}

	private void printSolution(int[][] table, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(table[i][j] + "  ");
			}
			System.out.println();
		}
	}

	// Rat in a Maze
	public boolean solveMaze(int maze[][]) {
		int sol[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		if (solveMazeUtil(maze, 0, 0, sol) == false) {
			System.out.print("Solution doesn't exist");
			return false;
		}

		printSolution(sol);
		return true;
	}

	// A recursive utility function to solve Maze problem
	boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) {
		int N = maze.length;
		if (x == N - 1 && y == N - 1) { // Base case: When it reaches the end of row & col, return true
			sol[x][y] = 1;
			return true;
		}

		// Check if maze[x][y] is valid
		if (isSafe(maze, x, y) == true) {
			sol[x][y] = 1; // mark x,y as part of solution path

			if (solveMazeUtil(maze, x + 1, y, sol)) // Move forward in x direction
				return true;

			if (solveMazeUtil(maze, x, y + 1, sol)) // Move down in y direction
				return true;

			sol[x][y] = 0; // If none of the above movements works then BACKTRACK: un mark x,y as part of solution path
			return false;
		}

		return false;
	}

	boolean isSafe(int maze[][], int x, int y) {
		int N = maze.length;
		// if (x,y outside maze) return false
		return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
	}

	public void printSolution(int sol[][]) {
		int N = sol.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + sol[i][j] + " ");
			System.out.println();
		}
	}

}