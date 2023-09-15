package jmwdev.basiccalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * Simple calculator that takes string input in the form "5*2/3" and applies the requested operations to return a result: "5*2/3 = 3.333333"
 * Uses simplistic regex to identify numbers and operators.  Supports negative numbers and uses Doubles.
 * <p>
 * Exercises a form of command pattern.  Calculations are all DoubleBinaryOperators and execution of them is wrapped by the enum
 * <p>
 * Bracketing supported
 */
public class BasicCalculator {
    static final Logger log = LogManager.getLogger(BasicCalculator.class);
    static Pattern removeBrackets = Pattern.compile("\\(([^()]+?)\\)");
    static Pattern containsBrackets = Pattern.compile("^.*[(]+.+[)]+.*$");
    static Pattern getNumbers = Pattern.compile("(-\\d+\\.\\d|\\d+\\.\\d|-\\d+|\\d+)");
    static Pattern getOperators = Pattern.compile(String.format("\\d+[ ]{1}([%s]{1})", Operator.getSymbolsRegex()));

    private static Double applyOperations(List<Double> numbers, List<Operator> operations) {
        Double subtotal = numbers.get(0);
        for (int i = 0; i < operations.size(); i++) {
            subtotal = operations.get(i).apply(subtotal, numbers.get(i + 1));
        }
        return subtotal;
    }

    static Double calculate(final String line) {
        String formatted = formatExpression(calculateBracketed(line));
        // interestingly the order of optional regex cases is important most accepting must come last
        List<Double> numbers = getNumbers.matcher(formatted)
                .results()
                .map(a -> Double.parseDouble(a.group(1))).toList();
        List<Operator> operators = BasicCalculator.getOperators.matcher(formatted)
                .results()
                .map(a -> Operator.fromSymbol(a.group(1)))
                .filter(Objects::nonNull).toList();
        return applyOperations(numbers, operators);
    }

    public static String formatExpression(final String line) {
        char[] chars = line.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (Character.isDigit(c) || c == '.'
                    || (c == '-' && (i == 0 || i < chars.length - 1
                    && !(Character.isDigit(chars[i - 1]))
                    && Character.isDigit(chars[i + 1])))) {
                builder.append(c);
            } else {
                builder.append(' ');
                builder.append(c);
                builder.append(' ');
            }
        }
        return builder.toString();
    }

    static String calculateBracketed(final String line) {
        String updated = line;
        while (updated.matches(containsBrackets.pattern())) {
            log.info("resolving brackets: {}", updated);
            Optional<MatchResult> match = removeBrackets.matcher(updated).results().findFirst();
            if (match.isPresent()) {
                MatchResult sub = match.get();
                StringBuilder builder = new StringBuilder(updated);
                builder.replace(sub.start(), sub.end(), calculate(sub.group(1)).toString());
                updated = builder.toString();
            }
        }
        return updated;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "0";
        while (!input.isEmpty()) {
            log.info("Enter your calculation");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                Double total = calculate(input);
                String result = total.toString();
                if (total % 1.0 == 0) {
                    result = String.valueOf(total.intValue());
                }
                String formatted = formatExpression(input);
                log.info("{} = {}", formatted, result);
            }
        }
        log.info("Goodbye");
    }


}
