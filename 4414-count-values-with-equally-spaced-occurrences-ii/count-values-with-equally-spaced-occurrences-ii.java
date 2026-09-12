class Solution {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, List<Integer>> hm = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            hm.computeIfAbsent(nums[i], k-> new ArrayList<Integer>()).add(i);
        }

        int count = 0;
        for(Map.Entry<Integer, List<Integer>> entry : hm.entrySet()){
            List<Integer> l = entry.getValue();
            int n = l.size();
            
            if(n >= 3){
                int diff = l.get(1) - l.get(0);
                boolean check = true;
                    
                for(int i = 2; i < n; i++){ 
                    if(l.get(i) - l.get(i - 1) != diff){
                        check = false;
                        break;
                    }
                }
                if(check){
                    count++;
                }
            }
        }
        return count;
    }
}