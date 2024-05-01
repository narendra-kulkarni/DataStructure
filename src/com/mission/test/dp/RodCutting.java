package com.mission.test.dp;

public class RodCutting {

	public static void main(String[] args) {

		// Given a rod of length n inches and an array of prices that includes
		// prices of all pieces of size smaller than n. Determine the maximum value
		// obtainable by cutting up the rod and selling the pieces.
		// For example, if the length of the rod is 8 and the values of different
		// pieces are given as the following, then the maximum obtainable value is
		// 22 (by cutting in two pieces of lengths 2 and 6)

		RodCutting r = new RodCutting();
		int[] arr = { 1, 5, 8, 9, 10, 17, 17, 20 };
		int price = r.getMaxPrice(arr);
		System.out.println("Maximum obtainable price : " + price);
	}

	public int getMaxPrice(int[] price) {
		int[] memo = new int[price.length + 1];
		memo[0] = 0;

		for (int i = 1; i < memo.length; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++)
				max = Math.max(max, price[j] + memo[i - j - 1]);
			memo[i] = max;
		}

		return memo[price.length];
	}
}
