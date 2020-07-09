package com.practice.oop.concepts;

/*
 * Association is relation between two separate classes which establishes through their Objects. Association can be one-to-one, 
 * one-to-many, many-to-one, many-to-many.
 * In Object-Oriented programming, an Object communicates to other Object to use functionality and services provided by that object.
 * 
 * Two forms of association: i.Aggregation , ii.Composition  
 *   Composition is a "strong Association" whereas Aggregation is a "weak Association".
 */

/*
 * In above example two separate classes Bank and Employee are associated through their Objects. Bank can have many employees,
 * So it is a one-to-many relationship.
 */
//Association between both the classes in main method
public class Association {
	public static void main(String[] args) {
		Bank bank = new Bank("Axis");
		Employee emp = new Employee("Neha");

		System.out.println(emp.getEmployeeName() + " is employee of " + bank.getBankName());
	}
}

// Java program to illustrate the concept of Association
class Bank {
	private String name;

	// bank name
	Bank(String name) {
		this.name = name;
	}

	public String getBankName() {
		return this.name;
	}
}

class Employee {
	private String name;

	// employee name
	Employee(String name) {
		this.name = name;
	}

	public String getEmployeeName() {
		return this.name;
	}
}