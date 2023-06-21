package jmwdev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUnion {
    public static void main(String[] args) {
        String[] a1 = {"a", "b", "c", "d", "e"};
        String[] a2 = {"d", "e", "f", "g", "h"};
        for(String res : uniqueNamesCollections(a1, a2)) {
            System.out.println("Classic:"+res);
        }

        for(String res : uniqueNamesCollections(a1, a2)) {
            System.out.println("Streams:"+res);
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
        for(int i=0; i<a1.length; i++) {
                result.add(a1[i]);
        }
        for(int j=0; j<a2.length; j++) {
            boolean unique = true;
            for(int i=0; i<result.size(); i++) {
                if(a2[j].equals(result.get(i))) {
                    unique = false;
                }
            }
            if(unique) {
                result.add(a2[j]);
            }
        }
        return result.toArray(String[]::new);
    }
}