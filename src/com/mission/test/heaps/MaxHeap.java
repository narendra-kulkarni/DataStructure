package com.mission.test.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeap {

	// Priority queue is the java class which implements heap.
	private PriorityQueue<Integer> maxHeap;
	//private PriorityQueue<Integer> minHeap;

	// By default priority queue is min heap. To implement max heap we hap to provide new comparator
	// New comparator can be from collections API or custom comparator.
	public MaxHeap() {
		//minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	}

	// To add an element in the queue add or offer method can be used
	public void add(int num) {
		maxHeap.offer(num);
	}

	// To remove an element poll or remove method is used. Highest priority number will be removed
	public int remove() {
		return maxHeap.poll();
	}

	// To get the maximum element (root in the max heap/element with the highest priority) without
	// removing it from the heap peek method can be used.
	public int max() {
		return maxHeap.peek();
	}

	public static void main(String[] args) {
		MaxHeap h = new MaxHeap();
		h.add(4);
		h.add(7);
		h.add(1);
		h.add(3);

		System.out.print(h.max());
		System.out.print(h.remove());
		System.out.print(h.max());
	}
}
