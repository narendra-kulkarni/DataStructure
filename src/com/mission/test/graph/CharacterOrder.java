package com.mission.test.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CharacterOrder {

	// Given a sorted dictionary (array of words) of an alien language, find
	// order of characters in the language
	public static void main(String[] args) {
		String[] dictionary = { "baa", "abcd", "abca", "cab", "cad" };
		CharacterOrder o = new CharacterOrder();
		o.printOrder(dictionary, 4);
	}

	public void printOrder(String[] dictionary, int size) {
		Graph g = new Graph(size);

		for (int i = 0; i < dictionary.length - 1; i++) {
			String word1 = dictionary[i];
			String word2 = dictionary[i + 1];

			for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
				if (word1.charAt(j) != word2.charAt(j)) {
					g.addEdge(word1.charAt(j) - 'a', word2.charAt(j) - 'a');
					break;	// this is important
				}
			}
		}

		System.out.print("Order of characters is : ");
		g.topologicalSort();
	}

	private class Graph {
		int vertices;
		List<Integer>[] adajacencies;

		@SuppressWarnings("unchecked")
		Graph(int vertices) {
			this.vertices = vertices;
			adajacencies = new List[vertices];
			for (int i = 0; i < vertices; i++)
				adajacencies[i] = new LinkedList<>();
		}

		void addEdge(int src, int dest) {
			adajacencies[src].add(dest);
		}

		void topologicalSort() {
			Stack<Integer> stack = new Stack<>();
			boolean[] visited = new boolean[vertices];
			for (int i = 0; i < vertices; i++)
				if (!visited[i])
					topologicalSortUtil(i, visited, stack);

			while (!stack.isEmpty())
				System.out.print(((char) (stack.pop() + 'a')) + " ");
		}

		private void topologicalSortUtil(int src, boolean[] visited, Stack<Integer> stack) {
			visited[src] = true;

			for(int adjacent : adajacencies[src]) {
				if (!visited[adjacent])
					topologicalSortUtil(adjacent, visited, stack);
			}

			stack.push(src);
		}
	}
}
