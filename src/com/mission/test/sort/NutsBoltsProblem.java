package com.mission.test.sort;

public class NutsBoltsProblem {

	public void matchPairs(char[] nuts, char[] bolts, int low, int high) {
		if (low < high) {
			int pivot = partition(nuts, low, high, bolts[high]);
			partition(bolts, low, high, nuts[pivot]);
			matchPairs(nuts, bolts, low, pivot - 1);
			matchPairs(nuts, bolts, pivot + 1, high);
		}
	}

	private int partition(char[] arr, int low, int high, char pivot) {
		int i = low;

		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				swap(arr, i++, j);
			} else if (arr[j] == pivot) {
				swap(arr, j--, high);
			}
		}

		swap(arr, i, high);
		return i;
	}

	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
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
