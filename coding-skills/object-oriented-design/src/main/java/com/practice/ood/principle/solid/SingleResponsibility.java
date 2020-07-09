package com.practice.ood.principle.solid;

import java.util.Date;

/*
 * S.O.L.I.D Principles:
 * Single responsibility principle - A class should have only one reason to change.
 * Open/Closed principle - Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.
 * Liskov Substitution Principle - Derived types must be completely substitutable for their base types.
 * Interface Segregation Principle - No client should be forced to depend on methods it does not use.
 * Dependency inversion principle - High-level modules should not depend on low-level modules. Both should depend on abstractions.
 *   						 Abstractions should not depend upon details. Details should depend upon abstractions.
 */

/*
 * Single Responsibility Principle was defined by Robert C. Martin as - A class should have only one reason to change.
 * Wikipedia and many write-ups on Single Responsibility Principle describe it as - A class should have only one responsibility.
 */

public class SingleResponsibility {

}

/*
 *  Take a look at the Employee class below. The above Employee class looks logically correct. It has all the employee attributes like employeeId,
 *  name, age, address & dateOfJoining. It even tells you if the employee is eligible for promotion this year and calculates the income tax he has to pay for the year.
 * 
 */
class Employee1 {
	private String	employeeId;
	private String	name;
	private String	address;
	private Date	dateOfJoining;

	public boolean isPromotionDueThisYear() {
		// promotion logic implementation
		return true;
	}

	public Double calcIncomeTaxForCurrentYear() {
		// income tax logic implementation
		return 0.0;
	}
	// Getters & Setters for all the private attributes
}

/*
 * Refactoring the Employee class so that it adheres to Single Responsibility Principle, Let us now refactor the Employee 
 * class to make it own a single responsibility.
 */

class HRPromotions {
	public boolean isPromotionDueThisYear(Employee2 emp) {
		// promotion logic implementation using the employee information passed
		return true;
	}
}
/*Similarly, lets move the income tax calculation logic from Employee class to FinITCalculations class*/

class FinITCalculations {
	public Double calcIncomeTaxForCurrentYear(Employee2 emp) {
		// income tax logic implementation using the employee information passed
		return 0.0;
	}
}

class Employee2 {
	private String	employeeId;
	private String	name;
	private String	address;
	private Date	dateOfJoining;
	// Getters & Setters for all the private attributes
}