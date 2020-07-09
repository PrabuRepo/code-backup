package com.practice.oop.concepts;

import java.util.List;

/*
 * It represents a relationship between two or more objects where all objects have their own life cycle and there is no owner. The name of an association
 * specifies the nature of the relationship between objects. Association is a relation between two separate classes which establishes through their Objects.
 * Association can be one-to-one, one-to-many, many-to-one, many-to-many. In Object-Oriented programming, an Object communicates to other Object to use 
 * functionality and services provided by that object.
 * There are two forms of association: Composition and Aggregation
 */
public class Association {

}

/*
 * Let’s take an example of the relationship between Teacher and Student. Multiple students can associate with a single teacher and a single student 
 * can associate with multiple teachers. But there is no ownership between the objects and both have their own lifecycle. Both can be created and deleted
 * independently.
 */
class Teacher {
	private String name;
	private List<Student> students;
	// getter and setter methods
}

class Student {
	private String name;
	private List<Teacher> teachers;
	// getter and setter methods
}