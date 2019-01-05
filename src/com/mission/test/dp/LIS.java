package com.mission.test.dp;

import java.util.Arrays;

public class LIS {

	// The variation to this problem is Maximum sum increasing subsequence
	// There instead of length of the sequence, sum will be stored in the memopad.

	public int getLIS(int[] arr) {
		int[] memo = new int[arr.length];

		// Initialize all elements of the memopad. At every element the value of the subsequence
		// will be 1 by default.
		for (int i = 0; i < arr.length; i++)
			memo[i] = 1;

		// Calculate the values in the bottom up manner
		for (int i = 1; i < arr.length; i++)
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && memo[i] < memo[j] + 1)
					memo[i] = memo[j] + 1;
			}

		// Find out the maximum value for the subsequence
		return Arrays.stream(memo).max().getAsInt();
	}

	public static void main(String[] args) {
		LIS l = new LIS();
		int[] arr = {4, 9, 2, 1, 7, 5, 6};
		int max = l.getLIS(arr);
		System.out.println("Longest increasing subsequence : " + max);
	}
}
