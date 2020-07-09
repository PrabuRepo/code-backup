package com.web.hackersrank.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Java30daysofcode {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		int temp, noOfSwaps=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n-1;j++) {
				if(a[j]>a[j+1]) {
					temp = a[j+1];
					a[j+1]=a[j];
					a[j]=temp;
					noOfSwaps++;
				}
			}
		}
		System.out.println("Array is sorted in "+noOfSwaps+" swaps.");
		System.out.println("First Element:"+a[0]);
		System.out.println("Last Element:"+a[n-1]);
		in.close();
	}

	public void interfaceSample() {

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();

		AdvancedArithmetic myCalculator = new Calculator();
		int sum = myCalculator.divisorSum(n);
		System.out.println("I implemented: " + myCalculator.getClass().getInterfaces()[0].getName());
		System.out.println(sum);

	}

	public void exceptionSample() {

		Scanner in = new Scanner(System.in);
		String S = in.next();

		try {
			int data = Integer.parseInt(S);
			System.out.println(data);
		} catch (NumberFormatException e) {
			System.out.println("Bad String");
			// e.printStackTrace();
		}
		in.close();

	}

	public void inheritanceSample() {

		Scanner scan = new Scanner(System.in);
		String firstName = scan.next();
		String lastName = scan.next();
		int id = scan.nextInt();
		int numScores = scan.nextInt();
		int[] testScores = new int[numScores];
		for (int i = 0; i < numScores; i++) {
			testScores[i] = scan.nextInt();
		}
		scan.close();
		Student s = new Student(firstName, lastName, id, testScores);
		s.printPerson();
		System.out.println("Grade: " + s.calculate());

	}

	static int hourGlassSum(int rmin, int rmax, int cmin, int cmax, int[][] a) {
		int total = 0, count = 0;
		for (int i = rmin; i <= rmax; i++) {
			for (int j = cmin; j <= cmax; j++) {
				count++;
				if (!(count == 4 || count == 6)) {
					total += a[i][j];
					System.out.print(count + ":" + i + "," + j + "-" + a[i][j] + "; ");
				}
			}
		}
		System.out.println();
		return total;
	}

	public void twoDimArray() {

		int[][] a = new int[6][6];
		int rmin, rmax, cmin, cmax, sum = 0, max = 0;
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				a[i][j] = in.nextInt();
			}
		}

		rmin = 0;
		rmax = 2;
		for (int i = 0; i < 4; i++) {
			cmin = 0;
			cmax = 2;
			for (int j = 0; j < 4; j++) {
				System.out.println("rmin:" + rmin + "; " + "rmax:" + rmax + " | cmin:" + cmin + "; " + "cmax:" + cmax);
				sum = hourGlassSum(rmin, rmax, cmin, cmax, a);
				cmin++;
				cmax++;
				if (sum > max)
					max = sum;

				System.out.println("Sum:" + sum + "; " + "Max:" + max);
			}
			rmin++;
			rmax++;
		}

		System.out.println(max);
		in.close();

	}

	public void consecutiveOnes() {
		int count = 0, temp = 0;
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while (n > 0) {
			if (n % 2 == 1)
				temp++;
			else {
				if (temp > count)
					count = temp;
				temp = 0;
			}
			n = n / 2;
		}
		System.out.println(count);
		in.close();
	}

	static int factorial(int n) {
		return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
	}

	public void testFactorials() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int result = factorial(n);
		System.out.println(result);

	}

	public void phoneBook() {
		Scanner in = new Scanner(System.in);
		Map<String, Integer> dict = new HashMap<>();
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			String name = in.next();
			int phone = in.nextInt();
			dict.put(name, phone);
		}
		while (in.hasNext()) {
			String s = in.next();
			if (dict.containsKey(s)) {
				System.out.println(s + "=" + dict.get(s));
			} else {
				System.out.println("Not found");
			}
		}
		in.close();
	}
}

class Person {
	protected String firstName;
	protected String lastName;
	protected int idNumber;

	// Constructor
	Person(String firstName, String lastName, int identification) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = identification;
	}

	// Print person data
	public void printPerson() {
		System.out.println("Name: " + lastName + ", " + firstName + "\nID: " + idNumber);
	}
}

class Student extends Person {
	private int[] testScores;

	/*
	 * Class Constructor
	 * 
	 * @param firstName - A string denoting the Person's first name.
	 * 
	 * @param lastName - A string denoting the Person's last name.
	 * 
	 * @param id - An integer denoting the Person's ID number.
	 * 
	 * @param scores - An array of integers denoting the Person's test scores.
	 */
	Student(String firstName, String lastName, int identification, int[] scores) {
		super(firstName, lastName, identification);
		this.testScores = scores;
	}

	/*
	 * Method Name: calculate
	 * 
	 * @return A character denoting the grade.
	 */
	public char calculate() {
		char grade = 0;
		int total = 0;
		float avg;
		for (int i = 0; i < testScores.length; i++)
			total += testScores[i];
		avg = total / testScores.length;
		if (avg >= 90 && avg <= 100)
			grade = 'O';
		else if (avg >= 80 && avg < 90)
			grade = 'E';
		else if (avg >= 70 && avg < 80)
			grade = 'A';
		else if (avg >= 55 && avg < 70)
			grade = 'P';
		else if (avg >= 40 && avg < 55)
			grade = 'D';
		else
			grade = 'T';
		return grade;
	}
}

class Calculator implements AdvancedArithmetic {
	int power(int n, int p) throws Exception {
		if (n >= 0 && p >= 0) {
			return (int) Math.pow((double) n, (double) p);
		} else {
			throw new Exception("n and p should be non-negative");
		}
	}

	@Override
	public int divisorSum(int n) {
		int total = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				total += i;
			}
		}
		return total;
	}
}

interface AdvancedArithmetic {
	int divisorSum(int n);
}