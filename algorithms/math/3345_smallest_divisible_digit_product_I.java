package algorithms.math;

class Solution {
    public int smallestNumber(int n, int t) {
        while(n <= 100){
            int num = n;
            int digitSum = 1;

            while(num != 0){
                digitSum *= num % 10;

                if(digitSum == 0){
                    return num;
                }
                
                num = num / 10;
            }

            if(digitSum % t == 0){
                return n;
            }

            n++;
        }

        return -1;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 10; // Example input
        int t = 5;  // Example input
        int result = solution.smallestNumber(n, t);
        System.out.println("The smallest number is: " + result);
    }
}