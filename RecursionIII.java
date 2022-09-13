import utils.*;

public class RecursionIII {

    private int max;

    /**
     * Maximum Path Sum Binary Tree II
     * <p>
     * Given a binary tree in which each node contains an integer number. Find the
     * maximum possible sum from any node to any node (the start node and the end
     * node can be the same).
     */
    public int maxPathSum(TreeNode root) {
        this.max = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return this.max;
    }

    private int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = maxPathSumHelper(node.left);
        int right = maxPathSumHelper(node.right);

        this.max = Math.max(max, Math.max(0, left) + node.key + Math.max(0, right));
        return Math.max(Math.max(left, right), 0) + node.key;
    }

    /**
     * Max Path Sum From Leaf To Root
     * <p>
     * Given a binary tree in which each node contains an integer number. Find the
     * maximum possible path sum from a leaf to root.
     */
    public int maxPathSumLeafToRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSumLeafToRoot(root.left);
        int right = maxPathSumLeafToRoot(root.right);
        if (root.left == null) {
            return right + root.key;
        } else if (root.right == null) {
            return left + root.key;
        }
        return Math.max(left, right) + root.key;
    }
}
