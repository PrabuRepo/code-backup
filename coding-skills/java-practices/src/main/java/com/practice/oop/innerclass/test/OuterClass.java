package com.practice.oop.innerclass.test;

/**
 * 
 * @author Prabu
 *
 */
public class OuterClass {

	int x = 5;

	/**
	 * 
	 * @author Prabu
	 *
	 */
	public class InnerClass{
		int y = 20;
		
		void displayInner(){
			x= 10;
			System.out.println("Inner class variable:"+x);
			displayOuter();
		}
	}
	
	void displayOuter(){
		InnerClass innerClass = new InnerClass();
		System.out.println("Outer class variable:"+innerClass.y);
	}
}

