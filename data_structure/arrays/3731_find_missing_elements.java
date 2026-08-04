package data_structure.arrays;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = nums.length;

        for(int i = 0; i < n; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        if((max - min) + 1 == n){
            return new ArrayList<Integer>();
        }

        int[] check = new int[(max - min) + 1];

        for(int i = 0; i < n; i++){
            check[nums[i] - min]++;
        }

        List<Integer> ans = new ArrayList<Integer>();
        int p = 0;

        for(int i = 0; i < check.length; i++){
            if(check[i] == 0){
                ans.add(i + min); 
            }
        }

        return ans;
    }
}

class Main{
    public static void main(String args[]){
        Solution s = new Solution();

        int nums[] = {1, 4, 2, 5};

        List<Integer> result = s.findMissingElements(nums);
        System.out.println(result); // Output: [3]
    }
}