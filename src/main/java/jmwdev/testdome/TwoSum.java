package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    static Logger log = LogManager.getLogger(TwoSum.class);

    public static int[] findTwoSum(int[] list, int sum) {
        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            if (values.containsKey(list[i])) {
                return new int[]{i, values.get(list[i])};
            }
            values.put(sum - list[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[]{3, 1, 5, 7, 5, 9}, 10);
        log.info("{} {}", indices[0], indices[1]);
    }

}
