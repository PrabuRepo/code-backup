package com.basic.algorithms;

public class PatternSearching {

	// Naive Pattern Searching; Time complexity:O(mn) = Exactly O(m(n-m))
	public void naivePatternSearching(String str, String pattern) {
		int j;
		int n = str.length(); // String Length
		int m = pattern.length(); // Pattern Length
		for (int i = 0; i <= (n - m); i++) {
			if (str.charAt(i) == pattern.charAt(0)) {
				for (j = 0; j < m; j++) {
					if (str.charAt(i + j) != pattern.charAt(j)) break;
				}
				if (j == m) System.out.println("Pattern found at: " + i);
			}
		}
	}

	// KMP(Knuth Morris Pratt) Algorithm
	public void KMPAlgorithm(String text, String pattern) {
		int m = pattern.length(), n = text.length();
		int[] lps = computeLPSArray(pattern);

		int i = 0, j = 0;
		while (i < n) {
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;

				if (j == m) {
					System.out.println("Pattern found at index:" + (i - j));
					j = lps[j - 1];
				}
			} else {
				if (j != 0) j = lps[j - 1];
				else i++;
			}
		}
	}

	// LPS - Longest Prefix Suffix
	public int[] computeLPSArray(String pattern) {
		int m = pattern.length();
		int[] lps = new int[m];
		lps[0] = 0;
		int i = 1, j = 0;
		while (i < m) {
			if (pattern.charAt(i) == pattern.charAt(j)) {
				lps[i++] = ++j; // j= increment & assign; i = assign & increment
			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

	// Rabin-Karp Algorithm
	public void rabinKarpAlgorithm(String text, String pattern) {
		int prime = 101;
		long textHash = 0, patternHash = 0;
		int n = text.length(), m = pattern.length();
		// Calculate hash value
		for (int i = 0; i < m; i++) {
			// Pattern Hash Value
			patternHash += pattern.charAt(i) * Math.pow(prime, i);
			// Text Hash Value
			textHash += text.charAt(i) * Math.pow(prime, i);
		}
		int j;
		for (int i = 0; i <= n - m; i++) {
			if (textHash == patternHash) {
				// Check for characters one by one
				for (j = 0; j < m; j++) {
					if (text.charAt(i + j) != pattern.charAt(j)) break;
				}
				if (j == m) System.out.println("Pattern found at index:" + i);
			}

			// Recalculate the test hash value
			if (i < n - m) {
				textHash -= text.charAt(i); // X = OldHash - text(first char)
				textHash /= prime; // X = X/prime
				textHash += (text.charAt(i + m) * Math.pow(prime, m - 1)); // NewHash = X + text(next char) *
																			// prime^(m-1);
			}
		}
	}

	// Finite Automata
	public void finiteAutomata() {

	}

	// Z Algorithm
	public void zAlgorithm(String text, String pattern) {
		int zArrayLength = text.length() + pattern.length() + 1;
		String zString = pattern + "$" + text;
		int[] z = calculateZArray(zString);

		for (int i = 0; i < zArrayLength; i++) {
			if (z[i] == pattern.length()) {
				System.out.println("Pattern found at index: " + (i - pattern.length() - 1));
			}
		}
	}

	private int[] calculateZArray(String str) {
		int n = str.length();
		int[] zArray = new int[n];
		int left = 0, right = 0;
		int j;
		for (int i = 1; i < n; i++) {
			if (i > right) {
				left = right = i;
				while (right < n && str.charAt(right - left) == str.charAt(right))
					right++;

				zArray[i] = right - left;
				right--;
			} else {
				// j corresponds to number which matches in [L,R] interval.
				j = i - left;

				/*if Z[j] is less than remaining interval then Z[i] will be equal to Z[j]. For example, str = "ababab", i = 3, R = 5 and L = 2*/
				if (zArray[j] < right - 1 + 1) {
					zArray[i] = zArray[j];
				} else {
					// else start from R and check manually
					left = i;
					while (right < n && str.charAt(i) == str.charAt(right - left))
						right++;

					zArray[i] = right - left;
					right--;
				}
			}
		}
		return zArray;
	}
}