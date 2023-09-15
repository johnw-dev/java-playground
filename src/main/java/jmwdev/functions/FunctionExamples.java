package jmwdev.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class FunctionExamples {

    static Logger logger = LogManager.getLogger("Functions");

    public static void main(String[] args) {
        DoubleUnaryOperator log = Math::log;
        DoubleUnaryOperator sqrt = Math::sqrt;
        DoubleUnaryOperator logThenSqrt = sqrt.compose(log);
        DoubleUnaryOperator sqrtThenLog = sqrt.andThen(log);
        DoubleConsumer logVoid = logger::info;
        DoublePredicate isFinite = Double::isFinite;

        BiConsumer<Double, Double> addAndLog = (a, b) -> logger.info(a + b);
        DoubleBinaryOperator subtract = (a, b) -> a - b;
        BiFunction<Double, Double, String> multiplyAndToString = (a, b) -> String.valueOf(a * b);
        DoubleSupplier giveMyFive = () -> 5.0;

        logVoid.accept(logThenSqrt.applyAsDouble(3.14));
        // Output: 1.06
        logVoid.accept(sqrtThenLog.applyAsDouble(3.14));
        // Output 0.57
        addAndLog.accept(1.0, 2.0);
        logVoid.accept(subtract.applyAsDouble(3.0, 1.0));
        String multipliedString = multiplyAndToString.apply(2.0, 3.0);
        logger.info(multipliedString);
        logVoid.accept(600.0);
        logger.info(giveMyFive.getAsDouble());
        logger.info(isFinite.test(giveMyFive.getAsDouble()));

        // Currying
        DoubleFunction<DoubleUnaryOperator> weight = mass -> gravity -> mass * gravity;
        double myWeightOnEarth = weight.apply(9.81).applyAsDouble(60.0);
        logger.info("My weight on Earth: {}", myWeightOnEarth);
        double myWeightOnMars = weight.apply(3.75).applyAsDouble(60.0);
        logger.info("My weight on Mars: {}", myWeightOnMars);

        // Monad: Optional

        Optional<Integer> val = Optional.of(2).flatMap(f -> Optional.of(3).flatMap(s -> Optional.of(f + s)));
        val.ifPresent(logger::info);
    }

    Integer sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    Integer factorial(Integer number) {
        // head recursion
        return (number == 1) ? 1 : number * factorial(number - 1);
    }

    Integer factorial(Integer number, Integer result) {
        // tail recursion
        return (number == 1) ? result : factorial(number - 1, result * number);
    }
}