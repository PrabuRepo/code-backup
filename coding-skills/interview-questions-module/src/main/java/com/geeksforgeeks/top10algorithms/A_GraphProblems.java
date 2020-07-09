package com.geeksforgeeks.top10algorithms;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class A_GraphProblems {

	int						vertices;
	LinkedList<Integer>[]	adjList;

	public void initialize(int noOfVertices) {
		this.vertices = noOfVertices;
		adjList = new LinkedList[noOfVertices];
		for (int i = 0; i < noOfVertices; i++)
			adjList[i] = new LinkedList<>();
	}

	public void addEdge(int src, int dest) {
		adjList[src].add(dest);
	}

	public void clear() {
		for (int i = 0; i < vertices; i++)
			adjList[i] = new LinkedList<>();
	}

	public void bfs(int startNode) {
		boolean[] visited = new boolean[vertices];
		bfs(startNode, visited);
	}

	private void bfs(int start, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int data = queue.remove();
			System.out.print(data + " ");

			ListIterator<Integer> iterator = adjList[data].listIterator();
			while (iterator.hasNext()) {
				int node = iterator.next();
				if (!visited[node]) {
					visited[node] = true;
					queue.add(node);
				}
			}
		}
	}

	public void dfs(int startingNode) {
		boolean[] visited = new boolean[vertices];

		dfs(startingNode, visited);

		// To visit unconnected graph
		for (int i = 0; i < vertices; i++) {
			if (!visited[i])
				dfs(i, visited);
		}

	}

	private void dfs(int index, boolean[] visited) {
		visited[index] = true;
		System.out.print(index + " ");

		ListIterator<Integer> iter = adjList[index].listIterator();
		while (iter.hasNext()) {
			int node = iter.next();
			if (!visited[node]) {
				dfs(node, visited);
			}
		}
	}

	public void topoSort() {
		boolean[] visited = new boolean[vertices];
		Stack<Integer> result = new Stack<>();

		for (int i = 0; i < vertices; i++) {
			if (!visited[i])
				topoSort(i, visited, result);
		}

		while (!result.isEmpty())
			System.out.print(result.pop() + " ");

	}

	private void topoSort(int vertex, boolean[] visited, Stack<Integer> result) {
		visited[vertex] = true;

		ListIterator<Integer> iter = adjList[vertex].listIterator();
		while (iter.hasNext()) {
			int node = iter.next();
			if (!visited[node]) {
				topoSort(node, visited, result);
			}
		}

		result.push(vertex);
	}

	/************** Test Data *******************/
	public void testData1() {
		initialize(4);

		addEdge(0, 1);
		addEdge(0, 2);
		addEdge(1, 2);
		addEdge(2, 0);
		addEdge(2, 3);
		addEdge(3, 3);
	}

	public void testData2() {
		initialize(6);

		addEdge(5, 2);
		addEdge(5, 0);
		addEdge(4, 0);
		addEdge(4, 1);
		addEdge(2, 3);
		addEdge(3, 1);
	}

	public static void main(String[] args) {

		A_GraphProblems ob = new A_GraphProblems();

		System.out.println("Breadth First Traversal:");
		ob.testData1();
		ob.bfs(2);

		System.out.println("\nBreadth First Traversal:");
		ob.dfs(2);

		System.out.println("\nTopological Sorting - Linear ordering of vertices");
		ob.testData2();
		ob.topoSort();
	}

}
