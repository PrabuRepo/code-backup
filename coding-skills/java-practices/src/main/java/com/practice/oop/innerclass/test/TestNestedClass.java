package com.practice.oop.innerclass.test;

import com.practice.oop.innerclass.test.OuterClass.InnerClass;

public class TestNestedClass {

	
	public static void main(String[] args){
		OuterClass outerClass = new OuterClass();
		OuterClass.InnerClass innerClass = outerClass.new InnerClass();
		outerClass.displayOuter();
		innerClass.displayInner();
		
	}

}
