public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch b = new BinarySearch();
        int[] array = new int[]{3,4,5,6,6,12,16};
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
     * Given a 2D matrix that contains integers only, which each row is sorted in an ascending order. The first element of next row is larger than (or equal to) the last element of previous row.
     * Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.
     */
    public int[] search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{-1, -1};
        }
        int H = matrix.length;
        int W = matrix[0].length;

        int left = 0;
        int right = H * W - 1;
        int mid;

        while (left <= right){
            mid = left + (right - left) / 2;
            int y = mid % W;
            int x = mid / W;
            if (matrix[x][y] == target) {
                return new int[]{x, y};
            }
            if (matrix[x][y] > target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return new int[]{-1, -1};
    }

    /**
     *Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to T.
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
     * Given a target integer T and an integer array A sorted in ascending order, find the index of the first occurrence of T in A or return -1 if there is no such index.
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
}
