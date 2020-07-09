package com.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {
	// To implement Trie datastructure using map
	public Map<Character, TrieNode> childNodes;

	// To implement Trie datastructure using array
	public TrieNode[] children;

	public List<String> startWith;

	public boolean isEndOfWord;

	// Added for word Boggle problem
	public String word;

	public TrieNode() {
		childNodes = new HashMap<>();
		children = new TrieNode[26];
		startWith = new ArrayList<>();
		isEndOfWord = false;
	}
}
