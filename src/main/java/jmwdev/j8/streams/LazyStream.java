package jmwdev.j8.streams;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class LazyStream {
    static Logger logger = LogManager.getLogger(StreamBasic.class);

    public static void main(String[] args) {
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            elements.add(RandomUtils.nextInt(0,200));
        }
        var newElements = elements.stream()
                .peek((x) ->logger.info("raw {}", x))
                .filter((x) -> x > 50)
                .peek((x) ->logger.info("f1 {}", x))
                .filter((x) -> x < 100)
                .peek((x) ->logger.info("f2 {}", x))
                .filter((x) -> x < 80)
                .peek((x) ->logger.info("f3 {}", x))
                .limit(4)
                .toList();
        newElements.forEach((x) -> logger.info("final {}", x));
    }

}
