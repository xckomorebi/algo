public class DPI {
    /**
     * Fibonacci Number
     * <p>
     * Get the Kth number in the Fibonacci Sequence. (K is 0-indexed, the 0th
     * Fibonacci number is 0 and the 1st Fibonacci number is 1).
     */
    public long fibonacci(int K) {
        long a = 0;
        long b = 1;
        while (K-- > 0) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return a;
    }

    /**
     * Longest Ascending SubArray
     * <p>
     * Given an unsorted array, find the length of the longest subarray in which the
     * numbers are in ascending order.
     */
    public int longest(int[] array) {
        int max = 0;
        int cur = 0;
        int prev = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                max = 1;
                cur = 1;
                prev = array[0];
            } else {
                if (array[i] > prev) {
                    cur++;
                    prev = array[i];
                    if (cur > max) {
                        max = cur;
                    }
                } else {
                    prev = array[i];
                    cur = 1;
                }
            }
        }
        return max;
    }

    /**
     * Max Product Of Cutting Rope
     * <p>
     * Given a rope with positive integer-length n, how to cut the rope into m
     * integer-length parts with length p[0], p[1], ...,p[m-1], in order to get the
     * maximal product of p[0]*p[1]* ... *p[m-1]? m is determined by you and must be
     * greater than 0 (at least one cut must be made). Return the max product you
     * can have.
     */
    public int maxProduct(int length) {
        int[] dp = new int[length];
        dp[0] = 1;
        if (length <= 3) {
            return length - 1;
        }
        for (int i = 1; i < length; i++) {
            dp[i] = i + 1;
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], j * dp[i - j]);
            }
        }
        return dp[length - 1];
    }

    /**
     * Array Hopper I
     * <p>
     * Given an array A of non-negative integers, you are initially positioned at
     * index 0 of the array. A[i] means the maximum jump distance from that position
     * (you can only jump towards the end of the array). Determine if you are able
     * to reach the last index.
     */
    public boolean canJump(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (i > max) {
                return false;
            } else {
                max = Math.max(max, i + array[i]);
            }
        }
        return true;
    }
}
