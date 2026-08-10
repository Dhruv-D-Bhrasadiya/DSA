package algorithms.game_theory;

class Solution {
    int n;
    int[] sum;
    int[][] memo;
    int dfs(int index, int limit) {
        if (index == n) return 0;
        if (memo[index][limit] != -1) return memo[index][limit];
        int max = 0;
        for (int take = 1; take <= 2 * limit && index + take <= n; take++) {
            int newLimit = Math.max(limit, take);
            max = Math.max(max, sum[index] - dfs(index + take, newLimit));
        }
        return memo[index][limit] = max;
    }
    public int stoneGameII(int[] piles) {
        n = piles.length;
        sum = new int[n + 1];
        memo = new int[n][n + 1];
        for (int i = n - 1; i >= 0; i--)
            sum[i] = sum[i + 1] + piles[i];
        for (int[] row : memo)
            java.util.Arrays.fill(row, -1);

        return dfs(0, 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] piles = {2, 7, 9, 4, 4};
        int result = solution.stoneGameII(piles);
        System.out.println(result); // Output the result
    }
}