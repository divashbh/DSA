public class SiblingNodesChecker {

    // Variables to store the depths and parent nodes for the given values nodeA and
    // nodeB
    private int depthNodeA, depthNodeB;
    private TreeNode parentNodeA, parentNodeB;

    // Function to check if two nodes in a binary tree are siblings
    public boolean areSiblings(TreeNode root, int nodeAValue, int nodeBValue) {
        // Perform a depth-first search to find the depths and parents of nodeA and
        // nodeB
        dfs(root, null, nodeAValue, nodeBValue, 0);
        // Return true if both nodes are at the same depth and have different parents
        return depthNodeA == depthNodeB && parentNodeA != parentNodeB;
    }

    // Depth-first search function to traverse the tree and find depths and parents
    private void dfs(TreeNode node, TreeNode parent, int nodeAValue, int nodeBValue, int depth) {
        if (node == null) {
            return;
        }

        // If the current node matches the value of nodeA
        if (node.val == nodeAValue) {
            depthNodeA = depth; // Set the depth of nodeA
            parentNodeA = parent; // Set the parent of nodeA
        }
        // If the current node matches the value of nodeB
        else if (node.val == nodeBValue) {
            depthNodeB = depth; // Set the depth of nodeB
            parentNodeB = parent; // Set the parent of nodeB
        }

        // Recur to the left and right subtrees
        dfs(node.left, node, nodeAValue, nodeBValue, depth + 1);
        dfs(node.right, node, nodeAValue, nodeBValue, depth + 1);
    }

    public static void main(String[] args) {
        // Create the binary tree
        /*
         * 1
         * / \
         * 2 3
         * /
         * 4
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        int nodeAValue = 4, nodeBValue = 3;

        SiblingNodesChecker snc = new SiblingNodesChecker();
        // Check if nodes with values nodeAValue and nodeBValue are siblings
        boolean result = snc.areSiblings(root, nodeAValue, nodeBValue);
        System.out.println(result); // Print the result
    }
}

// Definition of TreeNode class
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
