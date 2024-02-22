package jmwdev.sort;

import java.util.Arrays;

public class SelectionSort {

    private static int[]  selectionSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            var swapIndex = 0;
            var swapValue = input[swapIndex];
            for (int j = 0; j < input.length; j++) {
                if(input[j] < swapValue) {
                    swapIndex = j;
                    swapValue = input[j];
                }
            }
            var tmp = input[i];
            input[i] = swapValue;
            input[swapIndex] = tmp;
        }
        return input;
    }
    public static void main(String[] args) {
        var input = new int[] { 1,7,9,2,3,4,5,6,8};
        var output = selectionSort(input);
        Arrays.stream(output)
                .mapToObj(i -> String.format("%d,", i))
                .forEach(System.out::print);

    }


}
