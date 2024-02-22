package jmwdev.j8.functions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

interface MyFunctionalInterface<T> {

    static int getStaticInt() {
        // not inherited by implementors
        return 1;
    }

    default int getDefaultInt() {
        // inherited by implementors
        return 2;
    }

    abstract boolean isNegative(T t);

}



public class FunctionalInterfaceTest {

    static Logger logger = LogManager.getLogger(FunctionalInterfaceTest.class);
    // Just for demonstration
    static MyFunctionalInterface<Integer> lambda = i -> {
        boolean result = i < 0;
        logger.info("Result: {} {}", i,result);
        return result;
    };


    private static <T> boolean check(T t, Predicate<T> lambda) {
        boolean result = lambda.test(t);
        logger.info("{} => {}", t, result);
        return result;
    }

    private static <T, U> boolean checkBi(T t, U u, BiPredicate<T, U> lambda) {
        boolean result = lambda.test(t, u);
        logger.info("{},{} => {}", t, u, result);
        return result;
    }

    public static void main(String[] args) {

        lambda.isNegative(1);
        lambda.isNegative(-1);

        // MyFunctionalInterface can instead be boiled down to a predicate
        check(1.0, i -> i%2 ==0);
        check(2.0, i -> i%2 ==0);
        String testString = "hello world";
        check(testString, s -> s.startsWith("h"));
        check(testString, s -> s.contains(" "));
        checkBi(testString, "h", String::startsWith);
    }

}
