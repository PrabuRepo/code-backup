package com.problems.patterns;

/*
 * Two State Sequence Pattern/Two Variable Approach:
 *   - This approach work for sequence problems 
 *     with last values decide the next result
 *   - Use last 2 variables as p1(prev), p2(curr) 
 *   - assign p2 to tmp 
 *   - calculate p2 using p1, based on problem 
 *   - assign tmp to p1 
 *   - Final result will be p2.
 *
 */
public class RollingArrayPatterns {

	// 1.Fibonacci numbers: Two variable approach:
	public int fibonacci4(int n) {
		if (n == 0)
			return 0;
		int prev = 1, curr = 1;
		for (int i = 2; i < n; i++) {
			int tmp = curr;
			curr += prev;
			prev = tmp;
		}
		return curr;
	}

	// 2.Decode Ways/Count Number of Encodings of a digit string:
	/* I think we can use two variables to store the previous results.
	 * Since we only use dp[i-1] and dp[i-2] to compute dp[i], why not 
	 * just use two variable prev1, prev2 instead? This can reduce the 
	 * space to O(1) */
	public int numDecodings(String s) {
		if (s.charAt(0) == '0')
			return 0;
		int prev = 1, curr = 1;
		for (int i = 1; i < s.length(); i++) {
			// if prev & curr are zero, we can jump out of the loop earlier
			if (prev == 0 && curr == 0)
				return 0;

			int tmp = curr;
			if (s.charAt(i) == '0')
				curr = 0;
			int num = Integer
					.valueOf(s.substring(i - 1, i + 1));
			if (num >= 10 && num <= 26)
				curr += prev;
			prev = tmp;
		}
		return curr;
	}

	// House thief/House Robber I, II, III
	public int houseRob14(int[] nums) {
		if (nums.length == 0)
			return 0;
		int prev = 0, curr = 0;
		for (int i = 0; i < nums.length; i++) {
			int tmp = curr;
			curr = Math.max(curr, nums[i] + prev);
			prev = tmp;
		}
		return curr;
	}

	// Min Cost Climbing Stairs
	public int minCostClimbingStairs(int[] cost) {
		int curr = 0, prev = 0;
		for (int i = 0; i < cost.length; i++) {
			int tmp = curr;
			curr = cost[i] + Math.min(curr, prev);
			prev = tmp;
		}
		return Math.min(curr, prev);
	}

	/* Maximum Sum Subsequence Non-Adjacent:
	 * Given an array of integers, find the subset of non-adjacent
	 * elements with the maximum sum. Calculate the sum of that subset.
	 */
	// Recursive slow solution.
	public int maxSubsetSum1(int arr[],
			int index) {
		if (index == 0) {
			return arr[0];
		} else if (index == 1) {
			return Math
					.max(arr[0], arr[1]);
		}
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

}