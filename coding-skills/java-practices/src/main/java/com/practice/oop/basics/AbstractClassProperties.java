package com.practice.oop.basics;

/*
 * A class is called abstract when it is declared with the keyword abstract. 
 *  1. Abstract class contains at least one abstract method. It can also contain n numbers of concrete method.
 *  2. The abstract class can have public, private, protected or default variables and also constants
 *  3. A class can extend only one abstract class and abstract class doesn't support multiple inheritance.
 *  4. If an abstract class is extended its mandatory to implement all abstract methods.
 */
public class AbstractClassProperties {

}

abstract class Drive {
	public abstract void drive();
}

class FourWD extends Drive {
	public void drive() {
		// 4wd drive
	}
}

class TwoWD extends Drive

{

	public void drive() {
		// 2wd drive
	}
}