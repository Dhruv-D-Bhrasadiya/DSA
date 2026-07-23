package math;

/* 
Approach: Math
class Solution {
    public int gcdOfOddEvenSums(int n) {
        int even = (n ) * (n - 1);
        int odd = (int) Math.pow((n), 2);

        while(odd != 0){
            int rem = even % odd;
            even = odd;
            odd = rem;
        }

        return even;
    }
}
*/
    
class Solution {
    public int gcdOfOddEvenSums(int n) {
        return n;
    }
}

class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5; // Example input
        int result = sol.gcdOfOddEvenSums(n);
        System.out.println("GCD of odd and even sums for n = " + n + " is: " + result);
    }
}
