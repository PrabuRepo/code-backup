package com.practice.oop.concepts;

/*
 * The process of binding or wrapping the data and the codes that operate on the data into a single entity. This keeps the data safe from outside 
 * interface and misuse. One way to think about encapsulation is as a protective wrapper that prevents code and data from being arbitrarily accessed
 *  by other code defined outside the wrapper.
 *  
 *  For example - if a field is declared private, it cannot be accessed by anyone outside the class, thereby hiding the fields within the class. 
 *  Consider below Person class diagram, the id and name parameters should not be accessed directly outside Person class - achieved by private declaration.
 */
public class Encapsulation {
	public static void main(String[] args) {

		Person p1 = new Person();
		p1.setName("Ramesh");
		/*
		 * Note: As id and name are encapsulated in Person class, those cannot be accessed 
		 * directly here. Also there is no way to assign id in this
		 * class. Try to uncomment below code and you would find compile time error.
		 */
		// p1.id = "123";
		// p1.name = "this will give compile time error";
		// p1.sayHello(); // You can't access this method, as it is private to Person

		System.out.println("Person 1 - " + p1.getId() + " : " + p1.getName());
	}
}

/*
 * Difference between Abstraction and Encapsulation: 
 * 	Encapsulation is a process of binding or wrapping the data and the codes that operate on the data into a single entity. This keeps the data safe 
 *  from outside interface and misuse.
 *  Abstraction is the concept of hiding irrelevant details. In other words, make the complex system simple by hiding the unnecessary detail from the user.
 *  Abstraction is implemented in Java using interface and abstract class while Encapsulation is implemented using private, package-private and protected
 *  access modifiers.
 *  Abstraction solves the problem at the design level. Whereas Encapsulation solves the problem at the implementation level.
 */
class Person {

	private double id;
	private String name;

	public Person() {
		// Only Person class can access and assign
		id = Math.random();
		sayHello();
	}

	// This method is protected for giving access within Person class only
	private void sayHello() {
		System.out.println("Hello - " + getId());
	}

	public double getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}