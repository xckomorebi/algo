import java.util.*;
import utils.RandomListNode;
import utils.GraphNode;
import utils.TreeNode;

public class CrossTrainingII {
    public static void main(String[] args) {
        CrossTrainingII c = new CrossTrainingII();
        int[] array = new int[] { 3, 9, 1, 2, 3 };
        System.out.println(c.allPairs(array, 4));
        // List<List<Integer>> list = new ArrayList<>();
        // list.add(Arrays.asList(1, 2));
        // list.get(0).add(3);
        // System.out.println(list.get(0));
    }

    /**
     * Deep Copy Linked List With Random Pointer
     * <p>
     * Each of the nodes in the linked list has another pointer pointing to a random
     * node in the list or null. Make a deep copy of the original list.
     */
    public RandomListNode copy(RandomListNode head) {
        if (head == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        RandomListNode newNode = new RandomListNode(cur.value);
        map.put(cur, newNode);
        map.put(null, null);

        while (cur != null) {
            if (map.containsKey(cur.next)) {
                map.get(cur).next = map.get(cur.next);
            } else {
                RandomListNode newNext = new RandomListNode(cur.next.value);
                map.get(cur).next = newNext;
                map.put(cur.next, newNext);
            }
            if (map.containsKey(cur.random)) {
                map.get(cur).random = map.get(cur.random);
            } else {
                RandomListNode newRandom = new RandomListNode(cur.random.value);
                map.get(cur).random = newRandom;
                map.put(cur.random, newRandom);
            }
            cur = cur.next;
        }
        return newNode;
    }

    /**
     * Deep Copy Undirected Graph
     * <p>
     * Make a deep copy of an undirected graph, there could be cycles in the
     * original graph.
     */
    public List<GraphNode> copy(List<GraphNode> graph) {
        List<GraphNode> copyGraph = new ArrayList<>();
        Map<GraphNode, GraphNode> map = new HashMap<>();
        for (GraphNode node : graph) {
            GraphNode copyNode = new GraphNode(node.key);
            map.put(node, copyNode);
            copyGraph.add(copyNode);
        }

        Deque<GraphNode> queue = new ArrayDeque<>(graph);

        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            for (GraphNode child : node.neighbors) {
                if (map.containsKey(child)) {
                    map.get(node).neighbors.add(map.get(child));
                } else {
                    GraphNode copyChild = new GraphNode(child.key);
                    queue.offer(child);
                    map.put(child, copyChild);
                    map.get(node).neighbors.add(copyChild);
                }
            }
        }
        return copyGraph;
    }

    /**
     * Closest Number In Binary Search Tree
     * <p>
     * In a binary search tree, find the node containing the closest number to the
     * given target number.
     */
    public int closest(TreeNode root, int target) {
        int result = root.key;
        while (root != null) {
            if (Math.abs(result - target) > Math.abs(root.key - target)) {
                result = root.key;
            }
            if (root.key == target) {
                return result;
            }
            if (root.key < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return result;
    }

    /**
     * Largest Number Smaller In Binary Search Tree
     * <p>
     * In a binary search tree, find the node containing the largest number smaller
     * than the given target number. If there is no such number, return -2^31.
     */
    public int largestSmaller(TreeNode root, int target) {
        int result = -(1 << 31);

        while (root != null) {
            if (root.key >= target) {
                root = root.left;
            } else {
                result = root.key;
                root = root.right;
            }
        }
        return result;
    }

    /**
     * 2 Sum
     * <p>
     * Determine if there exist two elements in a given array, the sum of which is
     * the given target number.
     */
    public boolean existSum(int[] array, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (set.contains(target - num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    /**
     * 2 Sum All Pair I Find all pairs of elements in a given array that sum to the
     * given target number. Return all the pairs of indices.
     */
    public List<List<Integer>> allPairs(int[] array, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            if (map.containsKey(target - num)) {
                for (int index : map.get(target - num)) {
                    result.add(Arrays.asList(index, i));
                }
            }
            if (map.containsKey(num)) {
                map.get(num).add(i);
            } else {
                map.put(num, new ArrayList<>(Arrays.asList(i)));
            }
        }
        return result;
    }

    /**
     * 2 Sum All Pair II
     * <p>
     * Find all pairs of elements in a given array that sum to the pair the given
     * target number. Return all the distinct pairs of values.
     */
    public List<List<Integer>> allPairs2(int[] array, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int seenMid = 0;

        for (int num : array) {
            if (num * 2 == target) {
                if (seenMid++ == 1) {
                    result.add(Arrays.asList(num, num));
                }
            } else {
                if (!set.contains(num) && set.contains(target - num)) {
                    result.add(Arrays.asList(target - num, num));
                }
                set.add(num);
            }
        }

        return result;
    }
}
