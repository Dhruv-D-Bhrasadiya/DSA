class Solution {
    public int totalNumbers(int[] digits) {
        int[] f = new int[10];

        for (int x : digits) f[x]++;

        int ans = 0;

        for (int a = 1; a <= 9; a++) {
            if (f[a] == 0) continue;
            f[a]--;

            for (int b = 0; b <= 9; b++) {
                if (f[b] == 0) continue;
                f[b]--;

                for (int c = 0; c <= 8; c += 2)
                    if (f[c] > 0) ans++;

                f[b]++;
            }

            f[a]++;
        }

        return ans;
    }
}