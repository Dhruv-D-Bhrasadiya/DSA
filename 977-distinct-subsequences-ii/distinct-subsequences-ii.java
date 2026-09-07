class Solution {
    public int distinctSubseqII(String s) {
        long mod = 1_000_000_007;
        long[] dp = new long[26];

        long total = 0;

        for (char ch : s.toCharArray()) {
            int x = ch - 'a';

            long add = (total + 1) % mod;

            total = (total + add - dp[x] + mod) % mod;

            dp[x] = add;
        }

        return (int) total;
    }
}