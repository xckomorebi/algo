package utils;

import java.util.*;

public class TreeNode {
    public int key;

    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.key = key;
    }

    public static TreeNode fromLevelOrder(String[] input) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.valueOf(input[0]));
        queue.offer(root);

        int i = 1;
        while (i < input.length) {
            TreeNode node = queue.poll();
            if (!input[i].equals("#")) {
                node.left = new TreeNode(Integer.valueOf(input[i]));
                queue.offer(node.left);
            }
            i++;
            if (i == input.length) {
                break;
            }
            if (!input[i].equals("#")) {
                node.right = new TreeNode(Integer.valueOf(input[i]));
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }
}
