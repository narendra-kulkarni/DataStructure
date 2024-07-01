package com.mission.test.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Given a weighted graph and a source vertex in the graph,
// find the shortest paths from the source to all the other vertices in the given graph.
// Note: The given graph does not contain any negative edge.
public class DijkstraAlgorithm {

	private Graph g;

	public void dijkstra(int src) {
		boolean[] spt = new boolean[g.vertices];
		int[] dist = new int[g.vertices];
		for (int i = 0; i < g.vertices; i++)
			dist[i] = Integer.MAX_VALUE;
		dist[src] = 0;

		for (int i = 0; i < g.vertices; i++) {
			// Get the vertex which is still to be processed and 
			// has current minimum distance
			int u = minIndex(spt, dist);
			spt[u] = true;

			Iterator<Edge> itr = g.adjacencies[u].iterator();
			while (itr.hasNext()) {
				Edge v = itr.next();
				if (!spt[v.dest] && dist[u] != Integer.MAX_VALUE
						&& dist[v.dest] > dist[u] + v.weight)
					dist[v.dest] = dist[u] + v.weight;
			}
		}

		for (int i = 0; i < dist.length; i++)
			System.out.println("Source : " + i + ", distance : " + dist[i]);
	}

	private int minIndex(boolean[] spt, int[] dist) {
		int minIndex = -1;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < g.vertices; i++) {
			if (!spt[i] && dist[i] <= min) {
				min = dist[i];
				minIndex = i;
			}
		}

		return minIndex;
	}

	/******************************************************/

	public static void main(String[] args) {
		DijkstraAlgorithm d = new DijkstraAlgorithm();
		d.createGraph();
		d.dijkstra(0);
	}

	public void createGraph() {
		g = new Graph(5);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 1);
		g.addEdge(1, 4, 4);
		g.addEdge(2, 1, 2);
		g.addEdge(2, 3, 4);
		g.addEdge(3, 4, 4);
	}

	private class Graph {
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
