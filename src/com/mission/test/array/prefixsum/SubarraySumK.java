package com.mission.test.array.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumK {

    // Method to find if there exists a subarray with sum equal to k
    public boolean findSubarrayWithSumK(int[] nums, int k) {
        // Create a hash map to store the prefix sum and its first occurrence index
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        // This handles the case when the subarray starts from index 0
        prefixSumMap.put(0, -1);

        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i]; // Calculate the prefix sum

            // Check if there is a subarray with sum k
            if (prefixSumMap.containsKey(prefixSum - k)) {
                return true;
            }

            // Store the prefix sum if it's not already in the map
            if (!prefixSumMap.containsKey(prefixSum)) {
                prefixSumMap.put(prefixSum, i);
            }
        }

        return false; // No subarray found with the sum k
    }

    // Method to check whether there exists a subarray of length at least 2
    // within the given integer array nums whose sum is divisible by the integer k.
    public boolean hasGoodSubarray(int[] nums, int k) {
        // Handle the special case where k is 0
        if (k == 0) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == 0 && nums[i + 1] == 0) {
                    return true;
                }
            }
            return false;
        }

        // Initialize the hash map with remainder 0 mapped to index -1
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1); // This handles the case where subarray starts from index 0

        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;

            // Adjust remainder to be positive
            if (remainder < 0) {
                remainder += k;
            }

            if (remainderMap.containsKey(remainder)) {
                // Ensure subarray length is at least 2
                if (i - remainderMap.get(remainder) > 1) {
                    return true;
                }
            } else {
                // Store the first occurrence of this remainder
                remainderMap.put(remainder, i);
            }
        }

        return false;
    }

    /*************************************************************/

    public static void main(String[] args) {
        SubarraySumK solution = new SubarraySumK();

        int[] nums1 = {1, 2, 3, 7, 5};
        int k1 = 12;
        System.out.println("Test Case 1: " + solution.findSubarrayWithSumK(nums1, k1)); // Expected output: true

        int[] nums5 = {-1, -2, -3, -4, -5};
        int k5 = -5;
        System.out.println("Test Case 5: " + solution.findSubarrayWithSumK(nums5, k5)); // Expected output: true

        int[] nums6 = {1, 2, 3, 4, 5};
        int k6 = 11;
        System.out.println("Test Case 6: " + solution.findSubarrayWithSumK(nums6, k6)); // Expected output: false
    }
}
