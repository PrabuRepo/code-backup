package com.practice.oop.innerclass;

/**
 * Outerclass can't access inner class variables & methods & but innerClass access variables & 
 * methods of outer class. Innerclass variables can access only inside that method where we 
 * created. 
 */

class OuterClass1{
	
	private int x =20;
	/* 
	 * Can use modifier's like final, abstract, public,private,protected & static.
	 * It just acts like normal variable.
	 */
	
	public void innerClassMtd(){
		int rel = 500;
		//Can use modifier's like final & abstract
		class InnerClass1{
			private int ans;
			
			public void getValue(){
				System.out.println("Inner Class inside the method:"+x);
				/* We can't even access the local variable of innerClassMtd() & throws
				 * compilation error , but it accessible if use final modifier.
				 */
				
				//System.out.println("Inside Inner Class:"+rel);
			}
		}
		new InnerClass1().getValue();
	}
	
}

public class InsideMethod {
	public static void main(String[] args) {
		OuterClass1 outObj = new OuterClass1();
		outObj.innerClassMtd();
	}
}
