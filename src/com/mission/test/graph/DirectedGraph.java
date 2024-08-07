package com.mission.test.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

	public void dfsRecursive() {
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if (!visited[i])
				dfsUtil(i, visited);
		}
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

	public void bfs(int startNode) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[vertices];

		// Mark the current node as visited and enqueue it
		visited[startNode] = true;
		queue.add(startNode);

		while (!queue.isEmpty()) {
			int currentNode = queue.poll();
			System.out.print(currentNode + " ");

			for (int neighbor : adj[currentNode]) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
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

	// why visisted is not enough and we need recursion array as well? -> consider
	// a veretx which has indegree more than 1 from two separate branches
	// A -> B <- c
	// We should ensure that dfs branch (recursion stack) does not encounter the same
	// vertext again
	private boolean isCyclic(int vertex, boolean[] visited, boolean[] recStack) {
		visited[vertex] = true;
		recStack[vertex] = true;

		for (int i : adj[vertex]) {
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
		//g.addEdge(1, 2);
		//g.addEdge(2, 0);
		g.addEdge(2, 3);
		//g.addEdge(3, 3);
		g.dfsRecursive();
		System.out.println();
		g.dfsIterative(2);
		System.out.println();
		System.out.println(g.isCyclic());
	}
}
