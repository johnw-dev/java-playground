package jmwdev;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {

    public static String[] reverseArray(String[] input) {
        String[] output = new String[input.length];
        int j=input.length-1;
        for(int i=0; i<input.length; i++) {
            output[j--]=input[i];
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter space separate elements");
        String line = scanner.nextLine();
        String[] input = line.split(" ");
        String[] output = reverseArray(input);
        Arrays.stream(output).forEach(s -> System.out.print(s+" "));
    }
}
