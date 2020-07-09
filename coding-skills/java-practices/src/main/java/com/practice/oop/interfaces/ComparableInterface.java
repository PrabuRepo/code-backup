package com.practice.oop.interfaces;

import java.util.ArrayList;
import java.util.Collections;

/*
 * A comparable object is capable of comparing itself with another object. The class itself must implements the 
 * java.lang.Comparable interface to compare its instances.
 * 
 * Comparator Vs Comparable:
 *   - Unlike Comparable, Comparator is external to the element type we are comparing. It’s a separate class. We create 
 *     multiple separate classes (that implement Comparator) to compare by different members.
 *   - Logically, Comparable interface compares “this” reference with the object specified and Comparator in Java compares 
 *     two different class objects provided.
 *   - If any class implements Comparable interface in Java then collection of that object either List or Array can be sorted 
 *     automatically by using Collections.sort() or Arrays.sort() method and objects will be sorted based on there natural order
 *     defined by CompareTo method.
 *   - To summarize, if sorting of objects needs to be based on natural order then use Comparable whereas if you sorting 
 *     needs to be done on attributes of different objects, then use Comparator in Java.
 */
public class ComparableInterface {

	public static void main(String[] args) {
		ArrayList<Student1> list = new ArrayList<>();
		list.add(new Student1(3, "ccc"));
		list.add(new Student1(5, "eee"));
		list.add(new Student1(1, "aaa"));
		list.add(new Student1(2, "bbb"));
		list.add(new Student1(4, "ddd"));

		Collections.sort(list);
		list.stream().forEach(k -> System.out.println(k.id + " - " + k.name));
	}

}

class Student1 implements Comparable<Student1> {
	int		id;
	String	name;

	public Student1(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int compareTo(Student1 o) {
		// return this.id - o.id; //Asc order using id
		return o.name.compareToIgnoreCase(this.name); // Desc order using name
	}
}