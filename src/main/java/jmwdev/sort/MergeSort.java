package jmwdev.sort;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;

        // split left
        int[] l = new int[mid];
        System.arraycopy(a, 0, l, 0, mid);
        mergeSort(l, mid);

        // split right
        int[] r = new int[n - mid];
        System.arraycopy(a, mid, r, 0, n - mid);
        mergeSort(r, n - mid);

        // merge sorted sets together
        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            int[] a, int[] l, int[] r, int lMax, int rMax) {

        // iterators
        int lIt = 0, rIt = 0, i = 0;
        while (lIt < lMax && rIt < rMax) {
            if (l[lIt] <= r[rIt]) {
                a[i++] = l[lIt++];
            }
            else {
                a[i++] = r[rIt++];
            }
        }
        while (lIt < lMax) {
            a[i++] = l[lIt++];
        }
        while (rIt < rMax) {
            a[i++] = r[rIt++];
        }
    }

    public static void main(String[] args) {
        int[] actual = { 5, 1, 6, 2, 3, 4 };
        MergeSort.mergeSort(actual, actual.length);
        Arrays.stream(actual).forEach(System.out::println);
    }
}
