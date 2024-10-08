package com.mission.test.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

	/**************************************************/

	// IMP: Topological sort with loop detection

	public boolean topologicalSortWithCycleDetection() {
		Stack<Integer> stack = new Stack<>();
		int[] visited = new int[vertices];

		for (int i = 0; i < vertices; i++) {
			if (visited[i] == 0) {
				if (!topologicalSort(i, visited, stack)) {
					// cycle detected
					return false;
				}
			}
		}

		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		return true;
	}

	private boolean topologicalSort(int i, int[] visited, Stack<Integer> stack) {
		visited[i] = 1; //currently visiting

		for (int adjacent : adj[i]) {
			if (visited[adjacent] == 1) {
				// If an adjacent vertex is in the visiting state, cycle is detected
				return false;
			} else if (visited[adjacent] == 0) {
				// If an adjacent vertex has not been visited, recurse on it
				if (!topologicalSort(adjacent, visited, stack)) {
					return false;
				}
			}
		}

		// Mark the current node as visited and push to stack
		visited[i] = 2;
		stack.push(i);
		return true;
	}

	/***************************************************/

	// BFS solution
	public List<Integer> topologicalSortBFS() {
		int[] inDegree = new int[vertices];

		// Calculate in-degree for each vertex
		for (int i = 0; i < vertices; i++) {
			for (int neighbor : adj[i]) {
				inDegree[neighbor]++;
			}
		}

		// Create a queue and enqueue all vertices with in-degree 0
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < vertices; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		List<Integer> topOrder = new ArrayList<>();
		int visited = 0;

		while (!queue.isEmpty()) {
			int v = queue.poll();
			topOrder.add(v);
			visited++;

			// Reduce in-degree of adjacent vertices
			for (int neighbor : adj[v]) {
				if (--inDegree[neighbor] == 0) {
					queue.offer(neighbor);
				}
			}
		}

		// Check if there was a cycle
		if (visited != vertices) {
			System.out.println("There exists a cycle in the graph");
			return null;
		}

		return topOrder;
	}

	/****************************************************/

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

	/**********************************************/

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
