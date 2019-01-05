package com.mission.test.heaps;

import java.util.PriorityQueue;

public class KthLargestStatistics {

	private int k;

	private PriorityQueue<Integer> minHeap;

	// To get the kth largest element we will create minheap of size k. Since it is a min heap kth largest
	// element will be found at the root.
	public KthLargestStatistics(int k) {
		this.k = k;
		minHeap = new PriorityQueue<>(k);
	}

	// The kth largest element can be found at root in O(1)
	public int findKthLargest() throws Exception {
		if (minHeap.size() < k)
			throw new Exception("Element not found.");
		return minHeap.peek();
	}

	// If the size of the heap is less than k, then add the number in the heap. If the size is equal to
	// or greater than k, then check whether the given number is greater than the current root of the heap.
	// If yes, remove the root and add the new number in the heap. This operation gets performed in O(logn)
	public void addToStream(int num) {
		if (minHeap.size() < k)
			minHeap.offer(num);
		else {
			if (minHeap.peek() < num) {
				minHeap.poll();
				minHeap.offer(num);
			}
		}
	}

	public static void main(String[] args) {
		int k = 4;
		KthLargestStatistics s = new KthLargestStatistics(k);
		s.addToStream(5);
		s.addToStream(7);
		s.addToStream(3);
		s.addToStream(9);
		s.addToStream(1);

		try {
			System.out.println(k + "th largest element is : " + s.findKthLargest());   // 3
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
