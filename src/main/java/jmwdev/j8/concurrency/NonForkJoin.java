package jmwdev.j8.concurrency;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class NonForkJoin {
    static Logger logger = LogManager.getLogger(NonForkJoin.class);

    public static void main(String[] args) {
        var names = Arrays.asList("Bob", "James", "Jill", "jack");
        var results = new ArrayList<String>();
        var stopWatch = new StopWatch();
        stopWatch.start();
        names.forEach(name -> {
            String newValue = addNameLengthTransform(name);
            results.add(newValue);
        });
        stopWatch.stop();
        logger.info("{} - {}", stopWatch.getTime(), results);    }

    private static String addNameLengthTransform(String name) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return name.length()+" - "+name;
    }
}
