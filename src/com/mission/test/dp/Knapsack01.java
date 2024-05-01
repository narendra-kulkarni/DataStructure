package com.mission.test.dp;

public class Knapsack01 {

	public int getMaxVal(int[] vals, int[] weights, int limit) {
		int[][] memo = new int[weights.length + 1][limit + 1];

		for (int i = 0; i <= weights.length; i++) {
			for (int j = 0; j <= limit; j++) {
				if (i == 0 || j == 0)
					memo[i][j] = 0;
				else if (weights[i - 1] <= j)
					memo[i][j] = Math.max(memo[i - 1][j],
							memo[i - 1][j - weights[i - 1]] + vals[i - 1]);
				else
					memo[i][j] = memo[i - 1][j];
			}
		}

		return memo[weights.length][limit];
	}

	public static void main(String[] args) {
		Knapsack01 k = new Knapsack01();
		int[] vals = {60, 100, 120};
		int[] weights = {10, 20, 30};
		int weight = 50;
		int max = k.getMaxVal(vals, weights, weight);
		System.out.println("Maximum value is : " + max);
	}
}
