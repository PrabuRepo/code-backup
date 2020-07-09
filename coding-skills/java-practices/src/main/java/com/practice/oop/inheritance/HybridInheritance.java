package com.practice.oop.inheritance;

/*
 * Hybrid: Combination of Hierarchical & Multiple 
 * The Diamond Problem happens due to Hybrid Inheritance.
 */
public class HybridInheritance {
	public static void main(String args[]) {
		System.out.println("Sample class to implement the Hybrid Inheritance!");
	}
}

// A Grand parent class in diamond
class GrandParent {
	void fun() {
		System.out.println("Grandparent");
	}
}

// First Parent class
class Parent01 extends GrandParent {
	void fun() {
		System.out.println("Parent1");
	}
}

// Second Parent Class
class Parent02 extends GrandParent {
	void fun() {
		System.out.println("Parent2");
	}
}

// Error : Test is inheriting from multiple classes
/*class Test extends Parent01,Parent02{

	public static void main(String args[]) {
		Test t = new Test();
		t.fun();
	}
}
*/

/*
 * If there is a diamond through interfaces, then there is no issue if none of the middle interfaces provide implementation 
 * of root interface. If they provide implementation, then implementation can be accessed as above using super keyword.
 */

// A simple Java program to demonstrate how diamond problem is handled in case of default methods
interface GPI {
	// default method
	default void show() {
		System.out.println("Default GPI");
	}
}

interface PI3 extends GPI {
}

interface PI4 extends GPI {
}

// Implementation class code
class TestClass implements PI3, PI4 {
	public static void main(String args[]) {
		TestClass d = new TestClass();
		d.show();
	}
}