package algorithms.two_pointer;
/*
Approach 1: Brute Force
class Solution {
    public int maxValidPairSum(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(j - i >= k && nums[i] + nums[j] > ans){
                    ans = nums[i] + nums[j];
                }
            }
        }
        return ans;
    }
}
Time Complexity: O(n^2)

Approach 2: Two Pointer

class Solution {
    public int maxValidPairSum(int[] nums, int k) {

        
        int best = nums[0];
        int ans = Integer.MIN_VALUE;

       for(int j = k;j<nums.length;j++){
           best = Math.max(best,nums[j-k]);
           ans = Math.max(ans,best+nums[j]);
       }
       
        return ans;
    }
}
Time complexity: O(n)
*/

class Solution {
    public int maxValidPairSum(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        int[] suffix = new int[n + 1];

        for(int i = n - 1; i >= 0; i--){
            suffix[i] = Math.max(nums[i], suffix[i + 1]);
        }

        for(int i = 0; i + k < n; i++){
            ans = Math.max(ans, nums[i] + suffix[i + k]);
        }

        return ans;
    }
}

class Main{
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums = {1, 3, 5, 2, 8};
        int k = 2;
        System.out.println(s.maxValidPairSum(nums, k)); // Output: 9 (4 + 5)
    }
}