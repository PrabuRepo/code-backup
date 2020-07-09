package com.consolidated.problems.design;

import java.util.HashMap;
import java.util.HashSet;

/*
 * Design a data structure that allows O(1) time complexity to insert, delete and get most frequent element.
 * 
 * Analysis: At first, a hash map seems to be good for insertion and deletion. But how to make getMostFrequent O(1)?
 *  Regular sorting algorithm takes nlogn, so we can not use that. As a result we can use a linked list to track the 
 *  maximum frequency.
 */
public class FrequentCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FrequentCollection fc = new FrequentCollection();
		fc.insert(1);
		fc.insert(2);
		fc.insert(3);
		fc.insert(2);
		fc.insert(3);
		fc.insert(3);
		fc.insert(2);
		fc.insert(1);

		System.out.println(fc.getMostFrequent());
		fc.remove(2);
		fc.remove(2);
		System.out.println(fc.getMostFrequent());

	}

	HashMap<Integer, Node>	map;
	Node					head, tail;

	/** Initialize your data structure here. */
	public FrequentCollection() {
		map = new HashMap<Integer, Node>();
	}

	/**
	 * Inserts a value to the collection.
	 */
	public void insert(int val) {
		if (map.containsKey(val)) {
			Node n = map.get(val);
			n.set.remove(val);

			if (n.next != null) {
				n.next.set.add(val); // next + 1
				map.put(val, n.next);
			} else {
				Node t = new Node(n.value + 1);
				t.set.add(val);
				n.next = t;
				t.prev = n;
				map.put(val, t);
			}

			// update head
			if (head.next != null) head = head.next;
		} else {
			if (tail == null || head == null) {
				Node n = new Node(1);
				n.set.add(val);
				map.put(val, n);

				head = n;
				tail = n;
				return;
			}

			if (tail.value > 1) {
				Node n = new Node(1);
				n.set.add(val);
				map.put(val, n);
				tail.prev = n;
				n.next = tail;
				tail = n;
			} else {
				tail.set.add(val);
				map.put(val, tail);
			}

		}

	}

	/**
	 * Removes a value from the collection.
	 */
	public void remove(int val) {
		Node n = map.get(val);
		n.set.remove(val);

		if (n.value == 1) {
			map.remove(val);
		} else {
			n.prev.set.add(val);
			map.put(val, n.prev);
		}

		while (head != null && head.set.size() == 0) {
			head = head.prev;
		}

	}

	/** Get the most frequent element from the collection. */
	public int getMostFrequent() {
		if (head == null) return -1;
		else return head.set.iterator().next();
	}
}

class Node {
	int					value;
	Node				prev;
	Node				next;
	HashSet<Integer>	set;

	public Node(int v) {
		value = v;
		set = new HashSet<Integer>();
	}

	public String toString() {
		return value + ":" + set.toString();
	}
}
