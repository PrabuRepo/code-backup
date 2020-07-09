package com.geeks.problem.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.common.utilities.Utils;

/*
 * DP Quotes: "Those who cannot remember the past are condemned to repeat it" 
 * 
 * Dynamic Programming is an algorithmic paradigm that solves a given complex problem by breaking it into subproblems 
  and stores the results of subproblems to avoid computing the same results again.
 Two main properties: 
  1. Overlapping Property - DP is mainly used when solutions of same subproblems are needed again and again. In DP, computed solutions 
  							to subproblems are stored in a table so that these don’t have to recomputed.
  	 Two types of Overlapping Property: i. Memoization(Top Down), ii. Tabulation(Bottom up)
  	 
  2. Optimal Substructure -  A given problems has Optimal Substructure Property if optimal solution of the given problem can be obtained
   							 by using optimal solutions of its subproblems. Eg: Shortest Path problem has following optimal substructure property
*/
/*
How to solve a Dynamic Programming Problem ?
 Step 1 : How to classify a problem as a Dynamic Programming Problem?
	   - Typically, all the problems that require to maximize or minimize certain quantity or counting problems that say 
	      to count the arrangements under certain condition or certain probability problems can be solved by using Dynamic Programming.
	   - All dynamic programming problems satisfy the overlapping subproblems property and most of the classic dynamic problems
	     also satisfy the optimal substructure property. Once, we observe these properties in a given problem, 
	     be sure that it can be solved using DP.
 Step 2 : Deciding the state	
 Step 3 : Formulating a relation among the states
 Step 4 : Adding memoization or tabulation for the state
*/
public class DynamicProgramming {

	/************************** Understand the DP Problems **************************/
	// Fibonacci Number calculation:
	// 1.using recursive function
	public static long fibonacci1(int n) {
		if (n <= 1) return n;
		return fibonacci1(n - 1) + fibonacci1(n - 2);
	}

	static long[]	lookup	= new long[100];
	static int[]	lookup2	= new int[50];

	/* 2.Memoization (Top Down): The memoized program for a problem is similar to the recursive version with a small modification that 
	 *   it looks into a lookup table before computing solutions.
	 */
	public static long fibonacci2(int n) {
		if (lookup[n] == 0) {
			if (n <= 1) lookup[n] = n;
			else lookup[n] = fibonacci2(n - 1) + fibonacci2(n - 2);
		}
		return lookup[n];
	}

	/*3. Tabulation (Bottom Up): The tabulated program for a given problem builds a table in bottom up fashion and returns the
	 *   last entry from table.
	 */
	public static long fibonacci3(int n) {
		long[] fib = new long[n + 1];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i <= n; i++)
			fib[i] = fib[i - 1] + fib[i - 2];
		return fib[n];
	}

	/************************** Simple Problems ****************************/
	static int[] lookUp;

	/* Three Problems are same:  
	 * 		- Climbing Stairs
	 * 		- Triple Step
	 * 		- Count number of ways to cover a distance 
	 */
	/**
	 * Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a
	 * time. Implement a method to count how many possible ways the child can run up the stairs.
	 */
	// 1.Recursive Solution
	public int tripleSteps1(int n) {
		if (n < 0) return 0;
		else if (n == 0) return 1;

		return tripleSteps1(n - 1) + tripleSteps1(n - 2) + tripleSteps1(n - 3);
	}

	// 2. DP- Top down: Memoization
	public int tripleSteps2(int n) {
		if (n <= 0) return 0;

		lookUp = new int[n + 1];
		return tripleStepsUtil(n);
	}

	public int tripleStepsUtil(int n) {
		if (n < 0) return 0;
		else if (n == 0) return 1;

		if (lookUp[n] == 0) lookUp[n] = tripleStepsUtil(n - 1) + tripleStepsUtil(n - 2) + tripleStepsUtil(n - 3);

		return lookUp[n];
	}

	// 3. DP- Bottom up: Tabulation
	public int tripleSteps3(int n) {
		if (n <= 0) return 0;
		else if (n == 1) return 1;
		else if (n == 2) return 2;
		else if (n == 3) return 4;

		int[] lookup = new int[n + 1];
		lookup[1] = 1;
		lookup[2] = 2;
		lookup[3] = 4;
		for (int i = 4; i <= n; i++)
			lookup[i] = lookup[i - 1] + lookup[i - 2] + lookup[i - 3];

		return lookup[n];
	}

	// Modification of above problems: Minimum no of steps required to run up the stairs
	// This method is not working.
	public int minTripleSteps(int n) {
		if (n < 0) return Integer.MAX_VALUE;
		if (n == 0 || n == 1) return 1;
		return Utils.min(minTripleSteps(n - 1), minTripleSteps(n - 2), minTripleSteps(n - 3));
	}

	/* No of ways to form number: Given 3 numbers {1, 3, 5}, we need to tell the total number of ways we can form a number
	   'N'*/
	public int noOfWaysToFormN1(int n) {
		if (n < 0) return 0;
		if (n == 0) return 1;
		return noOfWaysToFormN1(n - 1) + noOfWaysToFormN1(n - 3) + noOfWaysToFormN1(n - 5);
	}

	// Using memoization
	public int noOfWaysToFormN2(int n) {
		if (n < 0) return 0;
		if (n == 0 || n == 1) return 1;
		if (lookup2[n] == 0) lookup2[n] = noOfWaysToFormN2(n - 1) + noOfWaysToFormN2(n - 3) + noOfWaysToFormN2(n - 5);
		return lookup2[n];
	}

	/* Recursive Digit Sum: 
	 *   Given an integer, we need to find the super digit of the integer.
	 *     If x has only 1 digit, then its super digit is x. 
	 *     Otherwise, the super digit of  is equal to the super digit of the sum of the digits of x.
	 *     Eg: 9875 -> 2
	 *  
	 * Ref: Check the proof in the below link: http://applet-magic.com/digitsummod9.htm
	 * */
	public int digitSum(String n, int k) {
		int digit = 0;
		int i = 0;
		// Eg: (88) -> (8%9)
		while (i < n.length()) {
			// x += (int) n.charAt(i++) - (int) ('0');
			digit += Character.getNumericValue(n.charAt(i++));
			digit = digit % 9;
		}
		digit = digit * k;

		return digit == 0 ? 9 : digit % 9;
	}

	// Example2: Towers of hanoi
	public void towersOfHanoi(int n, char source, char destination, char aux) {
		if (n == 0) return;
		/*Move first n-1 disks from source pole to auxiliary pole using destination as temporary pole*/
		towersOfHanoi(n - 1, source, aux, destination);

		System.out.printf("Move the disk %d from %c to %c\n", n, source, destination);

		/*Move the n-1 disks from auxiliary (now source) pole to destination pole using source pole as temporary (auxiliary) pole*/
		towersOfHanoi(n - 1, aux, destination, source);
		// Just for count no of moves; 2^n - 1
	}

	/************************** Type 1 Problems ****************************/
	// Coin Change: minimum no of coins
	public int minCoins(int coins[], int amount) {
		if (amount < 0) return -1;
		if (amount == 0) return 0;

		int min = Integer.MAX_VALUE;
		// Try every coin that has smaller value than amount
		for (int coin : coins) {
			int currMin = minCoins(coins, amount - coin);

			if (currMin >= 0 && currMin < min) min = currMin + 1;
		}

		return min == Integer.MAX_VALUE ? -1 : min;
	}

	// DP(Top down): Time Complexity: O(S*n)
	public int minCoins2(int[] coins, int amount) {
		return minCoins2(coins, amount, new int[amount]);
	}

	public int minCoins2(int coins[], int amount, int[] dp) {
		if (amount < 0) return -1;
		if (amount == 0) return 0;
		if (dp[amount - 1] != 0) return dp[amount - 1];

		int min = Integer.MAX_VALUE;
		// Try every coin that has smaller value than amount
		for (int coin : coins) {
			int currMin = minCoins2(coins, amount - coin, dp);

			if (currMin >= 0 && currMin < min) min = currMin + 1;
		}
		dp[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;

		return dp[amount - 1];
	}

	// DP(Bottom up): Time Complexity: O(S*n)
	public int minCoins3(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[max];
		Arrays.fill(dp, max);
		dp[0] = 0;

		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
			}
		}

		return dp[amount] > amount ? -1 : dp[amount];
	}

	// Minimum number of jumps to reach end -> Its similar to snake and ladder problems
	public int minJumps(int arr[]) {
		return minJumps(arr, 0, arr.length - 1);
	}

	// Returns minimum number of jumps to reach arr[h] from arr[l]
	public int minJumps(int arr[], int l, int h) {
		// Base case: when source and destination are same
		if (h == l) return 0;

		// When nothing is reachable from the given source
		if (arr[l] == 0) return Integer.MAX_VALUE;

		// Traverse through all the points reachable from arr[l]. Recursively get the minimum number of jumps needed to
		// reach
		// arr[h] from these reachable points.
		int minJumps = Integer.MAX_VALUE;
		for (int i = l + 1; i <= h && i <= l + arr[l]; i++) {
			int currJump = minJumps(arr, i, h);
			if (currJump != Integer.MAX_VALUE && currJump + 1 < minJumps) {
				minJumps = currJump + 1;
			}
		}

		// System.out.println("End: l=" + l + "; h=" + h + "; Min= " + min);
		return minJumps;
	}

	// Approach2: DP - Bottom up Approach; Time: O(n^2); Space: O(n)
	public int minJumps2(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];

		// initialize with infinity
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i < n - 1; i++)
			for (int j = 1; j <= nums[i] && i + j < n; j++)
				dp[i + j] = Math.min(dp[i + j], 1 + dp[i]);

		return dp[n - 1];
	}

	// Efficient Approach: Greedy Algorithm- Linear Approach
	public int minJumps3(int[] nums) {
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

	// Rod cutting
	public int cutRod(int price[], int n) {
		if (n <= 0) return 0;
		int max_val = Integer.MIN_VALUE;

		// Recursively cut the rod in different pieces and compare different configurations
		for (int i = 0; i < n; i++) {
			int prev = price[i] + cutRod(price, n - i - 1);
			// System.out.println("Value of price with " + n + " cuts: " + prev);
			max_val = Math.max(max_val, prev);
		}
		return max_val;
	}

	// How to print maximum number of A’s using given four keys. Print Max number of As using Ctrl-A, Ctrl-C, Crtl-V
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

	// Approach-2: Dynamic Programming approach
	/* The above function computes the same subproblems again and again. Re computations of same subproblems can be avoided
	 * by storing the solutions to subproblems and solving problems in bottom up manner.*/
	public int printMaxNoOfA2(int n) {
		if (n <= 6) return n;

		int[] result = new int[n + 1];
		// For the first 6 element, result = n
		for (int i = 1; i <= 6; i++)
			result[i] = i;

		// For remaining element - Max(2*f(n-3), 3*f(n-4),4*f(n-5)......(n-2)*f(1)
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

	// Minimum Edit Distance; Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
	public int minEditDistance(String str1, String str2) {
		return minEditDistance(str1, str2, str1.length(), str2.length());
	}

	public int minEditDistance(String str1, String str2, int m, int n) {
		if (m == 0) return n;
		if (n == 0) return m;

		if (str1.charAt(m - 1) == str2.charAt(n - 1)) return minEditDistance(str1, str2, m - 1, n - 1);

		/* 1.Insert; 2. Remove, 3.Replace */
		return 1 + Utils.min(minEditDistance(str1, str2, m, n - 1), minEditDistance(str1, str2, m - 1, n),
				minEditDistance(str1, str2, m - 1, n - 1));
	}

	// KnapSack Problem using Recursion
	public int knapSackProblem(int maxWeight, int[] weight, int[] value, int n) {
		if (n == 0 || maxWeight == 0) return 0;
		/*If weight of the nth item is more than Knapsack capacity maxWeight, then this item cannot be included in the optimal solution*/
		if (maxWeight < weight[n]) return knapSackProblem(maxWeight, weight, value, n - 1);

		/* Return the maximum of two cases:
		    1. the item is included in the optimal subset
		    2. not included in the optimal set */
		else return Math.max(value[n] + knapSackProblem(maxWeight - weight[n], weight, value, n - 1),
				knapSackProblem(maxWeight, weight, value, n - 1));
		/* int temp1 = value[n] + knapSackProblem(maxWeight - weight[n], weight, value, n - 1);
		 int temp2 = knapSackProblem(maxWeight, weight, value, n - 1);
		 System.out.println("Val1:" + temp1 + "; Val2:" + temp2);*/
	}

	// KnapSack Problem using Dynamic Programming
	public int knapSackProblem2(int maxWeight, int[] weight, int[] value) {
		int n = weight.length;
		int[][] result = new int[n + 1][maxWeight + 1];

		int i = 0, j = 0;
		for (i = 0; i <= n; i++) { // i - 0 - length of weight
			for (j = 0; j <= maxWeight; j++) {// j -0 to Maximum weight or capacity
				if (i == 0 || j == 0) {
					result[i][j] = 0;
				} else if (j >= weight[i - 1]) {
					result[i][j] = Math.max(value[i - 1] + result[i - 1][j - weight[i - 1]], result[i - 1][j]);
				} else {
					result[i][j] = result[i - 1][j];
				}
			}
		}

		// Print Values
		i = n;
		j = maxWeight;
		while (i > 0 && j > 0) {
			if (result[i][j] == result[i - 1][j]) {
				i--;
			} else {
				System.out.println("Weight:" + weight[i - 1] + "; Value:" + value[i - 1]);
				i--;
				j = j - weight[i];
			}
		}
		return result[n][maxWeight];
	}

	/************************** Type 2 Problems ****************************/
	/*Coin Change 2 /Coins - No of ways to get amount
	 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent), 
	 * write code to calculate the number of ways of representing n cents.
	 */
	public int coinChanges(int[] coins, int amount) {
		return coinChange1(coins, coins.length - 1, amount);
	}

	public int coinChange1(int coins[], int i, int cash) {
		// Base cases
		// 1.If cash is 0 then there is 1 solution (do not include any coin)
		if (cash == 0) return 1;
		// 2.If cash is less than 0 then no solution exists
		if (cash < 0) return 0;
		// 3.If there are no coins and cash is greater than 0, then no solution exist
		if (i <= 0 && cash >= 1) // (cash >=1) -> Its optional condition
			return 0;

		/*coinChange1 is sum of solutions 
		 * (i) including coins[i] and decrement no of coins 
		 * (ii) excluding coins[i] & keep same coin count */
		return coinChange1(coins, i - 1, cash) + coinChange1(coins, i, cash - coins[i]);
	}

	/*
	Path in Matrix :https://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
	Unique Paths I, II: Done  
	Robot in a Grid: Similar to Unique path problem
	Minimum Cost Path
	Word Break
	Word Break II*/

	public int uniquePaths(int m, int n) {
		if (m == 1 || n == 1) return 1;

		return uniquePaths(m - 1, n) + uniquePaths(n - 1, m);
		// Including diagonal
		// return uniquePaths(m-1,n)+uniquePaths(n-1,m)+uniquePaths(m-1. n-1);
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int r = obstacleGrid.length, c = obstacleGrid[0].length;
		if (r == 0 && c == 0) return 0;
		return uniquePathsWithObstacles(obstacleGrid, 0, 0);
	}

	public int uniquePathsWithObstacles(int[][] a, int i, int j) {
		int r = a.length, c = a[0].length;
		if (i == r - 1 && j == c - 1 && a[i][j] == 0) return 1;
		if (i < 0 || i >= r || j < 0 || j >= c) return 0;
		if (a[i][j] == 1) return 0;
		return uniquePathsWithObstacles(a, i + 1, j) + uniquePathsWithObstacles(a, i, j + 1);
	}

	/************************** Subsequence Problems **************************/
	/************************** 1. Array: Subsequence Problems **************************/
	static int max_ref;

	// Longest Increasing Subsequence
	// Recursive solution
	public int lengthOfLIS(int[] nums) {
		if (nums.length <= 1) return nums.length;

		return lengthOfLIS(nums, 0, Integer.MIN_VALUE);
	}

	public int lengthOfLIS(int[] nums, int i, int prevNum) {
		if (i >= nums.length) return 0;

		int taken = 0, notTaken = 0;
		if (prevNum < nums[i]) {
			taken = 1 + lengthOfLIS(nums, i + 1, nums[i]);
		}

		notTaken = lengthOfLIS(nums, i + 1, prevNum);

		return Math.max(taken, notTaken);
	}

	// Longest Increasing Sequence:
	// Approach2: DP Approach
	public int longestIncreasingSubSequence2(int[] arr) {
		int n = arr.length;
		if (n <= 1) return n;

		int[] lis = new int[n];
		Arrays.fill(lis, 1);

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && lis[i] < lis[j] + 1) lis[i] = lis[j] + 1;
			}
		}

		// Return the max value
		return Utils.max(lis);
	}

	public int lengthOfLIS3(int[] nums) {
		int[] tails = new int[nums.length];
		int size = 0;
		for (int x : nums) {
			int i = 0, j = size;
			// Binary Search
			while (i != j) {
				int m = (i + j) / 2;
				if (tails[m] < x) i = m + 1;
				else j = m;
			}
			tails[i] = x;
			if (i == size) ++size;
		}
		return size;
	}

	// Approach1: Using Binary Search
	public int longestIncreasingSubSequence3(int input[]) {
		int T[] = new int[input.length];
		int R[] = new int[input.length];
		for (int i = 0; i < R.length; i++) {
			R[i] = -1;
		}
		T[0] = 0;
		int len = 0;
		for (int i = 1; i < input.length; i++) {
			if (input[T[0]] > input[i]) { // if input[i] is less than 0th value of T then replace it there.
				T[0] = i;
			} else if (input[T[len]] < input[i]) { // if input[i] is greater than last value of T then append it in T
				len++;
				T[len] = i;
				R[T[len]] = T[len - 1];
			} else { // do a binary search to find ceiling of input[i] and put it there.
				int index = ceilIndex(input, T, len, input[i]);
				T[index] = i;
				R[T[index]] = T[index - 1];
			}
		}

		// this prints increasing subsequence in reverse order.
		System.out.print("Subsequence: ");
		int index = T[len];
		while (index != -1) {
			System.out.print(input[index] + " ");
			index = R[index];
		}

		System.out.println();
		return len + 1;
	}

	private static int ceilIndex(int input[], int T[], int end, int s) {
		int start = 0;
		int middle;
		int len = end;
		while (start <= end) {
			middle = (start + end) / 2;
			if (middle < len && input[T[middle]] < s && s <= input[T[middle + 1]]) {
				return middle + 1;
			} else if (input[T[middle]] < s) {
				start = middle + 1;
			} else {
				end = middle - 1;
			}
		}
		return -1;
	}

	// Length & Print the Maximum Sum Increasing Subsequence: Using DP approach
	public int maxSumIncreasingSubSequence(int[] a) {
		int n = a.length;
		if (n <= 1) return n;

		int[] msis = new int[n];
		int[] indexSeq = new int[n];
		for (int i = 0; i < n; i++) {
			msis[i] = a[i];
			indexSeq[i] = i;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i] && msis[i] < msis[j] + a[i]) {
					msis[i] = msis[j] + a[i];
					indexSeq[i] = j;
				}
			}
		}

		System.out.println("Print the max increasing sum sequence: ");
		printMSIS(a, msis, indexSeq);

		// Return the max value
		return Utils.max(msis);
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

	// Length & Print the Longest Bitonic Subsequence - using DP
	public int longestBitonicSequence(int[] arr) {
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

	// Length & Print the Maximum sum Bi-tonic Sub-sequence
	public int maxSumBitonicSubsequence(int[] arr) {
		int n = arr.length;
		if (n <= 1) return n;

		int[] msis = new int[n]; // msis - Maximum Sum Increasing Sequence
		int[] msds = new int[n]; // msds - Maximum Sum Decreasing Sequence

		for (int i = 0; i < n; i++) {
			msis[i] = arr[i];
			msds[i] = arr[i];
		}

		// Find the MSIS
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && msis[i] < msis[j] + arr[i]) msis[i] = msis[j] + arr[i];
			}
		}

		// Find the MSDS
		for (int i = n - 2; i >= 0; i--) {
			for (int j = n - 1; j > i; j--) {
				if (arr[j] < arr[i] && msds[i] < msds[j] + arr[i]) msds[i] = msds[j] + arr[i];
			}
		}

		// Find the max value : Max = MSIS+MSDS-arr[i]
		int max = Integer.MIN_VALUE, temp;
		for (int i = 0; i < n; i++) {
			temp = msis[i] + msds[i] - arr[i];
			if (max < temp) max = temp;
		}

		return max;
	}

	// Length & Print the Longest Increasing consecutive subsequence
	public int longestIncreasingConsecutiveSubsequence() {
		return 0;
	}

	// Length & Print the Longest Zig-Zag Subsequence or Longest Alternating subsequence
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

	// Length & Print the Maximum sum alternating subsequence
	public int maxSumAlternatingSubsequence() {
		return 0;
	}

	/************************** 2.String: Subsequence Problems **************************/
	// Length & Print the Longest Common SubSequence
	// Approach1: Using Recursion
	public int lengthOfLCS(String s1, String s2) {
		return lengthOfLCS(s1, s2, s1.length() - 1, s2.length() - 1);
	}

	private int lengthOfLCS(String s1, String s2, int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		} else if (s1.charAt(i) == s2.charAt(j)) {
			return 1 + lengthOfLCS(s1, s2, i - 1, j - 1);
		} else {
			return Math.max(lengthOfLCS(s1, s2, i, j - 1), lengthOfLCS(s1, s2, i - 1, j));
		}
	}

	// Length & Print the Longest Common SubSequence
	// Approach2: Using DP(Memoization)
	public int lengthOfLCS2(String s1, String s2) {
		int i = s1.length(), j = s2.length();
		return lengthOfLCS2(s1, s2, i - 1, j - 1, new Integer[i][j]);
	}

	private int lengthOfLCS2(String s1, String s2, int i, int j, Integer[][] dp) {
		if (i < 0 || j < 0) return 0;

		if (dp[i][j] != null) {
			return dp[i][j];
		} else if (s1.charAt(i) == s2.charAt(j)) {
			dp[i][j] = 1 + lengthOfLCS2(s1, s2, i - 1, j - 1, dp);
		} else {
			dp[i][j] = Math.max(lengthOfLCS2(s1, s2, i, j - 1, dp), lengthOfLCS2(s1, s2, i - 1, j, dp));
		}
		return dp[i][j];
	}

	// Approach3: Using DP(Tabulation)
	public int lengthOfLCS3(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		int[][] lcs = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					lcs[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}
		printLongestSubSequence(lcs, s1, s2);
		return lcs[m][n];
	}

	private void printLongestSubSequence(int[][] lcsArr, String s1, String s2) {
		int i = s1.length(), j = s2.length();
		int longSeqCount = lcsArr[i][j];
		char[] result = new char[longSeqCount];
		int index = longSeqCount;
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				result[--index] = s1.charAt(i - 1);
				i--;
				j--;
			} else if (lcsArr[i - 1][j] > lcsArr[i][j - 1]) {
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

	// Shortest Common Supersequence
	// Approach1: Recursion
	public int lengthOfSCS1(String s1, String s2) {
		return lengthOfSCS1(s1, s2, s1.length(), s2.length());
	}

	public int lengthOfSCS1(String s1, String s2, int m, int n) {
		if (m == 0) return n;
		if (n == 0) return m;

		if (s1.charAt(m - 1) == s2.charAt(n - 1)) return 1 + lengthOfSCS1(s1, s2, m - 1, n - 1);

		return 1 + Math.min(lengthOfSCS1(s1, s2, m - 1, n), lengthOfSCS1(s1, s2, m, n - 1));
	}

	// Approach2: DP - Bottom Up Approach
	public int lengthOfSCS2(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		if (m == 0 || n == 0) return 0;
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0) dp[i][j] = j;
				else if (j == 0) dp[i][j] = i;
				else if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
				else dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[m][n];
	}

	// Length & Print the Longest Repeated Subsequence
	public int longestRepeatedSubSequence(String str) {
		int n = str.length();
		int[][] lrsArray = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					lrsArray[i][j] = 0;
				} else if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
					lrsArray[i][j] = 1 + lrsArray[i - 1][j - 1];
				} else {
					lrsArray[i][j] = Math.max(lrsArray[i - 1][j], lrsArray[i][j - 1]);
				}
			}
		}
		printLongestRepeatedSubSequence(lrsArray, str);
		return lrsArray[n][n];
	}

	private void printLongestRepeatedSubSequence(int[][] lrsArray, String str) {
		int i, j, count, index;
		i = j = str.length();
		count = index = lrsArray[i][j];
		char[] repeatedSeq = new char[count];

		while (i > 0 && j > 0) {
			if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
				repeatedSeq[--index] = str.charAt(i - 1);
				i--;
				j--;
			} else if (lrsArray[i - 1][j] > lrsArray[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		System.out.print("SubSequence:");
		for (int k = 0; k < count; k++)
			System.out.print(repeatedSeq[k] + " ");
	}

	public int longestPalindromeSubSequence1(String str) {
		return longestPalindromeSubSequence1(str, 0, str.length() - 1);
	}

	// Longest Palindrome SubSequence using Recursion
	public int longestPalindromeSubSequence1(String str, int i, int j) {
		// Base Case 1: If there is only 1 character
		if (i == j) return 1;

		// Base Case 2: If there are only 2 characters and both are same
		if (str.charAt(i) == str.charAt(j) && i + 1 == j) return 2;

		// If the first and last characters match
		if (str.charAt(i) == str.charAt(j)) return longestPalindromeSubSequence1(str, i + 1, j - 1) + 2;

		// If the first and last characters do not match
		return Math.max(longestPalindromeSubSequence1(str, i, j - 1), longestPalindromeSubSequence1(str, i + 1, j));
	}

	// Longest Palindrome SubSequence using DP
	public int longestPalindromeSubSequence2(String str) {
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

		// Print the Sequence
		System.out.println("Palindromic SubSequence:" + printPalindromicSeq(result, str));

		return result[0][n - 1];
	}

	private String printPalindromicSeq(int[][] result, String str) { // Its my own logic - needs to verified
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

	/************************** Partition Problems **************************/
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

	/******************************** Misc Problems ************************************/
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
		if (costs == null || costs.length == 0) { return 0; }

		int n = costs.length;
		int k = costs[0].length;

		// dp[i][j] means the min cost painting for house i, with color j
		int[][] dp = new int[n][k];

		// Initialization
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

		// Final state
		int minCost = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++)
			minCost = Math.min(minCost, dp[n - 1][i]);

		return minCost;
	}

	/***********************************************************************************************/
	/* 
	 * Binomial Coeff: A binomial coefficient C(n, k) can be defined as the coefficient of X^k in the expansion of (1 + X)^n.
	 *    Optimal Structure: C(n, k) = C(n-1, k-1) + C(n-1, k);
	 *    Overlapping Subproblems: Above function computes the same subproblems again and again
	 *    
	 *    Note: Binomial coeff uses Pascal's triangle concept
	 */
	// Using recursive function
	public int binomialCoeff1(int n, int k) {
		if (k == 0 || k == n) return 1;
		return binomialCoeff1(n - 1, k - 1) + binomialCoeff1(n - 1, k);
	}

	// Using DP(Tabulation Approach - Bottom up); Both Complexity: O(nk)
	public int binomialCoeff2(int n, int k) {
		int[][] result = new int[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= Math.min(i, k); j++) {
				if (j == 0 || j == i) result[i][j] = 1;
				else result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
			}
		}
		return result[n][k];
	}

	// Efficient Tabulation method; Same time complexity, but O(k) space complexity
	public int binomialCoeff3(int n, int k) {
		int[] result = new int[k + 1];
		result[0] = 1;
		for (int i = 1; i <= n; i++)
			for (int j = Math.min(i, k); j > 0; j--)
				result[j] = result[j] + result[j - 1];

		return result[k];
	}

	/* Ugly Numbers:Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 
	 * 12, 15, … shows the first 11 ugly numbers. By convention, 1 is included.*/

	/*Approach1: Brute Force Approach
	 * To check if a number is ugly, divide the number by greatest divisible powers of 2, 3 and 5, if the number becomes 1 then 
	 * it is an ugly number otherwise not.
	 */
	public int findNthUglyNumber1(int n) {
		int i = 1, count = 1;
		while (count < n) {
			i++;
			if (isUglyNumber(i)) {
				count++;
			}
		}
		return i;
	}

	private boolean isUglyNumber(int n) {
		n = divideWithGreatestDivisor(n, 2);
		n = divideWithGreatestDivisor(n, 3);
		n = divideWithGreatestDivisor(n, 5);
		return n == 1 ? true : false;
	}

	private int divideWithGreatestDivisor(int a, int b) {
		while (a % b == 0)
			a = a / b;
		return a;
	}

	/* Approach2: Using Dynamic Programming*/
	public int findNthUglyNumber2(int n) {
		int[] result = new int[n];
		int index2 = 0, index3 = 0, index5 = 0, uglyNo;
		int value2, value3, value5;
		result[0] = 1;

		for (int i = 1; i < n; i++) {
			value2 = result[index2] * 2;
			value3 = result[index3] * 3;
			value5 = result[index5] * 5;
			// minimum number from 3 values will be ugly number
			uglyNo = findMin(value2, value3, value5);
			result[i] = uglyNo;
			if (uglyNo == value2) index2++;

			if (uglyNo == value3) index3++;

			if (uglyNo == value5) index5++;

		}

		return result[n - 1];
	}

	private int findMin(int a, int b, int c) {
		if (a < b && a < c) return a;
		else if (b < c) return b;
		else return c;
	}

	// Approach1: Iterative solution; Find the sum and print the subset
	public void subSetSum1(int[] a, int sum) {
		int[] binary = new int[a.length];
		System.out.println("Sum of subset: " + subSet(a, binary, 0, sum));
		// System.out.println(Arrays.toString(binary));
		for (int i = 0; i < binary.length; i++) {
			if (binary[i] == 1) System.out.print(a[i] + ", ");
		}
	}

	public int subSet(int[] a, int[] binary, int index, int sum) {
		if (index == a.length) {
			// System.out.println(Arrays.toString(a));
			if (sum(a, binary) == sum) return sum;
			return -1;
		}
		int value1 = -1, value2 = -1;

		binary[index] = 0;
		value1 = subSet(a, binary, index + 1, sum);

		if (value1 == -1) {
			binary[index] = 1;
			value2 = subSet(a, binary, index + 1, sum);
		}

		return value1 != -1 ? value1 : value2;
	}

	// Approach1: Recursive solution
	/* This solution may try all subsets of given set in worst case. Therefore time complexity of the above solution is exponential.
	 * The problem is in-fact NP-Complete (There is no known polynomial time solution for this problem).
	 **/
	public void subSetSum2(int[] a, int sum) {
		System.out.println("Is subset found for sum: " + subSetSum2(a, 0, sum));
	}

	//
	public boolean subSetSum2(int[] a, int index, int sum) {
		if (sum == 0) return true;
		if (index == a.length && sum != 0) return false;

		// This condition is to skip the element which is greater than sum
		if (a[index] > sum) return subSetSum2(a, index + 1, sum);

		/* else, check if sum can be obtained by any of the following
		 *   (a) including the first element
		 *   (b) excluding the first element 
		 */
		return subSetSum2(a, index + 1, sum) || subSetSum2(a, index + 1, sum - a[index]);
	}

	// Aproach3: DP Solution
	public void subSetSum3(int[] a, int sum) {
		System.out.println("Is subset found for sum: " + subSetSum2(a, 0, sum));
	}

	// Looks like duplicate of subSetSum2
	// Returns true if there is a subset of set[] with sum equal to given sum
	public boolean isSubsetSum(int set[], int n, int sum) {
		// Base Cases
		if (sum == 0) return true;
		if (n == 0 && sum != 0) return false;

		// If last element is greater than sum, then ignore it
		if (set[n - 1] > sum) return isSubsetSum(set, n - 1, sum);

		/* else, check if sum can be obtained 
		by any of the following
		    (a) including the last element
		    (b) excluding the last element */
		return isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]);
	}

	private int sum(int[] a, int[] binary) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			if (binary[i] == 1) {
				sum += a[i];
			}
		}
		return sum;
	}

	public int minCost(int cost[][]) {
		return minCost(cost, 0, 0);
	}

	int minCost(int cost[][], int r, int c) {
		if (r >= cost.length || c >= cost[0].length) return Integer.MAX_VALUE;
		else if (r == cost.length - 1 && c == cost[0].length - 1) return cost[r][c];
		else return cost[r][c]
				+ Utils.min(minCost(cost, r + 1, c + 1), minCost(cost, r + 1, c), minCost(cost, r, c + 1));
	}

	public int friendsPairProblem(int n) {
		if (n <= 0) return 0;

		if (n == 1 || n == 2) return n;

		return friendsPairProblem(n - 1) + ((n - 1) * friendsPairProblem(n - 2));
	}

	// Matrix Chain Multiplication using Recursion
	public int matrixChainOrder1(int[] p) {
		return 0;
	}

	// Matrix Chain Multiplication using DP
	public int matrixChainOrder2(int[] p) {
		int n = p.length - 1, j, q;
		int[][] result = new int[n][n];
		for (int len = 2; len <= n; len++) {
			for (int i = 0; i < n - len + 1; i++) {
				j = i + len - 1;
				result[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					q = result[i][k] + result[k + 1][j] + (p[i] * p[k + 1] * p[j + 1]);
					result[i][j] = Math.min(q, result[i][j]);
				}
			}
		}
		System.out.println((int) 'z');
		return result[0][n - 1];
	}

	/* Strobogrammatic Number I: A strobogrammatic number is a number that looks the same when rotated 180 degrees
	 * (looked at upside down). Write a function to determine if a number is strobogrammatic. The number is represented
	 * as a string.
	 */
	public boolean isStrobogrammatic(String num) {
		if (num.length() == 1) {
			if (num == "0" || num == "1" || num == "8") return true;
			return false;
		}

		int l = 0, h = num.length() - 1;
		while (l <= h) {
			if (!isValid(num.charAt(l), num.charAt(h))) return false;
			l++;
			h--;
		}

		return true;
	}

	private boolean isValid(char a, char b) {
		switch (a) {
			case '0':
				return b == '0';
			case '1':
				return b == '1';
			case '6':
				return b == '9';
			case '8':
				return b == '8';
			case '9':
				return b == '6';
			default:
				return false;
		}
	}

	/*Strobogrammatic Number II:
	 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down). 
	 * Find all strobogrammatic numbers that are of length = n. For example, Given n = 2, return ["11","69","88","96"].
	 */
	// Using Recursion:
	public List<String> findStrobogrammatic1(int n) {
		return findStrobogrammaticUtil(n, n);
	}

	public List<String> findStrobogrammaticUtil(int n, int length) {
		if (n == 0) return Arrays.asList("");
		if (n == 1) return Arrays.asList("0", "1", "8");

		List<String> middle = findStrobogrammaticUtil(n - 2, length);
		List<String> result = new ArrayList<>();
		for (String str : middle) {
			if (n != length) result.add("0" + str + "0");
			result.add("1" + str + "1");
			result.add("6" + str + "9");
			result.add("8" + str + "8");
			result.add("9" + str + "6");
		}
		return result;
	}

	// Using Iteration:
	public List<String> findStrobogrammatic2(int n) {
		List<String> one = Arrays.asList("0", "1", "8"), result = Arrays.asList("");
		if (n % 2 == 1) result = one;
		for (int i = (n % 2) + 2; i <= n; i += 2) {
			List<String> newList = new ArrayList<>();
			for (String str : result) {
				if (i != n) newList.add("0" + str + "0");
				newList.add("1" + str + "1");
				newList.add("6" + str + "9");
				newList.add("8" + str + "8");
				newList.add("9" + str + "6");
			}
			result = newList;
		}

		// result.forEach(k -> System.out.print(k + ", "));
		return result;
	}

	/*
	 * Strobogrammatic Number III: A strobogrammatic number is a number that looks the same when rotated 180 degrees
	 * (looked at upside down). Write a function to count the total strobogrammatic numbers that exist in the range of
	 * low <= num <= high. For example, Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three
	 * strobogrammatic numbers. Note: Because the range might be a large number, the low and high numbers are
	 * represented as string.
	 */

	// Approach-1
	public int noOfSumInGivenRange1(int i, int n, int currSum, int sum) {
		if (i > n) return 0;
		else if (currSum + i == sum) return 1;
		else if (currSum < sum) {
			return noOfSumInGivenRange1(i + 1, n, currSum + i, sum) + noOfSumInGivenRange1(i + 1, n, currSum, sum);
		} else return 0;
	}

	// Approach-2: Both Approach 1 & Approach 2 are almost same with minor change
	public int noOfSumInGivenRange2(int i, int n, int sum) {
		if (i > n) return 0;
		else if (i == sum) return 1;
		else if (i < sum) {
			return noOfSumInGivenRange2(i + 1, n, sum - i) + noOfSumInGivenRange2(i + 1, n, sum);
		} else return 0;
	}

	// Approach-1
	public int noOfSumofSquareInGivenRange1(int i, int n, int currSum, int sum) {
		int tempSum = (int) (currSum + Math.pow(i, 2));
		if (i > n) return 0;
		else if (tempSum == sum) return 1;
		else if (currSum < sum) {
			return noOfSumofSquareInGivenRange1(i + 1, n, tempSum, sum)
					+ noOfSumofSquareInGivenRange1(i + 1, n, currSum, sum);
		} else return 0;
	}

	// Approach-2: Both Approach 1 & Approach 2 are almost same with minor change
	public int noOfSumofSquareInGivenRange2(int i, int n, int sum) {
		int squareValue = (int) Math.pow(i, 2);
		if (i > n) return 0;
		else if (squareValue == sum) return 1;
		else if (squareValue < sum) {
			return noOfSumofSquareInGivenRange2(i + 1, n, sum - squareValue)
					+ noOfSumofSquareInGivenRange2(i + 1, n, sum);
		} else return 0;
	}

	public int sumOfNthPower(int start, int n, int sum, int currSum) {
		int result = 0;
		if (currSum == 0) return 1;

		int end = (int) Math.sqrt(sum);
		for (int i = start; i <= end; i++) {
			int diff = currSum - (int) Math.pow(i, n);
			if (diff >= 0) result += sumOfNthPower(i + 1, n, sum, diff);
		}

		return result;
	}

	public int sumOfNthPower1(int n, int sum, int num) {
		int nPowerNum = (int) Math.pow(num, n);
		if (nPowerNum < sum) return sumOfNthPower1(n, sum - nPowerNum, num + 1) + sumOfNthPower1(n, sum, num + 1);
		else if (nPowerNum == sum) return 1;
		else return 0;
	}

	/*Decode Ways: A message containing letters from A-Z is being encoded to numbers using the following mapping:
	'A' -> 1
	'B' -> 2
	*/
	public int numDecodings(String s) {
		if (s.length() == 0) return 0;

		return numDecodings(s, 0);
	}

	public int numDecodings(String s, int i) {
		int n = s.length();
		if (i == n) return 1;
		if (i > n || s.charAt(i) == '0') return 0;

		int sum = 0;
		if (i + 1 < n)
			if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6')) sum += numDecodings(s, i + 2);

		return sum + numDecodings(s, i + 1);
	}

	/*
	 * Get Target Number Using Number List and Arithmetic Operations: Given a list of numbers and a target number, write
	 * a program to determine whether the target number can be calculated by applying "+-/*" operations to the number
	 * list? You can assume () is automatically added when necessary. For example, given {1,2,3,4} and 21, return true.
	 * Because (1+2)*(3+4)=21.
	 */
	// This is a partition problem which can be solved by using depth first search.
	public static boolean isReachable(ArrayList<Integer> list, int target) {
		if (list == null || list.size() == 0) return false;
		int i = 0, j = list.size() - 1;
		ArrayList<Integer> results = getResults(list, i, j, target);
		for (int num : results)
			if (num == target) return true;

		return false;
	}

	public static ArrayList<Integer> getResults(ArrayList<Integer> list, int left, int right, int target) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		if (left > right) {
			return result;
		} else if (left == right) {
			result.add(list.get(left));
			return result;
		}

		for (int i = left; i < right; i++) {

			ArrayList<Integer> result1 = getResults(list, left, i, target);
			ArrayList<Integer> result2 = getResults(list, i + 1, right, target);

			for (int x : result1) {
				for (int y : result2) {
					result.add(x + y);
					result.add(x - y);
					result.add(x * y);
					if (y != 0) result.add(x / y);
				}
			}
		}

		return result;
	}

	public int sum(int[] arr, int n) {
		if (n < 0) return 0;

		return arr[n] + sum(arr, n - 1);
	}
}