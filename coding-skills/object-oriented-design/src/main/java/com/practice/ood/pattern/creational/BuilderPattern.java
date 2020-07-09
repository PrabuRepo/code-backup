package com.practice.ood.pattern.creational;

/*
 * There are two specific problems that we need to solve in Classes:
 *   Too many constructor arguments.
 *   Incorrect object state.
 * This is where the Builder pattern comes into play.
 * 
 * Builder Pattern:
 * 	The Builder pattern allows us to write readable, understandable code to set up complex objects. It is often implemented with
 *  a fluent interface, which you may have seen in tools like Apache Camel or Hamcrest. The builder will contain all of the fields 
 *  that exist on the BankAccount class itself. We will configure all of the fields that we want on the builder, and then we'll use
 *  the builder to create accounts. At the same time, we'll remove the public constructor from the BankAccount class and replace it
 *  with a private constructor so that accounts can only be created via the builder.
 *  
 * Fluent Interface:
 * In software engineering, a fluent interface is a method for designing object-oriented APIs that relies extensively on method chaining.
 * Its goal is to increase code legibility by creating a domain-specific language. 
 * A fluent interface is normally implemented by using method chaining to implement method cascading, concretely by having each method 
 * return this (self). Stated more abstractly, a fluent interface relays the instruction context of a subsequent call in method chaining, 
 * where generally the context is
 * 		Defined through the return value of a called method
 * 		Self-referential, where the new context is equivalent to the last context
 * 		Terminated through the return of a void context
 */
public class BuilderPattern {
	public static void main(String[] args) {

		BankAccount account = new BankAccount.Builder().accountNumber(1234L).withOwner("Marge").atBranch("Springfield")
				.openingBalance(100).atRate(2.5).build();
		System.out.println(account.toString());

		BankAccount anotherAccount = new BankAccount.Builder().accountNumber(4567L).withOwner("Homer")
				.atBranch("Springfield").atRate(2.5).build();
		System.out.println(anotherAccount.toString());
	}

}

class BankAccount {
	private long accountNumber;
	private String owner;
	private String branch;
	private double balance;
	private double interestRate;

	// Constructor should be private.
	private BankAccount() {
	}

	// TODO: Is this required??
	private BankAccount(long accountNumber, String owner, String branch, double balance, double interestRate) {
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.branch = branch;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public static class Builder {
		private long accountNumber; // This is important, so we'll pass it to the constructor.
		private String owner;
		private String branch;
		private double balance;
		private double interestRate;

		public Builder() {
		}

		public Builder accountNumber(long accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Builder withOwner(String owner) {
			this.owner = owner;
			return this; // By returning the builder each time, we can create a fluent interface.
		}

		public Builder atBranch(String branch) {
			this.branch = branch;
			return this;
		}

		public Builder openingBalance(double balance) {
			this.balance = balance;
			return this;
		}

		public Builder atRate(double interestRate) {
			this.interestRate = interestRate;
			return this;
		}

		public BankAccount build() {
			// Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
			BankAccount account = new BankAccount(); // Since the builder is in the BankAccount class, we can invoke its
																								// private constructor.
			account.accountNumber = this.accountNumber;
			account.owner = this.owner;
			account.branch = this.branch;
			account.balance = this.balance;
			account.interestRate = this.interestRate;
			return account;
		}
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	public String getBranch() {
		return branch;
	}

	public double getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	@Override
	public String toString() {
		return "Accnt No: " + this.accountNumber + "; Owner: " + this.owner + "; Branch: " + this.branch + "; Opening Bal: "
				+ this.balance + "; Interest Rate:  " + this.interestRate;
	}

}