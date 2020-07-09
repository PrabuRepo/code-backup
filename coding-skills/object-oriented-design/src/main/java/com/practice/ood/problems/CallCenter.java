package com.practice.ood.problems;

import java.util.ArrayList;
import java.util.List;

public class CallCenter {
	public static void main(String[] args) {
		CallHandler ch = new CallHandler();
	}
}

/* Represents a call from a user. Calls have a minimum rank and are assigned to the
 * first employee who can handle that call.
 */
class Call {
	/* Minimal rank of employee who can handle this call. */
	private Rank rank;

	/* Person who is calling. */
	private Caller caller;

	/* Employee who is handling call. */
	private Employee handler;

	public Call(Caller c) {
		rank = Rank.Responder;
		caller = c;
	}

	/* Set employee who is handling call. */
	public void setHandler(Employee e) {
		handler = e;
	}

	/* Play recorded message to the customer. */
	public void reply(String message) {
		System.out.println(message);
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank r) {
		rank = r;
	}

	public Rank incrementRank() {
		if (rank == Rank.Responder) {
			rank = Rank.Manager;
		} else if (rank == Rank.Manager) {
			rank = Rank.Director;
		}
		return rank;
	}

	/* Disconnect call. */
	public void disconnect() {
		reply("Thank you for calling");
	}
}

/* CallHandler represents the body of the program,
 * and all calls are funneled first through it. 
 */
class CallHandler {
	/* We have 3 levels of employees: respondents, managers, directors. */
	private final int LEVELS = 3;

	/* Initialize with 10 respondents, 4 managers, and 2 directors. */
	private final int NUM_RESPONDENTS = 10;
	private final int NUM_MANAGERS = 4;
	private final int NUM_DIRECTORS = 2;

	/* List of employees, by level.
	 * employeeLevels[0] = respondents
	 * employeeLevels[1] = managers
	 * employeeLevels[2] = directors
	 */
	List<List<Employee>> employeeLevels;

	/* queues for each call�s rank */
	List<List<Call>> callQueues;

	public CallHandler() {
		employeeLevels = new ArrayList<List<Employee>>(LEVELS);
		callQueues = new ArrayList<List<Call>>(LEVELS);

		// Create respondents.
		ArrayList<Employee> respondents = new ArrayList<Employee>(NUM_RESPONDENTS);
		for (int k = 0; k < NUM_RESPONDENTS - 1; k++) {
			respondents.add(new Respondent(this));
		}
		employeeLevels.add(respondents);

		// Create managers.
		ArrayList<Employee> managers = new ArrayList<Employee>(NUM_MANAGERS);
		managers.add(new Manager(this));
		employeeLevels.add(managers);

		// Create directors.
		ArrayList<Employee> directors = new ArrayList<Employee>(NUM_DIRECTORS);
		directors.add(new Director(this));
		employeeLevels.add(directors);
	}

	/* Gets the first available employee who can handle this call. */
	public Employee getHandlerForCall(Call call) {
		for (int level = call.getRank().getValue(); level < LEVELS - 1; level++) {
			List<Employee> employeeLevel = employeeLevels.get(level);
			for (Employee emp : employeeLevel) {
				if (emp.isFree()) {
					return emp;
				}
			}
		}
		return null;
	}

	/* Routes the call to an available employee, or saves in a queue if no employee available. */
	public void dispatchCall(Caller caller) {
		Call call = new Call(caller);
		dispatchCall(call);
	}

	/* Routes the call to an available employee, or saves in a queue if no employee available. */
	public void dispatchCall(Call call) {
		/* Try to route the call to an employee with minimal rank. */
		Employee emp = getHandlerForCall(call);
		if (emp != null) {
			emp.receiveCall(call);
			call.setHandler(emp);
		} else {
			/* Place the call into corresponding call queue according to its rank. */
			call.reply("Please wait for free employee to reply");
			callQueues.get(call.getRank().getValue()).add(call);
		}
	}

	/* An employee got free. Look for a waiting call that he/she can serve. Return true
	 * if we were able to assign a call, false otherwise. */
	public boolean assignCall(Employee emp) {
		/* Check the queues, starting from the highest rank this employee can serve. */
		for (int rank = emp.getRank().getValue(); rank >= 0; rank--) {
			List<Call> que = callQueues.get(rank);

			/* Remove the first call, if any */
			if (que.size() > 0) {
				Call call = que.remove(0);
				if (call != null) {
					emp.receiveCall(call);
					return true;
				}
			}
		}
		return false;
	}
}

class Caller {
	private String name;
	private int userId;

	public Caller(int id, String nm) {
		name = nm;
		userId = id;
	}
}

class Director extends Employee {
	public Director(CallHandler callHandler) {
		super(callHandler);
		rank = Rank.Director;
	}
}

/* Employee is a super class for the Director, Manager, and Respondent classes. It is implemented as an
 * abstract class, since there should be no reason to instantiated an Employee type directly.
 */
abstract class Employee {
	private Call currentCall = null;
	protected Rank rank;
	private CallHandler callHandler;

	public Employee(CallHandler handler) {
		callHandler = handler;
	}

	/* Start the conversation */
	public void receiveCall(Call call) {
		currentCall = call;
	}

	/* the issue is resolved, finish the call */
	public void callCompleted() {
		if (currentCall != null) {
			/* Disconnect the call. */
			currentCall.disconnect();

			/* Free the employee */
			currentCall = null;
		}

		/* Check if there is a call waiting in queue */
		assignNewCall();
	}

	/*
	 * The issue has not been resolved. Escalate the call, and assign a new call
	 * to the employee.
	 */
	public void escalateAndReassign() {
		if (currentCall != null) {
			/* escalate call */
			currentCall.incrementRank();
			callHandler.dispatchCall(currentCall);

			/* free the employee */
			currentCall = null;
		}

		/* assign a new call */
		assignNewCall();
	}

	/* Assign a new call to an employee, if the employee is free. */
	public boolean assignNewCall() {
		if (!isFree()) {
			return false;
		}
		return callHandler.assignCall(this);
	}

	/* Returns whether or not the employee is free. */
	public boolean isFree() {
		return currentCall == null;
	}

	public Rank getRank() {
		return rank;
	}
}

class Manager extends Employee {
	public Manager(CallHandler callHandler) {
		super(callHandler);
		rank = Rank.Manager;
	}
}

enum Rank {
	Responder(0), Manager(1), Director(2);

	private int value;

	private Rank(int v) {
		value = v;
	}

	public int getValue() {
		return value;
	}
}

class Respondent extends Employee {
	public Respondent(CallHandler callHandler) {
		super(callHandler);
		rank = Rank.Responder;
	}
}
