package algorithms.game_theory;

class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int s : stones) {
            count[s % 3]++;
        }
        int a = count[1];
        int b = count[2];
        int c = count[0];
        if (c % 2 == 0) {
            return a > 0 && b > 0;
        }

        return Math.abs(a - b) > 2;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] stones = {5,1,2,4,3};
        boolean result = solution.stoneGameIX(stones);
        System.out.println(result); // Output: false
    }
}