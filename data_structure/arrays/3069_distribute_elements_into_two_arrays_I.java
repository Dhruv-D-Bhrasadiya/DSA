package data_structure.arrays;


import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        if(n == 2){
            return nums;
        }

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        l1.add(nums[0]);
        l2.add(nums[1]);


        for(int i = 2; i < n; i++){
            if(l1.getLast() > l2.getLast()){
                l1.add(nums[i]);
                System.out.println(nums[i] + " In l1");

            }
            else{
                l2.add(nums[i]);
                System.out.println(nums[i] + " In l2");
            }
        }

        int[] ans = new int[n];
        int p = 0;

        for(int i = 0; i < l1.size(); i++){
            ans[i] = l1.get(p++);
        }
        p = 0;
        for(int i = l1.size(); i < n; i++){
            ans[i] = l2.get(p++);
        }
        return ans;
    }
}

class Main{
    public static void main(String args[]){
        Solution s = new Solution();
        int[] nums = {5,4,3,8};
        int[] ans = s.resultArray(nums);
        for(int i:ans){
            System.out.print(i + " ");
        } // Output = 5 8 4 3
    }
}