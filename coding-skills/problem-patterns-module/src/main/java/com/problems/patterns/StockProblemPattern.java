package com.problems.patterns;

import java.util.Arrays;

/*
 * Formula for stock problems:
 * 	buyingPrice = Min(buyingPrice, currPrice-profit)
 *  profit = Max(profit, currPrice-buyingPrice)
 */
public class StockProblemPattern {
	/*
	 * Buy and Sell Stock Problems:
	 * Ref: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
	 */
	/*
	 * Max Profit Stock Problem Solution:
	 * 	- Find Minimum  - Buying Price 
	 *  - Find Max difference - Profit(SP - BP) 
	 *  1. Only 1 trx
	 *  2. Only 2 trx
	 *  3: Only K trx
	 *  4: Any number of Trx
	 *  5: Any no of Trx with cooling time
	 *  6. Any no of Trx with Fee
	 *  Note:
	 *  min: BuyingPrice,
	 *  diff: Profit = (Sell Price - Buy Price)
	 *  
	 */
	// Best Time to Buy and Sell Stock - One Transaction
	public int maxProfit1(int[] prices) {
		int diff = 0, min = Integer.MAX_VALUE;
		for (int price : prices) {
			// Find Min:(without considering prev values)
			min = Math.min(min, price);
			// Find Max Diff (as per element order):
			diff = Math.max(diff, price - min);
		}
		return diff;
	}

	// Best Time to Buy and Sell Stock III - Atmost Two Transactions
	public int maxProfit2(int[] prices) {
		int diff1 = 0, min1 = Integer.MAX_VALUE;
		int diff2 = 0, min2 = Integer.MAX_VALUE;
		for (int price : prices) {
			// Find max profit for one trans
			min1 = Math.min(min1, price);
			diff1 = Math.max(diff1, price - min1);
			// Find max profit for 2 trx:
			// Here we consider only 1 max trx profit/diff:
			min2 = Math.min(min2, price - diff1);
			diff2 = Math.max(diff2, price - min2);
		}
		return diff2;
	}

	// Best Time to Buy and Sell Stock IV
	public int maxProfit3(int k, int[] prices) {
		// or prices.length/2
		if (k >= prices.length >>> 1) {
			// Use Multiple Trx solution
			return maxProfit4(prices);
		}
		int[] diff = new int[k + 1];
		int[] min = new int[k + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		for (int price : prices) {
			for (int i = 1; i <= k; i--) {
				min[i] = Math.min(min[i],
						price - diff[i - 1]);
				diff[i] = Math.max(diff[i], price - min[i]);
			}
		}
		return diff[k];
	}

	// Best Time to Buy and Sell Stock II - Multiple Transactions
	public int maxProfit4(int[] prices) {
		int diff = 0, min = Integer.MAX_VALUE;
		for (int price : prices) {
			// Find min and also keep adding
			// prev profit/diff:
			min = Math.min(min, price - diff);
			// Find Max diff:
			diff = Math.max(diff, price - min);
		}
		return diff;
	}

	// Best Time to Buy and Sell Stock with Cooldown - Multiple Transactions
	// Similar to Prev soln, but store prev diff
	public int maxProfit5(int[] prices) {
		int prevDiff = 0, currDiff = 0,
				min = Integer.MAX_VALUE;
		for (int price : prices) {
			int temp = currDiff;
			min = Math.min(min, price - prevDiff);
			currDiff = Math.max(currDiff, price - min);
			prevDiff = temp;
		}
		return currDiff;
	}

	// Best Time to Buy and Sell Stock with Trx Fee - Multiple Transactions
	// Approach1: pay the fee when selling the stock:
	public int maxProfit6(int[] prices, int fee) {
		int diff = 0, min = Integer.MAX_VALUE;
		for (int price : prices) {
			min = Math.min(min, price - diff);
			diff = Math.max(diff, price - min - fee);
		}
		return diff;
	}

	// Approach2: pay the fee when buying the stock:
	public int maxProfit62(int[] prices, int fee) {
		int diff = 0, min = Integer.MAX_VALUE;
		for (int price : prices) {
			min = Math.min(min, price + fee - diff);
			diff = Math.max(diff, price - min);
		}
		return diff;
	}

}
