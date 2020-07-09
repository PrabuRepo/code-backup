package com.practice.oop.access.specifier1;

/*
 * Access Modifiers: Class to test the Data members & Methods
 */
public class AccessSpecifierTest1 {

	private int		privateInstanceVariable		= 10;
	int				defaultInstanceVariable		= 20;
	protected int	protectedInstanceVariable	= 30;
	public int		publicInstanceVariable		= 40;

	// Private Access Specifier method
	private void privateDisplay() {
		System.out.println("Private Access Specifier method");
	}

	// Default Access Specifier method
	void defaultDisplay() {
		System.out.println("Default Access Specifier method");
	}

	// Protected Access Specifier method
	protected void protectedDisplay() {
		System.out.println("Protected Access Specifier method");
	}

	// Public Access Specifier method
	public void publicDisplay() {
		System.out.println("Public Access Specifier method");
	}

	public static void main(String[] args) {
		AccessSpecifierTest1 ob = new AccessSpecifierTest1();
		System.out.println("1.Same Class -----> ");
		System.out.println("Property accessibility: ");
		System.out.println("Private Instance Variable:" + ob.privateInstanceVariable);
		System.out.println("Default Instance Variable:" + ob.defaultInstanceVariable);
		System.out.println("Protected Instance Variable:" + ob.protectedInstanceVariable);
		System.out.println("Public Instance Variable:" + ob.publicInstanceVariable);

		System.out.println("Method accessibility: ");
		ob.privateDisplay();
		ob.defaultDisplay();
		ob.protectedDisplay();
		ob.publicDisplay();

	}
}

class Test1 extends AccessSpecifierTest1 {
	public static void main(String[] args) {
		// Sub Class inherits methods from Parent class
		Test1 ob = new Test1();
		System.out.println("2.Same Package Sub Class -----> ");
		System.out.println("Property accessibility: ");
		// System.out.println("Private Instance Variable:" + ob.privateInstanceVariable); // Throws Compilation Error
		System.out.println("Default Instance Variable:" + ob.defaultInstanceVariable);
		System.out.println("Protected Instance Variable:" + ob.protectedInstanceVariable);
		System.out.println("Public Instance Variable:" + ob.publicInstanceVariable);

		System.out.println("Method accessibility: ");
		// ob.privateDisplay(); // Throws Compilation Error
		ob.defaultDisplay();
		ob.protectedDisplay();
		ob.publicDisplay();
	}
}

class Test2 {
	public static void main(String[] args) {
		// Create instance of the AccessSpecifierTest1 class and access the methods
		AccessSpecifierTest1 ob = new AccessSpecifierTest1();
		System.out.println("3.Same Package Non-Sub Class -----> ");
		System.out.println("Property accessibility: ");
		// System.out.println("Private Instance Variable:" + ob.privateInstanceVariable); // Throws Compilation Error
		System.out.println("Default Instance Variable:" + ob.defaultInstanceVariable);
		System.out.println("Protected Instance Variable:" + ob.protectedInstanceVariable);
		System.out.println("Public Instance Variable:" + ob.publicInstanceVariable);

		System.out.println("Method accessibility: ");
		// ob.privateDisplay(); // Throws Compilation Error
		ob.defaultDisplay();
		ob.protectedDisplay();
		ob.publicDisplay();
	}
}