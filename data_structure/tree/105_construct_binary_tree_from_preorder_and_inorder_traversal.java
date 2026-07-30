package data_structure.tree;

import java.util.Map;
import java.util.HashMap;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int x) { 
        val = x;
    }
    
    TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private int preorderIndex;
    private Map<Integer, Integer> mapping;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        mapping = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            mapping.put(inorder[i], i);
        }

        preorderIndex = 0;
        return build(preorder, 0, inorder.length - 1);        
    }

    private TreeNode build(int[] preorder, int start, int end) {
        if (start > end) return null;

        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        int mid = mapping.get(rootVal);

        root.left = build(preorder, start, mid - 1);
        root.right = build(preorder, mid + 1, end);

        return root;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = solution.buildTree(preorder, inorder);

        // Output: [3,9,20,null,null,15,7]
        // Traverse the tree and print the values to verify the structure
        printTree(root);
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}