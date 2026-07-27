package data_structure.tree;
import java.util.List;
import java.util.ArrayList;
// import java.util.Queue;
// import java.util.LinkedList;

//  Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
 
/*
Approach 1: BFS Level order traversal

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
      
        List<Integer> l = new ArrayList<>();
        if(root == null){
          return l;
        }
      
        Queue<TreeNode> q = new LinkedList<>();
        
        q.add(root);
        
        while(!q.isEmpty()){
          int n = q.size();
          TreeNode node = null;
          while(n-- != 0){
            node = q.poll();
            
            if(node.left != null){
              q.add(node.left);
            }
            if(node.right != null){
              q.add(node.right);
            }
            
          }
          l.add(node.val);
        }
        
        return l;
    }
}
Time Complexity: O(n) where n is the number of nodes in the binary tree.
Space Complexity: O(n) where n is the number of nodes in the binary tree.
*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    
    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
        
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();

        // pass vales root = [1,2,3,null,5,null,4]
        
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        List<Integer> result = solution.rightSideView(root);
        System.out.println("Right side view of the binary tree: " + result);
    }
}