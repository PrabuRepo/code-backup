package com.common.model;

public class WordNode {
	public String	word;
	public int		count;
	public WordNode	prev;

	public WordNode(String w, int c, WordNode prev) {
		this.word = w;
		this.count = c;
		this.prev = prev;
	}

	public WordNode(String w, int c) {
		this.word = w;
		this.count = c;
	}
}