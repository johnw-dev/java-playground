package jmwdev.j8.streams;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class InfiniteStream {
    static Logger logger = LogManager.getLogger(InfiniteStream.class); // REPLACE

    public static void main(String[] args) {
        logger.info("started");
        Stream<Integer> s = Stream.generate(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return RandomUtils.nextInt(0,100);
        });
        s.filter(x -> x> 10 && x <20).forEach(logger::info);
        logger.info("finished");
    }
}
