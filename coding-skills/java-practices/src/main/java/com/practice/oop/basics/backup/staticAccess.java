package com.practice.oop.basics.backup;

public class staticAccess {

	static int stVar1;
	int instVar1;
	
	public staticAccess(int instData, int statData) {
		this.instVar1 = instData;
		stVar1 = statData;
	}
	
	static{
		//1. Can't initialize the Non-Static variable inside static block
		//2. Used to intialize the variable while loading the class itself
		//instVar1 = 50; //Compilation error
		stVar1 =100;
	}
	
	
	public static void staticMethod(){
		System.out.println("Inside static method");
		System.out.println("Static Variable:"+stVar1);
		//Normal instance variable can't access inside the static method
		//System.out.println("Instance Variable:"+instVar1);
	}
	
	public void normalMethod(){
		System.out.println("Inside Normal method");
		//Static variable can access by other methods
		System.out.println("Static Variable:"+stVar1);
		System.out.println("Instance Variable:"+instVar1);
	}
	public static void main(String[] args) {
		staticAccess obj1 = new staticAccess(5, 10);
		System.out.println("****Before Obj2 Creation:Obj1 Values****");
		//staticAccess.staticMethod();
		obj1.staticMethod();
		obj1.normalMethod();
		staticAccess obj2 = new staticAccess(22, 44);
		System.out.println("****After Obj2 Creation:Obj1 Values****");
		staticAccess.staticMethod();
		//obj1.staticMethod();
		obj1.normalMethod();
		System.out.println("****Obj2 Values****");
		obj2.staticMethod();
		obj2.normalMethod();
	}

}
