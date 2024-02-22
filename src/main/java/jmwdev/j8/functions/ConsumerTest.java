package jmwdev.j8.functions;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerTest {
    static Logger logger = LogManager.getLogger(ConsumerTest.class);

    public static void main(String[] args) {
        Consumer<String> printer = logger::info;
        Supplier<String> random = () -> String.valueOf(RandomUtils.nextInt());
        Supplier<List<String>> tenStrings = () -> {
            List<String> elements = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                elements.add(random.get());
            }
            return elements;
        };
        var elements = tenStrings.get();
        elements.forEach(printer);

        var mapCities = new HashMap<String, String>();
        BiConsumer<String, String> biCon = mapCities::put;
        biCon.accept("Dublin", "Ireland");
        biCon.accept("Edinburgh", "Scotland");
        biCon.accept("London", "England");

        BiConsumer<String, String> mapPrinter = (k,v) -> logger.info("{}:{}",k,v);
        mapCities.forEach(mapPrinter);
    }
}
