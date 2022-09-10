import java.util.*;
import utils.TreeNode;
import utils.TreeNodeP;
import utils.KnaryTreeNode;

public class CrossTrainingI {
    public static void main(String[] args) {
        String[] input = new String[] {
            "4","3","8","1","#","6","#","#","2","5","7"
        };
        TreeNode root = TreeNode.fromLevelOrder(input);
        CrossTrainingI c = new CrossTrainingI();
        double target = -1000.0;
        int k = 3;
        int[] result = c.closestKValues(root, target, k);
        System.out.println(result);
    }

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
     * Given two nodes in a binary tree (with parent pointer available), find their
     * lowest common ancestor.
     */
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        TreeNodeP cur1 = one;
        TreeNodeP cur2 = two;

        int height1 = 0;
        int height2 = 0;

        while (cur1.parent != null) {
            height1++;
            cur1 = cur1.parent;
        }
        while (cur2.parent != null) {
            height2++;
            cur2 = cur2.parent;
        }

        if (height1 < height2) {
            int h = height2 - height1;
            while (h-- > 0) {
                two = two.parent;
            }
        } else {
            int h = height1 - height2;
            while (h-- > 0) {
                one = one.parent;
            }
        }

        while (one != two) {
            one = one.parent;
            two = two.parent;
        }
        return one;
    }

    /**
     * Array Deduplication IV
     * <p>
     * Given an unsorted integer array, remove adjacent duplicate elements
     * repeatedly, from left to right. For each group of elements with the same
     * value do not keep any of them. Do this in-place, using the left side of the
     * original array. Return the array after deduplication.
     */
    public int[] dedup4(int[] array) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (i == 0 || array[i - 1] != array[j]) {
                array[i++] = array[j++];
            } else {
                while (j < array.length && array[i - 1] == array[j]) {
                    j++;
                }
                i--;
            }
        }
        return Arrays.copyOf(array, i);
    }

    /**
     * Closest Number In Binary Search Tree II
     * <p>
     * In a binary search tree, find k nodes containing the closest numbers to the
     * given target number. return them in sorted array
     */
    public int[] closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return (int) (Math.abs(b - target) - Math.abs(a - target));
            }
        });

        closestKValues(root, pq, k, target);
        k = Math.min(pq.size(), k);
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        Arrays.sort(result);

        return result;
    }

    private void closestKValues(TreeNode node, PriorityQueue<Integer> pq, int k, double target) {
        if (node == null) {
            return;
        }
        int key = node.key;
        if (pq.size() < k) {
            pq.offer(key);
        } else if (Math.abs(key - target) < Math.abs(pq.peek() - target)) {
            pq.poll();
            pq.offer(key);
        }
        closestKValues(node.left, pq, k, target);
        closestKValues(node.right, pq, k, target);
    }

    /**
     * Lowest Common Ancestor IV
     * <p>
     * Given K nodes in a binary tree, find their lowest common ancestor.
    */
    public TreeNode lowestCommonAncestor2(TreeNode root, List<TreeNode> nodes) {
        Set<TreeNode> set = new HashSet<>();
        for (TreeNode n : nodes) {
            set.add(n);
        }

        return lowestCommonAncestor(root, set);
    }

    private TreeNode lowestCommonAncestor(TreeNode node, Set<TreeNode> set) {
        if (node == null || set.contains(node)) {
            return node;
        }

        TreeNode left = lowestCommonAncestor(node.left, set);
        TreeNode right = lowestCommonAncestor(node.right, set);

        if (left != null && right != null) {
            return node;
        } else {
            return left == null ? right : left;
        }
    }

    /**
     * Lowest Common Ancestor V
     * <p>
     * Given two nodes in a K-nary tree, find their lowest common ancestor.
    */
    public KnaryTreeNode lowestCommonAncestor3(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }

        KnaryTreeNode prev = null;

        for (KnaryTreeNode node : root.children) {
            if (prev == null) {
                prev = lowestCommonAncestor3(node, a, b);
            } else {
                if (lowestCommonAncestor3(node, a, b) != null) {
                    return root;
                }
            }
        }

        return prev;
    }

    /**
     * Lowest Common Ancestor VI
     * <p>
     * Given M nodes in a K-nary tree, find their lowest common ancestor.
    */
    public KnaryTreeNode lowestCommonAncestor4(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
        Set<KnaryTreeNode> set = new HashSet<>(nodes);
        return lowestCommonAncestor4(root, set);
    }

    private KnaryTreeNode lowestCommonAncestor4(KnaryTreeNode root, Set<KnaryTreeNode> set) {
        if (root == null || set.contains(root)) {
            return root;
        }

        KnaryTreeNode prev = null;

        for (KnaryTreeNode node : root.children) {
            if (prev == null) {
                prev = lowestCommonAncestor4(node, set);
            } else {
                if (lowestCommonAncestor4(node, set) != null) {
                    return root;
                }
            }
        }

        return prev;
    }
}
