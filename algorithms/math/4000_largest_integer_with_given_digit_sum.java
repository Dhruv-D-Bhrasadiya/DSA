package algorithms.math;

class Solution {
    public int largestInteger(int n, int s) {
        if(s > n * 9){
            return -1;
        }
        if(s == 0){
            return 0;
        }
        if(n == 1 && s <= 9){
            return s;
        }

        int ans = 0;
        if(s < 9){
            ans = s;
            s = 0;
            n--;
        }

        while(s >= 9 && n > 0){
            ans = ans + 9;
            s = s - 9;
            if(s >= 9){
                ans *= 10;
            }
            n--;
        }

        if(s == 0 && n > 0){
            while(n > 0){
                ans *= 10;
                n--;
            }
        }
        else if(s < 9 && n > 0){
            ans = (ans * 10) + s;
            n--;
            while(n > 0){
                ans *= 10;
                n--;
            }
        }
        return ans;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestInteger(2, 9)); // Expected output: 90
        System.out.println(solution.largestInteger(2, 19)); // Expected output: -1
        System.out.println(solution.largestInteger(5, 0));  // Expected output: 0
    }
}
