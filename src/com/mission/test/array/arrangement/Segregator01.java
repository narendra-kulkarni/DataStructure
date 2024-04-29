package com.mission.test.array.arrangement;

public class Segregator01 {

	// Segregate 0s on the left side and 1s on the right side. This can be done using counting sort
	// in two passes. For one pass approach, following method can be used. Time complexity O(n) and
	// space complexity O(1) 

	public void segregate(int[] arr) {
		int left = 0;
		int right = arr.length - 1;

		while (left < right) {
			while (arr[left] == 0 && left < right)
				left++;

			while (arr[right] == 1 && left < right)
				right--;

			if (left < right) {
				arr[left++] = 0;
				arr[right--] = 1;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = {0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1};
		Segregator01 s = new Segregator01();
		s.segregate(arr);

		for (int temp : arr)
			System.out.print(temp + " ");
	}
}
