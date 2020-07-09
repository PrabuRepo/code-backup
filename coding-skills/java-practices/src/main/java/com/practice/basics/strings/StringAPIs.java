package com.practice.basics.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.StringJoiner;

/*
 * String represents sequence of characters enclosed within the double quotes.In many languages, strings are treated as character 
 * arrays. But In java, strings are treated as objects. To create and manipulate the strings, Java provides three classes.
 *  1) java.lang.String        (From JDK 1.0)
 *  2) java.lang.StringBuffer  (From JDK 1.5)
 *  3) java.lang.StringBuilder (From JDK 1.5)
 * 
 * Important Points:
 *  1.All these three classes are members of java.lang package and they are final classes. That means you can’t create subclasses to 
 *    these three classes.
 *  2.All three classes implement Serializable and CharSequence interface.
 *  3.String objects are immutable in java. Where as StringBuffer and StringBuilder objects are mutable.That means, you can perform 
 *    modifications to existing objects.
 *  4.Only String and StringBuffer objects are thread safe. StringBuilder objects are not thread safe.
 *  5.In all three classes, toString() method is overrided. So. whenever you use reference variables of these three types, they will
 *    return contents of the objects not physical address of the objects.
 *  6.hashCode() and equals() methods are overrided only in String class but not in StringBuffer and StringBuilder classes.
 *  7.There is no reverse() and delete() methods in String class. But,StringBuffer and StringBuilder have reverse() and delete() methods.
 *  8. In case of String class, you can create the objects without new operator. But in case of StringBuffer and StringBuilder class,
 *     you have to use new operator to create the objects.
 */
public class StringAPIs {
	public static void main(String[] args) {

		/********** String Concepts ************/
		stringConstructors();

		stringLiterals();

		stringLength();

		stringConcatenations();

		characterExtraction();

		/********** String API's ************/
		stringAPIs();

		findLargestDigit();

		characterCount("abcbbdef");

		duplicateCharCount("abcbbdef");

		stringToInteger();

		integerToString();

		stringJoiner();

		charValues();

		charAPI();

		// Other String manipulations
		reverseString1();
		reverseString2();
		reverseString3();
		reverseString4();
		reverseString5();
	}

	/*
	 * There are total 13 constructors in java.lang.String class. It provides many ways to create the string objects.
	 */
	public static void stringConstructors() {
		// If you want to create an empty string object, then use no-arg constructor of String class.
		String s1 = new String();

		// constructor takes character array as an argument.
		char[] chars = { 'J', 'A', 'V', 'A' };
		String s2 = new String(chars);

		// constructor takes string as an argument
		String s3 = new String("JAVA");

		// constructor takes StringBuffer type as an argument.
		StringBuffer strBuff = new StringBuffer("abc");
		String s4 = new String(strBuff);

		// constructor takes StringBuilder type as an argument.
		StringBuilder strBldr = new StringBuilder("abc");
		String s5 = new String(strBldr);
	}

	/*
	 * In Java, all string literals like “java”, “abc”, “123” are treated as objects of java.lang.String class. That means,
	 * all methods of String class are also applicable to string literals.
	 */
	public static void stringLiterals() {
		String s1 = "abc";

		String s2 = "abc" + "def";

		String s3 = "123" + "A" + "B";

		System.out.println(s1);

		System.out.println(s2);

		System.out.println(s3);
	}

	public static void stringLength() {
		// Length: length() method of String class is used to find the length of the string
		System.out.println("Find the length of the string: ");
		String s01 = new String("abc"); // or s = "abc";
		System.out.println(s01.length());
	}

	public static void stringConcatenations() {
		// Concatenation: “+” operator is used to concatenate two or more string objects or string literals.
		System.out.println("String concatenation using + operator: ");
		String s02 = "Java" + "Concept" + "Of" + "The" + "Day";
		System.out.println(s02);
		String s1 = new String("Java");
		String s2 = new String("Concept");
		String s3 = new String("Of");
		String s4 = new String("The");
		String s5 = new String("Day");
		// Concatenating five string objects using "+" operator
		System.out.println(s1 + s2 + s3 + s4 + s5);
		System.out.println("String concatenation with other types: ");
		int i = 5000;
		double d = 6000.0006;
		String s03 = "Java";
		System.out.println(i + d + s03);
		// Null concatenations
		String s001 = null;
		String s002 = null;
		System.out.println(s001 + s002);       // Output : nullnull
		System.out.println("null" + "null");    // Output : nullnull
		System.out.println(s001 + "JAVA" + s002);     // Output : nullJAVAnull
		// System.out.println(null+null); //Compile Time Error

		System.out.println("String concatenation using concat() method: ");
		/*Restrictions in concat method: 
		 * - But using concat() method, we can concatenate only two string objects. It is not possible to concatenate more than 
		 *   two string objects using concat() method.
		 * - And also using concat() method we can’t concatenate a string object with other type of object. Because, concat() 
		 *   method takes only String type as an argument.
		 */
		String s04 = "JAVA";
		String s05 = "J2EE";
		System.out.println(s04.concat(s05));
	}

	public static void characterExtraction() {
		// 1.charAt() Method: This method returns character at the specified index.
		String s = "Java Concept Of The Day";
		System.out.println(s.charAt(5));      // Output : C
		System.out.println(s.charAt(10));     // Output : p
		System.out.println(s.charAt(25));     // This statement will throw StringIndexOutOfBoundsException

		/* 2. getChars() Method:
		 *  This method copies the set of characters from the string into specified character array. 
		 * Signature:   public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
		 */
		char[] dst = new char[10];// Defining destination char array
		s.getChars(5, 11, dst, 2);// Copying the set of characters from s into dst.
		for (char c : dst)
			System.out.print(c);       // Output : --Concep--

		// 3.This method converts whole string into a character array.
		char[] arr = s.toCharArray();
		for (char c : arr)
			System.out.print(c);     // Output : Java Concept Of The Day

		/*4.subString() Method:
		 * public String substring(int beginIndex) –> 
		 *     This form returns sub string starting from ‘beginIndex’ to the end of the specified string.
		 * public String substring(int beginIndex, int endIndex) –> 
		 *     This form returns sub string starting from ‘beginIndex’ to ‘endIndex’ of the specified string.
		 */
		String subString1 = s.substring(11);
		System.out.println(subString1);           // Output : t Of The Day
		String subString2 = s.substring(5, 15);
		System.out.println(subString2);         // Output : Concept Of
	}

	public static void stringAPIs() {

		String s = "GeeksforGeeks";
		// or String s= new String ("GeeksforGeeks");

		// Returns the number of characters in the String.
		System.out.println("String length = " + s.length());

		// Returns the character at ith index.
		System.out.println("Character at 3rd position = " + s.charAt(3));

		// Return the substring from the ith index character to end of string
		System.out.println("Substring " + s.substring(3));

		// Returns the substring from i to j-1 index.
		System.out.println("Substring  = " + s.substring(2, 5));

		// Concatenates string2 to the end of string1.
		String s1 = "Geeks";
		String s2 = "forGeeks";
		System.out.println("Concatenated string  = " + s1.concat(s2));

		// Returns the index within the string of the first occurrence of the specified string.
		String s4 = "Learn Share Learn";
		System.out.println("Index of Share " + s4.indexOf("Share"));

		// Returns the index within the string of the first occurrence of the specified string, starting at the specified index.
		System.out.println("Index of a  = " + s4.indexOf('a', 3));

		// Checking equality of Strings
		Boolean out = "Geeks".equals("geeks");
		System.out.println("Checking Equality  " + out);
		out = "Geeks".equals("Geeks");
		System.out.println("Checking Equality  " + out);

		out = "Geeks".equalsIgnoreCase("gEeks ");
		System.out.println("Checking Equality" + out);

		int out1 = s1.compareTo(s2);
		System.out.println("If s1 = s2" + out);

		// Converting cases
		String word1 = "GeeKyMe";
		System.out.println("Changing to lower Case " + word1.toLowerCase());

		// Converting cases
		String word2 = "GeekyME";
		System.out.println("Changing to UPPER Case " + word1.toUpperCase());

		// Trimming the word
		String word4 = " Learn Share Learn ";
		System.out.println("Trim the word " + word4.trim());

		// Replacing characters
		String str1 = "feeksforfeeks";
		System.out.println("Original String " + str1);
		String str2 = "feeksforfeeks".replace('f', 'g');
		System.out.println("Replaced f with g -> " + str2);
	}

	public static void searchChars() {
		// This is a string in which a character to be searched.
		String str = "GeeksforGeeks is a computer science portal";

		// Returns index of first occurrence of character.
		int firstIndex = str.indexOf('s');
		System.out.println("First occurrence of char 's'" + " is found at : " + firstIndex);

		// Returns index of last occurrence specified character.
		int lastIndex = str.lastIndexOf('s');
		System.out.println("Last occurrence of char 's' is" + " found at : " + lastIndex);

		// Index of the first occurrence of specified char after the specified index if found.
		int first_in = str.indexOf('s', 10);
		System.out.println("First occurrence of char 's'" + " after index 10 : " + first_in);

		int last_in = str.lastIndexOf('s', 20);
		System.out.println("Last occurrence of char 's'" + " after index 20 is : " + last_in);

		// gives ASCII value of character at location 20
		int char_at = str.charAt(20);
		System.out.println("Character at location 20: " + char_at);

		// throws StringIndexOutOfBoundsException
		// char_at = str.charAt(50);
	}

	public void searchSubString() {
		// This is a string in which a substring is to be searched.
		String str = "GeeksforGeeks is a computer science portal";

		// Returns index of first occurrence of substring
		int firstIndex = str.indexOf("Geeks");
		System.out.println("First occurrence of char Geeks" + " is found at : " + firstIndex);

		// Returns index of last occurrence
		int lastIndex = str.lastIndexOf("Geeks");
		System.out.println("Last occurrence of char Geeks is" + " found at : " + lastIndex);

		// Index of the first occurrence after the specified index if found.
		int first_in = str.indexOf("Geeks", 10);
		System.out.println("First occurrence of char Geeks" + " after index 10 : " + first_in);

		int last_in = str.lastIndexOf("Geeks", 20);
		System.out.println("Last occurrence of char Geeks " + "after index 20 is : " + last_in);
	}

	public void searchCharSeq() {
		// This is a string in which substring to be searched.
		String test = "software";

		CharSequence seq = "soft";
		boolean bool = test.contains(seq);
		System.out.println("Found soft?: " + bool);

		// it returns true substring if found.
		boolean seqFound = test.contains("war");
		System.out.println("Found war? " + seqFound);

		// it returns true substring if found otherwise
		// return false.
		boolean sqFound = test.contains("wr");
		System.out.println("Found wr?: " + sqFound);
	}

	public void stringMAtchStartAndEnd() {
		// This is a string in which substring is to be searched.
		String str = "GeeksforGeeks is a computer science portal";

		System.out.println(str.startsWith("Geek"));
		System.out.println(str.startsWith("is", 14));
		System.out.println(str.endsWith("port"));
	}

	// Find Largest Number Less Than Given Number And Without A Given DIgit
	public static void findLargestDigit() {
		System.out.println(getLLessThanN(123, 2));
		System.out.println(getLLessThanN(4582, 5));
		System.out.println(getLLessThanN(98512, 5));
		System.out.println(getLLessThanN(548624, 8));
	}

	private static int getLLessThanN(int number, int digit) {
		// Converting digit to char
		char c = Integer.toString(digit).charAt(0);

		// Decrementing number & checking whether it contains digit
		for (int i = number; i > 0; --i) {
			if (Integer.toString(i).indexOf(c) == -1)
				return i; // If 'i' doesn't contain 'c'
		}

		return -1;
	}

	// How To Count Occurrences Of Each Character In String In Java?
	public static void characterCount(String inputString) {
		// Creating a HashMap containing char as a key and occurrences as a value
		HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();

		// Converting given string to char array
		char[] strArray = inputString.toCharArray();

		// checking each char of strArray
		for (char c : strArray) {
			if (charCountMap.containsKey(c)) {
				// If char is present in charCountMap, incrementing it's count by 1
				charCountMap.put(c, charCountMap.get(c) + 1);
			} else {
				// If char is not present in charCountMap,
				// putting this char to charCountMap with 1 as it's value
				charCountMap.put(c, 1);
			}
		}

		// Printing the charCountMap
		System.out.println(charCountMap);
	}

	// Program To Find Duplicate Characters In A String
	public static void duplicateCharCount(String inputString) {
		// Creating a HashMap containing char as key and it's occurrences as value
		HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();

		// Converting given string to char array
		char[] strArray = inputString.toCharArray();

		// checking each char of strArray
		for (char c : strArray) {
			if (charCountMap.containsKey(c)) {
				// If char is present in charCountMap, incrementing it's count by 1
				charCountMap.put(c, charCountMap.get(c) + 1);
			} else {
				// If char is not present in charCountMap,
				// putting this char to charCountMap with 1 as it's value
				charCountMap.put(c, 1);
			}
		}
		// Getting a Set containing all keys of charCountMap
		Set<Character> charsInString = charCountMap.keySet();

		System.out.println("Duplicate Characters In " + inputString);

		// Iterating through Set 'charsInString'
		for (Character ch : charsInString) {
			if (charCountMap.get(ch) > 1) {
				// If any char has a count of more than 1, printing it's count
				System.out.println(ch + " : " + charCountMap.get(ch));
			}
		}
	}

	// String to Integer
	public static void stringToInteger() {
		String s = "2015";
		int i = Integer.parseInt(s);
		System.out.println(i);

		i = Integer.valueOf(s);
		System.out.println(i);
	}

	public static void integerToString() {
		int i = 2015;
		String s = Integer.toString(i);
		System.out.println(s);

		i = 2015;
		s = String.valueOf(i);
		System.out.println(s);
	}

	public static void stringJoiner() {
		String[] a = new String[] { "first", "second", "third" };
		StringJoiner sj = new StringJoiner(",");
		for (String s : a)
			sj.add(s);
		System.out.println(sj); // first,second,third
	}

	public static void charValues() {
		System.out.println("a- " + (int) ('a'));
		System.out.println("z- " + (int) ('z'));
		System.out.println("A- " + (int) ('A'));
		System.out.println("Z- " + (int) ('Z'));
	}

	public static void charAPI() {
		char ch = 'a';
		Character.isDigit(ch);
	}

	public static void reverseString1() {
		String input = "GeeksforGeeks";

		// getBytes() method to convert string into bytes[].
		byte[] strAsByteArray = input.getBytes();

		byte[] result = new byte[strAsByteArray.length];

		// Store result in reverse order into the result byte[]
		for (int i = 0; i < strAsByteArray.length; i++)
			result[i] = strAsByteArray[strAsByteArray.length - i - 1];

		System.out.println(new String(result));
	}

	public static void reverseString2() {
		String input = "Geeks for Geeks";
		StringBuilder input1 = new StringBuilder();
		// append a string into StringBuilder input1
		input1.append(input);

		// reverse StringBuilder input1
		input1 = input1.reverse();

		// print reversed String
		System.out.println(input1);
	}

	public static void reverseString3() {
		String input = "GeeksForGeeks";
		// convert String to character array by using toCharArray
		char[] try1 = input.toCharArray();

		for (int i = try1.length - 1; i >= 0; i--)
			System.out.print(try1[i]);
	}

	public static void reverseString4() {
		String input = "Geeks For Geeks";
		char[] temparray = input.toCharArray();
		int left, right = 0;
		right = temparray.length - 1;

		for (left = 0; left < right; left++, right--) {
			// Swap values of left and right
			char temp = temparray[left];
			temparray[left] = temparray[right];
			temparray[right] = temp;
		}

		for (char c : temparray)
			System.out.print(c);
		System.out.println();
	}

	public static void reverseString5() {
		String input = "Geeks For Geeks";
		char[] hello = input.toCharArray();
		List<Character> trial1 = new ArrayList<>();

		for (char c : hello)
			trial1.add(c);

		Collections.reverse(trial1);
		ListIterator<Character> li = trial1.listIterator();
		while (li.hasNext())
			System.out.print(li.next());
	}

}
