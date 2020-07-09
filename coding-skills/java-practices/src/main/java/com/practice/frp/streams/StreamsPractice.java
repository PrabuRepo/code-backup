package com.practice.frp.streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
 * Stream Processing: You can attach listeners to a Stream. These listeners are called when the Stream iterates the
 * elements internally. The listeners are called once for each element in the stream. That way each listener gets to
 * process each element in the stream. This is referred to as stream processing.
 * 
 * The listeners of a stream form a chain. The first listener in the chain can process the element in the stream, and
 * then return a new element for the next listener in the chain to process. A listener can either return the same
 * element or a new, depending on what the purpose of that listener (processor) is.
 * 
 * Streams: Stream API is used to process collections of objects. A stream is a sequence of objects that supports
 * various methods which can be pipelined to produce the desired result.
 * 
 * A stream pipeline consists of a source (such as a Collection, an array, a generator function, or an I/O channel);
 * followed by zero or more intermediate operations such as Stream.filter or Stream.map; and a terminal operation such
 * as Stream.forEach or Stream.reduce.
 *
 * Intermediate operations return a new stream. They are always lazy; executing an intermediate operation such as
 * filter() does not actually perform any filtering, but instead creates a new stream that, when traversed, contains the
 * elements of the initial stream that match the given predicate. Traversal of the pipeline source does not begin until
 * the terminal operation of the pipeline is executed.
 * 
 * Terminal operations, such as Stream.forEach or IntStream.sum, may traverse the stream to produce a result or
 * aside-effect. After the terminal operation is performed, the stream pipelineis considered consumed, and can no longer
 * be used; if you need to traverse the same data source again, you must return to the data source to get a newstream.
 * 
 */

public class StreamsPractice {

	public static void main(String[] args) {
		StreamsPractice ob = new StreamsPractice();

		ob.obtainingStreams();

		ob.intermediateOperations();

		ob.terminalOperations();

		ob.streamCombinations();

		ob.streamComparisons();

	}

	// Obtaining a Stream
	/*
	 * Streams can be obtained in a number of ways. Some examples include: 
	 * 	•From a Collection via the stream() and parallelStream() methods;
	 * 	•From an array via Arrays.stream(Object[]);
	 * 	•From static factory methods on the stream classes, such as Stream.of(Object[]), IntStream.range(int, int) or Stream.iterate(Object, UnaryOperator);
	 * 	•The lines of a file can be obtained from BufferedReader.lines();
	 * 	•Streams of file paths can be obtained from methods in Files;
	 * 	•Streams of random numbers can be obtained from Random.ints();
	 *  •Numerous other stream-bearing methods in the JDK, including BitSet.stream(), Pattern.splitAsStream(java.lang.CharSequence),and JarFile.stream().
	 */

	public void obtainingStreams() {
		Stream<String> strStream = null;
		Stream<Integer> integerStream = null;
		IntStream intStream = null;

		// 1.From a Collection via the stream() and parallelStream() methods
		List<String> names = new ArrayList<>(
				Arrays.asList(new String[] { "Amitabh", "Shekhar", "Aman", "Rahul", "Shahrukh", "Salman", "Yana", "Lokesh" }));
		strStream = names.stream();
		strStream = names.parallelStream();

		// 2.From an array via Arrays.stream(Object[])
		String[] arr = { "a", "b", "c" };
		strStream = Arrays.stream(arr);

		// 3.From static factory methods on the stream classes, such as Stream.of(Object[]), IntStream.range(int, int) or
		// Stream.iterate(Object, UnaryOperator)
		strStream = Stream.of("a", "b", "c");
		String[] strArr = { "a", "b", "c" };
		strStream = Stream.of(strArr);

		intStream = IntStream.range(1, 10);
		int[] ints = { 1, 2, 3 };
		intStream = Arrays.stream(ints);

		integerStream = Stream.of(1, 2, 3);

		// Infinite stream
		integerStream = Stream.iterate(0, n -> n + 1);

		// Streams of file paths can be obtained from methods in Files
		try {
			Stream<String> rows = Files.lines(Paths.get("data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Intermediate Operations
	public void intermediateOperations() {
		System.out.println("\n\n**************Stream: Intermediate Operations**************");
		List<String> names = new ArrayList<>(
				Arrays.asList(new String[] { "Amitabh", "ad", "AMAN", "Rahul", "Shahrukh", "AMAN", "Rahul", "al" }));

		// Filter: Filter takes a Predicate, and leaves only the values that the Predicate is true for.
		System.out.println("Filter Sample: ");
		Stream<String> filteredStream = names.stream().filter(s -> s.length() <= 3);
		filteredStream.forEach(i -> System.out.print(i + " "));

		// Map: Map takes a Function, and transforms the values in the stream from one type to another
		System.out.println("\nMap Sample: ");
		Stream<String> streamMapped = names.stream().map(s -> s.toLowerCase());
		streamMapped.forEach(i -> System.out.print(i + " "));

		/* FlatMap: The Java Stream flatMap() methods maps a single element into multiple elements. The idea is that you "flatten" each element 
		 * from a complex structure consisting of multiple internal elements, to a "flat" stream consisting only of these internal elements.
		 */
		System.out.println();
		List<String> stringList = new ArrayList<String>();
		stringList.add("One flew over the cuckoo's nest");
		stringList.add("To kill a muckingbird");
		stringList.add("Gone with the wind");

		Stream<String> stream = stringList.stream();
		stream.flatMap(s -> Arrays.asList(s.split(" ")).stream()).forEach((i) -> System.out.println(i));

		/* Sorted: Sorted takes a Comparator, and orders the values in the stream according to the ordering specified in the Comparator. */
		System.out.println("Sorted Sample: ");
		Stream<String> sortedStream = names.stream().map(s -> s.toLowerCase()).sorted();
		sortedStream.forEach(i -> System.out.print(i + " "));

		names.stream().sorted().map(String::toUpperCase).forEach(System.out::println);
		names.stream().sorted((s1, s2) -> s2.compareToIgnoreCase(s1)).map(String::toUpperCase).forEach(System.out::println);

		System.out.println("\nSorted Sample(Desc): ");
		sortedStream = names.stream().map(s -> s.toLowerCase()).sorted((a, b) -> b.compareTo(a));
		sortedStream.forEach(i -> System.out.print(i + " "));

		/* distinct():It returns a new Stream which will only contain the distinct elements from the original stream. Any duplicates will be eliminated.*/
		System.out.println("\nDistinct Sample: ");
		Stream<String> distinctStream = names.stream().distinct();
		distinctStream.forEach(i -> System.out.print(i + " "));

		/* Limit: limit the number of elements in a stream to a number given to the limit() method as parameter. The limit() method returns 
		 * a new Stream which will at most contain the given number of elements. 
		 */
		System.out.println("\nLimit Sample: ");
		Stream<String> limitStream = names.stream().limit(4);
		limitStream.forEach(i -> System.out.print(i + " "));

		/* Skip: Discarding the first n elements
		 */
		System.out.println("\nSkip Sample: ");
		Stream<String> skipStream = names.stream().skip(3);
		skipStream.forEach(i -> System.out.print(i + " "));
		IntStream.range(1, 10).skip(5).forEach(i -> System.out.print(i + " "));

		/* Peek: It takes a Consumer (java.util.function.Consumer) as parameter. The Consumer will get called for each element in the stream. 
		 * The peek() method returns a new Stream which contains all the elements in the original stream. The purpose of the peek() method is, 
		 * as the method says, to peek at the elements in the stream, not to transform them.
		 */
		System.out.println("\nPeek Sample: ");
		Stream<String> peekStream = names.stream().peek(i -> System.out.print("Name: "));
		peekStream.forEach(i -> System.out.print(i + " "));

		/* Boxed: If you want to convert stream of objects to collection, then you can use one of the static methods in the Collectors class.
		 * The same process doesn’t work on streams of primitives, however. To convert a stream of primitives, you must first box the elements 
		 * in their wrapper class and then collect them. This type of stream in called boxed stream. 
		 */
		System.out.println("\nBoxed Stream Sample: ");
		// It works perfect for Objects !!
		List<Integer> strings = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
		// Compilation Error !!
		// IntStream.of(1, 2, 3, 4, 5).collect(Collectors.toList());

		// Example to convert int stream to List of Integer.
		List<Integer> ints = IntStream.of(1, 2, 3, 4, 5).boxed().collect(Collectors.toList());
		System.out.println(ints);
		// Stream operations directly
		Optional<Integer> max = IntStream.of(1, 2, 3, 4, 5).boxed().max(Integer::compareTo);
		System.out.println(max);

		// Example to convert long stream to List of Long.
		List<Long> longs = LongStream.of(1l, 2l, 3l, 4l, 5l).boxed().collect(Collectors.toList());

		// Example to convert double stream to List of Doubles.
		List<Double> doubles = DoubleStream.of(1d, 2d, 3d, 4d, 5d).boxed().collect(Collectors.toList());
	}

	// Terminal Operations
	public void terminalOperations() {
		System.out.println("\n\n**************Stream: Terminal Operations**************");
		List<String> names = Arrays.asList("ad", "Amitabh", "AMAN", "Rahul", "Shahrukh", "AMAN", "Rahul", "al");

		List<Integer> numbers = Arrays.asList(12, 84, 5, 64, 28, 8);

		/* ForEach: It starts the internal iteration of the elements in the Stream, applies a Consumer to each element in the Stream. 
		 * ForEach method returns void.*/
		System.out.println("ForEach Sample: ");
		names.stream().forEach(i -> System.out.print(i + " "));

		Map<Integer, String> map = new HashMap<>();
		map.put(1, "abc");
		map.put(2, "xyz");
		map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));

		// Collect: Collect stops the stream and puts the values from the stream into some kind of Collection, like a List.
		System.out.println("\nCollect Sample: ");
		List<String> distinctElements = names.stream().distinct().collect(Collectors.toList());
		distinctElements.stream().forEach(i -> System.out.print(i + " "));

		Set<String> mySet = names.stream().collect(Collectors.toSet());
		mySet.stream().forEach(i -> System.out.print(i + " "));

		String joined = names.stream().collect(Collectors.joining(", "));
		System.out.println("Joined Strings: " + joined);

		// toArray: toArray() stops the stream and returns an array with the elements contained in the stream.
		System.out.println("\nToArray Sample: ");
		Object[] objects = names.stream().toArray();
		Stream.of(objects).forEach(i -> System.out.print(i + " "));

		/* Reduce: It is a terminal operation that can reduce all elements in the stream to a single element.
		 * The reduce method takes a BinaryOperator as a parameter.
		 */
		System.out.println("\nReduce Sample: ");
		int result = numbers.stream().reduce(0, (sum, i) -> sum + i);
		System.out.println("Sum: " + result);

		String reduced = names.stream().reduce("", (acc, el) -> acc + "|" + el);
		System.out.println("Concatenate String: " + reduced);

		List<Integer> costList = Arrays.asList(100, 200, 300, 400, 500);
		// Apply tax for each cost and sum all the cost with tax values
		double bill = costList.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
		// or
		bill = costList.stream().map((cost) -> cost + .12 * cost).reduce(0.0, (sum, cost) -> sum + cost);
		System.out.println("Total : " + bill);

		/* Matchers: It takes a single Predicate as parameter, starts the internal iteration of the Stream, and applies the Predicate parameter to each 
		 * element. If matches found, Predicate returns true, otherwise return false. 
		 * Matcher Methods: anyMatch(), allMatch(), noneMatch().
		 */
		/* anyMatch: If the Predicate returns true for any of the elements, anyMatch() method returns true, otherwise return false. */
		System.out.println("AnyMatch Sample: ");
		boolean flag = names.stream().anyMatch(s -> s.startsWith("R"));
		System.out.println("Any Match: " + flag);

		/* allMatch:  If the Predicate returns true for all elements in the Stream, allMatch() method returns true, otherwise return false. */
		System.out.println("AllMatch Sample: ");
		flag = names.stream().allMatch(s -> s.startsWith("R"));
		System.out.println("All Match: " + flag);

		/* noneMatch: It will return true if no elements are matched by the Predicate, and false if one or more elements are matched. */
		System.out.println("NoneMatch Sample: ");
		flag = names.stream().noneMatch(s -> s.contains("Z"));
		System.out.println("None Match: " + flag);

		/*count: Count method starts the internal iteration of the elements in the Stream, and counts the elements.*/
		System.out.println("Count Sample: ");
		long count = names.stream().filter(s -> s.length() > 4).count();
		System.out.println("Count: " + count);

		/* FindAny: can find a single element from the Stream. The element found can be from anywhere in the Stream. There is no guarantee 
		 * about from where in the stream the element is taken. */
		System.out.println("FindAny Sample: ");
		Optional<String> anyElement = names.stream().findAny();
		System.out.println("Find Any: " + anyElement.get());

		// FindFirst: It finds the first element in the Stream, if any elements are present in the Stream.
		System.out.println("FindFirst Sample: ");
		Optional<String> findFirst = names.stream().findFirst();
		System.out.println("Find First: " + findFirst.get());

		Stream.of("Ava", "Albert", "Ameer").sorted().findFirst().ifPresent(System.out::println);

		/* Min: The Java Stream min() method is a terminal operation that returns the smallest element in the Stream. Which element is the
		 * smallest is determined by the Comparator implementation you pass to the min() method.
		 */
		System.out.println("min sample: ");
		Optional<Integer> val = numbers.stream().min((a, b) -> a.compareTo(b));
		System.out.println("Min: " + val.get());

		/* Max: The Java Stream max() method is a terminal operation that returns the largest element in the Stream. Which element is the 
		 * largest is determined by the Comparator implementation you pass to the max() method.
		 */
		System.out.println("max sample: ");
		val = numbers.stream().max((a, b) -> a.compareTo(b));
		System.out.println("Max: " + val.get());

		System.out.println("Sum sample: ");
		System.out.println("Sum: " + IntStream.range(1, 10).sum());

		// Avg of squares of an int array
		Arrays.stream(new int[] { 2, 4, 6, 8 }).map(i -> i * i).average().ifPresent(System.out::println);

		// Summary Statistics
		System.out.println("Summary Statistics Sample: ");
		IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10).summaryStatistics();
		System.out.println("Count: " + summary.getCount());
		System.out.println("Sum: " + summary.getSum());
		System.out.println("Count: " + summary.getAverage());
		System.out.println("Min: " + summary.getMin());
		System.out.println("Max: " + summary.getMax());
		System.out.println("Summary: " + summary);

		// Grouping Results: Revisit after some time

		// Collectors.groupingBy -> Group by length
		Map<Integer, List<String>> groups = names.stream().collect(Collectors.groupingBy(w -> w.length()));

		// Collectors.toSet: Same as before but with Set
		Map<Object, Set<String>> groupSet = names.stream()
				.collect(Collectors.groupingBy(w -> w.substring(0, 1), Collectors.toSet()));

		// Collectors.counting Count the number of values in a group

		// Collectors.summing__ summingInt, summingLong, summingDouble to sum group values

		// Collectors.averaging__ averagingInt, averagingLong, ...

		// Average length of each element of a group
		// Collectors.averagingInt(String::length)
	}

	// Imperative Style Vs Declarative Style
	public void streamComparisons() {
		System.out.println("\n\n**************Stream: Comparison between imperative & declarative**************");
		// 1. Iteration: External Vs Internal Iterations:
		// Imperative: External Iterations
		List<String> list = Arrays.asList("Apple", "Orange", "Banana");
		for (String s : list) {
			System.out.println(s);
		}

		Iterator<String> iterator = list.listIterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());

		// Declarative: Internal Iterations
		// using lambda expression
		list.forEach(s -> System.out.println(s));
		// or using method reference on System.out instance
		list.forEach(System.out::println);

		// 2. Count Even Numbers:
		// Imperative:
		List<Integer> intList = Arrays.asList(3, 2, 12, 5, 6, 11, 13);
		long count = 0;
		for (Integer i : intList) {
			if (i % 2 == 0) {
				count++;
			}
		}
		System.out.println(count);

		// Declarative:
		count = intList.stream().filter(i -> i % 2 == 0).count();
		System.out.println(count);

		// 3.Retrieving even number list
		// Imperative:
		intList = Arrays.asList(3, 2, 12, 5, 6, 11, 13);
		List<Integer> evenList = new ArrayList<>();
		for (Integer i : intList) {
			if (i % 2 == 0) {
				evenList.add(i);
			}
		}
		System.out.println(evenList);

		// Declarative:
		evenList = intList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
		System.out.println(evenList);
		// or
		intList.stream().filter(i -> i % 2 == 0).forEach(System.out::println);

		// 4.Finding sum of all even numbers
		// Imperative:
		intList = Arrays.asList(3, 2, 12, 5, 6, 11, 13);
		int sum = 0;
		for (Integer i : intList) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		System.out.println(sum);

		// Declarative:
		sum = intList.stream().filter(i -> i % 2 == 0).mapToInt(Integer::intValue).sum();
		System.out.println(sum);
		// or
		sum = intList.stream().filter(i -> i % 2 == 0).reduce(0, (i, c) -> i + c);
		System.out.println(sum);

		// 5.Finding whether all integers are less than 10 in the list
		// Imperative:
		intList = Arrays.asList(3, 2, 12, 5, 6, 11, 13);
		boolean b = true;
		for (Integer i : intList) {
			if (i >= 10) {
				b = false;
				break;
			}
		}
		System.out.println(b);

		// Declarative:
		boolean flag = intList.stream().allMatch(i -> i < 10);
		System.out.println(flag);

		// 6.Finding all sub-directory names in a directory
		// Imperative:
		List<String> allDirNames = new ArrayList<>();
		try {
			for (File file : new File("d:\\").listFiles()) {
				if (file.isDirectory()) {
					allDirNames.add(file.getName());
				}
			}
			System.out.println(allDirNames);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Declarative:
		try {
			allDirNames = Arrays.stream(new File("d:\\").listFiles()).filter(File::isDirectory).map(File::getName)
					.collect(Collectors.toList());
			System.out.println(allDirNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Other Samples:
	public void streamCombinations() {
		System.out.println("\n\n**************Stream: Combining multiple Operations**************");
		List<String> names = Arrays.asList("Amitabh", "Shekhar", "Aman", "Rahul", "Shahrukh", "Salman", "Yana", "Lokesh");

		List<String> strings = Arrays.asList("A", "bbb", "CC", "dd");
		String joined = strings.stream().map(String::toUpperCase).collect(Collectors.joining(":"));
		System.out.println("Joined Strings: " + joined);
		// or
		joined = strings.stream().map(s -> s.toUpperCase()).collect(Collectors.joining(":"));
		System.out.println("Joined Strings: " + joined);

		List<String> words = Arrays.asList("hello", "cool", "Java8", "world!");
		List<Integer> lengths = words.stream().map(String::length).distinct().collect(Collectors.toList());
		lengths.stream().forEach(i -> System.out.print(i + " "));
		// or
		lengths = words.stream().map(s -> s.length()).distinct().collect(Collectors.toList());
		System.out.println();
		lengths.stream().forEach(i -> System.out.print(i + " "));

		// Stream from array, sort, filter and print
		String[] arr = { "Al", "Ankit", "Sam", "Savitha", "Amanda" };
		Stream.of(arr).filter(s -> s.startsWith("S")).sorted().forEach(i -> System.out.print(i + " "));

		// Stream from List, filter and print
		names.stream().map(String::toLowerCase).filter(s -> s.startsWith("a")).forEach(i -> System.out.print(i + " "));

		// Stream with filter, map & print
		names.stream().filter(s -> s.startsWith("S")).map(String::toUpperCase).forEach(System.out::println);

		// Stream with filter, sorted, map & collect
		Set<String> memNamesInUppercase = names.stream().sorted().map(String::toUpperCase).collect(Collectors.toSet());
		memNamesInUppercase.forEach(System.out::println);

		// Stream with filter & count
		System.out.println("Count:" + names.stream().filter(s -> s.startsWith("S")).count());

		// Reduce
		System.out.println("streams with reduce operation:");
		Optional<String> reduced = names.stream().reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent(System.out::println);

		try {
			// Stream rows from text file, sort, filter and print
			Stream<String> bands = Files.lines(Paths.get("bands.txt"));
			bands.sorted().filter(s -> s.length() > 13).forEach(i -> System.out.print(i + " "));
			bands.close();

			// Stream rows from text file and saves the list
			List<String> list = Files.lines(Paths.get("bands.txt")).filter(s -> s.startsWith("jit"))
					.collect(Collectors.toList());
			list.stream().forEach(i -> System.out.print(i + " "));

			// Stream from file and count
			Stream<String> rows = Files.lines(Paths.get("data.txt"));
			int rowCount = (int) rows.map(s -> s.split(",")).filter(s -> s.length == 3).count();
			System.out.println("Row Count: " + rowCount);
			rows.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}