package jmwdev.sort;

import java.util.Arrays;

// swap elements from right to left
public class InsertionSort {

    private static int[] insertionSort(int[] input) {
        return insertionSort(input, 0);
    }
    private static int[] insertionSort(int[] input, int start) {
        for(int i=start; i<input.length; i++) {
            int sIt = i;
            while(sIt > 0 && input[sIt-1] > input[sIt]) {
                var tmp = input[sIt-1];
                input[sIt-1] = input[sIt];
                input[sIt] = tmp;
                sIt--;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        var input = new int[] { 1,7,9,2,3,4,5,6,8};
        var output = insertionSort(input);
        Arrays.stream(output)
                .mapToObj(i -> String.format("%d,", i))
                .forEach(System.out::print);

    }
}
