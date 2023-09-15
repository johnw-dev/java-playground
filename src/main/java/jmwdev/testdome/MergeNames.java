package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeNames {
    static Logger log = LogManager.getLogger(MergeNames.class);


    public static String[] uniqueNames(String[] names1, String[] names2) {
        // Solution
        List<String> merged = new ArrayList<>(Arrays.asList(names1));
        Arrays.stream(names2).filter(name -> !merged.contains(name)).forEach(merged::add);
        return merged.toArray(new String[0]);
    }


    public static void main(String[] args) {
        String[] names1 = new String[]{"Ava", "Emma", "Olivia"};
        String[] names2 = new String[]{"Olivia", "Sophia", "Emma"};
        String result = String.join(", ", MergeNames.uniqueNames(names1, names2));
        log.info(result); // should print Ava, Emma, Olivia, Sophia

    }

}