package com.practice.collection.datastructures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void testHashMap() {
		Map<String, Integer> hashMap = new HashMap<>();
		hashMap.put("wewe", 14);
		hashMap.put("vfdd", 28);
		hashMap.put("bggdd", 73);
		hashMap.put("qvbvb", 6);

		Set<String> set = hashMap.keySet();
		for (String key : set)
			System.out.println("Key:" + key + " Value:" + hashMap.get(key));

	}

	public void testMapIterations() {

		Map<String, Integer> hashMap = new HashMap<>();
		hashMap.put("wewe", 14);
		hashMap.put("vfdd", 28);
		hashMap.put("bggdd", 73);
		hashMap.put("qvbvb", 6);

		Map<String, Integer> treeMap = new TreeMap<String, Integer>();
		treeMap.putAll(hashMap);

		System.out.println("=====HASH MAP=========");
		// Display the HashMap using the keyset()
		System.out.println("***Display the HashMap using the keyset()****");
		Set<String> setHash = hashMap.keySet();
		for (String str : setHash) {
			System.out.println("Key:" + str + "->Value:" + hashMap.get(str));
		}

		// Display the HashMap using keyset() & iterator()
		System.out.println("***Display the HashMap using the keyset() & iterator()****");
		Iterator<String> iterHash = hashMap.keySet().iterator();
		while (iterHash.hasNext()) {
			String Key = iterHash.next();
			System.out.println("Key:" + Key + "->Value:" + hashMap.get(Key));
		}

		// Display the HashMap using the direct entrySet()
		System.out.println("***Display the HashMap using the direct entrySet()****");
		System.out.println("HashMap:" + hashMap.entrySet());

		// Display the HashMap by iterating the entryset
		System.out.println("***Display the HashMap by iterating the entryset****");
		for (Map.Entry<String, Integer> entry : hashMap.entrySet())
			System.out.println(entry.getKey() + ": " + entry.getValue());

		System.out.println("***Display the HashMap using the entrySet() & iterator()****");
		Iterator<Entry<String, Integer>> iterHash2 = hashMap.entrySet().iterator();
		while (iterHash2.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iterHash2.next();
			System.out.println("Key:" + entry.getKey() + "->Value:" + entry.getValue());
		}

		System.out.println("Print map using lambda expression:");
		hashMap.forEach((k, v) -> System.out.println("Key:" + k + "/Value:" + v));
	}

	public void testLinkedHashMap() {
		Map<Integer, String> map = new LinkedHashMap<>();
	}

	/*
	 *  A concurrentHashMap is a thread-safe implementation of Map interface. In this class put and remove methods are synchronized 
	 *  but not get method. This class is different from Hashtable in terms of locking; it means that hashtable use object level 
	 *  lock but this class uses bucket level lock thus having better performance. The allowed concurrency among update operations
	 *  is guided by the optional concurrencyLevel constructor argument (default 16), which is used as a hint for internal sizing.
	 */
	public void testConcurrentHashMap() {
		Map<Integer, String> map = new ConcurrentHashMap<>();
	}

	public void testTreeMap() {
		Map<Integer, String> map = new ConcurrentHashMap<>();
	}

}
