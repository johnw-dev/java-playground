package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ReverseWord {
    static Logger logger = LogManager.getLogger("ReferenceTable");

    public static String reverseWords(String input) {
        String output = "";
        if (input == null) {
            return output;
        }
        for (String word : input.split(" ")) {
            char[] wordc = word.toCharArray();
            char[] rword = new char[wordc.length];
            int j = word.length() - 1;
            for (int i = 0; i < word.length(); i++) {
                rword[j--] = wordc[i];
            }
            output = output.concat(" " + new String(rword));
        }
        output = output.substring(1, input.length() + 1);
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("enter string:");
        String output = reverseWords(scanner.nextLine());
        logger.info(output);
    }
}
