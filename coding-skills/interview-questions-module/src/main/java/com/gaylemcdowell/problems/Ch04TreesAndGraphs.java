package com.gaylemcdowell.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Random;

public class Ch04TreesAndGraphs {

	public static void main(String[] args) {
		Ch04TreesAndGraphs ob = new Ch04TreesAndGraphs();

		System.out.println("Minimal Tree: ");
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		ob.minimalBST(arr);

		TreeNode root = ob.mockData1();
		System.out.println("\nDFS: ");
		ob.listOfDepthsDFS(root);
		System.out.println("BFS: ");
		ob.listOfDepthsBFS2(root);
		System.out.println("BFS: ");
		ob.listOfDepthsBFS1(root);

		root = ob.mockData2();
		System.out.println("\nCheck Balanced: " + ob.isBalanced1(root));

		root = ob.mockData2();
		System.out.println("Validate BST: " + ob.validateBST(root));

		root = ob.mockData1();
		System.out.println("Find Successor: " + ob.findSuccessor(root, 5));

		System.out.println("Build Order: ");
		char[] projects = { 'a', 'b', 'c', 'd', 'e', 'f' };
		char[][] dependencies = { { 'a', 'd' }, { 'f', 'b' }, { 'b', 'd' }, { 'f', 'a' }, { 'd', 'c' } };
		ob.buildOrder(projects, dependencies);

		root = ob.mockData1();
		System.out.println("\nFirst Common Ancestor: ");
		TreeNode result = ob.firstCommonAncestor(root, 1, 6);
		if (result != null) System.out.println("FCA: " + result.data);

		System.out.println("BST Sequences: ");
		TreeNode node = new TreeNode(100);
		int[] array = { 100, 50, 20, 75, 150, 120, 170 };
		for (int a : array)
			node.insertInOrder(a);

		ArrayList<LinkedList<Integer>> allSeq = allSequences(node);
		for (LinkedList<Integer> list : allSeq)
			System.out.println(list);

		System.out.println(allSeq.size());

		System.out.println("Get Random Node: ");
		int[] array2 = { 1, 0, 6, 2, 3, 9, 4, 5, 8, 7 };
		for (int x : array2)
			ob.insertInOrder(x);
		System.out.println("Random Node: " + ob.getRandomNode().data);
		System.out.println("Random Node: " + ob.getRandomNode().data);

		root = ob.mockData3();
		System.out.println("No of paths: " + ob.countPathFromAllNodeSum(root, 7));

	}

	/*
	 * 1.Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
	 */

	/*
	 * 2.Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm to create 
	 * a binary search tree with minimal height.
	 */

	public void minimalBST(int[] arr) {
		TreeNode root = minimalBST(arr, 0, arr.length - 1);
		System.out.println("Inorder Traversal: ");
		inorderTraversal(root);
	}

	public TreeNode minimalBST(int[] arr, int start, int end) {
		if (start > end) return null;

		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(arr[mid]);
		root.left = minimalBST(arr, start, mid - 1);
		root.right = minimalBST(arr, mid + 1, end);
		return root;
	}

	/*
	 * 3.List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth 
	 * (e.g., if you have a tree with depth 0, you'll have 0 linked lists).
	 *  i. Using DFS(Pre Order Traversal)
	 *  ii. USing BFS(Level Order Traversal)
	 */
	public void listOfDepthsDFS(TreeNode root) {
		List<List<Integer>> listOfNodes = new LinkedList<>();
		listOfDepths(root, listOfNodes, 0);

		// Print the result
		printListOfNodes(listOfNodes);
	}

	public void listOfDepths(TreeNode root, List<List<Integer>> listOfNodes, int level) {
		if (root == null) return;
		List<Integer> list = null;
		if (listOfNodes.size() == level) {
			list = new LinkedList<>();
			listOfNodes.add(list);
		} else {
			list = listOfNodes.get(level);
		}
		list.add(root.data);
		listOfDepths(root.left, listOfNodes, level + 1);
		listOfDepths(root.right, listOfNodes, level + 1);
	}

	public ArrayList<LinkedList<TreeNode>> listOfDepthsBFS1(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();

		LinkedList<TreeNode> current = new LinkedList<>();
		if (root != null) current.add(root);

		while (current.size() > 0) {
			result.add(current); // Add previous level
			LinkedList<TreeNode> parents = current; // Go to next level
			current = new LinkedList<TreeNode>();
			for (TreeNode parent : parents) {

				if (parent.left != null) {
					current.add(parent.left);
				}
				if (parent.right != null) {
					current.add(parent.right);
				}
			}
		}

		// Print the data
		System.out.println("Nodes at depth level");
		for (LinkedList<TreeNode> list : result) {
			ListIterator<TreeNode> iter = list.listIterator();
			while (iter.hasNext())
				System.out.print(iter.next().data + " ");

			System.out.println();
		}

		return result;
	}

	// Approach2: Using BFS; Similar to level by traversal
	public List<List<Integer>> listOfDepthsBFS2(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> level;
		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<TreeNode> q2 = new LinkedList<>();
		q1.add(root);
		TreeNode current = null;
		while (!q1.isEmpty() || !q2.isEmpty()) {
			level = new ArrayList<>();
			while (!q1.isEmpty()) {
				current = q1.poll();
				level.add(current.data);
				if (current.left != null) q2.add(current.left);
				if (current.right != null) q2.add(current.right);
			}
			if (!level.isEmpty()) result.add(level);
			level = new ArrayList<>();
			while (!q2.isEmpty()) {
				current = q2.poll();
				level.add(current.data);
				if (current.left != null) q1.add(current.left);
				if (current.right != null) q1.add(current.right);
			}
			if (!level.isEmpty()) result.add(level);
		}
		// Print the result
		printListOfNodes(result);
		return result;
	}

	/*
	 * 4.Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of this question, 
	 * a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
	 */

	public boolean isBalanced1(TreeNode root) {
		if (root == null) return true;
		int diff = Math.abs(height(root.left) - height(root.right));
		return (diff <= 1) && isBalanced1(root.left) && isBalanced1(root.right);
	}

	public int height(TreeNode root) {
		if (root == null) return 0;

		return 1 + Integer.max(height(root.left), height(root.right));
	}

	// Approach 2: This approach avoids two recursive calls & check the tree is balanced or not while calculating the
	// height
	// Time Complexity: O(n);
	public boolean isBalanced2(TreeNode root) {
		return checkHeight(root) != Integer.MIN_VALUE;
	}

	public int checkHeight(TreeNode root) {
		if (root == null) return 0;

		int leftHeight = checkHeight(root.left);
		if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		int rightHeight = checkHeight(root.right);
		if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		return Math.abs(leftHeight - rightHeight) <= 1 ? Math.max(leftHeight, rightHeight) + 1 : Integer.MIN_VALUE;
	}

	/*
	 * 5.Validate BST: Implement a function to check if a binary tree is a binary search tree.
	 */
	public boolean validateBST(TreeNode root) {
		return validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean validateBST(TreeNode root, int min, int max) {
		if (root == null) return true;

		if (root.data < min || root.data > max) return false;

		return validateBST(root.left, min, root.data) && validateBST(root.right, root.data, max);
	}
	/*
	 * 6.Successor: Write an algorithm to find the "next" node (i .e., in-order successor) of a given node in a binary search tree. 
	 * You may assume that each node has a link to its parent.
	 */

	public int findSuccessor(TreeNode root, int data) {
		if (root == null) return -1;

		if (data == root.data) {
			// Find the next node in the right subtree
			TreeNode rightSubTree = root.right;
			if (rightSubTree != null) {
				while (rightSubTree.left != null)// Keep moving to left side, to find the next(successor) node in BST
					rightSubTree = rightSubTree.left;
				return rightSubTree.data;
			}
			return -1;
		} else if (data < root.data) {
			int successor = findSuccessor(root.left, data);
			if (successor == -1) // If there is no element in right side, then root element should be next element
				successor = root.data;
			return successor;
		} else {
			return findSuccessor(root.right, data);
		}
	}

	/*
	 * 7.Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the 
	 * second project is dependent on the first project). All of a project's dependencies must be built before the project is. 
	 * Find a build order that will allow the projects to be built. If there is no valid build order, return an error.
	 * EXAMPLE
	 * Input:
	 * projects: a, b, c, d, e, f -> n=6
	 * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c) -> {{0,3}, {5,1}, {1,3}, {5,0}, {3,2}}
	 * Output: f, e, a, b, d, c
	 */

	public void buildOrder(char[] projects, char[][] dependencies) {
		int n = projects.length;
		LinkedList<Character>[] adjList = new LinkedList[n];

		for (int i = 0; i < n; i++)
			adjList[i] = new LinkedList<>();

		for (int i = 0; i < dependencies.length; i++) {
			int start = charToInt(dependencies[i][0]);
			adjList[start].add(dependencies[i][1]);
		}

		topoSortUsingIndegree(adjList, n);
	}

	// Kahn’s algorithm for Topological Sorting
	/*	A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
		Proof: There’s a simple proof to the above fact is that a DAG does not contain a cycle which means that all paths will be of finite length.
		       Now let S be the longest path from u(source) to v(destination). Since S is the longest path there can be no incoming edge to u and 
		       no outgoing edge from v, if this situation had occurred then S would not have been the longest path
		=> indegree(u) = 0 and outdegree(v) = 0*/
	public void topoSortUsingIndegree(LinkedList<Character>[] adjList, int n) {
		Queue<Character> queue = new LinkedList<>();
		ArrayList<Character> linearOrder = new ArrayList<>();
		int[] indegree;
		int count = 0;

		// Step-1: Compute in-degree
		indegree = indegree(adjList, n);

		// Step-2: Pick all the vertices with in-degree as 0 and add them into a queue
		for (int i = 0; i < n; i++)
			if (indegree[i] == 0) queue.add(intToChar(i));

		// Step-3:Remove a vertex from the queue
		while (!queue.isEmpty()) {
			char vertex = queue.poll();
			linearOrder.add(vertex);
			// 1.Increment count of visited nodes by 1.
			count++;

			// 2.Decrease in-degree by 1 for all its neighboring nodes
			ListIterator<Character> iter = adjList[charToInt(vertex)].listIterator();
			while (iter.hasNext()) {
				char data = iter.next();
				if (--indegree[charToInt(data)] == 0) queue.add(data); // 3.If in-degree of a neighboring nodes is
																		// reduced to zero, then add it to the queue.
			}
		}
		// Step-4:If count of visited nodes is equal to the number of nodes in the graph then print the topological sort
		if (count == n) {
			linearOrder.stream().forEach(i -> System.out.print(i + "-"));
		} else {
			System.out.println("Graph is not an a DAG and also it contains a cycle.");
		}
	}

	private int[] indegree(LinkedList<Character>[] adjList, int n) {
		int[] indegree = new int[n];

		for (int i = 0; i < n; i++) {
			if (adjList[i].size() > 0) {
				ListIterator<Character> iterator = adjList[i].listIterator();
				while (iterator.hasNext())
					indegree[charToInt(iterator.next())]++;
			}
		}
		return indegree;
	}

	public int charToInt(char ch) {
		return (int) (ch - 'a');
	}

	public char intToChar(int d) {
		return (char) ('a' + d);
	}

	/*
	 * 8.First Common Ancestor: Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. 
	 * Avoid storing additional nodes in a data structure. NOTE: This is not necessarily a binary search tree.
	 */
	public TreeNode firstCommonAncestor(TreeNode root, int data1, int data2) {
		if (root == null) return null;

		if (root.data == data1 || root.data == data2) return root;

		TreeNode left = firstCommonAncestor(root.left, data1, data2);
		TreeNode right = firstCommonAncestor(root.right, data1, data2);

		if (left != null && right != null) return root;
		return left != null ? left : right;
	}

	/*
	 * 9.BST Sequences: A binary search tree was created by traversing through an array from left to right and inserting each 
	 * element. Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
	 */
	public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
			ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
		/* One list is empty. Add the remainder to [a cloned] prefix and
		 * store result. */
		if (first.size() == 0 || second.size() == 0) {
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
			result.addAll(first);
			result.addAll(second);
			results.add(result);
			return;
		}

		/* Recurse with head of first added to the prefix. Removing the
		 * head will damage first, so we’ll need to put it back where we
		 * found it afterwards. */
		int headFirst = first.removeFirst();
		prefix.addLast(headFirst);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		first.addFirst(headFirst);

		/* Do the same thing with second, damaging and then restoring
		 * the list.*/
		int headSecond = second.removeFirst();
		prefix.addLast(headSecond);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		second.addFirst(headSecond);
	}

	public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

		if (node == null) {
			result.add(new LinkedList<Integer>());
			return result;
		}

		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.data);

		/* Recurse on left and right subtrees. */
		ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
		ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

		/* Weave together each list from the left and right sides. */
		for (LinkedList<Integer> left : leftSeq) {
			for (LinkedList<Integer> right : rightSeq) {
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
				weaveLists(left, right, weaved, prefix);
				result.addAll(weaved);
			}
		}
		return result;
	}

	/*
	 * 10.Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an algorithm to determine 
	 * if T2 is a subtree of Tl. 
	 * A tree T2 is a subtree ofTi if there exists a node n in Ti such that the subtree of n is identical to T2. That is, if you 
	 * cut off the tree at node n, the two trees would be identical.
	 */
	// Check if a binary tree is subtree of another binary tree
	/*
	 * Time Complexity: O(n+km)
	 * Explanation: A naive answer would be to say that it is 0 (nm) time, wheren is the number of nodes in s and m is the
	 * number of nodes in t. While this is technically correct, a little more thought can produce a tighter bound. We do
	 * not actually call matchTree on every node in n. Rather, we call it k times, where k is the number of occurrences of
	 * t's root in s. The runtime is closer to O( n + km).
	 */
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null) return false;

		if (s.data == t.data && isSameTree(s, t)) return true;

		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) return true;
		if (p == null || q == null) return false;
		return (p.data == q.data && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
	}

	// Approach2: Time Complexity: O(n) -> Using inorder & preorder of both tree
	public boolean isSubtree2(TreeNode s, TreeNode t) {
		return false;
	}

	// Approach3: Time Complexity: O(m+n): Use Preorder traversal and build the string for the both, finally check the
	// substring
	public boolean isSubtree3(TreeNode T1, TreeNode T2) {
		if (T1 == null) return true;
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		buildTree(T1, sb1);
		buildTree(T2, sb2);

		return (sb1.indexOf(sb2.toString()) != -1);
	}

	public void buildTree(TreeNode root, StringBuilder sb) {
		if (root == null) {
			sb.append("X");
			return;
		}

		sb.append(root.data);
		buildTree(root.left, sb);
		buildTree(root.right, sb);
	}

	/*
	 * 11.Random Node: You are implementing a binary tree class from scratch which, in addition to insert, find, and delete, has a 
	 * method getRandomNode() which returns a random node from the tree. All nodes should be equally likely to be chosen. Design
	 * and implement an algorithm for getRandomNode, and explain how you would implement the rest of the methods.
	 */
	TreeNode root = null;

	public void insertInOrder(int value) {
		if (root == null) root = new TreeNode(value);
		else root.insertInOrder(value);
	}

	public int size() {
		return root == null ? 0 : root.size();
	}

	public TreeNode getRandomNode() {
		if (root == null) return null;

		Random random = new Random();
		int i = random.nextInt(size());
		return root.getIthNode(i);
	}
	/*
	 * 12.Paths with Sum: You are given a binary tree in which each node contains an integer value (which might be positive or 
	 * negative). Design an algorithm to count the number of paths that sum to a given value. The path does not need to start or 
	 * end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
	 */

	public int countPathFromAllNodeSum(TreeNode root, int targetSum) {
		if (root == null) return 0;

		int pathFromLeft = countPathsWithSum(root, 0, targetSum);
		int pathOnLeft = countPathFromAllNodeSum(root.left, targetSum);
		int pathOnRight = countPathFromAllNodeSum(root.right, targetSum);
		return pathFromLeft + pathOnLeft + pathOnRight;
	}

	public int countPathsWithSum(TreeNode root, int currSum, int targetSum) {
		if (root == null) return 0;

		currSum += root.data;
		int count = 0;
		if (currSum == targetSum) count++;

		count += countPathsWithSum(root.left, currSum, targetSum);
		count += countPathsWithSum(root.right, currSum, targetSum);
		return count;
	}

	public void countRootToAnyNodeSum(TreeNode root, int sum) {
		System.out.println("No of paths: " + countPathsWithSum(root, 0, sum));
	}

	/********************** Util Methods *******************/
	public void inorderTraversal(TreeNode root) {
		if (root != null) {
			inorderTraversal(root.left);
			System.out.print(root.data + " ");
			inorderTraversal(root.right);
		}
	}

	public void printListOfNodes(List<List<Integer>> listOfNodes) {
		// Print the data
		System.out.println("Nodes at depth level");
		for (List<Integer> list : listOfNodes) {
			ListIterator<Integer> iter = list.listIterator();
			while (iter.hasNext())
				System.out.print(iter.next() + " ");

			System.out.println();
		}
	}

	/********************** Mock Data *******************/
	public TreeNode mockData1() {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = minimalBST(arr, 0, arr.length - 1);
		return root;
	}

	public TreeNode mockData2() {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(3);
		root.right.right.right = new TreeNode(4);
		root.right.right.right.right = new TreeNode(8);
		root.right.right.right.right.right = new TreeNode(6);
		return root;
	}

	public TreeNode mockData3() {
		int[] arr = { 5, 2, 3, 2, 5, 6, 1, 3, 2, 2 };
		TreeNode root = minimalBST(arr, 0, arr.length - 1);
		return root;
	}
}

/* One node of a binary tree. The data element stored is a single 
 * character.
 */
class TreeNode {
	public int		data;
	public TreeNode	left;
	public TreeNode	right;
	private int		size	= 0;

	public TreeNode(int d) {
		data = d;
		size = 1;
	}

	public void insertInOrder(int d) {
		if (d <= data) {
			if (left == null) left = new TreeNode(d);
			else left.insertInOrder(d);
		} else {
			if (right == null) right = new TreeNode(d);
			else right.insertInOrder(d);
		}
		size++;
	}

	public int size() {
		return size;
	}

	public TreeNode find(int d) {
		if (d == data) return this;
		else if (d <= data) return left != null ? left.find(d) : null;
		else if (d > data) return right != null ? right.find(d) : null;
		return null;
	}

	public TreeNode getRandomNode() {
		int leftSize = left == null ? 0 : left.size();
		Random random = new Random();
		int index = random.nextInt(size);

		if (index < leftSize) return left.getRandomNode();
		else if (index == leftSize) return this;
		else return right.getRandomNode();
	}

	public TreeNode getIthNode(int i) {
		int leftSize = left == null ? 0 : left.size();

		if (i < leftSize) return left.getIthNode(i);
		else if (i == leftSize) return this;
		else return right.getIthNode(i - (leftSize + 1));
	}
}