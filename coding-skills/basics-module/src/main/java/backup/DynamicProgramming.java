package backup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicProgramming {
	/************** Type 1: Optimization(Compute min/max value) **********************/

	// 0 - 1 Knapsack Problem
	// Returns the maximum value that can be put in a knapsack of capacity W
	public int maxPossible1(int val[], int wt[], int W) {
		return maxPossible1(W, wt, val, wt.length - 1);
	}

	public int maxPossible1(int capacity, int wt[], int val[], int i) {
		// Base Case
		if (i == 0 || capacity == 0) return 0;

		// If weight of the nth item is more than Knapsack capacity capacity, then
		// this item cannot be included in the optimal solution
		if (wt[i - 1] > capacity) return maxPossible1(capacity, wt, val, i - 1);

		// Return the maximum of two cases:
		// (1) nth item included
		// (2) not included
		else return Math.max(val[i - 1] + maxPossible1(capacity - wt[i - 1], wt, val, i - 1),
				maxPossible1(capacity, wt, val, i - 1));
	}

	// Approach2: DP - Bottom Up Approach
	public int maxPossible2(int[] val, int[] wt, int weight) {
		int n = wt.length;
		int[][] dp = new int[n + 1][weight + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= weight; j++) {
				if (j < wt[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], (val[i - 1] + dp[i - 1][j - wt[i - 1]]));
				}
			}
		}
		return dp[n][weight];
	}

	// Approach2: DP - Bottom Up Approach - Using 1D Array Space; O(n^2) time
	public int maxPossible3(int[] val, int[] wt, int capacity) {
		int n = wt.length;
		int[] dp = new int[capacity + 1];

		for (int i = 0; i < n; i++)
			for (int j = capacity; j >= wt[i]; j++)
				dp[j] = Math.max(dp[j], (val[i] + dp[j - wt[i]]));

		return dp[capacity];
	}

	// Rod cutting
	public int cutRod1(int price[]) {
		return cutRod1(price, price.length);
	}

	public int cutRod1(int price[], int n) {
		if (n <= 0) return 0;
		int max_val = Integer.MIN_VALUE;

		// Recursively cut the rod in different pieces and compare different configurations
		for (int i = 0; i < n; i++) {
			int prev = price[i] + cutRod1(price, n - i - 1);
			// System.out.println("Value of price with " + n + " cuts: " + prev);
			max_val = Math.max(max_val, prev);
		}
		return max_val;
	}

	// Approach-2: Bottom Up Approach - 2D array; If they any given fixed length
	public int cutRod2(int[] prices, int len) {
		int maxCuts = prices.length;
		int[][] dp = new int[maxCuts + 1][len + 1];

		for (int i = 1; i <= maxCuts; i++)
			for (int j = 1; j <= len; j++)
				dp[i][j] = (j < i) ? dp[i - 1][j] : Math.max(dp[i - 1][j], prices[i - 1] + dp[i][j - i]);

		return dp[maxCuts][len];
	}

	// Approach-3: Bottom Up Approach - 1D array
	public int cutRod3(int price[]) {
		int dp[] = new int[price.length + 1];
		for (int i = 1; i <= price.length; i++) {
			for (int j = i; j <= price.length; j++) {
				dp[j] = Math.max(dp[j], dp[j - i] + price[i - 1]);
			}
		}
		return dp[price.length];
	}

	// Minimum Edit Distance
	public int minEditDistance(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		if (m == 0) return n;
		if (n == 0) return m;

		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++)
			dp[i][0] = i;
		for (int j = 0; j <= n; j++)
			dp[0][j] = j;

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
			}
		}

		return dp[m][n];
	}

	// Print Max number of As using Ctrl-A, Ctrl-C, Crtl-V
	// Approach-1: Recursive approach
	public int printMaxNoOfA1(int n) {
		if (n <= 6) return n;

		int multiplier = 2, max = -1, currValue;
		/* Formula:
		 * if n<=6 -> result = n
		 * else    -> Max(2*f(n-3), 3*f(n-4),4*f(n-5)......(n-2)*f(1)
		 */
		for (int i = n - 3; i >= 1; i--) {
			currValue = multiplier * printMaxNoOfA1(i);
			if (currValue > max) max = currValue;
			multiplier++;
		}
		return max;
	}

	// Approach2: DP- Top Down Approach
	public int printMaxNoOfA2(int n) {
		int[] dp = new int[n]; // To store the result
		Arrays.fill(dp, -1);
		return printMaxNoOfA2(n, dp);
	}

	public int printMaxNoOfA2(int n, int[] dp) {
		if (n <= 6) return n;

		int multiplier = 2, max = -1, currValue;
		/* Formula:
		 * if n<=6 -> result = n
		 * else    -> Max(2*f(n-3), 3*f(n-4),4*f(n-5)......(n-2)*f(1)
		 */
		for (int i = n - 3; i >= 1; i--) {
			if (dp[i] == -1) dp[i] = printMaxNoOfA2(i, dp);

			currValue = multiplier * dp[i];

			if (currValue > max) max = currValue;
			multiplier++;
		}
		return max;
	}

	/************** Type 2: Compute all the ways/possibilities **********************/

	/*Paint Fence:
	 */
	public int numWays(int n, int k) {
		int dp[] = { 0, k, k * k, 0 };

		if (n <= 2) return dp[n];

		for (int i = 2; i < n; i++) {
			dp[3] = (k - 1) * (dp[1] + dp[2]);
			dp[1] = dp[2];
			dp[2] = dp[3];
		}

		return dp[3];
	}

	/************** Type 4: Partition Problems: ******************************/
	/* Sub Set Sum:
	 * Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
	 * to given total. Another variation is given an array is it possible to split it up into 2 equal
	 * sum partitions. Partition need not be equal sized. Just equal sum.
	 *
	 * Time complexity - O(input.size * total_sum)
	 * Space complexity - O(input.size*total_sum)
	 */
	// Approach1: Using Recursive Function
	boolean isSubsetSum1(int arr[], int i, int sum) {
		// Base Cases
		if (sum == 0) return true;
		if (i < 0 && sum != 0) return false;

		// If last element is greater than sum, then ignore it
		if (arr[i] > sum) return isSubsetSum1(arr, i - 1, sum);

		/* else, check if sum can be obtained by any of the following
		 * 	 (a) including the last element
		 *   (b) excluding the last element 
		*/
		return isSubsetSum1(arr, i - 1, sum) || isSubsetSum1(arr, i - 1, sum - arr[i]);
	}

	public boolean isSubsetSum2(int[] arr, int sum) {
		boolean dp[][] = new boolean[arr.length + 1][sum + 1];

		for (int i = 0; i <= arr.length; i++)
			dp[i][0] = true;

		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j - arr[i - 1] >= 0) {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[arr.length][sum];

	}

	/* Partition Equal Subset Sum: 
	 * It's similar to Subset Sum Problem which asks us to find if there is a subset whose sum equals to target value. 
	 * For this problem, the target value is exactly the half of sum of array.
	 */
	// Approach1: Recursive function
	public boolean canPartition1(int arr[]) {
		int n = arr.length;
		// Calculate sum of the elements in array
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += arr[i];

		// If sum is odd, there cannot be two subsets
		// with equal sum
		if (sum % 2 != 0) return false;

		// Find if there is subset with sum equal to half
		// of total sum
		return isSubsetSum1(arr, n, sum / 2);
	}

	public boolean canPartition2(int[] nums) {
		int sum = 0;
		for (int n : nums)
			sum += n;

		if (sum % 2 != 0) return false;

		// Arrays.sort(nums);
		sum /= 2;
		// Find the subset sum for sum/2
		int n = nums.length;
		boolean[][] dp = new boolean[n + 1][sum + 1];

		for (int i = 0; i < n; i++)
			dp[i][0] = true;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) { // Here iteration from 1 to sum
				if (j < nums[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
				}
			}
		}

		return dp[n][sum];
	}

	/* Same like prev approach
	 * Time Complexity: O(n^2); Space Complexity: O(n)
	 */
	public boolean canPartition3(int[] nums) {
		int sum = 0;
		for (int n : nums)
			sum += n;

		if (sum % 2 != 0) return false;

		sum /= 2;
		// Find the subset sum for sum/2
		int n = nums.length;
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;

		for (int i = 0; i < n; i++)
			for (int j = sum; j >= nums[i]; j--) // If it is 1D array, should start from max to min
				dp[j] = dp[j] || dp[j - nums[i]];

		return dp[sum];
	}

	/* Minimum sum partition:
	 * Given an array, the task is to divide it into two sets S1 and S2 such that the absolute difference between their
	 * sums is minimum. Returns minimum possible difference between sums of two subsets 
	 * Input: 36 7 46 40 -> Sets: {}, {}
	 * Output: 23
	 */

	// Approach1: Recursive Function
	public int minSumPartition1(int[] arr) {
		int n = arr.length;
		// Compute total sum of elements
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += arr[i];

		// Compute result using recursive function
		return minSumPartition1(arr, n - 1, 0, sum);
	}

	// Function to find the minimum sum
	public int minSumPartition1(int arr[], int i, int sumCalculated, int sumTotal) {
		// If we have reached last element. Sum of one subset is sumCalculated,
		// sum of other subset is sumTotal- sumCalculated. Return absolute difference of two sums.
		if (i < 0) return Math.abs((sumTotal - sumCalculated) - sumCalculated);

		// For every item arr[i], we have two choices
		// (1) We do not include it first set
		// (2) We include it in first set
		// We return minimum of two choices
		return Math.min(minSumPartition1(arr, i - 1, sumCalculated + arr[i], sumTotal),
				minSumPartition1(arr, i - 1, sumCalculated, sumTotal));
	}

	// Approach2: Using DP - Bottom Up Approach
	public int minSumPartition2(int[] arr) {
		if (arr == null || arr.length == 0) return 0;
		int n = arr.length;

		int sum = 0;
		for (int a : arr)
			sum += a;

		boolean[][] dp = new boolean[n + 1][sum + 1];

		for (int i = 0; i <= n; i++)
			dp[i][0] = true;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j < arr[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
				}
			}
		}

		for (int j = sum / 2; j >= 0; j--)
			if (dp[n][j]) {
				System.out.println(j);
				return sum - 2 * j;
			}

		return 0;
	}

	/* Target Sum: You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols 
	 * + and -. For each integer, you should choose one from + and - as its new symbol.
	 * Find out how many ways to assign symbols to make sum of integers equal to target S.
	 */
	// Aproach1: BruteForce Approach using DFS
	public int findTargetSumWays1(int[] num, int s) {
		if (num == null || num.length == 0) return 0;
		return noOfWays(num, s, 0, 0);
	}

	public int noOfWays(int[] nums, int target, int sum, int index) {
		if (nums.length == index) return target == sum ? 1 : 0;

		return noOfWays(nums, target, sum + nums[index], index + 1)
				+ noOfWays(nums, target, sum - nums[index], index + 1);
	}

	int result = 0;

	// Aproach2: Optimization of DFS: : The idea is If the sum of all elements left is smaller than absolute value of
	// target, there will be no answer following the current path. Thus we can return.
	public int findTargetSumWays2(int[] num, int s) {
		if (num == null || num.length == 0) return 0;
		int n = num.length;
		result = 0;
		int[] sum = new int[n];
		sum[n - 1] = num[n - 1];
		for (int i = n - 2; i >= 0; i--)
			sum[i] = sum[i + 1] + num[i];

		noOfWays(num, sum, s, 0);
		return result;
	}

	public void noOfWays(int[] nums, int[] sums, int target, int index) {
		if (nums.length == index) {
			if (target == 0) result++;
			return;
		}
		if (sums[index] < Math.abs(target)) return;

		noOfWays(nums, sums, target + nums[index], index + 1);
		noOfWays(nums, sums, target - nums[index], index + 1);
	}

	// Aproach3: Optimization of DFS: Top Down DP or Memoization
	public int findTargetSumWays3(int[] num, int s) {
		if (num == null || num.length == 0) return 0;
		// key: serialized curIndex and targetSum, value: its corresponding number of ways
		Map<String, Integer> memo = new HashMap<>();
		return noOfWays(num, memo, s, 0, 0);
	}

	public int noOfWays(int[] nums, Map<String, Integer> memo, int target, int sum, int index) {
		String serializedKey = index + "-" + sum;
		if (memo.containsKey(serializedKey)) memo.get(serializedKey);

		if (nums.length == index) return target == sum ? 1 : 0;

		int add = noOfWays(nums, target, sum + nums[index], index + 1);
		int sub = noOfWays(nums, target, sum - nums[index], index + 1);
		memo.put(serializedKey, add + sum);
		return add + sub;
	}

	// Approach4: DP: Top Down Approach
	public int findTargetSumWays4(int[] num, int s) {
		int sum = 0;
		for (int n : num)
			sum += n;

		return sum < s || (s + sum) % 2 != 0 ? 0 : noOfWays(num, (sum + s) / 2);
	}

	/*Using subset sum algorithm using DP Bottom Up Approach, very fast
	 * The original problem statement is equivalent to: Find a subset of nums that need to be positive, and the rest of
	 * them negative, such that the sum is equal to target Let P be the positive subset and N be the negative subset 
	 * For example: Given nums = [1, 2, 3, 4, 5] and target = 3 
	 *   Then one possible solution is +1-2+3-4+5 = 3 Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4] 
	 * Then let's see how this can be converted to a subset
	 * sum problem: sum(P) - sum(N) = target sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N) 2 * sum(P) =
	 * target + sum(nums)
	 * So the original problem has been converted to a subset sum problem as follows: Find a subset P
	 * of nums such that sum(P) = (target + sum(nums)) / 2
	 */
	public int noOfWays(int[] num, int sum) {
		int[] dp = new int[sum + 1];
		dp[0] = 1;
		for (int i = 0; i < num.length; i++)
			for (int j = sum; j >= num[i]; j--)
				dp[j] += dp[j - num[i]];

		return dp[sum];
	}

	/* Largest Divisible Subset: 
	 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in
	 * this subset satisfies: Si % Sj = 0 or Sj % Si = 0. If there are multiple solutions, return any subset is fine.
	 * Example 1: nums: [1,2,3] Result: [1,2] (of course, [1,3] will also be ok)
	 * Example 2: nums: [1,2,4,8] Result: [1,2,4,8]
	 */
	// Approach: DP Bottom Approach; Similar to LIS
	public List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums == null || nums.length == 0) return result;

		int n = nums.length, max = 0, maxIndex = -1;
		int[] count = new int[n];
		int[] prevPath = new int[n];
		// Sort the arrau
		Arrays.sort(nums);

		for (int i = 0; i < n; i++) {
			count[i] = 1;
			prevPath[i] = -1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] % nums[j] == 0 && count[i] <= count[j]) {
					count[i] = count[j] + 1;
					prevPath[i] = j;
				}
			}
			// Find the max count
			if (count[i] > max) {
				max = count[i];
				maxIndex = i;
			}
		}

		while (maxIndex != -1) {
			result.add(nums[maxIndex]);
			maxIndex = prevPath[maxIndex];
		}

		return result;
	}

	// Using DP & BackTracking(DFS) Algorithm
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
		int n = s.length();
		// DP: Fill Palindrome partition
		boolean[][] dp = new boolean[n][n];
		for (int len = 1; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1;
				if (s.charAt(i) == s.charAt(j)) {
					if (len == 1 || len == 2) dp[i][j] = true;
					else dp[i][j] = dp[i + 1][j - 1];
				}
			}
		}

		backtrackPartition(dp, s, result, new ArrayList<>(), 0);
		return result;
	}

	public void backtrackPartition(boolean[][] dp, String s, List<List<String>> result, List<String> tempList,
			int start) {
		if (s.length() == start) {
			result.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i < s.length(); i++) {
				if (dp[start][i]) {
					tempList.add(s.substring(start, i + 1));
					backtrackPartition(dp, s, result, tempList, i + 1);
					tempList.remove(tempList.size() - 1);
				}
			}
		}
	}

	/*
	 * Palindromic Partioning II: Min Cuts
	 * 
	 */
	// Approach1:Recursion
	public int minCut1(String s) {
		if (s.length() < 2 || isPalindrome(s)) return 0;
		int n = s.length();
		int min = n - 1;
		for (int i = 1; i <= n - 1; i++)
			if (isPalindrome(s)) {
				min = Math.min(min, 1 + minCut1(s.substring(i)));
			}

		return min;
	}

	public boolean isPalindrome(String str) {
		boolean flag = true;
		int l = 0, h = str.length() - 1;
		while (l < h) {
			if (str.charAt(l++) != str.charAt(h--)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// Approach2: Using DP
	public int minCut2(String s) {
		int n = s.length();
		if (n <= 1) return 0;
		boolean[][] lookup = new boolean[n][n];
		int[] cut = new int[n];
		for (int j = 0; j < n; j++) {
			cut[j] = j;
			for (int i = 0; i <= j; i++) {
				if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || lookup[i + 1][j - 1])) {
					lookup[i][j] = true;
					if (i > 0) {
						cut[j] = Math.min(cut[j], cut[i - 1] + 1);
					} else {
						cut[j] = 0;
					}
				}
			}
		}
		return cut[n - 1];
	}

	// Approach2: Using DP - Time Complexity: O(n^3)
	public int minCut3(String str) {
		// Get the length of the string
		int n = str.length();

		/* Create two arrays to build the solution 
		   in bottom up manner 
		   C[i][j] = Minimum number of cuts needed  
		             for palindrome partitioning 
		             of substring str[i..j] 
		   P[i][j] = true if substring str[i..j] is 
		             palindrome, else false 
		   Note that C[i][j] is 0 if P[i][j] is 
		   true */
		int[][] C = new int[n][n];
		boolean[][] P = new boolean[n][n];

		int i, j, k, L; // different looping variables

		// Every substring of length 1 is a palindrome
		for (i = 0; i < n; i++) {
			P[i][i] = true;
			C[i][i] = 0;
		}

		/* L is substring length. Build the solution in 
		 bottom up manner by considering all substrings 
		 of length starting from 2 to n. The loop  
		 structure is same as Matrx Chain Multiplication 
		 problem ( 
		See https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/ )*/
		for (L = 2; L <= n; L++) {
			// For substring of length L, set different
			// possible starting indexes
			for (i = 0; i < n - L + 1; i++) {
				j = i + L - 1; // Set ending index

				// If L is 2, then we just need to
				// compare two characters. Else need to
				// check two corner characters and value
				// of P[i+1][j-1]
				if (L == 2) P[i][j] = (str.charAt(i) == str.charAt(j));
				else P[i][j] = (str.charAt(i) == str.charAt(j)) && P[i + 1][j - 1];

				// IF str[i..j] is palindrome, then
				// C[i][j] is 0
				if (P[i][j] == true) C[i][j] = 0;
				else {
					// Make a cut at every possible
					// localtion starting from i to j,
					// and get the minimum cost cut.
					C[i][j] = Integer.MAX_VALUE;
					for (k = i; k <= j - 1; k++)
						C[i][j] = Integer.min(C[i][j], C[i][k] + C[k + 1][j] + 1);
				}
			}
		}

		// Return the min cut value for complete
		// string. i.e., str[0..n-1]
		return C[0][n - 1];
	}

	/********************************* Type5: DP for DFS Problems ***********************/
	/*Android unlock patterns: 
	 * 	Given an Android 3x3 key lock screen and two integers m and n, where 1 <= m <= n <= 9, count the total number of unlock patterns
	 *  of the Android lock screen, which consist of minimum of m keys and maximum n keys.
	 *  Rules for a valid pattern:
	 */

	// cur: the current position
	// remain: the steps remaining
	int DFS(boolean vis[], int[][] skip, int cur, int remain) {
		if (remain < 0) return 0;
		if (remain == 0) return 1;
		vis[cur] = true;
		int rst = 0;
		for (int i = 1; i <= 9; ++i) {
			// If vis[i] is not visited and (two numbers are adjacent or skip number is already visited)
			if (!vis[i] && (skip[cur][i] == 0 || (vis[skip[cur][i]]))) {
				rst += DFS(vis, skip, i, remain - 1);
			}
		}
		vis[cur] = false;
		return rst;
	}

	public int numberOfPatterns(int m, int n) {
		// Skip array represents number to skip between two pairs
		int[][] skip = new int[10][10];
		skip[1][3] = skip[3][1] = 2;
		skip[1][7] = skip[7][1] = 4;
		skip[3][9] = skip[9][3] = 6;
		skip[7][9] = skip[9][7] = 8;
		skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
		boolean vis[] = new boolean[10];
		int rst = 0;
		// DFS search each length from m to n
		for (int i = m; i <= n; ++i) {
			rst += DFS(vis, skip, 1, i - 1) * 4; // 1, 3, 7, 9 are symmetric
			rst += DFS(vis, skip, 2, i - 1) * 4; // 2, 4, 6, 8 are symmetric
			rst += DFS(vis, skip, 5, i - 1); // 5
		}
		return rst;
	}

	/**************************************** Type6: Other Problems ************************/

	/* Encode String with Shortest Length: Given a non-empty string, encode the string such that its encoded length is the shortest.
	 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
	 * Input: "aaaaaaaaaa"; Output: "10[a]"
	 * Input: "aabcaabcd"; Output: "2[aabc]d"
	 * Input: "abbbabbbcabbbabbbc"
	 * Output: "2[2[abbb]c]"
	 */

	public String encode(String s) {
		String[][] dp = new String[s.length()][s.length()];
		for (int l = 0; l < s.length(); l++) {
			for (int i = 0; i < s.length() - l; i++) {
				int j = i + l;
				String substr = s.substring(i, j + 1);
				if (l < 4) dp[i][j] = substr;
				else {
					dp[i][j] = substr;
					for (int k = i; k < j; k++) {
						if ((dp[i][k] + dp[k + 1][j]).length() < dp[i][j].length()) dp[i][j] = dp[i][k] + dp[k + 1][j];
					}
					for (int k = 0; k < l; k++) {
						String repeatStr = substr.substring(0, k + 1);
						if (repeatStr != null && substr.length() % repeatStr.length() == 0
								&& substr.replaceAll(repeatStr, "").length() == 0) {
							String ss = substr.length() / repeatStr.length() + "[" + dp[i][i + k] + "]";
							if (ss.length() < dp[i][j].length()) {
								dp[i][j] = ss;
							}
						}
					}
				}
			}
		}
		return dp[0][s.length() - 1];
	}

	public String encode2(String s) {
		if (s == null || s.length() <= 4) return s;

		int len = s.length();

		String[][] dp = new String[len][len];

		// iterate all the length, stay on the disgnose of the dp matrix
		for (int l = 0; l < len; l++) {
			for (int i = 0; i < len - l; i++) {
				int j = i + l;
				String substr = s.substring(i, j + 1);
				dp[i][j] = substr;
				if (l < 4) continue;

				for (int k = i; k < j; k++) {
					if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
						dp[i][j] = dp[i][k] + dp[k + 1][j];
					}
				}

				String pattern = kmp(substr);
				if (pattern.length() == substr.length()) continue; // no repeat pattern found
				String patternEncode = substr.length() / pattern.length() + "[" + dp[i][i + pattern.length() - 1] + "]";
				if (patternEncode.length() < dp[i][j].length()) {
					dp[i][j] = patternEncode;
				}
			}
		}

		return dp[0][len - 1];
	}

	private String kmp(String s) {
		int len = s.length();
		int[] LPS = new int[len];

		int i = 1, j = 0;
		LPS[0] = 0;
		while (i < len) {
			if (s.charAt(i) == s.charAt(j)) {
				LPS[i++] = ++j;
			} else if (j == 0) {
				LPS[i++] = 0;
			} else {
				j = LPS[j - 1];
			}
		}

		int patternLen = len - LPS[len - 1];
		if (patternLen != len && len % patternLen == 0) {
			return s.substring(0, patternLen);
		} else {
			return s;
		}
	}
}