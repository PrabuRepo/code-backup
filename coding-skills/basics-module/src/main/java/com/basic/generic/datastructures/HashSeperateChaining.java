package com.basic.generic.datastructures;

import java.util.Scanner;

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
public class HashSeperateChaining<T extends Comparable<T>> {

	LinkedList<T>[]	table;
	int				hashSize;
	int				capacity;

	public HashSeperateChaining(int capacity) {
		this.capacity = capacity;
		this.table = new LinkedList[capacity];
	}

	private int hash(T n) {
		return (Integer) n % capacity;
	}

	public void insert(T t) {
		int pos = hash(t);
		if (table[pos] == null)
			table[pos] = new LinkedList<>();
		hashSize++;
		table[pos].insert(t);
	}

	public void delete(T t) {
		int pos = hash(t);
		if (table[pos] != null) {
			if (table[pos].delete(t))
				hashSize--;
			else
				System.out.println("Data not found!");
		} else {
			System.out.println("Data not found!");
		}
	}

	public boolean search(T t) {
		int pos = hash(t);
		boolean flag = false;
		if (table[pos] != null) {
			if (table[pos].find(t))
				flag = true;
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
		HashSeperateChaining<Integer> hashTable = new HashSeperateChaining<>(10);
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
