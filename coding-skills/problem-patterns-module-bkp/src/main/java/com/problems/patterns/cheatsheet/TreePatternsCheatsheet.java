package com.problems.patterns.cheatsheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.common.model.TreeNode;

public class TreePatternsCheatsheet {

	/****************************** Tree Breadth First Search **********************************/
	/* Tree Breadth First Search:
	 * 		Binary Tree Level Order Traversal
	 * 		Reverse Level Order Traversal
	 * 		Zigzag Traversal
	 * 		Level Averages in a Binary Tree 
	 * 		Minimum Depth of a Binary Tree
	 * 		Level Order Successor
	 * 		Connect Level Order Siblings
	 * 		Connect All Level Order Siblings
	 * 		Right View of a Binary Tree
	 * 		Tree Boundary 
	 */
	// Binary Tree Level Order Traversal (easy)
	// 1.Iterative Approach using Queue:
	public void levelOrder(TreeNode root) {
		if (root == null) return;
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

	// 2.Recursive Approach:
	public List<List<Integer>> levelByLevelOrderRecursive(
			TreeNode root) {
		List<List<Integer>> result = new LinkedList<>();
		levelOrder(root, result, 0);
		return result;
	}

	public void levelOrder(TreeNode root,
			List<List<Integer>> result,
			int level) {
		if (root == null) return;
		if (result.size() <= level)
			result.add(new ArrayList<>());
		result.get(level).add(root.data);
		levelOrder(root.left, result, level + 1);
		levelOrder(root.right, result, level + 1);
	}

	// Reverse Level Order Traversal (easy) - Bottom-up/Reverse level order traversal:
	public void reverseLevelOrder(TreeNode root) {
		if (root != null) {
			Stack<TreeNode> stack = new Stack<>();
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				root = queue.poll();
				stack.push(root);
				if (root.right != null)
					queue.add(root.right);
				if (root.left != null)
					queue.add(root.left);
			}
			while (!stack.isEmpty())
				System.out.print(
						stack.pop().data + " ");
		}
	}

	// Zigzag Traversal (medium)
	/* Spiral Order Traversal:
	 *	1.Using 2 stack 
	 *  2.Using Deque    
	 */
	// 1.Using 2 stack
	public void levelOrderSpiralForm(
			TreeNode root) {
		if (root == null) return;
		Stack<TreeNode> s1 = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();
		s1.push(root);
		while (!s1.isEmpty() || !s2.isEmpty()) {
			while (!s1.isEmpty()) {
				root = s1.pop();
				System.out.print(root.data + " ");
				if (root.left != null)
					s2.push(root.left);
				if (root.right != null)
					s2.push(root.right);
			}
			while (!s2.isEmpty()) {
				root = s2.pop();
				System.out.print(root.data + " ");
				if (root.right != null)
					s1.push(root.right);
				if (root.left != null)
					s1.push(root.left);
			}
		}
	}

	// Level Averages in a Binary Tree (easy) - Average of Levels in Binary Tree - LeetCode -???
	// Breadth First Search
	public List<Double> averageOfLevels1(
			TreeNode root) {
		List<Double> result = new ArrayList<>();
		if (root == null) return result;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int n = queue.size();
			double sum = 0;
			for (int i = 0; i < n; i++) {
				TreeNode curr = queue.poll();
				sum += curr.data;
				if (curr.left != null)
					queue.add(curr.left);
				if (curr.right != null)
					queue.add(curr.right);
			}
			result.add(sum / n);
		}
		return result;
	}

	// Depth First Search
	public List<Double> averageOfLevels2(
			TreeNode root) {
		List<Double> sum = new ArrayList<>();
		List<Integer> count = new ArrayList<>();
		average(root, 0, sum, count);
		for (int i = 0; i < sum.size(); i++)
			sum.set(i, sum.get(i) / count.get(i));
		return sum;
	}

	public void average(TreeNode curr, int level,
			List<Double> sum,
			List<Integer> count) {
		if (curr == null) return;
		if (level < sum.size()) {
			sum.set(level,
					sum.get(level) + curr.data);
			count.set(level,
					count.get(level) + 1);
		} else {
			sum.add(curr.data * 1.0);
			count.add(1);
		}
		average(curr.left, level + 1, sum, count);
		average(curr.right, level + 1, sum,
				count);
	}

	// Minimum Depth of a Binary Tree (easy) - Minimum Depth of Binary Tree is equal to the nearest leaf from root.
	// DFS Approach:
	public int minDepth1(TreeNode root) {
		if (root == null) return 0;
		int left = minDepth1(root.left);
		int right = minDepth1(root.right);
		return (left == 0 || right == 0)
				? left + right + 1
				: Math.min(left, right) + 1;
	}

	// Using BFS version 1
	public int minDepth2(TreeNode root) {
		if (root == null) return 0;
		Queue<QueuePack> queue = new LinkedList<>();
		queue.add(new QueuePack(root, 1));
		while (!queue.isEmpty()) {
			QueuePack queuePack = queue.poll();
			TreeNode curr = queuePack.node;
			if (curr.left == null
					&& curr.right == null)
				return queuePack.depth;
			if (curr.left != null)
				queue.add(new QueuePack(curr.left,
						queuePack.depth + 1));
			if (curr.right != null) queue
					.add(new QueuePack(curr.right,
							queuePack.depth + 1));
		}
		return 0;
	}

	// Using BFS version 2
	public int minDepth(TreeNode root) {
		if (root == null) return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int level = 1;
		while (!queue.isEmpty()) {
			int len = queue.size();
			while (len-- > 0) {
				TreeNode node = queue.poll();
				if (node.left == null
						&& node.right == null)
					return level;
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
			}
			level++;
		}
		return 0;
	}

	// Level Order Successor of a node in Binary Tree
	public TreeNode levelOrderSuccessor(
			TreeNode root, TreeNode key) {
		if (root == null) return root;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			if (curr.left != null)
				queue.add(curr.left);
			if (curr.right != null)
				queue.add(curr.right);
			if (curr.data == key.data) break;
		}
		return queue.peek();
	}

	// Level Order Predecessor of a node in Binary Tree
	public TreeNode levelOrderPredecessor(
			TreeNode root, TreeNode key) {
		if (root == null) return root;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		TreeNode prev = null;
		while (!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			if (curr.data == key.data) break;
			else prev = curr;
			if (curr.left != null)
				queue.add(curr.left);
			if (curr.right != null)
				queue.add(curr.right);
		}
		return prev;
	}

	// Connect Level Order Siblings (medium) - Check Below
	// Connect All Level Order Siblings (medium) - Check Below

	// Right View of a Binary Tree (easy)
	// Approach1:
	static int maxRightLevel = 0;

	public void rightViewOfTree2(TreeNode root) {
		rightViewOfTree2(root, 1);
	}

	public void rightViewOfTree2(TreeNode root,
			int level) {
		if (root == null) return;
		if (maxRightLevel < level) {
			System.out.print(root.data + " ");
			maxRightLevel = level;
		}
		rightViewOfTree2(root.right, level + 1);
		rightViewOfTree2(root.left, level + 1);
	}

	/* Approach2: 
	 * 	The core idea of this algorithm:
	 * 	   1.Each depth/level of the tree only select one node.
	 * 	   2. View depth/level is current size of result list.
	 */
	// Approach2:
	public List<Integer> rightSideView(
			TreeNode root) {
		List<Integer> result = new ArrayList<>();
		rightSideView(root, result, 0);
		return result;
	}

	public void rightSideView(TreeNode root,
			List<Integer> result, int level) {
		if (root == null) return;
		if (level == result.size())
			result.add(root.data);
		rightSideView(root.right, result,
				level + 1);
		rightSideView(root.left, result,
				level + 1);
	}

	// Tree Boundary (hard) - ???

	/****************************** Tree Depth First Search **********************************/
	/* Pattern: Tree Depth First Search
	 * 		Binary Tree Path Sum
	 * 		All Paths for a Sum
	 * 		Sum of Path Numbers
	 * 		Path With Given Sequence
	 * 		Count Paths for a Sum
	 * 		Tree Diameter 
	 * 		Path with Maximum Sum 
	 */
	// Binary Tree Path Sum
	/* Path Sum I:
	 *  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
	 *  along the path equals the given sum.
	 *  Note: A leaf is a node with no children.
	 *  Example: Given the below binary tree and sum = 22,
	 */
	// Path Sum I:
	public boolean hasPathSum(TreeNode root,
			int sum) {
		if (root == null) return false;
		if (sum == root.data && root.left == null
				&& root.right == null)
			return true;
		return hasPathSum(root.left,
				sum - root.data)
				|| hasPathSum(root.right,
						sum - root.data);
	}

	/*
	 * Path Sum II: Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	 * Note: A leaf is a node with no children.
	 * Example: Given the below binary tree and sum = 22,
	 */
	// Path Sum II:
	public List<List<Integer>> pathSumII(
			TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> eachList = new ArrayList<>();
		pathSum(root, result, eachList, sum);
		return result;
	}

	public void pathSum(TreeNode root,
			List<List<Integer>> result,
			List<Integer> eachList, int sum) {
		if (root == null) return;
		eachList.add(root.data);
		if (root.data == sum && root.left == null
				&& root.right == null)
			result.add(new ArrayList<>(eachList));
		pathSum(root.left, result, eachList,
				sum - root.data);
		pathSum(root.right, result, eachList,
				sum - root.data);
		eachList.remove(eachList.size() - 1);
	}

	/*
	 * Path Sum III: You are given a binary tree in which each node contains an integer value. Find the number of paths
	 * that sum to a given value. The path does not need to start or end at the root or a leaf, but it must go downwards
	 * (traveling only from parent nodes to child nodes). The tree has no more than 1,000 nodes and the values are in
	 * the range -1,000,000 to 1,000,000. 
	 * Example: root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
	 */
	// Path Sum III:
	// Brute Force DFS Approach: Time - O(n^2)
	public int pathSumIII(TreeNode root,
			int sum) {
		if (root == null) return 0;
		return pathSumFrom(root, sum)
				+ pathSumIII(root.left, sum)
				+ pathSumIII(root.right, sum);
	}

	public int pathSumFrom(TreeNode root,
			int sum) {
		if (root == null) return 0;
		return (sum == root.data ? 1 : 0)
				+ pathSumFrom(root.left,
						sum - root.data)
				+ pathSumFrom(root.right,
						sum - root.data);
	}

	// Prefix Sum Method: Time: O(n), Space:O(n)
	public int pathSumIII2(TreeNode root,
			int sum) {
		HashMap<Integer, Integer> preSum = new HashMap<>();
		preSum.put(0, 1);
		return helper(root, 0, sum, preSum);
	}

	public int helper(TreeNode root, int currSum,
			int target,
			HashMap<Integer, Integer> preSum) {
		if (root == null) return 0;
		currSum += root.data;
		int res = preSum.getOrDefault(
				currSum - target, 0);
		preSum.put(currSum,
				preSum.getOrDefault(currSum, 0)
						+ 1);
		res += helper(root.left, currSum, target,
				preSum)
				+ helper(root.right, currSum,
						target, preSum);
		preSum.put(currSum,
				preSum.get(currSum) - 1);
		return res;
	}

	// All Paths for a Sum - Check all the root to leaf path for the given Sum
	public void sumOfNodesFromRootToLeaf(
			TreeNode root, int sum) {
		ArrayList<Integer> path = new ArrayList<>();
		if (rootToLeafUtil1(root, sum, path)) {
			System.out.print("Sum Path for " + sum
					+ " is:");
			path.stream().forEach(k -> System.out
					.print(k + " "));
		} else {
			System.out.println(
					"Sum is not present");
		}
	}

	// Approach1:
	private boolean rootToLeafUtil1(TreeNode root,
			int sum, ArrayList<Integer> path) {
		if (root == null) return false;
		if (root.left == null
				&& root.right == null) {
			if (sum == root.data) {
				path.add(root.data);
				return true;
			} else {
				return false;
			}
		}
		if (rootToLeafUtil1(root.left,
				sum - root.data, path)) {
			path.add(root.data);
			return true;
		}
		if (rootToLeafUtil1(root.right,
				sum - root.data, path)) {
			path.add(root.data);
			return true;
		}
		return false;
	}

	// Approach2:
	public boolean rootToLeafUtil2(TreeNode root,
			int currSum, int sum,
			ArrayList<Integer> path) {
		if (root == null) return false;
		currSum += root.data;
		if (root.left == null
				&& root.right == null) {
			if (currSum == sum) return true;
			else return false;
		}
		boolean flag = rootToLeafUtil2(root.left,
				currSum, sum, path);
		if (!flag)
			flag = rootToLeafUtil2(root.right,
					currSum, sum, path);
		if (!flag) path.remove(path.size() - 1);
		return false;
	}

	// Sum of Path Numbers
	// Path With Given Sequence
	// Count Paths for a Sum
	// Tree Diameter - Check Below
	// Path with Maximum Sum - Check Below
	// Brute Force Approach:
	public int maxPathSum1(TreeNode root) {
		if (root == null)
			return Integer.MIN_VALUE;
		int left = maxPathSumUtil(root.left);
		int right = maxPathSumUtil(root.right);
		return Math.max(left + right + root.data,
				Math.max(maxPathSum(root.left),
						maxPathSum(root.right)));
	}

	private int maxPathSumUtil(TreeNode root) {
		if (root == null) return 0;
		return Math.max(0, root.data + Math.max(
				maxPathSumUtil(root.left),
				maxPathSumUtil(root.right)));
	}

	/****************************** Tree Traversal Modification **********************************/
	// Convert a given Binary Tree to Doubly Linked List:
	TreeNode head = null, prev = null;

	// Convert a given Binary Tree to Doubly Linked List:(using Inorder Traversal)
	/* Approach1:
	 * The idea is to do inorder traversal of the binary tree. While doing inorder traversal, keep track of the previously 
	 * visited node in a variable say prev. For every visited node, make it next of prev and previous of this node as prev.
	 * Note: Here left -> prev, right -> next
	 */
	public TreeNode convertBinaryTreeToDLL1(
			TreeNode root) {
		head = null;
		return binaryTreeToDLLUtil1(root);
	}

	public TreeNode binaryTreeToDLLUtil1(
			TreeNode root) {
		if (root == null) return null;
		binaryTreeToDLLUtil1(root.left);
		if (prev == null) {
			head = root;
		} else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;
		binaryTreeToDLLUtil1(root.right);
		return head;
	}

	// Flatten Binary Tree to Linked List:
	// Modification of Inorder Traversal
	public void flatten(TreeNode root) {
		if (root == null) return;
		flatten(root.left);
		if (root.left != null) {
			TreeNode tempRight = root.right;
			root.right = root.left;
			root.left = null;
			while (root.right != null)
				root = root.right;
			root.right = tempRight;
		}
		flatten(root.right);
	}

	// Populating Next Right Pointers in Each Node I, II:

	// Approach1: Using extended level order traversal - Level order traversal with null markers;
	// Time Complexity: O(n) & Space Complexity: O(n)
	public void connectNodesAtSameLevel1(
			TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		TreeNode curr = null;
		while (!q.isEmpty()) {
			curr = q.poll();
			if (curr != null) {
				curr.next = q.peek();
				if (curr.left != null)
					q.add(curr.left);
				if (curr.right != null)
					q.add(curr.right);
			} else if (!q.isEmpty()) {
				q.add(null);
			}
		}
	}

	// Approach2: Using extended pre order traversal(Works only for complete binary tree or perfect binary tree)
	// Time Complexity:O(n); Space complexity: Only recursion stack space or O(1)
	public void connectNodesAtSameLevel2(
			TreeNode root) {
		if (root == null) return;
		if (root.left == null
				&& root.right == null)
			return;
		if (root.left != null
				&& root.right != null)
			root.left.next = root.right;
		if (root.next != null)
			root.right.next = root.next.left;
		connectNodesAtSameLevel2(root.left);
		connectNodesAtSameLevel2(root.right);
	}

	/* Approach3 : Connect nodes at same level using constant extra space
	 *    Traverse the nextRight ptr before the left & right ptr, then traverse getNextRight().
	 */
	// i.Approach30: Recursive approach
	public void connectNodesAtSameLevel30(
			TreeNode root) {
		if (root == null) return;
		if (root.next != null)
			connectNodesAtSameLevel30(root.next);
		if (root.left != null) {
			if (root.right != null) {
				root.left.next = root.right;
				root.right.next = getNextRight(
						root);
			} else {
				root.left.next = getNextRight(
						root);
			}
			connectNodesAtSameLevel30(root.left);
		} else if (root.right != null) {
			root.right.next = getNextRight(root);
			connectNodesAtSameLevel30(root.right);
		} else {
			connectNodesAtSameLevel30(
					getNextRight(root));
		}
	}

	private TreeNode getNextRight(TreeNode node) {
		TreeNode temp = node.next;
		while (temp != null) {
			if (temp.left != null)
				return temp.left;
			if (temp.right != null)
				return temp.right;
			temp = temp.next;
		}
		return null;
	}

	// Recover Binary Search Tree:

	TreeNode	firstNode	= null;
	TreeNode	secondNode	= null;
	TreeNode	prevNode	= new TreeNode(
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

	// Minimum Absolute Difference in BST:
	int		min		= Integer.MAX_VALUE;
	Integer	prevVal	= null;

	// Approach1: Inorder Traversal Modification
	public int getMinimumDifference1(
			TreeNode root) {
		if (root == null) return min;
		inorder(root);
		return min;
	}

	public void inorder(TreeNode root) {
		if (root == null) return;
		inorder(root.left);
		if (prevVal != null) min = Math.min(min,
				root.data - prevVal);
		prevVal = root.data;
		inorder(root.right);
	}

	// Find Mode in Binary Search Tree:
	// Approach1: Brute Force Approach using Map
	public int[] findMode1(TreeNode root) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] max = new int[1];
		max[0] = 0;
		inorder(root, map, max);
		List<Integer> list = new LinkedList<>();
		for (int key : map.keySet())
			if (map.get(key) == max[0])
				list.add(key);
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++)
			result[i] = list.get(i);
		return result;
	}

	public void inorder(TreeNode root,
			Map<Integer, Integer> map,
			int[] max) {
		if (root == null) return;
		map.put(root.data,
				map.getOrDefault(root.data, 0)
						+ 1);
		max[0] = Math.max(max[0],
				map.get(root.data));
		inorder(root.left, map, max);
		inorder(root.right, map, max);
	}

	// Approach2: Without Map
	int	curCount	= 1;
	int	maxCount	= 0;

	public int[] findMode(TreeNode root) {
		if (root == null) return new int[0];
		List<Integer> list = new ArrayList<>();
		inorder(root, list);
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			res[i] = list.get(i);
		return res;
	}

	// Simple Modification of inorder traversal
	private void inorder(TreeNode root,
			List<Integer> list) {
		if (root == null) return;
		inorder(root.left, list);
		if (prev != null) {
			if (root.data == prev.data)
				curCount++;
			else curCount = 1;
		}
		prev = new TreeNode(root.data);
		if (curCount > maxCount) {
			maxCount = curCount;
			list.clear();
			list.add(root.data);
		} else if (curCount == maxCount)
			list.add(root.data);
		inorder(root.right, list);
	}

	/****************************** Optimized Tree Traversal **********************************/

	// Most Frequent Subtree Sum:
	public int[] findFrequentTreeSum(
			TreeNode root) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		int[] max = new int[1];
		subTreeSum(root, map, max);
		for (Integer key : map.keySet())
			if (map.get(key) == max[0])
				list.add(key);
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++)
			result[i] = list.get(i);
		return result;
	}

	public int subTreeSum(TreeNode root,
			Map<Integer, Integer> map,
			int[] max) {
		if (root == null) return 0;
		int sum = root.data;
		sum += subTreeSum(root.left, map, max);
		sum += subTreeSum(root.right, map, max);
		map.put(sum,
				map.getOrDefault(sum, 0) + 1);
		max[0] = Math.max(max[0], map.get(sum));
		return sum;
	}

	// Binary Tree Maximum Path Sum:
	// Efficient Approach:
	int maxValue;

	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
		maxPathDown(root);
		return maxValue;
	}

	private int maxPathDown(TreeNode node) {
		if (node == null) return 0;
		int left = Math
				.max(maxPathDown(node.left), 0); // Compare with zero to eliminate the -ve values
		int right = Math
				.max(maxPathDown(node.right), 0);
		maxValue = Math.max(maxValue,
				left + right + node.data);
		return Math.max(left, right) + node.data;
	}

	// Diameter of Binary Tree:
	int max = 0;

	public int diameterOfBinaryTree(
			TreeNode root) {
		if (root == null) return 0;
		height2(root);
		return max;
	}

	// Modification of Height
	public int height2(TreeNode root) {
		if (root == null) return 0;
		int left = height2(root.left);
		int right = height2(root.right);
		max = Math.max(max, left + right);
		return Math.max(left, right) + 1;
	}

	// Check if given BT is Height Balanced/Balanced Binary Tree:
	// Approach 2: This approach avoids two recursive calls & check the tree is balanced or not while calculating the
	// height
	// Time Complexity: O(n);
	public boolean isBinaryTreeBalanced2(
			TreeNode root) {
		return checkHeight(
				root) != Integer.MIN_VALUE;
	}

	public int checkHeight(TreeNode root) {
		if (root == null) return 0;
		int leftHeight = checkHeight(root.left);
		if (leftHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		int rightHeight = checkHeight(root.right);
		if (rightHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return Math.abs(
				leftHeight - rightHeight) <= 1
						? Math.max(leftHeight,
								rightHeight) + 1
						: Integer.MIN_VALUE;
	}
}

class QueuePack {
	public int		depth;
	public TreeNode	node;

	public QueuePack(TreeNode node, int depth) {
		this.depth = depth;
		this.node = node;
	}
}
