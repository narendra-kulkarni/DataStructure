package com.mission.test.graph;

import java.util.*;

public class ShortestPathDAG {

	static final int INF = Integer.MAX_VALUE;

	class AdjListNode {
		private int v;
		private int weight;

		AdjListNode(int _v, int _w) {
			v = _v;
			weight = _w;
		}

		int getV() {
			return v;
		}

		int getWeight() {
			return weight;
		}
	}

	// Class to represent graph as an adjacency list of
	// nodes of type AdjListNode
	class Graph {
		private int V;
		private List<AdjListNode> adj[];

		@SuppressWarnings("unchecked")
		Graph(int v) {
			V = v;
			adj = new List[V];
			for (int i = 0; i < v; ++i)
				adj[i] = new LinkedList<AdjListNode>();
		}

		void addEdge(int u, int v, int weight) {
			AdjListNode node = new AdjListNode(v, weight);
			adj[u].add(node);
		}

		// A recursive function used by shortestPath.
		void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
			// Mark the current node as visited.
			visited[v] = true;

			// Recur for all the vertices adjacent to this vertex
			Iterator<AdjListNode> it = adj[v].iterator();
			while (it.hasNext()) {
				AdjListNode node = it.next();
				if (!visited[node.getV()])
					topologicalSortUtil(node.getV(), visited, stack);
			}
			// Push current vertex to stack which stores result
			stack.push(new Integer(v));
		}

		// The function to find shortest paths from given vertex. It
		// uses recursive topologicalSortUtil() to get topological
		// sorting of given graph.
		void shortestPath(int s) {
			// Initialize distances to all vertices as infinite and
			// distance to source as 0
			int dist[] = new int[V];
			for (int i = 0; i < V; i++)
				dist[i] = INF;
			dist[s] = 0;

			// Call the recursive helper function to store Topological
			// Sort starting from all vertices one by one
			Stack<Integer> stack = new Stack<>();
			boolean visited[] = new boolean[V];
			for (int i = 0; i < V; i++)
				if (visited[i] == false)
					topologicalSortUtil(i, visited, stack);

			// Process vertices in topological order
			while (!stack.isEmpty()) {
				int u = stack.pop();

				// Update distances of all adjacent vertices
				if (dist[u] != INF) {
					Iterator<AdjListNode> it = adj[u].iterator();
					while (it.hasNext()) {
						AdjListNode i = it.next();
						if (dist[i.getV()] > dist[u] + i.getWeight())
							dist[i.getV()] = dist[u] + i.getWeight();
					}
				}
			}

			// Print the calculated shortest distances
			for (int i = 0; i < V; i++) {
				if (dist[i] == INF)
					System.out.print("INF ");
				else
					System.out.print(dist[i] + " ");
			}
		}
	}

	// Method to create a new graph instance through an object
	// of ShortestPath class.
	Graph newGraph(int number) {
		return new Graph(number);
	}

	public static void main(String args[]) {
		ShortestPathDAG t = new ShortestPathDAG();
		Graph g = t.newGraph(6);
		g.addEdge(0, 1, 5);
		g.addEdge(0, 2, 3);
		g.addEdge(1, 3, 6);
		g.addEdge(1, 2, 2);
		g.addEdge(2, 4, 4);
		g.addEdge(2, 5, 2);
		g.addEdge(2, 3, 7);
		g.addEdge(3, 4, -1);
		g.addEdge(4, 5, -2);

		int s = 1;
		System.out.println("Following are shortest distances from source " + s);
		g.shortestPath(s);
	}
}