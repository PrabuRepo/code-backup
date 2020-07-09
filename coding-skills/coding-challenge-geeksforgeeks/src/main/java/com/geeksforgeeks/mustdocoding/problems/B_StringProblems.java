package com.geeksforgeeks.mustdocoding.problems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class B_StringProblems {

	public static void checkBalancedParantheses(String str) {
		Stack<Character> stack = new Stack<>();
		char ch, poppedCh;
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (ch == '(' || ch == '[' || ch == '{') {
				stack.push(ch);
			} else {
				if (!stack.isEmpty()) {
					poppedCh = stack.pop();
					if (!((poppedCh == '{' && ch == '}') || (poppedCh == '[' && ch == ']') || (poppedCh == '(' && ch == ')'))) {
						flag = false;
						break;
					}
				} else {
					flag = false;
					break;
				}
			}
		}

		if (flag && stack.isEmpty()) {
			System.out.println("balanced");
		} else {
			System.out.println("not balanced");
		}
	}

	public static void reverseWords(String str) {
		String[] strArray = str.split("\\.");

		StringBuilder sb = new StringBuilder();
		for (int i = strArray.length - 1; i >= 0; i--) {
			sb.append(strArray[i]);
			if (i != 0)
				sb.append(".");
		}
		System.out.println(sb.toString());
	}

	public static void permutationOfGivenString(String str) {
		ArrayList<String> result = new ArrayList<>();
		permutate(str, 0, result);
		Collections.sort(result);
		result.stream().forEach(i -> System.out.print(i + " "));
	}

	public static void permutate(String str, int start, ArrayList<String> result) {
		if (start < str.length()) {
			for (int i = start; i < str.length(); i++) {
				str = swap(str, start, i);
				permutate(str, start + 1, result);
				str = swap(str, start, i);
			}
		} else {
			result.add(str);
		}
	}

	public static String swap(String str, int index1, int index2) {
		char[] array = str.toCharArray();
		char temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return new String(array);
	}

	public static void checkAnagram(String str1, String str2) {
		if (str1 != null && str2 != null && str1.length() == str2.length()) {
			int[] array1 = new int[26];
			int[] array2 = new int[26];
			for (int i = 0; i < str1.length(); i++) {
				array1[(int) (str1.charAt(i) - 'a')]++;
				array2[(int) (str2.charAt(i) - 'a')]++;
			}
			boolean flag = true;
			for (int i = 0; i < str1.length(); i++) {
				char ch = str1.charAt(i);
				if (array1[(int) (ch - 'a')] != array2[(int) (ch - 'a')]) {
					flag = false;
					break;
				}
			}
			if (flag)
				System.out.println("YES");
			else
				System.out.println("NO");
		} else {
			System.out.println("NO");
		}
	}

	public void testCheckBalancedParantheses(Scanner in) {
		int n = in.nextInt();
		while (n-- > 0) {
			String str = in.next();
			checkBalancedParantheses(str);
		}
	}

	public void testReverseWords(Scanner in) {
		int n = in.nextInt();
		while (n-- > 0) {
			String str = in.next();
			reverseWords(str);
		}
	}

	public void testPermutationOfString(Scanner in) {
		int n = in.nextInt();
		while (n-- > 0) {
			String str = in.next();
			permutationOfGivenString(str);
			System.out.println();
		}
	}

	public void testCheckAnagram(Scanner in) {
		int n = in.nextInt();
		while (n-- > 0) {
			String str1 = in.next();
			String str2 = in.next();
			checkAnagram(str1, str2);
		}
	}

	// Using HashSet: TimeComplexity-O(n)
	public static void removeDuplicates1(String str) {
		char[] arr = str.toCharArray();
		Set<Character> set = new HashSet<>();
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!set.contains(arr[i])) {
				set.add(arr[i]);
				arr[index++] = arr[i];
			}
		}

		for (int i = 0; i < index; i++)
			System.out.print(arr[i] + "");
		System.out.println();
	}

	// Using LinkedHashSet: TimeComplexity-O(n)
	public static void removeDuplicates2(String str) {
		char[] arr = str.toCharArray();
		Set<Character> set = new LinkedHashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (!set.contains(arr[i])) {
				set.add(arr[i]);
			}
		}
		set.stream().forEach(System.out::print);
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		B_StringProblems ob = new B_StringProblems();
		Scanner in = new Scanner(System.in);
		// ob.testCheckBalancedParantheses(in);
		// ob.testReverseWords(in);
		// ob.testPermutationOfString(in);
		// ob.testCheckAnagram(in);
		// removeDuplicates(str.nextToken(""));
		removeDuplicates2("geeks for geeks");
	}
}
