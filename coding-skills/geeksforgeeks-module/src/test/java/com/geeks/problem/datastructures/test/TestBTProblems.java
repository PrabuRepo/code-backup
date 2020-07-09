package com.geeks.problem.datastructures.test;

import java.util.ArrayList;

import com.common.model.TreeNode;
import com.geeks.problem.datastructures.BTProblems;

public class TestBTProblems extends BTProblems {
	public static void main(String[] args) {
		TestBTProblems obj = new TestBTProblems();

		// Binary Tree Traversals
		// obj.testTreeTraversals();

		// Tree Terminologies
		obj.testTreeTerminologyProblems();

		// Construction & Conversion
		// obj.testConstAndConvProblems();

		// obj.testCheckingAndPrintingProblems();

		// obj.testSummationProblems();

		// Lowest Common Ansectors
		// obj.testLCAProblems();

		// Misc Problems
		// obj.testMiscProblems();

		// obj.testBasicOperations();
	}

	// Method Invocations - Start
	public void testBasicOperations() {

		subtreeWithAllDeepest(mockBT3());

	}

	public void testTreeTraversals() {
		TreeNode root = mockBST1();
		System.out.println(
				"*****Binary Tree Traversals*****");
		System.out.println("PreOrder Recursive:");
		preOrder(root);
		System.out
				.println("\nPreOrder Iterative:");
		preOrderIterative(root);
		System.out
				.println("\nInOrder Recursive:");
		inOrder(root);
		System.out
				.println("\nInOrder Iterative:");
		inOrderIterative(root);
		System.out.println(
				"\nPostOrder Recursive:");
		postOrder(root);
		System.out.println(
				"\nPostOrder Iterative(Using 2 stack):");
		postOrderUsing2Stack(root);
		System.out.println(
				"\nPostOrder Iterative(Using 1 stack):");
		postOrderUsingStack(root);
		System.out.println(
				"\nLevel Order Traversal:");
		levelOrder(root);
		System.out.println(
				"\nReverse Level Order Traversal:");
		reverseLevelOrder(root);
		System.out.println(
				"\nLevel by Level Order Traversal:");
		levelByLevelOrder1(root);
		System.out.println(
				"\nLevel Order Spiral Traversal:");
		levelOrderSpiralForm(root);
		System.out.println(
				"\nVertical View Traversal:");
		verticalViewTraversal1(root);
		System.out.println(
				"Vertical View Traversal - Iterative:");
		verticalViewTraversal2(root);
		TreeNode root2 = mockBST4();
		System.out.println(
				"Left View Traversal - Recursive:");
		leftViewOfTree2(root2);
		System.out.println(
				"\nRight View Traversal - Recursive:");
		rightViewOfTree2(root2);
		System.out
				.println("\nTop view traversal:");
		topViewTraversal(root);
		System.out.println(
				"\nBottom view traversal:");
		bottomViewTraversal(root);
		System.out
				.println("\nDiagonal Traversal:");
		diagonalTraversal(root);
	}

	public void testTreeTerminologyProblems() {
		TreeNode root1 = mockBST1();
		TreeNode root2 = mockBST2();
		TreeNode root4 = mockBST4();

		System.out.println(
				"*****Tree Terminologies****");
		System.out
				.println("Size of a binary tree:"
						+ sizeOfBinaryTree(
								root1));
		System.out.println(
				"Size of a binary tree(Iterative):"
						+ sizeOfBinaryTreeIterative(
								root1));
		System.out.println(
				"No of leaf Nodes in the tree(Recursive):"
						+ countLeafNodes1(root1));
		System.out.println(
				"No of leaf Nodes in the tree(Iterative):"
						+ countLeafNodes2(root1));
		System.out
				.println("Print the leaf nodes:");
		printLeafNodes(root1);
		System.out.println(
				"\nHeight of a Tree(Recursive):"
						+ heightOfTree1(root4));
		System.out.println(
				"Height of a Tree(Iterative):"
						+ heightOfTree2(root4));
		System.out.println(
				"Depth of a Node(Recursive):"
						+ depthOfNode(root1, 9));
		System.out.println(
				"Depth of a Tree(Recursive):"
						+ depthOfTree(root4));
		System.out.println(
				"Depth of the deepest odd level node in Binary Tree: "
						+ deepestOddLevelNode(
								root1));
		System.out.println("Is tree balanced?:"
				+ isBinaryTreeBalanced1(root2));
		System.out.println("Is tree balanced?:"
				+ isBinaryTreeBalanced2(root2));
		System.out.println("Level of tree:"
				+ levelOfTree(root1));
		System.out.println(
				"Level of given node(Recursive):"
						+ levelOfNode1(root1, 8));
		System.out.println(
				"Level of given node(Iterative):"
						+ levelOfNode2(root1, 8));
		System.out.println(
				"\nDisplay the deepest nodes in the tree: ");
		deepestNodesInTree(root1);
		System.out.println(
				"\nSum of the nodes in the deepest leaf: "
						+ sumOfNodesAtMaxDepth(
								root1));
		System.out.println(
				"Sum Root to Leaf Numbers: "
						+ sumNumbers(mockBT1()));
		System.out.println(
				"Print all the path from root to leaf in the tree - Integer: ");
		printPathFromRootToLeaf1(root1);
		System.out.println(
				"Print all the path from root to leaf in the tree - String: ");
		printPathFromRootToLeaf2(root1);
		System.out.println(
				"\nPrint the path from root to given node in the tree: ");
		findPathFromRootToAnyNode1(root1, 10);
		System.out.println(
				"\nPrint Ancestors(Approach-1):");
		printAncestorsOfGivenNode(root1, 1);

	}

	public void testConstAndConvProblems() {
		// BLNode root = mockBT2();
		TreeNode root = mockBST1();

		System.out.println(
				"\n*******Construction & Conversion*******");

		System.out.println(
				"constructTreeFromInAndPreOrder:");
		char in[] = new char[] { 'D', 'B', 'E',
				'A', 'F', 'C' };
		char pre[] = new char[] { 'A', 'B', 'D',
				'E', 'C', 'F' };
		TreeNode charNode = constructTreeFromInAndPreOrder(
				in, pre);
		System.out.println("Inorder:");
		inOrderCharNode(charNode);
		System.out.println("\nPreorder:");
		preOrderCharNode(charNode);
		System.out.println("\nPostorder:");
		postOrderCharNode(charNode);

		charNode = null;
		System.out.println(
				"\nconstructTreeFromInAndPostOrder:");
		char[] post = { 'D', 'E', 'B', 'F', 'C',
				'A' };
		charNode = constructTreeFromInAndPostOrder(
				in, post);
		System.out.println("Inorder:");
		inOrderCharNode(charNode);
		System.out.println("\nPreorder:");
		preOrderCharNode(charNode);
		System.out.println("\nPostorder:");
		postOrderCharNode(charNode);

		System.out.println(
				"Pre Order Traversal before serialization:");
		preOrder(root);
		System.out.println(
				"\nSerialize the Binary Tree(Tree to List):");
		ArrayList<Integer> list = serialize1(
				root);
		System.out.println(
				"\n Deserialize the data(List to Tree):");
		TreeNode deserializedNode = deserialize1(
				list);
		// BLNode deserializedNode = deSerialize(list, new OrderIndex());
		preOrder(deserializedNode);

		System.out.println(
				"\nSerialize the Binary Tree(Tree to String):");
		String str = serialize2(root);
		System.out.println(
				"\n Deserialize the data(String to Tree):");
		deserializedNode = deSerialize2(str);
		preOrder(deserializedNode);

		System.out.println("\nSum Tree: "
				+ convertBTToSumTree(root));
		System.out.println(
				"PreOrder Traversal after sum tree:");
		preOrder(root);

		System.out.println(
				"\nBinary Tree to DLL(Inorder Traversal)");
		root = mockBST1();
		printDLL(convertBinaryTreeToDLL1(root));
		root = mockBST1();
		printDLL(convertBinaryTreeToDLL2(root));
		System.out.println(
				"Binary Tree to DLL(Level order Traversal)");
		root = mockBST1();
		convertBinaryTreeToDLL3(root);
		printDLL(root);

		System.out.println("Convert BT to BST: ");
		convertBTtoBST(mockBT1());

		System.out.println(
				"\nFlatten BT to LinkedList: ");
		root = mockBT3();
		flatten(root);
		inOrder(root);
	}

	public void testCheckingAndPrintingProblems() {
		TreeNode root2 = mockBT2();
		// BLNode root1 = mockBST1();
		System.out.println(
				"Check both are same binary tree?"
						+ isSameTree(root2,
								root2));
		root2 = mockBT2();
		TreeNode root4 = mockBT4();
		System.out.println(
				"Is Subtree of Another Tree: "
						+ isSubtree1(root2,
								root4));
		System.out.println(
				"Is Subtree of Another Tree: "
						+ isSubtree3(root2,
								root4));
	}

	public void testSummationProblems() {
		TreeNode root1 = mockBST1();
		TreeNode root2 = mockBST2();
		System.out.println(
				"root to any node sum(Approach-1):");
		sumPathFromRootToAnyNode1(root1, 30);
		System.out.println(
				"\nroot to any node sum(Approach-2):");
		sumPathFromRootToAnyNode2(root1, 30);
		System.out.println("\nroot to leaf sum:");
		sumPathFromRootToLeaf(root1, 30);
		System.out.println("\nMaximum sum:");
		maxSumPathFromRootToLeaf(root2);
		System.out.println(
				"\nDiagonal sum of tree:");
		diagonalSumOfTree(root1);
		TreeNode temp = new TreeNode(-1);
		temp.left = new TreeNode(-2);
		System.out.println(
				"Max path between two leafs: "
						+ maxSumPathFromLeafToLeaf1(
								temp));
		System.out.println(
				"Max path between two leafs: "
						+ maxSumPathFromLeafToLeaf2(
								temp));
		System.out.println(
				"Max path between two leafs: "
						+ maxSumPathFromLeafToLeaf3(
								temp));
	}

	public void testLCAProblems() {
		System.out.println(
				"\n*****Lowest Common Ansectors*****");
		// Construct Binary Tree
		TreeNode root3 = mockBT1();
		// BST
		TreeNode root = mockBST1();
		System.out.println(
				"LCA for two nodes(Approach-1):"
						+ lowestCommonAncestor1(
								root3, 4,
								8).data);
		if (LCA(root, 9, 10) != null)
			System.out.println(
					"LCA for two nodes(Approach-2):"
							+ LCA(root3, 9,
									10).data);
		else System.out.println(
				"Element is not present in the tree!");
		System.out.println(
				"\nLCA for two nodes(Approach-3):"
						+ lowestCommonAncestor3(
								root3, 4, 5));

	}

	public void testMiscProblems() {
		TreeNode node1 = mockBTMirror1(),
				node2 = mockBTMirror2();
		System.out
				.println("Is Miror Binary Tree: "
						+ isMirror(node1, node2));

		node1 = mockBTIsomorphism1();
		node2 = mockBTIsomorphism2();
		System.out.println(
				"Is Isomorohic Binary Tree: "
						+ isomorphism(node1,
								node2));

		TreeNode root = mockBST2();
		System.out.println("Diameter of a Tree: "
				+ diameterOfTree1(root));
		System.out.println("Diameter of a Tree: "
				+ diameterOfTree2(root));

		System.out.println(
				"Connect nodes at same level using constant extra space: ");
		root = mockBST1();
		connect11(root);
		System.out.println(
				"Print the nodes at the same level"); // ??

		System.out.println(
				"Count smaller elements: ");
		int[] nums = { 5, 2, 6, 1 };
		countSmaller(nums);

		root = mockBT5();
		System.out.println(
				"Binary Consecutive problems: "
						+ longestConsecutive(
								root));

		root = mockBT5();
		System.out.println(
				"Find Leaves of Binary Tree (Java): ");
		findLeaves(root);
	}

	// Method Invocations - End
	// Test Data - Start
	public TreeNode mockBST1() {
		TreeNode root = null;
		root = insert(root, 6);
		root = insert(root, 9);
		// root = insert(root, 10);
		root = insert(root, 7);
		root = insert(root, 8);
		root = insert(root, 4);
		root = insert(root, 2);
		root = insert(root, 3);
		root = insert(root, 1);
		root = insert(root, 5);
		return root;
	}

	public TreeNode mockBST4() {
		TreeNode root = null;
		root = insert(root, 1);
		root = insert(root, 2);
		root = insert(root, 3);
		root = insert(root, 4);
		root = insert(root, 5);
		root = insert(root, 6);
		root = insert(root, 7);
		root = insert(root, 8);
		root = insert(root, 9);
		root = insert(root, 10);
		return root;
	}

	public TreeNode mockBST2() {
		TreeNode root = null;
		root = insert(root, 6);
		root = insert(root, 8);
		root = insert(root, 7);
		root = insert(root, 9);
		root = insert(root, 4);
		root = insert(root, 2);
		root = insert(root, 3);
		return root;
	}

	public TreeNode mockBT1() {
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(2);
		root3.right = new TreeNode(3);
		root3.left.left = new TreeNode(4);
		root3.left.right = new TreeNode(5);
		root3.right.left = new TreeNode(6);
		root3.right.right = new TreeNode(7);
		return root3;
	}

	public TreeNode mockBT2() {
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(2);
		root3.right = new TreeNode(3);
		root3.left.left = new TreeNode(4);
		root3.left.right = new TreeNode(5);
		return root3;
	}

	public TreeNode mockBT3() {
		TreeNode root3 = new TreeNode(3);
		root3.left = new TreeNode(5);
		root3.left.left = new TreeNode(6);
		root3.left.right = new TreeNode(2);
		root3.left.right.left = new TreeNode(7);
		root3.left.right.right = new TreeNode(4);

		root3.right = new TreeNode(1);
		root3.right.left = new TreeNode(0);
		root3.right.right = new TreeNode(8);
		return root3;
	}

	public TreeNode mockBT4() {
		TreeNode root3 = new TreeNode(2);
		root3.left = new TreeNode(4);
		root3.right = new TreeNode(5);
		// root3.left.left = new BLNode(0);
		return root3;
	}

	public TreeNode mockBT5() {
		TreeNode root3 = new TreeNode(1);
		root3.right = new TreeNode(3);
		root3.right.right = new TreeNode(4);
		root3.right.right.right = new TreeNode(5);
		root3.left = new TreeNode(8);
		root3.left.left = new TreeNode(9);
		root3.left.left.left = new TreeNode(10);
		root3.left.left.left.left = new TreeNode(
				11);
		return root3;
	}

	public TreeNode mockBTMirror1() {
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(3);
		root3.right = new TreeNode(2);
		root3.right.left = new TreeNode(5);
		root3.right.right = new TreeNode(4);
		return root3;
	}

	public TreeNode mockBTMirror2() {
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(2);
		root3.left.left = new TreeNode(4);
		root3.left.right = new TreeNode(5);
		root3.right = new TreeNode(3);
		return root3;
	}

	public TreeNode mockBTIsomorphism1() {
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(2);
		root3.left.left = new TreeNode(4);
		root3.left.right = new TreeNode(5);
		root3.left.right.left = new TreeNode(7);
		root3.left.right.right = new TreeNode(8);
		root3.right = new TreeNode(3);
		root3.right.left = new TreeNode(6);
		return root3;
	}

	public TreeNode mockBTIsomorphism2() {
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(3);
		root3.left.right = new TreeNode(6);
		root3.right = new TreeNode(2);
		root3.right.left = new TreeNode(4);
		root3.right.right = new TreeNode(5);
		root3.right.right.left = new TreeNode(8);
		root3.right.right.right = new TreeNode(7);
		return root3;
	}
	// Test Data - End
}
