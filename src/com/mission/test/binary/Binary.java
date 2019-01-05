package com.mission.test.binary;

public class Binary {

	// Brian Kernighan’s Algorithm
	public int countSetBits(int i) {
		int count = 0;
		while (i > 0) {
			i &= i - 1;
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		Binary b = new Binary();
		System.out.println("The number of set bits : " + b.countSetBits(7));
	}
}
