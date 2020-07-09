package com.consolidated.problems.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class T9Dictionary {
	public static void main(String[] args) {
		System.out.println("Approach1(Brute Force Approach): ");
		ArrayList<String> words = getValidT9Words("33835676368", AssortedMethods.getWordListAsHashSet());
		for (String w : words)
			System.out.println(w);

		System.out.println("Approach2(Using Trie): ");
		words = getValidT9Words("8733", AssortedMethods.getTrieDictionary());
		for (String w : words)
			System.out.println(w);

		System.out.println("Approach3(Using Preprocess in HashTable): ");
		HashMapList<String, String> dictionary = initializeDictionary(AssortedMethods.getListOfWords());
		words = getValidT9Words("33835676368", dictionary);
		for (String w : words)
			System.out.println(w);
	}

	// Approach1:
	public static ArrayList<String> getValidT9Words(String number, HashSet<String> wordList) {
		ArrayList<String> results = new ArrayList<String>();
		getValidWords(number, 0, "", wordList, results);
		return results;
	}

	public static void getValidWords(String number, int index, String prefix, HashSet<String> wordSet,
			ArrayList<String> results) {
		/* If it's a complete word, print it. */
		if (index == number.length()) {
			if (wordSet.contains(prefix)) {
				results.add(prefix);
			}
			return;
		}

		/* Get characters that match this digit */
		char digit = number.charAt(index);
		char[] letters = getT9Chars(digit);

		/* Go through all remaining options. */
		if (letters != null) {
			for (char letter : letters) {
				getValidWords(number, index + 1, prefix + letter, wordSet, results);
			}
		}
	}

	// Approach2: Using Trie
	public static ArrayList<String> getValidT9Words(String number, Trie trie) {
		ArrayList<String> results = new ArrayList<String>();
		getValidWords(number, 0, "", trie.getRoot(), results);
		return results;
	}

	public static void getValidWords(String number, int index, String prefix, TrieNode trieNode,
			ArrayList<String> results) {
		/* If it's a complete word, print it. */
		if (index == number.length()) {
			if (trieNode.terminates()) { // Is complete word
				results.add(prefix);
			}
			return;
		}

		/* Get characters that match this digit */
		char digit = number.charAt(index);
		char[] letters = getT9Chars(digit);

		/* Go through all remaining options. */
		if (letters != null) {
			for (char letter : letters) {
				TrieNode child = trieNode.getChild(letter);
				if (child != null) { /* If there are words that start with prefix + letter, continue */
					getValidWords(number, index + 1, prefix + letter, child, results);
				}
			}
		}
	}

	public static ArrayList<String> getValidT9Words(String numbers, HashMapList<String, String> dictionary) {
		return dictionary.get(numbers);
	}

	/* Convert from a string to its T9 representation. */
	public static String convertToT9(String word, HashMap<Character, Character> letterToNumberMap) {
		StringBuilder sb = new StringBuilder();
		for (char c : word.toCharArray()) {
			if (letterToNumberMap.containsKey(c)) {
				char digit = letterToNumberMap.get(c);
				sb.append(digit);
			}
		}
		return sb.toString();
	}

	/* Convert mapping of number->letters into letter->number */
	public static HashMap<Character, Character> createLetterToNumberMap() {
		HashMap<Character, Character> letterToNumberMap = new HashMap<Character, Character>();
		for (int i = 0; i < t9Letters.length; i++) {
			char[] letters = t9Letters[i];
			if (letters != null) {
				for (char letter : letters) {
					char c = Character.forDigit(i, 10);
					letterToNumberMap.put(letter, c);
				}
			}
		}
		return letterToNumberMap;
	}

	/* Create a hash table that maps from a number to all words that
	 * have this numerical representation. */
	public static HashMapList<String, String> initializeDictionary(String[] words) {
		/* Create hash table that maps from a letter to the digit */
		HashMap<Character, Character> letterToNumberMap = createLetterToNumberMap();

		/* Create word -> number map */
		HashMapList<String, String> wordsToNumbers = new HashMapList<String, String>();
		for (String word : words) {
			String numbers = convertToT9(word, letterToNumberMap);
			wordsToNumbers.put(numbers, word);
		}
		return wordsToNumbers;
	}

	// Common Utils

	public static char[][] t9Letters = { null, // 0
			null, // 1
			{ 'a', 'b', 'c' }, // 2
			{ 'd', 'e', 'f' }, // 3
			{ 'g', 'h', 'i' }, // 4
			{ 'j', 'k', 'l' }, // 5
			{ 'm', 'n', 'o' }, // 6
			{ 'p', 'q', 'r', 's' }, // 7
			{ 't', 'u', 'v' }, // 8
			{ 'w', 'x', 'y', 'z' } // 9
	};

	public static char[] getT9Chars(char digit) {
		if (!Character.isDigit(digit)) { return null; }
		int dig = Character.getNumericValue(digit) - Character.getNumericValue('0');
		return t9Letters[dig];
	}
}

/* Implements a trie. We store the input list of words in tries so
 * that we can efficiently find words with a given prefix. 
 */
class Trie {
	// The root of this trie.
	private TrieNode root;

	/* Takes a list of strings as an argument, and constructs a trie that stores these strings. */
	public Trie(ArrayList<String> list) {
		root = new TrieNode();
		for (String word : list) {
			root.addWord(word);
		}
	}

	/* Takes a list of strings as an argument, and constructs a trie that stores these strings. */
	public Trie(String[] list) {
		root = new TrieNode();
		for (String word : list) {
			root.addWord(word);
		}
	}

	/* Checks whether this trie contains a string with the prefix passed
	 * in as argument.
	 */
	public boolean contains(String prefix, boolean exact) {
		TrieNode lastNode = root;
		int i = 0;
		for (i = 0; i < prefix.length(); i++) {
			lastNode = lastNode.getChild(prefix.charAt(i));
			if (lastNode == null) { return false; }
		}
		return !exact || lastNode.terminates();
	}

	public boolean contains(String prefix) {
		return contains(prefix, false);
	}

	public TrieNode getRoot() {
		return root;
	}
}

/* One node in the trie. Most of the logic of the trie is implemented
 * in this class.
 */
class TrieNode {
	/* The children of this node in the trie.*/
	private HashMap<Character, TrieNode>	children;
	private boolean							terminates	= false;

	// The character stored in this node as data.
	private char character;

	/* Constructs a trie node and stores this character as the node's value.
	 * Initializes the list of child nodes of this node to an empty hash map. */
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
	}

	/* Constructs a trie node and stores in the node the char passed in
	 * as the argument. Initializes the list of child nodes of this
	 * node to an empty hash map.
	 */
	public TrieNode(char character) {
		this();
		this.character = character;
	}

	/* Returns the character data stored in this node. */
	public char getChar() {
		return character;
	}

	/* Add this word to the trie, and recursively create the child
	 * nodes. */
	public void addWord(String word) {
		if (word == null || word.isEmpty()) { return; }

		char firstChar = word.charAt(0);

		TrieNode child = getChild(firstChar);
		if (child == null) {
			child = new TrieNode(firstChar);
			children.put(firstChar, child);
		}

		if (word.length() > 1) {
			child.addWord(word.substring(1));
		} else {
			child.setTerminates(true);
		}
	}

	/* Find a child node of this node that has the char argument as its
	 * data. Return null if no such child node is present in the trie.
	 */
	public TrieNode getChild(char c) {
		return children.get(c);
	}

	/* Returns whether this node represents the end of a complete word. */
	public boolean terminates() {
		return terminates;
	}

	/* Set whether this node is the end of a complete word.*/
	public void setTerminates(boolean t) {
		terminates = t;
	}
}

class HashMapList<T, E> {
	private HashMap<T, ArrayList<E>> map = new HashMap<T, ArrayList<E>>();

	/* Insert item into list at key. */
	public void put(T key, E item) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<E>());
		}
		map.get(key).add(item);
	}

	/* Insert list of items at key. */
	public void put(T key, ArrayList<E> items) {
		map.put(key, items);
	}

	/* Get list of items at key. */
	public ArrayList<E> get(T key) {
		return map.get(key);
	}

	/* Check if hashmaplist contains key. */
	public boolean containsKey(T key) {
		return map.containsKey(key);
	}

	/* Check if list at key contains value. */
	public boolean containsKeyValue(T key, E value) {
		ArrayList<E> list = get(key);
		if (list == null) return false;
		return list.contains(value);
	}

	/* Get the list of keys. */
	public Set<T> keySet() {
		return map.keySet();
	}

	@Override
	public String toString() {
		return map.toString();
	}
}