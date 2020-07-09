package com.consolidated.problems.design;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/*
 * What is a Cache?
 *   A cache is a hardware or software component that stores data so that future requests for that data can be served faster; the data 
 *   stored in a cache might be the result of an earlier computation or a copy of data stored elsewhere.
 *   
 * What Is An LRU Cache?
 *   So a LRU Cache is a storage of items so that future access to those items can be serviced quickly and an LRU policy is used 
 *   for cache eviction.
 *   The Constraints/Operations 
 *     Lookup of cache items must be O(1)
 *     Addition to the cache must be O(1)
 *  The cache should evict items using the LRU policy
 *  
 * Implementation: 
 *    There are many ways to do this but the most common approach is to use 2 critical structures: a doubly linked list and a hashtable.
 *    Doubly Linked List: This will hold the items that our cache has. We will have n items in the cache. This structure satisfies 
 *    the constraint for fast addition since any doubly linked list item can be added or removed in O(1) time with proper references.
 *    Hashtable: The hashtable will give us fast access to any item in the doubly linked list items to avoid O(n) search for items and 
 *    the LRU entry (which will always be at the tail of the doubly linked list).
 *    
 *    Complexities: Time: Bot get and put methods are O( 1 ) in time complexity. Space We use O( n ) space since we will store n items 
 *    where n is the capacity of the cache.
 */

//TODO: LRU Cache: Add the element in the front and removing from the rear

public class LRUCache {
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(1);
		cache.set(2, 1);
		System.out.println(cache.get(2));
		cache.set(3, 2);
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
	}

	private Map<Integer, CacheNode> map  = null;
	private int                     cap  = 0;
	DoublyLinkedList                list = null;

	public LRUCache(int capacity) {
		this.cap = capacity;
		this.list = new DoublyLinkedList();
		map = new HashMap<>();
	}

	public int get(int key) {
		if (!map.containsKey(key)) return -1;

		CacheNode node = map.get(key);
		list.delete(node);
		CacheNode newNode = list.addFirst(node.key,
				node.value);
		map.put(key, newNode);
		return node.value;
	}

	public void set(int key, int value) {
		if (map.containsKey(key)) {
			list.delete(map.get(key));
		} else if (map.size() >= cap) {
			CacheNode rear = list.getRear();
			map.remove(rear.key);
			list.delete(rear);
		}

		CacheNode node = list.addFirst(key, value);
		map.put(key, node);
	}

}

//LRU Cache opposite of above: Adding element in rear and removing from the front
class LRUCache2 {
	public static void main(String[] args) {
		LRUCache2 cache = new LRUCache2(1);
		cache.put(2, 1);
		System.out.println(cache.get(2));
		cache.put(3, 2);
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
	}

	private HashMap<Integer, CacheNode> map;
	private DoubleLinkedList            dll;
	private int                         capacity;

	public LRUCache2(int size) {
		this.capacity = size;
		this.map = new HashMap<>(capacity);
		this.dll = new DoubleLinkedList();
	}

	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		} else {
			CacheNode node = map.get(key);
			dll.remove(node);
			map.put(key, dll.addLast(node.key, node.value));
			return node.value;
		}
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			dll.remove(map.get(key));
		} else {
			if (map.size() >= capacity) {
				map.remove(dll.front.key);
				dll.remove(dll.front);
			}
		}
		map.put(key, dll.addLast(key, value));
	}
}

class LRUCache3 {
	// store keys of cache 
	static Deque<Integer>   dq;

	// store references of key in cache 
	static HashSet<Integer> map;

	// maximum capacity of cache 
	static int              csize;

	LRUCache3(int n) {
		dq = new LinkedList<>();
		map = new HashSet<>();
		csize = n;
	}

	/* Refers key x with in the LRU cache */
	public void add(int x) {

	}

	/* Refers key x with in the LRU cache */
	public void remove(int x) {

	}

	// display contents of cache 
	public void display() {
		Iterator<Integer> itr = dq.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
	}
}

class DoublyLinkedList {
	CacheNode front, rear;

	public CacheNode addFirst(int key, int val) {
		CacheNode newNode = new CacheNode(key, val);
		if (front == null) {
			front = rear = newNode;
		} else {
			newNode.next = front;
			front.prev = newNode;
			front = newNode;
		}
		return newNode;
	}

	public void delete(CacheNode node) {
		if (node.prev != null) node.prev.next = node.next;
		else front = node.next;
		if (node.next != null) node.next.prev = node.prev;
		else rear = node.prev;
	}

	public CacheNode getFront() {
		return front;
	}

	public CacheNode getRear() {
		return rear;
	}
}

class DoubleLinkedList {
	public CacheNode front, rear;

	public CacheNode addLast(int key, int val) {
		CacheNode newNode = new CacheNode(key,
				val);
		if (front == null) {
			front = rear = newNode;
		} else {
			rear.next = newNode;
			newNode.prev = rear;
			rear = newNode;
		}
		return newNode;
	}

	public void remove(CacheNode currNode) {
		// Update the prev ptr or head currNode
		if (currNode.prev != null)
			currNode.prev.next = currNode.next;
		else front = currNode.next;

		// Update the next ptr or rear currNode
		if (currNode.next != null)
			currNode.next.prev = currNode.prev;
		else rear = currNode.prev;
	}

}

class CacheNode {
	int              value;
	int              key;
	public CacheNode prev, next;

	public CacheNode(int key, int val) {
		this.key = key;
		this.value = val;
		prev = next = null;
	}
}
