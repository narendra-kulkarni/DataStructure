package com.mission.test.sort;

public class CountingSort {

	// arr is the original array to be sorted. countArr has length equal to the range of the numbers.
	public int[] countSort(int[] arr, int[] countArr) {
		int[] sortedArr = new int[arr.length];

		for (int i = 0; i < arr.length; i++)
			countArr[arr[i]]++;

		for (int i = 2; i < countArr.length; i++)
			countArr[i] += countArr[i - 1];

		for (int i = 0; i < arr.length; i++) {
			sortedArr[countArr[arr[i]] - 1] = arr[i];
			countArr[arr[i]]--;
		}

		return sortedArr;
	}

	public static void main(String[] args) {
		CountingSort s = new CountingSort();
		int[] arr = {3, 1, 2, 1, 2, 1, 3, 3, 3, 1, 2, 1};
		int[] sArr = s.countSort(arr, new int[4]);
		print(sArr);
	}

	private static void print(int[] arr) {
		for (int temp : arr)
			System.out.print(temp + " ");
	}
}
