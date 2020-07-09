package com.practice.ood.principle.solid;

public class OpenClosedPrinciple {

}

class Rectangle {
	public double	length;
	public double	width;
}

class RectangleAreaCalculator {
	public double calculateRectangleArea(Rectangle rectangle) {
		return rectangle.length * rectangle.width;
	}
}

class Circle {
	public double radius;
}

class CircleAreaCalculator {
	public double calculateRectangleArea(Rectangle rectangle) {
		return rectangle.length * rectangle.width;
	}

	public double calculateCircleArea(Circle circle) {
		return (22 / 7) * circle.radius * circle.radius;
	}
}

/*
 *  Note that this design is not extensible, i.e what if complicated shapes keep coming, AreaCalculator will need to keep on adding their computation logic 
 *  in newer methods. We are not really expanding the scope of shapes; rather we are simply doing piece-meal(bit-by-bit) solution for every shape that is added. 
 *  
 *  Modification of above design to comply with Open/Closed Principle: 
 *  Let us now see a more elegant design which solves the flaws in the above design by adhering to the Open/Closed Principle. We will first of all make
 *  the design extensible. For this we need to first define a base type Shape and have Circle & Rectangle implement Shape interface
 */

interface Shape {
	public double calculateArea();
}

class Rectangle2 implements Shape {
	double	length;
	double	width;

	public double calculateArea() {
		return length * width;
	}
}

class Circle2 implements Shape {
	public double radius;

	public double calculateArea() {
		return (22 / 7) * radius * radius;
	}
}