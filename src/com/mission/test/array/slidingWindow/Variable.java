package com.mission.test.array.slidingWindow;

import java.util.HashMap;
import java.util.Map;

// Type 2: Problems in which window size is not specified
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
