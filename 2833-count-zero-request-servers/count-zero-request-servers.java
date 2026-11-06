class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int ql = queries.length;
        int ll = logs.length;

        int[] arr = new int [ql];

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        Integer[] qidx = new Integer[ql];
        for(int i = 0; i < ql; i++){
            qidx[i] = i;
        }

        Arrays.sort(qidx, (a, b) -> Integer.compare(queries[a], queries[b]));

        int r = 0;
        int l = 0;
        
        Map<Integer, Integer> hm = new HashMap<>();
        
        for(int idx : qidx){
            int qt = queries[idx];
            int s = qt - x;
            int e = qt;
            

            while(r < ll && logs[r][1] <= e){
                int sidx = logs[r][0];
                hm.put(sidx, hm.getOrDefault(sidx, 0) + 1);
                r++;
            }

            while(l < ll && logs[l][1] < s){
                int sidx = logs[l][0];
                hm.put(sidx, hm.get(sidx) - 1);

                if(hm.get(sidx) == 0){
                    hm.remove(sidx);
                }
                l++;
            }
            
            arr[idx] = n - hm.size();
        }
        return arr;
    }
}