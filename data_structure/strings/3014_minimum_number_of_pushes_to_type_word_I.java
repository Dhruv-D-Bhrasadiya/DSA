package data_structure.strings;

/*
Approach:
class Solution {
    public int minimumPushes(String word) {
        int one = 8;
        int two = 8;
        int three = 8;
        int four = 2;

        int n = word.length();

        int ans = 0;

        while(n > 0 && one > 0){
            ans += 1;
            one--;
            n--;
        }

        while(n > 0 && two > 0){
            ans += 2;
            two--;
            n--;
        }

        while(n > 0 && three > 0){
            ans += 3;
            three--;
            n--;
        }

        while(n > 0 && four > 0){
            ans += 4;
            four--;
            n--;
        }

        return ans;
    }
}
    time complexity: O(n)
    space complexity: O(1)

    the below code has time complexity of O(1) and space complexity of O(1)
*/

class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int rem = n % 8;
        int comp = n / 8;

        int ans = 8 * comp * (comp + 1) / 2 + (comp + 1) * rem;

        return ans;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minimumPushes("abcde")); // Output: 5
        System.out.println(solution.minimumPushes("xycdefghij")); // Output: 12
    }
}