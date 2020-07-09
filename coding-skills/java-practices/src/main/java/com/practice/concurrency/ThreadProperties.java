package com.practice.concurrency;

/*
 * This class explains all the Thread properties.
 * 
 * Which thread related methods are available in Thread class?
 *  public static void sleep(long millis) throws Interrupted exception
 *  public static void yield() public final void join() throws Interrupted exception
 *  public final void setPriority(int priority)
 *  public void start()
 *  public void interrupt()
 *  public final void join()
 *  public void run()
 *  public void resume()
 * 
 * Below thread class used to schedule the threads:
 *     public static void sleep(long millis) throwsR InterruptedException
 *     public static void yield()
 *     public final void join() throws InterruptedException
 *     public final void setPriority(int priority)
 *     public final void wait() throws InterruptedException
 *     public final void notify()
 *     public final void notifyAll()
 *     
 * Which thread related methods are available in Object class?
 *   public final void wait() throws Interrupted exception
 *   public final void notify()
 *   public final void notifyAll()
 * 
 * List the methods which when called the thread does not release the locks held?
 *   join()
 *   sleep()
 *   yield()
 *   
 * List the methods which when called on the object the thread releases the locks held on that object?
 *   notify()
 *   notifyAll()
 *   wait()
 */
public class ThreadProperties {

}

/*
 * wait, notify, notifyAll: 
 *  wait() is a method of Object class. wait() allows thread to release the lock and goes to suspended state. The thread is only 
 *  active when a notify() or notifAll() method is called for the same object.
 *  notify() wakes up the first thread that called wait() on the same object. 
 *  notifyAll() wakes up all the threads that called wait() on the same object. The highest priority thread will run first.
 * 
 */
class WaitNotifySample extends Thread {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("\n\t Begining of main method");

		WaitNotifySample ob = new WaitNotifySample();
		Thread t1 = new Thread(ob, "one");
		Thread t2 = new Thread(ob, "two");
		Thread t3 = new Thread(ob, "three");
		Thread t4 = new Thread(ob, "four");
		t1.start();
		t3.start();
		t4.start();
		t2.start();
		System.out.println("\n\t End of main method");
	}

	public void run() {
		Thread ct = Thread.currentThread();
		String t_name = ct.getName();
		System.out.println("\n\t Begining of Thread :  " + t_name);

		if (!t_name.equals("two")) {
			try {
				lock(); // All the Threads are blocked, and wait until thread t2 completes execution
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (t_name.equals("two")) {
			release(); // All the Threads are released, once thread t2 completes execution
		}
		System.out.println("\n\t End of Thread :  " + t_name);
	}

	synchronized void lock() throws InterruptedException {
		wait();
	}

	synchronized void release() {
		notifyAll();
	}
}

/* sleep() allows the thread to go to sleep state for x milliseconds. When a thread goes into sleep state it doesn’t release 
 * the lock.
 * 
 * Yield Vs Sleep:
 *  yield() method pauses the currently executing thread temporarily for giving a chance to the remaining waiting threads of 
 *  the same priority to execute. If there is no waiting thread or all the waiting threads have a lower priority then the same 
 *  thread will continue its execution. The yielded thread when it will get the chance for execution is decided by the thread
 *  scheduler whose behavior is vendor dependent. If doesn't release the lock on the objects acquired.
 *   
 *   sleep() allows the thread to go to sleep state for x milliseconds. When a thread goes into sleep state it doesn’t releases 
 *   the lock.
 */
class SleepSample {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("\n\t Begining of main method");

		WaitNotifySample ob = new WaitNotifySample();
		Thread t1 = new Thread(ob, "one");
		Thread t2 = new Thread(ob, "two");
		t1.start();
		t2.start();
		System.out.println("\n\t End of main method");
	}

	public void run() {
		for (int i = 1; i <= 5; i++) {
			try {
				Thread ct = Thread.currentThread();
				System.out.println(ct.getName() + " : From Run Method : " + i);
				// 4.Waiting/Sleep/Blocked State
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

/*
 * Suspend: Suspend used to block specified thread 
 * Resume: This block will be released by resume() method
 */
class SuspendResumeDemo implements Runnable {

	public void run() {

		String t_name = Thread.currentThread().getName();
		System.out.println("\t  Beginign of " + t_name);
		for (int i = 1; i <= 5; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("\t From Thread : " + t_name + " Count :  " + i);
		}
		System.out.println("\t End of " + t_name);
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("\t Beginign of main method");
		SuspendResumeDemo ob = new SuspendResumeDemo();
		Thread t1 = new Thread(ob, "one");
		Thread t2 = new Thread(ob, "two");
		t1.start();
		t2.start();
		Thread.sleep(100); // Currently executing thread to sleep for the specified milli seconds
		t1.suspend();  // Used to block specified thread & block will be released by resume() method
		Thread.sleep(3000);
		t1.resume();

		System.out.println("\t End of main method");
	}
}

/*
 * Priorities determine, which thread receives CPU ctrl & gets to be executed first.
 * MIN_PRIORITY = 1
 * MAX_PRIORITY = 10
 * NORM_PRIORITY  = 5 
 */
class PriorityDemo extends Thread {
	int	x	= 0;
	int	y	= 0;

	public void run() {
		Thread ct = Thread.currentThread();
		String t_name = ct.getName();

		if (t_name.equals("Ghost")) {
			while (true) {
				x++;
			}
		}
		if (t_name.equals("Rider")) {
			while (true) {
				y++;
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("\n\t Beginign of main method");
		PriorityDemo ob = new PriorityDemo();
		Thread t1 = new Thread(ob, "Ghost");
		Thread t2 = new Thread(ob, "Rider");
		t1.setPriority(8);
		t2.setPriority(2);
		t1.start();
		t2.start();

		Thread.sleep(10);

		t1.stop();
		t2.stop();

		System.out.println("\n\t X value : " + ob.x);
		System.out.println("\n\t Y value : " + ob.y);

		System.out.println("\n\t End of main method");
	}
}

/*
 * synchronized keyword can be applied to static/non-static methods or a block of code. Only one thread at a time can access
 * synchronized methods and if there are multiple threads trying to access the same method then other threads have to wait for 
 * the execution of method by one thread. Synchronized keyword provides a lock on the object and thus prevents race condition. 
 */
class SynchronizedSample {

	/*
	 * When a synch non static method is called a lock is obtained on the object. When a synch static method is called a lock is 
	 * obtained on the class and not on the object.The lock on the object and the lock on the class don’t interfere with each other.
	 * It means, a thread accessing a synch non static method, then the other thread can access the synch static method at the 
	 * same time but can’t access the synch non static method.
	 * 
	 * Can the variables or classes be Synchronized?
	 * No. Only methods and blocks can be synchronized.
	 */
	public synchronized void method() {
		System.out.println("Synchronized non static method!");
	}

	/*
	 * When a synch static method is called a lock is obtained on the class and not on the object.
	 */
	public static synchronized void staticmethod() {
		System.out.println("Synchronized static method!");
	}

	public void myMethod() {
		synchronized (this) {
			// synchronized keyword on block of code
		}
	}
}

/*
 * Daemon thread are service provider threads run in the background,these not used to run the application code generally.When 
 * all user threads(non-daemon threads) complete their execution the jvm exit the application whatever may be the state of the
 * daemon threads. Jvm does not wait for the daemon threads to complete their execution if all user threads have completed their
 * execution.
 * 
 * To create Daemon thread set the daemon value of Thread using setDaemon(boolean value) method. By default all the threads 
 * created by user are user thread. To check whether a thread is a Daemon thread or a user thread use isDaemon() method.
 * 
 * Example of the Daemon thread is the Garbage Collector run by jvm to reclaim the unused memory by the application. The Garbage
 * collector code runs in a Daemon thread which terminates as all the user threads are done with their execution.
*/