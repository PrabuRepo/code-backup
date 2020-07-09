package com.problems.patterns;

import java.util.LinkedList;
import java.util.Queue;

public class QueuePatterns {

	//Circular tour/Gas Station - Circular Queue & Greed Alg

	//First non-repeating character in a stream
	public void firstNonRepeatingChar(char[] ch) {
		if (ch.length == 0) return;
		Queue<Character> queue = new LinkedList<>();
		int[] count = new int[26];
		for (int i = 0; i < ch.length; i++) {
			// Add first non repeating char in queue
			queue.add(ch[i]);
			// Increase the count array corresponding to the char
			count[ch[i] - 'a']++;

			while (!queue.isEmpty()) {
				if (count[queue.peek()
						- 'a'] > 1) {
					queue.poll();
				} else {
					System.out.print(
							queue.peek() + " ");
					break;
				}
			}
			if (queue.isEmpty())
				System.out.print("-1" + " ");
		}
	}

	//LRU Cache
	//LFU Cache 

}