package com.mission.test.search;

public class BinarySearch {

	public static void main(String[] args) {
		BinarySearch s = new BinarySearch();
		int[] arr = {5, 6, 7, 8, 9, 10, 11, 2, 3, 4};
		int num = s.findNumberOfRotations(arr);
		System.out.println("The number of rotations are : " + num);
		System.out.println("Index of the search key is : " + s.searchRotatedArray(arr, 10));

		int[] array = {4, 5, 6, 7, 8, 9, 10};
		System.out.println("Index of the search key is : " + s.binarySearch(array, 8));
	}

	// In binary search stop condition is low > high. The loop needs to run till low is smaller than or equal to
	// the high value. Mid is calculated as (low + (high - low) / 2) to avoid integer overflow. The "low + high"
	// in (low + high) / 2, can overflow if low and high are large numbers.

	// Every turn we pick half of the entire list for searching, to achieve O(log N) results. In rotated array check
	// which side is fully sorted and take the decision based on that.

	public int binarySearch(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (arr[mid] == key)
				return mid;
			else if (key > arr[mid])
				low = mid + 1;
			else
				high = mid - 1;
		}

		return -1;
	}

	public int findNumberOfRotations(int[] arr) {
		int low = 0;
		int high = arr.length - 1;

		// If the array is not rotated at all
		if (arr[low] <= arr[high]) {
			return 0;
		}

		while (low <= high) {
			int mid = low + (high - low) / 2;

			// Check if mid is the rotation point
			if (mid > 0 && arr[mid] < arr[mid - 1]) {
				return mid;
			}

			// If the first half is sorted, rotation point is in the second half.
			// Otherwise, rotation point is in the first half
			if (arr[low] <= arr[mid]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return 0;
	}

	public int searchRotatedArray(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (arr[mid] == key)
				return mid;
			// left half is sorted
			else if (arr[low] <= arr[mid]) {
				// If key is in the range of left sorted half
				if (key >= arr[low] && key < arr[mid])
					high = mid - 1;
				else
					low = mid + 1;
			} else { // right half is sorted
				// If key is in the range of the right sorted half
				if (key <= arr[high] && key > arr[mid])
					low = mid + 1;
				else
					high = mid - 1;
			}
		}

		return -1;
	}
}
