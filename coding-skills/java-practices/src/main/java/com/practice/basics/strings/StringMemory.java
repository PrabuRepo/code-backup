package com.practice.basics.strings;

/*
 * JVM divides the allocated memory to a Java program into two parts. one is Stack and another one is heap. 
 *   - Stack is used for execution purpose
 *   - Heap is used for storage purpose. 
 * In that heap memory, JVM allocates some memory specially meant for string literals. This part of the heap memory
 * is called "String Constant Pool".
 * 
 * Whenever you create a string object using string literal, that object is stored in the "string constant pool" and 
 * whenever you create a string object using new keyword, such object is stored in the "heap" memory.
 */
public class StringMemory {

	/* when you create string objects like below, they will be stored in the String Constant Pool.
	 * 
	 * “When you create a string object using string literal, JVM first checks the content of to be created object. If there
	 *  exist an object in the pool with the same content, then it returns the reference of that object. It doesn’t create 
	 *  new object. If the content is different from the existing objects then only it creates new object.”
	 * 
	 */
	public void stringConstantPool() {
		String s1 = "abc";
		String s2 = "xyz";
		String s3 = "123";
		String s4 = "A";
	}

	/*
	 * when you create string objects using new keyword like below, they will be stored in the heap memory.
	 * when you create string objects using new keyword, a new object is created whether the content is same or not.
	 */
	public void heapMemory() {
		String s5 = new String("abc");
		char[] c = { 'J', 'A', 'V', 'A' };
		String s6 = new String(c);
		String s7 = new String(new StringBuffer());
	}

	/* 
	 * This can be proved by using “==” operator. As “==” operator returns true if two objects have same physical address in 
	 * the memory otherwise it will return false. In the below example, s1 and s2 are created using string literal “abc”. 
	 * So, s1 == s2 returns true. Where as s3 and s4 are created using new operator having the same content. But, s3 == s4 
	 * returns false.
	 * 
	 * In simple words, there can not be two string objects with same content in the string constant pool. But, there can be 
	 * two string objects with the same content in the heap memory.
	 */
	public void memoryComparison() {
		// Creating string objects using literals
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1 == s2);        // Output : true

		// Creating string objects using new operator
		String s3 = new String("abc");
		String s4 = new String("abc");
		System.out.println(s3 == s4);        // Output : false
	}

	/*
	 * In simple words, there can not be two string objects with same content in the string constant pool. But, there can be two string
	 * objects with the same content in the heap memory.
	 * Conclusion : 
	 *   Immutability is the fundamental property of string objects. In whatever way you create the string objects, either using string 
	 *   literals or using new operator, they are immutable.
	 */
	public void proveStringsImmutable() {
		String s1 = "JAVA";
		String s2 = "JAVA";
		System.out.println(s1 == s2);         // Output : true

		s1 = s1 + "J2EE";
		System.out.println(s1 == s2);         // Output : false

		// is new String() also immutable? -Yes
		String s3 = new String("JAVA");
		System.out.println(s3);         // Output : JAVA
		s3.concat("J2EE");
		System.out.println(s3);         // Output : JAVA

	}

	/*
	 * When To Use “==”, equals() And hashCode() On Strings?
	 *    “==” operator, equals() method and hashcode() methods are used to check the equality of any type of objects in Java. 
	 * 
	 * Shallow Comparison:
	 * “==” operator compares the two objects on their physical address. That means if two references are pointing to same object
	 * in the memory, then comparing those two references using “==” operator will return true. For example, if s1 and s2 are 
	 * two references pointing to same object in the memory, then invoking s1 == s2 will return true. This type of comparison 
	 * is called “Shallow Comparison”.
	 * 
	 * Deep Comparison:
	 * equals() method, if not overrided, will perform same comparison as “==” operator does i.e comparing the objects on 
	 * their physical address. So, it is always recommended that you should override equals() method in your class so that 
	 * it provides field by field comparison of two objects. This type of comparison is called “Deep Comparison”.
	 * 
	 * hashCode() method returns hash code value of an object in the Integer form. It is recommended that whenever you override 
	 * equals() method, you should also override hashCode() method so that two equal objects according to equals() method must 
	 * return same hash code values. This is the general contract between equals() and hashCode() methods that must be maintained
	 * all the time.
	 * 
	 * String class, equals() method is overridden to provide the comparison of two string objects based on their contents.
	 * That means, any two string objects having same content will be equal according to equals() method
	 * hashCode() method is also overridden so that two equal string objects according to equals() method will return same 
	 * hash code values
	 */
	public void stringEqualsAndHashcode() {

		// Example 1:
		String s1 = "JAVA";
		String s2 = "JAVA";
		System.out.println(s1 == s2);// s1 == s2 —> will return true as both are pointing to same object in the constant pool.
		System.out.println(s1.equals(s2));// s1.equals(s2) —> will also return true as both are referring to same object.
		System.out.println(s1.hashCode() == s2.hashCode());// s1.hashCode() == s2.hashCode() —> It also returns true.

		// Example 2:
		String s3 = new String("JAVA");
		String s4 = new String("JAVA");
		System.out.println(s3 == s4);// s1 == s2 —> will return false because s1 and s2 are referring to two different objects in the memory.
		System.out.println(s3.equals(s4));// s1.equals(s2) —> will also return true as both are referring to same object.
		System.out.println(s3.hashCode() == s4.hashCode());// s1.hashCode() == s2.hashCode() —> It also returns true.

		// Example 3:
		String s5 = "JAVA";
		String s6 = new String("JAVA");
		System.out.println(s5 == s6);// s1 == s2 —> will return false because s1 and s2 are referring to two different objects in the memory.
		System.out.println(s5.equals(s6));// s1.equals(s2) —> will return true as both the objects have same content.
		System.out.println(s5.hashCode() == s6.hashCode());// s1.hashCode() == s2.hashCode() —> It also returns true.

		// Example 4:
		String s7 = "0-42L";
		String s8 = "0-43-";
		System.out.println(s7 == s8);// s1 == s2 —> will return false as s1 and s2 are referring to two different objects in the memory.
		System.out.println(s7.equals(s8));// s1.equals(s2) —> It will also return false as both the objects have different content.
		System.out.println(s7.hashCode() == s8.hashCode());// s1.hashCode() == s2.hashCode() —> It will return true. (???….)
		/* This is because, two unequal string objects according to equals() method may have same hash code values. Therefore,
		 * it is recommended not to use hashCode() method to compare two string objects. You may not get expected result.
		 **/

		/*
		 * Conclusion:
		 *   - When you want to check the equality of two string objects on their physical existence in the memory, then use “==” operator. 
		 *   - If you want to check the equality of two string objects depending upon their contents, then use equals() method. 
		 *   - It is recommended not to use hashCode() method to check the equality of two string objects. You may get unexpected result.
		 */
	}

}
