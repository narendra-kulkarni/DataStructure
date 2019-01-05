package com.mission.test.dp;

public class PalindromePartition {

	public static void main(String[] args) {
		PalindromePartition p = new PalindromePartition();
		String str = "ababbbabbababa";
		int[][] memo = new int[str.length()][str.length()];
		for (int i = 0; i < str.length(); i++)
			for (int j = 0; j < str.length(); j++)
				memo[i][j] = -1;
		int res = p.getMinCuts(str, 0, str.length() - 1, memo);
		System.out.println("Minimum cuts required are : " + res);
	}

	public int getMinCuts(String str, int i, int j, int[][] memo) {
		if (i == j)
			return 0;

		if (memo[i][j] != -1)
			return memo[i][j];

		if (isPalindrome(str, i, j)) {
			memo[i][j] = 0;
			return 0;
		}

		int result = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int temp = getMinCuts(str, i, k, memo) + 1 + getMinCuts(str, k + 1, j, memo);
			if (temp < result)
				result = temp;
		}

		memo[i][j] = result;
		return result;
	}

	private boolean isPalindrome(String str, int i, int j) {
		while (i < j) {
			if (str.charAt(i) == str.charAt(j)) {
				i++;
				j--;
			} else
				return false;
		}
		return true;
	}
}
