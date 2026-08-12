package algorithms.sliding_window;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {

        Map<Integer, Integer> freq = new HashMap<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {

            freq.put(nums[right],
                    freq.getOrDefault(nums[right], 0) + 1);

            while (freq.get(nums[right]) > k) {
                freq.put(nums[left],
                        freq.get(nums[left]) - 1);
                left++;
            }

            maxLen = Math.max(maxLen,
                    right - left + 1);
        }

        return maxLen;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 1, 2, 3, 1, 2}; // Example input
        int k = 2; // Example frequency limit
        int result = solution.maxSubarrayLength(nums, k); // Answer = 6
        System.out.println("Length of longest subarray with at most k frequency: " + result);
    }
}
