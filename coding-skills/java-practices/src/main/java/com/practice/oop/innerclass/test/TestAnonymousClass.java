package com.practice.oop.innerclass.test;

public class TestAnonymousClass {

	public static void main(String[] args){
		Employer employer = new Employer() {
			
			@Override
			void displayUser() {
				System.out.println("Display user");
				
			}
		};
		employer.displayUser();
	}
	
}


abstract class Employer{
	
	abstract void displayUser();
}
