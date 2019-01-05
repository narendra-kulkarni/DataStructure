package com.mission.test.sort;

public class QuickSort {

	public void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pivot = partition(arr, low, high);
			quickSort(arr, low, pivot - 1);
			quickSort(arr, pivot + 1, high);
		}
	}

	// Pivot is picked as the low. After partitioning, pivot value will be at its right position
	private int partition(int[] arr, int low, int high) {
		int pivot = low;

		while (low < high) {
			while (arr[low] <= arr[pivot])	// equality is checked to take care of duplicate elements
				low++;

			while (arr[high] > arr[pivot])
				high--;

			if (low < high)
				swap(arr, low, high);
		}

		swap(arr, pivot, high);
		return high;			// After partition return new position of pivot
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		QuickSort s = new QuickSort();
		int arr[] = {7, -3, 4, 6, 23, 10, 1, 4};
		s.quickSort(arr, 0, arr.length - 1);
		print(arr);
	}

	private static void print(int[] arr) {
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println("\n");
	}
}
