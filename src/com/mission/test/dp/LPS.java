package com.mission.test.dp;

public class LPS {

	public static void main(String[] args) {
		LPS l = new LPS();
		String str = "geekgs";
		int max = l.getMaxLength(str);
		System.out.println("Longest palindromic subsequence length : " + max);
	}

	// This is variation of LCS. LPS(str) = LCS(str, reverse(str))
	public int getMaxLength(String str) {
		int[][] memo = new int[str.length() + 1][str.length() + 1];
		String reverse = new StringBuilder(str).reverse().toString();

		for (int i = 0; i <= str.length(); i++) {
			for (int j = 0; j <= str.length(); j++) {
				if (i == 0 || j == 0)
					memo[i][j] = 0;
				else if (str.charAt(i - 1) == reverse.charAt(j - 1))
					memo[i][j] = 1 + memo[i - 1][j - 1];
				else
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
			}
		}

		return memo[str.length()][str.length()];
	}
}
