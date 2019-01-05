package com.mission.test.dp;

public class EditDistance {

	// If first string is empty i = 0, then edit distance is length of the second string
	// If second string is empty j = 0, then edit distance is length of the first string

	public int editDistance(String str1, String str2) {
		int[][] memo = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 0; i <= str1.length(); i++) {
			for (int j = 0; j <= str2.length(); j++) {
				if (i == 0)
					memo[i][j] = j;
				else if (j == 0)
					memo[i][j] = i;
				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
					memo[i][j] = memo[i - 1][j - 1];
				else
					memo[i][j] = 1 + Math.min(memo[i - 1][j] , 
							Math.min(memo[i][j - 1] , memo[i - 1][j - 1]));
			}
		}

		return memo[str1.length()][str2.length()];
	}

	public static void main(String[] args) {
		EditDistance l = new EditDistance();
		String str1 = "ABE";
		String str2 = "BE";
		int min = l.editDistance(str1, str2);
		System.out.println("Minimum edit distance : " + min);
	}
}
