package math;

import java.util.Arrays;

class Solution {
    public long gcdSum(int[] n) {
        int len = n.length, m = n[0];
        for(int i = 0; i < len; ++i) {
            int v = n[i];
            m = Math.max(m, v);
            n[i] = gcd(v, m);
        }

        Arrays.sort(n);
        int l = 0, r = len - 1;
        long ans = 0;

        while(l < r)
            ans += gcd(n[l++], n[r--]);

        return ans;
    }

    private int gcd(int a, int b) {
        while(b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

public class Sum_of_GCD_of_formed_pairs {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] n = {3, 6, 2, 8}; // Example input
        long result = sol.gcdSum(n);
        System.out.println("Sum of GCD of formed pairs is: " + result);
    }
}
