package com.practice.basics.strings;

/*
 * Immutable class is a class which once created, it’s contents can not be changed. java.lang.String objects are immutable in java.
 * That is, once you create String objects, you can’t modify them. Whenever you try to modify the existing String object, a new String 
 * object is created with modifications. 
 * Immutable objects are the objects whose state can not be changed once constructed. e.g. String class
 * 
 * To create an immutable class following steps should be followed:
 *  - Create a final class.
 *  - Set the values of properties using constructor only.
 *  - Make the properties of the class final and private
 *  - Do not provide any setters for these properties. 
 *  - If the instance fields include references to mutable objects, don't allow those objects to be changed:
 *       Don't provide methods that modify the mutable objects.
 *       Don't share references to the mutable objects. Never store references to external, mutable objects passed to the 
 *       constructor; if necessary, create copies, and store references to the copies. Similarly, create copies of your internal 
 *       mutable objects when necessary to avoid returning the originals in your methods.
 *       
 *  All wrapper classes in java.lang are immutable – 
 *     String, Integer, Boolean, Character, Byte, Short, Long, Float, Double, BigDecimal, BigInteger  
 *     
 *  Advantages of immutability?
 *    - Immutable objects are automatically thread-safe, the overhead caused due to use of synchronisation is avoided.
 *    - Once created the state of the immutable object can not be changed so there is no possibility of them getting into an 
 *      inconsistent state.
 *    - The references to the immutable objects can be easily shared or cached without having to copy or clone them as there 
 *      state can not be changed ever after construction.
 *    - The best use of the immutable objects is as the keys of a map.
 */
public class ImmutableClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String st = "ABCD";
		System.out.println("String:" + st + " & Hashcode:" + st.hashCode());
		st.concat("DEFG"); // Here "st" value cant be changed, since String is immutable
		System.out.println("String:" + st + " & Hashcode:" + st.hashCode());
		// Here "st" value can be changed only when we assigned to string object & now new object
		// will be created(i.e hashcode is different for "st" variable)
		st = st.concat("-EFGH");
		System.out.println("String:" + st + " & Hashcode:" + st.hashCode());
		st = "GHVF";
		System.out.println("String:" + st + " & Hashcode:" + st.hashCode());
		// st & st2 will have same hashcode(i.e memory), because string is immutable & cant be changed
		// In string pool, if any one of the string is already assigned to for some variable
		// then same memory will be reused by new variable which has same string.
		String st2 = "ABCD";
		System.out.println("String:" + st2 + " & Hashcode:" + st2.hashCode());

		st2 = "ABCD-EFGH";
		System.out.println("String:" + st2 + " & Hashcode:" + st2.hashCode());
	}
}

// Sample ImmutableClass
final class FinalPersonClass {
	private final String	name;
	private final int		age;

	public FinalPersonClass(final String name, final int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
}
