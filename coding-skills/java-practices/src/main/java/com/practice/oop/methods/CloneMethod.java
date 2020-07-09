package com.practice.oop.methods;

/*
 * Object cloning refers to creation of exact copy of an object. It creates a new instance of the class of current object and
 * initializes all its fields with exactly the contents of the corresponding fields of this object.
 */
public class CloneMethod {
	public static void main(String[] args) {

	}
}

/*
 * Using Assignment Operator to create copy of reference variable:
 *    In Java, there is no operator to create copy of an object. Unlike C++, in Java, if we use assignment operator then it will 
 *  create a copy of reference variable and not the object. This can be explained by taking an example. Following program 
 *  demonstrates the same. 
 */
class Model {
	int x, y;

	Model() {
		x = 10;
		y = 20;
	}
}

class ObjectAssignmentTest {
	public static void main(String[] args) {
		Model ob1 = new Model();

		System.out.println(ob1.x + " " + ob1.y);

		// Creating a new reference variable ob2 pointing to same address as ob1
		Model ob2 = ob1;

		// Any change made in ob2 will be reflected in ob1
		ob2.x = 100;

		System.out.println(ob1.x + " " + ob1.y);
		System.out.println(ob2.x + " " + ob2.y);
	}
}

/*Shallow Copy: Creating a copy using clone() method
* The class whose object’s copy is to be made must have a public clone method in it or in one of its parent class.
*  - Every class that implements clone() should call super.clone() to obtain the cloned object reference.
*  - The class must also implement java.lang.Cloneable interface whose object clone we want to create otherwise it will throw
*    CloneNotSupportedException when clone method is called on that class’s object.
*/
class ShallowCopy implements Cloneable {
	int		a;
	int		b;
	Model	c	= new Model();

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class ShallowCopyTest {
	public static void main(String args[]) throws CloneNotSupportedException {
		ShallowCopy t1 = new ShallowCopy();
		t1.a = 10;
		t1.b = 20;
		t1.c.x = 30;
		t1.c.y = 40;

		ShallowCopy t2 = (ShallowCopy) t1.clone();

		// Creating a copy of object t1 and passing it to t2
		t2.a = 100;

		// Change in primitive type of t2 will not be reflected in t1 field
		t2.c.x = 300;

		// Change in object type field will be reflected in both t2 and t1(shallow copy)
		System.out.println(t1.a + " " + t1.b + " " + t1.c.x + " " + t1.c.y);
		System.out.println(t2.a + " " + t2.b + " " + t2.c.x + " " + t2.c.y);
	}
}

/*
 *Deep Copy: 
 *  If we want to create a deep copy of object X and place it in a new object Y then new copy of any referenced objects fields 
 *  are created and these references are placed in object Y. This means any changes made in referenced object fields in object X 
 *  or Y will be reflected only in that object and not in the other. In below example, we create a deep copy of object. 
 *  A deep copy copies all fields, and makes copies of dynamically allocated memory pointed to by the fields. A deep copy occurs 
 *  when an object is copied along with the objects to which it refers.
 * 
 * Deep Copy vs Shallow Copy:
 *   Shallow copy is method of copying an object and is followed by default in cloning. In this method the fields of an old object
 *  X are copied to the new object Y. While copying the object type field the reference is copied to Y i.e object Y will point to
 *  same location as pointed out by X. If the field value is a primitive type it copies the value of the primitive type. 
 *  Therefore, any changes made in referenced objects in object X or Y will be reflected in other object.
 */
class DeepCopy implements Cloneable {
	int		a;
	int		b;
	Model	c	= new Model();

	public Object clone() throws CloneNotSupportedException {
		// Assign the shallow copy to new refernce variable t
		DeepCopy deepCopy = (DeepCopy) super.clone();
		deepCopy.c = new Model();
		return deepCopy;
	}
}

class DeepCopyTest {
	public static void main(String args[]) throws CloneNotSupportedException {
		DeepCopy t1 = new DeepCopy();
		t1.a = 10;
		t1.b = 20;
		t1.c.x = 30;
		t1.c.y = 40;

		DeepCopy t2 = (DeepCopy) t1.clone();

		// Creating a copy of object t1 and passing it to t2
		t2.a = 100;

		// Change in primitive type of t2 will not be reflected in t1 field
		t2.c.x = 300;

		// Change in object type field will be reflected in both t2 and t1(shallow copy)
		System.out.println(t1.a + " " + t1.b + " " + t1.c.x + " " + t1.c.y);
		System.out.println(t2.a + " " + t2.b + " " + t2.c.x + " " + t2.c.y);
	}
}