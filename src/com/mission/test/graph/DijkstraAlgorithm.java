package com.mission.test.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

	public static void main(String[] args) {
		DijkstraAlgorithm d = new DijkstraAlgorithm();
		d.createGraph();
		d.dijkstra(0);
	}

	public void createGraph() {
		g = new Graph(9);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 7, 8);
		g.addEdge(1, 2, 8);
		g.addEdge(1, 7, 11);
		g.addEdge(7, 6, 1);
		g.addEdge(7, 8, 7);
		g.addEdge(6, 8, 6);
		g.addEdge(2, 8, 2);
		g.addEdge(2, 3, 7);
		g.addEdge(6, 5, 2);
		g.addEdge(2, 5, 4);
		g.addEdge(3, 5, 14);
		g.addEdge(3, 4, 9);
		g.addEdge(5, 4, 10);
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
