package com.common.model;

public class ListNode {

	public int		data;
	public ListNode	next, prev;
	public ListNode	right, down, random;

	public ListNode(int data) {
		this.data = data;
		this.prev = this.next = null;
		this.right = this.down = this.random = null;
	}

}
