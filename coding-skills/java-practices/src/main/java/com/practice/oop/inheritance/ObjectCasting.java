package com.practice.oop.inheritance;

/*
 * Upcasting(Implicit): Casting from a subclass to a superclass is called upcasting. Typically, the upcasting is implicitly
 * performed by the compiler. Upcasting is closely related to inheritance – another core concept in Java. It’s common to use 
 * reference variables to refer to a more specific type. And every time we do this, implicit upcasting takes place.
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
 * 
 *  instanceOf(): instanceof is used to identify whether the object is of a particular class type or its subclass.
 */
public class ObjectCasting {

	public static void main(String[] args) {
		ClassA objA = new ClassA(1000, 2000);
		ClassB objB = new ClassB(100, 200);
		ClassC objC = new ClassC(10, 20);

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
		ObjectCasting test = new ObjectCasting();
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

	public void implicitCastingBtoA(ClassB objB) {
		System.out.println("ObjectB reference will be displayed in ObjectA!");
		ClassA objA = objB;
		objA.printA();
		((ClassB) objA).printB();
	}

	public void implicitCastingCtoA(ClassC objC) {
		System.out.println("ObjectC reference will be displayed in ObjectA!");
		ClassA objA = objC;
		objA.printA();
		((ClassB) objA).printB();
		((ClassC) objA).printC();
	}

	public void implicitCastingCtoB(ClassC objC) {
		System.out.println("ObjectC reference will be displayed in ObjectB!");
		ClassB objB = objC;
		objB.printA();
		objB.printB();
		((ClassC) objC).printC();
	}

	public void explicitCastingAtoB(ClassA objA) {
		if (objA instanceof ClassB) {
			System.out.println("ClassA(Super Class) obj is assigned to ClassB(Sub class) obj");
			ClassB temp = (ClassB) objA;
			temp.printA();
			temp.printB();
		}
	}

	public void explicitCastingAtoC(ClassA objA) {
		if (objA instanceof ClassC) {
			System.out.println("ClassA(Super Class) obj is assigned to ClassC(Sub class) obj");
			ClassC temp = (ClassC) objA;
			temp.printA();
			temp.printB();
			temp.printC();
		}
	}

	public void explicitCastingBtoC(ClassB objB) {
		if (objB instanceof ClassC) {
			System.out.println("ClassB(Super Class) obj is assigned to ClassC(Sub class) obj");
			ClassC temp = (ClassC) objB;
			temp.printA();
			temp.printB();
			temp.printC();
		}
	}
}

class ClassA {
	int x, y;

	public ClassA(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void printA() {
		System.out.println("Class A Values:" + x + "," + y);
	}
}

class ClassB extends ClassA {
	int x, y;

	public ClassB(int x, int y) {
		super(2 * x, 2 * y);
		this.x = x;
		this.y = y;
	}

	void printB() {
		System.out.println("Class B Values:" + x + "," + y);
	}
}

class ClassC extends ClassB {
	int x, y;

	public ClassC(int x, int y) {
		super(3 * x, 3 * y);
		this.x = x;
		this.y = y;
	}

	void printC() {
		System.out.println("Class C Values:" + x + "," + y);
	}
}
