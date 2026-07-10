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