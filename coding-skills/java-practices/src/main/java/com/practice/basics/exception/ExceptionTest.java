package com.practice.basics.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * The exception is said to be thrown whenever an exceptional event occurs in java which signals that something is not correct
 * with the code written and may give unexpected result. An exceptional event is an occurrence of a condition which alters the 
 * normal program flow. Exceptional handler is the code that does something about the exception.
 * 
 * Throwable is a parent class of all Exception classes. There are two types of Exceptions:
 *   1.Checked exceptions - Checked exception forces to handle the exception using a try-catch block; Eg: IOException
 *   2.UncheckedExceptions or RunTimeExceptions - unchecked exception doesn't allow explicit handle of exception. 
 *                                                Eg: Arithmetic, NullPointer, FileNotFound Exceptions
 * Both types of exception extends Exception class.
 * 
 * Error Vs Exception: 
 * 
 * throw Vs throws:
 * 
 * How do you get the descriptive information about the Exception occurred during the program execution?
 * All the exceptions inherit a method printStackTrace() from the Throwable class. This method prints the stack trace from where
 * the exception occurred. It prints the most recently entered method first and continues down, printing the name of each method 
 * as it works its way down the call stack from the top.
 *   
 */
public class ExceptionTest {

	public static void main(String[] args) {
	}

	// Possible combination to write try, catch finally block
	public void possibleExceptionHandling() {
		/** Option 1: **/
		try {
			// lines of code that may throw an exception
		} catch (Exception e1) {
			// lines of code to handle the exception thrown in try block
		} finally {
			// the clean code which is executed always no matter the exception occurs or not.
			// finally block contains release of connections, closing of result set etc.
		}

		/** Option 2: **/
		try {
		} finally {
			// finally block contains release of connections, closing of result set etc.
		}

		/** Option 3: **/
		try {
		} catch (Exception e2) {
			// lines of code to handle the exception thrown in try block
		}

		/** Option 4: **/
		//
		/* try with resources: This feature add another way to exception handling with resources management. It is also referred to as
		 * automatic resource management. This try statement contains a parenthesis in which one or more resources is declared.
		 * How try-resources works?
		 * Any object that implements java.lang.AutoCloseable or java.io.Closeable, can be passed as a parameter to try statement.
		 * A resource is an object that is used in program and must be closed after the program is finished. The try-with-resources 
		 * statement ensures that each resource is closed at the end of the statement of the try block. You do not have to explicitly
		 * close the resources.
		 * AutoCloseable interface has one method: close(). When we open any such AutoCloseable resource in special try-with-resource block,
		 * immediately after finishing the try block, JVM calls this close() method on all resources initialized in “try()” block.  
		 */
		try (BufferedReader br = new BufferedReader(new FileReader("d:\\myfile.txt"))) {

		} catch (Exception e3) {

		}
	}

	public void tryWithoutResourcesMethod(String[] args) {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("C:/temp/test.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void tryResourcesMethod() {
		try (BufferedReader br = new BufferedReader(new FileReader("d:\\myfile.txt"))) {
			String str;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException ie) {
			System.out.println("exception");
		}
	}

	// To create you own exception extend the Exception class or any of its subclasses.
	public void customException() {

		class CustomException1 extends Exception {
		} // this will create Checked Exception
		class CustomException2 extends IOException {
		} // this will create Checked exception
		class CustomException3 extends RuntimeException {
		} // this will create UnChecked exception
	}

	// ClassNotFoundException and NoClassDefFoundError
	public void classNotFoundExp() {
		/*
		 * A ClassNotFoundException is thrown when the reported class is not found by the ClassLoader in the CLASSPATH. It could also
		 * mean that the class in question is trying to be loaded from another class which was loaded in a parent classloader and hence 
		 * the class from the child classloader is not visible.
		 * 
		 * Consider if NoClassDefFoundError occurs which is something like java.lang.NoClassDefFoundError src/com/TestClass,
		 * does not mean that the TestClass class is not in the CLASSPATH. It means that the class TestClass was found by the ClassLoader
		 * but when trying to load the class, it ran into an error reading the class definition. This typically happens when the class 
		 * in question has static blocks or members which use a Class that's not found by the ClassLoader. So to find the culprit, view 
		 * the source of the class in question (TestClass in this case) and look for code using static blocks or static members.
		*/
	}
}
