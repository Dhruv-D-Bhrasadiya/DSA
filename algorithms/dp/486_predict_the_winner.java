package algorithms.dp;

import java.util.Arrays;

class Solution {
    public int solve(int[][] dp, int []v, int i, int j, int k){
        if(i > j){
            return 0;
        }

        if(dp[i][j] != Integer.MIN_VALUE){
            return dp[i][j];
        }

        int first = 0;
        int last = 0;
        int ans = 0;

        if(k % 2 == 0){
            first = v[i] + solve(dp, v, i + 1, j, k + 1);
            last = v[j] + solve(dp, v, i, j-1, k + 1);
            ans=Math.max(first,last);
        }
        else{
            first =- v[i] + solve(dp, v, i+1, j, k + 1);
            last =- v[j] + solve(dp, v, i, j - 1, k + 1);
            ans = Math.min(first, last);
        }
        return dp[i][j] = ans;
    }
    public boolean predictTheWinner(int[] v) {
        int i, n = v.length;
        int[][] dp = new int[n][n];
        
        for(i = 0; i < n; i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        return solve(dp, v , 0 , n - 1, 0) >= 0;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 5, 2};
        boolean result = solution.predictTheWinner(nums);
        System.out.println(result); // Output: false
    }
}
