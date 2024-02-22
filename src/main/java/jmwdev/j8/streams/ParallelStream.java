package jmwdev.j8.streams;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class ParallelStream {
    static Logger logger = LogManager.getLogger(ParallelStream.class);

    public static void main(String[] args) {
        int sum1 = Stream.of(10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0)
                .mapToInt(Double::intValue)
                .peek(n -> logger.info("sequencial {}", n))
                .sum();

        int sum2 = Stream.of(10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0)
                .parallel()
                .mapToInt(Double::intValue)
                .peek(n -> logger.info("parallel {}", n))
                .sum();
        logger.info("{} {}", sum1, sum2);
    }
}
