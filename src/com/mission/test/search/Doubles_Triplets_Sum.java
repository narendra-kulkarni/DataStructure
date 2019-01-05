package com.mission.test.search;

import java.util.Arrays;

public class Doubles_Triplets_Sum {

	public static void main(String[] args) {
		int[] arr = {5, 1, 3, 4, 7};
		int sum = 12;

		findDoubles(arr.clone(), sum);	// O(nlogn)
		findTriplets(arr.clone(), sum);	// O(n square)
	}

	// Both these methods, are based on sorting. After sorting initialize two variables to be the corner
	// of the array and calculate sum. If sum equals to the desired result then we are done else if sum is
	// less then increment lower pointer else decrement higher pointer

	private static void findDoubles(int[] arr, int sum) {
		Arrays.sort(arr);

		int i = 0, j = arr.length - 1;
		while (i < j) {
			int temp = arr[i] + arr[j];

			if (temp == sum) {
				System.out.println("{" + arr[i] + "," + arr[j] + "}");
				j--;
			}
			else if (temp < sum)
				i++;
			else
				j--;
		}
	}

	private static void findTriplets(int[] arr, int sum) {
		Arrays.sort(arr);

		for (int i = 0; i < arr.length - 2; i++) {
			int j = i + 1;
			int k = arr.length - 1;

			while (j < k) {
				int temp = arr[i] + arr[j] + arr[k];

				if (temp == sum) {
					System.out.println("{" + arr[i] + "," + arr[j] + "," + arr[k] + "}");
					k--;
				}
				else if (temp < sum)
					j++;
				else
					k--;
			}
		}
	}
}
