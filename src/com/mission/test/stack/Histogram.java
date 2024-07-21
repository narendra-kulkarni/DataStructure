package com.mission.test.stack;

import java.util.Stack;

public class Histogram {

    public static int largestRectangleArea(int[] input) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        // Create a new array with additional last element as 0. Without this logic,
        // area for the last elements will not be calculated. Check the example below
        int[] heights = new int[input.length + 1];
        // arraycopy(sourceArray, sourceStart, destArray, destStart, length of source to copy
        System.arraycopy(input, 0, heights, 0, input.length);
        heights[input.length] = 0;

        for (int i = 0; i < heights.length; i++) {
           while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
               int height = heights[stack.pop()];
               // Width calculation is the most important part. Consider previous
               // element of the popped one. Example {2, 1, 2}. The answer is 3, not 2
               //  o   o
               //  o o o
               int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
               maxArea = Math.max(maxArea, width * height);
           }
           // index helps with difference calculation
           stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // The problem: Given an array of integers representing the histogram's bar heights,
        // find the area of the largest rectangle that can be formed in the histogram.
        int[] heights = {3, 2, 5, 6, 1, 4, 4};
        //         o
        //       o o
        //       o o   o o
        //   o   o o   o o
        //   o o o o   o o
        //   o o o o o o o
        System.out.println("Largest rectangle area is : " + Histogram.largestRectangleArea(heights));
    }
}
