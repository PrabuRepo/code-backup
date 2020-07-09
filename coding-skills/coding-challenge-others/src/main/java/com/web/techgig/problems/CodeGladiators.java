package com.web.techgig.problems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
	int			data;
	TreeNode	left, right;

	public TreeNode(int x) {
		this.data = x;
		this.left = this.right = null;
	}
}

public class CodeGladiators {

	static void testCountLeafNodeAfterDelete() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		TreeNode root = null;
		for (int i = 0; i < n; i++) {
			root = insert(root, in.nextInt());
		}
		int x = in.nextInt();
		System.out.println("Before delete:");
		levelOrder(root);
		root = delete(root, x);
		System.out.println("\nAfter delete:");
		levelOrder(root);
		System.out.println("\nNo of leaf nodes:");
		System.out.println(countLeafNodes(root));
	}

	static TreeNode insert(TreeNode root, int data) {
		TreeNode newTreeNode = new TreeNode(data);
		if (root == null) {
			return newTreeNode;
		} else {
			TreeNode temp;
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				temp = queue.remove();
				if (temp.left == null) {
					temp.left = newTreeNode;
					break;
				} else {
					queue.add(temp.left);
				}

				if (temp.right == null) {
					temp.right = newTreeNode;
					break;
				} else {
					queue.add(temp.right);
				}
			}
		}
		return root;
	}

	static TreeNode delete(TreeNode root, int TreeNodeIndex) {
		if (root != null) {
			if (TreeNodeIndex == 0) {
				root = null;
			} else {
				TreeNode temp;
				int count = 0;
				Queue<TreeNode> queue = new LinkedList<>();
				queue.add(root);
				while (!queue.isEmpty()) {
					temp = queue.remove();
					if (temp.left != null) {
						if (TreeNodeIndex == ++count) {
							temp.left = null;
							break;
						}
						queue.add(temp.left);
					}
					if (temp.right != null) {
						if (TreeNodeIndex == ++count) {
							temp.right = null;
							break;
						}
						queue.add(temp.right);
					}
				}
			}
		}
		return root;
	}

	static int countLeafNodes(TreeNode root) {
		if (root == null)
			return 0;
		else if (root.left == null && root.right == null)
			return 1;
		return countLeafNodes(root.left) + countLeafNodes(root.right);
	}

	static void levelOrder(TreeNode root) {
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				root = queue.remove();
				System.out.print(root.data + " ");
				if (root.left != null)
					queue.add(root.left);
				if (root.right != null)
					queue.add(root.right);
			}
		}
	}

	public static void main(String[] args) {
	}

}
