package algorithms.math;

class Solution {
    public long maxPairStrength(int[] nums) {
        long ans = 0;

        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                long g = gcd(nums[i], nums[j]);
                long cur = 1l * nums[i] * nums[j] / (g * g);
                ans = Math.max(ans, cur);
            }
        }

        return ans;
    }
    private long gcd(long a, long b){
        while(b != 0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] nums = {2, 3, 5};
        long result = solution.maxPairStrength(nums);
        System.out.println(result); // Output: 15
    }
}
