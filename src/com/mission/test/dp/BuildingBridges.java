package com.mission.test.dp;

import java.util.Arrays;

public class BuildingBridges {

	public static void main(String[] args) {
		// Consider a 2-D map with a horizontal river passing through its center.
		// There are n cities on the southern bank with x-coordinates a(1) … a(n)
		// and n cities on the northern bank with x-coordinates b(1) … b(n).
		// You want to connect as many north-south pairs of cities as possible with bridges
		// such that no two bridges cross. When connecting cities, you can only connect
		// city a(i) on the northern bank to city b(i) on the southern bank.

		BuildingBridges bb = new BuildingBridges();
		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8 }; // bank A
		int[] b = { 5, 1, 8, 3, 4, 2, 6, 7 }; // bank B
		int res = bb.getMaximumBridges(a, b);
		System.out.println("Maximum bridges possible are : " + res);
	}

	public int getMaximumBridges(int[] a, int[] b) {
		Pair[] pairs = new Pair[a.length];
		for (int i = 0; i < a.length; i++)
			pairs[i] = new Pair(a[i], b[i]);

		// sort array b in increasing order. This will sort a
		// correspondingly
		Arrays.sort(pairs, (x, y) -> x.b - y.b);

		// save the sorted Array A in the temp array
		int temp[] = new int[a.length];
		for (int i = 0; i < a.length; i++)
			temp[i] = pairs[i].a;

		// Send sorted array A for longest increasing subsequence
		return lis(temp);
	}

	private int lis(int[] a) {
		int[] memo = new int[a.length];
        Arrays.fill(memo, 1);

		for (int i = 1; i < memo.length; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && memo[i] < memo[j] + 1)
					memo[i] = memo[j] + 1;
			}
		}

		return Arrays.stream(memo).max().getAsInt();
	}

	private class Pair {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}