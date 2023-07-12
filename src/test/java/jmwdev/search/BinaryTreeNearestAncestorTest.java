package jmwdev.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BinaryTreeNearestAncestorTest {

    @Test
    void testRunProvidedScenario() {
        assertEquals(25, BinaryTreeNearestAncestor.run(25, 100));
    }

    @Test
    void testRunWhenAncestorIsRoot() {
        assertEquals(1, BinaryTreeNearestAncestor.run(16, 27));
    }

    @Test
    void testRunWhenAncestorIsParent() {
        assertEquals(8, BinaryTreeNearestAncestor.run(16, 8));
    }

    @Test
    void testRunWhenAncestorIsBranchNode() {
        assertEquals(8, BinaryTreeNearestAncestor.run(16, 17));
    }

    @Test
    void getAncestorsShouldReturnCorrectList() {
        Integer[] expected = new Integer[]{1, 2, 4, 8};
        List<Integer> actual = BinaryTreeNearestAncestor.getAncestors(8, new ArrayList<>());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual.get(i));
        }
    }

    @Test
    void testConstraintViolationFirstParameterTooLarge() {
        assertThrows(BinaryTreeNearestAncestor.ConstraintViolationException.class, () -> BinaryTreeNearestAncestor.run(1000001, 27));
    }

    @Test
    void testConstraintViolationSecondParameterTooSmall() {
        assertThrows(BinaryTreeNearestAncestor.ConstraintViolationException.class, () -> BinaryTreeNearestAncestor.run(27, 0));
    }
}
