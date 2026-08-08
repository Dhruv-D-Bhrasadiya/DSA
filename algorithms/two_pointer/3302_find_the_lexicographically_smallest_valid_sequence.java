package algorithms.two_pointer;

import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        int[] suf = new int[m];
        Arrays.fill(suf, -1);

        for (int i = n - 1, j = m - 1; i >= 0 && j >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(j))
                suf[j--] = i;
        }

        int[] ans = new int[m];
        int i = 0, j = 0;
        boolean used = false;

        while (i < n && j < m) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i++;
                continue;
            }
             if (!used &&
                (j == m - 1 ||
                 (suf[j + 1] != -1 && i < suf[j + 1]))) {

                ans[j++] = i++;
                used = true;
                continue;
            }
            i++;
        }
        return j == m ? ans : new int[0];
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        String word1 = "vbcca";
        String word2 = "abc";
        int[] result = solution.validSequence(word1, word2);
        System.out.println(Arrays.toString(result)); // Output: [0, 2, 4]
    }
}