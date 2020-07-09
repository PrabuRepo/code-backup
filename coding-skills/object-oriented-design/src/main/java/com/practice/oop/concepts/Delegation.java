package com.practice.oop.concepts;

/*
 * Delegation means hand over the responsibility for a particular task to another class or method. It is a technique where an object expresses certain
 * behavior to the outside but in reality delegates responsibility for implementing that behaviour to an associated object.
 * Applicability: Use the Delegation in order to achieve the following
 *    Reduce the coupling of methods to their class
 *    Components that behave identically, but realize that this situation can change in the future.
 *    If you need to use functionality in another class but you do not want to change that functionality then use delegation instead of inheritance.
 */
public class Delegation {
	public static void main(String[] args) {
		// Here TicketBookingByAgent class is internally
		// delegating train ticket booking responsibility to other class
		TicketBookingByAgent agent = new TicketBookingByAgent(new TrainBooking());
		agent.bookTicket();

		agent = new TicketBookingByAgent(new AirBooking());
		agent.bookTicket();
	}
}

/*
 * Example 1: Let's take Ticket Booking exampleExample 1: Let's take Ticket Booking example
 */
interface TravelBooking {
	public void bookTicket();
}

class TrainBooking implements TravelBooking {
	@Override
	public void bookTicket() {
		System.out.println("Train ticket booked");
	}
}

class AirBooking implements TravelBooking {
	@Override
	public void bookTicket() {
		System.out.println("Flight ticket booked");
	}
}

class TicketBookingByAgent implements TravelBooking {

	TravelBooking t;

	public TicketBookingByAgent(TravelBooking t) {
		this.t = t;
	}

	// Delegation --- Here ticket booking responsibility
	// is delegated to other class using polymorphism
	@Override
	public void bookTicket() {
		t.bookTicket();
	}
}
