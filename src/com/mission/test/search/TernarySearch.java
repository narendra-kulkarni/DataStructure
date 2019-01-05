package com.mission.test.search;

public class TernarySearch {

	// Ternary search in its worst case does more comparisons than the binary search. So generally binary search
	// is preferred over ternary search.

	public int ternarySearch(int arr[], int low, int high, int key) {
		if (high >= low) {
			int mid1 = low + (high - low)/3;
			int mid2 = mid1 + (high - low)/3;

			// If x is present at the mid1
			if (arr[mid1] == key)  return mid1;

			// If x is present at the mid2
			if (arr[mid2] == key)  return mid2;

			// If x is present in left one-third
			if (arr[mid1] > key) return ternarySearch(arr, low, mid1 - 1, key);

			// If x is present in right one-third
			if (arr[mid2] < key) return ternarySearch(arr, mid2 + 1, high, key);

			// If x is present in middle one-third
			return ternarySearch(arr, mid1 + 1, mid2 - 1, key);
		}

		// We reach here when element is not present in array
		return -1;
	}

	public static void main(String[] args) {
		TernarySearch s = new TernarySearch();
		int key = 5;
		int[] arr = {4, 9, 2, 6, -1, 5, 10, 3};
		int index = s.ternarySearch(arr, 0, arr.length, key);
		System.out.println(key + " is found at : " + index);	// 5
	}
}
