package com.mission.test.selection;

import java.util.Random;

public class QuickSelect {

	// Quick select is reduced quick sort algorithm. Here we take random pivot and try to partition around it.
	// If after partition the pivot index is the kth order statistics, we are done. If not then we choose the
	// half of the array depending on the pivot value.

	private int quickSelect(int[] arr, int first, int last, int k) {
		if (first <= last) {
			int pivot = partition(arr, first, last);
			if (pivot == k)
				return arr[pivot];
			else if (pivot < k)
				return quickSelect(arr, pivot + 1, last, k);
			else
				return quickSelect(arr, first, pivot - 1, k);
		}
		return Integer.MIN_VALUE;
	}

	public int partition(int[] arr, int first, int last) {
		int pivot = first + new Random().nextInt(last - first + 1);
		swap(arr, last, pivot);

		for (int i = first; i < last; i++) {
			if (arr[i] > arr[last]) {
				swap(arr, i, first);
				first++;
			}
		}

		swap(arr, first, last);
		return first;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		QuickSelect s = new QuickSelect();
		int k = 3;
		int[] arr = {4, 7, 2, 9, 1, 6, 8, -1};
		int temp = s.quickSelect(arr, 0, arr.length - 1, k - 1);
		System.out.print("The number " + k + " largest is : " + temp); // 7
	}
}
