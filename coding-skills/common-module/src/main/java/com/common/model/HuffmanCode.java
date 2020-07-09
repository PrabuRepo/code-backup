package com.common.model;

public class HuffmanCode {

	public int			count;
	public char			c;
	public HuffmanCode	left;
	public HuffmanCode	right;

	public HuffmanCode(char ch, int n) {
		this.count = n;
		this.c = ch;
		this.left = null;
		this.right = null;
	}

}
