package jmwdev;

import org.junit.jupiter.api.Test;

import static jmwdev.ReverseArray.reverseArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseArrayTest {

    @Test
    void testReverseArray() {
        String[] input = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "0", "0", "1"};
        String[] expected = {"1", "0", "0", "0", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
        String[] actual = reverseArray(input);
        assertArrayEquals(expected, actual);
    }


}
