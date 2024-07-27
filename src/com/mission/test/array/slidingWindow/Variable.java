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

            // Check if window is valid
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
