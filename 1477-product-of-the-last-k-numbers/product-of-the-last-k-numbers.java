class ProductOfNumbers {
    List<Integer> nums;

    public ProductOfNumbers() {
        nums = new ArrayList<>();
    }
    
    public void add(int num) {
        nums.add(num);
    }
    
    public int getProduct(int k) {
        int i = nums.size() - 1;
        int prod = 1;
        while(k > 0){
            prod *= nums.get(i);
            i--;
            k--;
        }
        return prod;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */