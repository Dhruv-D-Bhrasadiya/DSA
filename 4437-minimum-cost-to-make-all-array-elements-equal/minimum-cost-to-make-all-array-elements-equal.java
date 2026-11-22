class Solution {
    public long minCost(int[] nums) {
        int n = nums.length;
        long count = Long.MAX_VALUE;
        int pre[] = new int[n];
        int suf[] = new int[n];

        
        for(int i = 1; i < n - 1; i++){
            if(nums[i] != nums[i - 1]){
                pre[i] = pre[i - 1] + 1;
            }
        }

        for(int i = n - 2; i > 0; i--){
            if(nums[i] != nums[i + 1]){
                suf[i] = suf[i + 1] + 1;
            }
        }

        for(int i = 1; i < n - 1; i++){
            int temp = i;
            while(temp + 1 < n - 1 && nums[temp] == nums[temp + 1]){
                temp++;
            }
            if(pre[i] > 0){
                if(suf[temp] > 0){
                    long total = (1L * i * nums[i]) + (1L * (n - temp - 1) * nums[temp]);
                    if(total < count){
                        count = total;
                    }
                }
                else{
                    long total = 1L * i * nums[i];
                    if(total < count){
                        count = total;
                    }
                }
                
            }
            else{
                if(suf[temp] > 0){
                    long total = 1L * (n - temp - 1) * nums[temp];
                    if(total < count){
                        count = total;
                    }
                }
            }
            i = temp;
        }
        
        return count == Long.MAX_VALUE ? 0 : count;
    }
}