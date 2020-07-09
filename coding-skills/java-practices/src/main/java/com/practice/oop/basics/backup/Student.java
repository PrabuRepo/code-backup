package com.practice.oop.basics.backup;

public class Student {
	String Name;
	int rollNo;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Name + rollNo;
	}
}
