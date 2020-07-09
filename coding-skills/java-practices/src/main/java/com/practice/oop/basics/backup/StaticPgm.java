package com.practice.oop.basics.backup;

public class StaticPgm {
	String k;
	Student stu = new Student();
	Integer value;
	//System.out.println();  Compilation Error 
	static{
		/*System.out.println(k);
		k =8;
		System.out.println(k);*/
	}
	static void meth2(String k, Student stu, Integer value){
		//Static variable can't access directly in static method, but can access from 
		//other method calls
		k="cmcd";
		stu.setName("XYZ");
		stu.setRollNo(888);
		System.out.println(k);
		value = 55;
		//Local variables can’t be declared as static.     
		//static int temp;
	}
	void meth1(){
		System.out.println("Before static method call");
		k = new String("sds");
		stu.setName("Prabu");
		stu.setRollNo(121);
		value = 33;
		System.out.println(k);
		System.out.println(stu);
		System.out.println(value);
		meth2(k, stu, value);
		System.out.println("After static method call");
		System.out.println(k);
		System.out.println(stu);
		System.out.println(value);
		//Local variables can’t be declared as static.     
		//static int temp;
	}
	public static void main(String[] args) {
		StaticPgm obj = new StaticPgm();
		obj.meth1();
	}
}
