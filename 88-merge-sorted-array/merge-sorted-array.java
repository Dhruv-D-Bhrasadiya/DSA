class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // int nums1[] = new int[m + n];
        if(m == 1 && n == 0){
            return;
        }
        else if(m == 0 && n == 1){
            nums1[0] = nums2[0];
            return;
        }
        else if(m == 0 && n == 0){
            return;
        }
        int i = m - 1; 
        int j = n - 1;
        int p = m + n - 1;

        while(i >= 0 && j >= 0){
            if(nums1[i] == nums2[j]){
                nums1[p--] = nums1[i--];
                nums1[p--] = nums2[j--];
            }
            else if(nums1[i] > nums2[j]){
                nums1[p--] = nums1[i--];
            }
            else{
                nums1[p--] = nums2[j--];
            }
        }
        if(i >= 0){
            while(i >= 0){
                nums1[p--] = nums1[i--];
            }   
        }
        else{
            while(j >= 0){
                nums1[p--] = nums2[j--];
            }
        }
    }
}