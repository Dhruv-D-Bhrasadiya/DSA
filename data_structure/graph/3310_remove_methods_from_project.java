package data_structure.graph

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invoc) {
        HashSet<Integer> sus = new HashSet<>();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<ArrayList<Integer>> incoming = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            incoming.add(new ArrayList<>());
        }

        for (int[] edge : invoc) {
            graph.get(edge[0]).add(edge[1]);
            incoming.get(edge[1]).add(edge[0]);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(k);
        sus.add(k);

        while (!queue.isEmpty()) {
            int curr = queue.removeFirst();

            for (int next : graph.get(curr)) {
                if (!sus.contains(next)) {
                    sus.add(next);
                    queue.addLast(next);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        boolean invalid = false;

        for (int i = 0; i < n; i++) {
            if (sus.contains(i)) {
                for (int parent : incoming.get(i)) {
                    if (!sus.contains(parent)) {
                        invalid = true;
                        break;
                    }
                }

                if (invalid) break;
            } else {
                ans.add(i);
            }
        }

        if (invalid) {
            ans.clear();
            for (int i = 0; i < n; i++) {
                ans.add(i);
            }
        }

        return ans;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        int k = 2;
        int[][] invoc = {{1, 2}, {0, 2}, {0, 1}, {3, 4}};
        List<Integer> result = solution.remainingMethods(n, k, invoc);
        // Output the remaining methods [3, 4s]
        System.out.println(result); // Output the remaining methods
    }
}