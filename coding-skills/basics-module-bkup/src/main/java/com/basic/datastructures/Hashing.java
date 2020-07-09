package com.basic.datastructures;

import java.util.Arrays;
import java.util.Scanner;

import com.common.utilities.LinkedList;

/*
 * Hashing: Hashing is a technique that is used to uniquely identify a specific object from a group of similar objects. 
 * The idea of hashing is to distribute entries (key/value pairs) uniformly across an array. Each element is assigned a key (Key calculates 
 * using the hash function). Using the key, the algorithm (hash function) computes an index that suggests where an entry can be found
 * or inserted.
 *  
 * Hash Function: In simple terms, a hash function maps a big number or string to a small integer that can be used as index in hash table.
 * A good hash function should have following properties
 *    1) Easy to compute: It should be easy to compute and must not become an algorithm in itself.
 *    2) Uniform distribution: It should provide a uniform distribution across the hash table and should not result in clustering.
 *    3) Less Collisions: Collisions occur when pairs of elements are mapped to the same hash value. These should be avoided.
 * Purpose of Hashing:
  - Hashing is used to index data 
  - In cryptographic application 
  - Sharding the Keys 
  - Finding duplicate records 
 *    
 * Hash Table: An array that stores pointers to records corresponding to a given phone number. An entry in hash table is NIL if no 
 * existing phone number has hash function value equal to the index for the entry.
 * 
 * Collision Handling: Since a hash function gets us a small number for a big key, there is possibility that two keys result in same 
 * value. The situation where a newly inserted key maps to an already occupied slot in hash table is called collision and must be
 * handled using some collision handling technique. Following are the ways to handle collisions:
 * 
 * Separate Chaining:The idea is to make each cell of hash table point to a linked list of records that have same hash function value. Chaining 
 * is simple, but requires additional memory outside the table. 
 * 
 * Open Addressing: In open addressing, all elements are stored in the hash table itself. Each table entry contains either a record or NIL. 
 * When searching for an element, we one by one examine table slots until the desired element is found or it is clear that the element is
 * not in the table.
 *
 */
public class Hashing {

}

/*
 * Open Addressing: Like separate chaining, open addressing is a method for handling collisions. In Open Addressing, all elements are 
 * stored in the hash table itself. So at any point, size of the table must be greater than or equal to the total number of keys.
 * 
 * Open Addressing is done following ways: - 
 *    1. Linear Probing  - 
 *    		Linear probing is when the interval between successive probes is fixed (usually to 1). Let’s assume that the hashed index for
 *       a particular entry is index. The probing sequence for linear probing will be:
 *        index = index % hashTableSize 
 *        index = (index + 1) % hashTableSize
 *        index = (index + 2) % hashTableSize  and so on…
 *        
 *    2. Quadratic Probing - Quadratic probing is similar to linear probing and the only difference is the interval between successive
 *    probes or entry slots. The interval between slots is computed by adding the successive value of an arbitrary polynomial in the 
 *    original hashed index. The probing sequence will be:
 *        index = index % hashTableSize 
 *        index = (index + 1^2) % hashTableSize
 *        index = (index + 2^2) % hashTableSize  and so on…
 *    
 *    3. Double Hashing -Double hashing is similar to linear probing and the only difference is the interval between successive probes.
 *    	 Here, the interval between probes is computed by using two hash functions. The probing sequence will be:
 *    			index = (index + 1 * indexH) % hashTableSize;
 *    			index = (index + 2 * indexH) % hashTableSize;  and so on…
 *    	Here index -> First HashFunction; indexH = second hash function
 *    
 */
class HashOpenAddressing {
	Integer[]	array;
	int			currSize;
	int			maxSize;

	// Initialize the array size in the constructor
	public HashOpenAddressing(int size) {
		// Choose max size as nearest prime number to avoid clustering
		this.maxSize = nextPrime(size);
		System.out.println("Max size: " + maxSize);
		this.array = new Integer[maxSize];
		// Arrays.fill(array, -1);
	}

	// Insert Operation
	public void insert(int input) {
		if (!isHashFull()) {
			// int key = findKey1(input); // Linear Probing
			int key = findKey2(input); // Quadratic Probing
			// int key = findKey3(input); // Double Hashing
			array[key] = input;
			currSize++;
		} else {
			System.out.println("Hash full, increase the size.");
		}
	}

	// Search Operation
	public Integer search(int element) {
		// return findValue1(element); //Linear Probing
		// return findValue2(element); // Quadratic Probing
		return findValue3(element); // Double Hashing
	}

	// Delete Operation
	public void delete(Integer n) {
		// int key = findValue1(n); //Linear Probing
		// int key = findValue2(n); // Quadratic Probing
		int key = findValue3(n); // Double Hashing
		if (key >= 0) {
			array[key] = null;
			currSize--;
		} else {
			System.out.println("Data not found!!");
		}
	}

	// Get the empty index/key to insert the data using Linear Probing
	public int findKey1(int data) {
		int index = data % maxSize;
		int i = 1, hashValue = index;
		while (array[index] != null) {
			// 1.Linear Probing: If slot hash(x) % S is full, then we try (hash(x) + i) % S; where i=1,2,3...
			index = (hashValue + i) % maxSize;
			i++;
		}
		return index;
	}

	/*
	 * Quadratic Probing is not working for below test case
	 * Max size = 11;
	 * 25 25 25 25 25 25 25 25 25 25 25 
	 */
	// Get the empty index/key to insert the data using Quadratic Probing
	public int findKey2(int data) {
		int index = data % maxSize;
		int i = 0, hashValue = index;
		while (array[index] != null) {
			// 2.Quadratic Probing: If slot hash(x) % S is full, then we try (hash(x) + 1*1) %S; where i=1,2,3..
			index = (hashValue + (i * i)) % maxSize;
			System.out.println("i: " + i + " index: " + index);
			i++;
		}
		return index;
	}

	// Get the empty index/key to insert the data using Double Hashing
	public int findKey3(int data) {
		int index = hash1(data); // First hash function is typically hash1(key) = key % TABLE_SIZE
		int i = 0, hash1 = index, indexH = hash2(data); // Second hash function is : hash2(key) = PRIME – (key % PRIME)
														// where PRIME is a prime smaller
		// than the TABLE_SIZE.
		while (array[index] != null) {
			// 3.Double Hashing: (hash1(key) + i * hash2(key)) % TABLE_SIZE) %S; where i=1,2,3..
			index = (hash1 + (i * indexH)) % maxSize;
			System.out.println("i: " + i + " index: " + index);
			i++;
		}
		return index;
	}

	// Find the data in the array (use Linear Probing)
	public int findValue1(int element) {
		int index = -1;
		index = element % maxSize; // Find the hashing value
		int hashValue = index;
		for (int i = 1; i < maxSize; i++) {
			if (array[index] != null && array[index] == element) {
				return index;
			} else {
				index = (hashValue + i) % maxSize; // Linear Probing: (hash(x) + i) % S;
			}
		}
		return -1;
	}

	// Find the data in the array (use Linear Probing)
	public int findValue2(int element) {
		int index = -1;
		index = element % maxSize; // Find the hashing value
		int hashValue = index;
		for (int i = 1; i < maxSize; i++) {
			if (array[index] != null && array[index] == element) {
				return index;
			} else {
				index = (hashValue + (i * i)) % maxSize; // Linear Probing: (hash(x) + i) % S;
			}
		}
		return -1;
	}

	// Find the data in the array (use Double Hashing)
	public int findValue3(int element) {
		int index = hash1(element); // First hash function is typically hash1(key) = key % TABLE_SIZE
		int hashValue1 = index, hashValue2 = hash2(element);
		for (int i = 1; i < maxSize; i++) {
			if (array[index] != null && array[index] == element) {
				return index;
			} else {
				// 3.Double Hashing: (hash1(key) + i * hash2(key)) % TABLE_SIZE) %S;
				index = (hashValue1 + (i * hashValue2)) % maxSize;
			}
		}
		return -1;
	}

	// Display
	public void display() {
		for (int i = 0; i < maxSize; i++) {
			System.out.print(i + " - " + array[i] + "; ");
		}
	}

	private boolean isHashFull() {
		return (currSize == maxSize);
	}

	private int hash1(int n) {
		return n % maxSize;
	}

	private int hash2(int n) {
		int PRIME_NO = 7; // hash2(key) = PRIME – (key % PRIME) where PRIME is a prime smaller than the TABLE_SIZE.
		return (PRIME_NO - n % PRIME_NO);
	}

	/************ Util Methods ******************/
	private int nextPrime(int n) {
		while (!isPrime(n)) {
			n++;
		}
		return n;
	}

	private boolean isPrime(int n) {
		int sqrt = (int) Math.sqrt((double) n);
		boolean flag = true;
		if (n == 1) {
			flag = false;
		} else if (n == 2 || n == 3) {
			flag = true;
		} else {
			for (int i = 2; i <= sqrt; i++) {
				if (n % i == 0) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		HashOpenAddressing hashTable = new HashOpenAddressing(10);
		do {
			System.out.println("HashTable Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.Find/Search");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {

				case 1:
					System.out.println("Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0) {
						hashTable.insert(in.nextInt());
					}
					System.out.println("Elements are inserted!");
					break;
				case 2:
					System.out.println("Enter element needs to be deleted:");
					hashTable.delete(in.nextInt());
					break;
				case 3:
					System.out.println("Enter elements needs to be find:");
					System.out.println("Element present at index: " + hashTable.search(in.nextInt()));
					break;
				default:
					System.out.println("Please enter the valid option!!!");
					break;
			}
			System.out.println("\nDisplay:");
			hashTable.display();
			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}
}

/* Separate Chaining:The idea is to make each cell of hash table point to a linked list of records that have same hash function value. Chaining
 * is simple, but requires additional memory outside the table.
 */
class HashSeperateChaining {

	LinkedList[]	table;
	int				hashSize;
	int				capacity;

	public HashSeperateChaining(int capacity) {
		this.capacity = capacity;
		this.table = new LinkedList[capacity];
	}

	private int hash(int n) {
		return (Integer) n % capacity;
	}

	public void insert(int t) {
		int pos = hash(t);
		if (table[pos] == null) table[pos] = new LinkedList();
		hashSize++;
		table[pos].add(t);
	}

	public void delete(int t) {
		int pos = hash(t);
		if (table[pos] != null) {
			if (table[pos].remove(t)) hashSize--;
			else System.out.println("Data not found!");
		} else {
			System.out.println("Data not found!");
		}
	}

	public boolean search(int t) {
		int pos = hash(t);
		boolean flag = false;
		if (table[pos] != null) {
			if (table[pos].contains(t)) flag = true;
		}
		return flag;
	}

	public void display() {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				System.out.println("\ni=" + i + "->");
				table[i].display();
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		HashSeperateChaining hashTable = new HashSeperateChaining(10);
		do {
			System.out.println("HashTable Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.Find/Search");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {

				case 1:
					System.out.println("Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0) {
						hashTable.insert(in.nextInt());
					}
					System.out.println("Elements are inserted!");
					break;
				case 2:
					System.out.println("Enter element needs to be deleted:");
					hashTable.delete(in.nextInt());
					break;
				case 3:
					System.out.println("Enter elements needs to be find:");
					System.out.println("Element is present in the list? " + hashTable.search(in.nextInt()));
					break;
				default:
					System.out.println("Please enter the valid option!!!");
					break;
			}
			System.out.println("\nDisplay:");
			hashTable.display();
			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();

	}

}

class MyHashSet {

	public static void main(String[] args) {
		MyHashSet set = new MyHashSet();
		set.add(1);
		set.add(2);
		System.out.println("Contains: " + set.contains(1));
		System.out.println("Contains: " + set.contains(2));
		set.remove(2);
		set.remove(3);
		System.out.println("Contains: " + set.contains(1));
		System.out.println("Contains: " + set.contains(2));
	}

	private int[]	map;
	private int		size;

	/** Initialize your data structure here. */
	public MyHashSet() {
		int size = 1000000;
		map = new int[size];
		Arrays.fill(map, -1);
	}

	public void add(int key) {
		if (!contains(key)) map[key % size] = key;
	}

	public void remove(int key) {
		if (!contains(key)) map[key % size] = -1;
	}

	/** Returns true if this set contains the specified element */
	public boolean contains(int key) {
		return map[key % size] == -1 ? false : true;
	}

}
