import java.util.*;

public class DPII {
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
            {0, 1, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 0, 1},
            {1, 1, 1, 1},
        };
        DPII d = new DPII();
        d.largest(matrix);

    }
    /**
     * Array Hopper II
     * <p>
     * Given an array A of non-negative integers, you are initially positioned at
     * index 0 of the array. A[i] means the maximum jump distance from index i (you
     * can only jump towards the end of the array). Determine the minimum number of
     * jumps you need to reach the end of array. If you can not reach the end of the
     * array, return -1.
     */
    public int minJump(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = 1;

        for (int i = 0; i < array.length; i++) {
            if (dp[i] == 0) {
                return -1;
            } else {
                for (int j = i + 1; j <= i + array[i] && j < array.length; j++) {
                    if (dp[j] == 0) {
                        dp[j] = dp[i] + 1;
                    } else {
                        dp[j] = Math.min(dp[i] + 1, dp[j]);
                    }
                }
            }
        }
        return dp[array.length - 1] - 1;
    }

    /**
     * Largest SubArray Sum
     * <p>
     * Given an unsorted integer array, find the subarray that has the greatest sum.
     * Return the sum.
     */
    public int largestSum(int[] array) {
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            cur += array[i];
            max = Math.max(cur, max);
            if (cur < 0) {
                cur = 0;
            }
        }
        return max;
    }

    /**
     * Dictionary Word I
     * <p>
     * Given a word and a dictionary, determine if it can be composed by
     * concatenating words from the given dictionary.
     */
    public boolean canBreak(String input, String[] dict) {
        Set<String> set = new HashSet<>();
        for (String s : dict) {
            set.add(s);
        }

        boolean[] dp = new boolean[input.length() + 1];
        dp[0] = true;
        char[] array = input.toCharArray();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j]) {
                    if (set.contains(new String(array, j, i + 1 - j))) {
                        dp[i + 1] = true;
                        break;
                    }
                }
            }
        }
        return dp[input.length()];
    }

    /**
     * Edit Distance
     * <p>
     * Given two strings of alphanumeric characters, determine the minimum number of
     * Replace, Delete, and Insert operations needed to transform one string into
     * the other.
     */
    public int editDistance(String one, String two) {
        int l1 = one.length();
        int l2 = two.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= l1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= l2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
            }
        }

        return dp[l1][l2];
    }

    /**
     * Largest Square Of 1s
     * <p>
     * Determine the largest square of 1s in a binary matrix (a binary matrix only
     * contains 0 and 1), return the length of the largest square.
     */
    public int largest(int[][] matrix) {
        int max = 0;
        int H = matrix.length;
        int W = matrix[0].length;

        int[][] dp = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    }

                    max = Math.max(dp[i][j], max);
                }
            }
        }

        for (int i = 0; i < H; i++) {
            System.out.print("|");
            for (int j = 0; j < W; j++) {
                System.out.print(dp[i][j]);
                System.out.print(" ");
            }
            System.out.println("|");
        }

        return max;
    }
}
