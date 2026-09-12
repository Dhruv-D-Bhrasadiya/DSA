class Solution {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, List<Integer>> hm = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            hm.computeIfAbsent(nums[i], k-> new ArrayList<Integer>()).add(i);
        }

        int count = 0;
        for(Map.Entry<Integer, List<Integer>> entry : hm.entrySet()){
            List<Integer> l = entry.getValue();
            if(l.size() == 3){
                int i1 =  l.get(0);
                int i2 =  l.get(1);
                int i3 =  l.get(2);
                if(i2 - i1 == i3 - i2){
                    count++;
                }
            }
        }
        return count;
    }
}