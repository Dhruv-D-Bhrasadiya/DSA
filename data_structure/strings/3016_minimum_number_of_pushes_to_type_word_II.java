package data_structure.strings;

/*
Approach:
class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int[] freq = new int[26];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            freq[word.charAt(i) - 'a']++;
        }

        Arrays.sort(freq);

        // you can also reverse the array instead of iterating from the end of the array.
        for (int i = 0; i < freq.length / 2; i++) {
            int temp = freq[i];
            freq[i] = freq[freq.length - 1 - i];
            freq[freq.length - 1 - i] = temp;
        }

        int one = 8;
        int two = 8;
        int three = 8;
        int four = 2;

        int i = 0;

        while (i < n && one > 0) {
            ans += freq[i];
            one--;
            i++;
        }

        while (i < n && two > 0) {
            ans += 2 * freq[i];
            two--;
            i++;
        }

        while (i < n && three > 0) {
            ans += 3 * freq[i];
            three--;
            i++;
        }

        while (i < n && four > 0) {
            ans += 4 * freq[i];
            four--;
            i++;
        }

        return ans;
    }
}
    time complexity: O(nlogn) where n is the length of the word
    space complexity: O(1)
    leetcode runs this in 8 MS

Approach 2:
class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int[] freq = new int[26];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            freq[word.charAt(i) - 'a']++;
        }

        Arrays.sort(freq);

        int one = 8;
        int two = 8;
        int three = 8;
        int four = 2;

        int i = 25;

        while (i >= 0 && one > 0 && freq[i] > 0) {
            ans += freq[i];
            one--;
            i--;
        }

        while (i >= 0 && two > 0  && freq[i] > 0) {
            ans += 2 * freq[i];
            two--;
            i--;
        }

        while (i >= 0 && three > 0 && freq[i] > 0) {
            ans += 3 * freq[i];
            three--;
            i--;
        }

        while (i >= 0 && four > 0 && freq[i] > 0) {
            ans += 4 * freq[i];
            four--;
            i--;
        }

        return ans;
    }
}

    time complexity: O(nlogn) where n is the length of the word
    space complexity: O(1)
    Leetcode runs this in 9 MS
*/

class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int[] freq = new int[26];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            freq[word.charAt(i) - 'a']++;
        }

        countSort(freq);

        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;
            
            int k = 25 - i;
            int multiplier = (k / 8) + 1;
            
            ans += freq[i] * multiplier;
        }

        return ans;
    }

    public void countSort(int[] nums){
       int max=0;
        for(int i=0;i<26;i++){
        max=Math.max(nums[i],max);
        }
        int freq[]=new int[max+1];
        for(int i=0;i<26;i++){
            freq[nums[i]]++;
        }
        int j=0;
        for(int i=0;i<max+1;i++){
            while(freq[i]>0){
                nums[j]=i;
                freq[i]--;
                j++;
            }
        }
    }
}
// Time Complexity: O(n) where n is the length of the word 
// leetcode runs this in 7 MS


class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "aabbccddeeffgghhiiiiii"; // Answer: 24
        int result = solution.minimumPushes(word);
        System.out.println("Minimum number of pushes to type the word '" + word + "': " + result);
    }
}
