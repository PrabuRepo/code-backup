package com.basic.datastructures.java.api;

import java.util.Arrays;
import java.util.List;

/*
 * Practice String/StringBuilder/StringBuffer APIs
 */
public class StringAPI {

	public static void main(String[] args) {
		StringAPI ob = new StringAPI();

		// String:
		ob.StringComparisonAPIs();

		ob.stringConversionAPIs();

		ob.StringGetAPIs();

		ob.stringIndexAPIs();

		ob.StringRegexAPIs();

		// String Builder:

		ob.stringBuiderAPIs();
	}

	// StringBuilder Vs StringBuffer API:
	public void stringAPIs() {
		String str = "Practice", str2 = "Test";
		System.out.println("charAt: " + str.charAt(2));
		System.out.println("compareTo: " + str.compareTo(str2));
		System.out.println("compareToIgnoreCase: " + str.compareToIgnoreCase(str2));
		System.out.println("equals: " + str.equals(str2));
		System.out.println("equalsIgnoreCase: " + str.equalsIgnoreCase(str2));
		System.out.println("==" + str == str2);
		System.out.println("endsWith: " + str.endsWith("ce"));
		System.out.println("startsWith: " + str.startsWith("Pr"));
		System.out.println("length: " + str.length());
		System.out.println("concat: " + str.concat(str2));
		System.out.println("join: " + String.join("/", "25", "06", "2018")); // Str join with given delimiter
		System.out.println("contains: " + str.contains("ic"));
		System.out.println("indexOf substring: " + str.indexOf("ri"));
		System.out.println("indexOf substring from given index: " + str.indexOf("ra", 1));
		System.out.println("indexOf char: " + str.indexOf('r'));
		System.out.println("indexOf char from given index: " + str.indexOf('i', 2));
		System.out.println("lastIndexOf substring: " + str.lastIndexOf("ri"));
		System.out.println("lastIndexOf substring from given index: " + str.lastIndexOf("ra", 1));
		System.out.println("lastIndexOf char: " + str.lastIndexOf('r'));
		System.out.println("lastIndexOf char from given index: " + str.lastIndexOf('i', 2));
		System.out.println("replace char: " + str.replace('c', 'e'));
		System.out.println("replace charseq: " + str.replace("ic", "na"));
		System.out.println("replace Regex: " + str.replaceAll("\\s", "")); // Remove all the spaces in the string
		System.out.println("split: " + str.split(" ")); // Split based on regex
		System.out.println("matches: " + str.matches("\\s")); // regex matches
		System.out.println("substring from index: " + str.substring(2));
		System.out.println("substring from - to index: " + str.substring(2, 5));
		System.out.println("substring from - to index: " + str.subSequence(2, 5));
		System.out.println("toCharArray: " + Arrays.toString(str.toCharArray()));
		System.out.println("valueOf: " + String.valueOf("10")); // valueOf is static method in String class
		System.out.println("toUpperCase: " + str.toUpperCase());
		System.out.println("toLowerCase: " + str.toLowerCase());
	}

	public void generalStringInfo() {
		/*
		 * Fast Facts:
		 * 	Mutable? No
		 * 	Primitive? No
		 * 	Comparison: s1.equals(s2)
		 * 	Access the ith character: s1.charAt(i)
		 * 	ASCII – 7bits char set; 128 chars (0-127)
		 * 	Extended ASCII – 8 bits char set; 256 chars (0-255)
		 * convert between strings and integers: adding and subtracting different characters from each other. 
		 * 	ASCII characters are sequential. Therefore, we can guarantee that '5' - '0' = 5 or 'd' - 'a' = 3.
		 */

	}

	public void StringComparisonAPIs() {
		String str = "Practice", str2 = "Test";
		System.out.println("compareTo: " + str.compareTo(str2));
		System.out.println("compareToIgnoreCase: " + str.compareToIgnoreCase(str2));
		/* equals Vs '==' : equals is preferable for string comparison; bcoz it compares values in the string; But == compares memory
		   address*/
		System.out.println("equals: " + str.equals(str2));
		System.out.println("equalsIgnoreCase: " + str.equalsIgnoreCase(str2));
		System.out.println("==" + str == str2);
		System.out.println("endsWith: " + str.endsWith("ce"));
		System.out.println("startsWith: " + str.startsWith("Pr"));
		System.out.println("contains: " + str.contains("ic"));
	}

	// Get substring, char or subsequence API
	public void StringGetAPIs() {
		String str = "Practice";
		System.out.println("charAt: " + str.charAt(2));
		System.out.println("ASCII Value: " + (int) str.charAt(2));
		System.out.println("substring from index: " + str.substring(2)); // index - 2 to length of string
		System.out.println("substring from - to index: " + str.substring(2, 5)); // index - 2 to 4(inclusive of 2 and
		// exclusive of 5)
		System.out.println("subSequence from - to index: " + str.subSequence(2, 5));
		System.out.println("concat: " + str.concat("Test"));
	}

	public void stringIndexAPIs() {
		String str = "Practice";
		System.out.println("indexOf substring: " + str.indexOf("ri"));
		System.out.println("indexOf substring from given index: " + str.indexOf("ra", 1));
		System.out.println("indexOf char: " + str.indexOf('r'));
		System.out.println("indexOf char from given index: " + str.indexOf('i', 2));
		System.out.println("lastIndexOf substring: " + str.lastIndexOf("ri"));
		System.out.println("lastIndexOf substring from given index: " + str.lastIndexOf("ra", 1));
		System.out.println("lastIndexOf char: " + str.lastIndexOf('r'));
		System.out.println("lastIndexOf char from given index: " + str.lastIndexOf('i', 2));
		System.out.println("length: " + str.length());
	}

	// String Conversion API's
	public void stringConversionAPIs() {
		String str = "Practice";
		System.out.println("toCharArray: " + Arrays.toString(str.toCharArray()));
		System.out.println("valueOf: " + String.valueOf("10")); // valueOf is static method in String class
		System.out.println("toUpperCase: " + str.toUpperCase());
		System.out.println("toLowerCase: " + str.toLowerCase());

		System.out.println("String Array to List Conversion: ");
		List<String> alphabets = Arrays.asList(new String[] { "a", "b", "b", "d" });
		alphabets.stream().forEach(i -> System.out.print(i + " "));

	}

	public void StringRegexAPIs() {
		String str = "Practice";
		System.out.println("replace char: " + str.replace('c', 'e'));
		System.out.println("replace charseq: " + str.replace("ic", "na"));
		System.out.println("replace Regex: " + str.replaceAll("\\s", "")); // Remove all the spaces in the string
		System.out.println("split: " + str.split(" ")); // Split based on regex
		System.out.println("matches: " + str.matches("\\s")); // regex matches
		System.out.println("join: " + String.join("/", "25", "06", "2018")); // Str join with given delimiter
	}

	public void stringBuiderAPIs() {
		StringBuilder sb = new StringBuilder("Practice");
		String str = "Test";
		// Add
		sb = new StringBuilder("Practice");
		System.out.println("append: " + sb.append(str));
		System.out.println("append char array: " + sb.append(new char[] { 'a', 'b', 'c' }));

		// Insert
		sb = new StringBuilder("Practice");
		System.out.println("insert char: " + sb.insert(2, 'z'));
		System.out.println("insert string: " + sb.insert(4, "xyz"));
		System.out.println("insert char array: " + sb.insert(8, new char[] { 'a', 'b', 'c' }));

		// Update
		sb = new StringBuilder("Practice");
		sb.setCharAt(1, 'm'); // void method
		System.out.println("setCharAt: " + sb.toString());
		System.out.println("replace char from - to index: " + sb.replace(1, 3, "ss"));

		// Delete
		sb = new StringBuilder("Practice");
		System.out.println("deleteCharAt: " + sb.deleteCharAt(3));
		System.out.println("delete from - to index: " + sb.delete(2, 5));

		sb = new StringBuilder("Practice");
		System.out.println("reverse: " + sb.reverse());
	}

}