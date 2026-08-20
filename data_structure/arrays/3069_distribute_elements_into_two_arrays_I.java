package data_structure.arrays;

/*
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        if(n == 2){
            return nums;
        }

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        l1.add(nums[0]);
        l2.add(nums[1]);


        for(int i = 2; i < n; i++){
            if(l1.getLast() > l2.getLast()){
                l1.add(nums[i]);
                System.out.println(nums[i] + " In l1");

            }
            else{
                l2.add(nums[i]);
                System.out.println(nums[i] + " In l2");
            }
        }

        int[] ans = new int[n];
        int p = 0;

        for(int i = 0; i < l1.size(); i++){
            ans[i] = l1.get(p++);
        }
        p = 0;
        for(int i = l1.size(); i < n; i++){
            ans[i] = l2.get(p++);
        }
        return ans;
    }
}
Optimal solution given below:
 */

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        if(n <= 2){
            return nums;
        }

        int a = nums[0];
        int b = nums[1];
        int p1 = 1;

        nums[1] *= -1;

        for(int i = 2; i < n; i++){
            if(a > b){
                a = nums[i];
                p1++;
            }
            else{
                b = nums[i];
                nums[i] = -1 * nums[i];
            }
        }
        
        int[] ans = new int[n];
        int idx1 = 0; 
        int idx2 = p1;

        for(int i = 0; i < n; i++){
            if(nums[i] < 0){
                ans[idx2++] = nums[i] * -1;
            }
            else{
                ans[idx1++] = nums[i];
            }
        }

        return ans;
    }
}

class Main{
    public static void main(String args[]){
        Solution s = new Solution();
        int[] nums = {5,4,3,8};
        int[] ans = s.resultArray(nums);
        for(int i:ans){
            System.out.print(i + " ");
        } // Output = 5 8 4 3
    }
}