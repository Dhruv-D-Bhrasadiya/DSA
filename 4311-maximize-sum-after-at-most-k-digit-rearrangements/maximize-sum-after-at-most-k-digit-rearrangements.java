class Solution {
    public long maximizeSum(int[] nums, int k) {
        long sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i : nums){
            sum += i;
            pq.add(getMax(i) - i);
        }
        while (k > 0 && !pq.isEmpty() && pq.peek() > 0){
            sum += pq.poll();
            k--;
        }
        return sum;
    }
    public int getMax(int num){
        int[] digit = new int[10];
        while(num > 0){
            digit[num % 10]++;
            num /= 10;
        }
        int ans = 0;
        for(int i = 9; i >= 0; i--){
            while(digit[i] > 0){
                ans = ans * 10 + i;
                digit[i]--;
            }
        }
        return ans;
    }
}