package com.mission.test.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapSort_PQ {

	public void sort(int[] arr) {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		Arrays.stream(arr).forEach(q::offer);
		for (int i = 0; i < arr.length; i++)
			arr[i] = q.poll();
	}

	public static void main(String[] args) {
		HeapSort_PQ s = new HeapSort_PQ();
		int[] arr = {4, 2, 8, 9, 5, -1, 10, 7};
		s.sort(arr);
		Arrays.stream(arr).forEach(t -> System.out.print(t + " "));
	}
}
