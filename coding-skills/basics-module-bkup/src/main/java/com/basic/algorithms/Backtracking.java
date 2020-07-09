package com.basic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.common.utilities.Utils;

public class Backtracking {

	/********************* Backtracking Template - I ***********************/
	// Subsets/Subsequence of Array: Time : O(2^n)
	public List<List<Integer>> subsets1(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack1(res, new ArrayList<>(), nums, 0);
		res.stream()
				.forEach(k -> System.out.print(k + ", "));
		return res;
	}

	private void backtrack1(List<List<Integer>> res,
			List<Integer> tmp, int[] nums, int start) {
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
		res.stream()
				.forEach(k -> System.out.print(k + ", "));
		return res;
	}

	private void backtrack2(List<List<Integer>> res,
			List<Integer> tmp, int[] nums, int start) {
		res.add(new ArrayList<>(tmp));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1])
				continue;
			tmp.add(nums[i]);
			backtrack2(res, tmp, nums, i + 1);
			tmp.remove(tmp.size() - 1);
		}
	}

	// Subsets/Subseq of a string: Time: O(2^n)
	public List<List<Character>> subSequence(String str) {
		List<List<Character>> res = new ArrayList<>();
		subSeq(res, new ArrayList<>(), str, 0);
		res.stream()
				.forEach(k -> System.out.print(k + ", "));
		return res;
	}

	public void subSeq(List<List<Character>> res,
			List<Character> tmp, String str, int start) {
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
		res.stream().forEach(
				val -> System.out.print(val + ", "));
		return res;
	}

	public void backtrack3(int n, int k, int start,
			List<Integer> list, List<List<Integer>> res) {
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
	public List<List<Integer>> combinationSum(int[] nums,
			int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack4(res, new ArrayList<>(), nums, target, 0);
		res.stream().forEach(
				val -> System.out.print(val + ", "));
		return res;
	}

	private void backtrack4(List<List<Integer>> res,
			List<Integer> tmp, int[] nums, int target,
			int start) {
		if (target < 0) return;
		else if (target == 0) res.add(new ArrayList<>(tmp));
		else {
			for (int i = start; i < nums.length; i++) {
				tmp.add(nums[i]);
				backtrack4(res, tmp, nums, target - nums[i],
						i);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	// Permutations I- Time Complexity: O(n!)
	// Approach1: Time Complexity: O(n!)
	public List<List<Integer>> permute1(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack8(res, new ArrayList<>(), nums);
		res.stream()
				.forEach(k -> System.out.print(k + ", "));
		return res;
	}

	private void backtrack8(List<List<Integer>> res,
			List<Integer> tmp, int[] nums) {
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
		res.stream()
				.forEach(k -> System.out.print(k + ", "));
		return res;
	}

	public void backtrack9(int[] nums, int s,
			List<List<Integer>> res) {
		if (s >= nums.length) {
			List<Integer> list = Arrays.stream(nums).boxed()
					.collect(Collectors.<Integer>toList());
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
		backtrack10(list, new ArrayList<>(), nums,
				new boolean[nums.length]);
		list.stream().forEach(
				val -> System.out.print(val + ", "));
		return list;
	}

	private void backtrack10(List<List<Integer>> list,
			List<Integer> tmp, int[] nums, boolean[] used) {
		if (tmp.size() == nums.length) {
			list.add(new ArrayList<>(tmp));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (used[i]
						|| i > 0 && nums[i] == nums[i - 1]
								&& !used[i - 1])
					continue;
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
		res.stream()
				.forEach(k -> System.out.print(k + ", "));
		return res;
	}

	public void backtrack11(int[] nums, int s,
			List<List<Integer>> res) {
		if (s >= nums.length) {
			List<Integer> list = Arrays.stream(nums).boxed()
					.collect(Collectors.<Integer>toList());
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

}