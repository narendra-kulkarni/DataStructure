package com.mission.test.search;

public class BinarySearch {

	public static void main(String[] args) {
		BinarySearch s = new BinarySearch();
		int[] arr = {5, 6, 7, 8, 9, 10, 11, 2, 3, 4};
		int num = s.findNumberOfRotations(arr, 0, arr.length - 1);
		System.out.println("The number of rotations are : " + num);
		System.out.println("Index of the search key is : " + s.searchRotatedArray(arr, 10));

		int[] array = {4, 5, 6, 7, 8, 9, 10};
		System.out.println("Index of the search key is : " + s.binarySearch(array, 8));
	}

	// In binary search stop condition is low > high. The loop needs to run till low is smaller than or equal to 
	// the high value. Mid is calculated as (low + (high - low) / 2) so as to avoid overflow of numbers. Every time
	// we need to pick half of the entire list for searching, to achieve O(logn) results. In rotated array check
	// which side is fully sorted and take the decision based on that.

	// This method is recursive, so we need to pass values of low and high to the new method call.
	// To find the number of rotations we must find the minimum number in the rotated array.
	public int findNumberOfRotations(int[] arr, int low, int high) {
		if (low > high) 
			return 0;

		int mid = low + (high - low) / 2;

		if (arr[mid] < arr[mid - 1])
			return arr.length - mid;
		else if (arr[low] <= arr[mid]) // first half is sorted, so minimum number will be found in the later half
			return findNumberOfRotations(arr, mid + 1, high);
		else 
			return findNumberOfRotations(arr, low, mid - 1);
	}

	// This method is iterative so there is no need to pass values of low and high
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

	public int searchRotatedArray(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (arr[mid] == key)
				return mid;
			// left half is sorted
			else if (arr[low] < arr[mid]) {
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
