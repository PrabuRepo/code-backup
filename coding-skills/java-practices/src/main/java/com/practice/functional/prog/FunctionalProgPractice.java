package com.practice.functional.prog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import com.practice.functional.prog.model.Condition;
import com.practice.functional.prog.model.MathOperation;
import com.practice.functional.prog.model.MathOperationImpl;
import com.practice.functional.prog.model.Person;

/*
 * Functional programming contains the following key concepts:
 * 	- Functions as first class objects
 * 	- Pure functions 
 *  - Higher order functions 
 * 
 * Pure functional programming has a set of rules to follow too:
 * 	- No state 
 * 	- No side effects 
 * 	- Immutable variables 
 * 	- Favor recursion over looping
 * 
 * Functional Interface:  A functional interface in Java is an interface that contains only a single abstract (unimplemented) method. 
 * A functional interface can contain default and static methods which do have an implementation, in addition to the single unimplemented method.
 * A Java functional interface can be implemented by a Java Lambda Expression.
 * 
 * Java lambda expressions are Java's first step into functional programming. A Java lambda expression is thus a function which can be created 
 * without belonging to any class. A Java lambda expression can be passed around as if it was an object and executed on demand.
 * Java lambda expressions are commonly used to implement simple event listeners / callbacks, or in functional programming with the Java Streams API.
 */
public class FunctionalProgPractice {

	public static void main(String[] args) {
		FunctionalProgPractice ob = new FunctionalProgPractice();

		ob.implementFunctionalInterface1();
		ob.implementFunctionalInterface2();
		ob.implementFunctionalInterface3();

		// Type Inference: Return Type of method refers from Functional Interface.
		ob.typeInference();

		// Lambda Parameters: It can be zero, one or many
		ob.lambdaDiffParamSamples();

		// Class Vs Anonymous Class Impl Vs Lambda Expression
		ob.lammbdaAndAnonymousImplComparisons();

		ob.lambdaExprForCollection();

		ob.builtInFunctionalInterfaces();

		ob.methodReferences();

		ob.comparatorSample();
	}

	// A Java functional interface can be implemented by a Java Lambda Expression.
	public void implementFunctionalInterface1() {
		System.out.println("\n\n************Implement Functional Interfaces - 1**********");
		MyFunctionalInterface lambda = () -> System.out.println("Executing...");
		lambda.execute();
	}

	/* Same Functional Interface can be implemented with different functionalities and trying with different optional syntax */
	public void implementFunctionalInterface2() {
		System.out.println("\n\n************Implement Functional Interfaces - 2**********");
		// Functional Interface Implementations:
		MathOperation addition = (int a, int b) -> a + b;
		// without type declaration - Type is optional in lambda expression
		MathOperation subtraction = (a, b) -> a - b;
		// Curly braces are mandatory when you use the return statement
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};
		MathOperation division = (a, b) -> a / b;

		// Method Calls:
		System.out.println("10 + 5 = " + addition.operation(10, 5));
		System.out.println("10 - 5 = " + subtraction.operation(10, 5));
		System.out.println("10 x 5 = " + multiplication.operation(10, 5));
		System.out.println("10 / 5 = " + division.operation(10, 5));
	}

	// Runnable Interface Implementation
	public void implementFunctionalInterface3() {
		System.out.println("\n\n************Implement Functional Interfaces - 3**********");

		// 1. Using Anonymous Inner Class
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Runnable inside anonymous type");

			}
		});
		thread1.run();

		// 2. Using Lambda Expression
		Thread thread2 = new Thread(() -> System.out.println("Runnable inside Lambda expressions"));
		thread2.run();

	}

	// Type Inference: Return Type of method refers from Functional Interface.
	public void typeInference() {
		MathOperation addition = (a, b) -> a + b;
		// Data Type of a, b can be referred from MathOperation functional interface
		System.out.println("10 + 5 = " + addition.operation(10, 5));
	}

	// Lambda Parameters: It can be zero, one or many
	public void lambdaDiffParamSamples() {
		System.out.println("\n\n************Lambda Parameters: zero, one or many**********");
		// Zero Params
		Print ob = () -> System.out.println("Print Something");
		ob.print();

		// One Params
		Sayable ob2 = name -> "Hello " + name;
		System.out.println(ob2.say("Prabu"));

		// Two Params
		Addable ob3 = (a, b) -> a + b;
		System.out.println("Add: " + ob3.add(5, 8));
	}

	// Class Vs Anonymous Class Vs Lambda Expression
	// MathOperation is an functional interface with single abstract methods and can be implemented in 3 ways:
	public void lammbdaAndAnonymousImplComparisons() {
		System.out.println("\n\n************Class Vs Anonymous Class Vs Lambda Expression**********");
		// way 1: Function defined in the MathOperationImpl class, which implements MathOperation Interface
		MathOperationImpl mathOperationImpl1 = new MathOperationImpl();
		System.out.println("Using class implementation of interface:" + mathOperationImpl1.operation(10, 15));

		/* 
		 * way 2: Function defined in the Anonymous implementation of MathOperation interface;
		 * Even though lambda expressions are close to anonymous interface implementations, there are a few differences that are worth noting.
		 * 	The major difference is, that an anonymous interface implementation can have state (member variables) whereas a lambda expression cannot. 
		 */
		MathOperation mathOperation1 = new MathOperation() {
			private int count = 0; // Member variable

			@Override
			public int operation(int a, int b) {
				count++;
				return a + b;
			}
		};
		System.out.println("Using anonymous innertype:" + mathOperation1.operation(10, 25));

		// way 3: Lambda expressions- Function defined in inline; It works only with Functional Interface;
		// A lambda expression cannot have any state fields. A lambda expression is thus said to be stateless.
		MathOperation mathOperation2 = (a, b) -> a + b;
		System.out.println("Using Lamda Expressions:" + mathOperation2.operation(10, 35));
	}

	public void lambdaExprForCollection() {
		System.out.println("\n\n**************Useful Lambda Expressions for Collection**************");
		List<String> list = Arrays.asList("Amitabh", "Shekhar", "Aman", "Rahul", "Shahrukh", "Salman", "Yana", "Lokesh");
		System.out.println("ForEach: ");
		list.forEach(i -> System.out.print(i + " "));

		List<Integer> linkedList = Arrays.asList(1, 3, 5, 7, 10);
		linkedList.forEach(i -> System.out.print(i + " "));

		Map<Integer, String> map = new HashMap<>();
		map.put(1, "abc");
		map.put(2, "xyz");
		// Using direct forEach
		map.forEach((k, v) -> System.out.println("Key: " + k + "; Val: " + v));
		// Using Keyset
		map.entrySet().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));

		list.sort((a, b) -> a.length() - b.length());
		list.forEach(i -> System.out.print(i + " "));

		list.sort(Comparator.comparing(n -> n.length())); // same
		list.forEach(i -> System.out.print(i + " "));

		list.sort(Comparator.comparing(String::length)); // same
		list.forEach(i -> System.out.print(i + " "));

		List<Person> persons = new ArrayList<>();
		Collections.sort(persons, (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()));

		List<Integer> intList = Arrays.asList(23, 7, 17, 3, 13);
		Collections.sort(intList);
		Collections.sort(intList, (a, b) -> b - a);
		Collections.sort(intList, Comparator.reverseOrder());

		// Map sample with lambda expressions:
		Map<String, Integer> items = new HashMap<>();
		items.put("A", 10);
		items.put("B", 20);
		items.put("C", 30);
		items.put("D", 40);
		items.put("E", 50);
		items.put("F", 60);

		items.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));

		items.forEach((k, v) -> {
			System.out.println("Item : " + k + " Count : " + v);
			if ("E".equals(k))
				System.out.println("Hello E");
		});

		// Add a new key-value pair only if the key does not exist in the HashMap
		items.putIfAbsent("G", 70);
		items.putIfAbsent("B", 20);

		System.out.println(items);

		// Add more Map samples from below link:
		// https://mkyong.com/java8/java-8-filter-a-map-examples/
	}

	// Collection Operations before and after Java8 Implementations
	public void comparatorSample() {
		List<Person> persons = Arrays.asList(new Person("Charles", "Dicken", 60), new Person("Niharika", "Kothari", 25),
				new Person("Havilah", "Thathaputi", 24), new Person("Alekya", "Saladi", 24),
				new Person("Muthu", "Sakthivel", 27));

		/** Before Java 8: */
		System.out.println("Without Lambda Expressions:");
		// Sort list by last name
		Collections.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getFirstName().compareTo(p2.getFirstName());
			}
		});
		// Create a method that prints all elements in the list
		System.out.println("Print all the data:");
		printAll(persons);
		// Create a method print all the last names start with 'T'
		System.out.println("Print data based on char:");
		printConditionally(persons, "H", new Condition() {
			@Override
			public boolean test(Person p, String letter) {
				return p.getFirstName().startsWith(letter);
			}
		});

		/** Java 8: */
		System.out.println("With Lambda Expressions:");
		// Sort list by last name
		Collections.sort(persons, (p1, p2) -> p1.getFirstName().compareTo(p2.getLastName()));
		// Create a method that prints all elements in the list
		System.out.println("Print all the data:");
		printAll(persons);
		// Create a method print all the last names start with 'T'
		System.out.println("Print data based on char:");
		printConditionally(persons, "H", (p, letter) -> p.getFirstName().startsWith(letter));

	}

	// Overall High Level Functional Interfaces: Predicate, Consumer, Supplier, Function, Operator, Runnable, Comparator
	public void builtInFunctionalInterfaces() {
		System.out.println("\n\n************Built In Functional Interfaces**********");
		List<String> names = Arrays.asList("bob", "josh", "megan");

		Predicate<String> predicate = s -> s.length() < 5;
		System.out.println(predicate.test("Predicate Test"));
		// filter uses predicate functional interface
		List<String> namesWithM = names.stream().filter(name -> name.startsWith("m")).collect(Collectors.toList());

		Consumer<Integer> consumer = a -> System.out.println("Print: " + (a + 10));
		consumer.accept(15);
		// ForEach uses consumer functional interface
		names.forEach(name -> System.out.println("Hello, " + name));

		Supplier<String> supplier = () -> "Print Supplier";
		System.out.println(supplier.get());

		Function<String, Integer> function = (s) -> s.length();
		System.out.println("String Length: " + function.apply("Function Test"));

		BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
		System.out.println("Addition: " + binaryOperator.apply(8, 9));

		UnaryOperator<String> unaryOperator = s -> s.toUpperCase();
		// ReplaceAll uses unary operator functional interface
		names.replaceAll(unaryOperator); // or names.replaceAll(name -> name.toUpperCase());
		names.forEach(i -> System.out.print(i + " "));

		Runnable runnable = () -> System.out.println("Execute Run Method");
		runnable.run();

		Comparator<Integer> comparator = (a, b) -> b - a;
		List<Integer> list = Arrays.asList(5, 3, 7, 2, 4);
		Collections.sort(list, comparator);
		list.forEach(i -> System.out.print(i + " "));
	}

	/* Method References:
	 *   Static method
	 *   Instance method on parameter objects
	 *   Instance method
	 *   Constructor
	 */
	public void methodReferences() {
		// Write sample for below

		// Static method
		// Instance method on parameter objects
		// Instance method
		// Constructor
	}

	private void printAll(List<Person> persons) {
		for (Person person : persons)
			System.out.println(person);
	}

	private void printConditionally(List<Person> persons, String letter, Condition condition) {
		for (Person person : persons) {
			if (condition.test(person, letter)) {
				System.out.println(person);
			}
		}
	}

	private void reverse(int[] arr) {
		int last = arr.length - 1;
		int middle = arr.length / 2;
		for (int i = 0; i <= middle; i++) {
			int temp = arr[i];
			arr[i] = arr[last - i];
			arr[last - i] = temp;
		}
	}

}

/* Functional Interface:  A functional interface in Java is an interface that contains only a single abstract (unimplemented) method. 
 * A functional interface can contain default and static methods which do have an implementation, in addition to the single unimplemented method. 
 */
interface MyFunctionalInterface {
	public void execute();

	public default void print(String text) {
		System.out.println(text);
	}

	public static void print(String text, PrintWriter writer) throws IOException {
		writer.write(text);
	}
}

interface Print {
	public void print();
}

interface Sayable {
	public String say(String name);
}

interface Addable {
	public int add(int a, int b);
}
