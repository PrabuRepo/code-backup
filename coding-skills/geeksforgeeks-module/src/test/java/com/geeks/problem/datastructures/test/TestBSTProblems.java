package com.geeks.problem.datastructures.test;

import java.util.ArrayList;

import com.common.model.TreeNode;
import com.geeks.problem.datastructures.BSTProblems;

public class TestBSTProblems extends BSTProblems {
	public static void main(String[] args) {

		TestBSTProblems obj = new TestBSTProblems();
		obj.testBSTBasics();

		obj.testConstructionProblems();

		obj.testConversionProblems();

		obj.testCheckingProblems();

		obj.testPathProblems();
	}

	public void testBSTBasics() {
		TreeNode root = mockBST2();
		System.out.println("Inorder Traversal");
		inOrder(root);
		System.out.println("Preorder Traversal");
		preOrder(root);

		TreeNode node = binarySearchIterative(root, 16);
		if (node != null) System.out.println("\nSearch Element:" + node.data);
		else System.out.println("\nElement not found");

		root = delete(root, 3);
	}

	public void testConstructionProblems() {

	}

	public void testConversionProblems() {
		// Serialize: TreeNode to List; Deserialize: List to TreeNode
		TreeNode root1 = mockBST1();
		ArrayList<Integer> list = new ArrayList<>();
		list = serialize1(list, root1);
		System.out.println("Print after serialize:");
		for (int i : list)
			System.out.print(i + " ");
		Integer[] arr = new Integer[list.size()];
		arr = list.toArray(arr);
		TreeNode deserializedNode = deserialize1(arr, 0, list.size() - 1);
		System.out.println("\nPreOrder after deserialize:");
		preOrder(deserializedNode);
	}

	public void testCheckingProblems() {
		TreeNode root3 = mockBST3();
		System.out.println("\nIs Binary Search Tree? " + isBST(root3));
	}

	public void testPathProblems() {
		TreeNode root = mockBST2();
		System.out.println("Locest common ansector of 2 elements:" + lowestCommonAncestor(root, 8, 10).data);

		root = mockBST2();
		System.out.println("Preorder Traversal to verify th Predecessor & Successor:");
		inOrder(root);
		System.out.println("\nInorder Predecessor:" + inOrderPredecessor(root, 6));
		System.out.println("Inorder Successor:" + inOrderSuccessor(root, 8));

		testKthElementProblems();

	}

	public void testKthElementProblems() {
		System.out.println("Kth Smallest Element in a BST: ");
		System.out.println("Using Inorder traversal & additional O(n) space: " + kthSmallestElement1(mockBST1(), 9));
		System.out.println("Using Inorder traversal, without additional space: " + kthSmallestElement2(mockBST1(), 9));
		System.out.println("Using Priority Queue, Inorder traversal: " + kthSmallestElement3(mockBST1(), 9));
		System.out.println("Using Augmented Tree Data Structure" + kthSmallestElement5(mockBST1(), 9));

		System.out.println("Kth Largest Element in a BST: ");
		System.out.println(
				"Using Reverse Inorder traversal & additional O(n) space: " + kthLargestElement1(mockBST1(), 4));
		System.out.println(
				"Using reverse inorder traversal, without additional space: " + kthLargestElement2(mockBST1(), 4));
		System.out.println("Using Priority Queue, Inorder traversal: " + kthLargestElement3(mockBST1(), 4));
	}

	private TreeNode mockBST1() {
		TreeNode root = null;
		root = insert(root, 6);
		root = insert(root, 9);
		root = insert(root, 10);
		root = insert(root, 7);
		root = insert(root, 8);
		root = insert(root, 4);
		root = insert(root, 2);
		root = insert(root, 3);
		root = insert(root, 1);
		root = insert(root, 5);
		return root;
	}

	private TreeNode mockBST2() {
		TreeNode root = null;
		root = insertIterative(root, 6);
		root = insertIterative(root, 9);
		root = insertIterative(root, 10);
		root = insertIterative(root, 7);
		root = insertIterative(root, 8);
		root = insertIterative(root, 4);
		root = insertIterative(root, 2);
		root = insertIterative(root, 3);
		root = insertIterative(root, 1);
		root = insertIterative(root, 5);
		return root;
	}

	/*Corner cases - To check BST
	 *  Eg: [-2147483648,null,2147483647], [1,1],[-2147483648], [2147483647], [-2147483648,-2147483648], [2147483647, null,2147483647]
	 */
	private TreeNode mockBST3() {
		TreeNode root = null;
		root = new TreeNode(-2147483648);
		root.left = new TreeNode(-2147483648);

		// root = new BLNode(2147483647);
		// root.right = new BLNode(2147483647);

		// root = new BLNode(1);
		// root.left = new BLNode(1);

		// root = new BLNode(1);
		// root.right = new BLNode(1);

		// root = new BLNode(-2147483648);
		// root.right = new BLNode(2147483647);

		// root = new BLNode(2147483647);
		// root.left = new BLNode(-2147483648);
		return root;
	}
}
