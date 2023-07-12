package jmwdev.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/*
Find the first (lowest) common ancestor of two nodes in a binary tree without allocating any tree data structure in memory.

The first common ancestor of two nodes v and w is the lowest (i.e. deepest) node that has both v and w as descendants.

Consider the binary tree's indexes starting from 1 in the root, increasing from the leftmost node to the right at each level. (Standard tree node indexing from left to right)

INPUT
int    index1
int    index2

OUTPUT
int    indexCommonAncestor

CONSTRAINTS
1 <= index1 <= 1000000
1 <= index2 <= 1000000

EXAMPLES
Input
13, 15
Output
3

Input
11, 13
Output
1

Input
10, 11
Output
5
 */
class BinaryTreeNearestAncestor {

    private static final Logger logger = LogManager.getLogger("BinaryTreeNearestAncestor");


    /**
     * validates the constraints of the input
     *
     * @param param indicates which input is being validated
     * @param value the value of the input
     */
    private static void validateInput(int param, int value) {
        if (value < 1 || value > 1000000) {
            throw new ConstraintViolationException(String.format("index %d out of bounds: %d", param, value));
        }
    }

    public static void main(String[] args) {
        logger.info("Enter two numbers: [1-1000000] [1-1000000]");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Integer> ints = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
        int ancestor = run(ints.get(0), ints.get(1));
        logger.info("nearest ancestor: {}", ancestor);
    }

    public static int run(int index1, int index2) {
        validateInput(1, index1);
        validateInput(2, index2);
        Integer commonAncestor = 0;

        List<Integer> a1 = getAncestors(index1, new ArrayList<>());
        List<Integer> a2 = getAncestors(index2, new ArrayList<>());

        for (int i = 0; i < (Math.min(a1.size(), a2.size())); i++) {
            if (a1.get(i).equals(a2.get(i))) {
                commonAncestor = a1.get(i);
            }
        }
        return commonAncestor;
    }

    /**
     * gets the list of ancestors for a child node
     *
     * @param child   the child node from which to start building the lineage
     * @param lineage the list of ancestors calculated thus far
     * @return the list of ancestors
     */
    public static List<Integer> getAncestors(int child, List<Integer> lineage) {
        lineage.add(0, child);
        if (child > 1) {
            int parent = child % 2 == 0 ? child / 2 : (child - 1) / 2;
            return getAncestors(parent, lineage);
        }
        return lineage;
    }

    public static class ConstraintViolationException extends RuntimeException {

        public ConstraintViolationException(String message) {
            super(message);
        }
    }
}