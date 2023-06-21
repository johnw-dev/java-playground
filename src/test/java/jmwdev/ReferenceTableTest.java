package jmwdev;

import org.junit.jupiter.api.Test;

import java.util.List;

import static jmwdev.ReferenceTable.getTable;
import static jmwdev.ReferenceTable.Operation;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReferenceTableTest {

    @Test
    public void testReferenceTableAddition() {
        List<ReferenceTable.Calculation> actual = getTable(2, Operation.ADD);
        int[] expected = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for(int i=0;i<expected.length;i++) {
            final int fi=i;
            assertAll(
                    () -> assertEquals(String.valueOf(expected[fi]), actual.get(fi).getResult()),
                    () -> assertEquals(String.format("| %d + %d = %d |",2, fi, expected[fi]), actual.get(fi).toString())
            );
        }
    }

    @Test
    public void testReduceFraction() {
        assertAll(
                () -> assertEquals("1/2", Operation.reduceFraction(5,10)),
                () -> assertEquals("1/2", Operation.reduceFraction(6,12)),
                () -> assertEquals("1/3", Operation.reduceFraction(3,9)),
                () -> assertEquals("3/1", Operation.reduceFraction(9,3)),
                () -> assertEquals("1/1", Operation.reduceFraction(3,3)),
                () -> assertEquals("5/6", Operation.reduceFraction(5,6)),
                () -> assertEquals("1/100", Operation.reduceFraction(1,100))

                );
    }
}
