package com.mission.test.array.slidingWindow;

import java.util.HashMap;
import java.util.Map;

// Type 2: Problems in which window size is not specified.
// Two pointers (left, right) -> Window expansion (right++) -> Window Contraction (left++)
//    -> Window state (map or array) -> Valid window & optimization condition (imp part of the problem)
//
// Key Points for Optimization:
// 1. Efficient State Updates: Use data structures that allow O(1) updates to the window state.
// 2. Minimize Re-computation: Avoid recalculating the entire window state.
//                             Instead, update it incrementally as you add or remove elements.
// 3. Early Termination: If possible, add conditions to break the loop early when a solution
//                             is found or further processing is unnecessary.
// 4. Space Efficiency: For problems involving a fixed character set (like ASCII),
//                             use an array instead of a HashMap for better performance.
// 5. Avoid Substring Creation: In string problems, avoid creating substrings in each iteration.
//                             Instead, keep track of indices and create the final substring only once.
public class Variable {

    // Variable Window Pattern
    /*
    public Result slidingWindowTemplate(String/Array input) {
        // Initialize window pointers and other necessary variables
        int left = 0, right = 0;
        Result result = ...; // Initialize result structure

        // Initialize window state (e.g., HashMap or array)
        WindowState state = ...;

        while (right < input.length()) {
            // Expand the window
            addElement(state, input[right]);

            // While the window is valid
            while (windowIsValid(state)) {
                // Update result if necessary
                result = updateResult(result, left, right);

                // Contract the window
                removeElement(state, input[left]);
                left++;
            }

            right++;
        }

        return result;
    }
    */

    public int longestUniqueSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();

        for (int right = 0, left = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (charMap.containsKey(currentChar)) {
                // Math.max is needed to avoid a repeating character that is
                // already outside our current window.
                left = Math.max(charMap.get(currentChar) + 1, left);
            }
            charMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";

        // Note that this is int array
        int[] target = new int[128];
        for (char ch : t.toCharArray())
            target[ch]++;

        String result = "";
        int total = t.length();
        int[] window = new int[128];

        for (int left = 0, right = 0; right < s.length(); right++) {
            // Expand the window & calculate current state
            char current = s.charAt(right);
            window[current]++;

            if (target[current] > 0 && window[current] <= target[current])
                total--;

            // While the window is valid, i.e., meets the target state
            while (total == 0) {
                // Update result if necessary
                if (result.isEmpty() || right - left + 1 < result.length()) {
                    result = s.substring(left, right + 1);
                }

                // Contract the window
                char leftChar = s.charAt(left);
                window[leftChar]--;
                if (target[leftChar] > 0 && window[leftChar] < target[leftChar])
                    total++;

                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Problem 1: Given a string str, find the length of the longest substring
        // without repeating characters.
        Variable v = new Variable();
        String str = "geeksforgeeks";
        int len = v.longestUniqueSubstring(str);
        System.out.println("The length : " + len);

        // Problem 2: Given two strings s and t of lengths m and n respectively, return
        // the minimum window substring of s such that every character in t
        // (including duplicates) is included in the window
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println("Minimum Window Substring : " + v.minWindow(s, t));
    }
}
