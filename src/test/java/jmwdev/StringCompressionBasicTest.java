package jmwdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCompressionBasicTest {

    @ParameterizedTest
    @Disabled
    @CsvSource({"aaaaabbbbcccdde,a5b4c3d2e1"})
    void testCompression(ArgumentsAccessor args) {
        String input = args.get(0, String.class);
        String expected = args.get(1, String.class);
        assertEquals(expected, StringCompressionBasic.compress(input));
    }
}
