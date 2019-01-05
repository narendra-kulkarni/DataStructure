package com.mission.test.rand;

import java.util.Random;

public class Shuffle {

	public void shuffle(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int rand = new Random().nextInt(arr.length);
			swap(arr, i, rand);
		}
	}

	private void swap(int[] arr, int i, int rand) {
		int temp = arr[i];
		arr[i] = arr[rand];
		arr[rand] = temp;
	}

	public static void main(String[] args) {
		Shuffle s = new Shuffle();
		int[] arr = {3, 9, 1, 6, 2, 10, -2, 0, 5};
		s.shuffle(arr);
		printArray(arr);
	}

	private static void printArray(int[] arr) {
		for (int temp : arr)
			System.out.print(temp + " ");
		System.out.println();
	}
}
