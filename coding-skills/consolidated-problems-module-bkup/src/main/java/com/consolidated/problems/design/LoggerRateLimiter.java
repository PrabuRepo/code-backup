package com.consolidated.problems.design;

import java.util.HashMap;
import java.util.Map;

/*
 * Logger Rate Limiter:
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and 
 * only if it is not printed in the last 10 seconds. Given a message and a timestamp (in seconds granularity), return true 
 * if the message should be printed in the given timestamp, otherwise returns false.
 * It is possible that several messages arrive roughly at the same time.
 */
public class LoggerRateLimiter {
	public static void main(String[] args) {
		System.out.println("Approach1: Logger Rate Limiter: ");
		LoggerRateLimiter1 ob1 = new LoggerRateLimiter1();
		System.out.println("Print: " + ob1.shouldPrintMessage(1, "foo"));
		System.out.println("Print: " + ob1.shouldPrintMessage(2, "bar"));
		System.out.println("Print: " + ob1.shouldPrintMessage(3, "foo"));
		System.out.println("Print: " + ob1.shouldPrintMessage(8, "bar"));
		System.out.println("Print: " + ob1.shouldPrintMessage(10, "foo"));
		System.out.println("Print: " + ob1.shouldPrintMessage(10, "bar"));
		System.out.println("Print: " + ob1.shouldPrintMessage(11, "foo"));

		System.out.println("Approach2: Design Hit Counter - ");
		LoggerRateLimiter2 ob2 = new LoggerRateLimiter2();
		System.out.println("Print: " + ob2.shouldPrintMessage(1, "foo"));
		System.out.println("Print: " + ob2.shouldPrintMessage(2, "bar"));
		System.out.println("Print: " + ob2.shouldPrintMessage(3, "foo"));
		System.out.println("Print: " + ob2.shouldPrintMessage(8, "bar"));
		System.out.println("Print: " + ob2.shouldPrintMessage(10, "foo"));
		System.out.println("Print: " + ob2.shouldPrintMessage(10, "bar"));
		System.out.println("Print: " + ob2.shouldPrintMessage(11, "foo"));
	}
}

// Approach1:
class LoggerRateLimiter1 {
	Map<String, Integer> messageMap;

	public LoggerRateLimiter1() {
		messageMap = new HashMap<>();
	}

	public boolean shouldPrintMessage(int timestamp, String message) {
		if (!messageMap.containsKey(message) || timestamp - messageMap.get(message) >= 10) {
			messageMap.put(message, timestamp);
			return true;
		}
		return false;
	}
}

// Approach2:
class LoggerRateLimiter2 {
	Map<String, Integer> messageMap;

	public LoggerRateLimiter2() {
		messageMap = new HashMap<>();
	}

	public boolean shouldPrintMessage(int timestamp, String message) {
		if (timestamp < messageMap.getOrDefault(message, 0)) return false;

		messageMap.put(message, timestamp + 10);
		return true;
	}

}