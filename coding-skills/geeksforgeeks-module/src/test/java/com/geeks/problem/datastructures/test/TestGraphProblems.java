package com.geeks.problem.datastructures.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import com.common.model.EdgeNode;
import com.common.model.GraphNode;
import com.geeks.problem.datastructures.GraphProblems;

public class TestGraphProblems extends GraphProblems {

	public static void main(String[] args) throws IOException {
		TestGraphProblems graph = new TestGraphProblems();

		// graph.testIntroductionProblems();

		// graph.testGraphCycleProblems();

		// graph.testTopoSortProblems();

		// graph.testMSTProblems();

		// graph.testShortestPathProblems();

		// graph.testConnectivity();

		// graph.testMiscProblems();

		// graph.testDFSProblems();

		graph.testBFSProblems();

	}

	public void testIntroductionProblems() {
		System.out.println("Graph Operations:");
		// mockAdjList4();
		int n = 4; // No of vertices
		LinkedList<Integer>[] adjList = buildAdjListDirectedGraph(n, edges1());

		System.out.println("Breadth First Search:");
		bfs(adjList, n, 1);
		System.out.println("\nDepth First Search(Recursive):");
		dfs(adjList, n, 1);
		System.out.println("\nDepth First Search(Iterative):");
		dfsIterative(adjList, n, 1);
		System.out.println("\nDepth First Search (for unconnected graph):");
		int n2 = 5; // No of vertices
		LinkedList<Integer>[] adjList2 = buildAdjListUndirectedGraph(n2, edges5());
		disConnectedGraphDFS(adjList2, n2, 1);

		System.out.print("Display:");
		displayAdjList(adjList, n);
		adjList2 = buildAdjListUndirectedGraph(6, edges6());
		System.out.println("Route b/w Nodes: " + routeBwNodes(adjList2, 0, 3));
	}

	public void testGraphCycleProblems() {
		int n = 6; // No of vertices
		LinkedList<Integer>[] adjList = buildAdjListDirectedGraph(n, edges6());
		// mockAdjListForCycle();
		System.out.println("Detect the cycle in the directed graph:" + hasCycleInDirectedGraph(adjList, n));

		int n2 = 5; // No of vertices
		LinkedList<Integer>[] adjList2 = buildAdjListDirectedGraph(n, edges7());
		System.out.println("Detect the cycle in the directed graph:" + hasCycleInUndirectedGraph(adjList2, n2));

		int n3 = 4, e = 5;
		EdgeNode[] edges = buildIncidenceWeightedGraph(n3, e, incidenceEdge2());
		System.out.println("Detect the cycle in the directed graph:" + hasCycleInUndirectedGraph(edges, n3, e));
	}

	public void testTopoSortProblems() {
		// mockAdjList4();
		int n = 6; // No of vertices
		LinkedList<Integer>[] adjList = buildAdjListDirectedGraph(n, edges4());
		System.out.println("Topological Sorting - Linear ordering of vertices:");
		toplogicalSort(adjList, n);

		System.out.println("\nTopological Sorting(Khan's Algorithm) - Linear ordering of vertices:");
		n = 4; // No of vertices
		adjList = buildAdjListDirectedGraph(n, edges8());
		topoSortUsingIndegree(adjList, n);

		System.out.println("\nAlien Dictionary: ");
		// String words[] = { "baa", "abcd", "abca", "cab", "cad" };
		String words[] = { "wrt", "wrf", "er", "ett", "rftt" };
		alienDictionary(words, 4);
	}

	public void testMSTProblems() {
		// MST - Mininmum Spanning tree
		System.out.println("MST: Prim's Algorithm");
		primsMST(mockAdjMatrixData2(), 9);

		System.out.println("MST: Kruskal's Algorithm");
		int n = 5, e = 5;
		EdgeNode[] edges = buildIncidenceWeightedGraph(n, e, incidenceEdge2());
		KruskalsMST(edges, n);
	}

	public void testShortestPathProblems() {
		// SP - Shortest Path
		System.out.println("SP: Dijikstra's Algorithm - Adjacency Matrix");
		dijkstraSP(mockAdjMatrixData2(), 9, 2);
		LinkedList<GraphNode>[] adjList = buildAdjListDirectWeightedGraph(5, edges4());
		System.out.println("SP: Dijikstra's Algorithm - Adjacency List");
		dijkstraSP(adjList, 5, 0);

		System.out.println("\nSP: BellmanFord Algorithm");
		int n = 5, e = 8;
		EdgeNode[] edges = buildIncidenceWeightedGraph(n, e, incidenceEdge1());
		bellmanFordSP(edges, n, e, 0);

		System.out.println("SP: Floyd Warshall Algorithm");
		floydWarshallSP(mockAdjMatrixData4(), 4);

		String[][] equations = { { "a", "b" }, { "b", "c" } };
		double[] values = { 2.0, 3.0 };
		String[][] queries = { { "a", "c" }, { "b", "c" }, { "a", "e" }, { "a", "a" }, { "x", "x" } };
		System.out.println("Evaluate Division: " + calcEquation1(equations, values, queries));
	}

	public void testConnectivity() {
		int[][] edges = { { 1, 0 }, { 0, 2 }, { 0, 3 }, { 3, 4 } };
		System.out.println("Check if a given graph is tree or not: " + isTree(5, edges));
	}

	public void testMiscProblems() {
		int[][] preRequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		// int[][] preRequisites = { { 1, 0 } };
		System.out.println("\nCourse Schedule(Using DFS): " + canFinish(4, preRequisites));
		System.out.println("Course Schedule(Using DFS): " + Arrays.toString(findOrder(4, preRequisites)));

		int[][] M = { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } };
		System.out.println("\nFriend Circles: " + findFriendCircleNum(M));

		String beginWord = "hit";
		String endWord = "cog";
		String[] wordList = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println("Word Ladder length: " + ladderLength(beginWord, endWord, Arrays.asList(wordList)));
		System.out.println("Word Ladder length: " + findLadders(beginWord, endWord, Arrays.asList(wordList)));
	}

	public void testDFSProblems() {

	}

	public void testBFSProblems() {
		System.out.println(
				"Snake & Ladder: Find min no of dice throws-> " + findMinDiceThrows(mockSnakeAndLadderData(30), 30));
		int[][] edges = { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 4 } };
		System.out.println("Minimum Height Trees: " + findMinHeightTrees(6, edges));

		HashSet<String> dict = new HashSet<>();
		dict.add("a");
		dict.add("aa");
		dict.add("aaa");
		System.out.println(stringDeletion("aca", dict));
	}

	/******** Adjacency List Data setup **********/

	public int[][] edges1() { // No of vertices = 5
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 0 }, { 2, 3 }, { 3, 3 } };
		return edges;
	}

	public int[][] edges2() {// No of vertices = 5
		int[][] edges = { { 1, 0 }, { 0, 2 }, { 2, 1 }, { 0, 3 }, { 1, 4 } };
		return edges;
	}

	public int[][] edges3() { // No of vertices = 6
		int[][] edges = { { 5, 2 }, { 5, 0 }, { 4, 0 }, { 4, 1 }, { 2, 3 }, { 3, 1 } };
		return edges;
	}

	public int[][] edges4() {// No of vertices = 5
		int[][] edges = { { 0, 1 }, { 1, 3 }, { 1, 4 }, { 2, 0 }, { 2, 3 }, { 3, 0 }, { 4, 3 } };
		return edges;
	}

	public int[][] edges5() {// No of vertices = 5
		int[][] edges = { { 1, 0 }, { 2, 3 }, { 3, 4 } };
		return edges;
	}

	public int[][] edges6() {// No of vertices = 6
		int[][] edges = { { 5, 2 }, { 0, 5 }, { 4, 0 }, { 1, 4 }, { 2, 3 }, { 3, 1 } };
		return edges;
	}

	public int[][] edges7() {// No of vertices = 5
		int[][] edges = { { 1, 0 }, { 2, 0 }, { 0, 3 }, { 3, 4 }, { 1, 2 } };
		return edges;
	}

	public int[][] edges8() {// No of vertices = 4
		int[][] edges = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		return edges;
	}

	/******** Adjacency Matrix setup **********/

	public int[][] mockAdjMatrixData1() {// noOfVertices = 5
		int[][] adjMatrix = new int[][] { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 }, { 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 },
				{ 0, 5, 7, 9, 0 } };
		return adjMatrix;
	}

	public int[][] mockAdjMatrixData2() { // noOfVertices = 9
		int[][] adjMatrix = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 7, 0 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 }, };
		return adjMatrix;
	}

	public int[][] mockAdjMatrixData3() { // noOfVertices = 6
		int[][] adjMatrix = new int[][] { { 0, 4, 0, 5, 2, 0 }, { 0, 0, 6, 0, 0, 4 }, { 0, 0, 0, 3, 0, 0 },
				{ 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 0, 0 }, };
		return adjMatrix;
	}

	public int[][] mockAdjMatrixData4() { // noOfVertices =4
		int INF = Integer.MAX_VALUE;
		int[][] adjMatrix = new int[][] { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 },
				{ INF, INF, INF, 0 } };
		return adjMatrix;
	}

	/******** Edge List data setup **********/
	public int[][] incidenceEdge1() { // V-5, E-8
		int[][] edges = { { 0, 1, -1 }, { 0, 2, 4 }, { 1, 2, 3 }, { 1, 3, 2 }, { 1, 4, 2 }, { 3, 2, 5 }, { 3, 1, 1 },
				{ 4, 3, -3 } };
		return edges;
	}

	public int[][] incidenceEdge2() { // V-4, E-5
		int[][] edges = { { 0, 1, 10 }, { 0, 2, 6 }, { 0, 3, 5 }, { 1, 3, 15 }, { 2, 3, 4 } };
		return edges;
	}

	public int[][] incidenceEdge3() { // V-3, E-3
		int[][] edges = { { 0, 1, 1 }, { 1, 2, 1 }, { 0, 2, 1 } };
		return edges;
	}

	public int[] mockSnakeAndLadderData(int N) {
		int moves[] = new int[N];
		Arrays.fill(moves, -1);

		// Ladders
		moves[2] = 21;
		moves[4] = 7;
		moves[10] = 25;
		moves[19] = 28;

		// Snakes
		moves[26] = 0;
		moves[20] = 8;
		moves[16] = 3;
		moves[18] = 6;

		return moves;
	}

}
