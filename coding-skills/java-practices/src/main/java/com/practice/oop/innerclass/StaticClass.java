package com.practice.oop.innerclass;

/**
 * Inner class with static modifier. It acts like a inner class, but static rule applies for this class
 * i.e it can access only static variable/method.
 */
class OuterClass3{
	
	static int x =20;
	int y=100;
	//Should use modifier static.
	static class InnerClass3{
		public int ans;
		public void getValue(){
			display();
			System.out.println("Inside Inner Class:"+x);
			//System.out.println("Inside Inner Class:"+y); Throws compilation error
			System.out.println("Inner class Var:"+ans);
		}
	}
	public static void display(){
		System.out.println("Outer class display");
	}
	/*public void accessInner(){
		InnerClass obj = new InnerClass();
		obj.ans = 500;
		System.out.println("Access Inner:"+obj.ans);
		obj.getValue();
		System.out.println("--------------------");
	}*/
}

public class StaticClass {
	public static void main(String[] args) {
		//We don't need to create instance variable for static class, can access using class name.
		OuterClass3.InnerClass3 inObj = new OuterClass3.InnerClass3();
		//For normal inner class, have to create instance in below way
		//OuterClass.InnerClass inObj = new OuterClass().new InnerClass();
		inObj.ans = 100;
		inObj.getValue();
	}
}
