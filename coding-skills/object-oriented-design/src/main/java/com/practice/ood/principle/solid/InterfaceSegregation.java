package com.practice.ood.principle.solid;

/*
 * The Interface Segregation Principle (ISP) states that clients should not be forced to depend upon interface members 
 * they do not use. When we have non-cohesive interfaces, the ISP guides us to create multiple, smaller, cohesive interfaces.
 * 
 * When you apply ISP, classes and their dependencies communicate using tightly-focused interfaces, minimizing dependencies
 * on unused members and reducing coupling accordingly. Smaller interfaces are easier to implement, improving flexibility 
 * and the possibility of reuse. As fewer classes share these interfaces, the number of changes that are required in response
 * to an interface modification is lowered, which increases robustness.
 * 
 *  “Don’t depend on things you don’t need”.
 */
public class InterfaceSegregation {

}

/* Imagine the ATM’s owner wants to add a message that appears only for withdrawal functionality, they want to display
 * the message that says “This ATM will charge you some fee for withdrawals, do you agree?” How would you solve it? 
 * 
 * Perhaps you would add a method to the Messenger interface and be done with it. But this causes you to recompile all the
 * users of this interface and almost all the system needs to be redeployed, which is in direct violation of OCP. 
 * Let the code rot begin!*/

interface Messenger {
	public void askForCard();

	public void tellInvalidCard();

	public void askForPin();

	public void tellInvalidPin();

	public void tellCardWasSiezed();

	public void askForAccount();

	public void tellNotEnoughMoneyInAccount();

	public void tellAmountDeposited();

	public void tellBalance();
}

/*
 * Instead, split the Messenger interface up so that different ATM functionality depend on separate Messengers.
 */
interface LoginMessenger {
	public void askForCard();

	public void tellInvalidCard();

	public void askForPin();

	public void tellInvalidPin();
}

interface WithdrawalMessenger {
	public void tellNotEnoughMoneyInAccount();

	public void askForFeeConfirmation();
}

class EnglishMessenger implements LoginMessenger, WithdrawalMessenger {

	@Override
	public void tellNotEnoughMoneyInAccount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void askForFeeConfirmation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void askForCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tellInvalidCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void askForPin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tellInvalidPin() {
		// TODO Auto-generated method stub

	}

}