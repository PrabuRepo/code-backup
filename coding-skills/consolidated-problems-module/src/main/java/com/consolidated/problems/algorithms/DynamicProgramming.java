package com.consolidated.problems.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.model.Box;
import com.common.model.TreeNode;
import com.common.utilities.Utils;

/* Grokking Dynamic Programming Patterns for Coding Interviews:
 * All the problems will be solved using 3 approaches,
 *    - Recursion
 *    - DP: Top Down Approach or Memoization 
 *    - DP: Bottom Up Approach or Tabulation
 */
public class DynamicProgramming {

	/***************************** Pattern 1: 0/1 Knapsack *************************/
	/*  0/1 Knapsack
		Subset Sum
		Equal Subset Sum Partition
		Minimum Subset Sum Difference
		Count of Subset Sum
		Target Sum
	*/
	public int knapsack1(int val[], int wt[], int W) {
		return knapsack1(W, wt, val, wt.length - 1);
	}

	public int knapsack1(int capacity, int wt[], int val[], int i) {
		if (i < 0 || capacity == 0) return 0;
		// System.out.print(i + " "); -> Uncomment to understand the iteration
		if (wt[i] > capacity) return knapsack1(capacity, wt, val, i - 1);
		return Math.max(val[i] + knapsack1(capacity - wt[i], wt, val, i - 1), knapsack1(capacity, wt, val, i - 1));
	}

	// Approach2: DP - Top Up Approach
	public int knapsack2(int[] val, int[] wt, int weight) {
		return 0;
	}

	// Approach3: DP - Bottom Up Approach - 2D Array
	public int knapsack31(int[] val, int[] wt, int weight) {
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

	// Approach3: DP - Bottom Up Approach - 1D Array
	public int knapsack32(int[] val, int[] wt, int capacity) {
		int n = wt.length;
		int[] dp = new int[capacity + 1];
		for (int i = 0; i < n; i++)
			for (int j = capacity; j >= wt[i]; j--)
				dp[j] = Math.max(dp[j], (val[i] + dp[j - wt[i]]));

		return dp[capacity];
	}

	/* Subset Sum:
	 * Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
	 * to given total. Another variation is given an array is it possible to split it up into 2 equal
	 * sum partitions. Partition need not be equal sized. Just equal sum.
	 */

	// Approach1: Using Recursive Function; Time: O(2^n)
	boolean isSubsetSum1(int arr[], int sum) {
		return isSubsetSum1(arr, arr.length - 1, sum);
	}

	boolean isSubsetSum1(int arr[], int i, int sum) {
		if (sum == 0) return true;
		if (i < 0) return false;
		if (arr[i] > sum) return isSubsetSum1(arr, i - 1, sum);
		return isSubsetSum1(arr, i - 1, sum) || isSubsetSum1(arr, i - 1, sum - arr[i]);
	}

	// Approach3: Bottom Up DP - Time: O(n*sum); Space: O(n*sum)
	public boolean isSubsetSum31(int[] arr, int sum) {
		boolean dp[][] = new boolean[arr.length + 1][sum + 1];
		for (int i = 0; i <= arr.length; i++)
			dp[i][0] = true;
		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j < arr[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
				}
			}
		}
		return dp[arr.length][sum];
	}

	// Approach3: Bottom Up DP - Time: O(n*sum); Space: O(sum)
	public boolean isSubsetSum32(int[] arr, int sum) {
		boolean dp[] = new boolean[sum + 1];
		dp[0] = true;
		for (int i = 0; i < arr.length; i++)
			for (int j = sum; j >= arr[i]; j--)
				dp[j] = dp[j] || dp[j - arr[i]];
		return dp[sum];
	}

	/* Partition Equal Subset Sum/Equal Subset Sum Partition/Partition problem: 
	 * It's similar to Subset Sum Problem which asks us to find if there is a subset whose sum equals to target value. 
	 * For this problem, the target value is exactly the half of sum of array.
	 */
	// Approach1: Recursive function; Time Complexity: O(2^n)
	public boolean canEqualSubsetPartition1(int arr[]) {
		int n = arr.length, sum = 0;
		for (int i = 0; i < n; i++)
			sum += arr[i];
		if (sum % 2 == 1) return false;
		return isSubsetSum1(arr, sum / 2);
	}

	// Approach3: Bottom Up DP - Time: O(n*sum);
	// Space: O(n*sum)
	public boolean canEqualSubsetPartition31(int[] nums) {
		int sum = 0;
		for (int n : nums)
			sum += n;
		if (sum % 2 != 0) return false;
		sum /= 2;
		int n = nums.length;
		boolean[][] dp = new boolean[n + 1][sum + 1];
		for (int i = 0; i < n; i++)
			dp[i][0] = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j < nums[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
				}
			}
		}
		return dp[n][sum];
	}

	/* Approach3: Bottom Up DP - 
	 * Time: O(n*sum); Space: O(sum)
	 */
	public boolean canEqualSubsetPartition32(int[] nums) {
		int sum = 0;
		for (int n : nums)
			sum += n;
		if (sum % 2 != 0) return false;
		sum /= 2;
		int n = nums.length;
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;
		for (int i = 0; i < n; i++)
			for (int j = sum; j >= nums[i]; j--)
				dp[j] = dp[j] || dp[j - nums[i]];
		return dp[sum];
	}

	/* Minimum sum partition/Minimum Subset Sum Difference:
	 * Given an array, the task is to divide it into two sets S1 and S2 such that the absolute difference between their
	 * sums is minimum. Returns minimum possible difference between sums of two subsets 
	 * Input: 36 7 46 40 -> Sets: {}, {}
	 * Output: 23
	 */

	// Approach1: Recursive Function; Time Complexity: O(2^n)
	public int minSumPartition1(int[] arr) {
		int n = arr.length, sum = 0;
		for (int i = 0; i < n; i++)
			sum += arr[i];
		return minSumPartition1(arr, n - 1, 0, sum);
	}

	// Function to find the minimum sum
	public int minSumPartition1(int arr[], int i, int subsetSum, int sumTotal) {
		if (i < 0) {
			int remaining = sumTotal - subsetSum;
			return Math.abs(remaining - subsetSum);
		}
		return Math.min(minSumPartition1(arr, i - 1, subsetSum + arr[i], sumTotal),
				minSumPartition1(arr, i - 1, subsetSum, sumTotal));
	}

	// Approach3: Using DP - Bottom Up Approach
	public int minSumPartition3(int[] arr) {
		int n = arr.length, sum = 0;
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

	/*
	 * Count of Subset Sum/Perfect Sum Problem:
	 * 
	 */
	// Approach1: Using Recursive Function; Time Complexity: O(2^n)
	public int countSubsetSum1(int arr[], int sum) {
		return countSubsetSum1(arr, arr.length - 1, sum);
	}

	private int countSubsetSum1(int arr[], int i, int sum) {
		if (sum == 0) return 1;
		if (i < 0) return 0;
		if (arr[i] > sum) return countSubsetSum1(arr, i - 1, sum);
		return countSubsetSum1(arr, i - 1, sum) + countSubsetSum1(arr, i - 1, sum - arr[i]);
	}

	// Approach3: Bottom Up DP - Time: O(n*sum); Space: O(n*sum)
	public int countSubsetSum3(int[] nums, int sum) {
		int n = nums.length, count = 0;
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;
		for (int i = 0; i < n; i++)
			for (int j = sum; j >= nums[i]; j--) {
				if (j == sum && dp[j - nums[i]]) count++;
				else dp[j] = dp[j] || dp[j - nums[i]];
			}

		return count;
	}

	/* Target Sum: 
	 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols 
	 * + and -. For each integer, you should choose one from + and - as its new symbol.
	 * Find out how many ways to assign symbols to make sum of integers equal to target S.
	 */
	// Aproach1: Recursive Algorithm; Time Complexity: O(2^n)
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

	// Aproach2: Top Down DP or Memoization
	public int findTargetSumWays2(int[] num, int s) {
		if (num == null || num.length == 0) return 0;
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

	// Approach3: DP: Bottom Up Approach
	/*Using subset sum algorithm using DP Bottom Up Approach,Let's see how this can be converted to a subset sum problem: 
	 * sum(P) - sum(N) = target; // where P & N set of elements
	 * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N); //Add sum(P) + sum(N) on both sides
	 *  2 * sum(P) =  target + sum(P) + sum(N);
	 *  2* sum(P) = target + sum(nums); // where sum(nums) = sum(P) + sum(N); 
	 * So the original problem has been converted to a subset sum problem as follows:
	 *  Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
	 */
	public int findTargetSumWays3(int[] num, int s) {
		int sum = 0;
		for (int n : num)
			sum += n;
		return sum < s || (s + sum) % 2 != 0 ? 0 : noOfWays(num, (sum + s) / 2);
	}

	public int noOfWays(int[] num, int sum) {
		int[] dp = new int[sum + 1];
		dp[0] = 1;
		for (int i = 0; i < num.length; i++)
			for (int j = sum; j >= num[i]; j--)
				dp[j] += dp[j - num[i]];

		return dp[sum];
	}

	/*
	 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
	 * Example 1: Input:  [3,4,8,5], backpack size=10; Output:  9
	 * Example 2: Input:  [2,3,5,7], backpack size=12; Output:  12
	 */
	// 1.Recursive Approach:
	public int backPack1(int m, int[] A) {
		return backPack1(A, m, 0, 0);
	}

	public int backPack1(int[] arr, int max, int curr, int i) {
		if (i >= arr.length) return curr;
		if (curr + arr[i] > max) return backPack1(arr, max, curr, i + 1);

		return Math.max(backPack1(arr, max, curr + arr[i], i + 1), backPack1(arr, max, curr, i + 1));
	}

	// DP: Bottom Up Approach - 1D Array; Time:O(n.cap); Space:O(cap)
	public int backPack3(int cap, int[] A) {
		int[] dp = new int[cap + 1];
		for (int i = 0; i < A.length; i++) {
			for (int j = cap; j >= A[i]; j--) {
				if (j >= A[i]) dp[j] = Math.max(dp[j], A[i] + dp[j - A[i]]);
			}
		}
		return dp[cap];
	}

	/***************************** Pattern 2: Unbounded Knapsack *************************/
	/*
	Unbounded Knapsack
	Rod Cutting
	Coin Change
	Minimum Coin Change
	Maximum Ribbon Cut
	*/

	// Unbounded Knapsack: 3 Approaches
	// 1.Recursive Approach
	public int unboundedKnapsack1(int val[], int wt[], int W) {
		return unboundedKnapsack1(W, wt, val, wt.length - 1);
	}

	public int unboundedKnapsack1(int capacity, int wt[], int val[], int i) {
		if (i < 0 || capacity == 0) return 0;
		// System.out.print(i + " ");
		if (wt[i] > capacity) return unboundedKnapsack1(capacity, wt, val, i - 1);
		return Utils.max(val[i] + unboundedKnapsack1(capacity - wt[i], wt, val, i),
				val[i] + unboundedKnapsack1(capacity - wt[i], wt, val, i - 1),
				unboundedKnapsack1(capacity, wt, val, i - 1));
	}

	// Using DP - Bottom Up Approach
	public int unboundedKnapsack3(int val[], int wt[], int capacity) {
		int n = wt.length;
		int dp[] = new int[capacity + 1];
		for (int j = 0; j < n; j++)
			for (int i = wt[j]; i <= capacity; i++)
				// if (wt[j] <= i) {
				dp[i] = Math.max(dp[i], dp[i - wt[j]] + val[j]);
		// }

		return dp[capacity];
	}

	// Small modification of above
	public int unboundedKnapsack34(int val[], int wt[], int capacity) {
		int n = wt.length;
		int dp[] = new int[capacity + 1];
		for (int i = 0; i <= capacity; i++)
			for (int j = 0; j < n; j++)
				if (wt[j] <= i) {
					dp[i] = Math.max(dp[i], dp[i - wt[j]] + val[j]);
				}

		return dp[capacity];
	}

	// Rod cutting
	/*public int cutRod(int price[], int n) {
		if (n <= 0) return 0;
		int max_val = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {			
			int prev = price[i] + cutRod(price, n - i - 1);
			max_val = Math.max(max_val, prev);
		}
		return max_val;
	}*/

	// Rod cutting
	public int cutRod1(int price[]) {
		return cutRod1(price, price.length);
	}

	public int cutRod1(int price[], int n) {
		if (n <= 0) return 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			System.out.print(i + " ");
			max = Math.max(max, price[i] + cutRod1(price, n - i - 1));
		}
		return max;
	}

	public int cutRod2(int[] prices) {
		int len = prices.length;
		int[][] dp = new int[len + 1][len + 1];
		for (int i = 1; i <= len; i++)
			for (int j = 1; j <= len; j++)
				dp[i][j] = (j < i) ? dp[i - 1][j] : Math.max(dp[i - 1][j], prices[i - 1] + dp[i][j - i]);
		return dp[len][len];
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

	// How to print maximum number of A’s using given four keys. Print Max number of As using Ctrl-A, Ctrl-C, Crtl-V
	// Approach-1: Recursive approach
	public int printMaxNoOfA1(int n) {
		if (n <= 6) return n;
		int multiplier = 2, max = -1, currValue;
		for (int i = n - 3; i >= 1; i--) {
			currValue = multiplier * printMaxNoOfA1(i);
			if (currValue > max) max = currValue;
			multiplier++;
		}
		return max;
	}

	// Approach-2: Dynamic Programming approach
	/* The above function computes the same subproblems again and again. Re computations of same subproblems can be avoided
	 * by storing the solutions to subproblems and solving problems in bottom up manner.*/
	public int printMaxNoOfA2(int n) {
		if (n <= 6) return n;
		int[] result = new int[n + 1];
		for (int i = 1; i <= 6; i++)
			result[i] = i;
		for (int i = 7; i <= n; i++) {
			int multiplier = 2, currValue;
			for (int j = i - 3; j >= 1; j--) {
				currValue = multiplier * result[j];
				if (currValue > result[i]) result[i] = currValue;
				multiplier++;
			}
		}
		return result[n];
	}
	/* Jump Game II: Minimum number of jumps to reach end. It can be solved using 
	 *   1. Recursive Approach
	 *   2. Linear Algorithm - Greedy Approach 
	 *   3. DP Approach
	 * 	 4. BFS Approach 
	 */

	// Minimum number of jumps to reach end -> Its similar to snake and ladder problems
	public int minJumps(int arr[]) {
		return minJumps(arr, 0, arr.length - 1);
	}

	// Returns minimum number of jumps to reach arr[h] from arr[l]
	public int minJumps(int arr[], int index, int n) {
		if (index >= n) return 0;
		if (arr[index] == 0) return Integer.MAX_VALUE;
		int minJumps = Integer.MAX_VALUE;
		for (int i = index + 1; i <= n && i <= index + arr[index]; i++) {
			int currJump = minJumps(arr, i, n);
			if (currJump != Integer.MAX_VALUE && currJump + 1 < minJumps) {
				minJumps = currJump + 1;
			}
		}
		return minJumps;
	}

	// Approach3: DP - Bottom up Approach; Time: O(n^2); Space: O(n)
	public int minJumps3(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < n - 1; i++)
			for (int j = 1; j <= nums[i] && i + j < n; j++)
				dp[i + j] = Math.min(dp[i + j], 1 + dp[i]);
		return dp[n - 1];
	}

	// Efficient Approach: Greedy Algorithm- Linear Approach
	public int minJumps4(int[] nums) {
		int currMax = 0, currEnd = 0, jumps = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			currMax = Math.max(currMax, i + nums[i]);
			if (i == currEnd) {
				jumps++;
				currEnd = currMax;
			}
		}
		return jumps;
	}

	/*
	 * Jump Game I:Given an array of non-negative integers, you are initially positioned at the first index of the array. 
	 * Each element in the array represents your maximum jump length at that position. Determine if you are able to reach 
	 * the last index.
	 * 
	 */
	public boolean canJump(int[] nums) {
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > max) return false;
			max = Math.max(max, i + nums[i]);
		}

		return true;
	}

	// Recursive Solution: Time Complexity: Exponential O(S^n), where S = Total amount
	public int coinChange1(int[] coins, int amount) {
		int result = minCoins(coins, amount);
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	public int minCoins(int coins[], int amount) {
		if (amount < 0) return -1;
		if (amount == 0) return 0;
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int currMin = minCoins(coins, amount - coin);
			if (currMin >= 0 && currMin < min) min = currMin + 1;
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	// DP(Memoization): Time Complexity: O(S*n)
	public int coinChange2(int[] coins, int amount) {
		return minCoins(coins, amount, new int[amount]);
	}

	public int minCoins(int coins[], int amount, int[] dp) {
		if (amount < 0) return -1;
		if (amount == 0) return 0;
		if (dp[amount - 1] != 0) return dp[amount - 1];
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int currMin = minCoins(coins, amount - coin, dp);
			if (currMin >= 0 && currMin < min) min = currMin + 1;
		}
		dp[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
		return dp[amount - 1];
	}

	// DP(Bottom up): Time Complexity: O(S*n)
	public int coinChange(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[max];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				if (coin <= i) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

	/*Coin Change 2 /Coins - No of ways to get amount
	 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent), 
	 * write code to calculate the number of ways of representing n cents.
	 */
	public int coinChanges(int[] coins, int amount) {
		return coinChange1(coins, coins.length - 1, amount);
	}

	public int coinChange1(int coins[], int i, int cash) {
		if (cash == 0) return 1;
		if (cash < 0) return 0;
		if (i <= 0 && cash >= 1) return 0;
		return coinChange1(coins, i - 1, cash) + coinChange1(coins, i, cash - coins[i]);
	}

	// Approach: DP Bottom up Approach
	public int coinChanges3(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int coin : coins) {
			for (int i = 1; i <= amount; i++) {
				if (i >= coin) dp[i] += dp[i - coin];
			}
		}
		return dp[amount];
	}

	// Combination Sum IV
	// Approach1: Recursion
	public int combinationSum41(int[] nums, int target) {
		if (target == 0) return 1;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) count += combinationSum4(nums, target - nums[i]);
		}
		return count;
	}

	// Approach2: DP Top down or Memoization
	public int combinationSum42(int[] nums, int target) {
		int[] dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		return helper(nums, target, dp);
	}

	public int helper(int[] nums, int target, int[] dp) {
		if (dp[target] != -1) return dp[target];
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) count += combinationSum4(nums, target - nums[i]);
		}
		dp[target] = count;
		return count;
	}

	// Approach3: DP Bottom up approach
	public int combinationSum4(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 1; i <= target; i++) {
			for (int num : nums) {
				if (i >= num) dp[i] += dp[i - num];
			}
		}
		return dp[target];
	}

	/*
	 * Min cost to Paint House: 
	 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting 
	 * each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
	 *  
	 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is 
	 * the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... 
	 * Find the minimum cost to paint all houses.
	 */

	public int minCostToPaintHouse(int[][] costs) {
		if (costs == null || costs.length == 0) return 0;
		int n = costs.length;
		for (int i = 1; i < n; i++) {
			costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
			costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
			costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
		}
		return Utils.min(costs[n - 1][0], costs[n - 1][1], costs[n - 1][2]);
	}

	/*
	 * Paint House II: 
	 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with
	 * a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
	 * 
	 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] 
	 * is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... 
	 * Find the minimum cost to paint all houses.
	 * 
	 * 	Ref: http://buttercola.blogspot.com/2015/09/leetcode-paint-house-ii.html
	 */
	public int minCostToPaintHouseII(int[][] costs) {
		if (costs == null || costs.length == 0) return 0;
		int n = costs.length;
		int k = costs[0].length;
		int[][] dp = new int[n][k];
		for (int i = 0; i < k; i++)
			dp[0][i] = costs[0][i];
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < k; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int m = 0; m < k; m++) {
					if (m != j) {
						dp[i][j] = Math.min(dp[i - 1][m] + costs[i][j], dp[i][j]);
					}
				}
			}
		}
		int minCost = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++)
			minCost = Math.min(minCost, dp[n - 1][i]);
		return minCost;
	}

	/***************************** Pattern 3: Fibonacci Numbers *************************/
	/*
	Fibonacci numbers
	Staircase
	Number factors
	Minimum jumps to reach the end
	Minimum jumps with fee
	House thief
	*/

	// Fibonacci Number calculation:
	// 1.using recursive function
	public int fibonacci1(int n) {
		if (n <= 1) return n;
		return fibonacci1(n - 1) + fibonacci1(n - 2);
	}

	// 2.DP: Top Down or Memoization
	public int fibonacci2(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		return fibonacci2(n, dp);
	}

	public int fibonacci2(int n, int[] dp) {
		if (dp[n] != -1) return dp[n];
		if (n <= 1) {
			dp[n] = n;
		} else {
			dp[n] = fibonacci2(n - 1) + fibonacci2(n - 2);
		}
		return dp[n];
	}

	// 3.DP:Bottom Up or Tabulation
	public static long fibonacci3(int n) {
		long[] fib = new long[n + 1];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i <= n; i++)
			fib[i] = fib[i - 1] + fib[i - 2];
		return fib[n];
	}

	// Staircase
	// 1.Recursive Solution
	public int tripleSteps1(int n) {
		if (n < 0) return 0;
		else if (n == 0) return 1;
		return tripleSteps1(n - 1) + tripleSteps1(n - 2) + tripleSteps1(n - 3);
	}

	// 2. DP- Top down: Memoization
	public int tripleSteps2(int n) {
		if (n <= 0) return 0;
		int[] dp = new int[n + 1];
		return tripleStepsUtil(n, dp);
	}

	public int tripleStepsUtil(int n, int[] dp) {
		if (n < 0) return 0;
		else if (n == 0) return 1;
		if (dp[n] == 0) {
			dp[n] = tripleStepsUtil(n - 1, dp) + tripleStepsUtil(n - 2, dp) + tripleStepsUtil(n - 3, dp);
		}
		return dp[n];
	}

	// 3. DP- Bottom up: Tabulation
	public int tripleSteps3(int n) {
		if (n <= 0) return 0;
		else if (n == 1) return 1;
		else if (n == 2) return 2;
		else if (n == 3) return 4;
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i <= n; i++)
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		return dp[n];
	}

	// Approach1: Using Recursion
	public int numDecodings1(String s) {
		if (s.length() == 0) return 0;
		return numDecodings(s, 0);
	}

	public int numDecodings(String s, int i) {
		int n = s.length();
		if (i == n) return 1;
		if (i > n || s.charAt(i) == '0') return 0;
		int sum = numDecodings(s, i + 1);
		if (i + 1 < n)
			if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6')) sum += numDecodings(s, i + 2);
		return sum;
	}

	// Approach2: DP-Bottom up
	/*I used a dp array of size n + 1 to save subproblem solutions. dp[0] means an empty string will have one way to decode, dp[1] means the way to decode a string of size 1. I then check one digit and two digit combination and save the results along the way. In the end, dp[n] will be the end result.*/
	public int numDecodings3(String s) {
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) != '0' ? 1 : 0;
		for (int i = 2; i <= n; i++) {
			int first = Integer.valueOf(s.substring(i - 1, i));
			int second = Integer.valueOf(s.substring(i - 2, i));
			if (first >= 1 && first <= 9) dp[i] += dp[i - 1];
			if (second >= 10 && second <= 26) dp[i] += dp[i - 2];
		}
		return dp[n];
	}

	// Modification of approach2:
	/* I think we can use two variables to store the previous results. Since we only use dp[i-1] and dp[i-2] to compute dp[i], why not just use two variable prev1, prev2 instead? This can reduce the space to O(1) */
	public int numDecodings32(String s) {
		if (s.charAt(0) == '0') return 0;
		int prev1 = 1;
		int prev2 = 1;
		int res = prev2;
		for (int i = 1; i < s.length(); i++) {
			res = 0;
			if (prev1 == 0 && prev2 == 0) return 0;
			if (s.charAt(i) != '0') res += prev2;
			int num = Integer.valueOf(s.substring(i - 1, i + 1));
			if (num >= 10 && num <= 26) res += prev1;
			prev1 = prev2;
			prev2 = res;
		}
		return res;
	}

	/* House Robber: 
	 * You are a professional robber planning to rob houses along a street. Each house has a certain
	 * amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have
	 * security system connected and it will automatically contact the police if two adjacent houses were broken into on
	 * the same night. Given a list of non-negative integers representing the amount of money of each house, determine
	 * the maximum amount of money you can rob tonight without alerting the police.
	 * 
	 * Solution: 
	 *   5 Different Approaches: 
	 *     1.Recursion
	 *     2.DP-Top Down Approach
	 *     3.DP-Bottom Up Approach
	 *     4.Bottom Up - 2 variable approach
	 *     5.Bottom up - odd/even approach
	 */
	// 1.Recursion:
	public int houseRob11(int[] nums) {
		return rob1(nums, nums.length - 1);
	}

	private int rob1(int[] nums, int i) {
		if (i < 0) return 0;
		return Math.max(rob1(nums, i - 2) + nums[i], rob1(nums, i - 1));
	}

	// 2.DP-Top Down Approach
	int[] memo;

	public int houseRob12(int[] nums) {
		memo = new int[nums.length + 1];
		Arrays.fill(memo, -1);
		return rob2(nums, nums.length - 1);
	}

	private int rob2(int[] nums, int i) {
		if (i < 0) return 0;
		if (memo[i] >= 0) return memo[i];
		int result = Math.max(rob2(nums, i - 2) + nums[i], rob2(nums, i - 1));
		memo[i] = result;
		return result;
	}

	/* Java Solution 3- Dynamic Programming The key is to find the relation dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]).
	 */
	public int houseRob13(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
		}
		return dp[nums.length - 1];
	}

	// Approach4:Bottom Up - 2 variable approach
	public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		int prev1 = 0;
		int prev2 = 0;
		for (int num : nums) {
			int tmp = prev1;
			prev1 = Math.max(prev2 + num, prev1);
			prev2 = tmp;
		}
		return prev1;
	}

	/* Java Solution 5: We can use two variables, even and odd, to track the maximum value so far as iterating the
	 * array.You can use the following example to walk through the code.
	 */
	public int houseRob15(int[] num) {
		if (num == null || num.length == 0) return 0;
		int even = 0;
		int odd = 0;
		for (int i = 0; i < num.length; i++) {
			if (i % 2 == 0) {
				even += num[i];
				even = even > odd ? even : odd;
			} else {
				odd += num[i];
				odd = even > odd ? even : odd;
			}
		}
		return even > odd ? even : odd;
	}

	/* House Robber II: 
	 * After robbing those houses on that street, the thief has found himself a new place for his
	 * thievery so that he will not get too much attention. This time, all houses at this place are arranged in a
	 * circle. That means the first house is the neighbor of the last one.Meanwhile, the security system for these
	 * houses remain the same as for those in the previous street. Given a list of non- negative integers representing
	 * the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the
	 * police.
	 */

	/* Analysis:
	 * This is an extension of House Robber.There are two cases here 1)1 st element is included and last is not included
	 * 2)1 st is not included and last is included.Therefore, we can use the similar dynamic programming approach to
	 * scan the array twice and get the larger value.
	 */

	public int houseRob2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		int max1 = robHelper(nums, 0, nums.length - 2);
		int max2 = robHelper(nums, 1, nums.length - 1);
		return Math.max(max1, max2);
	}

	public int robHelper(int[] nums, int i, int j) {
		if (i == j) return nums[i];
		int[] dp = new int[nums.length];
		dp[i] = nums[i];
		dp[i + 1] = Math.max(nums[i + 1], dp[i]);
		for (int k = i + 2; k <= j; k++) {
			dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k]);
		}
		return dp[j];
	}

	/* House Robber III:
	 * The houses form a binary tree. If the root is robbed, its left and right can not be robbed. 
	 * 
	 * Analysis: Traverse down the tree recursively. We can use an array to keep 2 values: the maximum money when a root is selected and
	 * the maximum value when a root if NOT selected.
	 */
	public int houseRob3(TreeNode root) {
		if (root == null) return 0;
		int[] result = helper(root);
		return Math.max(result[0], result[1]);
	}

	public int[] helper(TreeNode root) {
		if (root == null) {
			int[] result = { 0, 0 };
			return result;
		}
		int[] result = new int[2];
		int[] left = helper(root.left);
		int[] right = helper(root.right);
		result[0] = root.data + left[1] + right[1];
		result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		return result;
	}
	/*Pain Fence: 
	 * 	There is a fence with n posts, each post can be painted with one of the k colors. You have to paint all the posts such that 
	 * no more than two adjacent fence posts have the same color. Return the total number of ways you can paint the fence.
	 */

	/* Solution1:
	 * The key to solve this problem is finding this relation.f(n)=(k-1)(f(n-1)+f(n-2)) Assuming there are 3 posts, if
	 * the first one and the second one has the same color, then the third one has k-1 options. The first and second
	 * together has k options. If the first and the second do not have same color, the total is k * (k-1), then the
	 * third one has k options. Therefore, f(3) = (k-1)*k + k*(k-1)*k = (k-1)(k+k*k)
	 */

	public int paintFence1(int n, int k) {
		int dp[] = { 0, k, k * k, 0 };
		if (n <= 2) return dp[n];
		for (int i = 2; i < n; i++) {
			dp[3] = (k - 1) * (dp[1] + dp[2]);
			dp[1] = dp[2];
			dp[2] = dp[3];
		}
		return dp[3];
	}

	public int paintFence2(int n, int k) {
		if (n <= 0 || k <= 0) return 0;
		if (n == 1) return k;
		int preSame = 0, preDiff = k;
		for (int i = 1; i < n; i++) {
			int same = preDiff;
			int diff = (k - 1) * (preSame + preDiff);
			preSame = same;
			preDiff = diff;
		}
		return preSame + preDiff;
	}

	/***************************** Pattern 4: Palindromic Subsequence *************************/
	/*Longest Palindromic Subsequence
	Longest Palindromic Substring
	Count of Palindromic Substrings
	Minimum Deletions in a String to make it a Palindrome
	Palindromic Partitioning*/

	// Longest Palindromic Subsequence:
	// 1.Recursion Approach
	public int longestPalindromeSubSequence1(String str) {
		return longestPalindromeSubSequence1(str, 0, str.length() - 1);
	}

	public int longestPalindromeSubSequence1(String str, int i, int j) {
		if (i == j) return 1;
		if (str.charAt(i) == str.charAt(j) && i + 1 == j) return 2;
		if (str.charAt(i) == str.charAt(j)) return longestPalindromeSubSequence1(str, i + 1, j - 1) + 2;
		return Math.max(longestPalindromeSubSequence1(str, i, j - 1), longestPalindromeSubSequence1(str, i + 1, j));
	}

	// 3.DP-Bottom Up Approach
	public int longestPalindromeSubSequence3(String str) {
		int n = str.length();
		int[][] result = new int[n][n];
		for (int i = 1; i < n; i++)
			result[i][i] = 1;
		int j;
		for (int len = 2; len <= n; len++) {
			for (int i = 0; i < (n - len + 1); i++) {
				j = i + len - 1;
				if (str.charAt(i) == str.charAt(j) && len == 2) {
					result[i][j] = 2;
				} else if (str.charAt(i) == str.charAt(j)) {
					result[i][j] = result[i + 1][j - 1] + 2;
				} else {
					result[i][j] = Math.max(result[i][j - 1], result[i + 1][j]);
				}
			}
		}
		return result[0][n - 1];
	}

	private String printPalindromicSeq(int[][] result, String str) {
		int n = str.length();
		int row = 0, col = n - 1, start = 0, end = result[0][n - 1] - 1;
		char[] seq = new char[result[0][n - 1]];
		while (row <= col) {
			if (result[row][col] > result[row][col - 1] && result[row][col] > result[row + 1][col]) {
				seq[start++] = str.charAt(col);
				seq[end--] = str.charAt(col);
				row++;
				col--;
			} else if (result[row][col] == result[row][col - 1]) {
				col--;
			} else if (result[row][col] == result[row + 1][col]) {
				row++;
			} else {
				row++;
				col--;
			}
		}
		return String.valueOf(seq);
	}

	// Longest Palindromic Substring:

	/* Method 1(Brute	Force):
	 * The simple approach is to check each substring whether the substring is a palindrome or not. We can run three
	 * loops, the outer two loops pick all substrings one by one by fixing the corner characters, the inner loop checks
	 * whether the picked substring is palindrome or not.
	 * Time complexity: O ( n^3 )
	 */
	public String longestPalindromicSubString1(String str) {
		int max = -1, n = str.length();
		String maxString = null, subString;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				subString = str.substring(i, j + 1);
				if (isPalindrome(subString) && max < (j - i + 1)) {
					max = j - i + 1;
					maxString = subString;
				}
			}
		}
		return maxString;
	}

	// 2. Using DP-Bottom Up Approach:
	public String longestPalindromicSubString3(String str) {
		int n = str.length(), max = 1, start = 0;
		boolean[][] table = new boolean[n][n];
		for (int i = 0; i < n; i++)
			table[i][i] = true;
		for (int len = 2; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1;
				if (str.charAt(i) == str.charAt(j) && (len == 2 || table[i + 1][j - 1])) {
					table[i][j] = true;
					if (len > max) {
						start = i;
						max = len;
					}
				}
			}
		}
		int maxLen = max + start;
		return str.substring(start, maxLen);
	}

	public boolean isPalindrome(String str) {
		int l = 0, h = str.length() - 1;
		while (l < h) {
			if (str.charAt(l++) != str.charAt(h--)) return false;
		}
		return true;
	}

	/* Minimum number of deletions to make a string palindrome:
	 * Given a string of size ‘n’. The task is to remove or delete minimum number of characters from the string so that
	 * the resultant string is palindrome.
	 *  Solution: Use Longest Palindromic Subsequence
	 */
	public int minimumNumberOfDeletions(String str) {
		int n = str.length();
		int len = longestPalindromeSubSequence3(str);
		return (n - len);
	}

	// Count All Palindromic Subsequence in a given String
	// 2.DP-Top down Approach
	public int countPalindromicSubseq2(String str, int i, int j) {
		int[][] dp = new int[str.length()][str.length()];
		return countPalindromicSubseq2(str, i, j, dp);
	}

	public int countPalindromicSubseq2(String str, int i, int j, int[][] dp) {
		if (i >= str.length() || j < 0) return 0;
		if (dp[i][j] != -1) return dp[i][j];
		if ((i - j == 1) || (i - j == -1)) {
			if (str.charAt(i) == str.charAt(j)) return dp[i][j] = 3;
			else return dp[i][j] = 2;
		}
		if (i == j) return dp[1][j] = 1;
		else if (str.charAt(i) == str.charAt(j)) return dp[i][j] = countPalindromicSubseq2(str, i + 1, j, dp)
				+ countPalindromicSubseq2(str, i, j - 1, dp) + 1;
		else return dp[i][j] = countPalindromicSubseq2(str, i + 1, j, dp) + countPalindromicSubseq2(str, i, j - 1, dp)
				- countPalindromicSubseq2(str, i + 1, j - 1, dp);

	}

	// 3.DP-Bottom Up Approach
	public int countPalindromicSubseq3(String str) {
		int N = str.length();
		int[][] cps = new int[N + 1][N + 1];
		for (int i = 0; i < N; i++)
			cps[i][i] = 1;
		for (int L = 2; L <= N; L++) {
			for (int i = 0; i < N; i++) {
				int k = L + i - 1;
				if (k < N) {
					if (str.charAt(i) == str.charAt(k)) {
						cps[i][k] = cps[i][k - 1] + cps[i + 1][k] + 1;
					} else {
						cps[i][k] = cps[i][k - 1] + cps[i + 1][k] - cps[i + 1][k - 1];
					}
				}
			}
		}
		return cps[0][N - 1];
	}

	// Count of Palindromic Substrings
	public int CountPalindromicSubstrings(String str) {
		int n = str.length();
		int dp[][] = new int[n][n];
		boolean P[][] = new boolean[n][n];
		for (int i = 0; i < n; i++)
			P[i][i] = true;
		for (int gap = 1; gap < n; gap++) {
			for (int i = 0; i < n - gap; i++) {
				int j = gap + i;
				if (str.charAt(i) == str.charAt(j) && gap == 1) {
					P[i][i + 1] = true;
					dp[i][j] = 1;
				} else {
					if (str.charAt(i) == str.charAt(j) && P[i + 1][j - 1]) P[i][j] = true;
					dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
					if (P[i][j] == true) dp[i][j] += 1;
				}
			}
		}
		return dp[0][n - 1];
	}

	/*
	 * Palindrome PartitioningII:
	 *   Given a string s, partition s such that every substring of the partition is a palindrome. Return the minimum cuts needed for 
	 *   a palindrome partitioning of s.*/
	public int palindromicPartioningII(String s) {
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

	/*
	 * Palindrome PartitioningI:
	 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome
	 *  partitioning of s.
	 *  [["aa","b"], ["a","a","b"]] 
	 *  
	 *  Note: This solution doesnt give expected result. Below method gives ["aa", "a", "a", "b"]
	 */
	public List<List<String>> palindromicPartioningI(String str) {
		int n = str.length();
		List<List<String>> result = new ArrayList<>();
		List<String> eachList = new ArrayList<>();
		if (n == 1) {
			eachList.add(str);
			result.add(eachList);
			return result;
		}
		int j;
		int[][] a = new int[n][n];
		for (int range = 1; range <= n; range++) {
			eachList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				j = i + range - 1;
				if (str.charAt(i) == str.charAt(j)) {
					if (range == 1 || range == 2) a[i][j] = 1;
					else a[i][j] = a[i + 1][j - 1];

					if (a[i][j] == 1) eachList.add(str.substring(i, j + 1));
				}
			}
			result.add(eachList);
		}
		return result;
	}

	/* Sherlock and Anagrams:
	 * Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string. 
	 * Given a string, find the number of pairs of substrings of the string that are anagrams of each other.
	 * Eg: abba => The list of all anagrammatic pairs is {a,a}, {ab, ba}, {b,b}, {abb, bba}; Count: 4
	 */
	// Approach1: Brute Force Approach:
	static int sherlockAndAnagrams(String s) {
		int n = s.length(), count = 0;
		for (int len = 1; len < n; len++) {
			for (int i = 0; i < n - len + 1; i++) {
				String s1 = s.substring(i, i + len);
				for (int j = i + 1; j < n - len + 1; j++) {
					String s2 = s.substring(j, j + len);
					if (isAnagram(s1, s2)) count++;
				}
			}
		}
		return count;
	}

	private static boolean isAnagram(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		if (m != n) return false;
		if (m == 1) return s1.equals(s2) ? true : false;
		int[] hash = new int[26];
		for (int i = 0; i < m; i++) {
			hash[s1.charAt(i) - 'a']++;
			hash[s2.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++)
			if (Math.abs(hash[i]) != 0) return false;
		return true;
	}

	/***************************** Pattern 5: Longest Common Substring *************************/
	/*Longest Common Substring
	Longest Common Subsequence
	Minimum Deletions and Insertions to Transform a String into another
	Longest Increasing Subsequence
	Maximum Sum Increasing Subsequence
	Shortest Common Super-sequence
	Minimum Deletions to Make a Sequence Sorted
	Longest Repeating Subsequence
	Subsequence Pattern Matching
	Longest Bitonic Subsequence
	Longest Alternating Subsequence
	Edit Distance
	Strings Interleaving*/

	// Longest Common Substring:
	// 1.Recursion Approach:
	public int longestCommonSubstring1(String s1, String s2) {
		return longestCommonSubstring1(s1, s2, s1.length() - 1, s2.length() - 1, 0);
	}

	public int longestCommonSubstring1(String s1, String s2, int i, int j, int count) {
		if (i < 0 || j < 0) return count;
		if (s1.charAt(i) == s2.charAt(j)) return longestCommonSubstring1(s1, s2, i - 1, j - 1, count + 1);
		return Utils.max(count, longestCommonSubstring1(s1, s2, i - 1, j, 0),
				longestCommonSubstring1(s1, s2, i, j - 1, 0));
	}

	// 2.DP:Bottom Up Approach:Time Complexity-O(m.n)
	public int longestCommonSubstring3(String str1, String str2) {
		int m = str1.length(), n = str2.length();
		int[][] dp = new int[m][n];
		int max = 0, row = 0, col = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					if (i == 0 || j == 0) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}
					if (max < dp[i][j]) {
						max = dp[i][j];
						row = i;
						col = j;
					}
				}
			}
		}
		return max;
	}

	public String printLongestCommonSubstring(int[][] dp, int row, int col, String str) {
		String substring = "";
		while (row >= 0 && col >= 0 && dp[row][col] != 0) {
			substring = str.charAt(row) + substring;
			row--;
			col--;
		}
		return substring;
	}

	// Longest Common subsequence:
	// 1.Recursive approach
	public int longestCommonSubSequence1(String str1, String str2) {
		return longestCommonSubSequence1(str1, str2, str1.length() - 1, str2.length() - 1);
	}

	private int longestCommonSubSequence1(String str1, String str2, int i, int j) {
		if (i < 0 || j < 0) return 0;
		if (str1.charAt(i) == str2.charAt(j)) return 1 + longestCommonSubSequence1(str1, str2, i - 1, j - 1);
		return Math.max(longestCommonSubSequence1(str1, str2, i - 1, j),
				longestCommonSubSequence1(str1, str2, i, j - 1));
	}

	// 2.DP:Top Down approach
	public int longestCommonSubSequence2(String str1, String str2) {
		int[][] dp = new int[str1.length()][str2.length()];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		return longestCommonSubSequence2(str1, str2, str1.length() - 1, str2.length() - 1, dp);
	}

	private int longestCommonSubSequence2(String str1, String str2, int i, int j, int[][] dp) {
		if (i < 0 || j < 0) return 0;
		if (dp[i][j] != -1) return dp[i][j];
		if (str1.charAt(i) == str2.charAt(j))
			return dp[i][j] = longestCommonSubSequence2(str1, str2, i - 1, j - 1, dp) + 1;
		return dp[i][j] = Math.max(longestCommonSubSequence2(str1, str2, i - 1, j, dp),
				longestCommonSubSequence2(str1, str2, i, j - 1, dp));
	}

	// 3.DP Bottom Up Approach
	public int longestCommonSubSequence3(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		printLongestSubSequence(dp, s1, s2);
		return dp[m][n];
	}

	// Print the longest common sub sequence
	private void printLongestSubSequence(int[][] dp, String s1, String s2) {
		int i = s1.length(), j = s2.length();
		int longSeqCount = dp[i][j];
		char[] result = new char[longSeqCount];
		int index = longSeqCount;
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				result[--index] = s1.charAt(i - 1);
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		System.out.print("SubSequence:");
		for (int k = 0; k < longSeqCount; k++) {
			System.out.print(result[k] + "-");
		}
	}

	// Shortest Common Supersequence:
	// Solution Modification LCS;
	// 1.Recursion
	public int shortestCommonSuperSeq1(String str1, String str2) {
		return shortestCommonSuperSeq1(str1, str2, str1.length(), str2.length());
	}

	private int shortestCommonSuperSeq1(String str1, String str2, int m, int n) {
		if (m == 0) return n;
		if (n == 0) return m;
		if (str1.charAt(m - 1) == str2.charAt(n - 1)) return 1 + shortestCommonSuperSeq1(str1, str2, m - 1, n - 1);
		return 1 + Math.min(shortestCommonSuperSeq1(str1, str2, m - 1, n),
				shortestCommonSuperSeq1(str1, str2, m, n - 1));
	}

	// 3.DP Bottom Up Approach:
	public int shortestCommonSuperSeq31(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m][n];
	}

	// 3.DP Bottom Up Approach:
	/* Directly using LCS Solution:
	 * Length of the shortest supersequence  = (Sum of lengths of given two strings) - (Length of LCS of two given strings) 
	 */

	// Directly using LCS Solution:
	int shortestSuperSequence(String X, String Y) {
		int m = X.length();
		int n = Y.length();
		int l = longestCommonSubSequence3(X, Y);
		return (m + n - l);
	}

	// Longest Repeating Subsequence:
	/* Solution: Modification of Longest Common Subsequence problem. The idea is to find the LCS(str,
	 * str)where str is the input string with the restriction that when both the characters are same, they shouldn’t be
	 * on the same index in the two strings.
	 */
	// 1.Recursive approach
	public int longestRepeatingSubSeq1(String str1, String str2) {
		return longestRepeatingSubSeq1(str1, str2, str1.length() - 1, str2.length() - 1);
	}

	private int longestRepeatingSubSeq1(String str1, String str2, int i, int j) {
		if (i < 0 || j < 0) return 0;
		if (str1.charAt(i) == str2.charAt(j) && i != j) return 1 + longestRepeatingSubSeq1(str1, str2, i - 1, j - 1);
		return Math.max(longestRepeatingSubSeq1(str1, str2, i - 1, j), longestRepeatingSubSeq1(str1, str2, 1, j - 1));
	}

	// 3.DP Bottom Up Approach
	public int longestRepeatingSubSeq3(String str) {
		int n = str.length();
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return dp[n][n];
	}

	/*
	 * Minimum Deletions and Insertions to Transform a String into another:
	 * Given two strings ‘str1’ and ‘str2’ of size m and n respectively. The task is to remove/delete and insert
	 * minimum number of characters from/in str1 so as to transform it into str2. Convert Str1 to Str2;
	 * Input : str1 = "heap", str2 = "pea" 
	 * Output : Minimum Deletion = 2 and Minimum Insertion = 1
	 * Explanation: p and h deleted from heap. Then, p is inserted at the beginning.
	 * Solution: Use LCS solution
	 */
	public void printMinDelAndInsert3(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		int lcsLen = longestCommonSubSequence3(str1, str2);
		System.out.println("Minimum number of " + "deletions = ");
		System.out.println(len1 - lcsLen);
		System.out.println("Minimum number of " + "insertions = ");
		System.out.println(len2 - lcsLen);
	}

	/* Edit Distance: Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
	 * This problem is extended version of previous one. Here we have one more operation - replace
	 */
	// Recursion Approach
	public int minDistance1(String word1, String word2) {
		return minDistance(word1, word2, word1.length() - 1, word2.length() - 1);
	}

	public int minDistance(String s1, String s2, int i, int j) {
		if (i < 0) return j + 1;
		if (j < 0) return i + 1;
		if (s1.charAt(i) == s2.charAt(j)) return minDistance(s1, s2, i - 1, j - 1);
		return 1 + Utils.min(minDistance(s1, s2, i, j - 1), minDistance(s1, s2, i - 1, j),
				minDistance(s1, s2, i - 1, j - 1));
	}

	// DP-Bottom up Approach
	public int minDistance(String word1, String word2) {
		int m = word1.length(), n = word2.length();
		if (m == 0 && n == 0) return 0;
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0) dp[i][j] = j;
				else if (j == 0) dp[i][j] = i;
				else if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] = 1 + Utils.min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[m][n];
	}

	/*
	 * Interleaving String:
	 *  Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
	 *  Example: 
	 *  	Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" Output: true
	 *  	Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc" Output: false
	 */
	// Recursive Approach
	public boolean isInterleave1(String s1, String s2, String s3) {
		if ((s1.length() + s2.length()) != s3.length()) return false;
		return isInterleave(s1, s2, s3, 0, 0, 0);
	}

	public boolean isInterleave(String s1, String s2, String s3, int i1, int i2, int i3) {
		if (i1 == s1.length() && i2 == s2.length() && i3 == s3.length()) return true;
		if (i3 == s3.length()) return false;
		return (i1 < s1.length() && s1.charAt(i1) == s3.charAt(i3) && isInterleave(s1, s2, s3, i1 + 1, i2, i3 + 1))
				|| (i2 < s2.length() && s2.charAt(i2) == s3.charAt(i3) && isInterleave(s1, s2, s3, i1, i2 + 1, i3 + 1));
	}

	// DP-Bottom Up Approach
	public boolean isInterleave3(String s1, String s2, String s3) {
		if ((s1.length() + s2.length()) != s3.length()) return false;
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = true;
				} else if (i == 0) {
					dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
				} else if (j == 0) {
					dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
				} else {
					dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
							|| (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	/*
	 * Wild card Matching:
	 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
	'?' Matches any single character.
	'*' Matches any sequence of characters (including the empty sequence).
	 */
	// Approach1: Recursion
	// Approach2: Using DP-Top Down Approach
	// Approach3: Using DP-Bottom Up Approach- Time: O(mn), Space: O(mn)
	public boolean wildCardMatch3(String s, String p) {
		int m = s.length(), n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for (int j = 2; j <= n; j++)
			if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				} else {
					dp[i][j] = false;
				}

			}
		}
		return dp[m][n];
	}

	// Approach4: Linear Time Solution
	public boolean wildCardMatch4(String s, String p) {
		int i = 0, j = 0, star = -1, mark = -1;
		while (i < s.length()) {
			if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				i++;
				j++;
			} else if (j < p.length() && p.charAt(j) == '*') {
				star = j;
				mark = i;
				j++;
			} else if (star != -1) {
				j = star + 1;
				mark++;
				i = mark;
			} else {
				return false;
			}
		}
		while (j < p.length() && p.charAt(j) == '*')
			j++;
		return j == p.length();
	}

	/*
	 * Regular Expression Matching:
	 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
	 *  '.' Matches any single character.
	 *  '*' Matches zero or more of the preceding element.
	 */

	// Approach1: Recursion
	// Approach2: Using DP-Top Down Approach
	// Approach3: Using DP-Bottom Up Approach- Time: O(mn), Space: O(mn) - Similar to WildCard Matching Prob
	public boolean regExMatch3(String s, String p) {
		int m = s.length(), n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for (int j = 2; j <= n; j++)
			if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 2];
					if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
						dp[i][j] = dp[i][j] || dp[i - 1][j];
				} else {
					dp[i][j] = false;
				}
			}
		}
		return dp[m][n];
	}

	/***************************** Pattern 6 - Longest Increasing Subsequence *******************************/
	// Longest Increasing Sequence:
	// Approach1: Recursive APproach
	private int max_ref; // stores the LIS

	/* To make use of recursive calls, this function must return two things: 
	1) Length of LIS ending with element arr[n-1]. We use max_ending_here for this purpose 
	2) Overall maximum as the LIS may end with an element before arr[n-1] max_ref is used this purpose. 
	The value of LIS of full array of size n is stored in max_ref which is our final result 
	*/
	public int longestIncreasingSubSequence1(int arr[], int n) {
		max_ref = 1;
		lis(arr, n);
		return max_ref;
	}

	private int lis(int arr[], int n) {
		if (n == 1) return 1;
		int res, max_ending_here = 1; // 'max_ending_here' is length of LIS ending with arr[n-1]

		/* Recursively get all LIS ending with arr[0], arr[1] ... arr[n-2]. If   arr[i-1] is smaller than arr[n-1], and 
		   max ending with arr[n-1] needs to be updated, then update it */
		for (int i = 1; i < n; i++) {
			res = lis(arr, i);
			if (arr[i - 1] < arr[n - 1] && res + 1 > max_ending_here) {
				max_ending_here = res + 1;
			}
		}

		max_ref = Math.max(max_ref, max_ending_here);

		return max_ending_here;
	}

	// Approach2: DP Approach : O(n^2)
	public int longestIncreasingSubSequence3(int[] arr) {
		int n = arr.length;
		if (n <= 1) return n;

		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		int max = dp[0];
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
			}
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	// Approach4: Binary Search Approach : O(nlogn)
	public int longestIncreasingSubSequence4(int[] nums) {
		int[] dp = new int[nums.length];
		int size = 0;
		for (int x : nums) {
			int i = 0, j = size;
			// Binary Search
			while (i != j) {
				int m = (i + j) / 2;
				if (dp[m] < x) i = m + 1;
				else j = m;
			}
			dp[i] = x;
			if (i == size) ++size;
		}
		return size;
	}

	// Maximum Sum Increasing Subsequence:

	// Approach3: DP-Bottom Up Approach
	public int maxSumIncreasingSubSequence3(int[] a) {
		int n = a.length;
		if (n <= 1) return n;

		int[] dp = new int[n];
		int[] indexSeq = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = a[i];
			indexSeq[i] = i;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i] && dp[i] < dp[j] + a[i]) {
					dp[i] = dp[j] + a[i];
					indexSeq[i] = j;
				}
			}
		}

		System.out.println("Print the max increasing sum sequence: ");
		printMSIS(a, dp, indexSeq);

		// Return the max value
		return Utils.max(dp);
	}

	private void printMSIS(int[] a, int[] msis, int[] indexSeq) {
		// Find the max value and corresponding index
		int max = Integer.MIN_VALUE, index = 0;
		for (int i = 0; i < msis.length; i++) {
			if (msis[i] > max) {
				max = msis[i];
				index = i;
			}
		}

		// Print the max increasing sum sequence
		int temp = max;
		while (temp > 0) {
			System.out.print(a[index] + " ");
			temp = temp - a[index];
			index = indexSeq[index];
		}

	}

	// Minimum number of deletions to make a sorted sequence:
	/* Approach1: 
	 * A simple solution is to remove all subsequences one by one and check if remaining set of elements are in sorted
	 * order or not. Time complexity of this solution is exponential.
	 */

	// Approach3:
	/* An efficient approach uses the concept of finding the length of the longest increasing subsequence of a given
	 * sequence.
	 */

	public int minimumNumberOfDeletions(int arr[], int n) {
		// Find longest increasing subsequence
		int len = longestIncreasingSubSequence3(arr);

		// After removing elements other than the lis, we get sorted sequence.
		return (n - len);
	}

	// Longest Bitonic Subsequence
	// Approach3: DP-Bottom Up Approach
	public int longestBitonicSequence3(int[] arr) {
		int n = arr.length;
		int[] lis = new int[n]; // Largest Increasing Sequence array
		int[] lds = new int[n]; // Largest Decreasing Sequence array
		for (int i = 0; i < n; i++) {
			lis[i] = 1;
			lds[i] = 1;
		}

		// Largest Increasing Sequence logic
		for (int i = 1; i < n; i++)
			for (int j = 0; j < i; j++)
				if (arr[j] < arr[i] && lis[i] < lis[j] + 1) lis[i] = lis[j] + 1;

		// Largest Decreasing Sequence logic
		for (int i = n - 2; i >= 0; i--)
			for (int j = n - 1; j > i; j--)
				if (arr[j] < arr[i] && lds[i] < lds[j] + 1) lds[i] = lds[j] + 1;

		int max = Integer.MIN_VALUE, temp;
		// Find the Bitonic value from LIS & LDS ( LIS+LDS-1)
		for (int i = 0; i < n; i++) {
			temp = lds[i] + lis[i] - 1;
			if (temp > max) max = temp;
		}

		return max;
	}

	// Longest Zig-Zag Subsequence or Longest Alternating subsequence

	// Approach3: DP-Bottom Up Approach
	public int longestZigZagSubsequence(int arr[], int n) {
		int dp[][] = new int[n][2];

		for (int i = 0; i < n; i++)
			dp[i][0] = dp[i][1] = 1;

		int res = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && dp[i][0] < dp[j][1] + 1) {
					dp[i][0] = dp[j][1] + 1;
				}

				if (arr[j] > arr[i] && dp[i][1] < dp[j][0] + 1) {
					dp[i][1] = dp[j][0] + 1;
				}
			}

			if (res < Math.max(dp[i][0], dp[i][1])) {
				res = Math.max(dp[i][0], dp[i][1]);
			}
		}

		return res;
	}

	/* Box Stacking:
	 * You are given a set of n types of rectangular 3-D boxes, where the i^th box has height h(i), width w(i) and depth
	 * d(i) (all real numbers).
	 * You want to create a stack of boxes which is as tall as possible, but you can only stack a box on top of another box 
	 * if the dimensions of the 2-D base of the lower box are each strictly larger than those of the 2-D base of the higher
	 * box. Of course, you can rotate a box so that any side functions as its base. It is also allowable to use multiple 
	 * instances of the same type of box.
	 */
	public int boxStacking(int[][] dimensions) {
		// 1.Create all rotations of boxes, always keep length greater or equal to width
		Box[] boxes = new Box[3 * dimensions.length];
		for (int i = 0; i < dimensions.length; i++) {
			boxes[i * 3] = createBox(dimensions[i][0], dimensions[i][1], dimensions[i][2]);
			boxes[i * 3 + 1] = createBox(dimensions[i][1], dimensions[i][0], dimensions[i][2]);
			boxes[i * 3 + 2] = createBox(dimensions[i][2], dimensions[i][1], dimensions[i][0]);
		}

		// 2.Sort: Decreasing Order based on (length * width)
		Arrays.sort(boxes, (a, b) -> ((b.length * b.width)) - (a.length * a.width));

		// 3.Find the max height using Longest Increasing Sequence algorithm
		int dp[] = new int[boxes.length];
		int result[] = new int[boxes.length];

		for (int i = 0; i < dp.length; i++) {
			dp[i] = boxes[i].height;
			result[i] = i;
		}

		int max = dp[0];
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < i; j++) {
				if (boxes[i].length < boxes[j].length && boxes[i].width < boxes[j].width) {
					if (dp[i] < dp[j] + boxes[i].height) {
						// dp[] array used to find max height.
						dp[i] = dp[j] + boxes[i].height;
						// result[] array used to find the dimensions
						result[i] = j;
					}
				}
			}
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	private Box createBox(int height, int side1, int side2) {
		Box box = new Box();
		box.height = height;
		box.length = Math.max(side1, side2);
		box.width = Math.min(side1, side2);
		return box;
	}

}