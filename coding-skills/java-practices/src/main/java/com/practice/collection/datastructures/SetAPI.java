
package com.practice.collection.datastructures;

import java.util.HashSet;
import java.util.Set;

/*
 * A Set is a collection that contains no duplicate elements and at most one null element.
 * HashSet, SortedSet and TreeSet are the commonly used class which implements Set interface.

SortedSet - It is an interface which extends Set. As the name suggests, the interface allows the data to be iterated in the ascending order or sorted on the basis of Comparator or Comparable interface. All elements inserted into the interface must implement Comparable or Comparator interface.
TreeSet - It is the implementation of SortedSet interface. This implementation provides guaranteed log(n) time cost for the basic operations (add, remove and contains). The class is not synchronized. The class uses Red-Black tree data structure.
HashSet: This class implements the Set interface, backed by a hash table (actually a HashMap instance). It makes no guarantees as to the iteration order of the set; in particular, it does not guarantee that the order will remain constant over time. This class permits the null element. This class offers constant time performance for the basic operations (add, remove, contains and size), assuming the hash function disperses the elements properly among the buckets
 */
public class SetAPI {

	public void testHashSet() {

		Set<String> data = new HashSet<>();
		data.add("Chandler");
		data.add("Monica");
		data.add("Joey");
		data.add("Pheobe");
		data.add("Ross");
		data.add("Rachael");

		data.forEach(n -> System.out.println(n));

	}

}
