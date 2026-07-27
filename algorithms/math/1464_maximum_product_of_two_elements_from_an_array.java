package algorithms.math;

/*
Approach 1: Brute Force

class Solution {
    public int maxProduct(int[] nums) {
        int product=0;
        int max=0;
        for(int i=0;i<nums.length;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                product=(nums[i]-1)*(nums[j]-1);
                max=Math.max(max,product);
            }
        }
        return max;
    }
}
*/

class Solution {
    public int maxProduct(int[] nums) {
        int m1 = 0;
        int m2 = 0;

        for(int i = 0; i < nums.length; i++){
            int temp = nums[i];
            if(m1 < temp){
                m2 = m1;
                m1 = temp;
            }
            else{
                m2 = Math.max(m2, temp);
            }
        }
        return (m1 - 1) * (m2 - 1);
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(new int[]{3, 4, 5, 2})); // Expected output: 12
        System.out.println(solution.maxProduct(new int[]{1, 5, 4, 5})); // Expected output: 16
        System.out.println(solution.maxProduct(new int[]{3, 7})); // Expected output: 12
    }
}
