package com.mission.test.misc.binary;

public class Addition {

	// 0100		->		4
	// 0101		->		5
	// ----
	// 1001		->		9
	
	public static int add(int x, int y) {
		while (y != 0) {
			int carry = x & y;	// carry is obtained by and operator
			x = x ^ y;			// sum is obtained by xor operator
			y = carry << 1;		// carry needs to be left shifted by 1
		}

		return x;
	}

	public static void main(String[] args) {
		System.out.println(add(4, 5));
	}
}
