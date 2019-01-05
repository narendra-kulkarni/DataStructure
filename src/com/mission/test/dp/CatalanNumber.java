package com.mission.test.dp;

public class CatalanNumber {

	/** Count the number of expressions containing n pairs of parentheses which are correctly matched. 
	 For n = 3, possible expressions are ((())), ()(()), ()()(), (())(), (()()).

	 Count the number of possible Binary Search Trees with n keys

	 Count the number of full binary trees (A rooted binary tree is full if every vertex has either
	 two children or no children) with n+1 leaves. */

	// Catalan number Cn = Ci-1 * Cn-i
	// Consider i is picked as a root then left subtree will have i - 1 elements while right subtree will
	// have n - i elements. Since these two are independent of each other, multiplication is done

	public int catalanNumber(int num) {
		int[] memo = new int[num + 1];
		memo[0] = memo[1] = 1;

		for (int i = 2; i <= num; i++)
			for (int j = 0; j < i; j++)
				memo[i] += memo[j] * memo[i - j - 1];

		return memo[num];
	}

	public static void main(String[] args) {
		CatalanNumber c = new CatalanNumber();
		int n = 6;
		int catalan = c.catalanNumber(n);
		System.out.println("The catalan number is : " + catalan);
	}
}
