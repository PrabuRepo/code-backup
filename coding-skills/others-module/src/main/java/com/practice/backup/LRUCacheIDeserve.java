package com.practice.backup;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>IDeserve <br>
 * <a href="<a class="vglnk" href="https://www.youtube.com/c/IDeserve" rel=
 * "nofollow"><span>https</span><span>://</span><span>www</span><span>.</span><span>youtube</span><span>.</span><span>com</span><span>/</span><span>c</span><span>/</span><span>IDeserve</span></a>"><a
 * class="vglnk" href="https://www.youtube.com/c/IDeserve" rel=
 * "nofollow"><span>https</span><span>://</span><span>www</span><span>.</span><span>youtube</span><span>.</span><span>com</span><span>/</span><span>c</span><span>/</span><span>IDeserve</span></a></a>
 * Implement Least Recently Used (LRU) cache
 *
 * @author Saurabh
 */
public class LRUCacheIDeserve {

	private DoublyLinkedList	pageList;
	private Map<Integer, CNode>	pageMap;
	private final int			cacheSize;

	public LRUCacheIDeserve(int cacheSize) {
		this.cacheSize = cacheSize;
		pageList = new DoublyLinkedList(cacheSize);
		pageMap = new HashMap<Integer, CNode>();
	}

	public void accessPage(int pageNumber) {
		CNode pageCNode = null;
		if (pageMap.containsKey(pageNumber)) {
			// If page is present in the cache, move the page to the start of list
			pageCNode = pageMap.get(pageNumber);
			pageList.movePageToHead(pageCNode);
		} else {
			// If the page is not present in the cache, add the page to the cache
			if (pageList.getCurrSize() == pageList.getSize()) {
				// If cache is full, we will remove the tail from the cache pageList
				// Remove it from map too
				pageMap.remove(pageList.getTail().getPageNumber());
			}
			pageCNode = pageList.addPageToList(pageNumber);
			pageMap.put(pageNumber, pageCNode);
		}
	}

	public void printCacheState() {
		pageList.printList();
		System.out.println();
	}

	public static void main(String[] args) {
		int cacheSize = 4;
		LRUCacheIDeserve cache = new LRUCacheIDeserve(cacheSize);
		cache.accessPage(4);
		cache.printCacheState();
		cache.accessPage(2);
		cache.printCacheState();
		cache.accessPage(1);
		cache.printCacheState();
		cache.accessPage(1);
		cache.printCacheState();
		cache.accessPage(4);
		cache.printCacheState();
		cache.accessPage(3);
		cache.printCacheState();
		cache.accessPage(7);
		cache.printCacheState();
		cache.accessPage(8);
		cache.printCacheState();
		cache.accessPage(3);
		cache.printCacheState();
	}
}

class DoublyLinkedList {

	private final int	size;
	private int			currSize;
	private CNode		head;
	private CNode		tail;

	public DoublyLinkedList(int size) {
		this.size = size;
		currSize = 0;
	}

	public CNode getTail() {
		return tail;
	}

	public void printList() {
		if (head == null) {
			return;
		}
		CNode tmp = head;
		while (tmp != null) {
			System.out.print(tmp);
			tmp = tmp.getNext();
		}
	}

	public CNode addPageToList(int pageNumber) {
		CNode pageCNode = new CNode(pageNumber);
		if (head == null) {
			head = pageCNode;
			tail = pageCNode;
			currSize = 1;
			return pageCNode;
		} else if (currSize < size) {
			currSize++;
		} else {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		pageCNode.setNext(head);
		head.setPrev(pageCNode);
		head = pageCNode;
		return pageCNode;
	}

	public void movePageToHead(CNode pageCNode) {
		if (pageCNode == null || pageCNode == head) {
			return;
		}

		if (pageCNode == tail) {
			tail = tail.getPrev();
			tail.setNext(null);
		}

		CNode prev = pageCNode.getPrev();
		CNode next = pageCNode.getNext();
		prev.setNext(next);

		if (next != null) {
			next.setPrev(prev);
		}

		pageCNode.setPrev(null);
		pageCNode.setNext(head);
		head.setPrev(pageCNode);
		head = pageCNode;
	}

	public int getCurrSize() {
		return currSize;
	}

	public void setCurrSize(int currSize) {
		this.currSize = currSize;
	}

	public CNode getHead() {
		return head;
	}

	public void setHead(CNode head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}
}

class CNode {

	private int		pageNumber;
	private CNode	prev;
	private CNode	next;

	public CNode(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int data) {
		this.pageNumber = data;
	}

	public CNode getPrev() {
		return prev;
	}

	public void setPrev(CNode prev) {
		this.prev = prev;
	}

	public CNode getNext() {
		return next;
	}

	public void setNext(CNode next) {
		this.next = next;
	}

	public String toString() {
		return pageNumber + "  ";
	}
}