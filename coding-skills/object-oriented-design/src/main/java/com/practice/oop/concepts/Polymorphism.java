package com.practice.oop.concepts;

/*
 * The process of representing one form in multiple forms is known as Polymorphism.
 * 
 * Types of Polymorphism: 
 * 		Compile time polymorphism or method overloading or static banding - When a type of the object is determined at a compiled time(by the compiler)
 * 		Runtime polymorphism or method overriding or dynamic binding - When a type of the object is determined at run-time
 *  
 */

public class Polymorphism {
	public static void main(String[] args) {
		// Compile time Polymorphism:
		System.out.println(Adder.add(11, 11));
		System.out.println(Adder.add(11, 11, 11));

		// Here the runtime polymorphism fundamental is not applied,
		// as it is of single CashPayment form
		CashPayment c = new CashPayment();
		c.pay();

		// Now the initialization is done using runtime polymorphism and
		// it can have many forms at runtime
		// Single payment "p" instance can be used to pay by cash and credit card
		Payment p = new CashPayment();
		p.pay(); // Pay by cash

		p = new CreditPayment();
		p.pay(); // Pay by creditcard
	}

}

/**
 * Compile time Polymorphism
 */
class Adder {
	static int add(int a, int b) {
		return a + b;
	}

	static int add(int a, int b, int c) {
		return a + b + c;
	}
}

/**
 *  Run time Polymorphism:
 *  Example: Payment Processing Example
 *    In this Payment Processing Example, applying runtime polymorphism and it can have many forms at runtime. Refer below source code the single
 *    payment "p" instance can be used to pay by cash and credit card, payment p instance takes many forms here.
 */
interface Payment {
	public void pay();
}

/**
* Cash IS-A Payment type
* 
*/
class CashPayment implements Payment {

	// method overriding
	@Override
	public void pay() {
		System.out.println("This is cash payment");
	}

}

/**
* Creditcard IS-A Payment type
*/
class CreditPayment implements Payment {

	// method overriding
	@Override
	public void pay() {
		System.out.println("This is credit card payment");
	}

}
