package jmwdev.j8.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest {

    static Logger logger = LogManager.getLogger(FunctionTest.class);
    public static void main(String[] args) {
        Function<String, Integer> len = String::length;
        BiFunction<String, String, Integer> len2 = (s1, s2) -> (s1+s2).length();
        BiFunction<String, Integer, String> format = (s, i) -> s+i;

        logger.info(len.apply("I am 6"));
        logger.info(len2.apply("I am", "  7"));
        logger.info(format.apply("hello:", 5));
    }
}
