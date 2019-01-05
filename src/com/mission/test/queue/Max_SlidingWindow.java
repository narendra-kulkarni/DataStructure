package com.mission.test.queue;

import java.util.Deque;
import java.util.LinkedList;

public class Max_SlidingWindow {

	// For this problem doubly ended queue is used where elements can be added/removed from the front and also
	// from the end. We will maintain the indices of the elements in the queue and not the actual values.

	// The idea here is to maintain the largest element of the current window at the front of the queue as and
	// how we add and remove the elements in the queue. Although it might appear that the complexity of the below
	// algorithm is n square, the actual complexity is O(n), since all the elements are handled at most twice.
	public int[] getMaxArray(int[] arr, int windowSize) {
		int[] maxArray = new int[arr.length - windowSize + 1];
		// Indices will be stored in the queue and not the actual elements
		Deque<Integer> q = new LinkedList<>();

		// Only older elements are removed from the front, all other operations are performed from the back.

		// First process the elements of window size. This is just the setup before processing the rest of the 
		// elements.
		int i = 0;
		for (; i < windowSize; i++) {
			// Get rid of smaller elements. This ensures the larger element is at the front
			while (!q.isEmpty() && arr[q.getLast()] <= arr[i])
				q.removeLast();

			// Add the new element from the back
			q.addLast(i);
		}

		// Process the remaining elements.
		for (; i < arr.length; i++) {
			maxArray[i - windowSize] = arr[q.getFirst()]; // maximum element of the window before sliding

			// Get rid of smaller elements
			while (!q.isEmpty() && arr[q.getLast()] <= arr[i])
				q.removeLast();

			// Get rid of older elements which are no longer in the range of the current window
			while (!q.isEmpty() && q.getFirst() <= (i - windowSize))
				q.removeFirst();

			// Add the new element from the back
			q.addLast(i);
		}

		maxArray[arr.length - windowSize] = arr[q.getFirst()];
		return maxArray;
	}

	public static void main(String[] args) {
		Max_SlidingWindow w = new Max_SlidingWindow();
		int[] arr = {2, 4, 3, 5, 1, 0, 3, 10, 8, 9}; // 4, 5, 5, 5, 3, 10, 10, 10
		print(arr, w.getMaxArray(arr, 3));
	}

	private static void print(int[] arr, int[] maxArr) {
		System.out.print("Original array is : ");
		for (int i : arr)
			System.out.print(i + " ");
		System.out.print("\n");

		System.out.print("Maximum array is : ");
		for (int i : maxArr)
			System.out.print(i + " ");
		System.out.println("\n");
	}
}
