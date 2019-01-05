package com.mission.test.sort;

public class NutsBoltsProblem {

	public void matchPairs(char[] nuts, char[] bolts, int i, int j) {
		if (i < j) {
			int pivot = partition(nuts, i, j, bolts[j]);
			partition(bolts, i, j, nuts[pivot]);
			matchPairs(nuts, bolts, i, pivot - 1);
			matchPairs(nuts, bolts, pivot + 1, j);
		}
	}

	private int partition(char[] arr, int low, int high, char c) {
		int i = low;
		for (int k = low; k < high; k++) {
			if (arr[k] < c) {
				char temp = arr[i];
				arr[i] = arr[k];
				arr[k] = temp;
				i++;
			} else if (arr[k] == c) {
				char temp = arr[high];
				arr[high] = arr[k];
				arr[k] = temp;
				k--;
			}
		}
		char temp = arr[i];
		arr[i] = arr[high];
		arr[high] = temp;

		return i;
	}

	public static void main(String[] args) {
		NutsBoltsProblem p = new NutsBoltsProblem();
		char[] nuts = { '@', '#', '$', '%', '^', '&' };
		char[] bolts = { '$', '%', '&', '^', '@', '#' };

		p.matchPairs(nuts, bolts, 0, nuts.length - 1);

		System.out.println("Matched pairs are : ");
		printArray(nuts);
		printArray(bolts);
	}

	private static void printArray(char[] arr) {
		for (char temp : arr)
			System.out.print(temp + " ");
		System.out.println();
	}
}
