package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.common.model.TreeNode;
import com.common.model.ListNode;

public class BSTProblems {

	/************************ BST Basics ***********************/
	public TreeNode insert(TreeNode root, int data) {
		if (root == null) {
			root = new TreeNode(data);
		} else if (data <= root.data) {
			root.left = insert(root.left, data);
		} else {
			root.right = insert(root.right, data);
		}
		return root;
	}

	public TreeNode insertIterative(TreeNode root, int n) {
		TreeNode newNode = new TreeNode(n);
		if (root == null) return newNode;

		TreeNode temp = root;
		while (temp != null) {
			if (n < temp.data) {
				if (temp.left == null) {
					temp.left = newNode;
					break;
				} else temp = temp.left;
			} else {
				if (temp.right == null) {
					temp.right = newNode;
					break;
				} else temp = temp.right;
			}
		}
		return root;
	}

	public TreeNode delete(TreeNode root, int data) {
		if (root != null) {
			if (data < root.data) {
				root.left = delete(root.left, data);
			} else if (data > root.data) {
				root.right = delete(root.right, data);
			} else if (root.left != null && root.right != null) {
				// Case 3: Two child
				int minElement = findMin(root.right);
				root.data = minElement;
				root.right = delete(root.right, minElement);
			} else {
				// Case 1 & 2: No Child and One Child
				if (root.left == null) root = root.right;
				else if (root.right == null) root = root.left;
			}
		}
		return root;
	}

	public TreeNode binarySearch(TreeNode root, int data) {
		if (root == null) return null;

		if (root.data == data) {
			return root;
		} else if (data <= root.data) {
			return binarySearch(root.left, data);
		} else {
			return binarySearch(root.right, data);
		}
	}

	public TreeNode binarySearchIterative(TreeNode root, int data) {
		if (root == null) return null;

		TreeNode temp = root;
		while (temp != null) {
			if (data == temp.data) {
				return temp;
			} else if (data < temp.data) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}

		return null;
	}

	// Find the node with minimum value in a Binary Search Tree
	public int findMin(TreeNode root) {
		if (root == null) return -1;

		while (root.left != null)
			root = root.left;

		return root.data;
	}

	public int findMax(TreeNode root) {
		if (root == null) return -1;

		while (root.right != null)
			root = root.right;

		return root.data;
	}

	/* BST Traversals */
	public void preOrder(TreeNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public void inOrder(TreeNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	public void postOrder(TreeNode root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + " ");
		}
	}

	public int sizeOfBinaryTree(TreeNode root) {
		if (root == null) return 0;

		return 1 + sizeOfBinaryTree(root.left) + sizeOfBinaryTree(root.right);
	}

	/************************ Construction Problems ***********************/

	// Serialize & Deserialize - Uses Preorder
	// 1.Serialize:Uses Tree to List
	public ArrayList<Integer> serialize1(ArrayList<Integer> result, TreeNode root) {
		if (root != null) {
			result.add(root.data);
			serialize1(result, root.left);
			serialize1(result, root.right);
		}
		return result;
	}

	// 1.Deserialize: List to Tree
	public TreeNode deserialize1(Integer[] preOrder, int low, int high) {
		if (low > high) return null;
		TreeNode root = new TreeNode(preOrder[low]);
		int mid = findRight(preOrder, root.data, low, high);
		root.left = deserialize1(preOrder, low + 1, mid - 1);
		root.right = deserialize1(preOrder, mid, high);
		return root;
	}

	int findRight(Integer[] preOrder, int root, int low, int high) {
		int i;
		for (i = low; i <= high; i++) {
			if (root < preOrder[i]) break;
		}
		return i;
	}

	// 2. Serialize: Tree to String
	public String serialize2(TreeNode root) {
		if (root == null) return "";
		StringBuilder sb = new StringBuilder();
		serialize2(root, sb);
		System.out.println(sb.toString());
		return sb.toString();
	}

	public void serialize2(TreeNode root, StringBuilder sb) {
		if (root == null) return;
		sb.append(root.data + ",");
		serialize2(root.left, sb);
		serialize2(root.right, sb);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize2(String data) {
		if (data == null || data == "") return null;
		String[] str = data.split(",");
		return deserialize2(str, 0, str.length - 1);
	}

	public TreeNode deserialize2(String[] str, int l, int h) {
		if (l > h) return null;
		TreeNode root = new TreeNode(Integer.valueOf(str[l]));
		int m = findMid(str, l, h, root.data);
		root.left = deserialize2(str, l + 1, m - 1);
		root.right = deserialize2(str, m, h);
		return root;
	}

	public int findMid(String[] str, int l, int h, int data) {
		int val, i;
		for (i = l; i <= h; i++) {
			val = Integer.valueOf(str[i]);
			if (data < val) break;
		}
		return i;
	}

	// Similar to approach2
	public TreeNode deserialize21(String data) {
		if (data.isEmpty()) return null;
		Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
		return deserialize21(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public TreeNode deserialize21(Queue<String> q, int lower, int upper) {
		if (q.isEmpty()) return null;
		String s = q.peek();
		int val = Integer.parseInt(s);
		if (val < lower || val > upper) return null;
		q.poll();
		TreeNode root = new TreeNode(val);
		root.left = deserialize21(q, lower, val);
		root.right = deserialize21(q, val, upper);
		return root;
	}

	/************************ Conversion Problems ***********************/

	/*	Binary Tree to Binary Search Tree Conversion
		Convert Sorted Array to Binary Search Tree
	*/

	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0) return null;
		return arrayToBST(nums, 0, nums.length - 1);
	}

	private TreeNode arrayToBST(int[] nums, int low, int high) {
		if (low > high) return null;
		int mid = (low + high) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = arrayToBST(nums, low, mid - 1);
		root.right = arrayToBST(nums, mid + 1, high);
		return root;
	}

	// Approach1: Similar to sortedArrayToBST;
	// Time: O(nlogn) & Space:(Recursion Stack): O(n)
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return new TreeNode(head.data);
		ListNode prev = null;
		ListNode slow = head;
		ListNode fast = head;
		// Find the middle Node
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		TreeNode root = new TreeNode(slow.data);
		root.left = sortedListToBST(head);
		root.right = sortedListToBST(slow.next);
		return root;
	}

	// Another Approach: Both gives the same complexity;
	// Time: O(n) & Space(Recursion Stack): O(logn)
	ListNode node;

	public TreeNode sortedListToBST2(ListNode head) {
		if (head == null) return null;
		int size = length(head);
		node = head;
		return buildTree(0, size - 1);
	}

	public TreeNode buildTree(int l, int h) {
		if (l > h) return null;
		int m = l + (h - l) / 2;
		TreeNode left = buildTree(l, m - 1);
		TreeNode root = new TreeNode(node.data);
		root.left = left;
		node = node.next;
		root.right = buildTree(m + 1, h);
		return root;
	}

	public int length(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}

	/************************ Checking Problems ***************************/
	public boolean isBST(TreeNode root) {
		// return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return isBST1(root, null, null);
	}

	/*
	 * It handles all the corner cases:
	 *     Eg: [-2147483648,null,2147483647], [1,1],[-2147483648], [2147483647], [-2147483648,-2147483648],
	 *      [2147483647, null,2147483647]
	 */
	private boolean isBST1(TreeNode root, TreeNode minNode, TreeNode maxNode) {
		if (root == null) return true;
		if (minNode != null && minNode.data >= root.data) return false;
		else if (maxNode != null && maxNode.data <= root.data) return false;
		return isBST1(root.left, minNode, root) && isBST1(root.right, root, maxNode);
	}

	/* This method does not work for corner cases
	 *   Eg: [-2147483648,null,2147483647], [1,1],[-2147483648], [2147483647],[-2147483648,-2147483648], 
	 *   [2147483647, null,2147483647]
	 */
	public boolean isBST(TreeNode root, int min, int max) {
		if (root == null) return true;

		/*if (root.data <= min && root.data > max) 
		  return false;*/
		if (root.data <= min || root.data >= max) return false;

		return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
		// return isBST(root.left, min, root.data - 1) && isBST(root.right, root.data + 1, max);
	}

	/**************************** Searching/Path Problems **************************/

	public int inOrderPredecessor(TreeNode root, int key) {
		if (root == null) return -1;

		if (key == root.data) {
			TreeNode leftSubTree = root.left;
			if (leftSubTree != null) {
				// Find the next node in the left subtree(Pred should be present in the left sub tree)
				while (leftSubTree.right != null)// Keep moving to right side, to find the prev(successor) node in BST
					leftSubTree = leftSubTree.right;
				return leftSubTree.data;
			} else {
				return -1;
			}
		} else if (key < root.data) {
			return inOrderPredecessor(root.left, key);
		} else {
			int pred = inOrderPredecessor(root.right, key);
			if (pred == -1) // If there is no element in leftSubtree, then root element should be previous element
				pred = root.data;
			return pred;
		}
	}

	public int inOrderSuccessor(TreeNode root, int key) {
		if (root == null) return -1;

		if (key == root.data) {
			// Find the next node in the right subtree
			TreeNode rightSubTree = root.right;
			if (rightSubTree != null) {
				while (rightSubTree.left != null)// Keep moving to left side, to find the next(successor) node in BST
					rightSubTree = rightSubTree.left;
				return rightSubTree.data;
			}
			return -1;
		} else if (key < root.data) {
			int successor = inOrderSuccessor(root.left, key);
			if (successor == -1) // If there is no element in rightSubtree, then root element should be next element
				successor = root.data;
			return successor;
		} else {
			return inOrderSuccessor(root.right, key);
		}
	}

	/**
	 * Closest Binary Search Tree Value: Given a non-empty binary search tree and a target value, find the value in the
	 * BST that is closest to the target. Note: Given target value is a floating point. You are guaranteed to have only
	 * one unique value in the BST that is closest to the target. Use: Binary Search Alg
	 */

	int		closest;
	double	minDiff	= Double.MAX_VALUE;

	// Approach1: Recursive Approach
	public int closestValue1(TreeNode root, double target) {
		helper(root, target);
		return closest;
	}

	public void helper(TreeNode root, double target) {
		if (root == null) return;

		if (Math.abs(root.data - target) < minDiff) {
			minDiff = Math.abs(root.data - target);
			closest = root.data;
		}

		if (target < root.data) {
			helper(root.left, target);
		} else {
			helper(root.right, target);
		}
	}

	// Approach2: Iterative Approach
	public int closestValue2(TreeNode root, double target) {
		double closest = Integer.MAX_VALUE;
		int value = 0;
		TreeNode current = root;
		while (current != null) {
			if (closest > Math.abs(current.data - target)) {
				closest = Math.abs(current.data - target);
				value = current.data;
			}

			if (current.data < target) {
				current = current.right;
			} else if (current.data > target) {
				current = current.left;
			} else {
				break;
			}
		}
		return value;
	}

	/* Closest Binary Search Tree Value II:
	 * 		Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
	 * 
	 * Note:
	 * 	 Given target value is a floating point. You may assume k is always valid, that is: k ≤ total nodes.
	 * 	You are guaranteed to have only one unique set of k values in the BST that are closest to the target. 
	 * 
	 * Follow up: Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
	 */

	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> result = new ArrayList<>();
		if (root == null) { return result; }

		Stack<Integer> precedessor = new Stack<>();
		Stack<Integer> successor = new Stack<>();

		getPredecessor(root, target, precedessor);
		getSuccessor(root, target, successor);

		for (int i = 0; i < k; i++) {
			if (precedessor.isEmpty()) {
				result.add(successor.pop());
			} else if (successor.isEmpty()) {
				result.add(precedessor.pop());
			} else if (Math.abs((double) precedessor.peek() - target) < Math.abs((double) successor.peek() - target)) {
				result.add(precedessor.pop());
			} else {
				result.add(successor.pop());
			}
		}

		return result;
	}

	private void getPredecessor(TreeNode root, double target, Stack<Integer> precedessor) {
		if (root == null) { return; }

		getPredecessor(root.left, target, precedessor);

		if (root.data > target) { return; }

		precedessor.push(root.data);

		getPredecessor(root.right, target, precedessor);
	}

	private void getSuccessor(TreeNode root, double target, Stack<Integer> successor) {
		if (root == null) { return; }

		getSuccessor(root.right, target, successor);

		if (root.data <= target) { return; }

		successor.push(root.data);

		getSuccessor(root.left, target, successor);
	}

	public TreeNode lowestCommonAncestor(TreeNode root, int data1, int data2) {
		if (root != null) {
			if (data1 < root.data && data2 < root.data) {
				return lowestCommonAncestor(root.left, data1, data2);
			} else if (data1 > root.data && data2 > root.data) {
				return lowestCommonAncestor(root.right, data1, data2);
			} else {
				return root;
			}
		}
		return root;
	}

	/* Following are some important points in favor of BSTs:
	 *   We can get all keys in sorted order by just doing Inorder Traversal of BST. This is not a natural operation in
	 *   Hash Tables and requires extra efforts. 
	 *   Doing "finding closest lower and greater elements", "range queries", "order statistics" are easy to do with BSTs. 
	 *   Like sorting, these operations are not a natural operation with Hash Tables. 
	 *   With Self-Balancing BSTs, all operations are guaranteed to work in O(Logn) time. But with Hashing, Θ(1) is average
	 *   time and some particular operations may be costly, especially when table resizing happens.
	 */
	// 1.finding closest lower and greater elements - Floor and Ceil from a BST:
	static TreeNode root;

	// Function to find ceil of a given input in BST. If input is more than the max key in BST, return -1
	int Ceil(TreeNode node, int input) {
		if (node == null) return -1;
		if (node.data == input) return node.data;

		// If root's key is smaller, ceil must be in right subtree
		if (node.data < input) return Ceil(node.right, input);

		// Else, either left subtree or root has the ceil value
		int ceil = Ceil(node.left, input);

		return (ceil >= input) ? ceil : node.data;
	}

	// Function to find floor of a given input in BST. If input is less than the min key in BST, return -1
	public TreeNode floor(TreeNode root) {
		return null;
	}
	/* 2.Range Queries: Print BST keys in the given range
	 * Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. Print all the keys of tree in 
	 * range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST. Print all the keys in increasing order.
	 */

	/* The functions prints all the keys which in the given range [k1..k2]. The function assumes than k1 < k2 */
	void printRange(TreeNode node, int k1, int k2) {
		if (node == null) return;

		/* Since the desired o/p is sorted, recurse for left subtree first 
		If root->data is greater than k1, then only we can get o/p keys 
		in left subtree */
		if (k1 < node.data) {
			printRange(node.left, k1, k2);
		}

		/* if root's data lies in range, then prints root's data */
		if (k1 <= node.data && k2 >= node.data) {
			System.out.print(node.data + " ");
		}

		/* If root->data is smaller than k2, then only we can get o/p keys in right subtree */
		if (k2 > node.data) {
			printRange(node.right, k1, k2);
		}
	}

	// TODO: Simplify Kth Element Problemss...
	// 3.Order Statistics: Find k-th smallest/Largest element in BST
	// 3.i.Find k-th smallest element in BST
	// Approach1: Using Inorder traversal & additional O(n) space
	public int kthSmallestElement1(TreeNode root, int k) {
		if (root == null || k == 0) return 0;
		ArrayList<Integer> list = new ArrayList<>();
		kthSmallestElement1(root, list);
		return list.get(k - 1);
	}

	private void kthSmallestElement1(TreeNode root, ArrayList<Integer> list) {
		if (root == null) return;
		kthSmallestElement1(root.left, list);
		list.add(root.data);
		kthSmallestElement1(root.right, list);
	}

	// Approach2: Using Inorder traversal, without additional space
	public int kthSmallestElement2(TreeNode root, int k) {
		int[] count = new int[1];
		TreeNode result = kthSmallestElement2(root, k, count);
		return result != null ? result.data : 0;
	}

	private TreeNode kthSmallestElement2(TreeNode root, int k, int[] count) {
		if (root == null || count[0] > k) return null;
		TreeNode kthLargestNode = kthSmallestElement2(root.left, k, count);

		if (kthLargestNode == null) {
			count[0]++;
			if (count[0] == k) return root;
		}
		if (kthLargestNode == null) kthLargestNode = kthSmallestElement2(root.right, k, count);

		return kthLargestNode;
	}

	// Approach3: Using Priority Queue, Inorder traversal -> This solution gives kth eleemnt in BT also
	public int kthSmallestElement3(TreeNode root, int k) {
		if (root == null) return 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		kthSmallestElement3(root, queue, k);
		return queue.peek();
	}

	public void kthSmallestElement3(TreeNode root, PriorityQueue<Integer> queue, int k) {
		if (root == null) return;

		if (queue.size() < k || root.data < queue.peek()) {
			if (queue.size() >= k) queue.remove();
			queue.add(root.data);
		}

		kthSmallestElement3(root.left, queue, k);
		kthSmallestElement3(root.right, queue, k);
	}

	// Approach4: Morris Traversal
	public int kthSmallestElement4(TreeNode root, int k) {
		return 0;
	}

	// Approach5: Augmented Tree Data Structure
	public int kthSmallestElement5(TreeNode root, int k) {
		if (root == null) return 0;
		int subTreeSize = sizeOfBinaryTree(root);

		if (subTreeSize == k - 1) return root.data;
		else if (subTreeSize >= k) return kthSmallestElement5(root.left, k);
		else return kthSmallestElement5(root, k - subTreeSize - 1);
	}

	// 3.ii. K’th Largest element in BST
	// Approach1: Using Reverse Inorder traversal & additional O(n) space- (RiRoL)
	public int kthLargestElement1(TreeNode root, int k) {
		if (root == null || k == 0) return 0;
		ArrayList<Integer> list = new ArrayList<>();
		kthLargestElement1(root, list);
		return list.get(k - 1);
	}

	private void kthLargestElement1(TreeNode root, ArrayList<Integer> list) {
		if (root == null) return;
		kthLargestElement1(root.right, list);
		list.add(root.data);
		kthLargestElement1(root.left, list);
	}

	// Approach2: Using reverse inorder traversal, without additional space- (RiRoL)
	public int kthLargestElement2(TreeNode root, int k) {
		int[] count = new int[1];
		TreeNode result = kthLargestElement2(root, k, count);
		return result != null ? result.data : 0;
	}

	private TreeNode kthLargestElement2(TreeNode root, int k, int[] count) {
		if (root == null || count[0] > k) return null;
		TreeNode kthLargestNode = kthLargestElement2(root.right, k, count);

		if (kthLargestNode == null) {
			count[0]++;
			if (count[0] == k) return root;
		}
		if (kthLargestNode == null) kthLargestNode = kthLargestElement2(root.left, k, count);

		return kthLargestNode;
	}

	// Approach3: Using Priority Queue, Inorder traversal
	public int kthLargestElement3(TreeNode root, int k) {
		if (root == null) return 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		kthLargestElement3(root, queue, k);
		return queue.peek();
	}

	public void kthLargestElement3(TreeNode root, PriorityQueue<Integer> queue, int k) {
		if (root == null) return;

		if (queue.size() < k || root.data > queue.peek()) {
			if (queue.size() >= k) queue.remove();
			queue.add(root.data);
		}

		kthLargestElement3(root.left, queue, k);
		kthLargestElement3(root.right, queue, k);
	}

	/************************ Other BST Problems ***********************/

}