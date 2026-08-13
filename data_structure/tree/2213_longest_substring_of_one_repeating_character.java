package data_structure.tree;

import java.util.TreeMap;

class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        char[] arr = s.toCharArray();
        TreeMap<Integer, Integer> segs = new TreeMap<>();
        TreeMap<Integer, Integer> lens = new TreeMap<>();
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && arr[j] == arr[i]) j++;
            segs.put(i, j - 1);
            lens.put(j - i, lens.getOrDefault(j - i, 0) + 1);
            i = j;
        }
        int k = queryIndices.length;
        int[] result = new int[k];
        for (int q = 0; q < k; q++) {
            int pos = queryIndices[q];
            char ch = queryCharacters.charAt(q);
            if (arr[pos] != ch) {
                int l = segs.floorKey(pos);
                int r = segs.get(l);
                segs.remove(l);
                int oldLen = r - l + 1;
                lens.put(oldLen, lens.get(oldLen) - 1);
                if (lens.get(oldLen) == 0) lens.remove(oldLen);
                if (l <= pos - 1) {
                    segs.put(l, pos - 1);
                    int len1 = pos - l;
                    lens.put(len1, lens.getOrDefault(len1, 0) + 1);
                }
                if (pos + 1 <= r) {
                    segs.put(pos + 1, r);
                    int len2 = r - pos;
                    lens.put(len2, lens.getOrDefault(len2, 0) + 1);
                }
                int newL = pos, newR = pos;
                Integer rightKey = segs.ceilingKey(pos + 1);
                if (rightKey != null && rightKey == pos + 1 && arr[pos + 1] == ch) {
                    int rightR = segs.get(rightKey);
                    int rightLen = rightR - rightKey + 1;
                    lens.put(rightLen, lens.get(rightLen) - 1);
                    if (lens.get(rightLen) == 0) lens.remove(rightLen);
                    newR = rightR;
                    segs.remove(rightKey);
                }
                Integer leftKey = segs.floorKey(pos - 1);
                if (leftKey != null) {
                    int leftR = segs.get(leftKey);
                    if (leftR == pos - 1 && arr[pos - 1] == ch) {
                        int leftLen = leftR - leftKey + 1;
                        lens.put(leftLen, lens.get(leftLen) - 1);
                        if (lens.get(leftLen) == 0) lens.remove(leftLen);
                        newL = leftKey;
                        segs.remove(leftKey);
                    }
                }
                segs.put(newL, newR);
                int newLen = newR - newL + 1;
                lens.put(newLen, lens.getOrDefault(newLen, 0) + 1);
                arr[pos] = ch;
            }
            result[q] = lens.lastKey();
        }
        return result;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "babacc";
        String queryCharacters = "bcb";
        int[] queryIndices = {1, 3, 3};
        int[] result = solution.longestRepeating(s, queryCharacters, queryIndices);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}