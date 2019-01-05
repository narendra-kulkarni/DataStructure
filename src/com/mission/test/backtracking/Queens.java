package com.mission.test.backtracking;

public class Queens {

	public int numberOfSolutions;

	public void arrange(int num) {
		int[][] board = new int[num][num];
		placeOnBoard(board, num);
	}

	private void placeOnBoard(int[][]board, int queen) {
		if (queen == 0)										// Stop condition
			print(board);
		else {
			for (int i = 0; i < board.length; i++) {
				if (canPlaceAt(board, i, queen - 1)) {		// Condition check
					board[i][queen - 1] = 1;				// perform action
					placeOnBoard(board, queen - 1);			// recur for smaller problem
					board[i][queen - 1] = 0;				// backtrack
				}
			}
		}
	}

	private boolean canPlaceAt(int[][] board, int row, int col) {
		for (int i = 0; i < board.length; i++)
			if (board[row][i] == 1)
				return false;

		for (int i = 0; i < board.length; i++) 
			for (int j = 0; j < board.length; j++)
				if (Math.abs(row - i) == Math.abs(col - j) && board[i][j] == 1)
					return false;

		return true;
	}

	private void print(int[][] board) {
		numberOfSolutions++;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++)
				System.out.print(board[i][j] == 1 ? " X " : " . ");
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int num = 4;
		Queens q = new Queens();
		q.arrange(num);
		System.out.println(q.numberOfSolutions);
	}
}