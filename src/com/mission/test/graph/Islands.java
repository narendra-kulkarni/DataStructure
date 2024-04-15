package com.mission.test.graph;

public class Islands {

	private final static int[] rowSpans = {-1, -1, -1, 0, 0, 1, 1, 1};
	private final static int[] colSpans = {-1, 0, 1, -1, 1, -1, 0, 1};

	public static void main(String[] args) {
		int[][] arr = {{1, 0, 1, 0},
				{0, 0, 1, 1},
				{1, 0, 0, 1},
				{0, 1, 0, 0}};
		Islands i = new Islands();
		int num = i.findIslands(arr);
		System.out.println("The number of islands are : " + num);
	}

	public int findIslands(int[][] arr) {
		int counter = 0;
		int row = arr.length;
		int col = arr[0].length;
		boolean[][] visited = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					counter++;
					dfs(arr, i, j, visited);
				}
			}
		}

		return counter;
	}

	private void dfs(int[][] arr, int i, int j, boolean[][] visited) {
		visited[i][j] = true;

		for (int index = 0; index < 8; index++) {
			int newRow = i + rowSpans[index];
			int newCol = j + colSpans[index];
			if (inRange(arr, newRow, newCol) && arr[newRow][newCol] == 1 
					&& !visited[newRow][newCol])
				dfs(arr, newRow, newCol, visited);
		}
	}

	private boolean inRange(int[][] arr, int newRow, int newCol) {
		int row = arr.length;
		int col = arr[0].length;
		return (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col);
	}
}
