package com.practice.declartive.regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSample {

	public static void main(String[] args) {

		// 1. simple regular expression
		diffApproachForRegEx();
		// 2. RegEx char class
		charClassRegEx();
		// 3. Regex Quantifiers
		testQuantifier();
		// 4.Meta Characters
		metacharacters();
		// 5. commonSamples
		commonSamples1();
		commonSamples2();
		commonSamples3();
	}

	public static void diffApproachForRegEx() {
		System.out.println("Different way of validating Pattern:");
		// 1st way
		Pattern p = Pattern.compile(".*");
		Matcher m = p.matcher("as");
		System.out.println("1st way: " + m.matches());

		// 2nd way
		System.out.println("2nd way: " + Pattern.compile(".").matcher("as").matches());

		// 3rd way
		System.out.println("3rd way: " + Pattern.matches(".", "as"));
	}

	public static void charClassRegEx() {
		System.out.println("RegEx Character Class:");
		System.out.println(Pattern.matches("[amn]", "abcd"));// false (not a or m or n)
		System.out.println(Pattern.matches("[amn]", "a"));// true (among a or m or n)
		System.out.println(Pattern.matches("[amn]", "amn"));// false (among a or m or n)
		System.out.println(Pattern.matches("[amn]", "ammmna"));// false (m and a comes more than once)

		System.out.println(Pattern.matches("[^amn]", "1"));// true (Any character except a or m or n)

		System.out.println(Pattern.matches("[a-zA-Z]", "s")); // true (a through z or A through Z, inclusive (range))
		System.out.println(Pattern.matches("[a-zA-Z]", "fsfs")); // false (it accepts only one char)

		System.out.println(Pattern.matches("[a-d[M-P]]", "O")); // true (a through d, or m through p: [a-dm-p] (union))
		System.out.println(Pattern.matches("[a-d[M-P]]", "f")); // false

		System.out.println(Pattern.matches("[a-z&&[def]]", "f")); // true (d, e, or f (intersection))
		System.out.println(Pattern.matches("[a-z&&[^bc]]", "b"));// false (a through z, except for b and c: [ad-z] (subtraction))
		System.out.println(Pattern.matches("[a-z&&[^m-p]]", "m"));// false (a through z, and not m through p: [a-lq-z](subtraction))
	}

	public static void testQuantifier() {
		System.out.println("Quantifier Samples:");
		System.out.println("? quantifier ....");
		System.out.println(Pattern.matches("[amn]?", "a"));// true (a or m or n comes one time)
		System.out.println(Pattern.matches("[amn]?", "amn"));// false (a comes more than one time)

		System.out.println("+ quantifier ....");
		System.out.println(Pattern.matches("[amn]+", "a"));// true (a or m or n once or more times)
		System.out.println(Pattern.matches("[amn]+", "aammmnn"));// true (a or m or n comes more than once)
		System.out.println(Pattern.matches("[amn]+", "amzzta"));// false (z and t are not matching pattern)

		System.out.println("n quantifier ....");
		System.out.println(Pattern.matches("[amn]{5}", "aammmnn"));// false (X occurs 5 times only)
		System.out.println(Pattern.matches("[amn]{3,8}", "a"));// false (X occurs at least 3 times but less than 8 times)

		System.out.println("* quantifier ....");
		System.out.println(Pattern.matches("[amn]*", "ammfmna"));// true (a or m or n may come zero or more times)
	}

	public static void metacharacters() {
		System.out.println("metacharacters d....");// \\d means digit

		System.out.println(Pattern.matches("\\d", "abc"));// false (non-digit)
		System.out.println(Pattern.matches("\\d", "1"));// true (digit and comes once)
		System.out.println(Pattern.matches("\\d", "4443"));// false (digit but comes more than once)
		System.out.println(Pattern.matches("\\d", "323abc"));// false (digit and char)

		System.out.println("metacharacters D....");// \\D means non-digit

		System.out.println(Pattern.matches("\\D", "abc"));// false (non-digit but comes more than once)
		System.out.println(Pattern.matches("\\D", "1"));// false (digit)
		System.out.println(Pattern.matches("\\D", "4443"));// false (digit)
		System.out.println(Pattern.matches("\\D", "323abc"));// false (digit and char)
		System.out.println(Pattern.matches("\\D", "m"));// true (non-digit and comes once)

		System.out.println("metacharacters D with quantifier....");
		System.out.println(Pattern.matches("\\D*", "mak"));// true (non-digit and may come 0 or more times)
	}

	public static void commonSamples1() {
		System.out.println(Pattern.matches("[a-zA-Z0-9]{6}", "arun32"));// true
		System.out.println(Pattern.matches("[a-zA-Z0-9]{6}", "kkvarun32"));// false (more than 6 char)
		System.out.println(Pattern.matches("[a-zA-Z0-9]{6}", "JA2Uk2"));// true
		System.out.println(Pattern.matches("[a-zA-Z0-9]{6}", "arun$2"));// false ($ is not matched)
	}

	public static void commonSamples2() {
		// RegEx: input should contains one or more digit
		System.out.println("Example 1:");
		String input = "This order was placed for QT3000! OK?";
		String pattern = "(.*)(\\d+)(.*)";
		findPattern(pattern, input);

		input = " Derek Banas CA 12345 PA (412)555-1212 john_smith@gmail.com";
		// RegEx: Find the word in the input
		System.out.println("\nExample 2:");
		findPattern("\\s[A-Za-z]{2,20}\\s", input);

		// RegEx: Find the digits
		System.out.println("\nExample 3:");
		findPattern("\\s\\d{5}\\s", input);

		// RegEx: Find the digits
		System.out.println("\nExample 4:");
		findPattern("[a-zA-Z0-9._%-]+@+gmail+\\.[A-Za-z]{2,4}", input);
	}

	public static void commonSamples3() {
		System.out.println("by character classes and quantifiers ...");
		System.out.println(Pattern.matches("[789]{1}[0-9]{9}", "9953038949"));// true
		System.out.println(Pattern.matches("[789][0-9]{9}", "9953038949"));// true

		System.out.println(Pattern.matches("[789][0-9]{9}", "99530389490"));// false (11 characters)
		System.out.println(Pattern.matches("[789][0-9]{9}", "6953038949"));// false (starts from 6)
		System.out.println(Pattern.matches("[789][0-9]{9}", "8853038949"));// true

		System.out.println("by metacharacters ...");
		System.out.println(Pattern.matches("[789]{1}\\d{9}", "8853038949"));// true
		System.out.println(Pattern.matches("[789]{1}\\d{9}", "3853038949"));// false (starts from 3)
	}

	public static void regExHackersRankProb() {
		String namePattern = "[a-z]{1,20}";
		String emailPattern = "[a-z.]{1,40}+@gmail+.[a-z]{3}";
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		List<String> data = new ArrayList<>();
		for (int a0 = 0; a0 < N; a0++) {
			String firstName = in.next();
			String emailID = in.next();
			if (isValidInput(emailPattern, emailID) && isValidInput(namePattern, firstName)) {
				data.add(firstName);
			}
		}
		Collections.sort(data);
		data.forEach(System.out::println);
		in.close();
	}

	public static boolean isValidInput(String regEx, String input) {
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static void findPattern(String pattern, String input) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		while (m.find()) {

			if (m.group().length() != 0) {
				System.out.println(m.group().trim());
				// System.out.println(m.matches());
			}
			System.out.println("Start Index:" + m.start());
			System.out.println("End Index:" + m.end());
		}
	}

}
