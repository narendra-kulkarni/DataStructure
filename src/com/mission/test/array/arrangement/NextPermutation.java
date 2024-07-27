package com.mission.test.array.arrangement;

import java.util.Arrays;

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        // Find the first pair of adjacent elements where nums[i] < nums[i+1], from right to left
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1])
            i--;

        if (i >= 0) {
            // Find the smallest number on the right side of nums[i] that is greater than nums[i]
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i])
                j--;

            swap(nums, i, j);
        }

        // Reverse the subarray to the right of index i
        reverse(nums, i + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end)
            swap(nums, start++, end--);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /********************************************/

    public static void main(String[] args) {
        // The next permutation of an array of integers is the next lexicographically
        // greater permutation of its integer.
        // For example, the next permutation of arr = [1,2,3] is [1,3,2]
        NextPermutation n = new NextPermutation();
        int[] nums = {1, 2, 4, 3};
        n.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
