package com.basic.datastructures.java.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

/* Java Collection API Practice: 
 * Ref: https://www.geeksforgeeks.org/java-collection-tutorial/
 */
public class CollectionAPI {

	public static void main(String[] args) {
		CollectionAPI ob = new CollectionAPI();

		ob.testCollectionAPIs();

		System.out.println("TreeSet Asc Order -> ");
		ob.treeSetAllAPIs(ob.mockTreeSet1(new TreeSet<>()));

		System.out.println("TreeSet Desc Order -> ");
		ob.treeSetAllAPIs(ob.mockTreeSet1(new TreeSet<>(Collections.reverseOrder())));

		System.out.println("TreeMap Asc Order -> ");
		ob.treeMapAllAPIs(ob.mockTreeMap(new TreeMap<>()));

		System.out.println("TreeMap Desc Order -> ");
		ob.treeMapAllAPIs(ob.mockTreeMap(new TreeMap<>(Collections.reverseOrder())));
	}

	public void testCollectionAPIs() {

		Scanner in = new Scanner(System.in);
		char ch;
		do {
			System.out.println("******Java Collection API****");
			System.out.println("--List Types--");
			System.out.println("1.ArrayList");
			System.out.println("2.LinkedList");
			System.out.println("3.Vector");
			System.out.println("4.Stack");
			System.out.println("5.CopyOnWriteArrayList");
			System.out.println("--Queue Types--");
			System.out.println("11.Queue");
			System.out.println("12.Priority Queue");
			System.out.println("13.Deque");
			System.out.println("--Set Types--");
			System.out.println("21.HashSet");
			System.out.println("22.LinketHashSet");
			System.out.println("23.TreeSet");
			System.out.println("--Map Types--");
			System.out.println("31.HashMap");
			System.out.println("32.LinkedHashMap");
			System.out.println("33.HashTable");
			System.out.println("34.TreeMap");
			System.out.print("Enter option:");
			switch (in.nextInt()) {
			case 1:
				System.out.println("***ArrayList***");
				arrayListAPI();
				break;
			case 2:
				System.out.println("***LinkedList***");
				linkedListAPI();
				break;
			case 3:
				System.out.println("***Vector***");
				vectorAPI();
				break;
			case 4:
				System.out.println("***Stack***");
				stackAPI();
				break;
			case 11:
				System.out.println("***Queue***");
				queueAPI();
				break;
			case 12:
				System.out.println("***Priority Queue***");
				priorityQueueAPI();
				break;
			case 13:
				System.out.println("***Deque***");
				dequeAPI();
				break;
			case 21:
				System.out.println("***HashSet***");
				hashSetAPI();
				break;
			case 22:
				System.out.println("***Linked HashSet***");
				linkedHashSetAPI();
				break;
			case 23:
				System.out.println("***Tree Set***");
				treeSetAPI();
				break;
			case 31:
				System.out.println("***Hash Map***");
				hashMapAPI();
				break;
			case 32:
				System.out.println("***Linked Map***");
				linkedHashMapAPI();
				break;
			case 33:
				System.out.println("***Hash Table***");
				hashTableAPI();
				break;
			case 34:
				System.out.println("***Tree Map***");
				treeMapAPI();
				break;
			default:
				System.out.println("Please enter the valid option!!!");
				break;
			}

			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();

	}

	public void arrayListAPI() {
		List<Integer> list = new ArrayList<>();

		// Insert
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		// Display
		listDisplay(list);

		// Update
		list.set(3, 6);
		list.set(4, 7);

		// Display
		listDisplay(list);

		// Remove
		System.out.println("Remove based on Index:" + list.remove(1));
		// Get an element based on Index
		Integer element = list.get(3);
		// Remove
		System.out.println("Remove based on value(object):" + list.remove(element));

		// Display
		listDisplay(list);

		// Search
		System.out.println("Index of an element:" + list.indexOf(3));
		System.out.println("Check list conatains an element:" + list.contains(5));
	}

	public void linkedListAPI() {

		List<Integer> list = new LinkedList<>();

		// Insert
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		// Display
		listDisplay(list);

		// Remove
		System.out.println("Remove based on Index:" + list.remove(1));
		// Get an element based on Index
		Integer element = list.get(3);
		// Remove
		System.out.println("Remove based on value(object):" + list.remove(element));

		// Display
		listDisplay(list);

		// Search
		System.out.println("Index of an element:" + list.indexOf(3));
		System.out.println("Check list conatains an element:" + list.contains(5));
	}

	public void vectorAPI() {

		List<Integer> list = new Vector<>();

		// Insert
		list.add(4);
		list.add(3);
		list.add(5);
		list.add(2);
		list.add(1);

		// Display
		listDisplay(list);

		// Remove
		System.out.println("Remove based on Index:" + list.remove(1));
		// Get an element based on Index
		Integer element = list.get(3);
		// Remove
		System.out.println("Remove based on value(object):" + list.remove(element));

		// Display
		listDisplay(list);

		// Search
		System.out.println("Index of an element:" + list.indexOf(3));
		System.out.println("Check list conatains an element:" + list.contains(5));

	}

	public void stackAPI() {
		Stack<Integer> stack = new Stack<>();

		// Push
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);

		// Display
		listDisplay(stack);

		// Pop
		System.out.println("Popped element from the stack:" + stack.pop());

		// isEmpty
		System.out.println("Is stack Empty:" + stack.isEmpty());

		// Display
		listDisplay(stack);

		// Peek or Top
		System.out.println("Remove based on value(object):" + stack.peek());

		// Search
		System.out.println("Search an element in the stack:" + stack.search(stack.get(3)));
	}

	public void queueAPI() {
		Queue<Integer> queue = new LinkedList<>();

		System.out.println("Queue operation when its empty: ");
		System.out.println("Peek: " + queue.peek());
		System.out.println("Pop: " + queue.poll());

		try {
			System.out.println("Element: " + queue.element());
		} catch (Exception e) {
			System.out.println("Element: " + e.toString());
		}
		try {
			System.out.println("remove: " + queue.remove());
		} catch (Exception e) {
			System.out.println("remove: " + e.toString());
		}
		// Enqueue or Insert
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);

		// Display
		iteratorDisplay(queue.iterator());

		// Peek or Top
		System.out.println("Front element in the Queue(peek):" + queue.peek());
		System.out.println("Front element in the Queue(element):" + queue.element());

		// Dequeue
		System.out.println("Dequeue element from Queue(poll):" + queue.poll());
		iteratorDisplay(queue.iterator());
		System.out.println("Dequeue element from Queue(remove):" + queue.remove());
		iteratorDisplay(queue.iterator());

	}

	public void priorityQueueAPI() {

		PriorityQueue<Integer> queue = new PriorityQueue<>();

		// Enqueue or Insert
		queue.add(9);
		queue.add(12);
		queue.add(33);
		queue.add(4);
		queue.add(2);

		queue.offer(13); // Add

		// Display
		iteratorDisplay(queue.iterator());

		// Peek or Top
		System.out.println("Front element in the Queue:" + queue.peek());

		// Dequeue
		System.out.println("Dequeue element from Queue:" + queue.poll());

		// Display
		iteratorDisplay(queue.iterator());

		// Remove
		System.out.println("Remove element from Queue:" + queue.remove());
		System.out.println("Remove the given element: " + queue.remove(12));

		// Display
		iteratorDisplay(queue.iterator());

	}

	public void priorityQueueAPI2() {

		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

		// Enqueue or Insert
		queue.add(9);
		queue.add(12);
		queue.add(33);
		queue.add(4);
		queue.add(2);

		queue.offer(13); // Add

		// Display
		iteratorDisplay(queue.iterator());

		// Peek or Top
		System.out.println("Front element in the Queue:" + queue.peek());

		// Dequeue
		System.out.println("Dequeue element from Queue:" + queue.poll());

		// Display
		iteratorDisplay(queue.iterator());

		// Remove
		System.out.println("Remove element from Queue:" + queue.remove());

		// Display
		iteratorDisplay(queue.iterator());

	}

	/* Deque can act as both stack(push/pop) & queue(enqueue/deque)
	 * A linear collection that supports element insertion and removal at both ends. The name deque is short for 
	 * "double ended queue" and is usually pronounced "deck". Most Deque implementations place no fixed limits on the number of 
	 * elements they may contain, but this interface supports capacity-restricted deques as well as those with no fixed size limit.
	 */
	public void dequeAPI() {
		Deque<Integer> deque = new LinkedList<>();

		System.out.println("***********Deque Front/Head/First side operations************");
		System.out.println("1.Insert::");
		deque.push(10);
		deque.push(20);
		System.out.println("After push operation:");
		iteratorDisplay(deque.iterator());

		deque.offerFirst(80);
		deque.offerFirst(90);
		System.out.println("After offerFirst operation:");
		iteratorDisplay(deque.iterator());

		deque.addFirst(30);
		deque.addFirst(40);
		System.out.println("After addFirst operation:");
		iteratorDisplay(deque.iterator());

		System.out.println("2.Display::");
		System.out.println("getFirst operation:" + deque.getFirst());
		System.out.println("peek operation:" + deque.peek());
		System.out.println("element operation:" + deque.element());
		System.out.println("peekFirst operation:" + deque.peekFirst());

		System.out.println("3.Delete::");
		iteratorDisplay(deque.iterator());
		System.out.println("remove operation:" + deque.remove());
		iteratorDisplay(deque.iterator());
		System.out.println("removeFirst operation:" + deque.removeFirst());
		iteratorDisplay(deque.iterator());
		System.out.println("pop operation:" + deque.pop());
		iteratorDisplay(deque.iterator());
		System.out.println("poll operation:" + deque.poll());
		iteratorDisplay(deque.iterator());
		System.out.println("pollFirst operation:" + deque.pollFirst());
		iteratorDisplay(deque.iterator());

		System.out.println("***********Deque Rear/Tail/Last side operations***************");
		System.out.println("1.Insert::");
		deque.offer(50);
		deque.offer(60);
		System.out.println("After offer operation:");
		iteratorDisplay(deque.iterator());

		deque.offerLast(25);
		deque.offerLast(37);
		System.out.println("After offerLast operation:");
		iteratorDisplay(deque.iterator());

		deque.add(15);
		deque.add(17);
		System.out.println("After add operation:");
		iteratorDisplay(deque.iterator());

		deque.addLast(5);
		deque.addLast(7);
		System.out.println("After addLast operation:");
		iteratorDisplay(deque.iterator());

		System.out.println("2.Display::");
		System.out.println("getLast operation:" + deque.getLast());
		System.out.println("peekLast operation:" + deque.peekLast());

		System.out.println("3.Delete::");
		iteratorDisplay(deque.iterator());
		System.out.println("removeLast operation:" + deque.removeLast());
		iteratorDisplay(deque.iterator());
		System.out.println("pollLast operation:" + deque.pollLast());
		iteratorDisplay(deque.iterator());
	}

	public void hashSetAPI() {
		Set<Integer> set = new HashSet<>();
		// Insert
		set.add(5);
		set.add(4);
		set.add(3);
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(6);

		// Display
		iteratorDisplay(set.iterator());

		// Remove
		set.remove(4);

		System.out.println("After removing elements:");
		iteratorDisplay(set.iterator());

	}

	/**
	 * Hash table and linked list implementation of the Set interface, with predictable iteration order. This
	 * implementation differs from HashSet in that it maintains a doubly-linked list running through all of its entries.
	 * This linked list defines the iteration ordering, which is the order in which elements were inserted into the set
	 * insertion-order.
	 */
	public void linkedHashSetAPI() {

		Set<Integer> set = new LinkedHashSet<>();
		// Insert
		set.add(5);
		set.add(4);
		set.add(3);
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(6);

		// Display
		iteratorDisplay(set.iterator());

		// Remove
		set.remove(4);

		System.out.println("After removing elements:");
		iteratorDisplay(set.iterator());
	}

	public void treeSetAPI() {

		Set<Integer> set = new TreeSet<>();
		// Insert
		set.add(15);
		set.add(42);
		set.add(23);
		set.add(11);
		set.add(23);
		set.add(8);
		set.add(36);

		// Display
		iteratorDisplay(set.iterator());

		// Remove
		set.remove(11);

		System.out.println("After removing elements:");
		iteratorDisplay(set.iterator());

	}

	public void hashMapAPI() {
		Map<Integer, String> map = new HashMap<>();

		// Insert the element
		map.put(5, "aaaa");
		map.put(3, "wuek");
		map.put(7, "eiii");
		map.put(2, "iwoe");
		map.put(9, "fdhd");
		map.put(null, null);
		map.put(10, null);
		// It adds only if key doesnt present
		map.putIfAbsent(7, "add new");
		map.putIfAbsent(8, "add new");

		System.out.println("Get: " + map.get(5));
		System.out.println("Get of Default: " + map.getOrDefault(4, "Null"));
		System.out.println("Contains Key: " + map.containsKey(9));
		System.out.println("Contains Value: " + map.containsValue("aaaa"));
		System.out.println("Entry Set: " + map.entrySet());
		System.out.println("Key Set: " + map.keySet()); // To get all the keys
		System.out.println("Map Values: " + map.values()); // To get all the values

		// Display
		mapDisplay(map);

		// Remove
		map.remove(3);

		// Display
		System.out.println("Remove the element from Map");
		mapDisplay(map);
	}

	public void linkedHashMapAPI() {
		Map<Integer, String> map = new LinkedHashMap<>();

		// Insert the element
		map.put(5, "aaaa");
		map.put(3, "wuek");
		map.put(7, "eiii");
		map.put(2, "iwoe");
		map.put(9, "fdhd");
		map.put(null, null);
		map.put(10, null);
		// It adds only if key doesnt present
		map.putIfAbsent(7, "add new");
		map.putIfAbsent(8, "add new");

		// Display
		mapDisplay(map);

		// Remove
		map.remove(3);

		// Display
		System.out.println("Remove the element from Map");
		mapDisplay(map);
	}

	public void hashTableAPI() {
		Hashtable<Integer, String> map = new Hashtable<>();

		// Insert the element
		map.put(5, "aaaa");
		map.put(3, "wuek");
		map.put(7, "eiii");
		map.put(2, "iwoe");
		map.put(9, "fdhd");
		// map.put(null, null); //It throws null pointer exception
		// map.put(10, null);
		// It adds only if key doesnt present
		map.putIfAbsent(7, "add new");
		map.putIfAbsent(8, "add new");

		// Display
		mapDisplay(map);

		// Remove
		map.remove(3);

		// Display
		System.out.println("Remove the element from Map");
		mapDisplay(map);
	}

	public void treeMapAPI() {
		Map<Integer, String> map = new TreeMap<>();

		// Insert the element
		map.put(5, "aaaa");
		map.put(3, "wuek");
		map.put(7, "eiii");
		map.put(2, "iwoe");
		map.put(9, "fdhd");
		// map.put(null, null); // It throws null pointer exception
		map.put(10, null);
		// It adds only if key doesnt present
		map.putIfAbsent(7, "add new");
		map.putIfAbsent(8, "add new");

		// Display
		mapDisplay(map);

		// Remove
		map.remove(3);

		// Display
		System.out.println("Remove the element from Map");
		mapDisplay(map);

	}

	void iteratorDisplay(Iterator<Integer> iterator) {
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.println();
	}

	public void listDisplay(List<Integer> list) {
		ListIterator<Integer> iter = list.listIterator();
		// list.forEach(System.out::print);
		while (iter.hasNext())
			System.out.print(iter.next() + " ");
		System.out.println();
	}

	void mapDisplay(Map<Integer, String> map) {
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
	}

	public int[] getMode(int[] A, int[][] B) {
		List<Integer> list = new ArrayList<>();

		for (int[] arr : B) {
			A[arr[0]] = arr[1];
			list.add(mostFreqElement(A));
		}

		// Convert List to array:
		int[] result = new int[list.size()];
		int i = 0;
		for (int val : list)
			result[i++] = val;

		return result;
	}

	int mostFreqElement(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int val : arr) {
			if (map.get(val) == null)
				map.put(val, 0);
			map.put(val, map.get(val) + 1);
		}

		int count = 0, result = -1;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > count || (entry.getValue() == count && result < entry.getKey())) {
				count = entry.getValue();
				result = entry.getKey();
			}
		}

		return result;
	}

	public void testMapIterations() {
		Map<String, Integer> map = new HashMap<>();
		map.put("wewe", 14);
		map.put("vfdd", 28);
		map.put("bggdd", 73);
		map.put("qvbvb", 6);

		Map<String, Integer> treeMap = new TreeMap<String, Integer>();
		treeMap.putAll(map);

		System.out.println("=====HASH MAP=========");
		// Display the map using the keyset()
		System.out.println("***Display the map using the keyset()****");
		Set<String> setHash = map.keySet();
		for (String str : setHash) {
			System.out.println("Key:" + str + "->Value:" + map.get(str));
		}

		// Display the map using keyset() & iterator()
		System.out.println("***Display the map using the keyset() & iterator()****");
		Iterator<String> iterHash = map.keySet().iterator();
		while (iterHash.hasNext()) {
			String Key = iterHash.next();
			System.out.println("Key:" + Key + "->Value:" + map.get(Key));
		}

		// Display the map using the direct entrySet()
		System.out.println("***Display the map using the direct entrySet()****");
		System.out.println("map:" + map.entrySet());

		// Display the map by iterating the entryset
		System.out.println("***Display the map by iterating the entryset****");
		for (Map.Entry<String, Integer> entry : map.entrySet())
			System.out.println(entry.getKey() + ": " + entry.getValue());

		System.out.println("***Display the map using the entrySet() & iterator()****");
		Iterator<Entry<String, Integer>> iterHash2 = map.entrySet().iterator();
		while (iterHash2.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iterHash2.next();
			System.out.println("Key:" + entry.getKey() + "->Value:" + entry.getValue());
		}

		System.out.println("Print map using lambda expression:");
		map.forEach((k, v) -> System.out.println("Key:" + k + "/Value:" + v));
	}

	public void treeSetAllAPIs(TreeSet<Integer> set) {
		// First & Last values of the set
		System.out.println("First: " + set.first());
		System.out.println("Last: " + set.last());

		System.out.println("Poll First: " + set.pollFirst());
		System.out.println("Poll Last: " + set.pollLast());

		// Ceiling & Floor Value - Return exact value if present or nearest values:
		System.out.println("Ceiling & Floor Value: ");
		System.out.println("1.Mid Element in the array: ");
		System.out.println("Floor Value: " + set.floor(15));
		System.out.println("Ceiling Value: " + set.ceiling(15));
		System.out.println("2.First & Last Element in the array: ");
		System.out.println("Floor Value: " + set.floor(8));
		System.out.println("Ceiling Value: " + set.ceiling(42));

		// Higher & Lower Value - Return nearest values:
		System.out.println("Higher & Lower Value: ");
		System.out.println("1.Mid Element in the array: ");
		System.out.println("Lower Value: " + set.lower(15));
		System.out.println("Higher Value: " + set.higher(15));
		System.out.println("2.First & Last Element in the array: ");
		System.out.println("Lower Value: " + set.lower(8));
		System.out.println("Higher Value: " + set.higher(42));

		/* Lower Vs Floor:*/
		// Find the highest number which is lower than 25
		System.out.println(set.lower(25));// 5

		// Find the highest number which is lower than or equal to 25
		System.out.println(set.floor(25));// 25

		/* Higher Vs Ceiling*/
		// Find the lowest number higher than 25
		System.out.println(set.higher(25));// 35

		// Find the lowest number higher than or equal to 25
		System.out.println(set.ceiling(25));// 25

	}

	public void treeMapAllAPIs(TreeMap<String, Integer> treeMap) {

		// Displaying the TreeMap
		System.out.println("Initial Mappings are: " + treeMap);

		System.out.println("First Entry: " + treeMap.firstEntry());
		System.out.println("First Key: " + treeMap.firstKey());

		System.out.println("Last Entry: " + treeMap.lastEntry());
		System.out.println("First Key: " + treeMap.lastKey());

		System.out.println("Poll First Entry: " + treeMap.pollFirstEntry());
		System.out.println("Poll First Entry: " + treeMap.pollLastEntry());

		System.out.println("Floor Entry: " + treeMap.floorEntry("Geeks"));
		System.out.println("Floor Key: " + treeMap.floorKey("Geeks"));

		System.out.println("Ceiling Entry: " + treeMap.ceilingEntry("Geeks"));
		System.out.println("Ceiling Key: " + treeMap.ceilingKey("Geeks"));

		System.out.println("Lower Entry: " + treeMap.lowerEntry("Welcomes"));
		System.out.println("Lower Key: " + treeMap.lowerKey("Welcomes"));

		System.out.println("Higher Entry: " + treeMap.higherEntry("Welcomes"));
		System.out.println("Higher Key: " + treeMap.higherKey("Welcomes"));

		/* LowerKey Vs Floor & HigherKey Vs Ceiling */
		TreeMap<Integer, Integer> treeMap2 = new TreeMap<>();
		// Find the highest key which is lower than 25
		System.out.println(treeMap2.lowerKey(25));// 5

		// Find the highest key which is lower than or equal to 25
		System.out.println(treeMap2.floorKey(25));// 25

		// Find the lowest key higher than 25
		System.out.println(treeMap2.higherKey(25));// 35

		// Find the lowest key higher than or equal to 25
		System.out.println(treeMap2.ceilingKey(25));// 25

	}

	public TreeSet<Integer> mockTreeSet1(TreeSet<Integer> set) {
		set.add(15);
		set.add(42);
		set.add(23);
		set.add(11);
		set.add(23);
		set.add(8);
		set.add(36);
		return set;
	}

	public TreeMap<String, Integer> mockTreeMap(TreeMap<String, Integer> treeMap) {
		// new TreeMap<String, Integer>();
		treeMap.put("Geeks", 10);
		treeMap.put("Abc", 15);
		treeMap.put("Def", 20);
		treeMap.put("Welcomes", 25);
		treeMap.put("You", 30);

		return treeMap;
	}

	public void collectionsAPI(List<Integer> list, List<Integer>[] arrList) {
		Collections.sort(list);

		/*
		Collections.addAll(c, elements);
		
		Collections.binarySearch(list, key, c)
		
		Collections.copy(dest, src);
		
		Collections.disjoint(c1, c2);
		
		Collections.fill(list, obj);
		
		Collections.max(coll)
		
		Collections.min(coll)
		
		Collections.reverseOrder();
		
		Collections.reverse(list);
		*/}
}

/*DEQUE
 *   - Front side operation is same as stack operation(push/pop) 
 *   - Rear side operations is same as queue operation(enqueue/deque) 
 */
class DequeJavaAPI {

	public void insertFront(Deque<String> deque) {
		deque.offerFirst("Element1(Head)");
		deque.offerFirst("Element2(Head)");
		deque.offerFirst("Element3(Head)");
		deque.offerFirst("Element4(Head)");
	}

	public void insertRear(Deque<String> deque) {
		deque.offerLast("Element1(Tail)");
		deque.offerLast("Element2(Tail)");
		deque.offerLast("Element3(Tail)");
		deque.offerLast("Element4(Tail)");
	}

	public void deleteFront(Deque<String> deque) {
		System.out.println("Remove Front: " + deque.pollFirst());
		System.out.println("Remove Front: " + deque.pollFirst());
		System.out.println("Remove Front: " + deque.pollFirst());
	}

	public void deleteRear(Deque<String> deque) {
		System.out.println("Remove Rear: " + deque.pollLast());
		System.out.println("Remove Rear: " + deque.pollLast());
		System.out.println("Remove Rear: " + deque.pollLast());
	}

	// DeleteFront removes the data from rear after crossing front max
	public void deleteFrontAll(Deque<String> deque) {
		System.out.println("Remove Front: " + deque.pollFirst());
		System.out.println("Remove Front: " + deque.pollFirst());
		System.out.println("Remove Front: " + deque.pollFirst());
		System.out.println("Remove Front: " + deque.pollFirst());
		System.out.println("Remove Front: " + deque.pollFirst());
		System.out.println("Remove Front: " + deque.pollFirst());
	}

	// DeleteRear removes the data from front after crossing rear max
	public void deleteRearAll(Deque<String> deque) {
		System.out.println("Remove Rear: " + deque.pollLast());
		System.out.println("Remove Rear: " + deque.pollLast());
		System.out.println("Remove Rear: " + deque.pollLast());
		System.out.println("Remove Rear: " + deque.pollLast());
		System.out.println("Remove Rear: " + deque.pollLast());
		System.out.println("Remove Rear: " + deque.pollLast());
	}

	public static void main(String[] args) {
		DequeJavaAPI ob = new DequeJavaAPI();
		Deque<String> deque = new LinkedList<>();

		// Insert Front:
		ob.insertFront(deque);

		// Insert Rear
		ob.insertRear(deque);

		// Print all the elements
		System.out.println("Elements:" + deque);

		// Peek Elements:
		System.out.println("Peek element in the Front: " + deque.peekFirst());
		System.out.println("Peek element in the Rear: " + deque.peekLast());

		// Delete front
		// ob.deleteFront(deque);

		// Delete Rear
		ob.deleteRear(deque);

		// Print all the elements after deletion
		System.out.println("Elements:" + deque);

	}
}

class PriorityQueueAPI {
	public static void main(String args[]) {
		// Creating empty priority queue
		java.util.PriorityQueue<String> pQueue = new java.util.PriorityQueue<>();

		// Adding items to the pQueue
		pQueue.add("Java");
		pQueue.add("JavaScript");
		pQueue.add("Python");
		pQueue.add("C++");
		pQueue.add("C");

		// Printing all elements
		System.out.println("The queue elements:");
		Iterator itr = pQueue.iterator();
		while (itr.hasNext())
			System.out.print(itr.next() + ", ");

		// Printing the most priority element
		System.out.println("\nHead value using peek function:" + pQueue.peek());

		// Removing the top priority element (or head) and
		// printing the modified pQueue
		System.out.println("After removing an element with poll function: " + pQueue.poll());
		Iterator<String> itr2 = pQueue.iterator();
		while (itr2.hasNext())
			System.out.print(itr2.next() + ", ");
		System.out.println("\nAfter removing an element with poll function: " + pQueue.poll());
		Iterator<String> itr3 = pQueue.iterator();
		while (itr3.hasNext())
			System.out.print(itr3.next() + ", ");

		/*	
		
			// Removing Java
			pQueue.remove("Java");
			System.out.println("after removing Java with" + " remove function:");
			Iterator<String> itr3 = pQueue.iterator();
			while (itr3.hasNext())
				System.out.println(itr3.next());
		
			// Check if an element is present
			boolean b = pQueue.contains("C");
			System.out.println("Priority queue contains C" + "ot not?: " + b);
		
			// get objects from the queue in an array and
			// print the array
			Object[] arr = pQueue.toArray();
			System.out.println("Value in array: ");
			for (int i = 0; i < arr.length; i++)
				System.out.println("Value: " + arr[i].toString());*/
	}
}