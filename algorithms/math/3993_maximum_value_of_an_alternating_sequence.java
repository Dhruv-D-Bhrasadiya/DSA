package algorithms.math;

/*
Approach 1: Simulation/Greedy Approach

class Solution {
    public long maximumValue(int n, int s, int m) {
        if (n == 1) return s;

        long current = s;
        long maxVal = s;
        boolean standardRise = true;

        for (int i = 1; i < n; i++) {
            if (standardRise) {
                current += m;
            } else {
                current -= (m - 1);
            }
            maxVal = Math.max(maxVal, current);
            standardRise = !standardRise;   
        }
        return maxVal;
    }
}

Time Complexity: O(n) - We iterate through the sequence of length n to calculate the maximum value.
Space Complexity: O(1) - We use a constant amount of space for variables.

Leads to Time Limit Exceeded for large n, hence we can use a mathematical approach to solve it in O(1) time.
*/

class Solution {
    public long maximumValue(int n, int s, int m) {
        if (n == 1) {
            return s;
        }
        
        return (long) s + (long) (n / 2) * (m - 1) + 1;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int s = 5;
        int m = 10;
        long result = solution.maximumValue(n, s, m);
        System.out.println("Maximum value of the alternating sequence: " + result);
    }
}
