package com.consolidated.problems.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Zigzag Iterator:
 *    Given two 1d vectors, implement an iterator to return their elements alternately. For example, given two 1d vectors:
 *    v1 = [1, 2]; v2 = [3, 4, 5, 6] 
 *    By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
 */
public class ZigZagIterator {
	public static void main(String[] args) {
		ZigZagIterator test = new ZigZagIterator();
		ZigZagIterator1 ob = new ZigZagIterator1(test.mockList1(), test.mockList2());
		System.out.println("ZigZag Iterator(Approach1): ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.println("\n" + ob.hasNext());
		System.out.print(ob.next() + " ");

		ZigZagIterator2 ob2 = new ZigZagIterator2(test.mockList1(), test.mockList2());
		System.out.println("\nZigZag Iterator(Approach2): ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.println("\n" + ob2.hasNext());
		System.out.print(ob2.next() + " ");

		ZigZagIterator3 ob3 = new ZigZagIterator3(test.mockList1(), test.mockList2());
		System.out.println("\nZigZag Iterator(Approach3): ");
		System.out.print(ob3.next() + " ");
		System.out.print(ob3.next() + " ");
		System.out.print(ob3.next() + " ");
		System.out.print(ob3.next() + " ");
		System.out.print(ob3.next() + " ");
		System.out.print(ob3.next() + " ");
		System.out.print(ob3.next() + " ");
		System.out.println("\n" + ob3.hasNext());
		System.out.print(ob3.next() + " ");
	}

	public List<Integer> mockList1() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(9);
		return list1;
	}

	public List<Integer> mockList2() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(6);
		return list1;
	}
}

/* Approach1: Brute Force Approach, Without using any Java API
 */
class ZigZagIterator1 {
	List<Integer>	list1, list2;
	int				index;
	boolean			flag;

	public ZigZagIterator1(List<Integer> l1, List<Integer> l2) {
		this.list1 = l1;
		this.list2 = l2;
		flag = true;
		index = 0;
	}

	public int next() {
		if (!hasNext()) return -1;

		int next = 0;
		if (flag && index < list1.size()) {
			next = list1.get(index);
			if (index < list2.size()) flag = !flag;
			else index++;
		} else {
			next = list2.get(index);
			index++;
			if (index < list1.size()) flag = !flag;
		}
		return next;
	}

	public boolean hasNext() {
		return index < list1.size() || index < list2.size();
	}
}

/* Approach2: 
 *   Uses Java Iterator
 */
class ZigZagIterator2 {
	Iterator<Integer>	iter1, iter2;
	int					count	= 0;

	public ZigZagIterator2(List<Integer> list1, List<Integer> list2) {
		this.iter1 = list1.iterator();
		this.iter2 = list2.iterator();
		this.count = 0;
	}

	public int next() {
		if (!hasNext()) return -1;

		count++;
		if ((count % 2 == 1 && iter1.hasNext()) || !iter2.hasNext()) return iter1.next();
		else return iter2.next();
	}

	public boolean hasNext() {
		return iter1.hasNext() || iter2.hasNext();
	}
}

/* Approach3: 
 * Uses a Queue or linkedlist to store the iterators in different vectors. Every time we call next(), we pop an element
 * from the list, and re-add it to the end to cycle through the lists.
 */
class ZigZagIterator3 {
	Queue<Iterator<Integer>> queue;

	public ZigZagIterator3(List<Integer> v1, List<Integer> v2) {
		queue = new LinkedList<Iterator<Integer>>();
		if (!v1.isEmpty()) queue.add(v1.iterator());
		if (!v2.isEmpty()) queue.add(v2.iterator());
	}

	public int next() {
		if (!hasNext()) return -1;

		Iterator<Integer> poll = queue.remove();
		int result = (Integer) poll.next();
		if (poll.hasNext()) queue.add(poll);
		return result;
	}

	public boolean hasNext() {
		return !queue.isEmpty();
	}
}