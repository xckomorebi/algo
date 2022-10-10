import java.util.*;
import utils.*;

public class CrossTrainingIII {

    // private class Point {
    // public int x;
    // public int y;

    // public Point(int x, int y) {
    // this.x = x;
    // this.y = y;
    // }
    // }

    public static void main(String[] args) {
        // int[][] array = new int[][] { { 2, 3, 4, 4, 6, 6, 6, 8, 9, 10, 10, 10, 11 }, { 1, 2 } };
        // List<List<Integer>> input = new ArrayList<>();
        // for (int[] cur : array) {
        //     List<Integer> curList = new ArrayList<>();
        //     for (int num : cur) {
        //         curList.add(num);
        //     }
        //     input.add(curList);
        // }
        int[][] matrix = new int[][] {
                {5,8,7,7},
                {5,2,1,5},
                {7,1,7,1},
                {8,9,6,9},
                {9,8,9,9}};

        CrossTrainingIII c = new CrossTrainingIII();
        System.out.println(c.commonElementsInKSortedArrays(input));
    }

    /**
     * Common Numbers Of Two Arrays I(Array version)
     * <p>
     * Find all numbers that appear in both of the two unsorted arrays, return the
     * common numbers in increasing order.
     */
    public List<Integer> common(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                result.add(a[i]);
                i++;
                j++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                i++;
            }
        }
        return result;
    }

    /**
     * Largest Rectangle In Histogram
     * <p>
     * Given a non-negative integer array representing the heights of a list of
     * adjacent bars. Suppose each bar has a width of 1. Find the largest
     * rectangular area that can be formed in the histogram.
     */
    public int largest(int[] array) {
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; i++) {
            int cur = (i == array.length ? 0 : array[i]);
            while (!stack.isEmpty() && array[stack.peekLast()] > cur) {
                int height = array[stack.pollLast()];
                int left = (stack.isEmpty() ? 0 : stack.peekLast() + 1);
                result = Math.max(result, height * (i - left));
            }
            stack.offerLast(i);
        }
        return result;
    }

    /**
     * Max Water Trapped I
     * <p>
     * Given a non-negative integer array representing the heights of a list of
     * adjacent bars. Suppose each bar has a width of 1. Find the largest amount of
     * water that can be trapped in the histogram.
     */
    public int maxTrapped(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        int leftHeight = array[left];
        int rightHeight = array[right];
        int result = 0;

        while (left < right) {
            if (leftHeight <= rightHeight) {
                leftHeight = Math.max(array[++left], leftHeight);
                result += leftHeight - array[left];
            } else {
                rightHeight = Math.max(array[--right], rightHeight);
                result += rightHeight - array[right];
            }
        }
        return result;
    }

    /**
     * Most Points On A Line
     * <p>
     * Given an array of 2D coordinates of points (all the coordinates are
     * integers), find the largest number of points that can be crossed by a single
     * line in 2D space.
     */
    // public int most(Point[] points) {
    // return 0;
    // }

    /**
     * Merge K Sorted Array
     * <p>
     * Merge K sorted array into one big sorted array in ascending order.
     */
    public int[] merge(int[][] arrayOfArrays) {
        int length = 0;
        PriorityQueue<Entry> pq = new PriorityQueue<>(arrayOfArrays.length);

        for (int[] array : arrayOfArrays) {
            length += array.length;
            if (array.length != 0) {
                pq.add(new Entry(0, array));
            }
        }

        int[] result = new int[length];
        int i = 0;
        while (!pq.isEmpty()) {
            Entry cur = pq.poll();
            result[i++] = cur.getValue();
            if (cur.hasNext()) {
                pq.offer(new Entry(cur.index + 1, cur.array));
            }
        }

        return result;
    }

    private class Entry implements Comparable<Entry> {
        public int index;
        public int[] array;

        public Entry(int index, int[] array) {
            this.index = index;
            this.array = array;
        }

        public int getValue() {
            return this.array[this.index];
        }

        public boolean hasNext() {
            return index < array.length - 1;
        }

        @Override
        public int compareTo(Entry other) {
            return this.getValue() - other.getValue();
        }
    }

    /**
     * Merge K Sorted Lists
     * <p>
     * Merge K sorted lists into one big sorted list in ascending order.
     */
    public ListNode merge(List<ListNode> listOfLists) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.value - b.value;
            }
        });
        for (ListNode node : listOfLists) {
            pq.offer(node);
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            if (node.next != null) {
                pq.offer(node.next);
            }
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * Common Elements In Three Sorted Array
     * <p>
     * Find all common elements in 3 sorted arrays.
     */
    public List<Integer> common(int[] a, int[] b, int[] c) {
        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length && k < c.length) {
            if (a[i] == b[j] && b[j] == c[k]) {
                result.add(a[i]);
                i++;
                j++;
                k++;
            } else if (a[i] <= b[j] && a[i] <= c[k]) {
                i++;
            } else if (b[j] <= a[i] && b[j] <= c[k]) {
                j++;
            } else {
                k++;
            }
        }
        return result;
    }

    /**
     * Common Elements In K Sorted Lists
     * <p>
     * Find all common elements in K sorted lists.
     */
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Entry2> pq = new PriorityQueue<>();
        PriorityQueue<Entry2> pqNext = new PriorityQueue<>();
        PriorityQueue<Entry2> temp = null;

        int max = input.get(0).get(0);
        for (List<Integer> list : input) {
            Entry2 cur = new Entry2(0, list);
            max = Math.max(max, cur.getValue());
            pq.offer(cur);
        }

        while (!pq.isEmpty()) {
            Entry2 cur = pq.peek();
            if (cur.getValue() == max) {
                result.add(max);
                while (!pq.isEmpty()) {
                    Entry2 entry = pq.poll();
                    if (!entry.hasNext()) {
                        return result;
                    }
                    entry = entry.getNext();
                    max = Math.max(entry.getValue(), max);
                    pqNext.offer(entry);
                }
                temp = pq;
                pq = pqNext;
                pqNext = temp;
            } else {
                if (!cur.hasNext()) {
                    return result;
                }
                pq.poll();
                Entry2 next = cur.getNext();
                max = Math.max(next.getValue(), max);
                pq.offer(next);
            }
        }
        return result;
    }

    private class Entry2 implements Comparable<Entry2> {
        public int index;
        public List<Integer> list;

        public Entry2(int index, List<Integer> list) {
            this.index = index;
            this.list = list;
        }

        public int getValue() {
            return list.get(index);
        }

        @Override
        public int compareTo(Entry2 other) {
            return this.getValue() - other.getValue();
        }

        public boolean hasNext() {
            return this.index < this.list.size() - 1;
        }

        public Entry2 getNext() {
            return new Entry2(this.index + 1, this.list);
        }
    }

    /**
     * Max Water Trapped II
     * <p>
     * Given a non-negative integer 2D array representing the heights of bars in a
     * matrix. Suppose each bar has length and width of 1. Find the largest amount
     * of water that can be trapped in the matrix. The water can flow into a
     * neighboring bar if the neighboring bar's height is smaller than the water's
     * height. Each bar has 4 neighboring bars to the left, right, up and down side.
     */
    public int maxTrapped(int[][] matrix) {
        int H = matrix.length;
        int W = matrix[0].length;
        boolean[][] visited = new boolean[H][W];
        int result = 0;

        PriorityQueue<Cell> pq = new PriorityQueue<>(new Comparator<Cell>(){
            @Override
            public int compare(Cell a, Cell b) {
                return a.val - b.val;
            }
        });

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (i == 0 || i == H - 1 || j == 0 || j == W - 1) {
                    pq.offer(new Cell(i, j, matrix[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int height = pq.peek().val;
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            height = Math.max(height, cur.val);
            for (int[] dir : DIRS) {
                int a = cur.x + dir[0];
                int b = cur.y + dir[1];
                if (a >= 0 && a < H && b >= 0 && b < W && !visited[a][b]) {
                    result += Math.max(0, height - matrix[a][b]);
                    pq.offer(new Cell(a, b, matrix[a][b]));
                    visited[a][b] = true;
                }
            }
        }
        return result;
    }

    private class Cell {
        public int x;
        public int y;
        public int val;

        public Cell(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
