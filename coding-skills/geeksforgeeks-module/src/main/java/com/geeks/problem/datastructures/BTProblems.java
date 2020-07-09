package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import com.common.model.Height;
import com.common.model.ObjectState;
import com.common.model.OrderIndex;
import com.common.model.QueuePack;
import com.common.model.TreeNode;

public class BTProblems {

	/************************ BT Basic Operations ***********************/
	// Temporarily BST insert operations
	public TreeNode insert(TreeNode root,
			int data) {
		if (root == null) {
			root = new TreeNode(data);
		} else if (data <= root.data) {
			root.left = insert(root.left, data);
		} else {
			root.right = insert(root.right, data);
		}
		return root;
	}

	// Insert: Add element level by level
	static TreeNode BTinsert(TreeNode root,
			int data) {
		TreeNode newNode = new TreeNode(data);
		if (root == null) {
			return newNode;
		} else {
			TreeNode temp;
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				temp = queue.remove(); // NoSuchElementException - if this queue is empty
				if (temp.left == null) {
					temp.left = newNode;
					break;
				} else {
					queue.add(temp.left);
				}

				if (temp.right == null) {
					temp.right = newNode;
					break;
				} else {
					queue.add(temp.right);
				}
			}
		}
		return root;
	}

	// Delete the subtree of the corresponding node
	static TreeNode deleteSubTree(TreeNode root,
			int nodeIndex) {
		if (root != null) {
			if (nodeIndex == 0) {
				root = null;
			} else {
				TreeNode temp;
				int count = 0;
				Queue<TreeNode> queue = new LinkedList<>();
				queue.add(root);
				while (!queue.isEmpty()) {
					temp = queue.remove();
					if (temp.left != null) {
						if (nodeIndex == ++count) {
							temp.left = null;
							break;
						}
						queue.add(temp.left);
					}
					if (temp.right != null) {
						if (nodeIndex == ++count) {
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

	public TreeNode delete(TreeNode root,
			int data) {
		return root;
	}

	public TreeNode search(TreeNode root,
			int data) {
		return null;
	}

	/************************
	 * BT Traversal Problems - DFS/BFS
	 ***********************/
	/*
	 * DFS Traversals overview: - Preorder/Inorder/Postorder 1.Recursive Approach
	 * 2.Iterative Approach: - PreOrder - Stack - InOrder - Stack - PostOrder - 2
	 * Stack or single stack
	 */
	public void preOrder(TreeNode root) {
		if (root == null) return;

		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	public void inOrder(TreeNode root) {
		if (root == null) return;

		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}

	public void postOrder(TreeNode root) {
		if (root == null) return;

		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}

	public void preOrderCharNode(TreeNode root) {
		if (root == null) return;

		System.out.print(root.data + " ");
		preOrderCharNode(root.left);
		preOrderCharNode(root.right);
	}

	public void inOrderCharNode(TreeNode root) {
		if (root == null) return;

		inOrderCharNode(root.left);
		System.out.print(root.data + " ");
		inOrderCharNode(root.right);
	}

	public void postOrderCharNode(TreeNode root) {
		if (root == null) return;

		postOrderCharNode(root.left);
		postOrderCharNode(root.right);
		System.out.print(root.data + " ");
	}

	/*
	 * DFS Traversals Iterative approach overview: PreOrder - Stack InOrder - Stack
	 * PostOrder - 2 Stack or single stack
	 */
	public void preOrderIterative(TreeNode root) {
		if (root != null) {
			Stack<TreeNode> stack = new Stack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				root = stack.pop();
				System.out.print(root.data + " ");
				if (root.right != null)
					stack.push(root.right);
				if (root.left != null)
					stack.push(root.left);
			}
		}
	}

	public void inOrderIterative(TreeNode root) {
		if (root != null) {
			Stack<TreeNode> stack = new Stack<>();
			while (true) {
				if (root != null) {
					stack.push(root);
					root = root.left;
				} else {
					if (stack.isEmpty()) break;
					root = stack.pop();
					System.out.print(
							root.data + " ");
					root = root.right;
				}
			}
		}
	}

	// Reverse logic of Preorder iterative approach
	public void postOrderUsing2Stack(
			TreeNode root) {
		if (root != null) {
			Stack<TreeNode> s1 = new Stack<>();
			Stack<Integer> s2 = new Stack<>();
			s1.push(root);
			while (!s1.isEmpty()) {
				root = s1.pop();
				s2.push(root.data);
				if (root.left != null)
					s1.push(root.left);
				if (root.right != null)
					s1.push(root.right);
			}

			while (!s2.isEmpty())
				System.out.print(s2.pop() + " ");
		}
	}

	public void postOrderUsingStack(
			TreeNode root) {
		if (root != null) {
			TreeNode current = root, rightNode,
					topNode;
			Stack<TreeNode> stack = new Stack<>();
			while (current != null
					|| !stack.isEmpty()) {
				if (current != null) {
					stack.push(current);
					current = current.left;
				} else {
					rightNode = stack
							.peek().right;
					if (rightNode != null) {
						current = rightNode;
					} else {
						topNode = stack.pop();
						System.out.print(
								topNode.data
										+ " ");
						while (!stack.isEmpty()
								&& topNode == stack
										.peek().right) {
							topNode = stack.pop();
							System.out.print(
									topNode.data
											+ " ");
						}
					}
				}
			}
		}
	}

	/*
	 * BFS/Level Order Traversal: 1.Recursive Approach 2.Iterative Approach using
	 * Queue 3.Level by Level1: using single queue & level size iteration 4.Level by
	 * Level2 - using 2 queues 5.Bottom-up/Reverse level order traversal: Traverse
	 * level by level using queue and store the result in Stack 6.Bottom-up
	 * LevelByLevel Traversal: Iterative Approach; Single Iteration & levelSize
	 * iteration 7.Bottom-up LevelByLevel Traversal: Recursive Approach 8.Spiral
	 * Order Traversal:
	 */

	// 1.Recursive Approach:(LevelOrder - DFS Traversals)
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

	// 2.Iterative Approach using Queue:
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

	// 3. Level by Level1: using single queue & levelsize iteration
	public List<List<Integer>> levelByLevelOrder1(
			TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> result = new LinkedList<>();

		if (root == null) return result;

		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			while (levelSize-- > 0) {
				if (queue.peek().left != null)
					queue.offer(
							queue.peek().left);
				if (queue.peek().right != null)
					queue.offer(
							queue.peek().right);
				subList.add(queue.poll().data);
			}
			result.add(subList);
		}
		return result;
	}

	// Level by Level2 - using 2 queues
	public void levelByLevelOrder2(
			TreeNode root) {
		if (root != null) {
			Queue<TreeNode> q1 = new LinkedList<>();
			Queue<TreeNode> q2 = new LinkedList<>();
			q1.add(root);
			while (!q1.isEmpty()
					|| !q2.isEmpty()) {
				while (!q1.isEmpty()) {
					root = q1.remove();
					System.out.print(
							root.data + " ");
					if (root.left != null)
						q2.add(root.left);
					if (root.right != null)
						q2.add(root.right);
				}
				System.out.println();
				while (!q2.isEmpty()) {
					root = q2.poll();
					System.out.print(
							root.data + " ");
					if (root.left != null)
						q1.add(root.left);
					if (root.right != null)
						q1.add(root.right);
				}
				System.out.println();
			}
		}
	}

	// Bottom-up/Reverse level order traversal: Traverse level by level using queue
	// and store the result in Stack
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

	// Bottom-up LevelByLevel Traversal: Iterative Approach; Single Iteration &
	// levelSize iteration
	public List<List<Integer>> levelOrderBottom1(
			TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> result = new LinkedList<List<Integer>>();

		if (root == null) return result;

		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			while (levelSize-- > 0) {
				if (queue.peek().left != null)
					queue.offer(
							queue.peek().left);
				if (queue.peek().right != null)
					queue.offer(
							queue.peek().right);
				subList.add(queue.poll().data);
			}
			result.add(0, subList);// Add the list in the Zeroth Position
		}
		return result;
	}

	// Bottom-up LevelByLevel Traversal: Recursive Approach(LevelOrder - DFS
	// Traversals)
	public List<List<Integer>> levelOrderBottom2(
			TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		levelOrderBottom(root, result, 0);
		return result;
	}

	public void levelOrderBottom(TreeNode root,
			List<List<Integer>> result,
			int level) {
		if (root == null) return;
		if (result.size() <= level)
			result.add(0, new ArrayList<>()); // Create new List in the zeroth position

		levelOrderBottom(root.left, result,
				level + 1);
		levelOrderBottom(root.right, result,
				level + 1);

		result.get(result.size() - level - 1)
				.add(root.data); // Add the list into resultSize-level-1
	}

	/*
	 * Spiral Order Traversal: 1.Using 2 stack 2.Using Deque
	 */
	// 1.Using 2 stack
	public void levelOrderSpiralForm(
			TreeNode root) {
		if (root != null) {
			Stack<TreeNode> s1 = new Stack<>();
			Stack<TreeNode> s2 = new Stack<>();
			s1.push(root);
			while (!s1.isEmpty()
					|| !s2.isEmpty()) {
				while (!s1.isEmpty()) {
					root = s1.pop();
					System.out.print(
							root.data + " ");
					if (root.left != null)
						s2.push(root.left);
					if (root.right != null)
						s2.push(root.right);
				}
				while (!s2.isEmpty()) {
					root = s2.pop();
					System.out.print(
							root.data + " ");
					if (root.right != null)
						s1.push(root.right);
					if (root.left != null)
						s1.push(root.left);
				}
			}
		}
	}

	public void morrisInOrderTraversal(
			TreeNode root) {

	}

	/************************ BT View Problems ***********************/

	/*
	 * 2.Tree View Problems: - Vertical View - DFS/BFS - Top View - DFS/BFS - Bottom
	 * View - DFS - Left View - DFS - Right View - DFS - Diagonal View - DFS
	 */
	// Vertical View1: DFS Approach
	public void verticalViewTraversal1(
			TreeNode root) {
		if (root != null) {
			Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
			verticalOrder(root, map, 0);

			if (map != null) {
				for (Entry<Integer, ArrayList<Integer>> entry : map
						.entrySet())
					System.out.println(entry
							.getKey() + "-"
							+ entry.getValue());
			}
		}
	}

	private void verticalOrder(TreeNode root,
			Map<Integer, ArrayList<Integer>> map,
			int hd) {
		if (root == null) return;
		// hd - horizontal distance
		ArrayList<Integer> list = map.get(hd);
		if (list == null)
			list = new ArrayList<>();

		list.add(root.data);
		map.put(hd, list);

		verticalOrder(root.left, map, hd - 1);
		verticalOrder(root.right, map, hd + 1);
	}

	// Vertical View1: BFS Approach
	public void verticalViewTraversal2(
			TreeNode root) {
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
		Queue<QueuePack> queue = new LinkedList<>();
		QueuePack queuePack;
		ArrayList<Integer> list = new ArrayList<>();
		TreeNode curr;
		int hd;
		queue.add(new QueuePack(0, root));
		while (!queue.isEmpty()) {
			queuePack = queue.poll();
			hd = queuePack.hd;
			curr = queuePack.node;

			list = map.get(hd);
			if (list == null)
				list = new ArrayList<>();

			list.add(curr.data);
			map.put(hd, list);

			if (curr.left != null)
				queue.add(new QueuePack(hd - 1,
						curr.left));

			if (curr.right != null)
				queue.add(new QueuePack(hd + 1,
						curr.right));
		}

		for (Entry<Integer, ArrayList<Integer>> entry : map
				.entrySet())
			System.out.println(entry.getKey()
					+ "-" + entry.getValue());

	}

	/*
	 * Top View - DFS Approach - Check the issue in this solution void
	 * topView(BLNode root) { Map<Integer, Integer> map = new TreeMap<>();
	 * topView(root, map, 0);
	 * 
	 * for (java.util.Map.Entry<Integer, Integer> entry : map.entrySet()) {
	 * System.out.print(entry.getValue() + " "); } }
	 * 
	 * void topView(BLNode root, Map<Integer, Integer> map, int hd) { if (root ==
	 * null) return;
	 * 
	 * if (map.get(hd) == null) { map.put(hd, root.data); }
	 * 
	 * topView(root.left, map, hd - 1); topView(root.right, map, hd + 1); }
	 * 
	 * //C++ code: void verticalTraversal(node* head, map<int,int> &levelMap, int
	 * level) { if(head == NULL) return; if(levelMap.count(level) == 0)
	 * levelMap[level] = head->data; if(level == 0 || level < 0)
	 * verticalTraversal(head->left, levelMap, level - 1); if(level == 0 || level >
	 * 0) verticalTraversal(head->right, levelMap, level + 1); } void topView(node *
	 * root) { if (root == NULL) return;
	 * 
	 * map<int, int> levelMap; verticalTraversal(root, levelMap, 0);
	 * 
	 * for (map<int,int>::iterator it=levelMap.begin(); it!=levelMap.end(); ++it) {
	 * cout << it->second << ' '; } }
	 */

	// Top View - BFS Approach
	public void topViewTraversal(TreeNode root) {
		TreeMap<Integer, Integer> map = new TreeMap<>(); // Using Map Api
		// Set<Integer> set = new HashSet<>(); // Using Set Api
		Queue<QueuePack> queue = new LinkedList<>();
		QueuePack queuePack;
		TreeNode curr;
		int hd;
		queue.add(new QueuePack(0, root));
		while (!queue.isEmpty()) {
			queuePack = queue.poll();
			hd = queuePack.hd;
			curr = queuePack.node;

			/*
			 * if (!set.contains(hd)) { set.add(hd); System.out.print(curr.data + " "); }
			 */
			if (!map.containsKey(hd))
				map.put(hd, curr.data);

			if (curr.left != null)
				queue.add(new QueuePack(hd - 1,
						curr.left));

			if (curr.right != null)
				queue.add(new QueuePack(hd + 1,
						curr.right));
		}

		for (Integer key : map.keySet())
			System.out.print(map.get(key) + "-");
	}

	// Bottom View - BFS Approach
	public void bottomViewTraversal(
			TreeNode root) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		Queue<QueuePack> queue = new LinkedList<>();
		QueuePack queuePack;
		TreeNode curr;
		int hd;
		queue.add(new QueuePack(0, root));
		while (!queue.isEmpty()) {
			queuePack = queue.poll();
			hd = queuePack.hd;
			curr = queuePack.node;

			map.put(hd, curr.data);

			if (curr.left != null)
				queue.add(new QueuePack(hd - 1,
						curr.left));

			if (curr.right != null)
				queue.add(new QueuePack(hd + 1,
						curr.right));
		}

		for (Integer key : map.keySet())
			System.out.print(map.get(key) + "-");

	}

	// Diagonal View - DFS Approach
	public void diagonalTraversal(TreeNode root) {
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();

		diagonalTraversal(root, 0, map);

		for (Entry<Integer, ArrayList<Integer>> entry : map
				.entrySet())
			System.out.println(entry.getKey()
					+ " - " + entry.getValue());

	}

	public void diagonalTraversal(TreeNode root,
			int dist,
			Map<Integer, ArrayList<Integer>> map) {
		if (root == null) return;

		ArrayList<Integer> list = map.get(dist);
		if (list == null)
			list = new ArrayList<>();
		list.add(root.data);
		map.put(dist, list);

		diagonalTraversal(root.left, dist + 1,
				map);
		diagonalTraversal(root.right, dist, map);
	}

	/*
	 * Left View of Tree - BFS Approach: The left view contains all nodes that are
	 * first nodes in their levels. A simple solution is to do level order traversal
	 * and print the first node in every level.
	 */
	public void leftViewOfTree1(TreeNode root) {

	}

	// Left View - DFS Approach
	static int maxLeftLevel = 0;

	public void leftViewOfTree2(TreeNode root) {
		leftViewOfTree2(root, 1);
	}

	public void leftViewOfTree2(TreeNode root,
			int level) {
		if (root == null) return;

		if (maxLeftLevel < level) {
			System.out.print(root.data + " ");
			maxLeftLevel = level;
		}

		leftViewOfTree2(root.left, level + 1);
		leftViewOfTree2(root.right, level + 1);
	}

	/*
	 * Right View of Tree - BFS Approach: The Right view contains all nodes that are
	 * last nodes in their levels. A simple solution is to do level order traversal
	 * and print the last node in every level.
	 */
	public void rightViewOfTree1(TreeNode root) {

	}

	// Right View - DFS Approach
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

	/*
	 * Right Side View - DFS Approach: The core idea of this algorithm: 1.Each
	 * depth/level of the tree only select one node. 2. View depth/level is current
	 * size of result list.
	 */
	public List<Integer> rightSideView(
			TreeNode root) {
		List<Integer> result = new ArrayList<>();
		rightSideView(root, result, 0);
		return result;
	}

	public void rightSideView(TreeNode root,
			List<Integer> result, int level) {
		if (root == null) return;
		if (level == result.size()) // Add one element per level
			result.add(root.data);

		rightSideView(root.right, result,
				level + 1);
		rightSideView(root.left, result,
				level + 1);
	}

	/************************ BT Construction Problems ***********************/
	/*
	 * If you are given two traversal sequences, can you construct the binary tree?
	 * It depends on what traversals are given. If one of the traversal methods is
	 * Inorder then the tree can be constructed, otherwise not. Therefore, following
	 * combination can uniquely identify a tree. - Inorder and Preorder - Inorder
	 * and Postorder - Inorder and Level-order.
	 * 
	 * And following do not. - Postorder and Preorder - Preorder and Level-order -
	 * Postorder and Level-order.
	 * 
	 * So, even if three of them (Pre, Post and Level) are given, the tree can not
	 * be constructed.
	 */
	public TreeNode constructTreeFromInAndPreOrder(
			char[] inOrder, char[] preOrder) {
		OrderIndex preOrderIndex = new OrderIndex();
		preOrderIndex.index = 0;
		return buildTreeFromInAndPreOrder(inOrder,
				preOrder, 0, inOrder.length - 1,
				preOrderIndex);
	}

	private TreeNode buildTreeFromInAndPreOrder(
			char[] inOrder, char[] preOrder,
			int inOrderStart, int inOrderEnd,
			OrderIndex preOrderIndex) {
		if (inOrderStart > inOrderEnd)
			return null;

		TreeNode node = new TreeNode(
				preOrder[preOrderIndex.index++]);
		if (inOrderStart == inOrderEnd)
			return node;

		int rootNodeIndex = search(inOrder,
				inOrderStart, inOrderEnd,
				node.ch);
		node.left = buildTreeFromInAndPreOrder(
				inOrder, preOrder, inOrderStart,
				rootNodeIndex - 1, preOrderIndex);
		node.right = buildTreeFromInAndPreOrder(
				inOrder, preOrder,
				rootNodeIndex + 1, inOrderEnd,
				preOrderIndex);
		return node;
	}

	public TreeNode constructTreeFromInAndPostOrder(
			char[] inOrder, char[] postOrder) {
		OrderIndex postOrderIndex = new OrderIndex();
		postOrderIndex.index = postOrder.length
				- 1;
		return buildTreeFromInAndPostOrder(
				inOrder, postOrder, 0,
				inOrder.length - 1,
				postOrderIndex);
	}

	public TreeNode buildTreeFromInAndPostOrder(
			char[] inOrder, char[] postOrder,
			int inOrderStart, int inOrderEnd,
			OrderIndex postOrderIndex) {
		if (inOrderStart > inOrderEnd)
			return null;

		TreeNode node = new TreeNode(
				postOrder[postOrderIndex.index--]);
		if (inOrderStart == inOrderEnd)
			return node;

		int rootNodeIndex = search(inOrder,
				inOrderStart, inOrderEnd,
				node.ch);
		node.right = buildTreeFromInAndPostOrder(
				inOrder, postOrder,
				rootNodeIndex + 1, inOrderEnd,
				postOrderIndex);
		node.left = buildTreeFromInAndPostOrder(
				inOrder, postOrder, inOrderStart,
				rootNodeIndex - 1,
				postOrderIndex);
		return node;
	}

	private int search(char[] charArray,
			int start, int end, char x) {
		for (int i = start; i <= end; i++) {
			if (charArray[i] == x) return i;
		}
		return -1;
	}

	public void constructTreeFromInAndLevelOrder(
			TreeNode root) {

	}

	public void postOrderFromInOrderAndPreOrder(
			TreeNode root) {

	}

	/*
	 * Overview: Serialization is the process of converting a data structure or
	 * object into a sequence of bits so that it can be stored in a file or memory
	 * buffer, or transmitted across a network connection link to be reconstructed
	 * later in the same or another computer environment. Design an algorithm to
	 * serialize and deserialize a binary tree. There is no restriction on how your
	 * serialization/deserialization algorithm should work. You just need to ensure
	 * that a binary tree can be serialized to a string and this string can be
	 * deserialized to the original tree structure.
	 */
	// Approach1: Convert the tree to list; use PreOrder
	public ArrayList<Integer> serialize1(
			TreeNode root) {
		ArrayList<Integer> result = new ArrayList<>();
		serialize1(result, root);
		result.stream().forEach(
				k -> System.out.print(k + " "));
		return result;
	}

	private void serialize1(
			ArrayList<Integer> result,
			TreeNode root) {
		if (root == null) {
			result.add(-1);
			return;
		}
		result.add(root.data);
		serialize1(result, root.left);
		serialize1(result, root.right);
	}

	// Approach1: Convert the list to Tree;
	static int index = 0;

	public TreeNode deserialize1(
			ArrayList<Integer> list) {
		if (list.size() == index
				|| list.get(index) == -1)
			return null;
		TreeNode node = new TreeNode(
				list.get(index));
		index++;
		node.left = deserialize1(list);
		index++;
		node.right = deserialize1(list);
		return node;
	}

	// Same approach, but used class variable to increment the serialized data index
	public TreeNode deSerialize1(
			ArrayList<Integer> list,
			OrderIndex in) {
		if (list.size() == in.index
				|| list.get(in.index) == -1)
			return null;
		TreeNode root = new TreeNode(
				list.get(in.index));
		in.index++;
		root.left = deSerialize1(list, in);
		in.index++;
		root.right = deSerialize1(list, in);
		return root;
	}

	// Approach2: Convert the tree to string; use PreOrder
	public String serialize2(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serialize2(root, sb);
		System.out.println("Serialized data: "
				+ sb.toString());
		return sb.toString();
	}

	public void serialize2(TreeNode root,
			StringBuilder sb) {
		if (root == null) {
			sb.append("#,");
			return;
		}
		sb.append(root.data + ",");
		serialize2(root.left, sb);
		serialize2(root.right, sb);
	}

	// Approach2: Convert the string to tree
	public TreeNode deSerialize2(String data) {
		OrderIndex in = new OrderIndex();
		String str[] = data.split(",");
		return deSerialize2(str, in);
	}

	public TreeNode deSerialize2(String[] str,
			OrderIndex in) {
		if (str.length == in.index - 1
				|| str[in.index].equals("#"))
			return null;
		TreeNode root = new TreeNode(
				Integer.valueOf(str[in.index]));
		in.index++;
		root.left = deSerialize2(str, in);
		in.index++;
		root.right = deSerialize2(str, in);
		return root;
	}

	// Similar to approach2: Using Queue
	public TreeNode deserialize22(String data) {
		Queue<String> q = new LinkedList<>(
				Arrays.asList(data.split(",")));
		return deserialize(q);
	}

	public TreeNode deserialize(Queue<String> q) {
		String s = q.poll();
		if (s.equals("#")) return null;
		TreeNode root = new TreeNode(
				Integer.parseInt(s));
		root.left = deserialize(q);
		root.right = deserialize(q);
		return root;
	}

	// Approach3: Convert the tree to string; using lever Order Traversal
	public String serialize3(TreeNode root) {
		return "";
	}

	// Approach2: Convert the string to tree
	public TreeNode deSerialize3(String data) {
		return null;
	}

	/************************ BT Conversion Problems ***********************/

	TreeNode head = null, prev = null;

	// Convert a given Binary Tree to Doubly Linked List:(using Inorder Traversal)
	/*
	 * Approach1: The idea is to do inorder traversal of the binary tree. While
	 * doing inorder traversal, keep track of the previously visited node in a
	 * variable say prev. For every visited node, make it next of prev and previous
	 * of this node as prev. Note: Here left -> prev, right -> next
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

	/*
	 * Approach2: We add nodes at the beginning of current linked list and update
	 * head of the list using pointer to head pointer. Since we insert at the
	 * beginning, we need to process leaves in reverse order. For reverse order, we
	 * first traverse the right subtree before the left subtree. i.e. do a reverse
	 * inorder traversal.
	 */
	public TreeNode convertBinaryTreeToDLL2(
			TreeNode root) {
		head = null;
		return binaryTreeToDLLUtil2(root);
	}

	public TreeNode binaryTreeToDLLUtil2(
			TreeNode root) {
		if (root == null) return null;

		binaryTreeToDLLUtil2(root.right);

		root.right = head;

		if (head != null) head.left = root;

		head = root;
		binaryTreeToDLLUtil2(root.left);
		return head;
	}

	// Convert a given Binary Tree to Doubly Linked List(using Level order
	// Traversal) -> It's totally wrong
	public void convertBinaryTreeToDLL3(
			TreeNode root) {
		if (root == null) return;
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode curr, prev = null;
		queue.add(root);
		while (!queue.isEmpty()) {
			curr = queue.poll();
			if (curr.left != null)
				queue.add(curr.left);
			if (curr.right != null)
				queue.add(curr.right);
			curr.left = prev;
			curr.right = queue.peek();
			prev = curr;
		}
	}

	public void printDLL(TreeNode root) {
		while (root != null) {
			System.out.print(root.data + " ");
			root = root.right;
		}
		System.out.println();
	}

	public void convertBinaryTreeToThreadedBinaryTree(
			TreeNode root) {

	}

	public void convertBinaryTreeToMirrorTree(
			TreeNode root) {

	}

	// SumTree: Sum of left subtree + right subtree
	public int convertBTToSumTree(TreeNode root) {
		int result = 0;
		if (root != null) {
			int left = convertBTToSumTree(
					root.left);
			int right = convertBTToSumTree(
					root.right);
			result = left + right + root.data;
			root.data = left + right;
			return result;
		}
		return result;
	}

	// Binary Tree to Binary Search Tree Conversion
	public void convertBTtoBST(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		// Get elements in inorder
		inorderTraversal(root, list);
		// Sort the elements
		Collections.sort(list);
		// Convert the BT to BST
		int[] index = new int[1];
		convert(root, list, index);
		// Print the result:
		inOrder(root);
	}

	public void inorderTraversal(TreeNode root,
			List<Integer> list) {
		if (root == null) return;

		inorderTraversal(root.left, list);
		list.add(root.data);
		inorderTraversal(root.right, list);
	}

	public void convert(TreeNode root,
			List<Integer> list, int[] index) {
		if (root == null) return;
		convert(root.left, list, index);
		root.data = list.get(index[0]);
		index[0]++;
		convert(root.right, list, index);
	}

	public TreeNode deleteZeroSumSubTrees(
			TreeNode root) {
		return null;
	}

	// Flatten Binary Tree to Linked List
	// Approach11: Inorder Traversal Modification
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

	/************************ BT Checking Problems ***********************/
	public boolean checkSameBinaryTree(
			TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		return root1.data == root2.data
				&& checkSameBinaryTree(root1.left,
						root2.left)
				&& checkSameBinaryTree(
						root1.right, root2.right);
	}

	public boolean isSameTree(TreeNode p,
			TreeNode q) {
		if (p == null && q == null) return true;
		if (p == null || q == null) return false;
		return (p.data == q.data
				&& isSameTree(p.left, q.left)
				&& isSameTree(p.right, q.right));
	}

	// Symmetric Tree (Mirror Image of itself)
	// Recursive Approach
	public boolean isMirror(TreeNode node1,
			TreeNode node2) {
		if (node1 == null && node2 == null)
			return true;
		if (node1 == null || node2 == null)
			return false;
		return node1.data == node2.data
				&& isMirror(node1.left,
						node2.right)
				&& isMirror(node1.right,
						node2.left);
	}

	// Iterative Approach
	public boolean isMirror2(TreeNode node1,
			TreeNode node2) {
		return false;
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		return isMirror(root, root);
	}

	// Combination of same tree & mirror/symmetric tree logic
	public boolean isomorphism(TreeNode node1,
			TreeNode node2) {
		if (node1 == null && node2 == null)
			return true;
		if (node1 == null || node2 == null)
			return false;
		// There are two possible cases for node1 and node2 to be isomorphic
		// Case 1: The subtrees rooted at these nodes have NOT been "Flipped". (Similar
		// to Normal Tree comparison)
		// Case 2: The subtrees rooted at these nodes have been "Flipped" (Similar to
		// Mirror Tree comparison)
		// Both of these subtrees have to be isomorphic, hence the &&
		return node1.data == node2.data
				&& ((isomorphism(node1.left,
						node2.left)
						&& isomorphism(
								node1.right,
								node2.right))
						|| (isomorphism(
								node1.left,
								node2.right)
								&& isomorphism(
										node1.right,
										node2.left)));
	}

	// Invert/Flip a binary tree.
	// Approach1:
	public TreeNode invertTree(TreeNode root) {
		if (root == null || (root.left == null
				&& root.right == null))
			return root;
		invertTree(root.left);
		invertTree(root.right);
		TreeNode temp = root.right;
		root.right = root.left;
		root.left = temp;
		return root;
	}

	// Approach2:
	public TreeNode invertTree2(TreeNode root) {
		if (root == null) return null;
		TreeNode left = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(left);
		return root;
	}

	// Check if a binary tree is subtree of another binary tree
	// Approach1: Time Complexity: O(n^2)
	public boolean isSubtree1(TreeNode s,
			TreeNode t) {
		if (t == null) return true;
		if (s == null) return false;

		if (isSameTree(s, t)) return true;

		return isSubtree1(s.left, t)
				|| isSubtree1(s.right, t);
	}

	// Approach2: Time Complexity: O(n) -> Using inorder & preorder of both tree
	public boolean isSubtree2(TreeNode s,
			TreeNode t) {
		return false;
	}

	// Approach3: Time Complexity: O(m+n): Use Preorder traversal and build the
	// string for the both, finally check the
	// substring
	public boolean isSubtree3(TreeNode T1,
			TreeNode T2) {
		if (T1 == null) return true;
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		buildTree(T1, sb1);
		buildTree(T2, sb2);
		System.out
				.println("S1: " + sb1.toString());
		System.out
				.println("S2: " + sb2.toString());
		return (sb1
				.indexOf(sb2.toString()) != -1);
	}

	public void buildTree(TreeNode root,
			StringBuilder sb) {
		if (root == null) {
			sb.append("X");
			return;
		}

		sb.append(root.data);
		buildTree(root.left, sb);
		buildTree(root.right, sb);
	}

	// Approach 1: Time Complexity: O(n^2)
	public boolean isBinaryTreeBalanced1(
			TreeNode root) {
		if (root == null) return true;

		return Math.abs(heightOfTree1(root.left)
				- heightOfTree1(root.right)) <= 1
				&& isBinaryTreeBalanced1(
						root.left)
				&& isBinaryTreeBalanced1(
						root.right);
	}

	// Approach 2: This approach avoids two recursive calls & check the tree is
	// balanced or not while calculating the
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

	// Verify Preorder Serialization of a Binary Tree:
	/*
	 * Solution: Since this is a preorder serialization, degrees are calculated in a
	 * top- down fashion, and, tree is a structure that each node has only one
	 * indegree and at most two outdegree.Positive degree means there are more
	 * indegree than outdegree, which violates the definition.
	 */

	public boolean isValidSerialization(
			String preorder) {
		String[] strs = preorder.split(",");
		int degree = -1; // root has no indegree, for compensate init with -1
		for (String str : strs) {
			degree++; // all nodes have 1 indegree (root compensated)
			if (degree > 0) // total degree should never exceeds 0
				return false;

			if (!str.equals("#")) // only non-leaf node has 2 outdegree
				degree -= 2;
		}
		return degree == 0;
	}

	public void checkChildrenSumProperty() {

	}

	public boolean checkBinaryTreeComplete() {
		return false;
	}

	/************************ BT Print/Path Problems ***********************/
	/*
	 * BT Problems are same type of problems/approaches: 
	 * - Print/Path Type 
	 * - Summation Type 
	 * - LCA Type
	 */

	/* All the below categories solved by using only 2 approaches/algorithms
	 * Categories:
	 * Print all the paths from root to leaf :
	 * 	1. Root to Leaf: printPathFromRootToLeaf()
	 * Find the path of given node
	 * 	1. Root to leaf: findPathFromRootToLeaf()
	 * 	2. Root to any node: findPathFromRootToAnyNode() //Print the ancestors of given node
	 * Find the path of given sum:
	 * 	1. Root to leaf: sumPathFromRootToLeaf()
	 * 	2. Root to any node: sumPathFromRootToAnyNode()
	 * 	3. Any node to any node: sumPathFromAnyNodeToAnyNode()
	 * Maximum sum path:
	 * 	1. Root to Leaf: maxSumPathFromRootToLeaf()
	 * 	2. Root to any node:
	 * 	3. Any node to any node:
	 * 	4. From leaf to leaf: maxSumPathFromLeafToLeaf1()
	 * Find the max distance b/w Nodes - Diameter
	 * 	1. Any node to Any node: diameterOfTree()
	 * Lowest Common Ancestors
	 */
	// Print all the path from root to leaf in the tree

	// Binary Tree Paths - Integer
	public void printPathFromRootToLeaf1(
			TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		printPathFromRootToLeaf1(root, list);
	}

	private void printPathFromRootToLeaf1(
			TreeNode root,
			ArrayList<Integer> path) {
		if (root == null) return;

		path.add(root.data);
		if (root.left == null
				&& root.right == null) {
			path.stream().forEach(k -> System.out
					.print(k + "-"));
			System.out.println();
		}

		printPathFromRootToLeaf1(root.left, path);
		printPathFromRootToLeaf1(root.right,
				path);

		// Remove the visited path from list, if node is not present in the path
		if (!path.isEmpty())
			path.remove(path.size() - 1);
	}

	// Binary Tree Paths - String
	public List<String> printPathFromRootToLeaf2(
			TreeNode root) {
		List<String> result = new ArrayList<>();
		if (root == null) return result;
		printPathFromRootToLeaf2(root, result,
				"");
		result.stream().forEach(
				k -> System.out.print(k + ", "));
		return result;
	}

	public void printPathFromRootToLeaf2(
			TreeNode root, List<String> result,
			String path) {
		if (root == null) return;
		path += root.data;
		if (root.left == null
				&& root.right == null)
			result.add(path);
		printPathFromRootToLeaf2(root.left,
				result, path + "->");
		printPathFromRootToLeaf2(root.right,
				result, path + "->");
	}

	// Find the path from root to any node
	public void findPathFromRootToAnyNode1(
			TreeNode root, int n) {
		ArrayList<Integer> path = new ArrayList<>();

		if (findPathFromRootToAnyNode1(root, n,
				path))
			path.stream().forEach(k -> System.out
					.print(k + "-"));
		else System.out.println(
				"Element is not present in the tree");
	}

	// Approach1:
	private boolean findPathFromRootToAnyNode1(
			TreeNode root, int n,
			ArrayList<Integer> path) {
		if (root == null) return false;

		// To find the path, first add the element in the list and check the data
		path.add(root.data);
		if (root.data == n) return true;

		boolean flag = findPathFromRootToAnyNode1(
				root.left, n, path);
		// This flag should check separately, otherwise when root.right returns,
		// required element will be removed
		if (!flag)
			flag = findPathFromRootToAnyNode1(
					root.right, n, path);

		// Remove the visited path from list, if node is not present in the path;
		if (!flag) path.remove(path.size() - 1);
		return false;
	}

	// Approach-2:
	public boolean findPathFromRootToAnyNode2(
			TreeNode root, int element) {
		if (root == null) return false;

		if (root.data == element) return true;

		if (findPathFromRootToAnyNode2(root.left,
				element)
				|| findPathFromRootToAnyNode2(
						root.right, element)) {
			System.out.print(root.data + " ");
			return true;
		}
		return false;
	}

	/*
	 * Binary Tree Longest Consecutive Sequence: Given a binary tree, find the
	 * length of the longest consecutive sequence path. The path refers to any
	 * sequence of nodes from some starting node to any node in the tree along the
	 * parent-child connections. The longest consecutive path need to be from parent
	 * to child (cannot be the reverse).
	 */
	public int longestConsecutive(TreeNode root) {
		return longestConsecutive(root, 0, -1);
	}

	public int longestConsecutive(TreeNode curr,
			int currMax, int prevVal) {
		if (curr == null) return currMax;

		currMax = (prevVal + 1 == curr.data)
				? currMax + 1
				: 1;

		int left = longestConsecutive(curr.left,
				currMax, curr.data);
		int right = longestConsecutive(curr.right,
				currMax, curr.data);
		return Math.max(currMax,
				Math.max(left, right));
	}

	/*
	 * Binary Tree Longest Consecutive Sequence II: Given a binary tree, you need to
	 * find the length of Longest Consecutive Path in Binary Tree. Especially, this
	 * path can be either increasing or decreasing. For example, [1,2,3,4] and
	 * [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On
	 * the other hand, the path can be in the child-Parent-child order, where not
	 * necessarily be parent-child order.
	 */
	int maxval = 0;

	public int longestConsecutive2(
			TreeNode root) {
		longestPath(root);
		return maxval;
	}

	public int[] longestPath(TreeNode root) {
		if (root == null)
			return new int[] { 0, 0 };
		int inr = 1, dcr = 1;
		if (root.left != null) {
			int[] l = longestPath(root.left);
			if (root.data == root.left.data + 1)
				dcr = l[1] + 1;
			else if (root.data == root.left.data
					- 1)
				inr = l[0] + 1;
		}
		if (root.right != null) {
			int[] r = longestPath(root.right);
			if (root.data == root.right.data + 1)
				dcr = Math.max(dcr, r[1] + 1);
			else if (root.data == root.right.data
					- 1)
				inr = Math.max(inr, r[0] + 1);
		}
		maxval = Math.max(maxval, dcr + inr - 1);
		return new int[] { inr, dcr };
	}

	/************************ BT Summation Problems ***********************/
	// Root to leaf path sum:
	// Path Sum1: Check the sum presents in the path:
	public boolean hasPathSumFromRootToLeaf(
			TreeNode root, int sum) {
		if (root == null) return false;
		if (sum == root.data && root.left == null
				&& root.right == null)
			return true;
		return hasPathSumFromRootToLeaf(root.left,
				sum - root.data)
				|| hasPathSumFromRootToLeaf(
						root.right,
						sum - root.data);
	}

	// Check the root to leaf path for the given Sum and print the result:
	public void sumPathFromRootToLeaf(
			TreeNode root, int sum) {
		ArrayList<Integer> path = new ArrayList<>();
		if (sumPathFromRootToLeaf1(root, sum,
				path)) {
			// if (sumPathFromRootToLeaf2(root, sum, path)) {
			System.out.print("Sum Path for " + sum
					+ " is:");
			path.stream().forEach(k -> System.out
					.print(k + " "));
		} else {
			System.out.println(
					"Sum is not present from root to leaf!");
		}
	}

	private boolean sumPathFromRootToLeaf1(
			TreeNode root, int sum,
			ArrayList<Integer> path) {
		if (root == null) return false;

		if (sum == root.data && root.left == null
				&& root.right == null) {
			path.add(root.data);
			return true;
		}
		if (sumPathFromRootToLeaf1(root.left,
				sum - root.data, path)) {
			path.add(root.data);
			return true;
		}
		if (sumPathFromRootToLeaf1(root.right,
				sum - root.data, path)) {
			path.add(root.data);
			return true;
		}
		return false;
	}

	public boolean sumPathFromRootToLeaf2(
			TreeNode root, int currSum, int sum,
			ArrayList<Integer> path) {
		if (root == null) return false;

		currSum += root.data;
		path.add(root.data);
		if (root.left == null
				&& root.right == null) {
			if (currSum == sum) return true;
			else return false;
		}
		boolean flag = sumPathFromRootToLeaf2(
				root.left, currSum, sum, path);
		if (!flag) flag = sumPathFromRootToLeaf2(
				root.right, currSum, sum, path);
		if (!flag) path.remove(path.size() - 1);
		return false;
	}

	// Path Sum II: find all root-to-leaf paths where each path's sum equals the
	// given sum
	public List<List<Integer>> sumPathFromRootToLeafAll(
			TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> eachList = new ArrayList<>();
		sumPathFromRootToLeafAll(root, result,
				eachList, sum);
		return result;
	}

	public void sumPathFromRootToLeafAll(
			TreeNode root,
			List<List<Integer>> result,
			List<Integer> eachList, int sum) {
		if (root == null) return;

		eachList.add(root.data);

		if (root.data == sum && root.left == null
				&& root.right == null)
			result.add(new ArrayList<>(eachList));

		sumPathFromRootToLeafAll(root.left,
				result, eachList,
				sum - root.data);
		sumPathFromRootToLeafAll(root.right,
				result, eachList,
				sum - root.data);
		eachList.remove(eachList.size() - 1);
	}

	// Root to any node:
	public void sumPathFromRootToAnyNode1(
			TreeNode root, int sum) {
		// Root to leaf path sum equal to a given number
		ArrayList<Integer> path = new ArrayList<>();
		if (sumPathFromRootToAnyNode1(root, sum,
				0, path)) {
			System.out.print("Sum Path for " + sum
					+ " is:");
			path.stream().forEach(k -> System.out
					.print(k + " "));
		} else {
			System.out.println(
					"Sum is not present in the tree!");
		}
	}

	private boolean sumPathFromRootToAnyNode1(
			TreeNode root, int sum,
			int currentSum,
			ArrayList<Integer> path) {
		if (root == null) return false;

		currentSum += root.data;
		path.add(root.data);
		if (sum == currentSum) return true;

		boolean flag = sumPathFromRootToAnyNode1(
				root.left, sum, currentSum, path);
		if (!flag)
			flag = sumPathFromRootToAnyNode1(
					root.right, sum, currentSum,
					path);
		if (!flag) path.remove(path.size() - 1);

		return flag;
	}

	public void sumPathFromRootToAnyNode2(
			TreeNode root, int sum) {
		ArrayList<Integer> result = new ArrayList<>();
		if (sumPathFromRootToAnyNode2(root, sum,
				result))
			result.stream()
					.forEach(k -> System.out
							.print(k + " "));
		else System.out.println(
				"Sum is not present in the tree");
	}

	public boolean sumPathFromRootToAnyNode2(
			TreeNode root, int sum,
			ArrayList<Integer> path) {
		if (root == null) return false;

		if (root.data == sum) {
			path.add(root.data);
			return true;
		}

		if (sumPathFromRootToAnyNode2(root.left,
				sum - root.data, path)) {
			path.add(root.data);
			return true;
		}

		if (sumPathFromRootToAnyNode2(root.right,
				sum - root.data, path)) {
			path.add(root.data);
			return true;
		}
		return false;
	}

	// Any node to any node
	// Path SumIII:
	public int sumPathFromAnyNodeToAnyNode(
			TreeNode root, int sum) {
		HashMap<Integer, Integer> preSum = new HashMap<>();
		preSum.put(0, 1);
		return sumPathFromAnyNodeToAnyNode(root,
				0, sum, preSum);
	}

	public int sumPathFromAnyNodeToAnyNode(
			TreeNode root, int currSum,
			int target,
			HashMap<Integer, Integer> preSum) {
		if (root == null) return 0;

		currSum += root.data;
		int res = preSum.getOrDefault(
				currSum - target, 0);
		preSum.put(currSum,
				preSum.getOrDefault(currSum, 0)
						+ 1);

		res += sumPathFromAnyNodeToAnyNode(
				root.left, currSum, target,
				preSum)
				+ sumPathFromAnyNodeToAnyNode(
						root.right, currSum,
						target, preSum);
		preSum.put(currSum,
				preSum.get(currSum) - 1);
		return res;
	}

	static int maxSum = 0;

	// Find the maximum sum leaf to root path in a Binary Tree
	public int maxSumPathFromRootToLeaf(
			TreeNode root) {
		// Root to leaf path sum equal to a given number
		ArrayList<Integer> path = new ArrayList<>();
		// Find the maximum
		// int max = maxSumPathFromRootToLeaf1(root, 0);
		int max = maxSumPathFromRootToLeaf2(root,
				0, 0);
		System.out.print(
				"Max sum Path is " + max + "");
		System.out.println();
		// Find the path using max sum
		sumPathFromRootToLeaf(root, max);

		path.stream().forEach(
				k -> System.out.print(k + " "));
		return max;
	}

	// Find max value using static variable
	public void maxSumPathFromRootToLeaf1(
			TreeNode root, int currentSum) {
		if (root == null) return;

		currentSum += root.data;
		if (root.left == null
				&& root.right == null) {
			if (currentSum > maxSum)
				maxSum = currentSum;
			return;
		}

		maxSumPathFromRootToLeaf1(root.left,
				currentSum);
		maxSumPathFromRootToLeaf1(root.right,
				currentSum);
	}

	public int maxSumPathFromRootToLeaf2(
			TreeNode root, int currSum,
			int maxSum) {
		if (root == null) return 0;

		currSum += root.data;
		if (root.left == null
				&& root.right == null)
			return Math.max(currSum, maxSum);

		return Math.max(maxSumPathFromRootToLeaf2(
				root.left, currSum, maxSum),
				maxSumPathFromRootToLeaf2(
						root.right, currSum,
						maxSum));
	}

	public int maxSumPathFromRootToLeaf3(
			TreeNode root) {
		if (root == null) return 0;
		return Math.max(0,
				root.data + Math.max(
						maxSumPathFromRootToLeaf3(
								root.left),
						maxSumPathFromRootToLeaf3(
								root.right)));
	}

	// Find the maximum path sum between two leaves of a binary tree (Similar to
	// Diameter of the tree)

	/*
	 * Binary Tree Maximum Path Sum: Given a non-empty binary tree, find the maximum
	 * path sum. For this problem, a path is defined as any sequence of nodes from
	 * some starting node to any node in the tree along the parent-child
	 * connections. The path must contain at least one node and does not need to go
	 * through the root.
	 */
	/*
	 * Approach1: Brute Force Approach -> Time Complexity: O(n^2) 1) Find maximum
	 * sum from leaf to root in left subtree of X (we can use this post for this and
	 * next steps) 2) Find maximum sum from leaf to root in right subtree of X. 3)
	 * Add the above two calculated values and X->data and compare the sum with the
	 * maximum value obtained so far and update the maximum value. 4) Return the
	 * maximum value. The time complexity of above solution is O(n2)
	 */
	public int maxSumPathFromLeafToLeaf1(
			TreeNode root) {
		if (root == null)
			return Integer.MIN_VALUE; // Here return MIN_VALUE to handle -ve val

		int left = maxSumPathFromRootToLeaf3(
				root.left);
		int right = maxSumPathFromRootToLeaf3(
				root.right);
		return Math.max(left + right + root.data,
				Math.max(
						maxSumPathFromLeafToLeaf1(
								root.left),
						maxSumPathFromLeafToLeaf1(
								root.right)));
	}

	int maxValue;

	/*
	 * Approach2:Time Complexity: O(n) We can find the maximum sum using single
	 * traversal of binary tree. The idea is to maintain two values in recursive
	 * calls 1) Maximum root to leaf path sum for the subtree rooted under current
	 * node. 2) The maximum path sum between leaves (desired output).
	 */
	public int maxSumPathFromLeafToLeaf2(
			TreeNode root) {
		maxValue = Integer.MIN_VALUE;
		maxSumPathFromLeafToLeafUtil(root);
		return maxValue;
	}

	private int maxSumPathFromLeafToLeafUtil(
			TreeNode node) {
		if (node == null) return 0;
		int left = maxSumPathFromLeafToLeafUtil(
				node.left);
		int right = maxSumPathFromLeafToLeafUtil(
				node.right);
		maxValue = Math.max(maxValue,
				left + right + node.data);
		// Compare with zero to eliminate the -ve values
		return Math.max(0, node.data
				+ Math.max(left, right));
	}

	// Exactly similar to approach-2, but using result object
	public int maxSumPathFromLeafToLeaf3(
			TreeNode root) {
		ObjectState result = new ObjectState();
		maxSumPathFromLeafToLeaf3(root, result);
		return result.updatedValue;
	}

	public int maxSumPathFromLeafToLeaf3(
			TreeNode root, ObjectState result) {
		if (root == null) return 0;

		// 1.Move node to leaf in both side and find the values
		int leftMaxSumSubTree = maxSumPathFromLeafToLeaf3(
				root.left, result);
		int rightMaxSumSubTree = maxSumPathFromLeafToLeaf3(
				root.right, result);

		// 2.Update max leaf to value in result object
		result.updatedValue = Math.max(
				result.updatedValue,
				(leftMaxSumSubTree
						+ rightMaxSumSubTree
						+ root.data));

		// 3.Return max path sum from node to leaf
		return Math.max(leftMaxSumSubTree,
				rightMaxSumSubTree) + root.data;
	}

	// Diameter of a Binary Tree
	// Approach1: Time Complexity: O(n^2)
	public int diameterOfTree1(TreeNode root) {
		if (root == null) return 0;
		// Get the height of left and right sub trees
		int leftHeight = heightOfTree1(root.left);
		int rightHeight = heightOfTree1(
				root.right);

		/*
		 * Return max of following three 1) Height of left subtree + Height of right
		 * subtree + 1; 2) Diameter of left subtree; 3) Diameter of right subtree
		 */
		return Integer.max(
				leftHeight + rightHeight + 1,
				Integer.max(
						diameterOfTree1(
								root.left),
						diameterOfTree1(
								root.right)));
	}

	int maxDiameter = 0;

	// Efficient Approach: Modification of Height calculation; Time Complexity-O(n)
	public int diameterOfTree2(TreeNode root) {
		if (root == null) return 0;
		height2(root);
		return maxDiameter;
	}

	// Modification of Height
	public int height2(TreeNode root) {
		if (root == null) return 0;
		int left = height2(root.left);
		int right = height2(root.right);

		maxDiameter = Math.max(maxDiameter,
				left + right + 1);

		return Math.max(left, right) + 1;
	}

	// Similar to approach2, but using Height Function
	// Efficient Approach: Modification of Height calculation; Time Complexity-O(n)
	public int diameterOfTree3(TreeNode root) {
		Height height = new Height();
		return diameterOfTree2(root, height);
	}

	public int diameterOfTree2(TreeNode root,
			Height height) {
		Height leftHeight = new Height(),
				rightHeight = new Height();

		if (root == null) {
			height.h = 0;
			return 0;
		}

		// Get the diameter of the tree
		int leftMaxDiameter = diameterOfTree2(
				root.left, leftHeight);
		int rightMaxDiameter = diameterOfTree2(
				root.right, rightHeight);

		// Height of current node is max of heights of left and right subtrees plus 1
		height.h = Math.max(leftHeight.h,
				rightHeight.h) + 1;

		/*
		 * Return max of following three 1) Height of left subtree + Height of right
		 * subtree + 1; 2) Diameter of left subtree; 3) Diameter of right subtree
		 */
		return Integer.max(
				leftHeight.h + rightHeight.h + 1,
				Integer.max(leftMaxDiameter,
						rightMaxDiameter));
	}

	public void diagonalSumOfTree(TreeNode root) {
		Map<Integer, Integer> map = new HashMap<>();
		diagonalSumOfTree(root, 0, map);
		System.out.println("Key - Sum");
		for (Map.Entry<Integer, Integer> entry : map
				.entrySet()) {
			System.out.println(entry.getKey()
					+ " - " + entry.getValue());
		}
	}

	public void diagonalSumOfTree(TreeNode root,
			int dist, Map<Integer, Integer> map) {
		if (root == null) return;
		int sum = map.get(dist) == null ? 0
				: map.get(dist);
		sum += root.data;
		map.put(dist, sum);
		diagonalSumOfTree(root.left, dist + 1,
				map);
		diagonalSumOfTree(root.right, dist, map);
	}

	/************************ BT Lowest Common Ancestor ***********************/
	// Print the ancestors of the given node
	// Approach-1: Similar to find the path for the given node
	public void printAncestorsOfGivenNode(
			TreeNode root, int element) {
		ArrayList<Integer> path = new ArrayList<>();
		findPathFromRootToAnyNode1(root, element,
				path);
		path.stream().forEach(
				k -> System.out.print(k + " "));
	}

	/*
	 * LCA - Approach -1 : This method assumes that keys are present in Binary Tree.
	 * If one key is present and other is absent, then it returns the present key as
	 * LCA (Ideally should have returned NULL).
	 */
	public TreeNode lowestCommonAncestor1(
			TreeNode root, int n1, int n2) {
		if (root == null) return null;

		if (root.data == n1 || root.data == n2)
			return root;

		TreeNode left = lowestCommonAncestor1(
				root.left, n1, n2);
		TreeNode right = lowestCommonAncestor1(
				root.right, n1, n2);

		if (left != null && right != null)
			return root;

		return left != null ? left : right;
	}

	/*
	 * LCA - Approach -2; This method to handle all cases by passing two boolean
	 * variables node1 and node2. node1 is set as true when n1 is present in tree
	 * and node2 is set as true if n2 is present in tree.
	 */
	static boolean node1 = false, node2 = false;

	public TreeNode LCA(TreeNode root, int n1,
			int n2) {
		TreeNode node = lowestCommonAncestor2(
				root, n1, n2);

		if (root.data == n1 || root.data == n2) {
			return node; // To handle one of the element is root element; bcoz here node1 & node2 wont be
			// true.
		} else if (node1 && node2) return node;
		return null;
	}

	public TreeNode lowestCommonAncestor2(
			TreeNode root, int n1, int n2) {
		if (root == null) return null;

		if (root.data == n1) {
			node1 = true;
			return root;
		}
		if (root.data == n2) {
			node2 = true;
			return root;
		}

		TreeNode left = lowestCommonAncestor2(
				root.left, n1, n2);
		TreeNode right = lowestCommonAncestor2(
				root.right, n1, n2);

		if (left != null && right != null)
			return root;

		return left != null ? left : right;
	}

	// LCA- Approach-3: Find the path from root to n1 & n2. Then common node in
	// these two path is lowest common ancestor
	public Integer lowestCommonAncestor3(
			TreeNode root, int n1, int n2) {
		if (root == null) return null;

		ArrayList<Integer> path1 = new ArrayList<>();
		ArrayList<Integer> path2 = new ArrayList<>();

		if (!findPathFromRootToAnyNode1(root, n1,
				path1)
				|| !findPathFromRootToAnyNode1(
						root, n2, path2)) {
			System.out.println(
					"Element is not present in the Tree");
			return null;
		}

		System.out.println("Path1:");
		path1.stream().forEach(
				k -> System.out.print(k + " "));
		System.out.println("\nPath2:");
		path2.stream().forEach(
				k -> System.out.print(k + " "));

		int i = 0;
		for (i = 0; i < path1.size()
				&& i < path2.size(); i++) {
			if (!(path1.get(i) == path2.get(i)))
				break;
		}

		return path1.get(i - 1);
	}

	/************************
	 * BT Traversal(DFS/BFS) Modification Problems
	 ***********************/

	/* 
	 *  Connect the node at the same level or Populating Next Right Pointers in Each Node
	 *  Solution for the Perfect Binary Tree & Complete Binary Tree
	 */

	// Approach1: DFS Using extended pre order traversal
	// TC: (n); SC: Only recursion stack space or O(1)
	public void connect11(TreeNode root) {
		if (root == null) return;
		if (root.left == null
				&& root.right == null)
			return;
		if (root.left != null
				&& root.right != null)
			root.left.next = root.right;
		if (root.next != null)
			root.right.next = root.next.left;

		connect11(root.left);
		connect11(root.right);
	}

	//Approach2: DFS Iterative Alg 
	//TC: (n); SC: O(1)
	public void connect12(TreeNode root) {
		if (root == null) return;
		TreeNode pre = root, cur = null;
		while (pre.left != null) {
			cur = pre;
			while (cur != null) {
				cur.left.next = cur.right;
				if (cur.next != null)
					cur.right.next = cur.next.left;
				cur = cur.next;
			}
			pre = pre.left;
		}
	}

	// Approach3: Level order traversal with null markers;
	// Time Complexity: O(n) & Space Complexity: O(n)
	public void connect13(TreeNode root) {
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

	/* Connect the node at the same level or Populating Next Right Pointers in Each Node II
	 * Solution for all type of Binary Tree
	 */
	// Approach1: Level by level traverse.It may looks like DFS appraoch, but not. Because
	//Because this algorithm connects one level completely and then go to next level.
	public void connect21(TreeNode root) {
		if (root == null) return;

		if (root.next != null)
			connect21(root.next);

		if (root.left != null) {
			if (root.right != null) {
				root.left.next = root.right;
				root.right.next = findNextNode(
						root);
			} else {
				root.left.next = findNextNode(
						root);
			}
			connect21(root.left);
		} else if (root.right != null) {
			root.right.next = findNextNode(root);
			connect21(root.right);
		} else {
			connect21(findNextNode(root));
		}
	}

	// Approach2: DFS Algorithm - Iterative 
	public void connect22(TreeNode root) {
		if (root == null) return;

		while (root != null) {
			TreeNode curr = root;

			while (curr != null) {
				if (curr.left != null) {
					if (curr.right != null)
						curr.left.next = curr.right;
					else curr.left.next = findNextNode(
							curr);
				}
				if (curr.right != null) {
					curr.right.next = findNextNode(
							curr);
				}
				curr = curr.next;
			}

			if (root.left != null)
				root = root.left;
			else if (root.right != null)
				root = root.right;
			else root = findNextNode(root);
		}
	}

	// Find the next pointer: Keep checking next pointer in the same level
	private TreeNode findNextNode(TreeNode node) {
		TreeNode curr = node.next;
		while (curr != null) {
			if (curr.left != null)
				return curr.left;
			if (curr.right != null)
				return curr.right;
			curr = curr.next;
		}
		return null;
	}

	// Approach3 : Level by level traversal
	public void connect23(TreeNode root) {
		if (root == null) return;
		TreeNode currHead = root, curr = null,
				nextHead = null, nextCurr = null;

		while (currHead != null) {
			curr = currHead;
			//Traverse the nodes in the same level
			while (curr != null) {
				if (curr.left != null) {
					if (nextHead == null) {
						nextHead = nextCurr = curr.left;
					} else {
						nextCurr.next = curr.left;
						nextCurr = nextCurr.next;
					}
				}

				if (curr.right != null) {
					if (nextHead == null) {
						nextHead = nextCurr = curr.right;
					} else {
						nextCurr.next = curr.right;
						nextCurr = nextCurr.next;
					}
				}
				curr = curr.next;
			}
			currHead = nextHead;
			nextHead = null;
		}
	}

	/************************ Tree Terminologies ***********************/
	// Size of a BT - Recursive Approach
	public static int sizeOfBinaryTree(
			TreeNode root) {
		if (root == null) return 0;
		return 1 + sizeOfBinaryTree(root.left)
				+ sizeOfBinaryTree(root.right);
	}

	// Size of a BT - Iterative Approach
	public int sizeOfBinaryTreeIterative(
			TreeNode root) {
		if (root != null) return 0;

		int count = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode temp;
		queue.add(root);
		while (!queue.isEmpty()) {
			count++;
			temp = queue.poll();
			if (temp.left != null)
				queue.add(temp.left);
			if (temp.right != null)
				queue.add(temp.right);
		}

		return count;
	}

	/*
	 * Maximum Depth/Depth/Level/Height of a Tree - Recursive & Iterative:
	 */

	/*
	 * Recursive Approach: - Height - Path/no of nodes from any node to leaf -
	 * Height of the tree - Height from root to longest leaf - Depth of the tree ==
	 * Height of the tree == No of levels in the tree
	 */
	public int heightOfTree1(TreeNode root) {
		if (root == null) return 0; // 0 means count the nodes, -1 means count the edges
		return 1 + Math.max(
				heightOfTree1(root.left),
				heightOfTree1(root.right));
	}

	// Iterative Approach: Height of the tree
	public int heightOfTree2(TreeNode root) {
		int nodeCount = 0, height = 0; // 0 means count the nodes, -1 means count the edges
		if (root != null) {
			TreeNode temp;
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				nodeCount = queue.size();
				height++;
				while (nodeCount > 0) {
					temp = queue.poll();
					if (temp.left != null)
						queue.add(temp.left);
					if (temp.right != null)
						queue.add(temp.right);
					nodeCount--;
				}
			}
		}
		return height;
	}

	/*
	 * Depth of the Tree/Max Depth: Path/no of nodes from root to longest leaf Depth
	 * of the tree == Height of the tree == No of levels in the tree
	 */
	public int depthOfTree(TreeNode root) {
		if (root == null) return 0; // 0 means count the nodes, -1 means count the edges

		return 1 + Integer.max(
				depthOfTree(root.left),
				depthOfTree(root.right));
	}

	public int depthOfNode(TreeNode root,
			int data) {
		return depthOfNode(root, data, 1);
	}

	public int depthOfNode(TreeNode root,
			int data, int level) {
		if (root == null) return -1;

		if (root.data == data) return level;

		int depth = depthOfNode(root.left, data,
				level + 1);

		if (depth == -1)
			depth = depthOfNode(root.right, data,
					level + 1);

		return depth;
	}

	/*
	 * Overview of level, path, ancestor: - It follows same recursive approach to
	 * find the level, path or ancestor - Level: Pass level & element in recursive
	 * method - Path to leaf or any node: Pass list & element in recursive method -
	 * Ancestors for any node: Pass list & element in recursive method
	 */
	// Level of the tree
	public int levelOfTree(TreeNode root) {
		if (root == null) return 0; // 0 means count the nodes, -1 means count the edges

		return 1 + Integer.max(
				depthOfTree(root.left),
				depthOfTree(root.right));
	}

	// Level of the given Node - Recursive approach
	public int levelOfNode1(TreeNode root,
			int data) {
		return levelOfNode1(root, data, 1);
	}

	private int levelOfNode1(TreeNode root,
			int data, int level) {
		if (root == null) return 0;

		if (root.data == data) return level;

		int currentLevel = levelOfNode1(root.left,
				data, level + 1);
		if (currentLevel == 0)
			currentLevel = levelOfNode1(
					root.right, data, level + 1);

		return currentLevel;
	}

	// Level of the given Node - Iterative approach
	public int levelOfNode2(TreeNode root,
			int element) {
		int level = 0, count = 0;
		TreeNode curr = null;
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			level++;
			if (root.data == element)
				return level;
			while (!queue.isEmpty()) {
				count = queue.size();
				level++;
				while (count-- > 0) {
					curr = queue.remove();
					if (curr.left != null) {
						if (curr.left.data == element)
							return level;
						queue.add(curr.left);
					}
					if (curr.right != null) {
						if (curr.right.data == element)
							return level;
						queue.add(curr.right);
					}
				}
			}
		}
		return level;
	}

	// Required Methods for countNodes - end
	public int countLeafNodes1(TreeNode root) {
		if (root == null) return 0;
		else if (root.left == null
				&& root.right == null)
			return 1;
		return countLeafNodes1(root.left)
				+ countLeafNodes1(root.right);
	}

	public int countLeafNodes2(TreeNode root) {
		if (root == null) return 0;

		int count = 0;
		TreeNode curr;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			curr = queue.poll();
			if (curr.left == null
					&& curr.right == null)
				count++;
			if (curr.left != null)
				queue.add(curr.left);
			if (curr.right != null)
				queue.add(curr.right);
		}
		return count;
	}

	public void printLeafNodes(TreeNode root) {
		if (root == null) return;

		if (root.left == null
				&& root.right == null) {
			System.out.print(root.data + " ");
			return;
		}

		printLeafNodes(root.left);
		printLeafNodes(root.right);
	}

	/*
	 * Count Complete Tree Nodes: Given a complete binary tree, count the number of
	 * nodes. Definition of a complete binary tree: In a complete binary tree every
	 * level, except possibly the last, is completely filled, and all nodes in the
	 * last level are as far left as possible. It can have between 1 and 2h nodes
	 * inclusive at the last level h.
	 */
	/*
	 * Approach1: Time Complexity-O(log(n)^2) - A fully completed tree has node
	 * number: count = 2 ^ h - 1 - Compare left height and right height, if equal,
	 * use the formular, otherwise recurvisely search left and right at next level -
	 * The search pattern is very similar to binary search, the difference of
	 * heights ethier exsits in left side, or right side - Due to the reason stated
	 * in point 3, the time complexity is h ^ 2, there is h times for each level,
	 * and h times for calculating height at each level
	 */
	public int countNodes1(TreeNode root) {
		int leftHeight = findLeft(root);
		int rightHeight = findRight(root);

		if (leftHeight == rightHeight)
			return (1 << leftHeight) - 1; // or return (int)Math.pow(2, leftHeight) -1;

		return 1 + countNodes1(root.left)
				+ countNodes1(root.right);
	}

	/*
	 * Approach2: Time Complexity-O(log(n)^2) The height of a tree can be found by
	 * just going left. Let a single node tree have height 0. Find the height h of
	 * the whole tree. If the whole tree is empty, i.e., has height -1, there are 0
	 * nodes.
	 * 
	 * Otherwise check whether the height of the right subtree is just one less than
	 * that of the whole tree, meaning left and right subtree have the same height.
	 * - If yes, then the last node on the last tree row is in the right subtree and
	 * the left subtree is a full tree of height h-1. So we take the 2^h-1 nodes of
	 * the left subtree plus the 1 root node plus recursively the number of nodes in
	 * the right subtree. - If no, then the last node on the last tree row is in the
	 * left subtree and the right subtree is a full tree of height h-2. So we take
	 * the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus
	 * recursively the number of nodes in the left subtree. Since I halve the tree
	 * in every recursive step, I have O(log(n)) steps. Finding a height costs
	 * O(log(n)). So overall O(log(n)^2).
	 */
	public int countNodes2(TreeNode root) {
		int count = 0, h;
		h = height(root); // Overall Height

		while (root != null) {
			int rh = height(root.right); // Right Subtree height
			if (rh == h - 1) {
				count += 1 << h;
				root = root.right;
			} else {
				count += 1 << (h - 1);
				root = root.left;
			}
			h--;
		}

		return count;
	}

	// Required Methods for countNodes - start
	public int findLeft(TreeNode root) {
		if (root == null) return 0;
		return findLeft(root.left) + 1;
	}

	public int findRight(TreeNode root) {
		if (root == null) return 0;
		return findRight(root.right) + 1;
	}

	public int height(TreeNode root) {
		if (root == null) return -1;
		return height(root.left) + 1;
	}

	/*
	 * Find Leaves of Binary Tree: The key to solve this problem is converting the
	 * problem to be finding the index of the element in the result list. Then this
	 * is a typical DFS problem on trees.
	 */
	public List<List<Integer>> findLeaves(
			TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		findLeaves(root, result);

		printList(result);
		return result;
	}

	public int findLeaves(TreeNode root,
			List<List<Integer>> result) {
		if (root == null) return -1;

		int left = findLeaves(root.left, result);
		int right = findLeaves(root.right,
				result);

		int currLevel = Math.max(left, right) + 1;

		if (result.size() <= currLevel)
			result.add(new ArrayList<>());
		result.get(currLevel).add(root.data);

		return currLevel;
	}

	private void printList(
			List<List<Integer>> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(i + ": ");
			for (Integer data : list.get(i)) {
				System.out.print(data + " ");
			}
			System.out.println();
		}
	}

	// Display the deepest nodes in the tree
	public void deepestNodesInTree(
			TreeNode root) {
		int level = levelOfTree(root);

		List<Integer> list = new ArrayList<>();
		deepestNodes(root, level, list);
		System.out.println("Deepest Nodes: ");
		list.stream().forEach(
				k -> System.out.print(k + " "));

		// System.out.println("\nSum of deepest nodes: " + list.stream().mapToInt(k ->
		// k).sum());
	}

	public void deepestNodes(TreeNode root,
			int level, List<Integer> list) {
		if (root == null) return;
		if (level == 1) {
			list.add(root.data);
			return;
		}
		deepestNodes(root.left, level - 1, list);
		deepestNodes(root.right, level - 1, list);
	}

	// Sum of nodes at maximum depth of a Binary Tree
	public int sumOfNodesAtMaxDepth(
			TreeNode root) {
		int level = levelOfTree(root);
		return sumDeepestNodes(root, level);
	}

	public int sumDeepestNodes(TreeNode root,
			int level) {
		if (root == null) return 0;
		if (level == 1) return root.data;

		return sumDeepestNodes(root.left,
				level - 1)
				+ sumDeepestNodes(root.right,
						level - 1);
	}

	public int deepestOddLevelNode(
			TreeNode root) {
		return deepestOddLevelNode(root, 1);
	}

	public int deepestOddLevelNode(TreeNode root,
			int currLevel) {
		if (root == null) return 0;

		if (currLevel % 2 == 1 && isLeaf(root))
			return currLevel;

		return Integer.max(
				deepestOddLevelNode(root.left,
						currLevel + 1),
				deepestOddLevelNode(root.right,
						currLevel + 1));
	}

	public boolean isLeaf(TreeNode node) {
		return (node.left == null
				&& node.right == null);
	}

	public int sumNumbers(TreeNode root) {
		int[] sum = new int[1];
		sumNumbers(root, sum,
				new StringBuilder());
		return sum[0];
	}

	public void sumNumbers(TreeNode root,
			int[] sum, StringBuilder sb) {
		if (root == null) return;
		sb.append(String.valueOf(root.data));
		if (root.left == null
				&& root.right == null) {
			System.out.print(sb.toString() + "+");
			sum[0] += Integer
					.valueOf(sb.toString());
			return;
		}

		sumNumbers(root.left, sum, sb);
		if (sb.length() > 1)
			sb.deleteCharAt(sb.length() - 1);
		sumNumbers(root.right, sum, sb);

		if (sb.length() > 1)
			sb.deleteCharAt(sb.length() - 1);
	}

	/************************
	 * Misc Problems - Check this & move to corresponding category
	 ***********************/

	public int subtreeWithAllDeepest(
			TreeNode root) {
		int level = heightOfTree1(root);
		List<Integer> list = new ArrayList<>();
		deepestNodes(root, level, list);

		for (Integer data : list)
			System.out.println(data);
		int data1 = 0, data2 = 0;
		if (list.size() > 1) {
			data1 = list.get(0);
			data2 = list.get(1);
		} else {
			data1 = list.get(0);
		}
		System.out.println("SubTree:"
				+ lowestCommonAncestor1(root,
						data1, data2).data);
		return 0;
	}

	public List<Integer> countSmaller(
			int[] nums) {
		int n = nums.length;
		Integer[] result = new Integer[n];
		TreeNode root = null;
		for (int i = n - 1; i >= 0; i--)
			root = insert(root, nums[i], result,
					i, 0);

		return Arrays.asList(result);
	}

	public TreeNode insert(TreeNode root,
			int data, Integer[] result, int i,
			int currCount) {
		if (root == null) {
			root = new TreeNode(data, 0);
			result[i] = currCount;
		} else if (data == root.data) {
			result[i] = currCount + root.count;
		} else if (data < root.data) {
			root.count++;
			root.left = insert(root.left, data,
					result, i, currCount);
		} else {
			root.right = insert(root.right, data,
					result, i,
					currCount + root.count + 1);
		}
		return root;
	}

	public void swapNodes(TreeNode root, int k,
			int level) {
		if (root == null) return;

		if (level % k == 0) {
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;
		}

		swapNodes(root.left, k, level + 1);
		swapNodes(root.right, k, level + 1);
	}

	public TreeNode buildTree(int[][] nodes) {
		TreeNode root = new TreeNode(1);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int i = 0;

		while (!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			curr.left = nodes[i][0] == -1 ? null
					: new TreeNode(nodes[i][0]);
			curr.right = nodes[i][1] == -1 ? null
					: new TreeNode(nodes[i][1]);
			if (curr.left != null)
				queue.add(curr.left);
			if (curr.right != null)
				queue.add(curr.right);
			i++;
		}

		return root;
	}
}