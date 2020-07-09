package com.practice.ood.pattern.creational;

//Read this one more time 
public class AbstractFactoryPattern {
	public static void main(String[] args) {
		System.out.println(CarFactory2.buildCar(CarType.SMALL));
		System.out.println(CarFactory2.buildCar(CarType.SEDAN));
		System.out.println(CarFactory2.buildCar(CarType.LUXURY));
	}
}

enum Location {
	DEFAULT, USA, ASIA, INDIA
}

class INDIACarFactory {
	static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case SMALL:
			car = new SmallCar(Location.INDIA);
			break;

		case SEDAN:
			car = new SedanCar(Location.INDIA);
			break;

		case LUXURY:
			car = new LuxuryCar(Location.INDIA);
			break;

		default:
			break;

		}
		return car;
	}
}

class DefaultCarFactory {
	public static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case SMALL:
			car = new SmallCar(Location.DEFAULT);
			break;

		case SEDAN:
			car = new SedanCar(Location.DEFAULT);
			break;

		case LUXURY:
			car = new LuxuryCar(Location.DEFAULT);
			break;

		default:
			break;

		}
		return car;
	}
}

class USACarFactory {
	public static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case SMALL:
			car = new SmallCar(Location.USA);
			break;
		case SEDAN:
			car = new SedanCar(Location.USA);
			break;
		case LUXURY:
			car = new LuxuryCar(Location.USA);
			break;
		default:
			break;
		}
		return car;
	}
}

class CarFactory2 {
	private CarFactory2() {

	}

	public static Car buildCar(CarType type) {
		Car car = null;
		// We can add any GPS Function here which
		// read location property somewhere from configuration
		// and use location specific car factory
		// Currently I'm just using INDIA as Location
		Location location = Location.USA;

		switch (location) {
		case USA:
			car = USACarFactory.buildCar(type);
			break;

		case INDIA:
			car = INDIACarFactory.buildCar(type);
			break;

		default:
			car = DefaultCarFactory.buildCar(type);
		}
		return car;
	}
}
