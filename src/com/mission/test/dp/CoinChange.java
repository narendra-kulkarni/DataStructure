package com.mission.test.dp;

import java.util.Arrays;

public class CoinChange {

	public int getMinCoins(int[] coins, int amount) {
		int[] table = new int[amount + 1];
		Arrays.fill(table, Integer.MAX_VALUE);
		table[0] = 0;

		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				if (coin <= i && table[i - coin] != Integer.MAX_VALUE) {
					table[i] = Math.min(table[i], table[i - coin] + 1);
				}
			}
		}

		return table[amount] == Integer.MAX_VALUE ? -1 : table[amount];
	}

	public static void main(String[] args) {
		CoinChange c = new CoinChange();
		int val = 38;
		int[] denom = { 2, 5 };
		int min = c.getMinCoins(denom, val);
		System.out.println("Minimum number of coins : " + min);
	}
}
