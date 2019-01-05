package com.mission.test.array;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

	public static void main(String[] args) {
		Subsequence s = new Subsequence();
		int[] arr = {5, 9, 3};
		System.out.println("Subsequences");
		s.subsequences(arr);
		//s.subsequencesBT(arr);
		System.out.println("\nSubarrays");
		s.subarrays(arr);
	}

	// For array/string of size n, total number of combinations/subsequences (power set) possible are
	// [pow(2, n) - 1]. So run the loop for those many times and check for set bits. If the bit
	// is set print the array element for the corresponding index. This problem can also be solved with
	// backtracking since we want the exhaustive set. The problem will not be solved by dynamic programming
	// since each combination is unique and there is no overlap.
	public void subsequences(int[] arr) {
		for (int i = 1; i < (1 << arr.length); i++) {
			for (int j = 0; j < arr.length; j++)
				if ((i & (1 << j)) != 0)
					System.out.print(arr[j] + " ");
			System.out.println();
		}
	}

	public void subsequencesBT(int[] arr) {
		List<Integer> list = new ArrayList<>();
		subsequence(arr, 0, list);
	}

	private void subsequence(int[] arr, int i, List<Integer> list) {
		if (i == arr.length) {
			for (int temp : list)
				System.out.print(temp + " ");
			System.out.println();
		} else {
			// subsequence with ith number
			list.add(arr[i]);
			subsequence(arr, i + 1, list);
			
			// subsequence without ith number
			list.remove(new Integer(arr[i]));
			subsequence(arr, i + 1, list);
		}
	}

	public void subarrays(int[] arr) {
		// Pick starting point
		for (int i = 0; i < arr.length; i++) {
			List<Integer> list = new ArrayList<>();
			// Pick ending point
			for (int j = i; j < arr.length; j++) {
				list.add(arr[j]);
				// print
				for (int temp : list)
					System.out.print(temp + " ");
				System.out.println();
			}
		}
	}
}
