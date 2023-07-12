package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * get maximum value of an array of values
 */
public class GetMax {

    private static final Logger logger = LogManager.getLogger("getMax");

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        logger.info(getMaxIntegers(test).orElse(0));
        Record[] test2 = {new Record(0), new Record(5), new Record(4), new Record(0)};
        Record obj = getMaxRecords(test2).orElseThrow(RuntimeException::new);
        logger.info(obj.cost);
    }

    public static OptionalInt getMaxIntegers(int[] ints) {
        return Arrays.stream(ints).max();
    }

    public static Optional<Record> getMaxRecords(Record[] classes) {
        return Arrays.stream(classes).max(Record::compareTo);
    }

    record Record(int cost) implements Comparable<Record> {

        @Override
        public int compareTo(Record o) {
            return this.cost - o.cost;
        }
    }
}
