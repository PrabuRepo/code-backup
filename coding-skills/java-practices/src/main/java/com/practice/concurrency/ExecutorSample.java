package com.practice.concurrency;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * What is Executor Framework?
 *  In simple Java applications, we do not face much challenge while working with a small number of threads. If you have to develop a program that
 *  runs a lot of concurrent tasks, this approach will present many disadvantages such as lots of boiler plate code (create and manage threads), 
 *  executing thread manually and keeping track of thread execution results.
 *  Executor framework (since Java 1.5) solved this problem. The framework consist of three main interfaces (and lots of child interfaces) 
 *  i.e. Executor, ExecutorService and ThreadPoolExecutor.
 *  
 * ExecutorService is an interface and it’s implementations can execute a Runnable or Callable class in an asynchronous way. 
 * Note that invoking the run() method of a Runnable interface in a synchronous way is simply calling a method.
 * 
 * Executors is a utility class that provides factory methods for creating the implementations of the interface.
 *   1.Executes only one thread -> ExecutorService es = Executors.newSingleThreadExecutor();
 *   2.Internally manages thread pool of 2 threads -> ExecutorService es = Executors.newFixedThreadPool(2);
 *   3.Internally manages thread pool of 10 threads to run scheduled tasks -? ExecutorService es = Executors.newScheduledThreadPool(10); 
 */
public class ExecutorSample {

	public static void main(String[] args) {
		ExecutorSample ob = new ExecutorSample();
		ob.singleThreadExecutor();

		// ob.threadPoolExecutor();

		// ob.scheduledExecutor();
	}

	// Threads executed by newSingleThreadExecutor. All the tasks run in the single thread
	public void singleThreadExecutor() {
		System.out.println("Single Thread Executor: \n");

		// Sample Runnable Tasks:
		Runnable runnableTask1 = () -> {
			System.out.println("Task1: " + Thread.currentThread().getName());
		};
		Runnable runnableTask2 = () -> {
			System.out.println("Task2: " + Thread.currentThread().getName());
		};

		// Sample Callable Tasks:
		Callable<String> callableTask = () -> {
			TimeUnit.MILLISECONDS.sleep(1000);
			return "Current time :: " + LocalDateTime.now();
		};
		try {

			// Executor service instance
			ExecutorService service = Executors.newSingleThreadExecutor();

			// 1. execute runnable task using execute() method
			service.execute(runnableTask1);

			// 2. execute runnable task using submit() method
			Future<String> future = service.submit(runnableTask2, "DONE");
			System.out.println("Task2: " + future.get());

			// 3. execute callable task using submit() method
			Future<String> future2 = service.submit(callableTask);
			System.out.println("Callable Task: " + future2.get());

			/* shutdown: Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted. */
			service.shutdown();
			/* shutdownNow: Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution. */
			// service.shutdownNow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void threadPoolExecutor() {
		System.out.println("ThreadPool Executor: ");
		ExecutorService service = Executors.newFixedThreadPool(4);

		Runnable task1 = () -> {
			System.out.println("Task1: " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Task1: " + Thread.currentThread().getName() + " - End");
		};

		Runnable task2 = () -> {
			System.out.println("Task2: " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Task2: " + Thread.currentThread().getName() + " - End");
		};

		service.submit(task1);
		service.submit(task2);

		service.shutdown();
	}

	public void scheduledExecutor() {
		System.out.println("Scheduled Executor: ");
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

		Runnable task1 = () -> {
			System.out.println("Task1: " + Thread.currentThread().getName());
			System.out.println("Task1: " + Thread.currentThread().getName() + " - End");
		};

		Runnable task2 = () -> {
			System.out.println("Task2: " + Thread.currentThread().getName());
			System.out.println("Task2: " + Thread.currentThread().getName() + " - End");
		};

		service.schedule(task1, 5, TimeUnit.SECONDS);
		service.shutdown();

		ScheduledExecutorService service2 = Executors.newScheduledThreadPool(5);
		service2.scheduleAtFixedRate(task2, 4, 5, TimeUnit.SECONDS);
		// Should not shutdown for fixed rate executor

	}
}