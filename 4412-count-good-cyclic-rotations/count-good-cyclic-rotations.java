class Solution {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;
        int mid = (n / 2) - 1;
        
        int count = 0;
        long leftSum = 0;
        long rightSum = 0;
        
        int  i = 0; 
        int j = n - 1;
        
        while(i <= mid && j > mid){
            leftSum += nums[i];
            rightSum += nums[j];
            i++;
            j--;
        }
        
        if(leftSum > rightSum){
            count++;
        }
        
        for(int step = 0; step < n - 1; step++){
            long leftOut = nums[step];
            long rightToLeft = nums[(step + n / 2) % n];

            leftSum = leftSum - leftOut + rightToLeft;
            rightSum = rightSum - rightToLeft + leftOut;

            if(leftSum > rightSum){
                count++;
            }
        }
        return count;
    }
}