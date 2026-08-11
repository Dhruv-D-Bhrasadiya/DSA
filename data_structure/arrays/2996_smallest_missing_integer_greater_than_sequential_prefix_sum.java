package data_structure.arrays;

import java.util.Set;
import java.util.HashSet;

class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();

        for(int num : nums) {
            set.add(num);
        }

        int sum = nums[0];

        for(int i=1; i<n; i++) {
            if(nums[i] != nums[i-1] + 1) {
                break;
            } else {
                sum += nums[i];
            }
        }

        while(set.contains(sum)) {
            sum++;
        }

        return sum;
    }
} 

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,4,5,1,12,14,13}; // Example input
        int result = solution.missingInteger(nums); // Answer = 15
        System.out.println("Smallest missing integer greater than sequential prefix sum: " + result);
    }
}