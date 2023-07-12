package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListUnion {
    static Logger logger = LogManager.getLogger("ListUnion");

    public static void main(String[] args) {
        String[] a1 = {"a", "b", "c", "d", "e"};
        String[] a2 = {"d", "e", "f", "g", "h"};
        for (String res : uniqueNamesCollections(a1, a2)) {
            logger.info("Classic: {}", res);
        }

        for (String res : uniqueNamesCollections(a1, a2)) {
            logger.info("Streams: {}", res);
        }
    }

    public static String[] uniqueNamesCollections(String[] a1, String[] a2) {
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(a1));
        result.addAll(Arrays.stream(a2).filter(a -> !result.contains(a)).toList());
        return result.toArray(String[]::new);
    }

    public static String[] uniqueNamesClassic(String[] a1, String[] a2) {
        List<String> result = new ArrayList<>();
        Collections.addAll(result, a1);
        for (String s : a2) {
            boolean unique = true;
            for (String value : result) {
                if (s.equals(value)) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                result.add(s);
            }
        }
        return result.toArray(String[]::new);
    }
}