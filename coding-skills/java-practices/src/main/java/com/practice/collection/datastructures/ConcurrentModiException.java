package com.practice.collection.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModiException {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		List<Integer> copylist = new CopyOnWriteArrayList<Integer>();
		/*		copylist.add(1);
				copylist.add(2);
				copylist.add(3);
				copylist.add(4);
		*/
		copylist.addAll(list);
		System.out.println("\n==>CopyOnWriteArrayList Itertor:");
		Iterator<Integer> copyWriteListIterator = copylist.iterator();
		System.out.println("\n==> Iterator:");
		while (copyWriteListIterator.hasNext()) {
			Integer data = copyWriteListIterator.next();
			copylist.set(1, 99);
			if (data.equals(1))
				copylist.remove(1);
			System.out.println("Data:" + data);
		}
		/*System.out.println("\n==>For each:");
		for(Integer data : copylist){
			System.out.println("Data:"+data);
			list.set(2, 88);
			list.remove(3);
		}*/

		Iterator<Integer> iterator = list.iterator();
		System.out.println("\n==> Iterator:");
		while (iterator.hasNext()) {
			System.out.println("Data:" + iterator.next());
			list.set(1, 99);
			// list.remove(3);
		}
		System.out.println("\n==>For each:");
		for (Integer data : list) {
			System.out.println("Data:" + data);
			list.set(2, 88);
			list.remove(3);
		}
		System.out.println("\n==> collection stream() util....");
		list.forEach((temp) -> {
			System.out.println(temp);
		});

	}

}
