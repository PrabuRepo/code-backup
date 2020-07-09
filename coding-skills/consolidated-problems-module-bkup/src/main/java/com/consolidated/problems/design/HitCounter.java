package com.consolidated.problems.design;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Design Hit Counter: 
 * Design a hit counter which counts the number of hits received in the past 5 minutes. Each function accepts a timestamp 
 * parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order 
 * (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 * It is possible that several hits arrive roughly at the same time.
 */
public class HitCounter {
	public static void main(String[] args) {
		System.out.println("Approach1: Design Hit Counter - ");
		HitCounter1 ob1 = new HitCounter1(300); // Interval-5 min = 300sec
		ob1.hit(1);
		ob1.hit(2);
		ob1.hit(3);
		System.out.println("Hits: " + ob1.getHits(4));
		ob1.hit(300);
		ob1.hit(300);
		System.out.println("Hits: " + ob1.getHits(300));
		System.out.println("Hits: " + ob1.getHits(301));

		System.out.println("Approach2: Design Hit Counter - ");
		HitCounter2 ob2 = new HitCounter2(300); // Interval-5 min = 300sec
		ob2.hit(1);
		ob2.hit(2);
		ob2.hit(3);
		System.out.println("Hits: " + ob2.getHits(4));
		ob2.hit(300);
		ob2.hit(300);
		System.out.println("Hits: " + ob2.getHits(300));
		System.out.println("Hits: " + ob2.getHits(301));
	}
}

/* O(s) s is total seconds in given time interval, in this case 300. basic ideal is using buckets. 1 bucket for every
 * second because we only need to keep the recent hits info for 300 seconds. hit[] array is wrapped around by mod
 * operation. Each hit bucket is associated with times[] bucket which record current time. If it is not current time, it
 * means it is 300s or 600s... ago and need to reset to 1.
 */
class HitCounter1 {
	private int[]	times;
	private int[]	hits;
	private int		interval;	// Interval in seconds

	public HitCounter1(int interval) {
		hits = new int[interval];
		times = new int[interval];
		this.interval = interval;
	}

	void hit(int timestamp) {
		int index = timestamp % interval;
		if (times[index] != interval) {
			times[index] = timestamp;
			hits[index] = 1;
		} else {
			hits[index]++;
		}
	}

	int getHits(int timestamp) {
		int totalHits = 0;
		for (int i = 0; i < interval; i++)
			if (timestamp - times[i] < interval && hits[i] > 0) totalHits += hits[i];
		return totalHits;
	}
}

// Approach2: Using Queue
class HitCounter2 {
	private Queue<Integer>	queue;
	private int				interval;	// Interval in seconds

	public HitCounter2(int interval) {
		queue = new LinkedList<>();
		this.interval = interval;
	}

	void hit(int timestamp) {
		queue.add(timestamp);
	}

	int getHits(int timestamp) {
		while (!queue.isEmpty() && timestamp - queue.peek() >= interval)
			queue.poll();

		return queue.size();
	}
}
