package com.mission.test.array.slidingWindow;

import java.util.HashMap;
import java.util.Map;

// Type 2: Problems in which window size is not specified
public class Variable {

    // Window = left ---- right
    // 1. In this type of sliding window problem, we increase
    //    our right pointer one by one till our condition is true.
    // 2. At any step if our condition does not match, we shrink
    //    the size of the window by increasing left pointer
    // 3. Again when our condition satisfies, we start increasing
    //    the right pointer and follow step 1
    // 4. Follow the steps until end of the array is reached.

    public int longestUniqueSubstring(String str) {
        int res = 0;

        for (int left = 0; left < str.length(); left++) {
            boolean[] visited = new boolean[256];

            // Here window size = 1 needs to be considered.
            // Therefore, left = right
            for (int right = left; right < str.length(); right++) {
                if (visited[str.charAt(right)]) {
                    break;
                } else {
                    // window size = right - left + 1
                    res = Math.max(res, right - left + 1);
                    visited[str.charAt(right)] = true;
                }
            }

            visited[str.charAt(left)] = false;
        }

        return res;
    }

    // This is the optimized version O(N)
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();

        for (int right = 0, left = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            if (charMap.containsKey(currentChar)) {
                left = Math.max(charMap.get(currentChar) + 1, left);
            }
            charMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // Given a string str, find the length of the longest substring
        // without repeating characters.
        Variable v = new Variable();
        String str = "geeksforgeeks";
        int len = v.longestUniqueSubstring(str);
        System.out.println("The length : " + len);
    }
}
