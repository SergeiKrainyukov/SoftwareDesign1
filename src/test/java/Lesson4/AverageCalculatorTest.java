package Lesson4;

import org.junit.Test;

import static org.junit.Assert.*;

public class AverageCalculatorTest {

    @Test
    public void testCalculateAverage_withPositiveNumbers() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {1, 2, 3, 4, 5};
        assertEquals(3.0, calculator.calculateAverage(numbers), 0.001);
    }

    @Test
    public void testCalculateAverage_withNegativeNumbers() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {-1, -2, -3, -4, -5};
        assertEquals(-3.0, calculator.calculateAverage(numbers), 0.001);
    }

    @Test
    public void testCalculateAverage_withMixedNumbers() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {-1, 0, 1};
        assertEquals(0.0, calculator.calculateAverage(numbers), 0.001);
    }

    @Test
    public void testCalculateAverage_withSingleElement() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {5};
        assertEquals(5.0, calculator.calculateAverage(numbers), 0.001);
    }

    @Test
    public void testCalculateAverage_withEmptyArray() {
        AverageCalculator calculator = new AverageCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(new int[]{});
        });
        assertEquals("Массив не должен быть пустым или равным null.", exception.getMessage());
    }

    @Test
    public void testCalculateAverage_withNullArray() {
        AverageCalculator calculator = new AverageCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(null);
        });
        assertEquals("Массив не должен быть пустым или равным null.", exception.getMessage());
    }
}