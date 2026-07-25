package data_structure.arrays.two_pointer;

class Solution {
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        long res = 0;
        int c1 = 0;
        int c2 = 0;
        for (int v : nums) {
            if (v < a) {
                res += c1 + c2;
            } else if (v <= b) {
                c1++;
                res += c2;
            } else {
                c2++;
            }
        }
        return (int) (res % 1000000007);
    }
}    

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 2, 4, 5, 6};
        int a = 3;
        int b = 4;
        int result = solution.minAdjacentSwaps(nums, a, b);
        System.out.println("Minimum adjacent swaps to partition the array: " + result);
    }
}
