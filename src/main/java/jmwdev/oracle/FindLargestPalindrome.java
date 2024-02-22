package jmwdev.oracle;

import java.util.*;

public class FindLargestPalindrome {
    static int counter = 0;

    static void addOrNot(Set<SubStr> set, SubStr val, String original) {
        if(val.length() > 2 && !set.contains(val)) {
            var substr = original.substring(val.index(), val.length() + val.index());
            var sb = new StringBuilder(substr);
            var reverse = new StringBuilder(sb).reverse();
            if (substr.contentEquals(reverse)) {
                set.add(val);
            }
        }
    }

    static Set<SubStr> getSubStrings(String input) {
        Set<SubStr> results = new HashSet<>();
        var root = new SubStr(0, input.length());
        getSubStrings(input, root, results);
        String resStr = results.stream().map(a -> input.substring(a.index(), a.index()+a.length())).reduce((a,b) -> a+","+b).orElseThrow();
        System.out.printf("input: %s size: %d results: %s #: %d counter: %d %n", input, input.length(), resStr, results.size(), counter);
        return results;
    }

    static void getSubStrings(String original, SubStr str, Set<SubStr> results) {
        counter++;
        addOrNot(results, str, original);
        if(str.length>1) {
            for (int i = 2; i < str.length(); i++) {
                counter++;
                addOrNot(results, new SubStr(str.index(), i), original);
            }
            getSubStrings(original, new SubStr(str.index() + 1, str.length() - 1), results);
        }
    }

    static Optional<String> getLargestPallindrome(String input) {
        counter = 0;
        return getSubStrings(input).stream().reduce((a,b) -> a.length()>b.length()? a: b).map(a ->input.substring(a.index(), a.index()+a.length()));
    }

    record SubStr(int index, int length) {}

    public static void main(String[] args) {
        System.out.printf("result: %s%n", getLargestPallindrome("ABBAfgf").orElseThrow());
        System.out.printf("result: %s%n", getLargestPallindrome("abcdfgfabcdABBA").orElseThrow());
        System.out.printf("result: %s%n", getLargestPallindrome("abcdfgfBAAAABabcdABBA").orElseThrow());
    }
}
