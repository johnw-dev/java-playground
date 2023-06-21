package jmwdev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileWordSplitter {
    static String file ="test_string_consumption.txt";

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> words = getWords(br.lines());
        for(String word: words) {
            System.out.println(word);
        }
    }

    public static List<String> getWords(Stream<String> stream ) {
        List<String> words = new ArrayList<>();
        stream.forEach(a -> {
            Arrays.stream(a.split(" "))
                    .filter(s -> s.length()>0)
                    .forEach(s -> words.add(s));
        });
        return words;
    }
}
