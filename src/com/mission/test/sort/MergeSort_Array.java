package com.mission.test.sort;

public class MergeSort_Array {

	public void mergeSort(int[] arr, int[] helper, int low, int high) {
		if (low < high) {
			int mid = low + (high - low) / 2;
			mergeSort(arr, helper, low, mid);
			mergeSort(arr, helper, mid + 1, high);
			merge(arr, helper, low, mid, high);
		}
	}

	// Merge operation on array needs helper array where the current range will be copied. The helper array
	// will be used for comparing the elements.
	private void merge(int[] arr, int[] helper, int low, int mid, int high) {
		for (int k = low; k <= high; k++)
			helper[k] = arr[k];

		int i = low;
		int j = mid + 1;
		int k = low;

		while (i <= mid && j <= high) {
			if (helper[i] <= helper[j]) {
				arr[k] = helper[i];
				i++;
			}
			else {
				arr[k] = helper[j];
				j++;
			}
			k++;
		}

		// Only first half needs to be copied. The other half is already in the main array.
		while (i <= mid) {
			arr[k] = helper[i];
			i++;
			k++;
		}
	}

	public static void main(String[] args) {
		MergeSort_Array s = new MergeSort_Array();
		int arr[] = {7, -3, 4, 6, 23, 10, 1, 4};
		s.mergeSort(arr, new int[arr.length], 0, arr.length - 1);
		print(arr);
	}

	private static void print(int[] arr) {
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println("\n");
	}
}
