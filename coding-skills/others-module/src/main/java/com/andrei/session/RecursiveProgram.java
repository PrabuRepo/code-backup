package com.andrei.session;

public class RecursiveProgram {

	 static int count=1;
	
	static void headRecursiveCall(int n) {
		//No of calls in recursive program: n+1s
		System.out.println("No of calls:"+count++);
		if(n>=1) {
			System.out.println("Before recursive fun call");
			headRecursiveCall(n-1);
			System.out.println("Value:"+n);
		}
			
	}
	
	static void tailRecursiveCall(int n) {
		//No of calls in recursive program: n+1s
		//System.out.println("No of calls:"+count++);
		if(n>=1) {
			tailRecursiveCall(n-1);
			System.out.println("Value:"+n);
			tailRecursiveCall(n-1);
		}
			
	}
	public static void main(String[] args) {
		//headRecursiveCall(5);
		tailRecursiveCall(5);
	}

}
