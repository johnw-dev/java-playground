package jmwdev.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class DepthFirstSearch {
    private static final Logger logger = LogManager.getLogger("DepthFirstSearch");

    /**
     * recursive depth first search for node which returns the target node as the first element of it's lineage in the tree
     *
     * @param parent node from which to start search
     * @param id     the id of the node to find
     * @return the found Node or null
     */
    public static Optional<Node> getNodeDFS(final Node parent, final String id) {
        if (parent.getId().equals(id)) {
            return Optional.of(parent);
        } else {
            for (Node child : parent.getChildren()) {
                Optional<Node> foundNode = getNodeDFS(child, id);
                if (foundNode.isPresent()) {
                    return foundNode;
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<Node> getNearestCommonAncestor(Node parent, String id1, String id2) {
        // shortcut
        if (parent.getId().equals(id1) || parent.getId().equals(id2)) {
            return Optional.of(parent.getParent() != null ? parent.getParent() : parent);
        }
        List<Node> n1 = getNodeDFS(parent, id1).orElseThrow(NotFoundException::new).getAncestors();
        List<Node> n2 = getNodeDFS(parent, id2).orElseThrow(NotFoundException::new).getAncestors();
        Optional<Node> commonAncestor = Optional.empty();
        for (int i = 0; i < (Math.min(n1.size(), n2.size())); i++) {
            if (n1.get(i).equals(n2.get(i))) {
                commonAncestor = Optional.of(n1.get(i));
            }
        }
        return commonAncestor;
    }

    public static void main(String[] args) {
        Node root = new Node(null, "root");
        Arrays.stream(new int[]{1, 2, 3, 4, 5}).forEach(i -> root.addChild(new Node(root, "r" + i)));
        Node node = getNearestCommonAncestor(root, "r1", "r3").orElseThrow(NotFoundException::new);
        logger.info(node.getId());
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
            return Objects.equals(id, node.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class NotFoundException extends RuntimeException {

        public NotFoundException() {
            super();
        }
    }
}
