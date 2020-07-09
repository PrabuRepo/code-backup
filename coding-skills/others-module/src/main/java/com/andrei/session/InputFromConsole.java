package com.andrei.session;

import java.util.Arrays;
import java.util.Scanner;

public class InputFromConsole {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
	    System.out.println("Enter a number:\n");
	    int n = reader.nextInt(); // Scans the next token of the input as an int.
	    int q  = reader.nextInt();
	    //once finished
	    System.out.println("Entered element:"+n);
	    System.out.println("Entered element:"+q);
	    System.out.println("Enter sorted numbers:");
	    int[] array = new int[n];
	    for(int i = 0; i < n; i++)
            array[i] = reader.nextInt();
	    System.out.println("Entered array:\n"+Arrays.toString(array));
	    reader.close(); 
	    /*
	     scan.nextInt(); -> It reads Integer
         scan.nextDouble(); -> It reads double 
	    A call to scan.next(); returns the next token, a.
	    A call to scan.next(); returns the next token, b.
	    A call to scan.nextLine(); returns the next token, c. It's important to note that the scanner returns a space and a letter, because it's reading from the end of the last token until the beginning of the next line.
	    A call to scan.nextLine(); returns the contents of the next line, d e.
	    A call to scan.next(); returns the next token, f.
	    A call to scan.nextLine(); returns everything after f until the beginning of the next line; because there are no characters there, it returns an empty String.
	    A call to scan.nextLine(); returns g.*/

	}

}
