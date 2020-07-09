package com.practice.oop.concepts;

/*
 * Inheritance - IS-A relationship between a superclass and its subclasses.
 * The Inheritance is a process of obtaining the data members and methods from one class to another class, plus can have its own is known as inheritance.
 */
public class Inheritance {
	public static void main(String[] args) {
		Animal dog = new Dog();
		dog.setId(123); // inherited from super class
		dog.sound(); // overrided behavior of sub class
	}
}

/**
 * This is parent (also called as super or base) class Animal
 *  
 */
class Animal {
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void sound() {
		System.out.println("By default it is mute");
	}
}

/**
 * This is subclass (also called as derived or child or extended) Dog which is extending Animal
 * 
 */
class Dog extends Animal {

	// Own behavior
	private void bark() {
		System.out.println("Dog '" + getId() + "' is barking");
	}

	// Overriding super class behavior
	@Override
	public void sound() {
		bark();
	}
}