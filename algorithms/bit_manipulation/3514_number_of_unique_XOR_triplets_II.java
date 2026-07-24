package algorithms.bit_manipulation;

import java.util.Arrays;

class Solution {
    public static int uniqueXorTriplets(int[] arr) {
        int n = arr.length;

        boolean[] freq = new boolean[2048];

        int len = 0, idx = 0, ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int xor = arr[i] ^ arr[j];
                if (!freq[xor])
                    len++;
                freq[xor] = true;
            }
        }

        int[] pairXor = new int[len];

        for (int i = 0; i < 2048; i++) {
            if (freq[i]) {
                pairXor[idx++] = i;
            }
        }

        Arrays.fill(freq, false);

        for (int xor : pairXor) {
            for (int num : arr) {
                freq[xor ^ num] = true;
            }
        }

        for (boolean exists : freq) {
            if (exists)
                ans++;
        }

        return ans;
    }
}


class Main {
    public static void main(String[] args) {
        int[] arr = {6, 7, 8, 9};
        int result = Solution.uniqueXorTriplets(arr);
        System.out.println("Number of unique XOR triplets: " + result);
    }
    
}
