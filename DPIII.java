public class DPIII {
    /**
     * Longest Consecutive 1s
     * <p>
     * Given an array containing only 0s and 1s, find the length of the longest
     * subarray of consecutive 1s.
     */
    public int longest(int[] array) {
        int max = 0;
        int cur = 0;
        for (int num : array) {
            if (num == 1) {
                cur++;
                max = Math.max(max, cur);
            } else {
                cur = 0;
            }
        }
        return max;
    }

    /**
     * Longest Cross Of 1s
     * <p>
     * Given a matrix that contains only 1s and 0s, find the largest cross which
     * contains only 1s, with the same arm lengths and the four arms joining at the
     * central point.
     * 
     * Return the arm length of the largest cross.
     */
    public int largest(int[][] matrix) {
        int H = matrix.length;
        int W = matrix[0].length;

        int[][] right = new int[H][W];
        int[][] left = new int[H][W];
        int[][] up = new int[H][W];
        int[][] down = new int[H][W];

        int max = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (matrix[i][j] == 1) {
                    if (j == 0) {
                        left[i][j] = 1;
                    } else {
                        left[i][j] = left[i][j - 1] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0) {
                        up[i][j] = 1;
                    } else {
                        up[i][j] = up[i - 1][j] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = W - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (j == W - 1) {
                        right[i][j] = 1;
                    } else {
                        right[i][j] = right[i][j + 1] + 1;
                    }
                }
            }
        }
        for (int i = H - 1; i >= 0; i--) {
            for (int j = 0; j < W; j++) {
                if (matrix[i][j] == 1) {
                    if (i == H - 1) {
                        down[i][j] = 1;
                    } else {
                        down[i][j] = down[i + 1][j] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                max = Math.max(Math.min(Math.min(up[i][j], down[i][j]), Math.min(left[i][j], right[i][j])), max);
            }
        }
        return max;
    }
}
