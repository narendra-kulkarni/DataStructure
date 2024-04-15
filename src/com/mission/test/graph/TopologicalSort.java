package com.mission.test.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of
 * vertices such that for every directed edge u -> v, vertex u comes before v in
 * the ordering. Topological Sorting for a graph is not possible if the graph is
 * not a DAG.
 * 
 * @author Narendra
 *
 */
public class TopologicalSort {

	private int vertices;

	private List<Integer>[] adj;

	private int[] indegree;

	@SuppressWarnings("unchecked")
	private TopologicalSort(int num) {
		vertices = num;
		adj = new List[num];
		indegree = new int[num];
		for (int i = 0; i < num; i++)
			adj[i] = new LinkedList<>();
	}

	private void addEdge(int src, int dest) {
		adj[src].add(dest);
		indegree[dest]++;
	}

	public void topologicalSort() {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++)
			if (!visited[i])
				topologicalSort(i, visited, stack);
		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");
	}

	private void topologicalSort(int i, boolean[] visited, Stack<Integer> stack) {
		visited[i] = true;
		for (int adjacent : adj[i]) {
			if (!visited[adjacent])
				topologicalSort(adjacent, visited, stack);
		}
		// In topological sort, element is added to the stack after visiting all
		// the adjacent vertices (unlike DFS traversal where element is printed
		// before visiting the neighbors).
		stack.push(i);
	}

	// Backtracking solution
	public void allTopologicalSort() {
		boolean[] visited = new boolean[vertices];
		List<Integer> list = new ArrayList<>();
		topologicalSort(list, visited);
	}

	private void topologicalSort(List<Integer> list, boolean[] visited) {
		boolean flag = false;

		for (int i = 0; i < vertices; i++) {
			if (!visited[i] && indegree[i] == 0) {
				for (int adjacent : adj[i])
					indegree[adjacent]--;
				list.add(i);
				visited[i] = true;

				topologicalSort(list, visited);

				visited[i] = false;
				list.remove((Integer) i);
				for (int adjacent : adj[i])
					indegree[adjacent]++;
				flag = true;
			}
		}

		if (!flag) {
			System.out.println();
			for (int temp : list)
				System.out.print(temp + " ");
		}
	}

	public static void main(String[] args) {
		TopologicalSort s = new TopologicalSort(6);
		s.addEdge(5, 0);
		s.addEdge(4, 0);
		s.addEdge(5, 2);
		s.addEdge(4, 1);
		s.addEdge(2, 3);
		s.addEdge(3, 1);
		s.topologicalSort();
		System.out.print("\n----------------------------");
		s.allTopologicalSort();
	}
}
