package jmwdev;

import org.junit.jupiter.api.Test;
import static jmwdev.FactorialGenerator.generateFactorial;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialGeneratorTest {

    @Test
    public void testFactorial() {
        assertEquals(1, generateFactorial(1), "1");
        assertEquals(2, generateFactorial(2), "2");
        assertEquals(6, generateFactorial(3), "3");
        assertEquals(24, generateFactorial(4), "4");
    }

}
