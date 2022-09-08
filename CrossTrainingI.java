import java.util.*;
import utils.TreeNode;
import utils.TreeNodeP;

public class CrossTrainingI {
    /**
     * Array Deduplication I
     * <p>
     * Given a sorted integer array, remove duplicate elements. For each group of
     * elements with the same value keep only one of them. Do this in-place, using
     * the left side of the original array and maintain the relative order of the
     * elements of the array. Return the array after deduplication.
     */
    public int[] dedup(int[] array) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (i == 0 || array[i - 1] != array[j]) {
                array[i++] = array[j++];
            } else {
                while (j < array.length && array[i - 1] == array[j]) {
                    j++;
                }
            }
        }
        return Arrays.copyOf(array, i);
    }

    /**
     * Array Deduplication II
     * <p>
     * Given a sorted integer array, remove duplicate elements. For each group of
     * elements with the same value keep at most two of them. Do this in-place,
     * using the left side of the original array and maintain the relative order of
     * the elements of the array. Return the array after deduplication.
     */
    public int[] dedup2(int[] array) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (i == 0 || i == 1 || array[i - 2] != array[j]) {
                array[i++] = array[j++];
            } else {
                while (j < array.length && array[i - 2] == array[j]) {
                    j++;
                }
            }
        }
        return Arrays.copyOf(array, i);
    }

    /**
     * Array Deduplication III
     * <p>
     * Given a sorted integer array, remove duplicate elements. For each group of
     * elements with the same value do not keep any of them. Do this in-place, using
     * the left side of the original array and and maintain the relative order of
     * the elements of the array. Return the array after deduplication.
     */
    public int[] dedup3(int[] array) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (j == array.length - 1 || array[j] != array[j + 1]) {
                array[i++] = array[j++];
            } else {
                int cur = array[j];
                while (j < array.length && array[j] == cur) {
                    j++;
                }
            }
        }
        return Arrays.copyOf(array, i);
    }

    /**
     * Move 0s To The End II
     * <p>
     * Given an array of integers, move all the 0s to the right end of the array.
     * The relative order of the elements in the original array need to be
     * maintained.
     */
    public int[] moveZero(int[] array) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (array[j] != 0) {
                swap(array, i++, j++);
            } else {
                j++;
            }
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Rotate Matrix
     * <p>
     * Rotate an N * N matrix clockwise 90 degrees.
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    /**
     * Get Keys In Binary Tree Layer By Layer Zig-Zag Order
     * <p>
     * Get the list of keys in a given binary tree layer by layer in zig-zag order.
     */
    public List<Integer> zigZag(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        deque.offerLast(root);
        boolean l2r = false;
        while (!deque.isEmpty()) {
            if (l2r) {
                int size = deque.size();
                while (size-- > 0) {
                    TreeNode node = deque.pollFirst();
                    result.add(node.key);
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                }
            } else {
                int size = deque.size();
                while (size-- > 0) {
                    TreeNode node = deque.pollLast();
                    result.add(node.key);
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }

                }
            }
            l2r = !l2r;
        }
        return result;
    }

    /**
     * Lowest Common Ancestor Binary Search Tree I
     * <p>
     * Given two keys in a binary search tree, find their lowest common ancestor.
     */
    public TreeNode lca(TreeNode root, int p, int q) {
        if (p > q) {
            int temp = p;
            p = q;
            q = temp;
        }
        while (root.key > q || root.key < p) {
            if (root.key < p) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }

    /**
      * Lowest Common Ancestor II
      * <p>
      * Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.
    */
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        // TreeNodeP 
        return one;
    }
}
