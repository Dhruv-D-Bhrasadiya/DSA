package algorithms.greedy;

import java.utils.Map;
import java.utils.HashMap;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] rs) {
        Map<Integer, int[]> seen = new HashMap<>();

        for(int[] r:rs) {
            int row = r[0];
            int seat = r[1];

            if (!seen.containsKey(row)) {
                seen.put(row, new int[3]);
            }

            if (seat == 2 || seat == 3) {
                seen.get(row)[0] = 1;
            } 

            else if (seat == 4 || seat == 5) {
                seen.get(row)[0] = 1;
                seen.get(row)[1] = 1;
            }

            else if (seat == 6 || seat == 7) {
                seen.get(row)[1] = 1;
                seen.get(row)[2] = 1;
            }

            else if (seat == 8 || seat == 9) {
                seen.get(row)[2] = 1;
            }
        }

        int fourGang = 0;

        for(int key:seen.keySet()) {
            int[] curr = seen.get(key);

            int tmp = 0;

            if (curr[0] == 0) tmp++;
            if (curr[2] == 0) tmp++;

            if (tmp == 0 && curr[1] == 0) tmp++;

            fourGang += tmp;
        }
        
        fourGang += (n - seen.size()) * 2;

        return fourGang;
    }
}

class Main{
    public static void main(String args[]){
        Solution s = new Solution();
        int n = 3;
        int[][] reservedSeats = {{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}};
        int ans = s.maxNumberOfFamilies(n, reservedSeats);
        System.out.println(ans); // Output = 4
    }
}