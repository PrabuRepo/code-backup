package com.geeksforgeeks.school.problems;

public class SchoolProblems {
	public static void main(String[] args) {
		SchoolProblems ob = new SchoolProblems();

		// ob.testBasicProblems();

		// ob.testMathematical();

		// ob.testPatternPrinting();

		// ob.testMatrix();

		// ob.testStringProblems();

		ob.testArrayProblems();
	}

	static float PI = 3.142f;
	// ASCII values of alphabets: A – Z = 65 to 90, a – z = 97 to 122
	static int	ALPHABET_SIZE		= 26;
	static int	LOWER_CASE_START	= 97;
	static int	LOWER_CASE_END		= 122;
	static int	UPPER_CASE_START	= 65;
	static int	UPPER_CASE_END		= 90;

	/* **************1.Basic************/
	// Java only supports call by value
	void swapCallByValue(int a, int b) {
		int temp = b;
		b = a;
		a = temp;
		System.out.println("After swap(Inside Method):" + "a:" + a + ", b:" + b);
	}

	boolean isLeapYear(int year) {
		boolean flag = false;
		if (year % 400 == 0) // If year is century(like 1700, 1800 etc..), then it should be divisible by 100
			flag = true;
		else if (year % 100 != 0 && year % 4 == 0) // Other than century, simply divisible by 4
			flag = true;
		return flag;
	}

	void floydsTriangle(int n) {
		int count = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(count++ + " ");
			}
			System.out.println();
		}
	}

	/* **************2.Mathematical************/
	float areaOfCircle(int radius) {
		return PI * radius * radius;
	}

	double areaOfTriangle(int a, int b, int c) {
		int s = 0;
		if (a > 0 && b > 0 && c > 0) {
			s = (a + b + c) / 2;
		}
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));
	}

	int factorial(int n) {
		if (n <= 1)
			return 1;
		return n * factorial(n - 1);
	}

	// GCD or HCF calculation using Euclid's Algorithm
	int hcf(int a, int b) {
		if (a == 0)
			return b;
		return hcf((b % a), a);
	}

	int lcm(int a, int b) {
		return (a * b) / hcf(a, b);
	}

	int fibonacci(int n) {
		if (n <= 1)
			return n;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	void fibIterative(int n) {
		int f0 = 0, f1 = 1, next;
		System.out.print(f1 + " ");
		for (int i = 2; i <= n; i++) {
			next = f0 + f1;
			f0 = f1;
			f1 = next;
			System.out.print(next + " ");
		}
	}

	// f(n)= (1) + (2*3) + (4*5*6) … n
	long seriesSum(int n) {
		return seriesSum(n, 1, 1);
	}

	private long seriesSum(int n, int index, int count) {
		if (index > n)
			return 0;
		long result = 1;
		for (int i = 1; i <= index; i++)
			result *= count++;
		return result + seriesSum(n, ++index, count);
	}

	/* **************3.Pattern Printing************/
	void printInverseDiamondPattern(int n) {
		int firstHalf = 0, secondHalf = 0;
		firstHalf = secondHalf = n;
		for (int i = 0; i < (2 * n); i++) {
			if (i < n) {
				firstHalf = n - i;
				secondHalf = n + i;
			} else if (i > n) {
				++firstHalf;
				--secondHalf;
			}
			for (int j = 0; j < (2 * n); j++) {
				if (j < firstHalf || j >= secondHalf) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	void printDiamondPattern(int n) {

	}

	/* **************4.Array************/
	public int findLargest(int[] a) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (max < a[i])
				max = a[i];
		}
		return max;
	}

	public int sumOfElementsInArray(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		return sum;
	}

	public int mulOfElementsInArray(int[] a) {
		int result = 1;
		for (int i = 0; i < a.length; i++)
			result *= a[i];
		return result;
	}

	public void clockwiseRotationByOne(int[] a) {
		System.out.println("Before rotation:");
		printArray(a);
		int temp = a[a.length - 1];
		for (int i = a.length; i > 0; i--)
			a[i] = a[i - 1];
		a[0] = temp;
		System.out.println("After rotation:");
		printArray(a);
	}

	public void printSumTraingle(int[] a) {
		int size = a.length;
		int[][] result = new int[size][size];

		for (int i = 0; i < size; i++) {
			result[size - 1][i] = a[i];
		}
		for (int i = size - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				result[i][j] = result[i + 1][j] + result[i + 1][j + 1];
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}

	}

	private void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	/* **************5.Matrix************/

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

	public void printSpiralForm(int[][] a) {
		int rowStart = 0, rowEnd = a.length - 1, colStart = 0, colEnd = a[0].length - 1;
		while (rowStart <= rowEnd && colStart <= colEnd) {
			for (int i = colStart; i <= colEnd; i++)
				System.out.print(a[rowStart][i] + " ");
			rowStart++;

			for (int i = rowStart; i <= rowEnd; i++)
				System.out.print(a[i][colEnd] + " ");
			colEnd--;

			if (rowStart <= rowEnd) {// First two iterations are just below while condition, so dont need to check
										// this condition for those scenarios
				for (int i = colEnd; i >= colStart; i--)
					System.out.print(a[rowEnd][i] + " ");
				rowEnd--;
			}

			if (colStart <= colEnd) {
				for (int i = rowEnd; i >= rowStart; i--)
					System.out.print(a[i][colStart] + " ");
				colStart++;
			}
		}
	}

	// This will work only for same row and column size
	public void prinitDiagonalPattern(int[][] a) {
		int rowSize = a.length, colSize = a[0].length, index = 1, iterCount = -1, count = 0, i;
		int rIndex = 0, cIndex = 0, min = 0, max = -1;
		while (index++ < rowSize + colSize) {
			if (count < rowSize) { // Increase rowCount iterCount rowSize, after that decrease it;
				min = 0;
				max++;
				iterCount++;
			} else {
				if (count == rowSize)
					min = 0;
				min++;
				max = colSize - 1;
				iterCount--;
			}

			if (count % 2 == 0) { // If it is even, iterate in decreasing order
				rIndex = max;
				cIndex = min;
				for (int k = 0; k <= iterCount; k++)
					System.out.print(a[rIndex--][cIndex++] + " ");
			} else { // If it odd, iterate in increasing order
				rIndex = min;
				cIndex = max;
				for (int k = 0; k <= iterCount; k++)
					System.out.print(a[rIndex++][cIndex--] + " ");
			}
			count++;
		}

	}

	void printMatrix(int[][] data) {
		int row = data.length;
		int col = data[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

	/* **************6.Strings************/
	public void asciiValuesOfAlphabet() {
		System.out.println("Lower case alphbets and corresponding ascii values:");
		for (int i = LOWER_CASE_START; i < LOWER_CASE_START + 26; i++)
			System.out.println((char) i + "-" + i);
		System.out.println("Upper case alphbets and corresponding ascii values:");
		for (int i = UPPER_CASE_START; i < UPPER_CASE_START + 26; i++)
			System.out.println((char) i + "-" + i);

		// Way to find the ascii values of any character
		// System.out.println("Ascii values of char:" + (int) 'a');
	}

	public void stringOppositeCase(String s) {
		int asciiDiff = (LOWER_CASE_START - UPPER_CASE_END) - 1;
		int noOfAlphabets = 26;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int asciiValue = (int) ch;
			if (ch >= 'a' && ch <= 'z') {
				System.out.print((char) (asciiValue - noOfAlphabets - asciiDiff));
			} else if (ch >= 'A' && ch <= 'Z') {
				System.out.print((char) (asciiValue + noOfAlphabets + asciiDiff));
			} else {
				System.out.print(ch);
			}
		}
	}

	public void stringSplitAPI(String str) {
		System.out.println("String Split API:");
		String[] array = str.split("~");
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}

	// Pangram is a sentence containing every letter in the English alphabet
	public boolean isPanagram(String str) {
		boolean[] array = new boolean[ALPHABET_SIZE];
		int alphaIndex;
		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (ch >= 'a' && ch <= 'z') {
				alphaIndex = (int) ch - 'a';
				if (!array[alphaIndex])
					array[alphaIndex] = true;
			}
		}

		for (int i = 0; i < array.length; i++) {
			if (!array[i])
				return false;
		}

		return true;
	}

	public void missingCharsInStringPanagram(String str) {
		boolean[] array = new boolean[ALPHABET_SIZE];
		int alphaIndex;
		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (ch >= 'a' && ch <= 'z') {
				alphaIndex = (int) ch - 'a';
				if (!array[alphaIndex])
					array[alphaIndex] = true;
			}
		}

		for (int i = 0; i < array.length; i++) {
			if (!array[i]) {
				System.out.print((char) (i + LOWER_CASE_START));
			}
		}
	}

	public boolean isPalindrome(String s) {
		int last = s.length() - 1, first = 0;
		boolean flag = true;
		while (first <= last) {
			if (s.charAt(first++) != s.charAt(last--)) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	/* **************7.Date and Time************/

	/* **************8.Misc************/

	public void testBasicProblems() {
		// Problem 1:
		int a = 2, b = 7;
		System.out.println("Before Method call:" + "a:" + a + ", b:" + b);
		swapCallByValue(a, b);
		System.out.println("After Method call:" + "a:" + a + ", b:" + b);

		// Problem 2:
		System.out.println("Is leap year:" + isLeapYear(2004));

		// Problem 3:
		floydsTriangle(6);
	}

	public void testMathematical() {

		// Problem 1:
		System.out.println("Area of Circle:" + areaOfCircle(5));

		// Problem 2:
		System.out.println("Area of triangle:" + areaOfTriangle(3, 4, 5));

		// Problem 3:
		System.out.println("Factorial:" + factorial(5));

		// Problem 4:
		System.out.println("GCD or HCF:" + hcf(36, 60));

		// Problem 5:
		System.out.println("LCM:" + lcm(15, 25));

		// Problem 6:

		// Problem 7:
		System.out.println("Value of function f(n):" + seriesSum(4));

		// Problem 8:
		// Fibonacci - Recursive
		System.out.println("Fib number:" + fibonacci(9));
		// Fibonacci Iterative
		fibIterative(9);
	}

	void testPatternPrinting() {
		printInverseDiamondPattern(5);
	}

	void testMatrix() {
		int[][] a1 = { { 12, 5, 8 }, { 11, 9, 16 }, { 17, 18, 19 } };
		int[][] b1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] a2 = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
		int[][] b2 = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
		int[][] a3 = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 } };
		int[][] test = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 }, { 13, 14, 15, 16, 17, 18 } };

		int[][] result;

		// Problem 1:
		result = addMatrix(a2, b2);
		System.out.println("Matrix addition:");
		printMatrix(result);

		// Problem 2:
		result = subMatrix(a1, b1);
		System.out.println("Matrix subraction:");
		printMatrix(result);

		// Problem 4:
		result = mulMatrix(a2, b2);
		System.out.println("Matrix Multiplication:");
		printMatrix(result);

		// Problem 5:
		System.out.println("Original Matrix:");
		printMatrix(a3);
		System.out.println("Transpose of matrix:");
		printMatrix(transposeMatrix(a3));

		// Problem 6:
		System.out.println("Original Matrix:");
		printMatrix(test);
		System.out.println("Matrix Spiral Form:");
		printSpiralForm(test);

		// Problem 7:
		System.out.println("\nOriginal Matrix:");
		printMatrix(a2);
		System.out.println("Matrix diagonal pattern:");
		prinitDiagonalPattern(a2);
	}

	public void testStringProblems() {
		// Problem for understanding ascii values of alphabet
		asciiValuesOfAlphabet();

		// String Split API
		stringSplitAPI("test1");

		// Problem 1:
		System.out.println("Convert characters of a string to opposite case");
		stringOppositeCase("Hello Every One");

		// Problem 2:
		System.out.println("\nPanagram missing characters:");
		missingCharsInStringPanagram("The quick brown fox jumps");
		System.out.println("\nCheck given string is panagram?" + isPanagram("The quick brown fox jumps over the lazy dog"));

		// Problem 3:
		System.out.println("Check given string is Palindrome?" + isPalindrome("ABCDCBA"));
	}

	public void testArrayProblems() {
		// Problem 1:
		int[] arr = { 3, 6, 12, 100, 7, 21, 5 };
		System.out.println("Find the largest element in the array:" + findLargest(arr));

		System.out.print("Print Sum Triangle:\n");
		int[] a = { 4, 7, 3, 6, 7 };
		printSumTraingle(a);
	}
}
