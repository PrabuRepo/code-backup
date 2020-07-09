package com.geeks.problem.algorithms.test;

import com.geeks.problem.algorithms.PatternSearching;

public class TestPatternSearching extends PatternSearching {
	public static void main(String[] args) {

		TestPatternSearching ob = new TestPatternSearching();

		ob.testAlgorithms();
	}

	public void testAlgorithms() {
		String str = "AABAACAADAABAAABAA";
		String pattern = "AABA";
		/*String str = "ABCDABCD";
		String pattern = "abcabcc";*/

		System.out.println("Text:" + str);
		System.out.println("Pattern:" + pattern);

		// Naive Pattern Searching
		System.out.println("Naive Pattern Searching:");
		naivePatternSearching(str, pattern);

		// KMP Algorithm
		System.out.println("Pattern Searching:KMPAlgorithm");
		KMPAlgorithm(str, pattern);

		// Rabin-Karp Algorithm
		System.out.println("Pattern Searching:Rabin-Karp Algorithm");
		rabinKarpAlgorithm(str, pattern);

		// Z Algorithm
		System.out.println("Pattern Searching:Z Algorithm");
		zAlgorithm(str, pattern);

	}

	public void testProblems() {

	}
}
