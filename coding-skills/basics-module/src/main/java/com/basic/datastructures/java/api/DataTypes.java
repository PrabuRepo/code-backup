package com.basic.datastructures.java.api;

import java.util.Arrays;

public class DataTypes {

	public static void main(String[] args) {

		// TODO: Wrapper Class API: Integer API's, Character APIs

		characterAPI();

		integerAPI();

		charConversions();
	}

	public void dataTypesInfo() {
		/*
		  Java Data Types:
		  ****************
			1.byte:  8-bit signed two's complement integer. Range(Min: -128(2^7); Max: 127(2^7-1) (inclusive)).
			2.short: 16-bit signed two's complement integer. Range(Min: -32,768(2^15); Max: 32,767(2^15-1) (inclusive)).
			3.int: By default, the int data type is a 32-bit signed two's complement integer. Range(Min:-2^31; Max:2^31-1). 
			  In Java SE 8 and later, you can use the int data type to represent an unsigned 32-bit. Range(Min:0; Max:2^32-1). Use the Integer class to use int data type as an unsigned integer.
			4.long: 64-bit two's complement integer. Range(Min:-2^63; Max:2^63-1). 
			 In Java SE 8 and later, you can use the long data type to represent an unsigned 64-bit long. Range(Min:0; Max:2^64-1). 
			5.float: a single-precision 32-bit IEEE 754 floating point.  Range: ???
			6.double: a double-precision 64-bit IEEE 754 floating point. Range: ??? 
			7.boolean: The boolean data type has only two possible values: true and false. This data type represents one bit of information, but its "size" isn't something that's precisely defined.
			8.char: a single 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).
						128 Ascii chars & 256 Extended Ascii chars 
			String: In addition to the eight primitive data types listed above, the Java programming language also provides special support for character strings 
			via the java.lang.String class. 
		*/
	}

	public static void characterAPI() {
		Character.isDigit('1');
		Character.isLetter('A');
		Character.isAlphabetic('A');
		Character.isLetterOrDigit('3');
		Character.isLowerCase('a');
		Character.isUpperCase('A');
		Character.isWhitespace(' ');
		char ch = Character.toLowerCase('A');
		ch = Character.toUpperCase('a');
		String str = Character.toString(ch);
		System.out.println(str);
	}

	/* Note: character accepts 0 to 65535(16 bit: 2^16-1)
	 *       128 ASCII chars, 256 Extended Ascii chars
	 */
	public static void charConversions() {
		char ch = ' ';

		System.out.println(
				"To find starting number of alphabet: ");
		ch = 'a';
		System.out.println((int) ch);

		ch = 'A';
		System.out.println((int) ch);

		System.out.println("Alphabet Uppercase: ");
		int upperCase = 65;
		for (int i = upperCase; i <= upperCase + 25; i++) {
			System.out.print((char) i + " ");
		}

		System.out.println("\nAlphabet Lowercase: ");
		int lowerCase = 97;
		for (int i = lowerCase; i <= lowerCase + 25; i++) {
			System.out.print((char) i + " ");
		}

		System.out.println(
				"\nLower to Uppercase conversion: ");
		lowerCase = 97;
		for (int i = lowerCase; i <= lowerCase + 25; i++) {
			ch = (char) (i - 32); // 97-65 = 32
			System.out.print((char) ch + " ");
		}

		System.out.println(
				"\nUpper to Lowercase conversion: ");
		upperCase = 65;
		for (int i = upperCase; i <= upperCase + 25; i++) {
			ch = (char) (i + 32); // 65+32 = 97
			System.out.print(ch + " ");
		}

		System.out.println("\nAssigning char to int: ");
		ch = '8';
		int num = ch;
		System.out.println("Before: " + num);
		num = ch - '0';
		System.out.println("After: " + num);

		System.out.println(
				"Reading number from Numeric String: ");
		String str = "836212";
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			System.out.print(val + "->");

			val = str.charAt(i) - '0';
			System.out.print(val + " ");
		}

		System.out.println(
				"\nSegregate number from Alphanumeric string: ");
		str = "5dsa836d21-220";
		int n = str.length();
		for (int i = 0; i < n; i++) {
			ch = str.charAt(i);
			if (!Character.isDigit(ch))
				continue;

			int startIndex = i;
			while (i + 1 < n
					&& Character.isDigit(str.charAt(i + 1)))
				i++;
			int val = Integer.valueOf(
					str.substring(startIndex, i + 1));
			System.out.print(val + ", ");
		}

		System.out.println(
				"\nStore lowercase letter to int array: ");
		int[] arr = new int[26];
		Arrays.fill(arr, 0);
		str = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i) - 'a';
			arr[index]++;
		}
		System.out.println(Arrays.toString(arr));

		System.out.println(
				"Store uppercase letter to int array: ");
		arr = new int[26];
		Arrays.fill(arr, 0);
		str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i) - 'A';
			arr[index]++;
		}
		System.out.println(Arrays.toString(arr));

	}

	public static void integerAPI() {
		// Integer.parseInt(s.substring(start, i + 1))
	}
}
