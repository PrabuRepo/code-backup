package com.common.model;

public interface GraphOperations {

	public void buildDirectedGraphAdjList(int n, int[][] edges);

	public void addVertex(int n);

	public void removeVertex(int n);

	public void addEdge(int src, int dest);

	public void addEdge(int src, int dest, int weight);

	public void removeEdge(int src, int dest);

	public boolean hasEdge(int src, int dest);

	public boolean contains(int vertex);

	public void display();
}
