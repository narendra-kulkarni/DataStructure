package com.mission.test.backtracking;

import java.util.ArrayList;
import java.util.List;

public class ArrayBacktracking {

    public List<List<Integer>> Combinations(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        createSubset(nums, 0, res, new ArrayList<>());
        return res;
    }

    private void createSubset(int[] nums, int index,
              List<List<Integer>> res, List<Integer> subset) {
        if (index == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[index]);
        createSubset(nums, index + 1, res, subset);

        subset.remove(subset.size() - 1);
        createSubset(nums, index + 1, res, subset);
    }

    public List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPermutation = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        helper(nums, currentPermutation, used, result);
        return result;
    }

    private void helper(int[] nums, List<Integer> currentPermutation,
                        boolean[] used, List<List<Integer>> result) {
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
        } else {
            for(int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    currentPermutation.add(nums[i]);
                    helper(nums, currentPermutation, used, result);
                    currentPermutation.remove(currentPermutation.size() - 1);
                    used[i] = false;
                }
            }
        }
    }
}
