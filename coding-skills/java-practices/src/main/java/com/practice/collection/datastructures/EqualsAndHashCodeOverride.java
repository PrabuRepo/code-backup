package com.practice.collection.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This method explains about usage of equals & hashcode override. equals & hashcode: - Both method is implemented to
 * avoid the duplicate entry in Map.
 *
 */

public class EqualsAndHashCodeOverride {
	public static void main(String[] args) {

		System.out.println("\n List Demo: For comparing list value, only equals() method has to implement");
		List<Point2> list = new ArrayList<Point2>();
		list.add(new Point2(1, "aaa"));
		list.add(new Point2(1, "aaa"));
		// List invokes equals only when checks existing object
		if (list.contains(new Point2(1, "aaa"))) {
			System.out.println("Yes: List contains value");
		} else {
			System.out.println("No: List doesn't contain value");
		}
		System.out.println("List:" + list);

		System.out.println("\n Set Demo: For comparing set value, both equals() & hashCode() method has to implement");
		/**
		 * Before adding the data into SET or MAP, gets the hashcode(from hashCode() method), if hashcode is equal to previously
		 * generated then invokes the equal method() to check equality. If it is not equal, then only insert the data into SET
		 * or MAP. Thats why SET/MAP doesn't have duplicate value.
		 */
		Set<Point2> set = new HashSet<Point2>();
		set.add(new Point2(1, "aaa"));
		set.add(new Point2(1, "aaa"));
		set.add(new Point2(2, "bbb"));
		set.add(new Point2(2, "bbb"));
		set.add(new Point2(2, "ccc"));

		if (set.contains(new Point2(1, "aaa"))) {
			System.out.println("Yes: Set contains value");
		} else {
			System.out.println("No: Set doesn't contain value");
		}
		System.out.println("Set:" + set);

		Iterator<Point2> it = set.iterator();
		while (it.hasNext()) {
			System.out.println("Set Value:" + it.next());
		}

		System.out.println("\n HashMap Demo:For avoiding duplicate key value in map, both equals() & hashCode() method has to implement");
		Map<Point2, Point3> mp = new HashMap<Point2, Point3>();
		mp.put(new Point2(1, "aaa"), new Point3(10.33));
		mp.put(new Point2(1, "aaa"), new Point3(20.55));
		mp.put(new Point2(2, "ddd"), new Point3(30.66));
		mp.put(new Point2(2, "ddd"), new Point3(40.68));
		mp.put(new Point2(4, "eee"), new Point3(50.47));

		System.out.println("\n\t Hash Map is : " + mp);
		System.out.println("End of the program...");

		Set<Point2> keys = mp.keySet();
		Iterator<Point2> iter = keys.iterator();
		while (iter.hasNext()) {
			System.out.println("Hash Value:" + mp.get(iter.next()));
		}
	}
}

class Point2 {
	int		x;
	String	y;

	Point2(int x, String y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "Key:" + this.x + "," + this.y;
	}

	/*@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("Equals call:");
		System.out.println(this.x);
		System.out.println(((Point2) obj).x);
		return super.equals(obj);
	}*/

	@Override
	public int hashCode() {
		// return x; Mostly the multiplied no should be prime no
		int newHashCode = 11 * (x) + y.hashCode();
		// System.out.println("newHashCode:"+newHashCode);
		return newHashCode;
	}

	@Override
	public boolean equals(Object obj) {
		Point2 bObj = (Point2) obj;
		// System.out.println("X Value:"+this.x+","+ bObj.x);
		// System.out.println("Y Value:"+this.y+","+ bObj.y);
		return (this.x == bObj.x) && (this.y.equals(bObj.y));
	}

}

class Point3 {
	double d;

	Point3(double d) {
		this.d = d;
	}

	public String toString() {
		return "Value:" + this.d;
	}
}
