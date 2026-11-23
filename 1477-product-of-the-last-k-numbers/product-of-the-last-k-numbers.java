class ProductOfNumbers {
    private int nums[];
    private int size;

    public ProductOfNumbers() {
        nums = new int[16];
        nums[0] = 1;
        size = 1;
    }
    
    public void add(int num) {
        if(num == 0){
            size = 1;
            nums[0] = 1;
        }
        else{
            if(size == nums.length){
                nums = Arrays.copyOf(nums, size * 2);
            }
            nums[size] = nums[size - 1] * num;
            size++;
        }
    }
    
    public int getProduct(int k) {
        if(k >= size){
            return 0;
        }
        return nums[size - 1] / nums[size - 1 - k];
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */