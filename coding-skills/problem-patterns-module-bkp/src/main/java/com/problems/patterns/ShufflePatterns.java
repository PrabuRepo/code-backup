package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.common.utilities.Utils;

public class ShufflePatterns {

	public static void main(String[] args) {
		ShufflePatterns ob = new ShufflePatterns();
		int[] arr = { 1, 2, 3, 4, 5 };
		ob.randomize(arr);
		System.out.println(Arrays.toString(arr));

		int[] cards = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
				11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
				22, 23, 24, 25,
				26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36,
				37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
				48, 49, 50,
				51 };

		shuffleCards(cards);
		System.out.println(Arrays.toString(cards));

	}

	/* Shuffle a given array
	 * Given an array, write a program to generate a random permutation of array elements.Here shuffle means that every
	 * permutation of array element should equally likely.
	 */

	// Approach1:Brute Force Approach
	/* Let the given array be arr[]. A simple solution is to create an auxiliary array temp[] which is initially a copy
	 * of arr[]. Randomly select an element from temp[], copy the randomly selected element to arr[0] and remove the
	 * selected element from temp[]. Repeat the same process n times and keep copying elements to arr[1], arr[2], … .
	 * The time complexity of this solution will be O(n^2).
	 */

	// Approach2: Fisher–Yates shuffle Algorithm
	/* Fisher–Yates shuffle Algorithm works in O(n) time complexity. The assumption here is, we are given a function
	 * rand() that generates random number in O(1) time. The idea is to start from the last element, swap it with a
	 * randomly selected element from the whole array (including last). Now consider the array from 0 to n-2 (size
	 * reduced by 1), and repeat the process till we hit the first element.
	 */
	public void randomize(int arr[]) {
		Random random = new Random();
		int n = arr.length;
		// Start from the last element and swap one by one.
		for (int i = 0; i < n; i++) {
			// Pass index from 1 to n & pick a random index from 1 to n, because Random doesn't support zero
			int r = random.nextInt(i + 1); // or random.nextInt(n);
			// Swap i and random index 'r' in array
			Utils.swap(arr, i, r);
		}
	}

	/*Shuffle a deck of cards: Given a deck of cards, the task is to shuffle them.*/
	public static void shuffleCards(int card[]) {
		Random random = new Random();
		for (int i = 0; i < card.length; i++) {
			// Random for remaining positions.
			int r = i + random.nextInt(52 - i);
			// Swap index i & r
			Utils.swap(card, i, r);
		}
	}

	// Shuffle or Randomize a list in Java:
	public static void shuffleCollections() {
		ArrayList<String> mylist = new ArrayList<String>();
		mylist.add("ide");
		mylist.add("quiz");
		mylist.add("geeksforgeeks");
		mylist.add("quiz");
		mylist.add("practice");
		mylist.add("qa");

		System.out.println("Original List : \n" + mylist);

		Collections.shuffle(mylist);

		System.out.println("\nShuffled List : \n" + mylist);
	}

	/* Shuffle an Array: Shuffle a set of numbers without duplicates.
	 * Example: 
	 *  Initialize an array with set 1, 2, and 3. int[] nums = {1,2,3};
	 *  Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
	 *  solution.shuffle();
	 *  Resets the array back to its original configuration [1,2,3].
	 *  solution.reset();
	 *  Returns the random shuffling of array [1,2,3].
	 *  Solution.shuffle();
	 */
	int[]  original;
	int[]  shuffled;
	Random random;

	public void init(int[] nums) {
		original = nums;
		shuffled = Arrays.copyOf(nums, nums.length);
		random = new Random();
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		shuffled = Arrays.copyOf(original, original.length);
		return shuffled;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int n = shuffled.length;
		for (int i = 0; i < n; i++) {
			// Util Random generates number from 0 to n-1;
			int k = random.nextInt(n);
			// Swap the number based on random index 'k';
			Utils.swap(shuffled, i, k);
		}
		return shuffled;
	}

}