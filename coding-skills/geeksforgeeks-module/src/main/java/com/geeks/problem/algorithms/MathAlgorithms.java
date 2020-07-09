package com.geeks.problem.algorithms;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.model.Point;

public class MathAlgorithms {

	/*
	 * Why Modulo 10^9+7 (1000000007)?
	 * ===============================
	 * 1.It should just be large enough to fit in an largest integer data type i.e it makes sure that there is no over flow
	 *   in result.
	 * 2.It should be a prime number because if we take mod of a number by Prime the result is generally spaced i.e. the 
	 *   results are very different results in comparison to mod the number by non-prime, that is why primes are generally
	 *   used for mod.
	 */
	/******************** Basic Math Problems **************************/

	/*
	 * Prime numbers are those numbers that are greater than 1 and have only two factors 1 and itself.
	 */
	// Prime no with(o(n) time complexity:
	public boolean isPrime1(int n) {
		if (n <= 1) return false;
		if (n == 2) return true;

		for (int i = 2; i < n; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

	// Prime no with(o(sqrt(n)) time complexity:
	public boolean isPrime2(int n) {
		if (n <= 1) return false;

		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

	// Alternative approach to isPrime2().
	public boolean isPrime3(int n) {
		if (n <= 1) return false;

		for (int i = 2; i * i <= n; ++i) {
			if (n % i == 0) { return false; }
		}
		return true;
	}

	public void generatePrimeNumbers(int n) {
		for (int i = 1; i <= n; i++) {
			if (isPrime2(i)) System.out.print(i + ", ");
		}

	}

	// Factorial Number:
	public int factorial1(int n) {
		if (n <= 1) return 1;
		return n * factorial1(n - 1);
	}

	public int fibonacci1(int n) {
		if (n <= 1) return n;
		return fibonacci1(n - 1) + fibonacci1(n - 2);
	}

	public void fibonacci2(int n) {
		int f0 = 0, f1 = 1, next;
		System.out.print(f1 + " ");
		for (int i = 2; i <= n; i++) {
			next = f0 + f1;
			f0 = f1;
			f1 = next;
			System.out.print(next + " ");
		}
	}

	/*
	 * Greatest Common Divisor (GCD): (Greatest Common Divisor or Greatest common Factor or Highest Common Factor(GCD or GCF or HCF))
	 * The GCD of two or more numbers is the largest positive number that divides all the numbers that are considered. For
	 * example, the GCD of 6 and 10 is 2 because it is the largest positive number that can divide both 6 and 10.
	 */

	/* Naive approach: Traverse all the numbers from min(A, B) to 1 and check whether the current number divides both A and B. 
	* If yes, it is the GCD of A and B.
	* 
	* The time complexity is O(min(A, B)).
	*/
	public int GCD(int A, int B) {
		int m = Math.min(A, B), gcd;
		for (int i = m; i > 0; --i)
			if (A % i == 0 && B % i == 0) {
				gcd = i;
				return gcd;
			}
		return 1;
	}

	/* GCD is calculated using Euclid's algorithm(Recursive)-> GCD(A, B) = GCD(B, A%B), it will recurse until A%B =0.
	 * Time complexity: O(log(max(A, B)))
	 */
	public int gcd1(int a, int b) {
		if (b == 0) return a;
		return gcd1(b, a % b);
	}

	// GCD is calculated using Euclid's algorithm(Iterative)
	public int gcd2(int a, int b) {
		if (b != 0) {
			int temp;
			while (b > 0) {
				temp = a % b;
				a = b;
				b = temp;
			}
		}
		return a;
	}

	// Other way to calculate the GCD;
	public int gcd3(int a, int b) {
		// Everything divides 0
		if (a == 0 || b == 0) return 0;

		// base case
		if (a == b) return a;

		// a is greater
		if (a > b) return gcd3(a - b, b);
		return gcd3(a, b - a);
	}

	// Calculated using the formula -> [a*b = gcd(a,b) * lcm(a,b)]
	// Least Common Multiplier (LCM)
	public int lcm(int a, int b) {
		return (a * b) / gcd1(a, b);
	}

	public int lcmForArray(int[] a) {
		int result = 0;
		result = a[0];
		for (int i = 1; i < a.length; i++) {
			result = lcm(result, a[i]);
		}
		return result;

	}

	public int gcdForArray(int[] a) {
		int result = 0;
		result = a[0];
		for (int i = 1; i < a.length; i++) {
			result = gcd1(result, a[i]);
		}
		return result;
	}

	// Pascal Triangle
	public void displayPascalTriangle(int n) {
		List<List<Integer>> values = pascalTriangle(n);
		for (List<Integer> list : values) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public static List<List<Integer>> pascalTriangle(int numRows) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		if (numRows == 0) return result;

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

	/* Number can be expressed as x raised to power y: Given a positive integer n, find if it can be expressed as xy where 
	 * y > 1 and x > 0. x and y both are integers.
	 */
	// Approach1: Simple Approach
	public boolean isPower1(int num) {
		for (int i = 2; i * i <= num; i++) { // i = 1 to sqrt(n)
			if (num % i != 0) continue;
			int pow = findPower(num, i);
			if (pow != -1) {
				System.out.println("Result: " + i + "^" + pow + " = " + num);
				return true;
			}
		}
		return false;
	}

	public int findPower(int num, int b) {
		int x = 1, y = 0;
		while (x < num) {
			x *= b;
			y++;
			if (num == x) return y;
		}
		return -1;
	}

	/*Approach2: logarithmic Approach - Time Complexity: O(sqrt(n))
	 *   num = x^y;
	 *   log(num) =log(x^y)
	 *   y= log(num)/log(x)
	 */
	public boolean isPower2(int num) {
		for (int x = 2; x * x <= num; x++) { // i = 1 to sqrt(n)
			double y = Math.log(num) / Math.log(x);
			if (y - (int) y == 0) { // log(num)/log(x) is Integer return val
				System.out.println("Result: " + x + "^" + (int) y + " = " + num);
				return true;
			}
		}
		return false;
	}

	/******************* Conversion Problems ***************************/

	public int decimalToBin(int n) {
		if (n == 0 || n == 1) return n;

		String data = "";
		data += decimalToBin(n / 2);
		data += n % 2;
		return Integer.valueOf(data);
	}

	public int BinToDecimal(String str) {
		int decValue = 0, n = str.length() - 1;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '1') decValue += pow4(2, (n - i));
		}
		return decValue;
	}

	public void decimalToRoman() {

	}

	/*
	 * Write a function that parses Roman numerals. E.g. XIV returns 14.
	 */
	public int romanToInt(String s) {
		if (s == null || s == "") return 0;

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

	private Map<Character, Integer> loadRomanLetters() {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		return map;
	}

	private int getIntvalue(char r) {
		if (r == 'I') return 1;
		if (r == 'V') return 5;
		if (r == 'X') return 10;
		if (r == 'L') return 50;
		if (r == 'C') return 100;
		if (r == 'D') return 500;
		if (r == 'M') return 1000;
		return -1;
	}

	/*
	 * Write in words for a given digit. E.g. 123 returns one hundred and twenty three.
	 */
	public void writeNumberInWords() {

	}

	/******************* Arithmetic Operations w/o operators ***************************/

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

			b = (carry << 1);
			a = sum;
		}
		return sum;
	}

	// Recursive approach
	public static int sum2(int a, int b) {
		if (b == 0) return a;
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
		if (b == 0) return a;
		int diff = a ^ b;
		int borrow = (~a) & b;
		return sub2(diff, borrow << 1);
	}

	/*Divide two integers without using multiplication, division and mod operator.
	 *  1. Using subraction & increment operator - Time Complexity: O(n) 
	 *  2. Using Bit Manipulations - Time Complexity: O(logn)
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

	// Using Bit Manipulations - Time Complexity: O(logn) -> Its not working
	// dividend = quotient * divisor + remainder
	public static int div2(int dividend, int divisor) {
		int quotient = 0, temp = 0;

		// Calculate the sign
		int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		// test down from the highest bit and
		// accumulate the tentative value for
		// valid bit
		for (int i = 31; i >= 0; --i) {
			temp += divisor << i;
			if (temp <= dividend) {
				temp += divisor << i;
				quotient |= 1 << i;
			}
		}
		return sign * quotient;
	}

	// Calculate pow(x, n):
	// 1.Simple Approach: Time Complexity : O(n)
	public int pow1(int m, int n) {
		if (n == 0) return 1;
		return m * pow1(m, n - 1);
	}

	// 2.Better Approach: Time Complexity: O(logn)
	public int pow2(int m, int n) {
		if (n == 0) return 1;
		else if (n % 2 == 0) return pow2(m, n / 2) * pow2(m, n / 2);
		else return m * pow2(m, n / 2) * pow2(m, n / 2);
	}

	// 3.Optimized Solution, but doesnt support for -n. Time Complexity: O(logn)
	static int pow3(int m, int n) {
		int temp;
		if (n == 0) return 1;
		temp = pow3(m, n / 2);
		if (n % 2 == 0) return temp * temp;
		else return m * temp * temp;
	}

	// 4.Optimized Solution, which supports -n also. Time Complexity: O(logn)
	public double pow4(int m, int n) {
		double temp;
		if (n == 0) return 1;
		temp = pow4(m, n / 2);
		if (n % 2 == 0) return temp * temp;
		else if (n > 0) return m * temp * temp;
		else return (temp * temp) / m;
	}

	/*
	 * storing answers that are too large for their respective datatypes is an issue with this method. In some languages the
	 * answer will exceed the range of the datatype while in other languages it will timeout due to large number
	 * multiplications. In such instances, you must use modulus (%). Instead of finding x^n , you must find x^n%M . 
	 * 
	 * For example, run the implementation of the method to find 2^10^9. The O(n) solution will timeout, while the O(logn) solution 
	 * will run in time but it will produce garbage values. 
	 * To fix this you must use the modulo operation i.e. %M in those lines where a temporary answer is computed.
	 */
	// What is the value of N?
	public int modularExponentiation(int x, int n, int M) {
		if (n == 0) return 1;
		else if (n % 2 == 0) // n is even
			return modularExponentiation((x * x) % M, n / 2, M);
		else // n is odd
			return (x * modularExponentiation((x * x) % M, (n - 1) / 2, M)) % M;

	}

	/*
	 * 2^x = n -> log2^x = logn -> x = Math.round(logn/log2); 
	 */
	public boolean isPowerOfTwo1(int n) {
		if (n == 0) return false;

		int logValue = (int) Math.round(Math.log(n) / Math.log(2));
		System.out.println("Log Value: " + logValue);
		return (n == Math.pow(2, logValue));
	}

	public boolean isPowerOfTwo2(int n) {
		return ((n & n - 1) == 0);
	}

	/*
	 * To find the next power of 2 value; Eg 27 -> 32, 59 -> 64, 89 -> 128
	 * 2^x = n -> log2^x = logn -> x = Math.ceil(logn/log3); 
	 */
	public int nextPowerOfN(int n) {
		if (n == 0) return 1;

		int logValue = (int) Math.ceil(Math.log(n) / Math.log(2));
		System.out.println("Log Value: " + logValue);
		return (int) Math.pow(2, logValue);
	}

	/*
	 * 3^x = n -> log3^x = logn -> x = Math.round(logn/log3); 
	 */
	public boolean isPowerOfThree1(int n) {
		if (n == 0) return false;

		int logValue = (int) Math.round(Math.log(n) / Math.log(3));
		System.out.println("Log Value: " + logValue);
		return (n == Math.pow(3, logValue));
	}

	/*
	 * Any integer number other than power of 3 which divides highest power of 3 value that integer can hold 3^19 = 1162261467 
	 * (Assuming that integers are stored using 32 bits) will give reminder non-zero.
	 */
	public boolean isPowerOfThree2(int n) {
		if (n < 1) return false;
		// The maximum power of 3 value that integer can hold is 1162261467 ( 3^19 )
		return (1162261467 % n == 0);

		// Added this to find the max value in Integer
		/*for (int i = 1; i < 40; i++) {
			int val = (int) Math.pow(3, i);
			if (val >= Integer.MAX_VALUE)
				break;
			System.out.println(i + " - " + val);
		}*/
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
				if (p == n) return true;
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

			if ((f - (int) f) == 0.0) return true;
		}
		return false;
	}

	// Simple Approach: It doesnt work for high values
	static int sqrt1(int n) {
		if (n == 0 || n == 1) return n;
		int i = 1, multiply = 1;
		while (multiply < n) {
			i++;
			multiply = i * i;
		}
		return i * i == n ? i : (i - 1);
	}

	// Efficient Approach: Using Binary Search
	public static int sqrt2(int x) {
		int low = 1, high = x, div = 0, mid;
		while (low <= high) {
			mid = (low + high) / 2;
			div = x / mid;
			if (mid == div) return mid;
			else if (mid > div) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low - 1;
	}

	/********************* Matrix Problems ******************/

	public int[][] addMatrix(int[][] a, int[][] b) {
		int[][] add = null;
		int r1 = a.length, c1 = a[0].length, r2 = b.length, c2 = b[0].length;
		if (r1 == r2 && c1 == c2) {
			add = new int[r1][c1];
			for (int i = 0; i < r1; i++) {
				for (int j = 0; j < c1; j++) {
					add[i][j] = a[i][j] + b[i][j];
				}
			}
		} else {
			System.out.println("Unqual dimension Matrix");
		}
		return add;
	}

	public int[][] subMatrix(int[][] a, int[][] b) {
		int[][] add = null;
		int r1 = a.length, c1 = a[0].length, r2 = b.length, c2 = b[0].length;
		if (r1 == r2 && c1 == c2) {
			add = new int[r1][c1];
			for (int i = 0; i < r1; i++) {
				for (int j = 0; j < c1; j++) {
					add[i][j] = a[i][j] - b[i][j];
				}
			}
		} else {
			System.out.println("Unqual dimension Matrix");
		}
		return add;
	}

	public int[][] mulMatrix(int[][] a, int[][] b) {
		int[][] result = null;
		int r1 = a.length, c1 = a[0].length, r2 = b.length, c2 = b[0].length;
		if (c1 == r2) {
			result = new int[r1][c2];
			for (int i = 0; i < r1; i++) {
				for (int j = 0; j < c2; j++) {
					result[i][j] = 0;
					for (int k = 0; k < c1; k++) { // use c1 or r2
						result[i][j] += a[i][k] * b[k][j];
					}
				}
			}
		} else {
			System.out.println("Wrong dimension - Multiplication can't be performed.");
		}
		return result;
	}

	public int[][] transposeMatrix(int[][] a) {
		int row = a.length;
		int col = a[0].length;
		int[][] transpose = new int[col][row];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				transpose[j][i] = a[i][j];
			}
		}
		return transpose;
	}

	public void printMatrix(int[][] data) {
		int row = data.length;
		int col = data[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

	/********************** Misc Problems ***********************/
	public boolean isHappy(int n) {
		if (n <= 1) return true;
		int sum = 0, digit = 0;
		while (true) {
			digit = n % 10;
			sum += (int) Math.pow(digit, 2);
			n = n / 10;
			if (n == 0) {
				if (sum == 1) return true;
				else if (sum < 10) return false;
				else {
					n = sum;
					sum = 0;
				}
			}
		}
	}

	// Utility method to return sum of square of digit of n
	static int numSquareSum(int n) {
		int squareSum = 0;
		while (n != 0) {
			squareSum += (n % 10) * (n % 10);
			n /= 10;
		}
		return squareSum;
	}

	// method return true if n is Happy number
	public boolean isHappynumber(int n) {
		// slow & fast variables are used to find the cycle: Similiar to Floyd Algorithm in LL
		int slow = n, fast = n;
		do {
			// move slow number by one iteration
			slow = numSquareSum(slow);
			// move fast number by two iteration
			fast = numSquareSum(numSquareSum(fast));

			// System.out.println("S: "+slow+" F: "+fast); Eg: 20
		} while (slow != fast);

		// if both number meet at 1, then return true
		return (slow == 1);
	}

	public int missingNumber(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;

		int sumOfN = (n * (n + 1)) / 2;
		int sum = 0;
		for (int i = 0; i < nums.length; i++)
			sum += nums[i];

		return sumOfN - sum;
	}

	/*
	 * Fraction to Recurring Decimal:
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
		if (numerator == 0) return "0";
		if (denominator == 0) return "NaN";

		StringBuilder sb = new StringBuilder();
		// Find the sign
		if (numerator < 0 ^ denominator < 0) sb.append("-");

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
		if (rem == 0) return sb.toString();

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

	private final String[]	lessThan10	= { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
			"Nine" };
	private final String[]	lessThan20	= { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
			"Seventeen", "Eighteen", "Nineteen" };
	private final String[]	lessThan100	= { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
			"Eighty", "Ninety" };

	public String numberToWords(int num) {
		if (num == 0) return "Zero";
		return numberToWordsHelper(num);
	}

	public String numberToWordsHelper(int num) {
		String result;
		if (num < 10) result = lessThan10[num];
		else if (num < 20) result = lessThan20[num - 10];
		else if (num < 100) result = lessThan100[num / 10] + " " + numberToWordsHelper(num % 10);
		else if (num < 1000) result = numberToWordsHelper(num / 100) + " Hundred " + numberToWordsHelper(num % 100);
		else if (num < 1000000)
			result = numberToWordsHelper(num / 1000) + " Thousand " + numberToWordsHelper(num % 1000);
		else if (num < 1000000000)
			result = numberToWordsHelper(num / 1000000) + " Million " + numberToWordsHelper(num % 1000000);
		else result = numberToWordsHelper(num / 1000000000) + " Billion " + numberToWordsHelper(num % 1000000000);

		return result.trim();
	}

	/*
	 * We can solve above problem by following approach – For each point p, calculate its slope with other points and use a
	 * map to record how many points have same slope, by which we can find out how many points are on same line with p as
	 * their one point. For each point keep doing the same thing and update the maximum number of point count found so far.
	 * 
	 * Some things to note in implementation are: 
	 * 1) if two point are (x1, y1) and (x2, y2) then their slope will be (y2 –	 * y1) / (x2 – x1) which can be a double value 
	 * and can cause precision problems. 
	 * 2) If we use HashMap in Java for storing the slope, then total time complexity of solution will be O(n^2)
	 * 
	 * (To get rid of the precision problems, we treat slope as pair ((y2 – y1), (x2 – x1)) instead of ratio and reduce pair by 
	 * their gcd before inserting into map. In below code points which are verticalPoints or repeated are treated separately.)
	 */
	public int maxPoints(Point[] points) {
		if (points == null || points.length == 0) return 0;

		HashMap<BigDecimal, Integer> result = new HashMap<>();
		int max = 0;

		for (int i = 0; i < points.length; i++) {
			int overlapPoints = 0;
			int verticalPoints = 0;
			BigDecimal slope = null;
			for (int j = i + 1; j < points.length; j++) {
				// handle overlapPointss and verticalPoints
				if (points[i].x == points[j].x) {
					if (points[i].y == points[j].y) overlapPoints++;
					else verticalPoints++;
				} else {
					// Double doesn't work for big precision
					// double slope = points[j].y == points[i].y ? 0.0 : (1.0 * (points[j].y - points[i].y)) /
					// (points[j].x - points[i].x);
					slope = getSlope(points[j], points[i]);
					result.put(slope, result.getOrDefault(slope, 0) + 1); // Additional One is consider the current
																			// point
				}
			}

			for (Integer count : result.values())
				max = Math.max(count + overlapPoints + 1, max);

			max = Math.max(verticalPoints + overlapPoints + 1, max);
			result.clear();
		}

		return max;
	}

	private BigDecimal getSlope(Point a, Point b) {
		// Slope: y2-y1/x2-x1;
		return BigDecimal.valueOf(b.y - a.y).divide(BigDecimal.valueOf(b.x - a.x), new MathContext(20));
	}

	/* Add Digits:
	 * The problem, widely known as digit root problem, has a congruence formula:
	 * For base b (decimal case b = 10), the digit root of an integer is:
	 *   dr(n) = 0 if n == 0 
	 *   dr(n) = (b-1) if n != 0 and n % (b-1) == 0
	 *   dr(n) = n mod (b-1) if n % (b-1) != 0
	 *    or
	 *   dr(n) = 1 + (n - 1) % 9
	 */

	public int addDigits1(int num) {
		return 1 + (num - 1) % 9;
	}

	// addDigits1 & addDigits are same
	public int addDigits2(int num) {
		if (num == 0) return 0;
		else if (num % 9 == 0) return 9;
		else return num % 9;
	}

	/*
	 * Nth Digit:
	 * sequence   1 2 3 4 5 6 7 8 9 1  0  1  1  1  2  1  3  1  4  1  5  1  6
	 * Nth digit: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
	 * sequence is the Nth digital, like the 11th digital is 0, 12 is 1, 13 is 1, 14 is 1, 15 is 2, 16 is 1, 17 is 3.........
	 * 	1-------9 9*1 = 9 digits
	 * 	10-----99 90 *2 = 180 digits
	 * 	100---999 900 * 3 = 2700 digits
	 * 
	 * Now, for example gave N = 1000, then 1000-9-180 = 811, it means the 811th digit local in [100, 999], and we know each number
	 * like 100 has three digit, so 811 / 3 = 270,
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
		int origNum = start + ((n - 1) / digits); // n-1 -> To find the index in the string
		String str = String.valueOf(origNum);
		return Character.getNumericValue(str.charAt((n - 1) % digits));
	}

	/*Lexicographical Numbers:
	 * To view this as a preorder tree traversal, each tree node may have at most 10 children (last digit is from 0~9),
	 * the difference is that we don't have to go rom left child to the right child through parent, because we know how
	 * to get child from child (which is add one), it's like we linked the level nodes before doing a preorder tree
	 * traversal
	 */
	public List<Integer> lexicalOrder(int n) {
		List<Integer> result = new ArrayList<>();
		int val = 1;
		for (int i = 1; i <= n; i++) {
			result.add(val);
			if (val * 10 <= n) { // going to the left most leaf as far as possible
				val *= 10;
			} else if (val % 10 != 9 && val + 1 <= n) { // found the left most, doing level traversal (all children from
														// left to right)
				val++;
			} else {
				while ((val / 10) % 10 == 9) // we hit the right most node on this level
					val /= 10; // go back to the upper level
				val = val / 10 + 1; // preparing to do the level traversal again on upper level
			}
		}

		return result;
	}
}

// GCD and LCM
// Prime Factorization and Divisors
// Fibonacci Numbers
// Catalan Numbers
// Modular Arithmetic
// Euler Totient Function
// nCr Computations
// Chinese Remainder Theorem
// Factorial
// Prime numbers and Primality Tests
// Sieve Algorithms
// Divisibility and Large Numbers
// Series
// Number Digits
