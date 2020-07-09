package com.basic.datastructures.java.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamAPI {

	public static void main(String[] args) {
		StreamAPI ob = new StreamAPI();

		ob.sorting();

		ob.conversions();

		ob.basicMathOperations();

		ob.others();
	}

	public void sorting() {
		List<String> names = new ArrayList<>();
		// Sort - String, Array, List, Map, Custom Objects
		/* Sorted: Sorted takes a Comparator, and orders the values in the stream according to the ordering specified in the Comparator. */
		System.out.println("Sorted Sample: ");
		Stream<String> sortedStream = names.stream().map(s -> s.toLowerCase()).sorted();
		names.stream().sorted().map(String::toUpperCase).forEach(System.out::println);
		names.stream().sorted((s1, s2) -> s2.compareToIgnoreCase(s1)).map(String::toUpperCase).forEach(System.out::println);
	}

	public void conversions() {
		List<String> names = Arrays.asList("ad", "Amitabh", "AMAN", "Rahul", "Shahrukh", "AMAN", "Rahul", "al");
		/** Primitive Array to List Conversion: use Boxed */
		// Example to convert int stream to List of Integer.
		List<Integer> ints = IntStream.of(1, 2, 3, 4, 5).boxed().collect(Collectors.toList());
		// Example to convert long stream to List of Long.
		List<Long> longs = LongStream.of(1l, 2l, 3l, 4l, 5l).boxed().collect(Collectors.toList());
		// Example to convert double stream to List of Doubles.
		List<Double> doubles = DoubleStream.of(1d, 2d, 3d, 4d, 5d).boxed().collect(Collectors.toList());

		/** List to Primitive Array Conversion: */
		// List to Primitive array:
		int[] arr3 = ints.stream().mapToInt(i -> i.intValue()).toArray();
		arr3 = ints.stream().mapToInt(Integer::intValue).toArray();

		/**  List to Object array:*/
		Integer[] arr4 = ints.toArray(new Integer[ints.size()]);
		Integer[] arr5 = ints.stream().toArray(Integer[]::new);
		System.out.println("List to Object array: " + Arrays.toString(arr5));

		// List to Primitive conversion & filter out null values:
		int[] primitive = ints.stream().mapToInt(i -> (i == null ? 0 : i)).toArray();
		// Same using method reference:
		primitive = ints.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
		primitive = ints.stream().map(i -> (i == null ? 0 : i)).mapToInt(Integer::intValue).toArray();

		// String List to Array conversions:
		String[] strArr = names.toArray(new String[names.size()]);
		strArr = names.stream().toArray(String[]::new);

		// List to Set conversion:
		List<Integer> numbers = Arrays.asList(12, 84, 5, 64, 28, 8);
		Set<String> stringSet = names.stream().collect(Collectors.toSet());
		Set<Integer> numberSet = numbers.stream().collect(Collectors.toSet());
	}

	// Basic Math logic
	public void basicMathOperations() {
		List<String> names = Arrays.asList("ad", "Amitabh", "AMAN", "Rahul", "Shahrukh", "AMAN", "Rahul", "al");
		List<Integer> numbers = Arrays.asList(12, 84, 5, 64, 28, 8);
		// Sum:
		int result = numbers.stream().reduce(0, (sum, i) -> sum + i);
		result = IntStream.range(1, 10).sum();
		// Count:
		long count = names.stream().filter(s -> s.length() > 4).count();

		String concatnate = names.stream().reduce("", (acc, el) -> acc + "|" + el);
		String joined = names.stream().collect(Collectors.joining(", "));

		// Matches
		boolean flag = names.stream().anyMatch(s -> s.startsWith("R"));
		flag = names.stream().allMatch(s -> s.startsWith("R"));
		flag = names.stream().noneMatch(s -> s.contains("Z"));

		// Find
		Optional<String> anyElement = names.stream().findAny();
		Optional<String> findFirst = names.stream().findFirst();

		// Min & Max:
		Optional<Integer> val = numbers.stream().min((a, b) -> a.compareTo(b));
		val = numbers.stream().max((a, b) -> a.compareTo(b));
	}

	public void others() {
		List<String> names = new ArrayList<>();
		// Filter: Filter takes a Predicate, and leaves only the values that the Predicate is true for.
		Stream<String> filteredStream = names.stream().filter(s -> s.length() <= 3);

		// Map: Map takes a Function, and transforms the values in the stream from one type to another
		Stream<String> streamMapped = names.stream().map(s -> s.toLowerCase());
	}
}
