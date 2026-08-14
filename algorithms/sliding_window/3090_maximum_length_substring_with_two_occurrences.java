package algorithms.sliding_window;

import java.util.HashMap;

class Solution {
    public int maximumLengthSubstring(String s) {
        int max = 0;
        int left = 0;

        HashMap<Character, Integer> freq = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            while (freq.get(ch) > 2) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumLengthSubstring("bcbbbcba")); // Output: 4
        System.out.println(solution.maximumLengthSubstring("aaaa")); // Output: 2
    }
}