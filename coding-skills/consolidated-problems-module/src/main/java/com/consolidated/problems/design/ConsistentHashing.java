package com.consolidated.problems.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

/*
 * A general database method for performing a horizontal shard is to take the id against the total number of database
 * servers n and then to find out which machine it is on. The disadvantage of this method is that as the data continues
 * to increase, we need to increase the database server. When n is changed to n+1, almost all the data has to be moved,
 * which causes no consistency. To reduce this naive method of hash (% n) defects caused by the emergence of a new hash
 * algorithm : consistent hashing algorithm --Consistent Hashing. 
 * There are many ways to implement this algorithm. Here we implement a simple Consistent Hashing. 
 * 1. The id is modeled on 360. If there are 3 machines at the beginning, then the 3 machines are responsible for the three 
 *    parts of 0~119, 120~239, 240~359. Then, how much is the model, check which zone you are in, and which machine to go to.
 * 2. When the machine changes from n to n+1, we find the largest one from the n intervals, then divide it into two and give 
 *    half to the n+1th machine.
 * 3. For example, when changing from 3	 to 4, we find the third interval 0~119 is the current largest interval, then we divide
 *    0~119 into 0~59 and 60~119. 0~59 is still given to the first machine, 60~119 to the fourth machine. 
 * 4. Then we changed from 4 to 5, and we found that the largest interval is the third interval 120~239. After dividing into two,
 *    it becomes 120~179, 180~239. 
 */

public class ConsistentHashing {

	public static void main(String[] args) {
		System.out.println("Consitent Hashing: ");
		ConsistentHashing1 ob1 = new ConsistentHashing1();
		ob1.consistentHashing(1);
		ob1.consistentHashing(2);
		ob1.consistentHashing(3);
		ob1.consistentHashing(4);
		ob1.consistentHashing(5);

		System.out.println("Consitent Hashing: ");
		ConsistentHashing2 ob2 = new ConsistentHashing2(100, 3);
		ob2.addMachine(1);
		ob2.addMachine(2);
		ob2.addMachine(3);
	}
}

// Use the heap to maintain the interval.
class ConsistentHashing1 {
	public List<List<Integer>> consistentHashing(int n) {
		// Write your code here
		PriorityQueue<Range> heap = new PriorityQueue<>(16, new Comparator<Range>() {
			@Override
			public int compare(Range r1, Range r2) {
				if (r1.to - r1.from > r2.to - r2.from) return -1;
				if (r1.to - r1.from < r2.to - r2.from) return 1;
				return r1.id - r2.id;
			}
		});
		heap.offer(new Range(1, 0, 359));
		for (int i = 2; i <= n; i++) {
			Range range = heap.poll();
			Range range1 = new Range(range.id, range.from, (range.from + range.to) / 2);
			Range range2 = new Range(i, (range.from + range.to) / 2 + 1, range.to);
			heap.offer(range1);
			heap.offer(range2);
		}
		Range[] ranges = heap.toArray(new Range[0]);
		List<List<Integer>> results = new ArrayList<>(ranges.length);
		for (int i = 0; i < ranges.length; i++) {
			List<Integer> result = new ArrayList<>(3);
			result.add(ranges[i].from);
			result.add(ranges[i].to);
			result.add(ranges[i].id);
			results.add(result);
		}

		results.stream().forEach(k -> System.out.print(k + " "));
		System.out.println();
		return results;
	}
}

/*    
 * In Consistent Hashing I we introduced a relatively simple consistency hashing algorithm . This simple version has two
 * drawbacks: 
 *   - After adding a machine, the data comes from one of the machines. The read load of this machine is too large, which will 
 *     affect the normal service. 
 *   - When added to 3 machines, the load of each server is not balanced, it is 1:1:2.
 *    
 * To solve this problem, the concept of micro-shards was introduced. A better algorithm is this: 
 *   - Divide the 360° interval to be finer. Change from 0~359 to a range of 0~n-1, and connect the sections end to end,
 *     and connect them into a circle. 
 *   - When joining a new machine, randomly choose to sprinkle k points in the circle, representing the k micro-shards of the
 *     machine. 
 *   - Each data also corresponds to a point on the circumference, which is calculated by a hash function. 
 *   - A data belongs to the machine that is responsible for management, and is determined by the machine to which the first 
 *     micro-shard point touched clockwise on the circle corresponding to the point on the circumference of the data. 
 *     
 * n and k are typically 2^64 and 1000 in a real NoSQL database. Implement this method that introduces a micro-shard's 
 * consistent hashing. The main implementation of the following three functions: 
 *   create(int n, int k)
 *   addMachine(int machine_id) // add a new machine, return a list of shard ids. 
 *   getMachineIdByHashCode(int hashcode) //return machine id
 */
class ConsistentHashing2 {
	private TreeMap<Integer, Integer> tm = new TreeMap<>();

	private int[]	nums;
	private int		size	= 0;
	private int		k;

	public ConsistentHashing2(int n, int k) {
		this.nums = new int[n];
		for (int i = 0; i < n; i++)
			this.nums[i] = i;

		Random random = new Random();
		for (int i = 0; i < n; i++) {
			int j = random.nextInt(i + 1);
			int t = nums[i];
			nums[i] = nums[j];
			nums[j] = t;
		}
		this.k = k;
	}

	// @param n a positive integer
	// @param k a positive integer
	// @return a Solution object
	public static ConsistentHashing2 create(int n, int k) {
		// Write your code here
		return new ConsistentHashing2(n, k);
	}

	// @param machine_id an integer
	// @return a list of shard ids
	public List<Integer> addMachine(int machine_id) {
		// Write your code here
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < this.k; i++) {
			int id = this.nums[size++ % this.nums.length];
			ids.add(id);
			this.tm.put(id, machine_id);
		}
		ids.stream().forEach(k -> System.out.print(k + " "));
		System.out.println();
		return ids;
	}

	// @param hashcode an integer
	// @return a machine id
	public int getMachineIdByHashCode(int hashcode) {
		// Write your code here
		if (tm.isEmpty()) return 0;
		Integer ceiling = tm.ceilingKey(hashcode);
		if (ceiling != null) return tm.get(ceiling);
		return tm.get(tm.firstKey());
	}
}

class Range {
	int	id;
	int	from, to;

	Range(int id, int from, int to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}
}
