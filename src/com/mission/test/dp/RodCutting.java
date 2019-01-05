package com.mission.test.dp;

public class RodCutting {

	public static void main(String[] args) {
		RodCutting r = new RodCutting();
		int[] arr = { 1, 5, 8, 9, 10, 17, 17, 20 };
		int price = r.getMaxPrice(arr);
		System.out.println("Maximum obtainable price : " + price);
	}

	public int getMaxPrice(int[] price, int length) {
		if (length == 0)
			return 0;

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < length; i++)
			max = Math.max(max, price[i] + getMaxPrice(price, length - 1 - i));

		return max;
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
