class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0; 
        for(int n : nums){
            sum += n;
        }

        int target = sum - x;

        if(target == 0){
            return nums.length;
        }

        int left = 0;
        int currSum = 0;
        int maxLen = -1;

        for(int right = 0; right < nums.length; right++){
            currSum += nums[right];

            while(currSum > target && left <= right){
                currSum -= nums[left];
                left++;
            }

            if(currSum == target){
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}