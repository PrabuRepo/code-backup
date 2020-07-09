package com.practice.oop.concepts;

/*
 * Abstraction: Data Abstraction may also be defined as the process of identifying only the required characteristics of an object 
 * ignoring the irrelevant details. 
 * In java, abstraction is achieved by interfaces and abstract classes. We can achieve 100% abstraction using interfaces.
 * Abstraction is a contract, where implementing class should define all the abstract methods
 * 
 * Advantages:
 *  - It reduces the complexity of viewing the things.
 *  - Avoids code duplication and increases re-usability.
 *  - Helps to increase security of an application or program as only important details are provided to the user.
 * 
 * Encapsulation vs Data Abstraction
 *  - Encapsulation is data hiding(information hiding) while Abstraction is detail hiding(implementation hiding).
 *  - While encapsulation groups together data and methods that act upon the data, data abstraction deals with exposing the 
 *    interface to the user and hiding the details of implementation.
 *    
 * Abstract Class: Like a class, an abstract class can have variables, abstract methods & Concrete methods.
 * 
 * Interface: Like a class, an interface can have methods and variables, but the methods declared in interface are by default
 * abstract (only method signature, no body).  
 */
public class Abstraction {
	public static void main(String[] args) {

		System.out.println("Test Abstract class -> ");
		Shape s1 = new Circle("Red", 2.2);
		Shape s2 = new Rectangle("Yellow", 2, 4);
		System.out.println(s1.toString());
		System.out.println(s2.toString());

		System.out.println("Test Interface -> ");
		Vehicle bicycle = new Bicycle1();
		bicycle.changeGear(2);
		bicycle.speedUp(3);
		bicycle.applyBrakes(1);

		System.out.println("Bicycle present state :");
		bicycle.printStates();

		// creating instance of bike.
		Vehicle bike = new Bike();
		bike.changeGear(1);
		bike.speedUp(4);
		bike.applyBrakes(3);

		System.out.println("Bike present state :");
		bike.printStates();
	}
}

// Java program to illustrate the concept of Abstraction
abstract class Shape {
	String color;

	// these are abstract methods
	abstract double area();

	public abstract String toString();

	// abstract class can have constructor
	public Shape(String color) {
		System.out.println("Shape constructor called");
		this.color = color;
	}

	// this is a concrete method
	public String getColor() {
		return color;
	}
}

class Circle extends Shape {
	double radius;

	public Circle(String color, double radius) {
		// calling Shape constructor
		super(color);
		System.out.println("Circle constructor called");
		this.radius = radius;
	}

	@Override
	double area() {
		return Math.PI * Math.pow(radius, 2);
	}

	@Override
	public String toString() {
		return "Circle color is " + super.color + "and area is : " + area();
	}

}

class Rectangle extends Shape {

	double	length;
	double	width;

	public Rectangle(String color, double length, double width) {
		// calling Shape constructor
		super(color);
		System.out.println("Rectangle constructor called");
		this.length = length;
		this.width = width;
	}

	@Override
	double area() {
		return length * width;
	}

	@Override
	public String toString() {
		return "Rectangle color is " + super.color + "and area is : " + area();
	}
}

/**
 * Sample code for Interface
 */
interface Vehicle {
	// all are the abstract methods.
	void changeGear(int a);

	void speedUp(int a);

	void applyBrakes(int a);

	void printStates();
}

class Bicycle1 implements Vehicle {

	int	speed;
	int	gear;

	// to change gear
	@Override
	public void changeGear(int newGear) {

		gear = newGear;
	}

	// to increase speed
	@Override
	public void speedUp(int increment) {

		speed = speed + increment;
	}

	// to decrease speed
	@Override
	public void applyBrakes(int decrement) {

		speed = speed - decrement;
	}

	@Override
	public void printStates() {
		System.out.println("speed: " + speed + " gear: " + gear);
	}
}

class Bike implements Vehicle {

	int	speed;
	int	gear;

	// to change gear
	@Override
	public void changeGear(int newGear) {

		gear = newGear;
	}

	// to increase speed
	@Override
	public void speedUp(int increment) {

		speed = speed + increment;
	}

	// to decrease speed
	@Override
	public void applyBrakes(int decrement) {

		speed = speed - decrement;
	}

	@Override
	public void printStates() {
		System.out.println("speed: " + speed + " gear: " + gear);
	}

}