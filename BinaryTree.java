import java.util.*;
import utils.TreeNode;

public class BinaryTree {
    /**
     * Pre-order Traversal Of Binary Tree (recursive)
     * <p>
     * Implement a recursive, pre-order traversal of a given binary tree, return the
     * list of keys of each node in the tree as it is pre-order traversed.
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

    /**
     * Height of Binary Tree
     * <p>
     * Find the height of binary tree
     */
    public int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int h1 = findHeight(root.left);
        int h2 = findHeight(root.right);
        return Math.max(h1, h2) + 1;
    }

    /**
     * Check If Binary Tree Is Balanced
     * <p>
     * Check if a given binary tree is balanced. A balanced binary tree is one in
     * which the depths of every node’s left and right subtree differ by at most 1.
     */
    public boolean isBalanced(TreeNode root) {
        return findIfBalanced(root) != -1;
    }

    private int findIfBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h1 = findIfBalanced(root.left);
        int h2 = findIfBalanced(root.right);

        if (h1 == -1 || h2 == -1 || Math.abs(h1 - h2) > 1) {
            return -1;
        }

        return Math.max(h1, h2) + 1;
    }

    /**
     * Symmetric Binary Tree
     * <p>
     * Check if a given binary tree is symmetric
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.key != right.key) {
            return false;
        }

        return isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
    }

    /**
     * Tweaked Identical Binary Trees
     * <p>
     * Determine whether two given binary trees are identical assuming any number of
     * ‘tweak’s are allowed. A tweak is defined as a swap of the children of one
     * node in the tree.
     */
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null || one.key != two.key) {
            return false;
        }
        return (isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)
                || isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left));
    }

    /**
     * Is Binary Search Tree Or Not
     * <p>
     * Determine if a given binary tree is binary search tree.There should no be
     * duplicate keys in binary search tree.
     */
    public boolean isBST(TreeNode root) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        return isBST(root, min, max);
    }

    private boolean isBST(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.key >= max || node.key <= min) {
            return false;
        }

        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

    /**
     * Get Keys In Binary Search Tree In Given Range
     * <p>
     * Get the list of keys in a given binary search tree in a given range[min, max]
     * in ascending order, both min and max are inclusive.
     */
    public List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        getRange(root, min, max, result);
        return result;
    }

    private void getRange(TreeNode node, int min, int max, List<Integer> result) {
        if (min > max) {
            return;
        }
        if (node == null) {
            return;
        }
        getRange(node.left, min, Math.min(node.key, max), result);
        if (node.key >= min && node.key <= max) {
            result.add(node.key);
        }
        getRange(node.right, Math.max(node.key, min), max, result);
    }

    /**
     * Search In Binary Search Tree
     * <p>
     * Find the target key K in the given binary search tree, return the node that
     * contains the key if K is found, otherwise return null.
     */
    public TreeNode search(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.key == key) {
            return root;
        }
        if (root.key > key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    /**
     * Insert in Binary Search Tree
     * <p>
     * Insert a key in a binary search tree if the binary search tree does not
     * already contain the key. Return the root of the binary search tree.
     */
    // public TreeNode insert(TreeNode root, int key) {
    // if (root == null) {
    // return new TreeNode(key);
    // }
    // insertHelper(root, key);
    // return root;
    // }

    // private void insertHelper(TreeNode node, int key) {
    // if (node == null || node.key == key) {
    // return;
    // }

    // if (node.key > key) {
    // if (node.left == null) {
    // node.left = new TreeNode(key);
    // } else {
    // insertHelper(node.left, key);
    // }
    // } else {
    // if (node.right == null) {
    // node.right = new TreeNode(key);
    // } else {
    // insertHelper(node.right, key);
    // }
    // }
    // }
    public TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (root.key < key) {
            root.right = insert(root.right, key);
        } else if (root.key > key) {
            root.left = insert(root.left, key);
        }
        return root;
    }

    /**
     * Delete In Binary Search Tree
     * <p>
     * Delete the target key K in the given binary search tree if the binary search
     * tree contains K. Return the root of the binary search tree. Find your own way
     * to delete the node from the binary search tree, after deletion the binary
     * search tree's property should be maintained.
     */
    public TreeNode deleteTree(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.key > key) {
            root.left = deleteTree(root.left, key);
            return root;
        }
        if (root.key < key) {
            root.right = deleteTree(root.right, key);
            return root;
        }

        if (root.right == null) {
            return root.left;
        }
        if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }

        TreeNode node = root.right;
        while (node.left.left != null) {
            node = node.left;
        }
        TreeNode newRoot = node.left;
        node.left = newRoot.right; // !!!!IMPORTANT
        newRoot.left = root.left;
        newRoot.right = root.right;
        return newRoot;
    }

    /**
     * Pre-order Traversal of Binary Tree (iterative)
     * <p>
     * Implement an iterative, pre-order traversal of a given binary tree, return
     * the list of keys of each node in the tree as it is pre-order traversed.
     */
    public List<Integer> preOrder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            result.add(node.key);
            if (node.right != null) {
                stack.offerLast(node.right);
            }
            if (node.left != null) {
                stack.offerLast(node.left);
            }
        }
        return result;
    }

    /**
     * In-order Traversal of Binary Tree (iterative)
     * <p>
     * Implement an iterative, in-order traversal of a given binary tree, return the
     * list of keys of each node in the tree as it is in-order traversed.
     */
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        TreeNode helper = root.left;
        TreeNode node;

        while (helper != null || !stack.isEmpty()) {
            if (helper == null) {
                node = stack.pollLast();
                result.add(node.key);
                helper = node.right;
            } else {
                stack.offerLast(helper);
                helper = helper.left;
            }
        }

        return result;
    }

    /**
     * Post-order Traversal of Binary Tree (iterative)
     * <p>
     * Implement an iterative, post-order traversal of a given binary tree, return
     * the list of keys of each node in the tree as it is post-order traversed.
     */
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode helper = root;
        TreeNode node;

        while (helper != null || !stack.isEmpty()) {
            if (helper == null) {
                node = stack.peekLast();
                if (node.right != null) {
                    stack.offerLast(node.right);
                    helper = node.right.left;
                } else {
                    result.add(node.key);
                    helper = null;
                    if (!stack.isEmpty() && stack.peekLast().right == node) {
                        helper = node;
                    }
                }
            } else if (helper == stack.peekLast().right) {
                node = stack.pollLast();
                result.add(node.key);
                helper = null;
                if (!stack.isEmpty() && stack.peekLast().right == node) {
                    helper = node;
                }
            } else {
                stack.offerLast(helper);
                helper = helper.left;
            }
        }
        return result;
    }

    public List<Integer> postOrder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        TreeNode helper = root;

        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekLast();
            if (cur.right == helper || cur.left == helper && cur.right == null) {
                result.add(cur.key);
                helper = cur;
            } else {
                if (cur.right != null) {
                    stack.offerLast(cur.right);
                }
                if (cur.left != null) {
                    stack.offerLast(cur.left);
                }
                if (cur.left == null && cur.right == null) {
                    helper = null;
                }
            }
        }
        return result;
    }
}
