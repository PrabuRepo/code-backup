package com.basic.datastructures.java.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaExpression {
	public static void main(String[] args) {
		LambdaExpression ob = new LambdaExpression();

		ob.sortingSample();

		ob.iterationLambdaExpr();

	}

	/* Consolidation of all data types sorting:
	 *  Preferences: Arrays -> Collections -> Stream
	 *  Note: Arrays.sort works for arrays which can be of primitive data type also. 
	 * Collections.sort() works for objects Collections like ArrayList, LinkedList, etc.*/
	public void sortingSample() {
		stringSorting();

		stringCollectionSorting();

		arraySorting();

		listSorting();

		setSorting();

		priorityQueueSorting();

		mapSorting();

		customObjectsSorting();

	}

	// String Sorting:
	private void stringSorting() {
		String randomString = "adcbgekhs";
		// Convert string to char array
		char[] chars = randomString.toCharArray();
		Arrays.sort(chars);
		String sortedString1 = String.valueOf(chars);
		// or
		String sortedString2 = Stream.of(randomString.split("")).sorted().collect(Collectors.joining());
	}

	// String Collection Sorting:
	private void stringCollectionSorting() {
		// 1.Array Sort
		String[] tokens = { "Alex", "Charles", "Brian", "David" };
		Arrays.sort(tokens);
		Arrays.sort(tokens, Collections.reverseOrder());
		// 2.List Sort
		List<String> names = Arrays.asList("Alex", "Charles", "Brian", "David");
		names.sort(Comparator.comparing(String::toString));
		names.sort(Comparator.comparing(String::toString).reversed());
		// 3.Collection Sort
		Collections.sort(names);
		Collections.sort(names, Collections.reverseOrder());
		// 4.Stream Sort
		List<String> sortedNames = names.stream().sorted(Comparator.comparing(String::toString))
				.collect(Collectors.toList());
		List<String> reverseSortedNames = names.stream().sorted(Comparator.comparing(String::toString).reversed())
				.collect(Collectors.toList());
	}

	private void arraySorting() {
		// Primitive Arrays:
		int[] arr = new int[] { 15, 11, 9, 55, 47, 18, 520, 1123, 366, 420 };
		Arrays.sort(arr);
		Arrays.sort(arr, 2, 6);
		// Unfortunately, for a primitive array, there is no direct way to sort in descending order.
		// Solution1: Sort & reverse the array
		Arrays.sort(arr);
		// No API for primitive reverse
		reverse(arr);
		// Solution2: Use stream
		Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

		// Integer Object Arrays:
		Integer[] integerArr = new Integer[] { 15, 11, 9, 55, 47, 18, 520, 1123, 366, 420 };
		Arrays.sort(integerArr);
		Arrays.sort(integerArr, Collections.reverseOrder());
		Arrays.sort(integerArr, 2, 6);
	}

	private void listSorting() {
		List<Integer> numbersList = Arrays.asList(15, 11, 9, 55, 47, 18, 1123, 520, 366, 420);
		Collections.sort(numbersList);
		Collections.sort(numbersList, Collections.reverseOrder());
		numbersList.sort(Comparator.comparing(Integer::valueOf));
		// numbersList.sort(Comparator.comparing(Integer::valueOf).reversed());
	}

	private void setSorting() {
		// Set: There is no direct support for sorting the sets in Java.
		/*   1.Convert set to list.
		 *   2.Sort list using Collections.sort() API.
		 *   3.Convert list back to set.
		 */
	}

	private void priorityQueueSorting() {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		// Priority Queue for Map:
		// Sort based on Key
		PriorityQueue<Map.Entry<String, Integer>> pqMap1 = new PriorityQueue<>((a, b) -> a.getKey().compareTo(b.getKey()));
		// Sort based on value
		PriorityQueue<Map.Entry<String, Integer>> pqMap2 = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
		// Sort based on both
		PriorityQueue<Map.Entry<String, Integer>> pqMap3 = new PriorityQueue<>(
				(a, b) -> a.getValue().equals(b.getValue()) ? a.getKey().compareTo(b.getKey()) : a.getValue() - b.getValue());

		// Priority Queue for Custom Objects:
		Queue<Person> queue1 = new PriorityQueue<>((a, b) -> a.getAge() - b.getAge());
		Queue<Person> queue2 = new PriorityQueue<>((a, b) -> a.getFirstName().compareTo(b.getFirstName()));
	}

	private void mapSorting() {
		// Map: map can be sorted in two ways i.e. sort by key or sort by value
		// 1. sort by value
		Map<String, Integer> unsortedMap = new HashMap<>();
		Map<String, Integer> sortedMap = new HashMap<>();
		Map<String, Integer> reverseSortedMap = new HashMap<>();
		unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		// Use Comparator.reverseOrder() for reverse ordering
		unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

		// 2. sort by key - use TreeMap and Stream APIs to sort by key
		Map<String, Integer> unsortedMap2 = new HashMap<>();
		Map<String, Integer> sortedMap2 = new HashMap<>();
		Map<String, Integer> reverseSortedMap2 = new HashMap<>();
		unsortedMap2.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.forEachOrdered(x -> sortedMap2.put(x.getKey(), x.getValue()));
		// Use Comparator.reverseOrder() for reverse ordering
		unsortedMap2.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
				.forEachOrdered(x -> reverseSortedMap2.put(x.getKey(), x.getValue()));
	}

	private void customObjectsSorting() {
		// Sort custom objects:
		/* To sort list of custom objects, we have 2 approaches
		 * 	1.Comparable Interface: int compareTo(o){...}
		 *  2.Comparator Interface: int compare(o1, o2){...}
		 */

		// 3 ways to use the comparator refer comparatorSample()
		comparatorSample();

		// Comparator default Method-Comparing Sample:
		comparingSample();

		numberVsStringCompare();
	}

	// Numbers Vs Strings comparison API:
	private void numberVsStringCompare() {
		List<Person> persons = new ArrayList<Person>();
		// Using FP or Lambda Expression:
		// Numbers - Primitive
		Collections.sort(persons, (o1, o2) -> o2.getAge() - o2.getAge());
		// Numbers - Integer Object
		Collections.sort(persons, (o1, o2) -> o2.getId() - o2.getId());
		// or
		Collections.sort(persons, (o1, o2) -> o2.getId().compareTo(o2.getId()));
		// Strings:
		Collections.sort(persons, (o1, o2) -> o2.getFirstName().compareTo(o1.getFirstName()));
		Collections.sort(persons, (o1, o2) -> o2.getFirstName().compareToIgnoreCase(o1.getFirstName()));

		// Using Comparator.comparing default method:
		// Here no more confusion of compare method or minus
		Collections.sort(persons, Comparator.comparing(o -> o.getAge()));
		Collections.sort(persons, Comparator.comparing(o -> o.getId()));
		Collections.sort(persons, Comparator.comparing(o -> o.getFirstName()));
	}

	// Collection Operations before and after Java8 Implementations
	private void comparatorSample() {
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

		/** Java 8: */
		System.out.println("With Lambda Expressions:");
		// Sort list by last name
		Collections.sort(persons, (p1, p2) -> p1.getFirstName().compareTo(p2.getLastName()));
	}

	// comparing is default method in Comparator Interface
	private void comparingSample() {
		List<Person> persons = new ArrayList<Person>();
		/** comparing method call using method reference */
		Comparator<Person> comparatorByFirstName = Comparator.comparing(Person::getFirstName);
		Collections.sort(persons, comparatorByFirstName);
		Collections.sort(persons, Comparator.comparing(Person::getFirstName));
		Collections.sort(persons, Comparator.comparing(Person::getFirstName).reversed());
		// Group by sorting
		Collections.sort(persons,
				Comparator.comparing(Person::getLastName).thenComparing(Comparator.comparing(Person::getLastName)));

		/** comparing method call using lambda expression */
		Comparator<Person> comparatorByFirstName2 = Comparator.comparing(p -> p.getFirstName());
		Collections.sort(persons, comparatorByFirstName2);
		Collections.sort(persons, Comparator.comparing(p -> p.getFirstName()));
		Collections.sort(persons, comparatorByFirstName2.reversed());
		// Group by sorting
		Comparator<Person> comparatorByFullName2 = comparatorByFirstName2.thenComparing(p -> p.getLastName());
		Collections.sort(persons, comparatorByFullName2);
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

	// Consolidate iterations for array, collection & map
	public void iterationLambdaExpr() {
		// Array:
		int[] arr = { 1, 3, 5, 7 };
		Arrays.stream(arr).forEach(k -> System.out.print(k + " "));

		// List:
		List<Integer> list = new ArrayList<>();
		list.forEach(k -> System.out.print(k + " "));

		// Map:
		Map<String, Integer> map = new HashMap<>();
		map.put("wewe", 14);
		map.put("vfdd", 28);
		map.put("bggdd", 73);
		map.put("qvbvb", 6);
		map.forEach((k, v) -> System.out.println("Key:" + k + "/Value:" + v));
		map.keySet().forEach(k -> System.out.print(k + " "));
		map.values().forEach(k -> System.out.print(k + " "));
	}
}

class Person {

	private String firstName;
	private String lastName;
	private int age;
	private Integer id;

	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return firstName + "-" + lastName + "-" + age;
	}

}