package com.mission.test.search;

public class MaxIndexDiff {

	// Given an array A[], find out maximum j - i such that A[j] > A[i] 

	public int findMaxDiff(int[] arr) {
		int[] leftMins = new int[arr.length];
		int[] rightMaxs = new int[arr.length];

		leftMins[0] = arr[0];
		for (int i = 1; i < arr.length; i++) 
			leftMins[i] = Math.min(arr[i], leftMins[i - 1]);

		rightMaxs[arr.length - 1] = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--)
			rightMaxs[i] = Math.max(arr[i], rightMaxs[i + 1]);

		int i = 0, j = 0, maxDiff = -1;
		while (i < arr.length && j < arr.length) {
			if (leftMins[i] < rightMaxs[j]) {
				maxDiff = Math.max(maxDiff, j - i);
				j++;
			} else
				i++;
		}

		return maxDiff;
	}

	public static void main(String[] args) {
		MaxIndexDiff d = new MaxIndexDiff();
		int[] arr = {34, 8, 10, 3, 2, 80, 30, 33, 1};
		System.out.println("Maximum difference : " + d.findMaxDiff(arr));
	}
}
