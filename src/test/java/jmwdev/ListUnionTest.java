package jmwdev;

import org.junit.jupiter.api.Test;

import static jmwdev.ListUnion.uniqueNamesClassic;
import static jmwdev.ListUnion.uniqueNamesCollections;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ListUnionTest {

    @Test
    void uniqueNamesCollectionsShouldReturnSameSetWhenAllElementsConflict() {
        String[] test = {"a", "b", "c"};
        assertArrayEquals(test, uniqueNamesCollections(test, test));
        assertArrayEquals(test, uniqueNamesClassic(test, test));

    }

    @Test
    void uniqueNamesCollectionsShouldReturnAllElementsWhenMutuallyExclusive() {
        String[] expected = {"a", "b", "c", "d", "e", "f"};
        assertArrayEquals(expected, uniqueNamesCollections(new String[]{"a", "b", "c"}, new String[]{"d", "e", "f"}));
        assertArrayEquals(expected, uniqueNamesClassic(new String[]{"a", "b", "c"}, new String[]{"d", "e", "f"}));
    }

    @Test
    void uniqueNamesCollectionsShouldReturnAllUniqueElementsWhenSomeElementsConflict() {
        String[] expected = {"a", "b", "c", "d", "e", "f"};
        assertArrayEquals(expected, uniqueNamesCollections(new String[]{"a", "b", "c", "d", "e"}, new String[]{"d", "e", "f"}));
        assertArrayEquals(expected, uniqueNamesClassic(new String[]{"a", "b", "c", "d", "e"}, new String[]{"d", "e", "f"}));

    }


}
