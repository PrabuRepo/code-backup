package com.geeks.problem.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import com.common.model.EdgeNode;
import com.common.model.GraphNode;
import com.common.model.UndirectedGraphNode;
import com.common.utilities.DisjointSet;

public class GraphProblems {
	/******** Introduction, DFS and BFS **********/
	// 1.Graph Representations
	// i.Adjacency Matrix
	public void buildAdjacencyMatrix(int n) {
		int[][] adjMatrix = new int[n][n];
		Scanner in = new Scanner(System.in);
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				System.out.println(r + " - " + c + "->");
				adjMatrix[r][c] = in.nextInt();
			}
		}
		in.close();
	}

	// ii.Adjacency List
	// a.Create adjacent list instance based on no of vertices for simple directed graph
	public LinkedList<Integer>[] initializeAdjList(int n) {
		LinkedList<Integer>[] adjList = new LinkedList[n];
		for (int i = 0; i < n; i++)
			adjList[i] = new LinkedList<>();
		return adjList;
	}

	// b.Add edge in the Undirected graph
	public void addEdgeUndirectedGraph(LinkedList<Integer>[] adjList, int src, int dest) {
		adjList[src].add(dest);
		adjList[dest].add(src);
	}

	// c.Add edge in the Directed graph
	public void addEdgeDirectedGraph(LinkedList<Integer>[] adjList, int src, int dest) {
		adjList[src].add(dest);
	}

	// ii.Adjacency List(Weighted Node)
	// a.Create adjacent list instance based on no of vertices for simple directed graph
	public LinkedList<GraphNode>[] initializeAdjWeightedList(int v) {
		LinkedList<GraphNode>[] adjWeightedList = new LinkedList[v];
		for (int i = 0; i < v; i++)
			adjWeightedList[i] = new LinkedList<>();
		return adjWeightedList;
	}

	// b.Add edge in the Undirected graph
	public void addEdgeWeightedDirectedGraph(LinkedList<GraphNode>[] adjWeightedList, int src, int dest, int weight) {
		GraphNode node = new GraphNode();
		node.vertex = dest;
		node.weight = weight;
		adjWeightedList[src].add(node);
	}

	// d.Build directed graph from given input(edges); where n - No of vertices, edges - Edge list
	public LinkedList<Integer>[] buildAdjListDirectedGraph(int n, int[][] edges) {
		if (n == 0 || edges.length == 0) return null;

		LinkedList<Integer>[] adjList = initializeAdjList(n);
		for (int i = 0; i < edges.length; i++)
			addEdgeDirectedGraph(adjList, edges[i][0], edges[i][1]);
		return adjList;
	}

	// d.Build undirected graph from given input(edges); where n - No of vertices, edges - Edge list
	public LinkedList<Integer>[] buildAdjListUndirectedGraph(int n, int[][] edges) {
		if (n == 0 || edges.length == 0) return null;

		LinkedList<Integer>[] adjList = initializeAdjList(n);
		for (int i = 0; i < edges.length; i++)
			addEdgeUndirectedGraph(adjList, edges[i][0], edges[i][1]);
		return adjList;
	}

	// d.Build directed & weightd graph from given input(edges); where n - No of vertices, edges - Edge list
	public LinkedList<GraphNode>[] buildAdjListDirectWeightedGraph(int n, int[][] edges) {
		if (n == 0 || edges.length == 0 || edges[0].length != 3) return null;

		LinkedList<GraphNode>[] adjWeightedList = initializeAdjWeightedList(n);
		for (int i = 0; i < edges.length; i++)
			addEdgeWeightedDirectedGraph(adjWeightedList, edges[i][0], edges[i][1], edges[i][2]);
		return adjWeightedList;
	}

	// iii.Incidence or Edge based Representation
	// a.Create Edge array instance based on no of vertices for simple directed graph
	public EdgeNode[] initializeIncidence(int V, int E) {
		EdgeNode[] edges = new EdgeNode[E];
		for (int i = 0; i < E; i++)
			edges[i] = new EdgeNode();
		return edges;
	}

	// b. Add edges info like src, dest & weight values
	public void addEdgeIncidenceWeightedGraph(EdgeNode[] edges, int edgeIndex, int src, int dest, int weight) {
		edges[edgeIndex].src = src;
		edges[edgeIndex].dest = dest;
		edges[edgeIndex].weight = weight;
	}

	// d. Build Edge array for the given edge list
	public EdgeNode[] buildIncidenceWeightedGraph(int n, int e, int[][] edgeList) {
		if (n == 0 || edgeList.length == 0) return null;

		EdgeNode[] edges = initializeIncidence(n, e);
		for (int i = 0; i < edgeList.length; i++)
			addEdgeIncidenceWeightedGraph(edges, i, edgeList[i][0], edgeList[i][1], edgeList[i][2]);

		return edges;
	}

	// BFS Algorithm: 1.Adjacency Representation
	public void bfs(LinkedList<Integer>[] adjList, int n, int s) {
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		// Add the node in Queue and mark as visited
		queue.add(s);
		visited[s] = true;

		while (!queue.isEmpty()) {
			// Fetch from queue and display the node
			int data = queue.poll();
			System.out.print(data + "-");

			// Iterate all the adjacent vertices to this vertex
			ListIterator<Integer> list = adjList[data].listIterator();
			while (list.hasNext()) {
				int next = list.next();
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
	}

	// BFS Algorithm: 2.Adjacency Matrix Representation
	public void bfs(int[][] adjMatrix, int n) {
		if (adjMatrix.length == 0 || adjMatrix[0].length == 0) return;

		boolean[] visited = new boolean[n];
		bfsUtil(adjMatrix, visited, n);
	}

	public void bfsUtil(int[][] adjMatrix, boolean[] visited, int v) {
		int n = adjMatrix.length;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		while (!queue.isEmpty()) {
			int top = queue.poll();
			visited[top] = true;
			System.out.print(top + "-");
			for (int j = 0; j < n; j++) {
				if (!visited[j] && j != v && adjMatrix[top][j] == 1) queue.add(j);
			}
		}
	}

	// DFS Algorithm: 1.Adjacency List Representation (recursive approach)
	public void dfs(LinkedList<Integer>[] adjList, int n, int s) {
		boolean[] visited = new boolean[n];
		dfsUtil1(adjList, s, visited);
	}

	public void dfsUtil1(LinkedList<Integer>[] adjList, int v, boolean[] visited) {
		// Mark it as visited
		visited[v] = true;
		System.out.print(v + "-");

		// Iterate all the adjacent vertices to this vertex
		ListIterator<Integer> listIterator = adjList[v].listIterator();
		while (listIterator.hasNext()) {
			int data = listIterator.next();
			if (!visited[data]) {
				dfsUtil1(adjList, data, visited);
			}
		}
	}

	// DFS Algorithm: 2.Adjacency List Representation (iterative approach)
	public void dfsIterative(LinkedList<Integer>[] adjList, int n, int s) {
		boolean[] visited = new boolean[n];
		dfsUtil2(adjList, s, visited);
	}

	public void dfsUtil2(LinkedList<Integer>[] adjList, int s, boolean[] visited) {
		Stack<Integer> stack = new Stack<>();
		// Push the node into stack and mark it as visited
		stack.push(s);
		visited[s] = true;

		while (!stack.isEmpty()) {
			// Pop the element and display the vertex
			int data = stack.pop();
			System.out.print(data + "-");

			// Iterate all the adjacent vertices to this vertex
			ListIterator<Integer> listIterator = adjList[data].listIterator();
			while (listIterator.hasNext()) {
				int next = listIterator.next();
				if (!visited[next]) {
					stack.push(next);
					visited[next] = true;
				}
			}
		}
	}

	// DFS Algorithm:3. Adjacency Matrix Representation
	public void dfs(int[][] adjMatrix, int n) {
		if (adjMatrix.length == 0 || adjMatrix[0].length == 0) return;

		boolean[] visited = new boolean[n];
		dfsUtil(adjMatrix, visited, n);
	}

	public void dfsUtil(int[][] adjMatrix, boolean[] visited, int v) {
		int n = adjMatrix.length;
		visited[v] = true;
		System.out.print(v + "-");
		for (int j = 0; j < n; j++) {
			if (!visited[j] && j != v && adjMatrix[v][j] == 1) {
				dfsUtil(adjMatrix, visited, j);
			}
		}
	}

	// Find no of disconnected components in the graph. It can be solved using DFS, BFS & Union-Find
	// 1.i.Using DFS algorithm(Adjacency List)
	public int disConnectedGraphDFS(LinkedList<Integer>[] adjList, int n, int s) {
		boolean[] visited = new boolean[n];
		int groups = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				groups++;
				dfsUtil1(adjList, i, visited);
				System.out.println();
			}
		}
		return groups;
	}

	// 1.ii.Using DFS algorithm(Adjacency Matrix)
	public int disConnectedGraphDFS(int[][] adjMatrix, int n) {
		if (adjMatrix.length == 0) return 0;

		int groups = 0;
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				groups++;
				dfsUtil(adjMatrix, visited, i);
			}
		}
		return groups;
	}

	// 2.i.Using BFS algorithm(Adjacency List)
	public int disConnectedGraphBFS(LinkedList<Integer>[] adjList, int n, int s) {
		boolean[] visited = new boolean[n];
		int groups = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				groups++;
				bfsUtil(adjList, i, visited);
				System.out.println();
			}
		}
		return groups;
	}

	public void bfsUtil(LinkedList<Integer>[] adjList, int s, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		visited[s] = true;
		while (!queue.isEmpty()) {
			// Fetch from queue and display the node
			int top = queue.poll();
			System.out.print(top + "-");

			// Iterate all the adjacent vertices to this vertex
			ListIterator<Integer> list = adjList[top].listIterator();
			while (list.hasNext()) {
				int next = list.next();
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}

		}
	}

	// 2.ii.Using BFS algorithm(Adjacency Matrix)
	public int disConnectedGraphBFS(int[][] adjMatrix, int n) {
		if (adjMatrix.length == 0) return 0;

		int groups = 0;
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				groups++;
				bfsUtil(adjMatrix, visited, i);
			}
		}
		return groups;
	}

	// 3.Using Union-Find/Disjoint set
	public int disConnectedGraphUF(int[][] adjMatrix) {
		int row = adjMatrix.length, col = adjMatrix[0].length;
		DisjointSet ds = new DisjointSet(row);
		for (int i = 0; i < row; i++)
			ds.parent[i] = i;

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (adjMatrix[i][j] == 1 && i != j) {
					ds.union(i, j);
				}

		int count = 0;
		for (int i = 0; i < row; i++)
			if (ds.parent[i] == i) count++;

		return count;
	}

	// Disjoint Set/UF: Simplified version of disConnectedGraphUF()
	public int noOfConnectedGraphUF(int n, int[][] edges) {
		int[] nodes = new int[n];
		for (int i = 0; i < n; i++)
			nodes[i] = i;

		for (int[] edge : edges) {
			// Find Operation
			int i1 = find(nodes, edge[0]);
			int i2 = find(nodes, edge[1]);
			// Union Operation
			if (i1 != i2) nodes[i2] = i1;
		}

		int count = 0;
		for (int i = 0; i < n; i++)
			if (nodes[i] == i) count++;

		return count;
	}

	private int find(int[] arr, int i) {
		while (i != arr[i]) {
			arr[i] = arr[arr[i]];
			i = arr[i];
		}
		return i;
	}

	public void displayAdjList(LinkedList<Integer>[] adjList, int n) {
		for (int i = 0; i < n; i++) {
			System.out.println("\nEdges from Vertex: " + i + "->");
			ListIterator<Integer> iterator = adjList[i].listIterator();
			while (iterator.hasNext())
				System.out.print(iterator.next() + ", ");
		}
	}

	public void longestPathInDAG() {
		// Longest Path in a Directed Acyclic Graph
	}

	// Clone an Undirected Graph
	// Using BFS traversal to clone the graph
	public UndirectedGraphNode cloneGraph1(UndirectedGraphNode root) {
		if (root == null) return null;

		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		UndirectedGraphNode rootNode, clonedNeighNode, curr;
		// Similar to visited boolean array, but here we need node reference. So we use map to store the reference
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

		map.put(root, new UndirectedGraphNode(root.label));
		queue.add(root);

		while (!queue.isEmpty()) {
			curr = queue.poll();
			rootNode = map.get(curr);

			for (UndirectedGraphNode neighbor : curr.neighbors) {
				clonedNeighNode = map.get(neighbor);
				if (clonedNeighNode == null) {
					clonedNeighNode = new UndirectedGraphNode(neighbor.label);
					map.put(neighbor, clonedNeighNode);
					queue.add(neighbor);
				}
				rootNode.neighbors.add(clonedNeighNode);
			}
		}

		return map.get(root);
	}

	HashMap<Integer, UndirectedGraphNode> graphMap = new HashMap<>();

	// Using DFS traversal to clone the graph
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode root) {
		if (root == null) return null;

		if (graphMap.containsKey(root.label)) return graphMap.get(root.label);

		UndirectedGraphNode clone = new UndirectedGraphNode(root.label);
		graphMap.put(clone.label, clone);

		for (UndirectedGraphNode neigbhor : root.neighbors) {
			clone.neighbors.add(cloneGraph(neigbhor)); // Recursive call to traverse the graph
		}

		return clone;
	}

	// Using BFS
	public boolean routeBwNodes(LinkedList<Integer>[] adjList, int start, int end) {
		if (adjList.length == 0) return false;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[adjList.length];

		visited[start] = true;
		queue.add(start);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			Iterator<Integer> iter = adjList[curr].iterator();
			while (iter.hasNext()) {
				int adjNode = iter.next();
				if (!visited[adjNode]) {
					if (adjNode == end) return true;
					visited[adjNode] = true;
					queue.add(adjNode);
				}
			}
		}

		return false;
	}

	/******** Graph Cycle **********/
	// Using DFS algorithm
	public boolean hasCycleInDirectedGraph(LinkedList<Integer>[] adjList, int n) {
		boolean[] visited = new boolean[n];
		boolean[] recursionStack = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				if (hasCycleInDirectedGraphUtil(adjList, i, visited, recursionStack)) return true;
			}
		}
		return false;
	}

	public boolean hasCycleInDirectedGraphUtil(LinkedList<Integer>[] adjList, int vertex, boolean[] visited,
			boolean[] recursionStack) {
		// If this condition satisfies, then graph contains cycle
		if (recursionStack[vertex]) return true;

		if (visited[vertex]) return false;

		// Mark vertex as visited and set recursion stack
		visited[vertex] = true;
		recursionStack[vertex] = true;

		if (adjList[vertex] != null) {
			ListIterator<Integer> iter = adjList[vertex].listIterator();
			while (iter.hasNext()) {
				int adjVertex = iter.next();
				if (hasCycleInDirectedGraphUtil(adjList, adjVertex, visited, recursionStack)) return true;
			}
		}
		// Reset the recursion stack array
		recursionStack[vertex] = false;
		return false;
	}

	public boolean hasCycleInUndirectedGraph(LinkedList<Integer>[] adjList, int n) {
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				if (hasCycleInUndirectedGraphUtil(adjList, i, visited, -1)) return true;
			}
		}
		return false;
	}

	// using DFS
	private boolean hasCycleInUndirectedGraphUtil(LinkedList<Integer>[] adjList, int vertex, boolean[] visited,
			int parent) {
		visited[vertex] = true;
		ListIterator<Integer> iter = adjList[vertex].listIterator();
		while (iter.hasNext()) {
			int adjVertex = iter.next();
			if (!visited[adjVertex]) {
				if (hasCycleInUndirectedGraphUtil(adjList, adjVertex, visited, vertex)) return true;
			} else if (adjVertex != parent) return true;
		}
		return false;
	}

	// Using DisjointSet: Union-Find Algorithm can be used to check whether an undirected graph contains cycle or no
	public boolean hasCycleInUndirectedGraph(EdgeNode[] edges, int n, int e) {
		// DisjointSetUsingMap set = new DisjointSetUsingMap();
		DisjointSet ds = new DisjointSet(n);
		for (int i = 0; i < n; i++)
			ds.parent[i] = i;

		for (int i = 0; i < e; i++) {
			if (ds.union(edges[i].src, edges[i].src)) return true;
		}
		return false;
	}

	// Course Schedule I:
	// Topo Sort - BFS Approach
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		List<Integer>[] adjList = new LinkedList[numCourses];
		Queue<Integer> queue = new LinkedList<>();
		int[] indegree = new int[numCourses];

		for (int i = 0; i < numCourses; i++)
			adjList[i] = new LinkedList<>();

		for (int[] pair : prerequisites) {
			adjList[pair[0]].add(pair[1]);
			indegree[pair[1]]++;
		}

		for (int i = 0; i < indegree.length; i++)
			if (indegree[i] == 0) queue.offer(i);

		int count = 0;
		while (!queue.isEmpty()) {
			int course = queue.poll();
			count++;
			for (int adj : adjList[course])
				if (--indegree[adj] == 0) queue.offer(adj);
		}

		return count == numCourses;
	}

	// DFS Approach
	public boolean canFinish2(int noOfcourses, int[][] preRequisites) {
		// validation
		if (noOfcourses == 0 || preRequisites.length == 0) return true;

		// build graph
		LinkedList<Integer>[] adjList = buildAdjListDirectedGraph(noOfcourses, preRequisites);

		// Check the graph has cycle using DFS
		return !hasCycleInDirectedGraph(adjList, noOfcourses);
	}

	// Friend Circles: Find the friends circle group
	public int findFriendCircleNum(int[][] M) {
		if (M.length == 0 || M[0].length == 0) return 0;

		return disConnectedGraphDFS(M, M.length);
		// return disConnectedGraphBFS(M, M.length);
		// return disConnectedGraphUF(M);
	}

	/* Graph Valid Tree: Check if a given graph is tree or not
	 * An undirected graph is tree if it has following properties. 
	 *  1) There is no cycle.
	 *  2) The graph is connected.
	 */
	public boolean isTree(int n, int[][] edges) {
		if (n == 0 || edges.length == 0) return false;

		// Build graph from given input(edges)
		LinkedList<Integer>[] adjList = buildAdjListUndirectedGraph(n, edges);

		boolean[] visited = new boolean[n];

		// 1. Using DFS, check is there any cycle
		boolean isCycle = hasCycleInUndirectedGraphUtil(adjList, 0, visited, -1);

		// 2. Check if graph is connected,
		if (!isCycle) {
			for (int i = 0; i < n; i++)
				if (!visited[i]) return false;
		}

		return !isCycle;
	}

	public boolean validTree(int n, int[][] edges) {
		// initialize n isolated islands
		int[] nums = new int[n];
		Arrays.fill(nums, -1);

		// perform union find
		for (int i = 0; i < edges.length; i++) {
			int x = find(nums, edges[i][0]);
			int y = find(nums, edges[i][1]);

			// if two vertices happen to be in the same set
			// then there's a cycle
			if (x == y) return false;

			// union
			nums[x] = y;
		}

		return edges.length == n - 1;
	}

	// Function returns the minimum number of swaps
	// required to sort the array
	// Approach1: Using Graph
	public int minSwaps(int[] arr) {
		int n = arr.length;

		// Create two arrays and use as pairs where first
		// array is element and second array
		// is position of first element
		ArrayList<Pair> arrpos = new ArrayList<>();
		for (int i = 0; i < n; i++)
			arrpos.add(new Pair(arr[i], i));
		// Assume data as key,
		// Sort the array by array element values to
		// get right position of every element as the
		// elements of second array.
		/*	arrpos.sort(new Comparator<Pair>() {
		@Override
		public int compare(Pair o1, Pair o2) {
			if (o1.key < o2.key) return -1;
			// We can change this to make it then look at the
			// words alphabetical order
			else if (o1.key == o2.key) return 0;
			else return 1;
		}
		});*/
		// Collections.sort(arrpos, (a, b) -> (a.key - b.key));
		arrpos.sort((a, b) -> (a.key - b.key));
		// To keep track of visited elements. Initialize
		// all elements as not visited or false.
		Boolean[] vis = new Boolean[n];
		Arrays.fill(vis, false);

		// Initialize result
		int ans = 0;

		// Traverse array elements
		for (int i = 0; i < n; i++) {
			// already swapped and corrected or
			// already present at correct pos
			if (vis[i] || arrpos.get(i).val == i) continue;

			// find out the number of node in
			// this cycle and add in ans
			int cycle_size = 0;
			int j = i;
			while (!vis[j]) {
				vis[j] = true;

				// move to next node
				j = arrpos.get(j).val;
				cycle_size++;
			}

			// Update answer by adding current cycle.
			if (cycle_size > 0) {
				ans += (cycle_size - 1);
			}
		}

		return ans;
	}

	/* Approach2: Refer this link
	 * https://www.geeksforgeeks.org/minimum-number-of-swaps-required-to-sort-an-array-set-2/
	 * Conert from C++ to Java code 
	 */
	public int minSwaps2(int[] arr) {
		/*
		// Declare a vector of pair
		List<Pair> vec = new ArrayList<>();
		int n = arr.length;
		for (int i = 0; i < n; i++) {
		vec.add(new Pair(arr[i], i));
		}
		
		// Sort the vector w.r.t the first
		// element of pair
		Collections.sort(vec, (a, b) -> a.key - b.key);
		
		int ans = 0, c = 0, j;
		
		for (int i = 0; i < n; i++) {
		// If the element is already placed
		// correct, then continue
		if (vec.get(i).val == i) continue;
		else {
		// swap with its respective index
		swap(vec[i].first, vec[vec[i].second].first);
		swap(vec[i].second, vec[vec[i].second].second);
		}
		
		// swap until the correct
		// index matches
		if (i != vec[i].second) --i;
		
		// each swap makes one element
		// move to its correct index,
		// so increment answer
		ans++;
		}
		
		return ans;
		*/
		return 0;
	}

	/* BFS: Shortest Reach in a Graph: 
	 * Consider an undirected graph consisting of n nodes where each node is labeled from 1 to n and the edge between any two
	 * nodes is always of length 6. We define node s to be the starting position for a BFS. Given a graph, determine the
	 * distances from the start node to each of its descendants and return the list in node number order, ascending. If
	 * a node is disconnected, it's distance should be -1.
	 * For example, there are n=6 nodes in the graph with a starting node s=1. The list of edges = {{1,2},{2,3},{3,4},{1,5}}
	 *  and each has a weight of 6. 
	 *  Starting from node 1  and creating a list of distances, for nodes 2 through 6 we have distances = {6,12,18,6,-1}.
	 */
	public int[] shortestReach(int n, LinkedList<Integer>[] adjList, int startId) { // 0 indexed
		int[] distances = new int[n];
		Arrays.fill(distances, -1);
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		int level = 0, distance = 0;

		if (startId < 0 || startId >= n) return distances;

		visited[startId] = true;
		queue.add(startId);
		while (!queue.isEmpty()) {
			level = queue.size();
			while (level-- > 0) {
				int v = queue.poll();
				distances[v] = distance;
				// Add the adjacent nodes of 'v' in the queue
				Iterator<Integer> iter = adjList[v].iterator();
				while (iter.hasNext()) {
					int adjNode = iter.next();
					if (!visited[adjNode]) {
						visited[adjNode] = true;
						queue.add(adjNode);
					}
				}
			}
			distance += 6;
		}

		return distances;
	}

	/* Find the nearest clone: 
	 * In this challenge, there is a connected undirected graph where each of the nodes is a color. Given a color, find
	 * the shortest path connecting any two nodes of that color. Each edge has a weight of 1. If there is not a pair or
	 * if the color is not found, print -1. For example, given graph_nodes 5, and 4 edges g_from ={1,2,2,3} and 
	 * g_to={2,3,4,5}  and colors for each node are arr={1,2,3,1,3}  we can draw the following graph:
	 */
	static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
		int minDistance = Integer.MAX_VALUE;
		Queue<GraphNode2> queue = new LinkedList<>();
		boolean[] visited = new boolean[graphNodes];
		LinkedList<Integer>[] adjList = new LinkedList[graphNodes];

		for (int i = 0; i < graphNodes; i++)
			adjList[i] = new LinkedList<>();

		for (int i = 0; i < graphFrom.length; i++) {
			adjList[graphFrom[i] - 1].add(graphTo[i] - 1);
			adjList[graphTo[i] - 1].add(graphFrom[i] - 1);
		}
		int s = 0; // Starting vertex
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == val) {
				s = i;
				break;
			}
		}

		queue.add(new GraphNode2(s, ids[s], 0));
		visited[s] = true;
		while (!queue.isEmpty()) {
			GraphNode2 node = queue.poll();
			if (node.vertex != s && node.color == val) minDistance = Math.min(minDistance, node.dist);

			Iterator<Integer> iter = adjList[node.vertex].iterator();
			while (iter.hasNext()) {
				int adjNode = iter.next();
				if (!visited[adjNode]) {
					visited[adjNode] = true;
					int dist = node.color == ids[adjNode] ? 1 : node.dist + 1;
					queue.add(new GraphNode2(adjNode, ids[adjNode], dist));
				}
			}
		}

		return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
	}

	/* Roads and Libraries: 
	 * The Ruler of HackerLand believes that every citizen of the country should have access to a library.
	 * Unfortunately, HackerLand was hit by a tornado that destroyed all of its libraries and obstructed its roads! As
	 * you are the greatest programmer of HackerLand, the ruler wants your help to repair the roads and build some new
	 * libraries efficiently. 
	 * HackerLand has cities numbered from 1 to n. The cities are connected by m bidirectional roads. A citizen has 
	 * access to a library if: 
	 * Their city contains a library. 
	 * They can travel by road from their city to a
	 * city containing a library.
	 */
	static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
		long totalCost = 0;
		if (c_lib < c_road) {
			totalCost = (long) c_lib * (long) n;
			return totalCost;
		}

		// Build the adjacency list for the given edges
		LinkedList<Integer>[] adjList = new LinkedList[n];
		for (int i = 0; i < n; i++)
			adjList[i] = new LinkedList<>();

		for (int[] city : cities) {
			adjList[city[0] - 1].add(city[1] - 1);
			adjList[city[1] - 1].add(city[0] - 1);
		}

		List<List<Integer>> groups = new ArrayList<>();
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				List<Integer> adjCities = new ArrayList<>();
				dfs(adjList, i, visited, adjCities);
				groups.add(adjCities);
			}
		}

		for (List<Integer> list : groups)
			totalCost += c_road * (list.size() - 1) + c_lib;

		return totalCost;
	}

	public static void dfs(LinkedList<Integer>[] adjList, int s, boolean[] visited, List<Integer> adjCities) {
		visited[s] = true;
		adjCities.add(s);
		ListIterator<Integer> iter = adjList[s].listIterator();
		while (iter.hasNext()) {
			int node = iter.next();
			if (!visited[node]) dfs(adjList, node, visited, adjCities);
		}
	}

	/******** Topological Sorting ************/
	// Topo sort is used to order the graph
	/* Topological sorting Algorithm using DFS:
	 * Time Complexity: O(V+E)
	 */
	public void toplogicalSort(LinkedList<Integer>[] adjList, int n) {
		boolean[] visited = new boolean[n];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			if (!visited[i]) topoSortUtil(adjList, i, visited, stack);
		}

		while (!stack.isEmpty())
			System.out.print(stack.pop() + "-");
	}

	private void topoSortUtil(LinkedList<Integer>[] adjList, int v, boolean[] visited, Stack<Integer> stack) {
		visited[v] = true;

		ListIterator<Integer> listIterator = adjList[v].listIterator();
		while (listIterator.hasNext()) {
			int next = listIterator.next();
			if (!visited[next]) topoSortUtil(adjList, next, visited, stack);
		}

		stack.push(v);
	}

	// Kahn’s algorithm for Topological Sorting or Topological Sorting using BFS
	/*	A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
		Proof: There’s a simple proof to the above fact is that a DAG does not contain a cycle which means that all paths will be of finite length.
		       Now let S be the longest path from u(source) to v(destination). Since S is the longest path there can be no incoming edge to u and 
		       no outgoing edge from v, if this situation had occurred then S would not have been the longest path
		=> indegree(u) = 0 and outdegree(v) = 0*/
	public void topoSortUsingIndegree(LinkedList<Integer>[] adjList, int n) {
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> linearOrder = new ArrayList<>();
		int[] indegree;
		int count = 0;

		// Step-1: Compute in-degree
		indegree = indegree(adjList, n);

		// Step-2: Pick all the vertices with in-degree as 0 and add them into a queue
		for (int i = 0; i < n; i++)
			if (indegree[i] == 0) queue.add(i);

		// Step-3:Remove a vertex from the queue
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			linearOrder.add(vertex);
			// 1.Increment count of visited nodes by 1.
			count++;

			// 2.Decrease in-degree by 1 for all its neighboring nodes
			ListIterator<Integer> iter = adjList[vertex].listIterator();
			while (iter.hasNext()) {
				int data = iter.next();
				if (--indegree[data] == 0) queue.add(data); // 3.If in-degree of a neighboring nodes is reduced to zero,
															// then add it to the queue.
			}
		}
		// Step-4:If count of visited nodes is equal to the number of nodes in the graph then print the topological sort
		if (count == n) {
			linearOrder.stream().forEach(i -> System.out.print(i + " "));
		} else {
			System.out.println("Graph is not an a DAG and also it contains a cycle.");
		}
	}

	private int[] indegree(LinkedList<Integer>[] adjList, int n) {
		int[] indegree = new int[n];

		for (int i = 0; i < n; i++) {
			if (adjList[i].size() > 0) {
				ListIterator<Integer> iterator = adjList[i].listIterator();
				while (iterator.hasNext())
					indegree[iterator.next()]++;
			}
		}
		return indegree;
	}

	// Course Schedule II: Using topo sort(but its reverse)
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses == 0) return null;
		// Convert graph presentation from edges to indegree of adjacent list.
		int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
		for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
			indegree[prerequisites[i][0]]++;

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++)
			if (indegree[i] == 0) {
				// Add the course to the order because it has no prerequisites.
				order[index++] = i;
				queue.offer(i);
			}

		// How many courses don't need prerequisites.
		while (!queue.isEmpty()) {
			int prerequisite = queue.poll(); // Already finished this prerequisite course.
			for (int i = 0; i < prerequisites.length; i++) {
				if (prerequisites[i][1] == prerequisite) {
					indegree[prerequisites[i][0]]--;
					if (indegree[prerequisites[i][0]] == 0) {
						// If indegree is zero, then add the course to the order.
						order[index++] = prerequisites[i][0];
						queue.offer(prerequisites[i][0]);
					}
				}
			}
		}

		return (index == numCourses) ? order : new int[0];
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

		topoSortUsingIndegree2(adjList, n);
	}

	// Kahn’s algorithm for Topological Sorting
	/*	A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
		Proof: There’s a simple proof to the above fact is that a DAG does not contain a cycle which means that all paths will be of finite length.
		       Now let S be the longest path from u(source) to v(destination). Since S is the longest path there can be no incoming edge to u and 
		       no outgoing edge from v, if this situation had occurred then S would not have been the longest path
		=> indegree(u) = 0 and outdegree(v) = 0*/
	public void topoSortUsingIndegree2(LinkedList<Character>[] adjList, int n) {
		Queue<Character> queue = new LinkedList<>();
		ArrayList<Character> linearOrder = new ArrayList<>();
		int[] indegree;
		int count = 0;

		// Step-1: Compute in-degree
		indegree = indegree2(adjList, n);

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

	private int[] indegree2(LinkedList<Character>[] adjList, int n) {
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
	 * Given a sorted dictionary of an alien language, find order of characters Given a sorted dictionary (array of words)
	 * of an alien language, find order of characters in the language.
	 * 
	 * Solution using Topological Sort - Rewrite this
	 */
	public void alienDictionary(String[] words, int n) {
		LinkedList<Integer>[] adjList = new LinkedList[n];

		// Build adjacency list
		for (int i = 0; i < n; i++)
			adjList[i] = new LinkedList<>();

		for (int i = 0; i < words.length - 1; i++) {
			String words1 = words[i];
			String words2 = words[i + 1];
			int j = 0;
			while (j < Math.min(words1.length(), words2.length())) {
				if (words1.charAt(j) != words2.charAt(j)) {
					adjList[words1.charAt(j) - 'a'].add(words2.charAt(j) - 'a');
					break;
				}
				j++;
			}
		}

		// Topological sort
		boolean[] visited = new boolean[n];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++)
			if (!visited[i]) topoSortUtil(adjList, i, visited, stack);

		while (!stack.isEmpty())
			System.out.print((char) (stack.pop() + 'a') + "-");
	}

	/******** Minimum Spanning Tree **********/
	/* A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected graph is a spanning tree with
	 * weight less than or equal to the weight of every other spanning tree. The weight of a spanning tree is the sum of weights given to 
	 * each edge of the spanning tree. A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.
	 */
	public void primsMST(int[][] adjMatrix, int n) {
		int[] edgeWeight = new int[n]; // Edge Weight from source vertex
		int[] parent = new int[n]; // Parent used to print the path
		boolean[] visited = new boolean[n];

		// Set max values in edgeWeight array
		for (int i = 0; i < n; i++)
			edgeWeight[i] = Integer.MAX_VALUE;

		edgeWeight[0] = 0;
		parent[0] = -1;

		for (int i = 0; i < n; i++) {
			// Find the minimum value index in the edgeWeight array
			int u = findMinWeight(edgeWeight, visited);
			visited[u] = true;

			for (int v = 0; v < n; v++) {
				if (adjMatrix[u][v] != 0 && !visited[v] && adjMatrix[u][v] < edgeWeight[v]) {
					parent[v] = u;
					edgeWeight[v] = adjMatrix[u][v];
				}
			}
		}

		printMST(parent, edgeWeight, n);
	}

	private int findMinWeight(int[] weight, boolean[] visited) {
		int index = -1, min = Integer.MAX_VALUE;
		for (int i = 0; i < weight.length; i++) {
			if (weight[i] < min && !visited[i]) {
				min = weight[i];
				index = i;
			}
		}
		return index;
	}

	private void printMST(int[] parent, int[] weight, int n) {
		System.out.println(" Edge " + " Weight ");
		for (int i = 1; i < n; i++) {
			System.out.println(i + "-" + parent[i] + "  " + weight[i]); // adjMatrix[i][parent[i]]
		}
	}

	public void KruskalsMST(EdgeNode[] edges, int n) {
		ArrayList<EdgeNode> result = new ArrayList<>();
		DisjointSet set = new DisjointSet(n);

		for (int i = 0; i < n; i++)
			set.parent[i] = i;

		Arrays.sort(edges, (u, v) -> u.weight - v.weight);

		for (EdgeNode edge : edges) {
			int parent1 = set.find(edge.src);
			int parent2 = set.find(edge.dest);

			if (parent1 == parent2) {
				continue;
			} else {
				result.add(edge);
				set.union(edge.src, edge.dest);
			}
		}

		for (EdgeNode edge : result)
			System.out.println(edge.src + " - " + edge.dest + "->" + edge.weight);
	}

	/******** BackTracking **********/
	// Refer Backtracking.java

	/******** Shortest Paths **********/
	// Shortest Path from one source to one destination
	// Approach1: BFS
	public boolean hasRoute1(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t) {
		if (graph == null || s == null || t == null) return false;

		Set<Integer> visited = new HashSet<>();
		Queue<DirectedGraphNode> queue = new LinkedList<>();

		queue.add(s);
		visited.add(s.label);

		while (!queue.isEmpty()) {
			DirectedGraphNode curr = queue.poll();
			if (curr == t) return true;
			for (DirectedGraphNode next : curr.neighbors) {
				if (!visited.contains(next.label)) {
					queue.add(next);
				}
			}
		}
		return false;
	}

	// Approach2: DFS
	public boolean hasRoute2(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t) {
		if (graph == null || s == null || t == null) return false;
		Set<Integer> visited = new HashSet<>();
		return dfs(graph, s, t, visited);
	}

	public boolean dfs(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t,
			Set<Integer> visited) {
		if (s == null) return false;
		if (s == t) return true;
		visited.add(s.label);

		for (DirectedGraphNode next : s.neighbors) {
			if (!visited.contains(next.label)) {
				if (dfs(graph, next, t, visited)) return true;
			}
		}
		return false;
	}

	// Similar to method1, but here it displays the path as well
	public boolean hasRoute3(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t) {
		if (graph == null || s == null || t == null) return false;

		Set<Integer> visited = new HashSet<>();
		Queue<DirectedGraphNode> queue = new LinkedList<>();
		Map<Integer, Integer> map = new HashMap<>();

		queue.add(s);
		visited.add(s.label);
		map.put(s.label, null);

		while (!queue.isEmpty()) {
			DirectedGraphNode curr = queue.poll();
			if (curr == t) {
				printPath(map, t.label);
				return true;
			}
			for (DirectedGraphNode next : curr.neighbors) {
				if (!visited.contains(next.label)) {
					map.put(next.label, curr.label);
					queue.add(next);
				}
			}
		}
		return false;
	}

	public void printPath(Map<Integer, Integer> map, int dest) {
		List<Integer> path = new ArrayList<>();
		Integer curr = dest;
		while (curr != null) {
			path.add(0, curr);
			curr = map.get(curr);
		}

		path.stream().forEach(k -> System.out.print(k + "->"));
	}

	// Shortest Path from one source to all the destinations
	// Approach1: Adjacency Matrix Representation; Time Complexity: O(n^2)
	public void dijkstraSP(int[][] adjMatrix, int n, int source) {
		int[] edgeWeight = new int[n]; // Edge Weight from source vertex
		boolean[] visited = new boolean[n];

		// Set max values in edgeWeight array
		Arrays.fill(edgeWeight, Integer.MAX_VALUE);

		edgeWeight[source] = 0;
		// parent[0] = -1;

		for (int i = 0; i < n; i++) {
			int u = findMinWeight(edgeWeight, visited); // Find the minimum value index in the edgeWeight array
			visited[u] = true;

			for (int v = 0; v < n; v++)
				if (adjMatrix[u][v] != 0 && !visited[v])
					edgeWeight[v] = Math.min(edgeWeight[v], edgeWeight[u] + adjMatrix[u][v]);
		}

		printSP(edgeWeight, n);
	}

	/* Approach2: Adjacency List Representation; Time Complexity: O(n^2);
	 * If we use Min Binary Heap, then time complexity will be O(nlogn);
	 */
	public void dijkstraSP(List<GraphNode>[] adjList, int n, int source) {
		if (adjList == null) return;

		int[] edgeWeight = new int[n]; // Edge Weight from source vertex (Use PriorityQueue to maintain the Edge Weight
										// from source vertex)
		boolean[] visited = new boolean[n];

		// Set max values in edgeWeight array
		Arrays.fill(edgeWeight, Integer.MAX_VALUE);

		edgeWeight[source] = 0;

		GraphNode adjNode;
		for (int i = 0; i < n; i++) {
			// Find the minimum value index in the edgeWeight array
			int minIndex = findMinWeight(edgeWeight, visited);
			if (minIndex == -1) continue;

			visited[minIndex] = true;
			ListIterator<GraphNode> iter = adjList[minIndex].listIterator();
			while (iter.hasNext()) {
				adjNode = iter.next();
				if (!visited[adjNode.vertex] && edgeWeight[minIndex] + adjNode.weight < edgeWeight[adjNode.vertex]) {
					edgeWeight[adjNode.vertex] = edgeWeight[minIndex] + adjNode.weight;
				}
			}
		}

		printSP(edgeWeight, n);
	}

	/*
	 * Dijkstra follows Greed Alg; Bellmanford alg follows DP algorithms and it finds shortest paths from src to all vertices in the given graph.
	 * Dijkstra doesn’t work for Graphs with negative weight edges, Bellman-Ford works for such graphs. Time complexity of Bellman-Ford is 
	 * O(VE), which is more than Dijkstra.
	 * 
	 * How does this work? Like other Dynamic Programming Problems, the algorithm calculate shortest paths in bottom-up manner. It first
	 * calculates the shortest distances which have at-most one edge in the path. Then, it calculates shortest paths with at-most 2 edges, 
	 * and so on. After the i-th iteration of outer loop, the shortest paths with at most i edges are calculated. There can be maximum 
	 * |V| – 1 edges in any simple path, that is why the outer loop runs |v| – 1 times.
	 */
	public void bellmanFordSP(EdgeNode[] edges, int n, int e, int source) {
		int[] edgeWeight = new int[n]; // Edge Weight from source vertex
		// int[] parent = new int[n];

		for (int i = 0; i < n; i++)
			edgeWeight[i] = Integer.MAX_VALUE;

		edgeWeight[source] = 0;

		for (int i = 1; i < n; i++) { // outer loop runs |v| – 1 times.
			for (int j = 0; j < e; j++) {
				int u = edges[j].src;
				int v = edges[j].dest;
				int w = edges[j].weight;
				if (edgeWeight[u] != Integer.MAX_VALUE) edgeWeight[v] = Math.min(edgeWeight[v], edgeWeight[u] + w);

				// Below logic is used to find the path from src to dest
				/*if (edgeWeight[u] != Integer.MAX_VALUE && edgeWeight[u] + w < edgeWeight[v]) {
					edgeWeight[v] = w + edgeWeight[u]; 
					parent[v] = u;
				}*/
			}
			System.out.println(Arrays.toString(edgeWeight));
		}

		// Try one more time same process, If graph has cycle, then edges weight keep decreases.
		for (int j = 0; j < e; j++) {
			int u = edges[j].src;
			int v = edges[j].dest;
			int w = edges[j].weight;
			if (edgeWeight[u] != Integer.MAX_VALUE && edgeWeight[u] + w < edgeWeight[v])
				System.out.println("Graph contains negative weight cycle");
		}

		printSP(edgeWeight, n);

	}

	/*
	 * The Floyd Warshall Algorithm (Dynamic Programming) is for solving the All Pairs Shortest Path problem. The problem is 
	 * to find shortest distances between every pair of vertices in a given edge weighted directed Graph.
	 */
	public void floydWarshallSP(int[][] adjMatrix, int n) {
		int[][] dist = new int[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				dist[i][j] = adjMatrix[i][j];

		for (int v = 0; v < n; v++) // Via each vertex/node one by one
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (dist[i][v] != Integer.MAX_VALUE && dist[v][j] != Integer.MAX_VALUE)
						dist[i][j] = Math.min(dist[i][j], dist[i][v] + dist[v][j]);

		printSolution(dist, n);
	}

	void printSolution(int dist[][], int n) {
		System.out.println("Following matrix shows the shortest distances between every pair of vertices");
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (dist[i][j] == Integer.MAX_VALUE) System.out.print("INF ");
				else System.out.print(dist[i][j] + "   ");
			}
			System.out.println();
		}
	}

	private void printSP(int[] weight, int n) {
		System.out.println("Shortest path from source vertex to all the vertex");
		System.out.println(" Vertex " + "  Distance/Weight");
		for (int i = 0; i < n; i++) {
			System.out.println("    " + i + "   -    " + weight[i]);
		}
	}

	/*
	 * Evaluate Division:Equations are given in the format A / B = k, where A and B are variables represented as
	 * strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer
	 * does not exist, return -1.0. 
	 * Example: Given a / b = 2.0, b / c = 3.0. queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
	 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
	 */
	// Approach1: Using Floyd Warshall Algorithm
	public double[] calcEquation1(String[][] equations, double[] values, String[][] queries) {
		Map<String, Integer> map = new HashMap<>();
		// Find no of nodes and map it into numeric indices
		int n = 0;
		for (String[] eqn : equations)
			for (String str : eqn)
				if (!map.containsKey(str)) map.put(str, n++);

		// Adj Matrix Rep: Initialize the table
		double[][] table = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				table[i][j] = (i == j) ? 1d : -1d;

		// Fill the values
		for (int i = 0; i < equations.length; i++) {
			int r = map.get(equations[i][0]), c = map.get(equations[i][1]);
			table[r][c] = values[i];
			table[c][r] = 1 / values[i];
		}

		// Apply Flogy warshall alg
		for (int v = 0; v < n; v++) // Check for each vertex
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (table[i][v] >= 0d && table[v][j] >= 0d) table[i][j] = table[i][v] * table[v][j];

		// Query the different indices
		double[] result = new double[queries.length];
		for (int i = 0; i < queries.length; i++) {
			String[] query = queries[i];
			if (!map.containsKey(query[0]) || !map.containsKey(query[1])) result[i] = -1d;
			else result[i] = table[map.get(query[0])][map.get(query[1])];
		}
		return result;
	}

	// Approach2: Using DFS
	public double[] calcEquation2(String[][] equations, double[] values, String[][] query) {
		double[] result = new double[query.length];
		// filter unexpected words
		Set<String> words = new HashSet<>();
		for (String[] strs : equations) {
			words.add(strs[0]);
			words.add(strs[1]);
		}
		for (int i = 0; i < query.length; ++i) {
			String[] keys = query[i];
			if (!words.contains(keys[0]) || !words.contains(keys[1])) result[i] = -1.0d;
			else {
				Stack<Integer> stack = new Stack<>();
				result[i] = helper(equations, values, keys, stack);
			}
		}
		return result;
	}

	public double helper(String[][] equations, double[] values, String[] query, Stack<Integer> stack) {
		String from, to;
		// look up equations directly
		for (int i = 0; i < equations.length; ++i) {
			from = equations[i][0];
			to = equations[i][1];
			if (from.equals(query[0]) && to.equals(query[1])) return values[i];
			if (from.equals(query[1]) && to.equals(query[0])) return 1 / values[i];
		}
		// lookup equations by other equations
		for (int i = 0; i < equations.length; ++i) {
			from = equations[i][0];
			to = equations[i][1];
			if (!stack.contains(i) && query[0].equals(from)) { // From value matches, multiply with curr value
				stack.push(i);
				double temp = values[i] * helper(equations, values, new String[] { to, query[1] }, stack);
				if (temp > 0) return temp;
				else stack.pop();
			}
			if (!stack.contains(i) && query[0].equals(to)) {// To value matches, divide with curr value
				stack.push(i);
				double temp = helper(equations, values, new String[] { from, query[1] }, stack) / values[i];
				if (temp > 0) return temp;
				else stack.pop();
			}
		}
		return -1.0d;
	}

	/******** Connectivity **********/
	public void ConnectivityInDirectedGraph() {
		// Connectivity in a directed graph
	}

	/******** Hard Problems **********/
	// Travelling Salesman Problem
	public void tspMST() {

	}

	// TSP using Dynamic Programming(DP)
	public void tspDP() {

	}

	/******** Maximum Flow **********/
	public void fordfulkersonAlgorithm() {

	}

	/******** Misc Problems **********/
	/*Snake and Ladder Problem:
	 * The idea is to consider the given snake and ladder board as a directed graph with number of vertices equal to the number of cells 
	 * in the board. The problem reduces to finding the shortest path in a graph. Every vertex of the graph has an edge to next six
	 * vertices if next 6 vertices do not have a snake or ladder. If any of the next six vertices has a snake or ladder, then the edge 
	 * from current vertex goes to the top of the ladder or tail of the snake. Since all edges are of equal weight, we can efficiently 
	 * find shortest path using Breadth First Search of the graph.
	 */
	public int findMinDiceThrows(int[] moves, int n) {
		boolean[] visted = new boolean[n];
		Queue<GraphNode> queue = new LinkedList<>();
		queue.add(new GraphNode(0, 0)); // vertex no, weight or dist of this vertex from source
		visted[0] = true;
		GraphNode curr = null, adjNode = null;
		while (!queue.isEmpty()) {
			curr = queue.poll();
			int v = curr.vertex;
			if (v == n - 1) return curr.weight;

			for (int i = v + 1; (i <= v + 6 && i < n); i++) {
				adjNode = new GraphNode();
				if (!visted[i]) {
					visted[i] = true;
					// update the vertex & dist from source
					adjNode.vertex = moves[i] != -1 ? moves[i] : i;
					adjNode.weight = curr.weight + 1;
					queue.add(adjNode);
				}
			}

		}
		return -1;
	}

	// Reconstruct Itinerary
	public List<String> findItinerary(String[][] tickets) {
		Map<String, PriorityQueue<String>> map = new HashMap<>();
		for (String[] ticket : tickets) {
			// Check the source city is added in map
			if (map.get(ticket[0]) == null) map.put(ticket[0], new PriorityQueue<>());
			// Add the dest in queue and put into map
			map.get(ticket[0]).add(ticket[1]);
		}

		// Use LinkedList to add the data in the front
		LinkedList<String> result = new LinkedList<>();
		reconstructItinerary("JFK", map, result);
		return result;
	}

	// Using DFS, but remove the visted data from the priority queue
	public void reconstructItinerary(String s, Map<String, PriorityQueue<String>> map, LinkedList<String> result) {
		PriorityQueue<String> queue = map.get(s);

		while (queue != null && !queue.isEmpty())
			reconstructItinerary(queue.poll(), map, result);

		result.addFirst(s);
	}

	// Minimum Height Trees
	/*
	Ref: https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
	some statement for tree in graph theory:
	  (1) A tree is an undirected graph in which any two vertices are connected by exactly one path.
	  (2) Any connected graph who has n nodes with n-1 edges is a tree.
	  (3) The degree of a vertex of a graph is the number of edges incident to the vertex.
	  (4) A leaf is a vertex of degree 1. An internal vertex is a vertex of degree at least 2.
	  (5) A path graph is a tree with two or more vertices that is not branched at all.
	  (6) A tree is called a rooted tree if one vertex has been designated the root.
	  (7) The height of a rooted tree is the number of edges on the longest downward path between root and a leaf.
	
	Solution:(implementation is similar to the "BFS topological sort"): remove nodes from leave to root.     
	  1.We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers move the same speed. When two
	    pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find the roots. 
	  2. It is easy to see that the last two pointers are from the two ends of the longest path in the graph.
	  3. Remove the leaves, update the degrees of inner vertexes. Then remove the new leaves. Doing so level by level until there 
	     are 2 or 1 nodes left.
	  The time complexity and space complexity are both O(n). Note that for a tree we always have V = n, E = n-1.        
	*/
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Integer> leaves = new ArrayList<>();
		if (n == 1) {
			leaves.add(0);
			return leaves;
		}

		if (n == 0 || edges.length == 0 || edges[0].length == 0) return leaves;
		// Build adjList
		List<Integer>[] adjList = buildAdjListUndirectedGraph(n, edges);

		// Add leaves in the list
		for (int i = 0; i < n; i++)
			if (adjList[i].size() == 1) leaves.add(i);

		// Remove the leaves, update the degrees of inner vertexes. Then remove the new leaves.
		// Doing so level by level until there are 2 or 1 nodes left.
		List<Integer> newLeaves;
		while (n > 2) {
			n -= leaves.size();
			newLeaves = new ArrayList<>();
			for (Integer node : leaves) {
				int neighbor = adjList[node].iterator().next();
				adjList[neighbor].remove(node);
				if (adjList[neighbor].size() == 1) newLeaves.add(neighbor);
			}
			leaves = newLeaves;
		}

		return leaves;
	}

	// Same Solution, but Graph represented using Set instead of array
	public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
		if (n == 1) return Collections.singletonList(0);

		List<Set<Integer>> adj = new ArrayList<>(n);
		for (int i = 0; i < n; ++i)
			adj.add(new HashSet<>());
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}

		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; ++i)
			if (adj.get(i).size() == 1) leaves.add(i);

		while (n > 2) {
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for (int i : leaves) {
				int j = adj.get(i).iterator().next();
				adj.get(j).remove(i);
				if (adj.get(j).size() == 1) newLeaves.add(j);
			}
			leaves = newLeaves;
		}
		return leaves;
	}

	/* Word Ladder:
	 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence 
	 * from beginWord to endWord, such that:
	 *    Only one letter can be changed at a time.
	 *    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
	 */
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		LinkedList<WordNode> queue = new LinkedList<>();
		queue.add(new WordNode(beginWord, 1));
		Set<String> wordDict = new HashSet<>(wordList);

		while (!queue.isEmpty()) {
			WordNode top = queue.remove();

			if (top.word.equals(endWord)) return top.count;
			char[] arr = top.word.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char temp = arr[i];
					if (arr[i] != c) arr[i] = c;
					String newStr = new String(arr);
					if (wordDict.contains(newStr)) {
						queue.add(new WordNode(newStr, top.count + 1));
						wordDict.remove((Object) newStr);
					}
					arr[i] = temp;
				}
			}
		}
		return 0;
	}

	/* Word Ladder II:
	 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation
	 * sequence(s) from beginWord to endWord,
	 */
	public List<List<String>> findLadders(String start, String end, List<String> dict) {
		List<List<String>> result = new ArrayList<List<String>>();

		LinkedList<WordNode> queue = new LinkedList<WordNode>();
		queue.add(new WordNode(start, 1, null));

		// dict.add(end);

		int minStep = 0;

		HashSet<String> visited = new HashSet<String>();
		HashSet<String> unvisited = new HashSet<String>();
		unvisited.addAll(dict);

		int preNumSteps = 0;

		while (!queue.isEmpty()) {
			WordNode top = queue.remove();
			String word = top.word;
			int currNumSteps = top.count;

			if (word.equals(end)) {
				if (minStep == 0) minStep = top.count;

				if (top.count == minStep && minStep != 0) {
					// nothing
					ArrayList<String> t = new ArrayList<String>();
					t.add(top.word);
					while (top.prev != null) {
						t.add(0, top.prev.word);
						top = top.prev;
					}
					result.add(t);
					continue;
				}

			}

			// Why this???
			if (preNumSteps < currNumSteps) unvisited.removeAll(visited);

			preNumSteps = currNumSteps;

			char[] arr = word.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char temp = arr[i];
					if (arr[i] != c) {
						arr[i] = c;
					}

					String newWord = new String(arr);
					if (unvisited.contains(newWord)) {
						queue.add(new WordNode(newWord, top.count + 1, top));
						visited.add(newWord);
					}

					arr[i] = temp;
				}
			}
		}

		return result;
	}

	/*
	 * String Deletion: Given a String and dictionary hashset, write a function to determine the minimum no
	 * of characters to delete to make a word.
	 * Eg: dict = {a, aa, aaa}; 
	 * 		query = abc, o/p: 2; query: aac, o/p: 1;
	 */
	// Solution: BFS; Time-O(n!) because substring Time: n * n-1 * n-2... 1)
	public int stringDeletion(String query, HashSet<String> dict) {
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.add(query);
		while (!queue.isEmpty()) {
			String top = queue.poll();
			if (dict.contains(top)) return query.length() - top.length();
			// Check for all the substring
			for (int i = 0; i < top.length(); i++) {
				String subStr = top.substring(0, i) + top.substring(i + 1, top.length());
				if (subStr.length() > 0 && !visited.contains(subStr)) {
					visited.add(subStr);
					queue.add(subStr);
				}
			}
		}
		return -1;
	}
}

// Write a code for below

/*public interface Graph {

    //Mutators
    
    public void    clear        ();
    
    public Graph   addNode      (String nodeName);
    public Graph   addEdge      (String origin, String destination, Object value);
    public Graph   addEdge      (Edge edge, Object edgeValue);
    
    public Graph   removeNode   (String nodeName);
    public Graph   removeEdge   (String origin, String destination);
    public Graph   removeEdge   (Edge edge);
    
    public Graph   load         (TypedBufferReader input , char tokenSeparator);
    public void    write        (TypedBufferWriter output, char tokenSeparator);


    //Accessors
    
    public EdgeValueIO getEdgeValueIO ();
  	
    public int     getNodeCount ();
    public int     getEdgeCount ();

    public boolean hasNode      (String nodeName);
    public boolean hasEdge      (String origin, String destination);
    public boolean hasEdge      (Edge edge);

    public Object  getEdgeValue (String origin, String destination);
    public Object  getEdgeValue (Edge edge);
    
    public int     inDegree     (String nodeName);
    public int     outDegree    (String nodeName);
    public int     degree       (String nodeName);
    
    //The returned sets are all unmodifiable
    public Set     getAllNodes ();
    public Set     getAllEdges ();
    
    public Set     getOutNodes  (String nodeName);
    public Set     getInNodes   (String nodeName);
    public Set     getOutEdges  (String nodeName);
    public Set     getInEdges   (String nodeName);
    
    
    //Inner interface
    public interface Edge {
      public String getOrigin();
      public String getDestination();
      public Object getValue();
    }
  }*/

class WordNode {
	public String	word;
	public int		count;
	WordNode		prev;

	public WordNode(String w, int c, WordNode prev) {
		this.word = w;
		this.count = c;
		this.prev = prev;
	}

	public WordNode(String w, int c) {
		this.word = w;
		this.count = c;
	}
}

// Definition for Directed graph.
class DirectedGraphNode {
	int								label;
	ArrayList<DirectedGraphNode>	neighbors;

	DirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}
}

class Pair {
	public int	key;
	public int	val;

	public Pair(int k, int v) {
		this.key = k;
		this.val = v;
	}
}

class GraphNode2 {
	public int	vertex;
	public long	color;
	public int	dist;	// Distance from starting node

	public GraphNode2(int vertex, long color, int dist) {
		this.vertex = vertex;
		this.color = color;
		this.dist = dist;
	}
}