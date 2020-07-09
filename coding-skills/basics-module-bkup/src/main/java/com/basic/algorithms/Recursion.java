package com.basic.algorithms;

import java.util.ArrayList;
import java.util.List;

import com.common.utilities.Utils;

/*
 * Recursion:
 * 1. It needs base or exit condition; without that recursive func never ends. 
 * 2. For an input n, recursive function invokes n+1 times 
 * 
 * To solve a problem using recursion, then we need to make sure that:
 *   - The problem can broken down into smaller problems of same type.
 *   - Problem has some base case(s).
 *   - Base case is reached before the stack size limit exceeds.
 *   
 * When you hear a problem beginning with the following statements, it's often (though not always) a good candidate for recursion:
 *  - "Design an algorithm to compute the nth .. :; 
 *  - "Write code to list the first n .. :; 
 *  - "Implement a method to compute all..:; and so on.
 *  
 *  
 *  Recursion is useful for permutation, because it generates all combinations and tree-based questions
 */
//TODO: Add Recursion Pattern Problems: Only one prob per pattern 
public class Recursion {

	public static int count = 0;

	/**************** Methods to understand the recursion concepts *******************/
	/*************** 1.Single Recursion or Linear Recursion ********************/
	// single recursion: head recursive type
	public void headRecursiveCall(int n) {
		// No of calls in recursive program: n+1
		// System.out.println("No of calls:"+count++);
		count++;
		if (n >= 1) {
			// System.out.println("Before recursive fun call");
			headRecursiveCall(n - 1);
			System.out.print(n + " ");
		}

	}

	// single recursion: tail recursive type
	public void tailRecursiveCall(int n) {
		// No of calls in recursive program: n+1
		// System.out.println("No of calls:" + count++);
		count++;
		if (n >= 1) {
			System.out.print(n + " ");
			tailRecursiveCall(n - 1);
		}
	}

	// Example1: Factorial of n
	public long factRecursive(int n) {
		if (n == 0 || n == 1) return 1;
		return n * factRecursive(n - 1);
	}

	public long factIterative(int n) {
		int result = 1;
		if (n > 1) {
			for (int i = 1; i <= n; i++)
				result = result * i;
		}
		return result;
	}

	// Example2: Decimal to Binary Conversion
	public int decimalToBin(int n) {
		String data = "";
		if (n == 0 || n == 1) return n;

		data += decimalToBin(n / 2);
		data += n % 2;
		return Integer.valueOf(data);
	}

	/*************** 2.Multiple recursion & Binary Recursion ********************/
	/*Binary Recursion: This form of recursion has the potential for calling itself twice instead of once as with before. 
	 Many operations, such as traversals, on binary trees are naturally binary recursive, like the trees.*/

	// Binary Recursion: Both Head & Tail recursion
	public void binaryRecursiveCall(int n) {
		// No of calls in recursive program: n+1s
		// System.out.println("No of calls:" + count++);
		count++;
		if (n >= 1) {
			System.out.println(
					"t(" + (n - 1) + "): ");
			headRecursiveCall(n - 1); // Head Recursion
			// tailRecursiveCall(n - 1); // Tail Recursion

			// System.out.println("Value:"+n);
			System.out.println(
					"t(" + (n - 1) + "): ");
			headRecursiveCall(n - 1); // Head Recursion
			// tailRecursiveCall(n - 1); // Tail Recursion
		}
	}

	// Example1: Fibonacci Series
	public long fibRecursive(int n) {
		if (n <= 1) return n;
		return fibRecursive(n - 1)
				+ fibRecursive(n - 2);
	}

	// Fibonacci series: 0,1,1,2,3,5,8,13,21,34...
	public long fibIterative(int n) {
		if (n <= 1) return n;
		long f1 = 0, f2 = 1;
		System.out.print("Fibonacci series: ");
		System.out.print(f1 + " " + f2 + " ");
		int i = 2;
		long result = 0;
		while (i++ <= n) {
			result = f1 + f2;
			f1 = f2;
			f2 = result;
			System.out.print(result + " ");
		}
		return result;
	}

	// Example2: Quick Sort
	// Binary Recursion: Tail recursion type
	public void quickSort(int[] a, int l, int h) {
		if (l < h) {
			int m = partition(a, l, h);
			quickSort(a, l, m - 1);
			quickSort(a, m + 1, h);
		}
	}

	public static int partition(int[] a, int l,
			int h) {
		int j = l, pivot = a[h];

		for (int i = l; i <= h; i++) {
			if (a[i] < pivot) {
				Utils.swap(a, i, j);
				j++;
			}
		}
		Utils.swap(a, j, h);
		return j;
	}

	/*************** 3.Iterative Vs Recursive ********************/
	// Single Iteration
	public void singleIteration(int n) {
		for (int i = 1; i <= n; i++)
			System.out.print(i + ", ");
	}

	// Single Recursion
	public void singleRecursion(int i, int n) {
		if (i > n) return;
		System.out.print(i + ", ");
		singleRecursion(i + 1, n);
	}

	/*Double Iteration -> No of executions: n(n+1)/2 for this case. It varies based on the condition. 
	 * By default, no of execution: n*n times*/
	public void doubleIteration(int n) {
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= n; j++)
				System.out.print(j + ", ");
	}

	public static int dualRecursionCount = 0;

	/*Two Recursion -> No of executions: (2^n) - 1 or PrevValue+2^(n-1); Series: 1,3,7,15,31,63....*/
	public void doubleRecursion(int n) {
		doubleRecursion(0, n);
	}

	private void doubleRecursion(int index,
			int n) {
		if (index >= n) return;

		dualRecursionCount++;
		System.out.print(index + ", ");
		doubleRecursion(index + 1, n);
		// System.out.println();
		doubleRecursion(index + 1, n);
	}

	public static int oneIterAndRecurCount = 0;

	// One iteration & Recursion: Same result like double recursion method
	public void oneIterAndRecursion(int n) {
		// oneIterAndRecursion(0, n);
		oneIterAndRecursion2(n);
	}

	public void oneIterAndRecursion(int index,
			int n) {
		if (index >= n) return;
		// System.out.print(i + ", ");
		for (int j = index; j < n; j++) {
			oneIterAndRecurCount++;
			System.out.print(j + ", ");
			oneIterAndRecursion(j + 1, n);
		}
	}

	public void oneIterAndRecursion2(int n) {
		if (n <= 0) return;
		// System.out.print(i + ", ");
		for (int i = 0; i < n; i++) {
			oneIterAndRecurCount++;
			System.out.print(i + ", ");
			oneIterAndRecursion2(n - i - 1);
		}
	}

	// Example for double recursion: Sum of n combinations
	public void doubleRecursionWithSum(int n) {
		System.out.println("Sum: ");
		// sumOfSequences(1, 4, 0);
		List<Integer> seq = new ArrayList<>();
		sumOfSequences(1, 4, 0, seq);
	}

	public void sumOfSequences(int i, int n,
			int sum) {
		if (i > n) {
			System.out.print(sum + ", ");
			return;
		}

		sum += i;
		// System.out.print(i + "-" + sum + ", ");
		sumOfSequences(i + 1, n, sum);
		sumOfSequences(i + 1, n, sum - i);
	}

	public void sumOfSequences(int i, int n,
			int sum, List<Integer> seq) {
		if (i > n) {
			seq.stream().forEach(k -> System.out
					.print(k + ","));
			System.out.print("=" + sum + "; ");
			return;
		}

		sum += i;
		seq.add(i);
		// System.out.print(i + "-" + sum + ", ");
		sumOfSequences(i + 1, n, sum, seq);

		if (!seq.isEmpty())
			seq.remove(seq.size() - 1);

		sumOfSequences(i + 1, n, sum - i, seq);
	}

	// Triple Iteration
	public void tripleIteration(int n) {
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= n; j++)
				for (int k = j; k <= n; k++)
					System.out.print(k + ", ");
	}

	public static int tripleRecursionCount = 0;

	/*Three Recursion -> No of executions: PrevValue+3^(n-1); Series: 1,4,13,40,121,364....*/
	public void tripleRecursion(int i, int n) {
		if (i > n) return;

		System.out.print(i + ", ");
		tripleRecursionCount++;
		tripleRecursion(i + 1, n);
		tripleRecursion(i + 1, n);
		tripleRecursion(i + 1, n);
	}

	/***************  Recursion ********************/
	public void combination1(int n) {
		combination1(n, 0);
	}

	public void combination1(int n, int i) {
		if (i >= n) {
			System.out.println();
			return;
		}
		System.out.print(i + " ");
		combination1(n, i + 1);
		combination1(n, i + 1);
	}

	public int combinationSum41(int[] nums,
			int target) {
		if (target == 0) return 1;

		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			//System.out.print(i + " ");
			if (target >= nums[i])
				count += combinationSum41(nums,
						target - nums[i]);
		}
		return count;
	}

	public int combinationSum42(int[] nums,
			int target) {
		return combinationSum4(nums, target,
				nums.length - 1);
	}

	public int combinationSum4(int[] nums,
			int target, int i) {
		if (target == 0) return 1;
		if (target < 0 || i < 0) return 0;

		return combinationSum4(nums,
				target - nums[i], i)
				+ combinationSum4(nums, target,
						i - 1);
	}

	//Recursive another approach:
	public int coinChange(int[] coins,
			int amount) {
		int result = change(amount, coins,
				coins.length - 1);
		System.out.println(count);
		return result == Integer.MAX_VALUE ? -1
				: result;
	}

	public int change(int amount, int[] coins,
			int i) {
		if (amount == 0) {
			count++;
			return 0;
		}
		if (i < 0) return Integer.MAX_VALUE;

		if (amount < coins[i])
			return change(amount, coins, i - 1);

		int minVal = Math.min(
				change(amount - coins[i], coins,
						i),
				change(amount, coins, i - 1));
		if (minVal != Integer.MAX_VALUE && i == 0)
			System.out.println(minVal);
		return minVal == Integer.MAX_VALUE
				? minVal
				: minVal + 1;
	}

}