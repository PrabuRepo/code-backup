package com.problems.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphPatterns {
	/*************************** Topological Sort *******************/
	// Topological Sort
	/* Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for "every
	 * directed edge uv, vertex u comes before v in the ordering". Topological Sorting for a graph is not possible if the
	 * graph is not a DAG.
	 * Fact: A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
	 *   Approach1: DFS Algorithm
	 *   Approach2: BFS Algorithm (Kahn's Algorithm)
	 */
	// Topological Sort to order the graph
	public void toplogicalSort1(int n, LinkedList<Integer>[] adjList) {
		boolean[] visited = new boolean[n];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++)
			if (!visited[i]) {
				topoSortUtil(adjList, i, visited, stack);
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

	/* BFS Algorithm (Kahn's Algorithm) for Topological Sorting:
	 * A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
	 */
	public void toplogicalSort2(int n, LinkedList<Integer>[] adjList) {
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

	// Tasks Scheduling
	// Tasks Scheduling Order
	// All Tasks Scheduling Orders

	/************ Clean up: start ******************/
	// Alien Dictionary
	/*
	 * Given a sorted dictionary of an alien language, find order of characters Given a sorted dictionary (array of words)
	 * of an alien language, find order of characters in the language.
	 * 
	 * Solution using Topological Sort - Rewrite this
	 */
	public void alienDictionary(String[] words, int n) {
		LinkedList<Integer>[] adjList = new LinkedList[n];
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

	public String alienDictionary2(String[] words) {
		Map<Character, List<Character>> adjMap = new HashMap<>();
		for (int i = 0; i < words.length - 1; i++) {
			String words1 = words[i];
			String words2 = words[i + 1];
			int j = 0;
			while (j < Math.min(words1.length(), words2.length())) {
				if (words1.charAt(j) != words2.charAt(j)) {
					if (adjMap.get(words1.charAt(j)) == null) adjMap.put(words1.charAt(j), new ArrayList<>());
					if (adjMap.get(words2.charAt(j)) == null) adjMap.put(words2.charAt(j), new ArrayList<>());
					adjMap.get(words1.charAt(j)).add(words2.charAt(j));
					break;
				} else {
					if (adjMap.get(words1.charAt(j)) == null) adjMap.put(words1.charAt(j), new ArrayList<>());
				}
				j++;
			}
		}

		// Topological sort
		Set<Character> visited = new HashSet<>();
		Stack<Character> stack = new Stack<>();
		// Start with first char:
		topoSortUtil(adjMap, words[0].charAt(0), visited, stack);
		for (Map.Entry<Character, List<Character>> entry : adjMap.entrySet()) {
			if (!visited.contains(entry.getKey())) {
				topoSortUtil(adjMap, entry.getKey(), visited, stack);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty())
			sb.append(stack.pop());

		return sb.toString();
	}

	private void topoSortUtil(Map<Character, List<Character>> adjMap, char v, Set<Character> visited,
			Stack<Character> stack) {
		visited.add(v);
		if (adjMap.get(v) != null) {
			ListIterator<Character> listIterator = adjMap.get(v).listIterator();
			while (listIterator.hasNext()) {
				char next = listIterator.next();
				if (!visited.contains(next)) topoSortUtil(adjMap, next, visited, stack);
			}
		}
		stack.push(v);
	}

	// LintCode Problem:
	Map<Character, List<Character>>	graph	= new HashMap<>();
	Map<Character, Integer>			visited	= new HashMap<>();
	StringBuffer					sb		= new StringBuffer();

	public String alienOrder1(String[] words) {
		if (words == null || words.length == 0) { return ""; }

		// Build graph, and visited map.
		buildGraph(words);

		// Topological sort with dfs
		for (char c : graph.keySet()) {
			if (!dfs(c)) { return ""; }
		}

		return sb.toString();
	}

	private boolean dfs(Character c) {
		if (visited.get(c) == 1) return true;
		if (visited.get(c) == -1) return false;

		visited.put(c, -1);
		for (char edgeNode : graph.get(c)) {
			if (!dfs(edgeNode)) { return false; }
		}
		visited.put(c, 1);
		sb.insert(0, c); // leaf element, add to buffer
		return true;
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
			while (index < words[i].length() && index < words[i + 1].length()) {
				char c1 = words[i].charAt(index);
				char c2 = words[i + 1].charAt(index);
				if (c1 != c2) {
					graph.get(c1).add(c2);
					break;
				}
				index++;
			}
		}
	}

	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) return "";

		// Build graph && indegree map
		Map<Character, List<Character>> graph = new HashMap<>();
		Map<Character, Integer> inDegree = new HashMap<>();
		build(graph, inDegree, words);

		// Topological sort with BFS
		return topoSort(graph, inDegree);
	}

	private void build(Map<Character, List<Character>> graph, Map<Character, Integer> inDegree, String[] words) {
		// Init graph, inDegree map
		for (String word : words) {
			for (char c : word.toCharArray()) {
				graph.putIfAbsent(c, new ArrayList<>());
				inDegree.putIfAbsent(c, 0);
			}
		}
		// Build graph: find char diff between curr row and next row => build graph edge and increase inDegree
		// relationship
		// always compare same index on: words[i] -> words[i+1]
		// if c1 != c2, build graph and inDegree map and break: later index does not have any more relationship.
		for (int i = 0; i < words.length - 1; i++) {
			int index = 0;
			while (index < words[i].length() && index < words[i + 1].length()) {
				char c1 = words[i].charAt(index);
				char c2 = words[i + 1].charAt(index);
				if (c1 != c2) {
					graph.get(c1).add(c2);
					inDegree.put(c2, inDegree.get(c2) + 1);
					break;
				}
				index++;
			}
		}
	}

	private String topoSort(Map<Character, List<Character>> graph, Map<Character, Integer> inDegree) {
		Queue<Character> queue = new LinkedList<>();
		for (char c : inDegree.keySet()) { // Build queue with item of inDegree==0: means no edge points towards it.
			if (inDegree.get(c) == 0) queue.offer(c);
		}

		StringBuffer sb = new StringBuffer();
		while (!queue.isEmpty()) {
			char c = queue.poll();
			sb.append(c);
			for (char edgeNode : graph.get(c)) { // reduce edge degrees count since node:graph.get(c) has been processed
				inDegree.put(edgeNode, inDegree.get(edgeNode) - 1);
				if (inDegree.get(edgeNode) == 0) queue.offer(edgeNode);
			}
		}

		if (sb.length() != graph.size()) return "";
		return sb.toString();
	}

	/************ Clean up: end ******************/

	// Reconstruct Itinerary
	public List<String> findItinerary(String[][] tickets) {
		Map<String, PriorityQueue<String>> map = new HashMap<>();
		for (String[] ticket : tickets) {
			if (map.get(ticket[0]) == null) map.put(ticket[0], new PriorityQueue<>());
			map.get(ticket[0]).add(ticket[1]);
		}
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
	// Reconstructing a Sequence

	// Minimum Height Trees
	/*
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
		List<Integer>[] adjList = buildAdjListUndirectedGraph(n, edges);
		for (int i = 0; i < n; i++)
			if (adjList[i].size() == 1) leaves.add(i);
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

	// d.Build undirected graph from given input(edges); where n - No of vertices, edges - Edge list
	public LinkedList<Integer>[] buildAdjListUndirectedGraph(int n, int[][] edges) {
		if (n == 0 || edges.length == 0) return null;
		LinkedList<Integer>[] adjList = new LinkedList[n];
		for (int i = 0; i < n; i++)
			adjList[i] = new LinkedList<>();
		for (int i = 0; i < edges.length; i++) {
			adjList[edges[i][0]].add(edges[i][1]);
			adjList[edges[i][1]].add(edges[i][0]);
		}
		return adjList;
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

	// Course Schedule I:
	// Topo Sort - BFS Approach
	public boolean canFinish(int courses, int[][] prerequisites) {
		List<Integer>[] adjList = new LinkedList[courses];
		Queue<Integer> queue = new LinkedList<>();
		int[] indegree = new int[courses];
		for (int i = 0; i < courses; i++)
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
		return count == courses;
	}

	// Course Schedule II: Using topo sort(reverse)
	public int[] findOrder(int courses, int[][] preReq) {
		if (courses == 0) return null;
		int indegree[] = new int[courses], order[] = new int[courses], index = 0;
		for (int i = 0; i < preReq.length; i++)
			indegree[preReq[i][0]]++;
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < courses; i++)
			if (indegree[i] == 0) {
				order[index++] = i;
				queue.offer(i);
			}
		while (!queue.isEmpty()) {
			int prerequisite = queue.poll();
			for (int i = 0; i < preReq.length; i++) {
				if (preReq[i][1] == prerequisite) {
					indegree[preReq[i][0]]--;
					if (indegree[preReq[i][0]] == 0) {
						order[index++] = preReq[i][0];
						queue.offer(preReq[i][0]);
					}
				}
			}
		}
		return (index == courses) ? order : new int[0];
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
		indegree = indegree2(adjList, n);
		for (int i = 0; i < n; i++)
			if (indegree[i] == 0) queue.add(intToChar(i));
		while (!queue.isEmpty()) {
			char vertex = queue.poll();
			linearOrder.add(vertex);
			count++;
			ListIterator<Character> iter = adjList[charToInt(vertex)].listIterator();
			while (iter.hasNext()) {
				char data = iter.next();
				if (--indegree[charToInt(data)] == 0) queue.add(data);
			}
		}
		if (count == n) {
			linearOrder.stream().forEach(i -> System.out.print(i + "-"));
		} else {
			System.out.println("Not an a DAG and contains a cycle.");
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
}
