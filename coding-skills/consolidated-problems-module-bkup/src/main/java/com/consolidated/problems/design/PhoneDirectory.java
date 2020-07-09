package com.consolidated.problems.design;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Design a Phone Directory which supports the following operations: 
 *     get: Provide a number which is not assigned to anyone. 
 *     check: Check if a number is available or not. 
 *     release: Recycle or release a number.
 */
public class PhoneDirectory {
	public static void main(String[] args) {
		System.out.println("Approach1: Design a Phone  Directory: ");
		PhoneDirectory1 ob1 = new PhoneDirectory1(3);
		System.out.print(ob1.get() + " ");
		System.out.print(ob1.get() + " ");
		System.out.print("\n" + ob1.check(1) + " ");
		System.out.print(ob1.check(2) + " ");
		ob1.release(1);
		System.out.println(ob1.check(1) + " ");
		System.out.print(ob1.get() + " ");
		System.out.print(ob1.get() + " ");

		System.out.println("\n\nApproach2: Design a Phone  Directory: ");
		PhoneDirectory2 ob2 = new PhoneDirectory2(3);
		System.out.print(ob2.get() + " ");
		System.out.print(ob2.get() + " ");
		System.out.print("\n" + ob2.check(1) + " ");
		System.out.print(ob2.check(2) + " ");
		ob2.release(1);
		System.out.println(ob2.check(1) + " ");
		System.out.print(ob2.get() + " ");
		System.out.print(ob2.get() + " ");
	}
}

// Approach1: method of get() is O(n), check O(1) release O(1)
class PhoneDirectory1 {
	/**
	 * Initialize your data structure here
	 * 
	 * @param maxNumbers
	 *            * - The maximum numbers that can be stored in the phone directory.
	 */
	boolean[]	bitSet;
	int			smallestFreeIndex;

	public PhoneDirectory1(int maxNumbers) {
		this.bitSet = new boolean[maxNumbers];
		this.smallestFreeIndex = 0;
	}

	/**
	 * Provide a number which is not assigned to anyone.
	 * 
	 * @return - Return an available number. Return -1 if none is available.
	 */
	public int get() {
		if (smallestFreeIndex == bitSet.length) return -1;

		int num = smallestFreeIndex;
		bitSet[num] = true;

		for (int i = smallestFreeIndex + 1; i < bitSet.length; i++) {
			if (!bitSet[i]) {
				smallestFreeIndex = i;
				break;
			}
		}
		if (num == smallestFreeIndex) smallestFreeIndex = bitSet.length;

		return num;
	}

	/** Check if a number is available or not. */
	public boolean check(int number) {
		return !bitSet[number];
	}

	/** Recycle or release a number. */
	public void release(int number) {
		if (bitSet[number] = false) return;

		bitSet[number] = false;
		if (number < smallestFreeIndex) smallestFreeIndex = number;

	}
}

// Approach2: In the method that is all O(1)
class PhoneDirectory2 {
	Set<Integer>	used		= new HashSet<>();
	Queue<Integer>	available	= new LinkedList<>();
	int				maxNumbers;

	public PhoneDirectory2(int max) {
		this.maxNumbers = max;
		for (int i = 0; i < maxNumbers; i++)
			available.add(i);
	}

	public int get() {
		if (available.isEmpty()) return -1;

		int num = available.poll();
		used.add(num);
		return num;
	}

	public boolean check(int num) {
		if (num < 0 || num >= maxNumbers) return false;

		return !used.contains(num);
	}

	public void release(int num) {
		if (used.remove(num)) available.add(num);
	}
}
