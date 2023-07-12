package jmwdev.hacker.lambdaexpression;

import java.util.*;
import java.util.regex.*;
//
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

/**
 * * this Java 8 challenge tests your knowledge of Lambda expressions!
 * * Write the following methods that return a lambda expression performing a specified action: <br/>
 * PerformOperation isOdd(): The lambda expression must return if a number is odd or if it is even.<br/>
 * PerformOperation isPrime(): The lambda expression must return if a number is prime or if it is composite.<br/>
 * PerformOperation isPalindrome(): The lambda expression must return if a number is a palindrome or if it is not.<br/>
 * Sample Input<br/>
 * The first line contains an integer, (the number of test cases).<br/>
 * The subsequent lines each describe a test case in the form of space-separated integers:<br/>
 * The first integer specifies the condition to check for ( for Odd/Even, for Prime, or for Palindrome). The second integer denotes the number to be checked.<br/>
 * 5<br/>
 * 1 4<br/>
 * 2 5<br/>
 * 3 898<br/>
 * 1 3<br/>
 * 2 12<br/>
 * <p>
 * Sample Output<br/>
 * <p>
 * EVEN<br/>
 * PRIME<br/>
 * PALINDROME<br/>
 * ODD<br/>
 * COMPOSITE<br/>
 */

interface PerformOperation {

    boolean test(String in);
}

public class Solution {

    private static final Logger logger = LogManager.getLogger("hacker.lambdaexpression");

    enum Command {
        IS_ODD("1", "ODD", "EVEN", isOdd()),
        IS_PRIME("2", "PRIME", "COMPOSITE", isPrime()),
        IS_PALINDROME("3", "PALINDROME", "REGULAR", isPalindrome());

        private final String code;
        private final String positive;
        private final String negative;

        private final PerformOperation op;

        private Command(String code, String positive, String negative, PerformOperation op) {
            this.op = op;
            this.code = code;
            this.positive = positive;
            this.negative = negative;
        }

        static Command getCommand(String code) {
            return Arrays.stream(Command.values()).filter(cmd -> cmd.code.equals(code)).findFirst().orElse(null);
        }

        String getAnswer(String str) {
            if (op.test(str)) {
                return this.positive;
            } else {
                return this.negative;
            }
        }
    }

    public static void main(String[] args) {

//        Example input
//        List<String> commands = Arrays.asList( new String[]{"5", "1 4", "2 5", "3 898", "1 3", "2 12", "3 88", "3 895598", "3 895498","3 89588",});

        logger.info("enter a line separated list of commands followed by a number to execute: [1-3] [0-n]");
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        List<String> commands = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(input != null && !input.isEmpty()) {
            commands.add(input);
            input = in.nextLine();
        }
        process(commands.stream());
    }

    public static void process(Stream<String> in) {
        in.map(str -> {
            String[] vars = str.split(" ");
            if(vars.length !=2) return "";
            return Command.getCommand(vars[0]).getAnswer(vars[1]);
        }).forEach(logger::info);
    }

    private static PerformOperation isOdd() {
        Pattern pattern = Pattern.compile("[13579]$");
        return in -> pattern.matcher(in).find();
    }

    private static PerformOperation isPrime() {

        return in -> {
            int number = Integer.parseInt(in);
            for (int i = 2; i < number / 2; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        };
    }

    private static PerformOperation isPalindrome() {
        return in -> {
            int middle = (in.length() / 2);
            String s1 = in.substring(0,middle);
            String s2 = in.length() % 2 == 0 ? in.substring(middle) : in.substring(middle+1);
            for(int i = 0; i<s1.length(); i++) {
                if(s1.charAt(s1.length()-1-i)!= s2.charAt(i)) {
                    return false;
                }
            }
            return true;
        };
    }

}