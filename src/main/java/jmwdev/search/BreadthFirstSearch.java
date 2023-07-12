package jmwdev.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BreadthFirstSearch {

    private static final Logger logger = LogManager.getLogger("BreadthFirstSearch");

    /*
     * Challange: Find the first / lowest common ancestor of 2 nodes in a tree structure<br/>
     * example input:
     *
     */
    public static void main(String[] args) {
        Node tree = new Node(null, "root");
        Node node = getNearestCommonAncestor(tree, args[0], args[1]).orElseThrow(RuntimeException::new);
        logger.debug(node.getId());
    }

    /**
     * return the Node with a given id recursively searching breadth-first
     *
     * @param parents list of parent nodes.
     * @param id      the id to search for
     * @return the found Node or empty
     */
    public static Optional<Node> getNodeBFS(List<Node> parents, String id) {
        if (!parents.isEmpty()) {
            List<Node> childrenNodes = new ArrayList<>();
            for (Node parent : parents) {
                if (parent.getId().equals(id)) {
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
    public static Optional<Node> getNearestCommonAncestor(Node parent, String id1, String id2) {
        // shortcut
        if (parent.getId().equals(id1) || parent.getId().equals(id2)) {
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
