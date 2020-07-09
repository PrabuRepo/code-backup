package com.practice.ood.pattern.structural;

public class FacadePattern {
	public static void main(String[] args) {
		ShapeMaker shapeMaker = new ShapeMaker();

		shapeMaker.drawCircle();
		shapeMaker.drawRectangle();
		shapeMaker.drawSquare();
	}
}

interface Shape1 {
	void draw();
}

class Circle1 implements Shape1 {

	@Override
	public void draw() {
		System.out.println("Circle: draw() Method");
	}

}

class Square1 implements Shape1 {
	@Override
	public void draw() {
		System.out.println("Square: draw() Method");
	}
}

class Rectangle1 implements Shape1 {
	@Override
	public void draw() {
		System.out.println("Rectangle: draw() Method");
	}
}

class ShapeMaker {
	private Shape1	circle;
	private Shape1	rectangle;
	private Shape1	square;

	public ShapeMaker() {
		circle = new Circle1();
		rectangle = new Rectangle1();
		square = new Square1();
	}

	public void drawCircle() {
		circle.draw();
	}

	public void drawRectangle() {
		rectangle.draw();
	}

	public void drawSquare() {
		square.draw();
	}
}