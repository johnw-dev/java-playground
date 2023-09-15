package jmwdev.testdome;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BinarySearchTree {

    static Logger logger = LogManager.getLogger(BinarySearchTree.class);

    private static Boolean contains(Node root, int value) {
        if (root.value() == value) {
            return true;
        } else {
            if (root.value() > value && root.left() != null) {
                return contains(root.left(), value);
            } else if (root.value() < value && root.left() != null) {
                return contains(root.right(), value);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);
        String value = contains(n2, 3).toString();
        logger.info(value);
    }

    record Node(int value, Node left, Node right) {
    }

}