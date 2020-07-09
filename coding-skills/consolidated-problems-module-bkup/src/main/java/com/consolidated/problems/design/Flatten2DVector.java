package com.consolidated.problems.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 *Flatten 2D Vector:
 *  Implement an iterator to flatten a 2d vector. For example, 
 *  Given 2d vector =
 *  [[1,2], [3], [4,5,6]]
 *  By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 */
public class Flatten2DVector {
	public static void main(String[] args) {
		Flatten2DVector test = new Flatten2DVector();
		Flatten2DVector1 ob = new Flatten2DVector1(test.mockList());
		System.out.println("Flatten 2D Vector(Approach1): ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.println("\n" + ob.hasNext());
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.print(ob.next() + " ");
		System.out.println("\n" + ob.hasNext());
		System.out.print(ob.next() + " ");

		Flatten2DVector2 ob2 = new Flatten2DVector2(test.mockList());
		System.out.println("\n\nFlatten 2D Vector(Approach2): ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.println("\n" + ob2.hasNext());
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.print(ob2.next() + " ");
		System.out.println("\n" + ob2.hasNext());
		System.out.print(ob2.next() + " ");
	}

	public List<List<Integer>> mockList() {
		List<List<Integer>> vector = new ArrayList<>();

		vector.add(new ArrayList<>());

		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		vector.add(list1);

		list1 = new ArrayList<>();
		list1.add(3);
		vector.add(list1);

		list1 = new ArrayList<>();
		list1.add(4);
		list1.add(5);
		list1.add(6);
		vector.add(list1);

		return vector;
	}
}

/*
 * Implement an iterator to flatten a 2d vector. 
 * For example, Given 2d vector = [ [1,2], [3], [4,5,6] ] 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 */
// Approach1:
class Flatten2DVector1 {

	private List<List<Integer>>	vector;
	private int					row, col;

	public Flatten2DVector1(List<List<Integer>> list) {
		this.vector = list;
		this.row = 0;
		this.col = 0;
	}

	public int next() {
		if (!hasNext()) return -1;

		int next = vector.get(row).get(col);

		col++;
		if (col == vector.get(row).size()) {
			col = 0;
			row++;
		}

		return next;
	}

	public boolean hasNext() {
		int max = vector.size();
		while (row < max && (vector.get(row) == null || vector.get(row).isEmpty()))
			row++;

		return !vector.isEmpty() && row < max;
	}
}

class Flatten2DVector2 {
	private Iterator<List<Integer>>	outer;
	private Iterator<Integer>		inner;

	public Flatten2DVector2(List<List<Integer>> vec2d) {
		outer = vec2d.iterator();
		inner = outer.next().iterator();
		// Why this?
		// inner = Collections.emptyIterator(); // inner = outer.iterator(); wrong: if outer is null, then exception
	}

	public int next() {
		if (!hasNext()) return -1;

		return inner.next();
	}

	public boolean hasNext() {
		if (inner.hasNext()) return true;
		if (!outer.hasNext()) return false;

		inner = outer.next().iterator();
		while (!inner.hasNext() && outer.hasNext())
			inner = outer.next().iterator();

		return inner.hasNext();
	}

}
