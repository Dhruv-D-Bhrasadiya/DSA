class Solution {
    public long countCommas(long n) {
        int len = String.valueOf(n).length();

        long nums[]= new long[]{
            1000L, 
            1000000L, 
            1000000000L, 
            1000000000000L, 
            1000000000000000L, 
            1000000000000000000L
        };

        int k = 0;        
        for (long p : nums) if (n >= p) k++;
        
        return k * (n + 1) - (nums[k] - 1000) / 999;
    }
}