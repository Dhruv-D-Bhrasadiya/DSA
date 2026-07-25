package algorithms.math;

class Solution {
    public int maxProduct(int n) {
        int m1 = 0;
        int m2 = 0;

        while(n > 0){
            int temp = n % 10;
            if(m1 <= temp){
                m2 = m1;
                m1 = temp;
            }
            else{
                m2 = Math.max(m2, temp);
            }

            n = n / 10;
        }

        return m1 * m2;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 31;
        int result = solution.maxProduct(n);
        System.out.println("Maximum product of two digits in " + n + " is: " + result);
    }
}