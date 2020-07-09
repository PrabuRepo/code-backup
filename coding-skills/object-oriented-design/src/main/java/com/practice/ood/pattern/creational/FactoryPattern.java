package com.practice.ood.pattern.creational;

public class FactoryPattern {

	public static void main(String[] args) {

		System.out.println("Factory Design Pattern: Sample 1");
		ShapeFactory factory = new ShapeFactory();

		Shape shape = factory.getInstance("Circle");
		shape.draw();
		shape = factory.getInstance("Square");
		shape.draw();
		shape = factory.getInstance("Rectangle");
		shape.draw();

		System.out.println("Factory Design Pattern: Sample 2");

		System.out.println(CarFactory.buildCar(CarType.SMALL));
		System.out.println(CarFactory.buildCar(CarType.SEDAN));
		System.out.println(CarFactory.buildCar(CarType.LUXURY));
	}

}

/* Sample1: Simple Factory Patter example */
interface Shape {
	void draw();
}

class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Circle: draw() Method");
	}

}

class Square implements Shape {
	@Override
	public void draw() {
		System.out.println("Square: draw() Method");
	}
}

class Rectangle implements Shape {
	@Override
	public void draw() {
		System.out.println("Rectangle: draw() Method");
	}
}

class ShapeFactory {
	public Shape getInstance(String shape) {
		if (shape == null)
			return null;
		else if (shape.equalsIgnoreCase("Circle"))
			return new Circle();
		else if (shape.equalsIgnoreCase("Square"))
			return new Square();
		else if (shape.equalsIgnoreCase("Rectangle"))
			return new Rectangle();
		else
			return null;
	}
}

/* Sample 2: Same sample used in AbstractFactoryPattern class as well*/

enum CarType {
	SMALL, SEDAN, LUXURY
}

abstract class Car {

	private CarType model = null;
	private Location location = null;

	public Car(CarType model) {
		this.model = model;
		arrangeParts();
	}

	public Car(CarType model, Location location) {
		this.model = model;
		this.location = location;
	}

	private void arrangeParts() {
		// Do one time processing here
	}

	// Do subclass level processing in this method
	protected abstract void construct();

	public CarType getModel() {
		return model;
	}

	public void setModel(CarType model) {
		this.model = model;
	}
}

class LuxuryCar extends Car {

	LuxuryCar() {
		super(CarType.LUXURY);
		construct();
	}

	LuxuryCar(Location location) {
		super(CarType.LUXURY, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building luxury car");
		// add accessories
	}
}

class SedanCar extends Car {

	SedanCar() {
		super(CarType.SEDAN);
		construct();
	}

	SedanCar(Location location) {
		super(CarType.SEDAN, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building sedan car");
		// add accessories
	}
}

class SmallCar extends Car {

	SmallCar() {
		super(CarType.SMALL);
		construct();
	}

	SmallCar(Location location) {
		super(CarType.SMALL, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building small car");
		// add accessories
	}
}

class CarFactory {
	public static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case SMALL:
			car = new SmallCar();
			break;

		case SEDAN:
			car = new SedanCar();
			break;

		case LUXURY:
			car = new LuxuryCar();
			break;

		default:
			// throw some exception
			break;
		}
		return car;
	}
}
