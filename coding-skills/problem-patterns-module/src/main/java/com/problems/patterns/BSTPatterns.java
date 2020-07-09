package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.common.model.TreeNode;

public class BSTPatterns {

	/****************************Type1:Basic/Checking/Construction/Conversion/Searching**************************/
	// Serialize & Deserialize - Uses Preorder
	// 1.Serialize:Uses Tree to List
	public ArrayList<Integer> serialize1(
			ArrayList<Integer> result, TreeNode root) {
		if (root != null) {
			result.add(root.data);
			serialize1(result, root.left);
			serialize1(result, root.right);
		}
		return result;
	}

	// 1.Deserialize: List to Tree
	public TreeNode deserialize1(Integer[] preOrder,
			int low, int high) {
		if (low > high) return null;
		TreeNode root = new TreeNode(preOrder[low]);
		int mid = findRight(preOrder, root.data, low, high);
		root.left = deserialize1(preOrder, low + 1,
				mid - 1);
		root.right = deserialize1(preOrder, mid, high);
		return root;
	}

	int findRight(Integer[] preOrder, int root, int low,
			int high) {
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

	public void serialize2(TreeNode root,
			StringBuilder sb) {
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

	public TreeNode deserialize2(String[] str, int l,
			int h) {
		if (l > h) return null;
		TreeNode root = new TreeNode(
				Integer.valueOf(str[l]));
		int m = findMid(str, l, h, root.data);
		root.left = deserialize2(str, l + 1, m - 1);
		root.right = deserialize2(str, m, h);
		return root;
	}

	public int findMid(String[] str, int l, int h,
			int data) {
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
		Queue<String> q = new LinkedList<>(
				Arrays.asList(data.split(",")));
		return deserialize21(q, Integer.MIN_VALUE,
				Integer.MAX_VALUE);
	}

	public TreeNode deserialize21(Queue<String> q,
			int lower, int upper) {
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

	//	Convert Sorted Array to Binary Search Tree/Minimal Tree
	/*	Binary Tree to Binary Search Tree Conversion
	 * 	Convert Sorted Array to Binary Search Tree
	 */

	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0) return null;
		return arrayToBST(nums, 0, nums.length - 1);
	}

	private TreeNode arrayToBST(int[] nums, int low,
			int high) {
		if (low > high) return null;
		int mid = (low + high) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = arrayToBST(nums, low, mid - 1);
		root.right = arrayToBST(nums, mid + 1, high);
		return root;
	}

	public TreeNode lowestCommonAncestor(TreeNode root,
			int data1, int data2) {
		if (root != null) {
			if (data1 < root.data && data2 < root.data) {
				return lowestCommonAncestor(root.left,
						data1, data2);
			} else if (data1 > root.data
					&& data2 > root.data) {
						return lowestCommonAncestor(
								root.right, data1, data2);
					} else {
						return root;
					}
		}
		return root;
	}

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
			int successor = inOrderSuccessor(root.left,
					key);
			if (successor == -1) // If there is no element in rightSubtree, then root element should be next element
				successor = root.data;
			return successor;
		} else {
			return inOrderSuccessor(root.right, key);
		}
	}

	/****************************Type2: Traversal Modification**************************/
	//	Recover Binary Search Tree -Inorder/Morris Traversal

	TreeNode firstNode  = null;
	TreeNode secondNode = null;
	TreeNode prevNode   = new TreeNode(
			Integer.MIN_VALUE);

	public void recoverTree(TreeNode root) {
		inorderTraversal(root);
		swap(firstNode, secondNode);
	}

	public void inorderTraversal(TreeNode root) {
		if (root == null) return;
		inorderTraversal(root.left);
		if (firstNode == null
				&& prevNode.data >= root.data)
			firstNode = prevNode;
		if (firstNode != null
				&& prevNode.data >= root.data)
			secondNode = root;
		prevNode = root;
		inorderTraversal(root.right);
	}

	public void swap(TreeNode node1,
			TreeNode node2) {
		int temp = node1.data;
		node1.data = node2.data;
		node2.data = temp;
	}
}