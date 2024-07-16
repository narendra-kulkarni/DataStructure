package com.mission.test.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// A Bipartite Graph is a graph whose vertices can be divided into two independent sets,
// U and V such that every edge (u, v) either connects a vertex from U to V or a vertex
// from V to U. In other words, for every edge (u, v), either u belongs to U and v to V,
// or u belongs to V and v to U. We can also say that there is no edge that connects
// vertices of same set.
public class BipartiteGraph {

    public static boolean isBipartite(Graph g, int src) {
        int[] color = new int[g.vertices];
        Arrays.fill(color, -1);
        color[src] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            for (int adj: g.adj[temp]) {
                // If there is a self loop, then graph can not be bipartite
                if (adj == temp)
                    return false;

                if (color[adj] == -1) {
                    queue.add(adj);
                    color[adj] = 1 - color[temp];
                }
                else if (color[adj] == color[temp])
                    return false;
            }
        }

        return true;
    }

    // Adjacency matrix variation
    public static boolean isBipartite(int[][] graph, int src) {
        int vertices = graph.length;
        int[] color = new int[vertices];
        Arrays.fill(color, -1);
        color[src] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            for (int adj = 0; adj < vertices; adj++) {
                // IMP: If there is an edge between temp and adj
                if (graph[temp][adj] == 1) {
                    // If there is a self loop, then graph can not be bipartite
                    if (adj == temp)
                        return false;

                    if (color[adj] == -1) {
                        queue.add(adj);
                        color[adj] = 1 - color[temp];
                    }
                    else if (color[adj] == color[temp])
                        return false;
                }
            }
        }

        return true;
    }

    /******************************************/

    static class Graph {

        private int vertices;

        private List<Integer>[] adj;

        public Graph(int num) {
            this.vertices = num;
            adj = new List[num];
            for (int i = 0; i < num; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        void addEdge(int src, int dest) {
            adj[src].add(dest);
            adj[dest].add(src);
        }
    }

    public static void main(String[] args) {
        // Adjacency List variation
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        System.out.println("Graph is bipartite : " + BipartiteGraph.isBipartite(g, 0));

        // Adjacency matrix variation
        int[][] graph = {
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0}
        };
        System.out.println("Graph is bipartite : " + BipartiteGraph.isBipartite(graph, 0));
    }
}
