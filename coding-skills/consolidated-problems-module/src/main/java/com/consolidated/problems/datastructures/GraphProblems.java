package com.consolidated.problems.datastructures;

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
import java.util.Set;
import java.util.Stack;

import com.common.model.WordNode;
import com.common.utilities.DisjointSet;

public class GraphProblems {
	/******************************* Type1: Graph DS Problems *****************/

	/******************************* Type2: Graph Cycle *****************/

	/* Graph Valid Tree: Check if a given graph is tree or not
	 * An undirected graph is tree if it has following properties. 
	 *  1) There is no cycle.
	 *  2) The graph is connected.
	 */
	public boolean isTree(int n, int[][] edges) {
		if (n == 0 || edges.length == 0)
			return false;
		LinkedList<Integer>[] adjList = buildAdjListUndirectedGraph(
				n, edges);
		boolean[] visited = new boolean[n];
		boolean isCycle = hasCycleInUndirectedGraphUtil(
				adjList, 0, visited, -1);
		if (!isCycle) {
			for (int i = 0; i < n; i++)
				if (!visited[i])
					return false;
		}
		return !isCycle;
	}

	// using DFS
	private boolean hasCycleInUndirectedGraphUtil(
			LinkedList<Integer>[] adjList, int vertex,
			boolean[] visited,
			int parent) {
		visited[vertex] = true;
		ListIterator<Integer> iter = adjList[vertex]
				.listIterator();
		while (iter.hasNext()) {
			int adjVertex = iter.next();
			if (!visited[adjVertex]) {
				if (hasCycleInUndirectedGraphUtil(adjList,
						adjVertex, visited, vertex))
					return true;
			} else if (adjVertex != parent)
				return true;
		}
		return false;
	}

	// d.Build undirected graph from given input(edges); where n - No of vertices, edges - Edge list
	public LinkedList<Integer>[] buildAdjListUndirectedGraph(
			int n, int[][] edges) {
		if (n == 0 || edges.length == 0)
			return null;
		LinkedList<Integer>[] adjList = new LinkedList[n];
		for (int i = 0; i < n; i++)
			adjList[i] = new LinkedList<>();
		for (int i = 0; i < edges.length; i++) {
			adjList[edges[i][0]].add(edges[i][1]);
			adjList[edges[i][1]].add(edges[i][0]);
		}
		return adjList;
	}

	// Function returns the minimum number of swaps required to sort the array
	// Approach1: Using Graph
	public int minSwaps(int[] arr) {
		int n = arr.length;
		ArrayList<Pair> arrpos = new ArrayList<>();
		for (int i = 0; i < n; i++)
			arrpos.add(new Pair(arr[i], i));
		arrpos.sort((a, b) -> (a.key - b.key));
		Boolean[] vis = new Boolean[n];
		Arrays.fill(vis, false);
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (vis[i] || arrpos.get(i).val == i)
				continue;
			int cycle_size = 0;
			int j = i;
			while (!vis[j]) {
				vis[j] = true;
				j = arrpos.get(j).val;
				cycle_size++;
			}
			if (cycle_size > 0) {
				ans += (cycle_size - 1);
			}
		}
		return ans;
	}

	/************************** Type3: Topo Sort(DFS/BFS(Kahn’s)) *******************/

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
	public void buildOrder(char[] projects,
			char[][] dependencies) {
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
	public void topoSortUsingIndegree2(
			LinkedList<Character>[] adjList, int n) {
		Queue<Character> queue = new LinkedList<>();
		ArrayList<Character> linearOrder = new ArrayList<>();
		int[] indegree;
		int count = 0;
		indegree = indegree2(adjList, n);
		for (int i = 0; i < n; i++)
			if (indegree[i] == 0)
				queue.add(intToChar(i));
		while (!queue.isEmpty()) {
			char vertex = queue.poll();
			linearOrder.add(vertex);
			count++;
			ListIterator<Character> iter = adjList[charToInt(
					vertex)].listIterator();
			while (iter.hasNext()) {
				char data = iter.next();
				if (--indegree[charToInt(data)] == 0)
					queue.add(data);
			}
		}
		if (count == n) {
			linearOrder.stream().forEach(
					i -> System.out.print(i + "-"));
		} else {
			System.out.println(
					"Not an a DAG and contains a cycle.");
		}
	}

	private int[] indegree2(LinkedList<Character>[] adjList,
			int n) {
		int[] indegree = new int[n];
		for (int i = 0; i < n; i++) {
			if (adjList[i].size() > 0) {
				ListIterator<Character> iterator = adjList[i]
						.listIterator();
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
	 */
	// Topological Sort DFS - using Linked List Array
	// This solution doesn't work if graph has cycle
	public void alienDictionary1(String[] words, int n) {
		LinkedList<Integer>[] adjList = new LinkedList[n];
		for (int i = 0; i < n; i++)
			adjList[i] = new LinkedList<>();
		for (int i = 0; i < words.length - 1; i++) {
			String words1 = words[i];
			String words2 = words[i + 1];
			int j = 0;
			while (j < Math.min(words1.length(),
					words2.length())) {
				if (words1.charAt(j) != words2.charAt(j)) {
					adjList[words1.charAt(j) - 'a']
							.add(words2.charAt(j) - 'a');
					break;
				}
				j++;
			}
		}
		// Topological sort
		boolean[] visited = new boolean[n];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++)
			if (!visited[i])
				topoSortUtil(adjList, i, visited, stack);
		while (!stack.isEmpty())
			System.out.print(
					(char) (stack.pop() + 'a') + "-");
	}

	private void topoSortUtil(LinkedList<Integer>[] adjList,
			int v, boolean[] visited,
			Stack<Integer> stack) {
		visited[v] = true;
		ListIterator<Integer> listIterator = adjList[v]
				.listIterator();
		while (listIterator.hasNext()) {
			int next = listIterator.next();
			if (!visited[next])
				topoSortUtil(adjList, next, visited, stack);
		}
		stack.push(v);
	}

	// Topological Sort DFS - using HashMap
	Map<Character, List<Character>> graph   = new HashMap<>();
	Map<Character, Integer>         visited = new HashMap<>();
	StringBuffer                    sb      = new StringBuffer();

	public String alienDictionary2(String[] words) {
		if (words == null || words.length == 0)
			return "";

		// Build graph, and visited map.
		buildGraph(words);

		// Topological sort with dfs
		for (char c : graph.keySet()) {
			//If there is any cycle return empty string
			if (!dfs(c))
				return "";
		}

		return sb.toString();
	}

	private void buildGraph(String[] words) {
		// Create nodes
		for (String word : words) {
			for (char c : word.toCharArray()) {
				if (!graph.containsKey(c)) {
					graph.put(c, new ArrayList<>());
					visited.put(c, 0);
				}
			}
		}

		// Build edges
		for (int i = 0; i < words.length - 1; i++) {
			int index = 0;
			String word1 = words[i];
			String word2 = words[i + 1];
			while (index < Math.min(word1.length(),
					word2.length())) {
				char c1 = word1.charAt(index);
				char c2 = word2.charAt(index);
				if (c1 != c2) {
					graph.get(c1).add(c2);
					break;
				}
				index++;
			}
		}
	}

	//It handles cycle in graph as well. 0 -> Not visited, -1->In Progress & 1-> visited 
	private boolean dfs(Character c) {
		if (visited.get(c) == 1) return true;
		if (visited.get(c) == -1) return false;

		visited.put(c, -1);
		for (char edgeNode : graph.get(c)) {
			if (!dfs(edgeNode)) {
				return false;
			}
		}
		visited.put(c, 1);
		sb.insert(0, c); // leaf element, add to buffer
		return true;
	}

	/* Topological Sort DFS - using Map
	 * Say the number of characters in the dictionary (including duplicates) is n. 
	 * Building the graph takes O(n). Topological sort takes O(V + E). V <= n. E 
	 * also can't be larger than n. So the overall time complexity is O(n).
	 */
	public String alienDictionary3(String[] words) {
		Map<Character, Set<Character>> graph = new HashMap<>();
		int[] inDegree = new int[26];
		buildGraph(words, graph, inDegree);

		String order = topologicalSort(graph, inDegree);
		return order.length() == graph.size() ? order : "";
	}

	private void buildGraph(String[] words,
			Map<Character, Set<Character>> graph,
			int[] inDegree) {
		for (String word : words) {
			for (char c : word.toCharArray()) {
				if (graph.containsKey(c)) continue;
				graph.put(c, new HashSet<>());
			}
		}

		for (int i = 1; i < words.length; i++) {
			String first = words[i - 1];
			String second = words[i];
			int length = Math.min(first.length(),
					second.length());

			for (int j = 0; j < length; j++) {
				char parent = first.charAt(j);
				char child = second.charAt(j);
				if (parent != child) {
					if (!graph.get(parent)
							.contains(child)) {
						graph.get(parent).add(child);
						inDegree[child - 'a']++;
					}
					break;
				}
			}
		}
	}

	private String topologicalSort(
			Map<Character, Set<Character>> graph,
			int[] inDegree) {
		Queue<Character> queue = new LinkedList<>();
		for (char c : graph.keySet()) {
			if (inDegree[c - 'a'] == 0) {
				queue.offer(c);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			char c = queue.poll();
			sb.append(c);
			for (char neighbor : graph.get(c)) {
				inDegree[neighbor - 'a']--;
				if (inDegree[neighbor - 'a'] == 0) {
					queue.offer(neighbor);
				}
			}
		}
		return sb.toString();
	}

	// Reconstruct Itinerary
	public List<String> findItinerary(String[][] tickets) {
		Map<String, PriorityQueue<String>> map = new HashMap<>();
		for (String[] ticket : tickets) {
			if (map.get(ticket[0]) == null)
				map.put(ticket[0], new PriorityQueue<>());
			map.get(ticket[0]).add(ticket[1]);
		}
		LinkedList<String> result = new LinkedList<>();
		reconstructItinerary("JFK", map, result);
		return result;
	}

	// Using DFS, but remove the visted data from the priority queue
	public void reconstructItinerary(String s,
			Map<String, PriorityQueue<String>> map,
			LinkedList<String> result) {
		PriorityQueue<String> queue = map.get(s);
		while (queue != null && !queue.isEmpty())
			reconstructItinerary(queue.poll(), map, result);
		result.addFirst(s);
	}

	/***************** Type4: Graph Traversals(BFS/DFS/UF) **************************/

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
	public List<Integer> findMinHeightTrees(int n,
			int[][] edges) {
		List<Integer> leaves = new ArrayList<>();
		if (n == 1) {
			leaves.add(0);
			return leaves;
		}
		if (n == 0 || edges.length == 0
				|| edges[0].length == 0)
			return leaves;
		List<Integer>[] adjList = buildAdjListUndirectedGraph(
				n, edges);
		for (int i = 0; i < n; i++)
			if (adjList[i].size() == 1)
				leaves.add(i);
		List<Integer> newLeaves;
		while (n > 2) {
			n -= leaves.size();
			newLeaves = new ArrayList<>();
			for (Integer node : leaves) {
				int neighbor = adjList[node].iterator()
						.next();
				adjList[neighbor].remove(node);
				if (adjList[neighbor].size() == 1)
					newLeaves.add(neighbor);
			}
			leaves = newLeaves;
		}
		return leaves;
	}

	// Same Solution, but Graph represented using Set instead of array
	public List<Integer> findMinHeightTrees2(int n,
			int[][] edges) {
		if (n == 1)
			return Collections.singletonList(0);
		List<Set<Integer>> adj = new ArrayList<>(n);
		for (int i = 0; i < n; ++i)
			adj.add(new HashSet<>());
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; ++i)
			if (adj.get(i).size() == 1)
				leaves.add(i);
		while (n > 2) {
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for (int i : leaves) {
				int j = adj.get(i).iterator().next();
				adj.get(j).remove(i);
				if (adj.get(j).size() == 1)
					newLeaves.add(j);
			}
			leaves = newLeaves;
		}
		return leaves;
	}

	/* Find no of disconnected components in the graph. It can be solved using DFS, BFS & Union-Find
	 * Number of Connected Components in an Undirected Graph (Java)
	 */
	// 1.i.Using DFS algorithm(Adjacency List)
	public int disConnectedGraphDFS(
			LinkedList<Integer>[] adjList, int n, int s) {
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

	public void dfsUtil1(LinkedList<Integer>[] adjList,
			int v, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + "-");
		ListIterator<Integer> listIterator = adjList[v]
				.listIterator();
		while (listIterator.hasNext()) {
			int data = listIterator.next();
			if (!visited[data]) {
				dfsUtil1(adjList, data, visited);
			}
		}
	}

	// 1.ii.Using DFS algorithm(Adjacency Matrix)
	public int disConnectedGraphDFS(int[][] adjMatrix,
			int n) {
		if (adjMatrix.length == 0)
			return 0;
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

	public void dfsUtil(int[][] adjMatrix,
			boolean[] visited, int v) {
		int n = adjMatrix.length;
		visited[v] = true;
		System.out.print(v + "-");
		for (int j = 0; j < n; j++) {
			if (!visited[j] && j != v
					&& adjMatrix[v][j] == 1) {
				dfsUtil(adjMatrix, visited, j);
			}
		}
	}

	// 2.i.Using BFS algorithm(Adjacency List)
	public int disConnectedGraphBFS(
			LinkedList<Integer>[] adjList, int n, int s) {
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

	public void bfsUtil(LinkedList<Integer>[] adjList,
			int s, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		visited[s] = true;
		while (!queue.isEmpty()) {
			int top = queue.poll();
			System.out.print(top + "-");
			ListIterator<Integer> list = adjList[top]
					.listIterator();
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
	public int disConnectedGraphBFS(int[][] adjMatrix,
			int n) {
		if (adjMatrix.length == 0)
			return 0;
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

	public void bfsUtil(int[][] adjMatrix,
			boolean[] visited, int v) {
		int n = adjMatrix.length;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		while (!queue.isEmpty()) {
			int top = queue.poll();
			visited[top] = true;
			System.out.print(top + "-");
			for (int j = 0; j < n; j++) {
				if (!visited[j] && j != v
						&& adjMatrix[top][j] == 1)
					queue.add(j);
			}
		}
	}

	// 3.Using Union-Find/Disjoint set
	public int disConnectedGraphUF(int[][] adjMatrix) {
		int row = adjMatrix.length,
				col = adjMatrix[0].length;
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
			if (ds.parent[i] == i)
				count++;
		return count;
	}

	// Disjoint Set/UF: Simplified version
	public int noOfConnectedGraphUF(int n, int[][] edges) {
		int[] nodes = new int[n];
		for (int i = 0; i < n; i++)
			nodes[i] = i;
		for (int[] edge : edges) {
			// Find Operation
			int i1 = find(nodes, edge[0]);
			int i2 = find(nodes, edge[1]);
			// Union Operation
			if (i1 != i2)
				nodes[i2] = i1;
		}
		int count = 0;
		for (int i = 0; i < n; i++)
			if (nodes[i] == i)
				count++;
		return count;
	}

	private int find(int[] arr, int i) {
		while (i != arr[i]) {
			arr[i] = arr[arr[i]];
			i = arr[i];
		}
		return i;
	}

	// Friend Circles: DFS/BFS/UF
	public int findFriendCircleNum(int[][] M) {
		if (M.length == 0 || M[0].length == 0)
			return 0;
		// DFS
		return disConnectedGraphDFS(M, M.length);
		// BFS
		// return disConnectedGraphBFS(M, M.length);
		// UF
		// return disConnectedGraphUF(M);
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
	static long roadsAndLibraries(int n, int c_lib,
			int c_road, int[][] cities) {
		long totalCost = 0;
		if (c_lib < c_road) {
			totalCost = (long) c_lib * (long) n;
			return totalCost;
		}
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

	public static void dfs(LinkedList<Integer>[] adjList,
			int s, boolean[] visited,
			List<Integer> adjCities) {
		visited[s] = true;
		adjCities.add(s);
		ListIterator<Integer> iter = adjList[s]
				.listIterator();
		while (iter.hasNext()) {
			int node = iter.next();
			if (!visited[node])
				dfs(adjList, node, visited, adjCities);
		}
	}

	// Routing bw Node: BFS Approach
	public boolean routeBwNodes(
			LinkedList<Integer>[] adjList, int start,
			int end) {
		if (adjList.length == 0)
			return false;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[adjList.length];
		visited[start] = true;
		queue.add(start);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			Iterator<Integer> iter = adjList[curr]
					.iterator();
			while (iter.hasNext()) {
				int adjNode = iter.next();
				if (!visited[adjNode]) {
					if (adjNode == end)
						return true;
					visited[adjNode] = true;
					queue.add(adjNode);
				}
			}
		}
		return false;
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
	public int[] shortestReach(int n,
			LinkedList<Integer>[] adjList, int startId) {
		int[] distances = new int[n];
		Arrays.fill(distances, -1);
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		int level = 0, distance = 0;
		if (startId < 0 || startId >= n)
			return distances;
		visited[startId] = true;
		queue.add(startId);
		while (!queue.isEmpty()) {
			level = queue.size();
			while (level-- > 0) {
				int v = queue.poll();
				distances[v] = distance;
				Iterator<Integer> iter = adjList[v]
						.iterator();
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
	public int findShortest(int graphNodes, int[] graphFrom,
			int[] graphTo, long[] ids, int val) {
		int minDist = Integer.MAX_VALUE;
		Queue<GraphNode> queue = new LinkedList<>();
		boolean[] visited = new boolean[graphNodes];
		LinkedList<Integer>[] adjList = buildAdjList(
				graphNodes, graphFrom, graphTo);
		int s = 0;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == val) {
				s = i;
				break;
			}
		}
		queue.add(new GraphNode(s, ids[s], 0));
		visited[s] = true;
		while (!queue.isEmpty()) {
			GraphNode node = queue.poll();
			if (node.vertex != s && node.color == val)
				minDist = Math.min(minDist, node.dist);
			Iterator<Integer> iter = adjList[node.vertex]
					.iterator();
			while (iter.hasNext()) {
				int adjNode = iter.next();
				if (!visited[adjNode]) {
					visited[adjNode] = true;
					int dist = node.color == ids[adjNode]
							? 1
							: node.dist + 1;
					queue.add(new GraphNode(adjNode,
							ids[adjNode], dist));
				}
			}
		}
		return minDist == Integer.MAX_VALUE ? -1 : minDist;
	}

	public LinkedList<Integer>[] buildAdjList(int n,
			int[] graphFrom, int[] graphTo) {
		LinkedList<Integer>[] adjList = new LinkedList[n];
		for (int i = 0; i < n; i++)
			adjList[i] = new LinkedList<>();
		for (int i = 0; i < graphFrom.length; i++) {
			adjList[graphFrom[i] - 1].add(graphTo[i] - 1);
			adjList[graphTo[i] - 1].add(graphFrom[i] - 1);
		}
		return adjList;
	}

	/* Word Ladder:
	 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence 
	 * from beginWord to endWord, such that:
	 *    Only one letter can be changed at a time.
	 *    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
	 */
	public int ladderLength(String beginWord,
			String endWord, List<String> wordList) {
		LinkedList<WordNode> queue = new LinkedList<>();
		queue.add(new WordNode(beginWord, 1));
		Set<String> wordDict = new HashSet<>(wordList);
		while (!queue.isEmpty()) {
			WordNode top = queue.remove();
			if (top.word.equals(endWord))
				return top.count;
			char[] arr = top.word.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char temp = arr[i];
					if (arr[i] != c)
						arr[i] = c;
					String newStr = new String(arr);
					if (wordDict.contains(newStr)) {
						queue.add(new WordNode(newStr,
								top.count + 1));
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
	public List<List<String>> findLadders(String start,
			String end, List<String> dict) {
		List<List<String>> result = new ArrayList<List<String>>();
		LinkedList<WordNode> queue = new LinkedList<WordNode>();
		queue.add(new WordNode(start, 1, null));
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
				if (minStep == 0)
					minStep = top.count;
				if (top.count == minStep && minStep != 0) {
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
			if (preNumSteps < currNumSteps)
				unvisited.removeAll(visited);
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
						queue.add(new WordNode(newWord,
								top.count + 1, top));
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
	// Solution: BFS; Time-O(n!)
	// because substring Time: n * n-1 * n-2... 1)
	public int stringDeletion(String query,
			HashSet<String> dict) {
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.add(query);
		while (!queue.isEmpty()) {
			String top = queue.poll();
			if (dict.contains(top))
				return query.length() - top.length();
			for (int i = 0; i < top.length(); i++) {
				String subStr = top.substring(0, i) + top
						.substring(i + 1, top.length());
				if (subStr.length() > 0
						&& !visited.contains(subStr)) {
					visited.add(subStr);
					queue.add(subStr);
				}
			}
		}
		return -1;
	}

	/* Evaluate Division:
	 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
	 * number (floating point number). Given some queries, return the answers. If the answer does not exist, return
	 * -1.0. Example: Given a / b = 2.0, b / c = 3.0. queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
	 * . return [6.0, 0.5, -1.0, 1.0, -1.0 ].
	 */
	// Approach1: Using Floyd Warshall Algorithm
	public double[] calcEquation1(String[][] equations,
			double[] values, String[][] queries) {
		Map<String, Integer> map = new HashMap<>();
		int n = 0;
		for (String[] eqn : equations)
			for (String str : eqn)
				if (!map.containsKey(str))
					map.put(str, n++);
		double[][] T = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				T[i][j] = (i == j) ? 1d : -1d;
		for (int i = 0; i < equations.length; i++) {
			int r = map.get(equations[i][0]),
					c = map.get(equations[i][1]);
			T[r][c] = values[i];
			T[c][r] = 1 / values[i];
		}
		for (int v = 0; v < n; v++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (T[i][v] >= 0d && T[v][j] >= 0d)
						T[i][j] = T[i][v] * T[v][j];
		double[] result = new double[queries.length];
		for (int i = 0; i < queries.length; i++) {
			String[] query = queries[i];
			if (!map.containsKey(query[0])
					|| !map.containsKey(query[1]))
				result[i] = -1d;
			else
				result[i] = T[map.get(query[0])][map
						.get(query[1])];
		}
		return result;
	}

	// Approach2: Using DFS
	public double[] calcEquation2(String[][] equations,
			double[] values, String[][] query) {
		double[] result = new double[query.length];
		Set<String> words = new HashSet<>();
		for (String[] strs : equations) {
			words.add(strs[0]);
			words.add(strs[1]);
		}
		for (int i = 0; i < query.length; ++i) {
			String[] keys = query[i];
			if (!words.contains(keys[0])
					|| !words.contains(keys[1]))
				result[i] = -1.0d;
			else {
				Stack<Integer> stack = new Stack<>();
				result[i] = helper(equations, values, keys,
						stack);
			}
		}
		return result;
	}

	public double helper(String[][] eqn, double[] values,
			String[] query, Stack<Integer> stack) {
		String from, to;
		for (int i = 0; i < eqn.length; ++i) {
			from = eqn[i][0];
			to = eqn[i][1];
			if (from.equals(query[0])
					&& to.equals(query[1]))
				return values[i];
			if (from.equals(query[1])
					&& to.equals(query[0]))
				return 1 / values[i];
		}
		for (int i = 0; i < eqn.length; ++i) {
			from = eqn[i][0];
			to = eqn[i][1];
			if (!stack.contains(i)
					&& query[0].equals(from)) {
				stack.push(i);
				double temp = values[i] * helper(eqn,
						values,
						new String[] { to, query[1] },
						stack);
				if (temp > 0)
					return temp;
				else
					stack.pop();
			}
			if (!stack.contains(i) && query[0].equals(to)) {
				stack.push(i);
				double temp = helper(eqn, values,
						new String[] { from, query[1] },
						stack) / values[i];
				if (temp > 0)
					return temp;
				else
					stack.pop();
			}
		}
		return -1.0d;
	}/************************ Type5: Connectivity *******************************************/
	/* Circle of Strings - Euler Circuit
	 * Strongly Connected Components(Kosaraju Alg)
	 * Is Graph Bipartite
	 * https://www.geeksforgeeks.org/euler-circuit-directed-graph/
	 */

}

class Pair {
	public int key;
	public int val;

	public Pair(int k, int v) {
		this.key = k;
		this.val = v;
	}
}

class GraphNode {
	public int  vertex;
	public long color;
	public int  dist;

	public GraphNode(int vertex, long color, int dist) {
		this.vertex = vertex;
		this.color = color;
		this.dist = dist;
	}
}