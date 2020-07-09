package com.practice.io;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleInputExamples {
	public static void main(String[] args) {
		usingConsoleReader();
		usingBufferedReader();
		usingScanner();
		usingScannerTypeUnsafe();
	}

	// 1) Read console input using java.io.Console instance
	private static void usingConsoleReader() {
		Console console = null;
		String inputString = null;
		try {
			// creates a console object
			console = System.console();
			// if console is not null
			if (console != null) {
				// read line from the user input
				inputString = console.readLine("Name: ");
				// prints
				System.out.println("Name entered : " + inputString);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 2) Read console input using BufferedReader instance
	private static void usingBufferedReader() {
		System.out.println("Name: ");
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String inputString = bufferRead.readLine();

			System.out.println("Name entered : " + inputString);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// 3) Read console input using Scanner instance
	private static void usingScanner() {
		System.out.println("Name: ");

		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.nextLine();

		scanner.close();
		System.out.println("Name entered : " + inputString);
	}

	private static void usingScannerTypeUnsafe() {

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter your age as an integer > ");

		int age = scanner.nextInt();
		System.out.println("Your age is " + age);

		scanner.close();
	}
}