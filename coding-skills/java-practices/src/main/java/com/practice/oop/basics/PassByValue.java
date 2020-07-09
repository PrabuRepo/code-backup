package com.practice.oop.basics;

/*
 * Java supports only pass by value. The arguments passed as a parameter to a method is mainly primitive data types or objects. 
 * For the data type the actual value is passed.Java passes the references by value just like any other parameter. The pointer 
 * to the object is passed as value. Thus, method manipulation will alter the objects but will not change the object reference or 
 * initialize the new object. 
 */
public class PassByValue {

	public static void tricky(Point arg1, Point arg2) {
		arg1.x = 100;
		arg1.y = 100;
		Point temp = arg1;
		arg1 = arg2;
		arg2 = temp;
	}

	public static void main(String[] args) {
		Point pnt1 = new Point(0, 0);
		Point pnt2 = new Point(20, 20);
		System.out.println("X: " + pnt1.x + " Y: " + pnt1.y);
		System.out.println("X: " + pnt2.x + " Y: " + pnt2.y);
		System.out.println(" ");
		tricky(pnt1, pnt2);
		System.out.println("X: " + pnt1.x + " Y:" + pnt1.y);
		System.out.println("X: " + pnt2.x + " Y: " + pnt2.y);
	}
}

class Point {
	int	x;
	int	y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
