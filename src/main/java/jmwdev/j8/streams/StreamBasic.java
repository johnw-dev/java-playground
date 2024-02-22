package jmwdev.j8.streams;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class StreamBasic {
    static Logger logger = LogManager.getLogger(StreamBasic.class);

    public static void main(String[] args) {
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            elements.add(RandomUtils.nextInt(0,200));
        }

        var newElements = elements.stream()
                .peek((x) ->logger.info("{} {}", x, x.getClass()))
                .filter((x) -> x < 100)
                .map(String::valueOf)
                .peek((x) -> logger.info("{} {}", x, x.getClass()))
                .toList();
        newElements.forEach((x) -> logger.info("final {}", x));
    }
}
