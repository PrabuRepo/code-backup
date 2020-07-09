package com.consolidated.problems.design;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheJavaAPI {
	/*Several points to mention:
	In the constructor, the third boolean parameter specifies the ordering mode. If we set it to true, it 
	will be in access order. 
	By overriding removeEldestEntry in this way, we do not need to take care of it ourselves. It will automatically
	remove the least recent one when the size of map exceeds the specified capacity.
	Below is a “normal” HashMap + doubly-linked list implementation:*/

	private LinkedHashMap<Integer, Integer>	map;
	private final int						CAPACITY;

	public LRUCacheJavaAPI(int capacity) {
		CAPACITY = capacity;
		map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
			protected boolean removeEldestEntry(Map.Entry eldest) {
				return size() > CAPACITY;
			}
		};
	}

	public int get(int key) {
		return map.getOrDefault(key, -1);
	}

	public void set(int key, int value) {
		map.put(key, value);
	}

	public static void main(String[] args) {
		LRUCacheJavaAPI ob = new LRUCacheJavaAPI(4);
		ob.set(1, 10);
		ob.set(2, 20);
		ob.set(3, 30);
		ob.set(4, 40);
		ob.set(5, 40);
		ob.set(4, 40);
		ob.set(1, 10);
		System.out.println(ob.get(3));
	}

}
