package com.mission.test.dp;

public class LPS {

	public static void main(String[] args) {
		LPS l = new LPS();
		String str = "geeks for geeks";
		int[][] memo = new int[str.length()][str.length()];
		int max = l.getMaxLength(str, 0, str.length() - 1, memo);
		System.out.println("Longest palindromic subsequence length : " + max);
	}

	public int getMaxLength(String str, int i, int j, int[][] memo) {
		if (i == j)
			return 1;

		if (str.charAt(i) == str.charAt(j) && (i + 1) == j)
			return 2;

		if (memo[i][j] != 0)
			return memo[i][j];

		int result = 0;
		if (str.charAt(i) == str.charAt(j))
			result = 2 + getMaxLength(str, i + 1, j - 1, memo);
		else
			result = Math.max(getMaxLength(str, i + 1, j, memo), getMaxLength(str, i, j - 1, memo));

		memo[i][j] = result;
		return result;
	}
}
