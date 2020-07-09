package com.consolidated.problems.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Twitter {

}

class Twitter1 {
	Map<Integer, Set<Integer>>			users;		// UserId, Followers
	Map<Integer, Map<Integer, Integer>>	tweets;		// UserId, (timestamo, tweets)
	private static int					timeStamp;

	/** Initialize your data structure here. */
	public Twitter1() {
		users = new HashMap<>();
		tweets = new HashMap<>();
		timeStamp = 0;
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if (!users.containsKey(userId)) {
			users.put(userId, new HashSet<>());
			tweets.put(userId, new HashMap<>());
		}
		tweets.get(userId).put(timeStamp++, tweetId);
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
	 * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> newsFeed = new ArrayList<>();
		if (!users.containsKey(userId)) return newsFeed;

		Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> (b.getKey() - a.getKey()));

		// Add users own tweets
		for (Map.Entry<Integer, Integer> entry : tweets.get(userId).entrySet())
			queue.add(entry);

		// Add users followee tweets
		for (Integer user : users.get(userId))
			for (Map.Entry<Integer, Integer> entry : tweets.get(user).entrySet())
				queue.add(entry);

		// Populate recent feeds from queue
		for (int i = 0; i < 10 && !queue.isEmpty(); i++)
			newsFeed.add(queue.poll().getValue());

		return newsFeed;
	}

	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int followerId, int followeeId) {
		if (followerId == followeeId) return;

		// Add Follower in users map
		if (!users.containsKey(followerId)) {
			users.put(followerId, new HashSet<>());
			tweets.put(followerId, new HashMap<>());
		}
		// Add Followee in users map
		if (!users.containsKey(followeeId)) {
			users.put(followeeId, new HashSet<>());
			tweets.put(followeeId, new HashMap<>());
		}

		// Add followee against followers Id
		users.get(followerId).add(followeeId);
	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
		if (followerId == followeeId) return;
		if (users.get(followerId) == null || users.get(followeeId) == null) return;

		users.get(followerId).remove(followeeId);
	}
}

class Twitter2 {
	private static int			timeStamp	= 0;
	private Map<Integer, User>	userMap;		// easy to find if user exist

	// Tweet link to next Tweet so that we can save a lot of time when we execute getNewsFeed(userId)
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

	// OO design so User can follow, unfollow and post itself
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