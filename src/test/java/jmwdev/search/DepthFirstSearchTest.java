package jmwdev.search;

import jmwdev.search.DepthFirstSearch.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DepthFirstSearchTest {

    int counter = 1;

    String getNextName(Node n) {
        String parentId = n.getId();
        return parentId + "-n" + counter++;
    }

    void addGeneration(Node parent, int count) {
        for (int i = 0; i < count; i++) {
            Node child = new Node(parent, getNextName(parent));
            parent.addChild(child);
        }
    }

    Node getCommonTree() {
        Node root = new Node(null, "n0");
        addGeneration(root, 3);
        for (Node g1 : root.getChildren()) {
            addGeneration(g1, 2);
            for (Node g2 : g1.getChildren()) {
                addGeneration(g2, 2);
            }
        }
        return root;
    }

    @Test
    void testTreeWhenRootIsNearestDifferentDepths() {
        Node actual = DepthFirstSearch.getNearestCommonAncestor(getCommonTree(), "n0-n1", "n0-n2-n11-n14").orElse(null);
        assertNotNull(actual);
        assertEquals("n0", actual.getId());
    }

    @Test
    void testTreeWhenRootIsNearestSameDepths() {
        Node actual = DepthFirstSearch.getNearestCommonAncestor(getCommonTree(), "n0-n3-n17-n21", "n0-n2-n11-n14").orElse(null);
        assertNotNull(actual);
        assertEquals("n0", actual.getId());
    }


    @Test
    void testTreeWhenBranchNodeIsNearest() {
        Node actual = DepthFirstSearch.getNearestCommonAncestor(getCommonTree(), "n0-n2-n10-n12", "n0-n2-n11-n14").orElse(null);
        assertNotNull(actual);
        assertEquals("n0-n2", actual.getId());
    }

    @Test
    void testTreeWhenBothReferToSameNode() {
        Node actual = DepthFirstSearch.getNearestCommonAncestor(getCommonTree(), "n0-n2-n10-n12", "n0-n2-n10-n12").orElse(null);
        assertNotNull(actual);
        assertEquals("n0-n2-n10", actual.getId());
    }
}
