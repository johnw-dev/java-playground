package jmwdev;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static jmwdev.FileWordSplitter.getWords;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FileWordSplitterTest {

    @Test
    void testGetWordsBasic() {
        String[] lines = {"I have a   funny feeling", "this is going to", "work"};
        List<String> words = getWords(Stream.of(lines));
        assertArrayEquals(new String[]{"I", "have", "a", "funny", "feeling", "this", "is", "going", "to", "work"}, words.toArray());
    }
}
