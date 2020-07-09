package com.practice.oop.methods;

import java.rmi.Naming;

/*
 * Step 6: Create and execute the client application program
 *   The last step is to create the client application program and execute it on a separate command prompt .The lookup method 
 *   of Naming class is used to get the reference of the Stub object.
 *   
 * Note: The below client and server program is executed on the same machine so localhost is used. In order to access the remote
 * object from another machine, localhost is to be replaced with the IP address where the remote object is present.
 * 
 * Important Observations:
 *   RMI is a pure java solution to Remote Procedure Calls (RPC) and is used to create distributed application in java.
 *   Stub and Skeleton objects are used for communication between client and server side.
 */
public class RMIClient {
	public static void main(String args[]) {
		String answer, value = "Reflection in Java	";
		try {
			// lookup method to find reference of remote object
			Search access = (Search) Naming.lookup("rmi://localhost:1900" + "/geeksforgeeks");
			answer = access.query(value);
			System.out.println("Article on " + value + " " + answer + " at GeeksforGeeks");
		} catch (Exception ae) {
			System.out.println(ae);
		}
	}
}
