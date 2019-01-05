package com.mission.test.dp;

public class MaxSumSubArray {

	public static void main(String[] args) {
		MaxSumSubArray m = new MaxSumSubArray();
		int[] arr = { -2, -3, 4, -1, -2, 1, 5, -3 };
		int res = m.maxSumSubarray(arr);
		System.out.println("Maximum sum subarray is : " + res);
	}

	// This modified version takes care of the case where all elements
	// are negative
	public int maxSumSubarray(int arr[]) {
		int max_so_far = arr[0];
		int cur_max = arr[0];

		for (int i = 1; i < arr.length; i++) {
			cur_max = Math.max(arr[i], cur_max + arr[i]);
			max_so_far = Math.max(max_so_far, cur_max);
		}

		return max_so_far;
	}

	// Kadane's algorithm. The algorithm does not take care of the case
	// where all elements are negative. We will need to add one more loop
	// before calling this method to handle the above case.
	public int kadane(int[] arr) {
		int max_so_far = 0;
		int max_ending_here = 0;

		for (int i = 0; i < arr.length; i++) {
			max_ending_here += arr[i];
			if (max_ending_here < 0)
				max_ending_here = 0;
			if (max_ending_here > max_so_far)
				max_so_far = max_ending_here;
		}

		return max_so_far;
	}
}
