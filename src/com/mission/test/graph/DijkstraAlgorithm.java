package com.mission.test.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// Given a weighted graph and a source vertex in the graph,
// find the shortest paths from the source to all the other vertices in the given graph.
// Note: The given graph does not contain any negative edge.

// Dijkstra can also be considered as modification of the BFS algorithm as well where
// instead of normal queue, a priority queue is used. Min index method in the following
// code will not be needed, as the item to be processed will always be at the front of
// the queue.
public class DijkstraAlgorithm {

	// Time complexity: O((V+E)logV)
	public void dijkstra(Graph g, int src) {
		int[] dist = new int[g.vertices];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		// Use min heap which will provide minimum distance vertex in
		// the following while loop. For the first iteration, min distance
		// vertex is the source.
		PriorityQueue<Pair> q = new PriorityQueue<>();
		q.offer(new Pair(src, 0));

		while (!q.isEmpty()) {
			Pair current = q.poll();
			int u = current.vertex;

			// If shorter path is already available then skip following for loop
			if (current.distance > dist[u])
				continue;

			for (Edge edge : g.adjacencies[u]) {
				int newDist = dist[u] + edge.weight;
				if (dist[edge.dest] > newDist) {
					dist[edge.dest] = newDist;
					q.offer(new Pair(edge.dest, newDist));
				}
			}
		}

		for (int i = 0; i < dist.length; i++)
			System.out.println("Source : " + i + ", distance : " + dist[i]);
	}

	static class Pair implements Comparable<Pair> {

		int vertex;
		int distance;

		Pair(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}

		@Override
		public int compareTo(Pair p) {
			return Integer.compare(this.distance, p.distance);
		}
	}

	/******************************************************/

	public static void main(String[] args) {
		DijkstraAlgorithm d = new DijkstraAlgorithm();
		d.dijkstra(d.createGraph(), 0);
	}

	public Graph createGraph() {
		Graph g = new Graph(5);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 1);
		g.addEdge(1, 4, 4);
		g.addEdge(2, 1, 2);
		g.addEdge(2, 3, 4);
		g.addEdge(3, 4, 4);
		return g;
	}

	public class Graph {
		int vertices;
		List<Edge>[] adjacencies;

		@SuppressWarnings("unchecked")
		Graph(int num) {
			this.vertices = num;
			adjacencies = new List[num];
			for (int i = 0; i < num; i++)
				adjacencies[i] = new LinkedList<>();
		}

		void addEdge(int src, int dest, int weight) {
			adjacencies[src].add(new Edge(dest, weight));
			adjacencies[dest].add(new Edge(src, weight));
		}
	}

	private class Edge {
		int weight;
		int dest;

		Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}
}
