package com.practice.oop.interfaces;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Marker interface:
 *  It is an empty interface (no field or methods). Examples of marker interface are Serializable, Cloneable and Remote interface.
 *  All these interfaces are empty interfaces.
 *  public interface Serializable{
 *    // nothing here
 *  }
 */
public class MarkerInterfaceTest {
	public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		CloneableTest ob1 = new CloneableTest(10, "abc");
		CloneableTest ob2 = (CloneableTest) ob1.clone();
		ob2.i = 20;
		ob2.s = "def";
		System.out.println("ob1: " + ob1.i + ", " + ob1.s);
		System.out.println("ob2: " + ob2.i + ", " + ob2.s);

		/*
		Without Cloneable Interface, clone method throws CloneNotSupportedException.
		Exception in thread "main" java.lang.CloneNotSupportedException: com.practice.interfaces.CloneableTest2
		at java.lang.Object.clone(Native Method)
		CloneableTest2 obj1 = new CloneableTest2(100, "abcd");
		CloneableTest2 obj2 = (CloneableTest2) obj1.clone();
		obj2.i = 200;
		obj2.s = "efgh";
		System.out.println("obj1: " + obj1.i + ", " + obj1.s);
		System.out.println("obj2: " + obj2.i + ", " + obj2.s);
		*/

		SerializableTest a = new SerializableTest(20, "test string");

		// Serializing 'a'
		FileOutputStream fos = new FileOutputStream("xyz.txt");
		// FileOutputStream fos = new FileOutputStream("C:\\Users\\xyz\\Desktop\\Local Deployment\\xyz.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(a);

		// De-serializing 'a'
		FileInputStream fis = new FileInputStream("xyz.txt");
		// FileInputStream fis = new FileInputStream("C:\\Users\\xyz\\Desktop\\Local Deployment\\xyz.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		SerializableTest b = (SerializableTest) ois.readObject();// down-casting object

		System.out.println(b.i + " " + b.s);

		// closing streams
		oos.close();
		ois.close();
	}
}

/* 1.Cloneable interface: Cloneable interface is present in java.lang package. There is a method clone() in Object class.
 *  A class that implements the Cloneable interface indicates that it is legal for clone() method to make a field-for-field
 *   copy of instances of that class.
 *   Invoking Object’s clone method on an instance of the class that does not implement the Cloneable interface results in an 
 *   exception CloneNotSupport
 *   The class must also implement java.lang.Cloneable interface whose object clone we want to create otherwise it will throw 
 *   CloneNotSupportedException when clone method is called on that class’s object.
 *   */
class CloneableTest implements Cloneable {
	int i;
	String s;

	public CloneableTest(int i, String s) {
		this.i = i;
		this.s = s;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

// Using clone() method without Cloneable interface - It throws CloneNotSupportedException
class CloneableTest2 {
	int i;
	String s;

	public CloneableTest2(int i, String s) {
		this.i = i;
		this.s = s;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class SerializableTest implements Serializable {
	private static final long serialVersionUID = 291291L;

	int i;
	String s;

	public SerializableTest(int i, String s) {
		this.i = i;
		this.s = s;
	}
}

/*Remote interface : Remote interface is present in java.rmi package. A remote object is an object which is stored at one 
 * machine and accessed from another machine. So, to make an object a remote object, we need to flag it with Remote interface. 
 * Here, Remote interface serves to identify interfaces whose methods may be invoked from a non-local virtual machine.Any 
 * object that is a remote object must directly or indirectly implement this interface. RMI (Remote Method Invocation) provides 
 * some convenience classes that remote object implementations can extend which facilitate remote object creation.*/
