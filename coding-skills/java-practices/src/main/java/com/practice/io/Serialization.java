package com.practice.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*serialization:
 *   Serialization is the process of converting the object state to a byte stream.
 *   Deserialization is the process of converting the serialized form of an object back into a copy of the object.
 *   
 *   A Java object is serializable if its class or any of its superclasses implements either the java.io.Serializable interface 
 *   or its subinterface, java.io.Externalizable. Serializable is a Marker Interface. 
 *   
 *   When an object has to be transferred over a network (typically through rmi or EJB) or to persist the state of an object 
 *   to a file, the object Class needs to implement Serializable interface. Implementing this interface will allow the object 
 *   converted into bytestream and transfer over a network.
 *   
 * The serialization is used :-
 *   - To send state of one or more object’s state over the network through a socket.
 *   - To save the state of an object in a file.
 *   - An object’s state needs to be manipulated as a stream of bytes.
 *   
 * What is use of serialVersionUID?
 * 	During object serialization, the default Java serialization mechanism writes the metadata about the object, which includes 
 * the class name, field names and types, and superclass. This class definition is stored as a part of the serialized object. This 
 * stored metadata enables the deserialization process to reconstitute the objects and map the stream data into the class attributes
 * with the appropriate type
 * Everytime an object is serialized the java serialization mechanism automatically computes a hash value. ObjectStreamClass's 
 * computeSerialVersionUID() method passes the class name, sorted member names, modifiers, and interfaces to the secure hash 
 * algorithm (SHA), which returns a hash value.The serialVersionUID is also called suid.So when the serilaize object is retrieved, 
 * the JVM first evaluates the suid of the serialized class and compares the suid value with the one of the object. If the suid 
 * values match then the object is said to be compatible with the class and hence it is de-serialized. If not InvalidClassException
 * exception is thrown. 
 */
public class Serialization {
	public static void main(String[] args) {

		SerailizeModel c = new SerailizeModel(100, "ONE");

		System.out.println("Serialization...");
		System.out.println("Before serialization object state:" + c.name + ", " + c.age);
		try {
			FileOutputStream fs = new FileOutputStream("testTransient.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(c);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("DeSerialization...");
		try {
			FileInputStream fis = new FileInputStream("testTransient.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			c = (SerailizeModel) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After  de-serialization:" + c.name + ", " + c.age);

	}
}

class TestTransientVal implements Serializable {
	private static final long	serialVersionUID	= -22L;
	private String				name;
	transient private int		age;

	TestTransientVal(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public static void main(String[] args) {
		TestTransientVal c = new TestTransientVal(100, "ONE");
		System.out.println("Before serialization:" + c.name + " " + c.age);
		try {
			FileOutputStream fs = new FileOutputStream("testTransient.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(c);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream("testTransient.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			c = (TestTransientVal) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After  de-serialization:" + c.name + " " + c.age);
	}
}

class SerailizeModel implements Serializable {
	private static final long serialVersionUID = -22L;

	public String	name;
	public int		age;

	SerailizeModel(int age, String name) {
		this.age = age;
		this.name = name;
	}

}