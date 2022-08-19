public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch b = new BinarySearch();
        int[] array = new int[] { 3, 4, 5, 6, 6, 12, 16 };
        System.out.println(b.closest(array, 10));
    }

    /**
     * classical binarySearch
     */
    public int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Search in 2D
     * <p>
     * Given a 2D matrix that contains integers only, which each row is sorted in an
     * ascending order. The first element of next row is larger than (or equal to)
     * the last element of previous row.
     * Given a target number, returning the position that the target locates within
     * the matrix. If the target number does not exist in the matrix, return {-1,
     * -1}.
     */
    public int[] search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[] { -1, -1 };
        }
        int H = matrix.length;
        int W = matrix[0].length;

        int left = 0;
        int right = H * W - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            int y = mid % W;
            int x = mid / W;
            if (matrix[x][y] == target) {
                return new int[] { x, y };
            }
            if (matrix[x][y] > target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return new int[] { -1, -1 };
    }

    /**
     * Closest
     * <p>
     * Given a target integer T and an integer array A sorted in ascending order,
     * find the index i in A such that A[i] is closest to T.
     */
    public int closest(int[] array, int target) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        int mid;

        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return Math.abs(array[left] - target) > Math.abs(array[right] - target) ? right : left;
    }

    /**
     * First Occur
     * <p>
     * Given a target integer T and an integer array A sorted in ascending order,
     * find the index of the first occurrence of T in A or return -1 if there is no
     * such index.
     */
    public int firstOccur(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        int mid;

        while (left < right) {
            mid = left + (right - left) / 2;
            if (array[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return array[left] == target ? left : -1;
    }

    /**
     * K Cloest
     * <p>
     * Given a target integer T, a non-negative integer K and an integer array A
     * sorted in ascending order, find the K closest numbers to T in A. If there is
     * a tie, the smaller elements are always preferred.
     */
    public int[] kClosest(int[] array, int target, int k) {
        if (array == null || array.length == 0) {
            return array;
        }
        if (k == 0) {
            return new int[0];
        }

        int closest = closest(array, target);
        int left = closest;
        int right = closest;
        int count = 0;
        int[] result = new int[k];
        while (count < k) {
            if (right == array.length) {
                result[count++] = array[left--];
            } else if (left == -1) {
                result[count++] = array[right++];
            } else if (Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
                result[count++] = array[left--];
            } else {
                result[count++] = array[right++];
            }
        }
        return result;
    }

    /**
     * Smallest Element Larger than Target
     * <p>
     * Given a target integer T and an integer array A sorted in ascending order,
     * find the index of the smallest element in A that is larger than T or return
     * -1 if there is no such index.
     */
    public int smallestElementLargerThanTarget(int[] array, int target) {
        if (array == null || array.length == 0 || array[array.length - 1] <= target) {
            return -1;
        }

        if (array[0] > target) {
            return 0;
        }

        int left = 0;
        int right = array.length - 1;
        int mid;

        while (left < right - 1) {
            mid = left + (right - left) / 2;
            if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    /**
     * Search In Unknown Sized Sorted Array
     * <p>
     * Given a integer dictionary A of unknown size, where the numbers in the
     * dictionary are sorted in ascending order, determine if a given target integer
     * T is in the dictionary. Return the index of T in A, return -1 if T is not in
     * A.
     */
    public int search(Dictionary dict, int target) {
        int size = 1;
        while (dict.get(size) != null && dict.get(size) < target) {
            size <<= 1;
        }
        int left = size / 2;
        int right = size;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (dict.get(mid) == null || dict.get(mid) > target) {
                right = mid - 1;
            } else if (dict.get(mid) == target) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private class Dictionary {
        private int[] array;

        public Integer get(int index) {
            if (index >= array.length) {
                return null;
            } else {
                return array[index];
            }
        }
    }
}
