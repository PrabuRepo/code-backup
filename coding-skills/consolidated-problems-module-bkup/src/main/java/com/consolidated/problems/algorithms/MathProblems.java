package com.consolidated.problems.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.common.model.ListNode;

public class MathProblems {
	/*
	 * Why Modulo 10^9+7 (1000000007)?
	 * ===============================
	 * 1.It should just be large enough to fit in an largest integer data type i.e it makes sure that there is no over flow
	 *   in result.
	 * 2.It should be a prime number because if we take mod of a number by Prime the result is generally spaced i.e. the 
	 *   results are very different results in comparison to mod the number by non-prime, that is why primes are generally
	 *   used for mod.
	 */
	/********************** Type1: Number Types ****************************/

	/*
	 * Lexicographical Numbers:
	 * Given an integer n, return 1 - n in lexicographical order.
	 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
	 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
	 */
	// Approach1: Time Complexity-O(nlogn) & Space Complexity-O(n)
	public List<Integer> lexicalOrder1(int n) {
		List<String> list = new ArrayList<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i <= n; i++)
			list.add(String.valueOf(i));
		Collections.sort(list);
		// list.stream().forEach(k->System.out.print(k+" "));
		for (String str : list)
			result.add(Integer.valueOf(str));

		return result;
	}

	// Approach2: Time Complexity-O(n)
	public List<Integer> lexicalOrder(int n) {
		List<Integer> result = new ArrayList<>();
		int val = 1;
		for (int i = 1; i <= n; i++) {
			result.add(val);
			if (val * 10 <= n) {
				val *= 10;
			} else if (val % 10 != 9 && val + 1 <= n) {
				val++;
			} else {
				while ((val / 10) % 10 == 9)
					val /= 10;
				val = val / 10 + 1;
			}
		}

		return result;
	}

	/*  Happy Number:
	 *  Write an algorithm to determine if a number is "happy".
	 *  	A happy number is a number defined by the following process: Starting with any positive integer, replace the 
	 *  number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
	 *  or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy 
	 *  numbers.
	 *  Input: 19
	Output: true
	Explanation: 
	12 + 92 = 82
	82 + 22 = 68
	62 + 82 = 100
	12 + 02 + 02 = 1
	 */
	public boolean isHappy(int n) {
		if (n == 0)
			return false;

		int slow = n, fast = n;
		do {
			slow = squareSum(slow);
			fast = squareSum(squareSum(fast));
		} while (slow != fast);

		return (slow == 1);
	}

	private int squareSum(int n) {
		int sum = 0, digit = 0;
		while (n > 0) {
			digit = n % 10;
			sum += (digit * digit); // Square
			n = n / 10;
		}
		return sum;
	}

	/*Ugly Number
	 * Write a program to check whether a given number is an ugly number.
	
	Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
	
	Example 1:
	
	Input: 6
	Output: true
	Explanation: 6 = 2 × 3
	 */
	public boolean isUgly(int num) {
		while (num > 1) {
			if (num % 2 == 0)
				num /= 2;
			else if (num % 3 == 0)
				num /= 3;
			else if (num % 5 == 0)
				num /= 5;
			else
				break;
		}
		return num == 1 ? true : false;
	}

	/* Strobogrammatic Number I: A strobogrammatic number is a number that looks the same when rotated 180 degrees
	 * (looked at upside down). Write a function to determine if a number is strobogrammatic. The number is represented
	 * as a string.
	 */
	public boolean isStrobogrammatic(String num) {
		if (num.length() == 1) {
			if (num == "0" || num == "1" || num == "8")
				return true;
			return false;
		}

		int l = 0, h = num.length() - 1;
		while (l <= h) {
			if (!isValid(num.charAt(l), num.charAt(h)))
				return false;
			l++;
			h--;
		}

		return true;
	}

	private boolean isValid(char a, char b) {
		switch (a) {
		case '0':
			return b == '0';
		case '1':
			return b == '1';
		case '6':
			return b == '9';
		case '8':
			return b == '8';
		case '9':
			return b == '6';
		default:
			return false;
		}
	}

	/*
	 * Rotated Digits:
	 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.
	
	A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
	
	Now given a positive number N, how many numbers X from 1 to N are good?
	
	Example:
	Input: 10
	Output: 4
	Explanation: 
	There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
	Note that 1 and 10 are not good numbers, since they remain unchanged after rotating
	 */
	// Approach1:Brute Force Approach
	public int rotatedDigits1(int N) {
		int count = 0;
		for (int i = 1; i <= N; i++)
			if (isValid(i))
				count++;

		return count;
	}

	public boolean isValid(int num) {
		boolean valid = false;
		while (num > 0) {
			int mod = num % 10;
			if (mod == 2 || mod == 5 || mod == 6 || mod == 9)
				valid = true;
			else if (mod == 3 || mod == 4 || mod == 7)
				return false;
			num /= 10;
		}

		return valid;
	}

	/*Approach1:Dynamic Programming; It takes O(n) additional space
	 * dp[i] = 0, invalid number
	 * dp[i] = 1, valid and same number
	 * dp[i] = 2, valid and different number
	 */
	public int rotatedDigits(int N) {
		int[] dp = new int[N + 1];
		int count = 0;
		for (int i = 0; i <= N; i++) {
			if (i < 10) {
				if (i == 0 || i == 1 || i == 8) {
					dp[i] = 1;
				} else if (i == 2 || i == 5 || i == 6 || i == 9) {
					dp[i] = 2;
					count++;
				}
			} else {
				int a = dp[i / 10], b = dp[i % 10];
				if (a == 1 && b == 1) {
					dp[i] = 1;
				} else if (a >= 1 && b >= 1) {
					dp[i] = 2;
					count++;
				}
			}
		}

		return count;
	}

	/*
	 * Pascal's Triangle I:
	 * Input: 5
	Output:
	[
	 [1],
	[1,1],
	[1,2,1],
	[1,3,3,1],
	[1,4,6,4,1]
	]
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		if (numRows == 0)
			return result;

		list.add(1);
		result.add(list);

		int temp = 0;
		for (int i = 1; i < numRows; i++) {
			list = new ArrayList<>(); // Create new list for every instance
			list.addAll(result.get(i - 1)); // Get the previous value from result
			list.add(0);// Add empty value
			for (int j = i; j > 0; j--) {
				temp = list.get(j) + list.get(j - 1);
				list.set(j, temp);
			}
			result.add(list);
		}
		return result;
	}

	// Pascal's Triangle I:
	public List<Integer> getRow(int rowIndex) {
		Integer[] result = new Integer[rowIndex + 1];
		Arrays.fill(result, 0);
		result[0] = 1;

		for (int i = 1; i <= rowIndex; i++)
			for (int j = i; j > 0; j--)
				result[j] = result[j] + result[j - 1];

		return Arrays.asList(result);
	}

	public List<Integer> getRow2(int k) {
		Integer[] arr = new Integer[k + 1];
		Arrays.fill(arr, 0);
		arr[0] = 1;

		for (int i = 1; i <= k; i++)
			for (int j = i; j > 0; j--)
				arr[j] = arr[j] + arr[j - 1];

		return Arrays.asList(arr);
	}

	/********************** Type2: Math Formulas ****************************/

	/*
	 *  Power of Two:
	 *  Given an integer, write a function to determine if it is a power of two.
	 */
	// Approach1: Bit Manipulation; Time Complexity:O(1)
	public boolean isPowerOfTwo1(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}

	// Approach2: Bit Manipulation; Time Complexity:O(1)
	public boolean isPowerOfTwo2(int n) {
		return n > 0 && Integer.bitCount(n) == 1;
	}

	// Approach3: Using Iterative; Time Complexity:O(logn)
	public boolean isPowerOfTwo3(int n) {
		if (n <= 0)
			return false;
		while (n % 2 == 0)
			n /= 2;

		return n == 1;
	}

	// Approach4:
	public boolean isPowerOfTwo(int n) {
		if (n == 0)
			return false;

		int logValue = (int) Math.round(Math.log(n) / Math.log(2));
		System.out.println("Log Value: " + logValue);
		return (n == Math.pow(2, logValue));
	}

	// Power of Three
	// Approach1: Using Iteration; Time Complexity:O(log3n); log base 3
	public boolean isPowerOfThree1(int n) {
		if (n <= 0)
			return false;
		while (n % 3 == 0)
			n /= 3;
		return n == 1;
	}

	// Approach2: Using lograthmic Math logic
	public boolean isPowerOfThree(int n) {
		if (n <= 0)
			return false;

		int logValue = (int) Math.round(Math.log(n) / Math.log(3));
		return (n == Math.pow(3, logValue));
	}

	// Approach3: Time Complexity:O(1)
	public boolean isPowerOfThree3(int n) {
		// 1162261467 is 3^19, 3^20 is bigger than int
		return (n > 0 && 1162261467 % n == 0);
	}

	// Power of Four
	// Approach1: Using Iteration; Time Complexity:O(log4n); log base 4
	public boolean isPowerOfFour1(int n) {
		if (n <= 0)
			return false;
		while (n % 4 == 0)
			n /= 4;
		return n == 1;
	}

	// Approach2: Using lograthmic Math logic
	public boolean isPowerOfFour(int n) {
		if (n <= 0)
			return false;

		int logValue = (int) Math.round(Math.log(n) / Math.log(4));
		return (n == Math.pow(4, logValue));
	}

	// Approach3: How its working?
	public boolean isPowerOfFour3(int num) {
		return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
	}

	public boolean isPowerOfFour4(int num) {
		return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
		// 0x55555555 is to get rid of those power of 2 but not power of 4
		// so that the single 1 bit always appears at the odd position
	}

	// Check if a number can be expressed as x raised to power y
	/*
	 * The idea is simple try all numbers x starting from 2 to square root of n (given number). For every x, try x^y where y starts 
	 * from 2 and increases one by one until either x^y becomes n or greater than n.
	 */
	public boolean xPowY1(int n) {
		for (int x = 2; x <= Math.sqrt(n); x++) {
			int y = 2;

			double p = Math.pow(x, y);

			while (p <= n && p > 0) {
				if (p == n)
					return true;
				y++;
				p = Math.pow(x, y);
			}
		}
		return false;
	}

	public boolean xPowY2(int n) {
		// Find Log n in different bases and check if the value is an integer
		for (int x = 2; x <= (int) Math.sqrt(n); x++) {
			float f = (float) Math.log(n) / (float) Math.log(x);

			if ((f - (int) f) == 0.0)
				return true;
		}
		return false;
	}

	/********************** Type3: Conversions ****************************/

	/* Gray Code:
	 *    The gray code is a binary numeral system where two successive values differ in only one bit.
	 */
	public List<Integer> grayCode(int n) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < Math.pow(2, n); i++) // Math.pow(2,n) or 1<<n
			result.add(i ^ (i >> 1));

		return result;
	}

	/* Gray Number:
	 *    Given two integers, determine whether or not they differ by a single digit 
	 */
	public boolean isGrayNumber(int a, int b) {
		int xor = a ^ b;
		return (xor & (xor - 1)) == 0;
	}

	/*
	 * Integer to English Words:Convert a non-negative integer to its english words representation.
	 */
	private final String[] lessThan10 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
	private final String[] lessThan20 = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
			"Seventeen", "Eighteen", "Nineteen" };
	private final String[] lessThan100 = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
			"Ninety" };

	public String numberToWords(int num) {
		if (num == 0)
			return "Zero";
		return helper(num);
	}

	public String helper(int num) {
		final int BILLION = 1000000000;
		final int MILLION = 1000000;
		final int THOUSAND = 1000;
		final int HUNDRED = 100;
		final int TEN = 10;

		String result;
		if (num < TEN)
			result = lessThan10[num];
		else if (num < 20)
			result = lessThan20[num - 10];
		else if (num < HUNDRED)
			result = lessThan100[num / TEN] + " " + helper(num % TEN);
		else if (num < THOUSAND)
			result = helper(num / HUNDRED) + " Hundred " + helper(num % HUNDRED);
		else if (num < MILLION)
			result = helper(num / THOUSAND) + " Thousand " + helper(num % THOUSAND);
		else if (num < BILLION)
			result = helper(num / MILLION) + " Million " + helper(num % MILLION);
		else
			result = helper(num / BILLION) + " Billion " + helper(num % BILLION);

		return result.trim();
	}

	/*
	 *  Roman to Integer:
	 *   Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
	
	Symbol       Value
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000
	 */
	public int romanToInt(String s) {
		if (s == null || s == "")
			return 0;

		int next = 0, curr = 0, result = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			curr = getIntvalue(s.charAt(i)); // map.get(s.charAt(i));
			if (curr < next) {
				result -= curr;
			} else {
				result += curr;
			}
			next = curr;
		}
		return result;
	}

	private int getIntvalue(char r) {
		if (r == 'I')
			return 1;
		if (r == 'V')
			return 5;
		if (r == 'X')
			return 10;
		if (r == 'L')
			return 50;
		if (r == 'C')
			return 100;
		if (r == 'D')
			return 500;
		if (r == 'M')
			return 1000;
		return -1;
	}

	/*
	 * Integer to Roman:
	 */
	public String intToRoman1(int num) {
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < values.length; i++) {
			while (num >= values[i]) {
				num -= values[i];
				sb.append(strs[i]);
			}
			if (num < 1)
				break;
		}
		return sb.toString();
	}

	public String intToRoman2(int num) {
		String M[] = { "", "M", "MM", "MMM" };
		String C[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String X[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String I[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
	}

	/*
	 * Excel Sheet Column Number:
	 * Given a column title as appear in an Excel sheet, return its corresponding column number.
	
	For example:
	
	A -> 1
	B -> 2
	C -> 3
	...
	Z -> 26
	AA -> 27
	AB -> 28 
	 */
	public int titleToNumber1(String s) {
		int n = 0, result = 0;
		for (int i = s.length() - 1; i >= 0; i--, n++)
			result += Math.pow(26, n) * alphaToInt(s.charAt(i));
		return result;
	}

	public int titleToNumber(String s) {
		int n = 0, result = 0;
		for (int i = 0; i < s.length(); i++) {
			result *= 26;
			result += alphaToInt(s.charAt(i));
		}
		return result;
	}

	private int alphaToInt(char ch) {
		return (int) ch - 64;
	}

	private char intToAlphabet(int n) {
		return (char) (n + 'A');
	}

	/*
	 *  Excel Sheet Column Title:
	 *  Given a positive integer, return its corresponding column title as appear in an Excel sheet.
	
	For example:
	
	1 -> A
	2 -> B
	3 -> C
	...
	26 -> Z
	27 -> AA
	28 -> AB 
	 */
	// Similar to Decimal to Binary conversion
	public String convertToTitle(int n) {
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			n--;
			sb.append(intToAlphabet(n % 26));
			n /= 26;
		}
		sb.reverse();
		return sb.toString();
	}

	/********************** Type4: Arithmetic Operations ****************************/

	// Add Binary - Binary String Problem
	public String addBinary(String s1, String s2) {
		int carry = 0;
		int i = s1.length() - 1, j = s2.length() - 1;
		StringBuilder sb = new StringBuilder();

		for (; i >= 0 || j >= 0 || carry == 1; i--, j--) {
			int a = (i < 0 || s1.charAt(i) == '0') ? 0 : 1;
			int b = (j < 0 || s2.charAt(j) == '0') ? 0 : 1;
			sb.append((a + b + carry) % 2);
			carry = (a + b + carry) / 2; // or carry = sum < 2 ? 0:1;
		}

		return sb.reverse().toString();
	}

	// Add Strings - Decimal Addition
	public String addStrings1(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
		for (; i >= 0 || j >= 0 || carry == 1; i--, j--) {
			int x = i < 0 ? 0 : num1.charAt(i) - '0';
			int y = j < 0 ? 0 : num2.charAt(j) - '0';
			sb.append((x + y + carry) % 10);
			carry = (x + y + carry) / 10;
		}
		return sb.reverse().toString();
	}

	// Add Two Numbers I- Linked List
	public ListNode addTwoNumbers(ListNode list1, ListNode list2) {
		if (list1 == null && list2 == null)
			return null;
		if (list1 == null)
			return list2;
		if (list2 == null)
			return list1;

		ListNode result = new ListNode(0);
		ListNode current = result;
		int carry = 0, sum = 0, data1 = 0, data2 = 0;
		while (list1 != null || list2 != null || carry == 1) {
			data1 = list1 != null ? list1.data : 0;
			data2 = list2 != null ? list2.data : 0;
			sum = data1 + data2 + carry;
			carry = sum / 10;
			sum %= 10;
			current.next = new ListNode(sum);

			if (list1 != null)
				list1 = list1.next;
			if (list2 != null)
				list2 = list2.next;

			current = current.next;
		}

		return result.next;
	}

	/* Add Two Numbers II - Linked List
	 * Example: Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4); Output: 7 -> 8 -> 0 -> 7
	 *  2 Approaches:
	 *   1. Reverse both list, add and reverse the result
	 *   2. Using Stack
	 *   3. Using Tail Recursion 
	 */
	// Using Stack
	public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;

		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();

		while (l1 != null) {
			s1.push(l1.data);
			l1 = l1.next;
		}

		while (l2 != null) {
			s2.push(l2.data);
			l2 = l2.next;
		}

		int carry = 0;
		ListNode head = null;
		while (!s1.isEmpty() || !s2.isEmpty() || carry == 1) {
			int sum = (s1.isEmpty() ? 0 : s1.pop()) + (s2.isEmpty() ? 0 : s2.pop()) + carry;
			carry = sum / 10; // or (sum >= 10)?1:0;
			sum %= 10;
			ListNode newNode = new ListNode(sum);
			newNode.next = head;
			head = newNode;
		}

		return head;
	}

	// Using Tail recursion:
	// Using Recursive function
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;

		int len1 = length(l1);
		int len2 = length(l2);

		ListNode result = (len1 > len2) ? helper(l1, l2, len1 - len2) : helper(l2, l1, len2 - len1);

		if (result.data >= 10) {
			result.data %= 10;
			ListNode newNode = new ListNode(1);
			newNode.next = result;
			result = newNode;
		}

		return result;
	}

	public ListNode helper(ListNode l1, ListNode l2, int offset) {
		if (l1 == null)
			return null;

		ListNode prev = (offset == 0) ? new ListNode(l1.data + l2.data) : new ListNode(l1.data);
		ListNode curr = (offset == 0) ? helper(l1.next, l2.next, 0) : helper(l1.next, l2, offset - 1);

		if (curr != null && curr.data >= 10) {
			prev.data += 1;
			curr.data = curr.data % 10;
		}

		prev.next = curr;
		return prev;
	}

	public int length(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}

	// Sum of Two Integers/A + B Problem - Bit Alg
	/*Sum without using arithmetic operators.
	 * Half Adder Logic:
	 * Sum of two bits can be obtained by performing XOR (^) of the two bits(S= a^b).
	 * Carry bit can be obtained by performing AND (&) of two bits(C= a&b).
	 */
	public static int sum1(int a, int b) {
		int sum = 0, carry = 0;
		while (b != 0) {
			sum = a ^ b;
			carry = a & b;

			a = sum;
			b = (carry << 1);
		}
		return sum;
	}

	// Recursive approach
	public static int sum2(int a, int b) {
		if (b == 0)
			return a;
		int sum = a ^ b;
		int carry = a & b;
		return sum2(sum, carry << 1);
	}

	/*Sub without using arithmetic operators.
	 * Half Subtractor Logic:
	 * Difference of two bits can be obtained by performing XOR (^) of the two bits(D= a^b).
	 * Borrow bit can be obtained by performing AND (&) of two bits(B= a' & b).
	 */
	public static int sub1(int a, int b) {
		int sub = 0, borrow = 0;
		while (b != 0) {
			sub = a ^ b;
			borrow = (~a) & b;

			a = sub;
			b = borrow << 1;
		}
		return sub;
	}

	public static int sub2(int a, int b) {
		if (b == 0)
			return a;
		int diff = a ^ b;
		int borrow = (~a) & b;
		return sub2(diff, borrow << 1);
	}

	// Plus One - Array
	public int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i] += 1;
				return digits;
			}
			digits[i] = 0;
		}

		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;
		return newNumber;
	}

	// Plus One Linked List - Linked List
	/*
	 * 2 Approaches for this solution:
	 * 	- 1. Reverse the data, add one and reverse it again
	 *  - 2. using Stack
	 *  - 3. Tail Recursion
	 */
	public ListNode plusOne1(ListNode head) {
		ListNode h2 = reverse(head);

		ListNode p = h2;

		while (p != null) {
			if (p.data + 1 <= 9) {
				p.data = p.data + 1;
				break;
			} else {
				p.data = 0;
				if (p.next == null) {
					p.next = new ListNode(1);
					break;
				}
				p = p.next;
			}
		}

		return reverse(h2);
	}

	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode p1 = head;
		ListNode p2 = p1.next;
		while (p2 != null) {
			ListNode t = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = t;
		}

		head.next = null;

		return p1;
	}

	public ListNode plusOne2(ListNode head) {
		if (head == null)
			return null;

		int carry = plusOneUtil(head);

		if (carry == 1) {
			ListNode newNode = new ListNode(1);
			newNode.next = head;
			head = newNode;
		}

		return head;
	}

	public int plusOneUtil(ListNode node) {
		if (node == null)
			return 1;
		int carry = plusOneUtil(node.next);
		int sum = node.data + carry;
		node.data = sum % 10;
		return sum / 10;
	}

	// Multiply Strings - Decimal Mul
	public String multiply(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] values = new int[m + n];

		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int carryIndex = i + j, sumIndex = i + j + 1;
				int sum = mul + values[sumIndex];

				values[carryIndex] += sum / 10;
				values[sumIndex] = sum % 10;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int val : values)
			if (!(sb.length() == 0 && val == 0))
				sb.append(val);

		return sb.length() == 0 ? "0" : sb.toString();
	}

	// Divide Two Integers - Bit Alg
	/* Divide two integers without using multiplication, division and mod operator.
	 *  1. Using subraction & increment operator - Time Complexity: O(n) 
	 *  2. Using Bit Manipulations - Time Complexity: O(logn)
	 */
	/*
	 * Keep subtracting the divisor from dividend until dividend becomes less than divisor. The dividend becomes the
	 * remainder, and the number of times subtraction is done becomes the quotient. 
	 * - Time Complexity: O(a) This solution fails for large inputs.
	 */
	public static int div1(int dividend, int divisor) {
		int quotient = 0;

		// Calculate the sign
		int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		/* Keep subtracting the divisor from dividend until dividend becomes less than divisor. The dividend becomes the remainder, 
		 * and the number of times subtraction is done becomes the quotient. 
		 */
		while (dividend >= divisor) {
			dividend -= divisor;
			quotient++;
		}
		return sign * quotient;
	}

	// Time Complexity: O(logn)
	public int divide(int dividend, int divisor) {
		// Find the sign of the result
		boolean negSign = (dividend < 0 ^ divisor < 0) ? true : false;

		// handle special cases
		if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
			return Integer.MAX_VALUE;

		// get positive & long values
		long lDividend = Math.abs((long) dividend);
		long lDivisor = Math.abs((long) divisor);
		int quotient = 0;

		while (lDividend >= lDivisor) {
			int count = 1;
			long temp = lDivisor;
			while (lDividend - temp >= temp) {
				temp <<= 1;
				count <<= 1;
			}
			quotient += count;
			lDividend -= temp;
		}

		return negSign ? -quotient : quotient;
	}

	// Fraction to Recurring Decimal - Fractional Div
	/* Ref: https://www.geeksforgeeks.org/find-recurring-sequence-fraction/     
	* Steps:
	*   Calculate integer part which is floor(numerator/denominator)
	*   remainder = (numerator%denominator) / denominator.
	*   Then iterate below steps till reach zero or recurrence fraction:
	*   	1.Multiply the remainder by 10.
	*   	2.Append remainder / denominator to result. Here stores remainder in the Map.
	*   	3.Remainder = remainder % denominator.
	*/
	public String fractionToDecimal(int numerator, int denominator) {
		// validation
		if (numerator == 0)
			return "0";
		if (denominator == 0)
			return "NaN";

		StringBuilder sb = new StringBuilder();
		// Find the sign
		if (numerator < 0 ^ denominator < 0)
			sb.append("-");

		// convert int to long
		long num = numerator, den = denominator;
		num = Math.abs(num);
		den = Math.abs(den);

		// Find the quotient or Integer or real part
		long result = num / den;
		sb.append(result);

		// Find the remainder
		long rem = (num % den) * 10;

		// If rem is zero, return the result
		if (rem == 0)
			return sb.toString();

		// Find the fractional part
		HashMap<Long, Integer> map = new HashMap<>();
		sb.append(".");
		while (rem != 0) {
			// System.out.println("Rem: "+rem);
			// if there is recurrence, return the result
			if (map.containsKey(rem)) {
				int val = map.get(rem);
				String part1 = sb.substring(0, val);
				String part2 = sb.substring(val, sb.length());
				return part1 + "(" + part2 + ")";
			}

			// Store the rem in the result and calculate the next result & rem value
			map.put(rem, sb.length());
			result = rem / den;
			sb.append(result);
			rem = (rem % den) * 10;
		}

		return sb.toString();
	}

	/********************** Type5: Math Proofs/Tricks ****************************/
	/* Add Digits: Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
	 * The problem, widely known as digit root problem, has a congruence formula:
	 * For base b (decimal case b = 10), the digit root of an integer is:
	 *   dr(n) = 0 if n == 0 
	 *   dr(n) = (b-1) if n != 0 and n % (b-1) == 0
	 *   dr(n) = n mod (b-1) if n % (b-1) != 0
	 *    or
	 *   dr(n) = 1 + (n - 1) % 9
	 */
	public int addDigits(int num) {
		return 1 + (num - 1) % 9;
	}

	// addDigits1 & addDigits are same
	public int addDigits2(int num) {
		if (num == 0)
			return 0;
		else if (num % 9 == 0)
			return 9;
		else
			return num % 9;
	}

	// Below Solutions are loop/recursion:
	public int addDigits3(int num) {
		int add = 0;
		while (num > 0) {
			add += num % 10;
			num /= 10;
			if (num == 0 && add > 9) {
				num = add;
				add = 0;
			}
		}
		return add;
	}

	public int addDigits4(int num) {
		if (num < 9)
			return num;
		int add = 0;
		while (num > 0) {
			add += num % 10;
			num /= 10;
		}
		return add < 10 ? add : addDigits(add);
	}

	/*
	 * Count Numbers with Unique Digits:
	 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 <= x < 10^n.
	Example:
	Input: 2
	Output: 91 
	Explanation: The answer should be the total numbers in the range of 0 <= x < 100, 
	         excluding 11,22,33,44,55,66,77,88,99
	 */
	public int countNumbersWithUniqueDigits1(int n) {
		if (n == 0)
			return 1;
		int res = 10;
		int uniqueDigits = 9;
		int availableNumber = 9;
		while (n-- > 1 && availableNumber > 0) {
			uniqueDigits = uniqueDigits * availableNumber;
			res += uniqueDigits;
			availableNumber--;
		}
		return res;
	}

	public int countNumbersWithUniqueDigits(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 1;
		int uniqueDigits = 9, sum = 1;
		for (int i = 1; i <= n && uniqueDigits > 0; i++) {
			if (i == 1)
				dp[i] = dp[i - 1] * 9;
			else
				dp[i] = dp[i - 1] * uniqueDigits--;
			sum += dp[i];
		}
		return sum;
	}

	/*
	 * Nth Digit:
	 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 
	 */
	/*
	 * Nth Digit:
	 * sequence   1 2 3 4 5 6 7 8 9 1  0  1  1  1  2  1  3  1  4  1  5  1  6
	 * Nth digit: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
	 * sequence is the Nth digital, like the 11th digital is 0, 12 is 1, 13 is 1, 14 is 1, 15 is 2, 16 is 1, 17 is 3.........
	 * 	1-------9 9*1 = 9 digits
	 * 	10-----99 90 *2 = 180 digits
	 * 	100---999 900 * 3 = 2700 digits
	 * 
	 * Now, for example gave N = 1000, then 1000-9-180 = 811, it means the 811th digit local in [100, 999], and we know
	 * each number like 100 has three digit, so 811 / 3 = 270,
	 * Then, we know the 270th number in [100, 999], is 270th + 100 (start from 100) = 370.
	 * 370 still has three digit, which one is the answer? 3, 7, 0
	 * 	811 % 3 = 1, so the first one is the answer, so return 3.
	 */
	public int findNthDigit(int n) {
		int digits = 1, start = 1;
		long base = 9, count = digits * base; // No of values for "digits"

		while (n > count) {
			n -= count;
			digits++;
			base *= 10;
			start *= 10;
			count = digits * base;
		}
		int origNum = start + ((n - 1) / digits);
		String str = String.valueOf(origNum);
		return Character.getNumericValue(str.charAt((n - 1) % digits));
	}

	/*
	 * Integer Break:
	 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of 
	 * those integers. Return the maximum product you can get.
	 */
	// Choose Factor: 3; Time Complexity: O(n)
	public int integerBreak1(int n) {
		if (n < 2)
			return 0;
		if (n == 2)
			return 1;
		if (n == 3)
			return 2;

		int product = 1;
		while (n > 4) {
			product *= 3;
			n -= 3;
		}
		product *= n; // Multiply with remaining n
		return product;
	}

	// Time Complexity: O(logn)
	public int integerBreak(int n) {
		if (n == 2)
			return 1;
		else if (n == 3)
			return 2;
		else if (n % 3 == 0)
			return (int) Math.pow(3, n / 3);
		else if (n % 3 == 1)
			return 2 * 2 * (int) Math.pow(3, (n - 4) / 3);
		else
			return 2 * (int) Math.pow(3, n / 3);
	}

	/*
	 * Factorial Trailing Zeroes:
	Ref: 
	 https://www.geeksforgeeks.org/count-trailing-zeroes-factorial-number/
	 https://www.purplemath.com/modules/factzero.htm
	*/
	public int trailingZeroes(int n) {
		/*
		The only tricky thing about this problem is to determine how many 5s are there. since if you reach five, 
		you will innevitably reach 4, and thus you have a ton. Plus there are tons of even number there than five, 
		so no worries, you will find one to match 5 for any amount of 5 you find in the number n*/
		int count = 0;
		while (n > 0) {
			count += n / 5;
			n /= 5; // this loop makes sure n/ 5,25,125,625... since each is a power of 5 and have to be counted again
			// until the power
		}
		return count;
	}

	/********* clean up below ****/

}