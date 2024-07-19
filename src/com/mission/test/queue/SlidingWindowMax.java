package com.mission.test.queue;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMax {

	// For this problem doubly ended queue is used where elements can be added/removed from the front and also
	// from the end. We will maintain the indices of the elements in the queue and not the actual values.
	// The idea here is to maintain the largest element of the current window at the front of the queue as and
	// how we add and remove the elements in the queue. Complexity - O(N)
	public int[] slidingWindowMax(int[] nums, int k) {
		int[] maxArray = new int[nums.length - k + 1];
		Deque<Integer> deque = new LinkedList<>();
		int index = 0;

		for (int i = 0; i < nums.length; i++) {
			// Remove elements outside the current window
			if (!deque.isEmpty() && deque.peekFirst() == i - k) {
				deque.removeFirst();
			}

			// Remove smaller elements from the rear
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				deque.removeLast();
			}

			deque.addLast(i);

			// Print max of current window
			if (i >= k - 1) {
				maxArray[index++] = nums[deque.peekFirst()];
			}
		}

		return maxArray;
	}

	/********************************************************/

	// Problem Statement: Given an array of integers and a window size k,
	// find the maximum element in each contiguous subarray of size k as
	// the window slides from left to right.
	public static void main(String[] args) {
		SlidingWindowMax w = new SlidingWindowMax();
		int[] arr = {2, 4, 3, 5, 1, 0, 3, 10, 8, 9}; // 4, 5, 5, 5, 3, 10, 10, 10
		print(arr, w.slidingWindowMax(arr, 3));
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
