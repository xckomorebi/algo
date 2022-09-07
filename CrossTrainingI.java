import java.util.Arrays;

public class CrossTrainingI {
    /**
     * Array Deduplication I
     * <p>
     * Given a sorted integer array, remove duplicate elements. For each group of
     * elements with the same value keep only one of them. Do this in-place, using
     * the left side of the original array and maintain the relative order of the
     * elements of the array. Return the array after deduplication.
     */
    public int[] dedup(int[] array) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (i == 0 || array[i - 1] != array[j]) {
                array[i++] = array[j++];
            } else {
                while (j < array.length && array[i - 1] == array[j]) {
                    j++;
                }
            }
        }
        return Arrays.copyOf(array, i);
    }

    /**
     * Array Deduplication II
     * <p>
     * Given a sorted integer array, remove duplicate elements. For each group of
     * elements with the same value keep at most two of them. Do this in-place,
     * using the left side of the original array and maintain the relative order of
     * the elements of the array. Return the array after deduplication.
     */
    public int[] dedup2(int[] array) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (i == 0 || i == 1 || array[i - 2] != array[j]) {
                array[i++] = array[j++];
            } else {
                while (j < array.length && array[i - 2] == array[j]) {
                    j++;
                }
            }
        }
        return Arrays.copyOf(array, i);
    }

    /**
     * Array Deduplication III
     * <p>
     * Given a sorted integer array, remove duplicate elements. For each group of
     * elements with the same value do not keep any of them. Do this in-place, using
     * the left side of the original array and and maintain the relative order of
     * the elements of the array. Return the array after deduplication.
     */
    public int[] dedup3(int[] array) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            if (j == array.length - 1 || array[j] != array[j + 1]) {
                array[i++] = array[j++];
            } else {
                int cur = array[j];
                while (j < array.length && array[j] == cur) {
                    j++;
                }
            }
        }
        return Arrays.copyOf(array, i);
    }
}
