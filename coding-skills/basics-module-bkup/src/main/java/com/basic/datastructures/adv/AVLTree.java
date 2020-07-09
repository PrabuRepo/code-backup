package com.basic.datastructures.adv;

class AVLNode {
	int		data, height;
	AVLNode	left, right;

	AVLNode(int d) {
		data = d;
		height = 0;
	}
}

public class AVLTree {
	AVLNode root;

	int height(AVLNode node) {
		if (node == null)
			return -1;
		return node.height;
	}

	int getBalance(AVLNode node) {
		if (node == null)
			return 0;

		return height(node.left) - height(node.right);
	}

	int max(int a, int b) {
		return a > b ? a : b;
	}

	AVLNode rotateRight(AVLNode x) {
		AVLNode y = x.left;
		AVLNode temp = y.right;

		y.right = x;
		x.left = temp;

		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		return y;
	}

	AVLNode rotateLeft(AVLNode x) {
		AVLNode y = x.right;
		AVLNode temp = y.left;

		y.left = x;
		x.right = temp;

		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		return y;
	}

	public AVLNode insert(AVLNode root, int data) {
		if (root == null) {
			return new AVLNode(data);
		} else if (data < root.data) {
			root.left = insert(root.left, data);
		} else if (data > root.data) {
			root.right = insert(root.right, data);
		} else {
			return root;
		}

		root.height = max(height(root.left), height(root.right)) + 1;

		// int balance = getBalance(root);
		int balance = (root == null) ? 0 : (height(root.left) - height(root.right));

		if (balance > 1) {// Data insert into left side & right rotation
			if (data < root.left.data) {// LL Scenario
				return rotateRight(root);
			} else { // LR Scenario
				root.left = rotateLeft(root.left); // data > root.left.data
				return rotateRight(root);
			}
		}

		if (balance < -1) { // Data insert into right side & left rotation
			if (data > root.right.data) { // RR Scenario
				return rotateLeft(root);
			} else { // RL Scenario
				root.right = rotateRight(root.right); // data < root.right.data
				return rotateLeft(root);
			}
		}

		return root;
	}

	public AVLNode delete(AVLNode root, int data) {
		if (root == null)
			return root;

		if (data < root.data) {
			root.left = delete(root.left, data);
		} else if (data > root.data) {
			root.right = delete(root.right, data);
		} else if (root.left != null && root.right != null) {
			int minElement = findMin(root.right).data;
			root.data = minElement;
			root.right = delete(root.right, minElement);
		} else {
			if (root.left == null)
				root = root.right;
			else if (root.right == null)
				root = root.left;
		}

		// If tree has only only one node
		if (root == null)
			return root;

		// Update the height after deleting the element from the tree
		root.height = max(height(root.left), height(root.right)) + 1;

		int balance = getBalance(root);

		if (balance > 1) {
			if (getBalance(root.left) >= 0) { // Left Left Case
				return rotateRight(root);
			} else {// Left Right Case
				// if (getBalance(root.left) < 0) {
				root.left = rotateLeft(root.left);
				return rotateRight(root);
			}
		}

		if (balance < -1) {
			if (getBalance(root.right) <= -1) { // Right Right Case
				return rotateLeft(root);
			} else {// Right Left Case
				// if (getBalance(root.right) > 0) {
				root.right = rotateRight(root.right);
				return rotateLeft(root);
			}
		}
		return root;
	}

	private AVLNode findMin(AVLNode node) {
		if (node == null)
			return node;
		else if (node.left == null)
			return node;
		return findMin(node.left);
	}

	void preOrder(AVLNode node) {
		if (node != null) {
			System.out.print("D:" + node.data + " H:" + node.height + "; ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	void inOrder(AVLNode node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print("D:" + node.data + " H:" + node.height + "; ");
			inOrder(node.right);
		}
	}

	public AVLNode testAVLNode(AVLTree tree) {
		tree.root = tree.insert(tree.root, 10);
		tree.root = tree.insert(tree.root, 20);
		tree.root = tree.insert(tree.root, 30);
		tree.root = tree.insert(tree.root, 40);
		tree.root = tree.insert(tree.root, 50);
		tree.root = tree.insert(tree.root, 25);
		return tree.root;
	}

	public AVLNode testInsertScenariosData(AVLTree tree) {
		// LL Scenario - 1 rotation
		/*tree.root = tree.insert(tree.root, 5);
		tree.root = tree.insert(tree.root, 2);
		tree.root = tree.insert(tree.root, 1);*/

		// LR Scenario - 2 rotations
		/*tree.root = tree.insert(tree.root, 5);
		tree.root = tree.insert(tree.root, 2);
		tree.root = tree.insert(tree.root, 3);*/

		// RR Scenario - 1 rotation
		/*tree.root = tree.insert(tree.root, 1);
		tree.root = tree.insert(tree.root, 3);
		tree.root = tree.insert(tree.root, 4);*/

		// RL Scenario - 2 rotations
		tree.root = tree.insert(tree.root, 1);
		tree.root = tree.insert(tree.root, 3);
		tree.root = tree.insert(tree.root, 2);
		return tree.root;
	}

	public AVLNode testDeleteScenariosData(AVLTree tree) {
		// Delete Operation
		tree.root = tree.insert(tree.root, 9);
		tree.root = tree.insert(tree.root, 5);
		tree.root = tree.insert(tree.root, 10);
		tree.root = tree.insert(tree.root, 0);
		tree.root = tree.insert(tree.root, 6);
		tree.root = tree.insert(tree.root, 11);
		tree.root = tree.insert(tree.root, -1);
		tree.root = tree.insert(tree.root, 1);
		tree.root = tree.insert(tree.root, 2);

		/* The constructed AVL Tree would be
		   9
		  /  \
		 1    10
		/  \    \
		0    5    11
		/    /  \
		-1   2    6*/

		return tree.root;
	}

	public static void main(String[] args) {

		AVLTree tree = new AVLTree();
		// Test data
		// tree.root = tree.testAVLNode(tree);

		// Insert operation
		// tree.root = tree.testInsertScenariosData(tree);

		// Delete Operation
		tree.root = tree.testDeleteScenariosData(tree);

		System.out.println("Before deletion:");
		System.out.println("Preorder traversal" + " of constructed tree is : ");
		tree.preOrder(tree.root);
		System.out.println("\nInorder traversal" + " of constructed tree is : ");
		tree.inOrder(tree.root);
		tree.root = tree.delete(tree.root, 10);
		System.out.println("After deletion:");
		System.out.println("Preorder traversal" + " of constructed tree is : ");
		tree.preOrder(tree.root);
		System.out.println("\nInorder traversal" + " of constructed tree is : ");
		tree.inOrder(tree.root);

	}
}
