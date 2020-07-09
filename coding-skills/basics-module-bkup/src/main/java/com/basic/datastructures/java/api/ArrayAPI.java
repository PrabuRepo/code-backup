package com.basic.datastructures.java.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.common.utilities.Utils;

public class ArrayAPI {

	public static void main(String[] args) {

		ArrayAPI ob = new ArrayAPI();

		ob.arraySortAPI();

		//		ob.intArrayConversionsAPI();

		//		ob.stringArrayConversionsAPI();

		//		ob.arrayBinarySearchAPI();

		//		ob.arrayOtherAPIs();

		//		ob.arrayAPI();
	}

	/*
	 * Overall APIs in the Arrays.java
	 */
	public void arrayAPI() {
		int[] a = { 1, 5, 7, 9 };
		int[][] arr = { { 1, 6 }, { 9, 8 }, { 3, 5 } };

		Arrays.asList(a); // array
		Arrays.sort(a); // array
		Arrays.sort(a, 1, 3); // array, fromIndex, toIndex
		Arrays.sort(arr, (x, y) -> x[1] - y[1]); // array, comparator
		Arrays.binarySearch(a, 7); // array, key
		Arrays.binarySearch(arr, 0, arr.length - 1, 5); // array, fromIndex, toIndex, key
		Arrays.copyOf(a, a.length); // array, length
		Arrays.copyOfRange(a, 2, 5); // array, from, to
		Arrays.equals(a, a); // array1, array2
		Arrays.fill(a, -1); // array, val
		Arrays.hashCode(a); // array
		Arrays.stream(a);
	}

	public void arraySortAPI() {
		int[] a = { 5, 8, 2, 7, 1 };
		Arrays.sort(a, 1, 3); // array, fromIndex, toIndex
		Arrays.sort(a); // array
		// Unfortunately, for a primitive array, there is no direct way to sort in descending order.
		// Solution: Sort & reverse the array
		Arrays.sort(a);
		// No API for primitive reverse
		Utils.reverse(a);

		// Integer Object Arrays:
		Integer[] integerArr = new Integer[] { 15, 11, 9,
				55, 47, 18, 520, 1123, 366, 420 };
		Arrays.sort(integerArr);
		Arrays.sort(integerArr, Collections.reverseOrder());
		Arrays.sort(integerArr, 2, 6);

		// 2D Array sorting
		int[][] arr = { { 1, 6 }, { 9, 8 }, { 3, 5 } };
		//Asc order based on 0th index
		Arrays.sort(arr, (x, y) -> x[0] - y[0]); // array, comparator
		//Desc order based on 1st index
		Arrays.sort(arr, (x, y) -> y[1] - x[1]); // array, comparator
		for (int[] ar : arr)
			System.out.println(Arrays.toString(ar));
	}

	public void intArrayConversionsAPI() {
		System.out.println(
				"int/Integer: Array to list conversions: ");
		/**1.1. Primitive array to List: */
		// using boxed & collect in stream
		int[] arr = { 1, 2, 3 };
		List<Integer> list1 = Arrays.stream(arr).boxed()
				.collect(Collectors.toList());
		list1.forEach(k -> System.out.print(k + " "));

		/**1.2. Integer object array to list: */
		// using asList
		Integer[] arr2 = { 1, 2, 3 };
		// asList supports only object array's, not primitive
		List<Integer> list2 = Arrays.asList(arr2);
		list2.forEach(k -> System.out.print(k + " "));

		System.out.println("\nList to Array Conversions: ");
		/**2.1. List to Primitive array: */
		// using stream: mapToInt
		int[] arr3 = list1.stream()
				.mapToInt(i -> i.intValue()).toArray();
		arr3 = list1.stream().mapToInt(Integer::intValue)
				.toArray();
		System.out.println("List to primitive array: "
				+ Arrays.toString(arr3));

		/**2.2. List to Object array: */
		Integer[] arr4 = list1
				.toArray(new Integer[list1.size()]);
		Integer[] arr5 = list1.stream()
				.toArray(Integer[]::new);
		System.out.println("List to Object array: "
				+ Arrays.toString(arr5));

		/**List to Primitive conversion & filter out null values:*/
		int[] primitive = list1.stream()
				.mapToInt(i -> (i == null ? 0 : i))
				.toArray();
		// Same using method reference:
		primitive = list1.stream().filter(Objects::nonNull)
				.mapToInt(Integer::intValue).toArray();
		primitive = list1.stream()
				.map(i -> (i == null ? 0 : i))
				.mapToInt(Integer::intValue).toArray();
	}

	public void stringArrayConversionsAPI() {
		System.out.println(
				"String: Array to list conversions: ");
		/**1. String array to List: */
		String[] strArr = new String[] { "abc", "def",
				"ghi" };
		List<String> list = Arrays.asList(strArr);
		list.forEach(k -> System.out.print(k + " "));

		System.out.println("\nList to Array Conversions: ");
		/**2. List to String array: */
		String[] strArr2 = list
				.toArray(new String[list.size()]);
		String[] strArr3 = list.stream()
				.toArray(String[]::new);
		System.out.println("List to String array: "
				+ Arrays.toString(strArr2));
		System.out.println("List to String array: "
				+ Arrays.toString(strArr3));
	}

	public void arrayBinarySearchAPI() {
		int[] arr = { 1, 3, 5, 7, 9, 11 };
		System.out.println(Arrays.toString(arr));
		System.out.println(
				"Binary Search API for the given value: ");
		System.out.println("Exact Element: "
				+ Arrays.binarySearch(arr, 5));
		System.out.println("Before first element: "
				+ Arrays.binarySearch(arr, 0));
		System.out.println("Element b/w 2 elements: "
				+ Arrays.binarySearch(arr, 4)); // Returns -ve val of next pos
		System.out.println("After last element: "
				+ Arrays.binarySearch(arr, 12));

		System.out.println(
				"Binary Search API for the given ranges: ");
		// arr, fromIndex, toIndex, Val
		System.out.println("Exact Element: "
				+ Arrays.binarySearch(arr, 0, 3, 5));
		System.out.println("Before first element: " + Arrays
				.binarySearch(arr, 0, arr.length - 1, 5));
		System.out.println("Element b/w 2 elements: "
				+ Arrays.binarySearch(arr, 0,
						arr.length - 1, 4));
		System.out.println("After last element: "
				+ Arrays.binarySearch(arr, 2, 5, 12));
	}

	public void arrayOtherAPIs() {
		int[] a = { 5, 7, 8, 11, 4, 6, 2 };

		int[] cpy = Arrays.copyOf(a, a.length);
		System.out.println(Arrays.toString(cpy));

		int[] partialCpy = Arrays.copyOfRange(a, 1, 5);
		System.out.println(Arrays.toString(partialCpy));

		int[] a1 = { 5, 7, 8, 11, 4, 6, 2 };
		int[] a2 = { 5, 7, 8, 11, 4, 6, 2 };
		System.out.println(
				"Equals: " + Arrays.equals(a1, a2));

		Arrays.fill(a, -1);
		System.out
				.println("Fill -1: " + Arrays.toString(a));
	}
}
