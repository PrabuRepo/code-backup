package com.practice.oop.innerclass;

/**
 * Outerclass can access inner class variables & methods & viceversa. 
 * Innerclass can access any (Private or Public) variable/methods of outerclass and viceversa.
 * But to access Innerclass variable/method, should create object for that in outer class .
 */
class OuterClass{
	
	private int x =20;
	
	/* 
	 * Can use modifier's like final, abstract, public,private,protected & static.
	 * It just acts like normal variable.
	 */
	class InnerClass{
		private int ans;
		
		public void getValue(){
			display();
			System.out.println("Inside Inner Class:"+x);
		}
		
		public void display(){
			//To call the outer class display, should follow below way. 
			OuterClass.this.display();
			System.out.println("Inner class display");
		}
	}
	
	public void display(){
		System.out.println("Outer class display");
	}
	
	public void accessInner(){
		InnerClass obj = new InnerClass();
		obj.ans = 500;
		System.out.println("Access Inner:"+obj.ans);
		obj.getValue();
		System.out.println("--------------------");
	}
}

public class InsideClass {
	public static void main(String[] args) {
		OuterClass outObj = new OuterClass();
		outObj.accessInner();
		//OuterClass.InnerClass inObj = outObj.new InnerClass();
		OuterClass.InnerClass inObj = new OuterClass().new InnerClass();
		inObj.getValue();
	}
}
