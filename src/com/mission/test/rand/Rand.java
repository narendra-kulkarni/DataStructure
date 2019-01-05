package com.mission.test.rand;

import java.util.Random;

public class Rand {

	public static void main(String[] args) {
		// To get an integer random number in a certain range
		// (int)(Math.random * range) + lower 	:	range = upper - lower + 1   OR
		// new Random().nextInt(range) + lower  :   range = upper - lower + 1

		System.out.println("Numbers between 2 and 5\n");

		// E.g. To get random numbers between 2 to 5 : range will be 5 - 2 + 1 = 4
		for (int i = 0; i < 10; i++) {
			int rand = new Random().nextInt(4) + 2;
			//int rand = (int)(Math.random() * 4) + 2;
			System.out.println(rand);
		}

		System.out.println("-------------------------\nNumbers between 0 and 5\n");

		// E.g. To get random numbers between 0 to 5 : range will be 5 - 0 + 1 = 6
		for (int i = 0; i < 10; i++) {
			//int rand = new Random().nextInt(6);
			int rand = (int)(Math.random() * 6);
			System.out.println(rand);
		}
	}

	public static int[] getRandomArray(int len, int low, int high) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++)
			arr[i] = low + new Random().nextInt(high - low + 1);
		return arr;
	}
}
