package com.mission.test.backtracking;

public class StringPermutations {

	// Anagrams generation
	
	public void permute(char[] arr, int low, int high) {
		if (low == high)
			print(arr);
		else {
			for (int i = low; i <= high; i++) {
				swap(arr, low, i);
				permute(arr, low + 1, high);
				swap(arr, low, i);
			}
		}
	}

	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private void print(char[] arr) {
		for (char ch : arr)
			System.out.print(ch);
		System.out.println();
	}

	public void permute1(char[] arr, int index, boolean[] visited, StringBuffer buff) {
		if (index == arr.length)
			System.out.println(buff.toString());
		else {
			for (int i = 0; i < arr.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					StringBuffer temp = new StringBuffer(buff);
					temp.append(arr[i]);
					permute1(arr, index + 1, visited, temp);
					visited[i] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		StringPermutations p = new StringPermutations();
		char[] arr = {'A', 'B', 'C'};
		p.permute(arr, 0, arr.length - 1);

		System.out.println("-----------------------");

		boolean[] visited = new boolean[arr.length];
		StringBuffer buff = new StringBuffer();
		p.permute1(arr, 0, visited, buff);
	}
}
