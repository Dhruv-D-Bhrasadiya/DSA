package algorithms.math;

class Solution {
    public boolean checkDivisibility(int n) {
        int sum = 0;
        int product = 1;
        int num = n;

        while(num > 0){
            int temp = num % 10;
            sum += temp;
            product *= temp;
            num = num / 10;
        }

        return n % (sum + product) == 0 ? true : false;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 99;
        boolean result = solution.checkDivisibility(n);
        System.out.println("Is " + n + " divisible by the sum and product of its digits? " + result);
    }
}