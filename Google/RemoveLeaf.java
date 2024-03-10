import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        while (root != null) {
            List<Integer> leaves = new LinkedList<>();
            root = removeLeaves(root, leaves);
            res.add(leaves);
        }
        return res;
    }

    private TreeNode removeLeaves(TreeNode node, List<Integer> leaves) {
        if (node == null)
            return null;
        
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return null;
        }
        
        node.left = removeLeaves(node.left, leaves);
        node.right = removeLeaves(node.right, leaves);
        
        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Create an instance of the Solution class
        Solution solution = new Solution();

        // Find and print the leaves of the tree
        List<List<Integer>> result = solution.findLeaves(root);
        for (List<Integer> leaves : result) {
            System.out.println(leaves);
        }
    }
}
