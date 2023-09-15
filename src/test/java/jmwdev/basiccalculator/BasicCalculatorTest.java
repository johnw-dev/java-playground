package jmwdev.basiccalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicCalculatorTest {
    @Test
    void calculateResultSimpleAddition() {
        assertEquals(2.0, BasicCalculator.calculate("1+1"));
    }

    @Test
    void calculateResultSimpleAdditionDecimal() {
        assertEquals(2.5, BasicCalculator.calculate("1+1.5"));
    }

    @Test
    void calculateResultSimpleSubtraction() {
        assertEquals(3.0, BasicCalculator.calculate("4-1"));
    }

    @Test
    void calculateResultSimpleMultiplication() {
        assertEquals(15.0, BasicCalculator.calculate("3*5"));
    }

    @Test
    void calculateResultMultipleCalculations() {
        assertEquals(12.0, BasicCalculator.calculate("3+5+4"));
    }

    @Test
    void calculateResultNegativeNumbersAndSubtraction() {
        assertEquals(-16.0, BasicCalculator.calculate("-3*5-1"));
    }

    @Test
    void calculateResultDoubleNegative() {
        assertEquals(5.0, BasicCalculator.calculate("2--3"));
    }

    @Test
    void calculateResultWithBrackets() {
        assertEquals(2.0, BasicCalculator.calculate("(1+1)"));
    }

    @Test
    void calculateResultWithMultipleBrackets() {
        assertEquals(60.0, BasicCalculator.calculate("(2+8)*(1+5)"));
    }

    @Test
    void calculateResultWithMultipleLayerBrackets() {
        assertEquals(250.0, BasicCalculator.calculate("(2+8)*((3+2)*(1+4))"));
    }

    @Test
    void calculateResultWithLotsOfNestedBrackets() {
        assertEquals(10.42, BasicCalculator.calculate("(((2^8)*2)+9)/(5*(2+8))"));

    }

    @Test
    void calculateResultBrokenBracketsShouldIgnore() {
        assertEquals(2.0, BasicCalculator.calculate("(1+1"));
    }

    @Test
    void calculateResultEmptyBracketsShouldIgnore() {
        assertEquals(1.0, BasicCalculator.calculate("()+1"));
    }

    @Test
    void calculateResultNumberInBracketsShouldIgnore() {
        assertEquals(2.0, BasicCalculator.calculate("(1)+1"));
    }
}
