package com.practice.oop.concepts;

/*
 * Definition: It is the mechanism in java by which one class is allow to inherit the features(fields and methods) of another class.
 * Terminology: Super Class, Sub Class
 * Keyword: extends
 * Default Super class: Object
 * Types:
 *   - Single parent & one child 
 *   - Multilevel: Parent-child1(derive from parent)-child2(derive from child1)-.....
 *   - Hierarchical: One Parent & multiple child
 *   - Multiple(through interfaces): More than one Parent & one child
 *   - Hybrid(through interfaces): Combination of Hierarchical & Multiple 
 *   
 *  Inheritance: IS-A relationship
 *  Association: HAS-A relationship (Aggregation relation is “has-a” and composition is “part-of” relation)
 */
public class Inheritance {
	public static void main(String args[]) {
		// Super class Instance:
		Bicycle bicycle = new Bicycle(3, 100, true);
		bicycle.bicyleDisplay();

		// Sub class Instance:
		MountainBike mb = new MountainBike(3, 100, 25);
		mb.bicyleDisplay();
		mb.mountainBikeDisplay();
	}
}

// base class
class Bicycle {
	// the Bicycle class has two fields
	public int		gear;
	public int		speed;
	private boolean	blueTooth;

	// the Bicycle class has one constructor
	public Bicycle(int gear, int speed) {
		this.gear = gear;
		this.speed = speed;
	}

	// the Bicycle class has one constructor
	public Bicycle(int gear, int speed, boolean blueTooth) {
		this.gear = gear;
		this.speed = speed;
		this.blueTooth = blueTooth;
	}

	public void bicyleDisplay() {
		System.out.println("Gear: " + gear + " Speed: " + speed + " BlueTooth: " + blueTooth);
	}

}

// derived class
class MountainBike extends Bicycle {

	// the MountainBike subclass adds one more field
	public int seatHeight;

	// the MountainBike subclass has one constructor
	public MountainBike(int gear, int speed, int startHeight) {
		// invoking base-class(Bicycle) constructor
		super(gear, speed);
		seatHeight = startHeight;
	}

	public void mountainBikeDisplay() {
		System.out.println("Gear: " + gear + " Speed: " + speed + " Seat Height: " + seatHeight);
		// Cannot access the private variable from in subclass
	}
}
