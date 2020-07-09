package com.consolidated.problems.datastructures.test;

import com.consolidated.problems.datastructures.StringProblems;

public class TestStringProblems extends StringProblems {
	public static void main(String[] args) {

		TestStringProblems ob = new TestStringProblems();
		System.out.println("Reverse Words: " + ob.reverseWords("the sky is blue"));
		System.out.println("Reverse Words: " + ob.reverseWords2("the sky is blue"));

		System.out.println("Count no of non consecutive ones: " + ob.count1(5));

		System.out.println("Add Strings: " + ob.addStrings("1", "9"));

		String[] words = { "practice", "makes", "perfect", "coding", "makes" };
		System.out.println("Shortest Word Distance : " + ob.shortestDistanceI(words, "coding", "makes"));

		System.out.println("String Compression: " + ob.strCompression("abbbbbbbbbbbbbb"));
	}

}
