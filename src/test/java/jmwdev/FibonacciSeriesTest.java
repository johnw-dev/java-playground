package jmwdev;

import org.junit.jupiter.api.Test;

import java.util.List;

import static jmwdev.FibonacciSeries.getSeries;
import static org.junit.jupiter.api.Assertions.*;

public class FibonacciSeriesTest {

    private void assertResult(Integer[] expected, List<Integer> result, String test) {
        assertAll(test,
                () -> assertEquals(expected.length, result.size()),
                () -> assertArrayEquals(expected, result.toArray())
        );

    }

    @Test
    public void testFibonacci() {
        assertThrows(IllegalArgumentException.class, () -> getSeries(0), "0 element");
        assertResult(new Integer[]{0}, getSeries(1), "1 element");
        assertResult(new Integer[]{0, 1}, getSeries(2), "2 elements");
        assertResult(new Integer[]{0, 1, 1}, getSeries(3), "3 elements");
        assertResult(new Integer[]{0, 1, 1}, getSeries(3), "3 elements");
        assertResult(new Integer[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34}, getSeries(10), "10 elements");
    }
}
