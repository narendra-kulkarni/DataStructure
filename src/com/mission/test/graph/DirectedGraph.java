package com.mission.test.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class DirectedGraph {

	private int vertices;

	private List<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public DirectedGraph(int num) {
		vertices = num;
		adj = new List[num];
		for (int i = 0; i < num; i++)
			adj[i] = new ArrayList<>();
	}

	public void addEdge(int from, int to) {
		adj[from].add(to);
	}

	public void dfsRecursive(int start) {
		boolean[] visited = new boolean[vertices];
		if (!visited[start])
			dfsUtil(start, visited);
	}

	private void dfsUtil(int i, boolean[] visited) {
		System.out.print(i + " ");
		visited[i] = true;

		for (Integer j : adj[i]) {
			if (!visited[j])
				dfsUtil(j, visited);
		}
	}

	public void dfsIterative(int start) {
		boolean[] visited = new boolean[vertices];
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		visited[start] = true;

		while (!stack.isEmpty()) {
			int v = stack.pop();
			System.out.print(v + " ");

			for (int temp : adj[v]) {
				if (!visited[temp]) {
					visited[temp] = true;
					stack.push(temp);
				}
			}
		}
	}

	public boolean isCyclic() {
		boolean[] visited = new boolean[vertices];
		boolean[] recStack = new boolean[vertices];

		for (int i = 0; i < vertices; i++)
			if (!visited[i])
				if (isCyclic(i, visited, recStack))
					return true;

		return false;
	}

	private boolean isCyclic(int vertex, boolean[] visited, boolean[] recStack) {
		visited[vertex] = true;
		recStack[vertex] = true;

		Iterator<Integer> itr = adj[vertex].iterator();
		while (itr.hasNext()) {
			int i = itr.next();

			if (!visited[i] && isCyclic(i, visited, recStack))
				return true;
			else if (recStack[i])
				return true;
		}

		recStack[vertex] = false;
		return false;
	}

	public static void main(String[] args) {
		DirectedGraph g = new DirectedGraph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		g.dfsRecursive(2);
		System.out.println();
		g.dfsIterative(2);
		System.out.println();
		System.out.println(g.isCyclic());
	}
}
