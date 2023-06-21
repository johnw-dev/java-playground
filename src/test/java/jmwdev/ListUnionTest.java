package jmwdev;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static jmwdev.ListUnion.uniqueNamesCollections;
import static jmwdev.ListUnion.uniqueNamesClassic;

public class ListUnionTest {

    @Test
    public void uniqueNamesCollectionsShouldReturnSameSetWhenAllElementsConflict() {
        String[] test = {"a", "b", "c"};
        assertArrayEquals(test, uniqueNamesCollections(test, test));
        assertArrayEquals(test, uniqueNamesClassic(test, test));

    }

    @Test
    public void uniqueNamesCollectionsShouldReturnAllElementsWhenMutuallyExclusive() {
        String[] expected = {"a", "b", "c", "d", "e", "f"};
        assertArrayEquals(expected, uniqueNamesCollections(new String[]{"a", "b", "c"}, new String[]{"d", "e", "f"}));
        assertArrayEquals(expected, uniqueNamesClassic(new String[]{"a", "b", "c"}, new String[]{"d", "e", "f"}));
    }

    @Test
    public void uniqueNamesCollectionsShouldReturnAllUniqueElementsWhenSomeElementsConflict() {
        String[] expected = {"a", "b", "c", "d", "e", "f"};
        assertArrayEquals(expected, uniqueNamesCollections(new String[]{"a", "b", "c", "d", "e"}, new String[]{"d", "e", "f"}));
        assertArrayEquals(expected, uniqueNamesClassic(new String[]{"a", "b", "c", "d", "e"}, new String[]{"d", "e", "f"}));

    }


}
