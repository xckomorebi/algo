package utils;

public class Common {
    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = array[temp];
    }
}
