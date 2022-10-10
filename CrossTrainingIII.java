import java.util.*;

public class CrossTrainingIII {
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
        for (int i = 0; i < array.length; i++) {
            if (stack.isEmpty()) {
                stack.offerLast(i);
            } else if (array[stack.peekLast()] > array[i]) {
                int prev = 0;
                int prevMax = 0;
                while (!stack.isEmpty() && array[stack.peekLast()] > array[i]) {
                    prev = stack.pollLast();
                    prevMax = array[prev];
                }
                prev = stack.isEmpty() ? -1 : prev;

                result = Math.max((i - 1 - prev) * prevMax, result);
            } else {
                stack.offerLast(i);
            }
        }
        result = Math.max((array.length - stack.peekFirst()) * array[stack.peekFirst()], result);

        return result;
    }
}
