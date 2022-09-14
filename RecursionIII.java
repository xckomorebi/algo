import java.util.*;
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

    /**
     * Binary Tree Path Sum To Target III
     * <p>
     * Given a binary tree in which each node contains an integer number. Determine
     * if there exists a path (the path can only be from one node to itself or to
     * any of its descendants), the sum of the numbers on the path is the given
     * target number.
     */
    public boolean exist(TreeNode root, int target) {
        this.map = new HashMap<>();
        this.found = false;
        map.put(0, 1);
        exist(root, 0, target);
        return this.found;
    }

    private boolean found;
    private Map<Integer, Integer> map;

    private void exist(TreeNode node, int sum, int target) {
        if (found) {
            return;
        }
        if (node == null) {
            return;
        }
        int curSum = sum + node.key;
        if (map.containsKey(curSum - target)) {
            found = true;
            return;
        }

        map.put(curSum, map.containsKey(curSum) ? map.get(curSum) + 1 : 1);
        exist(node.left, curSum, target);
        exist(node.right, curSum, target);

        if (map.get(curSum) == 1) {
            map.remove(curSum);
        } else {
            map.put(curSum, map.get(curSum) - 1);
        }
    }

    /**
     * Maximum Path Sum Binary Tree III
     * <p>
     * Given a binary tree in which each node contains an integer number. Find the
     * maximum possible subpath sum(both the starting and ending node of the subpath
     * should be on the same path from root to one of the leaf nodes, and the
     * subpath is allowed to contain only one node).
     */
    public int maxPathSum2(TreeNode root) {
        this.max = Integer.MIN_VALUE;
        maxPathSum(root, 0);
        return this.max;
    }

    private void maxPathSum(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        int curSum = sum + node.key;
        this.max = Math.max(this.max, curSum);
        curSum = Math.max(0, curSum);
        maxPathSum(node.left, curSum);
        maxPathSum(node.right, curSum);
    }

    /**
     * Flatten Binary Tree to Linked List
     * <p>
     * Given a binary tree, flatten it to a linked list in-place.
     */
    public TreeNode flatten(TreeNode root) {
        if (root == null) {
            return root;
        }
        flattenHelper(root);
        return root;
    }

    public TreeNode flattenHelper(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node;
        }

        if (node.left == null) {
            return flattenHelper(node.right);
        }
        if (node.right == null) {
            node.right = node.left;
            node.left = null;
            return flattenHelper(node.right);
        }

        TreeNode left = flattenHelper(node.left);
        TreeNode right = flattenHelper(node.right);
        left.right = node.right;
        node.right = node.left;
        node.left = null;

        return right;
    }

    public TreeNode reconstruct(int[] in, int[] pre) {
        this.map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        TreeNode root = reconstruct(in, 0, in.length - 1, pre, 0, pre.length - 1);
        return root;
    }

    private TreeNode reconstruct(int[] in, int inLeft, int inRight, int[] pre, int preLeft, int preRight) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode node = new TreeNode(pre[preLeft]);
        int mid = map.get(pre[preLeft]);
        node.left = reconstruct(in, inLeft, mid - 1, pre, preLeft + 1, preLeft + mid - inLeft);
        node.right = reconstruct(in, mid + 1, inRight, pre, preRight + mid - inRight + 1, preRight);
        return node;
    }

    public TreeNode reconstruct(int[] post) {
        TreeNode root = reconstruct(post, 0, post.length - 1);
        return root;
    }

    private TreeNode reconstruct(int[] post, int left, int right) {
        if (right < left) {
            return null;
        }

        TreeNode node = new TreeNode(post[right]);
        if (left == right) {
            return node;
        }
        int mid = binarySearch(post, left, right - 1, post[right]);
        node.left = reconstruct(post, left, mid);
        node.right = reconstruct(post, mid + 1, right - 1);
        return node;

    }

    private int binarySearch(int[] post, int left, int right, int target) {
        if (post[left] > target) {
            return left - 1;
        }
        if (post[right] < target) {
            return right;
        }

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (post[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
