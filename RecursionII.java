import java.util.*;
import utils.ListNode;
import utils.TreeNode;

public class RecursionII {
    public static void main(String[] args) {
        String input = "laioffercom";
        String pattern = "2io2e4";
        RecursionII r = new RecursionII();
        r.match(input, pattern);
    }

    /**
     * Spiral Order Traverse I
     * <p>
     * Traverse an N * N 2D array in spiral order clock-wise starting from the top
     * left corner. Return the list of traversal sequence.
     */
    public List<Integer> spiral(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        spiral(matrix, 0, result);
        return result;
    }

    private void spiral(int[][] matrix, int offset, List<Integer> result) {
        int n = matrix.length;
        if (offset == n / 2) {
            if (n % 2 == 1) {
                result.add(matrix[offset][offset]);
            }
            return;
        }

        for (int i = offset; i < n - offset - 1; i++) {
            result.add(matrix[offset][i]);
        }

        for (int i = offset; i < n - offset - 1; i++) {
            result.add(matrix[i][n - offset - 1]);
        }

        for (int i = n - offset - 1; i > offset; i--) {
            result.add(matrix[n - offset - 1][i]);
        }

        for (int i = n - offset - 1; i > offset; i--) {
            result.add(matrix[i][offset]);
        }
        spiral(matrix, offset + 1, result);
    }

    /**
     * N Queens
     * <p>
     * Get all valid ways of putting N Queens on an N * N chessboard so that no two
     * Queens threaten each other.
     */
    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        nqueens(n, cur, result);
        return result;
    }

    private void nqueens(int n, List<Integer> cur, List<List<Integer>> result) {
        if (cur.size() == n) {
            result.add(new ArrayList<Integer>(cur));
            return;
        }

        int curRow = cur.size();
        for (int i = 0; i < n; i++) {
            boolean valid = true;
            for (int j = 0; j < curRow; j++) {
                if (i == cur.get(j) || curRow + i == j + cur.get(j) || curRow - i == j - cur.get(j)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                cur.add(i);
                nqueens(n, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }

    /**
     * Reverse Linked List In Pairs
     * <p>
     * Reverse pairs of elements in a singly-linked list.
     */
    public ListNode reverseInPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = reverseInPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * String Abbreviation Matching
     * <p>
     * Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an
     * abbreviation, return if the string matches the abbreviation.
     */
    public boolean match(String input, String pattern) {
        char[] arr = input.toCharArray();
        char[] pa = pattern.toCharArray();

        int i = 0;
        int j = 0;

        int cur = 0;

        while (i < arr.length) {
            while (j < pa.length && pa[j] - '0' < 10 && pa[j] - '0' >= 0) {
                cur *= 10;
                cur += pa[j++] - '0';
            }

            if (cur != 0) {
                i++;
                cur--;
            } else {
                if (j == pa.length) {
                    return false;
                } else if (arr[i] != pa[j]) {
                    return false;
                } else {
                    i++;
                    j++;
                }
            }
        }

        if (cur != 0 || i != arr.length || j != pa.length) {
            return false;
        }
        return true;
    }

    /**
     * Store Number Of Nodes In Left Subtree
     * <p>
     * Given a binary tree, count the number of nodes in each node’s left subtree,
     * and store it in the numNodesLeft field.
     */
    public void numNodesLeft(TreeNode root) {
        numNodesLeftHelper(root);
    }

    public int numNodesLeftHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = numNodesLeftHelper(root.left);
        int right = numNodesLeftHelper(root.right);
        root.key = left;
        return left + right + 1;
    }

    /**
      * Lowest Common Ancestor I
      * <p>
      * Given two nodes in a binary tree, find their lowest common ancestor.
    */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null || root == one || root == two) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, one, two);
        TreeNode right = lowestCommonAncestor(root.right, one, two);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
