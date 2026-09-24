class Solution {
    public int smallestIndex(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            int a = nums[i];
            int b = ((a / 100) % 10) + ((a / 10) % 10) + (a % 10) + ((a / 1000) % 10);

            if(i == b){
                return i;
            }
        }
        return -1;
    }
}