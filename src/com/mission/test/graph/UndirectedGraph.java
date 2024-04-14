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

	private boolean isCyclic(int vertex, int parent, boolean[] visited) {
		visited[vertex] = true;

		Iterator<Integer> itr = adj[vertex].iterator();
		while (itr.hasNext()) {
			int i = itr.next();

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
