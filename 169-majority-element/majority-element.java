class Solution {
    public int majorityElement(int[] nums) {
        int cand = findCandidate(nums);
        int count = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(cand == nums[i]){
                count++;
            }
        }
        return (n / 2) < count ? cand : -1;
    }

    public int findCandidate(int[] nums){
        int n = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(n == nums[i]){
                count++;
            }
            else{
                count--;
                if(count == 0){
                    n = nums[i];
                    count++;
                }
            }
        }
        return n;
    }
}