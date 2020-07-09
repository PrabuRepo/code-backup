package com.consolidated.problems.cheatsheet;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import com.common.model.GraphNode;
import com.common.model.NestedInteger;
import com.common.model.TreeNode;

public class DesignProblems {

}

class LRUCache {
	private HashMap<Integer, CacheNode>	map;
	private DoubleLinkedList			dll;
	private int							capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new HashMap<>(capacity);
		this.dll = new DoubleLinkedList();
	}

	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		} else {
			CacheNode node = map.get(key);
			dll.remove(node);
			dll.moveToRear(node);
			return node.value;
		}
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			dll.remove(map.get(key));
		} else {
			if (map.size() >= capacity) {
				map.remove(dll.front.key);
				dll.remove(dll.front);
			}
		}
		map.put(key, dll.add(key, value));
	}
}

class DoubleLinkedList {
	public CacheNode front, rear;

	public CacheNode add(int key, int val) {
		CacheNode newNode = new CacheNode(key, val);
		if (front == null) {
			front = rear = newNode;
		} else {
			rear.next = newNode;
			newNode.prev = rear;
			rear = newNode;
		}
		return newNode;
	}

	public void remove(CacheNode node) {
		if (node.prev != null) node.prev.next = node.next;
		else front = node.next;
		if (node.next != null) node.next.prev = node.prev;
		else rear = node.prev;
	}

	public void moveToRear(CacheNode node) {
		node.next = null;
		if (rear == null) {
			front = rear = node;
		} else {
			rear.next = node;
			node.prev = rear;
			rear = node;
		}
	}
}

class CacheNode {
	int					value;
	int					key;
	public CacheNode	prev, next;

	public CacheNode(int key, int val) {
		this.key = key;
		this.value = val;
		prev = next = null;
	}
}

class LFUCache {
	private LFUNode						head;
	private int							cap;
	private HashMap<Integer, Integer>	valueHash;
	private HashMap<Integer, LFUNode>	nodeHash;

	public LFUCache(int capacity) {
		this.cap = capacity;
		valueHash = new HashMap<Integer, Integer>();
		nodeHash = new HashMap<Integer, LFUNode>();
	}

	public int get(int key) {
		if (valueHash.containsKey(key)) {
			increaseCount(key);
			return valueHash.get(key);
		}
		return -1;
	}

	public void put(int key, int value) {
		if (cap == 0) return;
		if (valueHash.containsKey(key)) {
			valueHash.put(key, value);
		} else {
			if (valueHash.size() < cap) {
				valueHash.put(key, value);
			} else {
				removeOld();
				valueHash.put(key, value);
			}
			addToHead(key);
		}
		increaseCount(key);
	}

	private void addToHead(int key) {
		if (head == null) {
			head = new LFUNode(0);
			head.keys.add(key);
		} else if (head.count > 0) {
			LFUNode node = new LFUNode(0);
			node.keys.add(key);
			node.next = head;
			head.prev = node;
			head = node;
		} else {
			head.keys.add(key);
		}
		nodeHash.put(key, head);
	}

	private void increaseCount(int key) {
		LFUNode node = nodeHash.get(key);
		node.keys.remove(key);
		if (node.next == null) {
			node.next = new LFUNode(node.count + 1);
			node.next.prev = node;
			node.next.keys.add(key);
		} else if (node.next.count == node.count + 1) {
			node.next.keys.add(key);
		} else {
			LFUNode tmp = new LFUNode(node.count + 1);
			tmp.keys.add(key);
			tmp.prev = node;
			tmp.next = node.next;
			node.next.prev = tmp;
			node.next = tmp;
		}
		nodeHash.put(key, node.next);
		if (node.keys.size() == 0) remove(node);
	}

	private void removeOld() {
		if (head == null) return;
		int old = head.keys.iterator().next();
		head.keys.remove(old);
		if (head.keys.size() == 0) remove(head);
		nodeHash.remove(old);
		valueHash.remove(old);
	}

	private void remove(LFUNode node) {
		if (node.prev == null) {
			head = node.next;
		} else {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
	}
}

class LFUNode {
	public int						count;
	public LinkedHashSet<Integer>	keys;
	public LFUNode					prev, next;

	public LFUNode(int count) {
		this.count = count;
		keys = new LinkedHashSet<Integer>();
		prev = next = null;
	}
}

class RandomizedSet {
	HashMap<Integer, Integer>	map;
	List<Integer>				list;

	public RandomizedSet() {
		map = new HashMap<>(); // Val, Index of list
		list = new ArrayList<>();
	}

	public boolean insert(int val) {
		if (map.containsKey(val)) return false;
		map.put(val, list.size());
		list.add(val);
		return true;
	}

	public boolean remove(int val) {
		if (!map.containsKey(val)) return false;
		int index = map.get(val);
		if (index < list.size() - 1) {
			int lastElement = list.get(list.size() - 1);
			list.set(index, lastElement);
			map.put(lastElement, index);
		}
		list.remove(list.size() - 1);
		map.remove(val);
		return true;
	}

	public int getRandom() {
		Random random = new Random();
		int randomIndex = random.nextInt(list.size());
		return list.get(randomIndex);
	}
}

class RandomizedCollection {
	HashMap<Integer, LinkedHashSet<Integer>>	map;
	List<Integer>								list;

	public RandomizedCollection() {
		map = new HashMap<>();
		list = new ArrayList<>();
	}

	public boolean insert(int val) {
		boolean contain = map.containsKey(val);
		if (!contain) map.put(val, new LinkedHashSet<>());
		map.get(val).add(list.size());
		list.add(val);
		return !contain;
	}

	public boolean remove(int val) {
		if (!map.containsKey(val)) return false;
		int index = map.get(val).iterator().next();
		map.get(val).remove(index);
		if (index < list.size() - 1) {
			int lastElement = list.get(list.size() - 1);
			list.set(index, lastElement); // O(1) - To update any index in the arraylist //Remove the existing one; Last
											// element should have max list size index
			map.get(lastElement).remove(list.size() - 1);
			map.get(lastElement).add(index); // Add the removed element index
		}
		list.remove(list.size() - 1); // O(1) - To remove the last element in the arraylist
		if (map.get(val).isEmpty()) map.remove(val);
		return true;
	}

	public int getRandom() {
		Random random = new Random();
		int randomIndex = random.nextInt(list.size());
		return list.get(randomIndex);
	}
}

class FrequentCollection {
	HashMap<Integer, FreqNode>	map;
	FreqNode					head, tail;

	public FrequentCollection() {
		map = new HashMap<Integer, FreqNode>();
	}

	public void insert(int val) {
		if (map.containsKey(val)) {
			FreqNode n = map.get(val);
			n.set.remove(val);
			if (n.next != null) {
				n.next.set.add(val); // next + 1
				map.put(val, n.next);
			} else {
				FreqNode t = new FreqNode(n.value + 1);
				t.set.add(val);
				n.next = t;
				t.prev = n;
				map.put(val, t);
			}
			if (head.next != null) head = head.next;
		} else {
			if (tail == null || head == null) {
				FreqNode n = new FreqNode(1);
				n.set.add(val);
				map.put(val, n);
				head = n;
				tail = n;
				return;
			}
			if (tail.value > 1) {
				FreqNode n = new FreqNode(1);
				n.set.add(val);
				map.put(val, n);
				tail.prev = n;
				n.next = tail;
				tail = n;
			} else {
				tail.set.add(val);
				map.put(val, tail);
			}
		}
	}

	public void remove(int val) {
		FreqNode n = map.get(val);
		n.set.remove(val);
		if (n.value == 1) {
			map.remove(val);
		} else {
			n.prev.set.add(val);
			map.put(val, n.prev);
		}
		while (head != null && head.set.size() == 0) {
			head = head.prev;
		}
	}

	public int getMostFrequent() {
		if (head == null) return -1;
		else return head.set.iterator().next();
	}
}

class FreqNode {
	int					value;
	FreqNode			prev;
	FreqNode			next;
	HashSet<Integer>	set;

	public FreqNode(int v) {
		value = v;
		set = new HashSet<Integer>();
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

// Approach2:
class Flatten2DVector2 {
	private Iterator<List<Integer>>	outer;
	private Iterator<Integer>		inner;

	public Flatten2DVector2(List<List<Integer>> vec2d) {
		outer = vec2d.iterator();
		inner = outer.next().iterator();
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

/*
 * Flatten Nested List Iterator:
 * Given a nested list of integers, implement an iterator to flatten it. Each element is either 
 * an integer, or a list -- whose elements may also be integers or other lists.
 * Example 1: Input: [[1,1],2,[1,1]]; Output: [1,1,2,1,1]
 */
class NestedIterator implements Iterator<Integer> {
	Stack<NestedInteger> stack;

	public NestedIterator(List<NestedInteger> nestedList) {
		stack = new Stack<>();
		flattenList(nestedList);
	}

	@Override
	public Integer next() {
		if (!hasNext()) return null;

		return stack.pop().getInteger();
	}

	@Override
	public boolean hasNext() {
		while (!stack.isEmpty()) {
			if (stack.peek().isInteger()) return true;
			flattenList(stack.pop().getList());
		}
		return false;
	}

	public void flattenList(List<NestedInteger> nestedList) {
		for (int i = nestedList.size() - 1; i >= 0; i--)
			stack.push(nestedList.get(i));
	}
}

// Approach1: Brute Force Approach, Without
// using any Java API
class ZigZagIterator1 {
	List<Integer>	list1, list2;
	int				index;
	boolean			flag;

	public ZigZagIterator1(List<Integer> l1, List<Integer> l2) {
		this.list1 = l1;
		this.list2 = l2;
		flag = true;
		index = 0;
	}

	public int next() {
		if (!hasNext()) return -1;
		int next = 0;
		if (flag && index < list1.size()) {
			next = list1.get(index);
			if (index < list2.size()) flag = !flag;
			else index++;
		} else {
			next = list2.get(index);
			index++;
			if (index < list1.size()) flag = !flag;
		}
		return next;
	}

	public boolean hasNext() {
		return index < list1.size() || index < list2.size();
	}
}

/* Approach2: Uses Java Iterator */
class ZigZagIterator2 {
	Iterator<Integer>	iter1, iter2;
	int					count	= 0;

	public ZigZagIterator2(List<Integer> list1, List<Integer> list2) {
		this.iter1 = list1.iterator();
		this.iter2 = list2.iterator();
		this.count = 0;
	}

	public int next() {
		if (!hasNext()) return -1;
		count++;
		if ((count % 2 == 1 && iter1.hasNext()) || !iter2.hasNext()) return iter1.next();
		else return iter2.next();
	}

	public boolean hasNext() {
		return iter1.hasNext() || iter2.hasNext();
	}
}

/* Approach3: 
 * Uses a Queue or linkedlist to store the iterators in different vectors. Every time we call next(), we pop an element
 * from the list, and re-add it to the end to cycle through the lists.
 */
// Approach3: Uses a Queue or linkedlist
class ZigZagIterator3 {
	Queue<Iterator<Integer>> queue;

	public ZigZagIterator3(List<Integer> v1, List<Integer> v2) {
		queue = new LinkedList<Iterator<Integer>>();
		if (!v1.isEmpty()) queue.add(v1.iterator());
		if (!v2.isEmpty()) queue.add(v2.iterator());
	}

	public int next() {
		if (!hasNext()) return -1;
		Iterator<Integer> poll = queue.remove();
		int result = (Integer) poll.next();
		if (poll.hasNext()) queue.add(poll);
		return result;
	}

	public boolean hasNext() {
		return !queue.isEmpty();
	}
}

class BSTIterator {
	Stack<TreeNode> stack;

	public BSTIterator(TreeNode root) {
		stack = new Stack<>();
		push(root);
	}

	public int next() {
		TreeNode node = stack.pop();
		int val = node.data;
		if (node.right != null) push(node.right);
		return val;
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public void push(TreeNode node) {
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}
}

// Design Phone Directory - Array/Queue & Set:
// Approach1: method of get() is O(n),
// check O(1) release O(1)
class PhoneDirectory1 {
	boolean[]	bitSet;
	int			smallestFreeIndex;

	public PhoneDirectory1(int maxNumbers) {
		this.bitSet = new boolean[maxNumbers];
		this.smallestFreeIndex = 0;
	}

	public int get() {
		if (smallestFreeIndex == bitSet.length) return -1;
		int num = smallestFreeIndex;
		bitSet[num] = true;
		for (int i = smallestFreeIndex + 1; i < bitSet.length; i++) {
			if (!bitSet[i]) {
				smallestFreeIndex = i;
				break;
			}
		}
		if (num == smallestFreeIndex) smallestFreeIndex = bitSet.length;
		return num;
	}

	public boolean check(int number) {
		return !bitSet[number];
	}

	public void release(int number) {
		if (bitSet[number] = false) return;
		bitSet[number] = false;
		if (number < smallestFreeIndex) smallestFreeIndex = number;
	}
}

// Approach2: In the method that is all O(1)
class PhoneDirectory2 {
	Set<Integer>	used		= new HashSet<>();
	Queue<Integer>	available	= new LinkedList<>();
	int				maxNumbers;

	public PhoneDirectory2(int max) {
		this.maxNumbers = max;
		for (int i = 0; i < maxNumbers; i++)
			available.add(i);
	}

	public int get() {
		if (available.isEmpty()) return -1;
		int num = available.poll();
		used.add(num);
		return num;
	}

	public boolean check(int num) {
		if (num < 0 || num >= maxNumbers) return false;
		return !used.contains(num);
	}

	public void release(int num) {
		if (used.remove(num)) available.add(num);
	}
}

/* Encode and Decode TinyURL
 * 
 */
// Approach 1- Using simple counter
class Codec1 {
	Map<Integer, String>	map	= new HashMap<>();
	int						i	= 0;

	public String encode(String longUrl) {
		map.put(i, longUrl);
		return "http://tinyurl.com/" + i++;
	}

	public String decode(String shortUrl) {
		int key = Integer.parseInt(shortUrl.replace("http://tinyurl.com/", ""));
		return map.get(key);
	}
}

// Approach 2- using hashcode
class Codec2 {
	Map<Integer, String> map = new HashMap<>();

	public String encode(String longUrl) {
		map.put(longUrl.hashCode(), longUrl);
		return "http://tinyurl.com/" + longUrl.hashCode();
	}

	public String decode(String shortUrl) {
		return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}
}

// Approach 3- using random function
class Codec3 {
	Map<Integer, String>	map	= new HashMap<>();
	Random					r	= new Random();
	int						key	= r.nextInt(10000);

	public String encode(String longUrl) {
		while (map.containsKey(key))
			key = r.nextInt(10000);
		map.put(key, longUrl);
		return "http://tinyurl.com/" + key;
	}

	public String decode(String shortUrl) {
		return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}
}

class Codec4 {
	public String encode(String longUrl) {
		return Base64.getUrlEncoder().encodeToString(longUrl.getBytes(StandardCharsets.UTF_8));
	}

	public String decode(String shortUrl) {
		return new String(Base64.getUrlDecoder().decode(shortUrl));
	}
}

// Design Hit Counter - Map
/* O(s) s is total seconds in given time interval, in this case 300. basic ideal is using buckets. 1 bucket for every
 * second because we only need to keep the recent hits info for 300 seconds. hit[] array is wrapped around by mod
 * operation. Each hit bucket is associated with times[] bucket which record current time. If it is not current time, it
 * means it is 300s or 600s... ago and need to reset to 1.
 */
class HitCounter1 {
	private int[]	times;
	private int[]	hits;
	private int		interval;

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

// Logger Rate Limiter - Map
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

// Design Twitter - Map/Heap

class Twitter1 {
	Map<Integer, Set<Integer>>			users;		// UserId, Followers
	Map<Integer, Map<Integer, Integer>>	tweets;		// UserId, (timestamo, tweets)
	private static int					timeStamp;

	public Twitter1() {
		users = new HashMap<>();
		tweets = new HashMap<>();
		timeStamp = 0;
	}

	public void postTweet(int userId, int tweetId) {
		if (!users.containsKey(userId)) {
			users.put(userId, new HashSet<>());
			tweets.put(userId, new HashMap<>());
		}
		tweets.get(userId).put(timeStamp++, tweetId);
	}

	public List<Integer> getNewsFeed(int userId) {
		List<Integer> newsFeed = new ArrayList<>();
		if (!users.containsKey(userId)) return newsFeed;
		Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> (b.getKey() - a.getKey()));
		for (Map.Entry<Integer, Integer> entry : tweets.get(userId).entrySet())
			queue.add(entry);
		for (Integer user : users.get(userId))
			for (Map.Entry<Integer, Integer> entry : tweets.get(user).entrySet())
				queue.add(entry);
		for (int i = 0; i < 10 && !queue.isEmpty(); i++)
			newsFeed.add(queue.poll().getValue());
		return newsFeed;
	}

	public void follow(int followerId, int followeeId) {
		if (followerId == followeeId) return;
		if (!users.containsKey(followerId)) {
			users.put(followerId, new HashSet<>());
			tweets.put(followerId, new HashMap<>());
		}
		if (!users.containsKey(followeeId)) {
			users.put(followeeId, new HashSet<>());
			tweets.put(followeeId, new HashMap<>());
		}
		users.get(followerId).add(followeeId);
	}

	public void unfollow(int followerId, int followeeId) {
		if (followerId == followeeId) return;
		if (users.get(followerId) == null || users.get(followeeId) == null) return;

		users.get(followerId).remove(followeeId);
	}
}

class Twitter2 {
	private static int			timeStamp	= 0;
	private Map<Integer, User>	userMap;

	private class Tweet {
		public int		id;
		public int		time;
		public Tweet	next;

		public Tweet(int id) {
			this.id = id;
			time = timeStamp++;
			next = null;
		}
	}

	public class User {
		public int			id;
		public Set<Integer>	followed;
		public Tweet		tweet_head;

		public User(int id) {
			this.id = id;
			followed = new HashSet<>();
			follow(id); // first follow itself
			tweet_head = null;
		}

		public void follow(int id) {
			followed.add(id);
		}

		public void unfollow(int id) {
			followed.remove(id);
		}

		// everytime user post a new tweet, add it to the head of tweet list.
		public void post(int id) {
			Tweet t = new Tweet(id);
			t.next = tweet_head;
			tweet_head = t;
		}
	}

	/** Initialize your data structure here. */
	public Twitter2() {
		userMap = new HashMap<Integer, User>();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if (!userMap.containsKey(userId)) {
			User u = new User(userId);
			userMap.put(userId, u);
		}
		userMap.get(userId).post(tweetId);

	}

	// Best part of this.
	// first get all tweets lists from one user including itself and all people it followed.
	// Second add all heads into a max heap. Every time we poll a tweet with
	// largest time stamp from the heap, then we add its next tweet into the heap.
	// So after adding all heads we only need to add 9 tweets at most into this
	// heap before we get the 10 most recent tweet.
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> res = new LinkedList<>();

		if (!userMap.containsKey(userId)) return res;

		Set<Integer> users = userMap.get(userId).followed;
		PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a, b) -> (b.time - a.time));
		for (int user : users) {
			Tweet t = userMap.get(user).tweet_head;
			// very imporant! If we add null to the head we are screwed.
			if (t != null) {
				q.add(t);
			}
		}
		int n = 0;
		while (!q.isEmpty() && n < 10) {
			Tweet t = q.poll();
			res.add(t.id);
			n++;
			if (t.next != null) q.add(t.next);
		}

		return res;

	}

	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int followerId, int followeeId) {
		if (!userMap.containsKey(followerId)) {
			User u = new User(followerId);
			userMap.put(followerId, u);
		}
		if (!userMap.containsKey(followeeId)) {
			User u = new User(followeeId);
			userMap.put(followeeId, u);
		}
		userMap.get(followerId).follow(followeeId);
	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
		if (!userMap.containsKey(followerId) || followerId == followeeId) return;
		userMap.get(followerId).unfollow(followeeId);
	}
}

/* Design Tic-Tac-Toe:
 * Design a Tic-tac-toe game that is played between two players on a n x n grid. You may assume the following rules: A
 * move is guaranteed to be valid and is placed on an empty block. Once a winning condition is reached, no more moves is
 * allowed. A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 */
/* Efficient Approach: Move Time Complexity-O(1)
 * O(1) Time, O(n) extra space. Set the player to 1 and -1; create rows[n] and cols[n] arrays and variables diagonal, 
 * anti_diagonal to record this row/column/diagonal .
 */
class TicTacToe1 {
	private int[]	rows;
	private int[]	cols;
	private int		diagonal;
	private int		antiDiagonal;

	public TicTacToe1(int n) {
		this.rows = new int[n];
		this.cols = new int[n];
	}

	public int move(int i, int j, int player) {
		int size = rows.length;
		int toAdd = (player) == 1 ? 1 : -1;
		rows[i] += toAdd;
		cols[i] += toAdd;
		if (i == j) diagonal += toAdd;
		if (i + j == size + 1) antiDiagonal += toAdd;
		if (Math.abs(rows[i]) == size || Math.abs(cols[i]) == size || Math.abs(diagonal) == size
				|| Math.abs(antiDiagonal) == size)
			return player;
		return 0;
	}
}

// Design Snake Game - Matrix
class SnakeGame1 {
	HashSet<Integer>	set;
	Deque<Integer>		body;
	int					score;
	int[][]				food;
	int					foodIndex;
	int					width;
	int					height;

	public SnakeGame1(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;
		set = new HashSet<Integer>();
		set.add(0);
		body = new LinkedList<Integer>();
		body.addLast(0);
	}

	public int move(String direction) {
		if (score == -1) { return -1; }
		int rowHead = body.peekFirst() / width;
		int colHead = body.peekFirst() % width;
		switch (direction) {
			case "U":
				rowHead--;
				break;
			case "D":
				rowHead++;
				break;
			case "L":
				colHead--;
				break;
			case "R":
				colHead++;
				break;
		}
		set.remove(body.peekLast());
		int headPos = rowHead * width + colHead;
		if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(headPos))
			return score = -1;
		set.add(headPos);
		body.addFirst(headPos);
		if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
			set.add(body.peekLast());
			foodIndex++;
			return ++score;
		}
		body.removeLast();
		return score;
	}
}

class SnakeAndLadder {
	/*Snake and Ladder Problem:
	 * The idea is to consider the given snake and ladder board as a directed graph with number of vertices equal to the number of cells 
	 * in the board. The problem reduces to finding the shortest path in a graph. Every vertex of the graph has an edge to next six
	 * vertices if next 6 vertices do not have a snake or ladder. If any of the next six vertices has a snake or ladder, then the edge 
	 * from current vertex goes to the top of the ladder or tail of the snake. Since all edges are of equal weight, we can efficiently 
	 * find shortest path using Breadth First Search of the graph.
	 */
	public int findMinDiceThrows(int[] moves, int n) {
		boolean[] visted = new boolean[n];
		Queue<GraphNode> queue = new LinkedList<>();
		queue.add(new GraphNode(0, 0)); // vertex no, weight or dist of this vertex from source
		visted[0] = true;
		GraphNode curr = null, adjNode = null;
		while (!queue.isEmpty()) {
			curr = queue.poll();
			int v = curr.vertex;
			if (v == n - 1) return curr.weight;

			for (int i = v + 1; (i <= v + 6 && i < n); i++) {
				adjNode = new GraphNode();
				if (!visted[i]) {
					visted[i] = true;
					// update the vertex & dist from source
					adjNode.vertex = moves[i] != -1 ? moves[i] : i;
					adjNode.weight = curr.weight + 1;
					queue.add(adjNode);
				}
			}

		}
		return -1;
	}
}

/*
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 */
class MovingAverage {
	// Variables for Approach-1
	public Queue<Integer> queue;

	// Variables for Approach-2
	int[]	window;
	int		count;
	// Common for both
	int	sum;
	int	size;
	int	i;

	public MovingAverage(int size) {
		this.queue = new LinkedList<>();
		this.size = size;
		// Another Approach using circular queue
		this.window = new int[size];
	}

	// Approach1:
	public double next(int val) {
		if (queue.isEmpty() || queue.size() < size) {
			sum += val;
			queue.add(val);
		} else {
			sum -= queue.poll();
			sum += val;
			queue.add(val);
		}
		return (double) (sum / queue.size());
	}

	// Approach2:
	public double next2(int val) {
		if (count < size) count++;
		sum -= window[i];
		sum += val;
		window[i] = val; // It will override the from value in the array
		i = (i + 1) % size; // Find the next index

		return (double) sum / count;
	}

}