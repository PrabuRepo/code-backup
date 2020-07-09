package com.common.model;

/*
 * Binary Tree Node
 */
public class TreeNode {
	public int		data;
	public char		ch;
	public TreeNode	left, right, next;
	public int		count;

	public TreeNode(int data) {
		this.data = data;
		this.left = this.right = null;
		this.next = null;
	}

	public TreeNode(int data, int count) {
		this.data = data;
		this.count = count;
	}

	public TreeNode(char ch) {
		this.ch = ch;
		left = right = null;
	}
}