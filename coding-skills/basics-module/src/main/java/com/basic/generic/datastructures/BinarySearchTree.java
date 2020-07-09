package com.basic.generic.datastructures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class BSTNode<T> {
	T			data;
	BSTNode<T>	left, right;

	public BSTNode(T x) {
		this.data = x;
		left = right = null;
	}
}

public class BinarySearchTree<T extends Comparable<T>> {
	BSTNode<T> root;

	private BSTNode<T> insertNode(BSTNode<T> root, T n) {
		if (root == null) {
			root = new BSTNode<>(n);
		} else if (n.compareTo(root.data) <= 0) {
			root.left = insertNode(root.left, n);
		} else if (n.compareTo(root.data) > 0) {
			root.right = insertNode(root.right, n);
		}
		return root;
	}

	public void insert(T n) {
		root = insertNode(root, n);
	}

	public BSTNode<T> insert1(T n) {
		BSTNode<T> newNode = new BSTNode<>(n);
		if (root == null)
			root = newNode;
		else {
			BSTNode<T> temp = root;
			while (temp != null) {
				if (n.compareTo(temp.data) <= 0) {
					if (temp.left == null) {
						temp.left = newNode;
						break;
					} else {
						temp = temp.left;
					}
				}
				if (n.compareTo(temp.data) > 0) {
					if (temp.right == null) {
						temp.right = newNode;
						break;
					} else {
						temp = temp.right;
					}
				}
			}
		}
		return root;
	}

	public BSTNode<T> deleteNode(BSTNode<T> root, T n) {
		if (root == null) {
			System.out.println("Data not found");
		} else if (n.compareTo(root.data) < 0) {
			root.left = deleteNode(root.left, n);
		} else if (n.compareTo(root.data) > 0) {
			root.right = deleteNode(root.right, n);
		} else if (root.left != null & root.right != null) {
			T minElement = findMin(root.right);
			root.data = minElement;
			root.right = deleteNode(root.right, minElement);

		} else {
			if (root.left == null) {
				root = root.right;
			} else if (root.right == null) {
				root = root.left;
			}
		}
		return root;
	}

	public void delete(T n) {
		root = deleteNode(root, n);
	}

	public boolean search(T data) {
		boolean flag = false;
		if (root != null) {
			if (searchElement(root, data) != null && searchElement(root, data).data == data)
				flag = true;
		} else {
			System.out.println("BST is Empty!");
		}
		return flag;
	}

	private BSTNode<T> searchElement(BSTNode<T> node, T x) {
		BSTNode<T> result = null;
		if (node != null) {
			if (x.compareTo(node.data) == 0) {
				result = node;
			} else if (x.compareTo(node.data) < 0) {
				result = searchElement(node.left, x);
			} else {
				result = searchElement(node.right, x);
			}
		}
		return result;
	}

	public T findMin(BSTNode<T> node) {
		if (node == null) {
			return null;
		} else {
			while (node.left != null)
				node = node.left;
		}
		return node.data;
	}

	public T findMax(BSTNode<T> node) {
		if (node == null) {
			return null;
		} else {
			while (node.right != null)
				node = node.right;
		}
		return node.data;
	}

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

	public int getHeight() {
		return height(root);
	}

	// Recursive Approach
	public BSTNode<T> insertRecursiveApproach(BSTNode<T> root, T data) {
		if (root == null) {
			return new BSTNode<>(data);
		} else {
			BSTNode<T> cur;
			if (data.compareTo(root.data) <= 0) {
				cur = insertRecursiveApproach(root.left, data);
				root.left = cur;
			} else {
				cur = insertRecursiveApproach(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	private void inOrder(BSTNode<T> root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + ",");
			inOrder(root.right);
		}
	}

	private void preOrder(BSTNode<T> root) {
		if (root != null) {
			System.out.print(root.data + ",");
			preOrder(root.left);
			preOrder(root.right);
		}

	}

	private void postOrder(BSTNode<T> root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + ",");
		}
	}

	private void levelOrder(BSTNode<T> n) {
		if (n != null) {
			Queue<BSTNode<T>> queue = new LinkedList<>();
			queue.add(n);
			while (!queue.isEmpty()) {
				BSTNode<T> temp = queue.remove();
				System.out.print(temp.data + " ");
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
			}

		}
	}

	private int height(BSTNode<T> n) {
		if (n != null) {
			int left = height(n.left);
			int right = height(n.right);
			return 1 + (left > right ? left : right);
		} else {
			return -1;
		}
	}

	public int depth(BSTNode<T> n) {
		return -1;
	}

	public static void main(String[] arge) {
		Scanner in = new Scanner(System.in);
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		char ch;
		int input;
		do {
			System.out.println("Binary Search Tree Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.Find");
			System.out.println("4.FindMin");
			System.out.println("5.FindMax");
			System.out.println("6.Height of the tree");
			System.out.println("7.Depth of the tree");
			System.out.print("Enter option:");
			input = in.nextInt();
			// BSTNode root = null;
			switch (input) {
			case 1:
				System.out.println("Enter no of elements to be inserted:");
				int t = in.nextInt();
				while (t-- > 0)
					bst.insert(in.nextInt());
				break;
			case 2:
				System.out.println("Element needs to be removed in tree:");
				bst.delete(in.nextInt());
				break;
			case 3:
				System.out.println("Enter elements to search:");
				System.out.println("Element present in BST? " + bst.search(in.nextInt()));
				break;
			case 4:
				System.out.println("Minmum element in BST:" + bst.findMin(bst.root));
				break;
			case 5:
				System.out.println("Maximum element in BST:" + bst.findMax(bst.root));
				break;
			case 6:
				System.out.println("Height of a tree:" + bst.getHeight());
				break;
			case 7:
				break;
			default:
				System.out.println("Please enter the valid option");
				break;
			}
			System.out.println("\nDisplay:");
			System.out.println("In Order Traversal:");
			bst.inOrderTraversal();
			System.out.println("\nPre Order Traversal:");
			bst.preOrderTraversal();
			System.out.println("\nPost Order Traversal:");
			bst.postOrderTraversal();
			System.out.println("\nLevel Order Traaversal:");
			bst.levelOrderTraversal();

			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}

}
