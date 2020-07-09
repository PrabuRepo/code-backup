package com.basic.algorithms;

public class MathProblems {
	// Reverse Integer
	public int reverse1(int n) {
		int rev = 0, curr = Math.abs(n);
		boolean flag = n < 0 ? true : false;
		int MAX = Integer.MAX_VALUE / 10;
		while (curr != 0) {
			int rem = curr % 10;
			// Integer Overflow Condition:
			if (rev > MAX || (rev == MAX && rem > MAX % 10))
				return 0;
			rev = rev * 10 + rem;
			curr /= 10;
		}

		return flag == false ? rev : -rev;
	}

	public int reverse2(int x) {
		int result = 0, rem = Math.abs(x);
		while (rem != 0) {
			int tail = rem % 10;
			int rev = (result * 10) + tail;
			if ((rev - tail) / 10 != result)
				return 0; // If rev overflows, this condition success and returns 0;
			result = rev;
			rem /= 10;
		}

		return x < 0 ? -result : result;
	}

	// Palindrome Number
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;

		int rem = x, result = 0;
		while (rem >= 10) {
			result *= 10;
			result += rem % 10;
			rem /= 10;
		}

		return result == x / 10 && rem == x % 10;
	}

	// Count Primes
	public int countPrimes(int n) {
		if (n <= 2)
			return 0;
		int count = 1;
		for (int i = 3; i < n; i++) {
			if (isPrime(i))
				count++;
		}
		return count;
	}

	private boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;
		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	// Pow(x, n)
	public double myPow(double x, int n) {
		return (n < 0) ? 1 / pow(x, -n) : pow(x, n);
	}

	public double pow(double x, int n) {
		if (n == 0)
			return 1.0;
		if (n == 1)
			return x;

		if (n % 2 == 0) {
			double val = pow(x, n / 2);
			return val * val;
		}

		return x * myPow(x, n - 1);
	}

	// Sqrt(x):
	// Simple Approach: It doesnt work for high values
	/*The squareroot of a (non-negative) number N always lies between 0 and N/2. The straightforward way to solve this problem would be to check every number k between 0 and N/2, until the square of k becomes greater than or rqual to N.  */
	public int mySqrt1(int n) {
		if (n == 0 || n == 1)
			return n;
		int i = 1, multiply = 1;
		while (multiply <= n / 2) {
			i++;
			multiply = i * i;
		}
		return i * i == n ? i : (i - 1);
	}

	// Modification of Binary Search
	public int mySqrt(int n) {
		if (n == 0 || n == 1)
			return n;

		int low = 1, high = n / 2, div = 0, mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			div = n / mid;
			if (mid == div)
				return mid;
			else if (mid > div)
				high = mid - 1;
			else
				low = mid + 1;

		}
		return low - 1;
	}

	public int sqrt3(int A) {
		if (A <= 1)
			return A;

		int l = 2, h = A / 2, m = 0, resut = 1;
		while (l <= h) {
			m = l + (h - l) / 2;

			long sq = (long) m * (long) m;
			if (sq == A)
				return m;
			else if (sq < A) {
				resut = m;
				l = m + 1;
			} else {
				h = m - 1;
			}
		}

		return resut;
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
		if (b == 0)
			return a;
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
		if (a == 0 || b == 0)
			return 0;

		// base case
		if (a == b)
			return a;

		// a is greater
		if (a > b)
			return gcd3(a - b, b);
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

	/*
	 * 2.Binary to String: Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print the binary 
	 * representation. If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR:'
	 */
	// Approach1:
	public String decimalToBinary1(double n) {
		if (n >= 1)
			return "ERROR";

		StringBuilder sb = new StringBuilder();
		double r;
		while (n > 0) {
			r = n * 2;
			if (r >= 1) {
				sb.append("1");
				n = r - 1;
			} else {
				sb.append("0");
				n = r;
			}

			if (sb.length() >= 32) {
				// return "ERROR";
				return sb.toString();
			}
		}
		return sb.toString();
	}

	// Approach2:
	public String decimalToBinary2(double n) {
		if (n >= 1)
			return "ERROR";

		StringBuilder sb = new StringBuilder();
		double frac = 0.5;
		while (n > 0) {
			if (n >= frac) {
				sb.append("1");
				n -= frac;
			} else {
				sb.append("0");
			}

			if (sb.length() >= 32) {
				// return "ERROR";
				return sb.toString();
			}
			frac /= 2;
		}
		return sb.toString();
	}

	/* String to Integer (atoi): Implement atoi which converts a string to an integer.
	 */
	public int myAtoi(String str) {
		if (str.isEmpty())
			return 0;
		int sign = 1, base = 0, i = 0, n = str.length();
		while (i < n && str.charAt(i) == ' ')
			i++;
		if (i == n)
			return 0;
		if (str.charAt(i) == '-' || str.charAt(i) == '+')
			sign = str.charAt(i++) == '-' ? -1 : 1;
		while (i < str.length() && str.charAt(i) >= '0'
				&& str.charAt(i) <= '9') {
			if (base > Integer.MAX_VALUE / 10
					|| (base == Integer.MAX_VALUE / 10
							&& str.charAt(i) - '0' > 7)) {
				return (sign == 1) ? Integer.MAX_VALUE
						: Integer.MIN_VALUE;
			}
			base = 10 * base + (str.charAt(i++) - '0');
		}
		return base * sign;
	}

	/* String to Integer (atoi): Implement atoi which converts a string to an integer.
	 */
	public int atoi(final String str) {
		if (str == null || str.length() == 0)
			return -1;

		int i = 0, n = str.length(), result = 0, sign = 1;
		// Skip space
		while (i < n && str.charAt(i) == ' ')
			i++;

		// Find sign
		if (i < n && str.charAt(i) == '+'
				|| str.charAt(i) == '-') {
			sign = str.charAt(i) == '-' ? -1 : 1;
			i++;
		}

		// If garbage char, return result
		if (i < n && !Character.isDigit(str.charAt(i)))
			return 0;

		while (i < n) {
			char ch = str.charAt(i);
			if (ch == ' ' || !Character.isDigit(ch))
				break;

			// int parseVal = ch - '0';
			int parseVal = Integer
					.parseInt(String.valueOf(ch));

			// Integer Overflow condition:
			if (sign == 1
					&& isIntOverFlow(result, parseVal, 7))
				return Integer.MAX_VALUE;
			if (sign == -1
					&& isIntOverFlow(result, parseVal, 8))
				return Integer.MIN_VALUE;

			result = result * 10 + parseVal;
			i++;
		}

		return result * sign;
	}

	private boolean isIntOverFlow(int num, int nextVal,
			int expected) {
		return (num > Integer.MAX_VALUE / 10
				|| (num == Integer.MAX_VALUE / 10
						&& nextVal > expected));
	}
}