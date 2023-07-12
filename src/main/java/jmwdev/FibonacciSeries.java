package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciSeries {
    static Logger logger = LogManager.getLogger("FibonacciSeries");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter number in series:");
        int size = Integer.parseInt(scanner.next());

        List<Integer> series = getSeries(size);
        for (int i : series) logger.info("{} ", i);
    }

    protected static List<Integer> getSeries(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Invalid entry - must be greater than 0");
        }
        List<Integer> series = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                series.add(0);
            } else if (i == 1) {
                series.add(1);
            } else {
                series.add(series.get(i - 1) + series.get(i - 2));
            }
        }
        return series;
    }
}
