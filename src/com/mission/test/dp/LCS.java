package com.mission.test.dp;

public class LCS {

	public int lcs(String str1, String str2) {
		int[][] memo = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 0; i <= str1.length(); i++) {
			for (int j = 0; j <= str2.length(); j++) {
				if (i == 0 || j == 0)
					memo[i][j] = 0;
				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
					memo[i][j] = memo[i - 1][j - 1] + 1;
				else
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
			}
		}

		print(memo);
		return memo[str1.length()][str2.length()];
	}

	public static void main(String[] args) {
		LCS l = new LCS();
		String str1 = "ABCDE";
		String str2 = "BE";
		int max = l.lcs(str1, str2);
		System.out.println("Longest common subsequence : " + max);
	}

	private void print(int[][] memo) {
		if (memo != null && memo.length > 0) {
			for (int i = 0; i < memo.length; i++) {
				for (int j = 0; j < memo[i].length; j++) {
					System.out.print(memo[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
