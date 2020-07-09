package com.practice.oop.inheritance;

/*
 * Upcasting(Implicit): Casting from a subclass to a superclass is called upcasting. Typically, the upcasting is implicitly performed by
 * the compiler. Upcasting is closely related to inheritance – another core concept in Java. It’s common to use reference
 * variables to refer to a more specific type. And every time we do this, implicit upcasting takes place.
 * 
 * Downcasting: It’s the casting from a superclass to a subclass.
 * 
 * Read this: http://www.baeldung.com/java-type-casting
 * 
 * The reference variable of the Parent class is capable to hold its object reference as well as its child object reference.
 * 
 * We have seen here that a parent class data member is accessed when a reference of parent type refers to a child object.
 * We can access child data member using type casting.
 * Syntax: (child_class_name) Parent_Reference_variable).func.name().
 * When we do typecasting, it is always a good idea to check if the typecasting is valid or not. instanceof helps use here. 
 */
public class ObjectCasting2 {

	public static void main(String[] args) {
		ClassAA objA = new ClassAA();
		ClassBB objB = new ClassBB();
		ClassCC objC = new ClassCC();

		System.out.println("***Before casting***");
		System.out.println("From ObjectA instance:");
		objA.printA();
		System.out.println("From ObjectA instance:");
		objB.printA();
		objB.printB();
		System.out.println("From ObjectA instance:");
		objC.printA();
		objC.printB();
		objC.printC();

		System.out.println("***Implicit casting***");
		ObjectCasting2 test = new ObjectCasting2();
		// Implicit Casting: subclass is assigned to superclass
		System.out.println("B->A Conversion: ");
		test.implicitCastingBtoA(objB);
		System.out.println("C->A Conversion: ");
		test.implicitCastingCtoA(objC);
		System.out.println("C->B Conversion: ");
		test.implicitCastingCtoB(objC);

		// Explicit casting impacts based on above implicit casting
		System.out.println("***Explicit casting***"); // ???Read this
		System.out.println("A->B Conversion: ");
		test.explicitCastingAtoB(objA);
		System.out.println("A->C Conversion: ");
		test.explicitCastingAtoC(objA);
		System.out.println("B->C Conversion: ");
		test.explicitCastingBtoC(objB);
	}

	public void implicitCastingBtoA(ClassBB objB) {
		System.out.println("ObjectB reference will be displayed in ObjectA!");
		ClassAA objA = objB;
		objA.printA();
		((ClassBB) objA).printB();
	}

	public void implicitCastingCtoA(ClassCC objC) {
		System.out.println("ObjectC reference will be displayed in ObjectA!");
		ClassAA objA = objC;
		objA.printA();
		((ClassBB) objA).printB();
		((ClassCC) objA).printC();
	}

	public void implicitCastingCtoB(ClassCC objC) {
		System.out.println("ObjectC reference will be displayed in ObjectB!");
		ClassBB objB = objC;
		objB.printA();
		objB.printB();
		((ClassCC) objC).printC();
	}

	public void explicitCastingAtoB(ClassAA objA) {
		if (objA instanceof ClassBB) {
			System.out.println("ClassAA(Super Class) obj is assigned to ClassBB(Sub class) obj");
			ClassBB temp = (ClassBB) objA;
			temp.printA();
			temp.printB();
		}
	}

	public void explicitCastingAtoC(ClassAA objA) {
		if (objA instanceof ClassCC) {
			System.out.println("ClassAA(Super Class) obj is assigned to ClassCC(Sub class) obj");
			ClassCC temp = (ClassCC) objA;
			temp.printA();
			temp.printB();
			temp.printC();
		}
	}

	public void explicitCastingBtoC(ClassBB objB) {
		if (objB instanceof ClassCC) {
			System.out.println("ClassBB(Super Class) obj is assigned to ClassCC(Sub class) obj");
			ClassCC temp = (ClassCC) objB;
			temp.printA();
			temp.printB();
			temp.printC();
		}
	}

}

class ClassAA {
	int x = 10;

	void printA() {
		System.out.println("Class A Values:" + x);
	}
}

class ClassBB extends ClassAA {
	int x = 20;

	void printB() {
		System.out.println("Class B Values:" + x);
	}
}

class ClassCC extends ClassBB {
	int x = 30;

	void printC() {
		System.out.println("Class C Values:" + x);
	}
}
