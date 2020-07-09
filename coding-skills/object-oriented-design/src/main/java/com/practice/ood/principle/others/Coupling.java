package com.practice.ood.principle.others;

public class Coupling {
	public static void main(String[] args) {

	}
}

/*
 * Definition:
 * 
 * Example:
 *  Think of your skin. It's stuck to your body. It fits like a glove. But what if you wanted to change your skin color
 *  from say white to black? Can you imagine just how painful it would be to peel off your skin, dye it, and then to 
 *  paste it back on etc? Changing your skin is difficult because it is tightly coupled to your body. You just can't 
 *  make changes easily. You would have to fundamentally redesign a human being in order to make this possible.
 *  If you want to change the skin, you would also HAVE TO change the design of your body as well because the two are 
 *  joined together - they are tightly coupled. "God was not a good object oriented programmer".
 * 
 * Tight Coupling Vs Loose Coupling:
 * 
 * To be clear in the service orientation architecture, services are loosely coupled to each other against monolithic 
 * which classes dependency to each other is on purpose
 */
// Flow: Client class -> Travel -> Car & Bike
class TightCoupling {

	public void main(String[] args) {
		Travel travel = new Travel();
		System.out.println("Car Journey: ");
		travel.startCarJourney();

		System.out.println("Bike Journey: ");
		travel.startBikeJourney();
	}
}

/* Tight Coupling:
 *  In the below example, Travel class is dependent on Car, Bike class to provide service to the end user called Journey. 
 * Car, Bike class is dependency of Travel class. 
 * In the above case Travel class is tightly coupled with Car class it means if any change in Car class requires Travel
 * class to change. For example if car class travel() method change go() method then Travel class startJourney() method 
 * need to call go() instead of travel() method.
 */
class Travel {
	Car car = new Car();

	void startCarJourney() {
		car.go();
	}

	Bike bike = new Bike();

	void startBikeJourney() {
		car.go();
	}
}

class Car {
	void go() {
		System.out.println("Travel by Car");
	}
}

class Bike {
	void go() {
		System.out.println("Travel by Car");
	}
}

/*
 * Definition: 
 *    In simple words, loose coupling means they are mostly independent.
 * Example:
 * The Hat is "loosely coupled" to the body. This means you can easily take the hat off without making any changes 
 * to the person/body. When you can do that then you have "loose coupling". See below for elaboration.
 * Now think of getting dressed in the morning. You don't like blue? No problems: you can put a red shirt on instead.
 * You can do this easily and effortlessly because the shirt is not really connected to your body the same way as your skin.
 * If you change your shirt, then you are not forced to change your body - when you can do that, then you have loose coupling.
 *  When you can't do that, then you have tight coupling.
 */
// Flow: Client class -> Travel -> Vehicle(Interface) -> Car & Bike
class LooseCoupling {
	public static void main(String[] args) {
		Travel2 travel = new Travel2();
		travel.setV(new Car2());
		System.out.println("Car Journey: ");
		travel.startJourney();

		travel.setV(new Bike2());
		System.out.println("Bike Journey: ");
		travel.startJourney();
	}
}

/* Loose Coupling: 
 * In the below example Car2 and Travel2 objects are loose coupled. It means Vehicle is an Interface and we can inject any of the
 * implemented classes at runtime and we can provide service to end user called Journey. The dependent object is externally 
 * injected by calling its setter method. So in Travel class no changes are required even though if we change vehicle , Car, 
 * Bike. This concept is called loose coupling between objects. 
 *
 * Spring framework provides loose coupling between objects by following poji/pojo model, and hence spring is said to be a 
 * most popular framework for the development of enterprise level applications in java.
 **/
class Travel2 {
	Vehicle v;

	public void setV(Vehicle v) {
		this.v = v;
	}

	public void startJourney() {
		v.start();
	}
}

interface Vehicle {
	void start();
}

// In car2 & Bike2 start() method cant be changed to go method. If we need any
// change, we should change the interface first.
class Car2 implements Vehicle {
	@Override
	public void start() {
		System.out.println("Travel by car");

	}
}

class Bike2 implements Vehicle {
	@Override
	public void start() {
		System.out.println("Travel by Bike");

	}
}
