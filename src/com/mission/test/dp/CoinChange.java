package com.mission.test.dp;

public class CoinChange {

	public int getMinCoins(int[] denom, int val) {
		int[] table = new int[val + 1];
		table[0] = 0;
		for (int i = 1; i <= val; i++)
			table[i] = Integer.MAX_VALUE;

		for (int i = 1; i <= val; i++) {
			for (int j = 0; j < denom.length; j++) {
				if (denom[j] <= i) {
					int sub_res = table[i - denom[j]];
					if (sub_res != Integer.MAX_VALUE && sub_res + 1 < table[i])
						table[i] = sub_res + 1;
				}
			}
		}

		return table[val];
	}

	public static void main(String[] args) {
		CoinChange c = new CoinChange();
		int val = 38;
		int[] denom = { 2, 5 };
		int min = c.getMinCoins(denom, val);
		System.out.println("Minimum number of coins : " + min);
	}
}
