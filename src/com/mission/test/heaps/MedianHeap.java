package com.mission.test.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

// Median heap is a special type of data structure which internally makes use of two heaps one min heap and
// other max heap to find out median of the given data (stream of data). Max heap (left heap) is used to keep
// smaller data and min heap (right heap) is used to keep larger data.
public class MedianHeap {

	private PriorityQueue<Integer> minHeap;

	private PriorityQueue<Integer> maxHeap;

	public MedianHeap() {
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	}

	// The idea here is to add the numbers successively to max heap and min heap. At any given point in time,
	// the max heap will have the largest element of first half of data at the root and min heap will have the
	// smallest element of second half of data at the root.
	public void addToStream(int num) {
		if (maxHeap.size() == minHeap.size())
			maxHeap.offer(num);
		else
			minHeap.offer(num);

		// The max heap data should be smaller than the min heap data. If this property fails
		// after adding a new number, then readjust the roots of both the heaps.
		if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
			int temp = maxHeap.poll();
			maxHeap.add(minHeap.poll());
			minHeap.add(temp);
		}
	}

	// If current number of numbers is even then median is average (mean) of two middle numbers. If current
	// number of numbers is odd then median is the middle number. For even numbers get the median as the average
	// of root of both the heaps, for odd numbers get the median as root of the max heap.
	public double getMedian() {
		if (minHeap.size() == maxHeap.size())
			return (double)(minHeap.peek() + maxHeap.peek()) / 2;
		else
			return maxHeap.peek();
	}

	public static void main(String[] args) {
		MedianHeap h = new MedianHeap();
		h.addToStream(1);
		printMedian(h); // 1

		h.addToStream(5);
		h.addToStream(10);
		printMedian(h); // 5

		h.addToStream(4);
		h.addToStream(2);
		h.addToStream(3);
		h.addToStream(9);
		h.addToStream(15);
		printMedian(h); // 4.5
	}

	private static void printMedian(MedianHeap h) {
		System.out.println("The median is : " + h.getMedian());
	}
}
