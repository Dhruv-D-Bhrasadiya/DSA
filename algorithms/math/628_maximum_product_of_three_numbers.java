package algorithms.math;

class Solution {
    public int maximumProduct(int[] nums) {
        int m1 = Integer.MIN_VALUE, m2 = Integer.MIN_VALUE, m3 = Integer.MIN_VALUE;
        int nm1 = Integer.MAX_VALUE, nm2 = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            if(m1 < nums[i]){
                m3 = m2;
                m2 = m1;
                m1 = nums[i];
            }
            else if(m2 < nums[i]){
                m3 = m2;
                m2 = nums[i];
            }
            else if(m3 < nums[i]){
                m3 = nums[i];
            }

            if(nm1 > nums[i]){
                nm2 = nm1;
                nm1 = nums[i];
            }
            else if(nm2 > nums[i]){
                nm2 = nums[i];
            }
        }

        return Math.max(m1 * m2 * m3, m1 * nm1 * nm2);
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4};
        int result = solution.maximumProduct(nums);
        System.out.println("Maximum product of three numbers: " + result);
    }
}