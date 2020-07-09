package com.practice.concurrency;

/*
 * This keyword is used to prevent concurrency. Synchronized keyword can be applied to static/non-static methods or a block of code.
 * Only one thread at a time can access synchronized methods and if there are multiple threads trying to access the same method
 * then other threads have to wait for the execution of method by one thread. Synchronized keyword provides a lock on the object 
 * and thus prevents race condition.
 */
public class SynchronizedProperties {
	/*public void synchronized simpleMethod() {}
	
	public void synchronized staticMethod() {}
	
	public void myMethod() {
		synchronized (this) { // synchronized keyword on block of code
		}
	}*/
}
