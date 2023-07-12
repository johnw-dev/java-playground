package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class FactorialGenerator {
    static Logger logger = LogManager.getLogger("FactorialGenerator");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Provide number for factorial generation");
        String input = scanner.next();
        int base = Integer.parseInt(input);
        int result = generateFactorial(base);
        logger.info(result);
    }

    public static int generateFactorial(int base) {
        int result = 1;
        for (int i = 1; i <= base; i++) {
            result *= i;
        }
        return result;
    }
}
