package com.practice.oop.access.specifier2;

import com.practice.oop.access.specifier1.AccessSpecifierTest1;

/*
 * Access Modifiers: Class to test the Data members & Methods
 */
public class AccessSpecifierTest2 extends AccessSpecifierTest1 {
	public static void main(String[] args) {
		// Create instance of the AccessSpecifierTest1 class and access the methods
		AccessSpecifierTest2 ob = new AccessSpecifierTest2();
		System.out.println("4.Different Package Sub Class -----> ");
		System.out.println("Property accessibility: ");
		// System.out.println("Private Instance Variable:" + ob.privateInstanceVariable); // Throws Compilation Error
		// System.out.println("Default Instance Variable:" + ob.defaultInstanceVariable); // Throws Compilation Error
		System.out.println("Protected Instance Variable:" + ob.protectedInstanceVariable);
		System.out.println("Public Instance Variable:" + ob.publicInstanceVariable);

		System.out.println("Method accessibility: ");
		// ob.privateDisplay(); // Throws Compilation Error
		// ob.defaultDisplay(); // Throws Compilation Error
		ob.protectedDisplay();
		ob.publicDisplay();
	}
}

class AccessSpecifierTest3 {
	public static void main(String[] args) {
		// Create instance of the AccessSpecifierTest1 class and access the methods
		AccessSpecifierTest1 ob = new AccessSpecifierTest1();
		System.out.println("5.Different Package Non-Sub Class -----> ");
		System.out.println("Property accessibility: ");
		// System.out.println("Private Instance Variable:" + ob.privateInstanceVariable); // Throws Compilation Error
		// System.out.println("Default Instance Variable:" + ob.defaultInstanceVariable); // Throws Compilation Error
		// System.out.println("Protected Instance Variable:" + ob.protectedInstanceVariable); // Throws Compilation Error
		System.out.println("Public Instance Variable:" + ob.publicInstanceVariable);

		System.out.println("Method accessibility: ");
		// ob.privateDisplay(); // Throws Compilation Error
		// ob.defaultDisplay(); // Throws Compilation Error
		// ob.protectedDisplay(); // Throws Compilation Error
		ob.publicDisplay();
	}
}