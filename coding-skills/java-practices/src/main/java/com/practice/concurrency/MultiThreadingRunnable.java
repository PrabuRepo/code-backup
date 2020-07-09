package com.practice.concurrency;

/*
 * Creating thread by implementing the Runnable interface
 */
public class MultiThreadingRunnable implements Runnable {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("\n\t Begining of main method");
		MultiThreadingRunnable ob = new MultiThreadingRunnable();
		// 1.New State
		Thread t1 = new Thread(ob, "Sekar"); // Creates the Thread object
		Thread t2 = new Thread(ob, "Prithivi");
		Thread t3 = new Thread(ob, "Bala");

		// 2.Runnable State
		t1.start();  // start() method invokes the run() method of the thread.
		t2.start();
		t3.start();

		System.out.println("\n\t Before Join\n t1.isAlive() " + t1.isAlive());
		System.out.println("\n\t t2.isAlive() " + t2.isAlive());
		System.out.println("\n\t t3.isAlive() " + t3.isAlive()); // isAlive(): Test if the thread is alive (returns threads state either T/F)

		t1.join();
		t2.join(); // Used to ensure that the application thread executes, after all the thread(t1,t2&t3) completes execution.
		t3.join();

		System.out.println("\n\t After Join\n t1.isAlive() " + t1.isAlive());
		System.out.println("\n\t t2.isAlive() " + t2.isAlive());
		System.out.println("\n\t t3.isAlive() " + t3.isAlive());

		for (int i = 1; i <= 5; i++) {
			System.out.println("\n\t From Main Method : " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n\t End of main method");
	}

	// 3.Running State
	public void run() { // run() method is called, when start() is executed
		synchronized (this) {
			/*synchronized():
			 *  Used to achieve that only one thread can access the method/shared data at a time.
			 *  threads have to wait until the first thread completes the execution.*/
			threadExec();
		}
		// 5.Dead State
	}

	public void threadExec() {
		for (int i = 1; i <= 5; i++) {
			try {
				Thread ct = Thread.currentThread();
				System.out.println(ct.getName() + " : From Run Method : " + i);
				// 4.Waiting/Sleep/Blocked State
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
