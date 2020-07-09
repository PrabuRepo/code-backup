package com.practice.oop.concepts;

/*
 * Definition: Polymorphism is the ability of an object to take on many forms.
 * Types: Overloading(Compile time Polymorphism), Overriding(Run time Polymorphism)
 * 
 * Overloading Vs Overriding:
 *  Overloading is about same function have different signatures.
 *  Overriding is about same function,same signature but different classes connected through inheritance.
 *                            
 * Overloading: 
 *     Overloading allows different methods to have same name, but different signatures where signature can differ by number
 * of input parameters or type of input parameters or both. Overloading is related to compile time (or static) Polymorphism.
 * Advantage: We don’t have to create and remember different names for functions doing the same thing.
 * 
 * Overriding:
 *     Method overriding is one of the way by which java achieve Run Time Polymorphism. When a method in a subclass has the same
 * name, same parameters or signature and same return type(or sub-type) as a method in its super-class, then the method in the
 * subclass is said to override the method in the super-class. When you override methods, JVM determines the proper method to
 * call at the program’s run time, not at the compile time.
 *     Dynamic method dispatch is the mechanism by which a call to an overridden method is resolved at run time, rather than
 * compile time. When an overridden method is called through a superclass reference, Java determines which version
 * (superclass/subclasses) of that method is to be executed based upon the type of the object being referred to at the time 
 * the call occurs. Thus, this determination is made at run time.
 *     
 * 
 * Static Vs Dynamic Binding:
 *  - Static binding which can be resolved at compile time by compiler is known as static or early binding. Binding of all 
 *    the static, private and final methods is done at compile-time
 *  - Static binding is better performance wise. Compiler knows that all such methods cannot be overridden and will always 
 *    be accessed by object of local class. Hence compiler doesn’t have any difficulty to determine object of class
 *  - In Dynamic binding compiler doesn’t decide the method to be called. Overriding is a perfect example of dynamic binding. 
 *    In overriding both parent and child classes have same method.
 */
public class Polymorphism {
	public static void main(String[] args) {
		System.out.println("Test Overloading -> ");
		OverLoading s = new OverLoading();
		System.out.println(s.sum(10, 20));
		System.out.println(s.sum(10, 20, 30));
		System.out.println(s.sum(10.5, 20.5));
		System.out.println("Main methods: ");
		main("Overloaded Main method");

		System.out.println("Test Overriding -> ");
		Parent obj1 = new Parent();
		// If a Parent type reference refers to a Parent object, then Parent's show is called
		obj1.show();
		obj1.staticMethod(10);
		// If a Parent type reference refers to a Child object Child's show() is called. This is called RUN TIME POLYMORPHISM.
		Parent obj2 = new Child();
		obj2.show();
		obj1.staticMethod(20); // It invokes only Parent static method only
	}

	// We can have two ore more static methods with same name, but differences in input parameters.
	// Like other static methods, we can overload main() in Java.
	public static void main(String args) {
		System.out.println(args);
	}
}

// Compile time Polymorphism: Java program to demonstrate working of method overloading in Java.
class OverLoading {

	// Overloaded sum(). This sum takes two int parameters
	public int sum(int x, int y) {
		return (x + y);
	}

	// We cannot overload by return type. Shows Compilation error: Duplicate method sum(int, int) in type OverLoading
	/*public float sum(int x, int y) {
		return (x + y);
	}*/

	// Overloaded sum(). This sum takes three int parameters
	public int sum(int x, int y, int z) {
		return (x + y + z);
	}

	// Overloaded sum(). This sum takes two double parameters
	public double sum(double x, double y) {
		return (x + y);
	}
}

/*
 * Rules for method overriding:
 *  - The access modifier for an overriding method can allow more, but not less, access than the overridden method. Eg: Super Class: Protected, Sub class: Public
 *  - Final methods can not be overridden
 *  - Static methods can not be overridden
 *  - Private methods can not be overridden 
 *  - The overriding method must have same return type (or sub type): From Java 5.0 onwards it is possible, "covariant return type"
 *  - Invoking overridden method from sub-class: We can call parent class method in overriding method using super keyword.
 *  - Overriding and constructor : We can not override constructor as parent and child class can never have constructor with same name
 *  - Overriding and abstract method : Abstract methods in an interface or abstract class are meant to be overridden in derived concrete classes
 *  - Overriding and synchronized/stricfp method : The presence of synchronized/stricfp modifier with method have no effect on the rules of overriding, i.e. it’s possible that a synchronized/stricfp method can override a non synchronized/stricfp one and vice-versa.
 *  - Overriding and Exception-Handling: 
 *       1. If the super-class overridden method does not throws an exception, subclass overriding method can only throws the unchecked exception, throwing checked exception will lead to compile-time error.
 *       2. If the super-class overridden method does throws an exception, subclass overriding method can only throw same, subclass exception. Throwing parent exception in Exception hierarchy will lead to compile time error.Also there is no issue if subclass overridden method is not throwing any exception.
 */
// Sample code for Overriding
class Parent {
	protected void show() {
		System.out.println("Parent's show()");
	}

	static void staticMethod(int data) {
		System.out.println("Parent static method: " + data);
	}

}

class Child extends Parent {
	// This method overrides show() of Parent
	@Override
	public void show() {
		System.out.println("Child's show()");
	}

	// It throws Compilation Error, if we remove static modifier
	static void staticMethod(int data) {
		System.out.println("Child static method: " + data);
	}

}