package com.practice.oop.methods;

public class HashCodeSample {
	public static void main(String jk[]) {
		Model1 ob1 = new Model1(10);
		Model1 ob2 = ob1;
		Model1 ob3 = new Model1(30);
		Model1 ob4 = new Model1(10);
		// Usage of getclass(): use to return the class name of particular object
		System.out.println("Class Name:" + ob2.getClass());
		// equals demo
		boolean tag;
		tag = ob1.equals(ob2);
		System.out.println("ob1.equals(ob2) are : " + tag);
		tag = ob1.equals(ob3);
		System.out.println("ob1.equals(ob3) are : " + tag);
		tag = ob1.equals(ob4);
		System.out.println("ob1.equals(ob4) are : " + tag);

		// hashcode demo

		int i = ob1.hashCode();
		System.out.println("ob1.hashCode() is :  " + i);

		int j = ob2.hashCode();
		System.out.println("ob2.hashCode() is :  " + j);

		int k = ob3.hashCode();
		System.out.println("ob3.hashCode() is :  " + k);

		int l = ob4.hashCode();
		System.out.println("ob4.hashCode() is :  " + l);
	}
}

class Model1 {
	int a;

	public Model1(int x) {
		a = x;
	}

	public boolean equals(Object obj) {
		// Reason to Override equals():- Used to check the value present in the HashCodeDemo object,
		Model1 h = (Model1) obj;
		if (h.a == this.a) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		// Reason to hashCode equals():-
		// Returns the same hash code for different object, if object has same value.
		// Here 10 is random value, hashcode depends on instant variable 'a' in the class HashCodeDemo
		return (a * 10);
	}
}