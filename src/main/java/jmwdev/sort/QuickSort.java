package jmwdev.sort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class QuickSort {
    static Logger logger = LogManager.getLogger(QuickSort.class);

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        log(arr, "swap: "+a+","+b);
    }

    private static int partition(int[] arr, int low, int high) {

        // choose which index to pivot from
        var pivot = arr[high];

        logger.info("sort: "+low+" - "+high+" = "+(high-low+1)+" elements; pivoting on value: "+pivot);

        int highest_below_pivot = (low - 1);
        for (int j = low; j < high; j++) {

            if(arr[j] < pivot) {
                highest_below_pivot++;
                swap(arr, highest_below_pivot, j);
            }
        }
        swap(arr, highest_below_pivot + 1, high);
        return highest_below_pivot + 1;
    }

    private static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            // initially move everything before
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    static void log(int[] arr, String s) {
        String arrString = Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .reduce((a, b) -> a+","+b)
                .orElse("");
        logger.info("{} -> {}", s, arrString);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 1, 6, 2, 3, 4 };
        log(arr, "before");
        quickSort(arr, 0, arr.length-1);
        log(arr, "sorted");

    }
}
