package com.practice.collection.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * There are three implementations of the List interface:
 * ArrayList : It is a resizable array implementation. The size of the ArrayList can be increased dynamically also operations 
 * like add, remove and get can be formed once the object is created. It also ensures that the data is retrieved in the manner
 * it was stored. The ArrayList is not thread-safe.
 * 
 * Vector: It is the thread-safe implementation of ArrayList. The methods are wrapped around a synchronized block. 
 * 
 * LinkedList: the LinkedList implements Queue interface too and provide FIFO (First In First Out) which mean than element is 
 * always removed from the head of the list and element is added at the end of the list. It is faster than ArrayList for add and 
 * remove operation as time complexity is O(1). But for searching or getting an element the average time complexity is O(n) where 
 * n is the size of the list.
 * 
 * What is the difference between List and a Set?
 *   A list can contain duplicate values but Set doesn't allow duplicates.
 *   A list allows retrieval of data to be in same order of insertion but Set doesn't ensures the order.
 */
public class ListAPI {

	/*
	 * - The ArrayList class creates the list which is internally stored in a dynamic array that grows or shrinks in size as the elements
	 *   are added or deleted from it.
	 * - ArrayList allows random access to the elements in the list as it operates on an index-based data structure.
	 * - ArrayList extends AbstarctList 
	 * - access to elements is faster in ArrayList as random access is also possible
	 * - manipulation of elements is slower in ArrayList 
	 */
	public void testArrayList() {

		List<String> list = new ArrayList<String>();
		list.add("Chandler");
		list.add("Monica");
		list.add("Joey");
		list.add("Pheobe");
		list.add("Ross");
		list.add("Rachael");

		System.out.println("List before sorting:");
		for (String string : list)
			System.out.println(string);

		// Sort the list
		Collections.sort(list);

		System.out.println("Simple For each iteration:");
		for (String string : list)
			System.out.println(string);

		System.out.println("For each iteration using lambda expression:");
		list.forEach(data -> System.out.println(data));

		System.out.println("For each iteration using method references:");
		list.forEach(System.out::println);

		String[] strArray = (String[]) list.toArray();

	}

	/*
	 * - LinkedList also creates the list which is internally stored in a DoublyLinked List.
	 * - LinkedList does not allow random access as it does not have indexes to access elements directly, it has to traverse the list 
	 *   to retrieve or access an element from the list.
	 * - LinkedList extends AbstractSequentialList
	 * - LinkedList implements List, Deque and Queue interface, thus it can behave as a Queue and List both.
	 * - Access to LinkedList elements is slower as it follows sequential access only.
	 * - Manipulation of elements is faster in LinkedList.
	 */
	public void testLinkedList() {

	}

	public void testVector() {

	}
}

/*
 * Arrays and ArrayList ?
 * Arrays are created of fix size whereas ArrayList is dynamic in nature and can vary its length. Also the size of an array cannot be incremented or decremented. But ArrayList has the ability to dynamically increase its capacity and insert more elements.
 * Once the array is created elements cannot be added or deleted from it. But with ArrayList the elements can be added and deleted at runtime.
 * ArrayList is one-dimensional but an array can be multidimensional.
 * An Array can contain objects of a single data type or class. ArrayList if not used with generic can contain objects of different class types.
 */

/*
 * ArrayList or LinkedList ?
 * Insertion: Adding new elements is pretty fast for either type of list. Inserting an element to the start of end of the ArrayList takes O(1). Inserting in middle of an ArrayList is operation of O(n). Inserting an element in Linkedlist takes O(1).
 * Access: For the ArrayList, getting an element from the index i is faster O(1) because each element has an index and can be accessed directly with an index but for LinkedList lookup time complexity isO(n). It's slow because elements are accessed in an sequential manner and always access starts from the head of the list.
 * Deletion:Time complexity of deletion in arraylist is O(n). This is because all remaining elements in the underlying array of Object instances must be shifted to the left after each remove operation. Linkedlist has time complexity of O(1) because deletion can be done by updating the pointers of prev and next elements.
 * So an ArrayList works best for cases where you're doing random access on the list and a LinkedList works better if you're doing a lot of editing in the middle of the list.
 */