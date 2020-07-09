package com.basic.datastructures.java.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IOAPI {

	public static void main(String[] args) {
		IOAPI ob = new IOAPI();
		// Reading from Console: Prefer to use Scanner & BufferedReader
		ob.scannerSample();

		ob.bufferedReaderSample();

		ob.customFastReaderSample();

		// Input/Output from external file :
		ob.externalFileReadingWritingSample();

	}

	/*
	 * Scanner Class – (easy, less typing, but not recommended very slow, refer this for reasons of slowness): In most of the cases we get TLE 
	 * while using scanner class. It uses built-in nextInt(), nextLong(), nextDouble methods to read the desired object after initiating scanner
	 * object with input stream.(eg System.in). 
	 */
	public void scannerSample() {
		System.out.println("Scanner Sample: ");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int k = s.nextInt();
		int count = 0;
		while (n-- > 0) {
			int x = s.nextInt();
			if (x % k == 0)
				count++;
		}
		System.out.println(count);
		s.close();
	}

	/* BufferedReader – (fast, but not recommended as it requires lot of typing): The Java.io.BufferedReader class reads text from a character-input stream,
	 * buffering characters so as to provide for the efficient reading of characters, arrays, and lines. With this method we will have to parse the value 
	 * every time for desired type. Reading multiple words from single line adds to its complexity because of the use of Stringtokenizer and hence this is
	 * not recommended. This gets accepted with a running time of approx 0.89 s.but still as you can see it requires a lot of typing all together and
	 * therefore method 3 is recommended.
	 */
	public void bufferedReaderSample() {
		System.out.println("\nBuffered Reader Sample: ");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int count = 0;
			while (n-- > 0) {
				int x = Integer.parseInt(br.readLine());
				if (x % k == 0)
					count++;
			}
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Userdefined FastReader Class- (which uses bufferedReader and StringTokenizer): This method uses the time advantage of BufferedReader and 
	 * StringTokenizer and the advantage of user defined methods for less typing and therefore a faster input altogether. This gets accepted with
	 * a time of 1.23 s and this method is very much recommended as it is easy to remember and is fast enough to meet the needs of most of the question 
	 * in competitive coding.
	 */
	public void customFastReaderSample() {
		System.out.println("\nCustome FastReader Sample: ");
		FastReader s = new FastReader();
		int n = s.nextInt();
		int k = s.nextInt();
		int count = 0;
		while (n-- > 0) {
			int x = s.nextInt();
			if (x % k == 0)
				count++;
		}
		System.out.println(count);
	}

	/*
	 * Input/Output from external file :
	 * In Java, we can use BufferedReader class for the fast Input and PrintWriter class for formatted representation to the output along with
	 * FileReader and FileWriter class.
	 * FileReader(String filename): This constructor creates a new FileReader, and instructs the parser to read file from that directory. The file
	 * must exist in that specified location.
	 * FileWriter(String fileName): This constructor creates a FileWriter object, to the specified location.
	 */
	public void externalFileReadingWritingSample() {
		System.out.println("\nFile Reading Sample: ");
		// BufferedReader Class for Fast buffer Input
		try (FileReader reader = new FileReader("input.txt")) {
			BufferedReader br = new BufferedReader(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nFile Writing Sample: ");
		// PrintWriter class prints formatted representations of objects to a text-output stream.
		try (FileWriter writer = new FileWriter("output.txt")) {
			PrintWriter pw = new PrintWriter(writer);
			// Your code goes Here
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}

}