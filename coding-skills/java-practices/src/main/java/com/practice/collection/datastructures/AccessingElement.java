package com.practice.collection.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * Different ways to access the element:
 *  - for loop
 *  - foreach 
 *  - Iterator
 *  - ListIterator
 *  
 * Index based access Vs Iterator based access:
 * --------------------------------------------
 * Index based access allows access of the element directly on the basis of index. The cursor of the data structure can
 * directly goto the 'nth' location and get the element. It does not have to traverse through n-1 elements.
 * 
 * In Iterator based access, the cursor has to traverse through each element to get the desired element. So to reach the 
 * 'nth' element it needs to traverse through n-1 elements.
 * 
 * Insertion, updation or deletion will be faster for iterator based access if the operations are performed on elements 
 * present in middle of the data structure.
 * 
 * Insertion, updation or deletion will be faster for index based access if the operations are performed on elements present 
 * at the end of the data structure.
 * 
 * Traversal or search in index based data structure is faster.
 * 
 * ArrayList is index access and LinkedList is iterator access.
 */
public class AccessingElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void testForLoop() {

	}

	/*
	 * Using for-each loop:
	 *    for-each version of for loop can also be used for traversing the elements of a collection. But this can only be used if 
	 *    we don't want to modify the contents of a collection and we don't want any reverse access. 
	 *    for-each loop can cycle through any collection of object that implements Iterable interface.
	 */
	public void testForEachLoop() {
		LinkedList<String> ls = new LinkedList<String>();
		ls.add("a");
		ls.add("b");
		ls.add("c");
		ls.add("d");
		for (String str : ls) {
			System.out.print(str + " ");
		}
	}

	/*
	 * Accessing elements using Iterator: 
	 *   Iterator Interface is used to traverse a list in forward direction, enabling you to remove or modify the elements of the 
	 *   collection. Each collection classes provide iterator() method to return an iterator.
	 * 
	 */
	public void testIterator() {
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("ab");
		ar.add("bc");
		ar.add("cd");
		ar.add("de");
		Iterator<String> it = ar.iterator();    // Declaring Iterator
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}

	/*
	 * Accessing elements using ListIterator:
	 * 	   ListIterator Interface is used to traverse a list in both forward and backward direction. It is available to only those 
	 * 	   collections that implements the List Interface.
	 */
	public void testListIterator() {
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("ab");
		ar.add("bc");
		ar.add("cd");
		ar.add("de");
		ListIterator<String> litr = ar.listIterator();
		while (litr.hasNext())   // In forward direction
		{
			System.out.print(litr.next() + " ");
		}
		while (litr.hasPrevious())   // In backward direction
		{
			System.out.print(litr.previous() + " ");
		}
	}
}
