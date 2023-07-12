package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ReverseArray {

    static Logger logger = LogManager.getLogger("ReverseArray");

    public static String[] reverseArray(String[] input) {
        String[] output = new String[input.length];
        int j = input.length - 1;
        for (String s : input) {
            output[j--] = s;
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter space separate elements");
        String line = scanner.nextLine();
        String[] input = line.split(" ");
        String[] output = reverseArray(input);
        String outputStr = String.join(" ", output);
        logger.info(outputStr);
    }
}
