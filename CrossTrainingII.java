import java.util.*;
import utils.RandomListNode;
import utils.GraphNode;
import utils.TreeNode;

public class CrossTrainingII {
    public static void main(String[] args) {
        CrossTrainingII c = new CrossTrainingII();
        int[] array = new int[] { 4, 3, 2, 1 };
        int[] result = c.countArray(array);
        for (int num : result) {
            System.out.println(num + " ");
        }
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

    /**
     * Get Count Array
     * <p>
     * Given an array A of length N containing all positive integers from [1...N].
     * How to get an array B such that B[i] represents how many elements A[j] (j >
     * i) in array A that are smaller than A[i].
     */
    public int[] countArrayN2(int[] array) {
        TreeSet<Integer> set = new TreeSet<>();
        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            result[i] = set.headSet(array[i]).size();
            set.add(array[i]);
        }
        return result;
    }

    // public int[] countArray(int[] array) {
    //     int[] result = new int[array.length];
    //     mergeSort(array, result, 0, (array.length - 1) / 2, array.length - 1);
    //     return result;
    // }

    // private void mergeSort(int[] array, int[] result, int left, int mid, int right) {
    //     if (mid - 1 == left) {
    //         if (array[left] > array[mid]) {
    //             result[left] += 1 + result[mid];
    //         }
    //     } else {
    //         mergeSort(array, result, left, (left + mid) / 2, mid);
    //     }
    //     if (right - 2 == mid) {
    //         if (array[mid + 1] > array[right]) {
    //             result[mid + 1] += 1 + result[right];
    //         }
    //     } else if (right - 1 != mid) {
    //         mergeSort(array, result, mid + 1, (mid + right + 1) / 2, right);
    //     }

    //     merge(array, result, left, mid, right);
    // }

    // private void merge(int[] array, int[] result, int left, int mid, int right) {

    //     int mid2 = mid + 1;
    //     while (mid >= left && right >= mid2) {
    //         if (array[mid] <= array[right]) {
    //             right--;
    //         } else {
    //             result[mid] += result[right--] + 1;
    //         }
    //     }
    //     if (right == mid2 - 1) {
    //         while (mid >= left) {
    //             if (array[mid] > array[mid2]) {
    //                 result[mid] += result[mid2] + 1;
    //             }
    //             mid--;
    //         }
    //     }
    // }

    /**
     * 3 Sum
     * <p>
     * Determine if there exists three elements in a given array that sum to the
     * given target number. Return all the triple of values that sums to target.
     */
    public List<List<Integer>> allTriple(int[] array, int target) {
        return null;
    }
}
