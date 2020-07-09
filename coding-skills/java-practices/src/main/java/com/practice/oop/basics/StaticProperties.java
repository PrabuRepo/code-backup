package com.practice.oop.basics;

/*
 * Static keyword can be used with the variables and methods but not with the class. Anything declared as static is
 * related to class and not objects.
 * 1. Static Variable: Multiples objects of a class shares the same instance of a static variable
 * 2. Static Method: A static method can be accessed without creating the objects. Just by using 
 *                   the Class name the method can be accessed.
 * 3. Static Block: ?
 */
public class StaticProperties {
	// 1.Static Variable
	private static int	count			= 0;
	private int			nonStaticcount	= 0;

	public void incrementCounter() {
		count++;
		nonStaticcount++;
	}

	// 2.Static Method
	public static void printMe() {
		System.out.println("Static Method Call!");

		/* Static method can call only static methods but not non-static methods. But non-static methods can call static methods.*/
		testNonstaticVariableInStaticMethod();

		/*Below method call throws Compilation Error: "Cannot make a static reference to the non-static
		   method incrementCounter() from the type StaticProperties"*/
		// incrementCounter();
	}

	/*
	 * Can you access non static variable in static context ?
	 * No, A static variable in Java belongs to its class and its value remains the same for all its instances. A static variable
	 * is initialized when the class is loaded by the JVM. If your code tries to access a non-static variable, without any 
	 * instance, the compiler will complain, because those variables are not created yet and they are not associated with any 
	 * instance.
	 */
	public static void testNonstaticVariableInStaticMethod() {
		count++;
		// nonStaticcount++; //Throws Compilation Error: "Cannot make a static reference to the non-static field nonStaticcount"
	}

	public static void main(String args[]) {

		StaticProperties obj1 = new StaticProperties();
		StaticProperties obj2 = new StaticProperties();
		obj1.incrementCounter();
		obj2.incrementCounter();
		System.out.println("Static count for Obj1: " + obj1.count); // It can be accessed without instance variable
		System.out.println("NonStatic count for Obj1: " + obj1.nonStaticcount);
		System.out.println("Static count for Obj2: " + obj2.count);
		System.out.println("NonStatic count for Obj2: " + obj2.nonStaticcount);

		printMe();
	}
}

/*Static Block Vs Init Block:
 * The static block is loaded when the class is loaded by the JVM for the 1st time only whereas init {} block is loaded every 
 * time class is loaded. Also first the static block is loaded then the init block.
 */
class LoadingBlocks {

	static {
		System.out.println("Inside static");
	}

	{
		System.out.println("Inside init");
	}

	public static void main(String args[]) {
		new LoadingBlocks();
		new LoadingBlocks();
		new LoadingBlocks();
	}
}