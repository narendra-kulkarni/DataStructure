package com.mission.test.array.slidingWindow;

// Type 1: Problems where we are generally given a specific size of
// window ‘K’ in the question itself.
public class Fixed {

    // 1. Compute the result for 1st window, i.e. include the
    //          first K elements of the data structure.
    // 2. Then use a loop to slide the window by 1 and keep
    //          computing the result window by window.

    public int maxSum(int[] arr, int k) {
        if (arr.length <= k) {
            System.out.println("Invalid");
            return -1;
        }

        // Compute sum of first window of size k
        int max_sum = 0;
        for (int i = 0; i < k; i++)
            max_sum += arr[i];

        // Compute sums of remaining windows by removing first element
        // of previous window and adding last element of current window.
        int window_sum = max_sum;
        for (int i = k; i < arr.length; i++) {
            // window size = i - k
            window_sum += arr[i] - arr[i - k];
            max_sum = Math.max(max_sum, window_sum);
        }

        return max_sum;
    }

    public static void main(String[] args) {
        // Find the maximum sum of all sub-arrays of size K
        Fixed f = new Fixed();
        int[] arr = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
        int k = 4;
        System.out.println(f.maxSum(arr, k));
    }
}
