package com.practice.concurrency;

/*
 * What is a Thread?
 *   In Java, "thread" means two different things:
 *     1. An instance of class java.lang.Thread. -It is just an object. Like any other object in Java, it has variables and methods,
 *     											  and lives and dies on the heap. 
 *     2. A thread of execution - It is an individual process (a "lightweight" process) that has its own call stack. In Java, 
 *     							  there is one thread per call stack—or, to think of it in reverse, one call stack per thread.
 *     
 * 2 Ways of implementing threads:
 * 1.implementing Runnable 
 * 2.extends Thread
 * 
 * Different states of a thread's lifecycle:
 *   1. New – When a thread is instantiated it is in New state until the start() method is called on the thread instance. 
 *            In this state the thread is not considered to be alive.
 *   2. Runnable – The thread enters into Runnable state after the start method is called in the thread instance. The thread may 
 *   			  enter into the Runnable state from Running state. In this state the thread is considered to be alive.
 *   3. Running – When the thread scheduler picks up the thread from the Runnable thread’s pool, the thread starts running and 
 *                the thread is said to be in Running state.
 *   4. Waiting/Blocked/Sleeping – In these states the thread is said to be alive but not runnable. The thread switches to this 
 *      state because of reasons like wait method called or sleep method has been called on the running thread or thread might be
 *      waiting for some i/o resource so blocked. 
 *   5. Dead – When the thread finishes its execution i.e. the run() method execution completes, it is said to be in dead state.
 *             A dead state can not be started again.
 */
public class MultiThreading extends Thread {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Begining of main method");

		// 1. New: When a thread is instantiated it is in New state until the start() method is called
		MultiThreading t0 = new MultiThreading();
		MultiThreading t1 = new MultiThreading();
		MultiThreading t2 = new MultiThreading();

		// 2. Runnable State: thread enters into this state after the start method is called in the thread instance
		t0.start();
		t1.start();
		t2.start();

		System.out.println("\n\t End of main method");
	}

	// 3. Running State: thread scheduler picks up the thread from the Runnable thread’s pool, the thread starts running
	public void run() {
		threadExec();
		// 5. Dead – When the thread finishes its execution i.e. the run() method execution completes
	}

	public void threadExec() {
		for (int i = 1; i <= 5; i++) {
			try {
				Thread ct = Thread.currentThread();
				System.out.println(ct.getName() + " : From Run Method : " + i);
				// 4.Waiting/Blocked/Sleep: Thread switches to this state when wait(), sleep() has been called on the running thread
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
