package com.practice.oop.methods;

/*
 * Object class in Java:
 *     Object class is present in java.lang package. Every class in Java is directly or indirectly derived from the Object class. 
 * If a Class does not extend any other class then it is direct child class of Object and if extends other class then it is an 
 * indirectly derived. Therefore the Object class methods are available to all Java classes. Hence Object class acts as a root of 
 * inheritance hierarchy in any Java Program.
 * 
 * There are 12 methods in Object class, Mentioned few below
 *   1. toString() 
 *   2. hashCode()
 *   3. equals(Object obj)
 *   4. getClass()
 *   5. finalize() 
 *   6. clone()
 *   7. The remaining three methods wait(), notify() notifyAll() are related to Concurrency.
 */
public class ObjectClassMethods {

	// public native int hashCode();

	// public boolean equals(Object obj)

	// protected native Object clone() throws CloneNotSupportedException;

	// public String toString()

	// protected void finalize() throws Throwable

	public static void main(String[] args) {

	}

	/*
	 * toString() : toString() provides String representation of an Object and used to convert an object to String. 
	 * 
	 * The default toString() method for class Object returns a string consisting of the name of the class of which the object is
	 *  an instance, the at-sign character `@’, and the unsigned hexadecimal representation of the hash code of the object.
	 *  public String toString(){
	 *    return getClass().getName() + "@" + Integer.toHexString(hashCode());
	 *  }
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	/*
	 * hashCode() : For every object, JVM generates a unique number which is hashcode. It returns distinct integers for distinct 
	 * objects. A common misconception about this method is that hashCode() method returns the address of object, which is not
	 * correct. It convert the internal address of object to an integer by using an algorithm. The hashCode() method is native
	 * because in Java it is impossible to find address of an object, so it uses native languages like C/C++ to find address 
	 * of the object.
	 * 
	 * Override of hashCode() method needs to be done such that for every object we generate a unique number.
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	/*
	 * equals(Object obj) : Compares the given object to “this” object (the object on which the method is called). It gives
	 *  a generic way to compare objects for equality. It is recommended to override equals(Object obj) method to get our own 
	 *  equality condition on Objects. 
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/*
	 * getClass() : Returns the class object of “this” object and used to get actual runtime class of the object. It can also be
	 * used to get metadata of this class. The returned Class object is the object that is locked by static synchronized methods
	 *  of the represented class. As it is final so we don’t override it.
	 */
	// ObjectClassMethods.getClass()

	/*
	 * finalize() method : This method is called just before an object is garbage collected. It is called by the Garbage Collector
	 * on an object when garbage collector determines that there are no more references to the object. We should override 
	 * finalize() method to dispose system resources, perform clean-up activities and minimize memory leaks. For example before
	 * destroying Servlet objects web container, always called finalize method to perform clean-up activities of the session.
	 * 
	 * Note :finalize method is called just once on an object even though that object is eligible for garbage collection
	 * multiple times.
	 */
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	/*
	 * clone() : It returns a new object that is exactly the same as this object. For clone() method refer Clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
