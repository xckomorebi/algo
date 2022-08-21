import java.util.*;
import utils.TreeNode;

public class BinaryTree {
    /**
      * Pre-order Traversal Of Binary Tree (recursive)
      * <p>
      * Implement a recursive, pre-order traversal of a given binary tree, return the list of keys of each node in the tree as it is pre-order traversed.
    */
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        preOrder(root, result);
        return result;
    }

    private void preOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.key);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }
}
