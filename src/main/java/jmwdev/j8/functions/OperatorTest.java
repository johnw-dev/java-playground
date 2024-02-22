package jmwdev.j8.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class OperatorTest {
    static Logger logger = LogManager.getLogger(OperatorTest.class);

    public static void main(String[] args) {
        UnaryOperator<String> intro = (s) -> "My name is "+s;
        BinaryOperator<String> introCustom = (s1, s2) -> s1+s2;

        logger.info(intro.apply("Bill"));
        logger.info(introCustom.apply("His name is ","Bill"));


    }
}
