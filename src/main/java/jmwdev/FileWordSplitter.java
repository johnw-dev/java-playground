package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileWordSplitter {
    static String file = "test_string_consumption.txt";
    static Logger logger = LogManager.getLogger("FileWordSplitter");


    public static void main(String[] args) throws FileNotFoundException {
        List<String> words;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            words = getWords(br.lines());
            for (String word : words) {
                logger.info(word);
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static List<String> getWords(Stream<String> stream) {
        List<String> words = new ArrayList<>();
        stream.forEach(a -> Arrays.stream(a.split(" "))
                .filter(s -> s.length() > 0)
                .forEach(words::add));
        return words;
    }
}
