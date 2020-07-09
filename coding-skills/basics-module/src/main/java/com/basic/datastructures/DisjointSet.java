package com.basic.datastructures;

import java.util.HashMap;
import java.util.Map;

/*
 *  Disjoint-set data structure (also called a union–find data structure or merge–find set) is a data structure that tracks a set of
 *  elements partitioned into a number of disjoint (non-overlapping) subsets. It provides near-constant-time operations to add new sets,
 *  to merge existing sets, and to determine whether elements are in the same set
 * Operations :
 *  1. Find : Can be implemented by recursively traversing the parent array until we hit a node who is parent of itself.
 *  2. Union: It takes, as input, two elements. And finds the representatives of their sets using the find operation, and finally puts 
 *     either one of the trees (representing the set) under the root node of the other tree, effectively merging the trees and the sets.
 *  3. Path Compression (Modifications to find()) : It speeds up the data structure by compressing the height of the trees. It can be 
 *     achieved by inserting a small caching mechanism into the Find operation. 
 *     
 * Applications:
 *   - Disjoint-set data structures model the partitioning of a set, for example to keep track of the connected components of an undirected graph.
 *   - This model can then be used to determine whether two vertices belong to the same component, or whether adding an edge between them would result in a cycle. 
 *   - This data structure is used by the Boost Graph Library to implement its Incremental Connected Components functionality.
 *   - It is also a key component in implementing Kruskal's algorithm to find the minimum spanning tree of a graph.
 *   
 *   This class has Disjoint Set implementation using Array & Map
 */
public class DisjointSet implements DisjointSetOperations {

	public int[] parent;

	public DisjointSet(int capacity) {
		parent = new int[capacity];
	}

	@Override
	public void createNode(int node) {
		// TODO Auto-generated method stub
	}

	public boolean union(int set1, int set2) {
		int root1 = find(set1);
		int root2 = find(set2);
		if (root1 != root2) { // If it doesn't have same parent
			parent[root2] = root1;
			return false; // Means pointed to same parent or union the two sets
		}
		return true; // Means already pointed to same parent, no need to combine or union the sets
	}

	public int find(int i) {
		while (parent[i] != i) {
			parent[i] = parent[parent[i]]; // Is that mandatory??
			i = parent[i];
		}

		return i;
	}

	public int find1(int node) {
		if (parent[node] == node)
			return node;

		parent[node] = find1(parent[node]);
		return parent[node];
	}

	public static void main(String[] args) {
	}

	@Override
	public int findParentRecursive(int node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findParentIterative(int node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean unionByRank(int n1, int n2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unionBySize(int n1, int n2) {
		// TODO Auto-generated method stub
		return false;
	}
}

class DisjointSetUsingArray implements DisjointSetOperations {
	// Array Index: Value of the Node. It should be int and start from 0 to size-1
	// Each Index is SetNode, which consists of value of node, parent node, rank and size
	public SetNode[] disJointSets;

	class SetNode {
		public int data;
		public int parent;
		public int rank;
		public int size;
	}

	public DisjointSetUsingArray(int n) {
		disJointSets = new SetNode[n];
	}

	@Override
	public void createNode(int node) {
		disJointSets[node] = new SetNode();
		disJointSets[node].rank = 0;
		disJointSets[node].parent = node;
	}

	@Override
	public int findParentRecursive(int node) {
		if (disJointSets[node].parent != node)
			disJointSets[node].parent = findParentRecursive(disJointSets[node].parent);

		return disJointSets[node].parent;
	}

	@Override
	public int findParentIterative(int node) {
		while (disJointSets[node].parent != node)
			node = disJointSets[node].parent;

		disJointSets[node].parent = node;
		return disJointSets[node].parent;
	}

	@Override
	public boolean union(int n1, int n2) {
		int parent1 = findParentRecursive(n1);
		int parent2 = findParentRecursive(n2);

		// Return false, if node is already in same set
		if (parent1 == parent2)
			return false;

		disJointSets[parent2].parent = parent1;

		return true;
	}

	@Override
	public boolean unionByRank(int n1, int n2) {
		int parent1 = findParentRecursive(n1);
		int parent2 = findParentRecursive(n2);

		// Return false, if node is already in same set
		if (parent1 == parent2)
			return false;

		if (disJointSets[parent1].rank > disJointSets[parent2].rank) {
			disJointSets[parent2].parent = parent1;
		} else if (disJointSets[parent2].rank > disJointSets[parent1].rank) {
			disJointSets[parent1].parent = parent2;
		} else {
			disJointSets[parent1].rank++;
			disJointSets[parent2].parent = parent1;
		}

		return true;
	}

	@Override
	public boolean unionBySize(int n1, int n2) {
		int parent1 = findParentRecursive(n1);
		int parent2 = findParentRecursive(n2);

		// Return false, if node is already in same set
		if (parent1 == parent2)
			return false;

		if (disJointSets[parent2].size > disJointSets[parent2].size) {
			disJointSets[parent1].parent = parent2;
			disJointSets[parent2].size += disJointSets[parent1].size;
		} else {
			disJointSets[parent2].parent = parent1;
			disJointSets[parent1].size += disJointSets[parent2].size;
		}

		return true;
	}
}

class DisjointSetUsingMap implements DisjointSetOperations {
	// Map-Key: Value of the Node. It can be int, char,string, or any custom object
	// Map-Value: SetNode, which consists of value of node, parent node, rank and size
	Map<Integer, SetNode> map = new HashMap<>();

	class SetNode {
		public int val;
		public SetNode parent;
		public int rank;
		public int size;
	}

	/*
	 * The MakeSet operation makes a new set by creating a new element with a unique id, a rank of 0, and a parent pointer to itself. 
	 * The parent pointer to itself indicates that the element is the representative member of its own set.
	 * The MakeSet operation has O(1) time complexity, so initializing n sets has O(n) time complexity.
	 */
	@Override
	public void createNode(int val) {
		SetNode node = new SetNode();
		node.val = val;
		node.rank = 0;
		node.parent = node;
		map.put(val, node);
	}

	public SetNode findSet(SetNode node) {
		SetNode parent = node.parent;
		// Here find the parent recursively & compress the path(Point all the sub node to parent)
		if (node != parent) // Path compression technique: To reduce the height of the tree/path.(Rank == height)
			node.parent = findSet(node.parent);
		return node.parent;
	}

	public int findSet(int data) {
		return findSet(map.get(data)).val;
	}

	@Override
	public boolean unionByRank(int n1, int n2) {
		SetNode parent1 = findSet(map.get(n1));
		SetNode parent2 = findSet(map.get(n2));

		if (parent1 == parent2)
			return false;

		// Merge the node based on rank
		if (parent1.rank > parent2.rank) {
			parent2.parent = parent1;
		} else if (parent2.rank > parent1.rank) {
			parent1.parent = parent2;
		} else {
			parent1.rank = parent1.rank + 1;
			parent2.parent = parent1;
		}
		return true;
	}

	@Override
	public boolean unionBySize(int n1, int n2) {
		SetNode parent1 = findSet(map.get(n1));
		SetNode parent2 = findSet(map.get(n2));

		if (parent1 == parent2)
			return false;

		// Merge the node based on size
		if (parent2.size > parent1.size) {
			parent1.parent = parent2;
			parent2.size += parent1.size;
		} else {
			parent2.parent = parent1;
			parent1.size += parent2.size;
		}
		return true;
	}

	@Override
	public int findParentRecursive(int node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findParentIterative(int node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean union(int n1, int n2) {
		// TODO Auto-generated method stub
		return false;
	}
}

interface DisjointSetOperations {
	/* Find & path compression logic:
	 * 	 1.Find: Find(x) follows the chain of parent pointers from x up the tree until it reaches a root element, whose parent is itself.
	 *     This root element is the representative member of the set to which x belongs, and may be x itself.
	 * 	 2.Path compression: Path compression flattens the structure of the tree by making every node point to the root whenever Find is
	 *     used on it. This is valid, since each element visited on the way to a root is part of the same set. The resulting flatter tree
	 *     speeds up future operations not only on these elements, but also on those referencing them.
	 */
	// Recursive Approach
	public int findParentRecursive(int node);

	// Iterative Approach
	public int findParentIterative(int node);

	public void createNode(int node);

	/*
	 * Union(x,y) uses Find to determine the roots of the trees x and y belong to. If the roots are distinct, the trees are combined
	 * by attaching the root of one to the root of the other.  union by rank or union by size is uses to implement this.
	 * Note: Union operation return type should be void. But boolean will be useful to apply in problems
	 */
	public boolean union(int n1, int n2);

	/*
	 * Union by rank always attaches the shorter tree to the root of the taller tree. Thus, the resulting tree is no taller than the 
	 * originals unless they were of equal height, in which case the resulting tree is taller by one node.
	 */
	public boolean unionByRank(int n1, int n2);

	/*
	 * Union by size always attaches the tree with fewer elements to the root of the tree having more elements.
	 */
	public boolean unionBySize(int n1, int n2);
}