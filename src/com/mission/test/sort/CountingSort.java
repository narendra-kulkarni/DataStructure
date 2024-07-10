package com.mission.test.sort;

public class CountingSort {

	// arr is the original array to be sorted.
	// countArr has length equal to the range of the numbers.
	// arr = {3, 1, 2, 1, 2, 1, 3, 3, 3, 1, 2, 1, 0}
	public int[] countSort(int[] arr, int[] countArr) {
		int[] sortedArr = new int[arr.length];

		// Count the occurrence of each number in the input array
		// countArr = {1, 5, 3, 4}
		for (int num : arr)
			countArr[num]++;

		// Calculate cumulative counts : This is essentially 1 associative index array
		// countArr = {1, 6, 9, 13}
		for (int i = 1; i < countArr.length; i++)
			countArr[i] += countArr[i - 1];

		// Iterate on the input array.
		// Get index for each element and place it in the sorted array.
		// Reduce count for that element
		for (int j : arr) {
            sortedArr[countArr[j] - 1] = j;
            countArr[j]--;
        }

		return sortedArr;
	}

	public static void main(String[] args) {
		CountingSort s = new CountingSort();
		int[] arr = {3, 1, 2, 1, 2, 1, 3, 3, 3, 1, 2, 1, 0};
		int[] sArr = s.countSort(arr, new int[4]);
		print(sArr);
	}

	private static void print(int[] arr) {
		for (int temp : arr)
			System.out.print(temp + " ");
	}
}
