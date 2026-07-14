package algorithms.dp;
import java.util.Arrays;

/*
Approach: Recursion

class Solution {
    public int subsequencePairCount(int[] nums) {
        return recursion(nums, 0, 0, 0);
    }

    public int recursion(int[] nums, int i, int gcd1, int gcd2){
        if(i == nums.length){
            if(gcd1 != 0 && gcd2 != 0 && gcd1 == gcd2){
                return 1;
            }
            else{
                return 0;
            }
        }

        int skip = recursion(nums, i + 1, gcd1, gcd2);
        
        int seq1 = recursion(nums, i + 1, findGCD(gcd1, nums[i]), gcd2);
        int seq2 = recursion(nums, i + 1, gcd1, findGCD(gcd2, nums[i]));

        return skip + seq1 + seq2;
    }

    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
*/

class Solution {

    static final long mod = 1000000007;
    long[][][] dp;

    long f(int[] nums, int i, int g1, int g2) {

        if (i == -1) {
            if (g1 != 0 && g2 != 0 && g1 == g2)
                return 1;
            return 0;
        }

        if (dp[i][g1][g2] != -1)
            return dp[i][g1][g2];

        long notTake = f(nums, i - 1, g1, g2) % mod;

        long g1Take = f(nums, i - 1, gcd(g1, nums[i]), g2) % mod;

        long g2Take = f(nums, i - 1, g1, gcd(g2, nums[i])) % mod;

        return dp[i][g1][g2] = (notTake + g1Take + g2Take) % mod;
    }

    int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public int subsequencePairCount(int[] nums) {

        int n = nums.length;

        int maxEl = 0;
        for (int num : nums)
            maxEl = Math.max(maxEl, num);

        dp = new long[n][maxEl + 1][maxEl + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= maxEl; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return (int) f(nums, n - 1, 0, 0);
    }
}
