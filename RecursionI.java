public class RecursionI {
    public static void main(String[] args) {
        RecursionI r = new RecursionI();
        int[] array = new int[] { 3, 5, 1, 2, 4, 8 };
        r.mergeSort(array);
    }

    /**
     * a to the power of b.
     * <p>
     * Evaluate a to the power of b, assuming both a and b are integers and b is
     * non-negative.
     */
    public long power(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        long temp = power(a, b / 2);
        return temp * temp * power(a, b % 2);
    }

    /**
     * Selection Sort
     * <p>
     * an array of integers, sort the elements in the array in ascending order. The
     * selection sort algorithm should be used to solve this problem.
     */
    public int[] solve(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
        return array;
    }

    /**
     * merge sort
     * <p>
     * Given an array of integers, sort the elements in the array in ascending
     * order. The merge sort algorithm should be used to solve this problem.
     */
    public int[] mergeSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    private void mergeSort(int[] array, int left, int right) {
        if (right == left) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, right, mid);
    }

    private void merge(int[] array, int left, int right, int mid) {
        int[] aux = new int[mid - left + 1];
        for (int i = 0; i < mid - left + 1; i++) {
            aux[i] = array[left + i];
        }

        int i = 0;
        int j = mid + 1;
        int k = left;
        while (k <= right) {
            if (j == right + 1) {
                array[k++] = aux[i++];
            } else if (i == mid - left + 1) {
                array[k++] = array[j++];
            } else {
                if (array[j] < aux[i]) {
                    array[k++] = array[j++];
                } else {
                    array[k++] = aux[i++];
                }
            }
        }
    }

    /**
     * quick sort. Given an array of integers, sort the elements in the array in
     * ascending order. The quick sort algorithm should be used to solve this
     * problem.
     */
    public int[] quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        quickSort(array, 0, array.length - 1);

        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int pivot = partition(array, left, right);
        quickSort(array, left, pivot - 1);
        quickSort(array, pivot + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivot = left + (int) (Math.random() * (right - left + 1));
        swap(array, pivot, right--);
        pivot = right;
        int k = left;
        while (k <= right) {
            if (array[k] < array[pivot]) {
                swap(array, left++, k++);
            } else {
                swap(array, k, right--);
            }
        }
        swap(array, k, pivot);
        return k;
    }

    /**
     * Move 0s To The End I
     * <p>
     * Given an array of integers, move all the 0s to the right end of the array.
     * The relative order of the elements in the original array does not need to be
     * maintained.
     */
    public int[] moveZero(int[] array) {
        return new int[0];
    }

    /**
     * Rainbow Sort
     * <p>
     * Given an array of balls, where the color of the balls can only be Red, Green
     * or Blue, sort the balls such that all the Red balls are grouped on the left
     * side, all the Green balls are grouped in the middle and all the Blue balls
     * are grouped on the right side. (Red is denoted by -1, Green is denoted by 0,
     * and Blue is denoted by 1).
     */
    public int[] rainbowSort(int[] array) {
        int i = 0;
        int j = array.length - 1;
        int k = 0;

        while (k <= j) {
            if (array[k] == -1) {
                swap(array, i++, k++);
            } else if (array[k] == 1) {
                swap(array, k, j--);
            } else {
                k++;
            }
        }
        return array;
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
