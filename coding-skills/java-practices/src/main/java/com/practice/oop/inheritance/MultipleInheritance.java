package com.practice.oop.inheritance;

/*
 * Multiple Inheritance: Multiple parent class & one child class
 * Why Java doesn't support multiple iheritance?
 *    Multiple Inheritance is a feature of object oriented concept, where a class can inherit properties of more than one parent 
 *    class. The problem occurs when there exist methods with same signature in both the super classes and subclass. On calling 
 *    the method, the compiler cannot determine which class method to be called and even on calling which class method gets the 
 *    priority. 
 *    Multiple inheritance is not supported by class because of ambiguity. In case of interface there is no ambiguity because 
 *    implementation to method(s) is provided by the implementing class.
 *
 *  Problems in Multiple Inheritance:
 *    1. The Diamond Problem
 *    2. Simplicity
 */
public class MultipleInheritance {
	public static void main(String args[]) {
		System.out.println("Sample class to implement the multiple inheritance!");
	}
}

class Parent1 {
	int data;

	public Parent1(int data) {
		this.data = data;
	}

	public void display() {
		System.out.println("Parent1: " + data);
	}
}

class Parent2 {
	int data;

	public Parent2(int data) {
		this.data = data;
	}

	public void display() {
		System.out.println("Parent1: " + data);
	}
}

/*class MultipleInheritance1 extends Parent1,Parent2 {

public MultipleInheritance1(int data) {
	super(data);
}

public static void main(String args[]) {
	MultipleInheritance1 t = new MultipleInheritance1(20);
	//From this code, we see that, on calling the method display() using Test object will cause complications such as whether to 
	//call Parent1’s display() or Parent2’s display() method.
	t.display();
}
}*/

/* How are above problems handled for Default Methods and Interfaces ?
	Java 8 supports default methods where interfaces can provide default implementation of methods. And a class can implement 
	two or more interfaces. In case both the implemented interfaces contain default methods with same method signature, the
	implementing class should explicitly specify which default method is to be used or it should override the default method.
*/

// A simple Java program to demonstrate multiple inheritance through default methods.
interface PI1 {
	// default method
	default void show() {
		System.out.println("Default PI1");
	}
}

interface PI2 {
	// Default method
	default void show() {
		System.out.println("Default PI2");
	}
}

// Implementation class code
class MultipleInheritance2 implements PI1, PI2 {
	/*Overriding default show method: If we remove implementation of default method from “TestClass”, we get compiler error. 
	 *See this for a sample run. Compilation Error: "Duplicate default methods named show with the parameters () and () are
	 * inherited from the types PI2 and PI1"*/
	@Override
	public void show() {
		System.out.println("Overrides the Child Class!");
		// use super keyword to call the show method of PI1 interface
		PI1.super.show();
		// use super keyword to call the show method of PI2 interface
		PI2.super.show();
	}

	public static void main(String args[]) {
		MultipleInheritance2 d = new MultipleInheritance2();
		d.show();
	}

}
