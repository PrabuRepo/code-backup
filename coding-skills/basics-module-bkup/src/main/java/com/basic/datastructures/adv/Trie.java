package com.basic.datastructures.adv;

import java.util.ArrayList;
import java.util.List;

import com.common.model.TrieNode;

/*
 * Trie or Retrieval or prefix tree is a tree data structure, which is used for retrieval of a key in a dataset
 * of strings. There are various applications of this very efficient data structure such as :
 *    - Auto Complete
 *    - Spell Checker
 *    - IP Routing(Longest Prefix Search)
 *    - T9 Predective Search
 *    - Solving Word Games - Word Boggle
 *    
 *  Time Complexity for Insert, Search: O(m); m - length of string
 *  Space Complexity: O(alphabet_size * len of string * no of string)
 *  
 *  Why Trie?
 *   - Finding all keys with a common prefix.
 *   - Enumerating a dataset of strings in lexicographical order. 
 */
public class Trie {
	public static void main(String[] args) {
		Trie ob = new Trie();
		ob.testTrieNodeUsingArray();
		// ob.testTrieNodeUsingMap();
	}

	public void testTrieNodeUsingArray() {
		// Input keys (use only 'a' through 'z' and lower case)
		String keys[] = { "the", "mhe", "a", "there", "answer", "any", "by", "bye", "their" };
		Trie1 trie = new Trie1();

		System.out.println("Insert the list of strings: ");
		for (int i = 0; i < keys.length; i++)
			trie.insert(keys[i]);

		System.out.println("Search the words: ");
		System.out.println("the --- " + trie.wordSearch("the"));
		System.out.println("these --- " + trie.wordSearch("these"));
		System.out.println("their --- " + trie.wordSearch("their"));
		System.out.println("thaw --- " + trie.wordSearch("thaw"));

		System.out.println("Search the prefix: ");
		System.out.println("an --- " + trie.wordSearch("an"));
		System.out.println("an --- " + trie.startsWith("an"));
		System.out.println("by --- " + trie.wordSearch("th"));
		System.out.println("by --- " + trie.startsWith("th"));

		System.out.println("Wildcard Match: Search with special chars: ");
		System.out.println(".he --- " + trie.wildcardMatch(".he"));
		System.out.println("t.e --- " + trie.wildcardMatch("t.e"));

		System.out.println("Auto Suggestions/Auto Complete: ");
		trie.printAutoSuggestions("th");
		trie.printAutoSuggestions("yrt");
		trie.printAutoSuggestions("bye");
		trie.printAutoSuggestions("a");

		System.out.println("Delete the string: ");
		trie.delete("mhe");
		System.out.println("after deletion..");
		System.out.println("an --- " + trie.wordSearch("mhe"));

	}

	public void testTrieNodeUsingMap() {
		Trie2 trie = new Trie2();
		// Input keys (use only 'a' through 'z' and lower case)
		String keys[] = { "the", "mhe", "a", "these", "answer", "any", "by", "bye", "their" };

		for (int i = 0; i < keys.length; i++)
			trie.insertIterative(keys[i]);

		// Search for different keys
		if (trie.searchIterative("the") == true) System.out.println("the --- " + "Present in trie");
		else System.out.println("the --- " + "Not present in trie");

		if (trie.searchIterative("these") == true) System.out.println("these --- " + "Present in trie");
		else System.out.println("these --- " + "Not present in trie");

		if (trie.searchIterative("their") == true) System.out.println("their --- " + "Present in trie");
		else System.out.println("their --- " + "Not present in trie");

		if (trie.searchIterative("thaw") == true) System.out.println("thaw --- " + "Present in trie");
		else System.out.println("thaw --- " + "Not present in trie");

		// Delete Operation
		System.out.println("After delete operation");
		trie.delete("the");

		if (trie.searchIterative("the") == true) System.out.println("the --- " + "Present in trie");
		else System.out.println("the --- " + "Not present in trie");

		trie.delete("these");

		if (trie.searchIterative("these") == true) System.out.println("these --- " + "Present in trie");
		else System.out.println("these --- " + "Not present in trie");
	}
}

/**
 * Trie Implementation using Array:
 */
class Trie1 {
	TrieNode root;

	public Trie1() {
		root = new TrieNode();
	}

	/** Insert: Inserts a word into the trie. */
	public void insert(String word) {
		int index;
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			index = word.charAt(i) - 'a';
			if (current.children[index] == null) {
				current.children[index] = new TrieNode();
			}
			current = current.children[index];
		}
		// Set last node of isEndOfWord as true, to mark it as leaf
		current.isEndOfWord = true;
		current.word = word;
	}

	/** Search Whole Word: Returns if the word is in the trie. */
	public boolean wordSearch(String word) {
		TrieNode result = search(word);
		return result == null ? false : (result != null && result.isEndOfWord);

	}

	/** Prefix Search: Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		TrieNode result = search(prefix);
		return result == null ? false : true;
	}

	public TrieNode search(String word) {
		int index;
		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			index = word.charAt(i) - 'a';
			if (current.children[index] == null) return null;

			current = current.children[index];
		}
		return current;
	}

	/**
	 * Wildcard Match: Returns if the word is in the data structure. A word could contain the dot character '.' to
	 * represent any one letter.
	 */
	public boolean wildcardMatch(String word) {
		return dfsSearch(root, word, 0);
	}

	public boolean dfsSearch(TrieNode node, String word, int index) {
		if (node.isEndOfWord && word.length() == index) return true;

		if (index >= word.length()) return false;

		char ch = word.charAt(index);

		if ('.' == ch) {
			for (int i = 0; i < 26; i++) {
				if (node.children[i] != null) {
					if (dfsSearch(node.children[i], word, index + 1)) return true;
				}
			}
		} else {
			TrieNode next = node.children[ch - 'a'];
			if (next != null) return dfsSearch(next, word, index + 1);
		}

		return false;
	}

	/** Auto Suggestions: Auto-complete feature using Trie */
	/*Auto-complete feature using Trie:
	 * We are given a Trie with a set of strings stored in it. Now the user types in a prefix of his search query, we
	 * need to give him all recommendations to auto-complete his query based on the strings stored in the Trie. We
	 * assume that the Trie stores past searches by the users.
	 * For example if the Trie store {“abc”, “abcd”, “aa”, “abbbaba”} and the User types in “ab” 
	 * then he must be shown {“abc”, “abcd”, “abbbaba”}.
	 * Time: O(prefixLen + noOfStringInDictionary);
	 */

	public void printAutoSuggestions(String prefix) {
		TrieNode node = search(prefix);

		if (node == null) {
			System.out.println("Prefix not found!");
		} else {
			List<String> suggestions = new ArrayList<>();
			autoSuggestions(node, suggestions);
			suggestions.stream().forEach(k -> System.out.print(k + "-> "));
			System.out.println();
		}
	}

	// Autocomplete or Autosuggestions
	public void autoSuggestions(TrieNode node, List<String> suggestions) {
		if (node == null) return;
		if (node.isEndOfWord) suggestions.add(node.word);

		for (int i = 0; i < 26; i++) {
			if (node.children[i] != null) {
				autoSuggestions(node.children[i], suggestions);
			}
		}
	}

	/** Remove: Remove the given string. */
	public void delete(String str) {
		delete(root, str, 0);
	}

	public TrieNode delete(TrieNode node, String str, int index) {
		if (node == null) return null;

		if (index == str.length()) {
			node.isEndOfWord = false;
			if (!node.isEndOfWord && isEmpty(node)) node = null;
			return node;
		}

		int chIndex = str.charAt(index) - 'a';
		node.children[chIndex] = delete(node.children[chIndex], str, index + 1);

		if (!node.isEndOfWord && isEmpty(node)) node = null;

		return node;
	}

	private boolean isEmpty(TrieNode root) {
		if (root == null) return true;

		for (int i = 0; i < 26; i++)
			if (root.children[i] != null) return false;

		return true;
	}
}

class Trie2 {
	private TrieNode root; // Using Map Impl

	public Trie2() {
		root = new TrieNode();
	}

	public void insertIterative(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode childNode = current.childNodes.get(ch);
			if (childNode == null) {
				childNode = new TrieNode();
				current.childNodes.put(ch, childNode);
			}
			current = childNode;
		}
		current.isEndOfWord = true;
	}

	public boolean searchIterative(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			TrieNode childNode = current.childNodes.get(word.charAt(i));
			if (childNode == null) return false;
			current = childNode;
		}
		return current.isEndOfWord;
	}

	public void delete(String word) {
		delete(root, word, 0);
	}

	public boolean delete(TrieNode current, String word, int index) {
		if (word.length() == index) {
			// If EOW is false, then word is not present in the Trie DS
			if (!current.isEndOfWord) return false;
			// Reset EOW to false
			current.isEndOfWord = false;
			/*If last node of the word has childNodes, then simple reset the EOW.
			  If last node doesn't have childNodes, then below cond satisfies and keep deleting the node below*/
			return current.childNodes.size() == 0;
		}

		char ch = word.charAt(index);
		TrieNode next = current.childNodes.get(ch);
		// if char is not present in the node, then next will be null.
		if (next == null) return false;

		// Recursive call for every char in the word
		boolean deleteNodeFlag = delete(next, word, index + 1);
		// If this deleteNodeFlag is true, then remove one by one from the map.
		if (deleteNodeFlag) {
			current.childNodes.remove(ch);
			return current.childNodes.size() == 0;
		}

		return false;
	}
}