package com.geeksforgeeks.ds.problems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class QueueGeeksProblems {

	/*Page Fault: Whenever a new page is referred and not present in memory, page fault occurs and Operating System
	replaces one of the existing pages with newly needed page*/
	// FIFO page replacement in Operating Systems.
	public static int findPageFaults(int[] a, int capacity) {
		int pageFault = 0;
		// To represent set of current pages. We use an unordered_set so that we quickly
		// check if a page is present in set or not
		HashSet<Integer> set = new HashSet<>();
		// To store the pages in FIFO manner
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < a.length; i++) {
			if (!set.contains(a[i])) {
				if (set.size() < capacity) {
					// push the current page into the queue
					set.add(a[i]);
					queue.add(a[i]);
					pageFault++;
				} else {
					// Pop the first page from the queue & remove it from set
					set.remove(queue.poll());
					// push the current page into the queue
					queue.add(a[i]);
					set.add(a[i]);
					pageFault++;
				}
			}
			for (Integer in : set)
				System.out.print(in + " ");
			System.out.println();

		}
		return pageFault;
	}

	public static void main(String[] args) {
		// int pages[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };
		int[] pages = { 0, 2, 1, 6, 4, 0, 1, 0, 3, 1, 2, 1 };
		System.out.println("Page Fault: " + findPageFaults(pages, 4));
	}

}
