package com.practice.basics.strings;

/*
 * String class EQUALS functionality:
 * Usage of equals method: equals() explains about comparison b/w two different Strings
 * Difference b/w equals & ==
 * equals/equalsIgnoreCase -> compares the value present in the string variable
 * == -> compares the reference of string variable
 */
public class StringEquals {
	public static void main(String[] args) {
		String s1 = "Welcome";
		String s2 = "Welcome";
		String s3 = new String("Welcome");

		System.out.print("\n\t (s1==s2) is : " + (s1 == s2));
		System.out.print("\n\t (s1.equals(s2)) is : " + (s1.equals(s2)));
		System.out.print("\n\t (s1.equalsIgnoreCase(s2)) is : " + (s1.equalsIgnoreCase(s2)));

		System.out.print("\n\t (s1==s3) is : " + (s1 == s3)); // false
		System.out.print("\n\t (s1.equals(s3)) is : " + (s1.equals(s3))); // true
		System.out.print("\n\t (s1.equalsIgnoreCase(s3)) is : " + (s1.equalsIgnoreCase(s3)));
	}
}
