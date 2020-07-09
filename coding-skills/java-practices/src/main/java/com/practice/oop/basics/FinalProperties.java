package com.practice.oop.basics;

import java.util.HashMap;
import java.util.Map;

/*
 * The final keyword can be assigned to:
 *   1. Class level variable
 *   2. method
 *   3. class
 *   4. Objects
 */
public class FinalProperties {
	final int i = 10;

	// 1. Final Class variable
	void modifyFinalVariable() {
		// i = 20; //It throws compilation error: "The final field FinalProperties.i cannot be assigned"
	}

	public static void main(String[] args) {

		// 4. Final Object: Final objects are instantiated only once. i.e
		final Map<String, String> map = new HashMap<>();
		map.put("key", "value");
		/* Below line throws compilation error: "The final local variable map cannot be assigned. It must be blank and not
		 *  using a compound assignment" */
		// map = new HashMap<>();
	}
}

class Parent {
	// 2. Final Method
	// If a final is assigned to a method then it cannot be overridden in its child class.
	final void print() {
		System.out.println("Inside");
	}
}

class Child extends Parent {
	// Below method throws compilation error: "Cannot override the final method from Parent"
	/*public final void print() {
		// error cannot override final method
		System.out.println("Inside");
	}*/
}

/*If a class is made as final, then no other class can extend it and make it as parent class.
 * E.g. String Class cannot be extended*/
final class FinalClass {

}

// 3. Final Class
/*Below class throws compilation error, when extends the FinalClass: 
 * "The type DeriveFinalClass cannot subclass the final class FinalClass" */
class DeriveFinalClass/* extends FinalClass */ {

}