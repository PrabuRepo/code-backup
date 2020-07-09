package com.geeks.problem.algorithms.test;

import java.util.Arrays;

import com.common.model.Interval;
import com.common.model.Job;
import com.geeks.problem.algorithms.GreedyAlgorithms;

public class TestGreedyAlgorithms extends GreedyAlgorithms {
	public static void main(String[] args) {
		TestGreedyAlgorithms ob = new TestGreedyAlgorithms();

		ob.testIntervalProblems();

		// ob.testStandardProblems();

		// ob.testSpecicalCaseDPProblems();

		// ob.testMiscProblems();
	}

	public void testIntervalProblems() {
		int s[] = { 1, 3, 0, 5, 8, 5 };
		int f[] = { 2, 4, 6, 7, 9, 9 };
		System.out.println("Activity Selection Problem:");
		activitySelectionProblem1(s, f);
		System.out.println("\nActivity Selection Problem2(Max no of Activites):" + maxActivities(s, f));

		System.out.println("N meetings in one room: ");
		findMeetingsInOneRoom(s, f);

		System.out.println("\nMeeting Rooms I: " + canAttendAllMeetings(mockIntervals()));
		System.out.println("Meeting Rooms II: " + minMeetingRooms(mockIntervals()));
		int[] arrv = { 900, 920, 920, 950, 1100, 1500, 1800 };
		int[] dep = { 910, 930, 1200, 1120, 1130, 1900, 2000 };
		System.out.println("Min no of platforms required: " + minPlatformRequired2(arrv, dep));

		System.out.println("Job Sequencing Problem: ");
		Job[] jobs = { new Job('a', 2, 100), new Job('b', 1, 19), new Job('c', 2, 27), new Job('d', 1, 25),
				new Job('e', 3, 15) };
		jobSequence(jobs);
		int[][] pairs = { { -10, -8 }, { 8, 9 }, { -5, 0 }, { 6, 10 }, { -6, -4 }, { 1, 7 }, { 9, 10 }, { -4, 7 } };
		// int[][] pairs = { { 1, 2 }, { 3, 4 }, { 2, 3 } };
		System.out.println("Maximum Length of Pair Chain: " + findLongestChain(pairs));

	}

	public void testStandardProblems() {
		System.out.println("Minimum no of coins: ");
		minNoOfCoins(56);

		int[] prices = { 1, 12, 5, 111, 200, 1000, 10 };
		System.out.println("\nMaximize Toys: " + maximizeToys(prices, 50));

		System.out.println("Huffman Encoding & Decoding:");
		String str = "abbcccddddeeeee"; // "geeksforgeeks"
		huffmanCoding(str);

		int[] pages = { 5, 0, 1, 3, 2, 4, 1, 0, 5 };
		System.out.println("Pagee Faults: " + noOfPageFaults(pages, 4));

	}

	public void testSpecicalCaseDPProblems() {
		int[][] arr = { { 60, 10 }, { 100, 20 }, { 120, 30 } };
		System.out.println("Fractional Knapsack Problem: " + fractionalKnapsack(arr, 50));
	}

	public void testMiscProblems() {
		int[] nums = { 1, 5, 10 };
		System.out.println("Patching Array: " + minPatches(nums, 20));
		System.out.println("" + compareVersion("0.1", "1.1"));
		int[] nums1 = { 6, 7 }, nums2 = { 6, 0, 4 };
		int k = 5;
		System.out.println("Create Maximum Number: " + Arrays.toString(maxNumber(nums1, nums2, k)));
		System.out.println("Find Permutation: " + Arrays.toString(findPermutation1("IDDIDDDI")));
		System.out.println("Find Permutation: " + Arrays.toString(findPermutation2("IDDIDDDI")));
	}

	private Interval[] mockIntervals() {
		Interval[] intervals = new Interval[5];
		intervals[0] = new Interval(1, 2);
		intervals[1] = new Interval(3, 9);
		intervals[2] = new Interval(6, 7);
		intervals[3] = new Interval(8, 10);
		intervals[4] = new Interval(12, 16);
		return intervals;
	}
}
