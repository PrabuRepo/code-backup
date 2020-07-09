package com.practice.oop.innerclass;

/**
 * Used to override the methods in the other class. Because we can’t access 
 * method, if we create it inside the class from Outside. 
 * We can use Anonymous class to create object for abstract class/Interface.
 **/
class OuterClass2{
	public void display(){
		System.out.println("Outer class display");
	}
}

class InnerClass2{
	//a
	OuterClass2 obj = new OuterClass2(){
		public void display(){
			System.out.println("Overridden in Inner class");
			test();
		}
		/**
		 * We can't access this method outside by using instanse obj.
		 * Can access only inside overriding method.
		 */
		
		public void test() {
		System.out.println("TEST");	
		}
	};
}

public class AnonymousClass {
	public static void main(String[] args) {
		InnerClass2 inObj = new InnerClass2();
		inObj.obj.display();
		OuterClass2 obj1 = new OuterClass2(){
			public void display(){
				System.out.println("Overridden in Main method");
			}
		};
		obj1.display();
	}
}
