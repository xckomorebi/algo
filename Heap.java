import java.util.*;

import utils.GraphNode;
import utils.TreeNode;

public class Heap {
    public static void main(String[] args) {
    }

    /**
     * K Smallest In Unsorted Array
     * <p>
     * Find the K smallest numbers in an unsorted integer array A. The returned
     * numbers should be in ascending order.
     */
    public int[] kSmallest(int[] array, int k) {
        int[] result = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : array) {
            pq.offer(i);
        }

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }

    /**
     * Get Keys In Binary Tree Layer By Layer
     * <p>
     * Get the list of list of keys in a given binary tree layer by layer. Each
     * layer is represented by a list of keys and the keys are traversed from left
     * to right.
     */
    public List<List<Integer>> layerByLayer(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int curSize = queue.size();
            List<Integer> curResult = new ArrayList<>();
            while (curSize-- > 0) {
                TreeNode cur = queue.poll();
                curResult.add(cur.key);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            result.add(curResult);
        }

        return result;
    }

    /**
     * Bipartite
     * <p>
     * Determine if an undirected graph is bipartite. A bipartite graph is one in
     * which the nodes can be divided into two groups such that no nodes have direct
     * edges to other nodes in the same group.
     */
    public boolean isBipartite(List<GraphNode> graph) {
        Deque<GraphNode> stack = new ArrayDeque<>();
        Set<GraphNode> set1 = new HashSet<>();
        Set<GraphNode> set2 = new HashSet<>();

        for (GraphNode node : graph) {
            stack.offerLast(node);
        }

        while (!stack.isEmpty()) {
            GraphNode cur = stack.pollLast();
            if (set1.contains(cur)) {
                for (GraphNode node : cur.neighbors) {
                    if (set1.contains(node)) {
                        return false;
                    } else {
                        set2.add(node);
                        stack.offerLast(node);
                    }
                }
            } else if (set2.contains(cur)) {
                for (GraphNode node : cur.neighbors) {
                    if (set2.contains(node)) {
                        return false;
                    } else {
                        set1.add(node);
                        stack.offerLast(node);
                    }
                }
            } else {
                set1.add(cur);
                for (GraphNode node : cur.neighbors) {
                    set2.add(node);
                    stack.offerLast(node);
                }
            }
        }
        return true;
    }

    public boolean isBipartite2(List<GraphNode> graph) {
        Deque<GraphNode> stack = new ArrayDeque<>();
        Map<GraphNode, Integer> map = new HashMap<>();
        for (GraphNode node : graph) {
            stack.offerLast(node);
        }

        while (!stack.isEmpty()) {
            GraphNode cur = stack.pollLast();
            if (map.containsKey(cur)) {
                for (GraphNode node : cur.neighbors) {
                    if (map.containsKey(node)) {
                        if (map.get(node) == map.get(cur)) {
                            return false;
                        }
                    } else {
                        map.put(node, 1 - map.get(cur));
                        stack.offerLast(node);
                    }
                }
            } else {
                map.put(cur, 0);
                for (GraphNode node : cur.neighbors) {
                    map.put(node, 1);
                    stack.offerLast(node);
                }
            }
        }
        return true;
    }

    /**
     * Check If Binary Tree Is Completed
     * <p>
     * Check if a given binary tree is completed. A complete binary tree is one in
     * which every level of the binary tree is completely filled except possibly the
     * last level. Furthermore, all nodes are as far left as possible.
     */
    public boolean isCompleted(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean seen = false;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int curSize = queue.size();
            List<Integer> curResult = new ArrayList<>();
            while (curSize-- > 0) {
                TreeNode cur = queue.poll();
                curResult.add(cur.key);
                if (cur.left != null) {
                    if (seen) {
                        return false;
                    }
                    queue.offer(cur.left);
                } else {
                    seen = true;

                }
                if (cur.right != null) {
                    if (seen) {
                        return false;
                    }
                    queue.offer(cur.right);
                } else {
                    seen = true;
                }
            }
        }

        return true;
    }

    /**
      * Kth Smallest Number In Sorted Matrix
      * <p>
      * Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.``
    */
    public int kthSmallest(int[][] matrix, int k) {
        int H = matrix.length;
        int W = matrix[0].length;

        // PriorityQueue<Cell> pq = new PriorityQueue<>((Cell a, Cell b) -> a.value - b.value);
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.offer(new Cell(matrix[0][0], 0, 0));

        int[][] visited = new int[H][W];

        while (--k > 0) {
            Cell cur = pq.poll();
            int row = cur.row;
            int col = cur.col;

            if (row < H - 1) {
                if (visited[row + 1][col] == 0) {
                    pq.offer(new Cell(matrix[row + 1][col], row + 1, col));
                    visited[row + 1][col] = 1;
                }
            }
            if (col < W - 1) {
                if (visited[row][col + 1] == 0) {
                    pq.offer(new Cell(matrix[row][col + 1], row, col + 1));
                    visited[row][col + 1] = 1;
                }
            }
            
        }
        return pq.peek().value; 
    }

    private class Cell implements Comparable<Cell> {
        public int value;
        public int row;
        public int col;

        public Cell(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Cell b) {
            return this.value - b.value;
        }
    }
}
