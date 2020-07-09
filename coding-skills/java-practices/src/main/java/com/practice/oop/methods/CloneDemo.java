package com.practice.oop.methods;

/**
 * Cloneable interface has clone metod, which is used to create different hashcode/reference
 * even if we copy object value from another object. 
 *
 */
public class CloneDemo implements Cloneable {
	   int x;
	   int y;
	  
	  public Object clone() throws CloneNotSupportedException 
	  {
	    return super.clone();
	  }
  public static void main(String[] args) throws CloneNotSupportedException {
	  CloneDemo t1 = new CloneDemo();
    t1.x = 100;
    t1.y = 200;
    CloneDemo t2 = (CloneDemo)t1.clone();
    CloneDemo t3 = (CloneDemo)t2.clone();
	System.out.println("Before init...............");
	System.out.println("\n\t T1 value 1 : "+t1.x);
	System.out.println("\n\t T1 value 2 : "+t1.y);
	System.out.println("\n\t T2 value 1 : "+t2.x);
	System.out.println("\n\t T2 value 2 : "+t2.y);
	System.out.println("\n\t T3 value 1 : "+t3.x);
	System.out.println("\n\t T3 value 2 : "+t3.y);
    t2.x = 500;
    t2.y = 600;

	System.out.println("After init........");
	System.out.println("\n\t T1 value 1 : "+t1.x);
	System.out.println("\n\t T1 value 2 : "+t1.y);
	System.out.println("\n\t T2 value 1 : "+t2.x);
	System.out.println("\n\t T2 value 2 : "+t2.y);
	System.out.println("\n\t T3 value 1 : "+t3.x);
	System.out.println("\n\t T3 value 2 : "+t3.y);
    
    System.out.print("\n\t t1.hashCode() is :  "+t1.hashCode());
    System.out.print("\n\t t2.hashCode()  is : "+t2.hashCode());
    System.out.print("\n\t t3.hashCode()  is : "+t3.hashCode());
  }
}
