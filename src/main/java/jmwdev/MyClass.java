package jmwdev;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class MyClass {
    public static void main(String args[]) {

        // Given an array and a target value, write a method that returns a list of couples that add up to the target value.
        int[] array = {1, 5, 0, 15, -2, 17, 10, 9};
        int target = 15;

        List<int[]> couples = findCouples(array, target);

        // In this example, your program should print [1, 6], [2, 3], [4, 5]
        for( int[] couple : couples) {
            System.out.printf("[%d, %d]", couple[0],couple[1]);
        }
        System.out.println();
    }

    private static List<int[]> findCouples(int[] array, int target) {
        List<int[]> couples = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i< array.length; i++) {
            map.put(array[i], i);
        }
        for(int i=0; i< array.length; i++) {
            int compliment = target-array[i];
            if(map.containsKey(compliment) && map.get(compliment) > i) {
                couples.add(new int[] {i, map.get(compliment)});
            }
        }
//        for( int i=0; i< array.length; i++) {
//            for (int j=i+1; j<array.length; j++) {
//                int sum = array[i]+array[j];
//                if(sum == target) {
//                    couples.add(new int[]{i, j});
//                }
//            }
//        }
        return couples;
    }
}