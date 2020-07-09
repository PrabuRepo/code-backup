package com.practice.ood.pattern.creational;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/*
 * The singleton pattern is a design pattern that restricts the instantiation of a class to one object.
 * Sometimes we need to have only one instance of our class for example a single DB connection shared by multiple objects 
 * as creating a separate DB connection for every object may be costly.
 * 
 * To implement a Singleton pattern, we have different approaches but all of them have the following common concepts.
 *    1. "Private constructor" to restrict instantiation of the class from other classes.
 *    2. "Private static final variable of the same class" that is the only instance variable of the class.
 *    3. "Public static method that returns the instance of the class", this is the global access point for outer world to
 *       get the instance of the singleton class.
 */
public class Singleton {

	public static void main(String[] args) {

	}
}

// 1. Eager initialization
class EagerInitializedSingleton {

	private static final EagerInitializedSingleton INSTANCE = new EagerInitializedSingleton();

	// private constructor to avoid client applications to use constructor
	private EagerInitializedSingleton() {
	}

	public static EagerInitializedSingleton getInstance() {
		return INSTANCE;
	}
}

/*2. Static block initialization: static blocks are executed during the loading of a class, even before the constructor is called*/
class StaticBlockSingleton {

	private static StaticBlockSingleton INSTANCE;

	private StaticBlockSingleton() {
	}

	// static block initialization for exception handling
	static {
		try {
			INSTANCE = new StaticBlockSingleton();
		} catch (Exception e) {
			throw new RuntimeException("Exception occured in creating singleton instance");
		}
	}

	public static StaticBlockSingleton getInstance() {
		return INSTANCE;
	}
}

// 3. Lazy Initialization
class LazyInitializedSingleton {

	private static LazyInitializedSingleton INSTANCE;

	private LazyInitializedSingleton() {
	}

	public static LazyInitializedSingleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LazyInitializedSingleton();
		}
		return INSTANCE;
	}
}

// 4. Thread Safe Singleton
class ThreadSafeSingleton {

	private static ThreadSafeSingleton INSTANCE;

	private ThreadSafeSingleton() {
	}

	public static synchronized ThreadSafeSingleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ThreadSafeSingleton();
		}
		return INSTANCE;
	}

	static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
		if (INSTANCE == null) {
			synchronized (ThreadSafeSingleton.class) {
				if (INSTANCE == null) {
					INSTANCE = new ThreadSafeSingleton();
				}
			}
		}
		return INSTANCE;
	}
}

// 5. Bill Pugh Singleton Implementation - This is similar to eager initialization
/* Java memory model had a lot of issues and the above approaches used to fail in certain scenarios where too many threads 
 * try to get the instance of the Singleton class simultaneously. So Bill Pugh came up with a different approach to create 
 * the Singleton class using an inner static helper class. 
 * 
 *  Notice the private inner static class that contains the instance of the singleton class. When the singleton class is loaded,
 *  SingletonHelper class is not loaded into memory and only when someone calls the getInstance method, this class gets loaded
 *  and creates the Singleton class instance. This is the most widely used approach for Singleton class as it doesn’t require 
 *  synchronization
 */
class BillPughSingleton {

	private BillPughSingleton() {
	}

	private static class SingletonHelper {
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}

	public static BillPughSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
}

// 6. Using Reflection to destroy Singleton Pattern
class ReflectionSingletonTest {

	public static void main(String[] args) {
		EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
		EagerInitializedSingleton instanceTwo = null;
		try {
			Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
				// Below code will destroy the singleton pattern
				constructor.setAccessible(true);
				instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(instanceOne.hashCode());
		System.out.println(instanceTwo.hashCode());
	}
}

// 7. Enum Singleton
enum EnumSingleton {
	INSTANCE;

	public static void doSomething() {
		// do something
	}
}

// 8. Serialization and Singleton
class SerializedSingleton implements Serializable {
	/* Add serialVersionUId to singleton objects: 
	 * This is required in cases where your class structure changes between serialization and deserialization. A changed class 
	 * structure will cause the JVM to give an exception in the de-serializing process.
	 * This problem can be solved only by adding a unique serial version id to the class. It will prevent the compiler from 
	 * throwing the exception by telling it that both classes are same, and will load the available instance variables only.
	 * */
	private static final long serialVersionUID = -7604766932017737115L;

	private SerializedSingleton() {
	}

	private static class SingletonHelper {
		private static final SerializedSingleton INSTANCE = new SerializedSingleton();
	}

	public static SerializedSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}

}