package jmwdev;

import java.util.Scanner;

public class FactorialGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide number for factorial generation");
        String input = scanner.next();
        Integer base = Integer.parseInt(input);
        int result = generateFactorial(base);
        System.out.println(result);
    }

    public static int generateFactorial(int base) {
        int result = 1;
        for(int i=1; i<=base; i++) {
            result *=i;
        }
        return result;
    }
}
