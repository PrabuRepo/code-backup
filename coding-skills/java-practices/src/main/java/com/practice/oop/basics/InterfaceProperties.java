package com.practice.oop.basics;

/*
 *  1. An interface can only contain abstract methods.
 *  2. In interface the variable is by default public final. In nutshell the interface doesn't have any variables 
 *     it only has constants.
 *  3. Abstract class doesn't support multiple inheritance whereas Interface does.
 *  4. If an interface is implemented its mandatory to implement all of its methods
 *  5. The problem with an interface is, if you want to add a new feature (method) in its contract, then you MUST implement
 *     the new method(s) in all of the classes which implement that interface. However, in the case of an abstract class, the 
 *     method can be simply implemented in the abstract class and the same can be called by its subclass.
 */
public class InterfaceProperties {

}

interface Car {

}

interface Truck {
}

class Car4WD extends Drive implements Car {

	@Override
	public void drive() {
		// TODO Auto-generated method stub

	}
}

class Truck4WD extends Drive implements Truck {

	@Override
	public void drive() {
		// TODO Auto-generated method stub

	}
}
