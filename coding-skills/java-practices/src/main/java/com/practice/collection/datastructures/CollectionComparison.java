package com.practice.collection.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionComparison {

	public static void main(String[] args) {
		CollectionComparison ob = new CollectionComparison();
		List<Person> list = ob.mockData();

		System.out.println("Print the data(Before sorting):");
		for (Person a : list)
			System.out.print(a);

		ob.comparatorSample1(list);

		ob.comparatorSample2(list);

		ob.comparableSample1(list);

		// Additional sample using comparator & Comparable interface
		List<Person2> list2 = ob.mockData2();
		System.out.println("Before Sorting:");
		for (Person2 a : list2) {
			System.out.print(a.getDogName() + ", ");
		}
	}

	// 1.Using Comparator Interface(Age) implementation
	public void comparatorSample1(List<Person> list) {
		System.out.println("\nSorting based on Age:");
		Collections.sort(list, new Comparator<Person>() { // Anonymous class implementation
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getPersonAge() <= o2.getPersonAge() ? -1 : 1;
			}
		});
		list.forEach(Person -> System.out.print(Person));
	}

	// 2.Using Comparator Interface(Name) implementation
	public void comparatorSample2(List<Person> list) {
		System.out.println("\nSorting based on Name:");
		Collections.sort(list, (Person d1, Person d2) -> d1.getPersonName().compareToIgnoreCase(d2.getPersonName())); // Lamda expression
		list.forEach(System.out::print);
	}

	// 3.Using Comparable Interface: It can be implemented in class level
	public void comparableSample1(List<Person> list) {
		System.out.println("\nSorting based on Id:");
		// By default it refers comparable interface in the class; sort() method throw compilation error without comparable
		// interface impl
		Collections.sort(list);
		list.forEach(System.out::print);
	}

	public void comparatorSample3(List<Person2> list) {
		/*
		 * Collections.sort(list) works without implementing comparable interface only list contains 
		 * string, because string has implemented the comparable interface(inbuilt implementation).
		 * But for user defined objects, we have to implement the comparable interface explicitly. 
		 */
		Collections.sort(list);// Sorts the array list
		System.out.println("Sorting based on names:(Using Comparable->compareTo())");
		// printing the sorted list of names
		for (Person2 a : list) {
			System.out.print(a.getDogName() + ", ");
		}
	}

	public void comparatorSample4(List<Person2> list) {
		System.out.println("\n Sorting based on age:(Using Compartor->compare())");
		// Sorts the array list using comparator
		Collections.sort(list, new Person2()); // new Dog() is equivalent to some comparator class implementation
		for (Person2 a : list)// printing the sorted list of ages
			System.out.print(a.getDogName() + "  : " + a.getDogAge() + ", ");
	}

	public void bothComparison() {

		List<Student> students = new ArrayList<Student>();
		students.add(new Student("Nick", 80, 18));
		students.add(new Student("Helen", 95, 19));
		students.add(new Student("Ross", 75, 17));
		students.add(new Student("asy", 59, 20));
		students.add(new Student("Madd", 90, 21));

		System.out.println("Order of students before sorting is: ");
		for (Student stu : students) {
			/*
			 * stu object prints all the instant variable in Student object, if override the
			 * toString() method in the Student class
			 */
			System.out.println(stu);
		}
		// Invokes compareTo() in Student class which is implemented comparable
		// interface
		System.out.println("Sorting based on Age:(Invokes compareTo() in Student class which is implemented " + "comparable interface)");
		Collections.sort(students);
		for (Student stu : students) {
			System.out.println(stu);
		}
		// Sorting based on Grade:(Invokes compare() in GradeComparator class which is
		// implemented comparator interface)
		Collections.sort(students, new GradeComparator());
		System.out.println("Sorting based on Grade:(Invokes compare() in GradeComparator class which is implemented " + "comparator interface)");
		for (Student stu : students) {
			System.out.println(stu);
		}
		// Sorting based on Name:(Invokes compare() in NameComparator class which is
		// implemented comparator interface)
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}

		});
		System.out.println("Sorting based on Name:(Invokes compare() in NameComparator class which is implemented " + "comparator interface)");
		for (Student stu : students) {
			System.out.println(stu);
		}

	}

	public List<Person> mockData() {
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Shaggy", 3, 111));
		list.add(new Person("Lacy", 2, 323));
		list.add(new Person("Roger", 10, 9423));
		list.add(new Person("Tommy", 4, 4393));
		list.add(new Person("Tammy", 1, 694));
		return list;
	}

	public List<Person2> mockData2() {
		List<Person2> list = new ArrayList<Person2>();

		list.add(new Person2("shaggy", 3));
		list.add(new Person2("Lacy", 2));
		list.add(new Person2("Roger", 10));
		list.add(new Person2("Tommy", 4));
		list.add(new Person2("Tammy", 1));
		return list;
	}
}

class Person implements Comparable<Person> {
	int		PersonAge;
	String	PersonName;
	int		id;

	public Person(String name, int age, int id) {
		PersonAge = age;
		PersonName = name;
		this.id = id;
	}

	public int getPersonAge() {
		return PersonAge;
	}

	public void setPersonAge(int PersonAge) {
		this.PersonAge = PersonAge;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String PersonName) {
		this.PersonName = PersonName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return PersonName + ": " + PersonAge + "-" + id + "; ";
	}

	@Override
	public int compareTo(Person o) {
		return this.id <= o.id ? -1 : 1;
	}
}

class Person2 implements Comparator<Person2>, Comparable<Person2> {
	private String	name;
	private int		age;

	Person2() {
	}

	Person2(String n, int a) {
		name = n;
		age = a;
	}

	public String getDogName() {
		return name;
	}

	public int getDogAge() {
		return age;
	}

	// Overriding the compareTo method, Comparable interface has comapareTo method declaration
	public int compareTo(Person2 d) {
		// System.out.println("this.name:"+this.name+" "+"d.name:"+d.name);
		// System.out.println("Return:"+(this.name).compareTo(d.name));
		// Sorting based on name
		// Positive no ->this object is greater than the object we are comparing against(Dog d)
		// Negative ->this object is less than the object we are comparing against(Dog d)
		// 0 ->this object is equal to the object we are comparing against(Dog d)
		// CompareTo sorting follows the bubble sorting
		// Ascending Order
		// return (this.name).compareTo(d.name);

		// Descending Order
		// return (d.name).compareTo(this.name);

		// compareToIgnoreCase -> used to ignore the letter cases.
		// compareTo -> Gives the wrong order, if the list of string has different cases.
		return (this.name).compareToIgnoreCase(d.name);
	}

	// Overriding the compare method to sort the age , Comparator interface has comapareTo method declaration
	public int compare(Person2 d, Person2 d1) {
		// System.out.println("d.age:"+d.age+" "+"d1.age:"+d1.age);
		// System.out.println("Return:"+(d.age - d1.age));
		// Sorting based on age
		return d.age - d1.age;
		// Sorting based on name
		// return (d.name).compareToIgnoreCase(d1.name);
	}
}

class Student implements Comparable<Student> {
	private String	name;
	private int		age;
	private String	lesson;
	private int		grade;

	public Student() {

	}

	public Student(String name, int grade, int age) {
		super();
		this.grade = grade;
		this.name = name;
		this.age = age;
		// this.lesson = lesson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "[name=" + this.name + ", age=" + this.age + ", lesson=" + this.lesson + ", grade=" + this.grade + "]";
	}

	@Override
	public int compareTo(Student s) {
		if (this.age > s.age) {
			return 1;
		} else if (this.age < s.age) {
			return -1;
		} else {
			return 0;
		}
		// return (this.name).compareTo(s.name);
	}
}

class GradeComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		// (ascending order would be: o1.getGrade()-o2.getGrade())
		// descending order
		return o1.getGrade() - o2.getGrade();
	}
}