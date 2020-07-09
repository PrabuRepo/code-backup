package com.gaylemcdowell.problems.test;

import com.gaylemcdowell.problems.Ch01ArraysAndStrings;

public class TestCh01ArraysAndStrings extends Ch01ArraysAndStrings {
	public static void main(String[] args) {
		Ch01ArraysAndStrings ob = new Ch01ArraysAndStrings();

		System.out.println("Is unique chars: " + ob.isUniqueChars1("abvcd"));
		System.out.println("Is unique chars: " + ob.isUniqueChars2("abcde"));

		System.out.println("Check Permutations: " + ob.checkPermutation1("abcde", "edbca"));
		System.out.println("Check Permutations: " + ob.checkPermutation2("abcdr", "edbca"));

	}
}
