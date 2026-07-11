/* 
Approach 1 : Brute force

class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            ans[i] = pathExist(nums, maxDiff, start, end);
        }

        return ans;
    }

    private boolean pathExist(int[] nums, int maxDiff, int start, int end) {
        if (start == end) return true;
        
        int left = Math.min(start, end);
        int right = Math.max(start, end);
        
        for (int i = left; i < right; i++) {
            if (Math.abs(nums[i] - nums[i + 1]) > maxDiff) {
                return false;
            }
        }
        return true;
    }
}
This approach is O(n * q) 
where,
 n = length of nums 
 q = number of queries.
This can be optimized to O(n + q) using a union-find or connected components approach.

Approach 2 : union-find
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < n; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) <= maxDiff) {
                union(parent, i, i - 1);
            }
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = find(parent, queries[i][0]) == find(parent, queries[i][1]);
        }

        return ans;
    }

    private void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    private int find(int[] parent, int a) {
        if (parent[a] != a) {
            parent[a] = find(parent, parent[a]);
        }
        return parent[a];
    }
}
*/


class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[] components = new int[n];
        int componentId = 0;
        components[0] = componentId;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                componentId++;
            }
            components[i] = componentId;
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = components[queries[i][0]] == components[queries[i][1]];
        }

        return ans;
    }
}

class Main {
    public static void main(String[] args) {
        Solution s = new Solution();

        int n = 4;
        int[] nums = {2, 5, 6, 8};
        int maxDiff = 2;
        int[][] queries = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};

        boolean[] result = s.pathExistenceQueries(n, nums, maxDiff, queries);
        System.out.println(Arrays.toString(result));
    }
}