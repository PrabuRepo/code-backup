package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.common.model.TrieNode;
import com.common.model.WordNode;

public class WordProblems {
	/*
	String: Shortest Word Distance, Word Pattern 
	Trie: Add and Search Word; Word Squares; Valid Word Square
	Matrix 4 Dir & Trie: Word Boggle or Word Search I, II
	Graph: Word Ladder I,II(BFS)
	Backtracking: Word Pattern II 
	DP: Word Break I, II
	Map:Unique Word Abbreviation, Minimum Unique Word Abbreviation
	Bit Manipulations: Maximum Product of Word Lengths/Find the Difference
	Heap: Top K Frequent Words
	Backtracking: Crossword Puzzle */

	/* Shortest Word Distance:
	 * words = ["practice", "makes", "perfect", "coding", "makes"]. 
	 * Given word1 = "coding", word2 = "practice", Result: 3. 
	 * Given word1 = "makes", word2 = "coding", Result: 1.
	 */
	public int shortestDistanceI(String[] words, String word1, String word2) {
		int min = Integer.MAX_VALUE, index1 = -1, index2 = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) index1 = i;

			if (words[i].equals(word2)) index2 = i;

			if (index1 != -1 && index2 != -1) min = Math.min(min, Math.abs(index1 - index2));
		}
		return min;
	}

	/* Word Pattern: Example 1: Input: pattern = "abba", 
	 * str = "dog cat cat dog";  Output: true
	 */
	public boolean wordPattern(String pattern, String str) {
		if (str == null || str.length() == 0 || pattern.length() == 0) return false;

		Map<Character, String> map = new HashMap<>();
		String[] arr = str.split(" ");
		if (pattern.length() != arr.length) return false;

		for (int i = 0; i < pattern.length(); i++) {
			char pat = pattern.charAt(i);
			if (map.containsKey(pat) && !map.get(pat).equals(arr[i])) return false;
			if (!map.containsKey(pat) && map.containsValue(arr[i])) return false;
			map.put(pat, arr[i]);
		}

		return true;
	}

	/*
	 * Word Pattern II: Given a pattern and a string str, find if str follows the same pattern.
	 * Here non-empty substring in str.
	 * 	Examples:	pattern = "abab", str = "redblueredblue" should return true.
	 * 				pattern = "aaaa", str = "asdasdasdasd" should return true.
	 * 				pattern = "aabb", str = "xyzabcxzyabc" should return false.
	 */
	public boolean wordPatternMatch1(String pattern, String str) {
		if (pattern.length() == 0 && str.length() == 0) return true;
		if (pattern.length() == 0) return false;

		HashMap<Character, String> map = new HashMap<Character, String>();
		return helper(pattern, str, 0, 0, map);
	}

	public boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map) {
		if (i == pattern.length() && j == str.length()) return true;

		if (i >= pattern.length() || j >= str.length()) return false;

		char pat = pattern.charAt(i); // pattern char
		for (int k = j + 1; k <= str.length(); k++) {
			String sub = str.substring(j, k);
			if (!map.containsKey(pat) && !map.containsValue(sub)) {
				map.put(pat, sub);

				if (helper(pattern, str, i + 1, k, map)) return true;

				// Backtracking, remove and check from next index
				map.remove(pat);
			} else if (map.containsKey(pat) && map.get(pat).equals(sub)) {
				if (helper(pattern, str, i + 1, k, map)) return true;
			}
		}

		return false;
	}

	/* Since containsValue() method is used here, the time complexity is O(n). We can use another set to track the value 
	 * set which leads to time complexity of O(1):
	 */
	public boolean wordPatternMatch2(String pattern, String str) {
		if (pattern.length() == 0 && str.length() == 0) return true;
		if (pattern.length() == 0) return false;

		HashMap<Character, String> map = new HashMap<Character, String>();
		HashSet<String> set = new HashSet<String>();
		return helper(pattern, str, 0, 0, map, set);
	}

	public boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map,
			HashSet<String> set) {
		if (i == pattern.length() && j == str.length()) return true;

		if (i >= pattern.length() || j >= str.length()) return false;

		char c = pattern.charAt(i);
		for (int k = j + 1; k <= str.length(); k++) {
			String sub = str.substring(j, k);
			if (!map.containsKey(c) && !set.contains(sub)) {
				map.put(c, sub);
				set.add(sub);
				if (helper(pattern, str, i + 1, k, map, set)) return true;
				map.remove(c);
				set.remove(sub);
			} else if (map.containsKey(c) && map.get(c).equals(sub)) {
				if (helper(pattern, str, i + 1, k, map, set)) return true;
			}
		}

		return false;
	}

	/*
	 * Valid Word Square:
	 * Given a sequence of words, check whether it forms a valid word square.
	 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
	 * b a l l
	 * a r e a
	 * l e a d
	 * l a d y
	 */
	public boolean validWordSquare(List<String> words) {
		if (words == null || words.size() == 0) return true;
		int m = words.size();
		for (int i = 0; i < m; i++) {
			int n = words.get(i).length();
			for (int j = 0; j < n; j++)
				if (j >= m || m != n || words.get(i).charAt(j) != words.get(j).charAt(i)) return false;
		}

		return true;
	}

	/*
	 * Word Squares:
	 * Given a set of words (without duplicates), find all word squares you can build from them.
	 * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 <=k < max(numRows, numColumns).
	 */
	TrieNode root;

	public List<List<String>> wordSquares(String[] words) {
		List<List<String>> ans = new ArrayList<>();
		if (words == null || words.length == 0) return ans;
		int len = words[0].length();
		// Build Trie
		buildTrie(words);
		// Search Words
		List<String> ansBuilder = new ArrayList<>();
		for (String w : words) {
			ansBuilder.add(w);
			search(len, ans, ansBuilder);
			ansBuilder.remove(ansBuilder.size() - 1);
		}
		ans.stream().forEach(k -> System.out.print(k + ", "));
		return ans;
	}

	void buildTrie(String[] words) {
		root = new TrieNode();
		for (String w : words) {
			TrieNode cur = root;
			for (char ch : w.toCharArray()) {
				int idx = ch - 'a';
				if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
				cur.children[idx].startWith.add(w);
				cur = cur.children[idx];
			}
		}
	}

	private void search(int len, List<List<String>> ans, List<String> ansBuilder) {
		if (ansBuilder.size() == len) {
			ans.add(new ArrayList<>(ansBuilder));
			return;
		}

		int idx = ansBuilder.size();
		StringBuilder prefixBuilder = new StringBuilder();
		for (String s : ansBuilder)
			prefixBuilder.append(s.charAt(idx));
		List<String> startWith = findByPrefix(prefixBuilder.toString());
		for (String sw : startWith) {
			ansBuilder.add(sw);
			search(len, ans, ansBuilder);
			ansBuilder.remove(ansBuilder.size() - 1);
		}
	}

	List<String> findByPrefix(String prefix) {
		List<String> ans = new ArrayList<>();
		TrieNode cur = root;
		for (char ch : prefix.toCharArray()) {
			int idx = ch - 'a';
			if (cur.children[idx] == null) return ans;

			cur = cur.children[idx];
		}
		ans.addAll(cur.startWith);
		return ans;
	}

	/* Word Boggle:
	 * Word Search I - Search one word
	 * Given a 2D board and a "word", find if the word exists in the grid.The word can be constructed from 
	 * letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
	 * The same letter cell may not be used more than once.
	 * Example:
	 * board =[['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E']]
	 * Given word = "ABCCED", return true.
	 * Given word = "SEE", return true.
	 */
	public boolean wordSearch(char[][] board, String str) {
		if (str.length() == 0 || board.length == 0 || board[0].length == 0) return false;

		int row = board.length, col = board[0].length;

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (str.charAt(0) == board[i][j]) if (dfsSearch1(board, str, i, j, 0)) return true;

		return false;
	}

	public boolean dfsSearch1(char[][] board, String word, int i, int j, int index) {
		int row = board.length, col = board[0].length;

		if (i < 0 || i >= row || j < 0 || j >= col || index >= word.length()) return false;

		if (word.charAt(index) == board[i][j]) {
			if (index == word.length() - 1) return true;
			char temp = board[i][j];
			board[i][j] = '#'; // Avoid to revisit the same value

			if (dfsSearch1(board, word, i - 1, j, index + 1) || dfsSearch1(board, word, i + 1, j, index + 1)
					|| dfsSearch1(board, word, i, j - 1, index + 1) || dfsSearch1(board, word, i, j + 1, index + 1)) {
				board[i][j] = temp;
				return true;
			}
			board[i][j] = temp;
		}

		return false;
	}

	/* Word Search II - Search array of words
	 * Given a 2D board and a "list of words" from the dictionary, find all words in the board.
	 */
	public List<String> wordSearchII1(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();
		if (words.length == 0 || board.length == 0 || board[0].length == 0) return result;

		HashSet<String> set = new HashSet<>(); // Set is used to remove the duplicate word
		for (String word : words)
			if (!set.add(word) && isExist(board, word)) result.add(word);

		result.stream().forEach(k -> System.out.print(k + " "));
		return result;
	}

	public boolean isExist(char[][] board, String word) {
		int row = board.length, col = board[0].length;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (word.charAt(0) == board[i][j]) if (dfsSearch1(board, word, i, j, 0)) return true;
		return false;
	}

	public List<String> wordSearchII2(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();

		// Build Trie datastructure
		TrieNode root = buildTrie2(words);

		// dfs search
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				dfsSearch(board, root, i, j, result);

		result.stream().forEach(k -> System.out.print(k + " "));

		return result;
	}

	// Insert all the words in the Trie DS
	public TrieNode buildTrie2(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode curr = root;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if (curr.children[index] == null) curr.children[index] = new TrieNode();
				curr = curr.children[index];
			}
			curr.word = word;
		}
		return root;
	}

	public void dfsSearch(char[][] board, TrieNode root, int i, int j, List<String> result) {
		int rSize = board.length, cSize = board[0].length;
		// Row & col Validation
		if (i < 0 || i >= rSize || j < 0 || j >= cSize) return;
		// Trie Validation
		char ch = board[i][j];
		if (ch == '#' || root.children[ch - 'a'] == null) return;

		root = root.children[ch - 'a'];
		if (root.word != null) {
			result.add(root.word);
			root.word = null;
		}

		board[i][j] = '#';

		dfsSearch(board, root, i, j - 1, result);
		dfsSearch(board, root, i, j + 1, result);
		dfsSearch(board, root, i - 1, j, result);
		dfsSearch(board, root, i + 1, j, result);

		board[i][j] = ch;
	}

	/* Word Ladder: Find the ladder length
	 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence 
	 * from beginWord to endWord, such that:
	 *    Only one letter can be changed at a time.
	 *    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
	 *    Input: beginWord = "hit", endWord = "cog"; wordList = ["hot","dot","dog","lot","log","cog"]; 
	 *    Output: 5;
	 *    Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
	 */
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		LinkedList<WordNode> queue = new LinkedList<>();
		queue.add(new WordNode(beginWord, 1));
		Set<String> wordDict = new HashSet<>(wordList);

		while (!queue.isEmpty()) {
			WordNode top = queue.remove();

			if (top.word.equals(endWord)) return top.count;
			char[] arr = top.word.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char temp = arr[i];
					if (arr[i] != c) arr[i] = c;
					String newStr = new String(arr);
					if (wordDict.contains(newStr)) {
						queue.add(new WordNode(newStr, top.count + 1));
						wordDict.remove((Object) newStr);
					}
					arr[i] = temp;
				}
			}
		}
		return 0;
	}

	/*
	 * Word Ladder II: Find ladder length and display the transformation
	 * Find all shortest transformation sequence(s) from beginWord to endWord, such that:
	 *    Input: beginWord = "hit", endWord = "cog"; wordList = ["hot","dot","dog","lot","log","cog"]
	 *    Output: [["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]]
	 */
	public List<List<String>> findLadders(String start, String end, List<String> dict) {
		List<List<String>> result = new ArrayList<List<String>>();

		LinkedList<WordNode> queue = new LinkedList<WordNode>();
		queue.add(new WordNode(start, 1, null));

		// dict.add(end);

		int minStep = 0;

		HashSet<String> visited = new HashSet<String>();
		HashSet<String> unvisited = new HashSet<String>();
		unvisited.addAll(dict);

		int preNumSteps = 0;

		while (!queue.isEmpty()) {
			WordNode top = queue.remove();
			String word = top.word;
			int currNumSteps = top.count;

			if (word.equals(end)) {
				if (minStep == 0) minStep = top.count;

				if (top.count == minStep && minStep != 0) {
					// nothing
					ArrayList<String> t = new ArrayList<String>();
					t.add(top.word);
					while (top.prev != null) {
						t.add(0, top.prev.word);
						top = top.prev;
					}
					result.add(t);
					continue;
				}

			}

			// Why this???
			if (preNumSteps < currNumSteps) unvisited.removeAll(visited);

			preNumSteps = currNumSteps;

			char[] arr = word.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char temp = arr[i];
					if (arr[i] != c) {
						arr[i] = c;
					}

					String newWord = new String(arr);
					if (unvisited.contains(newWord)) {
						queue.add(new WordNode(newWord, top.count + 1, top));
						visited.add(newWord);
					}

					arr[i] = temp;
				}
			}
		}

		return result;
	}

	/*
	 * String Deletion: Given a String and dictionary hashset, write a function to determine the minimum no
	 * of characters to delete to make a word.
	 * Eg: dict = {a, aa, aaa}; 
	 * 		query = abc, o/p: 2; query: aac, o/p: 1;
	 */
	// Solution: BFS; Time-O(n!) because substring Time: n * n-1 * n-2... 1)
	public int stringDeletion(String query, HashSet<String> dict) {
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.add(query);
		while (!queue.isEmpty()) {
			String top = queue.poll();
			if (dict.contains(top)) return query.length() - top.length();
			// Check for all the substring
			for (int i = 0; i < top.length(); i++) {
				String subStr = top.substring(0, i) + top.substring(i + 1, top.length());
				if (subStr.length() > 0 && !visited.contains(subStr)) {
					visited.add(subStr);
					queue.add(subStr);
				}
			}
		}
		return -1;
	}

	/*
	 * Word Break I:
	 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can
	 * be segmented into a space-separated sequence of one or more dictionary words.
	 * 	Note:The same word in the dictionary may be reused multiple times in the segmentation. You may assume the 
	 * dictionary does not contain duplicate words.
	 * 	Example 1:	Input: s = "leetcode", wordDict = ["leet", "code"]	Output: true
	 * 	Explanation: Return true because "leetcode" can be segmented as "leet code".
	 * 	Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] Output: false 
	 */
	// Using DFS Search: It throws TLE
	public boolean wordBreakI1(String s, List<String> wordDict) {
		return wordBreakHelper(s, wordDict, 0);
	}

	public boolean wordBreakHelper(String s, List<String> dict, int start) {
		if (start == s.length()) return true;

		int end = 0, len = 0;
		for (String word : dict) {
			len = word.length();
			end = start + len;

			if (end > s.length()) continue;

			if (s.substring(start, end).equals(word)) if (wordBreakHelper(s, dict, end)) return true;
		}

		return false;
	}

	// DP: Using string length & dict size; Time: O(string length * dict size).
	public boolean wordBreakI2(String s, List<String> wordDict) {
		boolean[] lookup = new boolean[s.length() + 1];
		lookup[0] = true;
		for (int i = 0; i < s.length(); i++) {
			if (lookup[i]) {
				int end = 0, len = 0;
				for (String word : wordDict) {
					len = word.length();
					end = i + len;
					if (end > s.length()) continue;
					if (lookup[end]) continue;

					if (s.substring(i, end).equals(word)) lookup[end] = true;
				}
			}
		}
		return lookup[s.length()];
	}

	// DP: Using only string length; Time: O(string length * string length).
	public boolean wordBreakI3(String s, List<String> wordDict) {
		int n = s.length();
		boolean[] lookup = new boolean[n + 1];
		lookup[0] = true;

		for (int i = 0; i < n; i++) {
			if (lookup[i]) {
				for (int j = i + 1; j <= n; j++)
					if (wordDict.contains(s.substring(i, j))) lookup[j] = true;
			}
		}

		return lookup[n];
	}

	/*
	 * Word Break II:
	 * Return all such possible sentences.
	 * Example 1: 
	 *  Input: s = "pineapplepenapple"; wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
	 *  Output:["pine apple pen apple", "pineapple pen apple","pine applepen apple"]
	 *  Explanation: Note that you are allowed to reuse a dictionary word.
	 */
	// Using DFS Search: It throws TLE
	public List<String> wordBreakII1(String s, List<String> wordDict) {
		List<String> result = new ArrayList<>();
		wordBreakHelper(s, wordDict, 0, "", result);
		return result;
	}

	public void wordBreakHelper(String s, List<String> dict, int start, String str, List<String> result) {
		if (start == s.length()) {
			result.add(str.trim());
			return;
		}

		int end = 0, len = 0;
		for (String word : dict) {
			len = word.length();
			end = start + len;

			if (end > s.length()) continue;

			String substr = s.substring(start, end);
			if (substr.equals(word)) wordBreakHelper(s, dict, end, str + " " + substr, result);

		}
	}

	// DP: Using only string length; Time: O(string length * string length).
	public List<String> wordBreakII2(String s, List<String> wordDict) {
		int n = s.length();
		List<String>[] lookup = new ArrayList[n + 1];
		lookup[0] = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (lookup[i] != null) {
				for (int j = i + 1; j <= n; j++) {
					String subStr = s.substring(i, j);
					if (wordDict.contains(subStr)) {
						if (lookup[j] == null) lookup[j] = new ArrayList<>();
						lookup[j].add(subStr);
					}
				}
			}
		}

		List<String> result = new ArrayList<>();
		if (lookup[n] == null) return result;
		dfs(lookup, result, "", s.length());

		return result;
	}

	public void dfs(List<String>[] lookup, List<String> result, String str, int i) {
		if (i == 0) {
			result.add(str.trim());
		} else {
			for (String word : lookup[i])
				dfs(lookup, result, word + " " + str, i - word.length());
		}
	}

	/* Unique Word Abbreviation:
	 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation 
	 * is unique if no other word from the dictionary has the same abbreviation.
	 * Example:Given dictionary = [ "deer", "door", "cake", "card" ]
	 * isUnique("dear") -> false; isUnique("cart") -> true
	 */
	public boolean isUnique(Map<String, String> map, String word) {
		String abb = getAbbrevation(word);
		return (!map.containsKey(abb) || map.get(abb).equals(word));
	}

	public String getAbbrevation(String word) {
		int n = word.length();
		if (n <= 2) return word;

		return String.valueOf(word.charAt(0) + Integer.toString(n - 2) + word.charAt(n - 1));
	}

	public Map<String, String> buildDictionary(String[] strings) {
		Map<String, String> map = new HashMap<>();

		for (String str : strings) {
			String abb = getAbbrevation(str);
			// If there is any duplicate str, put empty or put str
			if (map.containsKey(abb)) map.put(abb, "");
			else map.put(abb, str);
		}

		return map;
	}

	/* Minimum Unique Word Abbreviation:
	 * A string such as "word" contains the following abbreviations: ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd",
	 * "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"] 
	 * Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the 
	 * smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary. 
	 * Each number or letter in the abbreviation is considered length = 1. 
	 * For example, the abbreviation "a32bc" has length = 4. Examples: "apple", ["blade"] -> "a4" (because "5" or
	 * "4e" conflicts with "blade") "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", 
	 * "a3e", "2p2", "3le", "3l1").
	 */
	public String minAbbreviation(String target, String[] dictionary) {
		Set<String> visited = new HashSet<>();
		PriorityQueue<Abbr> q = new PriorityQueue<>((a, b) -> a.len - b.len);
		int len = target.length();
		String first = "";

		for (int i = 0; i < len; i++)
			first += "*";

		q.offer(new Abbr(first, 1));
		while (!q.isEmpty()) {
			Abbr ab = q.poll();
			String abbr = ab.abbr;
			boolean conflict = false;
			for (String word : dictionary) {
				if (word.length() == len && isConflict(abbr, word)) {
					conflict = true;
					break;
				}
			}
			if (conflict) generateAbbr(target, abbr, visited, q);
			else return NumAbbr(abbr);
		}

		return null;
	}

	boolean isConflict(String abbr, String str) {
		for (int i = 0; i < abbr.length(); i++)
			if (abbr.charAt(i) != '*' && str.charAt(i) != abbr.charAt(i)) return false;
		return true;
	}

	void generateAbbr(String str, String abbr, Set<String> visited, PriorityQueue<Abbr> q) {
		char[] temp = abbr.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == '*') {
				temp[i] = str.charAt(i);
				String next = new String(temp);
				if (!visited.contains(next)) {
					q.offer(new Abbr(next, abbrLength(next)));
					visited.add(next);
				}
				temp[i] = '*';
			}
		}
	}

	int abbrLength(String abbr) {
		int ret = 0, star = 0;
		for (char c : abbr.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				ret += 1 + star;
				star = 0;
			} else if (c == '*') {
				star = 1;
			}
		}
		return ret + star;
	}

	String NumAbbr(String abbr) {
		String ret = "";
		int count = 0;
		for (char c : abbr.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				if (count > 0) {
					ret += count;
					count = 0;
				}
				ret += c;
			} else {
				count++;
			}
		}
		if (count > 0) ret += count;
		return ret;
	}

	/* Maximum Product of Word Lengths:
	 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not 
	 * share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
	 * Example 1: Input: ["abcw","baz","foo","bar","xtfn","abcdef"]	Output: 16
	 * Explanation: The two words can be "abcw", "xtfn".
	 * Example 2: Input: ["a","ab","abc","d","cd","bcd","abcd"]	Output: 4
	 * Explanation: The two words can be "ab", "cd".
	 * Example 3: Input: ["a","aa","aaa","aaaa"] Output: 0
	 * Explanation: No such pair of words.
	 * The solution is calculated by doing a product of the length of each string to every other string. Anyhow the
	 * constraint given is that the two strings should not have any common character. This is taken care by creating a
	 * unique number for every string. Image a an 32 bit integer where 0 bit corresponds to 'a', 1st bit corresponds to
	 * 'b' and so on. Thus if two strings contain the same character when we do and "AND" the result will not be zero
	 * and we can ignore that case.
	 */
	public int maxProduct(String[] words) {
		int n = words.length;
		int[] checker = new int[n];
		int masks = 0; // Variable to enable the bit based on the char
		for (int i = 0; i < n; i++) {
			masks = 0;
			for (int j = 0; j < words[i].length(); j++) {
				masks |= 1 << (words[i].charAt(j) - 'a');
			}
			checker[i] = masks;
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((checker[i] & checker[j]) == 0) // 0 means -> There is no same char b/w the strings
					max = Math.max(max, (words[i].length() * words[j].length()));
			}
		}

		return max;
	}

	/*
	 * Top K Frequent Words:
	 * Given a non-empty list of words, return the k most frequent elements. Your answer should be sorted by frequency
	 * from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
	 * Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2; Output: ["i", "love"]
	 */
	// Approach1: using Map & Sorting -> Time Complexity - O(nlogn)
	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> count = new HashMap<>();
		for (String word : words) {
			count.put(word, count.getOrDefault(word, 0) + 1);
		}
		List<String> candidates = new ArrayList<>(count.keySet());
		Collections.sort(candidates,
				(w1, w2) -> count.get(w1).equals(count.get(w2)) ? w1.compareTo(w2) : count.get(w2) - count.get(w1));

		return candidates.subList(0, k);
	}

	// Approach2: using Map & Heap -> Time Complexity - O(nlogk)
	public List<String> topKFrequent2(String[] words, int k) {
		if (words.length == 0 || k == 0) return null;

		HashMap<String, Integer> map = new HashMap<>();
		for (String word : words)
			map.put(word, map.getOrDefault(word, 0) + 1);

		PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> {
			if (a.getValue() == b.getValue()) return a.getKey().compareTo(b.getKey());
			return b.getValue() - a.getValue();
		});

		for (Map.Entry<String, Integer> entry : map.entrySet())
			queue.add(entry);

		List<String> result = new ArrayList<>();
		while (!queue.isEmpty() && result.size() < k) {
			result.add(queue.poll().getKey());
		}
		return result;
	}

	// Crossword Puzzle: DFS & Backtracking
	public char[][] solvePuzzle(char[][] grid, String words) {
		return search(grid, Arrays.stream(words.split(";")).collect(Collectors.toSet()), 0, 0, 0);
	}

	static final int	SIZE		= 10;
	static final int[]	R_OFFSETS	= { 0, 1 };
	static final int[]	C_OFFSETS	= { 1, 0 };

	public char[][] search(char[][] grid, Set<String> words, int r, int c, int direction) {
		if (r == SIZE) return grid;
		if (c == SIZE) return search(grid, words, r + 1, 0, 0);
		if (direction == R_OFFSETS.length) return search(grid, words, r, c + 1, 0);

		// Count the length of the path in the grid
		int insertLength = countInsertLength(grid, r, c, direction);

		if (insertLength > 1) {
			for (String word : new ArrayList<>(words)) {
				// Validate the word can be inserted in grid
				if (canInsertWord1(grid, r, c, direction, insertLength, word)) {
					List<Integer> insertOffsets = new ArrayList<Integer>();

					for (int i = 0; i < insertLength; i++) {
						int row = r + R_OFFSETS[direction] * i;
						int col = c + C_OFFSETS[direction] * i;

						if (grid[row][col] == '-') {
							grid[row][col] = word.charAt(i);

							insertOffsets.add(i);
						}
					}
					words.remove(word);

					char[][] subResult = search(grid, words, r, c, direction + 1);

					if (subResult != null) return subResult;

					// Backtracking: Reassign the values
					words.add(word);

					for (int insertOffset : insertOffsets) {
						// Calculate row & col using prev offset
						int row = r + R_OFFSETS[direction] * insertOffset;
						int col = c + C_OFFSETS[direction] * insertOffset;

						grid[row][col] = '-';
					}
				}
			}

			return null;
		} else {
			return search(grid, words, r, c, direction + 1);
		}
	}

	public int countInsertLength(char[][] grid, int r, int c, int direction) {
		int prevRow = r - R_OFFSETS[direction];
		int prevCol = c - C_OFFSETS[direction];

		if (prevRow >= 0 && prevRow < SIZE && prevCol >= 0 && prevCol < SIZE && grid[prevRow][prevCol] != '+') return 0;

		int insertLength = 0;
		while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] != '+') {
			insertLength++;
			r += R_OFFSETS[direction];
			c += C_OFFSETS[direction];
		}
		return insertLength;
	}

	public boolean canInsertWord1(char[][] grid, int r, int c, int direction, int insertLength, String word) {
		if (word.length() != insertLength) return false;

		for (int k = 0; k < word.length(); k++) {
			int row = r + R_OFFSETS[direction] * k;
			int col = c + C_OFFSETS[direction] * k;
			if (grid[row][col] != '-' && grid[row][col] != word.charAt(k)) return false;
		}

		return true;
	}

	public boolean canInsertWord2(char[][] grid, int r, int c, int direction, int insertLength, String word) {

		return word.length() == insertLength && IntStream.range(0, word.length()).allMatch(k -> {
			int row = r + R_OFFSETS[direction] * k;
			int col = c + C_OFFSETS[direction] * k;

			return grid[row][col] == '-' || grid[row][col] == word.charAt(k);
		});
	}
}

class Abbr {
	String	abbr;
	int		len;

	Abbr(String abbr, int len) {
		this.abbr = abbr;
		this.len = len;
	}
}