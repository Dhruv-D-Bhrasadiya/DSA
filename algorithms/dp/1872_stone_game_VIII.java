package algorithms.dp;

class Solution {
    int n;
    int[] dp;
    int[] pref;

    int solve(int idx) {
        if(idx == n - 1)
            return 0;

        if(dp[idx] != Integer.MIN_VALUE)
            return dp[idx];

        int take = pref[idx + 1] - solve(idx + 1);

        if(idx + 1 == n - 1) {
            dp[idx] = take;
            return take;
        }

        int skip = solve(idx + 1);

        return dp[idx] = Math.max(take, skip);
    }

    public int stoneGameVIII(int[] stones) {
        n = stones.length;

        dp = new int[n];
        pref = new int[n];

        Arrays.fill(dp, Integer.MIN_VALUE);

        pref[0] = stones[0];

        for(int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + stones[i];

        return solve(0);
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] stones = {7,-6,5,10,5,-2,-6};
        int result = solution.stoneGameVIII(stones);
        System.out.println("Maximum score difference: " + result);
    }
}