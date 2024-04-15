package com.mission.test.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UndirectedGraph {

	private int vertices;

	private List<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public UndirectedGraph(int num) {
		vertices = num;
		adj = new List[num];
		for (int i = 0; i < num; i++)
			adj[i] = new ArrayList<>();
	}

	public void addEdge(int start, int dest) {
		adj[start].add(dest);
		adj[dest].add(start);
	}

	public boolean isCyclic() {
		boolean[] visited = new boolean[vertices];

		for (int i = 0; i < vertices; i++) 
			if (!visited[i])
				if (isCyclic(i, -1, visited))
					return true;

		return false;
	}

	// DFS tree provides path from starting vertex to the deepest in the branch
	// If already visited vertex is encoutered which is not parent (note that, in
	// undirected graph u -> v also means v -> u) then there is cycle
	private boolean isCyclic(int vertex, int parent, boolean[] visited) {
		visited[vertex] = true;

		for (int i : adj[vertex]) {
			if (!visited[i]) {
				if (isCyclic(i, vertex, visited))
					return true;
			}
			else if (i != parent)
				return true;
		}

		return false;
	}

	public static void main(String[] args) {
		UndirectedGraph g = new UndirectedGraph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		//g.addEdge(3, 1);
		System.out.println(g.isCyclic());
	}
}
