package com.practice.oop.interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Comparator interface is used to order the objects of user-defined classes. A comparator object is capable of comparing 
 * two objects of two different classes.
 * 
 *  Using comparator interface- Comparator interface is used to order the objects of user-defined class. This interface is 
 *  present java.util package and contains 2 methods - compare(Object obj1, Object obj2) and equals(Object element).
 */
public class ComparatorInterface {
	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<>();
		list.add(new Student(3, "ccc"));
		list.add(new Student(5, "eee"));
		list.add(new Student(1, "aaa"));
		list.add(new Student(2, "bbb"));
		list.add(new Student(4, "ddd"));

		// Using lambda expressions and functional interfaces
		// Collections.sort(list, (u, v) -> u.id - v.id);
		Collections.sort(list, (u, v) -> u.name.compareToIgnoreCase(v.name));

		// Using Inner class
		/*Collections.sort(list, new Comparator<Student>() {
			public int compare(Student o1, Student o2) {
				return o2.name.compareToIgnoreCase(o1.name); // Desc order based on Name
				// return o1.id - o2.id; // Asc order based on Id
			}
		});*/

		// Using Separate Comparator Implementation Class
		// Collections.sort(list, new IdComparator());
		list.stream().forEach(k -> System.out.println(k.id + " - " + k.name));
	}
}

class Student {
	int		id;
	String	name;

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}
}

// Using Separate class
class IdComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		// return o1.id - o2.id;
		return o1.id <= o2.id ? -1 : 1;
	}

}
