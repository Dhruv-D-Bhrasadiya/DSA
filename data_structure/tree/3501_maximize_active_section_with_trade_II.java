package data_structure.tree;

import java.util.ArrayList;
import java.util.List;

class Solution {

    private int[] zs, ze, val, log;
    private int[] st;
    private int m;

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int n = s.length();
        int ones = 0;

        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n;) {

            if (s.charAt(i) == '1') {
                ones++;
                i++;
            } else {
                int l = i++;
                while (i < n && s.charAt(i) == '0') i++;

                a[m] = l;
                b[m++] = i - 1;
            }
        }

        zs = new int[m];
        ze = new int[m];

        System.arraycopy(a, 0, zs, 0, m);
        System.arraycopy(b, 0, ze, 0, m);

        if (m > 1) build();

        ArrayList<Integer> ans = new ArrayList<>(queries.length);

        for (int[] q : queries)
            ans.add(ones + query(q[0], q[1]));

        return ans;
    }


    private void build() {

        int n = m - 1;

        val = new int[n];

        for (int i = 0; i < n; i++)
            val[i] = ze[i] - zs[i] + ze[i + 1] - zs[i + 1] + 2;


        log = new int[n + 1];

        for (int i = 2; i <= n; i++)
            log[i] = log[i >> 1] + 1;


        int levels = log[n] + 1;

        st = new int[levels * n];

        System.arraycopy(val, 0, st, 0, n);


        for (int k = 1; k < levels; k++) {

            int off = k * n;
            int prev = (k - 1) * n;
            int len = n - (1 << k) + 1;

            for (int i = 0; i < len; i++) {

                int x = st[prev + i];
                int y = st[prev + i + (1 << (k - 1))];

                st[off + i] = x > y ? x : y;
            }
        }
    }


    private int query(int l, int r) {

        if (m < 2) return 0;

        int left = lower(ze, l);
        int right = upper(zs, r) - 2;

        if (left > right) return 0;


        int ans = clip(left, l, r);

        int x = clip(right, l, r);
        if (x > ans) ans = x;


        if (right - left > 1) {

            int len = right - left - 1;
            int k = log[len];

            int off = k * (m - 1);

            x = Math.max(
                st[off + left + 1],
                st[off + right - (1 << k)]
            );

            if (x > ans) ans = x;
        }

        return ans;
    }


    private int clip(int i, int l, int r) {

        int res = val[i];

        if (l > zs[i])
            res -= l - zs[i];

        if (r < ze[i + 1])
            res -= ze[i + 1] - r;

        return res;
    }


    private int lower(int[] a, int x) {

        int l = 0, r = a.length;

        while (l < r) {
            int mid = (l + r) >>> 1;

            if (a[mid] < x)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }


    private int upper(int[] a, int x) {

        int l = 0, r = a.length;

        while (l < r) {
            int mid = (l + r) >>> 1;

            if (a[mid] <= x)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }
}


class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "01010";
        int[][] queries = {{0, 3}, {1, 4}, {1, 3}};
        List<Integer> result = solution.maxActiveSectionsAfterTrade(s, queries);
        System.out.println(result); 
    }
}
