package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class SortedSearch {
    static Logger log = LogManager.getLogger(SortedSearch.class);

    public static long countNumbersStreaming(int[] sortedArray, int lessThan) {
        return Arrays.stream(sortedArray).filter(num -> num < lessThan).count();
    }

    public static int countNumbersLongHand(int[] sortedArray, int lessThan) {

        int min = 0;
        int max = sortedArray.length;
        // short circuit 1
        if (max == 0 || sortedArray[min] >= lessThan) {
            return 0;
        }
        // short circuit 2
        if (sortedArray[max - 1] < lessThan) {
            return sortedArray.length;
        }

        // binary search
        int lastSeen = min;
        int index = (max / 2);

        while (index != lastSeen) {
            int value = sortedArray[index];
            if (value == lessThan) {
                return index;
            } else {
                if (lessThan < value) {
                    max = index;
                } else {
                    min = index;
                }
                lastSeen = index;
                index = ((max - min) / 2) + min;
            }
        }
        return index;
    }

    public static void main(String[] args) {

        log.info(SortedSearch.countNumbersLongHand(new int[]{1, 3, 5, 7}, 4));
        log.info(SortedSearch.countNumbersLongHand(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 13));
        log.info(SortedSearch.countNumbersStreaming(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 13));
        log.info(SortedSearch.countNumbersLongHand(new int[]{1}, 13));
        log.info(SortedSearch.countNumbersStreaming(new int[]{1}, 13));
        log.info(SortedSearch.countNumbersLongHand(new int[]{1}, 1));
        log.info(SortedSearch.countNumbersLongHand(new int[]{1}, 0));


    }

}