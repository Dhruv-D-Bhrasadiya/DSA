package algorithms.game_theory;

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            int best = Integer.MIN_VALUE;
            int runningSum = 0;
            for (int k = 1; k <= 3; k++) {
                if (i + k > n) break;
                runningSum += stoneValue[i + k - 1];
                best = Math.max(best, runningSum - dp[i + k]);
            }
            dp[i] = best;
        }
        
        if (dp[0] > 0) return "Alice";
        else if (dp[0] < 0) return "Bob";
        else return "Tie";
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] stoneValue = {1, 2, 3, 7};
        String result = solution.stoneGameIII(stoneValue);
        System.out.println(result); // Output: "Bob"
    }
}