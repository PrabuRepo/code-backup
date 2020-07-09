package com.practice.oop.concepts;

/*
 * Theory:
 *  Before JDK 5.0, it was not possible to override a method by changing the return type. When we override a parent class method,
 *  the name, argument types and return type of the overriding method in child class has to be exactly same as that of parent 
 *  class method. Overriding method was said to be invariant with respect to return type.
 *  
 *  Covariant return type:
 *  Java 5.0 onwards it is possible to have different return type for a overriding method in child class, but child’s return type 
 *  should be sub-type of parent’s return type. Overriding method becomes variant with respect to return type.
 *  
 *  Co-variant return type is based on Liskov substitution principle.
 *  Liskov Principle: If S is a subtype of T, then objects of type T in a program may be replaced with objects of type S 
 *                    without altering any of the desirable properties of that program
 */
public class CovariantReturnType {
	public static void main(String args[]) {
		Base base = new Base();
		base.fun();

		Derived derived = new Derived();
		derived.fun();
	}
}

class T {
}

class S extends T {
}

class Base {
	T fun() {
		System.out.println("Base fun()");
		return new T();
	}
}

class Derived extends Base {
	S fun() {
		System.out.println("Derived fun()");
		return new S();
	}
}