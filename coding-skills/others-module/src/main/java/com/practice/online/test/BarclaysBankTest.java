package com.practice.online.test;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import com.practice.online.test.output.input;

public class BarclaysBankTest {

}

class newthread implements Runnable {
	Thread t;

	newthread() {
		t = new Thread(this, "Hello");
		t.start();
	}

	public void run() {
		System.out.println(t.getName());
	}
}

class multithreaded_programing {
	public static void main(String args[]) {
		// new newthread();
		String s;
		int a1;
		s = "Foolish boy.";
		a1 = s.indexOf("Fool");
		System.out.println(a1);

		int var1 = 2;
		int var2 = ~var1;
		System.out.print(var1 + " " + var2);

		/*int x = 0;
		while (0) {
			System.out.print("x plus one is " + (x + 1));
		}*/

		String s1 = "Hello i love java";
		String s2 = new String(s1);
		System.out.println((s1.equals(s2)));

		double a, b;
		a = 3.0;
		b = 4.0;
		double c = Math.sqrt(a * a + b * b);
		System.out.println(c);

		char var3 = 'A';
		char var4 = 'a';
		System.out.println((int) var3 + (int) var4);

		System.out.println("****");
		// output.input m = new output.input();
		// output.input m = new input();
		input m = new output.input();
		m.foo();

		System.out.println("*******");

		Object o = new Float(3.14F);
		Object[] oa = new Object[1];
		oa[0] = o;
		o = null;
		oa[0] = null;
	}
}

class output {

	public static class input {
		public static void foo() {
			System.out.println("foo method!");
		}
	}
}

class Code10 {
	{
		final Vector v;
		v = new Vector();
	}

	public Code10() {
	}

	public void codeMethod() {
		// System.out.println(v.isEmpty()); //Compilation Error
	}

	public static void main(String args[]) {
		new Code10().codeMethod();
	}
}

class Test1 {
	// Illegal modifier for the constructor in type Test; only public, protected & private are permitted
	/*	static Test() {
	
		}*/

	public static void l(int i, int j) {
		i <<= j;
		System.out.println("i:" + i);
	}

	public static void main(String args[]) {
		int i = 3, j = 2;
		l(i, j);
		System.out.println(i);
	}
}

class Test implements Runnable {
	Integer id = 1;

	public static void main(String[] args) {
		new Thread(new Test()).start();
		new Thread(new Test()).start();
		new Thread(new Test()).start();
	}

	public void run() {
		press(id);
	}

	synchronized void press(Integer id) {
		System.out.print(id.intValue());
		System.out.print((++id).intValue());
	}
}

// A Java program to calculate max sum lengths of
// non overlapping contiguous subarrays with k as
// max element
class GFG {
	// Returns max sum of lengths with maximum element
	// as k
	static int calculateMaxSumLength(int arr[], int n, int k) {
		int ans = 0; // final sum of lengths

		// number of elements in current subarray
		int count = 0;

		// variable for checking if k appeared in subarray
		int flag = 0;

		for (int i = 0; i < n;) {
			count = 0;
			flag = 0;

			// count the number of elements which are
			// less than equal to k
			while (i < n && arr[i] <= k) {
				count++;
				if (arr[i] == k)
					flag = 1;
				i++;
			}

			// if current element appeared in current
			// subarray add count to sumLength
			if (flag == 1)
				ans += count;

			// skip the array elements which are
			// greater than k
			while (i < n && arr[i] > k)
				i++;
		}
		return ans;
	}

	// driver program to test above method
	public static void main(String[] args) {

		// int arr[] = { 4, 5, 7, 1, 2, 9, 8, 4, 3, 1 };
		// int[] arr = { 7, 2, 3, 1, 2, 3, 1, 6, 2, 3 };
		int[] arr = { 1, 2, 3, 2, 3, 4, 1 };
		int size = arr.length;
		int k = 4;
		int ans = calculateMaxSumLength(arr, size, k);
		System.out.println("Max Length :: " + ans);
	}
}

class TestClass {
	// https://www.geeksforgeeks.org/maximum-sum-lengths-non-overlapping-subarrays-k-max-element/
	public static int maxLenSubArray(int n, int k, int[] x) {
		int result = 0, len = 0, i = 0;
		boolean isPresent;
		while (i < n) {
			len = 0;
			isPresent = false;
			while (i < n && x[i] <= k) {
				len++;
				if (x[i] == k)
					isPresent = true;
				i++;
			}

			if (isPresent)
				result += len;

			while (i < n && x[i] > k)
				i++;
		}
		return result;
	}

	public static void main(String args[]) throws Exception {
		/* Sample code to perform I/O:
		 * Use either of these methods for input
		
		//BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();                // Reading input from STDIN
		System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
		*/
		// Scanner
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int k = in.nextInt();
			int[] X = new int[n];
			for (int i = 0; i < n; i++) {
				X[i] = in.nextInt();
			}
			System.out.println(maxLenSubArray(n, k, X));
		}

	}
}

class A1 {
	public A1() throws IOException {

	}
}

// Below class throws the exception
/*class B1 extends A1 {
	public B1() {

	}
}*/

class A2 {
	public A2() {

	}
}

// Below class throws the exception
class B2 extends A2 {
	public B2() throws IOException {

	}
}
/*
Farthest from zero
Given an integer array A of size N. Write a program to print the farthest element from 0.

If there are multiple elements, print the number with lesser value.

Input format
The first line contains a single integer N denoting the size of the array A.
The next line contains N integers denoting the elements of array A.

Output format
Print the farthest element from 0.*/