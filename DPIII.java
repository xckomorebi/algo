public class DPIII {
    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1 },
                { 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0 },
                { 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1 },
                { 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0 } };

        DPIII d = new DPIII();
        System.out.println(d.largestSquareSurroundedByOne(matrix));
    }

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

    /**
     * Largest Square Surrounded By One
     * <p>
     * Determine the largest square surrounded by 1s in a binary matrix (a binary
     * matrix only contains 0 and 1), return the length of the largest square.
     */
    public int largestSquareSurroundedByOne(int[][] matrix) {
        int H = matrix.length;
        int W = matrix[0].length;

        int[][] left = new int[H][W];
        int[][] right = new int[H][W];
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
                int rightDown = Math.min(right[i][j], down[i][j]);
                for (int k = rightDown; k > 0; k--) {
                    if (i + k - 1 < H && j + k - 1 < W) {
                        if (Math.min(up[i + k - 1][j + k - 1], left[i + k - 1][j + k - 1]) >= k) {
                            max = Math.max(k, max);
                        }
                    }
                }
            }
        }

        return max;
    }

    /**
     * Largest X Of 1s
     * <p>
     * Given a matrix that contains only 1s and 0s, find the largest X shape which
     * contains only 1s, with the same arm lengths and the four arms joining at the
     * central point.
     */
    public int largest2(int[][] matrix) {
        int H = matrix.length;
        int W = matrix[0].length;

        int[][] upLeft = new int[H][W];
        int[][] upRight = new int[H][W];
        int[][] downLeft = new int[H][W];
        int[][] downRight = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        upLeft[i][j] = 1;
                    } else {
                        upLeft[i][j] = 1 + upLeft[i - 1][j - 1];
                    }
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = W - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == W - 1) {
                        upRight[i][j] = 1;
                    } else {
                        upRight[i][j] = 1 + upRight[i - 1][j + 1];
                    }
                }
            }
        }
        for (int i = H - 1; i >= 0; i--) {
            for (int j = 0; j < W; j++) {
                if (matrix[i][j] == 1) {
                    if (i == H - 1 || j == 0) {
                        downLeft[i][j] = 1;
                    } else {
                        downLeft[i][j] = 1 + downLeft[i + 1][j - 1];
                    }
                }
            }
        }
        for (int i = H - 1; i >= 0; i--) {
            for (int j = W - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i == H - 1 || j == W - 1) {
                        downRight[i][j] = 1;
                    } else {
                        downRight[i][j] = 1 + downRight[i + 1][j + 1];
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                max = Math.max(max,
                        Math.min(Math.min(upLeft[i][j], upRight[i][j]), Math.min(downLeft[i][j], downRight[i][j])));
            }
        }
        return max;
    }

    /**
     * Largest SubMatrix Sum
     * <p>
     * Given a matrix that contains integers, find the submatrix with the largest
     * sum. Return the sum of the submatrix.
     */
    public int largest3(int[][] matrix) {
        return 0;
    }
}
