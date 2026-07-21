package data_structure.arrays;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int total = m * n;

        k %= total;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                row.add(0);
            }

            ans.add(row);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int oldIndex = i * n + j;

                int newIndex = (oldIndex + k) % total;

                int newRow = newIndex / n;
                int newCol = newIndex % n;

                ans.get(newRow).set(newCol, grid[i][j]);
            }
        }

        return ans;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {
            {3, 8, 1, 9},
            {19, 7, 2, 5},
            {4, 6, 11, 10},
            {12, 0, 21, 13},
        };
        int k = 1; // Example shift value
        List<List<Integer>> result = solution.shiftGrid(grid, k);
        
        System.out.println("Shifted Grid:");
        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }
}