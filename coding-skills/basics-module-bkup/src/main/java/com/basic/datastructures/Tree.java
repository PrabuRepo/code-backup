package com.basic.datastructures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.common.model.TreeNode;

public class Tree {

}

class BinarySearchTree {
	TreeNode root;

	public void insert(int n) {
		// Recursive
		root = insertNode1(root, n);

		// Iterative
		// root = insertNode2(n);
	}

	private TreeNode insertNode1(TreeNode root,
			int n) {
		if (root == null) {
			root = new TreeNode(n);
		} else if (n <= root.data) {
			root.left = insertNode1(root.left, n);
		} else {
			root.right = insertNode1(root.right,
					n);
		}
		return root;
	}

	public TreeNode insertNode2(int n) {
		TreeNode newNode = new TreeNode(n);
		if (root == null) root = newNode;

		else {
			TreeNode curr = root;
			while (curr != null) {
				if (n <= curr.data) {
					if (curr.left == null) {
						curr.left = newNode;
						break;
					} else {
						curr = curr.left;
					}
				} else {
					if (curr.right == null) {
						curr.right = newNode;
						break;
					} else {
						curr = curr.right;
					}
				}
			}
		}
		return root;
	}

	public void delete(int n) {
		root = deleteNode(root, n);
	}

	private TreeNode deleteNode(TreeNode root,
			int n) {
		if (root == null) {
			System.out.println("Data not found");
		} else if (n < root.data) {
			root.left = deleteNode(root.left, n);
		} else if (n > root.data) {
			root.right = deleteNode(root.right,
					n);
		} else if (root.left != null
				& root.right != null) {
			int minElement = findMin(root.right);
			root.data = minElement;
			root.right = deleteNode(root.right,
					minElement);
		} else {
			root = root.left != null ? root.left
					: root.right;
		}
		return root;
	}

	public boolean search(int data) {
		return search(root, data) != null ? true
				: false;
	}

	private TreeNode search(TreeNode node, int x) {
		if (node == null) return null;

		if (x == node.data) return node;
		else if (x < node.data)
			return search(node.left, x);
		else return search(node.right, x);
	}

	public int findMin(TreeNode node) {
		if (node == null) return -1;

		while (node.left != null)
			node = node.left;

		return node.data;
	}

	public int findMax(TreeNode node) {
		if (node == null) return -1;

		while (node.right != null)
			node = node.right;

		return node.data;
	}

	// Tree Traversals:
	public void inOrderTraversal() {
		inOrder(root);
	}

	public void preOrderTraversal() {
		preOrder(root);
	}

	public void postOrderTraversal() {
		postOrder(root);
	}

	public void levelOrderTraversal() {
		levelOrder(root);
	}

	// Depth of the Tree == Height of the Tree
	public int getHeight() {
		return height(root);
	}

	private void inOrder(TreeNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + ",");
			inOrder(root.right);
		}
	}

	private void preOrder(TreeNode root) {
		if (root != null) {
			System.out.print(root.data + ",");
			preOrder(root.left);
			preOrder(root.right);
		}

	}

	private void postOrder(TreeNode root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + ",");
		}
	}

	private void levelOrder(TreeNode n) {
		if (n != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(n);
			while (!queue.isEmpty()) {
				TreeNode temp = queue.remove();
				System.out.print(temp.data + " ");
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
			}

		}
	}

	private int height(TreeNode n) {
		if (n != null) {
			int left = height(n.left);
			int right = height(n.right);
			return 1 + (left > right ? left
					: right);
		} else {
			return -1;
		}
	}

	public static void main(String[] arge) {
		Scanner in = new Scanner(System.in);
		BinarySearchTree bst = new BinarySearchTree();
		char ch;
		int input;
		do {
			System.out.println(
					"Binary Search Tree Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.Find");
			System.out.println("4.FindMin");
			System.out.println("5.FindMax");
			System.out.println(
					"6.Height of the tree");
			System.out.println(
					"7.Depth of the tree");
			System.out.print("Enter option:");
			input = in.nextInt();
			// BTNode root = null;
			switch (input) {
				case 1:
					System.out.println(
							"Enter no of elements to be inserted:");
					int t = in.nextInt();
					while (t-- > 0)
						bst.insert(in.nextInt());
					break;
				case 2:
					System.out.println(
							"Element needs to be removed in tree:");
					bst.delete(in.nextInt());
					break;
				case 3:
					System.out.println(
							"Enter elements to search:");
					System.out.println(
							"Element present in BST? "
									+ bst.search(
											in.nextInt()));
					break;
				case 4:
					System.out.println(
							"Minmum element in BST:"
									+ bst.findMin(
											bst.root));
					break;
				case 5:
					System.out.println(
							"Maximum element in BST:"
									+ bst.findMax(
											bst.root));
					break;
				case 6:
					System.out.println(
							"Height of a tree:"
									+ bst.getHeight());
					break;
				case 7:
					break;
				default:
					System.out.println(
							"Please enter the valid option");
					break;
			}
			System.out.println("\nDisplay:");
			System.out.println(
					"In Order Traversal:");
			bst.inOrderTraversal();
			System.out.println(
					"\nPre Order Traversal:");
			bst.preOrderTraversal();
			System.out.println(
					"\nPost Order Traversal:");
			bst.postOrderTraversal();
			System.out.println(
					"\nLevel Order Traaversal:");
			bst.levelOrderTraversal();

			System.out.println(
					"\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}
}