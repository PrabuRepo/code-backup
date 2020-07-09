package com.practice.oop.basics;

/**
 * When you declare two static methods with same name and signature in both superclass and subclass then they hide each
 * other i.e. a call to the method in the subclass will call the static method declared in that class and a call to the
 * same method is superclass is resolved to the static method declared in the super class.
 * 
 */
public class MethodHiding {
	public static void main(String[] args) {
		Parent1 parent1 = new Child1();// Child object is reference by the variable of type Parent1
		Child1 child = new Child1();// Child object is referenced by the variable of type Child1

		parent1.foo();// It will call the method of Parent1. Even its child class reference, it invokes Parent1 class method
		child.foo();// It will call the method of Child1.

		parent1.bar();// It will call the method of Child1.
		child.bar();// It will call the method of Child1 again.
	}
}

class Parent1 {

	public static void foo() {
		System.out.println("I am foo in Super");
	}

	public void bar() {
		System.out.println("I am bar in Super");
	}

}

class Child1 extends Parent1 {

	// Hiding
	public static void foo() {
		System.out.println("I am foo in Child");
	}

	// Overriding
	public void bar() {
		System.out.println("I am bar in Child");
	}
}