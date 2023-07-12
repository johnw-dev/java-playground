package jmwdev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReverseWord {

    public static String reverseWords(String input) {
        String output = "";
        if(input == null) {
            return output;
        }
        for(String word : input.split(" ")){
            char[] wordc = word.toCharArray();
            char[] rword = new char[wordc.length];
            int j=word.length()-1;
            for(int i=0; i< word.length();i++) {
                rword[j--] = wordc[i];
            }
            output = output.concat(" " + new String(rword));
        }
        output = output.substring(1,input.length()+1);
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter string:");
        System.out.println(reverseWords(scanner.nextLine()));
    }
}
