package com.mission.test.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intervals {

    // Given an array of intervals where intervals[i] = [starti, endi],
    // merge all overlapping intervals, and return an array of the non-overlapping
    // intervals that cover all the intervals in the input.
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, (a , b) -> Integer.compare(a[0], b[0]));

        List<int[]> list = new ArrayList<>();
        int[] previous = intervals[0];
        list.add(previous);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];

            if (current[0] <= previous[1]) {
                previous[1] = Math.max(previous[1], current[1]);
            } else {
                previous = current;
                list.add(previous);
            }
        }

        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        Intervals i = new Intervals();

        int[][] intervals = {
            {1, 3},
            {12, 18},
            {8, 14},
            {2, 6}
        };

        int[][] result = i.merge(intervals);
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
