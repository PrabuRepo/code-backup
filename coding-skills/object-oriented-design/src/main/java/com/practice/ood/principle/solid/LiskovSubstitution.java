package com.practice.ood.principle.solid;

/*
 * Liskov Principle Definition: 
 * ----------------------------
 * "Derived types must be completely substitutable for their base types"
 * 
 * The Liskov Substitution Principle says that the object of a derived class should be able to replace an object of the 
 * base class without bringing any errors in the system or modifying the behavior of the base class.
 * 
 * In short: if S is a subset of T, an object of T could be replaced by an object of S without impacting the program and 
 * bringing any error in the system
 * 
 * In a very simple sentence, we can say: 
 *    The child class must not violate its base class characteristics. It must be capable with it. We can say it's same as 
 *    sub typing.
 *    
 * The LSP is a rule about the contract of the clases: if a base class satisfies a contract, then by the LSP derived classes
 * must also satisfy that contract.
 * 
 * Common ways to identify violations of LS principles are as follows: 
 *    - "Not implemented method" in the subclass.
 *    - Subclass function overrides the base class method to give it new meaning.
 */
public class LiskovSubstitution {

	public static void main(String[] args) {
		Employee emp1 = new PermanentEmployee(1, "Name1");
		Employee emp2 = new ContractEmployee(2, "Name2");

		float sal = emp1.salary();
		System.out.println("Permanent Employee: " + emp1.calculateBonus(sal));

		sal = emp2.salary();
		System.out.println("Contract Employee: " + emp2.calculateBonus(sal));

		A a = new B();
		System.out.println("Instance B to class A: " + a.dataA);
		a.display();

		B b = new B();
		System.out.println("Instance B to class B: " + b.dataA);
		b.display();
		b.print();
	}
}

abstract class Employee {
	int		id;
	String	name;

	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}

	abstract float salary();

	abstract float calculateBonus(float salary);

}

class PermanentEmployee extends Employee {

	public PermanentEmployee(int id, String name) {
		super(id, name);
	}

	@Override
	float salary() {
		return 10000;
	}

	@Override
	float calculateBonus(float salary) {
		return (salary * 0.1f);
	}
}

class ContractEmployee extends Employee {

	public ContractEmployee(int id, String name) {
		super(id, name);
	}

	@Override
	float salary() {
		return 7000;
	}

	@Override
	float calculateBonus(float salary) {
		return 0;
	}
}

interface IEmployee {
	float salary();
}

class A {
	public int dataA = 10;

	public void display() {
		System.out.println("Class A Display()! " + dataA);
	}
}

class B extends A {
	public int dataB = 20;

	public void display() {
		System.out.println("Class B Display()! " + dataB);
	}

	public void print() {
		System.out.println("Class B Print()! " + dataB);
	}
}