package com.problems.patterns;

public class PrefixSumPatterns {

	/* Prefix Sum Array: Given an array arr[] of size n, its prefix sum array is another array prefixSum[] of same size such that the 
	 * value of prefixSum[i] is arr[0] + arr[1] + arr[2] … arr[i].
	 * Ref: https://www.geeksforgeeks.org/prefix-sum-array-implementation-applications-competitive-programming/
	 */

	public void fillPrefixSum(int arr[], int n,
			int prefixSum[]) {
		prefixSum[0] = arr[0];
		// Adding present element  with previous element 
		for (int i = 1; i < n; ++i)
			prefixSum[i] = prefixSum[i - 1] + arr[i];
	}
}
