// Definition of the Solution class
class Solution {
    // HashSet to store the values to be deleted efficiently
    HashSet<Integer> set = new HashSet<>();
    // List to store the roots of the resulting trees
    List<TreeNode> list = new ArrayList<>();
    
    // Method to delete nodes from a binary tree
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // Add values to be deleted to the HashSet for efficient lookup
        for(int val: to_delete){
            set.add(val);
        }
        // Recursively process the tree and add roots of resulting trees to the list
        helper(root);
        // If the original root is not deleted, add it to the list
        if(!set.contains(root.val)){
            list.add(root);
        }
        // Return the list of roots of resulting trees
        return list;
    }
    
    // Recursive helper method to delete nodes and return updated roots
    public TreeNode helper(TreeNode node){
        // Base case: if node is null, return null
        if(node == null)
            return null;
        
        // Recursively process left and right subtrees
        node.left = helper(node.left);
        node.right = helper(node.right);
        
        // If the current node is in the set of values to be deleted
        if(set.contains(node.val)){
            // If left child exists and not in delete set, add it to the result list
            if(node.left != null){
                list.add(node.left);
            }
            // If right child exists and not in delete set, add it to the result list
            if(node.right != null){
                list.add(node.right);
            }
            // Return null to indicate deletion of current node
            return null;
        }
        // If the current node is not in the delete set, return it
        return node;
    }
}
