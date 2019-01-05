package com.mission.test.sort;

public class BasicSort {

	public static void main(String[] args) {
		BasicSort s = new BasicSort();
		int arr[] = {7, -3, 4, 6, 23, 10, 1};

		s.bubbleSort(arr.clone());
		s.insertionSort(arr.clone());
		s.selectionSort(arr.clone());
	}

	// Simplest sorting algorithm. The only significant advantage is it can detect sorted array in the first pass,
	// if the internal condition does not get satisfied.
	public void bubbleSort(int[] arr) {
		// Outer for loop is used to restrict the numbers to visit in the inner loop
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				// The numbers are compared with only the next element.
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		print(arr);
	}

	// This is a stable sorting algorithm in which in every iteration a number will be inserted into the correct 
	// position in already sorted list. This is an online algorithm meaning that it keeps data sorted as and
	// how it becomes available (good for stream of data). This is also an in place algorithm, since it does
	// not need any auxiliary space. Complexity best, average, worst - O(n square). This algorithm is very efficient
	// for small data. Adaptive algorithm - works well for (fully/partially) presorted data
	public void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int j = i - 1;
			int cur = arr[i];

			while (j >= 0 && arr[j] > cur) {  // if arr[j] >= cur won't make it stable. So don't check for equality 
				arr[j + 1] = arr[j];		  // Shift the elements ahead to make place for the current one	
				j--;
			}

			arr[j + 1] = cur;				  // Save the current element	
		}

		print(arr);
	}

	// In this algorithm in every iteration minimum element is picked and placed at the current position.
	// This is an in place algorithm with complexity O(n square). This is not a stable sort.
	public void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;					  // Store the index in the minimum

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min])
					min = j;
			}

			int temp = arr[i];				  // Swap current minimum with current element
			arr[i] = arr[min];
			arr[min] = temp;
		}

		print(arr);
	}

	private void print(int[] arr) {
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println("\n");
	}
}
