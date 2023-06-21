package jmwdev;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;


/**
 * Generates text math tables based on a base number provided by user.  This does not do
 */
public class ReferenceTable {

    public enum Operation {
        ADD("+", (a, b) -> String.valueOf(a+b)),
        SUB("-", (a, b) -> String.valueOf(a-b)),
        MULTIPLY("x", (a, b) -> String.valueOf(a*b)),
        DIVIDE("/", (a, b) -> (b==0) ? "0" : (Double.valueOf(a)/Double.valueOf(b) % 1 == 0) ? String.valueOf(a/b) : reduceFraction(a,b));
        private String symbol;

        public static String reduceFraction(Integer a, Integer b) {
            Integer max = a>b? a : b;
            for(int i=max;i>1;i--) {
                if(a % i == 0 && b % i == 0) {
                    Integer a2 = a/i;
                    Integer b2 = b/i;
                    return String.format("%d/%d",a2,b2);
                }
            }
            return String.format("%d/%d",a,b);
        }
        private BiFunction<Integer, Integer, String> fn;
        private Operation(String symbol, BiFunction<Integer, Integer, String> fn) {
            this.symbol = symbol;
            this.fn = fn;
        }

        @Override
        public String toString() {
            return this.symbol;
        }
    }

    static class Calculation {
        private final ReferenceTable.Operation op;
        private final Integer a;
        private final Integer b;
        public Calculation(ReferenceTable.Operation op1, Integer a1, Integer b1) {
            op = op1;
            a = a1;
            b = b1;
        }

        public String getResult() {
            return this.op.fn.apply(a,b);
        }

        @Override
        public String toString() {
            return String.format("| %d %s %d = %s |", a, op.toString(), b, getResult());
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter base number between 0 and 10");
        Integer base = Integer.parseInt(scanner.next());
        if(base < 0 || base > 10 ) {
            throw new IllegalArgumentException("number must be between 0 and 10");
        }
        for( Operation type: Operation.values()) {
            List<Calculation> table = getTable(base, type);
            for(Calculation line : table) {
                System.out.print(line.toString());
            }
            System.out.println("");

        }
    }

    public static List<Calculation> getTable(Integer base, Operation operation) {
        List<Calculation> table = new ArrayList<>();
        for(int i=0;i<=10;i++) {
            table.add(new Calculation(operation, base, i));
        }
        return table;
    }
}
