package com.basic.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import com.common.model.EdgeNode;
import com.common.model.GraphNode;
import com.common.utilities.DisjointSet;

/* 
 * Graph Representation: 
 * 		- Adjacency Matrix Representation
 * 		- Adjacency List Representation - Using LL array, Using Map
 * 		- Edge List Representation
 * 	    - Incidence Matrix Representation
 */
public class GraphImpl {
}

interface Graph {
	// DG - Directed Graph
	// UG - Undirected Graph
	public void buildDirectedGraph(int[][] edges);

	public void buildUndirectedGraph(int[][] edges);

	public void buildWeightedDG(int[][] edges);

	public void buildWeightedUG(int[][] edges);

	public int findNumberOfNodes(int[][] edges);

	public void printGraph();

	public void dfsRecursive(int source);

	// DFS Iterative Approach using Stack
	public void dfsIterative(int source);

	public int dfsDisconnectedGraph();

	// BFS Iterative Approach using Queue
	public void bfsIterative(int source);

	public int bfsDisconnectedGraph();

	/* Topological Sort:
	 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for "every
	 * directed edge uv, vertex u comes before v in the ordering". Topological Sorting for a graph is not possible if the
	 * graph is not a DAG.
	 * Fact: A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
	 *   Approach1: DFS Algorithm
	 *   Approach2: BFS Algorithm (Kahn's Algorithm)
	 */
	public void topologicalSortDfs();

	/* BFS Algorithm (Kahn's Algorithm) for Topological Sorting:
	 * A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
	 */
	public void topologicalSortBfs();

	public boolean detectCycleInDG();

	public boolean detectCycleInUG();

	// MST - Minimum Spanning Tree
	/* A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected graph is a spanning tree with
	 * weight less than or equal to the weight of every other spanning tree. The weight of a spanning tree is the sum of weights given to 
	 * each edge of the spanning tree. A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.
	 */
	public void mstPrimsAlg();

	public void mstKruskalsAlg();

	// SP - Shortest Path Alg
	public void spDijikstraAlg(int source);

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
	public void spBellmanFordAlg(int source);

	/* The Floyd Warshall Algorithm (Dynamic Programming) is for solving the All Pairs Shortest Path problem. The problem is 
	 * to find shortest distances between every pair of vertices in a given edge weighted directed Graph.
	 */
	public void spFloydWarshallAlg();
}

/**
 * Adjacency Matrix representation: A matrix indicating with each vertex associated with a row and column.
 */
class GraphAdjMatrix implements Graph {
	int N;
	int[][] adjMatrix;

	@Override
	public void buildDirectedGraph(int[][] edges) {
		N = findNumberOfNodes(edges);
		adjMatrix = new int[N][N];
		for (int[] edge : edges) {
			adjMatrix[edge[0]][edge[1]] = 1;
		}
	}

	@Override
	public void buildUndirectedGraph(int[][] edges) {
		N = findNumberOfNodes(edges);
		adjMatrix = new int[N][N];
		for (int[] edge : edges) {
			adjMatrix[edge[0]][edge[1]] = 1;
			adjMatrix[edge[1]][edge[0]] = 1;
		}
	}

	@Override
	public void buildWeightedDG(int[][] edges) {
		N = findNumberOfNodes(edges);
		adjMatrix = new int[N][N];
		for (int[] edge : edges) {
			adjMatrix[edge[0]][edge[1]] = edge[2];
		}
	}

	@Override
	public void buildWeightedUG(int[][] edges) {
		N = findNumberOfNodes(edges);
		adjMatrix = new int[N][N];
		for (int[] edge : edges) {
			adjMatrix[edge[0]][edge[1]] = edge[2];
			adjMatrix[edge[1]][edge[0]] = edge[2];
		}
	}

	// If input is given as direct matrix
	public void buildDirectedGraphUsingMatrix(int[][] matrix) {
		this.N = matrix.length;
		this.adjMatrix = matrix;
	}

	@Override
	public int findNumberOfNodes(int[][] edges) {
		Set<Integer> set = CommonUtil.findNumberOfNodes(edges);
		return set.size();
	}

	@Override
	public void printGraph() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public void dfsRecursive(int source) {
		if (adjMatrix.length == 0 || adjMatrix[0].length == 0)
			return;

		boolean[] visited = new boolean[N];
		dfsUtil(adjMatrix, visited, N);
	}

	private void dfsUtil(int[][] adjMatrix, boolean[] visited, int s) {
		int n = adjMatrix.length;
		visited[s] = true;
		System.out.print(s + "-");
		for (int i = 0; i < n; i++) {
			if (!visited[i] && i != s && adjMatrix[s][i] == 1) {
				dfsUtil(adjMatrix, visited, i);
			}
		}
	}

	@Override
	public void dfsIterative(int source) {

	}

	@Override
	public int dfsDisconnectedGraph() {
		if (adjMatrix.length == 0)
			return 0;

		int groups = 0;
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				groups++;
				dfsUtil(adjMatrix, visited, i);
			}
		}

		return groups;
	}

	@Override
	public void bfsIterative(int source) {
		if (adjMatrix.length == 0 || adjMatrix[0].length == 0)
			return;
		boolean[] visited = new boolean[N];
		bfsUtil(adjMatrix, visited, source);
	}

	private void bfsUtil(int[][] adjMatrix, boolean[] visited, int s) {
		int n = adjMatrix.length;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		while (!queue.isEmpty()) {
			int top = queue.poll();
			visited[top] = true;
			System.out.print(top + "-");
			for (int j = 0; j < n; j++) {
				if (!visited[j] && j != s && adjMatrix[top][j] == 1)
					queue.add(j);
			}
		}
	}

	@Override
	public int bfsDisconnectedGraph() {
		if (adjMatrix.length == 0)
			return 0;

		int groups = 0;
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				groups++;
				bfsUtil(adjMatrix, visited, i);
			}
		}
		return groups;
	}

	@Override
	public void topologicalSortDfs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void topologicalSortBfs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean detectCycleInDG() {
		return false;
	}

	@Override
	public boolean detectCycleInUG() {
		return false;
	}

	@Override
	public void mstPrimsAlg() {
		int[] edgeWeight = new int[N]; // Edge Weight from source vertex
		int[] parent = new int[N]; // Parent used to print the path
		boolean[] visited = new boolean[N];

		// Set max values in edgeWeight array
		for (int i = 0; i < N; i++)
			edgeWeight[i] = Integer.MAX_VALUE;

		edgeWeight[0] = 0;
		parent[0] = -1;

		for (int i = 0; i < N; i++) {
			// Find the minimum value index in the edgeWeight array
			int u = findMinWeight(edgeWeight, visited);
			visited[u] = true;

			for (int v = 0; v < N; v++) {
				if (adjMatrix[u][v] != 0 && !visited[v] && adjMatrix[u][v] < edgeWeight[v]) {
					parent[v] = u;
					edgeWeight[v] = adjMatrix[u][v];
				}
			}
		}

		printMST(parent, edgeWeight, N);
	}

	@Override
	public void mstKruskalsAlg() {
		// TODO Auto-generated method stub

	}

	// Time Complexity: O(n^2)
	@Override
	public void spDijikstraAlg(int source) {
		int[] edgeWeight = new int[N]; // Edge Weight from source vertex
		boolean[] visited = new boolean[N];

		// Set max values in edgeWeight array
		Arrays.fill(edgeWeight, Integer.MAX_VALUE);

		edgeWeight[source] = 0;
		// parent[0] = -1;

		for (int i = 0; i < N; i++) {
			int u = findMinWeight(edgeWeight, visited); // Find the minimum value index in the edgeWeight array
			visited[u] = true;

			for (int v = 0; v < N; v++)
				if (adjMatrix[u][v] != 0 && !visited[v])
					edgeWeight[v] = Math.min(edgeWeight[v], edgeWeight[u] + adjMatrix[u][v]);
		}

		printSP(edgeWeight, N);
	}

	@Override
	public void spBellmanFordAlg(int source) {

	}

	@Override
	public void spFloydWarshallAlg() {
		int[][] dist = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				dist[i][j] = adjMatrix[i][j];

		for (int v = 0; v < N; v++) // Via each vertex/node one by one
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (dist[i][v] != Integer.MAX_VALUE && dist[v][j] != Integer.MAX_VALUE)
						dist[i][j] = Math.min(dist[i][j], dist[i][v] + dist[v][j]);

		printfloydWarshallSP(dist, N);
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

	private void printfloydWarshallSP(int dist[][], int n) {
		System.out.println("Following matrix shows the shortest distances between every pair of vertices");
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (dist[i][j] == Integer.MAX_VALUE)
					System.out.print("INF ");
				else
					System.out.print(dist[i][j] + "   ");
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

	public static void main(String[] args) {
		GraphAdjMatrix graph = new GraphAdjMatrix();
		CommonUtil.testGraph(graph);
	}
}

/**
 * Adjacency List representation:Container of vertices, and each vertex has a list of adjacent vertices
 */
class GraphAdjList implements Graph {
	int N;
	LinkedList<Integer>[] adjList;
	LinkedList<GraphNode>[] adjListW;

	@Override
	public void buildDirectedGraph(int[][] edges) {
		this.N = findNumberOfNodes(edges);
		// Create the instance for each node
		adjList = new LinkedList[N];
		for (int i = 0; i < N; i++)
			adjList[i] = new LinkedList<>();

		// Add edges in the adjList
		for (int[] edge : edges)
			adjList[edge[0]].add(edge[1]);

	}

	@Override
	public void buildUndirectedGraph(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildWeightedDG(int[][] edges) {
		this.N = findNumberOfNodes(edges);
		// Create the instance for each node
		adjListW = new LinkedList[N];
		for (int i = 0; i < N; i++)
			adjListW[i] = new LinkedList<>();

		// Add edges in the adjList
		for (int[] edge : edges)
			adjListW[edge[0]].add(new GraphNode(edge[1], edge[2]));
	}

	@Override
	public void buildWeightedUG(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public int findNumberOfNodes(int[][] edges) {
		Set<Integer> set = CommonUtil.findNumberOfNodes(edges);
		return set.size();
	}

	@Override
	public void printGraph() {
		for (int i = 0; i < N; i++) {
			System.out.println("\nEdges from Vertex: " + i + "->");
			ListIterator<Integer> iterator = adjList[i].listIterator();
			while (iterator.hasNext())
				System.out.print(iterator.next() + ", ");
		}
	}

	@Override
	public void dfsRecursive(int source) {
		boolean[] visited = new boolean[N];
		dfs1(source, visited);
	}

	// Recursive Approach
	public void dfs1(int v, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + "-");
		ListIterator<Integer> listIterator = adjList[v].listIterator();
		while (listIterator.hasNext()) {
			int data = listIterator.next();
			if (!visited[data]) {
				dfs1(data, visited);
			}
		}
	}

	@Override
	public void dfsIterative(int source) {
		boolean[] visited = new boolean[N];
		// Iterative Approach
		dfs2(source, visited);

	}

	public void dfs2(int s, boolean[] visited) {
		Stack<Integer> stack = new Stack<>();
		visited[s] = true;
		stack.push(s);
		while (!stack.isEmpty()) {
			int data = stack.pop();
			System.out.print(data + "-");
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

	@Override
	public int dfsDisconnectedGraph() {
		// TODO Auto-generated method stub
		return 0;
	}

	// DFS Algorithm to traverse the graph and display the nodes
	public void dfs(int s) {

	}

	@Override
	public void bfsIterative(int source) {
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		visited[source] = true;
		while (!queue.isEmpty()) {
			int data = queue.poll();
			System.out.print(data + "-");
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

	@Override
	public int bfsDisconnectedGraph() {
		return 0;
	}

	// TODO: Topological Sort to order the graph -> Rewirte this, because below solution doesnt find cycle in graph
	@Override
	public void topologicalSortDfs() {
		boolean[] visited = new boolean[N];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < N; i++)
			if (!visited[i]) {
				topoSortUtil(i, visited, stack);
			}

		while (!stack.isEmpty())
			System.out.print(stack.pop() + "-");
	}

	private void topoSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
		visited[v] = true;
		ListIterator<Integer> listIterator = adjList[v].listIterator();
		while (listIterator.hasNext()) {
			int next = listIterator.next();
			if (!visited[next])
				topoSortUtil(next, visited, stack);
		}
		stack.push(v);
	}

	@Override
	public void topologicalSortBfs() {
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> linearOrder = new ArrayList<>();
		int[] indegree;
		int count = 0;

		// Step-1: Compute in-degree
		indegree = indegree(adjList, N);

		// Step-2: Pick all the vertices with in-degree as 0 and add them into a queue
		for (int i = 0; i < N; i++)
			if (indegree[i] == 0)
				queue.add(i);

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
				if (--indegree[data] == 0)
					queue.add(data); // 3.If in-degree of a neighboring nodes is reduced to zero,
														// then add it to the queue.
			}
		}
		// Step-4:If count of visited nodes is equal to the number of nodes in the graph then print the topological sort
		if (count == N) {
			linearOrder.forEach(i -> System.out.print(i + " "));
		} else {
			System.out.println("Graph is not an a DAG and also it contains a cycle.");
		}
	}

	@Override
	public boolean detectCycleInDG() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean detectCycleInUG() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mstPrimsAlg() {
		int[] edgeWeight = new int[N]; // Edge Weight from source vertex
		boolean[] visited = new boolean[N];
		ResultSet[] resultSet = new ResultSet[N];

		// Set max values in edgeWeight array
		Arrays.fill(edgeWeight, Integer.MAX_VALUE);

		edgeWeight[0] = 0;

		java.util.PriorityQueue<GraphNode> queue = new java.util.PriorityQueue<>((a, b) -> a.weight - b.weight);
		queue.add(new GraphNode(0, 0));
		resultSet[0] = new ResultSet();
		resultSet[0].parent = -1;

		while (!queue.isEmpty()) {
			GraphNode src = queue.poll();
			visited[src.vertex] = true;
			Iterator<GraphNode> iter = adjListW[src.vertex].iterator();
			if (iter == null)
				continue;
			while (iter.hasNext()) {
				GraphNode adjNode = iter.next();
				if (!visited[adjNode.vertex]) {
					edgeWeight[adjNode.vertex] = Math.min(edgeWeight[adjNode.vertex], edgeWeight[src.vertex] + adjNode.weight);
					queue.add(new GraphNode(adjNode.vertex, edgeWeight[adjNode.vertex]));
				}
			}
		}

		printSP(edgeWeight, N);

	}

	@Override
	public void mstKruskalsAlg() {
		// TODO Auto-generated method stub

	}

	// Time Complexity: O(V+E)
	@Override
	public void spDijikstraAlg(int source) {
		int[] edgeWeight = new int[N]; // Edge Weight from source vertex
		boolean[] visited = new boolean[N];

		// Set max values in edgeWeight array
		Arrays.fill(edgeWeight, Integer.MAX_VALUE);

		edgeWeight[source] = 0;

		java.util.PriorityQueue<GraphNode> queue = new java.util.PriorityQueue<>((a, b) -> a.weight - b.weight);
		queue.add(new GraphNode(source, 0));

		while (!queue.isEmpty()) {
			GraphNode src = queue.poll();
			visited[src.vertex] = true;
			Iterator<GraphNode> iter = adjListW[src.vertex].iterator();
			if (iter == null)
				continue;
			while (iter.hasNext()) {
				GraphNode adjNode = iter.next();
				if (!visited[adjNode.vertex]) {
					edgeWeight[adjNode.vertex] = Math.min(edgeWeight[adjNode.vertex], edgeWeight[src.vertex] + adjNode.weight);
					queue.add(new GraphNode(adjNode.vertex, edgeWeight[adjNode.vertex]));
				}
			}
		}

		printSP(edgeWeight, N);
	}

	@Override
	public void spBellmanFordAlg(int source) {
		// TODO Auto-generated method stub
	}

	@Override
	public void spFloydWarshallAlg() {
		// TODO Auto-generated method stub
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

	private void printSP(int[] weight, int n) {
		System.out.println("Shortest path from source vertex to all the vertex");
		System.out.println(" Vertex " + "  Distance/Weight");
		for (int i = 0; i < n; i++) {
			System.out.println("    " + i + "   -    " + weight[i]);
		}
	}

	public static void main(String[] args) {
		Graph graph = new GraphAdjList();
		CommonUtil.testGraph(graph);
	}

}

class GraphAdjListUsingMap implements Graph {

	int N;
	/* Map: Key-> Node or Vertex; It can be Integer, Character, String or any custom object
	 * Map: Value -> Value should be Collection. Collection such as List, Set(To avoid duplicate),
	 * 	PriorityQueue(To maintain any order), Head Node of Linked List(Sample: cloneGraph prob)
	 */
	Map<Integer, List<Integer>> adjMap;
	// Map<Integer, Set<Integer>> adjMap;
	// Map<Integer, PriorityQueue<Integer>> adjMap;

	Map<Integer, List<Integer>> adjMapW;

	List<Integer> vertices;

	@Override
	public void buildDirectedGraph(int[][] edges) {
		// Create the instance for each node
		adjMap = new HashMap<>();

		// Add edges in the adjList
		for (int[] edge : edges) {
			if (!adjMap.containsKey(edge[0]))
				adjMap.put(edge[0], new ArrayList<>());
			adjMap.get(edge[0]).add(edge[1]);
		}
	}

	@Override
	public void buildUndirectedGraph(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildWeightedDG(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildWeightedUG(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public int findNumberOfNodes(int[][] edges) {
		Set<Integer> set = CommonUtil.findNumberOfNodes(edges);
		vertices = new ArrayList<Integer>(set);
		return set.size();
	}

	@Override
	public void printGraph() {
		for (int i = 0; i < N; i++) {
			System.out.println("\nEdges from Vertex: " + i + "->");
			if (adjMap.get(i) != null)
				adjMap.get(i).forEach(k -> System.out.print(k + " "));
		}
	}

	@Override
	public void dfsRecursive(int source) {
		boolean[] visited = new boolean[N];
		// Recursive Approach
		dfs1(source, visited);
	}

	// Recursive Approach
	private void dfs1(int v, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + "-");
		if (adjMap.get(v) == null)
			return;
		for (int adjNode : adjMap.get(v)) {
			if (!visited[adjNode])
				dfs1(adjNode, visited);
		}
	}

	@Override
	public void dfsIterative(int source) {
		boolean[] visited = new boolean[N];
		// Iterative Approach
		dfs2(source, visited);
	}

	private void dfs2(int s, boolean[] visited) {
		Stack<Integer> stack = new Stack<>();
		visited[s] = true;
		stack.push(s);
		while (!stack.isEmpty()) {
			int v = stack.pop();
			System.out.print(v + "-");
			if (adjMap.get(v) == null)
				continue;
			for (int adjNode : adjMap.get(v)) {
				if (visited[adjNode])
					continue;
				visited[adjNode] = true;
				stack.push(adjNode);
			}
		}
	}

	@Override
	public int dfsDisconnectedGraph() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void bfsIterative(int source) {
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		visited[source] = true;
		while (!queue.isEmpty()) {
			int v = queue.poll();
			System.out.print(v + "-");
			if (adjMap.get(v) == null)
				continue;
			for (int adjNode : adjMap.get(v)) {
				if (!visited[adjNode]) {
					visited[adjNode] = true;
					queue.add(adjNode);
				}
			}
		}
	}

	@Override
	public int bfsDisconnectedGraph() {
		// TODO Auto-generated method stub
		return 0;
	}

	// TODO: Topological Sort to order the graph -> Rewirte this, because below solution doesnt find cycle in graph
	@Override
	public void topologicalSortDfs() {
		boolean[] visited = new boolean[N];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < N; i++)
			if (!visited[i]) {
				topoSortUtil(i, visited, stack);
			}

		while (!stack.isEmpty())
			System.out.print(stack.pop() + "-");
	}

	private void topoSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
		visited[v] = true;
		if (adjMap.get(v) != null) {
			for (int adjNode : adjMap.get(v)) {
				if (!visited[adjNode])
					topoSortUtil(adjNode, visited, stack);
			}
		}

		stack.push(v);
	}

	@Override
	public void topologicalSortBfs() {
		// If any specific ordering is required for Problem, use PriorityQueue
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> linearOrder = new ArrayList<>();
		int[] indegree;
		int count = 0;

		// Step-1: Compute in-degree
		indegree = indegree(adjMap);

		// Step-2: Pick all the vertices with in-degree as 0 and add them into a queue
		for (int i = 0; i < N; i++)
			if (indegree[i] == 0)
				queue.add(i);

		// Step-3:Remove a vertex from the queue
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			linearOrder.add(vertex);
			// 1.Increment count of visited nodes by 1.
			count++;

			// 2.Decrease in-degree by 1 for all its neighboring nodes
			if (adjMap.get(vertex) == null)
				continue;
			for (int adjNode : adjMap.get(vertex)) {
				// 3.If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
				if (--indegree[adjNode] == 0)
					queue.add(adjNode);
			}
		}
		// Step-4:If count of visited nodes is equal to the number of nodes in the graph then print the topological sort
		if (count == N) {
			linearOrder.forEach(i -> System.out.print(i + " "));
		} else {
			System.out.println("Graph is not an a DAG and also it contains a cycle.");
		}
	}

	@Override
	public boolean detectCycleInDG() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean detectCycleInUG() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mstPrimsAlg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mstKruskalsAlg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void spDijikstraAlg(int source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spBellmanFordAlg(int source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spFloydWarshallAlg() {
		// TODO Auto-generated method stub

	}

	private int[] indegree(Map<Integer, List<Integer>> adjMap) {
		int n = adjMap.size();
		int[] indegree = new int[n];

		for (Map.Entry<Integer, List<Integer>> entry : adjMap.entrySet()) {
			if (entry.getValue() == null)
				continue;
			for (int adjNode : entry.getValue())
				indegree[adjNode]++;
		}

		return indegree;
	}

	public static void main(String[] args) {
		GraphAdjListUsingMap graph = new GraphAdjListUsingMap();
		CommonUtil.testGraph(graph);
	}
}

/**
 * Edge List representation: Containers for vertices and edges. Vertices contain information only about the vertex.
 */
class GraphEdgeList implements Graph {
	// For Edge representation
	public int N;
	public int noOfEdges;
	public EdgeNode[] edges;
	public List<Integer> vertices;

	@Override
	public void buildDirectedGraph(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildUndirectedGraph(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildWeightedDG(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildWeightedUG(int[][] edges) {
		// TODO Auto-generated method stub

	}

	@Override
	public int findNumberOfNodes(int[][] edges) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printGraph() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dfsRecursive(int source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dfsIterative(int source) {
		// TODO Auto-generated method stub

	}

	@Override
	public int dfsDisconnectedGraph() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void bfsIterative(int source) {
		// TODO Auto-generated method stub

	}

	@Override
	public int bfsDisconnectedGraph() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void topologicalSortDfs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void topologicalSortBfs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean detectCycleInDG() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean detectCycleInUG() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mstPrimsAlg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mstKruskalsAlg() {
		ArrayList<EdgeNode> result = new ArrayList<>();
		Set<Integer> nodes = CommonUtil.findNumberOfNodes(edges);
		int n = nodes.size();
		DisjointSetUsingMap set = new DisjointSetUsingMap();

		for (int i = 0; i < n; i++)
			set.createNode(i);

		Arrays.sort(edges, (u, v) -> u.weight - v.weight);

		for (EdgeNode edge : edges) {
			if (set.unionByRank(edge.src, edge.dest)) {
				result.add(edge);
			}
		}

		for (EdgeNode edge : result)
			System.out.println(edge.src + " - " + edge.dest + "->" + edge.weight);
	}

	@Override
	public void spDijikstraAlg(int source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spBellmanFordAlg(int source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spFloydWarshallAlg() {
		// TODO Auto-generated method stub

	}

	public void buildIncidence(int V, int E, int[][] input) {
		this.N = V;
		this.noOfEdges = E;
		this.edges = new EdgeNode[E];
		for (int i = 0; i < E; i++)
			edges[i] = new EdgeNode(input[i][0], input[i][1], input[i][2]); // src, dest & weight

	}

	public void KruskalsMST(EdgeNode[] edges, int n) {
		ArrayList<EdgeNode> result = new ArrayList<>();
		DisjointSet set = new DisjointSet(n);

		for (int i = 0; i < n; i++)
			set.parent[i] = i;

		Arrays.sort(edges, (u, v) -> u.weight - v.weight);

		for (EdgeNode edge : edges) {
			if (!set.union(edge.src, edge.dest)) {
				result.add(edge);
			}
		}

		for (EdgeNode edge : result)
			System.out.println(edge.src + " - " + edge.dest + "->" + edge.weight);
	}

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
				if (edgeWeight[u] != Integer.MAX_VALUE) {
					edgeWeight[v] = Math.min(edgeWeight[v], edgeWeight[u] + w);
				}

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

	private void printSP(int[] weight, int n) {
		System.out.println("Shortest path from source vertex to all the vertex");
		System.out.println(" Vertex " + "  Distance/Weight");
		for (int i = 0; i < n; i++) {
			System.out.println("    " + i + "   -    " + weight[i]);
		}
	}

	public void display() {
		for (EdgeNode edge : edges)
			System.out.println("Src: " + edge.src + " Dest: " + edge.dest + " Weight: " + edge.weight);
	}

	public static void main(String[] args) {
		GraphEdgeList graph = new GraphEdgeList();
		CommonUtil.testGraph(graph);
	}

}

class MockData {
	public static int[][] mockMatrix1() {
		int[][] matrix = new int[][] { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 }, { 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 },
				{ 0, 5, 7, 9, 0 }, };

		return matrix;
	}

	public static int[][] mockMatrix2() {
		int[][] matrix = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 7, 0 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 }, };
		return matrix;
	}

	public static int[][] mockMatrix3() { // Directed Graph
		int[][] matrix = new int[][] { { 0, 4, 0, 5, 2, 0 }, { 0, 0, 6, 0, 0, 4 }, { 0, 0, 0, 3, 0, 0 },
				{ 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0 }, };
		return matrix;
	}

	public static int[][] mockAdjMatrixData4() {
		int INF = Integer.MAX_VALUE;
		int[][] matrix = new int[][] { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 }, { INF, INF, INF, 0 } };
		return matrix;
	}

	// Mock adjacent list with sample data
	public static int[][] mockEdges1() {
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 0 }, { 2, 3 }, { 3, 3 } };
		return edges;
	}

	public static int[][] mockEdges2() {
		int[][] edges = { { 1, 0 }, { 0, 2 }, { 2, 1 }, { 0, 3 }, { 1, 4 } };
		return edges;
	}

	public static int[][] mockEdges3() {
		int[][] edges = { { 5, 2 }, { 5, 0 }, { 4, 0 }, { 4, 1 }, { 2, 3 }, { 3, 1 } };
		return edges;
	}

	public static int[][] mockEdges4() {
		int[][] edges = { { 1, 0 }, { 0, 2 }, { 2, 1 }, { 0, 3 }, { 1, 4 } };
		return edges;
	}

	public static int[][] mockWeightedEdge1() {
		int[][] edges = { { 0, 1, -1 }, { 0, 2, 4 }, { 1, 2, 3 }, { 1, 3, 2 }, { 1, 4, 2 }, { 3, 2, 5 }, { 3, 1, 1 },
				{ 4, 3, -3 } };
		return edges;
	}
}

class CommonUtil {

	public static Set<Integer> findNumberOfNodes(int[][] edges) {
		HashSet<Integer> set = new HashSet<>();
		for (int[] edge : edges) {
			if (!set.contains(edge[0]))
				set.add(edge[0]);
			if (!set.contains(edge[1]))
				set.add(edge[1]);
		}
		return set;
	}

	public static Set<Integer> findNumberOfNodes(EdgeNode[] edges) {
		HashSet<Integer> set = new HashSet<>();
		for (EdgeNode edge : edges) {
			if (!set.contains(edge.src))
				set.add(edge.src);
			if (!set.contains(edge.dest))
				set.add(edge.dest);
		}
		return set;
	}

	public static void testGraph(Graph graph) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		do {
			System.out.println("Graph Operations:");
			System.out.println("1.Build Graph - ");
			System.out.println("2.BFS");
			System.out.println("3.DFS");
			System.out.println("4.Topological Sorting");
			System.out.println("5.Detect Cycle: ");
			System.out.println("6.MST Algorithm: Prim's, Kruskal's Alg: ");
			System.out.println("7.SP Algorithm: Dijikstras, Bellmanford, Floydwarshall: ");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {
			case 1:
				graph.buildDirectedGraph(MockData.mockEdges1());
				// graph.buildUndirectedGraph(MockData.mockEdges1());
				// graph.buildWeightedDG(MockData.mockEdges1());
				// graph.buildWeightedUG(MockData.mockEdges1());
				System.out.println("\nDisplay:");
				graph.printGraph();
				break;
			case 2:
				System.out.println("Enter starting index:");
				graph.bfsIterative(in.nextInt());
				graph.dfsDisconnectedGraph();
				break;
			case 3:
				System.out.println("Enter starting index:");
				graph.dfsRecursive(in.nextInt());
				// graph.dfsIterative(in.nextInt());
				// graph.dfsDisconnectedGraph();
				break;
			case 4:
				graph.topologicalSortDfs();
				// graph.topologicalSortBfs();
				break;
			case 5:
				graph.detectCycleInDG();
				graph.detectCycleInUG();
				break;
			case 6:
				graph.mstPrimsAlg();
				// graph.mstKruskalsAlg();
				break;
			case 7:
				System.out.println("Enter starting index:");
				graph.spDijikstraAlg(in.nextInt());
				// graph.spBellmanFordAlg(in.nextInt());
				// graph.spFloydWarshallAlg();
				break;
			default:
				System.out.println("Please enter the valid option!!!");
				break;
			}

			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}

}

class ResultSet {
	int parent;
	int weight;
}