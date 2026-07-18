package math;

/*
Approach: 

class Solution {
    public int findGCD(int[] nums) {
     Arrays.sort(nums);   
     int a = nums[0];
     int b = nums[nums.length - 1];
     int hcf = 1;
     for(int i = 1; i <= a && i <= b; i++){
        if(a % i == 0 && b % i == 0){
            hcf = i;
        }
     }
     return hcf;
    }
}
*/

class Solution {
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        while(min != 0){
            int temp = min;
            min = max % min;
            max = temp;
        }
        return max;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {7, 5, 6, 8, 3}; // Example input
        int gcd = solution.findGCD(nums);
        System.out.println("The GCD of the array is: " + gcd);
    }
}