class Solution {
    public long countIntersectingIntervals(int[][] intervals) {
        long count = 0;
        int n = intervals.length;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for(int i = 0; i < n; i++){
            int s1 = intervals[i][0];
            int e1 = intervals[i][1];
            while(!minHeap.isEmpty() && minHeap.peek() < s1){
                minHeap.poll();
            }

            count += minHeap.size();

            minHeap.offer(e1);
        }
        
        return count;
    }
}