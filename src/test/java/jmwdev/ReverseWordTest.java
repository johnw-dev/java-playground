package jmwdev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseWordTest {

    @ParameterizedTest
    @CsvSource({
            "Jane Doe, enaJ eoD",
            "John Doe, nhoJ eoD",
            ",",
            "JohnDoe,eoDnhoJ"
    })
    public void testReverseWord(ArgumentsAccessor args) {
        String a0 = args.get(0, String.class);
        String input = a0 != null? a0 : "";
        String a1 = args.get(1, String.class);
        String expected = a1 != null? a1 : "";
        assertEquals(expected, ReverseWord.reverseWords(input));
    }
}
