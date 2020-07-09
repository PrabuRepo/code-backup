package com.practice.collection.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* Collections Java documentation:
 *   This class is a member of the  Java Collections Framework.
 * 
 * This class consists exclusively of static methods that operate on or return collections. It contains polymorphic algorithms
 * that operate on collections, "wrappers", which return a new collection backed by a specified collection, and a few other 
 * odds and ends. 
 * The methods of this class all throw a NullPointerException if the collections or class objects provided to them are null. 
 * 
 * The documentation for the polymorphic algorithms contained in this class generally includes a brief description of the
 * implementation. Such descriptions should be regarded as implementation notes, rather than parts of the specification. 
 * 
 * Implementors should feel free to substitute other algorithms, so long as the specification itself is adhered to. (For example,
 * the algorithm used by sort does not have to be a mergesort, but it does have to be stable.)
 * 
 * The "destructive" algorithms contained in this class, that is, the algorithms that modify the collection on which they operate,
 * are specified to throw UnsupportedOperationException if the collection does not support the appropriate mutation primitive(s), 
 * such as the set method. These algorithms may, but are not required to, throw this exception if an invocation would have no 
 * effect on the collection. For example, invoking the sort method on an unmodifiable list that is already sorted may or may not
 * throw UnsupportedOperationException. 
 * 
 */
public class CollectionsAPI {

	public static void main(String[] args) {
		// Collections.sort(list);
		// Collections.binarySearch(list, key);

		/* How can an ArrayList be synchronized without using Vector?
		Collections.synchronizedList(list);
		//Other Synchronized methods:
		Collections.synchronizedCollection(c);
		Collections.synchronizedMap(m);
		Collections.synchronizedSet(s);
		*/

		/*
		 * To sort the elements in the reverse natural order of the strings, get a reverse Comparator from the Collections 
		 * class with reverseOrder(). Then, pass the reverse Comparator to the sort() method.
		 */
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(11);
		list.add(8);
		Comparator<Integer> comp = Collections.reverseOrder();
		Collections.sort(list, comp);

		// How to sort a list of strings - case insensitive?
		List<String> stringList = new ArrayList<>();
		Collections.sort(stringList, String.CASE_INSENSITIVE_ORDER);

		/*How to make a List (ArrayList,Vector,LinkedList) read only?
		 * A list implementation can be made read only using Collections.unmodifiableList(list). This method returns a new list. 
		 * If a user tries to perform add operation on the new list; UnSupportedOperationException is thrown.*/
		Collections.unmodifiableList(list);
	}
}
