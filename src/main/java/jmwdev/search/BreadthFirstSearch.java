package jmwdev.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class BreadthFirstSearch {

    private static final Logger logger = LogManager.getLogger("BreadthFirstSearch");

    private static void log(List<Node> nodes, String message) {
        var stringRepresentation = nodes.stream().map(Node::getId).reduce((a, b) -> a+","+b);
        logger.info("{} - {}", stringRepresentation, message);
    }

    /*
     * Challange: Find the first / lowest common ancestor of 2 nodes in a tree structure<br/>
     * example input:
     *
     */
    public static void main(String[] args) {
        // setup test tree
        Node root = new Node(null, "root");
        Arrays.stream(new int[]{1, 2, 3, 4, 5}).forEach(i -> {
            var child = new Node(root, "c" + i);
            for(int j=1; j <= 5; j++) {
                child.addChild(new Node(root, "gc" + i + j));
            }
            root.addChild(child);
        });
        // set targets
        String n1="gc43", n2="gc11";
        Node node = getNearestCommonAncestor(root, n1, n2).orElseThrow(RuntimeException::new);
        logger.info("result: {}", node.getId());
    }

    /**
     * return the Node with a given id recursively searching breadth-first
     *
     * @param parents list of parent nodes.
     * @param id      the id to search for
     * @return the found Node or empty
     */
     static Optional<Node> getNodeBFS(List<Node> parents, String id) {
        log(parents, "BFS");
        if (!parents.isEmpty()) {
            List<Node> childrenNodes = new ArrayList<>();
            for (Node parent : parents) {
                if (parent.getId().equals(id)) {
                    logger.info("Found {}", parent.getId());
                    return Optional.of(parent);
                } else {
                    childrenNodes.addAll(parent.getChildren());
                }
            }
            return getNodeBFS(childrenNodes, id);
        }
        return Optional.empty();
    }

    /**
     * returns the nearest common ancestor for 2 nodes
     *
     * @param parent the root of the tree to search
     * @param id1    the id of the first node
     * @param id2    the id of the second node
     * @return found ancestor node or empty
     */
    static Optional<Node> getNearestCommonAncestor(Node parent, String id1, String id2) {
        // shortcut
        logger.info("getNearest");
        if (parent.getId().equals(id1) || parent.getId().equals(id2)) {
            logger.info("returning parent: "+parent);
            return Optional.of(parent.getParent() != null ? parent.getParent() : parent);
        }
        List<Node> n1 = getNodeBFS(parent.getChildren(), id1).orElseThrow(NotFoundException::new).getAncestors();
        List<Node> n2 = getNodeBFS(parent.getChildren(), id2).orElseThrow(NotFoundException::new).getAncestors();
        Optional<Node> commonAncestor = Optional.empty();
        for (int i = 0; i < (Math.min(n1.size(), n2.size())); i++) {
            if (n1.get(i).equals(n2.get(i))) {
                commonAncestor = Optional.of(n1.get(i));
            }
        }
        return commonAncestor;
    }

    static class Node {
        private final Node parent;
        private final List<Node> children;
        private final String id;

        public Node(Node parent, String id) {
            this.parent = parent;
            this.id = id;
            this.children = new ArrayList<>();
        }

        public Node getParent() {
            return parent;
        }

        public String getId() {
            return id;
        }

        public void addChild(Node child) {
            this.children.add(child);
        }

        public List<Node> getChildren() {
            return children;
        }

        public List<Node> getAncestors(List<Node> ancestors) {
            if (getParent() != null) {
                ancestors.add(0, getParent());
                return getParent().getAncestors(ancestors);
            }
            return ancestors;
        }

        public List<Node> getAncestors() {
            return this.getAncestors(new ArrayList<>());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(id, node.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    private static class NotFoundException extends RuntimeException {

    }


}
