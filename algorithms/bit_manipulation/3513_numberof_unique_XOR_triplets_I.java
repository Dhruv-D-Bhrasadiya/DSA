package algorithms.bit_manipulation;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int m = n;
        
        m |= m >> 1;
        m |= m >> 2;
        m |= m >> 4;
        m |= m >> 8;
        m |= m >> 16;
        
        return (m + 1) >> (3 / (n + 1));
    }
}

class Main {
    public static void main(String[] args) {
        int[] nums = {3, 1, 2};
        Solution solution = new Solution();
        int result = solution.uniqueXorTriplets(nums);
        System.out.println("Number of unique XOR triplets: " + result);
    }
}
