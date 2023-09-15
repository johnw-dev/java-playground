package jmwdev.basiccalculator;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

enum Operator {
    PLUS("+", Double::sum),
    MINUS("-", (a, b) -> a - b),
    MULTIPLY("*", (a, b) -> a * b),
    DIVIDE("/", (a, b) -> a / b),
    MODULUS("%", (a, b) -> a % b),
    POWER_OF("^", Math::pow);

    private final DoubleBinaryOperator function;
    private final String symbol;

    private Operator(String symbol, DoubleBinaryOperator operator) {
        this.symbol = symbol;
        this.function = operator;
    }

    static String getSymbolsRegex() {
        String symbols = Arrays.stream(Operator.values())
                .map(a -> String.format("\\%s", a.symbol))
                .reduce(String::concat).orElseThrow(UnknownError::new);
        return String.format("\\d+[ ]?([%s]{1})", symbols);
    }

    static Operator fromSymbol(String symbol) {
        return Arrays.stream(Operator.values())
                .filter(a -> a.symbol.equals(symbol))
                .findFirst().orElse(null);
    }

    Double apply(Double a, Double b) {
        return this.function.applyAsDouble(a, b);
    }
}
