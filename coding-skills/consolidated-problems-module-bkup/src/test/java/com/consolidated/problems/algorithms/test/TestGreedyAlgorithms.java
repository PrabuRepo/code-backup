package com.consolidated.problems.algorithms.test;

import java.util.ArrayList;
import java.util.List;

import com.common.model.Interval;
import com.consolidated.problems.algorithms.GreedyAlgorithms;

public class TestGreedyAlgorithms extends GreedyAlgorithms {
	public static void main(String[] args) {
		TestGreedyAlgorithms ob = new TestGreedyAlgorithms();

		String[] list = { "cool", "lock", "cook" };
		commonChars(list);
		// ob.testIntervalProblems();

	}

	public static List<String> commonChars(String[] A) {
		List<String> result = new ArrayList<>();
		if (A.length == 0) return result;

		int n = A.length;
		int[] count = new int[26];

		for (int i = 0; i < A[0].length(); i++)
			count[A[0].charAt(i) - 'a']++;

		for (int i = 1; i < n; i++) {
			String s = A[i];
			for (int j = 0; j < s.length(); j++) {
				if (count[s.charAt(j) - 'a'] != 0) count[s.charAt(j) - 'a']++;
			}
		}

		for (int i = 0; i < 26; i++)
			if (count[i] != 0) {
				int ct = count[i] / n;
				while (ct-- > 0) {
					char ch = (char) (i + 'a');
					result.add(ch + "");
				}
			}

		return result;

	}

	public void testIntervalProblems() {
		int s[] = { 1, 3, 0, 5, 8, 5 };
		int f[] = { 2, 4, 6, 7, 9, 9 };
		System.out.println("Activity Selection Problem(Max no of Activites):" + maxActivities(s, f));

		System.out.println("N meetings in one room: ");
		findMeetingsInOneRoom(s, f);

		System.out.println("\nMeeting Rooms I: " + canAttendAllMeetings(mockIntervals()));
		System.out.println("Meeting Rooms II: " + minMeetingRooms1(mockIntervals()));
		int[] arrv = { 900, 920, 920, 950, 1100, 1500, 1800 };
		int[] dep = { 910, 930, 1200, 1120, 1130, 1900, 2000 };
		System.out.println("Min no of platforms required: " + minPlatformRequired2(arrv, dep));
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
